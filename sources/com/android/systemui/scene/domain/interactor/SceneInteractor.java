package com.android.systemui.scene.domain.interactor;

import com.android.compose.animation.scene.ContentKey;
import com.android.compose.animation.scene.ObservableTransitionState;
import com.android.compose.animation.scene.SceneKey;
import com.android.compose.animation.scene.TransitionKey;
import com.android.systemui.deviceentry.domain.interactor.DeviceUnlockedInteractor;
import com.android.systemui.deviceentry.shared.model.DeviceUnlockStatus;
import com.android.systemui.keyguard.domain.interactor.KeyguardEnabledInteractor;
import com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.scene.data.repository.SceneContainerRepository;
import com.android.systemui.scene.domain.resolver.HomeSceneFamilyResolver;
import com.android.systemui.scene.domain.resolver.SceneResolver;
import com.android.systemui.scene.shared.logger.SceneLogger;
import com.android.systemui.scene.shared.model.Scenes;
import dagger.Lazy;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SceneInteractor {
    public final StateFlow currentOverlays;
    public final StateFlow currentScene;
    public final Lazy deviceUnlockedInteractor;
    public final StateFlowImpl isRemoteUserInteractionOngoing;
    public final StateFlowImpl isSceneContainerUserInputOngoing;
    public final ReadonlyStateFlow isTransitionUserInputOngoing;
    public final ReadonlyStateFlow isVisible;
    public final Lazy keyguardEnabledInteractor;
    public final SceneLogger logger;
    public final Set onSceneAboutToChangeListener = new LinkedHashSet();
    public final SceneContainerRepository repository;
    public final Lazy sceneFamilyResolvers;
    public final ReadonlyStateFlow transitionState;
    public final ReadonlyStateFlow transitioningTo;

    public SceneInteractor(CoroutineScope coroutineScope, SceneContainerRepository sceneContainerRepository, SceneLogger sceneLogger, Lazy lazy, Lazy lazy2, Lazy lazy3) {
        this.repository = sceneContainerRepository;
        this.logger = sceneLogger;
        this.sceneFamilyResolvers = lazy;
        this.deviceUnlockedInteractor = lazy2;
        this.keyguardEnabledInteractor = lazy3;
        this.currentScene = sceneContainerRepository.currentScene;
        this.currentOverlays = sceneContainerRepository.currentOverlays;
        SceneInteractor$transitionState$1 sceneInteractor$transitionState$1 = new SceneInteractor$transitionState$1(this, null);
        ReadonlyStateFlow readonlyStateFlow = sceneContainerRepository.transitionState;
        final ReadonlyStateFlow stateIn = FlowKt.stateIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(readonlyStateFlow, sceneInteractor$transitionState$1, 0), coroutineScope, SharingStarted.Companion.Eagerly, ((StateFlowImpl) readonlyStateFlow.$$delegate_0).getValue());
        this.transitionState = stateIn;
        FlowKt.stateIn(new Flow() { // from class: com.android.systemui.scene.domain.interactor.SceneInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.scene.domain.interactor.SceneInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.scene.domain.interactor.SceneInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof com.android.systemui.scene.domain.interactor.SceneInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.scene.domain.interactor.SceneInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.scene.domain.interactor.SceneInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.scene.domain.interactor.SceneInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.scene.domain.interactor.SceneInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4d
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.compose.animation.scene.ObservableTransitionState r5 = (com.android.compose.animation.scene.ObservableTransitionState) r5
                        boolean r6 = r5 instanceof com.android.compose.animation.scene.ObservableTransitionState.Idle
                        if (r6 == 0) goto L3a
                        r5 = 0
                        goto L42
                    L3a:
                        boolean r6 = r5 instanceof com.android.compose.animation.scene.ObservableTransitionState.Transition
                        if (r6 == 0) goto L50
                        com.android.compose.animation.scene.ObservableTransitionState$Transition r5 = (com.android.compose.animation.scene.ObservableTransitionState.Transition) r5
                        com.android.compose.animation.scene.ContentKey r5 = r5.toContent
                    L42:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4d
                        return r1
                    L4d:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    L50:
                        kotlin.NoWhenBranchMatchedException r4 = new kotlin.NoWhenBranchMatchedException
                        r4.<init>()
                        throw r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.scene.domain.interactor.SceneInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                ReadonlyStateFlow.this.collect(new AnonymousClass2(flowCollector), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), null);
        this.isTransitionUserInputOngoing = FlowKt.stateIn(FlowKt.transformLatest(stateIn, new SceneInteractor$special$$inlined$flatMapLatest$1(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.FALSE);
        SceneInteractor$isVisible$1 sceneInteractor$isVisible$1 = new SceneInteractor$isVisible$1(this, null);
        ReadonlyStateFlow readonlyStateFlow2 = sceneContainerRepository.isVisible;
        StateFlowImpl stateFlowImpl = sceneContainerRepository.isRemoteUserInputOngoing;
        this.isVisible = FlowKt.stateIn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(readonlyStateFlow2, stateFlowImpl, sceneInteractor$isVisible$1), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.valueOf(((Boolean) ((StateFlowImpl) readonlyStateFlow2.$$delegate_0).getValue()).booleanValue() || ((Boolean) stateFlowImpl.getValue()).booleanValue()));
        this.isSceneContainerUserInputOngoing = sceneContainerRepository.isSceneContainerUserInputOngoing;
    }

    public static void changeScene$default(SceneInteractor sceneInteractor, SceneKey sceneKey, String str, TransitionKey transitionKey, Object obj, int i) {
        ReadonlyStateFlow readonlyStateFlow;
        SceneKey sceneKey2;
        if ((i & 4) != 0) {
            transitionKey = null;
        }
        if ((i & 8) != 0) {
            obj = null;
        }
        SceneKey sceneKey3 = (SceneKey) sceneInteractor.currentScene.getValue();
        SceneResolver sceneResolver = (SceneResolver) ((Map) sceneInteractor.sceneFamilyResolvers.get()).get(sceneKey);
        if (sceneResolver != null && (readonlyStateFlow = ((HomeSceneFamilyResolver) sceneResolver).resolvedScene) != null && (sceneKey2 = (SceneKey) ((StateFlowImpl) readonlyStateFlow.$$delegate_0).getValue()) != null) {
            sceneKey = sceneKey2;
        }
        if (sceneInteractor.validateSceneChange(sceneKey3, sceneKey, str)) {
            for (LockscreenSceneTransitionInteractor lockscreenSceneTransitionInteractor : sceneInteractor.onSceneAboutToChangeListener) {
                lockscreenSceneTransitionInteractor.getClass();
                if (sceneKey.equals(Scenes.Lockscreen) && obj != null) {
                    if (!(obj instanceof KeyguardState)) {
                        throw new IllegalArgumentException("Lockscreen sceneState needs to be a KeyguardState.");
                    }
                    StateFlowImpl stateFlowImpl = lockscreenSceneTransitionInteractor.repository.nextLockscreenTargetState;
                    stateFlowImpl.getClass();
                    stateFlowImpl.updateState(null, obj);
                }
            }
            sceneInteractor.logger.logSceneChanged(sceneKey3, sceneKey, str, false);
            sceneInteractor.repository.dataSource.changeScene(sceneKey, transitionKey);
        }
    }

    public final boolean validateSceneChange(SceneKey sceneKey, SceneKey sceneKey2, String str) {
        if (!this.repository.allContentKeys.contains(sceneKey2)) {
            return false;
        }
        ReadonlyStateFlow readonlyStateFlow = this.transitionState;
        Object value = ((StateFlowImpl) readonlyStateFlow.$$delegate_0).getValue();
        ObservableTransitionState.Transition transition = value instanceof ObservableTransitionState.Transition ? (ObservableTransitionState.Transition) value : null;
        ContentKey contentKey = transition != null ? transition.fromContent : null;
        SceneKey sceneKey3 = Scenes.Gone;
        boolean areEqual = Intrinsics.areEqual(contentKey, sceneKey3);
        if (!sceneKey2.equals(sceneKey3) || areEqual || ((DeviceUnlockStatus) ((DeviceUnlockedInteractor) this.deviceUnlockedInteractor.get()).deviceUnlockStatus.getValue()).isUnlocked || !((Boolean) ((KeyguardEnabledInteractor) this.keyguardEnabledInteractor.get()).isKeyguardEnabled.getValue()).booleanValue()) {
            return !Intrinsics.areEqual(sceneKey, sceneKey2);
        }
        throw new IllegalStateException(("Cannot change to the Gone scene while the device is locked and not currently transitioning from Gone. Current transition state is " + ((StateFlowImpl) readonlyStateFlow.$$delegate_0).getValue() + ". Logging reason for scene change was: " + str).toString());
    }
}
