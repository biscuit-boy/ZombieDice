import java.util.ArrayList;

public class TurnHandler {
    private PlayerSet players;
    private IOHandler printer;

    private int turn; // turn counter

    public TurnHandler(PlayerSet players, IOHandler printer){
        this.players = players;
        this.printer = printer;

        turn = 0;
    }

    // returns current player
    public Player getCurrentPlayer(){
        return players.getPlayer(turn);
    }

    // takes turn
    public void takeTurn(DiceSet allDice){
        Player player = getCurrentPlayer(); // current player
        ++ turn;

        int shotsTaken = 0;
        int roundPoints = 0;

        DiceSet remainingDice = new DiceSet(allDice); // dice not rolled yet
        DiceSet rolledDice = new DiceSet(); // dice already rolled
        DiceSet currentDice = new DiceSet(); // dice currently being rolled.

        remainingDice.shuffle();

        currentDice.addDie(remainingDice.draw());
        currentDice.addDie(remainingDice.draw());
        currentDice.addDie(remainingDice.draw());

        while (remainingDice.length() > 0) {
            remainingDice.shuffle();

            printer.printState(turn, roundPoints, shotsTaken, remainingDice.diceInfo());

            boolean choice = printer.getChoice(); // user decision

            if (choice) {
                currentDice.roll();

                int numBrain = currentDice.countNumber(sideType.BRAIN); // finds number of brains
                int numShot = currentDice.countNumber(sideType.SHOTGUN); // finds number of shots

                printer.printDice(currentDice); // prints rolled dice
                printer.printEvents(numBrain, numShot); // prints events of round

                shotsTaken += numShot;

                // if player dead
                if (shotsTaken >= 3) {
                    printer.printDeath();
                    printer.endRound(0);
                    break;
                }

                roundPoints += numBrain;

                // if player has won
                if (player.getPoints() + roundPoints >= 13){
                    player.addPoint(roundPoints);
                    break;
                }

                // all non-runners added to a toMove array that is put in rolledDice
                ArrayList<Die> toMove = new ArrayList<>();

                for (Die die : currentDice.getDice()) {
                    if (die.getFace() != sideType.RUNNER) {
                        toMove.add(die);
                    }
                }

                rolledDice.addAll(toMove);
                currentDice.removeAll(toMove);

                // adds number of dice move from remainingDice to currentDice
                for (int i = 0; i < toMove.size(); ++i){
                    remainingDice.shuffle();
                    currentDice.addDie(remainingDice.draw());
                }

            }

            // if player ends round
            else {
                player.addPoint(roundPoints);
                printer.endRound(roundPoints);
                break;
            }
        }
    }
}
