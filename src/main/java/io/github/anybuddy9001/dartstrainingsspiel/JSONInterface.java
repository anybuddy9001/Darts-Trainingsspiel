package io.github.anybuddy9001.dartstrainingsspiel;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.ResourceBundle;

public class JSONInterface {
    private final static Path settingsPath = Paths.get("settings.json");
    private static JSONObject settings;

    private static void createNewSettings() {
        settings = new JSONObject();
        settings.put("Version", ResourceBundle.getBundle("lang").getString("version")); //NON-NLS
        settings.put("OpenLog", true);
        settings.put("CustomLanguage", JSONObject.NULL);
        settings.put("DefaultGameDuration", 10);

        saveSettings();
    }

    public static void saveSettings() {
        try {
            FileWriter fileWriter = new FileWriter("settings.json");
            fileWriter.write(settings.toString(2));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void readSettingsFile() {
        if (!settingsPath.toFile().exists()) {
            createNewSettings();
            return;
        }
        try {
            String json = Files.readString(settingsPath);
            settings = new JSONObject(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Locale getCustomLanguage() {
        readSettingsFile();
        Object obj = settings.get("CustomLanguage");
        if (obj.getClass().isInstance(JSONObject.NULL)) {
            return null;
        }
        return new Locale(obj.toString());
    }

    public static void setCustomLanguage(Locale locale) {
        settings.put("CustomLanguage", locale == null ? JSONObject.NULL : locale);
    }

    public static boolean getDoOpenLog() {
        return settings.getBoolean("OpenLog");
    }

    public static void setDoOpenLog(boolean bool) {
        settings.put("OpenLog", bool);
    }

    public static int getDefaultGameDuration() {
        return settings.getInt("DefaultGameDuration");
    }

    public static void setDefaultGameDuration(int duration) {
        settings.put("DefaultGameDuration", duration);
    }
}
