package com.android.systemui.qs;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSSquishinessController {
    public final QSAnimator qsAnimator;
    public final QSPanelController qsPanelController;
    public final QuickQSPanelController quickQSPanelController;
    public float squishiness = 1.0f;

    public QSSquishinessController(QSAnimator qSAnimator, QSPanelController qSPanelController, QuickQSPanelController quickQSPanelController) {
        this.qsAnimator = qSAnimator;
        this.qsPanelController = qSPanelController;
        this.quickQSPanelController = quickQSPanelController;
    }
}
