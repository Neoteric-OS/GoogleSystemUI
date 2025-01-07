package com.android.systemui.screenshot.data.repository;

import android.app.IActivityTaskManager;
import com.android.systemui.screenshot.proxy.SystemUiProxyClient;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DisplayContentRepositoryImpl {
    public final IActivityTaskManager atmService;
    public final CoroutineDispatcher background;
    public final SystemUiProxyClient systemUiProxy;

    public DisplayContentRepositoryImpl(IActivityTaskManager iActivityTaskManager, SystemUiProxyClient systemUiProxyClient, CoroutineDispatcher coroutineDispatcher) {
        this.atmService = iActivityTaskManager;
        this.systemUiProxy = systemUiProxyClient;
        this.background = coroutineDispatcher;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$toDisplayTasksModel(com.android.systemui.screenshot.data.repository.DisplayContentRepositoryImpl r4, int r5, java.util.List r6, kotlin.coroutines.jvm.internal.ContinuationImpl r7) {
        /*
            r4.getClass()
            boolean r0 = r7 instanceof com.android.systemui.screenshot.data.repository.DisplayContentRepositoryImpl$toDisplayTasksModel$1
            if (r0 == 0) goto L16
            r0 = r7
            com.android.systemui.screenshot.data.repository.DisplayContentRepositoryImpl$toDisplayTasksModel$1 r0 = (com.android.systemui.screenshot.data.repository.DisplayContentRepositoryImpl$toDisplayTasksModel$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            com.android.systemui.screenshot.data.repository.DisplayContentRepositoryImpl$toDisplayTasksModel$1 r0 = new com.android.systemui.screenshot.data.repository.DisplayContentRepositoryImpl$toDisplayTasksModel$1
            r0.<init>(r4, r7)
        L1b:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L39
            if (r2 != r3) goto L31
            int r5 = r0.I$0
            java.lang.Object r4 = r0.L$0
            r6 = r4
            java.util.List r6 = (java.util.List) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L67
        L31:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L39:
            kotlin.ResultKt.throwOnFailure(r7)
            r0.L$0 = r6
            r0.I$0 = r5
            r0.label = r3
            com.android.systemui.screenshot.proxy.SystemUiProxyClient r4 = r4.systemUiProxy
            r4.getClass()
            kotlin.coroutines.SafeContinuation r7 = new kotlin.coroutines.SafeContinuation
            kotlin.coroutines.Continuation r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.intercepted(r0)
            r7.<init>(r0)
            com.android.internal.infra.ServiceConnector r4 = r4.proxyConnector
            com.android.systemui.screenshot.proxy.SystemUiProxyClient$isNotificationShadeExpanded$2$1 r0 = com.android.systemui.screenshot.proxy.SystemUiProxyClient$isNotificationShadeExpanded$2$1.INSTANCE
            com.android.internal.infra.AndroidFuture r4 = r4.postForResult(r0)
            com.android.systemui.screenshot.proxy.SystemUiProxyClient$isNotificationShadeExpanded$2$2 r0 = new com.android.systemui.screenshot.proxy.SystemUiProxyClient$isNotificationShadeExpanded$2$2
            r0.<init>()
            r4.whenComplete(r0)
            java.lang.Object r7 = r7.getOrThrow()
            if (r7 != r1) goto L67
            goto L77
        L67:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r4 = r7.booleanValue()
            com.android.systemui.screenshot.data.model.SystemUiState r7 = new com.android.systemui.screenshot.data.model.SystemUiState
            r7.<init>(r4)
            com.android.systemui.screenshot.data.model.DisplayContentModel r1 = new com.android.systemui.screenshot.data.model.DisplayContentModel
            r1.<init>(r5, r7, r6)
        L77:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.screenshot.data.repository.DisplayContentRepositoryImpl.access$toDisplayTasksModel(com.android.systemui.screenshot.data.repository.DisplayContentRepositoryImpl, int, java.util.List, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final Object getDisplayContent(int i, Continuation continuation) {
        return BuildersKt.withContext(this.background, new DisplayContentRepositoryImpl$getDisplayContent$2(this, i, null), continuation);
    }
}
