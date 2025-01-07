package com.android.systemui.qs.pipeline.domain.startable;

import com.android.systemui.CoreStartable;
import com.android.systemui.qs.pipeline.domain.interactor.AccessibilityTilesInteractor;
import com.android.systemui.qs.pipeline.domain.interactor.AutoAddInteractor;
import com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractor;
import com.android.systemui.qs.pipeline.domain.interactor.RestoreReconciliationInteractor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSPipelineCoreStartable implements CoreStartable {
    public final AccessibilityTilesInteractor accessibilityTilesInteractor;
    public final AutoAddInteractor autoAddInteractor;
    public final CurrentTilesInteractor currentTilesInteractor;
    public final RestoreReconciliationInteractor restoreReconciliationInteractor;

    public QSPipelineCoreStartable(CurrentTilesInteractor currentTilesInteractor, AccessibilityTilesInteractor accessibilityTilesInteractor, AutoAddInteractor autoAddInteractor, RestoreReconciliationInteractor restoreReconciliationInteractor) {
        this.currentTilesInteractor = currentTilesInteractor;
        this.accessibilityTilesInteractor = accessibilityTilesInteractor;
        this.autoAddInteractor = autoAddInteractor;
        this.restoreReconciliationInteractor = restoreReconciliationInteractor;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        AccessibilityTilesInteractor accessibilityTilesInteractor = this.accessibilityTilesInteractor;
        CurrentTilesInteractor currentTilesInteractor = this.currentTilesInteractor;
        accessibilityTilesInteractor.init(currentTilesInteractor);
        this.autoAddInteractor.init(currentTilesInteractor);
        this.restoreReconciliationInteractor.start();
    }
}
