package com.android.systemui.mediaprojection;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SessionCreationSource {
    public static final /* synthetic */ SessionCreationSource[] $VALUES;
    public static final SessionCreationSource APP;
    public static final SessionCreationSource CAST;
    public static final SessionCreationSource SYSTEM_UI_SCREEN_RECORDER;

    static {
        SessionCreationSource sessionCreationSource = new SessionCreationSource("APP", 0);
        APP = sessionCreationSource;
        SessionCreationSource sessionCreationSource2 = new SessionCreationSource("CAST", 1);
        CAST = sessionCreationSource2;
        SessionCreationSource sessionCreationSource3 = new SessionCreationSource("SYSTEM_UI_SCREEN_RECORDER", 2);
        SYSTEM_UI_SCREEN_RECORDER = sessionCreationSource3;
        SessionCreationSource[] sessionCreationSourceArr = {sessionCreationSource, sessionCreationSource2, sessionCreationSource3, new SessionCreationSource("UNKNOWN", 3)};
        $VALUES = sessionCreationSourceArr;
        EnumEntriesKt.enumEntries(sessionCreationSourceArr);
    }

    public static SessionCreationSource valueOf(String str) {
        return (SessionCreationSource) Enum.valueOf(SessionCreationSource.class, str);
    }

    public static SessionCreationSource[] values() {
        return (SessionCreationSource[]) $VALUES.clone();
    }
}
