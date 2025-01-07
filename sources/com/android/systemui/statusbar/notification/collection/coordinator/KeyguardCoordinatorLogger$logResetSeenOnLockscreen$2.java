package com.android.systemui.statusbar.notification.collection.coordinator;

import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class KeyguardCoordinatorLogger$logResetSeenOnLockscreen$2 extends Lambda implements Function1 {
    public static final KeyguardCoordinatorLogger$logResetSeenOnLockscreen$2 INSTANCE = new KeyguardCoordinatorLogger$logResetSeenOnLockscreen$2();

    public KeyguardCoordinatorLogger$logResetSeenOnLockscreen$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Reset tracking updated notification for lockscreen seen duration threshold: ", ((LogMessage) obj).getStr1());
    }
}
