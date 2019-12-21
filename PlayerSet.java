import java.util.ArrayList;

public class PlayerSet {
    private ArrayList<Player> players; // all players

    public PlayerSet(){
        players = new ArrayList<Player>();
    }

    // adds player to players
    public void addPlayer(Player player){
        players.add(player);
    }

    // gets size
    public int length(){
        return players.size();
    }

    // gets player at index x
    public Player getPlayer(int x){
        return players.get(x % length());
    }

    // checks if a player has won
    public boolean gameOver(){
        for (Player player : players){
            if (player.hasWon()){
                return true;
            }
        }

        return false;
    }

    // returns the winning player
    public Player getWinner(){
        for (Player player : players){
            if (player.hasWon()){
                return player;
            }
        }

        return players.get(0);
    }
}
