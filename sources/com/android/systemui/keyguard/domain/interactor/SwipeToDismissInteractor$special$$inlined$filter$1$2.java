package com.android.systemui.keyguard.domain.interactor;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SwipeToDismissInteractor$special$$inlined$filter$1$2 implements FlowCollector {
    public final /* synthetic */ FlowCollector $this_unsafeFlow;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.domain.interactor.SwipeToDismissInteractor$special$$inlined$filter$1$2$1, reason: invalid class name */
    public final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SwipeToDismissInteractor$special$$inlined$filter$1$2.this.emit(null, this);
        }
    }

    public SwipeToDismissInteractor$special$$inlined$filter$1$2(FlowCollector flowCollector) {
        this.$this_unsafeFlow = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.android.systemui.keyguard.domain.interactor.SwipeToDismissInteractor$special$$inlined$filter$1$2.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            com.android.systemui.keyguard.domain.interactor.SwipeToDismissInteractor$special$$inlined$filter$1$2$1 r0 = (com.android.systemui.keyguard.domain.interactor.SwipeToDismissInteractor$special$$inlined$filter$1$2.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.keyguard.domain.interactor.SwipeToDismissInteractor$special$$inlined$filter$1$2$1 r0 = new com.android.systemui.keyguard.domain.interactor.SwipeToDismissInteractor$special$$inlined$filter$1$2$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            kotlin.ResultKt.throwOnFailure(r8)
            goto L66
        L27:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2f:
            kotlin.ResultKt.throwOnFailure(r8)
            r8 = r7
            com.android.systemui.util.kotlin.Quad r8 = (com.android.systemui.util.kotlin.Quad) r8
            java.lang.Object r2 = r8.first
            com.android.systemui.shade.data.repository.FlingInfo r2 = (com.android.systemui.shade.data.repository.FlingInfo) r2
            java.lang.Object r4 = r8.second
            com.android.systemui.keyguard.shared.model.TransitionStep r4 = (com.android.systemui.keyguard.shared.model.TransitionStep) r4
            java.lang.Object r5 = r8.third
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            java.lang.Object r8 = r8.fourth
            com.android.systemui.keyguard.shared.model.StatusBarState r8 = (com.android.systemui.keyguard.shared.model.StatusBarState) r8
            if (r2 == 0) goto L66
            boolean r2 = r2.expand
            if (r2 != 0) goto L66
            com.android.systemui.keyguard.shared.model.StatusBarState r2 = com.android.systemui.keyguard.shared.model.StatusBarState.SHADE_LOCKED
            if (r8 == r2) goto L66
            com.android.systemui.keyguard.shared.model.KeyguardState r8 = r4.to
            com.android.systemui.keyguard.shared.model.KeyguardState r2 = com.android.systemui.keyguard.shared.model.KeyguardState.LOCKSCREEN
            if (r8 != r2) goto L66
            if (r5 == 0) goto L66
            r0.label = r3
            kotlinx.coroutines.flow.FlowCollector r6 = r6.$this_unsafeFlow
            java.lang.Object r6 = r6.emit(r7, r0)
            if (r6 != r1) goto L66
            return r1
        L66:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.SwipeToDismissInteractor$special$$inlined$filter$1$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
