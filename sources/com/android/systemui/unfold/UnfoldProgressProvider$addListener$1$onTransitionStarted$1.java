package com.android.systemui.unfold;

import com.android.wm.shell.unfold.ShellUnfoldProgressProvider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UnfoldProgressProvider$addListener$1$onTransitionStarted$1 implements Runnable {
    public final /* synthetic */ ShellUnfoldProgressProvider.UnfoldListener $listener;
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ UnfoldProgressProvider$addListener$1$onTransitionStarted$1(ShellUnfoldProgressProvider.UnfoldListener unfoldListener, int i) {
        this.$r8$classId = i;
        this.$listener = unfoldListener;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                this.$listener.onStateChangeStarted();
                break;
            default:
                this.$listener.onStateChangeFinished();
                break;
        }
    }
}
