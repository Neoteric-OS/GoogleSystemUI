package com.android.systemui.statusbar.notification.row;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.android.internal.widget.ConversationLayout;
import com.android.internal.widget.ImageFloatingTextView;
import com.android.internal.widget.MessagingLayout;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PrecomputedTextViewFactory implements NotifRemoteViewsFactory {
    @Override // com.android.systemui.statusbar.notification.row.NotifRemoteViewsFactory
    public final View instantiate(ExpandableNotificationRow expandableNotificationRow, int i, String str, Context context, AttributeSet attributeSet) {
        if (str.equals(ImageFloatingTextView.class.getName())) {
            return new PrecomputedImageFloatingTextView(context, attributeSet, 0);
        }
        if (str.equals(MessagingLayout.class.getName())) {
            MessagingLayout messagingLayout = new MessagingLayout(context, attributeSet);
            messagingLayout.setPrecomputedTextEnabled(true);
            return messagingLayout;
        }
        if (!str.equals(ConversationLayout.class.getName())) {
            return null;
        }
        ConversationLayout conversationLayout = new ConversationLayout(context, attributeSet);
        conversationLayout.setPrecomputedTextEnabled(true);
        return conversationLayout;
    }
}
