package com.android.systemui.statusbar.notification.row.ui.viewbinder;

import android.R;
import android.content.res.ColorStateList;
import android.view.View;
import android.widget.ImageView;
import com.android.systemui.statusbar.notification.row.HybridConversationNotificationView;
import com.android.systemui.statusbar.notification.row.HybridNotificationView;
import com.android.systemui.statusbar.notification.row.ui.viewmodel.ConversationAvatar;
import com.android.systemui.statusbar.notification.row.ui.viewmodel.ConversationData;
import com.android.systemui.statusbar.notification.row.ui.viewmodel.FacePile;
import com.android.systemui.statusbar.notification.row.ui.viewmodel.SingleIcon;
import com.android.systemui.statusbar.notification.row.ui.viewmodel.SingleLineViewModel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SingleLineViewBinder {
    public static final void bind(SingleLineViewModel singleLineViewModel, HybridNotificationView hybridNotificationView) {
        ConversationData conversationData;
        ConversationData conversationData2;
        if (!(hybridNotificationView instanceof HybridConversationNotificationView)) {
            if (hybridNotificationView != null) {
                hybridNotificationView.bind(singleLineViewModel != null ? singleLineViewModel.titleText : null, singleLineViewModel != null ? singleLineViewModel.contentText : null);
                return;
            }
            return;
        }
        if (singleLineViewModel != null && (conversationData2 = singleLineViewModel.conversationData) != null) {
            ConversationAvatar conversationAvatar = conversationData2.avatar;
            HybridConversationNotificationView hybridConversationNotificationView = (HybridConversationNotificationView) hybridNotificationView;
            hybridConversationNotificationView.getClass();
            if (conversationAvatar instanceof SingleIcon) {
                SingleIcon singleIcon = (SingleIcon) conversationAvatar;
                View view = hybridConversationNotificationView.mConversationFacePile;
                if (view != null) {
                    view.setVisibility(8);
                }
                hybridConversationNotificationView.mConversationIconView.setVisibility(0);
                hybridConversationNotificationView.mConversationIconView.setImageDrawable(singleIcon.iconDrawable);
                HybridConversationNotificationView.setSize(hybridConversationNotificationView.mConversationIconView, hybridConversationNotificationView.mSingleAvatarSize);
            } else {
                FacePile facePile = (FacePile) conversationAvatar;
                hybridConversationNotificationView.mConversationIconView.setVisibility(8);
                if (hybridConversationNotificationView.mConversationFacePile == null) {
                    hybridConversationNotificationView.mConversationFacePile = hybridConversationNotificationView.mConversationFacePileStub.inflate();
                }
                hybridConversationNotificationView.mConversationFacePile.setVisibility(0);
                ImageView imageView = (ImageView) hybridConversationNotificationView.mConversationFacePile.requireViewById(R.id.dangerous);
                ImageView imageView2 = (ImageView) hybridConversationNotificationView.mConversationFacePile.requireViewById(R.id.cycle);
                ImageView imageView3 = (ImageView) hybridConversationNotificationView.mConversationFacePile.requireViewById(R.id.dataSync);
                imageView.setImageTintList(ColorStateList.valueOf(facePile.bottomBackgroundColor));
                imageView2.setImageDrawable(facePile.bottomIconDrawable);
                imageView3.setImageDrawable(facePile.topIconDrawable);
                HybridConversationNotificationView.setSize(hybridConversationNotificationView.mConversationFacePile, hybridConversationNotificationView.mFacePileSize);
                HybridConversationNotificationView.setSize(imageView2, hybridConversationNotificationView.mFacePileAvatarSize);
                HybridConversationNotificationView.setSize(imageView3, hybridConversationNotificationView.mFacePileAvatarSize);
                HybridConversationNotificationView.setSize(imageView, (hybridConversationNotificationView.mFacePileProtectionWidth * 2) + hybridConversationNotificationView.mFacePileAvatarSize);
                hybridConversationNotificationView.mTransformationHelper.addViewTransformingToSimilar(imageView3);
                hybridConversationNotificationView.mTransformationHelper.addViewTransformingToSimilar(imageView2);
                hybridConversationNotificationView.mTransformationHelper.addViewTransformingToSimilar(imageView);
            }
        }
        HybridConversationNotificationView hybridConversationNotificationView2 = (HybridConversationNotificationView) hybridNotificationView;
        CharSequence charSequence = singleLineViewModel != null ? singleLineViewModel.titleText : null;
        CharSequence charSequence2 = singleLineViewModel != null ? singleLineViewModel.contentText : null;
        if (singleLineViewModel != null && (conversationData = singleLineViewModel.conversationData) != null) {
            r1 = conversationData.conversationSenderName;
        }
        hybridConversationNotificationView2.setText(charSequence, charSequence2, r1);
    }
}
