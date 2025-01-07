package com.android.systemui.statusbar.notification.stack.ui.viewbinder;

import android.view.View;
import com.android.systemui.statusbar.notification.collection.render.SectionHeaderNodeControllerImpl;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.SectionHeaderView;
import kotlin.KotlinNothingValueException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class NotificationListViewBinder$bindSilentHeaderClickListener$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ StateFlow $hasNonClearableSilentNotifications;
    final /* synthetic */ NotificationStackScrollLayout $parentView;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ NotificationListViewBinder this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationListViewBinder$bindSilentHeaderClickListener$2(NotificationListViewBinder notificationListViewBinder, NotificationStackScrollLayout notificationStackScrollLayout, StateFlow stateFlow, Continuation continuation) {
        super(2, continuation);
        this.this$0 = notificationListViewBinder;
        this.$parentView = notificationStackScrollLayout;
        this.$hasNonClearableSilentNotifications = stateFlow;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        NotificationListViewBinder$bindSilentHeaderClickListener$2 notificationListViewBinder$bindSilentHeaderClickListener$2 = new NotificationListViewBinder$bindSilentHeaderClickListener$2(this.this$0, this.$parentView, this.$hasNonClearableSilentNotifications, continuation);
        notificationListViewBinder$bindSilentHeaderClickListener$2.L$0 = obj;
        return notificationListViewBinder$bindSilentHeaderClickListener$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        ((NotificationListViewBinder$bindSilentHeaderClickListener$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                Flow flow = (Flow) this.this$0.viewModel.hasClearableAlertingNotifications$delegate.getValue();
                this.label = 1;
                obj = FlowKt.stateIn(flow, coroutineScope, this);
                if (obj == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    throw new KotlinNothingValueException();
                }
                ResultKt.throwOnFailure(obj);
            }
            final StateFlow stateFlow = (StateFlow) obj;
            final NotificationListViewBinder notificationListViewBinder = this.this$0;
            SectionHeaderNodeControllerImpl sectionHeaderNodeControllerImpl = notificationListViewBinder.silentHeaderController;
            final NotificationStackScrollLayout notificationStackScrollLayout = this.$parentView;
            final StateFlow stateFlow2 = this.$hasNonClearableSilentNotifications;
            View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewbinder.NotificationListViewBinder$bindSilentHeaderClickListener$2.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    NotificationListViewBinder notificationListViewBinder2 = NotificationListViewBinder.this;
                    NotificationStackScrollLayout notificationStackScrollLayout2 = notificationStackScrollLayout;
                    boolean z = !((Boolean) stateFlow.getValue()).booleanValue();
                    boolean z2 = !((Boolean) stateFlow2.getValue()).booleanValue();
                    notificationListViewBinder2.getClass();
                    notificationStackScrollLayout2.clearNotifications(2, z, z2);
                }
            };
            sectionHeaderNodeControllerImpl.clearAllClickListener = onClickListener;
            SectionHeaderView sectionHeaderView = sectionHeaderNodeControllerImpl._view;
            if (sectionHeaderView != null) {
                sectionHeaderView.mOnClearClickListener = onClickListener;
                sectionHeaderView.mClearAllButton.setOnClickListener(onClickListener);
            }
            this.label = 2;
            DelayKt.awaitCancellation(this);
            return coroutineSingletons;
        } catch (Throwable th) {
            SectionHeaderNodeControllerImpl sectionHeaderNodeControllerImpl2 = this.this$0.silentHeaderController;
            AnonymousClass2 anonymousClass2 = AnonymousClass2.INSTANCE;
            sectionHeaderNodeControllerImpl2.clearAllClickListener = anonymousClass2;
            SectionHeaderView sectionHeaderView2 = sectionHeaderNodeControllerImpl2._view;
            if (sectionHeaderView2 != null) {
                sectionHeaderView2.mOnClearClickListener = anonymousClass2;
                sectionHeaderView2.mClearAllButton.setOnClickListener(anonymousClass2);
            }
            throw th;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewbinder.NotificationListViewBinder$bindSilentHeaderClickListener$2$2, reason: invalid class name */
    public final class AnonymousClass2 implements View.OnClickListener {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
        }
    }
}
