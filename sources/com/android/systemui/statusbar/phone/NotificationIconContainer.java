package com.android.systemui.statusbar.phone;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Icon;
import android.util.AttributeSet;
import android.util.Property;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import androidx.collection.ArrayMap;
import com.android.app.animation.Interpolators;
import com.android.internal.statusbar.StatusBarIcon;
import com.android.settingslib.Utils;
import com.android.systemui.statusbar.StatusBarIconView;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder$bindIsolatedIcon$2$2$1$1;
import com.android.systemui.statusbar.notification.stack.AnimationFilter;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.android.systemui.statusbar.phone.NotificationIconContainer;
import com.android.wm.shell.R;
import java.util.HashMap;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class NotificationIconContainer extends ViewGroup {
    public static final AnonymousClass1 ADD_ICON_PROPERTIES;
    public static final AnonymousClass1 DOT_ANIMATION_PROPERTIES;
    public static final AnonymousClass1 ICON_ANIMATION_PROPERTIES;
    public static final AnonymousClass1 UNISOLATION_PROPERTY;
    public static final AnonymousClass1 UNISOLATION_PROPERTY_OTHERS;
    public static final AnonymousClass1 sTempProperties;
    public final int[] mAbsolutePosition;
    public int mActualLayoutWidth;
    public final float mActualPaddingEnd;
    public final float mActualPaddingStart;
    public int mAddAnimationStartIndex;
    public boolean mAnimationsEnabled;
    public int mCannedAnimationStartIndex;
    public boolean mChangingViewPositions;
    public int mDotPadding;
    public IconState mFirstVisibleIconState;
    public int mIconSize;
    public final HashMap mIconStates;
    public boolean mIsShowingOverflowDot;
    public boolean mIsStaticLayout;
    public StatusBarIconView mIsolatedIcon;
    public NotificationIconContainerViewBinder$bindIsolatedIcon$2$2$1$1 mIsolatedIconAnimationEndRunnable;
    public StatusBarIconView mIsolatedIconForAnimation;
    public Rect mIsolatedIconLocation;
    public int mMaxIcons;
    public boolean mOverrideIconColor;
    public ArrayMap mReplacingIcons;
    public final int mSpeedBumpIndex;
    public int mStaticDotDiameter;
    public int mThemedTextColorPrimary;
    public boolean mUseIncreasedIconScale;
    public float mVisualOverflowStart;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.NotificationIconContainer$1, reason: invalid class name */
    public final class AnonymousClass1 extends AnimationProperties {
        public final /* synthetic */ int $r8$classId;
        public final AnimationFilter mAnimationFilter;

        public AnonymousClass1(int i) {
            this.$r8$classId = i;
            switch (i) {
                case 1:
                    AnimationFilter animationFilter = new AnimationFilter();
                    animationFilter.animateX = true;
                    animationFilter.animateY = true;
                    animationFilter.animateAlpha = true;
                    animationFilter.mAnimatedProperties.add(View.SCALE_X);
                    animationFilter.mAnimatedProperties.add(View.SCALE_Y);
                    this.mAnimationFilter = animationFilter;
                    break;
                case 2:
                    this.mAnimationFilter = new AnimationFilter();
                    break;
                case 3:
                    AnimationFilter animationFilter2 = new AnimationFilter();
                    animationFilter2.animateAlpha = true;
                    this.mAnimationFilter = animationFilter2;
                    break;
                case 4:
                    AnimationFilter animationFilter3 = new AnimationFilter();
                    animationFilter3.animateAlpha = true;
                    this.mAnimationFilter = animationFilter3;
                    break;
                case 5:
                    AnimationFilter animationFilter4 = new AnimationFilter();
                    animationFilter4.animateX = true;
                    this.mAnimationFilter = animationFilter4;
                    break;
                default:
                    AnimationFilter animationFilter5 = new AnimationFilter();
                    animationFilter5.animateX = true;
                    this.mAnimationFilter = animationFilter5;
                    break;
            }
        }

        @Override // com.android.systemui.statusbar.notification.stack.AnimationProperties
        public final AnimationFilter getAnimationFilter() {
            switch (this.$r8$classId) {
            }
            return this.mAnimationFilter;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class IconState extends ViewState {
        public boolean justReplaced;
        public final View mView;
        public boolean needsCannedAnimation;
        public boolean noAnimations;
        public int visibleState;
        public float iconAppearAmount = 1.0f;
        public float clampedAppearAmount = 1.0f;
        public boolean justAdded = true;
        public final NotificationIconContainer$IconState$$ExternalSyntheticLambda0 mCannedAnimationEndListener = new Consumer() { // from class: com.android.systemui.statusbar.phone.NotificationIconContainer$IconState$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                NotificationIconContainer.IconState iconState = NotificationIconContainer.IconState.this;
                Property property = (Property) obj;
                iconState.getClass();
                if (property == View.TRANSLATION_Y && iconState.iconAppearAmount == 0.0f && iconState.mView.getVisibility() == 0) {
                    iconState.mView.setVisibility(4);
                }
            }
        };

        /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.statusbar.phone.NotificationIconContainer$IconState$$ExternalSyntheticLambda0] */
        public IconState(View view) {
            this.mView = view;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.android.systemui.statusbar.notification.stack.ViewState
        public final void applyToView(View view) {
            boolean z;
            Consumer consumer;
            boolean z2;
            Object obj;
            android.util.ArrayMap arrayMap;
            boolean z3;
            AnimationProperties animationProperties;
            if (view instanceof StatusBarIconView) {
                StatusBarIconView statusBarIconView = (StatusBarIconView) view;
                int i = this.visibleState;
                boolean z4 = (i == 2 && statusBarIconView.mVisibleState == 1) || (i == 1 && statusBarIconView.mVisibleState == 2);
                AnonymousClass1 anonymousClass1 = NotificationIconContainer.DOT_ANIMATION_PROPERTIES;
                NotificationIconContainer notificationIconContainer = NotificationIconContainer.this;
                boolean z5 = ((!notificationIconContainer.mAnimationsEnabled && statusBarIconView != notificationIconContainer.mIsolatedIcon) || this.noAnimations || z4) ? false : true;
                AnimationProperties animationProperties2 = null;
                if (z5) {
                    if (this.justAdded || this.justReplaced) {
                        super.applyToView(statusBarIconView);
                        if (this.justAdded && this.iconAppearAmount != 0.0f) {
                            statusBarIconView.setAlpha(0.0f);
                            obj = null;
                            statusBarIconView.setVisibleState(2, false, null, 0L);
                            animationProperties2 = NotificationIconContainer.ADD_ICON_PROPERTIES;
                            z3 = true;
                            arrayMap = obj;
                        }
                        arrayMap = 0;
                        animationProperties2 = null;
                        z3 = false;
                    } else {
                        if (i != statusBarIconView.mVisibleState) {
                            obj = null;
                            animationProperties2 = NotificationIconContainer.DOT_ANIMATION_PROPERTIES;
                            z3 = true;
                            arrayMap = obj;
                        }
                        arrayMap = 0;
                        animationProperties2 = null;
                        z3 = false;
                    }
                    if (!z3 && notificationIconContainer.mAddAnimationStartIndex >= 0 && notificationIconContainer.indexOfChild(view) >= notificationIconContainer.mAddAnimationStartIndex && (statusBarIconView.mVisibleState != 2 || this.visibleState != 2)) {
                        animationProperties2 = NotificationIconContainer.DOT_ANIMATION_PROPERTIES;
                        z3 = true;
                    }
                    if (this.needsCannedAnimation) {
                        AnonymousClass1 anonymousClass12 = NotificationIconContainer.sTempProperties;
                        AnimationFilter animationFilter = anonymousClass12.mAnimationFilter;
                        animationFilter.reset();
                        AnonymousClass1 anonymousClass13 = NotificationIconContainer.ICON_ANIMATION_PROPERTIES;
                        animationFilter.combineFilter(anonymousClass13.mAnimationFilter);
                        anonymousClass12.mInterpolatorMap = arrayMap;
                        android.util.ArrayMap arrayMap2 = anonymousClass13.mInterpolatorMap;
                        if (arrayMap2 != null) {
                            anonymousClass12.mInterpolatorMap = new android.util.ArrayMap();
                            anonymousClass12.mInterpolatorMap.putAll(arrayMap2);
                        }
                        anonymousClass12.setCustomInterpolator(View.TRANSLATION_Y, statusBarIconView.mShowsConversation ? Interpolators.ICON_OVERSHOT_LESS : Interpolators.ICON_OVERSHOT);
                        anonymousClass12.mAnimationEndAction = this.mCannedAnimationEndListener;
                        if (animationProperties2 != null) {
                            animationFilter.combineFilter(animationProperties2.getAnimationFilter());
                            android.util.ArrayMap arrayMap3 = animationProperties2.mInterpolatorMap;
                            if (arrayMap3 != null) {
                                if (anonymousClass12.mInterpolatorMap == null) {
                                    anonymousClass12.mInterpolatorMap = new android.util.ArrayMap();
                                }
                                anonymousClass12.mInterpolatorMap.putAll(arrayMap3);
                            }
                        }
                        anonymousClass12.duration = 100L;
                        notificationIconContainer.mCannedAnimationStartIndex = notificationIconContainer.indexOfChild(view);
                        animationProperties2 = anonymousClass12;
                        z3 = true;
                    }
                    if (!z3 && notificationIconContainer.mCannedAnimationStartIndex >= 0 && notificationIconContainer.indexOfChild(view) > notificationIconContainer.mCannedAnimationStartIndex && (statusBarIconView.mVisibleState != 2 || this.visibleState != 2)) {
                        AnonymousClass1 anonymousClass14 = NotificationIconContainer.sTempProperties;
                        AnimationFilter animationFilter2 = anonymousClass14.mAnimationFilter;
                        animationFilter2.reset();
                        animationFilter2.animateX = true;
                        anonymousClass14.mInterpolatorMap = arrayMap;
                        anonymousClass14.duration = 100L;
                        animationProperties2 = anonymousClass14;
                        z3 = true;
                    }
                    StatusBarIconView statusBarIconView2 = notificationIconContainer.mIsolatedIconForAnimation;
                    if (statusBarIconView2 != null) {
                        if (view == statusBarIconView2) {
                            animationProperties = NotificationIconContainer.UNISOLATION_PROPERTY;
                            animationProperties.delay = notificationIconContainer.mIsolatedIcon != null ? 100L : 0L;
                            final NotificationIconContainerViewBinder$bindIsolatedIcon$2$2$1$1 notificationIconContainerViewBinder$bindIsolatedIcon$2$2$1$1 = notificationIconContainer.mIsolatedIconAnimationEndRunnable;
                            Consumer consumer2 = notificationIconContainerViewBinder$bindIsolatedIcon$2$2$1$1 == null ? arrayMap : new Consumer() { // from class: com.android.systemui.statusbar.phone.NotificationIconContainer$IconState$$ExternalSyntheticLambda1
                                @Override // java.util.function.Consumer
                                public final void accept(Object obj2) {
                                    NotificationIconContainer.IconState iconState = NotificationIconContainer.IconState.this;
                                    NotificationIconContainerViewBinder$bindIsolatedIcon$2$2$1$1 notificationIconContainerViewBinder$bindIsolatedIcon$2$2$1$12 = notificationIconContainerViewBinder$bindIsolatedIcon$2$2$1$1;
                                    notificationIconContainerViewBinder$bindIsolatedIcon$2$2$1$12.run();
                                    NotificationIconContainer notificationIconContainer2 = NotificationIconContainer.this;
                                    if (notificationIconContainer2.mIsolatedIconAnimationEndRunnable == notificationIconContainerViewBinder$bindIsolatedIcon$2$2$1$12) {
                                        notificationIconContainer2.mIsolatedIconAnimationEndRunnable = null;
                                    }
                                }
                            };
                            if (consumer2 != null) {
                                animationProperties.mAnimationEndAction = consumer2;
                                animationProperties.mAnimationCancelAction = consumer2;
                            }
                        } else {
                            animationProperties = NotificationIconContainer.UNISOLATION_PROPERTY_OTHERS;
                            animationProperties.delay = notificationIconContainer.mIsolatedIcon == null ? 100L : 0L;
                        }
                        animationProperties2 = animationProperties;
                        z2 = true;
                        consumer = arrayMap;
                    } else {
                        z2 = z3;
                        consumer = arrayMap;
                    }
                } else {
                    consumer = null;
                    z2 = false;
                }
                AnimationProperties animationProperties3 = animationProperties2;
                statusBarIconView.setVisibleState(this.visibleState, z5, null, 0L);
                if (notificationIconContainer.mOverrideIconColor) {
                    int i2 = notificationIconContainer.mThemedTextColorPrimary;
                    boolean z6 = this.needsCannedAnimation && z5;
                    if (statusBarIconView.mIconColor != i2) {
                        statusBarIconView.mIconColor = i2;
                        ValueAnimator valueAnimator = statusBarIconView.mColorAnimator;
                        if (valueAnimator != null) {
                            valueAnimator.cancel();
                        }
                        int i3 = statusBarIconView.mCurrentSetColor;
                        if (i3 != i2) {
                            if (!z6 || i3 == 0) {
                                statusBarIconView.mCurrentSetColor = i2;
                                statusBarIconView.updateIconColor();
                            } else {
                                statusBarIconView.mAnimationStartColor = i3;
                                ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
                                statusBarIconView.mColorAnimator = ofFloat;
                                ofFloat.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
                                statusBarIconView.mColorAnimator.setDuration(100L);
                                statusBarIconView.mColorAnimator.addUpdateListener(statusBarIconView.mColorUpdater);
                                statusBarIconView.mColorAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.statusbar.StatusBarIconView.3
                                    public AnonymousClass3() {
                                    }

                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                    public final void onAnimationEnd(Animator animator) {
                                        StatusBarIconView statusBarIconView3 = StatusBarIconView.this;
                                        statusBarIconView3.mColorAnimator = null;
                                        statusBarIconView3.mAnimationStartColor = 0;
                                    }
                                });
                                statusBarIconView.mColorAnimator.start();
                            }
                        }
                    }
                }
                if (z2) {
                    animateTo(statusBarIconView, animationProperties3);
                } else {
                    super.applyToView(view);
                }
                NotificationIconContainer.sTempProperties.mAnimationEndAction = consumer;
                z = false;
            } else {
                z = false;
            }
            this.justAdded = z;
            this.justReplaced = z;
            this.needsCannedAnimation = z;
        }

        @Override // com.android.systemui.statusbar.notification.stack.ViewState
        public final void initFrom(View view) {
            super.initFrom(view);
            if (view instanceof StatusBarIconView) {
            }
        }
    }

    static {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
        anonymousClass1.duration = 200L;
        DOT_ANIMATION_PROPERTIES = anonymousClass1;
        AnonymousClass1 anonymousClass12 = new AnonymousClass1(1);
        anonymousClass12.duration = 100L;
        ICON_ANIMATION_PROPERTIES = anonymousClass12;
        sTempProperties = new AnonymousClass1(2);
        AnonymousClass1 anonymousClass13 = new AnonymousClass1(3);
        anonymousClass13.duration = 200L;
        anonymousClass13.delay = 50L;
        ADD_ICON_PROPERTIES = anonymousClass13;
        AnonymousClass1 anonymousClass14 = new AnonymousClass1(4);
        anonymousClass14.duration = 110L;
        UNISOLATION_PROPERTY_OTHERS = anonymousClass14;
        AnonymousClass1 anonymousClass15 = new AnonymousClass1(5);
        anonymousClass15.duration = 110L;
        UNISOLATION_PROPERTY = anonymousClass15;
    }

    public NotificationIconContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mSpeedBumpIndex = -1;
        this.mMaxIcons = Integer.MAX_VALUE;
        this.mIsStaticLayout = true;
        this.mIconStates = new HashMap();
        this.mActualLayoutWidth = Integer.MIN_VALUE;
        this.mActualPaddingEnd = -2.1474836E9f;
        this.mActualPaddingStart = -2.1474836E9f;
        this.mAddAnimationStartIndex = -1;
        this.mCannedAnimationStartIndex = -1;
        this.mAnimationsEnabled = true;
        this.mAbsolutePosition = new int[2];
        initResources();
        setWillNotDraw(true);
    }

    public final void applyIconStates() {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            ViewState viewState = (ViewState) this.mIconStates.get(childAt);
            if (viewState != null) {
                viewState.applyToView(childAt);
            }
        }
        this.mAddAnimationStartIndex = -1;
        this.mCannedAnimationStartIndex = -1;
        this.mIsolatedIconForAnimation = null;
    }

    public final void calculateIconXTranslations() {
        IconState iconState;
        float actualPaddingStart = getActualPaddingStart();
        int childCount = getChildCount();
        int i = this.mMaxIcons;
        int i2 = this.mActualLayoutWidth;
        if (i2 == Integer.MIN_VALUE) {
            i2 = getWidth();
        }
        float f = i2;
        float f2 = this.mActualPaddingEnd;
        if (f2 == -2.1474836E9f) {
            f2 = getPaddingEnd();
        }
        float f3 = f - f2;
        this.mVisualOverflowStart = 0.0f;
        this.mFirstVisibleIconState = null;
        int i3 = -1;
        int i4 = 0;
        while (true) {
            boolean z = true;
            if (i4 >= childCount) {
                break;
            }
            View childAt = getChildAt(i4);
            IconState iconState2 = (IconState) this.mIconStates.get(childAt);
            float f4 = 1.0f;
            if (iconState2.iconAppearAmount == 1.0f) {
                iconState2.setXTranslation(actualPaddingStart);
            }
            if (this.mFirstVisibleIconState == null) {
                this.mFirstVisibleIconState = iconState2;
            }
            iconState2.visibleState = iconState2.hidden ? 2 : 0;
            if (!shouldForceOverflow(i4, this.mSpeedBumpIndex, iconState2.iconAppearAmount, i)) {
                if (!isOverflowing(i4 == childCount + (-1), actualPaddingStart, f3, this.mIconSize)) {
                    z = false;
                }
            }
            if (i3 == -1 && z) {
                this.mVisualOverflowStart = actualPaddingStart;
                i3 = i4;
            }
            if (this.mUseIncreasedIconScale && (childAt instanceof StatusBarIconView)) {
                f4 = ((StatusBarIconView) childAt).getIconScaleIncreased();
            }
            actualPaddingStart += iconState2.iconAppearAmount * childAt.getWidth() * f4;
            i4++;
        }
        this.mIsShowingOverflowDot = false;
        if (i3 != -1) {
            float f5 = this.mVisualOverflowStart;
            while (i3 < childCount) {
                IconState iconState3 = (IconState) this.mIconStates.get(getChildAt(i3));
                int i5 = this.mStaticDotDiameter + this.mDotPadding;
                iconState3.setXTranslation(f5);
                if (this.mIsShowingOverflowDot) {
                    iconState3.visibleState = 2;
                } else {
                    float f6 = iconState3.iconAppearAmount;
                    if (f6 < 0.8f) {
                        iconState3.visibleState = 0;
                    } else {
                        iconState3.visibleState = 1;
                        this.mIsShowingOverflowDot = true;
                    }
                    f5 = (i5 * f6) + f5;
                }
                i3++;
            }
        } else if (childCount > 0) {
            this.mFirstVisibleIconState = (IconState) this.mIconStates.get(getChildAt(0));
        }
        if (isLayoutRtl()) {
            for (int i6 = 0; i6 < childCount; i6++) {
                IconState iconState4 = (IconState) this.mIconStates.get(getChildAt(i6));
                iconState4.setXTranslation((getWidth() - iconState4.mXTranslation) - r2.getWidth());
            }
        }
        StatusBarIconView statusBarIconView = this.mIsolatedIcon;
        if (statusBarIconView == null || (iconState = (IconState) this.mIconStates.get(statusBarIconView)) == null) {
            return;
        }
        iconState.setXTranslation(this.mIsolatedIconLocation.left - this.mAbsolutePosition[0]);
        iconState.visibleState = 0;
    }

    public final float getActualPaddingStart() {
        float f = this.mActualPaddingStart;
        return f == -2.1474836E9f ? getPaddingStart() : f;
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    public final void initResources() {
        getResources().getInteger(R.integer.max_notif_icons_on_aod);
        getResources().getInteger(R.integer.max_notif_icons_on_lockscreen);
        getResources().getInteger(R.integer.max_notif_static_icons);
        this.mDotPadding = getResources().getDimensionPixelSize(R.dimen.overflow_icon_dot_padding);
        this.mStaticDotDiameter = getResources().getDimensionPixelSize(R.dimen.overflow_dot_radius) * 2;
        this.mThemedTextColorPrimary = Utils.getColorAttr(android.R.attr.textColorPrimary, new ContextThemeWrapper(getContext(), android.R.style.Theme.DeviceDefault.DayNight)).getDefaultColor();
    }

    public boolean isOverflowing(boolean z, float f, float f2, float f3) {
        return z ? f + f3 > f2 : (f3 * 2.0f) + f > f2;
    }

    public final boolean isReplacingIcon(View view) {
        StatusBarIcon statusBarIcon;
        if (!(view instanceof StatusBarIconView)) {
            return false;
        }
        StatusBarIconView statusBarIconView = (StatusBarIconView) view;
        Icon icon = statusBarIconView.mIcon.icon;
        String groupKey = statusBarIconView.mNotification.getGroupKey();
        ArrayMap arrayMap = this.mReplacingIcons;
        return (arrayMap == null || (statusBarIcon = (StatusBarIcon) arrayMap.get(groupKey)) == null || !icon.sameAs(statusBarIcon.icon)) ? false : true;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        initResources();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(-65536);
        paint.setStyle(Paint.Style.STROKE);
        float actualPaddingStart = getActualPaddingStart();
        int i = this.mActualLayoutWidth;
        if (i == Integer.MIN_VALUE) {
            i = getWidth();
        }
        float f = i;
        float f2 = this.mActualPaddingEnd;
        if (f2 == -2.1474836E9f) {
            f2 = getPaddingEnd();
        }
        canvas.drawRect(actualPaddingStart, 0.0f, f - f2, getHeight(), paint);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float height = getHeight() / 2.0f;
        this.mIconSize = 0;
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            View childAt = getChildAt(i5);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            int i6 = (int) (height - (measuredHeight / 2.0f));
            childAt.layout(0, i6, measuredWidth, measuredHeight + i6);
            if (i5 == 0) {
                setIconSize(childAt.getWidth());
            }
        }
        getLocationOnScreen(this.mAbsolutePosition);
        if (this.mIsStaticLayout) {
            resetViewStates();
            calculateIconXTranslations();
            applyIconStates();
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int childCount = getChildCount();
        int i3 = this.mMaxIcons;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i), 0);
        float actualPaddingStart = getActualPaddingStart();
        float f = this.mActualPaddingEnd;
        if (f == -2.1474836E9f) {
            f = getPaddingEnd();
        }
        int i4 = (int) (f + actualPaddingStart);
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            measureChild(childAt, makeMeasureSpec, i2);
            if (i5 <= i3) {
                i4 = childAt.getMeasuredWidth() + i4;
            }
        }
        setMeasuredDimension(ViewGroup.resolveSize(i4, i), View.MeasureSpec.getSize(i2));
    }

    @Override // android.view.ViewGroup
    public final void onViewAdded(View view) {
        super.onViewAdded(view);
        boolean isReplacingIcon = isReplacingIcon(view);
        if (!this.mChangingViewPositions) {
            IconState iconState = new IconState(view);
            if (isReplacingIcon) {
                iconState.justAdded = false;
                iconState.justReplaced = true;
            }
            this.mIconStates.put(view, iconState);
        }
        int indexOfChild = indexOfChild(view);
        if (indexOfChild < getChildCount() - 1 && !isReplacingIcon && ((IconState) this.mIconStates.get(getChildAt(indexOfChild + 1))).iconAppearAmount > 0.0f) {
            int i = this.mAddAnimationStartIndex;
            if (i < 0) {
                this.mAddAnimationStartIndex = indexOfChild;
            } else {
                this.mAddAnimationStartIndex = Math.min(i, indexOfChild);
            }
        }
        if (!(view instanceof StatusBarIconView) || this.mChangingViewPositions) {
            return;
        }
        ((StatusBarIconView) view).updateIconDimens();
    }

    @Override // android.view.ViewGroup
    public final void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if (view instanceof StatusBarIconView) {
            boolean isReplacingIcon = isReplacingIcon(view);
            StatusBarIconView statusBarIconView = (StatusBarIconView) view;
            if ((this.mAnimationsEnabled || statusBarIconView == this.mIsolatedIcon) && statusBarIconView.mVisibleState != 2 && view.getVisibility() == 0 && isReplacingIcon) {
                float translationX = statusBarIconView.getTranslationX();
                int i = 0;
                while (true) {
                    if (i >= getChildCount()) {
                        i = getChildCount();
                        break;
                    } else if (getChildAt(i).getTranslationX() > translationX) {
                        break;
                    } else {
                        i++;
                    }
                }
                int i2 = this.mAddAnimationStartIndex;
                if (i2 < 0) {
                    this.mAddAnimationStartIndex = i;
                } else {
                    this.mAddAnimationStartIndex = Math.min(i2, i);
                }
            }
            if (this.mChangingViewPositions) {
                return;
            }
            this.mIconStates.remove(view);
            if ((this.mAnimationsEnabled || statusBarIconView == this.mIsolatedIcon) && !isReplacingIcon) {
                addTransientView(statusBarIconView, 0);
                statusBarIconView.setVisibleState(2, true, new NotificationIconContainer$$ExternalSyntheticLambda0(this, statusBarIconView), view == this.mIsolatedIcon ? 110L : 0L);
            }
        }
    }

    public final void resetViewStates() {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            ViewState viewState = (ViewState) this.mIconStates.get(childAt);
            viewState.initFrom(childAt);
            StatusBarIconView statusBarIconView = this.mIsolatedIcon;
            viewState.setAlpha((statusBarIconView == null || childAt == statusBarIconView) ? 1.0f : 0.0f);
            viewState.hidden = false;
        }
    }

    public final void setAnimationsEnabled(boolean z) {
        if (!z && this.mAnimationsEnabled) {
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                ViewState viewState = (ViewState) this.mIconStates.get(childAt);
                if (viewState != null) {
                    viewState.cancelAnimations(childAt);
                    viewState.applyToView(childAt);
                }
            }
        }
        this.mAnimationsEnabled = z;
    }

    public void setIconSize(int i) {
        this.mIconSize = i;
    }

    public boolean shouldForceOverflow(int i, int i2, float f, int i3) {
        return i >= i3 && f > 0.0f;
    }

    @Override // android.view.View
    public final String toString() {
        return super.toString() + " { overrideIconColor=" + this.mOverrideIconColor + ", maxIcons=" + this.mMaxIcons + ", isStaticLayout=" + this.mIsStaticLayout + ", themedTextColorPrimary=#" + Integer.toHexString(this.mThemedTextColorPrimary) + " }";
    }
}
