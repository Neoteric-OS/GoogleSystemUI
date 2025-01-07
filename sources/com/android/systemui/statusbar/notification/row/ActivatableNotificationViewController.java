package com.android.systemui.statusbar.notification.row;

import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import com.android.systemui.Gefingerpoken;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.util.ViewController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ActivatableNotificationViewController extends ViewController {
    public final AccessibilityManager mAccessibilityManager;
    public final ExpandableOutlineViewController mExpandableOutlineViewController;
    public final FalsingManager mFalsingManager;
    public final TouchHandler mTouchHandler;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TouchHandler implements Gefingerpoken, View.OnTouchListener {
        public TouchHandler() {
        }

        @Override // com.android.systemui.Gefingerpoken
        public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            return false;
        }

        @Override // android.view.View.OnTouchListener
        public final boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1) {
                ((ActivatableNotificationView) ActivatableNotificationViewController.this.mView).mLastActionUpTime = motionEvent.getEventTime();
            }
            if (!ActivatableNotificationViewController.this.mAccessibilityManager.isTouchExplorationEnabled() && motionEvent.getAction() == 1) {
                return ActivatableNotificationViewController.this.mFalsingManager.isFalseTap(1);
            }
            return false;
        }

        @Override // com.android.systemui.Gefingerpoken
        public final boolean onTouchEvent(MotionEvent motionEvent) {
            return false;
        }
    }

    public ActivatableNotificationViewController(ActivatableNotificationView activatableNotificationView, ExpandableOutlineViewController expandableOutlineViewController, AccessibilityManager accessibilityManager, FalsingManager falsingManager) {
        super(activatableNotificationView);
        this.mTouchHandler = new TouchHandler();
        this.mExpandableOutlineViewController = expandableOutlineViewController;
        this.mAccessibilityManager = accessibilityManager;
        this.mFalsingManager = falsingManager;
    }

    @Override // com.android.systemui.util.ViewController
    public final void onInit() {
        this.mExpandableOutlineViewController.mExpandableViewController.getClass();
        ActivatableNotificationView activatableNotificationView = (ActivatableNotificationView) this.mView;
        TouchHandler touchHandler = this.mTouchHandler;
        activatableNotificationView.setOnTouchListener(touchHandler);
        ((ActivatableNotificationView) this.mView).mTouchHandler = touchHandler;
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
    }
}
