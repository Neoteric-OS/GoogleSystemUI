package com.android.systemui.keyguard.ui.binder;

import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.systemui.keyguard.domain.interactor.KeyguardBlueprintInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardClockInteractor;
import com.android.systemui.keyguard.shared.model.ClockSize;
import com.android.systemui.keyguard.ui.view.layout.sections.AodBurnInLayer;
import com.android.systemui.keyguard.ui.view.layout.sections.ClockSection;
import com.android.systemui.keyguard.ui.viewmodel.AodBurnInViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardRootViewModel;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.plugins.clocks.ClockController;
import com.android.systemui.util.kotlin.DisposableHandles;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardClockViewBinder {
    public static final KeyguardClockViewBinder INSTANCE = new KeyguardClockViewBinder();
    public static ClockController lastClock;

    static {
        Intrinsics.checkNotNull(Reflection.getOrCreateKotlinClass(KeyguardClockViewBinder.class).getSimpleName());
    }

    public static final DisposableHandles bind(ClockSection clockSection, ConstraintLayout constraintLayout, KeyguardClockViewModel keyguardClockViewModel, KeyguardClockInteractor keyguardClockInteractor, KeyguardBlueprintInteractor keyguardBlueprintInteractor, KeyguardRootViewModel keyguardRootViewModel, AodBurnInViewModel aodBurnInViewModel) {
        DisposableHandles disposableHandles = new DisposableHandles();
        KeyguardClockViewBinder$bind$1 keyguardClockViewBinder$bind$1 = new KeyguardClockViewBinder$bind$1(keyguardClockInteractor, constraintLayout, null);
        CoroutineContext coroutineContext = RepeatWhenAttachedKt.MAIN_DISPATCHER_SINGLETON;
        EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
        disposableHandles.plusAssign(RepeatWhenAttachedKt.repeatWhenAttached(constraintLayout, emptyCoroutineContext, keyguardClockViewBinder$bind$1));
        disposableHandles.plusAssign(RepeatWhenAttachedKt.repeatWhenAttached(constraintLayout, emptyCoroutineContext, new KeyguardClockViewBinder$bind$2(keyguardClockViewModel, constraintLayout, clockSection, keyguardBlueprintInteractor, keyguardRootViewModel, aodBurnInViewModel, null)));
        return disposableHandles;
    }

    public final void addClockViews(ClockController clockController, ConstraintLayout constraintLayout) {
        if (clockController != null) {
            for (View view : clockController.getSmallClock().getLayout().getViews()) {
                if (view.getParent() != null) {
                    ((ViewGroup) view.getParent()).removeView(view);
                }
                constraintLayout.addView(view);
                view.setVisibility(4);
            }
            for (View view2 : clockController.getLargeClock().getLayout().getViews()) {
                if (view2.getParent() != null) {
                    ((ViewGroup) view2.getParent()).removeView(view2);
                }
                constraintLayout.addView(view2);
                view2.setVisibility(4);
            }
        }
    }

    public final void updateBurnInLayer(ConstraintLayout constraintLayout, KeyguardClockViewModel keyguardClockViewModel, ClockSize clockSize) {
        AodBurnInLayer aodBurnInLayer = keyguardClockViewModel.burnInLayer;
        ClockController clockController = (ClockController) ((StateFlowImpl) keyguardClockViewModel.currentClock.$$delegate_0).getValue();
        if (clockController != null) {
            int ordinal = clockSize.ordinal();
            if (ordinal == 0) {
                for (View view : clockController.getSmallClock().getLayout().getViews()) {
                    if (aodBurnInLayer != null) {
                        aodBurnInLayer.addView(view);
                    }
                }
            } else if (ordinal == 1) {
                for (View view2 : clockController.getSmallClock().getLayout().getViews()) {
                    if (aodBurnInLayer != null) {
                        aodBurnInLayer.removeView(view2);
                    }
                }
            }
        }
        AodBurnInLayer aodBurnInLayer2 = keyguardClockViewModel.burnInLayer;
        if (aodBurnInLayer2 != null) {
            aodBurnInLayer2.updatePostLayout();
        }
    }
}
