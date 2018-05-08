package pl.xkoem.userinterface;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class UserInterface {

    private final Supplier<String> userInput;
    private final Consumer<String> userOutput;
    private final LanguageStrings languageStrings;

    public UserInterface(Supplier<String> userInput, Consumer<String> userOutput, LanguageStrings languageStrings) {
        this.userInput = userInput;
        this.userOutput = userOutput;
        this.languageStrings = languageStrings;
    }

    public String get() {
        return userInput.get().trim();
    }

    public void accept(String string) {
        userOutput.accept(string);
    }

    public void accept(LanguageName languageName) {
        userOutput.accept(languageStrings.get(languageName));
    }

    public void accept(LanguageName languageName, ReplacePattern... replacePatterns) {
        String output = languageStrings.get(languageName);

        for (ReplacePattern replacePattern: replacePatterns) {
            output = output.replace(replacePattern.getFrom(), replacePattern.getTo());
        }
        userOutput.accept(output);
    }
}
