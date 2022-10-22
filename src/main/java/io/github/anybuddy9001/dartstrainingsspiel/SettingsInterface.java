package io.github.anybuddy9001.dartstrainingsspiel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.ResourceBundle;

public class SettingsInterface {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("lang");
    private static final Path settingsPath = Paths.get("settings.json");
    private static JSONObject settings;

    private static void createNewSettings() {
        settings = new JSONObject();
        settings.put("Version", resourceBundle.getString("version")); //NON-NLS
        settings.put("OpenLog", true);
        settings.put("GameOverOnEndless", true);
        settings.put("GameOverOnChallenge", false);
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
            checkIntegrity();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            System.out.println(resourceBundle.getString("mError.JSONInterface.corruptSettingsFile")); //NON-NLS
            backupAndRecreate();
        }
    }


    private static void checkIntegrity() throws JSONException {
        if (!settings.getString("Version").equals(ResourceBundle.getBundle("lang").getString("version"))) { //NON-NLS
            migrateOldSettings();
            return;
        }
        if (!(settings.has("OpenLog")
                && settings.has("GameOverOnEndless")
                && settings.has("GameOverOnChallenge")
                && settings.has("CustomLanguage")
                && settings.has("DefaultGameDuration"))) {
            throw new JSONException("Missing Key");
        }
    }

    /**
     * Parses old settings to apply them to a new version
     */
    private static void migrateOldSettings() {
        backupAndRecreate(); //ToDo write method body
    }

    /**
     * Creates a backup of the current file in "oldSettings.json" and a new settings file
     */
    private static void backupAndRecreate() {
        try (FileWriter fileWriter = new FileWriter("oldSettings.json")) {
            fileWriter.write(Files.readString(settingsPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        createNewSettings();
    }

    public static Locale getCustomLanguage() {
        readSettingsFile();
        Object obj = settings.get("CustomLanguage");
        if (obj.getClass().isInstance(JSONObject.NULL)) {
            return null;
        }
        return new Locale.Builder().setLanguage(obj.toString()).build();
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

    public static boolean getGameOverEndless() {
        return settings.getBoolean("GameOverOnEndless");
    }

    public static void setGameOverEndless(boolean bool) {
        settings.put("GameOverOnEndless", bool);
    }

    public static boolean getGameOverChallenge() {
        return settings.getBoolean("GameOverOnChallenge");
    }

    public static void setGameOverChallenge(boolean bool) {
        settings.put("GameOverOnChallenge", bool);
    }

    public static void resetToDefaultSettings() {
        backupAndRecreate();
    }
}
