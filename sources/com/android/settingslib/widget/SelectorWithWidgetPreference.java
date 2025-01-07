package com.android.settingslib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.preference.CheckBoxPreference;
import androidx.preference.PreferenceViewHolder;
import com.android.settingslib.widget.preference.selector.R$styleable;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class SelectorWithWidgetPreference extends CheckBoxPreference {
    static final int DEFAULT_MAX_LINES = 2;
    public final int mAppendixVisibility;
    public ImageView mExtraWidget;
    public View mExtraWidgetContainer;
    public final int mTitleMaxLines;

    public SelectorWithWidgetPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAppendixVisibility = -1;
        this.mWidgetLayoutResId = R.layout.preference_widget_radiobutton;
        this.mLayoutResId = R.layout.preference_selector_with_widget;
        if (this.mIconSpaceReserved) {
            this.mIconSpaceReserved = false;
            notifyChanged();
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SelectorWithWidgetPreference, 0, 0);
        this.mTitleMaxLines = obtainStyledAttributes.getInt(0, 2);
        obtainStyledAttributes.recycle();
    }

    @Override // androidx.preference.CheckBoxPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        int i;
        super.onBindViewHolder(preferenceViewHolder);
        View findViewById = preferenceViewHolder.findViewById(R.id.summary_container);
        if (findViewById != null) {
            findViewById.setVisibility(TextUtils.isEmpty(getSummary()) ? 8 : 0);
            View findViewById2 = preferenceViewHolder.findViewById(R.id.appendix);
            if (findViewById2 != null && (i = this.mAppendixVisibility) != -1) {
                findViewById2.setVisibility(i);
            }
        }
        this.mExtraWidget = (ImageView) preferenceViewHolder.findViewById(R.id.selector_extra_widget);
        View findViewById3 = preferenceViewHolder.findViewById(R.id.selector_extra_widget_container);
        this.mExtraWidgetContainer = findViewById3;
        ImageView imageView = this.mExtraWidget;
        if (imageView != null && findViewById3 != null) {
            imageView.setOnClickListener(null);
            this.mExtraWidgetContainer.setVisibility(8);
        }
        ((TextView) preferenceViewHolder.findViewById(android.R.id.title)).setMaxLines(this.mTitleMaxLines);
    }

    @Override // androidx.preference.TwoStatePreference, androidx.preference.Preference
    public final void onClick() {
    }
}
