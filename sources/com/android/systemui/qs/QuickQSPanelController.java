package com.android.systemui.qs;

import android.view.View;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.media.controls.domain.pipeline.interactor.MediaCarouselInteractor;
import com.android.systemui.media.controls.ui.view.MediaHost;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.customize.QSCustomizerController;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.statusbar.policy.SplitShadeStateControllerImpl;
import com.android.systemui.util.leak.RotationUtils;
import com.android.wm.shell.R;
import dagger.internal.Provider;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QuickQSPanelController extends QSPanelControllerBase {
    public final MediaCarouselInteractor mMediaCarouselInteractor;
    public final Provider mUsingCollapsedLandscapeMediaProvider;

    public QuickQSPanelController(QuickQSPanel quickQSPanel, QSHost qSHost, QSCustomizerController qSCustomizerController, boolean z, MediaHost mediaHost, Provider provider, MetricsLogger metricsLogger, UiEventLogger uiEventLogger, QSLogger qSLogger, DumpManager dumpManager, SplitShadeStateControllerImpl splitShadeStateControllerImpl, Provider provider2, MediaCarouselInteractor mediaCarouselInteractor) {
        super(quickQSPanel, qSHost, qSCustomizerController, z, mediaHost, metricsLogger, uiEventLogger, qSLogger, dumpManager, splitShadeStateControllerImpl, provider2);
        this.mUsingCollapsedLandscapeMediaProvider = provider;
    }

    public int getRotation() {
        return RotationUtils.getRotation(this.mView.getContext());
    }

    @Override // com.android.systemui.qs.QSPanelControllerBase
    public final void onConfigurationChanged() {
        int integer = this.mView.getResources().getInteger(R.integer.quick_qs_panel_max_tiles);
        View view = this.mView;
        if (integer != ((QuickQSPanel) view).mMaxTiles) {
            ((QuickQSPanel) view).mMaxTiles = integer;
            setTiles();
        }
        updateMediaExpansion();
    }

    @Override // com.android.systemui.qs.QSPanelControllerBase, com.android.systemui.util.ViewController
    public final void onInit() {
        super.onInit();
        updateMediaExpansion();
        MediaHost mediaHost = this.mMediaHost;
        mediaHost.setShowsOnlyActiveMedia(true);
        mediaHost.init(1);
    }

    @Override // com.android.systemui.qs.QSPanelControllerBase
    public final void setTiles() {
        ArrayList arrayList = new ArrayList();
        Iterator it = ((QSHostAdapter) this.mHost).getTiles().iterator();
        while (it.hasNext()) {
            arrayList.add((QSTile) it.next());
            if (arrayList.size() == ((QuickQSPanel) this.mView).mMaxTiles) {
                break;
            }
        }
        setTiles(arrayList, true);
    }

    public final void updateMediaExpansion() {
        int rotation = getRotation();
        boolean z = true;
        if (rotation != 1 && rotation != 3) {
            z = false;
        }
        boolean booleanValue = ((Boolean) this.mUsingCollapsedLandscapeMediaProvider.get()).booleanValue();
        MediaHost mediaHost = this.mMediaHost;
        if (booleanValue && z) {
            mediaHost.setExpansion(0.0f);
        } else {
            mediaHost.setExpansion(1.0f);
        }
    }
}
