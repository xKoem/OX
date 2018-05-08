package pl.xkoem;

import pl.xkoem.userinterface.UserInterface;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface(new Scanner(System.in)::nextLine, System.out::println, FileReader.readLanguageFile("languages/"+ getLang(args) +".lang"));
        OXGame oxGame = new OXGame(new Players(), userInterface, new GameConfiguration());
        oxGame.run();
    }

    static private String getLang(String[] args) {
        if(args.length < 1) {
            return "ENG";
        }
        return args[0];
    }
}
