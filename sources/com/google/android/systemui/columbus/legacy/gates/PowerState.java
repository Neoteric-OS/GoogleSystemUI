package com.google.android.systemui.columbus.legacy.gates;

import com.android.systemui.keyguard.WakefulnessLifecycle;
import dagger.Lazy;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PowerState extends Gate {
    public final CoroutineDispatcher bgDispatcher;
    public final Lazy powerManager;
    public final Lazy wakefulnessLifecycle;
    public final PowerState$wakefulnessLifecycleObserver$1 wakefulnessLifecycleObserver = new WakefulnessLifecycle.Observer() { // from class: com.google.android.systemui.columbus.legacy.gates.PowerState$wakefulnessLifecycleObserver$1
        @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
        public final void onFinishedGoingToSleep$1() {
            PowerState.this.updateBlocking$2();
        }

        @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
        public final void onStartedWakingUp() {
            PowerState.this.updateBlocking$2();
        }
    };

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.android.systemui.columbus.legacy.gates.PowerState$wakefulnessLifecycleObserver$1] */
    public PowerState(Lazy lazy, Lazy lazy2, CoroutineDispatcher coroutineDispatcher) {
        this.powerManager = lazy;
        this.wakefulnessLifecycle = lazy2;
        this.bgDispatcher = coroutineDispatcher;
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final void onActivate() {
        ((WakefulnessLifecycle) this.wakefulnessLifecycle.get()).addObserver(this.wakefulnessLifecycleObserver);
        updateBlocking$2();
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final void onDeactivate() {
        ((WakefulnessLifecycle) this.wakefulnessLifecycle.get()).removeObserver(this.wakefulnessLifecycleObserver);
    }

    public final void updateBlocking$2() {
        PowerState$updateBlocking$1 powerState$updateBlocking$1 = new PowerState$updateBlocking$1(this, null);
        BuildersKt.launch$default(this.coroutineScope, this.mainDispatcher, null, powerState$updateBlocking$1, 2);
    }
}
