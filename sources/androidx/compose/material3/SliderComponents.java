package androidx.compose.material3;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SliderComponents {
    public static final /* synthetic */ SliderComponents[] $VALUES;
    public static final SliderComponents THUMB;
    public static final SliderComponents TRACK;

    static {
        SliderComponents sliderComponents = new SliderComponents("THUMB", 0);
        THUMB = sliderComponents;
        SliderComponents sliderComponents2 = new SliderComponents("TRACK", 1);
        TRACK = sliderComponents2;
        $VALUES = new SliderComponents[]{sliderComponents, sliderComponents2};
    }

    public static SliderComponents valueOf(String str) {
        return (SliderComponents) Enum.valueOf(SliderComponents.class, str);
    }

    public static SliderComponents[] values() {
        return (SliderComponents[]) $VALUES.clone();
    }
}
