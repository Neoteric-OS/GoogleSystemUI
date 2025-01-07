package com.android.systemui.notetask;

import android.app.role.RoleManager;
import android.hardware.input.InputManager;
import android.view.ViewConfiguration;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.CommandQueue;
import java.util.Optional;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NoteTaskInitializer {
    public final Executor backgroundExecutor;
    public final CommandQueue commandQueue;
    public final NoteTaskController controller;
    public final boolean isEnabled;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    public final Optional optionalBubbles;
    public final RoleManager roleManager;
    public final UserTracker userTracker;
    public static final long MULTI_PRESS_TIMEOUT = ViewConfiguration.getMultiPressTimeout();
    public static final long LONG_PRESS_TIMEOUT = ViewConfiguration.getLongPressTimeout();
    public final NoteTaskInitializer$callbacks$1 callbacks = new NoteTaskInitializer$callbacks$1(this);
    public long lastStylusButtonTailUpEventTime = -MULTI_PRESS_TIMEOUT;

    public NoteTaskInitializer(NoteTaskController noteTaskController, RoleManager roleManager, CommandQueue commandQueue, Optional optional, UserTracker userTracker, KeyguardUpdateMonitor keyguardUpdateMonitor, InputManager inputManager, Executor executor, boolean z) {
        this.controller = noteTaskController;
        this.roleManager = roleManager;
        this.commandQueue = commandQueue;
        this.optionalBubbles = optional;
        this.userTracker = userTracker;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.backgroundExecutor = executor;
        this.isEnabled = z;
    }
}
