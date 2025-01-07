package com.android.systemui.accessibility.floatingmenu;

import android.content.res.Resources;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;
import com.android.systemui.accessibility.floatingmenu.MenuViewLayer;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MenuItemAccessibilityDelegate extends RecyclerViewAccessibilityDelegate.ItemDelegate {
    public final MenuAnimationController mAnimationController;
    public final MenuViewLayer mMenuViewLayer;

    public MenuItemAccessibilityDelegate(MenuViewLayer.AnonymousClass2 anonymousClass2, MenuAnimationController menuAnimationController, MenuViewLayer menuViewLayer) {
        super(anonymousClass2);
        this.mAnimationController = menuAnimationController;
        this.mMenuViewLayer = menuViewLayer;
    }

    @Override // androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate.ItemDelegate, androidx.core.view.AccessibilityDelegateCompat
    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        Resources resources = view.getResources();
        accessibilityNodeInfoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.action_move_top_left, resources.getString(R.string.accessibility_floating_button_action_move_top_left)));
        accessibilityNodeInfoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.action_move_top_right, resources.getString(R.string.accessibility_floating_button_action_move_top_right)));
        accessibilityNodeInfoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.action_move_bottom_left, resources.getString(R.string.accessibility_floating_button_action_move_bottom_left)));
        accessibilityNodeInfoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.action_move_bottom_right, resources.getString(R.string.accessibility_floating_button_action_move_bottom_right)));
        boolean z = this.mAnimationController.mMenuView.mIsMoveToTucked;
        accessibilityNodeInfoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(z ? R.id.action_move_out_edge_and_show : R.id.action_move_to_edge_and_hide, resources.getString(z ? R.string.accessibility_floating_button_action_move_out_edge_and_show : R.string.accessibility_floating_button_action_move_to_edge_and_hide_to_half)));
        accessibilityNodeInfoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.action_remove_menu, resources.getString(R.string.accessibility_floating_button_action_remove_menu)));
        accessibilityNodeInfoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.action_edit, resources.getString(R.string.accessibility_floating_button_action_edit)));
    }

    @Override // androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate.ItemDelegate, androidx.core.view.AccessibilityDelegateCompat
    public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        MenuAnimationController menuAnimationController = this.mAnimationController;
        if (i == 64) {
            menuAnimationController.fadeInNowIfEnabled();
        }
        if (i == 128) {
            menuAnimationController.fadeOutIfEnabled();
        }
        if (i == R.id.action_move_top_left) {
            MenuView menuView = menuAnimationController.mMenuView;
            menuView.updateMenuMoveToTucked(false);
            Rect menuDraggableBounds = menuView.getMenuDraggableBounds();
            menuAnimationController.moveAndPersistPosition(new PointF(menuDraggableBounds.left, menuDraggableBounds.top));
            return true;
        }
        if (i == R.id.action_move_top_right) {
            MenuView menuView2 = menuAnimationController.mMenuView;
            menuView2.updateMenuMoveToTucked(false);
            Rect menuDraggableBounds2 = menuView2.getMenuDraggableBounds();
            menuAnimationController.moveAndPersistPosition(new PointF(menuDraggableBounds2.right, menuDraggableBounds2.top));
            return true;
        }
        if (i == R.id.action_move_bottom_left) {
            MenuView menuView3 = menuAnimationController.mMenuView;
            menuView3.updateMenuMoveToTucked(false);
            Rect menuDraggableBounds3 = menuView3.getMenuDraggableBounds();
            menuAnimationController.moveAndPersistPosition(new PointF(menuDraggableBounds3.left, menuDraggableBounds3.bottom));
            return true;
        }
        if (i == R.id.action_move_bottom_right) {
            MenuView menuView4 = menuAnimationController.mMenuView;
            menuView4.updateMenuMoveToTucked(false);
            Rect menuDraggableBounds4 = menuView4.getMenuDraggableBounds();
            menuAnimationController.moveAndPersistPosition(new PointF(menuDraggableBounds4.right, menuDraggableBounds4.bottom));
            return true;
        }
        if (i == R.id.action_move_to_edge_and_hide) {
            menuAnimationController.moveToEdgeAndHide();
            return true;
        }
        if (i == R.id.action_move_out_edge_and_show) {
            menuAnimationController.moveOutEdgeAndShow();
            return true;
        }
        if (i != R.id.action_remove_menu && i != R.id.action_edit) {
            return super.performAccessibilityAction(view, i, bundle);
        }
        this.mMenuViewLayer.dispatchAccessibilityAction(i);
        return true;
    }
}
