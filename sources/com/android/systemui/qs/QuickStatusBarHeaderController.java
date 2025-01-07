package com.android.systemui.qs;

import com.android.systemui.util.ViewController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QuickStatusBarHeaderController extends ViewController {
    public boolean mListening;
    public final QuickQSPanelController mQuickQSPanelController;

    public QuickStatusBarHeaderController(QuickStatusBarHeader quickStatusBarHeader, QuickQSPanelController quickQSPanelController) {
        super(quickStatusBarHeader);
        this.mQuickQSPanelController = quickQSPanelController;
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
        ((QuickStatusBarHeader) this.mView).getClass();
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
        if (this.mListening) {
            this.mListening = false;
            QuickQSPanelController quickQSPanelController = this.mQuickQSPanelController;
            quickQSPanelController.setListening(false);
            if (quickQSPanelController.switchTileLayout(false)) {
                ((QuickStatusBarHeader) this.mView).updateResources();
            }
        }
    }
}
