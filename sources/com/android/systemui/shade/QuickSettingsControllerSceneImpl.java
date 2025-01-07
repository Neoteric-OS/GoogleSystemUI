package com.android.systemui.shade;

import com.android.systemui.qs.QSImpl;
import com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QuickSettingsControllerSceneImpl implements QuickSettingsController {
    public final QSSceneAdapterImpl qsSceneAdapter;
    public final ShadeInteractor shadeInteractor;

    public QuickSettingsControllerSceneImpl(QSSceneAdapterImpl qSSceneAdapterImpl, ShadeInteractor shadeInteractor) {
        this.shadeInteractor = shadeInteractor;
        this.qsSceneAdapter = qSSceneAdapterImpl;
    }

    @Override // com.android.systemui.shade.QuickSettingsController
    public final void closeQsCustomizer() {
        QSImpl qSImpl = (QSImpl) ((StateFlowImpl) this.qsSceneAdapter.qsImpl.$$delegate_0).getValue();
        if (qSImpl != null) {
            qSImpl.closeCustomizer();
        }
    }

    @Override // com.android.systemui.shade.QuickSettingsController
    public final boolean getExpanded() {
        return ((Boolean) ((ShadeInteractorImpl) this.shadeInteractor).baseShadeInteractor.isQsExpanded().getValue()).booleanValue();
    }

    @Override // com.android.systemui.shade.QuickSettingsController
    public final boolean isCustomizing() {
        return ((Boolean) ((StateFlowImpl) this.qsSceneAdapter.isCustomizerShowing.$$delegate_0).getValue()).booleanValue();
    }

    @Override // com.android.systemui.shade.QuickSettingsController
    public final boolean shouldQuickSettingsIntercept(float f, float f2, float f3) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.systemui.shade.QuickSettingsController
    public final void closeQs() {
    }
}
