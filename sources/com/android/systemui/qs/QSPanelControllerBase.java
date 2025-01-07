package com.android.systemui.qs;

import android.metrics.LogMaker;
import android.os.Handler;
import android.util.ArraySet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.widget.RemeasuringLinearLayout;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.Prefs;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.haptics.qs.QSLongPressEffect;
import com.android.systemui.media.controls.ui.view.MediaHost;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QSPanel;
import com.android.systemui.qs.QSTileRevealController;
import com.android.systemui.qs.customize.QSCustomizer;
import com.android.systemui.qs.customize.QSCustomizerController;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileViewImpl;
import com.android.systemui.statusbar.policy.SplitShadeStateControllerImpl;
import com.android.systemui.util.ViewController;
import com.android.systemui.util.animation.DisappearParameters;
import com.android.systemui.util.animation.UniqueObjectHostView;
import com.android.wm.shell.R;
import dagger.internal.Provider;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class QSPanelControllerBase extends ViewController implements Dumpable {
    public String mCachedSpecs;
    public boolean mDestroyed;
    public final DumpManager mDumpManager;
    public final QSHost mHost;
    public int mLastOrientation;
    public int mLastScreenLayout;
    public final Provider mLongPressEffectProvider;
    public final MediaHost mMediaHost;
    public final QSPanelControllerBase$$ExternalSyntheticLambda2 mMediaHostVisibilityListener;
    public final QSPanelControllerBase$$ExternalSyntheticLambda1 mMediaOrRecommendationVisibleConsumer;
    public Consumer mMediaVisibilityChangedListener;
    public final MetricsLogger mMetricsLogger;
    protected final QSPanel.OnConfigurationChangedListener mOnConfigurationChangedListener;
    public final QSPanelControllerBase$$ExternalSyntheticLambda0 mQSHostCallback;
    public final QSLogger mQSLogger;
    public final QSCustomizerController mQsCustomizerController;
    public QSTileRevealController mQsTileRevealController;
    public final ArrayList mRecords;
    public float mRevealExpansion;
    public boolean mShouldUseSplitNotificationShade;
    public final SplitShadeStateControllerImpl mSplitShadeStateController;
    public final UiEventLogger mUiEventLogger;
    public boolean mUsingHorizontalLayout;
    public QSImpl$$ExternalSyntheticLambda3 mUsingHorizontalLayoutChangedListener;
    public final boolean mUsingMediaPlayer;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.qs.QSPanelControllerBase$1, reason: invalid class name */
    public final class AnonymousClass1 implements QSPanel.OnConfigurationChangedListener {
        public AnonymousClass1() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TileRecord {
        public QSPanel.AnonymousClass1 callback;
        public QSTile tile;
        public QSTileViewImpl tileView;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.systemui.qs.QSPanelControllerBase$$ExternalSyntheticLambda0] */
    /* JADX WARN: Type inference failed for: r0v6, types: [com.android.systemui.qs.QSPanelControllerBase$$ExternalSyntheticLambda2] */
    public QSPanelControllerBase(QSPanel qSPanel, QSHost qSHost, QSCustomizerController qSCustomizerController, boolean z, MediaHost mediaHost, MetricsLogger metricsLogger, UiEventLogger uiEventLogger, QSLogger qSLogger, DumpManager dumpManager, SplitShadeStateControllerImpl splitShadeStateControllerImpl, Provider provider) {
        super(qSPanel);
        this.mRecords = new ArrayList();
        this.mCachedSpecs = "";
        this.mQSHostCallback = new QSHost.Callback() { // from class: com.android.systemui.qs.QSPanelControllerBase$$ExternalSyntheticLambda0
            @Override // com.android.systemui.qs.QSHost.Callback
            public final void onTilesChanged() {
                QSPanelControllerBase.this.setTiles();
            }
        };
        this.mDestroyed = false;
        new Consumer() { // from class: com.android.systemui.qs.QSPanelControllerBase$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                QSPanelControllerBase qSPanelControllerBase = QSPanelControllerBase.this;
                qSPanelControllerBase.getClass();
                boolean booleanValue = ((Boolean) obj).booleanValue();
                boolean z2 = false;
                if (!qSPanelControllerBase.mShouldUseSplitNotificationShade && booleanValue && qSPanelControllerBase.mLastOrientation == 2 && (qSPanelControllerBase.mLastScreenLayout & 48) == 32) {
                    z2 = true;
                }
                ((QSPanel) qSPanelControllerBase.mView).setColumnRowLayout(z2);
            }
        };
        this.mOnConfigurationChangedListener = new AnonymousClass1();
        this.mMediaHostVisibilityListener = new Function1() { // from class: com.android.systemui.qs.QSPanelControllerBase$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Boolean bool = (Boolean) obj;
                QSPanelControllerBase qSPanelControllerBase = QSPanelControllerBase.this;
                Consumer consumer = qSPanelControllerBase.mMediaVisibilityChangedListener;
                if (consumer != null) {
                    consumer.accept(bool);
                }
                qSPanelControllerBase.switchTileLayout(false);
                return null;
            }
        };
        this.mHost = qSHost;
        this.mQsCustomizerController = qSCustomizerController;
        this.mUsingMediaPlayer = z;
        this.mMediaHost = mediaHost;
        this.mMetricsLogger = metricsLogger;
        this.mUiEventLogger = uiEventLogger;
        this.mQSLogger = qSLogger;
        this.mDumpManager = dumpManager;
        this.mSplitShadeStateController = splitShadeStateControllerImpl;
        this.mShouldUseSplitNotificationShade = splitShadeStateControllerImpl.shouldUseSplitNotificationShade(qSPanel.getResources());
        this.mLongPressEffectProvider = provider;
    }

    public QSTileRevealController createTileRevealController() {
        return null;
    }

    public final void destroy() {
        ((QSHostAdapter) this.mHost).removeCallback(this.mQSHostCallback);
        this.mDestroyed = true;
        Iterator it = this.mRecords.iterator();
        while (it.hasNext()) {
            TileRecord tileRecord = (TileRecord) it.next();
            tileRecord.tile.removeCallback(tileRecord.callback);
            ((QSPanel) this.mView).mTileLayout.removeTile(tileRecord);
        }
        this.mRecords.clear();
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println(getClass().getSimpleName().concat(":"));
        printWriter.println("  Tile records:");
        Iterator it = this.mRecords.iterator();
        while (it.hasNext()) {
            TileRecord tileRecord = (TileRecord) it.next();
            if (tileRecord.tile instanceof Dumpable) {
                printWriter.print("    ");
                ((Dumpable) tileRecord.tile).dump(printWriter, strArr);
                printWriter.print("    ");
                printWriter.println(tileRecord.tileView.toString());
            }
        }
        MediaHost mediaHost = this.mMediaHost;
        if (mediaHost != null) {
            printWriter.println("  media bounds: " + mediaHost.getCurrentBounds());
            StringBuilder m = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("  horizontal layout: "), this.mUsingHorizontalLayout, printWriter, "  last orientation: ");
            m.append(this.mLastOrientation);
            printWriter.println(m.toString());
        }
        KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("  mShouldUseSplitNotificationShade: "), this.mShouldUseSplitNotificationShade, printWriter);
    }

    public final QSPanel.QSTileLayout getTileLayout() {
        return ((QSPanel) this.mView).mTileLayout;
    }

    public abstract void onConfigurationChanged();

    @Override // com.android.systemui.util.ViewController
    public void onInit() {
        QSPanel qSPanel = (QSPanel) this.mView;
        QSLogger qSLogger = this.mQSLogger;
        qSPanel.mQsLogger = qSLogger;
        qSPanel.mUsingMediaPlayer = this.mUsingMediaPlayer;
        qSPanel.mTileLayout = qSPanel.getOrCreateTileLayout();
        if (qSPanel.mUsingMediaPlayer) {
            RemeasuringLinearLayout remeasuringLinearLayout = new RemeasuringLinearLayout(qSPanel.mContext);
            qSPanel.mHorizontalLinearLayout = remeasuringLinearLayout;
            remeasuringLinearLayout.setOrientation(0);
            qSPanel.mHorizontalLinearLayout.setVisibility(qSPanel.mUsingHorizontalLayout ? 0 : 8);
            qSPanel.mHorizontalLinearLayout.setClipChildren(false);
            qSPanel.mHorizontalLinearLayout.setClipToPadding(false);
            RemeasuringLinearLayout remeasuringLinearLayout2 = new RemeasuringLinearLayout(qSPanel.mContext);
            qSPanel.mHorizontalContentContainer = remeasuringLinearLayout2;
            remeasuringLinearLayout2.setOrientation(1);
            qSPanel.setHorizontalContentContainerClipping();
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2, 1.0f);
            int dimension = (int) qSPanel.mContext.getResources().getDimension(R.dimen.qs_media_padding);
            layoutParams.setMarginStart(0);
            layoutParams.setMarginEnd(dimension);
            layoutParams.gravity = 16;
            qSPanel.mHorizontalLinearLayout.addView(qSPanel.mHorizontalContentContainer, layoutParams);
            qSPanel.addView(qSPanel.mHorizontalLinearLayout, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        }
        QSPanel qSPanel2 = (QSPanel) this.mView;
        qSLogger.logAllTilesChangeListening(qSPanel2.getDumpableTag(), "", qSPanel2.mListening);
        ((QSHostAdapter) this.mHost).addCallback(this.mQSHostCallback);
    }

    @Override // com.android.systemui.util.ViewController
    public void onViewAttached() {
        QSTileRevealController createTileRevealController = createTileRevealController();
        this.mQsTileRevealController = createTileRevealController;
        if (createTileRevealController != null) {
            float f = this.mRevealExpansion;
            Handler handler = createTileRevealController.mHandler;
            QSTileRevealController.AnonymousClass1 anonymousClass1 = createTileRevealController.mRevealQsTiles;
            if (f == 1.0f) {
                handler.postDelayed(anonymousClass1, 500L);
            } else {
                handler.removeCallbacks(anonymousClass1);
            }
        }
        this.mMediaHost.visibleChangedListeners.add(this.mMediaHostVisibilityListener);
        QSPanel qSPanel = (QSPanel) this.mView;
        qSPanel.mOnConfigurationChangedListeners.add(this.mOnConfigurationChangedListener);
        setTiles();
        this.mLastOrientation = this.mView.getResources().getConfiguration().orientation;
        this.mLastScreenLayout = this.mView.getResources().getConfiguration().screenLayout;
        this.mQSLogger.logOnViewAttached(this.mLastOrientation, ((QSPanel) this.mView).getDumpableTag());
        switchTileLayout(true);
        String dumpableTag = ((QSPanel) this.mView).getDumpableTag();
        DumpManager dumpManager = this.mDumpManager;
        dumpManager.getClass();
        DumpManager.registerDumpable$default(dumpManager, dumpableTag, this);
    }

    @Override // com.android.systemui.util.ViewController
    public void onViewDetached() {
        this.mQSLogger.logOnViewDetached(this.mLastOrientation, ((QSPanel) this.mView).getDumpableTag());
        QSPanel qSPanel = (QSPanel) this.mView;
        qSPanel.mOnConfigurationChangedListeners.remove(this.mOnConfigurationChangedListener);
        ((QSPanel) this.mView).mTileLayout.setListening(false, this.mUiEventLogger);
        this.mMediaHost.visibleChangedListeners.remove(this.mMediaHostVisibilityListener);
        this.mDumpManager.unregisterDumpable(((QSPanel) this.mView).getDumpableTag());
    }

    public void refreshAllTiles() {
        Iterator it = this.mRecords.iterator();
        while (it.hasNext()) {
            TileRecord tileRecord = (TileRecord) it.next();
            if (!tileRecord.tile.isListening()) {
                tileRecord.tile.refreshState();
            }
        }
    }

    public final void setExpanded(boolean z) {
        QSPanel qSPanel = (QSPanel) this.mView;
        if (qSPanel.mExpanded == z) {
            return;
        }
        this.mQSLogger.logPanelExpanded(qSPanel.getDumpableTag(), z);
        QSPanel qSPanel2 = (QSPanel) this.mView;
        if (qSPanel2.mExpanded != z) {
            qSPanel2.mExpanded = z;
            if (!z) {
                QSPanel.QSTileLayout qSTileLayout = qSPanel2.mTileLayout;
                if (qSTileLayout instanceof PagedTileLayout) {
                    final PagedTileLayout pagedTileLayout = (PagedTileLayout) qSTileLayout;
                    pagedTileLayout.post(new Runnable() { // from class: com.android.systemui.qs.QSPanel$$ExternalSyntheticLambda2
                        @Override // java.lang.Runnable
                        public final void run() {
                            PagedTileLayout pagedTileLayout2 = PagedTileLayout.this;
                            int i = QSPanel.$r8$clinit;
                            pagedTileLayout2.setCurrentItem(0, false);
                        }
                    });
                }
            }
        }
        this.mMetricsLogger.visibility(111, z);
        if (!z) {
            this.mUiEventLogger.log(((QSPanel) this.mView).closePanelEvent());
            QSCustomizerController qSCustomizerController = this.mQsCustomizerController;
            if (((QSCustomizer) qSCustomizerController.mView).isShown) {
                qSCustomizerController.hide(true);
                return;
            }
            return;
        }
        this.mUiEventLogger.log(((QSPanel) this.mView).openPanelEvent());
        for (int i = 0; i < this.mRecords.size(); i++) {
            QSTile qSTile = ((TileRecord) this.mRecords.get(i)).tile;
            this.mMetricsLogger.write(qSTile.populate(new LogMaker(qSTile.getMetricsCategory()).setType(1)));
        }
    }

    public final void setListening(boolean z) {
        QSPanel qSPanel = (QSPanel) this.mView;
        if (qSPanel.mListening == z) {
            return;
        }
        qSPanel.mListening = z;
        if (qSPanel.mTileLayout != null) {
            this.mQSLogger.logAllTilesChangeListening(qSPanel.getDumpableTag(), this.mCachedSpecs, z);
            ((QSPanel) this.mView).mTileLayout.setListening(z, this.mUiEventLogger);
        }
        if (((QSPanel) this.mView).mListening) {
            refreshAllTiles();
        }
    }

    public final void setSquishinessFraction(float f) {
        QSPanel qSPanel = (QSPanel) this.mView;
        if (Float.compare(f, qSPanel.mSquishinessFraction) == 0) {
            return;
        }
        qSPanel.mSquishinessFraction = f;
        QSPanel.QSTileLayout qSTileLayout = qSPanel.mTileLayout;
        if (qSTileLayout == null) {
            return;
        }
        qSTileLayout.setSquishinessFraction(f);
        if (qSPanel.getMeasuredWidth() == 0) {
            return;
        }
        qSPanel.updateViewPositions();
    }

    public void setTiles() {
        setTiles(((QSHostAdapter) this.mHost).getTiles(), false);
    }

    public final boolean shouldUseHorizontalLayout() {
        return !this.mShouldUseSplitNotificationShade && this.mUsingMediaPlayer && this.mMediaHost.state.visible && this.mLastOrientation == 2 && (this.mLastScreenLayout & 48) == 32;
    }

    public final boolean switchTileLayout(boolean z) {
        LinearLayout linearLayout;
        boolean shouldUseHorizontalLayout = shouldUseHorizontalLayout();
        boolean z2 = this.mUsingHorizontalLayout;
        if (shouldUseHorizontalLayout == z2 && !z) {
            return false;
        }
        this.mQSLogger.logSwitchTileLayout(((QSPanel) this.mView).getDumpableTag(), shouldUseHorizontalLayout, z2, z);
        this.mUsingHorizontalLayout = shouldUseHorizontalLayout;
        QSPanel qSPanel = (QSPanel) this.mView;
        UniqueObjectHostView uniqueObjectHostView = this.mMediaHost.hostView;
        if (uniqueObjectHostView == null) {
            uniqueObjectHostView = null;
        }
        if (shouldUseHorizontalLayout != qSPanel.mUsingHorizontalLayout || z) {
            Log.d(qSPanel.getDumpableTag(), "setUsingHorizontalLayout: " + shouldUseHorizontalLayout + ", " + z);
            qSPanel.mUsingHorizontalLayout = shouldUseHorizontalLayout;
            LinearLayout linearLayout2 = (shouldUseHorizontalLayout && qSPanel.mUsingMediaPlayer) ? qSPanel.mHorizontalContentContainer : qSPanel;
            Object obj = qSPanel.mTileLayout;
            int i = linearLayout2 == qSPanel ? qSPanel.mMovableContentStartIndex : 0;
            QSPanel.switchToParent((View) obj, linearLayout2, i, qSPanel.getDumpableTag());
            int i2 = i + 1;
            View view = qSPanel.mFooter;
            if (view != null) {
                QSPanel.switchToParent(view, linearLayout2, i2, qSPanel.getDumpableTag());
            }
            if (qSPanel.mUsingMediaPlayer) {
                qSPanel.mMediaHostView = uniqueObjectHostView;
                LinearLayout linearLayout3 = shouldUseHorizontalLayout ? qSPanel.mHorizontalLinearLayout : qSPanel;
                ViewGroup viewGroup = (ViewGroup) uniqueObjectHostView.getParent();
                Log.d(qSPanel.getDumpableTag(), "Reattaching media host: " + shouldUseHorizontalLayout + ", current " + viewGroup + ", new " + linearLayout3);
                if (viewGroup != linearLayout3) {
                    if (viewGroup != null) {
                        viewGroup.removeView(uniqueObjectHostView);
                    }
                    linearLayout3.addView(uniqueObjectHostView);
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) uniqueObjectHostView.getLayoutParams();
                    layoutParams.height = -2;
                    layoutParams.width = shouldUseHorizontalLayout ? 0 : -1;
                    layoutParams.weight = shouldUseHorizontalLayout ? 1.0f : 0.0f;
                    layoutParams.bottomMargin = (shouldUseHorizontalLayout && (qSPanel instanceof QuickQSPanel)) ? 0 : Math.max(qSPanel.mMediaTotalBottomMargin - qSPanel.getPaddingBottom(), 0);
                    layoutParams.topMargin = (!(qSPanel instanceof QuickQSPanel) || shouldUseHorizontalLayout) ? 0 : qSPanel.mMediaTopMargin;
                    uniqueObjectHostView.setLayoutParams(layoutParams);
                }
            } else {
                ViewGroup viewGroup2 = (ViewGroup) uniqueObjectHostView.getParent();
                if (viewGroup2 != null) {
                    viewGroup2.removeView(uniqueObjectHostView);
                }
            }
            qSPanel.setColumnRowLayout(shouldUseHorizontalLayout);
            qSPanel.updateMediaHostContentMargins(uniqueObjectHostView);
            if (qSPanel.mUsingMediaPlayer && (linearLayout = qSPanel.mHorizontalLinearLayout) != null && (qSPanel instanceof QuickQSPanel)) {
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
                layoutParams2.bottomMargin = Math.max(qSPanel.mMediaTotalBottomMargin - qSPanel.getPaddingBottom(), 0);
                qSPanel.mHorizontalLinearLayout.setLayoutParams(layoutParams2);
            }
            qSPanel.updatePadding();
            LinearLayout linearLayout4 = qSPanel.mHorizontalLinearLayout;
            if (linearLayout4 != null) {
                linearLayout4.setVisibility(shouldUseHorizontalLayout ? 0 : 8);
            }
        }
        updateMediaDisappearParameters();
        QSImpl$$ExternalSyntheticLambda3 qSImpl$$ExternalSyntheticLambda3 = this.mUsingHorizontalLayoutChangedListener;
        if (qSImpl$$ExternalSyntheticLambda3 != null) {
            qSImpl$$ExternalSyntheticLambda3.run();
        }
        return true;
    }

    public final void updateMediaDisappearParameters() {
        if (this.mUsingMediaPlayer) {
            MediaHost mediaHost = this.mMediaHost;
            DisappearParameters disappearParameters = mediaHost.state.disappearParameters;
            if (this.mUsingHorizontalLayout) {
                disappearParameters.disappearSize.set(0.0f, 0.4f);
                disappearParameters.gonePivot.set(1.0f, 0.0f);
                disappearParameters.contentTranslationFraction.set(0.25f, 1.0f);
                disappearParameters.disappearEnd = 0.6f;
            } else {
                disappearParameters.disappearSize.set(1.0f, 0.0f);
                disappearParameters.gonePivot.set(0.0f, 0.0f);
                disappearParameters.contentTranslationFraction.set(0.0f, 1.0f);
                disappearParameters.disappearEnd = 0.95f;
            }
            disappearParameters.fadeStartPosition = 0.95f;
            mediaHost.state.setDisappearParameters(disappearParameters);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v4, types: [com.android.systemui.plugins.qs.QSTile$Callback, com.android.systemui.qs.QSPanel$1] */
    public final void setTiles(Collection collection, boolean z) {
        QSTileRevealController qSTileRevealController;
        if (this.mDestroyed) {
            return;
        }
        if (!z && (qSTileRevealController = this.mQsTileRevealController) != null) {
            ArraySet arraySet = new ArraySet();
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                arraySet.add(((QSTile) it.next()).getTileSpec());
            }
            Set<String> stringSet = Prefs.get(qSTileRevealController.mContext).getStringSet("QsTileSpecsRevealed", Collections.EMPTY_SET);
            if (stringSet.isEmpty() || qSTileRevealController.mQsCustomizerController.isCustomizing()) {
                qSTileRevealController.addTileSpecsToRevealed(arraySet);
            } else {
                arraySet.removeAll(stringSet);
                qSTileRevealController.mTilesToReveal.addAll(arraySet);
            }
        }
        boolean z2 = true;
        if (((ArrayList) collection).size() <= this.mRecords.size()) {
            Iterator it2 = collection.iterator();
            int i = 0;
            while (true) {
                if (!it2.hasNext()) {
                    z2 = false;
                    break;
                } else if (((QSTile) it2.next()) != ((TileRecord) this.mRecords.get(i)).tile) {
                    break;
                } else {
                    i++;
                }
            }
            if (!z2 && i < this.mRecords.size()) {
                ArrayList arrayList = this.mRecords;
                List<TileRecord> subList = arrayList.subList(i, arrayList.size());
                for (TileRecord tileRecord : subList) {
                    ((QSPanel) this.mView).mTileLayout.removeTile(tileRecord);
                    tileRecord.tile.removeCallback(tileRecord.callback);
                }
                subList.clear();
                this.mCachedSpecs = (String) this.mRecords.stream().map(new QSPanelControllerBase$$ExternalSyntheticLambda3()).collect(Collectors.joining(","));
            }
        }
        if (!z2) {
            Iterator it3 = this.mRecords.iterator();
            while (it3.hasNext()) {
                TileRecord tileRecord2 = (TileRecord) it3.next();
                tileRecord2.tile.addCallback(tileRecord2.callback);
            }
            return;
        }
        Iterator it4 = this.mRecords.iterator();
        while (it4.hasNext()) {
            TileRecord tileRecord3 = (TileRecord) it4.next();
            ((QSPanel) this.mView).mTileLayout.removeTile(tileRecord3);
            tileRecord3.tile.removeCallback(tileRecord3.callback);
        }
        this.mRecords.clear();
        this.mCachedSpecs = "";
        Iterator it5 = collection.iterator();
        while (it5.hasNext()) {
            QSTile qSTile = (QSTile) it5.next();
            QSTileViewImpl qSTileViewImpl = new QSTileViewImpl(this.mView.getContext(), z, (QSLongPressEffect) this.mLongPressEffectProvider.get());
            final TileRecord tileRecord4 = new TileRecord();
            tileRecord4.tile = qSTile;
            tileRecord4.tileView = qSTileViewImpl;
            try {
                qSTileViewImpl.mQsLogger = this.mQSLogger;
            } catch (ClassCastException e) {
                Log.e("QSPanelControllerBase", "Failed to cast QSTileView to QSTileViewImpl", e);
            }
            final QSPanel qSPanel = (QSPanel) this.mView;
            qSPanel.getClass();
            ?? r3 = new QSTile.Callback() { // from class: com.android.systemui.qs.QSPanel.1
                @Override // com.android.systemui.plugins.qs.QSTile.Callback
                public final void onStateChanged(QSTile.State state) {
                    QSPanel.this.getClass();
                    tileRecord4.tileView.onStateChanged(state);
                }
            };
            tileRecord4.tile.addCallback(r3);
            tileRecord4.callback = r3;
            QSTileViewImpl qSTileViewImpl2 = tileRecord4.tileView;
            QSTile qSTile2 = tileRecord4.tile;
            qSTileViewImpl2.init(qSTile2);
            qSTile2.refreshState();
            QSPanel.QSTileLayout qSTileLayout = qSPanel.mTileLayout;
            if (qSTileLayout != null) {
                qSTileLayout.addTile(tileRecord4);
            }
            this.mRecords.add(tileRecord4);
            this.mCachedSpecs = (String) this.mRecords.stream().map(new QSPanelControllerBase$$ExternalSyntheticLambda3()).collect(Collectors.joining(","));
        }
    }

    public void onSplitShadeChanged(boolean z) {
    }
}
