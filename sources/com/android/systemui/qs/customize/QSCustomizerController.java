package com.android.systemui.qs.customize;

import android.animation.Animator;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.plugins.qs.QSContainerController;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.QSEditEvent;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QSHostAdapter;
import com.android.systemui.qs.QSImpl;
import com.android.systemui.qs.customize.QSCustomizer.ExpandAnimatorListener;
import com.android.systemui.qs.customize.TileQueryHelper;
import com.android.systemui.qs.customize.TileQueryHelper.TileCollector;
import com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.phone.LightBarController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.util.ViewController;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSCustomizerController extends ViewController {
    public final ConfigurationController mConfigurationController;
    public final AnonymousClass3 mConfigurationListener;
    public final AnonymousClass2 mKeyguardCallback;
    public final KeyguardStateController mKeyguardStateController;
    public final LightBarController mLightBarController;
    public final AnonymousClass1 mOnMenuItemClickListener;
    public final QSHost mQsHost;
    public final ScreenLifecycle mScreenLifecycle;
    public final TileAdapter mTileAdapter;
    public final TileQueryHelper mTileQueryHelper;
    public final Toolbar mToolbar;
    public final UiEventLogger mUiEventLogger;

    /* JADX WARN: Type inference failed for: r0v0, types: [com.android.systemui.qs.customize.QSCustomizerController$1] */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.systemui.qs.customize.QSCustomizerController$2] */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.systemui.qs.customize.QSCustomizerController$3] */
    public QSCustomizerController(QSCustomizer qSCustomizer, TileQueryHelper tileQueryHelper, QSHost qSHost, TileAdapter tileAdapter, ScreenLifecycle screenLifecycle, KeyguardStateController keyguardStateController, LightBarController lightBarController, ConfigurationController configurationController, UiEventLogger uiEventLogger) {
        super(qSCustomizer);
        this.mOnMenuItemClickListener = new Toolbar.OnMenuItemClickListener() { // from class: com.android.systemui.qs.customize.QSCustomizerController.1
            @Override // android.widget.Toolbar.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() != 1) {
                    return false;
                }
                QSCustomizerController.this.mUiEventLogger.log(QSEditEvent.QS_EDIT_RESET);
                QSCustomizerController qSCustomizerController = QSCustomizerController.this;
                Resources resources = qSCustomizerController.mView.getContext().getResources();
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(Arrays.asList(resources.getString(R.string.quick_settings_tiles_default).split(",")));
                TileAdapter tileAdapter2 = qSCustomizerController.mTileAdapter;
                List list = tileAdapter2.mCurrentSpecs;
                ((QSHostAdapter) tileAdapter2.mHost).changeTilesByUser(arrayList);
                if (arrayList.equals(tileAdapter2.mCurrentSpecs)) {
                    return false;
                }
                tileAdapter2.mCurrentSpecs = arrayList;
                tileAdapter2.recalcSpecs();
                return false;
            }
        };
        this.mKeyguardCallback = new KeyguardStateController.Callback() { // from class: com.android.systemui.qs.customize.QSCustomizerController.2
            @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
            public final void onKeyguardShowingChanged() {
                QSCustomizerController qSCustomizerController = QSCustomizerController.this;
                if (((QSCustomizer) qSCustomizerController.mView).isAttachedToWindow() && ((KeyguardStateControllerImpl) qSCustomizerController.mKeyguardStateController).mShowing && !((QSCustomizer) qSCustomizerController.mView).mOpening) {
                    qSCustomizerController.hide(true);
                }
            }
        };
        this.mConfigurationListener = new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.qs.customize.QSCustomizerController.3
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onConfigChanged(Configuration configuration) {
                QSCustomizerController qSCustomizerController = QSCustomizerController.this;
                ((QSCustomizer) qSCustomizerController.mView).updateNavBackDrop(configuration, qSCustomizerController.mLightBarController);
                QSCustomizer qSCustomizer2 = (QSCustomizer) qSCustomizerController.mView;
                qSCustomizer2.updateTransparentViewHeight();
                qSCustomizer2.mRecyclerView.mAdapter.notifyItemChanged(0);
                TileAdapter tileAdapter2 = qSCustomizerController.mTileAdapter;
                tileAdapter2.getClass();
                int integer = tileAdapter2.mContext.getResources().getInteger(R.integer.quick_settings_num_columns);
                if (integer != tileAdapter2.mNumColumns) {
                    tileAdapter2.mNumColumns = integer;
                    RecyclerView.LayoutManager layoutManager = ((QSCustomizer) qSCustomizerController.mView).mRecyclerView.getLayoutManager();
                    if (layoutManager instanceof GridLayoutManager) {
                        ((GridLayoutManager) layoutManager).setSpanCount(tileAdapter2.mNumColumns);
                    }
                }
            }
        };
        this.mTileQueryHelper = tileQueryHelper;
        this.mQsHost = qSHost;
        this.mTileAdapter = tileAdapter;
        this.mScreenLifecycle = screenLifecycle;
        this.mKeyguardStateController = keyguardStateController;
        this.mLightBarController = lightBarController;
        this.mConfigurationController = configurationController;
        this.mUiEventLogger = uiEventLogger;
        qSCustomizer.getClass();
        this.mToolbar = (Toolbar) qSCustomizer.findViewById(android.R.id.addToDictionaryButton);
    }

    public final void hide(boolean z) {
        long j;
        boolean z2 = z && this.mScreenLifecycle.mScreenState != 0;
        if (((QSCustomizer) this.mView).isShown) {
            this.mUiEventLogger.log(QSEditEvent.QS_EDIT_CLOSED);
            this.mToolbar.dismissPopupMenus();
            QSCustomizer qSCustomizer = (QSCustomizer) this.mView;
            qSCustomizer.mCustomizing = false;
            QSImpl qSImpl = qSCustomizer.mQs;
            if (qSImpl != null) {
                qSImpl.notifyCustomizeChanged();
            }
            if (this.mTileQueryHelper.mFinished) {
                this.mTileAdapter.saveSpecs(this.mQsHost);
            }
            QSCustomizer qSCustomizer2 = (QSCustomizer) this.mView;
            if (qSCustomizer2.isShown) {
                qSCustomizer2.isShown = false;
                Animator animator = qSCustomizer2.mClipper.mAnimator;
                if (animator != null) {
                    animator.cancel();
                }
                qSCustomizer2.mOpening = false;
                if (z2) {
                    j = qSCustomizer2.mClipper.animateCircularClip(qSCustomizer2.mX, qSCustomizer2.mY, false, qSCustomizer2.mCollapseAnimationListener);
                } else {
                    qSCustomizer2.setVisibility(8);
                    j = 0;
                }
                QSContainerController qSContainerController = qSCustomizer2.mQsContainerController;
                if (qSContainerController != null) {
                    qSContainerController.setCustomizerAnimating(z2);
                    qSCustomizer2.mQsContainerController.setCustomizerShowing(false, j);
                }
            }
            ((QSCustomizer) this.mView).updateNavColors(this.mLightBarController);
            ((KeyguardStateControllerImpl) this.mKeyguardStateController).removeCallback(this.mKeyguardCallback);
        }
    }

    public final boolean isCustomizing() {
        return ((QSCustomizer) this.mView).isCustomizing();
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
        View view = this.mView;
        ((QSCustomizer) view).updateNavBackDrop(view.getResources().getConfiguration(), this.mLightBarController);
        ((ConfigurationControllerImpl) this.mConfigurationController).addCallback(this.mConfigurationListener);
        TileQueryHelper tileQueryHelper = this.mTileQueryHelper;
        TileAdapter tileAdapter = this.mTileAdapter;
        tileQueryHelper.mListener = tileAdapter;
        tileAdapter.mMarginDecoration.mHalfMargin = this.mView.getResources().getDimensionPixelSize(R.dimen.qs_tile_margin_horizontal) / 2;
        final RecyclerView recyclerView = ((QSCustomizer) this.mView).mRecyclerView;
        recyclerView.setAdapter(tileAdapter);
        tileAdapter.mItemTouchHelper.attachToRecyclerView(recyclerView);
        this.mView.getContext();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(tileAdapter.mNumColumns) { // from class: com.android.systemui.qs.customize.QSCustomizerController.4
            @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
            public final void calculateItemDecorationsForChild(Rect rect, View view2) {
                if (view2 instanceof TextView) {
                    return;
                }
                rect.setEmpty();
                QSCustomizerController.this.mTileAdapter.mMarginDecoration.getItemOffsets(rect, view2, recyclerView, new RecyclerView.State());
                ((ViewGroup.MarginLayoutParams) ((GridLayoutManager.LayoutParams) view2.getLayoutParams())).leftMargin = rect.left;
                ((ViewGroup.MarginLayoutParams) ((GridLayoutManager.LayoutParams) view2.getLayoutParams())).rightMargin = rect.right;
            }

            @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public final void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler recycler, RecyclerView.State state, View view2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            }
        };
        gridLayoutManager.mSpanSizeLookup = tileAdapter.mSizeLookup;
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(tileAdapter.mDecoration);
        recyclerView.addItemDecoration(tileAdapter.mMarginDecoration);
        this.mToolbar.setOnMenuItemClickListener(this.mOnMenuItemClickListener);
        this.mToolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.qs.customize.QSCustomizerController$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                QSCustomizerController.this.hide(true);
            }
        });
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
        this.mTileQueryHelper.mListener = null;
        this.mToolbar.setOnMenuItemClickListener(null);
        ((ConfigurationControllerImpl) this.mConfigurationController).removeCallback(this.mConfigurationListener);
    }

    public final void show(int i, int i2, boolean z) {
        if (((QSCustomizer) this.mView).isShown) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        QSHost qSHost = this.mQsHost;
        QSHostAdapter qSHostAdapter = (QSHostAdapter) qSHost;
        Iterator it = qSHostAdapter.getTiles().iterator();
        while (it.hasNext()) {
            arrayList.add(((QSTile) it.next()).getTileSpec());
        }
        TileAdapter tileAdapter = this.mTileAdapter;
        if (!arrayList.equals(tileAdapter.mCurrentSpecs)) {
            tileAdapter.mCurrentSpecs = arrayList;
            tileAdapter.recalcSpecs();
        }
        if (z) {
            QSCustomizer qSCustomizer = (QSCustomizer) this.mView;
            if (!qSCustomizer.isShown) {
                QSCustomizer.reloadAdapterTileHeight(qSCustomizer.mRecyclerView.mAdapter);
                qSCustomizer.mRecyclerView.getLayoutManager().scrollToPosition(0);
                qSCustomizer.setVisibility(0);
                Animator animator = qSCustomizer.mClipper.mAnimator;
                if (animator != null) {
                    animator.cancel();
                }
                qSCustomizer.mClipper.mBackground.showSecondLayer();
                qSCustomizer.isShown = true;
                qSCustomizer.mCustomizing = true;
                QSImpl qSImpl = qSCustomizer.mQs;
                if (qSImpl != null) {
                    qSImpl.notifyCustomizeChanged();
                }
                QSContainerController qSContainerController = qSCustomizer.mQsContainerController;
                if (qSContainerController != null) {
                    qSContainerController.setCustomizerAnimating(false);
                    qSCustomizer.mQsContainerController.setCustomizerShowing(true);
                }
            }
        } else {
            QSCustomizer qSCustomizer2 = (QSCustomizer) this.mView;
            if (!qSCustomizer2.isShown) {
                QSCustomizer.reloadAdapterTileHeight(tileAdapter);
                qSCustomizer2.mRecyclerView.getLayoutManager().scrollToPosition(0);
                int[] locationOnScreen = qSCustomizer2.findViewById(R.id.customize_container).getLocationOnScreen();
                qSCustomizer2.mX = i - locationOnScreen[0];
                qSCustomizer2.mY = i2 - locationOnScreen[1];
                qSCustomizer2.isShown = true;
                qSCustomizer2.mOpening = true;
                qSCustomizer2.setVisibility(0);
                long animateCircularClip = qSCustomizer2.mClipper.animateCircularClip(qSCustomizer2.mX, qSCustomizer2.mY, true, qSCustomizer2.new ExpandAnimatorListener(tileAdapter));
                QSContainerController qSContainerController2 = qSCustomizer2.mQsContainerController;
                if (qSContainerController2 != null) {
                    qSContainerController2.setCustomizerAnimating(true);
                    qSCustomizer2.mQsContainerController.setCustomizerShowing(true, animateCircularClip);
                }
            }
            this.mUiEventLogger.log(QSEditEvent.QS_EDIT_OPEN);
        }
        TileQueryHelper tileQueryHelper = this.mTileQueryHelper;
        tileQueryHelper.mTiles.clear();
        tileQueryHelper.mSpecs.clear();
        tileQueryHelper.mFinished = false;
        String string = tileQueryHelper.mContext.getString(R.string.quick_settings_tiles_stock);
        String string2 = Settings.Secure.getString(tileQueryHelper.mContext.getContentResolver(), "sysui_qs_tiles");
        ArrayList arrayList2 = new ArrayList();
        if (string2 != null) {
            arrayList2.addAll(Arrays.asList(string2.split(",")));
        } else {
            string2 = "";
        }
        for (String str : string.split(",")) {
            if (!string2.contains(str)) {
                arrayList2.add(str);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        arrayList2.remove("cell");
        arrayList2.remove("wifi");
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            String str2 = (String) it2.next();
            if (!str2.startsWith("custom(")) {
                qSHostAdapter.getClass();
                TileSpec create = TileSpec.Companion.create(str2);
                CurrentTilesInteractorImpl currentTilesInteractorImpl = (CurrentTilesInteractorImpl) qSHostAdapter.interactor;
                currentTilesInteractorImpl.featureFlags.getClass();
                QSTile createTile = currentTilesInteractorImpl.tileFactory.createTile(create.getSpec());
                if (createTile != null) {
                    if (createTile.isAvailable()) {
                        arrayList3.add(createTile);
                    } else {
                        createTile.destroy();
                    }
                }
            }
        }
        TileQueryHelper.TileCollector tileCollector = tileQueryHelper.new TileCollector(arrayList3, qSHost);
        for (TileQueryHelper.TilePair tilePair : tileCollector.mQSTileList) {
            tilePair.mTile.addCallback(tileCollector);
            QSTile qSTile = tilePair.mTile;
            qSTile.setListening(tileCollector, true);
            qSTile.refreshState();
        }
        ((KeyguardStateControllerImpl) this.mKeyguardStateController).addCallback(this.mKeyguardCallback);
        ((QSCustomizer) this.mView).updateNavColors(this.mLightBarController);
    }
}
