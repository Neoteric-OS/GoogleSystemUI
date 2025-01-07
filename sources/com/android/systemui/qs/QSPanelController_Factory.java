package com.android.systemui.qs;

import com.android.internal.logging.MetricsLogger;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.media.controls.domain.pipeline.interactor.MediaCarouselInteractor;
import com.android.systemui.media.controls.ui.view.MediaHost;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.qs.QSTileRevealController;
import com.android.systemui.qs.customize.QSCustomizerController;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.settings.brightness.BrightnessController;
import com.android.systemui.settings.brightness.BrightnessSliderController;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.policy.SplitShadeStateControllerImpl;
import com.android.systemui.tuner.TunerService;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class QSPanelController_Factory implements Provider {
    public static QSPanelController newInstance(QSPanel qSPanel, TunerService tunerService, QSHost qSHost, QSCustomizerController qSCustomizerController, boolean z, MediaHost mediaHost, Object obj, DumpManager dumpManager, MetricsLogger metricsLogger, UiEventLogger uiEventLogger, QSLogger qSLogger, BrightnessController.Factory factory, BrightnessSliderController.Factory factory2, FalsingManager falsingManager, StatusBarKeyguardViewManager statusBarKeyguardViewManager, SplitShadeStateControllerImpl splitShadeStateControllerImpl, Provider provider, MediaCarouselInteractor mediaCarouselInteractor) {
        return new QSPanelController(qSPanel, tunerService, qSHost, qSCustomizerController, z, mediaHost, (QSTileRevealController.Factory) obj, dumpManager, metricsLogger, uiEventLogger, qSLogger, factory, factory2, falsingManager, statusBarKeyguardViewManager, splitShadeStateControllerImpl, provider, mediaCarouselInteractor);
    }
}
