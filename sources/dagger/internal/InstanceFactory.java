package dagger.internal;

import com.android.systemui.unfold.UnfoldTransitionProgressProvider;
import dagger.Lazy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class InstanceFactory implements Provider, Lazy {
    public final UnfoldTransitionProgressProvider instance;

    public InstanceFactory(UnfoldTransitionProgressProvider unfoldTransitionProgressProvider) {
        this.instance = unfoldTransitionProgressProvider;
    }

    @Override // javax.inject.Provider
    public final Object get() {
        return this.instance;
    }
}
