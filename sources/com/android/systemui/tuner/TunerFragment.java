package com.android.systemui.tuner;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.display.AmbientDisplayConfiguration;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragment;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TunerFragment extends PreferenceFragment {
    public static final String[] DEBUG_ONLY = {"nav_bar", BcSmartspaceDataPlugin.UI_SURFACE_LOCK_SCREEN_AOD, "picture_in_picture"};
    public final TunerService mTunerService;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public class TunerWarningFragment extends DialogFragment {
        @Override // android.app.DialogFragment
        public final Dialog onCreateDialog(Bundle bundle) {
            return new AlertDialog.Builder(getContext()).setTitle(R.string.tuner_warning_title).setMessage(R.string.tuner_warning).setPositiveButton(R.string.got_it, new DialogInterface.OnClickListener() { // from class: com.android.systemui.tuner.TunerFragment.TunerWarningFragment.1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    Settings.Secure.putInt(TunerWarningFragment.this.getContext().getContentResolver(), "seen_tuner_warning", 1);
                }
            }).show();
        }
    }

    public TunerFragment(TunerService tunerService) {
        this.mTunerService = tunerService;
    }

    @Override // android.app.Fragment
    public final void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override // androidx.preference.PreferenceFragment, android.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    @Override // android.app.Fragment
    public final void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.add(0, 2, 0, R.string.remove_from_settings);
    }

    @Override // androidx.preference.PreferenceFragment
    public final void onCreatePreferences(String str) {
        addPreferencesFromResource(R.xml.tuner_prefs);
        if (!getContext().getSharedPreferences("plugin_prefs", 0).getBoolean("plugins", false)) {
            this.mPreferenceManager.mPreferenceScreen.removePreference(findPreference("plugins"));
        }
        if (!new AmbientDisplayConfiguration(getContext()).alwaysOnAvailable()) {
            this.mPreferenceManager.mPreferenceScreen.removePreference(findPreference("doze"));
        }
        if (!Build.IS_DEBUGGABLE) {
            for (int i = 0; i < 3; i++) {
                Preference findPreference = findPreference(DEBUG_ONLY[i]);
                if (findPreference != null) {
                    this.mPreferenceManager.mPreferenceScreen.removePreference(findPreference);
                }
            }
        }
        if (Settings.Secure.getInt(getContext().getContentResolver(), "seen_tuner_warning", 0) == 0 && getFragmentManager().findFragmentByTag("tuner_warning") == null) {
            new TunerWarningFragment().show(getFragmentManager(), "tuner_warning");
        }
    }

    @Override // android.app.Fragment
    public final boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId != 2) {
            if (itemId != 16908332) {
                return super.onOptionsItemSelected(menuItem);
            }
            getActivity().finish();
            return true;
        }
        TunerService tunerService = this.mTunerService;
        final TunerFragment$$ExternalSyntheticLambda0 tunerFragment$$ExternalSyntheticLambda0 = new TunerFragment$$ExternalSyntheticLambda0(this);
        final TunerServiceImpl tunerServiceImpl = (TunerServiceImpl) tunerService;
        SystemUIDialog create = ((SystemUIDialog.Factory) tunerServiceImpl.mSystemUIDialogFactoryLazy.get()).create();
        SystemUIDialog.setShowForAllUsers(create);
        create.setMessage(R.string.remove_from_settings_prompt);
        create.setButton(-2, tunerServiceImpl.mContext.getString(R.string.cancel), (DialogInterface.OnClickListener) null);
        create.setButton(-1, tunerServiceImpl.mContext.getString(R.string.qs_customize_remove), new DialogInterface.OnClickListener() { // from class: com.android.systemui.tuner.TunerServiceImpl$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                TunerServiceImpl tunerServiceImpl2 = TunerServiceImpl.this;
                TunerFragment$$ExternalSyntheticLambda0 tunerFragment$$ExternalSyntheticLambda02 = tunerFragment$$ExternalSyntheticLambda0;
                tunerServiceImpl2.mContext.sendBroadcast(new Intent("com.android.systemui.action.CLEAR_TUNER"));
                ((UserTrackerImpl) tunerServiceImpl2.mUserTracker).getUserContext().getPackageManager().setComponentEnabledSetting(tunerServiceImpl2.mTunerComponent, 2, 1);
                Settings.Secure.putInt(tunerServiceImpl2.mContext.getContentResolver(), "seen_tuner_warning", 0);
                tunerFragment$$ExternalSyntheticLambda02.run();
            }
        });
        create.show();
        return true;
    }

    @Override // android.app.Fragment
    public final void onPause() {
        super.onPause();
        MetricsLogger.visibility(getContext(), 227, false);
    }

    @Override // android.app.Fragment
    public final void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.system_ui_tuner);
        MetricsLogger.visibility(getContext(), 227, true);
    }
}
