package com.android.systemui.statusbar.notification.row.wrapper;

import android.R;
import android.app.Notification;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.service.notification.StatusBarNotification;
import android.util.Pools;
import android.widget.ImageView;
import com.android.internal.widget.BigPictureNotificationImageView;
import com.android.systemui.statusbar.notification.ImageTransformState;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationBigPictureTemplateViewWrapper extends NotificationTemplateViewWrapper {
    public BigPictureNotificationImageView mImageView;

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper
    public final void onContentShown(boolean z) {
        this.mRow.getClass();
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationTemplateViewWrapper, com.android.systemui.statusbar.notification.row.wrapper.NotificationHeaderViewWrapper, com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper
    public final void onContentUpdated(ExpandableNotificationRow expandableNotificationRow) {
        Bitmap bitmap;
        super.onContentUpdated(expandableNotificationRow);
        this.mImageView = this.mView.findViewById(R.id.buttonPanel);
        StatusBarNotification statusBarNotification = expandableNotificationRow.mEntry.mSbn;
        Icon icon = (Icon) statusBarNotification.getNotification().extras.getParcelable("android.largeIcon.big", Icon.class);
        if (icon != null) {
            ImageView imageView = this.mRightIcon;
            Pools.SimplePool simplePool = ImageTransformState.sInstancePool;
            imageView.setTag(com.android.wm.shell.R.id.image_icon_tag, icon);
            this.mLeftIcon.setTag(com.android.wm.shell.R.id.image_icon_tag, icon);
            return;
        }
        ImageView imageView2 = this.mRightIcon;
        Pools.SimplePool simplePool2 = ImageTransformState.sInstancePool;
        Notification notification = statusBarNotification.getNotification();
        Icon largeIcon = notification.getLargeIcon();
        if (largeIcon == null && (bitmap = notification.largeIcon) != null) {
            largeIcon = Icon.createWithBitmap(bitmap);
        }
        imageView2.setTag(com.android.wm.shell.R.id.image_icon_tag, largeIcon);
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper
    public final void setAnimationsRunning(boolean z) {
        BigPictureNotificationImageView bigPictureNotificationImageView = this.mImageView;
        if (bigPictureNotificationImageView == null) {
            return;
        }
        Drawable drawable = bigPictureNotificationImageView.getDrawable();
        if (drawable instanceof AnimatedImageDrawable) {
            AnimatedImageDrawable animatedImageDrawable = (AnimatedImageDrawable) drawable;
            if (z) {
                animatedImageDrawable.start();
            } else {
                animatedImageDrawable.stop();
            }
        }
    }
}
