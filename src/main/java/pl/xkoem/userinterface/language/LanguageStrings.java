package pl.xkoem.userinterface.language;

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

    public boolean exist(LanguageName textName) {
        return language.containsKey(textName);
    }
}
