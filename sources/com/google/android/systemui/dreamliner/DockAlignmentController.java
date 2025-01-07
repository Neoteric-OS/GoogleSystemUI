package com.google.android.systemui.dreamliner;

import android.util.Log;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DockAlignmentController {
    public static final boolean DEBUG = Log.isLoggable("DockAlignmentController", 3);
    public int mAlignmentState = 0;
    public final CopyOnWriteArrayList mDockAlignmentStateChangeListeners = new CopyOnWriteArrayList();
    public final Optional mWirelessCharger;

    public DockAlignmentController(Optional optional) {
        this.mWirelessCharger = optional;
    }
}
