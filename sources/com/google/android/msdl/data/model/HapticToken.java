package com.google.android.msdl.data.model;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HapticToken {
    public static final /* synthetic */ HapticToken[] $VALUES;
    public static final HapticToken DRAG_INDICATOR;
    public static final HapticToken DRAG_THRESHOLD_INDICATOR;
    public static final HapticToken KEYPRESS_DELETE;
    public static final HapticToken KEYPRESS_RETURN;
    public static final HapticToken KEYPRESS_SPACEBAR;
    public static final HapticToken KEYPRESS_STANDARD;
    public static final HapticToken LONG_PRESS;
    public static final HapticToken NEGATIVE_CONFIRMATION_HIGH_EMPHASIS;
    public static final HapticToken NEGATIVE_CONFIRMATION_MEDIUM_EMPHASIS;
    public static final HapticToken NEUTRAL_CONFIRMATION_HIGH_EMPHASIS;
    public static final HapticToken NEUTRAL_CONFIRMATION_MEDIUM_EMPHASIS;
    public static final HapticToken POSITIVE_CONFIRMATION_HIGH_EMPHASIS;
    public static final HapticToken POSITIVE_CONFIRMATION_LOW_EMPHASIS;
    public static final HapticToken POSITIVE_CONFIRMATION_MEDIUM_EMPHASIS;
    public static final HapticToken SWIPE_THRESHOLD_INDICATOR;
    public static final HapticToken TAP_HIGH_EMPHASIS;
    public static final HapticToken TAP_LOW_EMPHASIS;
    public static final HapticToken TAP_MEDIUM_EMPHASIS;

    static {
        HapticToken hapticToken = new HapticToken("NEGATIVE_CONFIRMATION_HIGH_EMPHASIS", 0);
        NEGATIVE_CONFIRMATION_HIGH_EMPHASIS = hapticToken;
        HapticToken hapticToken2 = new HapticToken("NEGATIVE_CONFIRMATION_MEDIUM_EMPHASIS", 1);
        NEGATIVE_CONFIRMATION_MEDIUM_EMPHASIS = hapticToken2;
        HapticToken hapticToken3 = new HapticToken("POSITIVE_CONFIRMATION_HIGH_EMPHASIS", 2);
        POSITIVE_CONFIRMATION_HIGH_EMPHASIS = hapticToken3;
        HapticToken hapticToken4 = new HapticToken("POSITIVE_CONFIRMATION_MEDIUM_EMPHASIS", 3);
        POSITIVE_CONFIRMATION_MEDIUM_EMPHASIS = hapticToken4;
        HapticToken hapticToken5 = new HapticToken("POSITIVE_CONFIRMATION_LOW_EMPHASIS", 4);
        POSITIVE_CONFIRMATION_LOW_EMPHASIS = hapticToken5;
        HapticToken hapticToken6 = new HapticToken("NEUTRAL_CONFIRMATION_HIGH_EMPHASIS", 5);
        NEUTRAL_CONFIRMATION_HIGH_EMPHASIS = hapticToken6;
        HapticToken hapticToken7 = new HapticToken("NEUTRAL_CONFIRMATION_MEDIUM_EMPHASIS", 6);
        NEUTRAL_CONFIRMATION_MEDIUM_EMPHASIS = hapticToken7;
        HapticToken hapticToken8 = new HapticToken("LONG_PRESS", 7);
        LONG_PRESS = hapticToken8;
        HapticToken hapticToken9 = new HapticToken("SWIPE_THRESHOLD_INDICATOR", 8);
        SWIPE_THRESHOLD_INDICATOR = hapticToken9;
        HapticToken hapticToken10 = new HapticToken("TAP_HIGH_EMPHASIS", 9);
        TAP_HIGH_EMPHASIS = hapticToken10;
        HapticToken hapticToken11 = new HapticToken("TAP_MEDIUM_EMPHASIS", 10);
        TAP_MEDIUM_EMPHASIS = hapticToken11;
        HapticToken hapticToken12 = new HapticToken("DRAG_THRESHOLD_INDICATOR", 11);
        DRAG_THRESHOLD_INDICATOR = hapticToken12;
        HapticToken hapticToken13 = new HapticToken("DRAG_INDICATOR", 12);
        DRAG_INDICATOR = hapticToken13;
        HapticToken hapticToken14 = new HapticToken("TAP_LOW_EMPHASIS", 13);
        TAP_LOW_EMPHASIS = hapticToken14;
        HapticToken hapticToken15 = new HapticToken("KEYPRESS_STANDARD", 14);
        KEYPRESS_STANDARD = hapticToken15;
        HapticToken hapticToken16 = new HapticToken("KEYPRESS_SPACEBAR", 15);
        KEYPRESS_SPACEBAR = hapticToken16;
        HapticToken hapticToken17 = new HapticToken("KEYPRESS_RETURN", 16);
        KEYPRESS_RETURN = hapticToken17;
        HapticToken hapticToken18 = new HapticToken("KEYPRESS_DELETE", 17);
        KEYPRESS_DELETE = hapticToken18;
        HapticToken[] hapticTokenArr = {hapticToken, hapticToken2, hapticToken3, hapticToken4, hapticToken5, hapticToken6, hapticToken7, hapticToken8, hapticToken9, hapticToken10, hapticToken11, hapticToken12, hapticToken13, hapticToken14, hapticToken15, hapticToken16, hapticToken17, hapticToken18};
        $VALUES = hapticTokenArr;
        EnumEntriesKt.enumEntries(hapticTokenArr);
    }

    public static HapticToken valueOf(String str) {
        return (HapticToken) Enum.valueOf(HapticToken.class, str);
    }

    public static HapticToken[] values() {
        return (HapticToken[]) $VALUES.clone();
    }
}
