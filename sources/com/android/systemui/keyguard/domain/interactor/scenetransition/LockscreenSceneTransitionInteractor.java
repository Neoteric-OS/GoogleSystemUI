package com.android.systemui.keyguard.domain.interactor.scenetransition;

import com.android.systemui.CoreStartable;
import com.android.systemui.keyguard.data.repository.LockscreenSceneTransitionRepository;
import com.android.systemui.keyguard.domain.interactor.InternalKeyguardTransitionInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.scene.domain.interactor.SceneInteractor;
import java.util.UUID;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LockscreenSceneTransitionInteractor implements CoreStartable {
    public final CoroutineScope applicationScope;
    public UUID currentTransitionId;
    public final InternalKeyguardTransitionInteractor internalTransitionInteractor;
    public StandaloneCoroutine progressJob;
    public final LockscreenSceneTransitionRepository repository;
    public final SceneInteractor sceneInteractor;
    public final KeyguardTransitionInteractor transitionInteractor;

    public LockscreenSceneTransitionInteractor(KeyguardTransitionInteractor keyguardTransitionInteractor, InternalKeyguardTransitionInteractor internalKeyguardTransitionInteractor, CoroutineScope coroutineScope, SceneInteractor sceneInteractor, LockscreenSceneTransitionRepository lockscreenSceneTransitionRepository) {
        this.transitionInteractor = keyguardTransitionInteractor;
        this.internalTransitionInteractor = internalKeyguardTransitionInteractor;
        this.applicationScope = coroutineScope;
        this.sceneInteractor = sceneInteractor;
        this.repository = lockscreenSceneTransitionRepository;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0031  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$handleTransition(com.android.compose.animation.scene.ObservableTransitionState.Transition r17, com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor r18, kotlin.coroutines.Continuation r19) {
        /*
            Method dump skipped, instructions count: 361
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor.access$handleTransition(com.android.compose.animation.scene.ObservableTransitionState$Transition, com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object finishCurrentTransition$1(kotlin.coroutines.Continuation r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor$finishCurrentTransition$1
            if (r0 == 0) goto L13
            r0 = r6
            com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor$finishCurrentTransition$1 r0 = (com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor$finishCurrentTransition$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor$finishCurrentTransition$1 r0 = new com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor$finishCurrentTransition$1
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            java.lang.Object r5 = r0.L$0
            com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor r5 = (com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4c
        L2b:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L33:
            kotlin.ResultKt.throwOnFailure(r6)
            java.util.UUID r6 = r5.currentTransitionId
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            com.android.systemui.keyguard.shared.model.TransitionState r2 = com.android.systemui.keyguard.shared.model.TransitionState.FINISHED
            r0.L$0 = r5
            r0.label = r3
            com.android.systemui.keyguard.domain.interactor.InternalKeyguardTransitionInteractor r3 = r5.internalTransitionInteractor
            r4 = 1065353216(0x3f800000, float:1.0)
            java.lang.Object r6 = r3.updateTransition(r6, r4, r2, r0)
            if (r6 != r1) goto L4c
            return r1
        L4c:
            r5.resetTransitionData$1()
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor.finishCurrentTransition$1(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0089 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object finishReversedTransitionTo$1(com.android.systemui.keyguard.shared.model.KeyguardState r12, kotlin.coroutines.Continuation r13) {
        /*
            r11 = this;
            boolean r0 = r13 instanceof com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor$finishReversedTransitionTo$1
            if (r0 == 0) goto L13
            r0 = r13
            com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor$finishReversedTransitionTo$1 r0 = (com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor$finishReversedTransitionTo$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor$finishReversedTransitionTo$1 r0 = new com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor$finishReversedTransitionTo$1
            r0.<init>(r11, r13)
        L18:
            java.lang.Object r13 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L42
            if (r2 == r4) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r11 = r0.L$0
            com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor r11 = (com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor) r11
            kotlin.ResultKt.throwOnFailure(r13)
            goto L8b
        L2e:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L36:
            java.lang.Object r11 = r0.L$1
            com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor r11 = (com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor) r11
            java.lang.Object r12 = r0.L$0
            com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor r12 = (com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor) r12
            kotlin.ResultKt.throwOnFailure(r13)
            goto L6d
        L42:
            kotlin.ResultKt.throwOnFailure(r13)
            com.android.systemui.keyguard.shared.model.TransitionInfo r13 = new com.android.systemui.keyguard.shared.model.TransitionInfo
            com.android.systemui.keyguard.domain.interactor.InternalKeyguardTransitionInteractor r2 = r11.internalTransitionInteractor
            kotlinx.coroutines.flow.ReadonlyStateFlow r5 = r2.currentTransitionInfoInternal
            java.lang.Object r5 = r5.getValue()
            com.android.systemui.keyguard.shared.model.TransitionInfo r5 = (com.android.systemui.keyguard.shared.model.TransitionInfo) r5
            com.android.systemui.keyguard.shared.model.KeyguardState r7 = r5.to
            com.android.systemui.keyguard.shared.model.TransitionModeOnCanceled r10 = com.android.systemui.keyguard.shared.model.TransitionModeOnCanceled.REVERSE
            java.lang.String r6 = "LockscreenSceneTransitionInteractor"
            r9 = 0
            r5 = r13
            r8 = r12
            r5.<init>(r6, r7, r8, r9, r10)
            r0.L$0 = r11
            r0.L$1 = r11
            r0.label = r4
            com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl r12 = r2.repository
            java.lang.Object r13 = r12.startTransition(r13, r0)
            if (r13 != r1) goto L6c
            return r1
        L6c:
            r12 = r11
        L6d:
            java.util.UUID r13 = (java.util.UUID) r13
            r11.currentTransitionId = r13
            com.android.systemui.keyguard.domain.interactor.InternalKeyguardTransitionInteractor r11 = r12.internalTransitionInteractor
            java.util.UUID r13 = r12.currentTransitionId
            kotlin.jvm.internal.Intrinsics.checkNotNull(r13)
            com.android.systemui.keyguard.shared.model.TransitionState r2 = com.android.systemui.keyguard.shared.model.TransitionState.FINISHED
            r0.L$0 = r12
            r4 = 0
            r0.L$1 = r4
            r0.label = r3
            r3 = 1065353216(0x3f800000, float:1.0)
            java.lang.Object r11 = r11.updateTransition(r13, r3, r2, r0)
            if (r11 != r1) goto L8a
            return r1
        L8a:
            r11 = r12
        L8b:
            r11.resetTransitionData$1()
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor.finishReversedTransitionTo$1(com.android.systemui.keyguard.shared.model.KeyguardState, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void resetTransitionData$1() {
        StandaloneCoroutine standaloneCoroutine = this.progressJob;
        if (standaloneCoroutine != null) {
            standaloneCoroutine.cancel(null);
        }
        this.progressJob = null;
        this.currentTransitionId = null;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        this.sceneInteractor.onSceneAboutToChangeListener.add(this);
        BuildersKt.launch$default(this.applicationScope, null, null, new LockscreenSceneTransitionInteractor$listenForSceneTransitionProgress$1(this, null), 3);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object startTransition$1(com.android.systemui.keyguard.shared.model.TransitionInfo r5, kotlin.coroutines.jvm.internal.ContinuationImpl r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor$startTransition$1
            if (r0 == 0) goto L13
            r0 = r6
            com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor$startTransition$1 r0 = (com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor$startTransition$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor$startTransition$1 r0 = new com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor$startTransition$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            java.lang.Object r4 = r0.L$0
            com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor r4 = (com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor) r4
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4c
        L2b:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L33:
            kotlin.ResultKt.throwOnFailure(r6)
            java.util.UUID r6 = r4.currentTransitionId
            if (r6 == 0) goto L3d
            r4.resetTransitionData$1()
        L3d:
            r0.L$0 = r4
            r0.label = r3
            com.android.systemui.keyguard.domain.interactor.InternalKeyguardTransitionInteractor r6 = r4.internalTransitionInteractor
            com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl r6 = r6.repository
            java.lang.Object r6 = r6.startTransition(r5, r0)
            if (r6 != r1) goto L4c
            return r1
        L4c:
            java.util.UUID r6 = (java.util.UUID) r6
            r4.currentTransitionId = r6
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor.startTransition$1(com.android.systemui.keyguard.shared.model.TransitionInfo, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object transitionKtfTo$1(com.android.systemui.keyguard.shared.model.KeyguardState r4, kotlin.coroutines.Continuation r5) {
        /*
            r3 = this;
            com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor r0 = r3.transitionInteractor
            kotlinx.coroutines.flow.ReadonlyStateFlow r0 = r0.transitionState
            kotlinx.coroutines.flow.MutableStateFlow r0 = r0.$$delegate_0
            kotlinx.coroutines.flow.StateFlowImpl r0 = (kotlinx.coroutines.flow.StateFlowImpl) r0
            java.lang.Object r0 = r0.getValue()
            com.android.systemui.keyguard.shared.model.TransitionStep r0 = (com.android.systemui.keyguard.shared.model.TransitionStep) r0
            com.android.systemui.keyguard.shared.model.KeyguardState r1 = r0.to
            if (r1 != r4) goto L1a
            com.android.systemui.keyguard.shared.model.TransitionState r1 = com.android.systemui.keyguard.shared.model.TransitionState.FINISHED
            com.android.systemui.keyguard.shared.model.TransitionState r2 = r0.transitionState
            if (r2 != r1) goto L1a
            r1 = 1
            goto L1b
        L1a:
            r1 = 0
        L1b:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            if (r1 == 0) goto L23
            r3.resetTransitionData$1()
            return r2
        L23:
            boolean r0 = com.android.systemui.keyguard.shared.model.TransitionStep.isTransitioning$default(r0, r4)
            if (r0 == 0) goto L33
            java.lang.Object r3 = r3.finishCurrentTransition$1(r5)
            kotlin.coroutines.intrinsics.CoroutineSingletons r4 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            if (r3 != r4) goto L32
            return r3
        L32:
            return r2
        L33:
            java.lang.Object r3 = r3.finishReversedTransitionTo$1(r4, r5)
            kotlin.coroutines.intrinsics.CoroutineSingletons r4 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            if (r3 != r4) goto L3c
            return r3
        L3c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor.transitionKtfTo$1(com.android.systemui.keyguard.shared.model.KeyguardState, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
