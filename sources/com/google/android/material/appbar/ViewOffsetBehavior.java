package com.google.android.material.appbar;

import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ViewOffsetBehavior extends CoordinatorLayout.Behavior {
    public ViewOffsetHelper viewOffsetHelper;

    public void layoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
        coordinatorLayout.onLayoutChild(view, i);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
        layoutChild(coordinatorLayout, view, i);
        if (this.viewOffsetHelper == null) {
            this.viewOffsetHelper = new ViewOffsetHelper(view);
        }
        ViewOffsetHelper viewOffsetHelper = this.viewOffsetHelper;
        viewOffsetHelper.layoutTop = viewOffsetHelper.view.getTop();
        viewOffsetHelper.layoutLeft = viewOffsetHelper.view.getLeft();
        ViewOffsetHelper viewOffsetHelper2 = this.viewOffsetHelper;
        View view2 = viewOffsetHelper2.view;
        int top = 0 - (view2.getTop() - viewOffsetHelper2.layoutTop);
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        view2.offsetTopAndBottom(top);
        View view3 = viewOffsetHelper2.view;
        view3.offsetLeftAndRight(0 - (view3.getLeft() - viewOffsetHelper2.layoutLeft));
        return true;
    }
}
