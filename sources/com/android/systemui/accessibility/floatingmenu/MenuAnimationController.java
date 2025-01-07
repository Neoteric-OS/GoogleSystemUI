package com.android.systemui.accessibility.floatingmenu;

import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FlingAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.android.systemui.Prefs;
import java.util.HashMap;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MenuAnimationController {
    public PointF mAnimationEndPosition;
    public final ValueAnimator mFadeOutAnimator;
    public final Handler mHandler;
    public boolean mIsFadeEffectEnabled;
    public final MenuView mMenuView;
    final HashMap mPositionAnimations = new HashMap();
    final RadiiAnimator mRadiiAnimator;
    public MenuViewLayer$$ExternalSyntheticLambda0 mSpringAnimationsEndAction;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.accessibility.floatingmenu.MenuAnimationController$1, reason: invalid class name */
    public final class AnonymousClass1 {
        public AnonymousClass1() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MenuPositionProperty extends FloatPropertyCompat {
        public final DynamicAnimation.ViewProperty mProperty;

        public MenuPositionProperty(DynamicAnimation.ViewProperty viewProperty) {
            viewProperty.toString();
            this.mProperty = viewProperty;
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public final float getValue(Object obj) {
            return this.mProperty.getValue((MenuView) obj);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public final void setValue(Object obj, float f) {
            this.mProperty.setValue((MenuView) obj, f);
        }
    }

    public MenuAnimationController(final MenuView menuView, MenuViewAppearance menuViewAppearance) {
        this.mAnimationEndPosition = new PointF();
        this.mMenuView = menuView;
        Looper myLooper = Looper.myLooper();
        Objects.requireNonNull(myLooper, "looper must not be null");
        this.mHandler = new Handler(myLooper);
        ValueAnimator valueAnimator = new ValueAnimator();
        this.mFadeOutAnimator = valueAnimator;
        valueAnimator.setDuration(1000L);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.accessibility.floatingmenu.MenuAnimationController$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                MenuView.this.setAlpha(((Float) valueAnimator2.getAnimatedValue()).floatValue());
            }
        });
        this.mRadiiAnimator = new RadiiAnimator(menuViewAppearance.mRadii, new AnonymousClass1());
        this.mAnimationEndPosition = menuView.mMenuViewAppearance.getMenuPosition();
    }

    public static SpringForce createSpringForce() {
        SpringForce springForce = new SpringForce();
        springForce.setStiffness(700.0f);
        springForce.setDampingRatio(0.85f);
        return springForce;
    }

    public final void cancelAnimation(DynamicAnimation.ViewProperty viewProperty) {
        if (this.mPositionAnimations.containsKey(viewProperty)) {
            ((DynamicAnimation) this.mPositionAnimations.get(viewProperty)).cancel();
        }
    }

    public final void constrainPositionAndUpdate(PointF pointF, boolean z) {
        MenuView menuView = this.mMenuView;
        Rect menuDraggableBoundsWith = menuView.mMenuViewAppearance.getMenuDraggableBoundsWith(false);
        pointF.offset(-menuDraggableBoundsWith.left, -menuDraggableBoundsWith.top);
        float f = 0.0f;
        float f2 = pointF.x < ((float) menuDraggableBoundsWith.centerX()) ? 0.0f : 1.0f;
        if (pointF.y >= 0.0f && menuDraggableBoundsWith.height() != 0) {
            f = Math.min(1.0f, pointF.y / menuDraggableBoundsWith.height());
        }
        if (!z) {
            menuView.onEdgeChangedIfNeeded();
            return;
        }
        Position position = new Position(f2, f);
        MenuInfoRepository menuInfoRepository = menuView.mMenuViewModel.mInfoRepository;
        menuInfoRepository.mPercentagePosition = position;
        Prefs.get(menuInfoRepository.mContext).edit().putString("AccessibilityFloatingMenuPosition", position.toString()).apply();
        MenuViewAppearance menuViewAppearance = menuView.mMenuViewAppearance;
        Position position2 = menuViewAppearance.mPercentagePosition;
        position2.getClass();
        float f3 = position.mPercentageX;
        float f4 = position.mPercentageY;
        position2.mPercentageX = f3;
        position2.mPercentageY = f4;
        menuViewAppearance.mRadii = MenuViewAppearance.createRadii(menuViewAppearance.getMenuRadius(menuViewAppearance.mTargetFeaturesSize), menuViewAppearance.isMenuOnLeftSide());
        menuView.onEdgeChangedIfNeeded();
    }

    public FlingAnimation createFlingAnimation(MenuView menuView, MenuPositionProperty menuPositionProperty) {
        return new FlingAnimation(menuView, menuPositionProperty);
    }

    public final void fadeInNowIfEnabled() {
        if (this.mIsFadeEffectEnabled) {
            this.mFadeOutAnimator.cancel();
            this.mHandler.removeCallbacksAndMessages(null);
            this.mMenuView.setAlpha(1.0f);
        }
    }

    public final void fadeOutIfEnabled() {
        if (this.mIsFadeEffectEnabled) {
            this.mFadeOutAnimator.cancel();
            Handler handler = this.mHandler;
            handler.removeCallbacksAndMessages(null);
            ValueAnimator valueAnimator = this.mFadeOutAnimator;
            Objects.requireNonNull(valueAnimator);
            handler.postDelayed(new MenuAnimationController$$ExternalSyntheticLambda2(valueAnimator), 3000L);
        }
    }

    public final void flingMenuThenSpringToEdge(float f, float f2, float f3) {
        boolean z = false;
        if (!isOnLeftSide() ? f2 < -750.0f : f2 < 750.0f) {
            z = true;
        }
        Rect menuDraggableBounds = this.mMenuView.getMenuDraggableBounds();
        float f4 = z ? menuDraggableBounds.left : menuDraggableBounds.right;
        float f5 = (f4 - f) * 7.9799995f;
        flingThenSpringMenuWith(DynamicAnimation.TRANSLATION_X, z ? Math.min(f5, f2) : Math.max(f5, f2), createSpringForce(), Float.valueOf(f4));
        flingThenSpringMenuWith(DynamicAnimation.TRANSLATION_Y, f3, createSpringForce(), null);
    }

    public final void flingThenSpringMenuWith(final DynamicAnimation.ViewProperty viewProperty, float f, final SpringForce springForce, final Float f2) {
        MenuPositionProperty menuPositionProperty = new MenuPositionProperty(viewProperty);
        DynamicAnimation.ViewProperty viewProperty2 = menuPositionProperty.mProperty;
        MenuView menuView = this.mMenuView;
        float value = viewProperty2.getValue(menuView);
        Rect menuDraggableBounds = menuView.getMenuDraggableBounds();
        DynamicAnimation.AnonymousClass1 anonymousClass1 = DynamicAnimation.TRANSLATION_X;
        final float f3 = viewProperty.equals(anonymousClass1) ? menuDraggableBounds.left : menuDraggableBounds.top;
        final float f4 = viewProperty.equals(anonymousClass1) ? menuDraggableBounds.right : menuDraggableBounds.bottom;
        FlingAnimation createFlingAnimation = createFlingAnimation(menuView, menuPositionProperty);
        createFlingAnimation.setFriction(1.9f);
        createFlingAnimation.mVelocity = f;
        createFlingAnimation.mMinValue = Math.min(value, f3);
        createFlingAnimation.mMaxValue = Math.max(value, f4);
        createFlingAnimation.addEndListener(new DynamicAnimation.OnAnimationEndListener() { // from class: com.android.systemui.accessibility.floatingmenu.MenuAnimationController$$ExternalSyntheticLambda4
            @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
            public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f5, float f6) {
                float max;
                MenuAnimationController menuAnimationController = MenuAnimationController.this;
                menuAnimationController.getClass();
                if (z) {
                    return;
                }
                Float f7 = f2;
                if (f7 != null) {
                    max = f7.floatValue();
                } else {
                    max = Math.max(f3, Math.min(f4, f5));
                }
                menuAnimationController.springMenuWith(viewProperty, springForce, f6, max, true);
            }
        });
        cancelAnimation(viewProperty);
        this.mPositionAnimations.put(viewProperty, createFlingAnimation);
        if (f2 != null) {
            setAnimationEndPosition(viewProperty, f2);
        }
        createFlingAnimation.start();
    }

    public DynamicAnimation getAnimation(DynamicAnimation.ViewProperty viewProperty) {
        return (DynamicAnimation) this.mPositionAnimations.getOrDefault(viewProperty, null);
    }

    public final PointF getTuckedMenuPosition() {
        MenuView menuView = this.mMenuView;
        PointF menuPosition = menuView.mMenuViewAppearance.getMenuPosition();
        int i = menuView.mMenuViewAppearance.mSizeType;
        float f = ((i == 0 ? r0.mSmallIconSize : r0.mLargeIconSize) + ((i == 0 ? r0.mSmallPadding : r0.mLargePadding) * 2)) / 2.0f;
        return new PointF(isOnLeftSide() ? menuPosition.x - f : menuPosition.x + f, menuPosition.y);
    }

    public final boolean isOnLeftSide() {
        MenuView menuView = this.mMenuView;
        return menuView.getTranslationX() < ((float) menuView.getMenuDraggableBounds().centerX());
    }

    public final void moveAndPersistPosition(PointF pointF) {
        float f = pointF.x;
        MenuView menuView = this.mMenuView;
        menuView.setTranslationX(f);
        this.mAnimationEndPosition.x = f;
        float f2 = pointF.y;
        menuView.setTranslationY(f2);
        this.mAnimationEndPosition.y = f2;
        this.mAnimationEndPosition = pointF;
        menuView.mBoundsInParent.offsetTo((int) pointF.x, (int) pointF.y);
        constrainPositionAndUpdate(pointF, true);
    }

    public final void moveOutEdgeAndShow() {
        MenuView menuView = this.mMenuView;
        menuView.updateMenuMoveToTucked(false);
        PointF menuPosition = menuView.mMenuViewAppearance.getMenuPosition();
        DynamicAnimation.AnonymousClass1 anonymousClass1 = DynamicAnimation.TRANSLATION_X;
        SpringForce springForce = new SpringForce();
        springForce.setStiffness(700.0f);
        springForce.setDampingRatio(0.85f);
        springMenuWith(anonymousClass1, springForce, 0.0f, menuPosition.x, true);
        DynamicAnimation.AnonymousClass1 anonymousClass12 = DynamicAnimation.TRANSLATION_Y;
        SpringForce springForce2 = new SpringForce();
        springForce2.setStiffness(700.0f);
        springForce2.setDampingRatio(0.85f);
        springMenuWith(anonymousClass12, springForce2, 0.0f, menuPosition.y, true);
        menuView.onEdgeChangedIfNeeded();
    }

    public final void moveToEdgeAndHide() {
        MenuView menuView = this.mMenuView;
        menuView.updateMenuMoveToTucked(true);
        PointF menuPosition = menuView.mMenuViewAppearance.getMenuPosition();
        PointF tuckedMenuPosition = getTuckedMenuPosition();
        DynamicAnimation.AnonymousClass1 anonymousClass1 = DynamicAnimation.TRANSLATION_X;
        float signum = Math.signum(tuckedMenuPosition.x - menuPosition.x) * 750.0f;
        SpringForce springForce = new SpringForce();
        springForce.setStiffness(700.0f);
        springForce.setDampingRatio(0.85f);
        flingThenSpringMenuWith(anonymousClass1, signum, springForce, Float.valueOf(tuckedMenuPosition.x));
        menuView.mBoundsInParent.offsetTo((int) menuPosition.x, (int) menuPosition.y);
        fadeOutIfEnabled();
    }

    public final void setAnimationEndPosition(DynamicAnimation.ViewProperty viewProperty, Float f) {
        if (viewProperty.equals(DynamicAnimation.TRANSLATION_X)) {
            this.mAnimationEndPosition.x = f.floatValue();
        }
        if (viewProperty.equals(DynamicAnimation.TRANSLATION_Y)) {
            this.mAnimationEndPosition.y = f.floatValue();
        }
    }

    public void springMenuWith(DynamicAnimation.ViewProperty viewProperty, SpringForce springForce, float f, final float f2, final boolean z) {
        SpringAnimation springAnimation = new SpringAnimation(this.mMenuView, new MenuPositionProperty(viewProperty));
        springAnimation.mSpring = springForce;
        springAnimation.addEndListener(new DynamicAnimation.OnAnimationEndListener() { // from class: com.android.systemui.accessibility.floatingmenu.MenuAnimationController$$ExternalSyntheticLambda1
            @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
            public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z2, float f3, float f4) {
                MenuAnimationController menuAnimationController = MenuAnimationController.this;
                if (z2 || f3 != f2 || menuAnimationController.mPositionAnimations.values().stream().anyMatch(new MenuAnimationController$$ExternalSyntheticLambda5())) {
                    return;
                }
                MenuView menuView = menuAnimationController.mMenuView;
                PointF pointF = new PointF(menuView.getTranslationX(), menuView.getTranslationY());
                menuView.mBoundsInParent.offsetTo((int) pointF.x, (int) pointF.y);
                menuAnimationController.constrainPositionAndUpdate(pointF, z);
                MenuViewLayer$$ExternalSyntheticLambda0 menuViewLayer$$ExternalSyntheticLambda0 = menuAnimationController.mSpringAnimationsEndAction;
                if (menuViewLayer$$ExternalSyntheticLambda0 != null) {
                    menuViewLayer$$ExternalSyntheticLambda0.run();
                }
            }
        });
        springAnimation.mVelocity = f;
        cancelAnimation(viewProperty);
        this.mPositionAnimations.put(viewProperty, springAnimation);
        setAnimationEndPosition(viewProperty, Float.valueOf(f2));
        springAnimation.animateToFinalPosition(f2);
    }

    public final void startRadiiAnimation(float[] fArr) {
        RadiiAnimator radiiAnimator = this.mRadiiAnimator;
        if (radiiAnimator.mAnimationDriver.isStarted()) {
            radiiAnimator.mAnimationDriver.cancel();
            radiiAnimator.mStartValues = radiiAnimator.evaluate(radiiAnimator.mAnimationDriver.getAnimatedFraction());
        } else {
            radiiAnimator.mStartValues = radiiAnimator.mEndValues;
        }
        radiiAnimator.mEndValues = fArr;
        radiiAnimator.mAnimationDriver.start();
    }

    public final void startShrinkAnimation(Runnable runnable) {
        MenuView menuView = this.mMenuView;
        menuView.animate().cancel();
        menuView.animate().scaleX(0.0f).scaleY(0.0f).alpha(0.0f).translationY(menuView.getTranslationY()).withEndAction(runnable).start();
    }
}
