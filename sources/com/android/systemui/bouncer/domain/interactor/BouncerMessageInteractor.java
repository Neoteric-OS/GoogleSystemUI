package com.android.systemui.bouncer.domain.interactor;

import android.hardware.biometrics.BiometricSourceType;
import com.android.keyguard.KeyguardSecurityModel;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.authentication.shared.model.AuthenticationMethodModel;
import com.android.systemui.biometrics.data.repository.FacePropertyRepositoryImpl;
import com.android.systemui.bouncer.data.repository.BouncerMessageRepositoryImpl;
import com.android.systemui.bouncer.shared.model.BouncerMessageModel;
import com.android.systemui.bouncer.shared.model.BouncerMessageStrings;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryBiometricsAllowedInteractor;
import com.android.systemui.flags.SystemPropertiesHelper;
import com.android.systemui.keyguard.data.repository.BiometricSettingsRepositoryImpl;
import com.android.systemui.keyguard.data.repository.TrustRepositoryImpl;
import com.android.systemui.user.data.repository.UserRepositoryImpl;
import com.android.wm.shell.R;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BouncerMessageInteractor {
    public final StateFlowImpl bouncerMessage;
    public final CountDownTimerUtil countDownTimerUtil;
    public final FacePropertyRepositoryImpl facePropertyRepository;
    public final BouncerMessageInteractor$special$$inlined$map$1 initialBouncerMessage;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 isAnyBiometricsEnabledAndEnrolled;
    public final ReadonlyStateFlow isFingerprintAuthCurrentlyAllowedOnBouncer;
    public final BouncerMessageInteractor$kumCallback$1 kumCallback;
    public final BouncerMessageRepositoryImpl repository;
    public final KeyguardSecurityModel securityModel;
    public final SystemPropertiesHelper systemPropertiesHelper;
    public final UserRepositoryImpl userRepository;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.bouncer.domain.interactor.BouncerMessageInteractor$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function3 {
        /* synthetic */ Object L$0;
        /* synthetic */ boolean Z$0;
        int label;

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            boolean booleanValue = ((Boolean) obj).booleanValue();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(3, (Continuation) obj3);
            anonymousClass1.Z$0 = booleanValue;
            anonymousClass1.L$0 = (BouncerMessageModel) obj2;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            boolean z = this.Z$0;
            BouncerMessageModel bouncerMessageModel = (BouncerMessageModel) this.L$0;
            if (z) {
                return bouncerMessageModel;
            }
            return null;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.bouncer.domain.interactor.BouncerMessageInteractor$2, reason: invalid class name */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        /* synthetic */ Object L$0;
        int label;

        public AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass2 anonymousClass2 = BouncerMessageInteractor.this.new AnonymousClass2(continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            AnonymousClass2 anonymousClass2 = (AnonymousClass2) create((BouncerMessageModel) obj, (Continuation) obj2);
            Unit unit = Unit.INSTANCE;
            anonymousClass2.invokeSuspend(unit);
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            BouncerMessageInteractor.this.repository.setMessage((BouncerMessageModel) this.L$0, null);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v6, types: [com.android.keyguard.KeyguardUpdateMonitorCallback, com.android.systemui.bouncer.domain.interactor.BouncerMessageInteractor$kumCallback$1] */
    public BouncerMessageInteractor(BouncerMessageRepositoryImpl bouncerMessageRepositoryImpl, UserRepositoryImpl userRepositoryImpl, CountDownTimerUtil countDownTimerUtil, KeyguardUpdateMonitor keyguardUpdateMonitor, final TrustRepositoryImpl trustRepositoryImpl, final BiometricSettingsRepositoryImpl biometricSettingsRepositoryImpl, SystemPropertiesHelper systemPropertiesHelper, PrimaryBouncerInteractor primaryBouncerInteractor, CoroutineScope coroutineScope, FacePropertyRepositoryImpl facePropertyRepositoryImpl, KeyguardSecurityModel keyguardSecurityModel, final DeviceEntryBiometricsAllowedInteractor deviceEntryBiometricsAllowedInteractor) {
        this.repository = bouncerMessageRepositoryImpl;
        this.userRepository = userRepositoryImpl;
        this.countDownTimerUtil = countDownTimerUtil;
        this.systemPropertiesHelper = systemPropertiesHelper;
        this.facePropertyRepository = facePropertyRepositoryImpl;
        this.securityModel = keyguardSecurityModel;
        ReadonlyStateFlow stateIn = FlowKt.stateIn(deviceEntryBiometricsAllowedInteractor.isFingerprintCurrentlyAllowedOnBouncer, coroutineScope, SharingStarted.Companion.Eagerly, Boolean.FALSE);
        this.isFingerprintAuthCurrentlyAllowedOnBouncer = stateIn;
        ?? r6 = new KeyguardUpdateMonitorCallback() { // from class: com.android.systemui.bouncer.domain.interactor.BouncerMessageInteractor$kumCallback$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            public abstract /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[BiometricSourceType.values().length];
                    try {
                        iArr[BiometricSourceType.FINGERPRINT.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[BiometricSourceType.FACE.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onBiometricAcquired(BiometricSourceType biometricSourceType, int i) {
                BouncerMessageInteractor bouncerMessageInteractor = this;
                BouncerMessageRepositoryImpl bouncerMessageRepositoryImpl2 = bouncerMessageInteractor.repository;
                if (bouncerMessageRepositoryImpl2.messageSource == BiometricSourceType.FACE && i == 20) {
                    bouncerMessageRepositoryImpl2.setMessage(bouncerMessageInteractor.getDefaultMessage(), null);
                }
            }

            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onBiometricAuthFailed(BiometricSourceType biometricSourceType) {
                BouncerMessageModel message;
                AuthenticationMethodModel.Pattern pattern = AuthenticationMethodModel.Pattern.INSTANCE;
                AuthenticationMethodModel.Password password = AuthenticationMethodModel.Password.INSTANCE;
                AuthenticationMethodModel.Pin pin = AuthenticationMethodModel.Pin.INSTANCE;
                BiometricSourceType biometricSourceType2 = BiometricSourceType.FACE;
                DeviceEntryBiometricsAllowedInteractor deviceEntryBiometricsAllowedInteractor2 = DeviceEntryBiometricsAllowedInteractor.this;
                if (biometricSourceType == biometricSourceType2 && ((Boolean) deviceEntryBiometricsAllowedInteractor2.isFaceLockedOut.getValue()).booleanValue()) {
                    return;
                }
                if (biometricSourceType == BiometricSourceType.FINGERPRINT && ((Boolean) deviceEntryBiometricsAllowedInteractor2.isFingerprintLockedOut.getValue()).booleanValue()) {
                    return;
                }
                BouncerMessageInteractor bouncerMessageInteractor = this;
                BouncerMessageRepositoryImpl bouncerMessageRepositoryImpl2 = bouncerMessageInteractor.repository;
                int i = biometricSourceType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[biometricSourceType.ordinal()];
                if (i != 1) {
                    ReadonlyStateFlow readonlyStateFlow = bouncerMessageInteractor.isFingerprintAuthCurrentlyAllowedOnBouncer;
                    if (i != 2) {
                        Pair pair = BouncerMessageStrings.EmptyMessage;
                        message = BouncerMessageInteractorKt.toMessage(BouncerMessageStrings.defaultMessage(BouncerMessageInteractorKt.toAuthModel(bouncerMessageInteractor.getCurrentSecurityMode()), ((Boolean) ((StateFlowImpl) readonlyStateFlow.$$delegate_0).getValue()).booleanValue()));
                    } else {
                        Pair pair2 = BouncerMessageStrings.EmptyMessage;
                        AuthenticationMethodModel authModel = BouncerMessageInteractorKt.toAuthModel(bouncerMessageInteractor.getCurrentSecurityMode());
                        boolean booleanValue = ((Boolean) ((StateFlowImpl) readonlyStateFlow.$$delegate_0).getValue()).booleanValue();
                        Pair pair3 = BouncerMessageStrings.EmptyMessage;
                        Integer valueOf = Integer.valueOf(R.string.bouncer_face_not_recognized);
                        if (booleanValue) {
                            if (authModel.equals(pattern)) {
                                pair3 = new Pair(Integer.valueOf(BouncerMessageStrings.patternDefaultMessage(true)), valueOf);
                            } else if (authModel.equals(password)) {
                                pair3 = new Pair(Integer.valueOf(BouncerMessageStrings.passwordDefaultMessage(true)), valueOf);
                            } else if (authModel.equals(pin)) {
                                pair3 = new Pair(Integer.valueOf(BouncerMessageStrings.pinDefaultMessage(true)), valueOf);
                            }
                        } else if (authModel.equals(pattern)) {
                            pair3 = new Pair(valueOf, Integer.valueOf(R.string.kg_bio_try_again_or_pattern));
                        } else if (authModel.equals(password)) {
                            pair3 = new Pair(valueOf, Integer.valueOf(R.string.kg_bio_try_again_or_password));
                        } else if (authModel.equals(pin)) {
                            pair3 = new Pair(valueOf, Integer.valueOf(R.string.kg_bio_try_again_or_pin));
                        }
                        message = BouncerMessageInteractorKt.toMessage(pair3);
                    }
                } else {
                    Pair pair4 = BouncerMessageStrings.EmptyMessage;
                    AuthenticationMethodModel authModel2 = BouncerMessageInteractorKt.toAuthModel(bouncerMessageInteractor.getCurrentSecurityMode());
                    message = BouncerMessageInteractorKt.toMessage(authModel2.equals(pattern) ? new Pair(Integer.valueOf(R.string.kg_fp_not_recognized), Integer.valueOf(R.string.kg_bio_try_again_or_pattern)) : authModel2.equals(password) ? new Pair(Integer.valueOf(R.string.kg_fp_not_recognized), Integer.valueOf(R.string.kg_bio_try_again_or_password)) : authModel2.equals(pin) ? new Pair(Integer.valueOf(R.string.kg_fp_not_recognized), Integer.valueOf(R.string.kg_bio_try_again_or_pin)) : BouncerMessageStrings.EmptyMessage);
                }
                bouncerMessageRepositoryImpl2.setMessage(message, biometricSourceType);
            }

            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onBiometricAuthenticated(int i, BiometricSourceType biometricSourceType, boolean z) {
                BouncerMessageInteractor bouncerMessageInteractor = this;
                bouncerMessageInteractor.repository.setMessage(bouncerMessageInteractor.getDefaultMessage(), biometricSourceType);
            }
        };
        this.kumCallback = r6;
        FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(biometricSettingsRepositoryImpl.isFaceAuthEnrolledAndEnabled, biometricSettingsRepositoryImpl.isFingerprintEnrolledAndEnabled, new BouncerMessageInteractorKt$or$1(3, null));
        final BouncerMessageInteractor$special$$inlined$combine$1 bouncerMessageInteractor$special$$inlined$combine$1 = new BouncerMessageInteractor$special$$inlined$combine$1(new Flow[]{primaryBouncerInteractor.lastShownSecurityMode, biometricSettingsRepositoryImpl.authenticationFlags, trustRepositoryImpl.isCurrentUserTrustManaged(), flowKt__ZipKt$combine$$inlined$unsafeFlow$1, deviceEntryBiometricsAllowedInteractor.isFingerprintLockedOut, deviceEntryBiometricsAllowedInteractor.isFaceLockedOut, stateIn});
        Flow flow = new Flow() { // from class: com.android.systemui.bouncer.domain.interactor.BouncerMessageInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.bouncer.domain.interactor.BouncerMessageInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ BiometricSettingsRepositoryImpl $biometricSettingsRepository$inlined;
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ TrustRepositoryImpl $trustRepository$inlined;
                public final /* synthetic */ BouncerMessageInteractor this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.bouncer.domain.interactor.BouncerMessageInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, TrustRepositoryImpl trustRepositoryImpl, BouncerMessageInteractor bouncerMessageInteractor, BiometricSettingsRepositoryImpl biometricSettingsRepositoryImpl) {
                    this.$this_unsafeFlow = flowCollector;
                    this.$trustRepository$inlined = trustRepositoryImpl;
                    this.this$0 = bouncerMessageInteractor;
                    this.$biometricSettingsRepository$inlined = biometricSettingsRepositoryImpl;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x0043  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0034  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r17, kotlin.coroutines.Continuation r18) {
                    /*
                        Method dump skipped, instructions count: 1243
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.bouncer.domain.interactor.BouncerMessageInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = BouncerMessageInteractor$special$$inlined$combine$1.this.collect(new AnonymousClass2(flowCollector, trustRepositoryImpl, this, biometricSettingsRepositoryImpl), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        };
        this.bouncerMessage = bouncerMessageRepositoryImpl.bouncerMessage;
        keyguardUpdateMonitor.registerCallback(r6);
        FlowKt.launchIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(primaryBouncerInteractor.isShowing, flow, new AnonymousClass1(3, null))), new AnonymousClass2(null), 0), coroutineScope);
    }

    public final KeyguardSecurityModel.SecurityMode getCurrentSecurityMode() {
        return this.securityModel.getSecurityMode(this.userRepository.getSelectedUserInfo().id);
    }

    public final BouncerMessageModel getDefaultMessage() {
        Pair pair = BouncerMessageStrings.EmptyMessage;
        return BouncerMessageInteractorKt.toMessage(BouncerMessageStrings.defaultMessage(BouncerMessageInteractorKt.toAuthModel(getCurrentSecurityMode()), ((Boolean) ((StateFlowImpl) this.isFingerprintAuthCurrentlyAllowedOnBouncer.$$delegate_0).getValue()).booleanValue()));
    }

    public final void setFaceAcquisitionMessage(String str) {
        this.repository.setMessage(BouncerMessageInteractorKt.access$defaultMessage(getCurrentSecurityMode(), str, ((Boolean) ((StateFlowImpl) this.isFingerprintAuthCurrentlyAllowedOnBouncer.$$delegate_0).getValue()).booleanValue()), BiometricSourceType.FACE);
    }
}
