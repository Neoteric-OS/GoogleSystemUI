package com.android.wm.shell.pip.phone;

import com.android.wm.shell.pip.phone.PipController;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PipController$PipImpl$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ PipController.PipImpl f$0;
    public final /* synthetic */ Consumer f$1;

    public /* synthetic */ PipController$PipImpl$$ExternalSyntheticLambda2(PipController.PipImpl pipImpl, Consumer consumer, int i) {
        this.$r8$classId = i;
        this.f$0 = pipImpl;
        this.f$1 = consumer;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                PipController.PipImpl pipImpl = this.f$0;
                PipController.this.mPipBoundsState.mOnPipExclusionBoundsChangeCallbacks.remove(this.f$1);
                break;
            default:
                PipController.PipImpl pipImpl2 = this.f$0;
                PipController.this.mPipBoundsState.addPipExclusionBoundsChangeCallback(this.f$1);
                break;
        }
    }
}
