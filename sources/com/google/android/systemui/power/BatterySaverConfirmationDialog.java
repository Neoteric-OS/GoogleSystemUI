package com.google.android.systemui.power;

import android.content.Context;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.phone.SystemUIDialog;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BatterySaverConfirmationDialog {
    public final ActivityStarter mActivityStarter;
    public SystemUIDialog mConfirmationDialog;
    public final Context mContext;
    public final DialogTransitionAnimator mDialogTransitionAnimator;
    public boolean mIsStandardMode;
    public final SystemUIDialog.Factory mSystemUIDialogFactory;
    public final UiEventLogger mUiEventLogger;

    public BatterySaverConfirmationDialog(Context context, ActivityStarter activityStarter, UiEventLogger uiEventLogger, DialogTransitionAnimator dialogTransitionAnimator, SystemUIDialog.Factory factory) {
        this.mContext = context;
        this.mActivityStarter = activityStarter;
        this.mUiEventLogger = uiEventLogger;
        this.mDialogTransitionAnimator = dialogTransitionAnimator;
        this.mSystemUIDialogFactory = factory;
    }

    public final void log(BatteryMetricEvent batteryMetricEvent) {
        if (this.mUiEventLogger != null) {
            if (batteryMetricEvent.ordinal() != 17) {
                this.mUiEventLogger.log(batteryMetricEvent);
            } else {
                this.mUiEventLogger.logWithPosition(batteryMetricEvent, 0, (String) null, !this.mIsStandardMode ? 1 : 0);
            }
        }
    }
}
