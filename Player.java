public class Player {
    private int points;
    private String name;

    public Player(String name){
        points = 0;

        this.name = name;
    }

    public void addPoint(int x){
        points += x;
    }

    public int getPoints(){
        return points;
    }

    public String toString(){
        return name;
    }

    public boolean hasWon(){
        return (points >= 13);
    }
}
