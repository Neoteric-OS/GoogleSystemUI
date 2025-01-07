package com.android.systemui.battery.unified;

import android.graphics.Color;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface BatteryColors {
    public static final LightThemeColors LIGHT_THEME_COLORS = LightThemeColors.INSTANCE;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DarkThemeColors implements BatteryColors {
        public static final DarkThemeColors INSTANCE = null;

        static {
            Color.valueOf(0.0f, 0.0f, 0.0f, 0.18f).toArgb();
            Color.parseColor("#5F6368");
            Color.parseColor("#BDC1C6");
            Color.parseColor("#188038");
            Color.parseColor("#F29900");
            Color.parseColor("#C5221F");
        }

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof DarkThemeColors);
        }

        public final int hashCode() {
            return -791469770;
        }

        public final String toString() {
            return "DarkThemeColors";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class LightThemeColors implements BatteryColors {
        public static final LightThemeColors INSTANCE = new LightThemeColors();

        static {
            Color.valueOf(1.0f, 1.0f, 1.0f, 0.22f).toArgb();
            Color.parseColor("#9AA0A6");
            Color.parseColor("#80868B");
            Color.parseColor("#34A853");
            Color.parseColor("#FBBC04");
            Color.parseColor("#EA4335");
        }

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof LightThemeColors);
        }

        public final int hashCode() {
            return 782959120;
        }

        public final String toString() {
            return "LightThemeColors";
        }
    }

    static {
        DarkThemeColors darkThemeColors = DarkThemeColors.INSTANCE;
    }
}
