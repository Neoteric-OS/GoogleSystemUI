package com.android.systemui.statusbar.policy;

import android.app.NotificationManager;
import android.service.notification.ZenModeConfig;
import com.android.systemui.statusbar.policy.ZenModeController;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ZenModeControllerImpl$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ int $r8$classId = 1;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ ZenModeControllerImpl$$ExternalSyntheticLambda1(NotificationManager.Policy policy) {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ZenModeController.Callback callback = (ZenModeController.Callback) obj;
        switch (this.$r8$classId) {
            case 0:
                callback.onConfigChanged$1();
                break;
            default:
                callback.onConsolidatedPolicyChanged();
                break;
        }
    }

    public /* synthetic */ ZenModeControllerImpl$$ExternalSyntheticLambda1(ZenModeConfig zenModeConfig) {
    }
}
