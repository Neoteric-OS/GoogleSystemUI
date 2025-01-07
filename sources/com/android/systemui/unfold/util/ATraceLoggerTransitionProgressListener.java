package com.android.systemui.unfold.util;

import android.os.Trace;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.systemui.unfold.UnfoldTransitionProgressProvider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ATraceLoggerTransitionProgressListener implements UnfoldTransitionProgressProvider.TransitionProgressListener {
    public final String traceName;

    public ATraceLoggerTransitionProgressListener(String str) {
        this.traceName = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("systemui", str, "#FoldUnfoldTransitionInProgress");
    }

    @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
    public final void onTransitionFinished() {
        Trace.endAsyncSection(this.traceName, 0);
    }

    @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
    public final void onTransitionProgress(float f) {
        Trace.setCounter(this.traceName, (long) (f * 100));
    }

    @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
    public final void onTransitionStarted() {
        Trace.beginAsyncSection(this.traceName, 0);
    }
}
