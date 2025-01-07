package com.android.systemui.touchpad.tutorial.domain.interactor;

import com.android.systemui.inputdevice.tutorial.InputDeviceTutorialLogger;
import com.android.systemui.log.ConstantStringsLoggerImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.model.SysUiState;
import com.android.systemui.settings.DisplayTracker;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TouchpadGesturesInteractor {
    public final CoroutineScope backgroundScope;
    public final DisplayTracker displayTracker;
    public final InputDeviceTutorialLogger logger;
    public final SysUiState sysUiState;

    public TouchpadGesturesInteractor(SysUiState sysUiState, DisplayTracker displayTracker, CoroutineScope coroutineScope, InputDeviceTutorialLogger inputDeviceTutorialLogger) {
        this.sysUiState = sysUiState;
        this.displayTracker = displayTracker;
        this.backgroundScope = coroutineScope;
        this.logger = inputDeviceTutorialLogger;
    }

    public final void disableGestures() {
        ConstantStringsLoggerImpl constantStringsLoggerImpl = this.logger.$$delegate_0;
        LogLevel logLevel = LogLevel.DEBUG;
        constantStringsLoggerImpl.buffer.log(constantStringsLoggerImpl.tag, logLevel, "Disabling touchpad gestures across the system", null);
        BuildersKt.launch$default(this.backgroundScope, null, null, new TouchpadGesturesInteractor$setGesturesState$1(this, true, null), 3);
    }

    public final void enableGestures() {
        ConstantStringsLoggerImpl constantStringsLoggerImpl = this.logger.$$delegate_0;
        LogLevel logLevel = LogLevel.DEBUG;
        constantStringsLoggerImpl.buffer.log(constantStringsLoggerImpl.tag, logLevel, "Enabling touchpad gestures across the system", null);
        BuildersKt.launch$default(this.backgroundScope, null, null, new TouchpadGesturesInteractor$setGesturesState$1(this, false, null), 3);
    }
}
