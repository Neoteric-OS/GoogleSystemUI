package com.android.systemui.statusbar.pipeline.wifi.domain.interactor;

import com.android.systemui.statusbar.pipeline.shared.data.repository.ConnectivityRepositoryImpl;
import com.android.systemui.statusbar.pipeline.wifi.data.repository.WifiRepository;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WifiInteractorImpl {
    public final StateFlow activity;
    public final ReadonlyStateFlow areNetworksAvailable;
    public final StateFlow isDefault;
    public final StateFlow isEnabled;
    public final WifiInteractorImpl$special$$inlined$map$1 isForceHidden;
    public final WifiInteractorImpl$special$$inlined$map$1 ssid;
    public final StateFlow wifiNetwork;

    /* JADX WARN: Type inference failed for: r1v0, types: [com.android.systemui.statusbar.pipeline.wifi.domain.interactor.WifiInteractorImpl$special$$inlined$map$1] */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.statusbar.pipeline.wifi.domain.interactor.WifiInteractorImpl$special$$inlined$map$1] */
    public WifiInteractorImpl(ConnectivityRepositoryImpl connectivityRepositoryImpl, WifiRepository wifiRepository, CoroutineScope coroutineScope) {
        final StateFlow wifiNetwork = wifiRepository.getWifiNetwork();
        final int i = 0;
        this.ssid = new Flow() { // from class: com.android.systemui.statusbar.pipeline.wifi.domain.interactor.WifiInteractorImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.wifi.domain.interactor.WifiInteractorImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.wifi.domain.interactor.WifiInteractorImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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
                public final java.lang.Object emit(java.lang.Object r6, kotlin.coroutines.Continuation r7) {
                    /*
                        r5 = this;
                        boolean r0 = r7 instanceof com.android.systemui.statusbar.pipeline.wifi.domain.interactor.WifiInteractorImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.statusbar.pipeline.wifi.domain.interactor.WifiInteractorImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.wifi.domain.interactor.WifiInteractorImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.wifi.domain.interactor.WifiInteractorImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.wifi.domain.interactor.WifiInteractorImpl$special$$inlined$map$1$2$1
                        r0.<init>(r7)
                    L18:
                        java.lang.Object r7 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r7)
                        goto L68
                    L27:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r7)
                        com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel r6 = (com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel) r6
                        boolean r7 = r6 instanceof com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel.Unavailable
                        r2 = 0
                        if (r7 == 0) goto L3a
                        goto L5d
                    L3a:
                        boolean r7 = r6 instanceof com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel.Invalid
                        if (r7 == 0) goto L3f
                        goto L5d
                    L3f:
                        boolean r7 = r6 instanceof com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel.Inactive
                        if (r7 == 0) goto L44
                        goto L5d
                    L44:
                        boolean r7 = r6 instanceof com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel.CarrierMerged
                        if (r7 == 0) goto L49
                        goto L5d
                    L49:
                        boolean r7 = r6 instanceof com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel.Active
                        if (r7 == 0) goto L6b
                        com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel$Active r6 = (com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel.Active) r6
                        java.lang.String r7 = r6.ssid
                        if (r7 == 0) goto L5d
                        java.lang.String r4 = "<unknown ssid>"
                        boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r4)
                        if (r7 != 0) goto L5d
                        java.lang.String r2 = r6.ssid
                    L5d:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
                        java.lang.Object r5 = r5.emit(r2, r0)
                        if (r5 != r1) goto L68
                        return r1
                    L68:
                        kotlin.Unit r5 = kotlin.Unit.INSTANCE
                        return r5
                    L6b:
                        kotlin.NoWhenBranchMatchedException r5 = new kotlin.NoWhenBranchMatchedException
                        r5.<init>()
                        throw r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.wifi.domain.interactor.WifiInteractorImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        Object collect = wifiNetwork.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = wifiNetwork.collect(new WifiInteractorImpl$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        this.isEnabled = wifiRepository.isWifiEnabled();
        this.isDefault = wifiRepository.isWifiDefault();
        StateFlow wifiNetwork2 = wifiRepository.getWifiNetwork();
        this.wifiNetwork = wifiNetwork2;
        this.activity = wifiRepository.getWifiActivity();
        final ReadonlyStateFlow readonlyStateFlow = connectivityRepositoryImpl.forceHiddenSlots;
        final int i2 = 1;
        this.isForceHidden = new Flow() { // from class: com.android.systemui.statusbar.pipeline.wifi.domain.interactor.WifiInteractorImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.wifi.domain.interactor.WifiInteractorImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.wifi.domain.interactor.WifiInteractorImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r7 instanceof com.android.systemui.statusbar.pipeline.wifi.domain.interactor.WifiInteractorImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.statusbar.pipeline.wifi.domain.interactor.WifiInteractorImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.wifi.domain.interactor.WifiInteractorImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.wifi.domain.interactor.WifiInteractorImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.wifi.domain.interactor.WifiInteractorImpl$special$$inlined$map$1$2$1
                        r0.<init>(r7)
                    L18:
                        java.lang.Object r7 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r7)
                        goto L68
                    L27:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r7)
                        com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel r6 = (com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel) r6
                        boolean r7 = r6 instanceof com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel.Unavailable
                        r2 = 0
                        if (r7 == 0) goto L3a
                        goto L5d
                    L3a:
                        boolean r7 = r6 instanceof com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel.Invalid
                        if (r7 == 0) goto L3f
                        goto L5d
                    L3f:
                        boolean r7 = r6 instanceof com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel.Inactive
                        if (r7 == 0) goto L44
                        goto L5d
                    L44:
                        boolean r7 = r6 instanceof com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel.CarrierMerged
                        if (r7 == 0) goto L49
                        goto L5d
                    L49:
                        boolean r7 = r6 instanceof com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel.Active
                        if (r7 == 0) goto L6b
                        com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel$Active r6 = (com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel.Active) r6
                        java.lang.String r7 = r6.ssid
                        if (r7 == 0) goto L5d
                        java.lang.String r4 = "<unknown ssid>"
                        boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r4)
                        if (r7 != 0) goto L5d
                        java.lang.String r2 = r6.ssid
                    L5d:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
                        java.lang.Object r5 = r5.emit(r2, r0)
                        if (r5 != r1) goto L68
                        return r1
                    L68:
                        kotlin.Unit r5 = kotlin.Unit.INSTANCE
                        return r5
                    L6b:
                        kotlin.NoWhenBranchMatchedException r5 = new kotlin.NoWhenBranchMatchedException
                        r5.<init>()
                        throw r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.wifi.domain.interactor.WifiInteractorImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        Object collect = readonlyStateFlow.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = readonlyStateFlow.collect(new WifiInteractorImpl$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        this.areNetworksAvailable = FlowKt.stateIn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(wifiNetwork2, wifiRepository.getWifiScanResults(), new WifiInteractorImpl$areNetworksAvailable$1(this, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.FALSE);
    }
}
