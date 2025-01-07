package com.android.systemui.unfold.util;

import android.view.View;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.systemui.statusbar.window.StatusBarWindowControllerImpl$$ExternalSyntheticLambda1;
import com.android.systemui.unfold.UnfoldTransitionProgressProvider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class JankMonitorTransitionProgressListener implements UnfoldTransitionProgressProvider.TransitionProgressListener {
    public final StatusBarWindowControllerImpl$$ExternalSyntheticLambda1 attachedViewProvider;
    public final InteractionJankMonitor interactionJankMonitor = InteractionJankMonitor.getInstance();

    public JankMonitorTransitionProgressListener(StatusBarWindowControllerImpl$$ExternalSyntheticLambda1 statusBarWindowControllerImpl$$ExternalSyntheticLambda1) {
        this.attachedViewProvider = statusBarWindowControllerImpl$$ExternalSyntheticLambda1;
    }

    @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
    public final void onTransitionFinished() {
        this.interactionJankMonitor.end(44);
    }

    @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
    public final void onTransitionStarted() {
        this.interactionJankMonitor.begin((View) this.attachedViewProvider.get(), 44);
    }
}
