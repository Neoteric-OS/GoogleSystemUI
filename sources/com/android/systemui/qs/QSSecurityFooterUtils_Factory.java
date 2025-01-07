package com.android.systemui.qs;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.policy.SecurityController;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class QSSecurityFooterUtils_Factory implements Provider {
    public static QSSecurityFooterUtils newInstance(Context context, DevicePolicyManager devicePolicyManager, UserTracker userTracker, Handler handler, ActivityStarter activityStarter, SecurityController securityController, Looper looper, DialogTransitionAnimator dialogTransitionAnimator) {
        return new QSSecurityFooterUtils(context, devicePolicyManager, userTracker, handler, activityStarter, securityController, looper, dialogTransitionAnimator);
    }
}
