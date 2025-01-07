package com.android.systemui.qs.external;

import com.android.internal.logging.InstanceIdSequence;
import com.android.internal.logging.UiEventLogger;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TileRequestDialogEventLogger {
    public final InstanceIdSequence instanceIdSequence;
    public final UiEventLogger uiEventLogger;

    public TileRequestDialogEventLogger(UiEventLogger uiEventLogger, InstanceIdSequence instanceIdSequence) {
        this.uiEventLogger = uiEventLogger;
        this.instanceIdSequence = instanceIdSequence;
    }
}
