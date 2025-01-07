package com.android.systemui.education.ui.viewmodel;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ContextualEduViewModel$special$$inlined$map$1 implements Flow {
    public final /* synthetic */ FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 $this_unsafeTransform$inlined;
    public final /* synthetic */ ContextualEduViewModel this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.education.ui.viewmodel.ContextualEduViewModel$special$$inlined$map$1$2, reason: invalid class name */
    public final class AnonymousClass2 implements FlowCollector {
        public final /* synthetic */ FlowCollector $this_unsafeFlow;
        public final /* synthetic */ ContextualEduViewModel this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.education.ui.viewmodel.ContextualEduViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
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

        public AnonymousClass2(FlowCollector flowCollector, ContextualEduViewModel contextualEduViewModel) {
            this.$this_unsafeFlow = flowCollector;
            this.this$0 = contextualEduViewModel;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object emit(java.lang.Object r8, kotlin.coroutines.Continuation r9) {
            /*
                r7 = this;
                boolean r0 = r9 instanceof com.android.systemui.education.ui.viewmodel.ContextualEduViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                if (r0 == 0) goto L13
                r0 = r9
                com.android.systemui.education.ui.viewmodel.ContextualEduViewModel$special$$inlined$map$1$2$1 r0 = (com.android.systemui.education.ui.viewmodel.ContextualEduViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                com.android.systemui.education.ui.viewmodel.ContextualEduViewModel$special$$inlined$map$1$2$1 r0 = new com.android.systemui.education.ui.viewmodel.ContextualEduViewModel$special$$inlined$map$1$2$1
                r0.<init>(r9)
            L18:
                java.lang.Object r9 = r0.result
                kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L2f
                if (r2 != r3) goto L27
                kotlin.ResultKt.throwOnFailure(r9)
                goto L8a
            L27:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r8)
                throw r7
            L2f:
                kotlin.ResultKt.throwOnFailure(r9)
                com.android.systemui.education.shared.model.EducationInfo r8 = (com.android.systemui.education.shared.model.EducationInfo) r8
                com.android.systemui.education.shared.model.EducationUiType r9 = r8.educationUiType
                com.android.systemui.education.shared.model.EducationUiType r2 = com.android.systemui.education.shared.model.EducationUiType.Notification
                com.android.systemui.education.ui.viewmodel.ContextualEduViewModel r4 = r7.this$0
                int r5 = r8.userId
                if (r9 != r2) goto L76
                com.android.systemui.education.ui.viewmodel.ContextualEduNotificationViewModel r9 = new com.android.systemui.education.ui.viewmodel.ContextualEduNotificationViewModel
                r4.getClass()
                com.android.systemui.contextualeducation.GestureType r2 = r8.gestureType
                int r2 = r2.ordinal()
                if (r2 == 0) goto L65
                if (r2 == r3) goto L61
                r6 = 2
                if (r2 == r6) goto L5d
                r6 = 3
                if (r2 != r6) goto L57
                r2 = 2131951940(0x7f130144, float:1.9540309E38)
                goto L68
            L57:
                kotlin.NoWhenBranchMatchedException r7 = new kotlin.NoWhenBranchMatchedException
                r7.<init>()
                throw r7
            L5d:
                r2 = 2131953656(0x7f1307f8, float:1.954379E38)
                goto L68
            L61:
                r2 = 2131952798(0x7f13049e, float:1.9542049E38)
                goto L68
            L65:
                r2 = 2131951977(0x7f130169, float:1.9540384E38)
            L68:
                android.content.res.Resources r6 = r4.resources
                java.lang.String r2 = r6.getString(r2)
                java.lang.String r8 = com.android.systemui.education.ui.viewmodel.ContextualEduViewModel.access$getEduContent(r4, r8)
                r9.<init>(r2, r8, r5)
                goto L7f
            L76:
                com.android.systemui.education.ui.viewmodel.ContextualEduToastViewModel r9 = new com.android.systemui.education.ui.viewmodel.ContextualEduToastViewModel
                java.lang.String r8 = com.android.systemui.education.ui.viewmodel.ContextualEduViewModel.access$getEduContent(r4, r8)
                r9.<init>(r8, r5)
            L7f:
                r0.label = r3
                kotlinx.coroutines.flow.FlowCollector r7 = r7.$this_unsafeFlow
                java.lang.Object r7 = r7.emit(r9, r0)
                if (r7 != r1) goto L8a
                return r1
            L8a:
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.education.ui.viewmodel.ContextualEduViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    public ContextualEduViewModel$special$$inlined$map$1(FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1, ContextualEduViewModel contextualEduViewModel) {
        this.$this_unsafeTransform$inlined = flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
        this.this$0 = contextualEduViewModel;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        Object collect = this.$this_unsafeTransform$inlined.collect(new AnonymousClass2(flowCollector, this.this$0), continuation);
        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
    }
}
