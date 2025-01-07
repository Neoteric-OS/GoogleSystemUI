package com.android.systemui.statusbar.notification.row;

import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NotificationInlineImageCache$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ Set f$0;

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return !this.f$0.contains(((Map.Entry) obj).getKey());
    }
}
