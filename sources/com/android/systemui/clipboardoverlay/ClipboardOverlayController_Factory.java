package com.android.systemui.clipboardoverlay;

import android.content.Context;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.screenshot.TimeoutHandler;
import dagger.internal.Provider;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ClipboardOverlayController_Factory implements Provider {
    public static ClipboardOverlayController newInstance(Context context, ClipboardOverlayView clipboardOverlayView, ClipboardOverlayWindow clipboardOverlayWindow, BroadcastDispatcher broadcastDispatcher, BroadcastSender broadcastSender, TimeoutHandler timeoutHandler, ClipboardOverlayUtils clipboardOverlayUtils, Executor executor, ClipboardImageLoader clipboardImageLoader, ClipboardTransitionExecutor clipboardTransitionExecutor, UiEventLogger uiEventLogger) {
        return new ClipboardOverlayController(context, clipboardOverlayView, clipboardOverlayWindow, broadcastDispatcher, broadcastSender, timeoutHandler, clipboardOverlayUtils, executor, clipboardImageLoader, clipboardTransitionExecutor, uiEventLogger);
    }
}
