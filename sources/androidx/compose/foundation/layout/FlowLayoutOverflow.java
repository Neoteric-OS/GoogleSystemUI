package androidx.compose.foundation.layout;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class FlowLayoutOverflow {

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OverflowType {
        public static final /* synthetic */ OverflowType[] $VALUES;
        public static final OverflowType Clip;
        public static final OverflowType ExpandIndicator = null;
        public static final OverflowType ExpandOrCollapseIndicator = null;
        public static final OverflowType Visible = null;

        /* JADX INFO: Fake field, exist only in values array */
        OverflowType EF0;

        static {
            OverflowType overflowType = new OverflowType("Visible", 0);
            OverflowType overflowType2 = new OverflowType("Clip", 1);
            Clip = overflowType2;
            $VALUES = new OverflowType[]{overflowType, overflowType2, new OverflowType("ExpandIndicator", 2), new OverflowType("ExpandOrCollapseIndicator", 3)};
        }

        public static OverflowType valueOf(String str) {
            return (OverflowType) Enum.valueOf(OverflowType.class, str);
        }

        public static OverflowType[] values() {
            return (OverflowType[]) $VALUES.clone();
        }
    }
}
