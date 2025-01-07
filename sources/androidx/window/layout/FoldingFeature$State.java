package androidx.window.layout;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FoldingFeature$State {
    public static final FoldingFeature$State FLAT;
    public static final FoldingFeature$State HALF_OPENED;
    public static final FoldingFeature$State HORIZONTAL;
    public static final FoldingFeature$State VERTICAL;
    public final /* synthetic */ int $r8$classId;
    public final String description;

    static {
        int i = 1;
        VERTICAL = new FoldingFeature$State("VERTICAL", i);
        HORIZONTAL = new FoldingFeature$State("HORIZONTAL", i);
        int i2 = 0;
        FLAT = new FoldingFeature$State("FLAT", i2);
        HALF_OPENED = new FoldingFeature$State("HALF_OPENED", i2);
    }

    public /* synthetic */ FoldingFeature$State(String str, int i) {
        this.$r8$classId = i;
        this.description = str;
    }

    public final String toString() {
        switch (this.$r8$classId) {
        }
        return this.description;
    }
}
