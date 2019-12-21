import java.util.ArrayList;

public class TurnHandler {
    private PlayerSet players;
    private IOHandler printer;

    private int turn;

    public TurnHandler(PlayerSet players, IOHandler printer){
        this.players = players;
        this.printer = printer;

        turn = 0;
    }


    public Player getCurrentPlayer(){
        return players.getPlayer(turn);
    }


    public void takeTurn(DiceSet allDice){
        Player player = getCurrentPlayer();
        ++ turn;

        int shotsTaken = 0;
        int roundPoints = 0;

        DiceSet remainingDice = new DiceSet(allDice);
        DiceSet rolledDice = new DiceSet();
        DiceSet currentDice = new DiceSet();

        remainingDice.shuffle();

        currentDice.addDie(remainingDice.draw());
        currentDice.addDie(remainingDice.draw());
        currentDice.addDie(remainingDice.draw());

        while (remainingDice.length() > 0) {
            remainingDice.shuffle();

            printer.printState(turn, roundPoints, shotsTaken, remainingDice.diceInfo());

            boolean choice = printer.getChoice();

            if (choice) {
                currentDice.roll();

                int numBrain = currentDice.countNumber(sideType.BRAIN);
                int numShot = currentDice.countNumber(sideType.SHOTGUN);

                printer.printDice(currentDice);
                printer.printEvents(numBrain, numShot);

                shotsTaken += numShot;

                if (shotsTaken >= 3) {
                    printer.printDeath();
                    printer.endRound(0);
                    break;
                }

                roundPoints += numBrain;

                if (player.getPoints() + roundPoints >= 13){
                    player.addPoint(roundPoints);
                    break;
                }

                ArrayList<Die> toMove = new ArrayList<>();

                for (Die die : currentDice.getDice()) {
                    if (die.getFace() != sideType.RUNNER) {
                        toMove.add(die);
                    }
                }

                rolledDice.addAll(toMove);
                currentDice.removeAll(toMove);

                for (int i = 0; i < toMove.size(); ++i){
                    remainingDice.shuffle();
                    currentDice.addDie(remainingDice.draw());
                }

            } else {
                player.addPoint(roundPoints);
                printer.endRound(roundPoints);
                break;
            }
        }
    }
}
