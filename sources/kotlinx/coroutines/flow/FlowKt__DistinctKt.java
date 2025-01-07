package kotlinx.coroutines.flow;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract /* synthetic */ class FlowKt__DistinctKt {
    public static final Function1 defaultKeySelector = new Function1() { // from class: kotlinx.coroutines.flow.FlowKt__DistinctKt$defaultKeySelector$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return obj;
        }
    };
    public static final Function2 defaultAreEquivalent = new Function2() { // from class: kotlinx.coroutines.flow.FlowKt__DistinctKt$defaultAreEquivalent$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return Boolean.valueOf(Intrinsics.areEqual(obj, obj2));
        }
    };

    /* JADX WARN: Code restructure failed: missing block: B:6:0x000d, code lost:
    
        if (r0.areEquivalent == r4) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlinx.coroutines.flow.DistinctFlowImpl distinctUntilChangedBy$FlowKt__DistinctKt(kotlinx.coroutines.flow.Flow r2, kotlin.jvm.functions.Function1 r3, kotlin.jvm.functions.Function2 r4) {
        /*
            boolean r0 = r2 instanceof kotlinx.coroutines.flow.DistinctFlowImpl
            if (r0 == 0) goto L10
            r0 = r2
            kotlinx.coroutines.flow.DistinctFlowImpl r0 = (kotlinx.coroutines.flow.DistinctFlowImpl) r0
            kotlin.jvm.internal.Lambda r1 = r0.keySelector
            if (r1 != r3) goto L10
            kotlin.jvm.internal.Lambda r0 = r0.areEquivalent
            if (r0 != r4) goto L10
            goto L16
        L10:
            kotlinx.coroutines.flow.DistinctFlowImpl r0 = new kotlinx.coroutines.flow.DistinctFlowImpl
            r0.<init>(r2, r3, r4)
            r2 = r0
        L16:
            kotlinx.coroutines.flow.DistinctFlowImpl r2 = (kotlinx.coroutines.flow.DistinctFlowImpl) r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__DistinctKt.distinctUntilChangedBy$FlowKt__DistinctKt(kotlinx.coroutines.flow.Flow, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function2):kotlinx.coroutines.flow.DistinctFlowImpl");
    }
}
