package com.android.systemui.recents;

import android.os.Handler;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.policy.KeyguardStateController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class OverviewProxyRecentsImpl {
    public final ActivityStarter mActivityStarter;
    public Handler mHandler;
    public final KeyguardStateController mKeyguardStateController;
    public final OverviewProxyService mOverviewProxyService;

    public OverviewProxyRecentsImpl(OverviewProxyService overviewProxyService, ActivityStarter activityStarter, KeyguardStateController keyguardStateController) {
        this.mOverviewProxyService = overviewProxyService;
        this.mActivityStarter = activityStarter;
        this.mKeyguardStateController = keyguardStateController;
    }
}
