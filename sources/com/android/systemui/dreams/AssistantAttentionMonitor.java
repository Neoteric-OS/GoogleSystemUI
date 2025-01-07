package com.android.systemui.dreams;

import android.util.ArraySet;
import android.util.Log;
import com.android.systemui.CoreStartable;
import com.android.systemui.dreams.callbacks.AssistantAttentionCallback;
import com.android.systemui.dreams.conditions.AssistantAttentionCondition;
import com.android.systemui.shared.condition.Monitor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AssistantAttentionMonitor implements CoreStartable {
    public final AssistantAttentionCondition mAssistantAttentionCondition;
    public final AssistantAttentionCallback mCallback;
    public final Monitor mConditionMonitor;

    public AssistantAttentionMonitor(Monitor monitor, AssistantAttentionCondition assistantAttentionCondition, AssistantAttentionCallback assistantAttentionCallback) {
        this.mConditionMonitor = monitor;
        this.mAssistantAttentionCondition = assistantAttentionCondition;
        this.mCallback = assistantAttentionCallback;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        if (Log.isLoggable("AssistAttentionMonitor", 3)) {
            Log.d("AssistAttentionMonitor", "started");
        }
        ArraySet arraySet = new ArraySet();
        arraySet.add(this.mAssistantAttentionCondition);
        Monitor.Subscription subscription = new Monitor.Subscription(arraySet, this.mCallback, null);
        Monitor monitor = this.mConditionMonitor;
        monitor.addSubscription(subscription, monitor.mPreconditions);
    }
}
