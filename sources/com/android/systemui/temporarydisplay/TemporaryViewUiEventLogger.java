package com.android.systemui.temporarydisplay;

import com.android.internal.logging.InstanceIdSequence;
import com.android.internal.logging.UiEventLogger;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TemporaryViewUiEventLogger {
    public final InstanceIdSequence instanceIdSequence = new InstanceIdSequence(1048576);
    public final UiEventLogger logger;

    public TemporaryViewUiEventLogger(UiEventLogger uiEventLogger) {
        this.logger = uiEventLogger;
    }
}
