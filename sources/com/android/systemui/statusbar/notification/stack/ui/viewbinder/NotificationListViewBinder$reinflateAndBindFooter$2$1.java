package com.android.systemui.statusbar.notification.stack.ui.viewbinder;

import android.os.Trace;
import com.android.systemui.statusbar.notification.footer.ui.view.FooterView;
import com.android.systemui.statusbar.notification.footer.ui.viewmodel.FooterViewModel;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import java.util.concurrent.ThreadLocalRandom;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class NotificationListViewBinder$reinflateAndBindFooter$2$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ FooterViewModel $footerViewModel;
    final /* synthetic */ StateFlow $hasNonClearableSilentNotifications;
    final /* synthetic */ NotificationStackScrollLayout $parentView;
    int I$0;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ NotificationListViewBinder this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationListViewBinder$reinflateAndBindFooter$2$1(NotificationStackScrollLayout notificationStackScrollLayout, NotificationListViewBinder notificationListViewBinder, FooterViewModel footerViewModel, StateFlow stateFlow, Continuation continuation) {
        super(2, continuation);
        this.$parentView = notificationStackScrollLayout;
        this.this$0 = notificationListViewBinder;
        this.$footerViewModel = footerViewModel;
        this.$hasNonClearableSilentNotifications = stateFlow;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        NotificationListViewBinder$reinflateAndBindFooter$2$1 notificationListViewBinder$reinflateAndBindFooter$2$1 = new NotificationListViewBinder$reinflateAndBindFooter$2$1(this.$parentView, this.this$0, this.$footerViewModel, this.$hasNonClearableSilentNotifications, continuation);
        notificationListViewBinder$reinflateAndBindFooter$2$1.L$0 = obj;
        return notificationListViewBinder$reinflateAndBindFooter$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((NotificationListViewBinder$reinflateAndBindFooter$2$1) create((FooterView) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        int i;
        Throwable th;
        String str;
        int i2;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i3 = this.label;
        Unit unit = Unit.INSTANCE;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            FooterView footerView = (FooterView) this.L$0;
            NotificationStackScrollLayout notificationStackScrollLayout = this.$parentView;
            NotificationListViewBinder notificationListViewBinder = this.this$0;
            FooterViewModel footerViewModel = this.$footerViewModel;
            StateFlow stateFlow = this.$hasNonClearableSilentNotifications;
            int nextInt = ThreadLocalRandom.current().nextInt();
            Trace.asyncTraceForTrackBegin(4096L, "AsyncTraces", "bind FooterView", nextInt);
            try {
                FooterView footerView2 = notificationStackScrollLayout.mFooterView;
                if (footerView2 != null) {
                    i2 = notificationStackScrollLayout.indexOfChild(footerView2);
                    notificationStackScrollLayout.removeView(notificationStackScrollLayout.mFooterView);
                } else {
                    i2 = -1;
                }
                notificationStackScrollLayout.mFooterView = footerView;
                notificationStackScrollLayout.addView(footerView, i2);
                this.L$0 = "AsyncTraces";
                this.I$0 = nextInt;
                this.label = 1;
                notificationListViewBinder.getClass();
                Object coroutineScope = CoroutineScopeKt.coroutineScope(this, new NotificationListViewBinder$bindFooter$2(footerView, footerViewModel, notificationListViewBinder, notificationStackScrollLayout, stateFlow, null));
                if (coroutineScope != coroutineSingletons) {
                    coroutineScope = unit;
                }
                if (coroutineScope == coroutineSingletons) {
                    return coroutineSingletons;
                }
                i = nextInt;
                str = "AsyncTraces";
            } catch (Throwable th2) {
                i = nextInt;
                th = th2;
                str = "AsyncTraces";
                Trace.asyncTraceForTrackEnd(4096L, str, i);
                throw th;
            }
        } else {
            if (i3 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            i = this.I$0;
            str = (String) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th3) {
                th = th3;
                Trace.asyncTraceForTrackEnd(4096L, str, i);
                throw th;
            }
        }
        Trace.asyncTraceForTrackEnd(4096L, str, i);
        return unit;
    }
}
