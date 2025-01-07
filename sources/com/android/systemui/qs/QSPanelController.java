package com.android.systemui.qs;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
import com.android.systemui.settings.brightness.BrightnessMirrorHandler;
import com.android.systemui.settings.brightness.BrightnessSliderController;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.policy.BrightnessMirrorController;
import com.android.systemui.statusbar.policy.SplitShadeStateControllerImpl;
import com.android.systemui.tuner.TunerService;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$61;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSPanelController extends QSPanelControllerBase {
    public BrightnessController mBrightnessController;
    public final BrightnessController.Factory mBrightnessControllerFactory;
    public final BrightnessMirrorHandler mBrightnessMirrorHandler;
    public BrightnessSliderController mBrightnessSliderController;
    public final BrightnessSliderController.Factory mBrightnessSliderControllerFactory;
    public final FalsingManager mFalsingManager;
    public int mLastDensity;
    public boolean mListening;
    public final MediaCarouselInteractor mMediaCarouselInteractor;
    public final QSCustomizerController mQsCustomizerController;
    public final QSTileRevealController.Factory mQsTileRevealControllerFactory;
    public final StatusBarKeyguardViewManager mStatusBarKeyguardViewManager;
    public final AnonymousClass1 mTileLayoutTouchListener;
    public final TunerService mTunerService;

    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.systemui.qs.QSPanelController$1] */
    public QSPanelController(QSPanel qSPanel, TunerService tunerService, QSHost qSHost, QSCustomizerController qSCustomizerController, boolean z, MediaHost mediaHost, QSTileRevealController.Factory factory, DumpManager dumpManager, MetricsLogger metricsLogger, UiEventLogger uiEventLogger, QSLogger qSLogger, BrightnessController.Factory factory2, BrightnessSliderController.Factory factory3, FalsingManager falsingManager, StatusBarKeyguardViewManager statusBarKeyguardViewManager, SplitShadeStateControllerImpl splitShadeStateControllerImpl, Provider provider, MediaCarouselInteractor mediaCarouselInteractor) {
        super(qSPanel, qSHost, qSCustomizerController, z, mediaHost, metricsLogger, uiEventLogger, qSLogger, dumpManager, splitShadeStateControllerImpl, provider);
        this.mTileLayoutTouchListener = new View.OnTouchListener() { // from class: com.android.systemui.qs.QSPanelController.1
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getActionMasked() != 1) {
                    return false;
                }
                QSPanelController.this.mFalsingManager.isFalseTouch(15);
                return false;
            }
        };
        this.mTunerService = tunerService;
        this.mQsCustomizerController = qSCustomizerController;
        this.mQsTileRevealControllerFactory = factory;
        this.mFalsingManager = falsingManager;
        this.mBrightnessSliderControllerFactory = factory3;
        this.mBrightnessControllerFactory = factory2;
        BrightnessSliderController create = factory3.create(qSPanel.getContext(), qSPanel);
        this.mBrightnessSliderController = create;
        qSPanel.setBrightnessView(create.mView);
        BrightnessController create2 = ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$61) factory2).create(this.mBrightnessSliderController);
        this.mBrightnessController = create2;
        this.mBrightnessMirrorHandler = new BrightnessMirrorHandler(create2);
        this.mStatusBarKeyguardViewManager = statusBarKeyguardViewManager;
        this.mLastDensity = qSPanel.getResources().getConfiguration().densityDpi;
    }

    @Override // com.android.systemui.qs.QSPanelControllerBase
    public final QSTileRevealController createTileRevealController() {
        PagedTileLayout pagedTileLayout = (PagedTileLayout) ((QSPanel) this.mView).getOrCreateTileLayout();
        QSTileRevealController.Factory factory = this.mQsTileRevealControllerFactory;
        return new QSTileRevealController(factory.mContext, this, pagedTileLayout, factory.mQsCustomizerController);
    }

    public final void maybeReinflateBrightnessSlider() {
        int i = ((QSPanel) this.mView).getResources().getConfiguration().densityDpi;
        if (i != this.mLastDensity) {
            this.mLastDensity = i;
            BrightnessController brightnessController = this.mBrightnessController;
            BrightnessController.AnonymousClass2 anonymousClass2 = brightnessController.mStopListeningRunnable;
            Handler handler = brightnessController.mBackgroundHandler;
            handler.removeCallbacks(anonymousClass2);
            handler.post(anonymousClass2);
            brightnessController.mControlValueInitialized = false;
            BrightnessSliderController create = this.mBrightnessSliderControllerFactory.create(this.mView.getContext(), (ViewGroup) this.mView);
            this.mBrightnessSliderController = create;
            ((QSPanel) this.mView).setBrightnessView(create.mView);
            BrightnessController create2 = ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$61) this.mBrightnessControllerFactory).create(this.mBrightnessSliderController);
            this.mBrightnessController = create2;
            BrightnessMirrorHandler brightnessMirrorHandler = this.mBrightnessMirrorHandler;
            brightnessMirrorHandler.brightnessController = create2;
            brightnessMirrorHandler.updateBrightnessMirror();
            this.mBrightnessSliderController.init$9();
            if (this.mListening) {
                BrightnessController brightnessController2 = this.mBrightnessController;
                BrightnessController.AnonymousClass2 anonymousClass22 = brightnessController2.mStartListeningRunnable;
                Handler handler2 = brightnessController2.mBackgroundHandler;
                handler2.removeCallbacks(anonymousClass22);
                handler2.post(anonymousClass22);
            }
        }
    }

    @Override // com.android.systemui.qs.QSPanelControllerBase
    public final void onConfigurationChanged() {
        ((QSPanel) this.mView).updateResources();
        maybeReinflateBrightnessSlider();
        if (((QSPanel) this.mView).mListening) {
            refreshAllTiles();
        }
    }

    @Override // com.android.systemui.qs.QSPanelControllerBase, com.android.systemui.util.ViewController
    public final void onInit() {
        super.onInit();
        MediaHost mediaHost = this.mMediaHost;
        mediaHost.setExpansion(1.0f);
        mediaHost.setShowsOnlyActiveMedia(false);
        mediaHost.init(0);
        this.mQsCustomizerController.init$9();
        this.mBrightnessSliderController.init$9();
    }

    @Override // com.android.systemui.qs.QSPanelControllerBase
    public final void onSplitShadeChanged(boolean z) {
        ((PagedTileLayout) ((QSPanel) this.mView).getOrCreateTileLayout()).forceTilesRedistribution("Split shade state changed");
        ((QSPanel) this.mView).mCanCollapse = !z;
    }

    @Override // com.android.systemui.qs.QSPanelControllerBase, com.android.systemui.util.ViewController
    public final void onViewAttached() {
        super.onViewAttached();
        updateMediaDisappearParameters();
        this.mTunerService.addTunable((TunerService.Tunable) this.mView, "qs_show_brightness");
        ((QSPanel) this.mView).updateResources();
        QSPanel qSPanel = (QSPanel) this.mView;
        qSPanel.getClass();
        if (qSPanel.mListening) {
            refreshAllTiles();
        }
        switchTileLayout(true);
        BrightnessMirrorHandler brightnessMirrorHandler = this.mBrightnessMirrorHandler;
        BrightnessMirrorController brightnessMirrorController = brightnessMirrorHandler.mirrorController;
        if (brightnessMirrorController != null) {
            brightnessMirrorController.addCallback(brightnessMirrorHandler.brightnessMirrorListener);
        }
        ((PagedTileLayout) ((QSPanel) this.mView).getOrCreateTileLayout()).setOnTouchListener(this.mTileLayoutTouchListener);
        maybeReinflateBrightnessSlider();
    }

    @Override // com.android.systemui.qs.QSPanelControllerBase, com.android.systemui.util.ViewController
    public final void onViewDetached() {
        this.mTunerService.removeTunable((TunerService.Tunable) this.mView);
        BrightnessMirrorHandler brightnessMirrorHandler = this.mBrightnessMirrorHandler;
        BrightnessMirrorController brightnessMirrorController = brightnessMirrorHandler.mirrorController;
        if (brightnessMirrorController != null) {
            brightnessMirrorController.removeCallback(brightnessMirrorHandler.brightnessMirrorListener);
        }
        super.onViewDetached();
    }

    @Override // com.android.systemui.qs.QSPanelControllerBase
    public final void refreshAllTiles() {
        BrightnessController brightnessController = this.mBrightnessController;
        brightnessController.getClass();
        brightnessController.mBackgroundHandler.post(new BrightnessController.AnonymousClass2(brightnessController, 1));
        super.refreshAllTiles();
    }
}
