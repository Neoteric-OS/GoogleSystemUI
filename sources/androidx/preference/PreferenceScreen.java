package androidx.preference;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.PreferenceFragment;
import com.android.systemui.tuner.TunerActivity;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PreferenceScreen extends PreferenceGroup {
    public final boolean mShouldUseGeneratedIds;

    public PreferenceScreen(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, TypedArrayUtils.getAttr(R.attr.preferenceScreenStyle, android.R.attr.preferenceScreenStyle, context));
        this.mShouldUseGeneratedIds = true;
    }

    @Override // androidx.preference.Preference
    public final void onClick() {
        PreferenceFragment preferenceFragment;
        if (this.mIntent == null && this.mFragment == null && getPreferenceCount() != 0 && (preferenceFragment = this.mPreferenceManager.mOnNavigateToScreenListener) != null && (preferenceFragment.getActivity() instanceof PreferenceFragment.OnPreferenceStartScreenCallback)) {
            FragmentTransaction beginTransaction = ((TunerActivity) ((PreferenceFragment.OnPreferenceStartScreenCallback) preferenceFragment.getActivity())).getFragmentManager().beginTransaction();
            TunerActivity.SubSettingsFragment subSettingsFragment = new TunerActivity.SubSettingsFragment();
            Bundle bundle = new Bundle(1);
            bundle.putString("androidx.preference.PreferenceFragmentCompat.PREFERENCE_ROOT", this.mKey);
            subSettingsFragment.setArguments(bundle);
            subSettingsFragment.setTargetFragment(preferenceFragment, 0);
            beginTransaction.replace(R.id.content_frame, subSettingsFragment);
            beginTransaction.addToBackStack("PreferenceFragment");
            beginTransaction.commit();
        }
    }
}
