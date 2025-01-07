package com.android.systemui.statusbar.policy;

import com.android.systemui.statusbar.policy.ZenModeController;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ZenModeControllerImpl$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ int $r8$classId;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ZenModeController.Callback callback = (ZenModeController.Callback) obj;
        switch (this.$r8$classId) {
            case 0:
                callback.getClass();
                break;
            case 1:
                callback.onNextAlarmChanged();
                break;
            default:
                callback.getClass();
                break;
        }
    }
}
