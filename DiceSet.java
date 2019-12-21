import java.util.ArrayList;
import java.util.Collections;

public class DiceSet {
    private ArrayList<Die> dice;

    // initialize as empty set
    public DiceSet(){
        dice = new ArrayList<>();
    }

    // initialize with set of dice
    public DiceSet(DiceSet diceSet){
        dice = new ArrayList<>();

        dice.addAll(diceSet.getDice());
    }

    // add die to set
    public void addDie(Die die){
        dice.add(die);
    }

    // adds array of dice to set
    public void addAll(ArrayList<Die> dice){
        for (Die die : dice){
            this.dice.add(die);
        }
    }

    // removes array of dice from set
    public void removeAll(ArrayList<Die> dice){
        for (Die die : dice){
            this.dice.remove(die);
        }
    }

    // gets die at index x
    public Die get(int x){
        return dice.get(x);
    }

    // draws first die, removing it from this dieSet
    public Die draw(){
        Die tempDie = dice.get(0);

        dice.remove(tempDie);

        return tempDie;
    }

    // rolls the faces of each die
    public void roll(){
        for (Die die : dice){
            die.roll();
        }
    }

    // shuffles order of dieSet
    public void shuffle(){
        Collections.shuffle(dice);
    }

    // returns length of dieSet
    public int length(){
        return dice.size();
    }

    // counts number of dice sideType 'side'
    public int countNumber(sideType side){
        int count = 0;

        for (Die die : dice){
            if (die.getFace() == side){
                ++count;
            }
        }

        return count;
    }

    // returns dice in array
    public ArrayList<Die> getDice(){
        return dice;
    }

    // for printing remaining dice (only shows color)
    public String diceInfo(){
        String temp = "";

        for (Die die : dice){
            temp += "[" + die.toChar() + "]  ";
        }

        return temp;
    }
}
