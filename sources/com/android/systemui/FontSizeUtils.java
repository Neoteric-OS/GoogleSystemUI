package com.android.systemui;

import android.R;
import android.content.res.TypedArray;
import android.widget.TextView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class FontSizeUtils {
    public static void updateFontSize(TextView textView, int i) {
        if (textView != null) {
            textView.setTextSize(0, textView.getResources().getDimensionPixelSize(i));
        }
    }

    public static void updateFontSizeFromStyle(TextView textView, int i) {
        TypedArray obtainStyledAttributes = textView.getContext().obtainStyledAttributes(i, new int[]{R.attr.textSize});
        textView.setTextSize(0, obtainStyledAttributes.getDimensionPixelSize(0, (int) textView.getTextSize()));
        obtainStyledAttributes.recycle();
    }
}
