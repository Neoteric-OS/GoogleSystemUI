package com.android.settingslib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import androidx.preference.R$styleable;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class LayoutPreference extends Preference {
    public final boolean mAllowDividerAbove;
    public final boolean mAllowDividerBelow;
    public final LayoutPreference$$ExternalSyntheticLambda0 mClickListener;
    public final View mRootView;

    /* JADX WARN: Type inference failed for: r0v0, types: [com.android.settingslib.widget.LayoutPreference$$ExternalSyntheticLambda0] */
    public LayoutPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mClickListener = new View.OnClickListener() { // from class: com.android.settingslib.widget.LayoutPreference$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LayoutPreference.this.performClick();
            }
        };
        int[] iArr = R$styleable.Preference;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
        this.mAllowDividerAbove = obtainStyledAttributes.getBoolean(16, obtainStyledAttributes.getBoolean(16, false));
        this.mAllowDividerBelow = obtainStyledAttributes.getBoolean(17, obtainStyledAttributes.getBoolean(17, false));
        obtainStyledAttributes.recycle();
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr, 0, 0);
        int resourceId = obtainStyledAttributes2.getResourceId(3, 0);
        if (resourceId == 0) {
            throw new IllegalArgumentException("LayoutPreference requires a layout to be defined");
        }
        obtainStyledAttributes2.recycle();
        View inflate = LayoutInflater.from(this.mContext).inflate(resourceId, (ViewGroup) null, false);
        this.mLayoutResId = R.layout.layout_preference_frame;
        this.mRootView = inflate;
        if (this.mShouldDisableView) {
            this.mShouldDisableView = false;
            notifyChanged();
        }
    }

    @Override // androidx.preference.Preference
    public final void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        preferenceViewHolder.itemView.setOnClickListener(this.mClickListener);
        boolean z = this.mSelectable;
        preferenceViewHolder.itemView.setFocusable(z);
        preferenceViewHolder.itemView.setClickable(z);
        preferenceViewHolder.mDividerAllowedAbove = this.mAllowDividerAbove;
        preferenceViewHolder.mDividerAllowedBelow = this.mAllowDividerBelow;
        FrameLayout frameLayout = (FrameLayout) preferenceViewHolder.itemView;
        frameLayout.removeAllViews();
        ViewGroup viewGroup = (ViewGroup) this.mRootView.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(this.mRootView);
        }
        frameLayout.addView(this.mRootView);
    }
}
