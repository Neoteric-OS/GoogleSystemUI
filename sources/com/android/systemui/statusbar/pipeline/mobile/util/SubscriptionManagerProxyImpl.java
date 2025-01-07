package com.android.systemui.statusbar.pipeline.mobile.util;

import android.telephony.SubscriptionManager;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SubscriptionManagerProxyImpl {
    public final CoroutineDispatcher backgroundDispatcher;
    public final SubscriptionManager subscriptionManager;

    public SubscriptionManagerProxyImpl(CoroutineDispatcher coroutineDispatcher, SubscriptionManager subscriptionManager) {
        this.backgroundDispatcher = coroutineDispatcher;
        this.subscriptionManager = subscriptionManager;
    }

    public final Object getActiveSubscriptionInfo(int i, Continuation continuation) {
        return BuildersKt.withContext(this.backgroundDispatcher, new SubscriptionManagerProxyImpl$getActiveSubscriptionInfo$2(this, i, null), continuation);
    }
}
