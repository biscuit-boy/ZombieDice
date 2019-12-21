import java.util.ArrayList;

public class PlayerSet {
    private ArrayList<Player> players;

    public PlayerSet(){
        players = new ArrayList<Player>();
    }

    public PlayerSet(ArrayList<Player> players){
        this.players = players;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public int length(){
        return players.size();
    }

    public Player getPlayer(int x){
        return players.get(x % length());
    }

    public boolean gameOver(){
        for (Player player : players){
            if (player.hasWon()){
                return true;
            }
        }

        return false;
    }

    public Player getWinner(){
        for (Player player : players){
            if (player.hasWon()){
                return player;
            }
        }

        return players.get(0);
    }
}
