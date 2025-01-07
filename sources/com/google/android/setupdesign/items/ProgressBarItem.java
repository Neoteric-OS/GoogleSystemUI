package com.google.android.setupdesign.items;

import android.content.Context;
import android.util.AttributeSet;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class ProgressBarItem extends Item {
    public ProgressBarItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.google.android.setupdesign.items.Item
    public final int getDefaultLayoutResource() {
        return R.layout.sud_items_progress_bar;
    }
}
