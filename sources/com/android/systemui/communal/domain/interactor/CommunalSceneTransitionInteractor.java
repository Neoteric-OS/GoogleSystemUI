package com.android.systemui.communal.domain.interactor;

import com.android.compose.animation.scene.ObservableTransitionState;
import com.android.systemui.CoreStartable;
import com.android.systemui.communal.data.repository.CommunalSceneTransitionRepository;
import com.android.systemui.keyguard.domain.interactor.InternalKeyguardTransitionInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import java.util.UUID;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalSceneTransitionInteractor implements CoreStartable {
    public final CoroutineScope applicationScope;
    public UUID currentTransitionId;
    public final InternalKeyguardTransitionInteractor internalTransitionInteractor;
    public final ReadonlyStateFlow nextKeyguardState;
    public final FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 nextKeyguardStateInternal;
    public StandaloneCoroutine progressJob;
    public final CommunalSceneTransitionRepository repository;
    public final CommunalSceneInteractor sceneInteractor;
    public final CommunalSettingsInteractor settingsInteractor;
    public final KeyguardTransitionInteractor transitionInteractor;

    public CommunalSceneTransitionInteractor(KeyguardTransitionInteractor keyguardTransitionInteractor, InternalKeyguardTransitionInteractor internalKeyguardTransitionInteractor, CommunalSettingsInteractor communalSettingsInteractor, CoroutineScope coroutineScope, CommunalSceneInteractor communalSceneInteractor, CommunalSceneTransitionRepository communalSceneTransitionRepository, KeyguardInteractor keyguardInteractor) {
        this.transitionInteractor = keyguardTransitionInteractor;
        this.internalTransitionInteractor = internalKeyguardTransitionInteractor;
        this.settingsInteractor = communalSettingsInteractor;
        this.applicationScope = coroutineScope;
        this.sceneInteractor = communalSceneInteractor;
        this.repository = communalSceneTransitionRepository;
        CommunalSceneTransitionInteractor$nextKeyguardStateInternal$1 communalSceneTransitionInteractor$nextKeyguardStateInternal$1 = new CommunalSceneTransitionInteractor$nextKeyguardStateInternal$1(5, null);
        this.nextKeyguardState = FlowKt.stateIn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(communalSceneTransitionRepository.nextLockscreenTargetState, new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new CommunalSceneTransitionInteractor$nextKeyguardState$1(2, null), new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(FlowKt.combine(keyguardInteractor.isDreamingWithOverlay, keyguardInteractor.isKeyguardOccluded, keyguardInteractor.isKeyguardGoingAway, keyguardInteractor.isKeyguardShowing, communalSceneTransitionInteractor$nextKeyguardStateInternal$1))), new CommunalSceneTransitionInteractor$nextKeyguardState$2(3, null)), coroutineScope, SharingStarted.Companion.Eagerly, KeyguardState.LOCKSCREEN);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$handleIdle(com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor r6, com.android.compose.animation.scene.ObservableTransitionState r7, com.android.compose.animation.scene.ObservableTransitionState.Idle r8, kotlin.coroutines.Continuation r9) {
        /*
            r6.getClass()
            boolean r0 = r9 instanceof com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor$handleIdle$1
            if (r0 == 0) goto L16
            r0 = r9
            com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor$handleIdle$1 r0 = (com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor$handleIdle$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor$handleIdle$1 r0 = new com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor$handleIdle$1
            r0.<init>(r6, r9)
        L1b:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L3f
            if (r2 == r5) goto L3b
            if (r2 != r4) goto L33
            java.lang.Object r6 = r0.L$0
            com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor r6 = (com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor) r6
            kotlin.ResultKt.throwOnFailure(r9)
            goto L95
        L33:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3b:
            kotlin.ResultKt.throwOnFailure(r9)
            goto L5f
        L3f:
            kotlin.ResultKt.throwOnFailure(r9)
            boolean r9 = r7 instanceof com.android.compose.animation.scene.ObservableTransitionState.Transition
            if (r9 == 0) goto L61
            java.util.UUID r9 = r6.currentTransitionId
            if (r9 == 0) goto L61
            com.android.compose.animation.scene.SceneKey r9 = r8.currentScene
            com.android.compose.animation.scene.ObservableTransitionState$Transition r7 = (com.android.compose.animation.scene.ObservableTransitionState.Transition) r7
            com.android.compose.animation.scene.ContentKey r7 = r7.toContent
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r9, r7)
            if (r7 == 0) goto L61
            r0.label = r5
            java.lang.Object r6 = r6.finishCurrentTransition(r0)
            if (r6 != r1) goto L5f
            goto L9e
        L5f:
            r1 = r3
            goto L9e
        L61:
            com.android.compose.animation.scene.SceneKey r7 = r8.currentScene
            com.android.compose.animation.scene.SceneKey r8 = com.android.systemui.communal.shared.model.CommunalScenes.Communal
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r8)
            if (r7 == 0) goto L6e
            com.android.systemui.keyguard.shared.model.KeyguardState r7 = com.android.systemui.keyguard.shared.model.KeyguardState.GLANCEABLE_HUB
            goto L8a
        L6e:
            com.android.systemui.keyguard.domain.interactor.InternalKeyguardTransitionInteractor r7 = r6.internalTransitionInteractor
            kotlinx.coroutines.flow.ReadonlyStateFlow r7 = r7.currentTransitionInfoInternal
            java.lang.Object r7 = r7.getValue()
            com.android.systemui.keyguard.shared.model.TransitionInfo r7 = (com.android.systemui.keyguard.shared.model.TransitionInfo) r7
            com.android.systemui.keyguard.shared.model.KeyguardState r7 = r7.to
            com.android.systemui.keyguard.shared.model.KeyguardState r8 = com.android.systemui.keyguard.shared.model.KeyguardState.GLANCEABLE_HUB
            if (r7 != r8) goto L5f
            kotlinx.coroutines.flow.ReadonlyStateFlow r7 = r6.nextKeyguardState
            kotlinx.coroutines.flow.MutableStateFlow r7 = r7.$$delegate_0
            kotlinx.coroutines.flow.StateFlowImpl r7 = (kotlinx.coroutines.flow.StateFlowImpl) r7
            java.lang.Object r7 = r7.getValue()
            com.android.systemui.keyguard.shared.model.KeyguardState r7 = (com.android.systemui.keyguard.shared.model.KeyguardState) r7
        L8a:
            r0.L$0 = r6
            r0.label = r4
            java.lang.Object r7 = r6.transitionKtfTo(r7, r0)
            if (r7 != r1) goto L95
            goto L9e
        L95:
            com.android.systemui.communal.data.repository.CommunalSceneTransitionRepository r6 = r6.repository
            kotlinx.coroutines.flow.StateFlowImpl r6 = r6.nextLockscreenTargetState
            r7 = 0
            r6.setValue(r7)
            goto L5f
        L9e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor.access$handleIdle(com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor, com.android.compose.animation.scene.ObservableTransitionState, com.android.compose.animation.scene.ObservableTransitionState$Idle, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$handleTransition(com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor r11, com.android.compose.animation.scene.ObservableTransitionState r12, com.android.compose.animation.scene.ObservableTransitionState.Transition r13, kotlin.coroutines.Continuation r14) {
        /*
            Method dump skipped, instructions count: 345
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor.access$handleTransition(com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor, com.android.compose.animation.scene.ObservableTransitionState, com.android.compose.animation.scene.ObservableTransitionState$Transition, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void collectProgress(ObservableTransitionState.Transition transition) {
        StandaloneCoroutine standaloneCoroutine = this.progressJob;
        if (standaloneCoroutine != null) {
            standaloneCoroutine.cancel(null);
        }
        this.progressJob = BuildersKt.launch$default(this.applicationScope, null, null, new CommunalSceneTransitionInteractor$collectProgress$1(transition, this, null), 3);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object finishCurrentTransition(kotlin.coroutines.jvm.internal.ContinuationImpl r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor$finishCurrentTransition$1
            if (r0 == 0) goto L13
            r0 = r7
            com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor$finishCurrentTransition$1 r0 = (com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor$finishCurrentTransition$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor$finishCurrentTransition$1 r0 = new com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor$finishCurrentTransition$1
            r0.<init>(r6, r7)
        L18:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            r4 = 1
            if (r2 == 0) goto L35
            if (r2 != r4) goto L2d
            java.lang.Object r6 = r0.L$0
            com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor r6 = (com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L4e
        L2d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L35:
            kotlin.ResultKt.throwOnFailure(r7)
            java.util.UUID r7 = r6.currentTransitionId
            if (r7 != 0) goto L3d
            return r3
        L3d:
            com.android.systemui.keyguard.shared.model.TransitionState r2 = com.android.systemui.keyguard.shared.model.TransitionState.FINISHED
            r0.L$0 = r6
            r0.label = r4
            com.android.systemui.keyguard.domain.interactor.InternalKeyguardTransitionInteractor r4 = r6.internalTransitionInteractor
            r5 = 1065353216(0x3f800000, float:1.0)
            java.lang.Object r7 = r4.updateTransition(r7, r5, r2, r0)
            if (r7 != r1) goto L4e
            return r1
        L4e:
            r6.resetTransitionData()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor.finishCurrentTransition(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0089 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object finishReversedTransitionTo(com.android.systemui.keyguard.shared.model.KeyguardState r12, kotlin.coroutines.jvm.internal.ContinuationImpl r13) {
        /*
            r11 = this;
            boolean r0 = r13 instanceof com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor$finishReversedTransitionTo$1
            if (r0 == 0) goto L13
            r0 = r13
            com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor$finishReversedTransitionTo$1 r0 = (com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor$finishReversedTransitionTo$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor$finishReversedTransitionTo$1 r0 = new com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor$finishReversedTransitionTo$1
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
            com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor r11 = (com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor) r11
            kotlin.ResultKt.throwOnFailure(r13)
            goto L8b
        L2e:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L36:
            java.lang.Object r11 = r0.L$1
            com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor r11 = (com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor) r11
            java.lang.Object r12 = r0.L$0
            com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor r12 = (com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor) r12
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
            java.lang.String r6 = "CommunalSceneTransitionInteractor"
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
            r11.resetTransitionData()
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor.finishReversedTransitionTo(com.android.systemui.keyguard.shared.model.KeyguardState, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final void resetTransitionData() {
        StandaloneCoroutine standaloneCoroutine = this.progressJob;
        if (standaloneCoroutine != null) {
            standaloneCoroutine.cancel(null);
        }
        this.progressJob = null;
        this.currentTransitionId = null;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        if (this.settingsInteractor.isCommunalFlagEnabled()) {
            this.sceneInteractor.onSceneAboutToChangeListener.add(this);
            BuildersKt.launch$default(this.applicationScope, null, null, new CommunalSceneTransitionInteractor$listenForSceneTransitionProgress$1(this, null), 3);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object startTransition(com.android.systemui.keyguard.shared.model.TransitionInfo r5, kotlin.coroutines.jvm.internal.ContinuationImpl r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor$startTransition$1
            if (r0 == 0) goto L13
            r0 = r6
            com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor$startTransition$1 r0 = (com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor$startTransition$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor$startTransition$1 r0 = new com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor$startTransition$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            java.lang.Object r4 = r0.L$0
            com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor r4 = (com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor) r4
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
            r4.resetTransitionData()
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
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor.startTransition(com.android.systemui.keyguard.shared.model.TransitionInfo, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object transitionKtfTo(com.android.systemui.keyguard.shared.model.KeyguardState r4, kotlin.coroutines.jvm.internal.ContinuationImpl r5) {
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
            r3.resetTransitionData()
            return r2
        L23:
            boolean r0 = com.android.systemui.keyguard.shared.model.TransitionStep.isTransitioning$default(r0, r4)
            if (r0 == 0) goto L33
            java.lang.Object r3 = r3.finishCurrentTransition(r5)
            kotlin.coroutines.intrinsics.CoroutineSingletons r4 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            if (r3 != r4) goto L32
            return r3
        L32:
            return r2
        L33:
            java.lang.Object r3 = r3.finishReversedTransitionTo(r4, r5)
            kotlin.coroutines.intrinsics.CoroutineSingletons r4 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            if (r3 != r4) goto L3c
            return r3
        L3c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor.transitionKtfTo(com.android.systemui.keyguard.shared.model.KeyguardState, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
