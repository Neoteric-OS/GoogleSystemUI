package com.android.systemui.keyguard.data.repository;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import com.android.app.viewcapture.data.ViewNode;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.biometrics.AuthContainerView$$ExternalSyntheticOutline0;
import com.android.systemui.biometrics.AuthController;
import com.android.systemui.biometrics.data.repository.FacePropertyRepositoryImpl;
import com.android.systemui.doze.DozeMachine;
import com.android.systemui.doze.DozeTransitionListener;
import com.android.systemui.dreams.DreamOverlayCallbackController;
import com.android.systemui.keyguard.shared.model.BiometricUnlockMode;
import com.android.systemui.keyguard.shared.model.BiometricUnlockModel;
import com.android.systemui.keyguard.shared.model.CameraLaunchSourceModel;
import com.android.systemui.keyguard.shared.model.DismissAction;
import com.android.systemui.keyguard.shared.model.DozeStateModel;
import com.android.systemui.keyguard.shared.model.StatusBarState;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardRepositoryImpl {
    public final StateFlowImpl _animateBottomAreaDozingTransitions;
    public final StateFlowImpl _biometricUnlockState;
    public final StateFlowImpl _bottomAreaAlpha;
    public final StateFlowImpl _canIgnoreAuthAndReturnToGone;
    public final StateFlowImpl _clockShouldBeCentered;
    public final StateFlowImpl _dismissAction;
    public final StateFlowImpl _dozeTimeTick;
    public final StateFlowImpl _isActiveDreamLockscreenHosted;
    public final StateFlowImpl _isAodAvailable;
    public final StateFlowImpl _isDozing;
    public final StateFlowImpl _isKeyguardEnabled;
    public final StateFlowImpl _isQuickSettingsVisible;
    public final StateFlowImpl _keyguardAlpha;
    public final SharedFlowImpl _keyguardDone;
    public final StateFlowImpl _lastDozeTapToWakePosition;
    public final Flow _preSceneLinearDozeAmount;
    public final StateFlowImpl ambientIndicationVisible;
    public final ReadonlyStateFlow animateBottomAreaDozingTransitions;
    public final AuthController authController;
    public final ReadonlyStateFlow biometricUnlockState;
    public final ReadonlyStateFlow bottomAreaAlpha;
    public final ReadonlyStateFlow canIgnoreAuthAndReturnToGone;
    public final ReadonlyStateFlow clockShouldBeCentered;
    public final ReadonlyStateFlow dozeTimeTick;
    public final DozeTransitionListener dozeTransitionListener;
    public final Flow dozeTransitionModel;
    public final DreamOverlayCallbackController dreamOverlayCallbackController;
    public final StateFlow faceSensorLocation;
    public final Flow fingerprintSensorLocation;
    public final ReadonlyStateFlow isActiveDreamLockscreenHosted;
    public final ReadonlyStateFlow isAodAvailable;
    public final ReadonlyStateFlow isDozing;
    public final StateFlowImpl isDreaming;
    public final Flow isDreamingWithOverlay;
    public final Flow isEncryptedOrLockdown;
    public final StateFlowImpl isKeyguardDismissible;
    public final ReadonlyStateFlow isKeyguardEnabled;
    public final StateFlowImpl isKeyguardGoingAway;
    public final StateFlowImpl isKeyguardOccluded;
    public final StateFlowImpl isKeyguardShowing;
    public final ReadonlyStateFlow isQuickSettingsVisible;
    public final ReadonlyStateFlow keyguardAlpha;
    public final KeyguardStateController keyguardStateController;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    public final ReadonlyStateFlow lastDozeTapToWakePosition;
    public final StateFlowImpl lastRootViewTapPosition;
    public final StateFlowImpl onCameraLaunchDetected;
    public final StateFlowImpl panelAlpha;
    public final StateFlowImpl showDismissibleKeyguard;
    public final ReadonlyStateFlow statusBarState;
    public final SystemClock systemClock;
    public final StateFlowImpl topClippingBounds;
    public final UserTracker userTracker;
    public final ReadonlyStateFlow dismissAction = new ReadonlyStateFlow(StateFlowKt.MutableStateFlow(DismissAction.None.INSTANCE));
    public final ReadonlySharedFlow keyguardDone = new ReadonlySharedFlow(SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7));
    public final SharedFlowImpl keyguardDoneAnimationsFinished = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl$1$1, reason: invalid class name and collision with other inner class name */
        public final class C00811 implements FlowCollector {
            public static final C00811 INSTANCE = new C00811();

            @Override // kotlinx.coroutines.flow.FlowCollector
            public final /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
                ((Boolean) obj).getClass();
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return KeyguardRepositoryImpl.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            return CoroutineSingletons.COROUTINE_SUSPENDED;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                throw AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0.m(obj);
            }
            ResultKt.throwOnFailure(obj);
            StateFlowImpl stateFlowImpl = KeyguardRepositoryImpl.this.isKeyguardShowing;
            C00811 c00811 = C00811.INSTANCE;
            this.label = 1;
            stateFlowImpl.collect(c00811, this);
            return coroutineSingletons;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v11, types: [com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl$callback$1, java.lang.Object] */
    public KeyguardRepositoryImpl(StatusBarStateController statusBarStateController, KeyguardStateController keyguardStateController, KeyguardUpdateMonitor keyguardUpdateMonitor, DozeTransitionListener dozeTransitionListener, AuthController authController, DreamOverlayCallbackController dreamOverlayCallbackController, CoroutineDispatcher coroutineDispatcher, CoroutineScope coroutineScope, SystemClock systemClock, FacePropertyRepositoryImpl facePropertyRepositoryImpl, UserTracker userTracker, LockPatternUtils lockPatternUtils) {
        this.keyguardStateController = keyguardStateController;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.dozeTransitionListener = dozeTransitionListener;
        this.authController = authController;
        this.dreamOverlayCallbackController = dreamOverlayCallbackController;
        this.systemClock = systemClock;
        this.userTracker = userTracker;
        Boolean bool = Boolean.FALSE;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(bool);
        this._animateBottomAreaDozingTransitions = MutableStateFlow;
        this.animateBottomAreaDozingTransitions = new ReadonlyStateFlow(MutableStateFlow);
        Float valueOf = Float.valueOf(1.0f);
        this.bottomAreaAlpha = new ReadonlyStateFlow(StateFlowKt.MutableStateFlow(valueOf));
        StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(valueOf);
        this._keyguardAlpha = MutableStateFlow2;
        this.keyguardAlpha = new ReadonlyStateFlow(MutableStateFlow2);
        this.onCameraLaunchDetected = StateFlowKt.MutableStateFlow(new CameraLaunchSourceModel(null, 3));
        this.panelAlpha = StateFlowKt.MutableStateFlow(valueOf);
        StateFlowImpl MutableStateFlow3 = StateFlowKt.MutableStateFlow(Boolean.TRUE);
        this._clockShouldBeCentered = MutableStateFlow3;
        this.clockShouldBeCentered = new ReadonlyStateFlow(MutableStateFlow3);
        this.topClippingBounds = StateFlowKt.MutableStateFlow(null);
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) keyguardStateController;
        this.isKeyguardShowing = StateFlowKt.MutableStateFlow(Boolean.valueOf(keyguardStateControllerImpl.mShowing));
        StateFlowImpl MutableStateFlow4 = StateFlowKt.MutableStateFlow(bool);
        this._isAodAvailable = MutableStateFlow4;
        this.isAodAvailable = new ReadonlyStateFlow(MutableStateFlow4);
        this.isKeyguardOccluded = StateFlowKt.MutableStateFlow(Boolean.valueOf(keyguardStateControllerImpl.mOccluded));
        this.isKeyguardDismissible = StateFlowKt.MutableStateFlow(Boolean.valueOf(keyguardStateController.isUnlocked()));
        KeyguardStateControllerImpl keyguardStateControllerImpl2 = (KeyguardStateControllerImpl) keyguardStateController;
        this.isKeyguardGoingAway = StateFlowKt.MutableStateFlow(Boolean.valueOf(keyguardStateControllerImpl2.mKeyguardGoingAway));
        StateFlowImpl MutableStateFlow5 = StateFlowKt.MutableStateFlow(Boolean.valueOf(!lockPatternUtils.isLockScreenDisabled(((UserTrackerImpl) userTracker).getUserId())));
        this._isKeyguardEnabled = MutableStateFlow5;
        this.isKeyguardEnabled = new ReadonlyStateFlow(MutableStateFlow5);
        StateFlowImpl MutableStateFlow6 = StateFlowKt.MutableStateFlow(bool);
        this._canIgnoreAuthAndReturnToGone = MutableStateFlow6;
        this.canIgnoreAuthAndReturnToGone = new ReadonlyStateFlow(MutableStateFlow6);
        StateFlowImpl MutableStateFlow7 = StateFlowKt.MutableStateFlow(Boolean.valueOf(statusBarStateController.isDozing()));
        this._isDozing = MutableStateFlow7;
        this.isDozing = new ReadonlyStateFlow(MutableStateFlow7);
        StateFlowImpl MutableStateFlow8 = StateFlowKt.MutableStateFlow(0L);
        this._dozeTimeTick = MutableStateFlow8;
        this.dozeTimeTick = new ReadonlyStateFlow(MutableStateFlow8);
        this.showDismissibleKeyguard = StateFlowKt.MutableStateFlow(0L);
        StateFlowImpl MutableStateFlow9 = StateFlowKt.MutableStateFlow(null);
        this._lastDozeTapToWakePosition = MutableStateFlow9;
        this.lastDozeTapToWakePosition = new ReadonlyStateFlow(MutableStateFlow9);
        this.lastRootViewTapPosition = StateFlowKt.MutableStateFlow(null);
        this.ambientIndicationVisible = StateFlowKt.MutableStateFlow(bool);
        this.isDreamingWithOverlay = FlowKt.distinctUntilChanged(FlowConflatedKt.conflatedCallbackFlow(new KeyguardRepositoryImpl$isDreamingWithOverlay$1(this, null)));
        this.isDreaming = StateFlowKt.MutableStateFlow(bool);
        this._preSceneLinearDozeAmount = FlowConflatedKt.conflatedCallbackFlow(new KeyguardRepositoryImpl$_preSceneLinearDozeAmount$1(statusBarStateController, null));
        this.dozeTransitionModel = FlowConflatedKt.conflatedCallbackFlow(new KeyguardRepositoryImpl$dozeTransitionModel$1(this, null));
        final Flow conflatedCallbackFlow = FlowConflatedKt.conflatedCallbackFlow(new KeyguardRepositoryImpl$isEncryptedOrLockdown$1(this, null));
        this.isEncryptedOrLockdown = FlowKt.flowOn(FlowKt.mapLatest(new KeyguardRepositoryImpl$isEncryptedOrLockdown$4(this, null), new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new KeyguardRepositoryImpl$isEncryptedOrLockdown$3(this, null), new Flow() { // from class: com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl$special$$inlined$filter$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl$special$$inlined$filter$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ KeyguardRepositoryImpl this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl$special$$inlined$filter$1$2$1, reason: invalid class name */
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
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, KeyguardRepositoryImpl keyguardRepositoryImpl) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = keyguardRepositoryImpl;
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
                        boolean r0 = r6 instanceof com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl$special$$inlined$filter$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl$special$$inlined$filter$1$2$1 r0 = (com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl$special$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl$special$$inlined$filter$1$2$1 r0 = new com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl$special$$inlined$filter$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L50
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        r6 = r5
                        java.lang.Number r6 = (java.lang.Number) r6
                        int r6 = r6.intValue()
                        com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl r2 = r4.this$0
                        com.android.systemui.settings.UserTracker r2 = r2.userTracker
                        com.android.systemui.settings.UserTrackerImpl r2 = (com.android.systemui.settings.UserTrackerImpl) r2
                        int r2 = r2.getUserId()
                        if (r6 != r2) goto L50
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L50
                        return r1
                    L50:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl$special$$inlined$filter$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        })), coroutineDispatcher);
        this.statusBarState = FlowKt.stateIn(FlowConflatedKt.conflatedCallbackFlow(new KeyguardRepositoryImpl$statusBarState$1(statusBarStateController, this, null)), coroutineScope, SharingStarted.Companion.Eagerly, statusBarStateIntToObject(statusBarStateController.getState()));
        StateFlowImpl MutableStateFlow10 = StateFlowKt.MutableStateFlow(new BiometricUnlockModel(BiometricUnlockMode.NONE, null));
        this._biometricUnlockState = MutableStateFlow10;
        this.biometricUnlockState = new ReadonlyStateFlow(MutableStateFlow10);
        this.fingerprintSensorLocation = FlowConflatedKt.conflatedCallbackFlow(new KeyguardRepositoryImpl$fingerprintSensorLocation$1(this, null));
        this.faceSensorLocation = facePropertyRepositoryImpl.sensorLocation;
        StateFlowImpl MutableStateFlow11 = StateFlowKt.MutableStateFlow(bool);
        this._isQuickSettingsVisible = MutableStateFlow11;
        this.isQuickSettingsVisible = new ReadonlyStateFlow(MutableStateFlow11);
        this.isActiveDreamLockscreenHosted = new ReadonlyStateFlow(StateFlowKt.MutableStateFlow(bool));
        final ?? r2 = new KeyguardStateController.Callback() { // from class: com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl$callback$1
            @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
            public final void onKeyguardGoingAwayChanged() {
                KeyguardRepositoryImpl keyguardRepositoryImpl = KeyguardRepositoryImpl.this;
                AuthContainerView$$ExternalSyntheticOutline0.m(((KeyguardStateControllerImpl) keyguardRepositoryImpl.keyguardStateController).mKeyguardGoingAway, keyguardRepositoryImpl.isKeyguardGoingAway, null);
            }

            @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
            public final void onKeyguardShowingChanged() {
                KeyguardRepositoryImpl keyguardRepositoryImpl = KeyguardRepositoryImpl.this;
                StateFlowImpl stateFlowImpl = keyguardRepositoryImpl.isKeyguardShowing;
                KeyguardStateController keyguardStateController2 = keyguardRepositoryImpl.keyguardStateController;
                AuthContainerView$$ExternalSyntheticOutline0.m(((KeyguardStateControllerImpl) keyguardStateController2).mShowing, stateFlowImpl, null);
                Boolean valueOf2 = Boolean.valueOf(((KeyguardStateControllerImpl) keyguardStateController2).mOccluded);
                StateFlowImpl stateFlowImpl2 = keyguardRepositoryImpl.isKeyguardOccluded;
                stateFlowImpl2.getClass();
                stateFlowImpl2.updateState(null, valueOf2);
                Boolean valueOf3 = Boolean.valueOf(keyguardStateController2.isUnlocked());
                StateFlowImpl stateFlowImpl3 = keyguardRepositoryImpl.isKeyguardDismissible;
                stateFlowImpl3.getClass();
                stateFlowImpl3.updateState(null, valueOf3);
            }

            @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
            public final void onUnlockedChanged() {
                KeyguardRepositoryImpl keyguardRepositoryImpl = KeyguardRepositoryImpl.this;
                StateFlowImpl stateFlowImpl = keyguardRepositoryImpl.isKeyguardDismissible;
                Boolean valueOf2 = Boolean.valueOf(keyguardRepositoryImpl.keyguardStateController.isUnlocked());
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, valueOf2);
            }
        };
        keyguardStateControllerImpl2.addCallback(r2);
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass1(null), 3).invokeOnCompletion(new Function1() { // from class: com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ((KeyguardStateControllerImpl) KeyguardRepositoryImpl.this.keyguardStateController).removeCallback(r2);
                return Unit.INSTANCE;
            }
        });
    }

    public static final DozeStateModel access$dozeMachineStateToModel(KeyguardRepositoryImpl keyguardRepositoryImpl, DozeMachine.State state) {
        keyguardRepositoryImpl.getClass();
        switch (state.ordinal()) {
            case 0:
                return DozeStateModel.UNINITIALIZED;
            case 1:
                return DozeStateModel.INITIALIZED;
            case 2:
                return DozeStateModel.DOZE;
            case 3:
                return DozeStateModel.DOZE_SUSPEND_TRIGGERS;
            case 4:
                return DozeStateModel.DOZE_AOD;
            case 5:
                return DozeStateModel.DOZE_REQUEST_PULSE;
            case 6:
                return DozeStateModel.DOZE_PULSING;
            case 7:
                return DozeStateModel.DOZE_PULSING_BRIGHT;
            case 8:
                return DozeStateModel.DOZE_PULSE_DONE;
            case 9:
                return DozeStateModel.FINISH;
            case 10:
                return DozeStateModel.DOZE_AOD_PAUSED;
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                return DozeStateModel.DOZE_AOD_PAUSING;
            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                return DozeStateModel.DOZE_AOD_DOCKED;
            default:
                throw new IllegalArgumentException("Invalid DozeMachine.State: state");
        }
    }

    public static StatusBarState statusBarStateIntToObject(int i) {
        if (i == 0) {
            return StatusBarState.SHADE;
        }
        if (i == 1) {
            return StatusBarState.KEYGUARD;
        }
        if (i == 2) {
            return StatusBarState.SHADE_LOCKED;
        }
        throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Invalid StatusBarState value: "));
    }
}
