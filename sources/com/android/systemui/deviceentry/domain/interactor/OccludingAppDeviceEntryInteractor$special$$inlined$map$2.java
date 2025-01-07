package com.android.systemui.deviceentry.domain.interactor;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.internal.ChannelLimitedFlowMerge;
import kotlinx.coroutines.flow.internal.CombineKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class OccludingAppDeviceEntryInteractor$special$$inlined$map$2 implements Flow {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object $this_unsafeTransform$inlined;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.deviceentry.domain.interactor.OccludingAppDeviceEntryInteractor$special$$inlined$map$2$2, reason: invalid class name */
    public final class AnonymousClass2 implements FlowCollector {
        public final /* synthetic */ FlowCollector $this_unsafeFlow;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.deviceentry.domain.interactor.OccludingAppDeviceEntryInteractor$special$$inlined$map$2$2$1, reason: invalid class name */
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

        /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object emit(java.lang.Object r6, kotlin.coroutines.Continuation r7) {
            /*
                r5 = this;
                boolean r0 = r7 instanceof com.android.systemui.deviceentry.domain.interactor.OccludingAppDeviceEntryInteractor$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                if (r0 == 0) goto L13
                r0 = r7
                com.android.systemui.deviceentry.domain.interactor.OccludingAppDeviceEntryInteractor$special$$inlined$map$2$2$1 r0 = (com.android.systemui.deviceentry.domain.interactor.OccludingAppDeviceEntryInteractor$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                com.android.systemui.deviceentry.domain.interactor.OccludingAppDeviceEntryInteractor$special$$inlined$map$2$2$1 r0 = new com.android.systemui.deviceentry.domain.interactor.OccludingAppDeviceEntryInteractor$special$$inlined$map$2$2$1
                r0.<init>(r7)
            L18:
                java.lang.Object r7 = r0.result
                kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r2 = r0.label
                kotlin.Unit r3 = kotlin.Unit.INSTANCE
                r4 = 1
                if (r2 == 0) goto L31
                if (r2 != r4) goto L29
                kotlin.ResultKt.throwOnFailure(r7)
                goto L41
            L29:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r6)
                throw r5
            L31:
                kotlin.ResultKt.throwOnFailure(r7)
                com.android.systemui.keyguard.shared.model.FingerprintAuthenticationStatus r6 = (com.android.systemui.keyguard.shared.model.FingerprintAuthenticationStatus) r6
                r0.label = r4
                kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
                java.lang.Object r5 = r5.emit(r3, r0)
                if (r5 != r1) goto L41
                return r1
            L41:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.deviceentry.domain.interactor.OccludingAppDeviceEntryInteractor$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    public /* synthetic */ OccludingAppDeviceEntryInteractor$special$$inlined$map$2(int i, Object obj) {
        this.$r8$classId = i;
        this.$this_unsafeTransform$inlined = obj;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        switch (this.$r8$classId) {
            case 0:
                Object collect = ((OccludingAppDeviceEntryInteractor$special$$inlined$filter$1) this.$this_unsafeTransform$inlined).collect(new AnonymousClass2(flowCollector), continuation);
                if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    break;
                }
                break;
            case 1:
                final Flow[] flowArr = (Flow[]) this.$this_unsafeTransform$inlined;
                Object combineInternal = CombineKt.combineInternal(continuation, new Function0() { // from class: com.android.systemui.deviceentry.domain.interactor.OccludingAppDeviceEntryInteractor$special$$inlined$combine$1$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return new Object[flowArr.length];
                    }
                }, new OccludingAppDeviceEntryInteractor$special$$inlined$combine$1$3(3, null), flowCollector, flowArr);
                if (combineInternal != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    break;
                }
                break;
            case 2:
                Object collect2 = ((ChannelLimitedFlowMerge) this.$this_unsafeTransform$inlined).collect(new OccludingAppDeviceEntryInteractor$special$$inlined$filterNot$1$2(flowCollector), continuation);
                if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    break;
                }
                break;
            default:
                Object collect3 = ((OccludingAppDeviceEntryInteractor$special$$inlined$filter$1) this.$this_unsafeTransform$inlined).collect(new OccludingAppDeviceEntryInteractor$special$$inlined$map$3$2(flowCollector), continuation);
                if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    break;
                }
                break;
        }
        return Unit.INSTANCE;
    }
}
