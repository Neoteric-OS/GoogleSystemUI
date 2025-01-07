package androidx.preference;

import android.os.Bundle;
import android.view.View;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PreferenceRecyclerViewAccessibilityDelegate extends RecyclerViewAccessibilityDelegate {
    public final RecyclerViewAccessibilityDelegate.ItemDelegate mDefaultItemDelegate;
    public final AnonymousClass1 mItemDelegate;
    public final RecyclerView mRecyclerView;

    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.preference.PreferenceRecyclerViewAccessibilityDelegate$1] */
    public PreferenceRecyclerViewAccessibilityDelegate(RecyclerView recyclerView) {
        super(recyclerView);
        this.mDefaultItemDelegate = super.mItemDelegate;
        this.mItemDelegate = new AccessibilityDelegateCompat() { // from class: androidx.preference.PreferenceRecyclerViewAccessibilityDelegate.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                PreferenceRecyclerViewAccessibilityDelegate preferenceRecyclerViewAccessibilityDelegate = PreferenceRecyclerViewAccessibilityDelegate.this;
                preferenceRecyclerViewAccessibilityDelegate.mDefaultItemDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                RecyclerView recyclerView2 = preferenceRecyclerViewAccessibilityDelegate.mRecyclerView;
                recyclerView2.getClass();
                int childAdapterPosition = RecyclerView.getChildAdapterPosition(view);
                RecyclerView.Adapter adapter = recyclerView2.mAdapter;
                if (adapter instanceof PreferenceGroupAdapter) {
                    ((PreferenceGroupAdapter) adapter).getItem(childAdapterPosition);
                }
            }

            @Override // androidx.core.view.AccessibilityDelegateCompat
            public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
                return PreferenceRecyclerViewAccessibilityDelegate.this.mDefaultItemDelegate.performAccessibilityAction(view, i, bundle);
            }
        };
        this.mRecyclerView = recyclerView;
    }

    @Override // androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate
    public final AccessibilityDelegateCompat getItemDelegate() {
        return this.mItemDelegate;
    }
}
