package com.android.systemui.util.kotlin;

import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.flow.DistinctFlowImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class Utils {
    public static final Companion Companion = new Companion();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static Utils$Companion$sample$$inlined$combine$1 sampleFilter(Flow flow, Flow flow2, Function1 function1) {
            return new Utils$Companion$sample$$inlined$combine$1(1, new Utils$Companion$sampleFilter$$inlined$filter$1(FlowKt.sample(flow, flow2, Utils$Companion$sampleFilter$2.INSTANCE), function1));
        }

        public final SafeFlow sample(Flow flow, Flow flow2, Flow flow3) {
            return FlowKt.sample(flow, new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(flow2, flow3, Utils$Companion$sample$2.INSTANCE), new Utils$Companion$sample$3(3, this, Companion.class, "toTriple", "toTriple(Ljava/lang/Object;Lkotlin/Pair;)Lkotlin/Triple;", 4));
        }

        public final SafeFlow sample(Flow flow, Flow flow2, Flow flow3, StateFlow stateFlow) {
            return FlowKt.sample(flow, kotlinx.coroutines.flow.FlowKt.combine(flow2, flow3, stateFlow, Utils$Companion$sample$5.INSTANCE), new Utils$Companion$sample$6(3, this, Companion.class, "toQuad", "toQuad(Ljava/lang/Object;Lkotlin/Triple;)Lcom/android/systemui/util/kotlin/Quad;", 4));
        }

        public final SafeFlow sample(MutableStateFlow mutableStateFlow, MutableStateFlow mutableStateFlow2, ReadonlyStateFlow readonlyStateFlow, ReadonlyStateFlow readonlyStateFlow2, ReadonlyStateFlow readonlyStateFlow3) {
            return FlowKt.sample(mutableStateFlow, kotlinx.coroutines.flow.FlowKt.combine(mutableStateFlow2, readonlyStateFlow, readonlyStateFlow2, readonlyStateFlow3, Utils$Companion$sample$8.INSTANCE), new Utils$Companion$sample$9(3, this, Companion.class, "toQuint", "toQuint(Ljava/lang/Object;Lcom/android/systemui/util/kotlin/Quad;)Lcom/android/systemui/util/kotlin/Quint;", 4));
        }

        public final SafeFlow sample(Flow flow, SharedFlow sharedFlow, ReadonlyStateFlow readonlyStateFlow, StateFlow stateFlow, Flow flow2, Flow flow3) {
            return FlowKt.sample(flow, kotlinx.coroutines.flow.FlowKt.combine(sharedFlow, readonlyStateFlow, stateFlow, flow2, flow3, Utils$Companion$sample$11.INSTANCE), new Utils$Companion$sample$12(3, this, Companion.class, "toSextuple", "toSextuple(Ljava/lang/Object;Lcom/android/systemui/util/kotlin/Quint;)Lcom/android/systemui/util/kotlin/Sextuple;", 4));
        }

        public final SafeFlow sample(FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 flowKt__TransformKt$onEach$$inlined$unsafeTransform$1, ReadonlyStateFlow readonlyStateFlow, DistinctFlowImpl distinctFlowImpl, ReadonlyStateFlow readonlyStateFlow2, ReadonlyStateFlow readonlyStateFlow3, ReadonlyStateFlow readonlyStateFlow4, MutableStateFlow mutableStateFlow) {
            return FlowKt.sample(flowKt__TransformKt$onEach$$inlined$unsafeTransform$1, new Utils$Companion$sample$$inlined$combine$1(0, new Flow[]{readonlyStateFlow, distinctFlowImpl, readonlyStateFlow2, readonlyStateFlow3, readonlyStateFlow4, mutableStateFlow}), new Utils$Companion$sample$15(3, this, Companion.class, "toSeptuple", "toSeptuple(Ljava/lang/Object;Lcom/android/systemui/util/kotlin/Sextuple;)Lcom/android/systemui/util/kotlin/Septuple;", 4));
        }
    }
}
