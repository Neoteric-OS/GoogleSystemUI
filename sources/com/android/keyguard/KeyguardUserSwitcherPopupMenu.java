package com.android.keyguard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import com.android.systemui.plugins.FalsingManager;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardUserSwitcherPopupMenu extends ListPopupWindow {
    public Context mContext;
    public FalsingManager mFalsingManager;

    @Override // android.widget.ListPopupWindow
    public final void show() {
        super.show();
        ListView listView = getListView();
        listView.setVerticalScrollBarEnabled(false);
        listView.setHorizontalScrollBarEnabled(false);
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        shapeDrawable.setAlpha(0);
        listView.setDivider(shapeDrawable);
        listView.setDividerHeight(this.mContext.getResources().getDimensionPixelSize(R.dimen.bouncer_user_switcher_popup_divider_height));
        if (listView.getTag(R.id.header_footer_views_added_tag_key) == null) {
            final int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.bouncer_user_switcher_popup_header_height);
            listView.addHeaderView(new View(this.mContext) { // from class: com.android.keyguard.KeyguardUserSwitcherPopupMenu.1
                @Override // android.view.View
                public final void onMeasure(int i, int i2) {
                    setMeasuredDimension(1, dimensionPixelSize);
                }

                @Override // android.view.View
                public final void draw(Canvas canvas) {
                }
            }, null, false);
            listView.addFooterView(new View(this.mContext) { // from class: com.android.keyguard.KeyguardUserSwitcherPopupMenu.1
                @Override // android.view.View
                public final void onMeasure(int i, int i2) {
                    setMeasuredDimension(1, dimensionPixelSize);
                }

                @Override // android.view.View
                public final void draw(Canvas canvas) {
                }
            }, null, false);
            listView.setTag(R.id.header_footer_views_added_tag_key, new Object());
        }
        listView.setOnTouchListener(new View.OnTouchListener() { // from class: com.android.keyguard.KeyguardUserSwitcherPopupMenu$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                KeyguardUserSwitcherPopupMenu keyguardUserSwitcherPopupMenu = KeyguardUserSwitcherPopupMenu.this;
                keyguardUserSwitcherPopupMenu.getClass();
                if (motionEvent.getActionMasked() == 0) {
                    return keyguardUserSwitcherPopupMenu.mFalsingManager.isFalseTap(1);
                }
                return false;
            }
        });
        super.show();
    }
}
