package com.gorevce.task_service.model.enums;

public enum DifficultyLevel {
    EASY,
    MEDIUM,
    HARD;


    // function to get the difficulty enum from string
    public static DifficultyLevel getDifficultyLevel(String level) {
        for (DifficultyLevel difficultyLevel : DifficultyLevel.values()) {
            if (difficultyLevel.name().equalsIgnoreCase(level)) {
                return difficultyLevel;
            }
        }
        return null;
    }

    // function to get the difficulty string from difficulty enum
    public static String getDifficultyLevelString(DifficultyLevel level) {
        return level.name();
    }

    // function to check if the difficulty is valid
    public static boolean isValidDifficultyLevel(String level) {
        for (DifficultyLevel difficultyLevel : DifficultyLevel.values()) {
            if (difficultyLevel.name().equalsIgnoreCase(level)) {
                return true;
            }
        }
        return false;
    }

    // function to check if the difficulty is exist
    public static boolean isDifficultyLevelExist(DifficultyLevel level) {
        for (DifficultyLevel difficultyLevel : DifficultyLevel.values()) {
            if (difficultyLevel == level) {
                return true;
            }
        }
        return false;
    }


    // function to check if the difficulty is exist by string
    public static boolean isDifficultyLevelExist(String level) {
        for (DifficultyLevel difficultyLevel : DifficultyLevel.values()) {
            if (difficultyLevel.name().equalsIgnoreCase(level)) {
                return true;
            }
        }
        return false;
    }
}
