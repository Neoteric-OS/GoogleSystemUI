package com.android.systemui.statusbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.MathUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import androidx.activity.BackEventCompat$$ExternalSyntheticOutline0;
import com.android.app.animation.Interpolators;
import com.android.internal.policy.SystemBarUtils;
import com.android.systemui.statusbar.notification.ColorUpdateLogger;
import com.android.systemui.statusbar.notification.SourceType$Companion$from$1;
import com.android.systemui.statusbar.notification.row.ActivatableNotificationView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundView;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.NotificationRoundnessManager;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.android.systemui.statusbar.phone.NotificationIconContainer;
import com.android.systemui.util.DumpUtilsKt;
import com.android.wm.shell.R;
import java.io.PrintWriter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class NotificationShelf extends ActivatableNotificationView {
    public float mActualWidth;
    public AmbientState mAmbientState;
    public boolean mAnimationsEnabled;
    public boolean mCanInteract;
    public boolean mCanModifyColorOfNotifications;
    public final Rect mClipRect;
    public float mCornerAnimationDistance;
    public boolean mEnableNotificationClipping;
    public boolean mHasItemsInStableShelf;
    public boolean mHideBackground;
    public NotificationStackScrollLayout mHostLayout;
    public int mIndexOfFirstViewInShelf;
    public boolean mInteractive;
    public int mMaxIconsOnLockscreen;
    public int mNotGoneIndex;
    public int mPaddingBetweenElements;
    public NotificationRoundnessManager mRoundnessManager;
    public int mScrollFastThreshold;
    public NotificationIconContainer mShelfIcons;
    public boolean mShowNotificationShelf;
    public static final Interpolator ICON_ALPHA_INTERPOLATOR = new PathInterpolator(0.6f, 0.0f, 0.6f, 0.0f);
    public static final SourceType$Companion$from$1 BASE_VALUE = new SourceType$Companion$from$1("BaseValue");
    public static final SourceType$Companion$from$1 SHELF_SCROLL = new SourceType$Companion$from$1("ShelfScroll");

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ShelfState extends ExpandableViewState {
        public ExpandableView firstViewInShelf;
        public boolean hasItemsInStableShelf;

        public ShelfState() {
        }

        @Override // com.android.systemui.statusbar.notification.stack.ExpandableViewState, com.android.systemui.statusbar.notification.stack.ViewState
        public final void animateTo(View view, AnimationProperties animationProperties) {
            NotificationShelf notificationShelf = NotificationShelf.this;
            if (notificationShelf.mShowNotificationShelf) {
                super.animateTo(view, animationProperties);
                notificationShelf.mIndexOfFirstViewInShelf = notificationShelf.mHostLayout.indexOfChild(this.firstViewInShelf);
                notificationShelf.updateAppearance();
                boolean z = this.hasItemsInStableShelf;
                if (notificationShelf.mHasItemsInStableShelf != z) {
                    notificationShelf.mHasItemsInStableShelf = z;
                    notificationShelf.updateInteractiveness();
                }
                notificationShelf.mShelfIcons.setAnimationsEnabled(notificationShelf.mAnimationsEnabled);
            }
        }

        @Override // com.android.systemui.statusbar.notification.stack.ExpandableViewState, com.android.systemui.statusbar.notification.stack.ViewState
        public final void applyToView(View view) {
            NotificationShelf notificationShelf = NotificationShelf.this;
            if (notificationShelf.mShowNotificationShelf) {
                super.applyToView(view);
                notificationShelf.mIndexOfFirstViewInShelf = notificationShelf.mHostLayout.indexOfChild(this.firstViewInShelf);
                notificationShelf.updateAppearance();
                boolean z = this.hasItemsInStableShelf;
                if (notificationShelf.mHasItemsInStableShelf != z) {
                    notificationShelf.mHasItemsInStableShelf = z;
                    notificationShelf.updateInteractiveness();
                }
                notificationShelf.mShelfIcons.setAnimationsEnabled(notificationShelf.mAnimationsEnabled);
            }
        }
    }

    public NotificationShelf(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAnimationsEnabled = true;
        this.mClipRect = new Rect();
        this.mIndexOfFirstViewInShelf = -1;
        this.mActualWidth = -1.0f;
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final ExpandableViewState createExpandableViewState() {
        return new ShelfState();
    }

    @Override // com.android.systemui.statusbar.notification.row.ActivatableNotificationView, com.android.systemui.statusbar.notification.row.ExpandableView, com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        super.dump(DumpUtilsKt.asIndenting(printWriter), strArr);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x006d, code lost:
    
        if (r14 >= r2) goto L17;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00cc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public float getAmountInShelf(int r14, com.android.systemui.statusbar.notification.row.ExpandableView r15, boolean r16, boolean r17, boolean r18, float r19) {
        /*
            Method dump skipped, instructions count: 490
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.NotificationShelf.getAmountInShelf(int, com.android.systemui.statusbar.notification.row.ExpandableView, boolean, boolean, boolean, float):float");
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final void getBoundsOnScreen(Rect rect, boolean z) {
        super.getBoundsOnScreen(rect, z);
        float f = this.mActualWidth;
        int width = f > -1.0f ? (int) f : getWidth();
        if (isLayoutRtl()) {
            rect.left = rect.right - width;
        } else {
            rect.right = rect.left + width;
        }
    }

    @Override // com.android.systemui.statusbar.notification.row.ActivatableNotificationView
    public final View getContentView() {
        return this.mShelfIcons;
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView, android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    public boolean isXInView(float f, float f2, float f3, float f4) {
        return f3 - f2 <= f && f < f4 + f2;
    }

    public boolean isYInView(float f, float f2, float f3, float f4) {
        return f3 - f2 <= f && f < f4 + f2;
    }

    @Override // com.android.systemui.statusbar.notification.row.ActivatableNotificationView
    public final boolean needsOutline() {
        return !this.mHideBackground && super.needsOutline();
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView, android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateResources$3();
    }

    @Override // com.android.systemui.statusbar.notification.row.ActivatableNotificationView, android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        NotificationIconContainer notificationIconContainer = (NotificationIconContainer) findViewById(R.id.content);
        this.mShelfIcons = notificationIconContainer;
        notificationIconContainer.setClipChildren(false);
        this.mShelfIcons.setClipToPadding(false);
        this.mClipToActualHeight = false;
        updateClipping$1();
        setClipChildren(false);
        setClipToPadding(false);
        this.mShelfIcons.mIsStaticLayout = false;
        requestRoundness(1.0f, 1.0f, BASE_VALUE, false);
        updateResources$3();
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (this.mInteractive) {
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_EXPAND);
            accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, getContext().getString(R.string.accessibility_overflow_action)));
        }
    }

    @Override // com.android.systemui.statusbar.notification.row.ActivatableNotificationView, com.android.systemui.statusbar.notification.row.ExpandableView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int i5 = getResources().getDisplayMetrics().heightPixels;
        this.mClipRect.set(0, -i5, getWidth(), i5);
        NotificationIconContainer notificationIconContainer = this.mShelfIcons;
        if (notificationIconContainer != null) {
            notificationIconContainer.setClipBounds(this.mClipRect);
        }
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final boolean pointInView(float f, float f2, float f3) {
        float width = getWidth();
        float f4 = this.mActualWidth;
        float width2 = f4 > -1.0f ? (int) f4 : getWidth();
        float f5 = isLayoutRtl() ? width - width2 : 0.0f;
        if (!isLayoutRtl()) {
            width = width2;
        }
        return isXInView(f, f3, f5, width) && isYInView(f2, f3, (float) this.mClipTopAmount, (float) this.mActualHeight);
    }

    @Override // com.android.systemui.statusbar.notification.row.ActivatableNotificationView, com.android.systemui.statusbar.notification.row.ExpandableView
    public final void setFakeShadowIntensity(float f, float f2, int i, int i2) {
        if (!this.mHasItemsInStableShelf) {
            f = 0.0f;
        }
        super.setFakeShadowIntensity(f, f2, i, i2);
    }

    @Override // android.view.View
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" (hideBackground=");
        sb.append(this.mHideBackground);
        sb.append(" notGoneIndex=");
        sb.append(this.mNotGoneIndex);
        sb.append(" hasItemsInStableShelf=");
        sb.append(this.mHasItemsInStableShelf);
        sb.append(" interactive=");
        sb.append(this.mInteractive);
        sb.append(" animationsEnabled=");
        sb.append(this.mAnimationsEnabled);
        sb.append(" showNotificationShelf=");
        sb.append(this.mShowNotificationShelf);
        sb.append(" indexOfFirstViewInShelf=");
        return BackEventCompat$$ExternalSyntheticOutline0.m(sb, this.mIndexOfFirstViewInShelf, ')');
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v4, types: [android.view.ViewTreeObserver$OnPreDrawListener, com.android.systemui.statusbar.NotificationShelf$1, java.lang.Object] */
    public final void updateAppearance() {
        float f;
        int i;
        ExpandableView expandableView;
        boolean z;
        boolean z2;
        float f2;
        int i2;
        float f3;
        int i3;
        float f4;
        int i4;
        if (this.mShowNotificationShelf) {
            this.mShelfIcons.resetViewStates();
            float translationY = getTranslationY();
            AmbientState ambientState = this.mAmbientState;
            ExpandableView expandableView2 = ambientState.mLastVisibleBackgroundChild;
            this.mNotGoneIndex = -1;
            int i5 = 0;
            boolean z3 = ambientState.mCurrentScrollVelocity > ((float) this.mScrollFastThreshold) || (ambientState.mExpansionChanging && Math.abs(ambientState.mExpandingVelocity) > ((float) this.mScrollFastThreshold));
            AmbientState ambientState2 = this.mAmbientState;
            boolean z4 = ambientState2.mExpansionChanging && !ambientState2.mPanelTracking;
            int i6 = 0;
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            int i11 = 0;
            float f5 = 0.0f;
            float f6 = 0.0f;
            while (i10 < this.mHostLayout.getChildCount()) {
                ExpandableView expandableView3 = (ExpandableView) this.mHostLayout.getChildAt(i10);
                if (!expandableView3.needsClippingToShelf() || expandableView3.getVisibility() == 8) {
                    i = i10;
                    expandableView = expandableView2;
                    z = z3;
                    z2 = z4;
                    f2 = f5;
                    i2 = i5;
                    i6 = i6;
                    i8 = i8;
                    i7 = i7;
                    f6 = f6;
                    i9 = i9;
                } else {
                    int i12 = ((((ValueAnimator) expandableView3.getTag(ViewState.TAG_ANIMATOR_TRANSLATION_Z)) == null ? expandableView3.getTranslationZ() : ((Float) expandableView3.getTag(ViewState.TAG_END_TRANSLATION_Z)).floatValue()) > ((float) i5) || expandableView3.isPinned()) ? 1 : i5;
                    int i13 = expandableView3 == expandableView2 ? 1 : i5;
                    float translationY2 = expandableView3.getTranslationY();
                    expandableView = expandableView2;
                    float f7 = f5;
                    int i14 = i6;
                    int i15 = i7;
                    int i16 = i8;
                    boolean z5 = z3;
                    z = z3;
                    float f8 = f6;
                    boolean z6 = z4;
                    z2 = z4;
                    int i17 = i9;
                    i = i10;
                    float amountInShelf = getAmountInShelf(i10, expandableView3, z5, z6, i13, getTranslationY() - this.mPaddingBetweenElements);
                    i9 = Math.max(updateNotificationClipHeight(expandableView3, i12 != 0 ? getHeight() + translationY : translationY - this.mPaddingBetweenElements, i16), i17);
                    if (expandableView3 instanceof ExpandableNotificationRow) {
                        ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) expandableView3;
                        f6 = f8 + amountInShelf;
                        i7 = expandableNotificationRow.mNormalColor;
                        if (translationY2 < translationY || this.mNotGoneIndex != -1) {
                            f4 = f7;
                            i4 = i14;
                            if (this.mNotGoneIndex == -1) {
                                f4 = amountInShelf;
                                i4 = i15;
                            }
                        } else {
                            this.mNotGoneIndex = i16;
                            if (i15 != this.mBgTint) {
                                this.mBgTint = i15;
                                updateBackgroundTint(false);
                            }
                            i4 = i14;
                            this.mOverrideTint = i4;
                            f4 = f7;
                            this.mOverrideAmount = f4;
                            setBackgroundTintColor(calculateBgColor(true));
                        }
                        if (i13 != 0 && this.mCanModifyColorOfNotifications && this.mAmbientState.mShadeExpanded) {
                            int i18 = i11 == 0 ? i7 : i11;
                            expandableNotificationRow.mOverrideTint = i18;
                            expandableNotificationRow.mOverrideAmount = amountInShelf;
                            expandableNotificationRow.setBackgroundTintColor(expandableNotificationRow.calculateBgColor(true));
                            i11 = i18;
                        } else {
                            expandableNotificationRow.mOverrideTint = 0;
                            expandableNotificationRow.mOverrideAmount = 0.0f;
                            expandableNotificationRow.setBackgroundTintColor(expandableNotificationRow.calculateBgColor(true));
                            i11 = i7;
                        }
                        if (i16 != 0 || i12 == 0) {
                            expandableNotificationRow.setAboveShelf(false);
                        }
                        f3 = f4;
                        int i19 = i4;
                        i8 = i16 + 1;
                        i6 = i19;
                    } else {
                        i6 = i14;
                        f3 = f7;
                        i8 = i16;
                        i7 = i15;
                        f6 = f8;
                    }
                    if (expandableView3 instanceof ActivatableNotificationView) {
                        ActivatableNotificationView activatableNotificationView = (ActivatableNotificationView) expandableView3;
                        boolean z7 = !this.mAmbientState.isOnKeyguard() && !this.mAmbientState.mShadeExpanded && (activatableNotificationView instanceof ExpandableNotificationRow) && ((ExpandableNotificationRow) activatableNotificationView).mIsHeadsUp;
                        AmbientState ambientState3 = this.mAmbientState;
                        boolean z8 = ambientState3.mShadeExpanded && activatableNotificationView == ambientState3.getTrackedHeadsUpRow();
                        if (translationY2 < translationY) {
                            NotificationRoundnessManager notificationRoundnessManager = this.mRoundnessManager;
                            if (activatableNotificationView != notificationRoundnessManager.mSwipedView && activatableNotificationView != notificationRoundnessManager.mViewBeforeSwipedView && activatableNotificationView != notificationRoundnessManager.mViewAfterSwipedView && !z7 && !z8 && !activatableNotificationView.isAboveShelf()) {
                                AmbientState ambientState4 = this.mAmbientState;
                                if (!ambientState4.mPulsing && !ambientState4.mDozing) {
                                    float f9 = translationY2 + activatableNotificationView.mActualHeight;
                                    float f10 = this.mCornerAnimationDistance * ambientState4.mExpansionFraction;
                                    float f11 = translationY - f10;
                                    float saturate = translationY2 >= f11 ? MathUtils.saturate((translationY2 - f11) / f10) : 0.0f;
                                    SourceType$Companion$from$1 sourceType$Companion$from$1 = SHELF_SCROLL;
                                    i3 = 0;
                                    activatableNotificationView.requestTopRoundness(saturate, sourceType$Companion$from$1, false);
                                    activatableNotificationView.requestBottomRoundness(f9 >= f11 ? MathUtils.saturate((f9 - f11) / f10) : 0.0f, sourceType$Companion$from$1, false);
                                    f2 = f3;
                                    i2 = i3;
                                }
                            }
                        }
                    }
                    i3 = 0;
                    f2 = f3;
                    i2 = i3;
                }
                i5 = i2;
                f5 = f2;
                i10 = i + 1;
                expandableView2 = expandableView;
                z3 = z;
                z4 = z2;
            }
            int i20 = i8;
            float f12 = f6;
            int i21 = i9;
            int i22 = i5;
            for (int i23 = i22; i23 < this.mHostLayout.getTransientViewCount(); i23++) {
                View transientView = this.mHostLayout.getTransientView(i23);
                if (transientView instanceof ExpandableView) {
                    updateNotificationClipHeight((ExpandableView) transientView, getTranslationY(), -1);
                }
            }
            setClipTopAmount(i21);
            boolean z9 = (this.mViewState.hidden || i21 >= getHeight() || !this.mShowNotificationShelf || f12 < 1.0f) ? 1 : i22;
            float interpolation = ((PathInterpolator) Interpolators.STANDARD).getInterpolation(this.mAmbientState.mFractionToShade);
            if (this.mAmbientState.isOnKeyguard()) {
                float min = MathUtils.min(f12, this.mMaxIconsOnLockscreen + 1);
                NotificationIconContainer notificationIconContainer = this.mShelfIcons;
                if (min == 0.0f) {
                    notificationIconContainer.getClass();
                    f = 0.0f;
                } else {
                    float actualPaddingStart = notificationIconContainer.getActualPaddingStart() + (notificationIconContainer.mIconSize * min);
                    float f13 = notificationIconContainer.mActualPaddingEnd;
                    if (f13 == -2.1474836E9f) {
                        f13 = notificationIconContainer.getPaddingEnd();
                    }
                    f = f13 + actualPaddingStart;
                }
                float lerp = MathUtils.lerp(f, getWidth(), interpolation);
                int i24 = (int) lerp;
                NotificationBackgroundView notificationBackgroundView = this.mBackgroundNormal;
                if (notificationBackgroundView != null) {
                    notificationBackgroundView.mActualWidth = i24;
                }
                NotificationIconContainer notificationIconContainer2 = this.mShelfIcons;
                if (notificationIconContainer2 != null) {
                    notificationIconContainer2.mActualLayoutWidth = i24;
                }
                this.mActualWidth = lerp;
            } else {
                float width = getWidth();
                int i25 = (int) width;
                NotificationBackgroundView notificationBackgroundView2 = this.mBackgroundNormal;
                if (notificationBackgroundView2 != null) {
                    notificationBackgroundView2.mActualWidth = i25;
                }
                NotificationIconContainer notificationIconContainer3 = this.mShelfIcons;
                if (notificationIconContainer3 != null) {
                    notificationIconContainer3.mActualLayoutWidth = i25;
                }
                this.mActualWidth = width;
            }
            setVisibility(z9 != 0 ? 4 : i22);
            this.mShelfIcons.calculateIconXTranslations();
            this.mShelfIcons.applyIconStates();
            for (int i26 = i22; i26 < this.mHostLayout.getChildCount(); i26++) {
                ExpandableView expandableView4 = (ExpandableView) this.mHostLayout.getChildAt(i26);
                if (expandableView4 instanceof ExpandableNotificationRow) {
                    final ExpandableNotificationRow expandableNotificationRow2 = (ExpandableNotificationRow) expandableView4;
                    if (expandableView4.getVisibility() != 8) {
                        final StatusBarIconView statusBarIconView = expandableNotificationRow2.mEntry.mIcons.mShelfIcon;
                        int i27 = (statusBarIconView.getTag(ViewState.TAG_ANIMATOR_TRANSLATION_Y) == null || this.mAmbientState.mDozing) ? i22 : 1;
                        int i28 = statusBarIconView.getTag(R.id.continuous_clipping_tag) != null ? 1 : i22;
                        if (i27 != 0 && i28 == 0) {
                            final ViewTreeObserver viewTreeObserver = statusBarIconView.getViewTreeObserver();
                            final ?? r9 = new ViewTreeObserver.OnPreDrawListener() { // from class: com.android.systemui.statusbar.NotificationShelf.1
                                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                                public final boolean onPreDraw() {
                                    if (!(statusBarIconView.getTag(ViewState.TAG_ANIMATOR_TRANSLATION_Y) != null)) {
                                        if (viewTreeObserver.isAlive()) {
                                            viewTreeObserver.removeOnPreDrawListener(this);
                                        }
                                        StatusBarIconView statusBarIconView2 = statusBarIconView;
                                        Interpolator interpolator = NotificationShelf.ICON_ALPHA_INTERPOLATOR;
                                        statusBarIconView2.setTag(R.id.continuous_clipping_tag, null);
                                        return true;
                                    }
                                    NotificationShelf notificationShelf = NotificationShelf.this;
                                    ExpandableNotificationRow expandableNotificationRow3 = expandableNotificationRow2;
                                    Interpolator interpolator2 = NotificationShelf.ICON_ALPHA_INTERPOLATOR;
                                    notificationShelf.getClass();
                                    float translationY3 = expandableNotificationRow3.getTranslationY();
                                    if (notificationShelf.mClipTopAmount != 0) {
                                        translationY3 = Math.max(translationY3, notificationShelf.getTranslationY() + notificationShelf.mClipTopAmount);
                                    }
                                    StatusBarIconView statusBarIconView3 = expandableNotificationRow3.mEntry.mIcons.mShelfIcon;
                                    float translationY4 = statusBarIconView3.getTranslationY() + notificationShelf.getTranslationY() + statusBarIconView3.getTop();
                                    if (translationY4 >= translationY3 || notificationShelf.mAmbientState.isFullyHidden()) {
                                        statusBarIconView3.setClipBounds(null);
                                    } else {
                                        int i29 = (int) (translationY3 - translationY4);
                                        statusBarIconView3.setClipBounds(new Rect(0, i29, statusBarIconView3.getWidth(), Math.max(i29, statusBarIconView3.getHeight())));
                                    }
                                    return true;
                                }
                            };
                            viewTreeObserver.addOnPreDrawListener(r9);
                            statusBarIconView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.android.systemui.statusbar.NotificationShelf.2
                                @Override // android.view.View.OnAttachStateChangeListener
                                public final void onViewDetachedFromWindow(View view) {
                                    if (view == StatusBarIconView.this) {
                                        if (viewTreeObserver.isAlive()) {
                                            viewTreeObserver.removeOnPreDrawListener(r9);
                                        }
                                        StatusBarIconView statusBarIconView2 = StatusBarIconView.this;
                                        Interpolator interpolator = NotificationShelf.ICON_ALPHA_INTERPOLATOR;
                                        statusBarIconView2.setTag(R.id.continuous_clipping_tag, null);
                                    }
                                }

                                @Override // android.view.View.OnAttachStateChangeListener
                                public final void onViewAttachedToWindow(View view) {
                                }
                            });
                            statusBarIconView.setTag(R.id.continuous_clipping_tag, r9);
                        }
                    }
                }
            }
            if (this.mHideBackground != z9) {
                this.mHideBackground = z9;
                if (!this.mCustomOutline) {
                    setOutlineProvider(needsOutline() ? this.mProvider : null);
                }
            }
            if (this.mNotGoneIndex == -1) {
                this.mNotGoneIndex = i20;
            }
        }
    }

    @Override // com.android.systemui.statusbar.notification.row.ActivatableNotificationView
    public final void updateBackgroundColors() {
        updateColors$1();
        this.mBackgroundNormal.setCustomBackground$1();
        updateBackgroundTint();
        int i = ColorUpdateLogger.$r8$clinit;
    }

    public final void updateInteractiveness() {
        boolean z = this.mCanInteract && this.mHasItemsInStableShelf;
        this.mInteractive = z;
        setClickable(z);
        setFocusable(this.mInteractive);
        setImportantForAccessibility(this.mInteractive ? 1 : 4);
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x002e, code lost:
    
        if (r4.mIsHeadsUpEntry != false) goto L16;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0066 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int updateNotificationClipHeight(com.android.systemui.statusbar.notification.row.ExpandableView r7, float r8, int r9) {
        /*
            r6 = this;
            float r0 = r7.getTranslationY()
            int r1 = r7.mActualHeight
            float r1 = (float) r1
            float r0 = r0 + r1
            boolean r1 = r7.isPinned()
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L16
            boolean r1 = r7.isHeadsUpAnimatingAway$1()
            if (r1 == 0) goto L31
        L16:
            com.android.systemui.statusbar.notification.stack.AmbientState r1 = r6.mAmbientState
            r1.getClass()
            boolean r4 = r7 instanceof com.android.systemui.statusbar.notification.row.ExpandableNotificationRow
            if (r4 == 0) goto L33
            r4 = r7
            com.android.systemui.statusbar.notification.row.ExpandableNotificationRow r4 = (com.android.systemui.statusbar.notification.row.ExpandableNotificationRow) r4
            boolean r5 = r1.mDozing
            if (r5 == 0) goto L33
            com.android.systemui.statusbar.notification.collection.NotificationEntry r4 = r4.mEntry
            boolean r1 = r1.mPulsing
            if (r1 == 0) goto L31
            boolean r1 = r4.mIsHeadsUpEntry
            if (r1 == 0) goto L31
            goto L33
        L31:
            r1 = r3
            goto L34
        L33:
            r1 = r2
        L34:
            com.android.systemui.statusbar.notification.stack.AmbientState r4 = r6.mAmbientState
            boolean r4 = r4.isPulseExpanding()
            if (r4 == 0) goto L41
            if (r9 != 0) goto L3f
            goto L45
        L3f:
            r2 = r3
            goto L45
        L41:
            boolean r2 = r7.showingPulsing()
        L45:
            if (r1 != 0) goto L5d
            int r9 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r9 <= 0) goto L5a
            if (r2 != 0) goto L5a
            boolean r9 = r6.mEnableNotificationClipping
            if (r9 == 0) goto L55
            float r8 = r0 - r8
            int r8 = (int) r8
            goto L56
        L55:
            r8 = r3
        L56:
            r7.setClipBottomAmount(r8)
            goto L5d
        L5a:
            r7.setClipBottomAmount(r3)
        L5d:
            if (r2 == 0) goto L66
            float r6 = r6.getTranslationY()
            float r0 = r0 - r6
            int r6 = (int) r0
            return r6
        L66:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.NotificationShelf.updateNotificationClipHeight(com.android.systemui.statusbar.notification.row.ExpandableView, float, int):int");
    }

    public final void updateResources$3() {
        Resources resources = getResources();
        SystemBarUtils.getStatusBarHeight(((FrameLayout) this).mContext);
        this.mPaddingBetweenElements = resources.getDimensionPixelSize(R.dimen.notification_divider_height);
        this.mMaxIconsOnLockscreen = resources.getInteger(R.integer.max_notif_icons_on_lockscreen);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.notification_shelf_height);
        if (dimensionPixelOffset != layoutParams.height) {
            layoutParams.height = dimensionPixelOffset;
            setLayoutParams(layoutParams);
        }
        int dimensionPixelOffset2 = resources.getDimensionPixelOffset(R.dimen.shelf_icon_container_padding);
        this.mShelfIcons.setPadding(dimensionPixelOffset2, 0, dimensionPixelOffset2, 0);
        this.mScrollFastThreshold = resources.getDimensionPixelOffset(R.dimen.scroll_fast_threshold);
        this.mShowNotificationShelf = resources.getBoolean(R.bool.config_showNotificationShelf);
        this.mCornerAnimationDistance = resources.getDimensionPixelSize(R.dimen.notification_corner_animation_distance);
        this.mEnableNotificationClipping = resources.getBoolean(R.bool.notification_enable_clipping);
        this.mShelfIcons.mOverrideIconColor = true;
        if (this.mShowNotificationShelf) {
            return;
        }
        setVisibility(8);
    }

    public NotificationShelf(Context context, AttributeSet attributeSet, boolean z) {
        super(context, attributeSet);
        this.mAnimationsEnabled = true;
        this.mClipRect = new Rect();
        this.mIndexOfFirstViewInShelf = -1;
        this.mActualWidth = -1.0f;
        this.mShowNotificationShelf = z;
    }
}
