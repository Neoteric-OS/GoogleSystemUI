package com.android.settingslib.widget;

import android.content.Context;
import android.os.SystemProperties;
import java.util.Arrays;
import kotlin.enums.EnumEntriesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SettingsThemeHelper {
    public static ExpressiveThemeState expressiveThemeState = ExpressiveThemeState.UNKNOWN;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ExpressiveThemeState {
        public static final /* synthetic */ ExpressiveThemeState[] $VALUES;
        public static final ExpressiveThemeState DISABLED;
        public static final ExpressiveThemeState ENABLED;
        public static final ExpressiveThemeState UNKNOWN;

        static {
            ExpressiveThemeState expressiveThemeState = new ExpressiveThemeState("UNKNOWN", 0);
            UNKNOWN = expressiveThemeState;
            ExpressiveThemeState expressiveThemeState2 = new ExpressiveThemeState("ENABLED", 1);
            ENABLED = expressiveThemeState2;
            ExpressiveThemeState expressiveThemeState3 = new ExpressiveThemeState("DISABLED", 2);
            DISABLED = expressiveThemeState3;
            ExpressiveThemeState[] expressiveThemeStateArr = {expressiveThemeState, expressiveThemeState2, expressiveThemeState3};
            $VALUES = expressiveThemeStateArr;
            EnumEntriesKt.enumEntries(expressiveThemeStateArr);
        }

        public static ExpressiveThemeState valueOf(String str) {
            return (ExpressiveThemeState) Enum.valueOf(ExpressiveThemeState.class, str);
        }

        public static ExpressiveThemeState[] values() {
            return (ExpressiveThemeState[]) $VALUES.clone();
        }
    }

    public static final boolean isExpressiveTheme(Context context) {
        boolean z;
        ExpressiveThemeState expressiveThemeState2;
        if (expressiveThemeState == ExpressiveThemeState.UNKNOWN) {
            if (!SystemProperties.getBoolean("is_expressive_design_enabled", false)) {
                try {
                    Class<?> loadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
                    z = ((Boolean) loadClass.getMethod("getBoolean", (Class[]) Arrays.copyOf(new Class[]{String.class, Boolean.TYPE}, 2)).invoke(loadClass, Arrays.copyOf(new Object[]{"is_expressive_design_enabled", Boolean.FALSE}, 2))).booleanValue();
                } catch (IllegalArgumentException e) {
                    throw e;
                } catch (Exception unused) {
                    z = false;
                }
                if (!z) {
                    expressiveThemeState2 = ExpressiveThemeState.DISABLED;
                    expressiveThemeState = expressiveThemeState2;
                }
            }
            expressiveThemeState2 = ExpressiveThemeState.ENABLED;
            expressiveThemeState = expressiveThemeState2;
        }
        if (expressiveThemeState != ExpressiveThemeState.UNKNOWN) {
            return expressiveThemeState == ExpressiveThemeState.ENABLED;
        }
        throw new Exception("need to call com.android.settingslib.widget.SettingsThemeHelper.init(Context) first.");
    }
}
