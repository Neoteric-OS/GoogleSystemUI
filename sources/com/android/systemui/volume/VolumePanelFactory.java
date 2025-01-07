package com.android.systemui.volume;

import android.content.Context;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.plugins.ActivityStarter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VolumePanelFactory {
    public final ActivityStarter activityStarter;
    public final Context context;

    public VolumePanelFactory(Context context, ActivityStarter activityStarter, DialogTransitionAnimator dialogTransitionAnimator) {
        this.activityStarter = activityStarter;
    }
}
