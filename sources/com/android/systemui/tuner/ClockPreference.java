package com.android.systemui.tuner;

import android.R;
import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.AttributeSet;
import androidx.preference.DropDownPreference;
import com.android.systemui.Dependency;
import com.android.systemui.statusbar.phone.ui.StatusBarIconController;
import com.android.systemui.tuner.TunerService;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class ClockPreference extends DropDownPreference implements TunerService.Tunable {
    public final String mClock;
    public boolean mClockEnabled;
    public boolean mHasSeconds;
    public boolean mHasSetValue;
    public ArraySet mHideList;
    public boolean mReceivedClock;
    public boolean mReceivedSeconds;

    public ClockPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mClock = context.getString(R.string.stk_cc_ussd_to_dial_video);
        this.mEntryValues = new CharSequence[]{"seconds", "default", "disabled"};
    }

    @Override // androidx.preference.Preference
    public final void onAttached() {
        super.onAttached();
        ((TunerService) Dependency.sDependency.getDependencyInner(TunerService.class)).addTunable(this, "icon_blacklist", "clock_seconds");
    }

    @Override // androidx.preference.Preference
    public final void onDetached() {
        ((TunerService) Dependency.sDependency.getDependencyInner(TunerService.class)).removeTunable(this);
        unregisterDependency();
    }

    @Override // com.android.systemui.tuner.TunerService.Tunable
    public final void onTuningChanged(String str, String str2) {
        if ("icon_blacklist".equals(str)) {
            this.mReceivedClock = true;
            this.mHideList = StatusBarIconController.getIconHideList(this.mContext, str2);
            this.mClockEnabled = !r3.contains(this.mClock);
        } else if ("clock_seconds".equals(str)) {
            this.mReceivedSeconds = true;
            this.mHasSeconds = (str2 == null || Integer.parseInt(str2) == 0) ? false : true;
        }
        if (!this.mHasSetValue && this.mReceivedClock && this.mReceivedSeconds) {
            this.mHasSetValue = true;
            boolean z = this.mClockEnabled;
            if (z && this.mHasSeconds) {
                setValue("seconds");
            } else if (z) {
                setValue("default");
            } else {
                setValue("disabled");
            }
        }
    }

    @Override // androidx.preference.Preference
    public final void persistString(String str) {
        TunerService tunerService = (TunerService) Dependency.sDependency.getDependencyInner(TunerService.class);
        boolean equals = "seconds".equals(str);
        TunerServiceImpl tunerServiceImpl = (TunerServiceImpl) tunerService;
        Settings.Secure.putIntForUser(tunerServiceImpl.mContentResolver, "clock_seconds", equals ? 1 : 0, tunerServiceImpl.mCurrentUser);
        if ("disabled".equals(str)) {
            this.mHideList.add(this.mClock);
        } else {
            this.mHideList.remove(this.mClock);
        }
        TunerService tunerService2 = (TunerService) Dependency.sDependency.getDependencyInner(TunerService.class);
        TunerServiceImpl tunerServiceImpl2 = (TunerServiceImpl) tunerService2;
        Settings.Secure.putStringForUser(tunerServiceImpl2.mContentResolver, "icon_blacklist", TextUtils.join(",", this.mHideList), tunerServiceImpl2.mCurrentUser);
    }
}
