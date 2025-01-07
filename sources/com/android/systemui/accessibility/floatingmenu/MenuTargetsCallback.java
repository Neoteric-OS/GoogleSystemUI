package com.android.systemui.accessibility.floatingmenu;

import android.text.TextUtils;
import androidx.recyclerview.widget.DiffUtil;
import com.android.internal.accessibility.dialog.AccessibilityTarget;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MenuTargetsCallback extends DiffUtil.Callback {
    public final List mNewTargets;
    public final List mOldTargets;

    public MenuTargetsCallback(List list, List list2) {
        ArrayList arrayList = new ArrayList();
        this.mOldTargets = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this.mNewTargets = arrayList2;
        arrayList.addAll(list);
        arrayList2.addAll(list2);
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public final boolean areContentsTheSame(int i, int i2) {
        return TextUtils.equals(((AccessibilityTarget) ((ArrayList) this.mOldTargets).get(i)).getLabel(), ((AccessibilityTarget) ((ArrayList) this.mNewTargets).get(i2)).getLabel()) && TextUtils.equals(((AccessibilityTarget) ((ArrayList) this.mOldTargets).get(i)).getStateDescription(), ((AccessibilityTarget) ((ArrayList) this.mNewTargets).get(i2)).getStateDescription());
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public final boolean areItemsTheSame(int i, int i2) {
        return ((AccessibilityTarget) ((ArrayList) this.mOldTargets).get(i)).getId().equals(((AccessibilityTarget) ((ArrayList) this.mNewTargets).get(i2)).getId());
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public final int getNewListSize() {
        return ((ArrayList) this.mNewTargets).size();
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public final int getOldListSize() {
        return ((ArrayList) this.mOldTargets).size();
    }
}
