package com.android.systemui.screenrecord;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenRecordingAudioSource {
    public static final /* synthetic */ ScreenRecordingAudioSource[] $VALUES;
    public static final ScreenRecordingAudioSource INTERNAL;
    public static final ScreenRecordingAudioSource MIC;
    public static final ScreenRecordingAudioSource MIC_AND_INTERNAL;
    public static final ScreenRecordingAudioSource NONE;

    static {
        ScreenRecordingAudioSource screenRecordingAudioSource = new ScreenRecordingAudioSource("NONE", 0);
        NONE = screenRecordingAudioSource;
        ScreenRecordingAudioSource screenRecordingAudioSource2 = new ScreenRecordingAudioSource("INTERNAL", 1);
        INTERNAL = screenRecordingAudioSource2;
        ScreenRecordingAudioSource screenRecordingAudioSource3 = new ScreenRecordingAudioSource("MIC", 2);
        MIC = screenRecordingAudioSource3;
        ScreenRecordingAudioSource screenRecordingAudioSource4 = new ScreenRecordingAudioSource("MIC_AND_INTERNAL", 3);
        MIC_AND_INTERNAL = screenRecordingAudioSource4;
        $VALUES = new ScreenRecordingAudioSource[]{screenRecordingAudioSource, screenRecordingAudioSource2, screenRecordingAudioSource3, screenRecordingAudioSource4};
    }

    public static ScreenRecordingAudioSource valueOf(String str) {
        return (ScreenRecordingAudioSource) Enum.valueOf(ScreenRecordingAudioSource.class, str);
    }

    public static ScreenRecordingAudioSource[] values() {
        return (ScreenRecordingAudioSource[]) $VALUES.clone();
    }
}
