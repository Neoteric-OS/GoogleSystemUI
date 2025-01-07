package com.android.systemui.qs;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.os.Handler;
import android.util.ArraySet;
import android.util.Property;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import com.android.systemui.Prefs;
import com.android.systemui.qs.QSPanelControllerBase;
import com.android.systemui.qs.customize.QSCustomizerController;
import com.android.systemui.qs.tileimpl.QSTileViewImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSTileRevealController {
    public final Context mContext;
    public final PagedTileLayout mPagedTileLayout;
    public final QSPanelController mQSPanelController;
    public final QSCustomizerController mQsCustomizerController;
    public final ArraySet mTilesToReveal = new ArraySet();
    public final Handler mHandler = new Handler();
    public final AnonymousClass1 mRevealQsTiles = new AnonymousClass1();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.qs.QSTileRevealController$1, reason: invalid class name */
    public final class AnonymousClass1 implements Runnable {
        public AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            QSTileRevealController qSTileRevealController = QSTileRevealController.this;
            PagedTileLayout pagedTileLayout = qSTileRevealController.mPagedTileLayout;
            ArraySet arraySet = qSTileRevealController.mTilesToReveal;
            QSTileRevealController$1$$ExternalSyntheticLambda0 qSTileRevealController$1$$ExternalSyntheticLambda0 = new QSTileRevealController$1$$ExternalSyntheticLambda0(this);
            pagedTileLayout.getClass();
            boolean z = arraySet.isEmpty() || pagedTileLayout.mPages.size() < 2;
            boolean z2 = (pagedTileLayout.getScrollX() == 0 && pagedTileLayout.mFakeDragging) ? false : true;
            if (z || z2 || pagedTileLayout.mRunningInTestHarness || !pagedTileLayout.beginFakeDrag()) {
                return;
            }
            int size = pagedTileLayout.mPages.size() - 1;
            TileLayout tileLayout = (TileLayout) pagedTileLayout.mPages.get(size);
            ArrayList arrayList = new ArrayList();
            Iterator it = tileLayout.mRecords.iterator();
            while (it.hasNext()) {
                QSPanelControllerBase.TileRecord tileRecord = (QSPanelControllerBase.TileRecord) it.next();
                if (arraySet.contains(tileRecord.tile.getTileSpec())) {
                    int size2 = arrayList.size();
                    QSTileViewImpl qSTileViewImpl = tileRecord.tileView;
                    qSTileViewImpl.setAlpha(0.0f);
                    qSTileViewImpl.setScaleX(0.0f);
                    qSTileViewImpl.setScaleY(0.0f);
                    ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(qSTileViewImpl, PropertyValuesHolder.ofFloat((Property<?, Float>) View.ALPHA, 1.0f), PropertyValuesHolder.ofFloat((Property<?, Float>) View.SCALE_X, 1.0f), PropertyValuesHolder.ofFloat((Property<?, Float>) View.SCALE_Y, 1.0f));
                    ofPropertyValuesHolder.setDuration(450L);
                    ofPropertyValuesHolder.setStartDelay(size2 * 85);
                    ofPropertyValuesHolder.setInterpolator(new OvershootInterpolator(1.3f));
                    arrayList.add(ofPropertyValuesHolder);
                }
            }
            if (arrayList.isEmpty()) {
                pagedTileLayout.endFakeDrag();
                return;
            }
            AnimatorSet animatorSet = new AnimatorSet();
            pagedTileLayout.mBounceAnimatorSet = animatorSet;
            animatorSet.playTogether(arrayList);
            pagedTileLayout.mBounceAnimatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.qs.PagedTileLayout.1
                public final /* synthetic */ QSTileRevealController$1$$ExternalSyntheticLambda0 val$postAnimation;

                public AnonymousClass1(QSTileRevealController$1$$ExternalSyntheticLambda0 qSTileRevealController$1$$ExternalSyntheticLambda02) {
                    r2 = qSTileRevealController$1$$ExternalSyntheticLambda02;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    PagedTileLayout.this.mBounceAnimatorSet = null;
                    r2.run();
                }
            });
            pagedTileLayout.setOffscreenPageLimit(size);
            int width = pagedTileLayout.getWidth() * size;
            if (pagedTileLayout.isLayoutRtl()) {
                width = -width;
            }
            pagedTileLayout.scrollByX(width, 750);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory {
        public final Context mContext;
        public final QSCustomizerController mQsCustomizerController;

        public Factory(Context context, QSCustomizerController qSCustomizerController) {
            this.mContext = context;
            this.mQsCustomizerController = qSCustomizerController;
        }
    }

    public QSTileRevealController(Context context, QSPanelController qSPanelController, PagedTileLayout pagedTileLayout, QSCustomizerController qSCustomizerController) {
        this.mContext = context;
        this.mQSPanelController = qSPanelController;
        this.mPagedTileLayout = pagedTileLayout;
        this.mQsCustomizerController = qSCustomizerController;
    }

    public final void addTileSpecsToRevealed(ArraySet arraySet) {
        Context context = this.mContext;
        ArraySet arraySet2 = new ArraySet(Prefs.get(context).getStringSet("QsTileSpecsRevealed", Collections.EMPTY_SET));
        arraySet2.addAll(arraySet);
        Prefs.get(this.mContext).edit().putStringSet("QsTileSpecsRevealed", arraySet2).apply();
    }
}
