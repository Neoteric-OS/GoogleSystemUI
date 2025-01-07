package com.android.systemui.deviceentry.domain.interactor;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BiometricMessageInteractor$special$$inlined$map$3 implements Flow {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Flow $this_unsafeTransform$inlined;
    public final /* synthetic */ Object this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.deviceentry.domain.interactor.BiometricMessageInteractor$special$$inlined$map$3$2, reason: invalid class name */
    public final class AnonymousClass2 implements FlowCollector {
        public final /* synthetic */ FlowCollector $this_unsafeFlow;
        public final /* synthetic */ BiometricMessageInteractor this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.deviceentry.domain.interactor.BiometricMessageInteractor$special$$inlined$map$3$2$1, reason: invalid class name */
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

        public AnonymousClass2(FlowCollector flowCollector, BiometricMessageInteractor biometricMessageInteractor) {
            this.$this_unsafeFlow = flowCollector;
            this.this$0 = biometricMessageInteractor;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x005d A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
            /*
                r4 = this;
                boolean r0 = r6 instanceof com.android.systemui.deviceentry.domain.interactor.BiometricMessageInteractor$special$$inlined$map$3.AnonymousClass2.AnonymousClass1
                if (r0 == 0) goto L13
                r0 = r6
                com.android.systemui.deviceentry.domain.interactor.BiometricMessageInteractor$special$$inlined$map$3$2$1 r0 = (com.android.systemui.deviceentry.domain.interactor.BiometricMessageInteractor$special$$inlined$map$3.AnonymousClass2.AnonymousClass1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                com.android.systemui.deviceentry.domain.interactor.BiometricMessageInteractor$special$$inlined$map$3$2$1 r0 = new com.android.systemui.deviceentry.domain.interactor.BiometricMessageInteractor$special$$inlined$map$3$2$1
                r0.<init>(r6)
            L18:
                java.lang.Object r6 = r0.result
                kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L2f
                if (r2 != r3) goto L27
                kotlin.ResultKt.throwOnFailure(r6)
                goto L5e
            L27:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                r4.<init>(r5)
                throw r4
            L2f:
                kotlin.ResultKt.throwOnFailure(r6)
                com.android.systemui.keyguard.shared.model.DevicePosture r5 = (com.android.systemui.keyguard.shared.model.DevicePosture) r5
                int r5 = r5.ordinal()
                com.android.systemui.deviceentry.domain.interactor.BiometricMessageInteractor r6 = r4.this$0
                if (r5 == 0) goto L51
                if (r5 == r3) goto L51
                r2 = 2
                if (r5 == r2) goto L51
                r2 = 3
                if (r5 == r2) goto L4e
                r2 = 4
                if (r5 != r2) goto L48
                goto L51
            L48:
                kotlin.NoWhenBranchMatchedException r4 = new kotlin.NoWhenBranchMatchedException
                r4.<init>()
                throw r4
            L4e:
                java.util.Set r5 = r6.coExFaceAcquisitionMsgIdsToShowUnfolded
                goto L53
            L51:
                java.util.Set r5 = r6.coExFaceAcquisitionMsgIdsToShowDefault
            L53:
                r0.label = r3
                kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                java.lang.Object r4 = r4.emit(r5, r0)
                if (r4 != r1) goto L5e
                return r1
            L5e:
                kotlin.Unit r4 = kotlin.Unit.INSTANCE
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.deviceentry.domain.interactor.BiometricMessageInteractor$special$$inlined$map$3.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    public /* synthetic */ BiometricMessageInteractor$special$$inlined$map$3(Flow flow, Object obj, int i) {
        this.$r8$classId = i;
        this.$this_unsafeTransform$inlined = flow;
        this.this$0 = obj;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        switch (this.$r8$classId) {
            case 0:
                Object collect = this.$this_unsafeTransform$inlined.collect(new AnonymousClass2(flowCollector, (BiometricMessageInteractor) this.this$0), continuation);
                if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    break;
                }
                break;
            case 1:
                Object collect2 = ((DeviceEntryFingerprintAuthInteractor$special$$inlined$map$1) this.$this_unsafeTransform$inlined).collect(new BiometricMessageInteractor$special$$inlined$filterNot$1$2(flowCollector, (BiometricMessageInteractor) this.this$0), continuation);
                if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    break;
                }
                break;
            case 2:
                Object collect3 = ((BiometricMessageInteractor$special$$inlined$filterIsInstance$1) this.$this_unsafeTransform$inlined).collect(new BiometricMessageInteractor$special$$inlined$filterNot$2$2(flowCollector, (FaceHelpMessageDeferralInteractor) this.this$0), continuation);
                if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    break;
                }
                break;
            case 3:
                Object collect4 = ((BiometricMessageInteractor$special$$inlined$filterIsInstance$1) this.$this_unsafeTransform$inlined).collect(new BiometricMessageInteractor$special$$inlined$filterNot$3$2(flowCollector, (BiometricMessageInteractor) this.this$0), continuation);
                if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    break;
                }
                break;
            case 4:
                Object collect5 = ((BiometricMessageInteractor$special$$inlined$filter$1) this.$this_unsafeTransform$inlined).collect(new BiometricMessageInteractor$special$$inlined$map$5$2(flowCollector, (BiometricMessageInteractor) this.this$0), continuation);
                if (collect5 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    break;
                }
                break;
            default:
                Object collect6 = ((BiometricMessageInteractor$special$$inlined$filter$1) this.$this_unsafeTransform$inlined).collect(new BiometricMessageInteractor$special$$inlined$map$6$2(flowCollector, (FaceHelpMessageDeferralInteractor) this.this$0), continuation);
                if (collect6 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    break;
                }
                break;
        }
        return Unit.INSTANCE;
    }
}
