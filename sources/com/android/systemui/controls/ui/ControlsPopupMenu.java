package com.android.systemui.controls.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.PopupWindow;
import com.android.wm.shell.R;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsPopupMenu extends ListPopupWindow {
    public final Drawable dialogBackground;
    public final Drawable dimDrawable;
    public PopupWindow.OnDismissListener dismissListener;
    public int dropDownGravity;
    public final int horizontalMargin;
    public final int listDividerHeight;
    public final int maxWidth;
    public final Resources resources;

    public ControlsPopupMenu(Context context) {
        super(context);
        Resources resources = context.getResources();
        this.resources = resources;
        this.listDividerHeight = resources.getDimensionPixelSize(R.dimen.control_popup_items_divider_height);
        this.horizontalMargin = resources.getDimensionPixelSize(R.dimen.control_popup_horizontal_margin);
        this.maxWidth = resources.getDimensionPixelSize(R.dimen.control_popup_max_width);
        Drawable drawable = resources.getDrawable(R.drawable.controls_popup_bg);
        Intrinsics.checkNotNull(drawable);
        this.dialogBackground = drawable;
        this.dimDrawable = new ColorDrawable(resources.getColor(R.color.control_popup_dim));
        setBackgroundDrawable(drawable);
        setInputMethodMode(2);
        setModal(true);
        super.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.android.systemui.controls.ui.ControlsPopupMenu.1
            @Override // android.widget.PopupWindow.OnDismissListener
            public final void onDismiss() {
                View anchorView = ControlsPopupMenu.this.getAnchorView();
                View rootView = anchorView != null ? anchorView.getRootView() : null;
                if (rootView != null) {
                    rootView.setForeground(null);
                }
                PopupWindow.OnDismissListener onDismissListener = ControlsPopupMenu.this.dismissListener;
                if (onDismissListener != null) {
                    onDismissListener.onDismiss();
                }
            }
        });
    }

    @Override // android.widget.ListPopupWindow
    public final void setDropDownGravity(int i) {
        super.setDropDownGravity(i);
        this.dropDownGravity = i;
    }

    @Override // android.widget.ListPopupWindow
    public final void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.dismissListener = onDismissListener;
    }

    @Override // android.widget.ListPopupWindow
    public final void show() {
        super.show();
        int i = this.resources.getDisplayMetrics().widthPixels - (this.horizontalMargin * 2);
        int i2 = this.maxWidth;
        if (i2 <= i) {
            i = i2;
        }
        int width = getWidth();
        if (width == -2) {
            ListView listView = getListView();
            Intrinsics.checkNotNull(listView);
            int count = listView.getAdapter().getCount();
            int i3 = 0;
            for (int i4 = 0; i4 < count; i4++) {
                View view = listView.getAdapter().getView(i4, null, getListView());
                view.measure(View.MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE), 0);
                i3 = Math.max(i3, view.getMeasuredWidth());
            }
            if (i3 <= i) {
                i = i3;
            }
            setWidth(i);
        } else if (width == -1) {
            setWidth(i);
        }
        View anchorView = getAnchorView();
        if (anchorView != null) {
            int i5 = this.dropDownGravity;
            if (i5 == 0) {
                setHorizontalOffset((anchorView.getWidth() + (-getWidth())) / 2);
                if (anchorView.getLayoutDirection() == 1) {
                    setHorizontalOffset(-getHorizontalOffset());
                }
            } else if (i5 == 8388611 || i5 == 8388613) {
                setHorizontalOffset(0);
            }
            setVerticalOffset((-anchorView.getHeight()) / 2);
            anchorView.getRootView().setForeground(this.dimDrawable);
        }
        ListView listView2 = getListView();
        Intrinsics.checkNotNull(listView2);
        listView2.setClipToOutline(true);
        listView2.setBackground(this.dialogBackground);
        listView2.setDividerHeight(this.listDividerHeight);
        super.show();
    }
}
