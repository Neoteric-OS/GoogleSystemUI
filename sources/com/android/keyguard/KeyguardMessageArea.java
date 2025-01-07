package com.android.keyguard;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class KeyguardMessageArea extends TextView implements SecurityMessageDisplay {
    public ViewGroup mContainer;
    public boolean mIsDisabled;
    public boolean mIsVisible;
    public CharSequence mMessage;
    public final int mStyleResId;
    public int mTopMargin;

    public KeyguardMessageArea(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsDisabled = false;
        setLayerType(2, null);
        if (attributeSet != null) {
            this.mStyleResId = attributeSet.getStyleAttribute();
        } else {
            this.mStyleResId = R.style.Keyguard_TextView;
        }
        onThemeChanged();
    }

    @Override // android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mContainer = (ViewGroup) getRootView().findViewById(R.id.keyguard_message_area_container);
    }

    public final void onDensityOrFontScaleChanged() {
        TypedArray obtainStyledAttributes = ((TextView) this).mContext.obtainStyledAttributes(this.mStyleResId, new int[]{android.R.attr.textSize});
        setTextSize(0, obtainStyledAttributes.getDimensionPixelSize(0, 0));
        obtainStyledAttributes.recycle();
    }

    public void onThemeChanged() {
        update();
    }

    public void setMessage(CharSequence charSequence, boolean z) {
        if (TextUtils.isEmpty(charSequence)) {
            this.mMessage = null;
            update();
        } else {
            this.mMessage = charSequence;
            update();
        }
    }

    public final void update() {
        if (this.mIsDisabled) {
            setVisibility(8);
            return;
        }
        CharSequence charSequence = this.mMessage;
        setVisibility((TextUtils.isEmpty(charSequence) || !this.mIsVisible) ? 4 : 0);
        setText(charSequence);
        updateTextColor();
    }

    public abstract void updateTextColor();
}
