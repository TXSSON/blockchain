package utils;

public enum Icon {
    RECEIVE("\uD83D\uDCE5"),   // 📥
    MINER("\u26CF\uFE0F"),     // ⛏️
    CHAIN("\uD83D\uDD17"),     // 🔗
    SUCCESS("\u2705"),         // ✅
    ERROR("\u274C"),           // ❌
    BROADCAST("\uD83D\uDCE1"), // 📡
    TIME("\uD83D\uDD52"),      // 🕒
    DEVELOPER("\uD83E\uDDD1\u200D\uD83D\uDCBB"), // 🧑‍💻
    SERVER("\uD83D\uDDA7"),    // 🖧
    MESSAGE("\uD83D\uDCAC");   // 💬

    private final String unicode;

    Icon(String unicode) {
        this.unicode = unicode;
    }

    public String get() {
        return unicode;
    }
}

