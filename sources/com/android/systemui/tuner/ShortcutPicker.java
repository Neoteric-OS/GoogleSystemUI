package com.android.systemui.tuner;

import android.content.Context;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.provider.Settings;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragment;
import androidx.preference.PreferenceGroupAdapter;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;
import androidx.preference.PreferenceViewHolder;
import com.android.systemui.Dependency;
import com.android.systemui.tuner.ShortcutParser;
import com.android.systemui.tuner.TunerService;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class ShortcutPicker extends PreferenceFragment implements TunerService.Tunable {
    public static final /* synthetic */ int $r8$clinit = 0;
    public String mKey;
    public SelectablePreference mNonePreference;
    public final ArrayList mSelectablePreferences = new ArrayList();
    public TunerService mTunerService;

    @Override // android.app.Fragment
    public final void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if ("sysui_keyguard_left".equals(this.mKey)) {
            getActivity().setTitle(R.string.lockscreen_shortcut_left);
        } else {
            getActivity().setTitle(R.string.lockscreen_shortcut_right);
        }
    }

    @Override // androidx.preference.PreferenceFragment
    public final void onCreatePreferences(String str) {
        PreferenceManager preferenceManager = this.mPreferenceManager;
        Context context = preferenceManager.mContext;
        PreferenceScreen preferenceScreen = new PreferenceScreen(context, null);
        preferenceScreen.onAttachedToHierarchy(preferenceManager);
        preferenceScreen.mOrderingAsAdded = true;
        PreferenceCategory preferenceCategory = new PreferenceCategory(context, null);
        preferenceCategory.setTitle(R.string.tuner_other_apps);
        SelectablePreference selectablePreference = new SelectablePreference(context);
        this.mNonePreference = selectablePreference;
        this.mSelectablePreferences.add(selectablePreference);
        this.mNonePreference.setTitle(R.string.lockscreen_none);
        SelectablePreference selectablePreference2 = this.mNonePreference;
        selectablePreference2.setIcon(AppCompatResources.getDrawable(R.drawable.ic_remove_circle, selectablePreference2.mContext));
        selectablePreference2.mIconResId = R.drawable.ic_remove_circle;
        preferenceScreen.addPreference(this.mNonePreference);
        List<LauncherActivityInfo> activityList = ((LauncherApps) getContext().getSystemService(LauncherApps.class)).getActivityList(null, Process.myUserHandle());
        preferenceScreen.addPreference(preferenceCategory);
        activityList.forEach(new ShortcutPicker$$ExternalSyntheticLambda0(this, context, preferenceScreen, preferenceCategory));
        preferenceScreen.removePreference(preferenceCategory);
        for (int i = 0; i < preferenceCategory.getPreferenceCount(); i++) {
            Preference preference = preferenceCategory.getPreference(0);
            preferenceCategory.removePreference(preference);
            if (Integer.MAX_VALUE != preference.mOrder) {
                preference.mOrder = Integer.MAX_VALUE;
                PreferenceGroupAdapter preferenceGroupAdapter = preference.mListener;
                if (preferenceGroupAdapter != null) {
                    Handler handler = preferenceGroupAdapter.mHandler;
                    PreferenceGroupAdapter.AnonymousClass1 anonymousClass1 = preferenceGroupAdapter.mSyncRunnable;
                    handler.removeCallbacks(anonymousClass1);
                    handler.post(anonymousClass1);
                }
            }
            preferenceScreen.addPreference(preference);
        }
        setPreferenceScreen(preferenceScreen);
        this.mKey = getArguments().getString("androidx.preference.PreferenceFragmentCompat.PREFERENCE_ROOT");
        TunerService tunerService = (TunerService) Dependency.sDependency.getDependencyInner(TunerService.class);
        this.mTunerService = tunerService;
        tunerService.addTunable(this, this.mKey);
    }

    @Override // android.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        this.mTunerService.removeTunable(this);
    }

    @Override // androidx.preference.PreferenceFragment, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public final boolean onPreferenceTreeClick(Preference preference) {
        TunerService tunerService = this.mTunerService;
        TunerServiceImpl tunerServiceImpl = (TunerServiceImpl) tunerService;
        Settings.Secure.putStringForUser(tunerServiceImpl.mContentResolver, this.mKey, preference.toString(), tunerServiceImpl.mCurrentUser);
        getActivity().onBackPressed();
        return true;
    }

    @Override // com.android.systemui.tuner.TunerService.Tunable
    public final void onTuningChanged(String str, final String str2) {
        if (str2 == null) {
            str2 = "";
        }
        this.mSelectablePreferences.forEach(new Consumer() { // from class: com.android.systemui.tuner.ShortcutPicker$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                String str3 = str2;
                SelectablePreference selectablePreference = (SelectablePreference) obj;
                int i = ShortcutPicker.$r8$clinit;
                selectablePreference.setChecked(str3.equals(selectablePreference.toString()));
            }
        });
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AppPreference extends SelectablePreference {
        public final /* synthetic */ int $r8$classId = 0;
        public boolean mBinding;
        public final Object mInfo;

        public AppPreference(Context context, LauncherActivityInfo launcherActivityInfo) {
            super(context);
            this.mInfo = launcherActivityInfo;
            setTitle(context.getString(R.string.tuner_launch_app, launcherActivityInfo.getLabel()));
            setSummary(context.getString(R.string.tuner_app, launcherActivityInfo.getLabel()));
        }

        @Override // androidx.preference.Preference
        public final void notifyChanged() {
            switch (this.$r8$classId) {
                case 0:
                    if (!this.mBinding) {
                        super.notifyChanged();
                        break;
                    }
                    break;
                default:
                    if (!this.mBinding) {
                        super.notifyChanged();
                        break;
                    }
                    break;
            }
        }

        @Override // androidx.preference.CheckBoxPreference, androidx.preference.Preference
        public final void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
            switch (this.$r8$classId) {
                case 0:
                    this.mBinding = true;
                    if (getIcon() == null) {
                        setIcon(((LauncherActivityInfo) this.mInfo).getBadgedIcon(this.mContext.getResources().getConfiguration().densityDpi));
                    }
                    this.mBinding = false;
                    super.onBindViewHolder(preferenceViewHolder);
                    break;
                default:
                    this.mBinding = true;
                    if (getIcon() == null) {
                        setIcon(((ShortcutParser.Shortcut) this.mInfo).icon.loadDrawable(this.mContext));
                    }
                    this.mBinding = false;
                    super.onBindViewHolder(preferenceViewHolder);
                    break;
            }
        }

        @Override // com.android.systemui.tuner.SelectablePreference, androidx.preference.Preference
        public final String toString() {
            switch (this.$r8$classId) {
                case 0:
                    return ((LauncherActivityInfo) this.mInfo).getComponentName().flattenToString();
                default:
                    return ((ShortcutParser.Shortcut) this.mInfo).toString();
            }
        }

        public AppPreference(Context context, ShortcutParser.Shortcut shortcut, CharSequence charSequence) {
            super(context);
            this.mInfo = shortcut;
            setTitle(shortcut.label);
            setSummary(context.getString(R.string.tuner_app, charSequence));
        }
    }
}
