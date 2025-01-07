package com.android.systemui.qs;

import com.android.systemui.qs.QSTileRevealController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class QSTileRevealController$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ QSTileRevealController.AnonymousClass1 f$0;

    public /* synthetic */ QSTileRevealController$1$$ExternalSyntheticLambda0(QSTileRevealController.AnonymousClass1 anonymousClass1) {
        this.f$0 = anonymousClass1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        QSTileRevealController.AnonymousClass1 anonymousClass1 = this.f$0;
        QSTileRevealController qSTileRevealController = QSTileRevealController.this;
        if (((QSPanel) qSTileRevealController.mQSPanelController.mView).mExpanded) {
            qSTileRevealController.addTileSpecsToRevealed(qSTileRevealController.mTilesToReveal);
            QSTileRevealController.this.mTilesToReveal.clear();
        }
    }
}
