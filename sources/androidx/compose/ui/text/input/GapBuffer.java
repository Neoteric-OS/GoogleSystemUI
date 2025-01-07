package androidx.compose.ui.text.input;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class GapBuffer {
    public char[] buffer;
    public int capacity;
    public int gapEnd;
    public int gapStart;

    public final int gapLength() {
        return this.gapEnd - this.gapStart;
    }

    public final String toString() {
        return "";
    }
}
