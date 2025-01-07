package com.android.systemui.statusbar.notification.row;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.util.IndentingPrintWriter;
import android.util.MathUtils;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import com.android.app.animation.Interpolators;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.settingslib.Utils;
import com.android.systemui.Gefingerpoken;
import com.android.systemui.shade.TouchLogger;
import com.android.systemui.statusbar.notification.FakeShadowView;
import com.android.systemui.statusbar.notification.NotificationUtils;
import com.android.systemui.statusbar.notification.RoundableState;
import com.android.systemui.statusbar.notification.SourceType$Companion$from$1;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.android.systemui.util.DumpUtilsKt;
import com.android.wm.shell.R;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ActivatableNotificationView extends ExpandableView {
    public static final Path EMPTY_PATH = new Path();
    public boolean mAlwaysRoundBothCorners;
    public float mAnimationTranslationY;
    public float mAppearAnimationFraction;
    public float mAppearAnimationTranslation;
    public ValueAnimator mAppearAnimator;
    public ValueAnimator mBackgroundColorAnimator;
    public NotificationBackgroundView mBackgroundNormal;
    public int mBgTint;
    public Interpolator mCurrentAppearInterpolator;
    public int mCurrentBackgroundTint;
    public boolean mCustomOutline;
    public boolean mDismissUsingRowTranslationX;
    public boolean mDismissed;
    public boolean mDrawingAppearAnimation;
    public FakeShadowView mFakeShadow;
    public boolean mIsHeadsUpAnimation;
    public long mLastActionUpTime;
    public int mNormalColor;
    public int mNormalRippleColor;
    public final Set mOnDetachResetRoundness;
    public float mOutlineAlpha;
    public final Rect mOutlineRect;
    public float mOverrideAmount;
    public int mOverrideTint;
    public final ExpandableOutlineView$1 mProvider;
    public boolean mRefocusOnDismiss;
    public RoundableState mRoundableState;
    public boolean mShadowHidden;
    public int mStartTint;
    public Point mTargetPoint;
    public int mTargetTint;
    public int mTintedRippleColor;
    public final float[] mTmpCornerRadii;
    public final Path mTmpPath;
    public Gefingerpoken mTouchHandler;

    /* renamed from: -$$Nest$mgetCujType, reason: not valid java name */
    public static int m877$$Nest$mgetCujType(ActivatableNotificationView activatableNotificationView, boolean z) {
        return activatableNotificationView.mIsHeadsUpAnimation ? z ? 12 : 13 : z ? 14 : 15;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v5, types: [android.view.ViewOutlineProvider, com.android.systemui.statusbar.notification.row.ExpandableOutlineView$1] */
    public ActivatableNotificationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mOutlineRect = new Rect();
        this.mOutlineAlpha = -1.0f;
        this.mTmpPath = new Path();
        this.mDismissUsingRowTranslationX = true;
        this.mTmpCornerRadii = new float[8];
        ?? r2 = new ViewOutlineProvider() { // from class: com.android.systemui.statusbar.notification.row.ExpandableOutlineView$1
            @Override // android.view.ViewOutlineProvider
            public final void getOutline(View view, Outline outline) {
                ActivatableNotificationView activatableNotificationView = ActivatableNotificationView.this;
                if (!activatableNotificationView.mCustomOutline && !activatableNotificationView.hasRoundedCorner()) {
                    ActivatableNotificationView activatableNotificationView2 = ActivatableNotificationView.this;
                    if (!activatableNotificationView2.mAlwaysRoundBothCorners) {
                        int translation = !activatableNotificationView2.mDismissUsingRowTranslationX ? (int) activatableNotificationView2.getTranslation() : 0;
                        int max = Math.max(translation, 0);
                        ActivatableNotificationView activatableNotificationView3 = ActivatableNotificationView.this;
                        int i = activatableNotificationView3.mClipTopAmount;
                        int min = Math.min(translation, 0) + activatableNotificationView3.getWidth();
                        ActivatableNotificationView activatableNotificationView4 = ActivatableNotificationView.this;
                        outline.setRect(max, i, min, Math.max(activatableNotificationView4.mActualHeight - activatableNotificationView4.mClipBottomAmount, i));
                        outline.setAlpha(ActivatableNotificationView.this.mOutlineAlpha);
                    }
                }
                Path clipPath = ActivatableNotificationView.this.getClipPath(false);
                if (clipPath != null) {
                    outline.setPath(clipPath);
                }
                outline.setAlpha(ActivatableNotificationView.this.mOutlineAlpha);
            }
        };
        this.mProvider = r2;
        setOutlineProvider(r2);
        initDimens$3();
        this.mOnDetachResetRoundness = new HashSet();
        this.mBgTint = 0;
        this.mAppearAnimationFraction = -1.0f;
        setClipChildren(false);
        setClipToPadding(false);
        updateColors$1();
    }

    @Override // com.android.systemui.statusbar.notification.Roundable
    public void applyRoundnessAndInvalidate() {
        float topCornerRadius = super.getTopCornerRadius();
        float bottomCornerRadius = super.getBottomCornerRadius();
        NotificationBackgroundView notificationBackgroundView = this.mBackgroundNormal;
        float[] fArr = notificationBackgroundView.mCornerRadii;
        if (topCornerRadius != fArr[0] || bottomCornerRadius != fArr[4]) {
            notificationBackgroundView.mBottomIsRounded = bottomCornerRadius != 0.0f;
            fArr[0] = topCornerRadius;
            fArr[1] = topCornerRadius;
            fArr[2] = topCornerRadius;
            fArr[3] = topCornerRadius;
            fArr[4] = bottomCornerRadius;
            fArr[5] = bottomCornerRadius;
            fArr[6] = bottomCornerRadius;
            fArr[7] = bottomCornerRadius;
            notificationBackgroundView.updateBackgroundRadii();
        }
        invalidateOutline();
        super.applyRoundnessAndInvalidate();
    }

    public final int calculateBgColor(boolean z) {
        if (z && this.mOverrideTint != 0) {
            return NotificationUtils.interpolateColors(calculateBgColor(false), this.mOverrideAmount, this.mOverrideTint);
        }
        int i = this.mBgTint;
        return i != 0 ? i : this.mNormalColor;
    }

    public boolean childNeedsClipping(View view) {
        return (view instanceof NotificationBackgroundView) && isClippingNeeded();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        if (this.mDrawingAppearAnimation) {
            canvas.save();
            canvas.translate(0.0f, this.mAppearAnimationTranslation);
        }
        super.dispatchDraw(canvas);
        if (this.mDrawingAppearAnimation) {
            canvas.restore();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        String simpleName = getClass().getSimpleName();
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        TouchLogger.Companion.logDispatchTouch(simpleName, motionEvent, dispatchTouchEvent);
        return dispatchTouchEvent;
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j) {
        Path path;
        canvas.save();
        Path path2 = null;
        if (childNeedsClipping(view)) {
            path = getCustomClipPath(view);
            if (path == null) {
                path = getClipPath(false);
            }
            if (this.mDismissUsingRowTranslationX && (view instanceof NotificationChildrenContainer)) {
                path2 = path;
                path = null;
            }
        } else {
            path = null;
        }
        if (view instanceof NotificationChildrenContainer) {
            NotificationChildrenContainer notificationChildrenContainer = (NotificationChildrenContainer) view;
            notificationChildrenContainer.mChildClipPath = path2;
            notificationChildrenContainer.invalidate();
        }
        if (path != null) {
            canvas.clipPath(path);
        }
        boolean drawChild = super.drawChild(canvas, view, j);
        canvas.restore();
        return drawChild;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        NotificationBackgroundView notificationBackgroundView = this.mBackgroundNormal;
        int[] drawableState = getDrawableState();
        Drawable drawable = notificationBackgroundView.mBackground;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        notificationBackgroundView.mBackground.setState(drawableState);
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView, com.android.systemui.Dumpable
    public void dump(PrintWriter printWriter, String[] strArr) {
        final IndentingPrintWriter asIndenting = DumpUtilsKt.asIndenting(DumpUtilsKt.asIndenting(printWriter));
        super.dump(asIndenting, strArr);
        DumpUtilsKt.withIncreasedIndent(asIndenting, new Runnable() { // from class: com.android.systemui.statusbar.notification.row.ExpandableOutlineView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                asIndenting.println(ActivatableNotificationView.this.mRoundableState.debugString());
            }
        });
    }

    public final void enableAppearDrawing(boolean z) {
        if (z != this.mDrawingAppearAnimation) {
            this.mDrawingAppearAnimation = z;
            if (!z) {
                setContentAlpha$1(1.0f);
                this.mAppearAnimationFraction = -1.0f;
                this.mCustomOutline = false;
                applyRoundnessAndInvalidate();
            }
            invalidate();
        }
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView, com.android.systemui.statusbar.notification.Roundable
    public final int getClipHeight() {
        return this.mCustomOutline ? this.mOutlineRect.height() : super.getClipHeight();
    }

    public final Path getClipPath(boolean z) {
        int i;
        int i2;
        int i3;
        int i4;
        float topCornerRadius = this.mAlwaysRoundBothCorners ? this.mRoundableState.maxRadius : super.getTopCornerRadius();
        if (this.mCustomOutline) {
            Rect rect = this.mOutlineRect;
            i = rect.left;
            i2 = rect.top;
            int i5 = rect.right;
            i3 = rect.bottom;
            i4 = i5;
        } else {
            int translation = (this.mDismissUsingRowTranslationX || z) ? 0 : (int) getTranslation();
            int i6 = (int) (this.mExtraWidthForClipping / 2.0f);
            i = Math.max(translation, 0) - i6;
            i2 = this.mClipTopAmount;
            i4 = Math.min(translation, 0) + getWidth() + i6;
            i3 = Math.max(this.mMinimumHeightForClipping, Math.max(this.mActualHeight - this.mClipBottomAmount, (int) (i2 + topCornerRadius)));
        }
        if (i3 - i2 == 0) {
            return EMPTY_PATH;
        }
        float bottomCornerRadius = this.mAlwaysRoundBothCorners ? this.mRoundableState.maxRadius : super.getBottomCornerRadius();
        Path path = this.mTmpPath;
        path.reset();
        float[] fArr = this.mTmpCornerRadii;
        fArr[0] = topCornerRadius;
        fArr[1] = topCornerRadius;
        fArr[2] = topCornerRadius;
        fArr[3] = topCornerRadius;
        fArr[4] = bottomCornerRadius;
        fArr[5] = bottomCornerRadius;
        fArr[6] = bottomCornerRadius;
        fArr[7] = bottomCornerRadius;
        path.addRoundRect(i, i2, i4, i3, fArr, Path.Direction.CW);
        return this.mTmpPath;
    }

    public abstract View getContentView();

    public Path getCustomClipPath(View view) {
        return null;
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public int getHeadsUpHeightWithoutHeader() {
        return getHeight();
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final float getOutlineAlpha() {
        return this.mOutlineAlpha;
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final int getOutlineTranslation() {
        if (this.mCustomOutline) {
            return this.mOutlineRect.left;
        }
        if (this.mDismissUsingRowTranslationX) {
            return 0;
        }
        return (int) getTranslation();
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView, com.android.systemui.statusbar.notification.Roundable
    public final RoundableState getRoundableState() {
        return this.mRoundableState;
    }

    public final void initDimens$3() {
        Resources resources = getResources();
        boolean z = resources.getBoolean(R.bool.config_clipNotificationsToOutline);
        this.mAlwaysRoundBothCorners = z;
        float dimension = z ? resources.getDimension(R.dimen.notification_shadow_radius) : resources.getDimensionPixelSize(R.dimen.notification_corner_radius);
        RoundableState roundableState = this.mRoundableState;
        if (roundableState == null) {
            this.mRoundableState = new RoundableState(this, this, dimension);
        } else if (roundableState.maxRadius != dimension) {
            roundableState.maxRadius = dimension;
            roundableState.roundable.applyRoundnessAndInvalidate();
        }
        setClipToOutline(this.mAlwaysRoundBothCorners);
    }

    public final boolean isClippingNeeded() {
        return this.mAlwaysRoundBothCorners || this.mCustomOutline || ((getTranslation() > 0.0f ? 1 : (getTranslation() == 0.0f ? 0 : -1)) != 0 && !this.mDismissUsingRowTranslationX);
    }

    public boolean needsOutline() {
        if (isChildInGroup()) {
            return isGroupExpanded() && !isGroupExpansionChanging();
        }
        if (isSummaryWithChildren()) {
            return !isGroupExpanded() || isGroupExpansionChanging();
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mOnDetachResetRoundness.isEmpty()) {
            return;
        }
        Iterator it = ((HashSet) this.mOnDetachResetRoundness).iterator();
        while (it.hasNext()) {
            requestRoundnessReset((SourceType$Companion$from$1) it.next());
        }
        this.mOnDetachResetRoundness.clear();
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mBackgroundNormal = (NotificationBackgroundView) findViewById(R.id.backgroundNormal);
        FakeShadowView fakeShadowView = (FakeShadowView) findViewById(R.id.fake_shadow);
        this.mFakeShadow = fakeShadowView;
        this.mShadowHidden = fakeShadowView.getVisibility() != 0;
        this.mBackgroundNormal.setCustomBackground$1();
        updateBackgroundTint();
        if (0.7f != this.mOutlineAlpha) {
            this.mOutlineAlpha = 0.7f;
            applyRoundnessAndInvalidate();
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        Gefingerpoken gefingerpoken = this.mTouchHandler;
        if (gefingerpoken == null || !gefingerpoken.onInterceptTouchEvent(motionEvent)) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        setPivotX(getWidth() / 2);
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final void performAddAnimation(long j, long j2, boolean z) {
        enableAppearDrawing(true);
        this.mIsHeadsUpAnimation = z;
        if (this.mDrawingAppearAnimation) {
            startAppearAnimation(true, z ? 0.0f : -1.0f, j, j2, null, null, null, ExpandableView.ClipSide.BOTTOM);
        }
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public long performRemoveAnimation(long j, float f, boolean z, Runnable runnable, Runnable runnable2, AnimatorListenerAdapter animatorListenerAdapter, ExpandableView.ClipSide clipSide) {
        enableAppearDrawing(true);
        this.mIsHeadsUpAnimation = z;
        if (this.mDrawingAppearAnimation) {
            startAppearAnimation(false, f, 0L, j, runnable, runnable2, animatorListenerAdapter, clipSide);
            return 0L;
        }
        if (runnable != null) {
            runnable.run();
        }
        runnable2.run();
        return 0L;
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public void setActualHeight(int i, boolean z) {
        int i2 = this.mActualHeight;
        super.setActualHeight(i, z);
        if (i2 != i) {
            applyRoundnessAndInvalidate();
        }
        setPivotY(i / 2);
        NotificationBackgroundView notificationBackgroundView = this.mBackgroundNormal;
        if (notificationBackgroundView.mExpandAnimationRunning) {
            return;
        }
        notificationBackgroundView.mActualHeight = i;
        notificationBackgroundView.invalidate();
    }

    public void setBackgroundTintColor(int i) {
        if (i != this.mCurrentBackgroundTint) {
            this.mCurrentBackgroundTint = i;
            if (i == this.mNormalColor) {
                i = 0;
            }
            this.mBackgroundNormal.setTint(i);
        }
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public void setClipBottomAmount(int i) {
        int i2 = this.mClipBottomAmount;
        super.setClipBottomAmount(i);
        if (i2 != i) {
            applyRoundnessAndInvalidate();
        }
        NotificationBackgroundView notificationBackgroundView = this.mBackgroundNormal;
        notificationBackgroundView.mClipBottomAmount = i;
        notificationBackgroundView.invalidate();
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public void setClipTopAmount(int i) {
        int i2 = this.mClipTopAmount;
        super.setClipTopAmount(i);
        if (i2 != i) {
            applyRoundnessAndInvalidate();
        }
        NotificationBackgroundView notificationBackgroundView = this.mBackgroundNormal;
        notificationBackgroundView.mClipTopAmount = i;
        notificationBackgroundView.invalidate();
    }

    public final void setContentAlpha$1(float f) {
        View contentView = getContentView();
        if (contentView.hasOverlappingRendering()) {
            contentView.setLayerType((f == 0.0f || f == 1.0f) ? 0 : 2, null);
        }
        contentView.setAlpha(f);
        if (f == 1.0f) {
            resetAllContentAlphas();
        }
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public void setFakeShadowIntensity(float f, float f2, int i, int i2) {
        boolean z = this.mShadowHidden;
        boolean z2 = f == 0.0f;
        this.mShadowHidden = z2;
        if (z2 && z) {
            return;
        }
        FakeShadowView fakeShadowView = this.mFakeShadow;
        float translationZ = (getTranslationZ() + 0.1f) * f;
        if (translationZ == 0.0f) {
            fakeShadowView.mFakeShadow.setVisibility(4);
            return;
        }
        fakeShadowView.mFakeShadow.setVisibility(0);
        fakeShadowView.mFakeShadow.setTranslationZ(Math.max(fakeShadowView.mShadowMinHeight, translationZ));
        fakeShadowView.mFakeShadow.setTranslationX(i2);
        fakeShadowView.mFakeShadow.setTranslationY(i - r4.getHeight());
        if (f2 != fakeShadowView.mOutlineAlpha) {
            fakeShadowView.mOutlineAlpha = f2;
            fakeShadowView.mFakeShadow.invalidateOutline();
        }
    }

    public final void setOutlineRect(float f, float f2, float f3, float f4) {
        this.mCustomOutline = true;
        this.mOutlineRect.set((int) f, (int) f2, (int) f3, (int) f4);
        this.mOutlineRect.bottom = (int) Math.max(f2, r6.bottom);
        this.mOutlineRect.right = (int) Math.max(f, r5.right);
        applyRoundnessAndInvalidate();
    }

    public final void startAppearAnimation(final boolean z, float f, long j, long j2, final Runnable runnable, final Runnable runnable2, AnimatorListenerAdapter animatorListenerAdapter, ExpandableView.ClipSide clipSide) {
        float f2;
        this.mAnimationTranslationY = f * this.mActualHeight;
        ValueAnimator valueAnimator = this.mAppearAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.mAppearAnimator = null;
        }
        if (this.mAppearAnimationFraction == -1.0f) {
            if (z) {
                this.mAppearAnimationFraction = 0.0f;
                this.mAppearAnimationTranslation = this.mAnimationTranslationY;
            } else {
                this.mAppearAnimationFraction = 1.0f;
                this.mAppearAnimationTranslation = 0.0f;
            }
        }
        if (z) {
            this.mCurrentAppearInterpolator = Interpolators.FAST_OUT_SLOW_IN;
            f2 = 1.0f;
        } else {
            this.mCurrentAppearInterpolator = Interpolators.FAST_OUT_SLOW_IN_REVERSE;
            f2 = 0.0f;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(this.mAppearAnimationFraction, f2);
        this.mAppearAnimator = ofFloat;
        ofFloat.setInterpolator(this.mCurrentAppearInterpolator);
        this.mAppearAnimator.setDuration((long) (Math.abs(this.mAppearAnimationFraction - f2) * j2));
        this.mAppearAnimator.addUpdateListener(new ActivatableNotificationView$$ExternalSyntheticLambda0(this, clipSide));
        if (animatorListenerAdapter != null) {
            this.mAppearAnimator.addListener(animatorListenerAdapter);
        }
        setContentAlpha$1(((PathInterpolator) Interpolators.ALPHA_IN).getInterpolation((MathUtils.constrain(this.mAppearAnimationFraction, 0.7f, 1.0f) - 0.7f) / 0.3f));
        float f3 = this.mAppearAnimationFraction;
        float f4 = (1.0f - f3) * this.mAnimationTranslationY;
        this.mAppearAnimationTranslation = f4;
        float f5 = this.mActualHeight;
        float f6 = f3 * f5;
        if (this.mTargetPoint != null) {
            int width = getWidth();
            float f7 = 1.0f - this.mAppearAnimationFraction;
            Point point = this.mTargetPoint;
            int i = point.x;
            float f8 = this.mAnimationTranslationY;
            setOutlineRect(i * f7, AndroidFlingSpline$$ExternalSyntheticOutline0.m(f8, point.y, f7, f8), width - ((width - i) * f7), f5 - ((r11 - r10) * f7));
        } else {
            setOutlineRect(0.0f, f4, getWidth(), f6 + this.mAppearAnimationTranslation);
        }
        this.mAppearAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.statusbar.notification.row.ActivatableNotificationView.2
            public boolean mRunWithoutInterruptions;

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                this.mRunWithoutInterruptions = false;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                Runnable runnable3 = runnable2;
                if (runnable3 != null) {
                    runnable3.run();
                }
                if (this.mRunWithoutInterruptions) {
                    ActivatableNotificationView.this.enableAppearDrawing(false);
                }
                ActivatableNotificationView.this.onAppearAnimationFinished(z);
                if (this.mRunWithoutInterruptions) {
                    InteractionJankMonitor.getInstance().end(ActivatableNotificationView.m877$$Nest$mgetCujType(ActivatableNotificationView.this, z));
                } else {
                    InteractionJankMonitor.getInstance().cancel(ActivatableNotificationView.m877$$Nest$mgetCujType(ActivatableNotificationView.this, z));
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
                Runnable runnable3 = runnable;
                if (runnable3 != null) {
                    runnable3.run();
                }
                this.mRunWithoutInterruptions = true;
                InteractionJankMonitor.getInstance().begin(InteractionJankMonitor.Configuration.Builder.withView(ActivatableNotificationView.m877$$Nest$mgetCujType(ActivatableNotificationView.this, z), ActivatableNotificationView.this));
            }
        });
        final ValueAnimator valueAnimator2 = this.mAppearAnimator;
        Choreographer.getInstance().postFrameCallbackDelayed(new Choreographer.FrameCallback() { // from class: com.android.systemui.statusbar.notification.row.ActivatableNotificationView$$ExternalSyntheticLambda2
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j3) {
                ActivatableNotificationView activatableNotificationView = ActivatableNotificationView.this;
                ValueAnimator valueAnimator3 = valueAnimator2;
                ValueAnimator valueAnimator4 = activatableNotificationView.mAppearAnimator;
                if (valueAnimator4 == valueAnimator3) {
                    valueAnimator4.start();
                }
            }
        }, j);
    }

    public abstract void updateBackgroundColors();

    public void updateBackgroundTint() {
        updateBackgroundTint(false);
    }

    public final void updateColors$1() {
        this.mNormalColor = Utils.getColorAttrDefaultColor(android.R.^attr-private.materialColorSurfaceContainerHigh, 0, ((FrameLayout) this).mContext);
        this.mTintedRippleColor = ((FrameLayout) this).mContext.getColor(R.color.notification_ripple_tinted_color);
        this.mNormalRippleColor = ((FrameLayout) this).mContext.getColor(R.color.notification_ripple_untinted_color);
        this.mBgTint = 0;
        this.mOverrideTint = 0;
        this.mOverrideAmount = 0.0f;
    }

    public final void updateBackgroundTint(boolean z) {
        ValueAnimator valueAnimator = this.mBackgroundColorAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        int i = this.mBgTint != 0 ? this.mTintedRippleColor : this.mNormalRippleColor;
        NotificationBackgroundView notificationBackgroundView = this.mBackgroundNormal;
        Drawable drawable = notificationBackgroundView.mBackground;
        if (drawable instanceof RippleDrawable) {
            ((RippleDrawable) drawable).setColor(ColorStateList.valueOf(i));
            notificationBackgroundView.mRippleColor = Integer.valueOf(i);
        } else {
            notificationBackgroundView.mRippleColor = null;
        }
        int calculateBgColor = calculateBgColor(true);
        if (!z) {
            setBackgroundTintColor(calculateBgColor);
            return;
        }
        int i2 = this.mCurrentBackgroundTint;
        if (calculateBgColor != i2) {
            this.mStartTint = i2;
            this.mTargetTint = calculateBgColor;
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            this.mBackgroundColorAnimator = ofFloat;
            ofFloat.addUpdateListener(new ActivatableNotificationView$$ExternalSyntheticLambda0(this));
            this.mBackgroundColorAnimator.setDuration(360L);
            this.mBackgroundColorAnimator.setInterpolator(Interpolators.LINEAR);
            this.mBackgroundColorAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.statusbar.notification.row.ActivatableNotificationView.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    ActivatableNotificationView.this.mBackgroundColorAnimator = null;
                }
            });
            this.mBackgroundColorAnimator.start();
        }
    }

    public void onAppearAnimationFinished(boolean z) {
    }

    public void resetAllContentAlphas() {
    }
}
