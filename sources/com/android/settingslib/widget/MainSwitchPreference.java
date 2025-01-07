package com.android.settingslib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.preference.PreferenceViewHolder;
import androidx.preference.R$styleable;
import androidx.preference.TwoStatePreference;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class MainSwitchPreference extends TwoStatePreference implements CompoundButton.OnCheckedChangeListener {
    public MainSwitchBar mMainSwitchBar;
    public final List mSwitchChangeListeners;

    public MainSwitchPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        ArrayList arrayList = new ArrayList();
        this.mSwitchChangeListeners = arrayList;
        this.mLayoutResId = R.layout.settingslib_main_switch_layout;
        arrayList.add(this);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Preference, 0, 0);
            setTitle(obtainStyledAttributes.getText(4));
            setIconSpaceReserved(obtainStyledAttributes.getBoolean(15, true));
            obtainStyledAttributes.recycle();
        }
    }

    @Override // androidx.preference.Preference
    public final void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        preferenceViewHolder.mDividerAllowedAbove = false;
        preferenceViewHolder.mDividerAllowedBelow = false;
        MainSwitchBar mainSwitchBar = (MainSwitchBar) preferenceViewHolder.findViewById(R.id.settingslib_main_switch_bar);
        this.mMainSwitchBar = mainSwitchBar;
        mainSwitchBar.setOnClickListener(new View.OnClickListener() { // from class: com.android.settingslib.widget.MainSwitchPreference$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainSwitchPreference mainSwitchPreference = MainSwitchPreference.this;
                mainSwitchPreference.callChangeListener(Boolean.valueOf(mainSwitchPreference.mChecked));
            }
        });
        setIconSpaceReserved(this.mIconSpaceReserved);
        setChecked(this.mChecked);
        MainSwitchBar mainSwitchBar2 = this.mMainSwitchBar;
        if (mainSwitchBar2 != null) {
            CharSequence charSequence = this.mTitle;
            TextView textView = mainSwitchBar2.mTextView;
            if (textView != null) {
                textView.setText(charSequence);
            }
            MainSwitchBar mainSwitchBar3 = this.mMainSwitchBar;
            mainSwitchBar3.setVisibility(0);
            mainSwitchBar3.mSwitch.setOnCheckedChangeListener(mainSwitchBar3);
        }
        for (CompoundButton.OnCheckedChangeListener onCheckedChangeListener : this.mSwitchChangeListeners) {
            MainSwitchBar mainSwitchBar4 = this.mMainSwitchBar;
            if (!mainSwitchBar4.mSwitchChangeListeners.contains(onCheckedChangeListener)) {
                mainSwitchBar4.mSwitchChangeListeners.add(onCheckedChangeListener);
            }
        }
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        super.setChecked(z);
    }

    @Override // androidx.preference.TwoStatePreference
    public final void setChecked(boolean z) {
        super.setChecked(z);
        MainSwitchBar mainSwitchBar = this.mMainSwitchBar;
        if (mainSwitchBar == null || mainSwitchBar.mSwitch.isChecked() == z) {
            return;
        }
        MainSwitchBar mainSwitchBar2 = this.mMainSwitchBar;
        CompoundButton compoundButton = mainSwitchBar2.mSwitch;
        if (compoundButton != null) {
            compoundButton.setChecked(z);
        }
        mainSwitchBar2.mFrameView.setActivated(z);
    }

    public final void setIconSpaceReserved(boolean z) {
        if (this.mIconSpaceReserved != z) {
            this.mIconSpaceReserved = z;
            notifyChanged();
        }
    }

    @Override // androidx.preference.Preference
    public final void setTitle(CharSequence charSequence) {
        TextView textView;
        super.setTitle(charSequence);
        MainSwitchBar mainSwitchBar = this.mMainSwitchBar;
        if (mainSwitchBar == null || (textView = mainSwitchBar.mTextView) == null) {
            return;
        }
        textView.setText(charSequence);
    }
}
