package com.android.systemui.screenshot.policy;

import android.content.ComponentName;
import android.os.UserHandle;
import com.android.systemui.screenshot.ImageCaptureImpl;
import com.android.systemui.screenshot.data.repository.DisplayContentRepositoryImpl;
import java.util.List;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PolicyRequestProcessor {
    public final CoroutineDispatcher background;
    public final ImageCaptureImpl capture;
    public final ComponentName defaultComponent;
    public final UserHandle defaultOwner;
    public final DisplayContentRepositoryImpl displayTasks;
    public final List policies;

    public PolicyRequestProcessor(CoroutineDispatcher coroutineDispatcher, ImageCaptureImpl imageCaptureImpl, DisplayContentRepositoryImpl displayContentRepositoryImpl, List list, UserHandle userHandle, ComponentName componentName) {
        this.background = coroutineDispatcher;
        this.capture = imageCaptureImpl;
        this.displayTasks = displayContentRepositoryImpl;
        this.policies = list;
        this.defaultOwner = userHandle;
        this.defaultComponent = componentName;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object modify(com.android.systemui.screenshot.ScreenshotData r9, com.android.systemui.screenshot.policy.CaptureParameters r10, kotlin.coroutines.jvm.internal.ContinuationImpl r11) {
        /*
            r8 = this;
            boolean r0 = r11 instanceof com.android.systemui.screenshot.policy.PolicyRequestProcessor$modify$1
            if (r0 == 0) goto L14
            r0 = r11
            com.android.systemui.screenshot.policy.PolicyRequestProcessor$modify$1 r0 = (com.android.systemui.screenshot.policy.PolicyRequestProcessor$modify$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r7 = r0
            goto L1a
        L14:
            com.android.systemui.screenshot.policy.PolicyRequestProcessor$modify$1 r0 = new com.android.systemui.screenshot.policy.PolicyRequestProcessor$modify$1
            r0.<init>(r8, r11)
            goto L12
        L1a:
            java.lang.Object r11 = r7.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r7.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L38
            if (r1 == r3) goto L34
            if (r1 != r2) goto L2c
            kotlin.ResultKt.throwOnFailure(r11)
            goto L73
        L2c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L34:
            kotlin.ResultKt.throwOnFailure(r11)
            goto L58
        L38:
            kotlin.ResultKt.throwOnFailure(r11)
            com.android.systemui.screenshot.policy.CaptureType r11 = r10.type
            boolean r1 = r11 instanceof com.android.systemui.screenshot.policy.CaptureType.IsolatedTask
            if (r1 == 0) goto L5b
            android.content.ComponentName r4 = r10.component
            android.os.UserHandle r10 = r10.owner
            com.android.systemui.screenshot.policy.CaptureType$IsolatedTask r11 = (com.android.systemui.screenshot.policy.CaptureType.IsolatedTask) r11
            android.graphics.Rect r6 = r11.taskBounds
            r7.label = r3
            int r5 = r11.taskId
            r1 = r8
            r2 = r9
            r3 = r4
            r4 = r10
            java.lang.Object r11 = r1.replaceWithTaskSnapshot(r2, r3, r4, r5, r6, r7)
            if (r11 != r0) goto L58
            return r0
        L58:
            com.android.systemui.screenshot.ScreenshotData r11 = (com.android.systemui.screenshot.ScreenshotData) r11
            goto L75
        L5b:
            boolean r1 = r11 instanceof com.android.systemui.screenshot.policy.CaptureType.FullScreen
            if (r1 == 0) goto L76
            android.content.ComponentName r3 = r10.component
            android.os.UserHandle r4 = r10.owner
            com.android.systemui.screenshot.policy.CaptureType$FullScreen r11 = (com.android.systemui.screenshot.policy.CaptureType.FullScreen) r11
            r7.label = r2
            r6 = 0
            int r5 = r11.displayId
            r1 = r8
            r2 = r9
            java.lang.Object r11 = r1.replaceWithScreenshot(r2, r3, r4, r5, r6, r7)
            if (r11 != r0) goto L73
            return r0
        L73:
            com.android.systemui.screenshot.ScreenshotData r11 = (com.android.systemui.screenshot.ScreenshotData) r11
        L75:
            return r11
        L76:
            kotlin.NoWhenBranchMatchedException r8 = new kotlin.NoWhenBranchMatchedException
            r8.<init>()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.screenshot.policy.PolicyRequestProcessor.modify(com.android.systemui.screenshot.ScreenshotData, com.android.systemui.screenshot.policy.CaptureParameters, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002d  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x00d6 -> B:20:0x00d7). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object process(com.android.systemui.screenshot.ScreenshotData r21, kotlin.coroutines.jvm.internal.ContinuationImpl r22) {
        /*
            Method dump skipped, instructions count: 398
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.screenshot.policy.PolicyRequestProcessor.process(com.android.systemui.screenshot.ScreenshotData, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object replaceWithScreenshot(com.android.systemui.screenshot.ScreenshotData r10, android.content.ComponentName r11, android.os.UserHandle r12, int r13, java.lang.Integer r14, kotlin.coroutines.jvm.internal.ContinuationImpl r15) {
        /*
            r9 = this;
            boolean r0 = r15 instanceof com.android.systemui.screenshot.policy.PolicyRequestProcessor$replaceWithScreenshot$1
            if (r0 == 0) goto L13
            r0 = r15
            com.android.systemui.screenshot.policy.PolicyRequestProcessor$replaceWithScreenshot$1 r0 = (com.android.systemui.screenshot.policy.PolicyRequestProcessor$replaceWithScreenshot$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.screenshot.policy.PolicyRequestProcessor$replaceWithScreenshot$1 r0 = new com.android.systemui.screenshot.policy.PolicyRequestProcessor$replaceWithScreenshot$1
            r0.<init>(r9, r15)
        L18:
            java.lang.Object r15 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L46
            if (r2 != r3) goto L3e
            java.lang.Object r9 = r0.L$3
            r14 = r9
            java.lang.Integer r14 = (java.lang.Integer) r14
            java.lang.Object r9 = r0.L$2
            r12 = r9
            android.os.UserHandle r12 = (android.os.UserHandle) r12
            java.lang.Object r9 = r0.L$1
            r11 = r9
            android.content.ComponentName r11 = (android.content.ComponentName) r11
            java.lang.Object r9 = r0.L$0
            r10 = r9
            com.android.systemui.screenshot.ScreenshotData r10 = (com.android.systemui.screenshot.ScreenshotData) r10
            kotlin.ResultKt.throwOnFailure(r15)
        L3a:
            r2 = r10
            r5 = r11
            r4 = r12
            goto L7d
        L3e:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L46:
            kotlin.ResultKt.throwOnFailure(r15)
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            java.lang.String r2 = "Capturing screenshot: "
            r15.<init>(r2)
            r15.append(r11)
            java.lang.String r2 = " / "
            r15.append(r2)
            r15.append(r12)
            java.lang.String r15 = r15.toString()
            java.lang.String r2 = "PolicyRequestProcessor"
            android.util.Log.i(r2, r15)
            r0.L$0 = r10
            r0.L$1 = r11
            r0.L$2 = r12
            r0.L$3 = r14
            r0.label = r3
            com.android.systemui.screenshot.policy.PolicyRequestProcessor$captureDisplay$2 r15 = new com.android.systemui.screenshot.policy.PolicyRequestProcessor$captureDisplay$2
            r2 = 0
            r15.<init>(r9, r13, r2)
            kotlinx.coroutines.CoroutineDispatcher r9 = r9.background
            java.lang.Object r15 = kotlinx.coroutines.BuildersKt.withContext(r9, r15, r0)
            if (r15 != r1) goto L3a
            return r1
        L7d:
            r8 = r15
            android.graphics.Bitmap r8 = (android.graphics.Bitmap) r8
            android.graphics.Rect r6 = new android.graphics.Rect
            r9 = 0
            if (r8 == 0) goto L8a
            int r10 = r8.getWidth()
            goto L8b
        L8a:
            r10 = r9
        L8b:
            if (r8 == 0) goto L92
            int r11 = r8.getHeight()
            goto L93
        L92:
            r11 = r9
        L93:
            r6.<init>(r9, r9, r10, r11)
            if (r14 == 0) goto L9e
            int r9 = r14.intValue()
        L9c:
            r7 = r9
            goto La0
        L9e:
            r9 = -1
            goto L9c
        La0:
            r3 = 1
            com.android.systemui.screenshot.ScreenshotData r9 = com.android.systemui.screenshot.ScreenshotData.copy$default(r2, r3, r4, r5, r6, r7, r8)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.screenshot.policy.PolicyRequestProcessor.replaceWithScreenshot(com.android.systemui.screenshot.ScreenshotData, android.content.ComponentName, android.os.UserHandle, int, java.lang.Integer, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object replaceWithTaskSnapshot(com.android.systemui.screenshot.ScreenshotData r10, android.content.ComponentName r11, android.os.UserHandle r12, int r13, android.graphics.Rect r14, kotlin.coroutines.jvm.internal.ContinuationImpl r15) {
        /*
            r9 = this;
            boolean r0 = r15 instanceof com.android.systemui.screenshot.policy.PolicyRequestProcessor$replaceWithTaskSnapshot$1
            if (r0 == 0) goto L13
            r0 = r15
            com.android.systemui.screenshot.policy.PolicyRequestProcessor$replaceWithTaskSnapshot$1 r0 = (com.android.systemui.screenshot.policy.PolicyRequestProcessor$replaceWithTaskSnapshot$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.screenshot.policy.PolicyRequestProcessor$replaceWithTaskSnapshot$1 r0 = new com.android.systemui.screenshot.policy.PolicyRequestProcessor$replaceWithTaskSnapshot$1
            r0.<init>(r9, r15)
        L18:
            java.lang.Object r15 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L4a
            if (r2 != r3) goto L42
            int r13 = r0.I$0
            java.lang.Object r9 = r0.L$3
            r14 = r9
            android.graphics.Rect r14 = (android.graphics.Rect) r14
            java.lang.Object r9 = r0.L$2
            r12 = r9
            android.os.UserHandle r12 = (android.os.UserHandle) r12
            java.lang.Object r9 = r0.L$1
            r11 = r9
            android.content.ComponentName r11 = (android.content.ComponentName) r11
            java.lang.Object r9 = r0.L$0
            r10 = r9
            com.android.systemui.screenshot.ScreenshotData r10 = (com.android.systemui.screenshot.ScreenshotData) r10
            kotlin.ResultKt.throwOnFailure(r15)
        L3c:
            r2 = r10
            r5 = r11
            r4 = r12
            r7 = r13
            r6 = r14
            goto L80
        L42:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L4a:
            kotlin.ResultKt.throwOnFailure(r15)
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            java.lang.String r2 = "Capturing task snapshot: "
            r15.<init>(r2)
            r15.append(r11)
            java.lang.String r2 = " / "
            r15.append(r2)
            r15.append(r12)
            java.lang.String r15 = r15.toString()
            java.lang.String r2 = "PolicyRequestProcessor"
            android.util.Log.i(r2, r15)
            r0.L$0 = r10
            r0.L$1 = r11
            r0.L$2 = r12
            r0.L$3 = r14
            r0.I$0 = r13
            r0.label = r3
            com.android.systemui.screenshot.ImageCaptureImpl r9 = r9.capture
            r9.getClass()
            java.lang.Object r15 = com.android.systemui.screenshot.ImageCaptureImpl.captureTask$suspendImpl(r9, r13, r0)
            if (r15 != r1) goto L3c
            return r1
        L80:
            r8 = r15
            android.graphics.Bitmap r8 = (android.graphics.Bitmap) r8
            r3 = 3
            com.android.systemui.screenshot.ScreenshotData r9 = com.android.systemui.screenshot.ScreenshotData.copy$default(r2, r3, r4, r5, r6, r7, r8)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.screenshot.policy.PolicyRequestProcessor.replaceWithTaskSnapshot(com.android.systemui.screenshot.ScreenshotData, android.content.ComponentName, android.os.UserHandle, int, android.graphics.Rect, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
