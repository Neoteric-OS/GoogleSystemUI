package com.android.keyguard.logging;

import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardQuickAffordanceViewModel;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import java.util.List;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.text.StringsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardQuickAffordancesLogger {
    public final LogBuffer buffer;

    public KeyguardQuickAffordancesLogger(LogBuffer logBuffer) {
        this.buffer = logBuffer;
    }

    public final void logQuickAffordanceSelected(String str, String str2) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardQuickAffordancesLogger$logQuickAffordanceSelected$2 keyguardQuickAffordancesLogger$logQuickAffordanceSelected$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardQuickAffordancesLogger$logQuickAffordanceSelected$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("QuickAffordance selected with id: ", logMessage.getStr1(), ", in slot: ", logMessage.getStr2());
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("KeyguardQuickAffordancesLogger", logLevel, keyguardQuickAffordancesLogger$logQuickAffordanceSelected$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = str2;
        logMessageImpl.str2 = str;
        logBuffer.commit(obtain);
    }

    public final void logQuickAffordanceTapped(String str) {
        Pair pair;
        if (str != null) {
            List split$default = StringsKt.split$default(str, new String[]{"::"}, 0, 6);
            pair = new Pair(split$default.get(0), split$default.get(1));
        } else {
            pair = new Pair("", "");
        }
        String str2 = (String) pair.component1();
        String str3 = (String) pair.component2();
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardQuickAffordancesLogger$logQuickAffordanceTapped$2 keyguardQuickAffordancesLogger$logQuickAffordanceTapped$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardQuickAffordancesLogger$logQuickAffordanceTapped$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("QuickAffordance tapped with id: ", logMessage.getStr1(), ", in slot: ", logMessage.getStr2());
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("KeyguardQuickAffordancesLogger", logLevel, keyguardQuickAffordancesLogger$logQuickAffordanceTapped$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = str3;
        logMessageImpl.str2 = str2;
        logBuffer.commit(obtain);
    }

    public final void logQuickAffordanceTriggered(String str, String str2) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardQuickAffordancesLogger$logQuickAffordanceTriggered$2 keyguardQuickAffordancesLogger$logQuickAffordanceTriggered$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardQuickAffordancesLogger$logQuickAffordanceTriggered$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("QuickAffordance triggered with id: ", logMessage.getStr1(), ", in slot: ", logMessage.getStr2());
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("KeyguardQuickAffordancesLogger", logLevel, keyguardQuickAffordancesLogger$logQuickAffordanceTriggered$2, null);
        ((LogMessageImpl) obtain).str1 = str2;
        ((LogMessageImpl) obtain).str2 = str;
        logBuffer.commit(obtain);
    }

    public final void logUpdate(KeyguardQuickAffordanceViewModel keyguardQuickAffordanceViewModel) {
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardQuickAffordancesLogger$logUpdate$2 keyguardQuickAffordancesLogger$logUpdate$2 = new Function1() { // from class: com.android.keyguard.logging.KeyguardQuickAffordancesLogger$logUpdate$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("QuickAffordance updated: ", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("KeyguardQuickAffordancesLogger", logLevel, keyguardQuickAffordancesLogger$logUpdate$2, null);
        ((LogMessageImpl) obtain).str1 = keyguardQuickAffordanceViewModel.toString();
        logBuffer.commit(obtain);
    }
}
