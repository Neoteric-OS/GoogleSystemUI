package com.android.systemui.accessibility.floatingmenu;

import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MenuListViewTouchHandler implements RecyclerView.OnItemTouchListener {
    public final DragToInteractAnimationController mDragToInteractAnimationController;
    public final MenuAnimationController mMenuAnimationController;
    public float mTouchSlop;
    public final VelocityTracker mVelocityTracker = VelocityTracker.obtain();
    public final PointF mDown = new PointF();
    public final PointF mMenuTranslationDown = new PointF();
    public boolean mIsDragging = false;
    public Optional mOnActionDownEnd = Optional.empty();

    public MenuListViewTouchHandler(MenuAnimationController menuAnimationController, DragToInteractAnimationController dragToInteractAnimationController) {
        this.mMenuAnimationController = menuAnimationController;
        this.mDragToInteractAnimationController = dragToInteractAnimationController;
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0050, code lost:
    
        if (r9 != 3) goto L36;
     */
    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onInterceptTouchEvent(androidx.recyclerview.widget.RecyclerView r21, android.view.MotionEvent r22) {
        /*
            Method dump skipped, instructions count: 398
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.accessibility.floatingmenu.MenuListViewTouchHandler.onInterceptTouchEvent(androidx.recyclerview.widget.RecyclerView, android.view.MotionEvent):boolean");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public final void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public final void onTouchEvent(MotionEvent motionEvent) {
    }
}
