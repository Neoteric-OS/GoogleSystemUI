package com.android.systemui.deviceentry.domain.interactor;

import com.android.systemui.authentication.domain.interactor.AuthenticationInteractor;
import com.android.systemui.deviceentry.data.repository.DeviceEntryRepositoryImpl;
import com.android.systemui.deviceentry.shared.model.DeviceEntryRestrictionReason;
import com.android.systemui.deviceentry.shared.model.DeviceUnlockStatus;
import com.android.systemui.flags.SystemPropertiesHelper;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.domain.interactor.TrustInteractor;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;
import kotlinx.coroutines.flow.internal.ChannelLimitedFlowMerge;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeviceUnlockedInteractor {
    public final DeviceEntryBiometricSettingsInteractor biometricSettingsInteractor;
    public final ChannelFlowTransformLatest deviceEntryRestrictionReason;
    public final ChannelLimitedFlowMerge deviceUnlockSource;
    public final ReadonlyStateFlow deviceUnlockStatus;
    public final StateFlow faceEnrolledAndEnabled;
    public final FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 faceOrFingerprintOrTrustEnabled;
    public final StateFlow fingerprintEnrolledAndEnabled;
    public final DeviceUnlockedInteractor$special$$inlined$map$2 isInLockdown;
    public final PowerInteractor powerInteractor;
    public final SystemPropertiesHelper systemPropertiesHelper;
    public final ReadonlyStateFlow trustAgentEnabled;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DeviceEntryRestrictionReason.values().length];
            try {
                DeviceEntryRestrictionReason deviceEntryRestrictionReason = DeviceEntryRestrictionReason.UserLockdown;
                iArr[0] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                DeviceEntryRestrictionReason deviceEntryRestrictionReason2 = DeviceEntryRestrictionReason.UserLockdown;
                iArr[3] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                DeviceEntryRestrictionReason deviceEntryRestrictionReason3 = DeviceEntryRestrictionReason.UserLockdown;
                iArr[1] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                DeviceEntryRestrictionReason deviceEntryRestrictionReason4 = DeviceEntryRestrictionReason.UserLockdown;
                iArr[11] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                DeviceEntryRestrictionReason deviceEntryRestrictionReason5 = DeviceEntryRestrictionReason.UserLockdown;
                iArr[10] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                DeviceEntryRestrictionReason deviceEntryRestrictionReason6 = DeviceEntryRestrictionReason.UserLockdown;
                iArr[8] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                DeviceEntryRestrictionReason deviceEntryRestrictionReason7 = DeviceEntryRestrictionReason.UserLockdown;
                iArr[9] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                DeviceEntryRestrictionReason deviceEntryRestrictionReason8 = DeviceEntryRestrictionReason.UserLockdown;
                iArr[6] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                DeviceEntryRestrictionReason deviceEntryRestrictionReason9 = DeviceEntryRestrictionReason.UserLockdown;
                iArr[5] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                DeviceEntryRestrictionReason deviceEntryRestrictionReason10 = DeviceEntryRestrictionReason.UserLockdown;
                iArr[2] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                DeviceEntryRestrictionReason deviceEntryRestrictionReason11 = DeviceEntryRestrictionReason.UserLockdown;
                iArr[4] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                DeviceEntryRestrictionReason deviceEntryRestrictionReason12 = DeviceEntryRestrictionReason.UserLockdown;
                iArr[7] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Type inference failed for: r1v7, types: [com.android.systemui.deviceentry.domain.interactor.DeviceUnlockedInteractor$special$$inlined$map$2] */
    public DeviceUnlockedInteractor(CoroutineScope coroutineScope, AuthenticationInteractor authenticationInteractor, final DeviceEntryRepositoryImpl deviceEntryRepositoryImpl, TrustInteractor trustInteractor, DeviceEntryFaceAuthInteractor deviceEntryFaceAuthInteractor, DeviceEntryFingerprintAuthInteractor deviceEntryFingerprintAuthInteractor, PowerInteractor powerInteractor, DeviceEntryBiometricSettingsInteractor deviceEntryBiometricSettingsInteractor, SystemPropertiesHelper systemPropertiesHelper, KeyguardTransitionInteractor keyguardTransitionInteractor) {
        this.powerInteractor = powerInteractor;
        this.biometricSettingsInteractor = deviceEntryBiometricSettingsInteractor;
        this.systemPropertiesHelper = systemPropertiesHelper;
        DeviceUnlockedInteractor$special$$inlined$map$1 deviceUnlockedInteractor$special$$inlined$map$1 = new DeviceUnlockedInteractor$special$$inlined$map$1(deviceEntryFingerprintAuthInteractor.fingerprintSuccess, 0);
        final DeviceUnlockedInteractor$special$$inlined$map$1 deviceUnlockedInteractor$special$$inlined$map$12 = new DeviceUnlockedInteractor$special$$inlined$map$1(deviceEntryFaceAuthInteractor.isAuthenticated(), 4);
        final int i = 0;
        this.deviceUnlockSource = FlowKt.merge(deviceUnlockedInteractor$special$$inlined$map$1, new Flow() { // from class: com.android.systemui.deviceentry.domain.interactor.DeviceUnlockedInteractor$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.deviceentry.domain.interactor.DeviceUnlockedInteractor$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ DeviceEntryRepositoryImpl $deviceEntryRepository$inlined;
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.deviceentry.domain.interactor.DeviceUnlockedInteractor$special$$inlined$map$2$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, DeviceEntryRepositoryImpl deviceEntryRepositoryImpl) {
                    this.$this_unsafeFlow = flowCollector;
                    this.$deviceEntryRepository$inlined = deviceEntryRepositoryImpl;
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
                        boolean r0 = r6 instanceof com.android.systemui.deviceentry.domain.interactor.DeviceUnlockedInteractor$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.deviceentry.domain.interactor.DeviceUnlockedInteractor$special$$inlined$map$2$2$1 r0 = (com.android.systemui.deviceentry.domain.interactor.DeviceUnlockedInteractor$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.deviceentry.domain.interactor.DeviceUnlockedInteractor$special$$inlined$map$2$2$1 r0 = new com.android.systemui.deviceentry.domain.interactor.DeviceUnlockedInteractor$special$$inlined$map$2$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L57
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.lang.Boolean r5 = (java.lang.Boolean) r5
                        r5.getClass()
                        com.android.systemui.deviceentry.data.repository.DeviceEntryRepositoryImpl r5 = r4.$deviceEntryRepository$inlined
                        kotlinx.coroutines.flow.ReadonlyStateFlow r5 = r5.isBypassEnabled
                        java.lang.Object r5 = r5.getValue()
                        java.lang.Boolean r5 = (java.lang.Boolean) r5
                        boolean r5 = r5.booleanValue()
                        if (r5 == 0) goto L4a
                        com.android.systemui.deviceentry.shared.model.DeviceUnlockSource$FaceWithBypass r5 = com.android.systemui.deviceentry.shared.model.DeviceUnlockSource.FaceWithBypass.INSTANCE
                        goto L4c
                    L4a:
                        com.android.systemui.deviceentry.shared.model.DeviceUnlockSource$FaceWithoutBypass r5 = com.android.systemui.deviceentry.shared.model.DeviceUnlockSource.FaceWithoutBypass.INSTANCE
                    L4c:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L57
                        return r1
                    L57:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.deviceentry.domain.interactor.DeviceUnlockedInteractor$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        Object collect = ((DeviceUnlockedInteractor$special$$inlined$map$1) deviceUnlockedInteractor$special$$inlined$map$12).collect(new AnonymousClass2(flowCollector, (DeviceEntryRepositoryImpl) deviceEntryRepositoryImpl), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = ((ChannelFlowTransformLatest) deviceUnlockedInteractor$special$$inlined$map$12).collect(new DeviceUnlockedInteractor$special$$inlined$map$5$2(flowCollector, (DeviceUnlockedInteractor) deviceEntryRepositoryImpl), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, new DeviceUnlockedInteractor$special$$inlined$map$1(new DeviceUnlockedInteractor$special$$inlined$map$1(trustInteractor.isTrusted, 5), 7), new DeviceUnlockedInteractor$special$$inlined$map$1(new DeviceUnlockedInteractor$special$$inlined$map$1(authenticationInteractor.onAuthenticationResult, 6), 8));
        final ChannelFlowTransformLatest transformLatest = FlowKt.transformLatest(FlowKt.combine(deviceEntryBiometricSettingsInteractor.isFaceAuthEnrolledAndEnabled, deviceEntryBiometricSettingsInteractor.isFingerprintAuthEnrolledAndEnabled, trustInteractor.isEnrolledAndEnabled, DeviceUnlockedInteractor$faceOrFingerprintOrTrustEnabled$2.INSTANCE), new DeviceUnlockedInteractor$special$$inlined$flatMapLatest$1(null, this, deviceEntryFaceAuthInteractor, deviceEntryFingerprintAuthInteractor, trustInteractor));
        final int i2 = 1;
        this.isInLockdown = new Flow() { // from class: com.android.systemui.deviceentry.domain.interactor.DeviceUnlockedInteractor$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.deviceentry.domain.interactor.DeviceUnlockedInteractor$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ DeviceEntryRepositoryImpl $deviceEntryRepository$inlined;
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.deviceentry.domain.interactor.DeviceUnlockedInteractor$special$$inlined$map$2$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, DeviceEntryRepositoryImpl deviceEntryRepositoryImpl) {
                    this.$this_unsafeFlow = flowCollector;
                    this.$deviceEntryRepository$inlined = deviceEntryRepositoryImpl;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r6 instanceof com.android.systemui.deviceentry.domain.interactor.DeviceUnlockedInteractor$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.deviceentry.domain.interactor.DeviceUnlockedInteractor$special$$inlined$map$2$2$1 r0 = (com.android.systemui.deviceentry.domain.interactor.DeviceUnlockedInteractor$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.deviceentry.domain.interactor.DeviceUnlockedInteractor$special$$inlined$map$2$2$1 r0 = new com.android.systemui.deviceentry.domain.interactor.DeviceUnlockedInteractor$special$$inlined$map$2$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L57
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.lang.Boolean r5 = (java.lang.Boolean) r5
                        r5.getClass()
                        com.android.systemui.deviceentry.data.repository.DeviceEntryRepositoryImpl r5 = r4.$deviceEntryRepository$inlined
                        kotlinx.coroutines.flow.ReadonlyStateFlow r5 = r5.isBypassEnabled
                        java.lang.Object r5 = r5.getValue()
                        java.lang.Boolean r5 = (java.lang.Boolean) r5
                        boolean r5 = r5.booleanValue()
                        if (r5 == 0) goto L4a
                        com.android.systemui.deviceentry.shared.model.DeviceUnlockSource$FaceWithBypass r5 = com.android.systemui.deviceentry.shared.model.DeviceUnlockSource.FaceWithBypass.INSTANCE
                        goto L4c
                    L4a:
                        com.android.systemui.deviceentry.shared.model.DeviceUnlockSource$FaceWithoutBypass r5 = com.android.systemui.deviceentry.shared.model.DeviceUnlockSource.FaceWithoutBypass.INSTANCE
                    L4c:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L57
                        return r1
                    L57:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.deviceentry.domain.interactor.DeviceUnlockedInteractor$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        Object collect = ((DeviceUnlockedInteractor$special$$inlined$map$1) transformLatest).collect(new AnonymousClass2(flowCollector, (DeviceEntryRepositoryImpl) this), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = ((ChannelFlowTransformLatest) transformLatest).collect(new DeviceUnlockedInteractor$special$$inlined$map$5$2(flowCollector, (DeviceUnlockedInteractor) this), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        this.deviceUnlockStatus = FlowKt.stateIn(FlowKt.transformLatest(authenticationInteractor.authenticationMethod, new DeviceUnlockedInteractor$special$$inlined$flatMapLatest$2(null, this, keyguardTransitionInteractor)), coroutineScope, SharingStarted.Companion.Eagerly, new DeviceUnlockStatus(false, null));
    }
}
