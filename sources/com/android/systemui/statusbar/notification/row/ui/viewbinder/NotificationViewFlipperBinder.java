package com.android.systemui.statusbar.notification.row.ui.viewbinder;

import android.widget.ViewFlipper;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.statusbar.notification.row.ui.viewmodel.NotificationViewFlipperViewModel;
import kotlin.coroutines.EmptyCoroutineContext;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class NotificationViewFlipperBinder {
    public static void bindWhileAttached(ViewFlipper viewFlipper, NotificationViewFlipperViewModel notificationViewFlipperViewModel) {
        if (viewFlipper.isAutoStart()) {
            RepeatWhenAttachedKt.repeatWhenAttached(viewFlipper, EmptyCoroutineContext.INSTANCE, new NotificationViewFlipperBinder$bindWhileAttached$2(viewFlipper, notificationViewFlipperViewModel, null));
        }
    }
}
