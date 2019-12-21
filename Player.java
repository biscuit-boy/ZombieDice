public class Player {
    private int points; // point counter
    private String name; // identifier for printing purposes

    public Player(String name){
        points = 0;

        this.name = name;
    }

    // adds x points to points
    public void addPoint(int x){
        points += x;
    }

    // returns points
    public int getPoints(){
        return points;
    }

    // returns name
    public String toString(){
        return name;
    }

    public boolean hasWon(){
        return (points >= 13);
    }
}
