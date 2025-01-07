package com.android.systemui.media.controls.ui.viewmodel;

import android.os.Trace;
import com.android.systemui.util.concurrency.RepeatableExecutorImpl$$ExternalSyntheticLambda0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SeekBarViewModel$checkIfPollingNeeded$1 implements Runnable {
    public final /* synthetic */ RepeatableExecutorImpl$$ExternalSyntheticLambda0 $cancelPolling;
    public final /* synthetic */ int $traceCookie;

    public SeekBarViewModel$checkIfPollingNeeded$1(RepeatableExecutorImpl$$ExternalSyntheticLambda0 repeatableExecutorImpl$$ExternalSyntheticLambda0, int i) {
        this.$cancelPolling = repeatableExecutorImpl$$ExternalSyntheticLambda0;
        this.$traceCookie = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.$cancelPolling.run();
        Trace.endAsyncSection("SeekBarPollingPosition", this.$traceCookie);
    }
}
