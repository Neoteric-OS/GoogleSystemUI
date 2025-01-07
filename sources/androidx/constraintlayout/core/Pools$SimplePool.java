package androidx.constraintlayout.core;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Pools$SimplePool {
    public final Object[] mPool = new Object[256];
    public int mPoolSize;

    public final void release(ArrayRow arrayRow) {
        int i = this.mPoolSize;
        Object[] objArr = this.mPool;
        if (i < objArr.length) {
            objArr[i] = arrayRow;
            this.mPoolSize = i + 1;
        }
    }
}
