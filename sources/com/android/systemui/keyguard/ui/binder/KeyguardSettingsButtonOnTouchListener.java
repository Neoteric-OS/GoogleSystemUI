package com.android.systemui.keyguard.ui.binder;

import android.graphics.PointF;
import android.util.MathUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import com.android.systemui.keyguard.domain.interactor.KeyguardTouchHandlingInteractor;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardSettingsMenuViewModel;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardSettingsButtonOnTouchListener implements View.OnTouchListener {
    public final PointF downPositionDisplayCoords = new PointF();
    public final KeyguardSettingsMenuViewModel viewModel;

    public KeyguardSettingsButtonOnTouchListener(KeyguardSettingsMenuViewModel keyguardSettingsMenuViewModel) {
        this.viewModel = keyguardSettingsMenuViewModel;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                view.setPressed(false);
                PointF pointF = this.downPositionDisplayCoords;
                boolean z = MathUtils.dist(motionEvent.getRawX(), motionEvent.getRawY(), pointF.x, pointF.y) < ((float) ViewConfiguration.getTouchSlop());
                KeyguardTouchHandlingInteractor keyguardTouchHandlingInteractor = this.viewModel.interactor;
                if (z) {
                    keyguardTouchHandlingInteractor.hideMenu();
                    keyguardTouchHandlingInteractor.logger.log(KeyguardTouchHandlingInteractor.LogEvents.LOCK_SCREEN_LONG_PRESS_POPUP_CLICKED);
                    Boolean bool = Boolean.TRUE;
                    StateFlowImpl stateFlowImpl = keyguardTouchHandlingInteractor._shouldOpenSettings;
                    stateFlowImpl.getClass();
                    stateFlowImpl.updateState(null, bool);
                } else {
                    keyguardTouchHandlingInteractor.scheduleAutomaticMenuHiding();
                }
                if (z) {
                    view.performClick();
                }
            } else if (actionMasked == 3) {
                view.setPressed(false);
                this.viewModel.interactor.scheduleAutomaticMenuHiding();
            }
        } else {
            view.setPressed(true);
            this.downPositionDisplayCoords.set(motionEvent.getRawX(), motionEvent.getRawY());
            KeyguardTouchHandlingInteractor keyguardTouchHandlingInteractor2 = this.viewModel.interactor;
            StandaloneCoroutine standaloneCoroutine = keyguardTouchHandlingInteractor2.delayedHideMenuJob;
            if (standaloneCoroutine != null) {
                standaloneCoroutine.cancel(null);
            }
            keyguardTouchHandlingInteractor2.delayedHideMenuJob = null;
        }
        return true;
    }
}
