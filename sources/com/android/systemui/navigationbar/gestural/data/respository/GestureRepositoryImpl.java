package com.android.systemui.navigationbar.gestural.data.respository;

import com.android.systemui.navigationbar.gestural.domain.TaskMatcher;
import kotlin.Unit;
import kotlin.collections.EmptySet;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GestureRepositoryImpl {
    public final StateFlowImpl _gestureBlockedMatchers = StateFlowKt.MutableStateFlow(EmptySet.INSTANCE);
    public final CoroutineDispatcher mainDispatcher;

    public GestureRepositoryImpl(CoroutineDispatcher coroutineDispatcher) {
        this.mainDispatcher = coroutineDispatcher;
    }

    public final Object addGestureBlockedMatcher(TaskMatcher taskMatcher, Continuation continuation) {
        Object withContext = BuildersKt.withContext(this.mainDispatcher, new GestureRepositoryImpl$addGestureBlockedMatcher$2(this, taskMatcher, null), continuation);
        return withContext == CoroutineSingletons.COROUTINE_SUSPENDED ? withContext : Unit.INSTANCE;
    }

    public final Object removeGestureBlockedMatcher(TaskMatcher taskMatcher, Continuation continuation) {
        Object withContext = BuildersKt.withContext(this.mainDispatcher, new GestureRepositoryImpl$removeGestureBlockedMatcher$2(this, taskMatcher, null), continuation);
        return withContext == CoroutineSingletons.COROUTINE_SUSPENDED ? withContext : Unit.INSTANCE;
    }
}
