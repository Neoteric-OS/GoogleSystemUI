package androidx.window.embedding;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SplitController$SplitSupportStatus {
    public final int rawValue;
    public static final SplitController$SplitSupportStatus SPLIT_AVAILABLE = new SplitController$SplitSupportStatus(0);
    public static final SplitController$SplitSupportStatus SPLIT_UNAVAILABLE = new SplitController$SplitSupportStatus(1);
    public static final SplitController$SplitSupportStatus SPLIT_ERROR_PROPERTY_NOT_DECLARED = new SplitController$SplitSupportStatus(2);

    public SplitController$SplitSupportStatus(int i) {
        this.rawValue = i;
    }

    public final String toString() {
        int i = this.rawValue;
        return i != 0 ? i != 1 ? i != 2 ? "UNKNOWN" : "SplitSupportStatus: ERROR_SPLIT_PROPERTY_NOT_DECLARED" : "SplitSupportStatus: UNAVAILABLE" : "SplitSupportStatus: AVAILABLE";
    }
}
