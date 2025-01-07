package com.android.systemui.keyguard.ui.viewmodel;

import com.android.systemui.keyguard.ui.SwipeUpAnywhereGestureHandler;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LongPressHandlingViewLogger;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.statusbar.gesture.TapGestureDetector;
import dagger.Lazy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AlternateBouncerDependencies {
    public final LongPressHandlingViewLogger logger;
    public final AlternateBouncerMessageAreaViewModel messageAreaViewModel;
    public final PowerInteractor powerInteractor;
    public final SwipeUpAnywhereGestureHandler swipeUpAnywhereGestureHandler;
    public final TapGestureDetector tapGestureDetector;
    public final Lazy udfpsAccessibilityOverlayViewModel;
    public final AlternateBouncerUdfpsIconViewModel udfpsIconViewModel;
    public final AlternateBouncerViewModel viewModel;

    public AlternateBouncerDependencies(AlternateBouncerViewModel alternateBouncerViewModel, SwipeUpAnywhereGestureHandler swipeUpAnywhereGestureHandler, TapGestureDetector tapGestureDetector, AlternateBouncerUdfpsIconViewModel alternateBouncerUdfpsIconViewModel, Lazy lazy, AlternateBouncerMessageAreaViewModel alternateBouncerMessageAreaViewModel, PowerInteractor powerInteractor, LogBuffer logBuffer) {
        this.viewModel = alternateBouncerViewModel;
        this.swipeUpAnywhereGestureHandler = swipeUpAnywhereGestureHandler;
        this.tapGestureDetector = tapGestureDetector;
        this.udfpsIconViewModel = alternateBouncerUdfpsIconViewModel;
        this.udfpsAccessibilityOverlayViewModel = lazy;
        this.messageAreaViewModel = alternateBouncerMessageAreaViewModel;
        this.powerInteractor = powerInteractor;
        this.logger = new LongPressHandlingViewLogger(logBuffer, "AlternateBouncer");
    }
}
