package com.android.wm.shell.dagger;

import dagger.internal.Provider;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.android.HandlerContext;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellCoroutinesModule_ProvideBackgroundCoroutineScopeFactory implements Provider {
    public static ContextScope provideBackgroundCoroutineScope(WMShellCoroutinesModule wMShellCoroutinesModule, HandlerContext handlerContext) {
        wMShellCoroutinesModule.getClass();
        return CoroutineScopeKt.CoroutineScope(handlerContext);
    }
}
