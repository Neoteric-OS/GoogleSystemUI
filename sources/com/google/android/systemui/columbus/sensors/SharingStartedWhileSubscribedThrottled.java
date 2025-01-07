package com.google.android.systemui.columbus.sensors;

import com.android.systemui.util.time.SystemClockImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.internal.SubscriptionCountStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SharingStartedWhileSubscribedThrottled implements SharingStarted {
    public final SystemClockImpl clock = new SystemClockImpl();

    @Override // kotlinx.coroutines.flow.SharingStarted
    public final Flow command(SubscriptionCountStateFlow subscriptionCountStateFlow) {
        return FlowKt.distinctUntilChanged(com.android.systemui.util.kotlin.FlowKt.throttle(new FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1(new SharingStartedWhileSubscribedThrottled$command$2(2, null), FlowKt.distinctUntilChanged(FlowKt.mapLatest(new SharingStartedWhileSubscribedThrottled$command$1(2, null), subscriptionCountStateFlow))), 1000L, this.clock));
    }
}
