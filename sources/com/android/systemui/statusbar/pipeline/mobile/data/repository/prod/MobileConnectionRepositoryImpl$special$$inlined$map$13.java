package com.android.systemui.statusbar.pipeline.mobile.data.repository.prod;

import com.android.systemui.statusbar.pipeline.mobile.data.model.NetworkNameModel;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MobileConnectionRepositoryImpl$special$$inlined$map$13 implements Flow {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Flow $this_unsafeTransform$inlined;
    public final /* synthetic */ Object this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$13$2, reason: invalid class name */
    public final class AnonymousClass2 implements FlowCollector {
        public final /* synthetic */ FlowCollector $this_unsafeFlow;
        public final /* synthetic */ MobileConnectionRepositoryImpl this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$13$2$1, reason: invalid class name */
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

        public AnonymousClass2(FlowCollector flowCollector, MobileConnectionRepositoryImpl mobileConnectionRepositoryImpl) {
            this.$this_unsafeFlow = flowCollector;
            this.this$0 = mobileConnectionRepositoryImpl;
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
                boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$13.AnonymousClass2.AnonymousClass1
                if (r0 == 0) goto L13
                r0 = r6
                com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$13$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$13.AnonymousClass2.AnonymousClass1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$13$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$13$2$1
                r0.<init>(r6)
            L18:
                java.lang.Object r6 = r0.result
                kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L2f
                if (r2 != r3) goto L27
                kotlin.ResultKt.throwOnFailure(r6)
                goto L77
            L27:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                r4.<init>(r5)
                throw r4
            L2f:
                kotlin.ResultKt.throwOnFailure(r6)
                com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CallbackEvent$OnDisplayInfoChanged r5 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CallbackEvent.OnDisplayInfoChanged) r5
                android.telephony.TelephonyDisplayInfo r6 = r5.telephonyDisplayInfo
                int r6 = r6.getOverrideNetworkType()
                com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl r2 = r4.this$0
                if (r6 == 0) goto L50
                com.android.systemui.statusbar.pipeline.mobile.data.model.ResolvedNetworkType$OverrideNetworkType r6 = new com.android.systemui.statusbar.pipeline.mobile.data.model.ResolvedNetworkType$OverrideNetworkType
                com.android.systemui.statusbar.pipeline.mobile.util.MobileMappingsProxyImpl r2 = r2.mobileMappingsProxy
                android.telephony.TelephonyDisplayInfo r5 = r5.telephonyDisplayInfo
                int r5 = r5.getOverrideNetworkType()
                java.lang.String r5 = com.android.settingslib.mobile.MobileMappings.toDisplayIconKey(r5)
                r6.<init>(r5)
                goto L6c
            L50:
                android.telephony.TelephonyDisplayInfo r6 = r5.telephonyDisplayInfo
                int r6 = r6.getNetworkType()
                if (r6 == 0) goto L6a
                com.android.systemui.statusbar.pipeline.mobile.data.model.ResolvedNetworkType$DefaultNetworkType r6 = new com.android.systemui.statusbar.pipeline.mobile.data.model.ResolvedNetworkType$DefaultNetworkType
                com.android.systemui.statusbar.pipeline.mobile.util.MobileMappingsProxyImpl r2 = r2.mobileMappingsProxy
                android.telephony.TelephonyDisplayInfo r5 = r5.telephonyDisplayInfo
                int r5 = r5.getNetworkType()
                java.lang.String r5 = java.lang.Integer.toString(r5)
                r6.<init>(r5)
                goto L6c
            L6a:
                com.android.systemui.statusbar.pipeline.mobile.data.model.ResolvedNetworkType$UnknownNetworkType r6 = com.android.systemui.statusbar.pipeline.mobile.data.model.ResolvedNetworkType.UnknownNetworkType.INSTANCE
            L6c:
                r0.label = r3
                kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                java.lang.Object r4 = r4.emit(r6, r0)
                if (r4 != r1) goto L77
                return r1
            L77:
                kotlin.Unit r4 = kotlin.Unit.INSTANCE
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$13.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    public /* synthetic */ MobileConnectionRepositoryImpl$special$$inlined$map$13(Flow flow, Object obj, int i) {
        this.$r8$classId = i;
        this.$this_unsafeTransform$inlined = flow;
        this.this$0 = obj;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        switch (this.$r8$classId) {
            case 0:
                Object collect = ((MobileConnectionRepositoryImpl$special$$inlined$map$14) this.$this_unsafeTransform$inlined).collect(new AnonymousClass2(flowCollector, (MobileConnectionRepositoryImpl) this.this$0), continuation);
                if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    break;
                }
                break;
            case 1:
                Object collect2 = this.$this_unsafeTransform$inlined.collect(new MobileConnectionRepositoryImpl$special$$inlined$filter$1$2(flowCollector, (MobileConnectionRepositoryImpl) this.this$0), continuation);
                if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    break;
                }
                break;
            default:
                Object collect3 = this.$this_unsafeTransform$inlined.collect(new MobileConnectionRepositoryImpl$special$$inlined$map$15$2(flowCollector, (NetworkNameModel) this.this$0), continuation);
                if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    break;
                }
                break;
        }
        return Unit.INSTANCE;
    }
}
