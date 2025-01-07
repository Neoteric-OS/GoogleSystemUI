package com.google.android.material.behavior;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.widget.ViewDragHelper;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class SwipeDismissBehavior extends CoordinatorLayout.Behavior {
    public boolean interceptingEvents;
    public boolean requestingDisallowInterceptTouchEvent;
    public ViewDragHelper viewDragHelper;
    public int swipeDirection = 2;
    public float alphaStartSwipeDistance = 0.0f;
    public float alphaEndSwipeDistance = 0.5f;
    public final AnonymousClass1 dragCallback = new ViewDragHelper.Callback() { // from class: com.google.android.material.behavior.SwipeDismissBehavior.1
        public int activePointerId = -1;
        public int originalCapturedViewLeft;

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public final int clampViewPositionHorizontal(View view, int i) {
            int width;
            int width2;
            int width3;
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            boolean z = view.getLayoutDirection() == 1;
            int i2 = SwipeDismissBehavior.this.swipeDirection;
            if (i2 == 0) {
                if (z) {
                    width = this.originalCapturedViewLeft - view.getWidth();
                    width2 = this.originalCapturedViewLeft;
                } else {
                    width = this.originalCapturedViewLeft;
                    width3 = view.getWidth();
                    width2 = width3 + width;
                }
            } else if (i2 != 1) {
                width = this.originalCapturedViewLeft - view.getWidth();
                width2 = this.originalCapturedViewLeft + view.getWidth();
            } else if (z) {
                width = this.originalCapturedViewLeft;
                width3 = view.getWidth();
                width2 = width3 + width;
            } else {
                width = this.originalCapturedViewLeft - view.getWidth();
                width2 = this.originalCapturedViewLeft;
            }
            return Math.min(Math.max(width, i), width2);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public final int clampViewPositionVertical(View view, int i) {
            return view.getTop();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public final int getViewHorizontalDragRange(View view) {
            return view.getWidth();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public final void onViewCaptured(View view, int i) {
            this.activePointerId = i;
            this.originalCapturedViewLeft = view.getLeft();
            ViewParent parent = view.getParent();
            if (parent != null) {
                SwipeDismissBehavior swipeDismissBehavior = SwipeDismissBehavior.this;
                swipeDismissBehavior.requestingDisallowInterceptTouchEvent = true;
                parent.requestDisallowInterceptTouchEvent(true);
                swipeDismissBehavior.requestingDisallowInterceptTouchEvent = false;
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public final void onViewPositionChanged(View view, int i, int i2) {
            float width = view.getWidth();
            SwipeDismissBehavior swipeDismissBehavior = SwipeDismissBehavior.this;
            float f = width * swipeDismissBehavior.alphaStartSwipeDistance;
            float width2 = view.getWidth() * swipeDismissBehavior.alphaEndSwipeDistance;
            float abs = Math.abs(i - this.originalCapturedViewLeft);
            if (abs <= f) {
                view.setAlpha(1.0f);
            } else if (abs >= width2) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(Math.min(Math.max(0.0f, 1.0f - ((abs - f) / (width2 - f))), 1.0f));
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x0050, code lost:
        
            if (java.lang.Math.abs(r9.getLeft() - r8.originalCapturedViewLeft) >= java.lang.Math.round(r9.getWidth() * 0.5f)) goto L27;
         */
        @Override // androidx.customview.widget.ViewDragHelper.Callback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void onViewReleased(android.view.View r9, float r10, float r11) {
            /*
                r8 = this;
                r11 = -1
                r8.activePointerId = r11
                int r11 = r9.getWidth()
                r0 = 0
                int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
                com.google.android.material.behavior.SwipeDismissBehavior r2 = com.google.android.material.behavior.SwipeDismissBehavior.this
                r3 = 1
                r4 = 0
                if (r1 == 0) goto L39
                java.util.WeakHashMap r5 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
                int r5 = r9.getLayoutDirection()
                if (r5 != r3) goto L1a
                r5 = r3
                goto L1b
            L1a:
                r5 = r4
            L1b:
                int r6 = r2.swipeDirection
                r7 = 2
                if (r6 != r7) goto L21
                goto L52
            L21:
                if (r6 != 0) goto L2d
                if (r5 == 0) goto L2a
                int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
                if (r1 >= 0) goto L66
                goto L52
            L2a:
                if (r1 <= 0) goto L66
                goto L52
            L2d:
                if (r6 != r3) goto L66
                if (r5 == 0) goto L34
                if (r1 <= 0) goto L66
                goto L52
            L34:
                int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
                if (r1 >= 0) goto L66
                goto L52
            L39:
                int r1 = r9.getLeft()
                int r5 = r8.originalCapturedViewLeft
                int r1 = r1 - r5
                int r5 = r9.getWidth()
                float r5 = (float) r5
                r6 = 1056964608(0x3f000000, float:0.5)
                float r5 = r5 * r6
                int r5 = java.lang.Math.round(r5)
                int r1 = java.lang.Math.abs(r1)
                if (r1 < r5) goto L66
            L52:
                int r10 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
                if (r10 < 0) goto L61
                int r10 = r9.getLeft()
                int r0 = r8.originalCapturedViewLeft
                if (r10 >= r0) goto L5f
                goto L61
            L5f:
                int r0 = r0 + r11
                goto L69
            L61:
                int r8 = r8.originalCapturedViewLeft
                int r0 = r8 - r11
                goto L69
            L66:
                int r0 = r8.originalCapturedViewLeft
                r3 = r4
            L69:
                androidx.customview.widget.ViewDragHelper r8 = r2.viewDragHelper
                int r10 = r9.getTop()
                boolean r8 = r8.settleCapturedViewAt(r0, r10)
                if (r8 == 0) goto L7f
                com.google.android.material.behavior.SwipeDismissBehavior$SettleRunnable r8 = new com.google.android.material.behavior.SwipeDismissBehavior$SettleRunnable
                r8.<init>(r9, r3)
                java.util.WeakHashMap r10 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
                r9.postOnAnimation(r8)
            L7f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.behavior.SwipeDismissBehavior.AnonymousClass1.onViewReleased(android.view.View, float, float):void");
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public final boolean tryCaptureView(View view, int i) {
            int i2 = this.activePointerId;
            return (i2 == -1 || i2 == i) && SwipeDismissBehavior.this.canSwipeDismissView();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public final void onViewDragStateChanged(int i) {
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface OnDismissListener {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SettleRunnable implements Runnable {
        public final View view;

        public SettleRunnable(View view, boolean z) {
            this.view = view;
        }

        @Override // java.lang.Runnable
        public final void run() {
            ViewDragHelper viewDragHelper = SwipeDismissBehavior.this.viewDragHelper;
            if (viewDragHelper == null || !viewDragHelper.continueSettling()) {
                return;
            }
            View view = this.view;
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            view.postOnAnimation(this);
        }
    }

    public boolean canSwipeDismissView() {
        return true;
    }

    public OnDismissListener getListener() {
        return null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        boolean z = this.interceptingEvents;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            z = coordinatorLayout.isPointInChildBounds(view, (int) motionEvent.getX(), (int) motionEvent.getY());
            this.interceptingEvents = z;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.interceptingEvents = false;
        }
        if (!z) {
            return false;
        }
        if (this.viewDragHelper == null) {
            this.viewDragHelper = new ViewDragHelper(coordinatorLayout.getContext(), coordinatorLayout, this.dragCallback);
        }
        return !this.requestingDisallowInterceptTouchEvent && this.viewDragHelper.shouldInterceptTouchEvent(motionEvent);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        if (view.getImportantForAccessibility() == 0) {
            view.setImportantForAccessibility(1);
            ViewCompat.removeActionWithId(view, 1048576);
            ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(view, 0);
            if (canSwipeDismissView()) {
                ViewCompat.replaceAccessibilityAction(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_DISMISS, null, new AccessibilityViewCommand() { // from class: com.google.android.material.behavior.SwipeDismissBehavior.2
                    @Override // androidx.core.view.accessibility.AccessibilityViewCommand
                    public final boolean perform(View view2) {
                        SwipeDismissBehavior swipeDismissBehavior = SwipeDismissBehavior.this;
                        if (!swipeDismissBehavior.canSwipeDismissView()) {
                            return false;
                        }
                        WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                        boolean z = view2.getLayoutDirection() == 1;
                        int i2 = swipeDismissBehavior.swipeDirection;
                        view2.offsetLeftAndRight((!(i2 == 0 && z) && (i2 != 1 || z)) ? view2.getWidth() : -view2.getWidth());
                        view2.setAlpha(0.0f);
                        return true;
                    }
                });
            }
        }
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean onTouchEvent(View view, MotionEvent motionEvent) {
        if (this.viewDragHelper == null) {
            return false;
        }
        if (this.requestingDisallowInterceptTouchEvent && motionEvent.getActionMasked() == 3) {
            return true;
        }
        this.viewDragHelper.processTouchEvent(motionEvent);
        return true;
    }
}
