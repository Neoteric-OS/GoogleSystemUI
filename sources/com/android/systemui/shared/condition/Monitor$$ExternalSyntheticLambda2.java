package com.android.systemui.shared.condition;

import android.util.ArraySet;
import android.util.Log;
import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.plugins.log.TableLogBufferBase;
import com.android.systemui.shared.condition.Monitor;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class Monitor$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ Monitor$$ExternalSyntheticLambda2(int i, Object obj, Object obj2) {
        this.$r8$classId = i;
        this.f$0 = obj;
        this.f$1 = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                Monitor monitor = (Monitor) this.f$0;
                Monitor.Subscription.Token token = (Monitor.Subscription.Token) this.f$1;
                monitor.getClass();
                if (Log.isLoggable("Monitor", 3)) {
                    Log.d("Monitor", "removing subscription");
                }
                if (!monitor.mSubscriptions.containsKey(token)) {
                    Log.e("Monitor", "subscription not present:" + token);
                    break;
                } else {
                    Monitor.SubscriptionState subscriptionState = (Monitor.SubscriptionState) monitor.mSubscriptions.remove(token);
                    subscriptionState.mSubscription.mConditions.forEach(new Monitor$$ExternalSyntheticLambda1(monitor, token, 1));
                    if (subscriptionState.mActive) {
                        subscriptionState.mActive = false;
                        Monitor.Callback callback = subscriptionState.mSubscription.mCallback;
                    }
                    Monitor.Subscription.Token token2 = subscriptionState.mNestedSubscriptionToken;
                    if (token2 != null) {
                        monitor.mExecutor.execute(new Monitor$$ExternalSyntheticLambda2(0, monitor, token2));
                        subscriptionState.mNestedSubscriptionToken = null;
                        break;
                    }
                }
                break;
            default:
                Monitor.AnonymousClass1 anonymousClass1 = (Monitor.AnonymousClass1) this.f$0;
                Condition condition = (Condition) this.f$1;
                final Monitor monitor2 = Monitor.this;
                TableLogBuffer tableLogBuffer = monitor2.mLogBuffer;
                if (tableLogBuffer != null) {
                    String str = condition.mTag;
                    Boolean bool = condition.mIsConditionMet;
                    TableLogBufferBase.DefaultImpls.logChange(tableLogBuffer, "", str, bool != null ? Boolean.TRUE.equals(bool) ? "True" : "False" : "Invalid");
                }
                ArraySet arraySet = (ArraySet) monitor2.mConditions.get(condition);
                if (arraySet != null) {
                    arraySet.stream().forEach(new Consumer() { // from class: com.android.systemui.shared.condition.Monitor$$ExternalSyntheticLambda3
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            Monitor monitor3 = Monitor.this;
                            ((Monitor.SubscriptionState) monitor3.mSubscriptions.get((Monitor.Subscription.Token) obj)).update(monitor3);
                        }
                    });
                    break;
                }
                break;
        }
    }
}
