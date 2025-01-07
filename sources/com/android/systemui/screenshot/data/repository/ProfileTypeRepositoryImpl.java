package com.android.systemui.screenshot.data.repository;

import android.os.UserManager;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.sync.MutexImpl;
import kotlinx.coroutines.sync.MutexKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ProfileTypeRepositoryImpl {
    public final CoroutineDispatcher background;
    public final Map cache = new LinkedHashMap();
    public final MutexImpl mutex = MutexKt.Mutex$default();
    public final UserManager userManager;

    public ProfileTypeRepositoryImpl(UserManager userManager, CoroutineDispatcher coroutineDispatcher) {
        this.userManager = userManager;
        this.background = coroutineDispatcher;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0073 A[Catch: all -> 0x00a0, TRY_LEAVE, TryCatch #0 {all -> 0x00a0, blocks: (B:26:0x0064, B:28:0x0073), top: B:25:0x0064 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /* JADX WARN: Type inference failed for: r7v9, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r8v10, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getProfileType(int r8, kotlin.coroutines.jvm.internal.ContinuationImpl r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof com.android.systemui.screenshot.data.repository.ProfileTypeRepositoryImpl$getProfileType$1
            if (r0 == 0) goto L13
            r0 = r9
            com.android.systemui.screenshot.data.repository.ProfileTypeRepositoryImpl$getProfileType$1 r0 = (com.android.systemui.screenshot.data.repository.ProfileTypeRepositoryImpl$getProfileType$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.screenshot.data.repository.ProfileTypeRepositoryImpl$getProfileType$1 r0 = new com.android.systemui.screenshot.data.repository.ProfileTypeRepositoryImpl$getProfileType$1
            r0.<init>(r7, r9)
        L18:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L50
            if (r2 == r4) goto L40
            if (r2 != r3) goto L38
            int r7 = r0.I$0
            java.lang.Object r8 = r0.L$1
            kotlinx.coroutines.sync.Mutex r8 = (kotlinx.coroutines.sync.Mutex) r8
            java.lang.Object r0 = r0.L$0
            com.android.systemui.screenshot.data.repository.ProfileTypeRepositoryImpl r0 = (com.android.systemui.screenshot.data.repository.ProfileTypeRepositoryImpl) r0
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L35
            goto L8e
        L35:
            r7 = move-exception
            goto La7
        L38:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L40:
            int r8 = r0.I$0
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.sync.Mutex r7 = (kotlinx.coroutines.sync.Mutex) r7
            java.lang.Object r2 = r0.L$0
            com.android.systemui.screenshot.data.repository.ProfileTypeRepositoryImpl r2 = (com.android.systemui.screenshot.data.repository.ProfileTypeRepositoryImpl) r2
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = r7
            r7 = r2
            goto L64
        L50:
            kotlin.ResultKt.throwOnFailure(r9)
            r0.L$0 = r7
            kotlinx.coroutines.sync.MutexImpl r9 = r7.mutex
            r0.L$1 = r9
            r0.I$0 = r8
            r0.label = r4
            java.lang.Object r2 = r9.lock(r0)
            if (r2 != r1) goto L64
            return r1
        L64:
            java.util.Map r2 = r7.cache     // Catch: java.lang.Throwable -> La0
            java.lang.Integer r4 = new java.lang.Integer     // Catch: java.lang.Throwable -> La0
            r4.<init>(r8)     // Catch: java.lang.Throwable -> La0
            java.lang.Object r2 = r2.get(r4)     // Catch: java.lang.Throwable -> La0
            com.android.systemui.screenshot.data.model.ProfileType r2 = (com.android.systemui.screenshot.data.model.ProfileType) r2     // Catch: java.lang.Throwable -> La0
            if (r2 != 0) goto La3
            kotlinx.coroutines.CoroutineDispatcher r2 = r7.background     // Catch: java.lang.Throwable -> La0
            com.android.systemui.screenshot.data.repository.ProfileTypeRepositoryImpl$getProfileType$2$1 r4 = new com.android.systemui.screenshot.data.repository.ProfileTypeRepositoryImpl$getProfileType$2$1     // Catch: java.lang.Throwable -> La0
            r4.<init>(r7, r8, r5)     // Catch: java.lang.Throwable -> La0
            r0.L$0 = r7     // Catch: java.lang.Throwable -> La0
            r0.L$1 = r9     // Catch: java.lang.Throwable -> La0
            r0.I$0 = r8     // Catch: java.lang.Throwable -> La0
            r0.label = r3     // Catch: java.lang.Throwable -> La0
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r2, r4, r0)     // Catch: java.lang.Throwable -> La0
            if (r0 != r1) goto L89
            return r1
        L89:
            r6 = r0
            r0 = r7
            r7 = r8
            r8 = r9
            r9 = r6
        L8e:
            r1 = r9
            com.android.systemui.screenshot.data.model.ProfileType r1 = (com.android.systemui.screenshot.data.model.ProfileType) r1     // Catch: java.lang.Throwable -> L35
            java.lang.Integer r2 = new java.lang.Integer     // Catch: java.lang.Throwable -> L35
            r2.<init>(r7)     // Catch: java.lang.Throwable -> L35
            java.util.Map r7 = r0.cache     // Catch: java.lang.Throwable -> L35
            r7.put(r2, r1)     // Catch: java.lang.Throwable -> L35
            r2 = r9
            com.android.systemui.screenshot.data.model.ProfileType r2 = (com.android.systemui.screenshot.data.model.ProfileType) r2     // Catch: java.lang.Throwable -> L35
            r9 = r8
            goto La3
        La0:
            r7 = move-exception
            r8 = r9
            goto La7
        La3:
            r9.unlock(r5)
            return r2
        La7:
            r8.unlock(r5)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.screenshot.data.repository.ProfileTypeRepositoryImpl.getProfileType(int, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
