package com.android.compose.theme.typography;

import android.content.Context;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TypefaceNames {
    public final String brand;
    public final String plain;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        public static TypefaceNames get(Context context) {
            return new TypefaceNames(getTypefaceName(context, Config.Brand), getTypefaceName(context, Config.Plain));
        }

        public static String getTypefaceName(Context context, Config config) {
            String string = context.getString(context.getResources().getIdentifier(config.getConfigName(), "string", "android"));
            Intrinsics.checkNotNull(string);
            if (string.length() <= 0) {
                string = null;
            }
            return string == null ? config.getDefault() : string;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class Config {
        public static final /* synthetic */ Config[] $VALUES;
        public static final Config Brand;
        public static final Config Plain;
        private final String configName;

        /* renamed from: default, reason: not valid java name */
        private final String f14default = "sans-serif";

        static {
            Config config = new Config("Brand", "config_headlineFontFamily", 0);
            Brand = config;
            Config config2 = new Config("Plain", "config_bodyFontFamily", 1);
            Plain = config2;
            Config[] configArr = {config, config2};
            $VALUES = configArr;
            EnumEntriesKt.enumEntries(configArr);
        }

        public Config(String str, String str2, int i) {
            this.configName = str2;
        }

        public static Config valueOf(String str) {
            return (Config) Enum.valueOf(Config.class, str);
        }

        public static Config[] values() {
            return (Config[]) $VALUES.clone();
        }

        public final String getConfigName() {
            return this.configName;
        }

        public final String getDefault() {
            return this.f14default;
        }
    }

    public TypefaceNames(String str, String str2) {
        this.brand = str;
        this.plain = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TypefaceNames)) {
            return false;
        }
        TypefaceNames typefaceNames = (TypefaceNames) obj;
        return Intrinsics.areEqual(this.brand, typefaceNames.brand) && Intrinsics.areEqual(this.plain, typefaceNames.plain);
    }

    public final int hashCode() {
        return this.plain.hashCode() + (this.brand.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("TypefaceNames(brand=");
        sb.append(this.brand);
        sb.append(", plain=");
        return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, this.plain, ")");
    }
}
