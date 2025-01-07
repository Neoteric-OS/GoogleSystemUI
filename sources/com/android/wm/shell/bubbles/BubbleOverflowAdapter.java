package com.android.wm.shell.bubbles;

import android.content.Context;
import android.content.pm.ShortcutInfo;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.internal.util.ContrastColorUtil;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.wm.shell.R;
import com.android.wm.shell.bubbles.BadgedImageView;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BubbleOverflowAdapter extends RecyclerView.Adapter {
    public List mBubbles;
    public Context mContext;
    public BubblePositioner mPositioner;
    public BubbleOverflowContainerView$$ExternalSyntheticLambda1 mPromoteBubbleFromOverflow;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        public BadgedImageView iconView;
        public TextView textView;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        return this.mBubbles.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        final Bubble bubble = (Bubble) ((ArrayList) this.mBubbles).get(i);
        viewHolder2.iconView.setRenderedBubble(bubble);
        BadgedImageView.SuppressionFlag suppressionFlag = BadgedImageView.SuppressionFlag.FLYOUT_VISIBLE;
        BadgedImageView badgedImageView = viewHolder2.iconView;
        badgedImageView.removeDotSuppressionFlag(suppressionFlag);
        badgedImageView.setOnClickListener(new View.OnClickListener() { // from class: com.android.wm.shell.bubbles.BubbleOverflowAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BubbleOverflowAdapter bubbleOverflowAdapter = BubbleOverflowAdapter.this;
                Bubble bubble2 = bubble;
                bubbleOverflowAdapter.mBubbles.remove(bubble2);
                bubbleOverflowAdapter.notifyDataSetChanged();
                bubbleOverflowAdapter.mPromoteBubbleFromOverflow.accept(bubble2);
            }
        });
        String str = bubble.mTitle;
        if (str == null) {
            str = this.mContext.getResources().getString(R.string.notification_bubble_title);
        }
        badgedImageView.setContentDescription(this.mContext.getResources().getString(R.string.bubble_content_description_single, str, bubble.mAppName));
        badgedImageView.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.android.wm.shell.bubbles.BubbleOverflowAdapter.1
            @Override // android.view.View.AccessibilityDelegate
            public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, BubbleOverflowAdapter.this.mContext.getResources().getString(R.string.bubble_accessibility_action_add_back)));
            }
        });
        ShortcutInfo shortcutInfo = bubble.mShortcutInfo;
        viewHolder2.textView.setText(shortcutInfo != null ? shortcutInfo.getLabel() : bubble.mAppName);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final RecyclerView.ViewHolder onCreateViewHolder(int i, ViewGroup viewGroup) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bubble_overflow_view, viewGroup, false);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{android.R.attr.colorBackgroundFloating, android.R.attr.textColorPrimary});
        int ensureTextContrast = ContrastColorUtil.ensureTextContrast(obtainStyledAttributes.getColor(1, DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT), obtainStyledAttributes.getColor(0, -1), true);
        obtainStyledAttributes.recycle();
        ((TextView) linearLayout.findViewById(R.id.bubble_view_name)).setTextColor(ensureTextContrast);
        ViewHolder viewHolder = new ViewHolder(linearLayout);
        BadgedImageView badgedImageView = (BadgedImageView) linearLayout.findViewById(R.id.bubble_view);
        viewHolder.iconView = badgedImageView;
        badgedImageView.initialize(this.mPositioner);
        viewHolder.textView = (TextView) linearLayout.findViewById(R.id.bubble_view_name);
        return viewHolder;
    }
}
