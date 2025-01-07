package com.android.systemui.qs;

import com.android.internal.logging.InstanceId;
import com.android.internal.logging.InstanceIdSequence;
import com.android.internal.logging.UiEventLogger;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QsEventLoggerImpl implements UiEventLogger {
    public final /* synthetic */ UiEventLogger $$delegate_0;
    public final InstanceIdSequence sequence = new InstanceIdSequence(1048576);

    public QsEventLoggerImpl(UiEventLogger uiEventLogger) {
        this.$$delegate_0 = uiEventLogger;
    }

    public final void log(UiEventLogger.UiEventEnum uiEventEnum) {
        this.$$delegate_0.log(uiEventEnum);
    }

    public final void logWithInstanceId(UiEventLogger.UiEventEnum uiEventEnum, int i, String str, InstanceId instanceId) {
        this.$$delegate_0.logWithInstanceId(uiEventEnum, i, str, instanceId);
    }

    public final void logWithInstanceIdAndPosition(UiEventLogger.UiEventEnum uiEventEnum, int i, String str, InstanceId instanceId, int i2) {
        this.$$delegate_0.logWithInstanceIdAndPosition(uiEventEnum, i, str, instanceId, i2);
    }

    public final void logWithPosition(UiEventLogger.UiEventEnum uiEventEnum, int i, String str, int i2) {
        this.$$delegate_0.logWithPosition(uiEventEnum, i, str, i2);
    }

    public final void log(UiEventLogger.UiEventEnum uiEventEnum, int i, String str) {
        this.$$delegate_0.log(uiEventEnum, i, str);
    }

    public final void log(UiEventLogger.UiEventEnum uiEventEnum, InstanceId instanceId) {
        this.$$delegate_0.log(uiEventEnum, instanceId);
    }
}
