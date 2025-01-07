package com.android.systemui.tuner;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.view.MenuItem;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragment;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.demomode.DemoModeAvailabilityTracker;
import com.android.systemui.demomode.DemoModeController;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.settings.GlobalSettingsImpl;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DemoModeFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
    public static final String[] STATUS_ICONS = {"volume", "bluetooth", "location", "alarm", "zen", "sync", "tty", "eri", "mute", "speakerphone", "managed_profile"};
    public DemoModeController mDemoModeController;
    public Tracker mDemoModeTracker;
    public SwitchPreference mEnabledSwitch;
    public GlobalSettings mGlobalSettings;
    public SwitchPreference mOnSwitch;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Tracker extends DemoModeAvailabilityTracker {
        public Tracker(Context context, GlobalSettings globalSettings) {
            super(context, globalSettings);
        }

        @Override // com.android.systemui.demomode.DemoModeAvailabilityTracker
        public final void onDemoModeAvailabilityChanged() {
            DemoModeFragment demoModeFragment = DemoModeFragment.this;
            demoModeFragment.mEnabledSwitch.setChecked(demoModeFragment.mDemoModeTracker.isDemoModeAvailable);
            demoModeFragment.mOnSwitch.setEnabled(demoModeFragment.mDemoModeTracker.isDemoModeAvailable);
            demoModeFragment.mOnSwitch.setChecked(demoModeFragment.mDemoModeTracker.isInDemoMode);
        }

        @Override // com.android.systemui.demomode.DemoModeAvailabilityTracker
        public final void onDemoModeFinished() {
            DemoModeFragment demoModeFragment = DemoModeFragment.this;
            demoModeFragment.mOnSwitch.setChecked(demoModeFragment.mDemoModeTracker.isInDemoMode);
        }

        @Override // com.android.systemui.demomode.DemoModeAvailabilityTracker
        public final void onDemoModeStarted() {
            DemoModeFragment demoModeFragment = DemoModeFragment.this;
            demoModeFragment.mOnSwitch.setChecked(demoModeFragment.mDemoModeTracker.isInDemoMode);
        }
    }

    @Override // androidx.preference.PreferenceFragment
    public final void onCreatePreferences(String str) {
        Context context = getContext();
        SwitchPreference switchPreference = new SwitchPreference(context, null);
        this.mEnabledSwitch = switchPreference;
        switchPreference.setTitle(R.string.enable_demo_mode);
        this.mEnabledSwitch.mOnChangeListener = this;
        SwitchPreference switchPreference2 = new SwitchPreference(context, null);
        this.mOnSwitch = switchPreference2;
        switchPreference2.setTitle(R.string.show_demo_mode);
        this.mOnSwitch.setEnabled(false);
        this.mOnSwitch.mOnChangeListener = this;
        PreferenceManager preferenceManager = this.mPreferenceManager;
        preferenceManager.getClass();
        PreferenceScreen preferenceScreen = new PreferenceScreen(context, null);
        preferenceScreen.onAttachedToHierarchy(preferenceManager);
        preferenceScreen.addPreference(this.mEnabledSwitch);
        preferenceScreen.addPreference(this.mOnSwitch);
        setPreferenceScreen(preferenceScreen);
        Tracker tracker = new Tracker(context, this.mGlobalSettings);
        this.mDemoModeTracker = tracker;
        tracker.startTracking();
        this.mEnabledSwitch.setChecked(this.mDemoModeTracker.isDemoModeAvailable);
        this.mOnSwitch.setEnabled(this.mDemoModeTracker.isDemoModeAvailable);
        this.mOnSwitch.setChecked(this.mDemoModeTracker.isInDemoMode);
        setHasOptionsMenu(true);
    }

    @Override // android.app.Fragment
    public final void onDestroy() {
        Tracker tracker = this.mDemoModeTracker;
        ContentResolver contentResolver = tracker.context.getContentResolver();
        contentResolver.unregisterContentObserver(tracker.allowedObserver);
        contentResolver.unregisterContentObserver(tracker.onObserver);
        super.onDestroy();
    }

    @Override // android.app.Fragment
    public final boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            getFragmentManager().popBackStack();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // android.app.Fragment
    public final void onPause() {
        super.onPause();
        MetricsLogger.visibility(getContext(), 229, false);
    }

    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r8v41 */
    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public final boolean onPreferenceChange(Preference preference, Object obj) {
        String str;
        ?? r8 = obj == Boolean.TRUE ? 1 : 0;
        if (preference == this.mEnabledSwitch) {
            if (r8 == 0) {
                this.mOnSwitch.setChecked(false);
                Settings.Global.putString(((GlobalSettingsImpl) this.mDemoModeController.globalSettings).mContentResolver, "sysui_tuner_demo_on", String.valueOf(0));
            }
            MetricsLogger.action(getContext(), 235, (boolean) r8);
            Settings.Global.putString(((GlobalSettingsImpl) this.mDemoModeController.globalSettings).mContentResolver, "sysui_demo_allowed", String.valueOf((int) r8));
        } else {
            if (preference != this.mOnSwitch) {
                return false;
            }
            MetricsLogger.action(getContext(), 236, (boolean) r8);
            if (r8 != 0) {
                Intent intent = new Intent("com.android.systemui.demo");
                Settings.Global.putString(((GlobalSettingsImpl) this.mDemoModeController.globalSettings).mContentResolver, "sysui_tuner_demo_on", String.valueOf(1));
                intent.putExtra("command", "clock");
                try {
                    str = String.format("%02d00", Integer.valueOf(Integer.valueOf(Build.VERSION.RELEASE_OR_CODENAME.split("\\.")[0]).intValue() % 24));
                } catch (IllegalArgumentException unused) {
                    str = "1010";
                }
                intent.putExtra("hhmm", str);
                getContext().sendBroadcast(intent);
                intent.putExtra("command", "network");
                intent.putExtra("wifi", "show");
                intent.putExtra("mobile", "show");
                intent.putExtra("sims", "1");
                intent.putExtra("nosim", "false");
                intent.putExtra("level", "4");
                intent.putExtra("datatype", "lte");
                getContext().sendBroadcast(intent);
                intent.putExtra("fully", "true");
                getContext().sendBroadcast(intent);
                intent.putExtra("command", "battery");
                intent.putExtra("level", "100");
                intent.putExtra("plugged", "false");
                getContext().sendBroadcast(intent);
                intent.putExtra("command", "status");
                String[] strArr = STATUS_ICONS;
                for (int i = 0; i < 11; i++) {
                    intent.putExtra(strArr[i], "hide");
                }
                getContext().sendBroadcast(intent);
                intent.putExtra("command", "notifications");
                intent.putExtra("visible", "false");
                getContext().sendBroadcast(intent);
            } else {
                Settings.Global.putString(((GlobalSettingsImpl) this.mDemoModeController.globalSettings).mContentResolver, "sysui_tuner_demo_on", String.valueOf(0));
            }
        }
        return true;
    }

    @Override // android.app.Fragment
    public final void onResume() {
        super.onResume();
        MetricsLogger.visibility(getContext(), 229, true);
    }
}
