package com.google.android.material.snackbar;

import android.view.MotionEvent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.behavior.SwipeDismissBehavior;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class BaseTransientBottomBar$Behavior extends SwipeDismissBehavior {
    public final BaseTransientBottomBar$BehaviorDelegate delegate;

    public BaseTransientBottomBar$Behavior() {
        BaseTransientBottomBar$BehaviorDelegate baseTransientBottomBar$BehaviorDelegate = new BaseTransientBottomBar$BehaviorDelegate();
        this.alphaStartSwipeDistance = Math.min(Math.max(0.0f, 0.1f), 1.0f);
        this.alphaEndSwipeDistance = Math.min(Math.max(0.0f, 0.6f), 1.0f);
        this.swipeDirection = 0;
        this.delegate = baseTransientBottomBar$BehaviorDelegate;
    }

    @Override // com.google.android.material.behavior.SwipeDismissBehavior
    public final boolean canSwipeDismissView() {
        this.delegate.getClass();
        return false;
    }

    @Override // com.google.android.material.behavior.SwipeDismissBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        this.delegate.getClass();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked == 1 || actionMasked == 3) {
                if (SnackbarManager.snackbarManager == null) {
                    SnackbarManager.snackbarManager = new SnackbarManager();
                }
                synchronized (SnackbarManager.snackbarManager.lock) {
                }
            }
        } else if (coordinatorLayout.isPointInChildBounds(view, (int) motionEvent.getX(), (int) motionEvent.getY())) {
            if (SnackbarManager.snackbarManager == null) {
                SnackbarManager.snackbarManager = new SnackbarManager();
            }
            synchronized (SnackbarManager.snackbarManager.lock) {
            }
        }
        return super.onInterceptTouchEvent(coordinatorLayout, view, motionEvent);
    }
}
