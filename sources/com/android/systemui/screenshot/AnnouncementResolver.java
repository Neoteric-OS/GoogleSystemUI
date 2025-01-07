package com.android.systemui.screenshot;

import com.android.systemui.screenshot.data.repository.ProfileTypeRepositoryImpl;
import com.android.systemui.screenshot.resources.Messages;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AnnouncementResolver {
    public final CoroutineScope mainScope;
    public final Messages messages;
    public final ProfileTypeRepositoryImpl profileTypes;

    public AnnouncementResolver(Messages messages, ProfileTypeRepositoryImpl profileTypeRepositoryImpl, CoroutineScope coroutineScope) {
        this.messages = messages;
        this.profileTypes = profileTypeRepositoryImpl;
        this.mainScope = coroutineScope;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getScreenshotAnnouncement(int r5, kotlin.coroutines.jvm.internal.ContinuationImpl r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.android.systemui.screenshot.AnnouncementResolver$getScreenshotAnnouncement$1
            if (r0 == 0) goto L13
            r0 = r6
            com.android.systemui.screenshot.AnnouncementResolver$getScreenshotAnnouncement$1 r0 = (com.android.systemui.screenshot.AnnouncementResolver$getScreenshotAnnouncement$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.screenshot.AnnouncementResolver$getScreenshotAnnouncement$1 r0 = new com.android.systemui.screenshot.AnnouncementResolver$getScreenshotAnnouncement$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            java.lang.Object r4 = r0.L$0
            com.android.systemui.screenshot.AnnouncementResolver r4 = (com.android.systemui.screenshot.AnnouncementResolver) r4
            kotlin.ResultKt.throwOnFailure(r6)
            goto L43
        L2b:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L33:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.L$0 = r4
            r0.label = r3
            com.android.systemui.screenshot.data.repository.ProfileTypeRepositoryImpl r6 = r4.profileTypes
            java.lang.Object r6 = r6.getProfileType(r5, r0)
            if (r6 != r1) goto L43
            return r1
        L43:
            com.android.systemui.screenshot.data.model.ProfileType r6 = (com.android.systemui.screenshot.data.model.ProfileType) r6
            int r5 = r6.ordinal()
            if (r5 == r3) goto L64
            r6 = 2
            if (r5 == r6) goto L59
            com.android.systemui.screenshot.resources.Messages r4 = r4.messages
            kotlin.Lazy r4 = r4.savingScreenshotAnnouncement$delegate
            java.lang.Object r4 = r4.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L6e
        L59:
            com.android.systemui.screenshot.resources.Messages r4 = r4.messages
            kotlin.Lazy r4 = r4.savingToWorkProfileAnnouncement$delegate
            java.lang.Object r4 = r4.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L6e
        L64:
            com.android.systemui.screenshot.resources.Messages r4 = r4.messages
            kotlin.Lazy r4 = r4.savingToPrivateProfileAnnouncement$delegate
            java.lang.Object r4 = r4.getValue()
            java.lang.String r4 = (java.lang.String) r4
        L6e:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.screenshot.AnnouncementResolver.getScreenshotAnnouncement(int, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
