package androidx.datastore.core;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Data extends State {
    public final int hashCode;
    public final Object value;

    public Data(int i, int i2, Object obj) {
        super(i2);
        this.value = obj;
        this.hashCode = i;
    }
}
