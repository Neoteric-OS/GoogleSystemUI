package com.android.compose;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class TrackComponent {
    public static final /* synthetic */ TrackComponent[] $VALUES;
    public static final TrackComponent Background;
    public static final TrackComponent Icon;
    public static final TrackComponent Label;
    private final float zIndex;

    static {
        TrackComponent trackComponent = new TrackComponent(0.0f, 0, "Background");
        Background = trackComponent;
        TrackComponent trackComponent2 = new TrackComponent(1.0f, 1, "Icon");
        Icon = trackComponent2;
        TrackComponent trackComponent3 = new TrackComponent(1.0f, 2, "Label");
        Label = trackComponent3;
        TrackComponent[] trackComponentArr = {trackComponent, trackComponent2, trackComponent3};
        $VALUES = trackComponentArr;
        EnumEntriesKt.enumEntries(trackComponentArr);
    }

    public TrackComponent(float f, int i, String str) {
        this.zIndex = f;
    }

    public static TrackComponent valueOf(String str) {
        return (TrackComponent) Enum.valueOf(TrackComponent.class, str);
    }

    public static TrackComponent[] values() {
        return (TrackComponent[]) $VALUES.clone();
    }

    public final float getZIndex() {
        return this.zIndex;
    }
}
