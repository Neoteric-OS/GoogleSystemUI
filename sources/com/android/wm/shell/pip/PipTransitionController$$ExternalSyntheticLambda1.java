package com.android.wm.shell.pip;

import com.android.wm.shell.pip.PipTransitionController;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PipTransitionController$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Map.Entry f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ PipTransitionController$$ExternalSyntheticLambda1(Map.Entry entry, int i, int i2) {
        this.$r8$classId = i2;
        this.f$0 = entry;
        this.f$1 = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                Map.Entry entry = this.f$0;
                ((PipTransitionController.PipTransitionCallback) entry.getKey()).onPipTransitionFinished(this.f$1);
                break;
            default:
                Map.Entry entry2 = this.f$0;
                ((PipTransitionController.PipTransitionCallback) entry2.getKey()).onPipTransitionCanceled(this.f$1);
                break;
        }
    }
}
