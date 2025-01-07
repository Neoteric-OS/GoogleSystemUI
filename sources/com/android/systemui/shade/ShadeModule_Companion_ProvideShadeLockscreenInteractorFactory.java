package com.android.systemui.shade;

import com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor;
import dagger.internal.DelegateFactory;
import dagger.internal.Provider;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ShadeModule_Companion_ProvideShadeLockscreenInteractorFactory implements Provider {
    public static ShadeLockscreenInteractor provideShadeLockscreenInteractor(DelegateFactory delegateFactory) {
        Object obj = delegateFactory.get();
        Intrinsics.checkNotNull(obj);
        return (ShadeLockscreenInteractor) obj;
    }
}
