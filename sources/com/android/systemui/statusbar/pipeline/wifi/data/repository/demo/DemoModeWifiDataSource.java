package com.android.systemui.statusbar.pipeline.wifi.data.repository.demo;

import com.android.systemui.demomode.DemoModeController;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DemoModeWifiDataSource {
    public final DemoModeWifiDataSource$special$$inlined$map$1 _wifiCommands;
    public final ReadonlySharedFlow wifiEvents;

    public DemoModeWifiDataSource(DemoModeController demoModeController, CoroutineScope coroutineScope) {
        final Flow demoFlowForCommand = demoModeController.demoFlowForCommand();
        this.wifiEvents = FlowKt.shareIn(new Flow() { // from class: com.android.systemui.statusbar.pipeline.wifi.data.repository.demo.DemoModeWifiDataSource$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.wifi.data.repository.demo.DemoModeWifiDataSource$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ DemoModeWifiDataSource this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.wifi.data.repository.demo.DemoModeWifiDataSource$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, DemoModeWifiDataSource demoModeWifiDataSource) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = demoModeWifiDataSource;
                }

                /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
                /* JADX WARN: Code restructure failed: missing block: B:36:0x00a7, code lost:
                
                    if (r13.equals("none") == false) goto L53;
                 */
                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                /* JADX WARN: Type inference failed for: r13v2, types: [com.android.systemui.statusbar.pipeline.wifi.data.repository.demo.model.FakeWifiEventModel$WifiDisabled] */
                /* JADX WARN: Type inference failed for: r13v9, types: [com.android.systemui.statusbar.pipeline.wifi.data.repository.demo.model.FakeWifiEventModel$Wifi] */
                /* JADX WARN: Type inference failed for: r5v1, types: [com.android.systemui.statusbar.pipeline.wifi.data.repository.demo.model.FakeWifiEventModel$CarrierMerged] */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r13, kotlin.coroutines.Continuation r14) {
                    /*
                        Method dump skipped, instructions count: 352
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.wifi.data.repository.demo.DemoModeWifiDataSource$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), 0);
    }

    public static int toActivity(String str) {
        if (str != null) {
            int hashCode = str.hashCode();
            if (hashCode != 3365) {
                if (hashCode != 110414) {
                    if (hashCode == 100357129 && str.equals("inout")) {
                        return 3;
                    }
                } else if (str.equals("out")) {
                    return 2;
                }
            } else if (str.equals("in")) {
                return 1;
            }
        }
        return 0;
    }
}
