package com.android.systemui.tuner;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toolbar;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragment;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;
import com.android.systemui.Dependency;
import com.android.systemui.Dumpable;
import com.android.systemui.demomode.DemoModeController;
import com.android.systemui.fragments.FragmentService;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class TunerActivity extends Activity implements PreferenceFragment.OnPreferenceStartFragmentCallback, PreferenceFragment.OnPreferenceStartScreenCallback {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final DemoModeController mDemoModeController;
    public final GlobalSettings mGlobalSettings;
    public final TunerService mTunerService;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public class SubSettingsFragment extends PreferenceFragment {
        public PreferenceScreen mParentScreen;

        @Override // androidx.preference.PreferenceFragment
        public final void onCreatePreferences(String str) {
            this.mParentScreen = (PreferenceScreen) ((PreferenceFragment) getTargetFragment()).mPreferenceManager.mPreferenceScreen.findPreference(str);
            PreferenceManager preferenceManager = this.mPreferenceManager;
            PreferenceScreen preferenceScreen = new PreferenceScreen(preferenceManager.mContext, null);
            preferenceScreen.onAttachedToHierarchy(preferenceManager);
            setPreferenceScreen(preferenceScreen);
            while (this.mParentScreen.getPreferenceCount() > 0) {
                Preference preference = this.mParentScreen.getPreference(0);
                this.mParentScreen.removePreference(preference);
                preferenceScreen.addPreference(preference);
            }
        }

        @Override // android.app.Fragment
        public final void onDestroy() {
            super.onDestroy();
            PreferenceScreen preferenceScreen = this.mPreferenceManager.mPreferenceScreen;
            while (preferenceScreen.getPreferenceCount() > 0) {
                Preference preference = preferenceScreen.getPreference(0);
                preferenceScreen.removePreference(preference);
                this.mParentScreen.addPreference(preference);
            }
        }
    }

    public TunerActivity(DemoModeController demoModeController, TunerService tunerService, GlobalSettings globalSettings) {
        this.mDemoModeController = demoModeController;
        this.mTunerService = tunerService;
        this.mGlobalSettings = globalSettings;
    }

    @Override // android.app.Activity
    public final void onBackPressed() {
        if (getFragmentManager().popBackStackImmediate()) {
            return;
        }
        super.onBackPressed();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        TunerFragment tunerFragment;
        super.onCreate(bundle);
        setTheme(R.style.Theme_AppCompat_DayNight);
        getWindow().addFlags(Integer.MIN_VALUE);
        requestWindowFeature(1);
        setContentView(R.layout.tuner_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
        if (toolbar != null) {
            setActionBar(toolbar);
        }
        if (getFragmentManager().findFragmentByTag("tuner") == null) {
            String action = getIntent().getAction();
            if (action == null || !action.equals("com.android.settings.action.DEMO_MODE")) {
                tunerFragment = new TunerFragment(this.mTunerService);
            } else {
                DemoModeFragment demoModeFragment = new DemoModeFragment();
                demoModeFragment.mDemoModeController = this.mDemoModeController;
                demoModeFragment.mGlobalSettings = this.mGlobalSettings;
                tunerFragment = demoModeFragment;
            }
            getFragmentManager().beginTransaction().replace(R.id.content_frame, tunerFragment, "tuner").commit();
        }
    }

    @Override // android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
        TunerActivity$$ExternalSyntheticLambda0 tunerActivity$$ExternalSyntheticLambda0 = new TunerActivity$$ExternalSyntheticLambda0();
        Dependency dependency = Dependency.sDependency;
        Object remove = dependency.mDependencies.remove(FragmentService.class);
        if (remove instanceof Dumpable) {
            dependency.mDumpManager.unregisterDumpable(remove.getClass().getName());
        }
        if (remove != null) {
            tunerActivity$$ExternalSyntheticLambda0.accept(remove);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onMenuItemSelected(i, menuItem);
        }
        onBackPressed();
        return true;
    }
}
