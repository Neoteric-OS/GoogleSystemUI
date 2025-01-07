package com.android.systemui.statusbar.pipeline.mobile.ui;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.view.View;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VerboseMobileViewLogger {
    public final LogBuffer buffer;

    public VerboseMobileViewLogger(LogBuffer logBuffer) {
        this.buffer = logBuffer;
    }

    public final void logBinderReceivedNetworkTypeIcon(View view, int i, Icon.Resource resource) {
        LogLevel logLevel = LogLevel.VERBOSE;
        VerboseMobileViewLogger$logBinderReceivedNetworkTypeIcon$2 verboseMobileViewLogger$logBinderReceivedNetworkTypeIcon$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.mobile.ui.VerboseMobileViewLogger$logBinderReceivedNetworkTypeIcon$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "Binder[subId=" + logMessage.getInt1() + ", viewId=" + logMessage.getStr1() + "] received new network type icon: " + (logMessage.getBool1() ? AnnotationValue$1$$ExternalSyntheticOutline0.m(logMessage.getInt2(), "resId=") : "null");
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("VerboseMobileViewLogger", logLevel, verboseMobileViewLogger$logBinderReceivedNetworkTypeIcon$2, null);
        ((LogMessageImpl) obtain).str1 = Integer.toHexString(System.identityHashCode(view));
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.int1 = i;
        logMessageImpl.bool1 = resource != null;
        logMessageImpl.int2 = resource != null ? resource.res : -1;
        logBuffer.commit(obtain);
    }

    public final void logBinderReceivedSignalIcon(View view, int i, SignalIconModel signalIconModel) {
        LogLevel logLevel = LogLevel.VERBOSE;
        VerboseMobileViewLogger$logBinderReceivedSignalIcon$2 verboseMobileViewLogger$logBinderReceivedSignalIcon$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.mobile.ui.VerboseMobileViewLogger$logBinderReceivedSignalIcon$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "Binder[subId=" + logMessage.getInt1() + ", viewId=" + logMessage.getStr1() + "] received new signal icon: level=" + logMessage.getInt2() + " showExclamation=" + logMessage.getBool1();
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("VerboseMobileViewLogger", logLevel, verboseMobileViewLogger$logBinderReceivedSignalIcon$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = Integer.toHexString(System.identityHashCode(view));
        logMessageImpl.int1 = i;
        logMessageImpl.int2 = signalIconModel.getLevel();
        logMessageImpl.bool1 = signalIconModel instanceof SignalIconModel.Cellular ? ((SignalIconModel.Cellular) signalIconModel).showExclamationMark : false;
        logBuffer.commit(obtain);
    }

    public final void logBinderReceivedVisibility(int i, View view, boolean z) {
        LogLevel logLevel = LogLevel.VERBOSE;
        VerboseMobileViewLogger$logBinderReceivedVisibility$2 verboseMobileViewLogger$logBinderReceivedVisibility$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.mobile.ui.VerboseMobileViewLogger$logBinderReceivedVisibility$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "Binder[subId=" + logMessage.getInt1() + ", viewId=" + logMessage.getStr1() + "] received visibility: " + logMessage.getBool1();
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("VerboseMobileViewLogger", logLevel, verboseMobileViewLogger$logBinderReceivedVisibility$2, null);
        ((LogMessageImpl) obtain).str1 = Integer.toHexString(System.identityHashCode(view));
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.int1 = i;
        logMessageImpl.bool1 = z;
        logBuffer.commit(obtain);
    }
}
