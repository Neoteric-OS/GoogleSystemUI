package com.android.systemui.unfold.progress;

import com.android.systemui.unfold.UnfoldTransitionProgressProvider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PhysicsBasedUnfoldTransitionProgressProvider$addCallback$1 implements Runnable {
    public final /* synthetic */ UnfoldTransitionProgressProvider.TransitionProgressListener $listener;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ PhysicsBasedUnfoldTransitionProgressProvider this$0;

    public /* synthetic */ PhysicsBasedUnfoldTransitionProgressProvider$addCallback$1(PhysicsBasedUnfoldTransitionProgressProvider physicsBasedUnfoldTransitionProgressProvider, UnfoldTransitionProgressProvider.TransitionProgressListener transitionProgressListener, int i) {
        this.$r8$classId = i;
        this.this$0 = physicsBasedUnfoldTransitionProgressProvider;
        this.$listener = transitionProgressListener;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                this.this$0.listeners.add(this.$listener);
                break;
            default:
                this.this$0.listeners.remove(this.$listener);
                break;
        }
    }
}
