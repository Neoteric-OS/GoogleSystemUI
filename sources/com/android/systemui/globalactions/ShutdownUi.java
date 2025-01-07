package com.android.systemui.globalactions;

import android.R;
import android.content.Context;
import android.nearby.NearbyManager;
import androidx.compose.foundation.text.input.internal.RecordingInputConnection$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.BlurUtils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ShutdownUi {
    public BlurUtils mBlurUtils;
    public Context mContext;
    public NearbyManager mNearbyManager;

    public String getReasonMessage(String str) {
        if (str != null && str.startsWith("recovery-update")) {
            return this.mContext.getString(R.string.resolver_cross_profile_blocked);
        }
        if (str == null || !str.equals("recovery")) {
            return null;
        }
        return this.mContext.getString(R.string.resolver_cant_access_personal_apps_explanation);
    }

    public int getRebootMessage(boolean z, String str) {
        return (str == null || !str.startsWith("recovery-update")) ? ((str == null || !str.equals("recovery")) && !z) ? R.string.splash_screen_view_icon_description : R.string.reset : R.string.resolver_cant_share_with_work_apps_explanation;
    }

    public int getShutdownDialogContent(boolean z) {
        int poweredOffFindingMode = this.mNearbyManager.getPoweredOffFindingMode();
        if (poweredOffFindingMode != 1 && poweredOffFindingMode != 0) {
            if (poweredOffFindingMode == 2) {
                return z ? R.layout.shutdown_dialog : com.android.wm.shell.R.layout.shutdown_dialog_finder_active;
            }
            RecordingInputConnection$$ExternalSyntheticOutline0.m("Unexpected value for finder active: ", "ShutdownUi", poweredOffFindingMode);
        }
        return R.layout.shutdown_dialog;
    }
}
