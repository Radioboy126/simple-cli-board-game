import com.radioboy.simplegame.GameFlowControl;
import com.radioboy.simplegame.game.GameFlowInfo;
import com.radioboy.simplegame.game.essentials.Difficulty;


import java.util.Scanner;

public class Test {

    private  static  String lastString = "";
    public static void main(String[] args) throws Exception {

        boolean isGameOver = false;
        Scanner input = new Scanner(System.in);
        System.out.println("Game Setting Loaded: Easy Difficulty");
        System.out.println("Life Set At: 200HP");
        GameFlowControl.startGame(Difficulty.EASY);
        System.out.println("Game Controls:");
        System.out.println("Up: W");
        System.out.println("Down: S");
        System.out.println("Left: A");
        System.out.println("Right: D");
        System.out.println("Ready?");
        Thread.sleep(3000);
        lastString = GameFlowControl.getBoardString() + System.lineSeparator() + "Gooooo!!!!";
        System.out.print(lastString);
        do {
            char moveChar = input.next().charAt(0);
            GameFlowInfo info = GameFlowControl.move(moveChar);

            isGameOver = animate(info);
            Thread.sleep(400);
        } while (!isGameOver);
    }


    public static boolean animate(GameFlowInfo info) {
        int redos = lastString.split(System.lineSeparator()).length;
        while (redos > 0)
        {
            System.out.print(String.format("\033[%dA", 1));
            System.out.print("\033[2K");
            redos--;
        }
        StringBuilder string = new StringBuilder();
        string.append("\r");
        string.append(GameFlowControl.getBoardString());
        if (info.error == null) {
            if (info.monsterEncountered == null) {
                string.append("Nothing Here");

            }
            else {

                string.append( info.damageTaken == 0 ?
                        String.format("You encountered %s: You Dodged its attack!  %sLife Remaining: %d", info.monsterEncountered, System.lineSeparator(), info.hp):
                        String.format("You encountered %s: It dealt %d damage  %sLife Remaining: %d", info.monsterEncountered, info.damageTaken, System.lineSeparator(), info.hp));

                if (info.isGameOver ) {
                    if(info.isVictory)
                    {
                        string.append(System.lineSeparator());
                        string.append("You Win!!!!" );
                    }
                    else
                    {
                        string.append(System.lineSeparator());
                        string.append("You Lose!!!!" );
                    }
                    string.append(System.lineSeparator());
                    string.append("GAME OVER!");
                }
            }
        } else {
            string.append(info.error);
        }
        lastString=string.toString();
        System.out.print(lastString);

        return (info.isVictory || info.isGameOver);
    }
}



