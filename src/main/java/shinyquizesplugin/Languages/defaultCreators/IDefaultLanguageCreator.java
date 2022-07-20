package shinyquizesplugin.Languages.defaultCreators;

import java.util.Properties;

public interface IDefaultLanguageCreator {

    Properties create(String version);

    String getFileName();
}
