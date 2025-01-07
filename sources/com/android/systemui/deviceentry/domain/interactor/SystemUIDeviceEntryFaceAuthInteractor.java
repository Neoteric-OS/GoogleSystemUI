package com.android.systemui.deviceentry.domain.interactor;

import android.app.trust.TrustManager;
import android.content.Context;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.biometrics.data.repository.FacePropertyRepositoryImpl;
import com.android.systemui.biometrics.data.repository.FaceSensorInfo;
import com.android.systemui.biometrics.shared.model.SensorStrength;
import com.android.systemui.bouncer.domain.interactor.AlternateBouncerInteractor;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor;
import com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl;
import com.android.systemui.deviceentry.data.repository.FaceWakeUpTriggersConfig;
import com.android.systemui.deviceentry.shared.FaceAuthUiEvent;
import com.android.systemui.deviceentry.shared.model.ErrorFaceAuthenticationStatus;
import com.android.systemui.keyguard.data.repository.BiometricSettingsRepositoryImpl;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.shared.model.Edge;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.log.FaceAuthenticationLogger;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.user.data.repository.UserRepositoryImpl;
import com.android.wm.shell.R;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.flow.internal.ChannelLimitedFlowMerge;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SystemUIDeviceEntryFaceAuthInteractor implements DeviceEntryFaceAuthInteractor {
    public final AlternateBouncerInteractor alternateBouncerInteractor;
    public final CoroutineScope applicationScope;
    public final ChannelLimitedFlowMerge authenticationStatus;
    public final BiometricSettingsRepositoryImpl biometricSettingsRepository;
    public final Context context;
    public final FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 detectionStatus;
    public final DeviceEntryFingerprintAuthInteractor deviceEntryFingerprintAuthInteractor;
    public final FaceAuthenticationLogger faceAuthenticationLogger;
    public final StateFlowImpl faceAuthenticationStatusOverride;
    public final FacePropertyRepositoryImpl facePropertyRepository;
    public final FaceWakeUpTriggersConfig faceWakeUpTriggersConfig;
    public final StateFlowImpl isAuthenticated;
    public final Flow isBypassEnabled;
    public final StateFlowImpl isLockedOut;
    public final KeyguardTransitionInteractor keyguardTransitionInteractor;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    public final CoroutineDispatcher mainDispatcher;
    public final PowerInteractor powerInteractor;
    public final Lazy primaryBouncerInteractor;
    public final DeviceEntryFaceAuthRepositoryImpl repository;
    public final TrustManager trustManager;
    public final UserRepositoryImpl userRepository;
    public final List listeners = new ArrayList();
    public final kotlin.Lazy isBouncerVisible$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$isBouncerVisible$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return ((PrimaryBouncerInteractor) SystemUIDeviceEntryFaceAuthInteractor.this.primaryBouncerInteractor.get()).isShowing;
        }
    });

    public SystemUIDeviceEntryFaceAuthInteractor(Context context, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, DeviceEntryFaceAuthRepositoryImpl deviceEntryFaceAuthRepositoryImpl, Lazy lazy, AlternateBouncerInteractor alternateBouncerInteractor, KeyguardTransitionInteractor keyguardTransitionInteractor, FaceAuthenticationLogger faceAuthenticationLogger, KeyguardUpdateMonitor keyguardUpdateMonitor, DeviceEntryFingerprintAuthInteractor deviceEntryFingerprintAuthInteractor, UserRepositoryImpl userRepositoryImpl, FacePropertyRepositoryImpl facePropertyRepositoryImpl, FaceWakeUpTriggersConfig faceWakeUpTriggersConfig, PowerInteractor powerInteractor, BiometricSettingsRepositoryImpl biometricSettingsRepositoryImpl, TrustManager trustManager, Lazy lazy2, DeviceEntryFaceAuthStatusInteractor deviceEntryFaceAuthStatusInteractor) {
        this.context = context;
        this.applicationScope = coroutineScope;
        this.mainDispatcher = coroutineDispatcher;
        this.repository = deviceEntryFaceAuthRepositoryImpl;
        this.primaryBouncerInteractor = lazy;
        this.alternateBouncerInteractor = alternateBouncerInteractor;
        this.keyguardTransitionInteractor = keyguardTransitionInteractor;
        this.faceAuthenticationLogger = faceAuthenticationLogger;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.deviceEntryFingerprintAuthInteractor = deviceEntryFingerprintAuthInteractor;
        this.userRepository = userRepositoryImpl;
        this.facePropertyRepository = facePropertyRepositoryImpl;
        this.faceWakeUpTriggersConfig = faceWakeUpTriggersConfig;
        this.powerInteractor = powerInteractor;
        this.biometricSettingsRepository = biometricSettingsRepositoryImpl;
        this.trustManager = trustManager;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(null);
        this.faceAuthenticationStatusOverride = MutableStateFlow;
        this.authenticationStatus = FlowKt.merge(new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(MutableStateFlow), new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(deviceEntryFaceAuthStatusInteractor.authenticationStatus));
        this.detectionStatus = new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(deviceEntryFaceAuthRepositoryImpl._detectionStatus);
        this.isLockedOut = deviceEntryFaceAuthRepositoryImpl.isLockedOut;
        this.isAuthenticated = deviceEntryFaceAuthRepositoryImpl.isAuthenticated;
        this.isBypassEnabled = deviceEntryFaceAuthRepositoryImpl.isBypassEnabled;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$resetLockedOutState(com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor r4, int r5, kotlin.coroutines.jvm.internal.ContinuationImpl r6) {
        /*
            r4.getClass()
            boolean r0 = r6 instanceof com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$resetLockedOutState$1
            if (r0 == 0) goto L16
            r0 = r6
            com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$resetLockedOutState$1 r0 = (com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$resetLockedOutState$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$resetLockedOutState$1 r0 = new com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$resetLockedOutState$1
            r0.<init>(r4, r6)
        L1b:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r4 = r0.L$0
            com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor r4 = (com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor) r4
            kotlin.ResultKt.throwOnFailure(r6)
            goto L78
        L2e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L36:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.L$0 = r4
            r0.label = r3
            com.android.systemui.biometrics.data.repository.FacePropertyRepositoryImpl r6 = r4.facePropertyRepository
            kotlinx.coroutines.flow.ReadonlyStateFlow r0 = r6.sensorInfo
            kotlinx.coroutines.flow.MutableStateFlow r2 = r0.$$delegate_0
            kotlinx.coroutines.flow.StateFlowImpl r2 = (kotlinx.coroutines.flow.StateFlowImpl) r2
            java.lang.Object r2 = r2.getValue()
            if (r2 == 0) goto L72
            android.hardware.face.FaceManager r6 = r6.faceManager
            if (r6 != 0) goto L50
            goto L72
        L50:
            kotlinx.coroutines.flow.MutableStateFlow r0 = r0.$$delegate_0
            kotlinx.coroutines.flow.StateFlowImpl r0 = (kotlinx.coroutines.flow.StateFlowImpl) r0
            java.lang.Object r0 = r0.getValue()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            com.android.systemui.biometrics.data.repository.FaceSensorInfo r0 = (com.android.systemui.biometrics.data.repository.FaceSensorInfo) r0
            int r0 = r0.id
            int r5 = r6.getLockoutModeForUser(r0, r5)
            if (r5 == r3) goto L6e
            r6 = 2
            if (r5 == r6) goto L6b
            com.android.systemui.biometrics.shared.model.LockoutMode r5 = com.android.systemui.biometrics.shared.model.LockoutMode.NONE
            goto L70
        L6b:
            com.android.systemui.biometrics.shared.model.LockoutMode r5 = com.android.systemui.biometrics.shared.model.LockoutMode.PERMANENT
            goto L70
        L6e:
            com.android.systemui.biometrics.shared.model.LockoutMode r5 = com.android.systemui.biometrics.shared.model.LockoutMode.TIMED
        L70:
            r6 = r5
            goto L75
        L72:
            com.android.systemui.biometrics.shared.model.LockoutMode r5 = com.android.systemui.biometrics.shared.model.LockoutMode.NONE
            goto L70
        L75:
            if (r6 != r1) goto L78
            goto L95
        L78:
            com.android.systemui.biometrics.shared.model.LockoutMode r6 = (com.android.systemui.biometrics.shared.model.LockoutMode) r6
            com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl r4 = r4.repository
            com.android.systemui.biometrics.shared.model.LockoutMode r5 = com.android.systemui.biometrics.shared.model.LockoutMode.PERMANENT
            if (r6 == r5) goto L86
            com.android.systemui.biometrics.shared.model.LockoutMode r5 = com.android.systemui.biometrics.shared.model.LockoutMode.TIMED
            if (r6 != r5) goto L85
            goto L86
        L85:
            r3 = 0
        L86:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r3)
            kotlinx.coroutines.flow.StateFlowImpl r4 = r4._isLockedOut
            r4.getClass()
            r6 = 0
            r4.updateState(r6, r5)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
        L95:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor.access$resetLockedOutState(com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor, int, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final boolean canFaceAuthRun() {
        return ((Boolean) this.repository.canRunFaceAuth.getValue()).booleanValue();
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final Flow getAuthenticationStatus() {
        return this.authenticationStatus;
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final StateFlow isAuthenticated() {
        return this.isAuthenticated;
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final Flow isBypassEnabled() {
        return this.isBypassEnabled;
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final boolean isFaceAuthEnabledAndEnrolled() {
        return ((Boolean) this.biometricSettingsRepository.isFaceAuthEnrolledAndEnabled.getValue()).booleanValue();
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final boolean isFaceAuthStrong() {
        FaceSensorInfo faceSensorInfo = (FaceSensorInfo) this.facePropertyRepository.sensorInfo.getValue();
        return (faceSensorInfo != null ? faceSensorInfo.strength : null) == SensorStrength.STRONG;
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final StateFlow isLockedOut() {
        return this.isLockedOut;
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final boolean isRunning() {
        return ((Boolean) this.repository._isAuthRunning.getValue()).booleanValue();
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final void onAccessibilityAction() {
        runFaceAuth(FaceAuthUiEvent.FACE_AUTH_ACCESSIBILITY_ACTION, false);
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final void onDeviceLifted() {
        runFaceAuth(FaceAuthUiEvent.FACE_AUTH_TRIGGERED_PICK_UP_GESTURE_TRIGGERED, true);
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final void onNotificationPanelClicked() {
        runFaceAuth(FaceAuthUiEvent.FACE_AUTH_TRIGGERED_NOTIFICATION_PANEL_CLICKED, true);
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final void onPrimaryBouncerUserInput() {
        this.repository.cancel();
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final void onShadeExpansionStarted() {
        runFaceAuth(FaceAuthUiEvent.FACE_AUTH_TRIGGERED_QS_EXPANDED, false);
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final void onSwipeUpOnBouncer() {
        runFaceAuth(FaceAuthUiEvent.FACE_AUTH_TRIGGERED_SWIPE_UP_ON_BOUNCER, false);
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final void onUdfpsSensorTouched() {
        runFaceAuth(FaceAuthUiEvent.FACE_AUTH_TRIGGERED_UDFPS_POINTER_DOWN, false);
    }

    @Override // com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor
    public final void onWalletLaunched() {
        FaceSensorInfo faceSensorInfo = (FaceSensorInfo) ((StateFlowImpl) this.facePropertyRepository.sensorInfo.$$delegate_0).getValue();
        if ((faceSensorInfo != null ? faceSensorInfo.strength : null) == SensorStrength.STRONG) {
            runFaceAuth(FaceAuthUiEvent.FACE_AUTH_TRIGGERED_OCCLUDING_APP_REQUESTED, true);
        }
    }

    public final void runFaceAuth(FaceAuthUiEvent faceAuthUiEvent, boolean z) {
        DeviceEntryFaceAuthRepositoryImpl deviceEntryFaceAuthRepositoryImpl = this.repository;
        boolean booleanValue = ((Boolean) deviceEntryFaceAuthRepositoryImpl.isLockedOut.getValue()).booleanValue();
        StateFlowImpl stateFlowImpl = this.faceAuthenticationStatusOverride;
        if (booleanValue) {
            ErrorFaceAuthenticationStatus errorFaceAuthenticationStatus = new ErrorFaceAuthenticationStatus(9, this.context.getResources().getString(R.string.keyguard_face_unlock_unavailable));
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, errorFaceAuthenticationStatus);
        } else {
            stateFlowImpl.setValue(null);
            this.faceAuthenticationLogger.authRequested(faceAuthUiEvent);
            deviceEntryFaceAuthRepositoryImpl.requestAuthenticate(faceAuthUiEvent, z);
        }
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        final int i = 1;
        final int i2 = 0;
        KeyguardUpdateMonitor keyguardUpdateMonitor = this.keyguardUpdateMonitor;
        SystemUIDeviceEntryFaceAuthInteractor systemUIDeviceEntryFaceAuthInteractor = keyguardUpdateMonitor.mFaceAuthInteractor;
        if (systemUIDeviceEntryFaceAuthInteractor != null) {
            systemUIDeviceEntryFaceAuthInteractor.listeners.remove(keyguardUpdateMonitor.mFaceAuthenticationListener);
        }
        keyguardUpdateMonitor.mFaceAuthInteractor = this;
        this.listeners.add(keyguardUpdateMonitor.mFaceAuthenticationListener);
        FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 flowKt__TransformKt$onEach$$inlined$unsafeTransform$1 = new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(this.authenticationStatus, new SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$1(this, null), i2);
        CoroutineDispatcher coroutineDispatcher = this.mainDispatcher;
        Flow flowOn = FlowKt.flowOn(flowKt__TransformKt$onEach$$inlined$unsafeTransform$1, coroutineDispatcher);
        CoroutineScope coroutineScope = this.applicationScope;
        FlowKt.launchIn(flowOn, coroutineScope);
        FlowKt.launchIn(FlowKt.flowOn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(this.detectionStatus, new SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$2(this, null), i2), coroutineDispatcher), coroutineScope);
        DeviceEntryFaceAuthRepositoryImpl deviceEntryFaceAuthRepositoryImpl = this.repository;
        FlowKt.launchIn(FlowKt.flowOn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(deviceEntryFaceAuthRepositoryImpl.isLockedOut, new SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$3(this, null), i2), coroutineDispatcher), coroutineScope);
        FlowKt.launchIn(FlowKt.flowOn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(deviceEntryFaceAuthRepositoryImpl._isAuthRunning, new SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$4(this, null), i2), coroutineDispatcher), coroutineScope);
        UserRepositoryImpl userRepositoryImpl = this.userRepository;
        FlowKt.launchIn(FlowKt.flowOn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(com.android.systemui.util.kotlin.FlowKt.sample(deviceEntryFaceAuthRepositoryImpl.isAuthenticated, userRepositoryImpl.selectedUserInfo, SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$6.INSTANCE), new SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$7(this, null), i2), new SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$8(this, null), i2), coroutineDispatcher), coroutineScope);
        BiometricSettingsRepositoryImpl biometricSettingsRepositoryImpl = this.biometricSettingsRepository;
        FlowKt.launchIn(FlowKt.flowOn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(biometricSettingsRepositoryImpl.isFaceAuthEnrolledAndEnabled, new SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$9(this, null), i2), coroutineDispatcher), coroutineScope);
        FaceAuthenticationLogger faceAuthenticationLogger = this.faceAuthenticationLogger;
        faceAuthenticationLogger.getClass();
        faceAuthenticationLogger.logBuffer.log("DeviceEntryFaceAuthRepositoryLog", LogLevel.DEBUG, "KeyguardFaceAuthInteractor started", null);
        kotlin.Lazy lazy = this.isBouncerVisible$delegate;
        final SafeFlow pairwise = com.android.systemui.util.kotlin.FlowKt.pairwise((Flow) lazy.getValue());
        final Flow flow = new Flow() { // from class: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1$2$1 r0 = (com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1$2$1 r0 = new com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L41
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.util.kotlin.WithPrev r5 = (com.android.systemui.util.kotlin.WithPrev) r5
                        java.lang.Object r5 = r5.newValue
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L41
                        return r1
                    L41:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        Object collect = ((SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1) pairwise).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = ((SafeFlow) pairwise).collect(new SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$filter$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        FlowKt.launchIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(new Flow() { // from class: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1$2$1, reason: invalid class name */
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

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r6 instanceof com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1$2$1 r0 = (com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1$2$1 r0 = new com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L41
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.util.kotlin.WithPrev r5 = (com.android.systemui.util.kotlin.WithPrev) r5
                        java.lang.Object r5 = r5.newValue
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L41
                        return r1
                    L41:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        Object collect = ((SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1) flow).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = ((SafeFlow) flow).collect(new SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$filter$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, new SystemUIDeviceEntryFaceAuthInteractor$start$1(this, null), i2), coroutineScope);
        final SafeFlow pairwise2 = com.android.systemui.util.kotlin.FlowKt.pairwise(this.alternateBouncerInteractor.isVisible);
        final Flow flow2 = new Flow() { // from class: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1$2$1, reason: invalid class name */
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

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r6 instanceof com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1$2$1 r0 = (com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1$2$1 r0 = new com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L41
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.util.kotlin.WithPrev r5 = (com.android.systemui.util.kotlin.WithPrev) r5
                        java.lang.Object r5 = r5.newValue
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L41
                        return r1
                    L41:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        Object collect = ((SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1) pairwise2).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = ((SafeFlow) pairwise2).collect(new SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$filter$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        FlowKt.launchIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(new Flow() { // from class: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1$2$1, reason: invalid class name */
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

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r6 instanceof com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1$2$1 r0 = (com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1$2$1 r0 = new com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L41
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.util.kotlin.WithPrev r5 = (com.android.systemui.util.kotlin.WithPrev) r5
                        java.lang.Object r5 = r5.newValue
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L41
                        return r1
                    L41:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        Object collect = ((SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$map$1) flow2).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = ((SafeFlow) flow2).collect(new SystemUIDeviceEntryFaceAuthInteractorKt$whenItFlipsToTrue$$inlined$filter$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, new SystemUIDeviceEntryFaceAuthInteractor$start$2(this, null), i2), coroutineScope);
        Edge.Companion companion = Edge.Companion;
        KeyguardState keyguardState = KeyguardState.AOD;
        KeyguardState keyguardState2 = KeyguardState.LOCKSCREEN;
        Edge.StateToState stateToState = new Edge.StateToState(keyguardState, keyguardState2);
        KeyguardTransitionInteractor keyguardTransitionInteractor = this.keyguardTransitionInteractor;
        final ChannelLimitedFlowMerge merge = FlowKt.merge(keyguardTransitionInteractor.transition(stateToState), keyguardTransitionInteractor.transition(new Edge.StateToState(KeyguardState.OFF, keyguardState2)), keyguardTransitionInteractor.transition(new Edge.StateToState(KeyguardState.DOZING, keyguardState2)));
        final SafeFlow sample = com.android.systemui.util.kotlin.FlowKt.sample(new Flow() { // from class: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$map$1$2$1 r0 = (com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$map$1$2$1 r0 = new com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L48
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        kotlin.Pair r5 = (kotlin.Pair) r5
                        java.lang.Object r5 = r5.component1()
                        java.lang.Boolean r5 = (java.lang.Boolean) r5
                        r5.getClass()
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L48
                        return r1
                    L48:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        Object collect = ((SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$3) merge).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((ChannelLimitedFlowMerge) merge).collect(new SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect3 = ((SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$3) merge).collect(new SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$map$2$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, this.powerInteractor.detailedWakefulness);
        FlowKt.launchIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(new Flow() { // from class: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ SystemUIDeviceEntryFaceAuthInteractor this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$2$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, SystemUIDeviceEntryFaceAuthInteractor systemUIDeviceEntryFaceAuthInteractor) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = systemUIDeviceEntryFaceAuthInteractor;
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
                        boolean r0 = r8 instanceof com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r8
                        com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$2$2$1 r0 = (com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$2$2$1 r0 = new com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$2$2$1
                        r0.<init>(r8)
                    L18:
                        java.lang.Object r8 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L59
                    L27:
                        java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                        java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                        r6.<init>(r7)
                        throw r6
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r8)
                        r8 = r7
                        com.android.systemui.power.shared.model.WakefulnessModel r8 = (com.android.systemui.power.shared.model.WakefulnessModel) r8
                        com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor r2 = r6.this$0
                        com.android.systemui.deviceentry.data.repository.FaceWakeUpTriggersConfig r4 = r2.faceWakeUpTriggersConfig
                        com.android.systemui.power.shared.model.WakeSleepReason r5 = r8.lastWakeReason
                        com.android.systemui.deviceentry.data.repository.FaceWakeUpTriggersConfigImpl r4 = (com.android.systemui.deviceentry.data.repository.FaceWakeUpTriggersConfigImpl) r4
                        java.util.Set r4 = r4.wakeSleepReasonsToTriggerFaceAuth
                        boolean r4 = r4.contains(r5)
                        if (r4 != 0) goto L4c
                        com.android.systemui.power.shared.model.WakeSleepReason r8 = r8.lastWakeReason
                        com.android.systemui.log.FaceAuthenticationLogger r2 = r2.faceAuthenticationLogger
                        r2.ignoredWakeupReason(r8)
                    L4c:
                        if (r4 == 0) goto L59
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r6 = r6.$this_unsafeFlow
                        java.lang.Object r6 = r6.emit(r7, r0)
                        if (r6 != r1) goto L59
                        return r1
                    L59:
                        kotlin.Unit r6 = kotlin.Unit.INSTANCE
                        return r6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = SafeFlow.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }, new SystemUIDeviceEntryFaceAuthInteractor$start$5(this, null), i2), coroutineScope);
        final SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$3 systemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$3 = new SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$3(com.android.systemui.util.kotlin.FlowKt.sample(this.deviceEntryFingerprintAuthInteractor.isLockedOut, biometricSettingsRepositoryImpl.isFaceAuthEnrolledAndEnabled, SystemUIDeviceEntryFaceAuthInteractor$start$7.INSTANCE), i2);
        FlowKt.launchIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(com.android.systemui.util.kotlin.FlowKt.sample(new Flow() { // from class: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$map$1$2$1, reason: invalid class name */
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

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r6 instanceof com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$map$1$2$1 r0 = (com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$map$1$2$1 r0 = new com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L48
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        kotlin.Pair r5 = (kotlin.Pair) r5
                        java.lang.Object r5 = r5.component1()
                        java.lang.Boolean r5 = (java.lang.Boolean) r5
                        r5.getClass()
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L48
                        return r1
                    L48:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        Object collect = ((SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$3) systemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$3).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((ChannelLimitedFlowMerge) systemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$3).collect(new SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect3 = ((SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$3) systemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$3).collect(new SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$map$2$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, userRepositoryImpl.selectedUser, SystemUIDeviceEntryFaceAuthInteractor$start$11.INSTANCE), new SystemUIDeviceEntryFaceAuthInteractor$start$12(this, null), i2), coroutineScope);
        final SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$3 systemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$32 = new SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$3(com.android.systemui.util.kotlin.FlowKt.pairwise(userRepositoryImpl.selectedUser), i);
        final int i3 = 2;
        FlowKt.launchIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(com.android.systemui.util.kotlin.FlowKt.sample(new Flow() { // from class: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$map$1$2$1, reason: invalid class name */
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

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r6 instanceof com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$map$1$2$1 r0 = (com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$map$1$2$1 r0 = new com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L48
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        kotlin.Pair r5 = (kotlin.Pair) r5
                        java.lang.Object r5 = r5.component1()
                        java.lang.Boolean r5 = (java.lang.Boolean) r5
                        r5.getClass()
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L48
                        return r1
                    L48:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i3) {
                    case 0:
                        Object collect = ((SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$3) systemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$32).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((ChannelLimitedFlowMerge) systemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$32).collect(new SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect3 = ((SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$3) systemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$32).collect(new SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$map$2$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, (Flow) lazy.getValue(), SystemUIDeviceEntryFaceAuthInteractor$start$16.INSTANCE), new SystemUIDeviceEntryFaceAuthInteractor$start$17(this, null), i2), coroutineScope);
        FlowKt.launchIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(this.facePropertyRepository.cameraInfo, new SystemUIDeviceEntryFaceAuthInteractor$start$18(this, null), i2), coroutineScope);
    }
}
