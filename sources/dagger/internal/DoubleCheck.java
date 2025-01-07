package dagger.internal;

import dagger.Lazy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DoubleCheck implements Provider, Lazy {
    public static final Object UNINITIALIZED = new Object();
    public volatile Object instance = UNINITIALIZED;
    public volatile Provider provider;

    public DoubleCheck(Provider provider) {
        this.provider = provider;
    }

    public static Lazy lazy(Provider provider) {
        if (provider instanceof Lazy) {
            return (Lazy) provider;
        }
        provider.getClass();
        return new DoubleCheck(provider);
    }

    public static Provider provider(Provider provider) {
        provider.getClass();
        return provider instanceof DoubleCheck ? provider : new DoubleCheck(provider);
    }

    @Override // javax.inject.Provider
    public final Object get() {
        Object obj = this.instance;
        Object obj2 = UNINITIALIZED;
        if (obj == obj2) {
            synchronized (this) {
                try {
                    obj = this.instance;
                    if (obj == obj2) {
                        obj = this.provider.get();
                        Object obj3 = this.instance;
                        if (obj3 != obj2 && obj3 != obj) {
                            throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj3 + " & " + obj + ". This is likely due to a circular dependency.");
                        }
                        this.instance = obj;
                        this.provider = null;
                    }
                } finally {
                }
            }
        }
        return obj;
    }
}
