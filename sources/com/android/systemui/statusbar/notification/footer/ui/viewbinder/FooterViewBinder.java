package com.android.systemui.statusbar.notification.footer.ui.viewbinder;

import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttached$1;
import com.android.systemui.statusbar.notification.footer.ui.view.FooterView;
import com.android.systemui.statusbar.notification.footer.ui.viewmodel.FooterViewModel;
import com.android.systemui.statusbar.notification.stack.ui.viewbinder.NotificationListViewBinder$bindFooter$2$disposableHandle$1;
import com.android.systemui.statusbar.notification.stack.ui.viewbinder.NotificationListViewBinder$bindFooter$2$disposableHandle$2;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class FooterViewBinder {
    public static RepeatWhenAttachedKt$repeatWhenAttached$1 bindWhileAttached(FooterView footerView, FooterViewModel footerViewModel, NotificationListViewBinder$bindFooter$2$disposableHandle$1 notificationListViewBinder$bindFooter$2$disposableHandle$1, NotificationListViewBinder$bindFooter$2$disposableHandle$2 notificationListViewBinder$bindFooter$2$disposableHandle$2, NotificationListViewBinder$bindFooter$2$disposableHandle$2 notificationListViewBinder$bindFooter$2$disposableHandle$22) {
        FooterViewBinder$bindWhileAttached$1 footerViewBinder$bindWhileAttached$1 = new FooterViewBinder$bindWhileAttached$1(footerView, footerViewModel, notificationListViewBinder$bindFooter$2$disposableHandle$1, notificationListViewBinder$bindFooter$2$disposableHandle$2, notificationListViewBinder$bindFooter$2$disposableHandle$22, null);
        CoroutineContext coroutineContext = RepeatWhenAttachedKt.MAIN_DISPATCHER_SINGLETON;
        return RepeatWhenAttachedKt.repeatWhenAttached(footerView, EmptyCoroutineContext.INSTANCE, footerViewBinder$bindWhileAttached$1);
    }
}
