package com.android.systemui.ambient.touch.scrim;

import android.os.PowerManager;
import android.os.SystemClock;
import com.android.systemui.shade.ShadeExpansionChangeEvent;
import com.android.systemui.unfold.util.CallbackController;
import java.util.HashSet;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BouncerlessScrimController implements ScrimController, CallbackController {
    public final HashSet mCallbacks = new HashSet();
    public final Executor mExecutor;
    public final PowerManager mPowerManager;

    public BouncerlessScrimController(Executor executor, PowerManager powerManager) {
        this.mExecutor = executor;
        this.mPowerManager = powerManager;
    }

    @Override // com.android.systemui.ambient.touch.scrim.ScrimController
    public final void expand(ShadeExpansionChangeEvent shadeExpansionChangeEvent) {
        if (!shadeExpansionChangeEvent.expanded) {
            this.mExecutor.execute(new BouncerlessScrimController$$ExternalSyntheticLambda4(this, shadeExpansionChangeEvent, 2));
        } else {
            this.mPowerManager.wakeUp(SystemClock.uptimeMillis(), 4, "com.android.systemui:SwipeUp");
            this.mExecutor.execute(new Runnable() { // from class: com.android.systemui.ambient.touch.scrim.BouncerlessScrimController$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    BouncerlessScrimController.this.mCallbacks.forEach(new BouncerlessScrimController$$ExternalSyntheticLambda3());
                }
            });
        }
    }
}
