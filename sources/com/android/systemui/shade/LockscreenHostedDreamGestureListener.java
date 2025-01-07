package com.android.systemui.shade;

import android.view.GestureDetector;
import android.view.MotionEvent;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor;
import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LockscreenHostedDreamGestureListener extends GestureDetector.SimpleOnGestureListener {
    public final String TAG = Reflection.getOrCreateKotlinClass(LockscreenHostedDreamGestureListener.class).getSimpleName();
    public final FalsingManager falsingManager;
    public final KeyguardRepositoryImpl keyguardRepository;
    public final PowerInteractor powerInteractor;
    public final PrimaryBouncerInteractor primaryBouncerInteractor;
    public final ShadeLogger shadeLogger;
    public final StatusBarStateController statusBarStateController;

    public LockscreenHostedDreamGestureListener(FalsingManager falsingManager, PowerInteractor powerInteractor, StatusBarStateController statusBarStateController, PrimaryBouncerInteractor primaryBouncerInteractor, KeyguardRepositoryImpl keyguardRepositoryImpl, ShadeLogger shadeLogger) {
        this.falsingManager = falsingManager;
        this.powerInteractor = powerInteractor;
        this.statusBarStateController = statusBarStateController;
        this.primaryBouncerInteractor = primaryBouncerInteractor;
        this.keyguardRepository = keyguardRepositoryImpl;
        this.shadeLogger = shadeLogger;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onSingleTapUp(MotionEvent motionEvent) {
        if (!((Boolean) this.keyguardRepository.isActiveDreamLockscreenHosted.getValue()).booleanValue() || this.statusBarStateController.getState() != 1 || this.primaryBouncerInteractor.isBouncerShowing()) {
            return false;
        }
        if (this.falsingManager.isFalseTap(1)) {
            this.shadeLogger.d(this.TAG + "#onSingleTapUp false tap ignored");
        } else {
            this.shadeLogger.d(this.TAG + "#onSingleTapUp tap handled, requesting wakeUpIfDreaming");
            PowerInteractor powerInteractor = this.powerInteractor;
            if (powerInteractor.statusBarStateController.isDreaming()) {
                powerInteractor.repository.wakeUp(15, "DREAMING_SINGLE_TAP");
            }
        }
        return true;
    }
}
