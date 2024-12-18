package com.gorevce.freelancer_service.model.enums;

public enum PlatformEnum {
    FACEBOOK,
    INSTAGRAM,
    TWITTER,
    LINKEDIN,
    GITHUB,
    BEHANCE,
    DRIBBLE,
    PINTEREST,
    YOUTUBE,
    TIKTOK,
    SNAPCHAT,
    REDDIT,
    TUMBLR,
    FLICKR,
    VIMEO,
    SOUNDCLOUD,
    SPOTIFY,
    PANDORA,
    APPLE_MUSIC,
    GOOGLE_PLAY_MUSIC,
    AMAZON_MUSIC,
    DEEZER,
    TIDAL,
    NAPSTER,
    LAST_FM;

    // function to get the platform enum from string
    public static PlatformEnum getPlatformEnum(String platform) {
        for (PlatformEnum platformEnum : PlatformEnum.values()) {
            if (platformEnum.name().equalsIgnoreCase(platform)) {
                return platformEnum;
            }
        }
        return null;
    }

    // function to get the platform string from platform enum
    public static String getPlatformString(PlatformEnum platform) {
        return platform.name();
    }

    // function to check if the platform is valid
    public static boolean isValidPlatform(String platform) {
        for (PlatformEnum platformEnum : PlatformEnum.values()) {
            if (platformEnum.name().equalsIgnoreCase(platform)) {
                return true;
            }
        }
        return false;
    }

    // function to check if the platform is exist
    public static boolean isPlatformExist(PlatformEnum platform) {
        for (PlatformEnum platformEnum : PlatformEnum.values()) {
            if (platformEnum == platform) {
                return true;
            }
        }
        return false;
    }

    // function to check if the platform is exist by string
    public static boolean isPlatformExist(String platform) {
        for (PlatformEnum platformEnum : PlatformEnum.values()) {
            if (platformEnum.name().equalsIgnoreCase(platform)) {
                return true;
            }
        }
        return false;
    }


}
