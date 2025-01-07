package com.android.settingslib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import androidx.preference.PreferenceViewHolder;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.settingslib.core.instrumentation.SettingsJankMonitor;
import com.android.settingslib.core.instrumentation.SettingsJankMonitor$detectToggleJank$1;
import com.android.settingslib.widget.SettingsThemeHelper;
import com.android.wm.shell.R;
import java.util.concurrent.TimeUnit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class PrimarySwitchPreference extends RestrictedPreference {
    public boolean mChecked;
    public boolean mCheckedSet;
    public boolean mEnableSwitch;
    public CompoundButton mSwitch;

    public PrimarySwitchPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mEnableSwitch = true;
    }

    public Boolean getCheckedState() {
        if (this.mCheckedSet) {
            return Boolean.valueOf(this.mChecked);
        }
        return null;
    }

    @Override // com.android.settingslib.widget.TwoTargetPreference
    public final int getSecondTargetResId() {
        return SettingsThemeHelper.isExpressiveTheme(this.mContext) ? R.layout.settingslib_expressive_preference_switch : R.layout.preference_widget_switch_compat;
    }

    public boolean isSwitchEnabled() {
        return this.mEnableSwitch;
    }

    @Override // com.android.settingslib.RestrictedPreference, com.android.settingslib.widget.TwoTargetPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View findViewById = preferenceViewHolder.findViewById(android.R.id.widget_frame);
        if (findViewById instanceof LinearLayout) {
            ((LinearLayout) findViewById).setGravity(8388629);
        }
        CompoundButton compoundButton = (CompoundButton) preferenceViewHolder.findViewById(R.id.switchWidget);
        this.mSwitch = compoundButton;
        if (compoundButton != null) {
            compoundButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.settingslib.PrimarySwitchPreference$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PrimarySwitchPreference primarySwitchPreference = PrimarySwitchPreference.this;
                    CompoundButton compoundButton2 = primarySwitchPreference.mSwitch;
                    if (compoundButton2 == null || compoundButton2.isEnabled()) {
                        boolean z = !primarySwitchPreference.mChecked;
                        if (primarySwitchPreference.callChangeListener(Boolean.valueOf(z))) {
                            String str = primarySwitchPreference.mKey;
                            CompoundButton compoundButton3 = primarySwitchPreference.mSwitch;
                            InteractionJankMonitor interactionJankMonitor = SettingsJankMonitor.jankMonitor;
                            InteractionJankMonitor.Configuration.Builder withView = InteractionJankMonitor.Configuration.Builder.withView(57, compoundButton3);
                            if (str != null) {
                                withView.setTag(str);
                            }
                            if (SettingsJankMonitor.jankMonitor.begin(withView)) {
                                SettingsJankMonitor.scheduledExecutorService.schedule(SettingsJankMonitor$detectToggleJank$1.INSTANCE, 300L, TimeUnit.MILLISECONDS);
                            }
                            primarySwitchPreference.setChecked(z);
                            primarySwitchPreference.persistBoolean(z);
                        }
                    }
                }
            });
            this.mSwitch.setOnTouchListener(new PrimarySwitchPreference$$ExternalSyntheticLambda1());
            this.mSwitch.setContentDescription(this.mTitle);
            this.mSwitch.setChecked(this.mChecked);
            this.mSwitch.setEnabled(this.mEnableSwitch);
        }
    }

    public final void setChecked(boolean z) {
        if (this.mChecked == z && this.mCheckedSet) {
            return;
        }
        this.mChecked = z;
        this.mCheckedSet = true;
        CompoundButton compoundButton = this.mSwitch;
        if (compoundButton != null) {
            compoundButton.setChecked(z);
        }
    }

    @Override // com.android.settingslib.widget.TwoTargetPreference
    public final boolean shouldHideSecondTarget() {
        return getSecondTargetResId() == 0;
    }
}
