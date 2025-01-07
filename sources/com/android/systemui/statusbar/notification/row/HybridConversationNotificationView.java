package com.android.systemui.statusbar.notification.row;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.systemui.statusbar.ViewTransformationHelper;
import com.android.systemui.statusbar.notification.NotificationFadeAware;
import com.android.systemui.statusbar.notification.row.HybridNotificationView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class HybridConversationNotificationView extends HybridNotificationView {
    public View mConversationFacePile;
    public ViewStub mConversationFacePileStub;
    public ImageView mConversationIconView;
    public TextView mConversationSenderName;
    public int mFacePileAvatarSize;
    public int mFacePileProtectionWidth;
    public int mFacePileSize;
    public int mSingleAvatarSize;

    public HybridConversationNotificationView(Context context) {
        this(context, null);
    }

    public static void setSize(View view, int i) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i;
        view.setLayoutParams(layoutParams);
    }

    @Override // com.android.systemui.statusbar.notification.row.HybridNotificationView
    public final void bind(CharSequence charSequence, CharSequence charSequence2) {
        throw new IllegalStateException("Legacy code path not supported when com.android.systemui.notification_async_hybrid_view_inflation is enabled.");
    }

    public TextView getConversationSenderNameView() {
        return this.mConversationSenderName;
    }

    @Override // com.android.systemui.statusbar.notification.row.HybridNotificationView, android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mConversationIconView = (ImageView) requireViewById(R.id.datePicker);
        this.mConversationFacePileStub = (ViewStub) requireViewById(R.id.customPanel);
        TextView textView = (TextView) requireViewById(com.android.wm.shell.R.id.conversation_notification_sender);
        this.mConversationSenderName = textView;
        int i = this.mSecondaryTextColor;
        if (i != 1) {
            textView.setTextColor(i);
        }
        this.mFacePileSize = getResources().getDimensionPixelSize(com.android.wm.shell.R.dimen.conversation_single_line_face_pile_size);
        this.mFacePileAvatarSize = getResources().getDimensionPixelSize(com.android.wm.shell.R.dimen.conversation_single_line_face_pile_avatar_size);
        this.mSingleAvatarSize = getResources().getDimensionPixelSize(com.android.wm.shell.R.dimen.conversation_single_line_avatar_size);
        this.mFacePileProtectionWidth = getResources().getDimensionPixelSize(com.android.wm.shell.R.dimen.conversation_single_line_face_pile_protection_width);
        ViewTransformationHelper viewTransformationHelper = this.mTransformationHelper;
        TextView textView2 = this.mConversationSenderName;
        viewTransformationHelper.mCustomTransformations.put(Integer.valueOf(textView2.getId()), new HybridNotificationView.FadeOutAndDownWithTitleTransformation(textView2));
        this.mTransformationHelper.addViewTransformingToSimilar(this.mConversationIconView);
        this.mTransformationHelper.addTransformedView(this.mConversationSenderName);
    }

    @Override // com.android.systemui.statusbar.notification.row.HybridNotificationView
    public final void setNotificationFaded(boolean z) {
        NotificationFadeAware.setLayerTypeForFaded(this.mConversationFacePile, z);
    }

    public final void setText(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        if (charSequence3 == null) {
            this.mConversationSenderName.setVisibility(8);
        } else {
            this.mConversationSenderName.setVisibility(0);
            this.mConversationSenderName.setText(charSequence3);
        }
        super.bind(charSequence, charSequence2);
    }

    public HybridConversationNotificationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HybridConversationNotificationView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public HybridConversationNotificationView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }
}
