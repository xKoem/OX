package pl.xkoem.userinterface;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class UserInterface {

    private final Supplier<String> userInput;
    private final Consumer<String> userOutput;

    public UserInterface(Supplier<String> userInput, Consumer<String> userOutput) {
        this.userInput = userInput;
        this.userOutput = userOutput;
    }

    public String get() {
        return userInput.get();
    }

    public void accept(String string) {
        userOutput.accept(string);
    }
}
