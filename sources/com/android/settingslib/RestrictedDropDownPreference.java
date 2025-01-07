package com.android.settingslib;

import androidx.preference.DropDownPreference;
import androidx.preference.PreferenceViewHolder;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class RestrictedDropDownPreference extends DropDownPreference {
    @Override // androidx.preference.DropDownPreference, androidx.preference.Preference
    public final void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        throw null;
    }

    @Override // androidx.preference.Preference
    public final void performClick() {
        throw null;
    }

    @Override // androidx.preference.Preference
    public final void setEnabled(boolean z) {
        if (z) {
            throw null;
        }
        super.setEnabled(z);
    }
}
