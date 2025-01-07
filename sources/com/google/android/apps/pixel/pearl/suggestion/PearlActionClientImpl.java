package com.google.android.apps.pixel.pearl.suggestion;

import android.content.Context;
import android.os.UserHandle;
import kotlinx.coroutines.sync.MutexImpl;
import kotlinx.coroutines.sync.MutexKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PearlActionClientImpl {
    public IPearlActionService actionService;
    public final Context context;
    public final MutexImpl mutex = MutexKt.Mutex$default();
    public PearlActionClientImpl$connect$2$1$1 serviceConnection;
    public final UserHandle userHandle;

    public PearlActionClientImpl(Context context, UserHandle userHandle) {
        this.context = context;
        this.userHandle = userHandle;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00c1 A[Catch: all -> 0x00d8, TryCatch #1 {all -> 0x00d8, blocks: (B:24:0x0090, B:26:0x00c1, B:28:0x00c9, B:30:0x00d2, B:31:0x00dd, B:35:0x00e9, B:36:0x00f0), top: B:23:0x0090 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00e9 A[Catch: all -> 0x00d8, TRY_ENTER, TryCatch #1 {all -> 0x00d8, blocks: (B:24:0x0090, B:26:0x00c1, B:28:0x00c9, B:30:0x00d2, B:31:0x00dd, B:35:0x00e9, B:36:0x00f0), top: B:23:0x0090 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /* JADX WARN: Type inference failed for: r11v16, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r8v1, types: [com.google.android.apps.pixel.pearl.suggestion.PearlActionClientImpl$connect$2$1$1] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object connect(kotlin.coroutines.jvm.internal.ContinuationImpl r12) {
        /*
            Method dump skipped, instructions count: 245
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.apps.pixel.pearl.suggestion.PearlActionClientImpl.connect(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x008d A[Catch: all -> 0x00ac, TRY_LEAVE, TryCatch #0 {all -> 0x00ac, blocks: (B:12:0x0089, B:14:0x008d), top: B:11:0x0089 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object saveScreenshotAndProvideActions(android.graphics.Bitmap r14, android.content.ComponentName r15, android.app.assist.AssistContent r16, int r17, int r18, kotlin.jvm.functions.Function1 r19, kotlin.jvm.functions.Function1 r20, kotlin.coroutines.jvm.internal.ContinuationImpl r21) {
        /*
            Method dump skipped, instructions count: 184
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.apps.pixel.pearl.suggestion.PearlActionClientImpl.saveScreenshotAndProvideActions(android.graphics.Bitmap, android.content.ComponentName, android.app.assist.AssistContent, int, int, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
