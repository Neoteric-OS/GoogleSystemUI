package androidx.compose.runtime;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyInfo {
    public final int key;
    public final int location;
    public final int nodes;
    public final Object objectKey;

    public KeyInfo(Object obj, int i, int i2, int i3) {
        this.key = i;
        this.objectKey = obj;
        this.location = i2;
        this.nodes = i3;
    }
}
