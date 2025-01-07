package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.StaticLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.android.systemui.res.R$styleable;
import com.android.wm.shell.R;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VariableDateView extends TextView {
    public final String longerPattern;
    public VariableDateViewController$onMeasureListener$1 onMeasureListener;
    public final String shorterPattern;

    public VariableDateView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.VariableDateView, 0, 0);
        String string = obtainStyledAttributes.getString(0);
        this.longerPattern = string == null ? context.getString(R.string.system_ui_date_pattern) : string;
        String string2 = obtainStyledAttributes.getString(1);
        this.shorterPattern = string2 == null ? context.getString(R.string.abbrev_month_day_no_year) : string2;
        obtainStyledAttributes.recycle();
    }

    @Override // android.widget.TextView, android.view.View
    public final void onMeasure(int i, int i2) {
        VariableDateViewController$onMeasureListener$1 variableDateViewController$onMeasureListener$1;
        int size = (View.MeasureSpec.getSize(i) - getPaddingStart()) - getPaddingEnd();
        if (View.MeasureSpec.getMode(i) != 0 && (variableDateViewController$onMeasureListener$1 = this.onMeasureListener) != null) {
            VariableDateViewController variableDateViewController = variableDateViewController$onMeasureListener$1.this$0;
            if ((variableDateViewController.isQsExpanded || View.MeasureSpec.getMode(i) != Integer.MIN_VALUE) && size != variableDateViewController.lastWidth) {
                ((VariableDateView) variableDateViewController.mView).getClass();
                if ((size <= variableDateViewController.lastWidth || !Intrinsics.areEqual(variableDateViewController.datePattern, ((VariableDateView) variableDateViewController.mView).longerPattern)) && (size >= variableDateViewController.lastWidth || !Intrinsics.areEqual(variableDateViewController.datePattern, ""))) {
                    float f = size;
                    if (StaticLayout.getDesiredWidth(VariableDateViewControllerKt.getTextForFormat(variableDateViewController.currentTime, VariableDateViewControllerKt.getFormatFromPattern(((VariableDateView) variableDateViewController.mView).longerPattern)), ((VariableDateView) variableDateViewController.mView).getPaint()) <= f) {
                        variableDateViewController.changePattern(((VariableDateView) variableDateViewController.mView).longerPattern);
                    } else if (StaticLayout.getDesiredWidth(VariableDateViewControllerKt.getTextForFormat(variableDateViewController.currentTime, VariableDateViewControllerKt.getFormatFromPattern(((VariableDateView) variableDateViewController.mView).shorterPattern)), ((VariableDateView) variableDateViewController.mView).getPaint()) <= f) {
                        variableDateViewController.changePattern(((VariableDateView) variableDateViewController.mView).shorterPattern);
                    } else {
                        variableDateViewController.changePattern("");
                    }
                }
                variableDateViewController.lastWidth = size;
            }
        }
        super.onMeasure(i, i2);
    }
}
