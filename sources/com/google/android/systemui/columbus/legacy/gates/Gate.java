package com.google.android.systemui.columbus.legacy.gates;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.android.HandlerContext;
import kotlinx.coroutines.internal.ContextScope;
import kotlinx.coroutines.internal.MainDispatcherLoader;
import kotlinx.coroutines.scheduling.DefaultScheduler;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class Gate {
    public boolean active;
    public final ContextScope coroutineScope;
    public boolean isBlocked;
    public final Set listeners;
    public final CoroutineDispatcher mainDispatcher;
    public final HandlerContext mainPostDispatcher;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Listener {
        void onGateChanged(Gate gate);
    }

    public Gate() {
        DefaultScheduler defaultScheduler = Dispatchers.Default;
        HandlerContext handlerContext = MainDispatcherLoader.dispatcher;
        HandlerContext handlerContext2 = handlerContext.immediate;
        this.mainDispatcher = handlerContext2;
        this.mainPostDispatcher = handlerContext;
        this.coroutineScope = CoroutineScopeKt.CoroutineScope(handlerContext2);
        this.listeners = new LinkedHashSet();
    }

    public final boolean isActive() {
        return ((Boolean) BuildersKt.runBlocking(this.mainDispatcher, new Gate$isActive$1(this, null))).booleanValue();
    }

    public final boolean isBlocking() {
        return ((Boolean) BuildersKt.runBlocking(this.mainDispatcher, new Gate$isBlocking$1(this, null))).booleanValue();
    }

    public abstract void onActivate();

    public abstract void onDeactivate();

    public final void registerListener(Listener listener) {
        BuildersKt.launch$default(this.coroutineScope, null, null, new Gate$registerListener$1(listener, this, null), 3);
    }

    public final void setBlocking(boolean z) {
        BuildersKt.launch$default(this.coroutineScope, null, null, new Gate$setBlocking$1(this, z, null), 3);
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    public final void unregisterListener(Listener listener) {
        BuildersKt.launch$default(this.coroutineScope, null, null, new Gate$unregisterListener$1(listener, this, null), 3);
    }
}
