package com.android.systemui.unfold;

import com.android.systemui.util.kotlin.WithPrev;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class DisplaySwitchLatencyTracker$start$1$2$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ WithPrev $foldableDeviceState;
    int I$0;
    long J$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ DisplaySwitchLatencyTracker this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DisplaySwitchLatencyTracker$start$1$2$1(DisplaySwitchLatencyTracker displaySwitchLatencyTracker, WithPrev withPrev, Continuation continuation) {
        super(2, continuation);
        this.this$0 = displaySwitchLatencyTracker;
        this.$foldableDeviceState = withPrev;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DisplaySwitchLatencyTracker$start$1$2$1 displaySwitchLatencyTracker$start$1$2$1 = new DisplaySwitchLatencyTracker$start$1$2$1(this.this$0, this.$foldableDeviceState, continuation);
        displaySwitchLatencyTracker$start$1$2$1.L$0 = obj;
        return displaySwitchLatencyTracker$start$1$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DisplaySwitchLatencyTracker$start$1$2$1) create((FlowCollector) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0162 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x011f  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r24) {
        /*
            Method dump skipped, instructions count: 358
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.unfold.DisplaySwitchLatencyTracker$start$1$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
