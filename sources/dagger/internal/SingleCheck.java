package dagger.internal;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SingleCheck implements Provider {
    public static final Object UNINITIALIZED = new Object();
    public volatile Object instance;
    public volatile Provider provider;

    public static Provider provider(Provider provider) {
        if ((provider instanceof SingleCheck) || (provider instanceof DoubleCheck)) {
            return provider;
        }
        SingleCheck singleCheck = new SingleCheck();
        singleCheck.instance = UNINITIALIZED;
        singleCheck.provider = provider;
        return singleCheck;
    }

    @Override // javax.inject.Provider
    public final Object get() {
        Object obj = this.instance;
        if (obj != UNINITIALIZED) {
            return obj;
        }
        Provider provider = this.provider;
        if (provider == null) {
            return this.instance;
        }
        Object obj2 = provider.get();
        this.instance = obj2;
        this.provider = null;
        return obj2;
    }
}
