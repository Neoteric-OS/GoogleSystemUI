package com.android.systemui.touchpad.tutorial.ui.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.android.systemui.inputdevice.tutorial.InputDeviceTutorialLogger;
import com.android.systemui.touchpad.tutorial.domain.interactor.TouchpadGesturesInteractor;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TouchpadTutorialViewModel extends ViewModel {
    public final StateFlowImpl _screen;
    public final TouchpadGesturesInteractor gesturesInteractor;
    public final InputDeviceTutorialLogger logger;
    public final StateFlowImpl screen;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory implements ViewModelProvider.Factory {
        public final TouchpadGesturesInteractor gesturesInteractor;
        public final InputDeviceTutorialLogger logger;

        public Factory(TouchpadGesturesInteractor touchpadGesturesInteractor, InputDeviceTutorialLogger inputDeviceTutorialLogger) {
            this.gesturesInteractor = touchpadGesturesInteractor;
            this.logger = inputDeviceTutorialLogger;
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public final ViewModel create(Class cls) {
            return new TouchpadTutorialViewModel(this.gesturesInteractor, this.logger);
        }
    }

    public TouchpadTutorialViewModel(TouchpadGesturesInteractor touchpadGesturesInteractor, InputDeviceTutorialLogger inputDeviceTutorialLogger) {
        this.gesturesInteractor = touchpadGesturesInteractor;
        this.logger = inputDeviceTutorialLogger;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(Screen.TUTORIAL_SELECTION);
        this._screen = MutableStateFlow;
        this.screen = MutableStateFlow;
    }

    public final void goTo(Screen screen) {
        InputDeviceTutorialLogger.TutorialContext tutorialContext = InputDeviceTutorialLogger.TutorialContext.KEYBOARD_TOUCHPAD_TUTORIAL;
        this.logger.logGoingToScreen(screen);
        StateFlowImpl stateFlowImpl = this._screen;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, screen);
    }
}
