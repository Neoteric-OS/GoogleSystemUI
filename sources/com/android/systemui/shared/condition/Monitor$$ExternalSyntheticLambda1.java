package com.android.systemui.shared.condition;

import android.util.ArraySet;
import android.util.Log;
import com.android.systemui.shared.condition.Monitor;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class Monitor$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Monitor f$0;
    public final /* synthetic */ Monitor.Subscription.Token f$1;

    public /* synthetic */ Monitor$$ExternalSyntheticLambda1(Monitor monitor, Monitor.Subscription.Token token, int i) {
        this.$r8$classId = i;
        this.f$0 = monitor;
        this.f$1 = token;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                Monitor monitor = this.f$0;
                Monitor.Subscription.Token token = this.f$1;
                Condition condition = (Condition) obj;
                if (!monitor.mConditions.containsKey(condition)) {
                    monitor.mConditions.put(condition, new ArraySet());
                    if (Log.isLoggable(condition.mTag, 3)) {
                        Log.d(condition.mTag, "adding callback");
                    }
                    ArrayList arrayList = condition.mCallbacks;
                    Monitor.AnonymousClass1 anonymousClass1 = monitor.mConditionCallback;
                    arrayList.add(new WeakReference(anonymousClass1));
                    if (condition.mStarted) {
                        Monitor.this.mExecutor.execute(new Monitor$$ExternalSyntheticLambda2(1, anonymousClass1, condition));
                    } else {
                        condition.start();
                        condition.mStarted = true;
                    }
                }
                ((ArraySet) monitor.mConditions.get(condition)).add(token);
                break;
            default:
                Monitor monitor2 = this.f$0;
                Monitor.Subscription.Token token2 = this.f$1;
                Condition condition2 = (Condition) obj;
                if (!monitor2.mConditions.containsKey(condition2)) {
                    Log.e("Monitor", "condition not present:" + condition2);
                    break;
                } else {
                    Set set = (Set) monitor2.mConditions.get(condition2);
                    set.remove(token2);
                    if (set.isEmpty()) {
                        if (Log.isLoggable(condition2.mTag, 3)) {
                            Log.d(condition2.mTag, "removing callback");
                        }
                        Iterator it = condition2.mCallbacks.iterator();
                        while (it.hasNext()) {
                            Monitor.AnonymousClass1 anonymousClass12 = (Monitor.AnonymousClass1) ((WeakReference) it.next()).get();
                            if (anonymousClass12 == null || anonymousClass12 == monitor2.mConditionCallback) {
                                it.remove();
                            }
                        }
                        if (condition2.mCallbacks.isEmpty() && condition2.mStarted) {
                            condition2.stop();
                            condition2.mStarted = false;
                        }
                        monitor2.mConditions.remove(condition2);
                        break;
                    }
                }
                break;
        }
    }
}
