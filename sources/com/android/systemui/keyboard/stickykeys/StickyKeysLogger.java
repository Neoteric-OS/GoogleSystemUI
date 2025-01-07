package com.android.systemui.keyboard.stickykeys;

import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import java.util.Map;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class StickyKeysLogger {
    public final LogBuffer buffer;

    public StickyKeysLogger(LogBuffer logBuffer) {
        this.buffer = logBuffer;
    }

    public final void logNewSettingValue(boolean z) {
        LogLevel logLevel = LogLevel.INFO;
        StickyKeysLogger$logNewSettingValue$2 stickyKeysLogger$logNewSettingValue$2 = new Function1() { // from class: com.android.systemui.keyboard.stickykeys.StickyKeysLogger$logNewSettingValue$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return "sticky key setting changed, new state: ".concat(((LogMessage) obj).getBool1() ? "enabled" : "disabled");
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("stickyKeys", logLevel, stickyKeysLogger$logNewSettingValue$2, null);
        ((LogMessageImpl) obtain).bool1 = z;
        logBuffer.commit(obtain);
    }

    public final void logNewStickyKeysReceived(Map map) {
        LogLevel logLevel = LogLevel.VERBOSE;
        StickyKeysLogger$logNewStickyKeysReceived$2 stickyKeysLogger$logNewStickyKeysReceived$2 = new Function1() { // from class: com.android.systemui.keyboard.stickykeys.StickyKeysLogger$logNewStickyKeysReceived$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("new sticky keys state received: ", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("stickyKeys", logLevel, stickyKeysLogger$logNewStickyKeysReceived$2, null);
        ((LogMessageImpl) obtain).str1 = map.toString();
        logBuffer.commit(obtain);
    }

    public final void logNewUiState(Map map) {
        LogLevel logLevel = LogLevel.INFO;
        StickyKeysLogger$logNewUiState$2 stickyKeysLogger$logNewUiState$2 = new Function1() { // from class: com.android.systemui.keyboard.stickykeys.StickyKeysLogger$logNewUiState$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("new sticky keys state received: ", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("stickyKeys", logLevel, stickyKeysLogger$logNewUiState$2, null);
        ((LogMessageImpl) obtain).str1 = map.toString();
        logBuffer.commit(obtain);
    }
}
