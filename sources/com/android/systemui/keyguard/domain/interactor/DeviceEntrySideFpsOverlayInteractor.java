package com.android.systemui.keyguard.domain.interactor;

import android.content.Context;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.bouncer.domain.interactor.AlternateBouncerInteractor;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor;
import com.android.systemui.keyguard.data.repository.DeviceEntryFingerprintAuthRepository;
import com.android.systemui.keyguard.data.repository.DeviceEntryFingerprintAuthRepositoryImpl;
import com.android.systemui.scene.domain.interactor.SceneInteractor;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.internal.ChannelLimitedFlowMerge;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeviceEntrySideFpsOverlayInteractor {
    public final Context context;
    public final FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 isBouncerSceneActive;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    public final PrimaryBouncerInteractor primaryBouncerInteractor;
    public final FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 showIndicatorForAlternateBouncer;
    public final FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 showIndicatorForDeviceEntry;
    public final FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 showIndicatorForPrimaryBouncer;

    public DeviceEntrySideFpsOverlayInteractor(CoroutineScope coroutineScope, Context context, DeviceEntryFingerprintAuthRepository deviceEntryFingerprintAuthRepository, SceneInteractor sceneInteractor, PrimaryBouncerInteractor primaryBouncerInteractor, AlternateBouncerInteractor alternateBouncerInteractor, KeyguardUpdateMonitor keyguardUpdateMonitor) {
        this.context = context;
        this.primaryBouncerInteractor = primaryBouncerInteractor;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(Boolean.FALSE);
        ReadonlyStateFlow readonlyStateFlow = primaryBouncerInteractor.isShowing;
        FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 = new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(primaryBouncerInteractor.startingDisappearAnimation);
        final ReadonlySharedFlow readonlySharedFlow = ((DeviceEntryFingerprintAuthRepositoryImpl) deviceEntryFingerprintAuthRepository).shouldUpdateIndicatorVisibility;
        final ChannelLimitedFlowMerge merge = FlowKt.merge(readonlyStateFlow, primaryBouncerInteractor.startingToHide, flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1, flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2, new Flow() { // from class: com.android.systemui.keyguard.domain.interactor.DeviceEntrySideFpsOverlayInteractor$special$$inlined$filter$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.domain.interactor.DeviceEntrySideFpsOverlayInteractor$special$$inlined$filter$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.domain.interactor.DeviceEntrySideFpsOverlayInteractor$special$$inlined$filter$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.keyguard.domain.interactor.DeviceEntrySideFpsOverlayInteractor$special$$inlined$filter$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.keyguard.domain.interactor.DeviceEntrySideFpsOverlayInteractor$special$$inlined$filter$1$2$1 r0 = (com.android.systemui.keyguard.domain.interactor.DeviceEntrySideFpsOverlayInteractor$special$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.domain.interactor.DeviceEntrySideFpsOverlayInteractor$special$$inlined$filter$1$2$1 r0 = new com.android.systemui.keyguard.domain.interactor.DeviceEntrySideFpsOverlayInteractor$special$$inlined$filter$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L46
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        r6 = r5
                        java.lang.Boolean r6 = (java.lang.Boolean) r6
                        boolean r6 = r6.booleanValue()
                        if (r6 == 0) goto L46
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L46
                        return r1
                    L46:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.DeviceEntrySideFpsOverlayInteractor$special$$inlined$filter$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = ReadonlySharedFlow.this.$$delegate_0.collect(new AnonymousClass2(flowCollector), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        });
        this.showIndicatorForDeviceEntry = new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.distinctUntilChanged(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(new Flow() { // from class: com.android.systemui.keyguard.domain.interactor.DeviceEntrySideFpsOverlayInteractor$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.domain.interactor.DeviceEntrySideFpsOverlayInteractor$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ DeviceEntrySideFpsOverlayInteractor this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.domain.interactor.DeviceEntrySideFpsOverlayInteractor$special$$inlined$map$2$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, DeviceEntrySideFpsOverlayInteractor deviceEntrySideFpsOverlayInteractor) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = deviceEntrySideFpsOverlayInteractor;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:28:0x0075 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
                    /*
                        r4 = this;
                        boolean r5 = r6 instanceof com.android.systemui.keyguard.domain.interactor.DeviceEntrySideFpsOverlayInteractor$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r5 == 0) goto L13
                        r5 = r6
                        com.android.systemui.keyguard.domain.interactor.DeviceEntrySideFpsOverlayInteractor$special$$inlined$map$2$2$1 r5 = (com.android.systemui.keyguard.domain.interactor.DeviceEntrySideFpsOverlayInteractor$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r5
                        int r0 = r5.label
                        r1 = -2147483648(0xffffffff80000000, float:-0.0)
                        r2 = r0 & r1
                        if (r2 == 0) goto L13
                        int r0 = r0 - r1
                        r5.label = r0
                        goto L18
                    L13:
                        com.android.systemui.keyguard.domain.interactor.DeviceEntrySideFpsOverlayInteractor$special$$inlined$map$2$2$1 r5 = new com.android.systemui.keyguard.domain.interactor.DeviceEntrySideFpsOverlayInteractor$special$$inlined$map$2$2$1
                        r5.<init>(r6)
                    L18:
                        java.lang.Object r6 = r5.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r1 = r5.label
                        r2 = 1
                        if (r1 == 0) goto L2f
                        if (r1 != r2) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L76
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.keyguard.domain.interactor.DeviceEntrySideFpsOverlayInteractor r6 = r4.this$0
                        com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor r1 = r6.primaryBouncerInteractor
                        boolean r3 = r1.isBouncerShowing()
                        if (r3 == 0) goto L66
                        boolean r1 = r1.isAnimatingAway()
                        if (r1 != 0) goto L66
                        android.content.Context r1 = r6.context
                        android.content.res.Resources r1 = r1.getResources()
                        r3 = 2131034191(0x7f05004f, float:1.7678893E38)
                        boolean r1 = r1.getBoolean(r3)
                        if (r1 == 0) goto L66
                        com.android.keyguard.KeyguardUpdateMonitor r6 = r6.keyguardUpdateMonitor
                        boolean r1 = r6.isFingerprintDetectionRunning()
                        if (r1 == 0) goto L66
                        r6.getClass()
                        android.hardware.biometrics.BiometricSourceType r1 = android.hardware.biometrics.BiometricSourceType.FINGERPRINT
                        boolean r6 = r6.isUnlockingWithBiometricAllowed(r1)
                        if (r6 == 0) goto L66
                        r6 = r2
                        goto L67
                    L66:
                        r6 = 0
                    L67:
                        java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)
                        r5.label = r2
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r6, r5)
                        if (r4 != r0) goto L76
                        return r0
                    L76:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.DeviceEntrySideFpsOverlayInteractor$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = ChannelLimitedFlowMerge.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }, new DeviceEntrySideFpsOverlayInteractor$showIndicatorForPrimaryBouncer$3(2, null), 0), new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(alternateBouncerInteractor.isVisible, new DeviceEntrySideFpsOverlayInteractor$showIndicatorForAlternateBouncer$1(2, null), 0), new DeviceEntrySideFpsOverlayInteractor$showIndicatorForDeviceEntry$1(3, null))), new DeviceEntrySideFpsOverlayInteractor$showIndicatorForDeviceEntry$2(2, null), 0);
    }
}
