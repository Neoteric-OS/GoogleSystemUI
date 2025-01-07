package com.android.systemui.shade.domain.interactor;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShadeAnimationInteractorSceneContainerImpl$special$$inlined$flatMapLatest$2 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        ShadeAnimationInteractorSceneContainerImpl$special$$inlined$flatMapLatest$2 shadeAnimationInteractorSceneContainerImpl$special$$inlined$flatMapLatest$2 = new ShadeAnimationInteractorSceneContainerImpl$special$$inlined$flatMapLatest$2(3, (Continuation) obj3);
        shadeAnimationInteractorSceneContainerImpl$special$$inlined$flatMapLatest$2.L$0 = (FlowCollector) obj;
        shadeAnimationInteractorSceneContainerImpl$special$$inlined$flatMapLatest$2.L$1 = obj2;
        return shadeAnimationInteractorSceneContainerImpl$special$$inlined$flatMapLatest$2.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0054, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r5, r3) == false) goto L24;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r6.label
            r2 = 1
            if (r1 == 0) goto L15
            if (r1 != r2) goto Ld
            kotlin.ResultKt.throwOnFailure(r7)
            goto L6f
        Ld:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L15:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.Object r7 = r6.L$0
            kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
            java.lang.Object r1 = r6.L$1
            com.android.compose.animation.scene.ObservableTransitionState r1 = (com.android.compose.animation.scene.ObservableTransitionState) r1
            boolean r3 = r1 instanceof com.android.compose.animation.scene.ObservableTransitionState.Idle
            if (r3 == 0) goto L2c
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
            kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 r3 = new kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2
            r3.<init>(r1)
            goto L66
        L2c:
            boolean r3 = r1 instanceof com.android.compose.animation.scene.ObservableTransitionState.Transition
            if (r3 == 0) goto L72
            com.android.compose.animation.scene.ObservableTransitionState$Transition r1 = (com.android.compose.animation.scene.ObservableTransitionState.Transition) r1
            boolean r3 = r1.isInitiatedByUserInput
            if (r3 == 0) goto L5f
            com.android.compose.animation.scene.SceneKey r3 = com.android.systemui.scene.shared.model.Scenes.Shade
            com.android.compose.animation.scene.ContentKey r4 = r1.fromContent
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r3)
            if (r5 != 0) goto L56
            com.android.compose.animation.scene.ContentKey r5 = r1.toContent
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r3)
            if (r3 != 0) goto L56
            com.android.compose.animation.scene.SceneKey r3 = com.android.systemui.scene.shared.model.Scenes.QuickSettings
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r3)
            if (r4 != 0) goto L56
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r3)
            if (r3 == 0) goto L5f
        L56:
            kotlinx.coroutines.flow.SafeFlow r1 = r1.isUserInputOngoing
            com.android.systemui.shade.domain.interactor.ShadeAnimationInteractorSceneContainerImpl$isAnyCloseAnimationRunning$lambda$1$$inlined$map$1 r3 = new com.android.systemui.shade.domain.interactor.ShadeAnimationInteractorSceneContainerImpl$isAnyCloseAnimationRunning$lambda$1$$inlined$map$1
            r4 = 1
            r3.<init>(r1, r4)
            goto L66
        L5f:
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
            kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 r3 = new kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2
            r3.<init>(r1)
        L66:
            r6.label = r2
            java.lang.Object r6 = kotlinx.coroutines.flow.FlowKt.emitAll(r7, r3, r6)
            if (r6 != r0) goto L6f
            return r0
        L6f:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L72:
            kotlin.NoWhenBranchMatchedException r6 = new kotlin.NoWhenBranchMatchedException
            r6.<init>()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.shade.domain.interactor.ShadeAnimationInteractorSceneContainerImpl$special$$inlined$flatMapLatest$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
