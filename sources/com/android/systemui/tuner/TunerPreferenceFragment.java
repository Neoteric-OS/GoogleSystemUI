package com.android.systemui.tuner;

import android.os.Bundle;
import androidx.preference.DialogPreference;
import androidx.preference.PreferenceFragment;
import com.android.systemui.tuner.CustomListPreference;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class TunerPreferenceFragment extends PreferenceFragment {
    @Override // androidx.preference.PreferenceFragment, androidx.preference.PreferenceManager.OnDisplayPreferenceDialogListener
    public final void onDisplayPreferenceDialog(DialogPreference dialogPreference) {
        CustomListPreference.CustomListPreferenceDialogFragment customListPreferenceDialogFragment;
        if (dialogPreference instanceof CustomListPreference) {
            String str = dialogPreference.mKey;
            customListPreferenceDialogFragment = new CustomListPreference.CustomListPreferenceDialogFragment();
            Bundle bundle = new Bundle(1);
            bundle.putString("key", str);
            customListPreferenceDialogFragment.setArguments(bundle);
        } else {
            super.onDisplayPreferenceDialog(dialogPreference);
            customListPreferenceDialogFragment = null;
        }
        customListPreferenceDialogFragment.setTargetFragment(this, 0);
        customListPreferenceDialogFragment.show(getFragmentManager(), "dialog_preference");
    }
}
