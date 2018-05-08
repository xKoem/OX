package pl.xkoem;

import pl.xkoem.userinterface.UserInterface;
import pl.xkoem.userinterface.language.LanguageStrings;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface(new Scanner(System.in)::nextLine, System.out::println, getLang(args));
        OXGame oxGame = new OXGame(new Players(), userInterface, new GameConfiguration());
        oxGame.run();
    }

    static private LanguageStrings getLang(String[] args) {
        if(args.length < 1) {
            return new LanguageStrings();
        }
        return FileReader.readLanguageFile("languages/"+ args[0] +".lang", System.out::println);
    }
}
