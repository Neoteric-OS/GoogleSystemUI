package com.android.systemui.communal.ui.compose;

import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CommunalHubKt$CommunalHub$4$1$1 extends RestrictedSuspendLambda implements Function2 {
    final /* synthetic */ BaseCommunalViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommunalHubKt$CommunalHub$4$1$1(BaseCommunalViewModel baseCommunalViewModel, Continuation continuation) {
        super(continuation);
        this.$viewModel = baseCommunalViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        CommunalHubKt$CommunalHub$4$1$1 communalHubKt$CommunalHub$4$1$1 = new CommunalHubKt$CommunalHub$4$1$1(this.$viewModel, continuation);
        communalHubKt$CommunalHub$4$1$1.L$0 = obj;
        return communalHubKt$CommunalHub$4$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        ((CommunalHubKt$CommunalHub$4$1$1) create((AwaitPointerEventScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0065 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0037 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0074  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0063 -> B:6:0x0066). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r9.label
            r2 = 1
            r3 = 2
            r4 = 0
            if (r1 == 0) goto L25
            if (r1 == r2) goto L1d
            if (r1 != r3) goto L15
            java.lang.Object r1 = r9.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
            kotlin.ResultKt.throwOnFailure(r10)
            goto L66
        L15:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L1d:
            java.lang.Object r1 = r9.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
            kotlin.ResultKt.throwOnFailure(r10)
            goto L38
        L25:
            kotlin.ResultKt.throwOnFailure(r10)
            java.lang.Object r10 = r9.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r10 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r10
            r1 = r10
        L2d:
            r9.L$0 = r1
            r9.label = r2
            java.lang.Object r10 = androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitFirstDown$default(r1, r4, r9, r3)
            if (r10 != r0) goto L38
            return r0
        L38:
            androidx.compose.ui.input.pointer.PointerInputChange r10 = (androidx.compose.ui.input.pointer.PointerInputChange) r10
            com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel r5 = r9.$viewModel
            r5.onResetTouchState()
            boolean r10 = r10.isConsumed()
            if (r10 == 0) goto L5b
            com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel r10 = r9.$viewModel
            kotlinx.coroutines.flow.StateFlowImpl r10 = r10._isTouchConsumed
            java.lang.Object r5 = r10.getValue()
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L56
            goto L5b
        L56:
            java.lang.Boolean r5 = java.lang.Boolean.TRUE
            r10.updateState(r4, r5)
        L5b:
            r9.L$0 = r1
            r9.label = r3
            java.lang.Object r10 = androidx.compose.ui.input.pointer.AwaitPointerEventScope.awaitPointerEvent$default(r1, r9)
            if (r10 != r0) goto L66
            return r0
        L66:
            androidx.compose.ui.input.pointer.PointerEvent r10 = (androidx.compose.ui.input.pointer.PointerEvent) r10
            java.util.List r5 = r10.changes
            java.util.Iterator r5 = r5.iterator()
        L6e:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L97
            java.lang.Object r6 = r5.next()
            androidx.compose.ui.input.pointer.PointerInputChange r6 = (androidx.compose.ui.input.pointer.PointerInputChange) r6
            boolean r6 = r6.isConsumed()
            if (r6 == 0) goto L6e
            com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel r6 = r9.$viewModel
            kotlinx.coroutines.flow.StateFlowImpl r6 = r6._isTouchConsumed
            java.lang.Object r7 = r6.getValue()
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 == 0) goto L91
            goto L6e
        L91:
            java.lang.Boolean r7 = java.lang.Boolean.TRUE
            r6.updateState(r4, r7)
            goto L6e
        L97:
            java.util.List r10 = r10.changes
            int r5 = r10.size()
            r6 = 0
        L9e:
            if (r6 >= r5) goto Lb5
            java.lang.Object r7 = r10.get(r6)
            androidx.compose.ui.input.pointer.PointerInputChange r7 = (androidx.compose.ui.input.pointer.PointerInputChange) r7
            boolean r8 = androidx.compose.ui.input.pointer.PointerEventKt.changedToUp(r7)
            if (r8 != 0) goto Lb2
            boolean r7 = androidx.compose.ui.input.pointer.PointerEventKt.changedToUpIgnoreConsumed(r7)
            if (r7 == 0) goto L5b
        Lb2:
            int r6 = r6 + 1
            goto L9e
        Lb5:
            com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel r10 = r9.$viewModel
            r10.onResetTouchState()
            goto L2d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHub$4$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
