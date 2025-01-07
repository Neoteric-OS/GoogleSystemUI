package com.android.systemui.qs;

import com.android.systemui.util.animation.UniqueObjectHostView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class QSImpl$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ QSImpl f$0;

    public /* synthetic */ QSImpl$$ExternalSyntheticLambda3(QSImpl qSImpl) {
        this.f$0 = qSImpl;
    }

    @Override // java.lang.Runnable
    public final void run() {
        QSImpl qSImpl = this.f$0;
        UniqueObjectHostView uniqueObjectHostView = qSImpl.mQSPanelController.mMediaHost.hostView;
        if (uniqueObjectHostView == null) {
            uniqueObjectHostView = null;
        }
        uniqueObjectHostView.setAlpha(1.0f);
        qSImpl.mQSAnimator.mNeedsAnimatorUpdate = true;
    }
}
