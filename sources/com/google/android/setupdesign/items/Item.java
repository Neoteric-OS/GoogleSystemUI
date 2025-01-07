package com.google.android.setupdesign.items;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.android.wm.shell.R;
import com.google.android.setupdesign.R$styleable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class Item extends AbstractItem {
    public Item(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SudItem);
        obtainStyledAttributes.getBoolean(1, true);
        obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.getText(4);
        obtainStyledAttributes.getText(5);
        obtainStyledAttributes.getText(6);
        obtainStyledAttributes.getResourceId(2, getDefaultLayoutResource());
        obtainStyledAttributes.getBoolean(3, true);
        obtainStyledAttributes.getColor(8, 0);
        obtainStyledAttributes.getInt(7, 16);
        obtainStyledAttributes.recycle();
    }

    public int getDefaultLayoutResource() {
        return R.layout.sud_items_default;
    }
}
