package androidx.compose.foundation.text.selection;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CrossStatus {
    public static final /* synthetic */ CrossStatus[] $VALUES;
    public static final CrossStatus COLLAPSED;
    public static final CrossStatus CROSSED;
    public static final CrossStatus NOT_CROSSED;

    static {
        CrossStatus crossStatus = new CrossStatus("CROSSED", 0);
        CROSSED = crossStatus;
        CrossStatus crossStatus2 = new CrossStatus("NOT_CROSSED", 1);
        NOT_CROSSED = crossStatus2;
        CrossStatus crossStatus3 = new CrossStatus("COLLAPSED", 2);
        COLLAPSED = crossStatus3;
        $VALUES = new CrossStatus[]{crossStatus, crossStatus2, crossStatus3};
    }

    public static CrossStatus valueOf(String str) {
        return (CrossStatus) Enum.valueOf(CrossStatus.class, str);
    }

    public static CrossStatus[] values() {
        return (CrossStatus[]) $VALUES.clone();
    }
}
