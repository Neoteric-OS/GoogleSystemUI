package com.android.systemui.keyguard.ui.binder;

import android.graphics.PointF;
import android.util.MathUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewPropertyAnimator;
import com.android.systemui.animation.Expandable$Companion$fromView$1;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardQuickAffordanceViewModel;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.statusbar.VibratorHelper;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardQuickAffordanceOnTouchListener implements View.OnTouchListener {
    public static final Companion Companion = null;
    public final FalsingManager falsingManager;
    public ViewPropertyAnimator longPressAnimator;
    public final VibratorHelper vibratorHelper;
    public final View view;
    public final KeyguardQuickAffordanceViewModel viewModel;
    public final long longPressDurationMs = ViewConfiguration.getLongPressTimeout();
    public final Lazy downDisplayCoords$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.keyguard.ui.binder.KeyguardQuickAffordanceOnTouchListener$downDisplayCoords$2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return new PointF();
        }
    });

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static boolean isUsingAccurateTool$default(MotionEvent motionEvent) {
            int toolType = motionEvent.getToolType(0);
            return toolType == 2 || toolType == 3;
        }
    }

    public KeyguardQuickAffordanceOnTouchListener(View view, KeyguardQuickAffordanceViewModel keyguardQuickAffordanceViewModel, VibratorHelper vibratorHelper, FalsingManager falsingManager) {
        this.view = view;
        this.viewModel = keyguardQuickAffordanceViewModel;
        this.vibratorHelper = vibratorHelper;
        this.falsingManager = falsingManager;
    }

    public final void cancel() {
        ViewPropertyAnimator viewPropertyAnimator = this.longPressAnimator;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
        this.longPressAnimator = null;
        this.view.animate().scaleX(1.0f).scaleY(1.0f);
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        FalsingManager falsingManager;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            if (this.viewModel.configKey == null) {
                return false;
            }
            ((PointF) this.downDisplayCoords$delegate.getValue()).set(motionEvent.getRawX(), motionEvent.getRawY());
            if (Companion.isUsingAccurateTool$default(motionEvent)) {
                return false;
            }
            this.longPressAnimator = this.view.animate().scaleX(1.5f).scaleY(1.5f).setDuration(this.longPressDurationMs);
            return false;
        }
        if (actionMasked != 1) {
            if (actionMasked != 2) {
                if (actionMasked != 3) {
                    return false;
                }
                cancel();
                return true;
            }
            if (Companion.isUsingAccurateTool$default(motionEvent)) {
                return false;
            }
            if (MathUtils.dist(motionEvent.getRawX(), motionEvent.getRawY(), ((PointF) this.downDisplayCoords$delegate.getValue()).x, ((PointF) this.downDisplayCoords$delegate.getValue()).y) <= ViewConfiguration.getTouchSlop()) {
                return false;
            }
            cancel();
            return false;
        }
        if (!Companion.isUsingAccurateTool$default(motionEvent)) {
            cancel();
            return false;
        }
        if (this.viewModel.configKey == null) {
            return false;
        }
        if (MathUtils.dist(motionEvent.getRawX(), motionEvent.getRawY(), ((PointF) this.downDisplayCoords$delegate.getValue()).x, ((PointF) this.downDisplayCoords$delegate.getValue()).y) > ViewConfiguration.getTouchSlop() || (falsingManager = this.falsingManager) == null || falsingManager.isFalseTap(0)) {
            return false;
        }
        final String str = this.viewModel.configKey;
        this.view.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.keyguard.ui.binder.KeyguardQuickAffordanceOnTouchListener$dispatchClick$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                KeyguardQuickAffordanceOnTouchListener keyguardQuickAffordanceOnTouchListener = KeyguardQuickAffordanceOnTouchListener.this;
                VibratorHelper vibratorHelper = keyguardQuickAffordanceOnTouchListener.vibratorHelper;
                if (vibratorHelper != null) {
                    vibratorHelper.vibrate(keyguardQuickAffordanceOnTouchListener.viewModel.isActivated ? KeyguardBottomAreaVibrations.Activated : KeyguardBottomAreaVibrations.Deactivated);
                }
                KeyguardQuickAffordanceOnTouchListener keyguardQuickAffordanceOnTouchListener2 = KeyguardQuickAffordanceOnTouchListener.this;
                KeyguardQuickAffordanceViewModel keyguardQuickAffordanceViewModel = keyguardQuickAffordanceOnTouchListener2.viewModel;
                keyguardQuickAffordanceViewModel.onClicked.invoke(new KeyguardQuickAffordanceViewModel.OnClickedParameters(str, new Expandable$Companion$fromView$1(keyguardQuickAffordanceOnTouchListener2.view), keyguardQuickAffordanceViewModel.slotId));
            }
        });
        this.view.performClick();
        this.view.setOnClickListener(null);
        return false;
    }
}
