package pl.xkoem.userinterface;

import java.util.HashMap;

public class LanguageStrings {

    private HashMap<LanguageName, String> language;

    public LanguageStrings() {
        language = new HashMap<>();
    }

    public void add(LanguageName textName, String textContent) {
        language.put(textName, textContent);
    }

    public String get(LanguageName textName) {
        return language.get(textName);
    }
}
