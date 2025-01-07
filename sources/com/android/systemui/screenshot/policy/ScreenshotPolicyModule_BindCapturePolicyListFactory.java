package com.android.systemui.screenshot.policy;

import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ScreenshotPolicyModule_BindCapturePolicyListFactory implements Provider {
    public static List bindCapturePolicyList(PrivateProfilePolicy privateProfilePolicy, WorkProfilePolicy workProfilePolicy) {
        List listOf = CollectionsKt__CollectionsKt.listOf(workProfilePolicy, privateProfilePolicy);
        Preconditions.checkNotNullFromProvides(listOf);
        return listOf;
    }
}
