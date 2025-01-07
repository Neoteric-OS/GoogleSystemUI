package com.android.systemui.unfold.util;

import com.android.systemui.unfold.UnfoldTransitionProgressProvider;
import com.android.systemui.unfold.updates.FoldProvider;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UnfoldOnlyProgressProvider implements UnfoldTransitionProgressProvider {
    public boolean isFolded;
    public final ScopedUnfoldTransitionProgressProvider scopedProvider;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FoldListener implements FoldProvider.FoldCallback {
        public FoldListener() {
        }

        @Override // com.android.systemui.unfold.updates.FoldProvider.FoldCallback
        public final void onFoldUpdated(boolean z) {
            UnfoldOnlyProgressProvider unfoldOnlyProgressProvider = UnfoldOnlyProgressProvider.this;
            if (z) {
                unfoldOnlyProgressProvider.scopedProvider.setReadyToHandleTransition(true);
            }
            unfoldOnlyProgressProvider.isFolded = z;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SourceTransitionListener implements UnfoldTransitionProgressProvider.TransitionProgressListener {
        public SourceTransitionListener() {
        }

        @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
        public final void onTransitionFinished() {
            UnfoldOnlyProgressProvider unfoldOnlyProgressProvider = UnfoldOnlyProgressProvider.this;
            if (unfoldOnlyProgressProvider.isFolded) {
                return;
            }
            unfoldOnlyProgressProvider.scopedProvider.setReadyToHandleTransition(false);
        }
    }

    public UnfoldOnlyProgressProvider(FoldProvider foldProvider, Executor executor, UnfoldTransitionProgressProvider unfoldTransitionProgressProvider) {
        this.scopedProvider = new ScopedUnfoldTransitionProgressProvider(unfoldTransitionProgressProvider);
        foldProvider.registerCallback(new FoldListener(), executor);
        unfoldTransitionProgressProvider.addCallback(new SourceTransitionListener());
    }

    @Override // com.android.systemui.unfold.util.CallbackController
    public final void addCallback(Object obj) {
        this.scopedProvider.listeners.add((UnfoldTransitionProgressProvider.TransitionProgressListener) obj);
    }

    @Override // com.android.systemui.unfold.util.CallbackController
    public final void removeCallback(Object obj) {
        this.scopedProvider.listeners.remove((UnfoldTransitionProgressProvider.TransitionProgressListener) obj);
    }
}
