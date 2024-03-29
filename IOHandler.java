import java.util.Scanner;

public class IOHandler {
    PlayerSet players;
    Player player1;
    Player player2;
    Scanner in;

    public final int width = 80;

    public IOHandler(PlayerSet players){
        this.players = players;

        player1 = players.getPlayer(0);
        player2 = players.getPlayer(1);

        in = new Scanner(System.in);
    }

    // prints pretty, centered line
    // center will be centered, and left and right are centered in the two remaining spaces
    // the boolean "sides" determines whether the line will be printed with walls
    private void print(String left, String center, String right, boolean sides){
        int walls;

        if (sides){
            walls = 2;
        }
        else{
            walls = 0;
        }

        int sideLen = (width - walls - center.length()) / 2;
        int leftSpaceLen = (sideLen - left.length()) / 2;
        int rightSpaceLen = (sideLen - right.length()) / 2;

        // so that the line will always be 'width' wide
        int lastSpaceLen = width - walls - 2*leftSpaceLen - left.length() - center.length() - rightSpaceLen - right.length();

        String leftSpace = "";
        String rightSpace = "";
        String lastSpace = "";

        for (int i = 0; i < leftSpaceLen; ++i){
            leftSpace += " ";
        }

        for (int i = 0; i < rightSpaceLen; ++i){
            rightSpace += " ";
        }

        for (int i = 0; i < lastSpaceLen; ++i){
            lastSpace += " ";
        }

        if (sides){
            System.out.println("  |" + leftSpace + left + leftSpace + center + rightSpace + right + lastSpace + "|");
        }
        else{
            System.out.println("  " + leftSpace + left + leftSpace + center + rightSpace + right + lastSpace + "");
        }
    }

    private void print(String center){
        print("", center, "");
    }

    private void print(String left, String right){
        print(left, "", right);
    }

    private void print(String left, String center, String right){
        print(left, center, right, true);
    }

    private void print(String center, boolean sides){
        print("", center, "", sides);
    }

    // makes 'lines' blank lines, with or without walls (default with)
    private void newLine(int lines, boolean walls){
        for (int i = 0; i < lines; ++i){
            print("", "", "", walls);
        }
    }
    private void newLine(){
        newLine(true);
    }

    private void newLine(boolean walls){
        newLine(1, walls);
    }

    // makes horizontal line across screen of width width
    private void line(){
        String topLine = "";
        for (int i = 0; i < width; i++){
            topLine += "-";
        }

        System.out.println("  " + topLine);
    }

    // intro screen
    public void intro(){
        line();
        newLine();
        print("---------------");
        print("| Zombie Dice |");
        print("---------------");
        newLine();
        line();
    }

    // prints info on current round in a box before the player rolls.
    public void printState(int turn, int points, int shots, String diceInfo){
        line();
        newLine();

        // if player 1 turn
        if (turn % 2 != 0) {
            // in player 1 column
            print(player1.toString() + "'s Turn", "");

            int underline = player1.toString().length();

            String temp = "";
            for (int i = 0; i < underline; ++i) {
                temp += "-";
            }

            print(temp, "");
        }

        // if player 2 turn
        else {
            // in player 2 column
            print("", player2.toString() + "'s Turn");

            int underline = player2.toString().length();

            String temp = "";
            for (int i = 0; i < underline; ++i) {
                temp += "-";
            }

            print("", temp);
        }

        newLine();

        print(player1.toString(), player2.toString());
        print("Score: " + player1.getPoints(), "Score: " + player2.getPoints());

        newLine();

        print("Round Points: " + points);
        print("Shots Taken: " + shots);

        newLine();

        print("Remaining Dice: " + diceInfo);

        newLine();

        line();
    }

    // makes colorType usable for printing dice
    private String buffColor(colorType color){
        switch (color){
            case GREEN:  return "| Green |";
            case YELLOW: return "|Yellow |";
            case RED:    return "|  Red  |";
        }

        return "";
    }

    // makes sideType usable for printing dice
    private String buffSide(sideType side){
        switch (side){
            case RUNNER:  return "|Runner |";
            case SHOTGUN: return "|Shotgun|";
            case BRAIN:   return "| Brian |";
        }

        return "";
    }

    // displays centered dice
    public void printDice(DiceSet dice){
        String buff = "   "; // distance between dice (4 spaces)

        String topStr = "---------" + buff + "---------" + buff + "---------" ;
        String midStr = "|       |" + buff + "|       |" + buff + "|       |" ;

        String colorStr = buffColor(dice.get(0).getColor()) + buff +
                          buffColor(dice.get(1).getColor()) + buff +
                          buffColor(dice.get(2).getColor());

        String faceStr = buffSide(dice.get(0).getFace()) + buff +
                         buffSide(dice.get(1).getFace()) + buff +
                         buffSide(dice.get(2).getFace());

        print(topStr, false);
        print(midStr, false);
        print(colorStr, false);
        print(faceStr, false);
        print(midStr, false);
        print(topStr, false);
    }

    // prints what happened last roll
    public void printEvents(int points, int shots){
        newLine(false);

        // if/else for grammar
        if (points == 1){
            print("You got 1 point!", false);
        }
        else{
            print("You got " + points + " points!", false);
        }

        if (shots == 1){
            print("You took 1 shot!", false);
        }
        else{
            print("You took " + shots + " shots!", false);
        }

        newLine(false);
    }

    // displays death info
    public void printDeath(){
        newLine(false);
        print(" -- You have died! -- ", false);
    }

    // ends round
    public void endRound(int points){
        newLine(false);

        // for grammar
        if (points == 1){
            print("You scored 1 point this round!", false);
        }
        else{
            print("You scored "+points+" points this round!", false);
        }

        newLine(2, false);
        line();
        line();
        newLine(2, false);
    }

    // processes choice
    // returns -1 if round over, 1 if roll again, 0 if it cannot determine
    private int checkChoice(String choice){
        choice.replace(" ", "");
        choice = choice.toLowerCase();

        int value = 0;

        String[] yesAns = {"y", "yes", "roll", "continue"};
        String[] noAns = {"n", "no", "stop", "pass"};

        for (String ans : yesAns){
            if (ans.equals(choice)){
                ++value;
            }
        }

        for (String ans : noAns){
            if (ans.equals(choice)){
                --value;
            }
        }

        return value;
    }

    // gets user input on rolling (dummy proof)
    public boolean getChoice(){
        newLine(false);
        String prompt = "Would you like to roll? (yes / no) ";

        // custom spacing because we wish to read on same line
        int spaces = 2 + (width - prompt.length()) / 2;

        String space = "";

        for (int i = 0; i < spaces; ++i){
            space += " ";
        }

        // loops until receive valid answer
        int valid = 0;

        while (valid == 0) {

            System.out.print(space + prompt);
            String response = in.next();
            System.out.println();

            valid = checkChoice(response);
        }

        if (valid > 0){
            return true;
        }
        else {
            return false;
        }
    }

    // end of game message
    public void endGame(Player winner){
        newLine(false);
        line();
        newLine(false);

        print(winner.toString().toUpperCase() + " WINS!!");

        newLine(false);
        line();
        newLine(2, false);
    }
}
