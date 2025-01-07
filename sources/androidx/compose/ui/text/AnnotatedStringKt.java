package androidx.compose.ui.text;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AnnotatedStringKt {
    public static final AnnotatedString EmptyAnnotatedString = new AnnotatedString("");

    public static final boolean intersect(int i, int i2, int i3, int i4) {
        if (Math.max(i, i3) < Math.min(i2, i4)) {
            return true;
        }
        if (i <= i3 && i4 <= i2) {
            if (i2 != i4) {
                return true;
            }
            if ((i3 == i4) == (i == i2)) {
                return true;
            }
        }
        if (i3 <= i && i2 <= i4) {
            if (i4 != i2) {
                return true;
            }
            if ((i == i2) == (i3 == i4)) {
                return true;
            }
        }
        return false;
    }
}
