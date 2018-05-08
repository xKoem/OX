package pl.xkoem;

import pl.xkoem.userinterface.UserInterface;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface(new Scanner(System.in)::nextLine, System.out::println, FileReader.readLanguageFile("languages/ENG.lang"));
        OXGame oxGame = new OXGame(new Players(), userInterface, new GameConfiguration());
        oxGame.run();
    }
}
