package com.android.systemui.statusbar;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.android.systemui.statusbar.notification.row.StackScrollerDecorView;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class EmptyShadeView extends StackScrollerDecorView {
    public TextView mEmptyFooterText;
    public TextView mEmptyText;
    public int mFooterIcon;
    public int mFooterText;
    public int mFooterVisibility;
    public int mSize;
    public int mText;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class EmptyShadeViewState extends ExpandableViewState {
        public EmptyShadeViewState() {
        }

        @Override // com.android.systemui.statusbar.notification.stack.ExpandableViewState, com.android.systemui.statusbar.notification.stack.ViewState
        public final void applyToView(View view) {
            super.applyToView(view);
            if (view instanceof EmptyShadeView) {
                EmptyShadeView emptyShadeView = (EmptyShadeView) view;
                emptyShadeView.setContentVisible(((float) this.clipTopAmount) <= ((float) EmptyShadeView.this.mEmptyText.getPaddingTop()) * 0.6f && emptyShadeView.mIsVisible, true, null);
            }
        }
    }

    public EmptyShadeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mText = R.string.empty_shade_text;
        this.mFooterIcon = R.drawable.ic_friction_lock_closed;
        this.mFooterText = R.string.unlock_to_see_notif_text;
        this.mFooterVisibility = 8;
        this.mSize = getResources().getDimensionPixelSize(R.dimen.notifications_unseen_footer_icon_size);
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final ExpandableViewState createExpandableViewState() {
        return new EmptyShadeViewState();
    }

    @Override // com.android.systemui.statusbar.notification.row.StackScrollerDecorView
    public final View findContentView() {
        return findViewById(R.id.no_notifications);
    }

    @Override // com.android.systemui.statusbar.notification.row.StackScrollerDecorView
    public final View findSecondaryView() {
        return findViewById(R.id.no_notifications_footer);
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView, android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        Drawable drawable;
        super.onConfigurationChanged(configuration);
        this.mSize = getResources().getDimensionPixelSize(R.dimen.notifications_unseen_footer_icon_size);
        this.mEmptyText.setText(this.mText);
        this.mEmptyFooterText.setVisibility(this.mFooterVisibility);
        int i = this.mFooterText;
        this.mFooterText = i;
        if (i != 0) {
            this.mEmptyFooterText.setText(i);
        } else {
            this.mEmptyFooterText.setText((CharSequence) null);
        }
        int i2 = this.mFooterIcon;
        this.mFooterIcon = i2;
        if (i2 == 0) {
            drawable = null;
        } else {
            drawable = getResources().getDrawable(i2);
            int i3 = this.mSize;
            drawable.setBounds(0, 0, i3, i3);
        }
        this.mEmptyFooterText.setCompoundDrawablesRelative(drawable, null, null, null);
    }

    @Override // com.android.systemui.statusbar.notification.row.StackScrollerDecorView, android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mEmptyText = (TextView) findViewById(R.id.no_notifications);
        TextView textView = (TextView) findViewById(R.id.no_notifications_footer);
        this.mEmptyFooterText = textView;
        textView.setCompoundDrawableTintList(textView.getTextColors());
    }
}
