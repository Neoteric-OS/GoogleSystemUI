package com.android.settingslib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.settingslib.widget.preference.barchart.R$styleable;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class BarView extends LinearLayout {
    public TextView mBarSummary;
    public TextView mBarTitle;
    public View mBarView;

    public BarView(Context context) {
        super(context);
        init();
    }

    public CharSequence getSummary() {
        return this.mBarSummary.getText();
    }

    public CharSequence getTitle() {
        return this.mBarTitle.getText();
    }

    public final void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.settings_bar_view, this);
        setOrientation(1);
        setGravity(81);
        this.mBarView = findViewById(R.id.bar_view);
        this.mBarTitle = (TextView) findViewById(R.id.bar_title);
        this.mBarSummary = (TextView) findViewById(R.id.bar_summary);
    }

    public BarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
        int color = context.obtainStyledAttributes(new int[]{android.R.attr.colorAccent}).getColor(0, 0);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SettingsBarView);
        int color2 = obtainStyledAttributes.getColor(0, color);
        obtainStyledAttributes.recycle();
        this.mBarView.setBackgroundColor(color2);
    }
}
