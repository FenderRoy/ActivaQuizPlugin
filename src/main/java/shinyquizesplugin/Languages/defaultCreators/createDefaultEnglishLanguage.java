package shinyquizesplugin.Languages.defaultCreators;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class createDefaultEnglishLanguage implements IDefaultLanguageCreator{
    @Override
    public void create() {

        Properties p = new Properties();

        p.setProperty("language", "English");

        try {
            p.store(new FileWriter("/languages/"+getFileName()), "The english language file.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String getFileName() {
        return "english.properties";
    }
}
