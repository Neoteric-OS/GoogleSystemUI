package com.android.systemui.education.dagger;

import com.android.app.tracing.coroutines.TraceContextElementKt;
import com.android.app.tracing.coroutines.TraceDataThreadLocal;
import dagger.internal.Provider;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ContextualEducationModule_Companion_ProvideEduDataStoreScopeFactory implements Provider {
    public static ContextScope provideEduDataStoreScope(CoroutineDispatcher coroutineDispatcher) {
        CoroutineContext plus = CoroutineContext.DefaultImpls.plus(coroutineDispatcher, SupervisorKt.SupervisorJob$default());
        TraceDataThreadLocal traceDataThreadLocal = TraceContextElementKt.traceThreadLocal;
        return CoroutineScopeKt.CoroutineScope(plus.plus(EmptyCoroutineContext.INSTANCE));
    }
}
