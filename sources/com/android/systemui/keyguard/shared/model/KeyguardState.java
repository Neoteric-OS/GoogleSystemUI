package com.android.systemui.keyguard.shared.model;

import android.util.Log;
import com.android.app.viewcapture.data.ViewNode;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardState {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ KeyguardState[] $VALUES;
    public static final KeyguardState ALTERNATE_BOUNCER;
    public static final KeyguardState AOD;
    public static final Companion Companion;
    public static final KeyguardState DOZING;
    public static final KeyguardState DREAMING;
    public static final KeyguardState DREAMING_LOCKSCREEN_HOSTED;
    public static final KeyguardState GLANCEABLE_HUB;
    public static final KeyguardState GONE;
    public static final KeyguardState LOCKSCREEN;
    public static final KeyguardState OCCLUDED;
    public static final KeyguardState OFF;
    public static final KeyguardState PRIMARY_BOUNCER;
    public static final KeyguardState UNDEFINED;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static boolean deviceIsAsleepInState(KeyguardState keyguardState) {
            return !deviceIsAwakeInState(keyguardState);
        }

        public static boolean deviceIsAwakeInState(KeyguardState keyguardState) {
            keyguardState.checkValidState();
            switch (keyguardState.ordinal()) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                    return false;
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                    return true;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
    }

    static {
        KeyguardState keyguardState = new KeyguardState("OFF", 0);
        OFF = keyguardState;
        KeyguardState keyguardState2 = new KeyguardState("DOZING", 1);
        DOZING = keyguardState2;
        KeyguardState keyguardState3 = new KeyguardState("DREAMING", 2);
        DREAMING = keyguardState3;
        KeyguardState keyguardState4 = new KeyguardState("DREAMING_LOCKSCREEN_HOSTED", 3);
        DREAMING_LOCKSCREEN_HOSTED = keyguardState4;
        KeyguardState keyguardState5 = new KeyguardState("AOD", 4);
        AOD = keyguardState5;
        KeyguardState keyguardState6 = new KeyguardState("ALTERNATE_BOUNCER", 5);
        ALTERNATE_BOUNCER = keyguardState6;
        KeyguardState keyguardState7 = new KeyguardState("PRIMARY_BOUNCER", 6);
        PRIMARY_BOUNCER = keyguardState7;
        KeyguardState keyguardState8 = new KeyguardState("LOCKSCREEN", 7);
        LOCKSCREEN = keyguardState8;
        KeyguardState keyguardState9 = new KeyguardState("GLANCEABLE_HUB", 8);
        GLANCEABLE_HUB = keyguardState9;
        KeyguardState keyguardState10 = new KeyguardState("GONE", 9);
        GONE = keyguardState10;
        KeyguardState keyguardState11 = new KeyguardState("UNDEFINED", 10);
        UNDEFINED = keyguardState11;
        KeyguardState keyguardState12 = new KeyguardState("OCCLUDED", 11);
        OCCLUDED = keyguardState12;
        KeyguardState[] keyguardStateArr = {keyguardState, keyguardState2, keyguardState3, keyguardState4, keyguardState5, keyguardState6, keyguardState7, keyguardState8, keyguardState9, keyguardState10, keyguardState11, keyguardState12};
        $VALUES = keyguardStateArr;
        $ENTRIES = EnumEntriesKt.enumEntries(keyguardStateArr);
        Companion = new Companion();
    }

    public static KeyguardState valueOf(String str) {
        return (KeyguardState) Enum.valueOf(KeyguardState.class, str);
    }

    public static KeyguardState[] values() {
        return (KeyguardState[]) $VALUES.clone();
    }

    public final void checkValidState() {
        if (this != UNDEFINED) {
            return;
        }
        Log.e("KeyguardState", this + " is not a valid state when scene container is disabled");
    }
}
