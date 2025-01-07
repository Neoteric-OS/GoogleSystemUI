package com.airbnb.lottie;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RenderMode {
    public static final /* synthetic */ RenderMode[] $VALUES;
    public static final RenderMode AUTOMATIC;
    public static final RenderMode HARDWARE;
    public static final RenderMode SOFTWARE;

    static {
        RenderMode renderMode = new RenderMode("AUTOMATIC", 0);
        AUTOMATIC = renderMode;
        RenderMode renderMode2 = new RenderMode("HARDWARE", 1);
        HARDWARE = renderMode2;
        RenderMode renderMode3 = new RenderMode("SOFTWARE", 2);
        SOFTWARE = renderMode3;
        $VALUES = new RenderMode[]{renderMode, renderMode2, renderMode3};
    }

    public static RenderMode valueOf(String str) {
        return (RenderMode) Enum.valueOf(RenderMode.class, str);
    }

    public static RenderMode[] values() {
        return (RenderMode[]) $VALUES.clone();
    }
}
