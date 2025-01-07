package androidx.compose.animation.graphics.vector;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Ordering {
    public static final /* synthetic */ Ordering[] $VALUES;
    public static final Ordering Sequentially;
    public static final Ordering Together;

    static {
        Ordering ordering = new Ordering("Together", 0);
        Together = ordering;
        Ordering ordering2 = new Ordering("Sequentially", 1);
        Sequentially = ordering2;
        $VALUES = new Ordering[]{ordering, ordering2};
    }

    public static Ordering valueOf(String str) {
        return (Ordering) Enum.valueOf(Ordering.class, str);
    }

    public static Ordering[] values() {
        return (Ordering[]) $VALUES.clone();
    }
}
