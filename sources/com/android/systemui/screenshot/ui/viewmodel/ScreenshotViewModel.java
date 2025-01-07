package com.android.systemui.screenshot.ui.viewmodel;

import android.view.accessibility.AccessibilityManager;
import kotlin.collections.EmptyList;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenshotViewModel {
    public final StateFlowImpl _actions;
    public final StateFlowImpl _animationState;
    public final StateFlowImpl _badge;
    public final StateFlowImpl _isAnimating;
    public final StateFlowImpl _preview;
    public final StateFlowImpl _previewAction;
    public final StateFlowImpl _scrollableRect;
    public final StateFlowImpl _scrollingScrim;
    public final AccessibilityManager accessibilityManager;
    public final StateFlowImpl actions;
    public final StateFlowImpl animationState;
    public final StateFlowImpl badge;
    public final StateFlowImpl isAnimating;
    public final StateFlowImpl preview;
    public final StateFlowImpl previewAction;
    public final StateFlowImpl scrollableRect;
    public final StateFlowImpl scrollingScrim;

    public ScreenshotViewModel(AccessibilityManager accessibilityManager) {
        this.accessibilityManager = accessibilityManager;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(null);
        this._preview = MutableStateFlow;
        this.preview = MutableStateFlow;
        StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(null);
        this._scrollingScrim = MutableStateFlow2;
        this.scrollingScrim = MutableStateFlow2;
        StateFlowImpl MutableStateFlow3 = StateFlowKt.MutableStateFlow(null);
        this._badge = MutableStateFlow3;
        this.badge = MutableStateFlow3;
        StateFlowImpl MutableStateFlow4 = StateFlowKt.MutableStateFlow(null);
        this._previewAction = MutableStateFlow4;
        this.previewAction = MutableStateFlow4;
        StateFlowImpl MutableStateFlow5 = StateFlowKt.MutableStateFlow(EmptyList.INSTANCE);
        this._actions = MutableStateFlow5;
        this.actions = MutableStateFlow5;
        StateFlowImpl MutableStateFlow6 = StateFlowKt.MutableStateFlow(AnimationState.NOT_STARTED);
        this._animationState = MutableStateFlow6;
        this.animationState = MutableStateFlow6;
        StateFlowImpl MutableStateFlow7 = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this._isAnimating = MutableStateFlow7;
        this.isAnimating = MutableStateFlow7;
        StateFlowImpl MutableStateFlow8 = StateFlowKt.MutableStateFlow(null);
        this._scrollableRect = MutableStateFlow8;
        this.scrollableRect = MutableStateFlow8;
    }

    public final void setIsAnimating(boolean z) {
        Boolean valueOf = Boolean.valueOf(z);
        StateFlowImpl stateFlowImpl = this._isAnimating;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, valueOf);
    }
}
