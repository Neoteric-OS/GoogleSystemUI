package com.android.systemui.screenshot.policy;

import android.content.Context;
import com.android.systemui.screenshot.data.repository.ProfileTypeRepositoryImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WorkProfilePolicy implements CapturePolicy {
    public final Context context;
    public final ProfileTypeRepositoryImpl profileTypes;

    public WorkProfilePolicy(ProfileTypeRepositoryImpl profileTypeRepositoryImpl, Context context) {
        this.profileTypes = profileTypeRepositoryImpl;
        this.context = context;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0113 -> B:10:0x0117). Please report as a decompilation issue!!! */
    @Override // com.android.systemui.screenshot.policy.CapturePolicy
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object check(com.android.systemui.screenshot.data.model.DisplayContentModel r12, kotlin.coroutines.jvm.internal.ContinuationImpl r13) {
        /*
            Method dump skipped, instructions count: 349
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.screenshot.policy.WorkProfilePolicy.check(com.android.systemui.screenshot.data.model.DisplayContentModel, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
