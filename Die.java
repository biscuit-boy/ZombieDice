import java.util.ArrayList;

enum sideType{
    BRAIN, SHOTGUN, RUNNER;
}

enum colorType{
    GREEN, YELLOW, RED;
}

public class Die {
    private sideType face;
    private colorType color;
    private ArrayList<sideType> sides;

    public Die(colorType color){
        this.color = color;

        sides = generateSides();

        roll();
    }

    private ArrayList<sideType> generateSides(){
        ArrayList<sideType> temp = new ArrayList<>();

        if (color == colorType.GREEN){
            temp.add(sideType.BRAIN);
            temp.add(sideType.BRAIN);
            temp.add(sideType.BRAIN);
            temp.add(sideType.RUNNER);
            temp.add(sideType.RUNNER);
            temp.add(sideType.SHOTGUN);
        }

        else if (color == colorType.YELLOW){
            temp.add(sideType.BRAIN);
            temp.add(sideType.BRAIN);
            temp.add(sideType.RUNNER);
            temp.add(sideType.RUNNER);
            temp.add(sideType.SHOTGUN);
            temp.add(sideType.SHOTGUN);
        }

        else{
            temp.add(sideType.BRAIN);
            temp.add(sideType.RUNNER);
            temp.add(sideType.RUNNER);
            temp.add(sideType.SHOTGUN);
            temp.add(sideType.SHOTGUN);
            temp.add(sideType.SHOTGUN);
        }

        return temp;
    }

    public void roll(){
        face = sides.get((int) (Math.random()*6));
    }

    public colorType getColor(){
        return color;
    }

    public sideType getFace(){
        return face;
    }

    public String colorToString(){
        switch (color){
            case RED: return "Red";
            case GREEN: return "Green";
            case YELLOW: return "Yellow";
        }

        return "";
    }

    public String faceToString(){
        switch (face){
            case BRAIN: return "Brain";
            case RUNNER: return "Runner";
            case SHOTGUN: return "Shotgun";
        }

        return "";
    }

    public String toString(){
        return colorToString()+ " "+faceToString();
    }

    public char toChar(){
        switch (color){
            case YELLOW: return 'Y';
            case RED: return 'R';
            case GREEN: return 'G';
        }

        return '0';
    }
}
