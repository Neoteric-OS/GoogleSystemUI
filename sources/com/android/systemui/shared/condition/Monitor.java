package com.android.systemui.shared.condition;

import android.util.ArraySet;
import android.util.Log;
import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.shared.condition.Monitor;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class Monitor {
    public final Executor mExecutor;
    public final TableLogBuffer mLogBuffer;
    public final Set mPreconditions;
    public final HashMap mConditions = new HashMap();
    public final HashMap mSubscriptions = new HashMap();
    public final AnonymousClass1 mConditionCallback = new AnonymousClass1();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.shared.condition.Monitor$1, reason: invalid class name */
    public final class AnonymousClass1 {
        public AnonymousClass1() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Callback {
        void onConditionsChanged(boolean z);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Subscription {
        public final Callback mCallback;
        public final Set mConditions;
        public final Subscription mNestedSubscription;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Token {
        }

        public Subscription(Set set, Callback callback, Subscription subscription) {
            this.mConditions = Collections.unmodifiableSet(set);
            this.mCallback = callback;
            this.mNestedSubscription = subscription;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SubscriptionState {
        public boolean mActive;
        public Boolean mAllConditionsMet;
        public Subscription.Token mNestedSubscriptionToken;
        public final Subscription mSubscription;

        public SubscriptionState(Subscription subscription) {
            this.mSubscription = subscription;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x00b0  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x00d8  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void update(com.android.systemui.shared.condition.Monitor r9) {
            /*
                r8 = this;
                com.android.systemui.shared.condition.Monitor$Subscription r0 = r8.mSubscription
                java.util.Set r1 = r0.mConditions
                boolean r2 = r1.isEmpty()
                r3 = 0
                r4 = 1
                r5 = 0
                if (r2 == 0) goto L10
            Ld:
                r7 = r3
                goto L91
            L10:
                r2 = r1
                java.lang.Iterable r2 = (java.lang.Iterable) r2
                java.util.ArrayList r6 = new java.util.ArrayList
                r6.<init>()
                java.util.Iterator r2 = r2.iterator()
            L1c:
                boolean r7 = r2.hasNext()
                if (r7 == 0) goto L2b
                java.lang.Object r7 = r2.next()
                com.android.systemui.shared.condition.Condition r7 = (com.android.systemui.shared.condition.Condition) r7
                java.lang.Boolean r7 = r7.mIsConditionMet
                goto L1c
            L2b:
                boolean r2 = r6.isEmpty()
                if (r2 == 0) goto L32
                goto L33
            L32:
                r1 = r6
            L33:
                java.lang.Iterable r1 = (java.lang.Iterable) r1
                java.util.ArrayList r2 = new java.util.ArrayList
                r6 = 10
                int r6 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r1, r6)
                r2.<init>(r6)
                java.util.Iterator r1 = r1.iterator()
            L44:
                boolean r6 = r1.hasNext()
                if (r6 == 0) goto L64
                java.lang.Object r6 = r1.next()
                com.android.systemui.shared.condition.Condition r6 = (com.android.systemui.shared.condition.Condition) r6
                java.lang.Boolean r6 = r6.mIsConditionMet
                if (r6 == 0) goto L5f
                java.lang.Boolean r7 = java.lang.Boolean.TRUE
                boolean r6 = r7.equals(r6)
                java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)
                goto L60
            L5f:
                r6 = r3
            L60:
                r2.add(r6)
                goto L44
            L64:
                boolean r1 = r2.isEmpty()
                if (r1 == 0) goto L6b
                goto Ld
            L6b:
                java.util.Iterator r1 = r2.iterator()
                r2 = r5
            L70:
                boolean r6 = r1.hasNext()
                if (r6 == 0) goto L89
                java.lang.Object r6 = r1.next()
                java.lang.Boolean r6 = (java.lang.Boolean) r6
                if (r6 != 0) goto L80
                r2 = r4
                goto L70
            L80:
                java.lang.Boolean r7 = java.lang.Boolean.FALSE
                boolean r6 = r6.equals(r7)
                if (r6 == 0) goto L70
                goto L91
            L89:
                if (r2 == 0) goto L8c
                goto Ld
            L8c:
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r4)
                r7 = r1
            L91:
                if (r7 == 0) goto L9b
                boolean r1 = r7.booleanValue()
                if (r1 == 0) goto L9a
                goto L9b
            L9a:
                r4 = r5
            L9b:
                java.lang.Boolean r1 = r8.mAllConditionsMet
                if (r1 == 0) goto La6
                boolean r1 = r1.booleanValue()
                if (r4 != r1) goto La6
                return
            La6:
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r4)
                r8.mAllConditionsMet = r1
                com.android.systemui.shared.condition.Monitor$Subscription r2 = r0.mNestedSubscription
                if (r2 == 0) goto Ld8
                if (r4 == 0) goto Lbd
                com.android.systemui.shared.condition.Monitor$Subscription$Token r0 = r8.mNestedSubscriptionToken
                if (r0 != 0) goto Lbd
                com.android.systemui.shared.condition.Monitor$Subscription$Token r9 = r9.addSubscription(r2, r3)
                r8.mNestedSubscriptionToken = r9
                goto Ld7
            Lbd:
                boolean r0 = r1.booleanValue()
                if (r0 != 0) goto Ld7
                com.android.systemui.shared.condition.Monitor$Subscription$Token r0 = r8.mNestedSubscriptionToken
                if (r0 == 0) goto Ld7
                if (r0 != 0) goto Lca
                goto Ld7
            Lca:
                java.util.concurrent.Executor r1 = r9.mExecutor
                com.android.systemui.shared.condition.Monitor$$ExternalSyntheticLambda2 r2 = new com.android.systemui.shared.condition.Monitor$$ExternalSyntheticLambda2
                r4 = 0
                r2.<init>(r4, r9, r0)
                r1.execute(r2)
                r8.mNestedSubscriptionToken = r3
            Ld7:
                return
            Ld8:
                com.android.systemui.shared.condition.Monitor$Callback r8 = r0.mCallback
                r8.onConditionsChanged(r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.shared.condition.Monitor.SubscriptionState.update(com.android.systemui.shared.condition.Monitor):void");
        }
    }

    public Monitor(Executor executor, Set set, TableLogBuffer tableLogBuffer) {
        this.mExecutor = executor;
        this.mPreconditions = set;
        this.mLogBuffer = tableLogBuffer;
    }

    public final Subscription.Token addSubscription(final Subscription subscription, Set set) {
        if (set != null) {
            ArraySet arraySet = new ArraySet();
            arraySet.addAll(set);
            subscription = new Subscription(arraySet, null, subscription);
        }
        final Subscription.Token token = new Subscription.Token();
        final SubscriptionState subscriptionState = new SubscriptionState(subscription);
        this.mExecutor.execute(new Runnable() { // from class: com.android.systemui.shared.condition.Monitor$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                Monitor monitor = Monitor.this;
                Monitor.Subscription.Token token2 = token;
                Monitor.SubscriptionState subscriptionState2 = subscriptionState;
                Monitor.Subscription subscription2 = subscription;
                monitor.getClass();
                if (Log.isLoggable("Monitor", 3)) {
                    Log.d("Monitor", "adding subscription");
                }
                monitor.mSubscriptions.put(token2, subscriptionState2);
                subscription2.mConditions.forEach(new Monitor$$ExternalSyntheticLambda1(monitor, token2, 0));
                if (!subscriptionState2.mActive) {
                    subscriptionState2.mActive = true;
                    Monitor.Callback callback = subscriptionState2.mSubscription.mCallback;
                }
                subscriptionState2.update(monitor);
            }
        });
        return token;
    }
}
