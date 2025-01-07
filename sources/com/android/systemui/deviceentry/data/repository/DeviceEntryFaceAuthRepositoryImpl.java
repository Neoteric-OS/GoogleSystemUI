package com.android.systemui.deviceentry.data.repository;

import android.hardware.face.FaceManager;
import android.hardware.face.FaceSensorPropertiesInternal;
import android.os.CancellationSignal;
import android.os.SystemClock;
import com.android.compose.animation.scene.SceneKey;
import com.android.internal.logging.UiEventLogger;
import com.android.keyguard.ActiveUnlockConfig$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.biometrics.domain.interactor.DisplayStateInteractor;
import com.android.systemui.biometrics.domain.interactor.DisplayStateInteractorImpl;
import com.android.systemui.bouncer.domain.interactor.AlternateBouncerInteractor;
import com.android.systemui.deviceentry.shared.FaceAuthUiEvent;
import com.android.systemui.deviceentry.shared.model.AcquiredFaceAuthenticationStatus;
import com.android.systemui.deviceentry.shared.model.ErrorFaceAuthenticationStatus;
import com.android.systemui.deviceentry.shared.model.FaceDetectionStatus;
import com.android.systemui.deviceentry.shared.model.FailedFaceAuthenticationStatus;
import com.android.systemui.deviceentry.shared.model.HelpFaceAuthenticationStatus;
import com.android.systemui.deviceentry.shared.model.SuccessFaceAuthenticationStatus;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.keyguard.data.repository.BiometricSettingsRepositoryImpl;
import com.android.systemui.keyguard.data.repository.DeviceEntryFingerprintAuthRepository;
import com.android.systemui.keyguard.data.repository.DeviceEntryFingerprintAuthRepositoryImpl;
import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$isFinishedIn$$inlined$map$2;
import com.android.systemui.keyguard.shared.model.Edge;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.log.FaceAuthenticationLogger;
import com.android.systemui.log.SessionTracker;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.power.domain.interactor.PowerInteractor$special$$inlined$map$1;
import com.android.systemui.scene.shared.model.Scenes;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.user.data.repository.UserRepositoryImpl;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import dagger.Lazy;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SpreadBuilder;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedEagerly;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeviceEntryFaceAuthRepositoryImpl implements Dumpable {
    public final StateFlowImpl _authenticationStatus;
    public final StateFlowImpl _detectionStatus;
    public final StateFlowImpl _isAuthRunning;
    public final StateFlowImpl _isAuthenticated;
    public final StateFlowImpl _isLockedOut;
    public final AlternateBouncerInteractor alternateBouncerInteractor;
    public final CoroutineScope applicationScope;
    public CancellationSignal authCancellationSignal;
    public final CoroutineDispatcher backgroundDispatcher;
    public final BiometricSettingsRepositoryImpl biometricSettingsRepository;
    public final ReadonlyStateFlow canRunDetection;
    public final ReadonlyStateFlow canRunFaceAuth;
    public StandaloneCoroutine cancelNotReceivedHandlerJob;
    public final StateFlowImpl cancellationInProgress;
    public CancellationSignal detectCancellationSignal;
    public final DeviceEntryFaceAuthRepositoryImpl$detectionCallback$1 detectionCallback;
    public final DisplayStateInteractor displayStateInteractor;
    public final DeviceEntryFaceAuthRepositoryImpl$faceAuthCallback$1 faceAuthCallback;
    public final FaceAuthenticationLogger faceAuthLogger;
    public final DeviceEntryFaceAuthRepositoryImpl$faceLockoutResetCallback$1 faceLockoutResetCallback;
    public final FaceManager faceManager;
    public StandaloneCoroutine halErrorRetryJob;
    public final StateFlowImpl isAuthenticated;
    public final Flow isBypassEnabled;
    public final boolean isDetectionSupported;
    public final StateFlowImpl isLockedOut;
    public final KeyguardBypassController keyguardBypassController;
    public final KeyguardInteractor keyguardInteractor;
    public final KeyguardRepositoryImpl keyguardRepository;
    public final KeyguardTransitionInteractor keyguardTransitionInteractor;
    public final CoroutineDispatcher mainDispatcher;
    public final StateFlowImpl pendingAuthenticateRequest;
    public int retryCount;
    public final SessionTracker sessionTracker;
    public final UiEventLogger uiEventsLogger;
    public final UserRepositoryImpl userRepository;

    /* JADX WARN: Type inference failed for: r12v1, types: [com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$faceLockoutResetCallback$1] */
    /* JADX WARN: Type inference failed for: r1v16, types: [com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$faceAuthCallback$1] */
    /* JADX WARN: Type inference failed for: r1v17, types: [com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$detectionCallback$1] */
    public DeviceEntryFaceAuthRepositoryImpl(FaceManager faceManager, UserRepositoryImpl userRepositoryImpl, KeyguardBypassController keyguardBypassController, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, Executor executor, SessionTracker sessionTracker, UiEventLogger uiEventLogger, FaceAuthenticationLogger faceAuthenticationLogger, BiometricSettingsRepositoryImpl biometricSettingsRepositoryImpl, DeviceEntryFingerprintAuthRepository deviceEntryFingerprintAuthRepository, KeyguardRepositoryImpl keyguardRepositoryImpl, PowerInteractor powerInteractor, KeyguardInteractor keyguardInteractor, AlternateBouncerInteractor alternateBouncerInteractor, Lazy lazy, TableLogBuffer tableLogBuffer, TableLogBuffer tableLogBuffer2, KeyguardTransitionInteractor keyguardTransitionInteractor, DisplayStateInteractor displayStateInteractor, DumpManager dumpManager) {
        List sensorPropertiesInternal;
        FaceSensorPropertiesInternal faceSensorPropertiesInternal;
        this.faceManager = faceManager;
        this.userRepository = userRepositoryImpl;
        this.keyguardBypassController = keyguardBypassController;
        this.applicationScope = coroutineScope;
        this.mainDispatcher = coroutineDispatcher;
        this.backgroundDispatcher = coroutineDispatcher2;
        this.sessionTracker = sessionTracker;
        this.uiEventsLogger = uiEventLogger;
        this.faceAuthLogger = faceAuthenticationLogger;
        this.biometricSettingsRepository = biometricSettingsRepositoryImpl;
        this.keyguardRepository = keyguardRepositoryImpl;
        this.keyguardInteractor = keyguardInteractor;
        this.alternateBouncerInteractor = alternateBouncerInteractor;
        this.keyguardTransitionInteractor = keyguardTransitionInteractor;
        this.displayStateInteractor = displayStateInteractor;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(null);
        this.pendingAuthenticateRequest = MutableStateFlow;
        this._authenticationStatus = StateFlowKt.MutableStateFlow(null);
        this._detectionStatus = StateFlowKt.MutableStateFlow(null);
        Boolean bool = Boolean.FALSE;
        StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(bool);
        this._isLockedOut = MutableStateFlow2;
        this.isLockedOut = MutableStateFlow2;
        this.isDetectionSupported = (faceManager == null || (sensorPropertiesInternal = faceManager.getSensorPropertiesInternal()) == null || (faceSensorPropertiesInternal = (FaceSensorPropertiesInternal) CollectionsKt.firstOrNull(sensorPropertiesInternal)) == null) ? false : faceSensorPropertiesInternal.supportsFaceDetection;
        this._isAuthRunning = StateFlowKt.MutableStateFlow(bool);
        StateFlowImpl MutableStateFlow3 = StateFlowKt.MutableStateFlow(bool);
        this._isAuthenticated = MutableStateFlow3;
        this.isAuthenticated = MutableStateFlow3;
        StateFlowImpl MutableStateFlow4 = StateFlowKt.MutableStateFlow(bool);
        this.cancellationInProgress = MutableStateFlow4;
        Flow flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 = (keyguardBypassController == null || (flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 = FlowConflatedKt.conflatedCallbackFlow(new DeviceEntryFaceAuthRepositoryImpl$isBypassEnabled$1$1(keyguardBypassController, null))) == null) ? new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(bool) : flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
        this.isBypassEnabled = flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
        this.faceLockoutResetCallback = new FaceManager.LockoutResetCallback() { // from class: com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$faceLockoutResetCallback$1
            public final void onLockoutReset(int i) {
                StateFlowImpl stateFlowImpl = DeviceEntryFaceAuthRepositoryImpl.this._isLockedOut;
                Boolean bool2 = Boolean.FALSE;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, bool2);
            }
        };
        executor.execute(new Runnable() { // from class: com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl.1
            @Override // java.lang.Runnable
            public final void run() {
                DeviceEntryFaceAuthRepositoryImpl deviceEntryFaceAuthRepositoryImpl = DeviceEntryFaceAuthRepositoryImpl.this;
                FaceManager faceManager2 = deviceEntryFaceAuthRepositoryImpl.faceManager;
                if (faceManager2 != null) {
                    faceManager2.addLockoutResetCallback(deviceEntryFaceAuthRepositoryImpl.faceLockoutResetCallback);
                }
                DeviceEntryFaceAuthRepositoryImpl.this.faceAuthLogger.addLockoutResetCallbackDone();
            }
        });
        dumpManager.registerCriticalDumpable("DeviceEntryFaceAuthRepositoryImpl", this);
        SpreadBuilder spreadBuilder = new SpreadBuilder(5);
        spreadBuilder.addSpread(gatingConditionsForAuthAndDetect());
        spreadBuilder.add(new Pair(new DeviceEntryFaceAuthRepositoryKt$isFalse$$inlined$map$1(MutableStateFlow2), "isNotInLockOutState"));
        spreadBuilder.add(new Pair(new DeviceEntryFaceAuthRepositoryKt$isFalse$$inlined$map$1(keyguardRepositoryImpl.isKeyguardDismissible), "keyguardIsNotDismissible"));
        spreadBuilder.add(new Pair(biometricSettingsRepositoryImpl.isFaceAuthCurrentlyAllowed, "isFaceAuthCurrentlyAllowed"));
        spreadBuilder.add(new Pair(new DeviceEntryFaceAuthRepositoryKt$isFalse$$inlined$map$1(MutableStateFlow3), "faceNotAuthenticated"));
        Flow flowOn = FlowKt.flowOn(DeviceEntryFaceAuthRepositoryKt.access$andAllFlows(CollectionsKt__CollectionsKt.listOf(spreadBuilder.list.toArray(new Pair[spreadBuilder.list.size()])), "canFaceAuthRun", tableLogBuffer2), coroutineDispatcher2);
        StartedEagerly startedEagerly = SharingStarted.Companion.Eagerly;
        ReadonlyStateFlow stateIn = FlowKt.stateIn(flowOn, coroutineScope, startedEagerly, bool);
        this.canRunFaceAuth = stateIn;
        SpreadBuilder spreadBuilder2 = new SpreadBuilder(4);
        spreadBuilder2.addSpread(gatingConditionsForAuthAndDetect());
        spreadBuilder2.add(new Pair(flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2, "isBypassEnabled"));
        spreadBuilder2.add(new Pair(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(new DeviceEntryFaceAuthRepositoryKt$isFalse$$inlined$map$1(biometricSettingsRepositoryImpl.isFaceAuthCurrentlyAllowed), keyguardRepositoryImpl.isKeyguardDismissible, new DeviceEntryFaceAuthRepositoryKt$or$1(3, null)), "faceAuthIsNotCurrentlyAllowedOrCurrentUserIsTrusted"));
        DeviceEntryFingerprintAuthRepositoryImpl deviceEntryFingerprintAuthRepositoryImpl = (DeviceEntryFingerprintAuthRepositoryImpl) deviceEntryFingerprintAuthRepository;
        final Flow availableFpSensorType = deviceEntryFingerprintAuthRepositoryImpl.getAvailableFpSensorType();
        final int i = 0;
        spreadBuilder2.add(new Pair(new DeviceEntryFaceAuthRepositoryKt$isFalse$$inlined$map$1(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(new Flow() { // from class: com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$isUdfps$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$isUdfps$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$isUdfps$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$isUdfps$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$isUdfps$$inlined$map$1$2$1 r0 = (com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$isUdfps$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$isUdfps$$inlined$map$1$2$1 r0 = new com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$isUdfps$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4a
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.keyguard.data.repository.BiometricType r5 = (com.android.systemui.keyguard.data.repository.BiometricType) r5
                        com.android.systemui.keyguard.data.repository.BiometricType r6 = com.android.systemui.keyguard.data.repository.BiometricType.UNDER_DISPLAY_FINGERPRINT
                        if (r5 != r6) goto L3a
                        r5 = r3
                        goto L3b
                    L3a:
                        r5 = 0
                    L3b:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4a
                        return r1
                    L4a:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$isUdfps$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        Object collect = availableFpSensorType.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = availableFpSensorType.collect(new DeviceEntryFaceAuthRepositoryImpl$listenForSchedulingWatchdog$$inlined$filter$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, deviceEntryFingerprintAuthRepositoryImpl.isRunning(), new DeviceEntryFaceAuthRepositoryKt$and$1(3, null))), "udfpsAuthIsNotPossibleAnymore"));
        ReadonlyStateFlow stateIn2 = FlowKt.stateIn(FlowKt.flowOn(DeviceEntryFaceAuthRepositoryKt.access$andAllFlows(CollectionsKt__CollectionsKt.listOf(spreadBuilder2.list.toArray(new Pair[spreadBuilder2.list.size()])), "canFaceDetectRun", tableLogBuffer), coroutineDispatcher2), coroutineScope, startedEagerly, bool);
        this.canRunDetection = stateIn2;
        int i2 = 0;
        FlowKt.launchIn(FlowKt.flowOn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(stateIn, new DeviceEntryFaceAuthRepositoryImpl$observeFaceAuthGatingChecks$1(this, null), i2), coroutineDispatcher), coroutineScope);
        FlowKt.launchIn(FlowKt.flowOn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(stateIn2, new DeviceEntryFaceAuthRepositoryImpl$observeFaceDetectGatingChecks$1(this, null), i2), coroutineDispatcher), coroutineScope);
        PowerInteractor$special$$inlined$map$1 powerInteractor$special$$inlined$map$1 = powerInteractor.isAsleep;
        SceneKey sceneKey = Scenes.Gone;
        KeyguardState keyguardState = KeyguardState.GONE;
        FlowKt.launchIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.flowOn(FlowKt.merge(powerInteractor$special$$inlined$map$1, new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(keyguardTransitionInteractor.isFinishedIn(keyguardState), keyguardInteractor.statusBarState, new DeviceEntryFaceAuthRepositoryImpl$observeFaceAuthResettingConditions$1(3, null)), new DeviceEntryFaceAuthRepositoryImpl$gatingConditionsForAuthAndDetect$$inlined$map$3(userRepositoryImpl.selectedUser, 1)), coroutineDispatcher), new DeviceEntryFaceAuthRepositoryImpl$observeFaceAuthResettingConditions$3(this, null), 0), coroutineScope);
        Edge.Companion companion = Edge.Companion;
        Edge.Companion.create$default(sceneKey);
        final int i3 = 1;
        final Flow transition = keyguardTransitionInteractor.transition(Edge.Companion.create$default(null, keyguardState, 1));
        int i4 = 0;
        FlowKt.launchIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(new Flow() { // from class: com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$isUdfps$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$isUdfps$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$isUdfps$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$isUdfps$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$isUdfps$$inlined$map$1$2$1 r0 = (com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$isUdfps$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$isUdfps$$inlined$map$1$2$1 r0 = new com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$isUdfps$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4a
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.keyguard.data.repository.BiometricType r5 = (com.android.systemui.keyguard.data.repository.BiometricType) r5
                        com.android.systemui.keyguard.data.repository.BiometricType r6 = com.android.systemui.keyguard.data.repository.BiometricType.UNDER_DISPLAY_FINGERPRINT
                        if (r5 != r6) goto L3a
                        r5 = r3
                        goto L3b
                    L3a:
                        r5 = 0
                    L3b:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4a
                        return r1
                    L4a:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$isUdfps$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i3) {
                    case 0:
                        Object collect = transition.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = transition.collect(new DeviceEntryFaceAuthRepositoryImpl$listenForSchedulingWatchdog$$inlined$filter$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, new DeviceEntryFaceAuthRepositoryImpl$listenForSchedulingWatchdog$2(this, null), i4), coroutineScope);
        FlowKt.launchIn(FlowKt.flowOn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.combine(MutableStateFlow, stateIn, stateIn2, MutableStateFlow4, new DeviceEntryFaceAuthRepositoryImpl$processPendingAuthRequests$1(this, null)), new DeviceEntryFaceAuthRepositoryImpl$processPendingAuthRequests$2(this, null), i4), coroutineDispatcher), coroutineScope);
        this.faceAuthCallback = new FaceManager.AuthenticationCallback() { // from class: com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$faceAuthCallback$1
            public final void onAuthenticationAcquired(int i5) {
                StateFlowImpl stateFlowImpl = DeviceEntryFaceAuthRepositoryImpl.this._authenticationStatus;
                AcquiredFaceAuthenticationStatus acquiredFaceAuthenticationStatus = new AcquiredFaceAuthenticationStatus(i5);
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, acquiredFaceAuthenticationStatus);
            }

            public final void onAuthenticationError(int i5, CharSequence charSequence) {
                ErrorFaceAuthenticationStatus errorFaceAuthenticationStatus = new ErrorFaceAuthenticationStatus(i5, String.valueOf(charSequence));
                if (errorFaceAuthenticationStatus.isLockoutError()) {
                    StateFlowImpl stateFlowImpl = DeviceEntryFaceAuthRepositoryImpl.this._isLockedOut;
                    Boolean bool2 = Boolean.TRUE;
                    stateFlowImpl.getClass();
                    stateFlowImpl.updateState(null, bool2);
                }
                StateFlowImpl stateFlowImpl2 = DeviceEntryFaceAuthRepositoryImpl.this._isAuthenticated;
                Boolean bool3 = Boolean.FALSE;
                stateFlowImpl2.getClass();
                stateFlowImpl2.updateState(null, bool3);
                StateFlowImpl stateFlowImpl3 = DeviceEntryFaceAuthRepositoryImpl.this._authenticationStatus;
                stateFlowImpl3.getClass();
                stateFlowImpl3.updateState(null, errorFaceAuthenticationStatus);
                int i6 = errorFaceAuthenticationStatus.msgId;
                if (i6 == 1 || i6 == 2) {
                    DeviceEntryFaceAuthRepositoryImpl.this.faceAuthLogger.hardwareError(errorFaceAuthenticationStatus);
                    DeviceEntryFaceAuthRepositoryImpl deviceEntryFaceAuthRepositoryImpl = DeviceEntryFaceAuthRepositoryImpl.this;
                    int i7 = deviceEntryFaceAuthRepositoryImpl.retryCount;
                    if (i7 < 5) {
                        deviceEntryFaceAuthRepositoryImpl.retryCount = i7 + 1;
                        StandaloneCoroutine standaloneCoroutine = deviceEntryFaceAuthRepositoryImpl.halErrorRetryJob;
                        if (standaloneCoroutine != null) {
                            standaloneCoroutine.cancel(null);
                        }
                        deviceEntryFaceAuthRepositoryImpl.halErrorRetryJob = BuildersKt.launch$default(deviceEntryFaceAuthRepositoryImpl.applicationScope, null, null, new DeviceEntryFaceAuthRepositoryImpl$handleFaceHardwareError$1(deviceEntryFaceAuthRepositoryImpl, null), 3);
                    }
                }
                DeviceEntryFaceAuthRepositoryImpl.this.faceAuthLogger.authenticationError(i5, charSequence, errorFaceAuthenticationStatus.isLockoutError(), i6 == 5);
                DeviceEntryFaceAuthRepositoryImpl.access$onFaceAuthRequestCompleted(DeviceEntryFaceAuthRepositoryImpl.this);
            }

            public final void onAuthenticationFailed() {
                StateFlowImpl stateFlowImpl = DeviceEntryFaceAuthRepositoryImpl.this._isAuthenticated;
                Boolean bool2 = Boolean.FALSE;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, bool2);
                FaceAuthenticationLogger faceAuthenticationLogger2 = DeviceEntryFaceAuthRepositoryImpl.this.faceAuthLogger;
                faceAuthenticationLogger2.getClass();
                faceAuthenticationLogger2.logBuffer.log("DeviceEntryFaceAuthRepositoryLog", LogLevel.DEBUG, "Face authentication failed", null);
                StateFlowImpl stateFlowImpl2 = DeviceEntryFaceAuthRepositoryImpl.this._authenticationStatus;
                FailedFaceAuthenticationStatus failedFaceAuthenticationStatus = new FailedFaceAuthenticationStatus();
                stateFlowImpl2.getClass();
                stateFlowImpl2.updateState(null, failedFaceAuthenticationStatus);
                if (((Boolean) DeviceEntryFaceAuthRepositoryImpl.this._isLockedOut.getValue()).booleanValue()) {
                    return;
                }
                DeviceEntryFaceAuthRepositoryImpl.access$onFaceAuthRequestCompleted(DeviceEntryFaceAuthRepositoryImpl.this);
            }

            public final void onAuthenticationHelp(int i5, CharSequence charSequence) {
                StateFlowImpl stateFlowImpl = DeviceEntryFaceAuthRepositoryImpl.this._authenticationStatus;
                HelpFaceAuthenticationStatus helpFaceAuthenticationStatus = new HelpFaceAuthenticationStatus(i5, SystemClock.elapsedRealtime(), charSequence != null ? charSequence.toString() : null);
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, helpFaceAuthenticationStatus);
            }

            public final void onAuthenticationSucceeded(FaceManager.AuthenticationResult authenticationResult) {
                StateFlowImpl stateFlowImpl = DeviceEntryFaceAuthRepositoryImpl.this._isAuthenticated;
                Boolean bool2 = Boolean.TRUE;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, bool2);
                StateFlowImpl stateFlowImpl2 = DeviceEntryFaceAuthRepositoryImpl.this._authenticationStatus;
                SuccessFaceAuthenticationStatus successFaceAuthenticationStatus = new SuccessFaceAuthenticationStatus(authenticationResult);
                stateFlowImpl2.getClass();
                stateFlowImpl2.updateState(null, successFaceAuthenticationStatus);
                DeviceEntryFaceAuthRepositoryImpl.this.faceAuthLogger.faceAuthSuccess(authenticationResult);
                DeviceEntryFaceAuthRepositoryImpl.access$onFaceAuthRequestCompleted(DeviceEntryFaceAuthRepositoryImpl.this);
            }
        };
        this.detectionCallback = new FaceManager.FaceDetectionCallback() { // from class: com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$detectionCallback$1
            public final void onFaceDetected(int i5, int i6, boolean z) {
                FaceAuthenticationLogger faceAuthenticationLogger2 = DeviceEntryFaceAuthRepositoryImpl.this.faceAuthLogger;
                faceAuthenticationLogger2.getClass();
                faceAuthenticationLogger2.logBuffer.log("DeviceEntryFaceAuthRepositoryLog", LogLevel.DEBUG, "Face detected", null);
                StateFlowImpl stateFlowImpl = DeviceEntryFaceAuthRepositoryImpl.this._detectionStatus;
                FaceDetectionStatus faceDetectionStatus = new FaceDetectionStatus(i5, i6, z);
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, faceDetectionStatus);
            }
        };
    }

    public static final void access$clearPendingAuthRequest(DeviceEntryFaceAuthRepositoryImpl deviceEntryFaceAuthRepositoryImpl, String str) {
        StateFlowImpl stateFlowImpl = deviceEntryFaceAuthRepositoryImpl.pendingAuthenticateRequest;
        AuthenticationRequest authenticationRequest = (AuthenticationRequest) stateFlowImpl.getValue();
        FaceAuthUiEvent faceAuthUiEvent = authenticationRequest != null ? authenticationRequest.uiEvent : null;
        AuthenticationRequest authenticationRequest2 = (AuthenticationRequest) stateFlowImpl.getValue();
        deviceEntryFaceAuthRepositoryImpl.faceAuthLogger.clearingPendingAuthRequest(str, faceAuthUiEvent, authenticationRequest2 != null ? Boolean.valueOf(authenticationRequest2.fallbackToDetection) : null);
        stateFlowImpl.setValue(null);
    }

    public static final void access$onFaceAuthRequestCompleted(DeviceEntryFaceAuthRepositoryImpl deviceEntryFaceAuthRepositoryImpl) {
        StandaloneCoroutine standaloneCoroutine = deviceEntryFaceAuthRepositoryImpl.cancelNotReceivedHandlerJob;
        if (standaloneCoroutine != null) {
            standaloneCoroutine.cancel(null);
        }
        Boolean bool = Boolean.FALSE;
        StateFlowImpl stateFlowImpl = deviceEntryFaceAuthRepositoryImpl._isAuthRunning;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, bool);
        deviceEntryFaceAuthRepositoryImpl.authCancellationSignal = null;
        StateFlowImpl stateFlowImpl2 = deviceEntryFaceAuthRepositoryImpl.cancellationInProgress;
        stateFlowImpl2.getClass();
        stateFlowImpl2.updateState(null, bool);
    }

    public final void cancel() {
        CancellationSignal cancellationSignal = this.authCancellationSignal;
        if (cancellationSignal == null) {
            return;
        }
        cancellationSignal.cancel();
        StandaloneCoroutine standaloneCoroutine = this.cancelNotReceivedHandlerJob;
        if (standaloneCoroutine != null) {
            standaloneCoroutine.cancel(null);
        }
        this.cancelNotReceivedHandlerJob = BuildersKt.launch$default(this.applicationScope, null, null, new DeviceEntryFaceAuthRepositoryImpl$cancel$1(this, null), 3);
        StateFlowImpl stateFlowImpl = this.cancellationInProgress;
        Boolean bool = Boolean.TRUE;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, bool);
        Boolean bool2 = Boolean.FALSE;
        StateFlowImpl stateFlowImpl2 = this._isAuthRunning;
        stateFlowImpl2.getClass();
        stateFlowImpl2.updateState(null, bool2);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        List sensorPropertiesInternal;
        FaceSensorPropertiesInternal faceSensorPropertiesInternal;
        printWriter.println("DeviceEntryFaceAuthRepositoryImpl state:");
        printWriter.println("  cancellationInProgress: " + this.cancellationInProgress);
        printWriter.println("  _isLockedOut.value: " + this._isLockedOut.getValue());
        printWriter.println("  _isAuthRunning.value: " + this._isAuthRunning.getValue());
        printWriter.println("  isDetectionSupported: " + this.isDetectionSupported);
        printWriter.println("  FaceManager state:");
        printWriter.println("    faceManager: " + this.faceManager);
        FaceManager faceManager = this.faceManager;
        Boolean bool = null;
        printWriter.println("    sensorPropertiesInternal: " + (faceManager != null ? faceManager.getSensorPropertiesInternal() : null));
        FaceManager faceManager2 = this.faceManager;
        if (faceManager2 != null && (sensorPropertiesInternal = faceManager2.getSensorPropertiesInternal()) != null && (faceSensorPropertiesInternal = (FaceSensorPropertiesInternal) CollectionsKt.firstOrNull(sensorPropertiesInternal)) != null) {
            bool = Boolean.valueOf(faceSensorPropertiesInternal.supportsFaceDetection);
        }
        printWriter.println("    supportsFaceDetection: " + bool);
        printWriter.println("  _pendingAuthenticateRequest: " + this.pendingAuthenticateRequest.getValue());
        printWriter.println("  authCancellationSignal: " + this.authCancellationSignal);
        printWriter.println("  detectCancellationSignal: " + this.detectCancellationSignal);
        printWriter.println("  _authenticationStatus: " + this._authenticationStatus.getValue());
        printWriter.println("  _detectionStatus: " + this._detectionStatus.getValue());
        printWriter.println("  currentUserId: " + this.userRepository.getSelectedUserInfo().id);
        printWriter.println("  keyguardSessionId: " + this.sessionTracker.getSessionId(1));
        KeyguardBypassController keyguardBypassController = this.keyguardBypassController;
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("  lockscreenBypassEnabled: ", keyguardBypassController != null ? keyguardBypassController.getBypassEnabled() : false, printWriter);
    }

    public final Pair[] gatingConditionsForAuthAndDetect() {
        Flow isInTransitionWhere;
        Flow flow = ((DisplayStateInteractorImpl) this.displayStateInteractor).isDefaultDisplayOff;
        KeyguardState.Companion companion = KeyguardState.Companion;
        DeviceEntryFaceAuthRepositoryImpl$gatingConditionsForAuthAndDetect$1 deviceEntryFaceAuthRepositoryImpl$gatingConditionsForAuthAndDetect$1 = new DeviceEntryFaceAuthRepositoryImpl$gatingConditionsForAuthAndDetect$1(1, companion, KeyguardState.Companion.class, "deviceIsAwakeInState", "deviceIsAwakeInState(Lcom/android/systemui/keyguard/shared/model/KeyguardState;)Z", 0);
        KeyguardTransitionInteractor keyguardTransitionInteractor = this.keyguardTransitionInteractor;
        Pair pair = new Pair(new DeviceEntryFaceAuthRepositoryKt$isFalse$$inlined$map$1(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(flow, FlowKt.distinctUntilChanged(new KeyguardTransitionInteractor$isFinishedIn$$inlined$map$2(keyguardTransitionInteractor.finishedKeyguardState, deviceEntryFaceAuthRepositoryImpl$gatingConditionsForAuthAndDetect$1, 1)), new DeviceEntryFaceAuthRepositoryKt$and$1(3, null))), "displayIsNotOffWhileFullyTransitionedToAwake");
        BiometricSettingsRepositoryImpl biometricSettingsRepositoryImpl = this.biometricSettingsRepository;
        Pair pair2 = new Pair(biometricSettingsRepositoryImpl.isFaceAuthEnrolledAndEnabled, "isFaceAuthEnrolledAndEnabled");
        KeyguardRepositoryImpl keyguardRepositoryImpl = this.keyguardRepository;
        Pair pair3 = new Pair(new DeviceEntryFaceAuthRepositoryKt$isFalse$$inlined$map$1(keyguardRepositoryImpl.isKeyguardGoingAway), "keyguardNotGoingAway");
        isInTransitionWhere = keyguardTransitionInteractor.isInTransitionWhere(new Function1() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$isInTransitionWhere$1
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return Boolean.TRUE;
            }
        }, new DeviceEntryFaceAuthRepositoryImpl$gatingConditionsForAuthAndDetect$3(1, companion, KeyguardState.Companion.class, "deviceIsAsleepInState", "deviceIsAsleepInState(Lcom/android/systemui/keyguard/shared/model/KeyguardState;)Z", 0));
        Pair pair4 = new Pair(new DeviceEntryFaceAuthRepositoryKt$isFalse$$inlined$map$1(isInTransitionWhere), "deviceNotTransitioningToAsleepState");
        KeyguardInteractor keyguardInteractor = this.keyguardInteractor;
        return new Pair[]{pair, pair2, pair3, pair4, new Pair(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(new DeviceEntryFaceAuthRepositoryKt$isFalse$$inlined$map$1((Flow) keyguardInteractor.isSecureCameraActive$delegate.getValue()), new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(this.alternateBouncerInteractor.isVisible, keyguardInteractor.primaryBouncerShowing, new DeviceEntryFaceAuthRepositoryKt$or$1(3, null)), new DeviceEntryFaceAuthRepositoryKt$or$1(3, null)), "secureCameraNotActiveOrAnyBouncerIsShowing"), new Pair(biometricSettingsRepositoryImpl.isFaceAuthSupportedInCurrentPosture, "isFaceAuthSupportedInCurrentPosture"), new Pair(new DeviceEntryFaceAuthRepositoryKt$isFalse$$inlined$map$1(biometricSettingsRepositoryImpl.isCurrentUserInLockdown), "userHasNotLockedDownDevice"), new Pair(keyguardRepositoryImpl.isKeyguardShowing, "isKeyguardShowing"), new Pair(new DeviceEntryFaceAuthRepositoryKt$isFalse$$inlined$map$1(new DeviceEntryFaceAuthRepositoryImpl$gatingConditionsForAuthAndDetect$$inlined$map$3(this.userRepository.selectedUser, 0)), "userSwitchingInProgress")};
    }

    public final void requestAuthenticate(FaceAuthUiEvent faceAuthUiEvent, boolean z) {
        StateFlowImpl stateFlowImpl = this.pendingAuthenticateRequest;
        Object value = stateFlowImpl.getValue();
        FaceAuthenticationLogger faceAuthenticationLogger = this.faceAuthLogger;
        if (value != null) {
            AuthenticationRequest authenticationRequest = (AuthenticationRequest) stateFlowImpl.getValue();
            faceAuthenticationLogger.ignoredFaceAuthTrigger(authenticationRequest != null ? authenticationRequest.uiEvent : null, "Previously queued trigger skipped due to new request");
        }
        faceAuthenticationLogger.queueingRequest(faceAuthUiEvent, z);
        AuthenticationRequest authenticationRequest2 = new AuthenticationRequest(faceAuthUiEvent, z);
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, authenticationRequest2);
    }
}
