package com.android.systemui.biometrics.data.repository;

import android.hardware.biometrics.BiometricManager;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BiometricStatusRepositoryImpl {
    public final ReadonlySharedFlow authenticationState;
    public final BiometricManager biometricManager;
    public final BiometricStatusRepositoryImpl$special$$inlined$map$2 fingerprintAcquiredStatus;
    public final FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 fingerprintAuthenticationReason;
    public final FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 fingerprintAuthenticationState;
    public final FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 fingerprintRunningState;

    /* JADX WARN: Type inference failed for: r7v10, types: [com.android.systemui.biometrics.data.repository.BiometricStatusRepositoryImpl$special$$inlined$map$2] */
    public BiometricStatusRepositoryImpl(CoroutineScope coroutineScope, BiometricManager biometricManager) {
        this.biometricManager = biometricManager;
        final ReadonlySharedFlow shareIn = FlowKt.shareIn(FlowKt.distinctUntilChanged(FlowKt.callbackFlow(new BiometricStatusRepositoryImpl$authenticationState$1(this, null))), coroutineScope, SharingStarted.Companion.Eagerly, 1);
        final int i = 1;
        FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 flowKt__TransformKt$onEach$$inlined$unsafeTransform$1 = new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(new Flow() { // from class: com.android.systemui.biometrics.data.repository.BiometricStatusRepositoryImpl$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.biometrics.data.repository.BiometricStatusRepositoryImpl$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.biometrics.data.repository.BiometricStatusRepositoryImpl$special$$inlined$map$2$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.biometrics.data.repository.BiometricStatusRepositoryImpl$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.biometrics.data.repository.BiometricStatusRepositoryImpl$special$$inlined$map$2$2$1 r0 = (com.android.systemui.biometrics.data.repository.BiometricStatusRepositoryImpl$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.biometrics.data.repository.BiometricStatusRepositoryImpl$special$$inlined$map$2$2$1 r0 = new com.android.systemui.biometrics.data.repository.BiometricStatusRepositoryImpl$special$$inlined$map$2$2$1
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
                        com.android.systemui.biometrics.shared.model.AuthenticationState$Acquired r5 = (com.android.systemui.biometrics.shared.model.AuthenticationState.Acquired) r5
                        com.android.systemui.keyguard.shared.model.AcquiredFingerprintAuthenticationStatus r6 = new com.android.systemui.keyguard.shared.model.AcquiredFingerprintAuthenticationStatus
                        com.android.systemui.biometrics.shared.model.AuthenticationReason r2 = r5.requestReason
                        int r5 = r5.acquiredInfo
                        r6.<init>(r2, r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r6, r0)
                        if (r4 != r1) goto L48
                        return r1
                    L48:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.data.repository.BiometricStatusRepositoryImpl$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        Object collect = ((BiometricStatusRepositoryImpl$special$$inlined$map$1) shareIn).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = ((ReadonlySharedFlow) shareIn).$$delegate_0.collect(new BiometricStatusRepositoryImpl$special$$inlined$filter$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, new BiometricStatusRepositoryImpl$fingerprintAuthenticationState$2(2, null), 0);
        this.fingerprintAuthenticationReason = new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(new BiometricStatusRepositoryImpl$special$$inlined$map$1(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(new BiometricStatusRepositoryImpl$special$$inlined$map$1(flowKt__TransformKt$onEach$$inlined$unsafeTransform$1, 1), new BiometricStatusRepositoryImpl$fingerprintRunningState$2(2, null), 0), 0), new BiometricStatusRepositoryImpl$fingerprintAuthenticationReason$2(2, null), 0);
        final BiometricStatusRepositoryImpl$special$$inlined$map$1 biometricStatusRepositoryImpl$special$$inlined$map$1 = new BiometricStatusRepositoryImpl$special$$inlined$map$1(flowKt__TransformKt$onEach$$inlined$unsafeTransform$1, 2);
        final int i2 = 0;
        this.fingerprintAcquiredStatus = new Flow() { // from class: com.android.systemui.biometrics.data.repository.BiometricStatusRepositoryImpl$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.biometrics.data.repository.BiometricStatusRepositoryImpl$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.biometrics.data.repository.BiometricStatusRepositoryImpl$special$$inlined$map$2$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.biometrics.data.repository.BiometricStatusRepositoryImpl$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.biometrics.data.repository.BiometricStatusRepositoryImpl$special$$inlined$map$2$2$1 r0 = (com.android.systemui.biometrics.data.repository.BiometricStatusRepositoryImpl$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.biometrics.data.repository.BiometricStatusRepositoryImpl$special$$inlined$map$2$2$1 r0 = new com.android.systemui.biometrics.data.repository.BiometricStatusRepositoryImpl$special$$inlined$map$2$2$1
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
                        com.android.systemui.biometrics.shared.model.AuthenticationState$Acquired r5 = (com.android.systemui.biometrics.shared.model.AuthenticationState.Acquired) r5
                        com.android.systemui.keyguard.shared.model.AcquiredFingerprintAuthenticationStatus r6 = new com.android.systemui.keyguard.shared.model.AcquiredFingerprintAuthenticationStatus
                        com.android.systemui.biometrics.shared.model.AuthenticationReason r2 = r5.requestReason
                        int r5 = r5.acquiredInfo
                        r6.<init>(r2, r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r6, r0)
                        if (r4 != r1) goto L48
                        return r1
                    L48:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.data.repository.BiometricStatusRepositoryImpl$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        Object collect = ((BiometricStatusRepositoryImpl$special$$inlined$map$1) biometricStatusRepositoryImpl$special$$inlined$map$1).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = ((ReadonlySharedFlow) biometricStatusRepositoryImpl$special$$inlined$map$1).$$delegate_0.collect(new BiometricStatusRepositoryImpl$special$$inlined$filter$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
    }
}
