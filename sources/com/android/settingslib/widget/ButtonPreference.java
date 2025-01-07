package com.android.settingslib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import androidx.preference.R$styleable;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class ButtonPreference extends Preference {
    public Button mButton;
    public int mGravity;
    public Drawable mIcon;
    public CharSequence mTitle;

    public ButtonPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0, 0);
        this.mLayoutResId = R.layout.settingslib_button_layout;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Preference, 0, 0);
            this.mTitle = obtainStyledAttributes.getText(4);
            this.mIcon = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, com.android.settingslib.widget.preference.button.R$styleable.ButtonPreference, 0, 0);
            this.mGravity = obtainStyledAttributes2.getInt(0, 8388611);
            obtainStyledAttributes2.recycle();
        }
    }

    @Override // androidx.preference.Preference
    public final CharSequence getTitle() {
        return this.mTitle;
    }

    @Override // androidx.preference.Preference
    public final void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        this.mButton = (Button) preferenceViewHolder.findViewById(R.id.settingslib_button);
        setTitle(this.mTitle);
        setIcon(this.mIcon);
        int i = this.mGravity;
        if (i == 1 || i == 16 || i == 17) {
            this.mGravity = 1;
        } else {
            this.mGravity = 8388611;
        }
        Button button = this.mButton;
        if (button != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
            layoutParams.gravity = this.mGravity;
            this.mButton.setLayoutParams(layoutParams);
        }
        Button button2 = this.mButton;
        if (button2 != null) {
            button2.setOnClickListener(null);
        }
        Button button3 = this.mButton;
        if (button3 != null) {
            boolean z = this.mSelectable;
            button3.setFocusable(z);
            this.mButton.setClickable(z);
            this.mButton.setEnabled(isEnabled());
        }
        preferenceViewHolder.mDividerAllowedAbove = false;
        preferenceViewHolder.mDividerAllowedBelow = false;
    }

    @Override // androidx.preference.Preference
    public final void setEnabled(boolean z) {
        super.setEnabled(z);
        Button button = this.mButton;
        if (button != null) {
            button.setEnabled(z);
        }
    }

    @Override // androidx.preference.Preference
    public final void setIcon(Drawable drawable) {
        this.mIcon = drawable;
        if (this.mButton == null || drawable == null) {
            return;
        }
        int applyDimension = (int) TypedValue.applyDimension(1, 24.0f, this.mContext.getResources().getDisplayMetrics());
        drawable.setBounds(0, 0, applyDimension, applyDimension);
        this.mButton.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
    }

    @Override // androidx.preference.Preference
    public final void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        Button button = this.mButton;
        if (button != null) {
            button.setText(charSequence);
        }
    }
}
