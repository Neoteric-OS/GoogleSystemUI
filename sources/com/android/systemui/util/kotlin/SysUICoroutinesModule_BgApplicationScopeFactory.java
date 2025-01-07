package com.android.systemui.util.kotlin;

import dagger.internal.Provider;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SysUICoroutinesModule_BgApplicationScopeFactory implements Provider {
    public static ContextScope bgApplicationScope(SysUICoroutinesModule sysUICoroutinesModule, CoroutineScope coroutineScope, CoroutineContext coroutineContext) {
        sysUICoroutinesModule.getClass();
        return new ContextScope(coroutineScope.getCoroutineContext().plus(coroutineContext));
    }
}
