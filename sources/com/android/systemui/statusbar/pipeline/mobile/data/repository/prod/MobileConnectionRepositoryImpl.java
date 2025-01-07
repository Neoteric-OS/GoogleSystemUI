package com.android.systemui.statusbar.pipeline.mobile.data.repository.prod;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkRequest;
import android.telephony.TelephonyManager;
import androidx.compose.runtime.collection.MutableVectorKt$$ExternalSyntheticOutline0;
import com.android.app.viewcapture.data.ViewNode;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.flags.FeatureFlagsClassic;
import com.android.systemui.flags.FeatureFlagsClassicRelease;
import com.android.systemui.flags.Flags;
import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger;
import com.android.systemui.statusbar.pipeline.mobile.data.model.DataConnectionState;
import com.android.systemui.statusbar.pipeline.mobile.data.model.NetworkNameModel;
import com.android.systemui.statusbar.pipeline.mobile.data.model.ResolvedNetworkType;
import com.android.systemui.statusbar.pipeline.mobile.data.model.SystemUiCarrierConfig;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.CarrierConfigRepository;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository;
import com.android.systemui.statusbar.pipeline.mobile.util.MobileMappingsProxyImpl;
import com.android.systemui.statusbar.pipeline.shared.data.model.DataActivityModel;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import java.util.Collections;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedWhileSubscribed;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MobileConnectionRepositoryImpl implements MobileConnectionRepository {
    public final ReadonlyStateFlow allowNetworkSliceIndicator;
    public final CoroutineDispatcher bgDispatcher;
    public final ReadonlyStateFlow callbackEvents;
    public final ReadonlyStateFlow carrierId;
    public final ReadonlyStateFlow carrierName;
    public final ReadonlyStateFlow carrierNetworkChangeActive;
    public final ReadonlyStateFlow cdmaLevel;
    public final ReadonlyStateFlow cdmaRoaming;
    public final Context context;
    public final ReadonlyStateFlow dataActivityDirection;
    public final ReadonlyStateFlow dataConnectionState;
    public final ReadonlyStateFlow dataEnabled;
    public final ReadonlyStateFlow hasPrioritizedNetworkCapabilities;
    public final ReadonlyStateFlow inflateSignalStrength;
    public final ReadonlyStateFlow isAllowedDuringAirplaneMode;
    public final ReadonlyStateFlow isEmergencyOnly;
    public final ReadonlyStateFlow isGsm;
    public final ReadonlyStateFlow isInService;
    public final ReadonlyStateFlow isNonTerrestrial;
    public final ReadonlyStateFlow isRoaming;
    public final MobileMappingsProxyImpl mobileMappingsProxy;
    public final ReadonlyStateFlow networkName;
    public final NetworkRequest networkSliceRequest;
    public final ReadonlyStateFlow numberOfLevels;
    public final ReadonlyStateFlow operatorAlphaShort;
    public final ReadonlyStateFlow primaryLevel;
    public final ReadonlyStateFlow resolvedNetworkType;
    public final int subId;
    public final TableLogBuffer tableLogBuffer;
    public final TelephonyManager telephonyManager;
    public final MobileConnectionRepositoryImpl$special$$inlined$map$14 telephonyPollingEvent;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory {
        public final CoroutineDispatcher bgDispatcher;
        public final BroadcastDispatcher broadcastDispatcher;
        public final CarrierConfigRepository carrierConfigRepository;
        public final ConnectivityManager connectivityManager;
        public final Context context;
        public final FeatureFlagsClassic flags;
        public final MobileInputLogger logger;
        public final MobileMappingsProxyImpl mobileMappingsProxy;
        public final CoroutineScope scope;
        public final TelephonyManager telephonyManager;

        public Factory(Context context, BroadcastDispatcher broadcastDispatcher, ConnectivityManager connectivityManager, TelephonyManager telephonyManager, MobileInputLogger mobileInputLogger, CarrierConfigRepository carrierConfigRepository, MobileMappingsProxyImpl mobileMappingsProxyImpl, FeatureFlagsClassic featureFlagsClassic, CoroutineDispatcher coroutineDispatcher, CoroutineScope coroutineScope) {
            this.context = context;
            this.broadcastDispatcher = broadcastDispatcher;
            this.connectivityManager = connectivityManager;
            this.telephonyManager = telephonyManager;
            this.logger = mobileInputLogger;
            this.carrierConfigRepository = carrierConfigRepository;
            this.mobileMappingsProxy = mobileMappingsProxyImpl;
            this.flags = featureFlagsClassic;
            this.bgDispatcher = coroutineDispatcher;
            this.scope = coroutineScope;
        }
    }

    public MobileConnectionRepositoryImpl(int i, Context context, Flow flow, NetworkNameModel networkNameModel, String str, ConnectivityManager connectivityManager, TelephonyManager telephonyManager, SystemUiCarrierConfig systemUiCarrierConfig, BroadcastDispatcher broadcastDispatcher, MobileMappingsProxyImpl mobileMappingsProxyImpl, CoroutineDispatcher coroutineDispatcher, MobileInputLogger mobileInputLogger, TableLogBuffer tableLogBuffer, FeatureFlagsClassic featureFlagsClassic, CoroutineScope coroutineScope) {
        Flow flow2;
        this.subId = i;
        this.context = context;
        this.telephonyManager = telephonyManager;
        this.mobileMappingsProxy = mobileMappingsProxyImpl;
        this.bgDispatcher = coroutineDispatcher;
        this.tableLogBuffer = tableLogBuffer;
        if (telephonyManager.getSubscriptionId() != i) {
            throw new IllegalStateException(MutableVectorKt$$ExternalSyntheticOutline0.m(i, telephonyManager.getSubscriptionId(), "MobileRepo: TelephonyManager should be created with subId(", "). Found ", " instead."));
        }
        TelephonyCallbackState telephonyCallbackState = new TelephonyCallbackState(null, null, null, null, null, null, null, null);
        ReadonlyStateFlow stateIn = FlowKt.stateIn(new FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1(telephonyCallbackState, new MobileConnectionRepositoryImpl$callbackEvents$1$2(3, null), FlowKt.flowOn(FlowKt.callbackFlow(new MobileConnectionRepositoryImpl$callbackEvents$1$1(this, mobileInputLogger, null)), coroutineDispatcher)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), telephonyCallbackState);
        final MobileConnectionRepositoryImpl$special$$inlined$map$14 mobileConnectionRepositoryImpl$special$$inlined$map$14 = new MobileConnectionRepositoryImpl$special$$inlined$map$14(stateIn, 7);
        final int i2 = 0;
        Flow flow3 = new Flow() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L49
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CallbackEvent$OnServiceStateChanged r5 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CallbackEvent.OnServiceStateChanged) r5
                        android.telephony.ServiceState r5 = r5.serviceState
                        boolean r5 = r5.isEmergencyOnly()
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L49
                        return r1
                    L49:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        Object collect = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$14).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$14).collect(new MobileConnectionRepositoryImpl$dataEnabled$lambda$36$$inlined$map$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$14).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$10$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 3:
                        Object collect4 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$14).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$11$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 4:
                        Object collect5 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$14).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$12$2(flowCollector), continuation);
                        if (collect5 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 5:
                        Object collect6 = ((MobileConnectionRepositoryImpl$special$$inlined$map$13) mobileConnectionRepositoryImpl$special$$inlined$map$14).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$17$2(flowCollector), continuation);
                        if (collect6 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 6:
                        Object collect7 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$14).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect7 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 7:
                        Object collect8 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$14).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect8 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 8:
                        Object collect9 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$14).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$4$2(flowCollector), continuation);
                        if (collect9 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 9:
                        Object collect10 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$14).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$5$2(flowCollector), continuation);
                        if (collect10 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 10:
                        Object collect11 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$14).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$6$2(flowCollector), continuation);
                        if (collect11 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        Object collect12 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$14).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$7$2(flowCollector), continuation);
                        if (collect12 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        Object collect13 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$14).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$8$2(flowCollector), continuation);
                        if (collect13 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect14 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$14).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$9$2(flowCollector), continuation);
                        if (collect14 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        StartedWhileSubscribed WhileSubscribed$default = SharingStarted.Companion.WhileSubscribed$default(3);
        Boolean bool = Boolean.FALSE;
        this.isEmergencyOnly = FlowKt.stateIn(flow3, coroutineScope, WhileSubscribed$default, bool);
        if (((FeatureFlagsClassicRelease) featureFlagsClassic).isEnabled(Flags.ROAMING_INDICATOR_VIA_DISPLAY_INFO)) {
            final MobileConnectionRepositoryImpl$special$$inlined$map$14 mobileConnectionRepositoryImpl$special$$inlined$map$142 = new MobileConnectionRepositoryImpl$special$$inlined$map$14(stateIn, 8);
            final int i3 = 6;
            flow2 = new Flow() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
                public final class AnonymousClass2 implements FlowCollector {
                    public final /* synthetic */ FlowCollector $this_unsafeFlow;

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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
                            boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r6
                            com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1
                            r0.<init>(r6)
                        L18:
                            java.lang.Object r6 = r0.result
                            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                            int r2 = r0.label
                            r3 = 1
                            if (r2 == 0) goto L2f
                            if (r2 != r3) goto L27
                            kotlin.ResultKt.throwOnFailure(r6)
                            goto L49
                        L27:
                            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                            r4.<init>(r5)
                            throw r4
                        L2f:
                            kotlin.ResultKt.throwOnFailure(r6)
                            com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CallbackEvent$OnServiceStateChanged r5 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CallbackEvent.OnServiceStateChanged) r5
                            android.telephony.ServiceState r5 = r5.serviceState
                            boolean r5 = r5.isEmergencyOnly()
                            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                            r0.label = r3
                            kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                            java.lang.Object r4 = r4.emit(r5, r0)
                            if (r4 != r1) goto L49
                            return r1
                        L49:
                            kotlin.Unit r4 = kotlin.Unit.INSTANCE
                            return r4
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                    switch (i3) {
                        case 0:
                            Object collect = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$142).collect(new AnonymousClass2(flowCollector), continuation);
                            if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                        case 1:
                            Object collect2 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$142).collect(new MobileConnectionRepositoryImpl$dataEnabled$lambda$36$$inlined$map$1$2(flowCollector), continuation);
                            if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                        case 2:
                            Object collect3 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$142).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$10$2(flowCollector), continuation);
                            if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                        case 3:
                            Object collect4 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$142).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$11$2(flowCollector), continuation);
                            if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                        case 4:
                            Object collect5 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$142).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$12$2(flowCollector), continuation);
                            if (collect5 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                        case 5:
                            Object collect6 = ((MobileConnectionRepositoryImpl$special$$inlined$map$13) mobileConnectionRepositoryImpl$special$$inlined$map$142).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$17$2(flowCollector), continuation);
                            if (collect6 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                        case 6:
                            Object collect7 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$142).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$2$2(flowCollector), continuation);
                            if (collect7 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                        case 7:
                            Object collect8 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$142).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$3$2(flowCollector), continuation);
                            if (collect8 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                        case 8:
                            Object collect9 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$142).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$4$2(flowCollector), continuation);
                            if (collect9 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                        case 9:
                            Object collect10 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$142).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$5$2(flowCollector), continuation);
                            if (collect10 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                        case 10:
                            Object collect11 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$142).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$6$2(flowCollector), continuation);
                            if (collect11 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                        case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                            Object collect12 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$142).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$7$2(flowCollector), continuation);
                            if (collect12 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                        case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                            Object collect13 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$142).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$8$2(flowCollector), continuation);
                            if (collect13 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                        default:
                            Object collect14 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$142).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$9$2(flowCollector), continuation);
                            if (collect14 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                    }
                    return Unit.INSTANCE;
                }
            };
        } else {
            final MobileConnectionRepositoryImpl$special$$inlined$map$14 mobileConnectionRepositoryImpl$special$$inlined$map$143 = new MobileConnectionRepositoryImpl$special$$inlined$map$14(stateIn, 9);
            final int i4 = 7;
            flow2 = new Flow() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
                public final class AnonymousClass2 implements FlowCollector {
                    public final /* synthetic */ FlowCollector $this_unsafeFlow;

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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
                            boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r6
                            com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1
                            r0.<init>(r6)
                        L18:
                            java.lang.Object r6 = r0.result
                            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                            int r2 = r0.label
                            r3 = 1
                            if (r2 == 0) goto L2f
                            if (r2 != r3) goto L27
                            kotlin.ResultKt.throwOnFailure(r6)
                            goto L49
                        L27:
                            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                            r4.<init>(r5)
                            throw r4
                        L2f:
                            kotlin.ResultKt.throwOnFailure(r6)
                            com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CallbackEvent$OnServiceStateChanged r5 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CallbackEvent.OnServiceStateChanged) r5
                            android.telephony.ServiceState r5 = r5.serviceState
                            boolean r5 = r5.isEmergencyOnly()
                            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                            r0.label = r3
                            kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                            java.lang.Object r4 = r4.emit(r5, r0)
                            if (r4 != r1) goto L49
                            return r1
                        L49:
                            kotlin.Unit r4 = kotlin.Unit.INSTANCE
                            return r4
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                    switch (i4) {
                        case 0:
                            Object collect = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$143).collect(new AnonymousClass2(flowCollector), continuation);
                            if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                        case 1:
                            Object collect2 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$143).collect(new MobileConnectionRepositoryImpl$dataEnabled$lambda$36$$inlined$map$1$2(flowCollector), continuation);
                            if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                        case 2:
                            Object collect3 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$143).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$10$2(flowCollector), continuation);
                            if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                        case 3:
                            Object collect4 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$143).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$11$2(flowCollector), continuation);
                            if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                        case 4:
                            Object collect5 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$143).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$12$2(flowCollector), continuation);
                            if (collect5 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                        case 5:
                            Object collect6 = ((MobileConnectionRepositoryImpl$special$$inlined$map$13) mobileConnectionRepositoryImpl$special$$inlined$map$143).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$17$2(flowCollector), continuation);
                            if (collect6 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                        case 6:
                            Object collect7 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$143).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$2$2(flowCollector), continuation);
                            if (collect7 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                        case 7:
                            Object collect8 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$143).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$3$2(flowCollector), continuation);
                            if (collect8 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                        case 8:
                            Object collect9 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$143).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$4$2(flowCollector), continuation);
                            if (collect9 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                        case 9:
                            Object collect10 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$143).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$5$2(flowCollector), continuation);
                            if (collect10 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                        case 10:
                            Object collect11 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$143).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$6$2(flowCollector), continuation);
                            if (collect11 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                        case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                            Object collect12 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$143).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$7$2(flowCollector), continuation);
                            if (collect12 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                        case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                            Object collect13 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$143).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$8$2(flowCollector), continuation);
                            if (collect13 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                        default:
                            Object collect14 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$143).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$9$2(flowCollector), continuation);
                            if (collect14 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                    }
                    return Unit.INSTANCE;
                }
            };
        }
        this.isRoaming = FlowKt.stateIn(flow2, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        final MobileConnectionRepositoryImpl$special$$inlined$map$14 mobileConnectionRepositoryImpl$special$$inlined$map$144 = new MobileConnectionRepositoryImpl$special$$inlined$map$14(stateIn, 10);
        final int i5 = 8;
        this.operatorAlphaShort = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L49
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CallbackEvent$OnServiceStateChanged r5 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CallbackEvent.OnServiceStateChanged) r5
                        android.telephony.ServiceState r5 = r5.serviceState
                        boolean r5 = r5.isEmergencyOnly()
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L49
                        return r1
                    L49:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i5) {
                    case 0:
                        Object collect = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$144).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$144).collect(new MobileConnectionRepositoryImpl$dataEnabled$lambda$36$$inlined$map$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$144).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$10$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 3:
                        Object collect4 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$144).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$11$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 4:
                        Object collect5 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$144).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$12$2(flowCollector), continuation);
                        if (collect5 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 5:
                        Object collect6 = ((MobileConnectionRepositoryImpl$special$$inlined$map$13) mobileConnectionRepositoryImpl$special$$inlined$map$144).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$17$2(flowCollector), continuation);
                        if (collect6 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 6:
                        Object collect7 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$144).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect7 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 7:
                        Object collect8 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$144).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect8 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 8:
                        Object collect9 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$144).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$4$2(flowCollector), continuation);
                        if (collect9 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 9:
                        Object collect10 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$144).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$5$2(flowCollector), continuation);
                        if (collect10 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 10:
                        Object collect11 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$144).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$6$2(flowCollector), continuation);
                        if (collect11 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        Object collect12 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$144).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$7$2(flowCollector), continuation);
                        if (collect12 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        Object collect13 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$144).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$8$2(flowCollector), continuation);
                        if (collect13 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect14 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$144).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$9$2(flowCollector), continuation);
                        if (collect14 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), null);
        final MobileConnectionRepositoryImpl$special$$inlined$map$14 mobileConnectionRepositoryImpl$special$$inlined$map$145 = new MobileConnectionRepositoryImpl$special$$inlined$map$14(stateIn, 11);
        final int i6 = 9;
        this.isInService = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L49
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CallbackEvent$OnServiceStateChanged r5 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CallbackEvent.OnServiceStateChanged) r5
                        android.telephony.ServiceState r5 = r5.serviceState
                        boolean r5 = r5.isEmergencyOnly()
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L49
                        return r1
                    L49:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i6) {
                    case 0:
                        Object collect = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$145).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$145).collect(new MobileConnectionRepositoryImpl$dataEnabled$lambda$36$$inlined$map$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$145).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$10$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 3:
                        Object collect4 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$145).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$11$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 4:
                        Object collect5 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$145).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$12$2(flowCollector), continuation);
                        if (collect5 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 5:
                        Object collect6 = ((MobileConnectionRepositoryImpl$special$$inlined$map$13) mobileConnectionRepositoryImpl$special$$inlined$map$145).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$17$2(flowCollector), continuation);
                        if (collect6 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 6:
                        Object collect7 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$145).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect7 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 7:
                        Object collect8 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$145).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect8 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 8:
                        Object collect9 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$145).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$4$2(flowCollector), continuation);
                        if (collect9 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 9:
                        Object collect10 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$145).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$5$2(flowCollector), continuation);
                        if (collect10 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 10:
                        Object collect11 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$145).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$6$2(flowCollector), continuation);
                        if (collect11 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        Object collect12 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$145).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$7$2(flowCollector), continuation);
                        if (collect12 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        Object collect13 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$145).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$8$2(flowCollector), continuation);
                        if (collect13 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect14 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$145).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$9$2(flowCollector), continuation);
                        if (collect14 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        final MobileConnectionRepositoryImpl$special$$inlined$map$14 mobileConnectionRepositoryImpl$special$$inlined$map$146 = new MobileConnectionRepositoryImpl$special$$inlined$map$14(stateIn, 12);
        final int i7 = 10;
        this.isNonTerrestrial = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L49
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CallbackEvent$OnServiceStateChanged r5 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CallbackEvent.OnServiceStateChanged) r5
                        android.telephony.ServiceState r5 = r5.serviceState
                        boolean r5 = r5.isEmergencyOnly()
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L49
                        return r1
                    L49:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i7) {
                    case 0:
                        Object collect = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$146).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$146).collect(new MobileConnectionRepositoryImpl$dataEnabled$lambda$36$$inlined$map$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$146).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$10$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 3:
                        Object collect4 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$146).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$11$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 4:
                        Object collect5 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$146).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$12$2(flowCollector), continuation);
                        if (collect5 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 5:
                        Object collect6 = ((MobileConnectionRepositoryImpl$special$$inlined$map$13) mobileConnectionRepositoryImpl$special$$inlined$map$146).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$17$2(flowCollector), continuation);
                        if (collect6 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 6:
                        Object collect7 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$146).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect7 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 7:
                        Object collect8 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$146).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect8 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 8:
                        Object collect9 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$146).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$4$2(flowCollector), continuation);
                        if (collect9 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 9:
                        Object collect10 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$146).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$5$2(flowCollector), continuation);
                        if (collect10 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 10:
                        Object collect11 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$146).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$6$2(flowCollector), continuation);
                        if (collect11 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        Object collect12 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$146).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$7$2(flowCollector), continuation);
                        if (collect12 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        Object collect13 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$146).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$8$2(flowCollector), continuation);
                        if (collect13 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect14 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$146).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$9$2(flowCollector), continuation);
                        if (collect14 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        final MobileConnectionRepositoryImpl$special$$inlined$map$14 mobileConnectionRepositoryImpl$special$$inlined$map$147 = new MobileConnectionRepositoryImpl$special$$inlined$map$14(stateIn, 13);
        final int i8 = 11;
        this.isGsm = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L49
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CallbackEvent$OnServiceStateChanged r5 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CallbackEvent.OnServiceStateChanged) r5
                        android.telephony.ServiceState r5 = r5.serviceState
                        boolean r5 = r5.isEmergencyOnly()
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L49
                        return r1
                    L49:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i8) {
                    case 0:
                        Object collect = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$147).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$147).collect(new MobileConnectionRepositoryImpl$dataEnabled$lambda$36$$inlined$map$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$147).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$10$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 3:
                        Object collect4 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$147).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$11$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 4:
                        Object collect5 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$147).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$12$2(flowCollector), continuation);
                        if (collect5 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 5:
                        Object collect6 = ((MobileConnectionRepositoryImpl$special$$inlined$map$13) mobileConnectionRepositoryImpl$special$$inlined$map$147).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$17$2(flowCollector), continuation);
                        if (collect6 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 6:
                        Object collect7 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$147).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect7 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 7:
                        Object collect8 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$147).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect8 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 8:
                        Object collect9 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$147).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$4$2(flowCollector), continuation);
                        if (collect9 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 9:
                        Object collect10 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$147).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$5$2(flowCollector), continuation);
                        if (collect10 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 10:
                        Object collect11 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$147).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$6$2(flowCollector), continuation);
                        if (collect11 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        Object collect12 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$147).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$7$2(flowCollector), continuation);
                        if (collect12 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        Object collect13 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$147).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$8$2(flowCollector), continuation);
                        if (collect13 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect14 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$147).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$9$2(flowCollector), continuation);
                        if (collect14 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        final MobileConnectionRepositoryImpl$special$$inlined$map$14 mobileConnectionRepositoryImpl$special$$inlined$map$148 = new MobileConnectionRepositoryImpl$special$$inlined$map$14(stateIn, 14);
        final int i9 = 12;
        this.cdmaLevel = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L49
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CallbackEvent$OnServiceStateChanged r5 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CallbackEvent.OnServiceStateChanged) r5
                        android.telephony.ServiceState r5 = r5.serviceState
                        boolean r5 = r5.isEmergencyOnly()
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L49
                        return r1
                    L49:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i9) {
                    case 0:
                        Object collect = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$148).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$148).collect(new MobileConnectionRepositoryImpl$dataEnabled$lambda$36$$inlined$map$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$148).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$10$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 3:
                        Object collect4 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$148).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$11$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 4:
                        Object collect5 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$148).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$12$2(flowCollector), continuation);
                        if (collect5 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 5:
                        Object collect6 = ((MobileConnectionRepositoryImpl$special$$inlined$map$13) mobileConnectionRepositoryImpl$special$$inlined$map$148).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$17$2(flowCollector), continuation);
                        if (collect6 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 6:
                        Object collect7 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$148).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect7 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 7:
                        Object collect8 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$148).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect8 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 8:
                        Object collect9 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$148).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$4$2(flowCollector), continuation);
                        if (collect9 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 9:
                        Object collect10 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$148).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$5$2(flowCollector), continuation);
                        if (collect10 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 10:
                        Object collect11 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$148).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$6$2(flowCollector), continuation);
                        if (collect11 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        Object collect12 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$148).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$7$2(flowCollector), continuation);
                        if (collect12 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        Object collect13 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$148).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$8$2(flowCollector), continuation);
                        if (collect13 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect14 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$148).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$9$2(flowCollector), continuation);
                        if (collect14 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), 0);
        final MobileConnectionRepositoryImpl$special$$inlined$map$14 mobileConnectionRepositoryImpl$special$$inlined$map$149 = new MobileConnectionRepositoryImpl$special$$inlined$map$14(stateIn, 15);
        final int i10 = 13;
        this.primaryLevel = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L49
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CallbackEvent$OnServiceStateChanged r5 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CallbackEvent.OnServiceStateChanged) r5
                        android.telephony.ServiceState r5 = r5.serviceState
                        boolean r5 = r5.isEmergencyOnly()
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L49
                        return r1
                    L49:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i10) {
                    case 0:
                        Object collect = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$149).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$149).collect(new MobileConnectionRepositoryImpl$dataEnabled$lambda$36$$inlined$map$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$149).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$10$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 3:
                        Object collect4 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$149).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$11$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 4:
                        Object collect5 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$149).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$12$2(flowCollector), continuation);
                        if (collect5 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 5:
                        Object collect6 = ((MobileConnectionRepositoryImpl$special$$inlined$map$13) mobileConnectionRepositoryImpl$special$$inlined$map$149).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$17$2(flowCollector), continuation);
                        if (collect6 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 6:
                        Object collect7 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$149).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect7 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 7:
                        Object collect8 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$149).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect8 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 8:
                        Object collect9 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$149).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$4$2(flowCollector), continuation);
                        if (collect9 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 9:
                        Object collect10 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$149).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$5$2(flowCollector), continuation);
                        if (collect10 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 10:
                        Object collect11 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$149).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$6$2(flowCollector), continuation);
                        if (collect11 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        Object collect12 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$149).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$7$2(flowCollector), continuation);
                        if (collect12 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        Object collect13 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$149).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$8$2(flowCollector), continuation);
                        if (collect13 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect14 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$149).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$9$2(flowCollector), continuation);
                        if (collect14 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), 0);
        final MobileConnectionRepositoryImpl$special$$inlined$map$14 mobileConnectionRepositoryImpl$special$$inlined$map$1410 = new MobileConnectionRepositoryImpl$special$$inlined$map$14(stateIn, 3);
        final int i11 = 2;
        this.dataConnectionState = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L49
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CallbackEvent$OnServiceStateChanged r5 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CallbackEvent.OnServiceStateChanged) r5
                        android.telephony.ServiceState r5 = r5.serviceState
                        boolean r5 = r5.isEmergencyOnly()
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L49
                        return r1
                    L49:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i11) {
                    case 0:
                        Object collect = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1410).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1410).collect(new MobileConnectionRepositoryImpl$dataEnabled$lambda$36$$inlined$map$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1410).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$10$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 3:
                        Object collect4 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1410).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$11$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 4:
                        Object collect5 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1410).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$12$2(flowCollector), continuation);
                        if (collect5 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 5:
                        Object collect6 = ((MobileConnectionRepositoryImpl$special$$inlined$map$13) mobileConnectionRepositoryImpl$special$$inlined$map$1410).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$17$2(flowCollector), continuation);
                        if (collect6 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 6:
                        Object collect7 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1410).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect7 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 7:
                        Object collect8 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1410).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect8 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 8:
                        Object collect9 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1410).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$4$2(flowCollector), continuation);
                        if (collect9 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 9:
                        Object collect10 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1410).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$5$2(flowCollector), continuation);
                        if (collect10 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 10:
                        Object collect11 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1410).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$6$2(flowCollector), continuation);
                        if (collect11 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        Object collect12 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1410).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$7$2(flowCollector), continuation);
                        if (collect12 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        Object collect13 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1410).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$8$2(flowCollector), continuation);
                        if (collect13 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect14 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1410).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$9$2(flowCollector), continuation);
                        if (collect14 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), DataConnectionState.Disconnected);
        final MobileConnectionRepositoryImpl$special$$inlined$map$14 mobileConnectionRepositoryImpl$special$$inlined$map$1411 = new MobileConnectionRepositoryImpl$special$$inlined$map$14(stateIn, 4);
        final int i12 = 3;
        this.dataActivityDirection = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L49
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CallbackEvent$OnServiceStateChanged r5 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CallbackEvent.OnServiceStateChanged) r5
                        android.telephony.ServiceState r5 = r5.serviceState
                        boolean r5 = r5.isEmergencyOnly()
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L49
                        return r1
                    L49:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i12) {
                    case 0:
                        Object collect = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1411).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1411).collect(new MobileConnectionRepositoryImpl$dataEnabled$lambda$36$$inlined$map$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1411).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$10$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 3:
                        Object collect4 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1411).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$11$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 4:
                        Object collect5 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1411).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$12$2(flowCollector), continuation);
                        if (collect5 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 5:
                        Object collect6 = ((MobileConnectionRepositoryImpl$special$$inlined$map$13) mobileConnectionRepositoryImpl$special$$inlined$map$1411).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$17$2(flowCollector), continuation);
                        if (collect6 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 6:
                        Object collect7 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1411).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect7 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 7:
                        Object collect8 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1411).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect8 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 8:
                        Object collect9 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1411).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$4$2(flowCollector), continuation);
                        if (collect9 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 9:
                        Object collect10 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1411).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$5$2(flowCollector), continuation);
                        if (collect10 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 10:
                        Object collect11 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1411).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$6$2(flowCollector), continuation);
                        if (collect11 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        Object collect12 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1411).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$7$2(flowCollector), continuation);
                        if (collect12 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        Object collect13 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1411).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$8$2(flowCollector), continuation);
                        if (collect13 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect14 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1411).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$9$2(flowCollector), continuation);
                        if (collect14 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), new DataActivityModel(false, false));
        final MobileConnectionRepositoryImpl$special$$inlined$map$14 mobileConnectionRepositoryImpl$special$$inlined$map$1412 = new MobileConnectionRepositoryImpl$special$$inlined$map$14(stateIn, 5);
        final int i13 = 4;
        this.carrierNetworkChangeActive = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L49
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CallbackEvent$OnServiceStateChanged r5 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CallbackEvent.OnServiceStateChanged) r5
                        android.telephony.ServiceState r5 = r5.serviceState
                        boolean r5 = r5.isEmergencyOnly()
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L49
                        return r1
                    L49:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i13) {
                    case 0:
                        Object collect = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1412).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1412).collect(new MobileConnectionRepositoryImpl$dataEnabled$lambda$36$$inlined$map$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1412).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$10$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 3:
                        Object collect4 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1412).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$11$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 4:
                        Object collect5 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1412).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$12$2(flowCollector), continuation);
                        if (collect5 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 5:
                        Object collect6 = ((MobileConnectionRepositoryImpl$special$$inlined$map$13) mobileConnectionRepositoryImpl$special$$inlined$map$1412).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$17$2(flowCollector), continuation);
                        if (collect6 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 6:
                        Object collect7 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1412).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect7 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 7:
                        Object collect8 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1412).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect8 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 8:
                        Object collect9 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1412).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$4$2(flowCollector), continuation);
                        if (collect9 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 9:
                        Object collect10 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1412).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$5$2(flowCollector), continuation);
                        if (collect10 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 10:
                        Object collect11 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1412).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$6$2(flowCollector), continuation);
                        if (collect11 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        Object collect12 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1412).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$7$2(flowCollector), continuation);
                        if (collect12 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        Object collect13 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1412).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$8$2(flowCollector), continuation);
                        if (collect13 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect14 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1412).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$9$2(flowCollector), continuation);
                        if (collect14 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        this.resolvedNetworkType = FlowKt.stateIn(new MobileConnectionRepositoryImpl$special$$inlined$map$13(new MobileConnectionRepositoryImpl$special$$inlined$map$14(stateIn, 6), this, 0), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ResolvedNetworkType.UnknownNetworkType.INSTANCE);
        ReadonlyStateFlow readonlyStateFlow = systemUiCarrierConfig.shouldInflateSignalStrength;
        this.inflateSignalStrength = readonlyStateFlow;
        this.allowNetworkSliceIndicator = systemUiCarrierConfig.allowNetworkSliceIndicator;
        MobileConnectionRepositoryImpl$special$$inlined$map$14 mobileConnectionRepositoryImpl$special$$inlined$map$1413 = new MobileConnectionRepositoryImpl$special$$inlined$map$14(readonlyStateFlow, 0);
        StartedWhileSubscribed WhileSubscribed$default2 = SharingStarted.Companion.WhileSubscribed$default(3);
        MobileConnectionRepository.Companion.getClass();
        this.numberOfLevels = FlowKt.stateIn(mobileConnectionRepositoryImpl$special$$inlined$map$1413, coroutineScope, WhileSubscribed$default2, Integer.valueOf(MobileConnectionRepository.Companion.DEFAULT_NUM_LEVELS));
        this.carrierName = FlowKt.stateIn(new MobileConnectionRepositoryImpl$special$$inlined$map$13(flow, networkNameModel, 2), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), networkNameModel);
        this.cdmaRoaming = FlowKt.stateIn(FlowKt.flowOn(FlowKt.mapLatest(new MobileConnectionRepositoryImpl$cdmaRoaming$1(this, null), new MobileConnectionRepositoryImpl$special$$inlined$map$14(stateIn, 2)), coroutineDispatcher), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        final MobileConnectionRepositoryImpl$special$$inlined$map$13 mobileConnectionRepositoryImpl$special$$inlined$map$13 = new MobileConnectionRepositoryImpl$special$$inlined$map$13(BroadcastDispatcher.broadcastFlow$default(broadcastDispatcher, new IntentFilter("android.telephony.action.SUBSCRIPTION_CARRIER_IDENTITY_CHANGED"), null, new Function2() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$carrierId$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return (Intent) obj;
            }
        }, 14), this, 1);
        final int i14 = 5;
        this.carrierId = FlowKt.stateIn(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new MobileConnectionRepositoryImpl$carrierId$4(this, null), new Flow() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L49
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CallbackEvent$OnServiceStateChanged r5 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CallbackEvent.OnServiceStateChanged) r5
                        android.telephony.ServiceState r5 = r5.serviceState
                        boolean r5 = r5.isEmergencyOnly()
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L49
                        return r1
                    L49:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i14) {
                    case 0:
                        Object collect = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$13).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$13).collect(new MobileConnectionRepositoryImpl$dataEnabled$lambda$36$$inlined$map$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$13).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$10$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 3:
                        Object collect4 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$13).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$11$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 4:
                        Object collect5 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$13).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$12$2(flowCollector), continuation);
                        if (collect5 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 5:
                        Object collect6 = ((MobileConnectionRepositoryImpl$special$$inlined$map$13) mobileConnectionRepositoryImpl$special$$inlined$map$13).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$17$2(flowCollector), continuation);
                        if (collect6 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 6:
                        Object collect7 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$13).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect7 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 7:
                        Object collect8 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$13).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect8 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 8:
                        Object collect9 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$13).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$4$2(flowCollector), continuation);
                        if (collect9 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 9:
                        Object collect10 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$13).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$5$2(flowCollector), continuation);
                        if (collect10 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 10:
                        Object collect11 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$13).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$6$2(flowCollector), continuation);
                        if (collect11 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        Object collect12 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$13).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$7$2(flowCollector), continuation);
                        if (collect12 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        Object collect13 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$13).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$8$2(flowCollector), continuation);
                        if (collect13 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect14 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$13).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$9$2(flowCollector), continuation);
                        if (collect14 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Integer.valueOf(telephonyManager.getSimCarrierId()));
        this.networkName = FlowKt.stateIn(FlowKt.flowOn(FlowConflatedKt.conflatedCallbackFlow(new MobileConnectionRepositoryImpl$networkName$1(this, mobileInputLogger, str, networkNameModel, null)), coroutineDispatcher), coroutineScope, SharingStarted.Companion.Eagerly, networkNameModel);
        boolean isDataConnectionAllowed = telephonyManager.isDataConnectionAllowed();
        final MobileConnectionRepositoryImpl$special$$inlined$map$14 mobileConnectionRepositoryImpl$special$$inlined$map$1414 = new MobileConnectionRepositoryImpl$special$$inlined$map$14(stateIn, 1);
        final int i15 = 1;
        this.dataEnabled = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L49
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CallbackEvent$OnServiceStateChanged r5 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CallbackEvent.OnServiceStateChanged) r5
                        android.telephony.ServiceState r5 = r5.serviceState
                        boolean r5 = r5.isEmergencyOnly()
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L49
                        return r1
                    L49:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i15) {
                    case 0:
                        Object collect = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1414).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1414).collect(new MobileConnectionRepositoryImpl$dataEnabled$lambda$36$$inlined$map$1$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1414).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$10$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 3:
                        Object collect4 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1414).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$11$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 4:
                        Object collect5 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1414).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$12$2(flowCollector), continuation);
                        if (collect5 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 5:
                        Object collect6 = ((MobileConnectionRepositoryImpl$special$$inlined$map$13) mobileConnectionRepositoryImpl$special$$inlined$map$1414).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$17$2(flowCollector), continuation);
                        if (collect6 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 6:
                        Object collect7 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1414).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect7 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 7:
                        Object collect8 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1414).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect8 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 8:
                        Object collect9 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1414).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$4$2(flowCollector), continuation);
                        if (collect9 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 9:
                        Object collect10 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1414).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$5$2(flowCollector), continuation);
                        if (collect10 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 10:
                        Object collect11 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1414).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$6$2(flowCollector), continuation);
                        if (collect11 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        Object collect12 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1414).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$7$2(flowCollector), continuation);
                        if (collect12 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        Object collect13 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1414).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$8$2(flowCollector), continuation);
                        if (collect13 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect14 = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) mobileConnectionRepositoryImpl$special$$inlined$map$1414).collect(new MobileConnectionRepositoryImpl$special$$inlined$map$9$2(flowCollector), continuation);
                        if (collect14 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.valueOf(isDataConnectionAllowed));
        this.isAllowedDuringAirplaneMode = new ReadonlyStateFlow(StateFlowKt.MutableStateFlow(bool));
        this.networkSliceRequest = new NetworkRequest.Builder().addCapability(34).setSubscriptionIds(Collections.singleton(Integer.valueOf(i))).build();
        this.hasPrioritizedNetworkCapabilities = FlowKt.stateIn(FlowKt.flowOn(FlowConflatedKt.conflatedCallbackFlow(new MobileConnectionRepositoryImpl$hasPrioritizedNetworkCapabilities$1(connectivityManager, this, mobileInputLogger, null)), coroutineDispatcher), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getAllowNetworkSliceIndicator() {
        return this.allowNetworkSliceIndicator;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getCarrierId() {
        return this.carrierId;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getCarrierName() {
        return this.carrierName;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getCarrierNetworkChangeActive() {
        return this.carrierNetworkChangeActive;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getCdmaLevel() {
        return this.cdmaLevel;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getCdmaRoaming() {
        return this.cdmaRoaming;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getDataActivityDirection() {
        return this.dataActivityDirection;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getDataConnectionState() {
        return this.dataConnectionState;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getDataEnabled() {
        return this.dataEnabled;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getHasPrioritizedNetworkCapabilities() {
        return this.hasPrioritizedNetworkCapabilities;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getInflateSignalStrength() {
        return this.inflateSignalStrength;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getNetworkName() {
        return this.networkName;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getNumberOfLevels() {
        return this.numberOfLevels;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getOperatorAlphaShort() {
        return this.operatorAlphaShort;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getPrimaryLevel() {
        return this.primaryLevel;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getResolvedNetworkType() {
        return this.resolvedNetworkType;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final TableLogBuffer getTableLogBuffer() {
        return this.tableLogBuffer;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow isAllowedDuringAirplaneMode() {
        return this.isAllowedDuringAirplaneMode;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow isEmergencyOnly() {
        return this.isEmergencyOnly;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow isGsm() {
        return this.isGsm;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final Object isInEcmMode(Continuation continuation) {
        return BuildersKt.withContext(this.bgDispatcher, new MobileConnectionRepositoryImpl$isInEcmMode$2(this, null), continuation);
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow isInService() {
        return this.isInService;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow isNonTerrestrial() {
        return this.isNonTerrestrial;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow isRoaming() {
        return this.isRoaming;
    }
}
