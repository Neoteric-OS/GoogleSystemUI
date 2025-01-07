package com.android.systemui.shared.clocks;

import com.android.systemui.log.core.Logger;
import com.android.systemui.plugins.PluginLifecycleManager;
import com.android.systemui.shared.clocks.ClockRegistry;
import java.util.Iterator;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ClockRegistry$verifyLoadedProviders$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ ClockRegistry this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClockRegistry$verifyLoadedProviders$1(ClockRegistry clockRegistry, Continuation continuation) {
        super(2, continuation);
        this.this$0 = clockRegistry;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ClockRegistry$verifyLoadedProviders$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ClockRegistry$verifyLoadedProviders$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        ClockRegistry clockRegistry = this.this$0;
        synchronized (clockRegistry.availableClocks) {
            try {
                clockRegistry.isQueued.set(false);
                ClockRegistry.ClockInfo clockInfo = (ClockRegistry.ClockInfo) clockRegistry.availableClocks.get(clockRegistry.getCurrentClockId());
                if (clockInfo == null) {
                    Logger.i$default(clockRegistry.logger, "verifyLoadedProviders: currentClock=null", null, 2, null);
                    Iterator it = clockRegistry.availableClocks.entrySet().iterator();
                    while (it.hasNext()) {
                        PluginLifecycleManager pluginLifecycleManager = ((ClockRegistry.ClockInfo) ((Map.Entry) it.next()).getValue()).manager;
                        if (pluginLifecycleManager != null) {
                            pluginLifecycleManager.unloadPlugin();
                        }
                    }
                    return Unit.INSTANCE;
                }
                Logger.i$default(clockRegistry.logger, "verifyLoadedProviders: load currentClock", null, 2, null);
                PluginLifecycleManager pluginLifecycleManager2 = clockInfo.manager;
                if (pluginLifecycleManager2 != null) {
                    pluginLifecycleManager2.loadPlugin();
                }
                Iterator it2 = clockRegistry.availableClocks.entrySet().iterator();
                while (it2.hasNext()) {
                    PluginLifecycleManager pluginLifecycleManager3 = ((ClockRegistry.ClockInfo) ((Map.Entry) it2.next()).getValue()).manager;
                    if (pluginLifecycleManager3 != null && !Intrinsics.areEqual(pluginLifecycleManager2, pluginLifecycleManager3)) {
                        pluginLifecycleManager3.unloadPlugin();
                    }
                }
                return Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
