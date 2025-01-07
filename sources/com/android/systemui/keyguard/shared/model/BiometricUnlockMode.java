package com.android.systemui.keyguard.shared.model;

import java.util.Set;
import kotlin.collections.SetsKt;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BiometricUnlockMode {
    public static final /* synthetic */ BiometricUnlockMode[] $VALUES;
    public static final Companion Companion;
    public static final BiometricUnlockMode DISMISS_BOUNCER;
    public static final BiometricUnlockMode NONE;
    public static final BiometricUnlockMode ONLY_WAKE;
    public static final BiometricUnlockMode SHOW_BOUNCER;
    public static final BiometricUnlockMode UNLOCK_COLLAPSING;
    public static final BiometricUnlockMode WAKE_AND_UNLOCK;
    public static final BiometricUnlockMode WAKE_AND_UNLOCK_FROM_DREAM;
    public static final BiometricUnlockMode WAKE_AND_UNLOCK_PULSING;
    public static final Set dismissesKeyguardModes;
    public static final Set wakeAndUnlockModes;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
    }

    static {
        BiometricUnlockMode biometricUnlockMode = new BiometricUnlockMode("NONE", 0);
        NONE = biometricUnlockMode;
        BiometricUnlockMode biometricUnlockMode2 = new BiometricUnlockMode("WAKE_AND_UNLOCK", 1);
        WAKE_AND_UNLOCK = biometricUnlockMode2;
        BiometricUnlockMode biometricUnlockMode3 = new BiometricUnlockMode("WAKE_AND_UNLOCK_PULSING", 2);
        WAKE_AND_UNLOCK_PULSING = biometricUnlockMode3;
        BiometricUnlockMode biometricUnlockMode4 = new BiometricUnlockMode("SHOW_BOUNCER", 3);
        SHOW_BOUNCER = biometricUnlockMode4;
        BiometricUnlockMode biometricUnlockMode5 = new BiometricUnlockMode("ONLY_WAKE", 4);
        ONLY_WAKE = biometricUnlockMode5;
        BiometricUnlockMode biometricUnlockMode6 = new BiometricUnlockMode("UNLOCK_COLLAPSING", 5);
        UNLOCK_COLLAPSING = biometricUnlockMode6;
        BiometricUnlockMode biometricUnlockMode7 = new BiometricUnlockMode("DISMISS_BOUNCER", 6);
        DISMISS_BOUNCER = biometricUnlockMode7;
        BiometricUnlockMode biometricUnlockMode8 = new BiometricUnlockMode("WAKE_AND_UNLOCK_FROM_DREAM", 7);
        WAKE_AND_UNLOCK_FROM_DREAM = biometricUnlockMode8;
        BiometricUnlockMode[] biometricUnlockModeArr = {biometricUnlockMode, biometricUnlockMode2, biometricUnlockMode3, biometricUnlockMode4, biometricUnlockMode5, biometricUnlockMode6, biometricUnlockMode7, biometricUnlockMode8};
        $VALUES = biometricUnlockModeArr;
        EnumEntriesKt.enumEntries(biometricUnlockModeArr);
        Companion = new Companion();
        wakeAndUnlockModes = SetsKt.setOf(biometricUnlockMode2, biometricUnlockMode8, biometricUnlockMode3);
        dismissesKeyguardModes = SetsKt.setOf(biometricUnlockMode2, biometricUnlockMode3, biometricUnlockMode6, biometricUnlockMode8, biometricUnlockMode7);
    }

    public static BiometricUnlockMode valueOf(String str) {
        return (BiometricUnlockMode) Enum.valueOf(BiometricUnlockMode.class, str);
    }

    public static BiometricUnlockMode[] values() {
        return (BiometricUnlockMode[]) $VALUES.clone();
    }
}
