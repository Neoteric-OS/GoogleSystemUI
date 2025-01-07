package com.android.systemui.qs.tiles;

import android.os.Handler;
import android.os.Looper;
import android.safetycenter.SafetyCenterManager;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLoggerImpl;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.statusbar.policy.IndividualSensorPrivacyController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class CameraToggleTile_Factory implements Provider {
    public static CameraToggleTile newInstance(Handler handler, Looper looper, SafetyCenterManager safetyCenterManager, MetricsLogger metricsLogger, ActivityStarter activityStarter, FalsingManager falsingManager, StatusBarStateController statusBarStateController, QSHost qSHost, QsEventLoggerImpl qsEventLoggerImpl, QSLogger qSLogger, IndividualSensorPrivacyController individualSensorPrivacyController, KeyguardStateController keyguardStateController) {
        return new CameraToggleTile(handler, looper, safetyCenterManager, metricsLogger, activityStarter, falsingManager, statusBarStateController, qSHost, qsEventLoggerImpl, qSLogger, individualSensorPrivacyController, keyguardStateController);
    }
}
