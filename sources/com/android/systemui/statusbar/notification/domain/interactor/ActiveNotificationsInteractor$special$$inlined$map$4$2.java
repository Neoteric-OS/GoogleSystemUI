package com.android.systemui.statusbar.notification.domain.interactor;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ActiveNotificationsInteractor$special$$inlined$map$4$2 implements FlowCollector {
    public final /* synthetic */ FlowCollector $this_unsafeFlow;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor$special$$inlined$map$4$2$1, reason: invalid class name */
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
            return ActiveNotificationsInteractor$special$$inlined$map$4$2.this.emit(null, this);
        }
    }

    public ActiveNotificationsInteractor$special$$inlined$map$4$2(FlowCollector flowCollector) {
        this.$this_unsafeFlow = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.lang.Object r10, kotlin.coroutines.Continuation r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor$special$$inlined$map$4$2.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r11
            com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor$special$$inlined$map$4$2$1 r0 = (com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor$special$$inlined$map$4$2.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor$special$$inlined$map$4$2$1 r0 = new com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor$special$$inlined$map$4$2$1
            r0.<init>(r11)
        L18:
            java.lang.Object r11 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L30
            if (r2 != r3) goto L28
            kotlin.ResultKt.throwOnFailure(r11)
            goto L99
        L28:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L30:
            kotlin.ResultKt.throwOnFailure(r11)
            java.util.Map r10 = (java.util.Map) r10
            java.util.Collection r10 = r10.values()
            java.lang.Iterable r10 = (java.lang.Iterable) r10
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            java.util.Iterator r10 = r10.iterator()
        L44:
            boolean r2 = r10.hasNext()
            if (r2 == 0) goto L5b
            java.lang.Object r2 = r10.next()
            r4 = r2
            com.android.systemui.statusbar.notification.shared.ActiveNotificationModel r4 = (com.android.systemui.statusbar.notification.shared.ActiveNotificationModel) r4
            com.android.systemui.statusbar.notification.shared.CallType r4 = r4.callType
            com.android.systemui.statusbar.notification.shared.CallType r5 = com.android.systemui.statusbar.notification.shared.CallType.Ongoing
            if (r4 != r5) goto L44
            r11.add(r2)
            goto L44
        L5b:
            java.util.Iterator r10 = r11.iterator()
            boolean r11 = r10.hasNext()
            if (r11 != 0) goto L67
            r10 = 0
            goto L8e
        L67:
            java.lang.Object r11 = r10.next()
            boolean r2 = r10.hasNext()
            if (r2 != 0) goto L73
        L71:
            r10 = r11
            goto L8e
        L73:
            r2 = r11
            com.android.systemui.statusbar.notification.shared.ActiveNotificationModel r2 = (com.android.systemui.statusbar.notification.shared.ActiveNotificationModel) r2
            long r4 = r2.whenTime
        L78:
            java.lang.Object r2 = r10.next()
            r6 = r2
            com.android.systemui.statusbar.notification.shared.ActiveNotificationModel r6 = (com.android.systemui.statusbar.notification.shared.ActiveNotificationModel) r6
            long r6 = r6.whenTime
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 <= 0) goto L87
            r11 = r2
            r4 = r6
        L87:
            boolean r2 = r10.hasNext()
            if (r2 != 0) goto L78
            goto L71
        L8e:
            r0.label = r3
            kotlinx.coroutines.flow.FlowCollector r9 = r9.$this_unsafeFlow
            java.lang.Object r9 = r9.emit(r10, r0)
            if (r9 != r1) goto L99
            return r1
        L99:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor$special$$inlined$map$4$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
