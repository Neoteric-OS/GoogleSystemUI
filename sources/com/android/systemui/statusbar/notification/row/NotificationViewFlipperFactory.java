package com.android.systemui.statusbar.notification.row;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ViewFlipper;
import com.android.systemui.statusbar.notification.row.ui.viewbinder.NotificationViewFlipperBinder;
import com.android.systemui.statusbar.notification.row.ui.viewmodel.NotificationViewFlipperViewModel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationViewFlipperFactory implements NotifRemoteViewsFactory {
    public final NotificationViewFlipperViewModel viewModel;

    public NotificationViewFlipperFactory(NotificationViewFlipperViewModel notificationViewFlipperViewModel) {
        this.viewModel = notificationViewFlipperViewModel;
    }

    @Override // com.android.systemui.statusbar.notification.row.NotifRemoteViewsFactory
    public final View instantiate(ExpandableNotificationRow expandableNotificationRow, int i, String str, Context context, AttributeSet attributeSet) {
        if (!(str.equals(ViewFlipper.class.getName()) ? true : str.equals("ViewFlipper"))) {
            return null;
        }
        ViewFlipper viewFlipper = new ViewFlipper(context, attributeSet);
        NotificationViewFlipperBinder.bindWhileAttached(viewFlipper, this.viewModel);
        return viewFlipper;
    }
}
