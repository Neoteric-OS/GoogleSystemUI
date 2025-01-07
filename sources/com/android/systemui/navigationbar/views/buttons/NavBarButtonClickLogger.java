package com.android.systemui.navigationbar.views.buttons;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NavBarButtonClickLogger {
    public final LogBuffer buffer;

    public NavBarButtonClickLogger(LogBuffer logBuffer) {
        this.buffer = logBuffer;
    }

    public final void logAccessibilityButtonClick() {
        LogLevel logLevel = LogLevel.DEBUG;
        NavBarButtonClickLogger$logAccessibilityButtonClick$2 navBarButtonClickLogger$logAccessibilityButtonClick$2 = new Function1() { // from class: com.android.systemui.navigationbar.views.buttons.NavBarButtonClickLogger$logAccessibilityButtonClick$2
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return "Accessibility Button Triggered";
            }
        };
        LogBuffer logBuffer = this.buffer;
        logBuffer.commit(logBuffer.obtain("NavBarButtonClick", logLevel, navBarButtonClickLogger$logAccessibilityButtonClick$2, null));
    }

    public final void logImeSwitcherClick() {
        LogLevel logLevel = LogLevel.DEBUG;
        NavBarButtonClickLogger$logImeSwitcherClick$2 navBarButtonClickLogger$logImeSwitcherClick$2 = new Function1() { // from class: com.android.systemui.navigationbar.views.buttons.NavBarButtonClickLogger$logImeSwitcherClick$2
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return "Ime Switcher Triggered";
            }
        };
        LogBuffer logBuffer = this.buffer;
        logBuffer.commit(logBuffer.obtain("NavBarButtonClick", logLevel, navBarButtonClickLogger$logImeSwitcherClick$2, null));
    }

    public final void logRecentsButtonClick() {
        LogLevel logLevel = LogLevel.DEBUG;
        NavBarButtonClickLogger$logRecentsButtonClick$2 navBarButtonClickLogger$logRecentsButtonClick$2 = new Function1() { // from class: com.android.systemui.navigationbar.views.buttons.NavBarButtonClickLogger$logRecentsButtonClick$2
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return "Recents Button Triggered";
            }
        };
        LogBuffer logBuffer = this.buffer;
        logBuffer.commit(logBuffer.obtain("NavBarButtonClick", logLevel, navBarButtonClickLogger$logRecentsButtonClick$2, null));
    }
}
