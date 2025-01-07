package com.android.systemui.statusbar.notification.domain.interactor;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ActiveNotificationsInteractor$special$$inlined$map$1 implements Flow {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Flow $this_unsafeTransform$inlined;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor$special$$inlined$map$1$2, reason: invalid class name */
    public final class AnonymousClass2 implements FlowCollector {
        public final /* synthetic */ FlowCollector $this_unsafeFlow;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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

        /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object emit(java.lang.Object r8, kotlin.coroutines.Continuation r9) {
            /*
                r7 = this;
                boolean r0 = r9 instanceof com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                if (r0 == 0) goto L13
                r0 = r9
                com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor$special$$inlined$map$1$2$1
                r0.<init>(r9)
            L18:
                java.lang.Object r9 = r0.result
                kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L30
                if (r2 != r3) goto L28
                kotlin.ResultKt.throwOnFailure(r9)
                goto Lbf
            L28:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r8)
                throw r7
            L30:
                kotlin.ResultKt.throwOnFailure(r9)
                com.android.systemui.statusbar.notification.data.repository.ActiveNotificationsStore r8 = (com.android.systemui.statusbar.notification.data.repository.ActiveNotificationsStore) r8
                java.util.List r9 = r8.renderList
                java.util.ArrayList r2 = new java.util.ArrayList
                r4 = 10
                int r4 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r9, r4)
                r2.<init>(r4)
                java.util.Iterator r9 = r9.iterator()
            L46:
                boolean r4 = r9.hasNext()
                if (r4 == 0) goto Lb4
                java.lang.Object r4 = r9.next()
                com.android.systemui.statusbar.notification.data.repository.ActiveNotificationsStore$Key r4 = (com.android.systemui.statusbar.notification.data.repository.ActiveNotificationsStore.Key) r4
                boolean r5 = r4 instanceof com.android.systemui.statusbar.notification.data.repository.ActiveNotificationsStore.Key.Group
                if (r5 == 0) goto L64
                java.util.Map r5 = r8.groups
                r6 = r4
                com.android.systemui.statusbar.notification.data.repository.ActiveNotificationsStore$Key$Group r6 = (com.android.systemui.statusbar.notification.data.repository.ActiveNotificationsStore.Key.Group) r6
                java.lang.String r6 = r6.key
                java.lang.Object r5 = r5.get(r6)
                com.android.systemui.statusbar.notification.shared.ActiveNotificationEntryModel r5 = (com.android.systemui.statusbar.notification.shared.ActiveNotificationEntryModel) r5
                goto L75
            L64:
                boolean r5 = r4 instanceof com.android.systemui.statusbar.notification.data.repository.ActiveNotificationsStore.Key.Individual
                if (r5 == 0) goto Lae
                java.util.Map r5 = r8.individuals
                r6 = r4
                com.android.systemui.statusbar.notification.data.repository.ActiveNotificationsStore$Key$Individual r6 = (com.android.systemui.statusbar.notification.data.repository.ActiveNotificationsStore.Key.Individual) r6
                java.lang.String r6 = r6.key
                java.lang.Object r5 = r5.get(r6)
                com.android.systemui.statusbar.notification.shared.ActiveNotificationEntryModel r5 = (com.android.systemui.statusbar.notification.shared.ActiveNotificationEntryModel) r5
            L75:
                if (r5 == 0) goto L91
                boolean r4 = r5 instanceof com.android.systemui.statusbar.notification.shared.ActiveNotificationGroupModel
                if (r4 == 0) goto L80
                com.android.systemui.statusbar.notification.shared.ActiveNotificationGroupModel r5 = (com.android.systemui.statusbar.notification.shared.ActiveNotificationGroupModel) r5
                com.android.systemui.statusbar.notification.shared.ActiveNotificationModel r4 = r5.summary
                goto L87
            L80:
                boolean r4 = r5 instanceof com.android.systemui.statusbar.notification.shared.ActiveNotificationModel
                if (r4 == 0) goto L8b
                r4 = r5
                com.android.systemui.statusbar.notification.shared.ActiveNotificationModel r4 = (com.android.systemui.statusbar.notification.shared.ActiveNotificationModel) r4
            L87:
                r2.add(r4)
                goto L46
            L8b:
                kotlin.NoWhenBranchMatchedException r7 = new kotlin.NoWhenBranchMatchedException
                r7.<init>()
                throw r7
            L91:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.StringBuilder r8 = new java.lang.StringBuilder
                java.lang.String r9 = "Could not find notification with key "
                r8.<init>(r9)
                r8.append(r4)
                java.lang.String r9 = " in active notif store."
                r8.append(r9)
                java.lang.String r8 = r8.toString()
                java.lang.String r8 = r8.toString()
                r7.<init>(r8)
                throw r7
            Lae:
                kotlin.NoWhenBranchMatchedException r7 = new kotlin.NoWhenBranchMatchedException
                r7.<init>()
                throw r7
            Lb4:
                r0.label = r3
                kotlinx.coroutines.flow.FlowCollector r7 = r7.$this_unsafeFlow
                java.lang.Object r7 = r7.emit(r2, r0)
                if (r7 != r1) goto Lbf
                return r1
            Lbf:
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    public /* synthetic */ ActiveNotificationsInteractor$special$$inlined$map$1(Flow flow, int i) {
        this.$r8$classId = i;
        this.$this_unsafeTransform$inlined = flow;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        switch (this.$r8$classId) {
            case 0:
                ((StateFlowImpl) this.$this_unsafeTransform$inlined).collect(new AnonymousClass2(flowCollector), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            case 1:
                ((StateFlowImpl) this.$this_unsafeTransform$inlined).collect(new ActiveNotificationsInteractor$special$$inlined$map$2$2(flowCollector), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            case 2:
                ((StateFlowImpl) this.$this_unsafeTransform$inlined).collect(new ActiveNotificationsInteractor$special$$inlined$map$5$2(flowCollector), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            case 3:
                ((StateFlowImpl) this.$this_unsafeTransform$inlined).collect(new ActiveNotificationsInteractor$special$$inlined$map$6$2(flowCollector), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            case 4:
                ((StateFlowImpl) this.$this_unsafeTransform$inlined).collect(new ActiveNotificationsInteractor$special$$inlined$map$7$2(flowCollector), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            case 5:
                ((StateFlowImpl) this.$this_unsafeTransform$inlined).collect(new ActiveNotificationsInteractor$special$$inlined$map$8$2(flowCollector), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            case 6:
                ((StateFlowImpl) this.$this_unsafeTransform$inlined).collect(new ActiveNotificationsInteractor$special$$inlined$map$9$2(flowCollector), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            default:
                Object collect = ((ActiveNotificationsInteractor$special$$inlined$map$1) this.$this_unsafeTransform$inlined).collect(new ActiveNotificationsInteractor$special$$inlined$map$4$2(flowCollector), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
        }
    }
}
