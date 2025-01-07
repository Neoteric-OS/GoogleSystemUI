package com.android.systemui.inputdevice.tutorial;

import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import androidx.constraintlayout.motion.widget.MotionLayout$$ExternalSyntheticOutline0;
import com.android.systemui.inputdevice.tutorial.domain.interactor.ConnectionState;
import com.android.systemui.inputdevice.tutorial.ui.viewmodel.Screen;
import com.android.systemui.log.ConstantStringsLoggerImpl;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class InputDeviceTutorialLogger {
    public final /* synthetic */ ConstantStringsLoggerImpl $$delegate_0;
    public final LogBuffer buffer;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TutorialContext {
        public static final /* synthetic */ TutorialContext[] $VALUES;
        public static final TutorialContext KEYBOARD_TOUCHPAD_TUTORIAL;
        public static final TutorialContext TOUCHPAD_TUTORIAL;
        private final String string;

        static {
            TutorialContext tutorialContext = new TutorialContext("KEYBOARD_TOUCHPAD_TUTORIAL", "keyboard touchpad tutorial", 0);
            KEYBOARD_TOUCHPAD_TUTORIAL = tutorialContext;
            TutorialContext tutorialContext2 = new TutorialContext("TOUCHPAD_TUTORIAL", "touchpad tutorial", 1);
            TOUCHPAD_TUTORIAL = tutorialContext2;
            TutorialContext[] tutorialContextArr = {tutorialContext, tutorialContext2};
            $VALUES = tutorialContextArr;
            EnumEntriesKt.enumEntries(tutorialContextArr);
        }

        public TutorialContext(String str, String str2, int i) {
            this.string = str2;
        }

        public static TutorialContext valueOf(String str) {
            return (TutorialContext) Enum.valueOf(TutorialContext.class, str);
        }

        public static TutorialContext[] values() {
            return (TutorialContext[]) $VALUES.clone();
        }

        public final String getString() {
            return this.string;
        }
    }

    public InputDeviceTutorialLogger(LogBuffer logBuffer) {
        this.buffer = logBuffer;
        this.$$delegate_0 = new ConstantStringsLoggerImpl(logBuffer, "InputDeviceTutorial");
    }

    public final void logCloseTutorial(TutorialContext tutorialContext) {
        InputDeviceTutorialLogger$logCloseTutorial$2 inputDeviceTutorialLogger$logCloseTutorial$2 = new Function1() { // from class: com.android.systemui.inputdevice.tutorial.InputDeviceTutorialLogger$logCloseTutorial$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Closing ", ((LogMessage) obj).getStr1());
            }
        };
        LogLevel logLevel = LogLevel.INFO;
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("InputDeviceTutorial", logLevel, inputDeviceTutorialLogger$logCloseTutorial$2, null);
        ((LogMessageImpl) obtain).str1 = tutorialContext.getString();
        logBuffer.commit(obtain);
    }

    public final void logGoingBack(Screen screen) {
        InputDeviceTutorialLogger$logGoingBack$2 inputDeviceTutorialLogger$logGoingBack$2 = new Function1() { // from class: com.android.systemui.inputdevice.tutorial.InputDeviceTutorialLogger$logGoingBack$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Going back to ", ((LogMessage) obj).getStr1(), " screen");
            }
        };
        LogLevel logLevel = LogLevel.INFO;
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("InputDeviceTutorial", logLevel, inputDeviceTutorialLogger$logGoingBack$2, null);
        ((LogMessageImpl) obtain).str1 = screen.toString();
        logBuffer.commit(obtain);
    }

    public final void logGoingToScreen(com.android.systemui.touchpad.tutorial.ui.viewmodel.Screen screen) {
        TutorialContext tutorialContext = TutorialContext.TOUCHPAD_TUTORIAL;
        InputDeviceTutorialLogger$logGoingToScreen$2 inputDeviceTutorialLogger$logGoingToScreen$2 = new Function1() { // from class: com.android.systemui.inputdevice.tutorial.InputDeviceTutorialLogger$logGoingToScreen$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("Emitting new screen ", logMessage.getStr1(), " in ", logMessage.getStr2());
            }
        };
        LogLevel logLevel = LogLevel.INFO;
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("InputDeviceTutorial", logLevel, inputDeviceTutorialLogger$logGoingToScreen$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = screen.toString();
        logMessageImpl.str2 = tutorialContext.getString();
        logBuffer.commit(obtain);
    }

    public final void logMovingBetweenScreens(Screen screen, Screen screen2) {
        String str;
        InputDeviceTutorialLogger$logMovingBetweenScreens$2 inputDeviceTutorialLogger$logMovingBetweenScreens$2 = new Function1() { // from class: com.android.systemui.inputdevice.tutorial.InputDeviceTutorialLogger$logMovingBetweenScreens$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return MotionLayout$$ExternalSyntheticOutline0.m("Moving from ", logMessage.getStr1(), " screen to ", logMessage.getStr2(), " screen");
            }
        };
        LogLevel logLevel = LogLevel.INFO;
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("InputDeviceTutorial", logLevel, inputDeviceTutorialLogger$logMovingBetweenScreens$2, null);
        if (screen == null || (str = screen.toString()) == null) {
            str = "NO_SCREEN";
        }
        ((LogMessageImpl) obtain).str1 = str;
        ((LogMessageImpl) obtain).str2 = screen2.toString();
        logBuffer.commit(obtain);
    }

    public final void logNewConnectionState(ConnectionState connectionState) {
        InputDeviceTutorialLogger$logNewConnectionState$2 inputDeviceTutorialLogger$logNewConnectionState$2 = new Function1() { // from class: com.android.systemui.inputdevice.tutorial.InputDeviceTutorialLogger$logNewConnectionState$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "Received connection state: touchpad connected: " + logMessage.getBool1() + " keyboard connected: " + logMessage.getBool2();
            }
        };
        LogLevel logLevel = LogLevel.INFO;
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("InputDeviceTutorial", logLevel, inputDeviceTutorialLogger$logNewConnectionState$2, null);
        ((LogMessageImpl) obtain).bool1 = connectionState.touchpadConnected;
        ((LogMessageImpl) obtain).bool2 = connectionState.keyboardConnected;
        logBuffer.commit(obtain);
    }

    public final void logNextScreen(Screen screen) {
        InputDeviceTutorialLogger$logNextScreen$2 inputDeviceTutorialLogger$logNextScreen$2 = new Function1() { // from class: com.android.systemui.inputdevice.tutorial.InputDeviceTutorialLogger$logNextScreen$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("going to ", ((LogMessage) obj).getStr1(), " screen");
            }
        };
        LogLevel logLevel = LogLevel.INFO;
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("InputDeviceTutorial", logLevel, inputDeviceTutorialLogger$logNextScreen$2, null);
        ((LogMessageImpl) obtain).str1 = screen.toString();
        logBuffer.commit(obtain);
    }

    public final void logNextScreenMissingHardware(Screen screen) {
        LogLevel logLevel = LogLevel.WARNING;
        InputDeviceTutorialLogger$logNextScreenMissingHardware$2 inputDeviceTutorialLogger$logNextScreenMissingHardware$2 = new Function1() { // from class: com.android.systemui.inputdevice.tutorial.InputDeviceTutorialLogger$logNextScreenMissingHardware$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("next screen should be ", ((LogMessage) obj).getStr1(), " but required hardware is missing");
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("InputDeviceTutorial", logLevel, inputDeviceTutorialLogger$logNextScreenMissingHardware$2, null);
        ((LogMessageImpl) obtain).str1 = screen.toString();
        logBuffer.commit(obtain);
    }

    public final void logOpenTutorial(TutorialContext tutorialContext) {
        InputDeviceTutorialLogger$logOpenTutorial$2 inputDeviceTutorialLogger$logOpenTutorial$2 = new Function1() { // from class: com.android.systemui.inputdevice.tutorial.InputDeviceTutorialLogger$logOpenTutorial$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Opening ", ((LogMessage) obj).getStr1());
            }
        };
        LogLevel logLevel = LogLevel.INFO;
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("InputDeviceTutorial", logLevel, inputDeviceTutorialLogger$logOpenTutorial$2, null);
        ((LogMessageImpl) obtain).str1 = tutorialContext.getString();
        logBuffer.commit(obtain);
    }
}
