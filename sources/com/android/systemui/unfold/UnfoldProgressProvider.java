package com.android.systemui.unfold;

import com.android.systemui.unfold.UnfoldTransitionProgressProvider;
import com.android.systemui.unfold.updates.FoldProvider;
import com.android.wm.shell.unfold.ShellUnfoldProgressProvider;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UnfoldProgressProvider implements ShellUnfoldProgressProvider {
    public final FoldProvider foldProvider;
    public final UnfoldTransitionProgressProvider unfoldProgressProvider;

    public UnfoldProgressProvider(UnfoldTransitionProgressProvider unfoldTransitionProgressProvider, FoldProvider foldProvider) {
        this.unfoldProgressProvider = unfoldTransitionProgressProvider;
        this.foldProvider = foldProvider;
    }

    @Override // com.android.wm.shell.unfold.ShellUnfoldProgressProvider
    public final void addListener(final Executor executor, final ShellUnfoldProgressProvider.UnfoldListener unfoldListener) {
        this.unfoldProgressProvider.addCallback(new UnfoldTransitionProgressProvider.TransitionProgressListener() { // from class: com.android.systemui.unfold.UnfoldProgressProvider$addListener$1
            @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
            public final void onTransitionFinished() {
                executor.execute(new UnfoldProgressProvider$addListener$1$onTransitionStarted$1(unfoldListener, 1));
            }

            @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
            public final void onTransitionProgress(final float f) {
                Executor executor2 = executor;
                final ShellUnfoldProgressProvider.UnfoldListener unfoldListener2 = unfoldListener;
                executor2.execute(new Runnable() { // from class: com.android.systemui.unfold.UnfoldProgressProvider$addListener$1$onTransitionProgress$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        ShellUnfoldProgressProvider.UnfoldListener.this.onStateChangeProgress(f);
                    }
                });
            }

            @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
            public final void onTransitionStarted() {
                executor.execute(new UnfoldProgressProvider$addListener$1$onTransitionStarted$1(unfoldListener, 0));
            }
        });
        this.foldProvider.registerCallback(new FoldProvider.FoldCallback() { // from class: com.android.systemui.unfold.UnfoldProgressProvider$addListener$2
            @Override // com.android.systemui.unfold.updates.FoldProvider.FoldCallback
            public final void onFoldUpdated(boolean z) {
                ShellUnfoldProgressProvider.UnfoldListener.this.onFoldStateChanged(z);
            }
        }, executor);
    }
}
