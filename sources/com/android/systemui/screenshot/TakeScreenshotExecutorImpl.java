package com.android.systemui.screenshot;

import com.android.internal.logging.UiEventLogger;
import com.android.systemui.display.data.repository.DisplayRepository;
import com.android.systemui.display.data.repository.DisplayRepositoryImpl;
import com.android.systemui.screenshot.ScreenshotNotificationsController;
import com.android.systemui.screenshot.TakeScreenshotService;
import com.android.systemui.screenshot.policy.PolicyRequestProcessor;
import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$65;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$66;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TakeScreenshotExecutorImpl implements TakeScreenshotExecutor {
    public static final List ALLOWED_DISPLAY_TYPES = CollectionsKt__CollectionsKt.listOf(2, 1, 4, 3);
    public final ReadonlyStateFlow displays;
    public final HeadlessScreenshotHandler headlessScreenshotHandler;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$66 interactiveScreenshotHandlerFactory;
    public final CoroutineScope mainScope;
    public final Map notificationControllers = new LinkedHashMap();
    public LegacyScreenshotController screenshotController;
    public final ScreenshotNotificationsController.Factory screenshotNotificationControllerFactory;
    public final PolicyRequestProcessor screenshotRequestProcessor;
    public final UiEventLogger uiEventLogger;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MultiResultCallbackWrapper {
        public final Set idsPending = new LinkedHashSet();
        public final Set idsWithErrors = new LinkedHashSet();
        public final TakeScreenshotService.RequestCallback originalCallback;

        public MultiResultCallbackWrapper(TakeScreenshotService.RequestCallback requestCallback) {
            this.originalCallback = requestCallback;
        }

        public static final void access$reportToOriginalIfNeeded(MultiResultCallbackWrapper multiResultCallbackWrapper) {
            if (multiResultCallbackWrapper.idsPending.isEmpty()) {
                boolean isEmpty = multiResultCallbackWrapper.idsWithErrors.isEmpty();
                TakeScreenshotService.RequestCallback requestCallback = multiResultCallbackWrapper.originalCallback;
                if (isEmpty) {
                    requestCallback.onFinish();
                } else {
                    requestCallback.reportError();
                }
            }
        }
    }

    public TakeScreenshotExecutorImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$66 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$66, DisplayRepository displayRepository, CoroutineScope coroutineScope, PolicyRequestProcessor policyRequestProcessor, UiEventLogger uiEventLogger, ScreenshotNotificationsController.Factory factory, HeadlessScreenshotHandler headlessScreenshotHandler) {
        this.interactiveScreenshotHandlerFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$66;
        this.mainScope = coroutineScope;
        this.screenshotRequestProcessor = policyRequestProcessor;
        this.uiEventLogger = uiEventLogger;
        this.screenshotNotificationControllerFactory = factory;
        this.headlessScreenshotHandler = headlessScreenshotHandler;
        this.displays = ((DisplayRepositoryImpl) displayRepository).displays;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:0|1|(2:3|(11:5|6|7|(1:(2:10|11)(2:32|33))(3:34|35|(1:37))|12|13|(1:15)|16|(1:18)|19|(1:21)(4:23|24|25|26)))|40|6|7|(0)(0)|12|13|(0)|16|(0)|19|(0)(0)|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x003f, code lost:
    
        r10 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0064, code lost:
    
        r10 = new kotlin.Result.Failure(r10);
        r5 = r5;
        r6 = r6;
        r9 = r9;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0095 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0021  */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.android.systemui.screenshot.TakeScreenshotExecutorImpl, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r9v5, types: [com.android.systemui.screenshot.TakeScreenshotService$RequestCallback] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object dispatchToController(com.android.systemui.screenshot.ScreenshotHandler r6, com.android.systemui.screenshot.ScreenshotData r7, kotlin.jvm.functions.Function1 r8, com.android.systemui.screenshot.TakeScreenshotExecutorImpl$MultiResultCallbackWrapper$createCallbackForId$1 r9, kotlin.coroutines.jvm.internal.ContinuationImpl r10) {
        /*
            r5 = this;
            boolean r0 = r10 instanceof com.android.systemui.screenshot.TakeScreenshotExecutorImpl$dispatchToController$1
            if (r0 == 0) goto L13
            r0 = r10
            com.android.systemui.screenshot.TakeScreenshotExecutorImpl$dispatchToController$1 r0 = (com.android.systemui.screenshot.TakeScreenshotExecutorImpl$dispatchToController$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.screenshot.TakeScreenshotExecutorImpl$dispatchToController$1 r0 = new com.android.systemui.screenshot.TakeScreenshotExecutorImpl$dispatchToController$1
            r0.<init>(r5, r10)
        L18:
            java.lang.Object r10 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L49
            if (r2 != r3) goto L41
            java.lang.Object r5 = r0.L$4
            r9 = r5
            com.android.systemui.screenshot.TakeScreenshotService$RequestCallback r9 = (com.android.systemui.screenshot.TakeScreenshotService.RequestCallback) r9
            java.lang.Object r5 = r0.L$3
            r8 = r5
            kotlin.jvm.functions.Function1 r8 = (kotlin.jvm.functions.Function1) r8
            java.lang.Object r5 = r0.L$2
            r7 = r5
            com.android.systemui.screenshot.ScreenshotData r7 = (com.android.systemui.screenshot.ScreenshotData) r7
            java.lang.Object r5 = r0.L$1
            r6 = r5
            com.android.systemui.screenshot.ScreenshotHandler r6 = (com.android.systemui.screenshot.ScreenshotHandler) r6
            java.lang.Object r5 = r0.L$0
            com.android.systemui.screenshot.TakeScreenshotExecutorImpl r5 = (com.android.systemui.screenshot.TakeScreenshotExecutorImpl) r5
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L3f
            goto L61
        L3f:
            r10 = move-exception
            goto L64
        L41:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L49:
            kotlin.ResultKt.throwOnFailure(r10)
            com.android.systemui.screenshot.policy.PolicyRequestProcessor r10 = r5.screenshotRequestProcessor     // Catch: java.lang.Throwable -> L3f
            r0.L$0 = r5     // Catch: java.lang.Throwable -> L3f
            r0.L$1 = r6     // Catch: java.lang.Throwable -> L3f
            r0.L$2 = r7     // Catch: java.lang.Throwable -> L3f
            r0.L$3 = r8     // Catch: java.lang.Throwable -> L3f
            r0.L$4 = r9     // Catch: java.lang.Throwable -> L3f
            r0.label = r3     // Catch: java.lang.Throwable -> L3f
            java.lang.Object r10 = r10.process(r7, r0)     // Catch: java.lang.Throwable -> L3f
            if (r10 != r1) goto L61
            return r1
        L61:
            com.android.systemui.screenshot.ScreenshotData r10 = (com.android.systemui.screenshot.ScreenshotData) r10     // Catch: java.lang.Throwable -> L3f
            goto L6a
        L64:
            kotlin.Result$Failure r0 = new kotlin.Result$Failure
            r0.<init>(r10)
            r10 = r0
        L6a:
            java.lang.Throwable r0 = kotlin.Result.m1771exceptionOrNullimpl(r10)
            r1 = 0
            java.lang.String r2 = "Screenshot"
            if (r0 == 0) goto L8a
            java.lang.String r3 = "Failed to process screenshot request!"
            android.util.Log.e(r2, r3, r0)
            com.android.internal.logging.UiEventLogger r0 = r5.uiEventLogger
            int r3 = r7.source
            com.android.systemui.screenshot.ScreenshotEvent r3 = com.android.systemui.screenshot.ScreenshotEvent.getScreenshotSource(r3)
            java.lang.String r4 = r7.getPackageNameString()
            r0.log(r3, r1, r4)
            r5.onFailedScreenshotRequest(r7, r9)
        L8a:
            boolean r7 = r10 instanceof kotlin.Result.Failure
            if (r7 == 0) goto L8f
            r10 = 0
        L8f:
            com.android.systemui.screenshot.ScreenshotData r10 = (com.android.systemui.screenshot.ScreenshotData) r10
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            if (r10 != 0) goto L96
            return r7
        L96:
            com.android.internal.logging.UiEventLogger r0 = r5.uiEventLogger
            int r3 = r10.source
            com.android.systemui.screenshot.ScreenshotEvent r3 = com.android.systemui.screenshot.ScreenshotEvent.getScreenshotSource(r3)
            java.lang.String r4 = r10.getPackageNameString()
            r0.log(r3, r1, r4)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Screenshot request: "
            r0.<init>(r1)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r2, r0)
            com.android.systemui.screenshot.TakeScreenshotExecutorImpl$sam$java_util_function_Consumer$0 r0 = new com.android.systemui.screenshot.TakeScreenshotExecutorImpl$sam$java_util_function_Consumer$0     // Catch: java.lang.IllegalStateException -> Lbf
            r0.<init>(r8)     // Catch: java.lang.IllegalStateException -> Lbf
            r6.handleScreenshot(r10, r0, r9)     // Catch: java.lang.IllegalStateException -> Lbf
            return r7
        Lbf:
            r6 = move-exception
            java.lang.String r8 = "Error while ScreenshotController was handling ScreenshotData!"
            android.util.Log.e(r2, r8, r6)
            r5.onFailedScreenshotRequest(r10, r9)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.screenshot.TakeScreenshotExecutorImpl.dispatchToController(com.android.systemui.screenshot.ScreenshotHandler, com.android.systemui.screenshot.ScreenshotData, kotlin.jvm.functions.Function1, com.android.systemui.screenshot.TakeScreenshotExecutorImpl$MultiResultCallbackWrapper$createCallbackForId$1, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0330  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /* JADX WARN: Type inference failed for: r12v0, types: [com.android.systemui.screenshot.TakeScreenshotExecutorImpl$MultiResultCallbackWrapper$createCallbackForId$1] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x032b -> B:11:0x032d). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object executeScreenshots(com.android.internal.util.ScreenshotRequest r38, kotlin.jvm.functions.Function1 r39, com.android.systemui.screenshot.TakeScreenshotService.RequestCallback r40, kotlin.coroutines.jvm.internal.ContinuationImpl r41) {
        /*
            Method dump skipped, instructions count: 819
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.screenshot.TakeScreenshotExecutorImpl.executeScreenshots(com.android.internal.util.ScreenshotRequest, kotlin.jvm.functions.Function1, com.android.systemui.screenshot.TakeScreenshotService$RequestCallback, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getDisplaysToScreenshot(int r5, kotlin.coroutines.jvm.internal.ContinuationImpl r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.android.systemui.screenshot.TakeScreenshotExecutorImpl$getDisplaysToScreenshot$1
            if (r0 == 0) goto L13
            r0 = r6
            com.android.systemui.screenshot.TakeScreenshotExecutorImpl$getDisplaysToScreenshot$1 r0 = (com.android.systemui.screenshot.TakeScreenshotExecutorImpl$getDisplaysToScreenshot$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.screenshot.TakeScreenshotExecutorImpl$getDisplaysToScreenshot$1 r0 = new com.android.systemui.screenshot.TakeScreenshotExecutorImpl$getDisplaysToScreenshot$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            int r5 = r0.I$0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L41
        L29:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L31:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.I$0 = r5
            r0.label = r3
            kotlinx.coroutines.flow.ReadonlyStateFlow r4 = r4.displays
            java.lang.Object r6 = kotlinx.coroutines.flow.FlowKt.first(r4, r0)
            if (r6 != r1) goto L41
            return r1
        L41:
            java.util.Set r6 = (java.util.Set) r6
            r4 = 3
            if (r5 != r4) goto L68
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.Iterator r5 = r6.iterator()
        L51:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L95
            java.lang.Object r6 = r5.next()
            r0 = r6
            android.view.Display r0 = (android.view.Display) r0
            int r0 = r0.getDisplayId()
            if (r0 != 0) goto L51
            r4.add(r6)
            goto L51
        L68:
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.Iterator r5 = r6.iterator()
        L73:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L95
            java.lang.Object r6 = r5.next()
            r0 = r6
            android.view.Display r0 = (android.view.Display) r0
            java.util.List r1 = com.android.systemui.screenshot.TakeScreenshotExecutorImpl.ALLOWED_DISPLAY_TYPES
            int r0 = r0.getType()
            java.lang.Integer r2 = new java.lang.Integer
            r2.<init>(r0)
            boolean r0 = r1.contains(r2)
            if (r0 == 0) goto L73
            r4.add(r6)
            goto L73
        L95:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.screenshot.TakeScreenshotExecutorImpl.getDisplaysToScreenshot(int, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final void onFailedScreenshotRequest(ScreenshotData screenshotData, TakeScreenshotService.RequestCallback requestCallback) {
        this.uiEventLogger.log(ScreenshotEvent.SCREENSHOT_CAPTURE_FAILED, 0, screenshotData.getPackageNameString());
        Map map = this.notificationControllers;
        final int i = screenshotData.displayId;
        ((ScreenshotNotificationsController) map.computeIfAbsent(Integer.valueOf(i), new Function() { // from class: com.android.systemui.screenshot.TakeScreenshotExecutorImpl$getNotificationController$1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$65) TakeScreenshotExecutorImpl.this.screenshotNotificationControllerFactory).create(i);
            }
        })).notifyScreenshotError(R.string.screenshot_failed_to_capture_text);
        requestCallback.reportError();
    }
}
