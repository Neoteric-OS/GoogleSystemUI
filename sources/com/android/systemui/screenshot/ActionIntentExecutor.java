package com.android.systemui.screenshot;

import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.systemui.screenshot.proxy.SystemUiProxyClient;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ActionIntentExecutor {
    public final ActivityManagerWrapper activityManagerWrapper;
    public final CoroutineScope applicationScope;
    public final Context context;
    public final DisplayTracker displayTracker;
    public final CoroutineDispatcher mainDispatcher;
    public final SystemUiProxyClient systemUiProxy;

    public ActionIntentExecutor(Context context, ActivityManagerWrapper activityManagerWrapper, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, SystemUiProxyClient systemUiProxyClient, DisplayTracker displayTracker) {
        this.context = context;
        this.activityManagerWrapper = activityManagerWrapper;
        this.applicationScope = coroutineScope;
        this.mainDispatcher = coroutineDispatcher;
        this.systemUiProxy = systemUiProxyClient;
        this.displayTracker = displayTracker;
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x00b8, code lost:
    
        if (r0 == kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED) goto L30;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object launchIntent(android.content.Intent r19, android.os.UserHandle r20, boolean r21, android.app.ActivityOptions r22, android.app.ExitTransitionCoordinator r23, kotlin.coroutines.jvm.internal.ContinuationImpl r24) {
        /*
            Method dump skipped, instructions count: 421
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.screenshot.ActionIntentExecutor.launchIntent(android.content.Intent, android.os.UserHandle, boolean, android.app.ActivityOptions, android.app.ExitTransitionCoordinator, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final void launchIntentAsync(Intent intent, UserHandle userHandle) {
        CoroutineTracingKt.launch$default(this.applicationScope, null, new ActionIntentExecutor$launchIntentAsync$1(this, intent, userHandle, false, null, null, null), 6);
    }
}
