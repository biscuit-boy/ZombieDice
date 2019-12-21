import java.util.ArrayList;

public class Game {
    private PlayerSet players;
    private DiceSet allDice;
    private TurnHandler turnHandler;
    private IOHandler printer;

    static final int NUM_GREEN = 6;
    static final int NUM_YELLOW = 4;
    static final int NUM_RED = 3;

    public Game(){
        players = new PlayerSet();

        Player player1 = new Player("Player One");
        Player player2 = new Player("Player Two");

        players.addPlayer(player1);
        players.addPlayer(player2);

        this.allDice = generateDice();

        this.printer = new IOHandler(this.players);

        this.turnHandler = new TurnHandler(this.players, this.printer);
    }

    public Game(ArrayList<Player> players){
        this.players = new PlayerSet(players);

        this.allDice = generateDice();

        this.printer = new IOHandler(this.players);

        this.turnHandler = new TurnHandler(this.players, this.printer);
    }

    public void play(){
        printer.intro();
        while (!gameOver()){
            takeTurn();
        }

        printer.endGame(players.getWinner());
    }

    private DiceSet generateDice(){
        DiceSet dice = new DiceSet();

        for (int i = 0; i < NUM_GREEN; ++i){
            Die temp = new Die(colorType.GREEN);
            dice.addDie(temp);
        }

        for (int i = 0; i < NUM_YELLOW; ++i){
            Die temp = new Die(colorType.YELLOW);
            dice.addDie(temp);
        }

        for (int i = 0; i < NUM_RED; ++i){
            Die temp = new Die(colorType.RED);
            dice.addDie(temp);
        }

        return dice;
    }

    private void takeTurn(){
        turnHandler.takeTurn(allDice);
    }

    public Player currentPlayer(){
        return turnHandler.getCurrentPlayer();
    }

    private boolean gameOver(){
        return players.gameOver();
    }

    public PlayerSet getPlayers(){
        return players;
    }

}
