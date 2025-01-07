package androidx.compose.ui.platform;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AccessibilityIterators$AbstractTextSegmentIterator implements AccessibilityIterators$TextSegmentIterator {
    public final int[] segment = new int[2];
    public String text;

    public final int[] getRange(int i, int i2) {
        if (i < 0 || i2 < 0 || i == i2) {
            return null;
        }
        int[] iArr = this.segment;
        iArr[0] = i;
        iArr[1] = i2;
        return iArr;
    }
}
