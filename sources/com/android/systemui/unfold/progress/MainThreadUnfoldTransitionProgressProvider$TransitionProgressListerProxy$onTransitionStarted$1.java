package com.android.systemui.unfold.progress;

import com.android.systemui.unfold.progress.MainThreadUnfoldTransitionProgressProvider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MainThreadUnfoldTransitionProgressProvider$TransitionProgressListerProxy$onTransitionStarted$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ MainThreadUnfoldTransitionProgressProvider.TransitionProgressListerProxy this$0;

    public /* synthetic */ MainThreadUnfoldTransitionProgressProvider$TransitionProgressListerProxy$onTransitionStarted$1(MainThreadUnfoldTransitionProgressProvider.TransitionProgressListerProxy transitionProgressListerProxy, int i) {
        this.$r8$classId = i;
        this.this$0 = transitionProgressListerProxy;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                this.this$0.listener.onTransitionStarted();
                break;
            case 1:
                this.this$0.listener.onTransitionFinished();
                break;
            default:
                this.this$0.listener.onTransitionFinishing();
                break;
        }
    }
}
