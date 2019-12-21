import java.util.ArrayList;
import java.util.Collections;

public class DiceSet {
    private ArrayList<Die> dice;

    public DiceSet(){
        dice = new ArrayList<>();
    }

    public DiceSet(DiceSet diceSet){
        dice = new ArrayList<>();

        dice.addAll(diceSet.getDice());
    }

    public void addDie(Die die){
        dice.add(die);
    }

    public void addAll(ArrayList<Die> dice){
        for (Die die : dice){
            this.dice.add(die);
        }
    }

    public void removeAll(ArrayList<Die> dice){
        for (Die die : dice){
            this.dice.remove(die);
        }
    }

    public Die get(int x){
        return dice.get(x);
    }

    public Die draw(){
        Die tempDie = dice.get(0);

        dice.remove(tempDie);

        return tempDie;
    }

    public void roll(){
        for (Die die : dice){
            die.roll();
        }
    }

    public void shuffle(){
        Collections.shuffle(dice);
    }

    public int length(){
        return dice.size();
    }

    public int countNumber(sideType side){
        int count = 0;

        for (Die die : dice){
            if (die.getFace() == side){
                ++count;
            }
        }

        return count;
    }

    public ArrayList<Die> getDice(){
        return dice;
    }

    public String diceInfo(){
        String temp = "";

        for (Die die : dice){
            temp += "[" + die.toChar() + "]  ";
        }

        return temp;
    }
}
