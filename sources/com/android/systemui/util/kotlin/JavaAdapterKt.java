package com.android.systemui.util.kotlin;

import android.view.View;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleKt;
import com.android.systemui.dreams.DreamOverlayContainerViewController$$ExternalSyntheticLambda1;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttached$1;
import java.util.function.Consumer;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class JavaAdapterKt {
    public static final RepeatWhenAttachedKt$repeatWhenAttached$1 collectFlow(View view, Flow flow, Consumer consumer, CoroutineContext coroutineContext) {
        return collectFlow$default(view, flow, consumer, coroutineContext, 16);
    }

    public static RepeatWhenAttachedKt$repeatWhenAttached$1 collectFlow$default(View view, Flow flow, Consumer consumer, CoroutineContext coroutineContext, int i) {
        if ((i & 8) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return RepeatWhenAttachedKt.repeatWhenAttached(view, coroutineContext, new JavaAdapterKt$collectFlow$1(Lifecycle.State.CREATED, flow, consumer, null));
    }

    public static final FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 combineFlows(Flow flow, Flow flow2, StateFlow stateFlow, ReadonlySharedFlow readonlySharedFlow, DreamOverlayContainerViewController$$ExternalSyntheticLambda1 dreamOverlayContainerViewController$$ExternalSyntheticLambda1) {
        return kotlinx.coroutines.flow.FlowKt.combine(flow, flow2, stateFlow, readonlySharedFlow, new JavaAdapterKt$combineFlows$3(5, dreamOverlayContainerViewController$$ExternalSyntheticLambda1, Intrinsics.Kotlin.class, "suspendConversion0", "combineFlows$suspendConversion0$1(Lkotlin/jvm/functions/Function4;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0));
    }

    public static final void collectFlow(View view, Flow flow, Consumer consumer) {
        collectFlow$default(view, flow, consumer, null, 24);
    }

    public static final StandaloneCoroutine collectFlow(Lifecycle lifecycle, Flow flow, Consumer consumer) {
        return BuildersKt.launch$default(LifecycleKt.getCoroutineScope(lifecycle), null, null, new JavaAdapterKt$collectFlow$2(lifecycle, Lifecycle.State.CREATED, flow, consumer, null), 3);
    }
}
