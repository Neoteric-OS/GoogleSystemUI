package com.android.systemui.statusbar.notification.stack;

import com.android.internal.util.LatencyTracker;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DisplaySwitchNotificationsHiderTracker {
    public final LatencyTracker latencyTracker;
    public final ShadeInteractor notificationsInteractor;

    public DisplaySwitchNotificationsHiderTracker(ShadeInteractor shadeInteractor, LatencyTracker latencyTracker) {
        this.notificationsInteractor = shadeInteractor;
        this.latencyTracker = latencyTracker;
    }

    public final Object trackNotificationHideTimeWhenVisible(Flow flow, Continuation continuation) {
        Object collect = FlowKt.distinctUntilChanged(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(flow, ((ShadeInteractorImpl) this.notificationsInteractor).baseShadeInteractor.isAnyExpanded(), new DisplaySwitchNotificationsHiderTracker$trackNotificationHideTimeWhenVisible$2(3, null))).collect(new DisplaySwitchNotificationsHiderTracker$trackNotificationHideTime$2(this, 1), continuation);
        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
    }
}
