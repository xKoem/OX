package pl.xkoem;

import pl.xkoem.userinterface.language.LanguageName;
import pl.xkoem.userinterface.language.LanguageStrings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.function.Consumer;

public class FileReader {

    public static LanguageStrings readLanguageFile(String path, Consumer<String> output) {
        LanguageStrings languageStrings = new LanguageStrings();
        try(BufferedReader reader = new BufferedReader(new java.io.FileReader(new File(path)))) {
            String line;
            String textName;
            String textContent;

            while ((line = reader.readLine()) != null && !line.equals("")) {
                textName = line.substring(0, line.indexOf(":")).trim();
                textContent = line.substring(line.indexOf(":") + 1).trim();
                textContent = textContent.replaceAll("\\\\n", System.lineSeparator());
                try {
                    languageStrings.add(LanguageName.valueOf(textName), textContent);
                } catch (IllegalArgumentException e) {
                    System.out.println("Unrecognized game string: " + textName);
                }
            }

        } catch (FileNotFoundException e) {
            output.accept("Language path " + path + " not found. Loading default language.");
        } catch (IOException e) {
            output.accept("Cannot load file " + path + ". Loading default language.");
        }

        return languageStrings;
    }
}
