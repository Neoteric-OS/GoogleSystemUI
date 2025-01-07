package com.android.systemui.unfold;

import android.util.StatsEvent;
import android.util.StatsLog;
import com.android.systemui.unfold.DisplaySwitchLatencyTracker;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class DisplaySwitchLatencyTracker$start$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ DisplaySwitchLatencyTracker this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DisplaySwitchLatencyTracker$start$1(DisplaySwitchLatencyTracker displaySwitchLatencyTracker, Continuation continuation) {
        super(2, continuation);
        this.this$0 = displaySwitchLatencyTracker;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new DisplaySwitchLatencyTracker$start$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DisplaySwitchLatencyTracker$start$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ChannelFlowTransformLatest transformLatest = FlowKt.transformLatest(new DisplaySwitchLatencyTracker$start$1$invokeSuspend$$inlined$filter$1(com.android.systemui.util.kotlin.FlowKt.pairwise(this.this$0.deviceStateRepository.state), 0), new DisplaySwitchLatencyTracker$start$1$invokeSuspend$$inlined$flatMapLatest$1(this.this$0, null));
            final DisplaySwitchLatencyTracker displaySwitchLatencyTracker = this.this$0;
            FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.unfold.DisplaySwitchLatencyTracker$start$1.3
                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj2, Continuation continuation) {
                    DisplaySwitchLatencyTracker.DisplaySwitchLatencyEvent displaySwitchLatencyEvent = (DisplaySwitchLatencyTracker.DisplaySwitchLatencyEvent) obj2;
                    DisplaySwitchLatencyTracker.this.displaySwitchLatencyLogger.getClass();
                    int[] intArray = CollectionsKt.toIntArray(displaySwitchLatencyEvent.fromVisibleAppsUid);
                    int[] intArray2 = CollectionsKt.toIntArray(displaySwitchLatencyEvent.toVisibleAppsUid);
                    StatsEvent.Builder newBuilder = StatsEvent.newBuilder();
                    newBuilder.setAtomId(753);
                    newBuilder.writeInt(displaySwitchLatencyEvent.latencyMs);
                    newBuilder.writeInt(displaySwitchLatencyEvent.fromFoldableDeviceState);
                    newBuilder.writeInt(0);
                    newBuilder.writeInt(-1);
                    newBuilder.addBooleanAnnotation((byte) 1, true);
                    newBuilder.writeInt(-1);
                    newBuilder.addBooleanAnnotation((byte) 1, true);
                    newBuilder.writeIntArray(intArray);
                    newBuilder.addBooleanAnnotation((byte) 1, true);
                    newBuilder.writeInt(-1);
                    newBuilder.writeInt(displaySwitchLatencyEvent.toState);
                    newBuilder.writeInt(displaySwitchLatencyEvent.toFoldableDeviceState);
                    newBuilder.writeInt(-1);
                    newBuilder.addBooleanAnnotation((byte) 1, true);
                    newBuilder.writeInt(-1);
                    newBuilder.addBooleanAnnotation((byte) 1, true);
                    newBuilder.writeIntArray(intArray2);
                    newBuilder.addBooleanAnnotation((byte) 1, true);
                    newBuilder.writeInt(-1);
                    newBuilder.writeInt(-1);
                    newBuilder.writeInt(-1);
                    newBuilder.writeInt(0);
                    newBuilder.writeInt(-1);
                    newBuilder.writeInt(-1);
                    newBuilder.writeInt(-1);
                    newBuilder.writeInt(-1);
                    newBuilder.writeInt(-1);
                    newBuilder.usePooledBuffer();
                    StatsLog.write(newBuilder.build());
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (transformLatest.collect(flowCollector, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
