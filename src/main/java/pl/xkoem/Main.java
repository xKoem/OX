package pl.xkoem;

import pl.xkoem.userinterface.UserInterface;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        OXGame oxGame = new OXGame(new Players(), new UserInterface(new Scanner(System.in)::nextLine, System.out::println), new GameConfiguration());
        oxGame.run();
    }

}
