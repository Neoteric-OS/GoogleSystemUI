package com.android.systemui.tuner;

import android.provider.Settings;
import androidx.preference.Preference;
import com.android.systemui.Dependency;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NavBarTuner$$ExternalSyntheticLambda1 implements Preference.OnPreferenceChangeListener {
    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public final boolean onPreferenceChange(Preference preference, Object obj) {
        int[][] iArr = NavBarTuner.ICONS;
        String str = (String) obj;
        if ("default".equals(str)) {
            str = null;
        }
        TunerServiceImpl tunerServiceImpl = (TunerServiceImpl) ((TunerService) Dependency.sDependency.getDependencyInner(TunerService.class));
        Settings.Secure.putStringForUser(tunerServiceImpl.mContentResolver, "sysui_nav_bar", str, tunerServiceImpl.mCurrentUser);
        return true;
    }
}
