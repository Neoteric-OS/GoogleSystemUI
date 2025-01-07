package com.android.systemui.statusbar.pipeline.mobile.data.repository.prod;

import com.android.app.viewcapture.data.ViewNode;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MobileConnectionRepositoryImpl$special$$inlined$map$14 implements Flow {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ReadonlyStateFlow $this_unsafeTransform$inlined;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$14$2, reason: invalid class name */
    public final class AnonymousClass2 implements FlowCollector {
        public final /* synthetic */ FlowCollector $this_unsafeFlow;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$14$2$1, reason: invalid class name */
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
                boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$14.AnonymousClass2.AnonymousClass1
                if (r0 == 0) goto L13
                r0 = r6
                com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$14$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$14.AnonymousClass2.AnonymousClass1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$14$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$14$2$1
                r0.<init>(r6)
            L18:
                java.lang.Object r6 = r0.result
                kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L2f
                if (r2 != r3) goto L27
                kotlin.ResultKt.throwOnFailure(r6)
                goto L5a
            L27:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                r4.<init>(r5)
                throw r4
            L2f:
                kotlin.ResultKt.throwOnFailure(r6)
                java.lang.Boolean r5 = (java.lang.Boolean) r5
                boolean r5 = r5.booleanValue()
                if (r5 == 0) goto L43
                com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository$Companion r5 = com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository.Companion
                r5.getClass()
                int r5 = com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository.Companion.DEFAULT_NUM_LEVELS
                int r5 = r5 + r3
                goto L4a
            L43:
                com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository$Companion r5 = com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository.Companion
                r5.getClass()
                int r5 = com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository.Companion.DEFAULT_NUM_LEVELS
            L4a:
                java.lang.Integer r6 = new java.lang.Integer
                r6.<init>(r5)
                r0.label = r3
                kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                java.lang.Object r4 = r4.emit(r6, r0)
                if (r4 != r1) goto L5a
                return r1
            L5a:
                kotlin.Unit r4 = kotlin.Unit.INSTANCE
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$special$$inlined$map$14.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    public /* synthetic */ MobileConnectionRepositoryImpl$special$$inlined$map$14(ReadonlyStateFlow readonlyStateFlow, int i) {
        this.$r8$classId = i;
        this.$this_unsafeTransform$inlined = readonlyStateFlow;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        switch (this.$r8$classId) {
            case 0:
                this.$this_unsafeTransform$inlined.collect(new AnonymousClass2(flowCollector), continuation);
                break;
            case 1:
                this.$this_unsafeTransform$inlined.collect(new MobileConnectionRepositoryImpl$dataEnabled$lambda$36$$inlined$mapNotNull$1$2(flowCollector), continuation);
                break;
            case 2:
                this.$this_unsafeTransform$inlined.collect(new MobileConnectionRepositoryImpl$special$$inlined$map$16$2(flowCollector), continuation);
                break;
            case 3:
                this.$this_unsafeTransform$inlined.collect(new MobileConnectionRepositoryImpl$special$$inlined$mapNotNull$10$2(flowCollector), continuation);
                break;
            case 4:
                this.$this_unsafeTransform$inlined.collect(new MobileConnectionRepositoryImpl$special$$inlined$mapNotNull$11$2(flowCollector), continuation);
                break;
            case 5:
                this.$this_unsafeTransform$inlined.collect(new MobileConnectionRepositoryImpl$special$$inlined$mapNotNull$12$2(flowCollector), continuation);
                break;
            case 6:
                this.$this_unsafeTransform$inlined.collect(new MobileConnectionRepositoryImpl$special$$inlined$mapNotNull$13$2(flowCollector), continuation);
                break;
            case 7:
                this.$this_unsafeTransform$inlined.collect(new MobileConnectionRepositoryImpl$special$$inlined$mapNotNull$1$2(flowCollector), continuation);
                break;
            case 8:
                this.$this_unsafeTransform$inlined.collect(new MobileConnectionRepositoryImpl$special$$inlined$mapNotNull$2$2(flowCollector), continuation);
                break;
            case 9:
                this.$this_unsafeTransform$inlined.collect(new MobileConnectionRepositoryImpl$special$$inlined$mapNotNull$3$2(flowCollector), continuation);
                break;
            case 10:
                this.$this_unsafeTransform$inlined.collect(new MobileConnectionRepositoryImpl$special$$inlined$mapNotNull$4$2(flowCollector), continuation);
                break;
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                this.$this_unsafeTransform$inlined.collect(new MobileConnectionRepositoryImpl$special$$inlined$mapNotNull$5$2(flowCollector), continuation);
                break;
            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                this.$this_unsafeTransform$inlined.collect(new MobileConnectionRepositoryImpl$special$$inlined$mapNotNull$6$2(flowCollector), continuation);
                break;
            case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                this.$this_unsafeTransform$inlined.collect(new MobileConnectionRepositoryImpl$special$$inlined$mapNotNull$7$2(flowCollector), continuation);
                break;
            case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                this.$this_unsafeTransform$inlined.collect(new MobileConnectionRepositoryImpl$special$$inlined$mapNotNull$8$2(flowCollector), continuation);
                break;
            default:
                this.$this_unsafeTransform$inlined.collect(new MobileConnectionRepositoryImpl$special$$inlined$mapNotNull$9$2(flowCollector), continuation);
                break;
        }
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }
}
