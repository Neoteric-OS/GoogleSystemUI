package com.android.systemui.controls.management;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.android.wm.shell.R;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlHolderAccessibilityDelegate extends AccessibilityDelegateCompat {
    public boolean isFavorite;
    public final FavoritesModel$moveHelper$1 moveHelper;
    public final Function0 positionRetriever;
    public final Function1 stateRetriever;

    public ControlHolderAccessibilityDelegate(Function1 function1, Function0 function0, FavoritesModel$moveHelper$1 favoritesModel$moveHelper$1) {
        this.stateRetriever = function1;
        this.positionRetriever = function0;
        this.moveHelper = favoritesModel$moveHelper$1;
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        int intValue;
        int intValue2;
        this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.mInfo);
        boolean z = false;
        accessibilityNodeInfoCompat.mInfo.setContextClickable(false);
        String string = this.isFavorite ? view.getContext().getString(R.string.accessibility_control_change_unfavorite) : view.getContext().getString(R.string.accessibility_control_change_favorite);
        Intrinsics.checkNotNull(string);
        accessibilityNodeInfoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(16, string));
        Function0 function0 = this.positionRetriever;
        FavoritesModel$moveHelper$1 favoritesModel$moveHelper$1 = this.moveHelper;
        if (favoritesModel$moveHelper$1 != null && (intValue2 = ((Number) ((ControlHolder$accessibilityDelegate$2) function0).invoke()).intValue()) > 0 && intValue2 < favoritesModel$moveHelper$1.this$0.dividerPosition) {
            z = true;
        }
        if (z) {
            accessibilityNodeInfoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.accessibility_action_controls_move_before, view.getContext().getString(R.string.accessibility_control_move, Integer.valueOf(((Number) ((ControlHolder$accessibilityDelegate$2) function0).invoke()).intValue()))));
            accessibilityNodeInfoCompat.mInfo.setContextClickable(true);
        }
        if (favoritesModel$moveHelper$1 != null && (intValue = ((Number) ((ControlHolder$accessibilityDelegate$2) function0).invoke()).intValue()) >= 0 && intValue < favoritesModel$moveHelper$1.this$0.dividerPosition - 1) {
            accessibilityNodeInfoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.accessibility_action_controls_move_after, view.getContext().getString(R.string.accessibility_control_move, Integer.valueOf(((Number) ((ControlHolder$accessibilityDelegate$2) function0).invoke()).intValue() + 2))));
            accessibilityNodeInfoCompat.mInfo.setContextClickable(true);
        }
        accessibilityNodeInfoCompat.mInfo.setStateDescription((CharSequence) ((ControlHolder$accessibilityDelegate$1) this.stateRetriever).invoke(Boolean.valueOf(this.isFavorite)));
        accessibilityNodeInfoCompat.setCollectionItemInfo(null);
        accessibilityNodeInfoCompat.setClassName("android.widget.Switch");
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        if (super.performAccessibilityAction(view, i, bundle)) {
            return true;
        }
        Function0 function0 = this.positionRetriever;
        FavoritesModel$moveHelper$1 favoritesModel$moveHelper$1 = this.moveHelper;
        if (i == R.id.accessibility_action_controls_move_before) {
            if (favoritesModel$moveHelper$1 == null) {
                return true;
            }
            int intValue = ((Number) ((ControlHolder$accessibilityDelegate$2) function0).invoke()).intValue();
            if (intValue > 0) {
                FavoritesModel favoritesModel = favoritesModel$moveHelper$1.this$0;
                if (intValue < favoritesModel.dividerPosition) {
                    favoritesModel.onMoveItemInternal(intValue, intValue - 1);
                    return true;
                }
            }
            Log.w("FavoritesModel", "Cannot move position " + intValue + " before");
            return true;
        }
        if (i != R.id.accessibility_action_controls_move_after) {
            return false;
        }
        if (favoritesModel$moveHelper$1 == null) {
            return true;
        }
        int intValue2 = ((Number) ((ControlHolder$accessibilityDelegate$2) function0).invoke()).intValue();
        if (intValue2 >= 0) {
            FavoritesModel favoritesModel2 = favoritesModel$moveHelper$1.this$0;
            if (intValue2 < favoritesModel2.dividerPosition - 1) {
                favoritesModel2.onMoveItemInternal(intValue2, intValue2 + 1);
                return true;
            }
        }
        Log.w("FavoritesModel", "Cannot move position " + intValue2 + " after");
        return true;
    }
}
