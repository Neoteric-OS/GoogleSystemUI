package com.google.android.setupdesign.items;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.android.wm.shell.R;
import com.google.android.setupdesign.R$styleable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class ExpandableSwitchItem extends SwitchItem implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    public boolean isExpanded;

    public ExpandableSwitchItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isExpanded = false;
        new AccessibilityDelegateCompat() { // from class: com.google.android.setupdesign.items.ExpandableSwitchItem.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.mInfo);
                accessibilityNodeInfoCompat.addAction(ExpandableSwitchItem.this.isExpanded ? AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE : AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND);
            }

            @Override // androidx.core.view.AccessibilityDelegateCompat
            public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
                if (i != 262144 && i != 524288) {
                    return super.performAccessibilityAction(view, i, bundle);
                }
                ExpandableSwitchItem expandableSwitchItem = ExpandableSwitchItem.this;
                boolean z = expandableSwitchItem.isExpanded;
                boolean z2 = !z;
                if (z != z2) {
                    expandableSwitchItem.isExpanded = z2;
                    expandableSwitchItem.notifyItemRangeChanged(0);
                }
                return true;
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SudExpandableSwitchItem);
        obtainStyledAttributes.getText(0);
        obtainStyledAttributes.getText(1);
        obtainStyledAttributes.getInt(7, 48);
        obtainStyledAttributes.recycle();
    }

    @Override // com.google.android.setupdesign.items.SwitchItem, com.google.android.setupdesign.items.Item
    public final int getDefaultLayoutResource() {
        return R.layout.sud_items_expandable_switch;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        boolean z = this.isExpanded;
        boolean z2 = !z;
        if (z == z2) {
            return;
        }
        this.isExpanded = z2;
        notifyItemRangeChanged(0);
    }
}
