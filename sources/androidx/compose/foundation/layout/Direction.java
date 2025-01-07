package androidx.compose.foundation.layout;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Direction {
    public static final /* synthetic */ Direction[] $VALUES;
    public static final Direction Both;
    public static final Direction Horizontal;
    public static final Direction Vertical;

    static {
        Direction direction = new Direction("Vertical", 0);
        Vertical = direction;
        Direction direction2 = new Direction("Horizontal", 1);
        Horizontal = direction2;
        Direction direction3 = new Direction("Both", 2);
        Both = direction3;
        $VALUES = new Direction[]{direction, direction2, direction3};
    }

    public static Direction valueOf(String str) {
        return (Direction) Enum.valueOf(Direction.class, str);
    }

    public static Direction[] values() {
        return (Direction[]) $VALUES.clone();
    }
}
