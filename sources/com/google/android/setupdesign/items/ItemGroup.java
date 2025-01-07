package com.google.android.setupdesign.items;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class ItemGroup extends AbstractItemHierarchy {
    public final List children;
    public final int count;
    public final SparseIntArray hierarchyStart;

    public ItemGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.children = new ArrayList();
        this.hierarchyStart = new SparseIntArray();
        this.count = 0;
    }
}
