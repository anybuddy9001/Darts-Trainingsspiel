package io.github.anybuddy9001.dartstrainingsspiel;

import org.json.JSONObject;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ScoreInterface {
    private static final Path scorePath = Paths.get("scores.json");
    private static JSONObject scores;
}
