package com.android.systemui.tuner;

import android.app.ActivityManager;
import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.AttributeSet;
import androidx.preference.SwitchPreference;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.Dependency;
import com.android.systemui.statusbar.phone.ui.StatusBarIconController;
import com.android.systemui.tuner.TunerService;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class StatusBarSwitch extends SwitchPreference implements TunerService.Tunable {
    public Set mHideList;

    public StatusBarSwitch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // androidx.preference.Preference
    public final void onAttached() {
        super.onAttached();
        ((TunerService) Dependency.sDependency.getDependencyInner(TunerService.class)).addTunable(this, "icon_blacklist");
    }

    @Override // androidx.preference.Preference
    public final void onDetached() {
        ((TunerService) Dependency.sDependency.getDependencyInner(TunerService.class)).removeTunable(this);
        unregisterDependency();
    }

    @Override // com.android.systemui.tuner.TunerService.Tunable
    public final void onTuningChanged(String str, String str2) {
        if ("icon_blacklist".equals(str)) {
            this.mHideList = StatusBarIconController.getIconHideList(this.mContext, str2);
            setChecked(!r2.contains(this.mKey));
        }
    }

    @Override // androidx.preference.Preference
    public final void persistBoolean(boolean z) {
        if (z) {
            if (((ArraySet) this.mHideList).remove(this.mKey)) {
                MetricsLogger.action(this.mContext, 233, this.mKey);
                Settings.Secure.putStringForUser(this.mContext.getContentResolver(), "icon_blacklist", TextUtils.join(",", this.mHideList), ActivityManager.getCurrentUser());
                return;
            }
            return;
        }
        if (((ArraySet) this.mHideList).contains(this.mKey)) {
            return;
        }
        MetricsLogger.action(this.mContext, 234, this.mKey);
        ((ArraySet) this.mHideList).add(this.mKey);
        Settings.Secure.putStringForUser(this.mContext.getContentResolver(), "icon_blacklist", TextUtils.join(",", this.mHideList), ActivityManager.getCurrentUser());
    }
}
