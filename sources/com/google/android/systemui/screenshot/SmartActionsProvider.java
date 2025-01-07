package com.google.android.systemui.screenshot;

import android.content.Context;
import android.util.Log;
import com.android.systemui.screenshot.ScreenshotNotificationSmartActionsProvider;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SmartActionsProvider {
    public final CoroutineScope applicationScope;
    public final CoroutineDispatcher bgDispatcher;
    public final Context context;
    public final ScreenshotNotificationSmartActionsProvider smartActions;

    public SmartActionsProvider(Context context, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, ScreenshotNotificationSmartActionsProvider screenshotNotificationSmartActionsProvider) {
        this.context = context;
        this.applicationScope = coroutineScope;
        this.bgDispatcher = coroutineDispatcher;
        this.smartActions = screenshotNotificationSmartActionsProvider;
    }

    public final void notifyScreenshotOp(String str, ScreenshotNotificationSmartActionsProvider.ScreenshotOp screenshotOp, ScreenshotNotificationSmartActionsProvider.ScreenshotOpStatus screenshotOpStatus, long j) {
        Reflection.getOrCreateKotlinClass(SmartActionsProvider.class).getSimpleName();
        try {
            this.smartActions.notifyOp(str, screenshotOp, screenshotOpStatus, j);
        } catch (Throwable th) {
            Log.e("SmartActionsProvider", "Error in notifyScreenshotOp: ", th);
        }
    }
}
