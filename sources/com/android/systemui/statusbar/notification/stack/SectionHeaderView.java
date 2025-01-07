package com.android.systemui.statusbar.notification.stack;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.systemui.statusbar.notification.collection.render.SectionHeaderNodeControllerImpl$onHeaderClickListener$1;
import com.android.systemui.statusbar.notification.row.StackScrollerDecorView;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class SectionHeaderView extends StackScrollerDecorView {
    public ImageView mClearAllButton;
    public ViewGroup mContents;
    public SectionHeaderNodeControllerImpl$onHeaderClickListener$1 mLabelClickListener;
    public Integer mLabelTextId;
    public TextView mLabelView;
    public View.OnClickListener mOnClearClickListener;

    public SectionHeaderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mLabelClickListener = null;
        this.mOnClearClickListener = null;
    }

    @Override // com.android.systemui.statusbar.notification.row.StackScrollerDecorView
    public final View findContentView() {
        return this.mContents;
    }

    @Override // com.android.systemui.statusbar.notification.row.StackScrollerDecorView
    public final View findSecondaryView() {
        return null;
    }

    @Override // com.android.systemui.statusbar.notification.row.StackScrollerDecorView, android.view.View
    public final void onFinishInflate() {
        this.mContents = (ViewGroup) requireViewById(R.id.content);
        this.mLabelView = (TextView) requireViewById(R.id.header_label);
        ImageView imageView = (ImageView) requireViewById(R.id.btn_clear_all);
        this.mClearAllButton = imageView;
        View.OnClickListener onClickListener = this.mOnClearClickListener;
        if (onClickListener != null) {
            imageView.setOnClickListener(onClickListener);
        }
        SectionHeaderNodeControllerImpl$onHeaderClickListener$1 sectionHeaderNodeControllerImpl$onHeaderClickListener$1 = this.mLabelClickListener;
        if (sectionHeaderNodeControllerImpl$onHeaderClickListener$1 != null) {
            this.mLabelView.setOnClickListener(sectionHeaderNodeControllerImpl$onHeaderClickListener$1);
        }
        Integer num = this.mLabelTextId;
        if (num != null) {
            this.mLabelView.setText(num.intValue());
        }
        this.mLabelView.setAccessibilityHeading(true);
        super.onFinishInflate();
        setVisible(true, false);
    }
}
