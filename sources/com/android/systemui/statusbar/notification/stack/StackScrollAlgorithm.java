package com.android.systemui.statusbar.notification.stack;

import android.content.Context;
import android.content.res.Resources;
import android.util.MathUtils;
import android.view.View;
import com.android.internal.policy.SystemBarUtils;
import com.android.systemui.scene.shared.flag.SceneContainerFlag;
import com.android.systemui.statusbar.notification.SourceType$Companion$from$1;
import com.android.systemui.statusbar.notification.footer.ui.view.FooterView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.wm.shell.R;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StackScrollAlgorithm {
    public static final SourceType$Companion$from$1 STACK_SCROLL_ALGO = new SourceType$Companion$from$1("StackScrollAlgorithm");
    public boolean mClipNotificationScrollToTop;
    public boolean mEnableNotificationClipping;
    public float mGapHeight;
    public float mGapHeightOnLockscreen;
    public int mHeadsUpAppearHeightBottom;
    float mHeadsUpAppearStartAboveScreen;
    float mHeadsUpInset;
    public final NotificationStackScrollLayout mHostView;
    public boolean mIsExpanded;
    public float mLargeCornerRadius;
    public int mMarginBottom;
    public float mNotificationScrimPadding;
    public float mPaddingBetweenElements;
    public int mPinnedZTranslationExtra;
    public float mQuickQsOffsetHeight;
    public float mSmallCornerRadius;
    public final StackScrollAlgorithmState mTempAlgorithmState = new StackScrollAlgorithmState();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StackScrollAlgorithmState {
        public ExpandableView firstViewInShelf;
        public float mCurrentExpandedYPosition;
        public float mCurrentYPosition;
        public final ArrayList visibleChildren = new ArrayList();
    }

    public StackScrollAlgorithm(Context context, NotificationStackScrollLayout notificationStackScrollLayout) {
        this.mHostView = notificationStackScrollLayout;
        initView(context);
    }

    public static boolean childNeedsGapHeight(NotificationSectionsManager notificationSectionsManager, int i, ExpandableView expandableView, ExpandableView expandableView2) {
        return notificationSectionsManager.beginsSection(expandableView, expandableView2) && i > 0 && !(expandableView2 instanceof SectionHeaderView) && !(expandableView instanceof FooterView);
    }

    public void clampHunToTop(float f, float f2, ExpandableViewState expandableViewState) {
        float max = Math.max(f, expandableViewState.mYTranslation);
        float f3 = expandableViewState.height - (max - expandableViewState.mYTranslation);
        expandableViewState.setYTranslation(max);
        expandableViewState.height = (int) Math.max(f3, f2);
    }

    public float computeCornerRoundnessForPinnedHun(float f, float f2, float f3, float f4) {
        return MathUtils.lerp(f4, 1.0f, Math.min(1.0f, Math.max(0.0f, f2 - (f - f3)) / f3));
    }

    public final float getExpansionFractionWithoutShelf(StackScrollAlgorithmState stackScrollAlgorithmState, AmbientState ambientState) {
        float height = (ambientState.mShelf == null || stackScrollAlgorithmState.firstViewInShelf == null) ? 0.0f : r0.getHeight();
        float scrimTopPaddingOrZero = getScrimTopPaddingOrZero(ambientState);
        float f = (ambientState.mStackHeight - height) - scrimTopPaddingOrZero;
        float f2 = (ambientState.mStackEndHeight - height) - scrimTopPaddingOrZero;
        if (f2 == 0.0f) {
            return 0.0f;
        }
        return f / f2;
    }

    public float getGapForLocation(float f, boolean z) {
        return f > 0.0f ? MathUtils.lerp(this.mGapHeightOnLockscreen, this.mGapHeight, f) : z ? this.mGapHeightOnLockscreen : this.mGapHeight;
    }

    public final float getScrimTopPaddingOrZero(AmbientState ambientState) {
        if (!ambientState.isOnKeyguard() || (ambientState.mBypassController.getBypassEnabled() && ambientState.isPulseExpanding())) {
            return this.mNotificationScrimPadding;
        }
        return 0.0f;
    }

    public final void initView(Context context) {
        Resources resources = context.getResources();
        this.mPaddingBetweenElements = resources.getDimensionPixelSize(R.dimen.notification_divider_height);
        resources.getDimensionPixelSize(R.dimen.notification_min_height);
        this.mEnableNotificationClipping = resources.getBoolean(R.bool.notification_enable_clipping);
        this.mClipNotificationScrollToTop = resources.getBoolean(R.bool.config_clipNotificationScrollToTop);
        this.mHeadsUpInset = resources.getDimensionPixelSize(R.dimen.heads_up_status_bar_padding) + SystemBarUtils.getStatusBarHeight(context);
        this.mHeadsUpAppearStartAboveScreen = resources.getDimensionPixelSize(R.dimen.heads_up_appear_y_above_screen);
        context.getResources().getDimensionPixelSize(R.dimen.heads_up_cycling_padding);
        this.mPinnedZTranslationExtra = resources.getDimensionPixelSize(R.dimen.heads_up_pinned_elevation);
        this.mGapHeight = resources.getDimensionPixelSize(R.dimen.notification_section_divider_height);
        this.mGapHeightOnLockscreen = resources.getDimensionPixelSize(R.dimen.notification_section_divider_height_lockscreen);
        this.mNotificationScrimPadding = resources.getDimensionPixelSize(R.dimen.notification_side_paddings);
        this.mMarginBottom = resources.getDimensionPixelSize(R.dimen.notification_panel_margin_bottom);
        this.mQuickQsOffsetHeight = SystemBarUtils.getQuickQsOffsetHeight(context);
        this.mSmallCornerRadius = resources.getDimension(R.dimen.notification_corner_radius_small);
        this.mLargeCornerRadius = resources.getDimension(R.dimen.notification_corner_radius);
    }

    public void maybeUpdateHeadsUpIsVisible(ExpandableViewState expandableViewState, boolean z, boolean z2, boolean z3, float f, float f2) {
        if (z && z2 && z3) {
            expandableViewState.headsUpIsVisible = f < f2;
        }
    }

    public boolean shouldHunBeVisibleWhenScrolled(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        return z && !z2 && !z3 && (!z4 || z5);
    }

    public void updatePulsingStates(StackScrollAlgorithmState stackScrollAlgorithmState, AmbientState ambientState) {
        int size = stackScrollAlgorithmState.visibleChildren.size();
        ExpandableNotificationRow expandableNotificationRow = null;
        for (int i = 0; i < size; i++) {
            View view = (View) stackScrollAlgorithmState.visibleChildren.get(i);
            if (view instanceof ExpandableNotificationRow) {
                ExpandableNotificationRow expandableNotificationRow2 = (ExpandableNotificationRow) view;
                if (expandableNotificationRow2.showingPulsing() && (i != 0 || !ambientState.isPulseExpanding())) {
                    expandableNotificationRow2.mViewState.hidden = false;
                    expandableNotificationRow = expandableNotificationRow2;
                }
            }
        }
        float f = ambientState.mDozeAmount;
        if (f == 0.0f || f == 1.0f) {
            ambientState.mPulsingRow = expandableNotificationRow;
        }
    }

    public void updateViewWithShelf(ExpandableView expandableView, ExpandableViewState expandableViewState, float f) {
        expandableViewState.setYTranslation(Math.min(expandableViewState.mYTranslation, f));
        if (expandableViewState.mYTranslation >= f) {
            expandableViewState.hidden = (expandableView.isExpandAnimationRunning() || expandableView.hasExpandingChild()) ? false : true;
            expandableViewState.inShelf = true;
            expandableViewState.headsUpIsVisible = false;
        }
    }

    public void updateZTranslationForHunInStack(float f, float f2, float f3, ExpandableViewState expandableViewState) {
        SceneContainerFlag.isUnexpectedlyInLegacyMode();
    }

    public void clampHunToTop(float f, float f2, float f3, ExpandableViewState expandableViewState) {
        clampHunToTop(f + f2, f3, expandableViewState);
    }
}
