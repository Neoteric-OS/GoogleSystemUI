package com.android.systemui.qs.tiles.dialog;

import android.telephony.SubscriptionInfo;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class InternetDialogController$$ExternalSyntheticLambda1 implements Predicate {
    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        SubscriptionInfo subscriptionInfo = (SubscriptionInfo) obj;
        return (subscriptionInfo == null || subscriptionInfo.getDisplayName() == null) ? false : true;
    }
}
