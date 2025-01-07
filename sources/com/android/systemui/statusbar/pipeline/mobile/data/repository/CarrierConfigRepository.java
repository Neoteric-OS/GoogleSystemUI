package com.android.systemui.statusbar.pipeline.mobile.data.repository;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.PersistableBundle;
import android.telephony.CarrierConfigManager;
import android.util.SparseArray;
import com.android.keyguard.ActiveUnlockConfig$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger;
import com.android.systemui.statusbar.pipeline.mobile.data.model.SystemUiCarrierConfig;
import java.io.PrintWriter;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CarrierConfigRepository implements Dumpable {
    public final CarrierConfigManager carrierConfigManager;
    public final ReadonlySharedFlow carrierConfigStream;
    public boolean isListening;
    public final Lazy defaultConfig$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.repository.CarrierConfigRepository$defaultConfig$2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return CarrierConfigManager.getDefaultConfig();
        }
    });
    public final Lazy defaultConfigForLogs$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.repository.CarrierConfigRepository$defaultConfigForLogs$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return new SystemUiCarrierConfig((PersistableBundle) CarrierConfigRepository.this.defaultConfig$delegate.getValue());
        }
    });
    public final SparseArray configs = new SparseArray();

    public CarrierConfigRepository(BroadcastDispatcher broadcastDispatcher, CarrierConfigManager carrierConfigManager, DumpManager dumpManager, MobileInputLogger mobileInputLogger, CoroutineScope coroutineScope) {
        this.carrierConfigManager = carrierConfigManager;
        dumpManager.registerNormalDumpable(this);
        final CarrierConfigRepository$special$$inlined$filter$1 carrierConfigRepository$special$$inlined$filter$1 = new CarrierConfigRepository$special$$inlined$filter$1(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(BroadcastDispatcher.broadcastFlow$default(broadcastDispatcher, new IntentFilter("android.telephony.action.CARRIER_CONFIG_CHANGED"), null, new Function2() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.repository.CarrierConfigRepository$carrierConfigStream$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(((Intent) obj).getIntExtra("android.telephony.extra.SUBSCRIPTION_INDEX", -1));
            }
        }, 14), new CarrierConfigRepository$carrierConfigStream$2(mobileInputLogger, null), 0));
        this.carrierConfigStream = FlowKt.shareIn(new Flow() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.repository.CarrierConfigRepository$special$$inlined$mapNotNull$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.CarrierConfigRepository$special$$inlined$mapNotNull$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ CarrierConfigRepository this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.CarrierConfigRepository$special$$inlined$mapNotNull$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, CarrierConfigRepository carrierConfigRepository) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = carrierConfigRepository;
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.mobile.data.repository.CarrierConfigRepository$special$$inlined$mapNotNull$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.CarrierConfigRepository$special$$inlined$mapNotNull$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.CarrierConfigRepository$special$$inlined$mapNotNull$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.CarrierConfigRepository$special$$inlined$mapNotNull$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.data.repository.CarrierConfigRepository$special$$inlined$mapNotNull$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L5f
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.lang.Number r5 = (java.lang.Number) r5
                        int r5 = r5.intValue()
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.CarrierConfigRepository r6 = r4.this$0
                        android.telephony.CarrierConfigManager r6 = r6.carrierConfigManager
                        r2 = 0
                        if (r6 == 0) goto L44
                        android.os.PersistableBundle r6 = r6.getConfigForSubId(r5)
                        goto L45
                    L44:
                        r6 = r2
                    L45:
                        if (r6 == 0) goto L52
                        java.lang.Integer r2 = new java.lang.Integer
                        r2.<init>(r5)
                        kotlin.Pair r5 = new kotlin.Pair
                        r5.<init>(r2, r6)
                        r2 = r5
                    L52:
                        if (r2 == 0) goto L5f
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r2, r0)
                        if (r4 != r1) goto L5f
                        return r1
                    L5f:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.data.repository.CarrierConfigRepository$special$$inlined$mapNotNull$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = CarrierConfigRepository$special$$inlined$filter$1.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), 0);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("isListening: ", this.isListening, printWriter);
        if (this.configs.size() == 0) {
            printWriter.println("no carrier configs loaded");
            return;
        }
        printWriter.println("Carrier configs by subId");
        SparseArray sparseArray = this.configs;
        int i = 0;
        while (i < sparseArray.size()) {
            int i2 = i + 1;
            int keyAt = sparseArray.keyAt(i);
            printWriter.println("  subId=" + keyAt);
            printWriter.println("    config=".concat(((SystemUiCarrierConfig) this.configs.get(keyAt)).toStringConsideringDefaults()));
            i = i2;
        }
        printWriter.println("Default config:");
        printWriter.println("  " + ((SystemUiCarrierConfig) this.defaultConfigForLogs$delegate.getValue()));
    }

    public final SystemUiCarrierConfig getOrCreateConfigForSubId(int i) {
        Object obj = this.configs.get(i);
        Object obj2 = obj;
        if (obj == null) {
            SystemUiCarrierConfig systemUiCarrierConfig = new SystemUiCarrierConfig((PersistableBundle) this.defaultConfig$delegate.getValue());
            CarrierConfigManager carrierConfigManager = this.carrierConfigManager;
            PersistableBundle configForSubId = carrierConfigManager != null ? carrierConfigManager.getConfigForSubId(i) : null;
            if (configForSubId != null) {
                systemUiCarrierConfig.processNewCarrierConfig(configForSubId);
            }
            this.configs.put(i, systemUiCarrierConfig);
            obj2 = systemUiCarrierConfig;
        }
        return (SystemUiCarrierConfig) obj2;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void startObservingCarrierConfigUpdates(kotlin.coroutines.jvm.internal.ContinuationImpl r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.android.systemui.statusbar.pipeline.mobile.data.repository.CarrierConfigRepository$startObservingCarrierConfigUpdates$1
            if (r0 == 0) goto L13
            r0 = r5
            com.android.systemui.statusbar.pipeline.mobile.data.repository.CarrierConfigRepository$startObservingCarrierConfigUpdates$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.CarrierConfigRepository$startObservingCarrierConfigUpdates$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.statusbar.pipeline.mobile.data.repository.CarrierConfigRepository$startObservingCarrierConfigUpdates$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.data.repository.CarrierConfigRepository$startObservingCarrierConfigUpdates$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 == r3) goto L2b
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2b:
            kotlin.ResultKt.throwOnFailure(r5)
            goto L46
        L2f:
            kotlin.ResultKt.throwOnFailure(r5)
            r4.isListening = r3
            com.android.systemui.statusbar.pipeline.mobile.data.repository.CarrierConfigRepository$startObservingCarrierConfigUpdates$2 r5 = new com.android.systemui.statusbar.pipeline.mobile.data.repository.CarrierConfigRepository$startObservingCarrierConfigUpdates$2
            r5.<init>()
            r0.label = r3
            kotlinx.coroutines.flow.ReadonlySharedFlow r4 = r4.carrierConfigStream
            kotlinx.coroutines.flow.MutableSharedFlow r4 = r4.$$delegate_0
            java.lang.Object r4 = r4.collect(r5, r0)
            if (r4 != r1) goto L46
            return
        L46:
            kotlin.KotlinNothingValueException r4 = new kotlin.KotlinNothingValueException
            r4.<init>()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.data.repository.CarrierConfigRepository.startObservingCarrierConfigUpdates(kotlin.coroutines.jvm.internal.ContinuationImpl):void");
    }

    public static /* synthetic */ void getCarrierConfigStream$annotations() {
    }
}
