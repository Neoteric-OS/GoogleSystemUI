package com.android.systemui.qs.customize;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import com.android.internal.policy.SystemBarUtils;
import com.android.systemui.FontSizeUtils;
import com.android.systemui.plugins.qs.QSContainerController;
import com.android.systemui.qs.QSDetailClipper;
import com.android.systemui.qs.QSImpl;
import com.android.systemui.statusbar.phone.LightBarController;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class QSCustomizer extends LinearLayout {
    public boolean isShown;
    public final QSDetailClipper mClipper;
    public final AnonymousClass1 mCollapseAnimationListener;
    public boolean mCustomizing;
    public boolean mIsShowingNavBackdrop;
    public boolean mOpening;
    public QSImpl mQs;
    public QSContainerController mQsContainerController;
    public final RecyclerView mRecyclerView;
    public final Toolbar mToolbar;
    public final View mTransparentView;
    public int mX;
    public int mY;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ExpandAnimatorListener extends AnimatorListenerAdapter {
        public final TileAdapter mTileAdapter;

        public ExpandAnimatorListener(TileAdapter tileAdapter) {
            this.mTileAdapter = tileAdapter;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            QSCustomizer qSCustomizer = QSCustomizer.this;
            qSCustomizer.mOpening = false;
            QSImpl qSImpl = qSCustomizer.mQs;
            if (qSImpl != null) {
                qSImpl.notifyCustomizeChanged();
            }
            QSContainerController qSContainerController = QSCustomizer.this.mQsContainerController;
            if (qSContainerController != null) {
                qSContainerController.setCustomizerAnimating(false);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            QSCustomizer qSCustomizer = QSCustomizer.this;
            if (qSCustomizer.isShown) {
                qSCustomizer.mCustomizing = true;
                QSImpl qSImpl = qSCustomizer.mQs;
                if (qSImpl != null) {
                    qSImpl.notifyCustomizeChanged();
                }
            }
            QSCustomizer qSCustomizer2 = QSCustomizer.this;
            qSCustomizer2.mOpening = false;
            QSContainerController qSContainerController = qSCustomizer2.mQsContainerController;
            if (qSContainerController != null) {
                qSContainerController.setCustomizerAnimating(false);
            }
            QSCustomizer.this.mRecyclerView.setAdapter(this.mTileAdapter);
        }
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [com.android.systemui.qs.customize.QSCustomizer$1] */
    public QSCustomizer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCollapseAnimationListener = new AnimatorListenerAdapter() { // from class: com.android.systemui.qs.customize.QSCustomizer.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                QSCustomizer qSCustomizer = QSCustomizer.this;
                if (!qSCustomizer.isShown) {
                    qSCustomizer.setVisibility(8);
                }
                QSContainerController qSContainerController = QSCustomizer.this.mQsContainerController;
                if (qSContainerController != null) {
                    qSContainerController.setCustomizerAnimating(false);
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                QSCustomizer qSCustomizer = QSCustomizer.this;
                if (!qSCustomizer.isShown) {
                    qSCustomizer.setVisibility(8);
                }
                QSContainerController qSContainerController = QSCustomizer.this.mQsContainerController;
                if (qSContainerController != null) {
                    qSContainerController.setCustomizerAnimating(false);
                }
            }
        };
        LayoutInflater.from(getContext()).inflate(R.layout.qs_customize_panel_content, this);
        this.mClipper = new QSDetailClipper(findViewById(R.id.customize_container));
        Toolbar toolbar = (Toolbar) findViewById(android.R.id.addToDictionaryButton);
        this.mToolbar = toolbar;
        TypedValue typedValue = new TypedValue();
        ((LinearLayout) this).mContext.getTheme().resolveAttribute(android.R.attr.homeAsUpIndicator, typedValue, true);
        toolbar.setNavigationIcon(getResources().getDrawable(typedValue.resourceId, ((LinearLayout) this).mContext.getTheme()));
        toolbar.getMenu().add(0, 1, 0, android.R.string.ringtone_picker_title_alarm).setShowAsAction(1);
        toolbar.setTitle(R.string.qs_edit);
        RecyclerView recyclerView = (RecyclerView) findViewById(android.R.id.list);
        this.mRecyclerView = recyclerView;
        this.mTransparentView = findViewById(R.id.customizer_transparent_view);
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.mMoveDuration = 150L;
        DefaultItemAnimator defaultItemAnimator2 = recyclerView.mItemAnimator;
        if (defaultItemAnimator2 != null) {
            defaultItemAnimator2.endAnimations();
            recyclerView.mItemAnimator.mListener = null;
        }
        recyclerView.mItemAnimator = defaultItemAnimator;
        defaultItemAnimator.mListener = recyclerView.mItemAnimatorListener;
        updateTransparentViewHeight();
    }

    public static void reloadAdapterTileHeight(RecyclerView.Adapter adapter) {
        if (adapter instanceof TileAdapter) {
            TileAdapter tileAdapter = (TileAdapter) adapter;
            int dimensionPixelSize = tileAdapter.mContext.getResources().getDimensionPixelSize(R.dimen.qs_tile_height);
            FontSizeUtils.updateFontSize(tileAdapter.mTempTextView, R.dimen.qs_tile_text_size);
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            tileAdapter.mTempTextView.measure(makeMeasureSpec, makeMeasureSpec);
            tileAdapter.mMinTileViewHeight = Math.max(dimensionPixelSize, (tileAdapter.mContext.getResources().getDimensionPixelSize(R.dimen.qs_tile_padding) * 2) + (tileAdapter.mTempTextView.getMeasuredHeight() * 2));
        }
    }

    public final boolean isCustomizing() {
        return this.mCustomizing || this.mOpening;
    }

    @Override // android.view.View
    public final boolean isShown() {
        return this.isShown;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mToolbar.setTitleTextAppearance(((LinearLayout) this).mContext, android.R.style.TextAppearance.DeviceDefault.Widget.ActionBar.Title);
        this.mToolbar.getMenu().clear();
        this.mToolbar.getMenu().add(0, 1, 0, android.R.string.ringtone_picker_title_alarm).setShowAsAction(1);
    }

    public final void updateNavBackDrop(Configuration configuration, LightBarController lightBarController) {
        View findViewById = findViewById(R.id.nav_bar_background);
        boolean z = configuration.smallestScreenWidthDp >= 600 || configuration.orientation != 2;
        this.mIsShowingNavBackdrop = z;
        if (findViewById != null) {
            findViewById.setVisibility(z ? 0 : 8);
        }
        updateNavColors(lightBarController);
    }

    public final void updateNavColors(LightBarController lightBarController) {
        boolean z = this.mIsShowingNavBackdrop && this.isShown;
        if (lightBarController.mQsCustomizing == z) {
            return;
        }
        lightBarController.mQsCustomizing = z;
        lightBarController.reevaluate();
    }

    public final void updateTransparentViewHeight() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mTransparentView.getLayoutParams();
        Context context = ((LinearLayout) this).mContext;
        layoutParams.height = context.getResources().getBoolean(R.bool.config_use_large_screen_shade_header) ? 0 : SystemBarUtils.getQuickQsOffsetHeight(context);
        this.mTransparentView.setLayoutParams(layoutParams);
    }
}
