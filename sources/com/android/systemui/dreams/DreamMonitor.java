package com.android.systemui.dreams;

import android.util.ArraySet;
import com.android.systemui.CoreStartable;
import com.android.systemui.dreams.callbacks.DreamStatusBarStateCallback;
import com.android.systemui.dreams.conditions.DreamCondition;
import com.android.systemui.flags.RestartDozeListener;
import com.android.systemui.shared.condition.Monitor;
import com.android.systemui.util.condition.ConditionalCoreStartable$$ExternalSyntheticLambda0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DreamMonitor implements CoreStartable {
    public Monitor.Subscription.Token mBootCompletedToken;
    public final DreamStatusBarStateCallback mCallback;
    public final Monitor mConditionMonitor;
    public final DreamCondition mDreamCondition;
    public final Monitor mMonitor;
    public final RestartDozeListener mRestartDozeListener;
    public Monitor.Subscription.Token mStartToken;

    public DreamMonitor(Monitor monitor, DreamCondition dreamCondition, DreamStatusBarStateCallback dreamStatusBarStateCallback, RestartDozeListener restartDozeListener) {
        this.mMonitor = monitor;
        this.mConditionMonitor = monitor;
        this.mDreamCondition = dreamCondition;
        this.mCallback = dreamStatusBarStateCallback;
        this.mRestartDozeListener = restartDozeListener;
    }

    @Override // com.android.systemui.CoreStartable
    public final void onBootCompleted() {
        Monitor.Subscription subscription = new Monitor.Subscription(new ArraySet(), new ConditionalCoreStartable$$ExternalSyntheticLambda0(this, 0), null);
        Monitor monitor = this.mMonitor;
        this.mBootCompletedToken = monitor.addSubscription(subscription, monitor.mPreconditions);
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        Monitor.Subscription subscription = new Monitor.Subscription(new ArraySet(), new ConditionalCoreStartable$$ExternalSyntheticLambda0(this, 1), null);
        Monitor monitor = this.mMonitor;
        this.mStartToken = monitor.addSubscription(subscription, monitor.mPreconditions);
    }
}
