package androidx.concurrent.futures;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ResolvableFuture extends AbstractResolvableFuture {
    public final boolean set(Object obj) {
        if (obj == null) {
            obj = AbstractResolvableFuture.NULL;
        }
        if (!AbstractResolvableFuture.ATOMIC_HELPER.casValue(this, null, obj)) {
            return false;
        }
        AbstractResolvableFuture.complete(this);
        return true;
    }
}
