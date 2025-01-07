package com.android.systemui.navigationbar.views;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.inputmethodservice.InputMethodService;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowManagerGlobal;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import com.android.app.animation.Interpolators;
import com.android.internal.view.RotationPolicy;
import com.android.settingslib.Utils;
import com.android.systemui.accessibility.SystemActions$$ExternalSyntheticLambda1;
import com.android.systemui.model.SysUiState;
import com.android.systemui.navigationbar.ScreenPinningNotify;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler;
import com.android.systemui.navigationbar.views.NavigationBar;
import com.android.systemui.navigationbar.views.buttons.ButtonDispatcher;
import com.android.systemui.navigationbar.views.buttons.ButtonInterface;
import com.android.systemui.navigationbar.views.buttons.ContextualButton;
import com.android.systemui.navigationbar.views.buttons.ContextualButtonGroup;
import com.android.systemui.navigationbar.views.buttons.DeadZone;
import com.android.systemui.navigationbar.views.buttons.KeyButtonDrawable;
import com.android.systemui.plugins.NavigationEdgeBackPlugin;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.shade.domain.interactor.PanelExpansionInteractor;
import com.android.systemui.shared.rotation.FloatingRotationButton;
import com.android.systemui.shared.rotation.RotationButtonController;
import com.android.systemui.shared.rotation.RotationButtonController$$ExternalSyntheticLambda0;
import com.android.systemui.shared.system.QuickStepContract;
import com.android.systemui.shared.system.TaskStackChangeListeners;
import com.android.systemui.statusbar.phone.AutoHideController;
import com.android.wm.shell.R;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class NavigationBarView extends FrameLayout {
    public static final /* synthetic */ int $r8$clinit = 0;
    public AutoHideController mAutoHideController;
    public KeyButtonDrawable mBackIcon;
    public NavigationBarTransitions mBarTransitions;
    public Executor mBgExecutor;
    public final SparseArray mButtonDispatchers;
    public final Configuration mConfiguration;
    public final ContextualButtonGroup mContextualButtonGroup;
    public int mCurrentRotation;
    public View mCurrentView;
    public final int mDarkIconColor;
    public final DeadZone mDeadZone;
    public int mDisabledFlags;
    public DisplayTracker mDisplayTracker;
    public KeyButtonDrawable mDockedIcon;
    public EdgeBackGestureHandler mEdgeBackGestureHandler;
    public final FloatingRotationButton mFloatingRotationButton;
    public KeyButtonDrawable mHomeDefaultIcon;
    public View mHorizontal;
    public final boolean mImeCanRenderGesturalNavButtons;
    public boolean mImeDrawsImeNavBar;
    public boolean mInCarMode;
    public boolean mIsVertical;
    public boolean mLayoutTransitionsEnabled;
    public final Context mLightContext;
    public final int mLightIconColor;
    public int mNavBarMode;
    public int mNavigationIconHints;
    public NavigationBarInflaterView mNavigationInflaterView;
    public NavigationBar$$ExternalSyntheticLambda17 mOnVerticalChangedListener;
    public boolean mOverviewProxyEnabled;
    public PanelExpansionInteractor mPanelExpansionInteractor;
    public final NavigationBarView$$ExternalSyntheticLambda1 mPipListener;
    public final AnonymousClass1 mQuickStepAccessibilityDelegate;
    public KeyButtonDrawable mRecentIcon;
    public Optional mRecentsOptional;
    public final RotationButtonController mRotationButtonController;
    public final AnonymousClass2 mRotationButtonListener;
    public boolean mScreenOn;
    public boolean mScreenPinningActive;
    public final ScreenPinningNotify mScreenPinningNotify;
    public boolean mShowSwipeUpUi;
    public final Configuration mTmpLastConfiguration;
    public NavigationBar.AnonymousClass12 mTouchHandler;
    public final NavTransitionListener mTransitionListener;
    public NavigationBar$$ExternalSyntheticLambda17 mUpdateActiveTouchRegionsCallback;
    public View mVertical;
    public boolean mWakeAndUnlocking;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.navigationbar.views.NavigationBarView$2, reason: invalid class name */
    public final class AnonymousClass2 {
        public AnonymousClass2() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class NavTransitionListener implements LayoutTransition.TransitionListener {
        public boolean mBackTransitioning;
        public long mDuration;
        public boolean mHomeAppearing;
        public TimeInterpolator mInterpolator;
        public long mStartDelay;

        public NavTransitionListener() {
        }

        @Override // android.animation.LayoutTransition.TransitionListener
        public final void endTransition(LayoutTransition layoutTransition, ViewGroup viewGroup, View view, int i) {
            if (view.getId() == R.id.back) {
                this.mBackTransitioning = false;
            } else if (view.getId() == R.id.home && i == 2) {
                this.mHomeAppearing = false;
            }
        }

        @Override // android.animation.LayoutTransition.TransitionListener
        public final void startTransition(LayoutTransition layoutTransition, ViewGroup viewGroup, View view, int i) {
            if (view.getId() == R.id.back) {
                this.mBackTransitioning = true;
                return;
            }
            if (view.getId() == R.id.home && i == 2) {
                this.mHomeAppearing = true;
                this.mStartDelay = layoutTransition.getStartDelay(i);
                this.mDuration = layoutTransition.getDuration(i);
                this.mInterpolator = layoutTransition.getInterpolator(i);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.navigationbar.views.NavigationBarView$1] */
    /* JADX WARN: Type inference failed for: r1v4, types: [com.android.systemui.navigationbar.views.NavigationBarView$$ExternalSyntheticLambda1] */
    public NavigationBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCurrentView = null;
        this.mCurrentRotation = -1;
        this.mDisabledFlags = 0;
        this.mNavigationIconHints = 0;
        this.mTransitionListener = new NavTransitionListener();
        this.mLayoutTransitionsEnabled = true;
        this.mInCarMode = false;
        this.mScreenOn = true;
        SparseArray sparseArray = new SparseArray();
        this.mButtonDispatchers = sparseArray;
        this.mRecentsOptional = Optional.empty();
        this.mScreenPinningActive = false;
        this.mImeCanRenderGesturalNavButtons = InputMethodService.canImeRenderGesturalNavButtons();
        this.mQuickStepAccessibilityDelegate = new View.AccessibilityDelegate() { // from class: com.android.systemui.navigationbar.views.NavigationBarView.1
            public AccessibilityNodeInfo.AccessibilityAction mToggleOverviewAction;

            @Override // android.view.View.AccessibilityDelegate
            public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                if (this.mToggleOverviewAction == null) {
                    this.mToggleOverviewAction = new AccessibilityNodeInfo.AccessibilityAction(R.id.action_toggle_overview, NavigationBarView.this.getContext().getString(R.string.quick_step_accessibility_toggle_overview));
                }
                accessibilityNodeInfo.addAction(this.mToggleOverviewAction);
            }

            @Override // android.view.View.AccessibilityDelegate
            public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
                if (i != R.id.action_toggle_overview) {
                    return super.performAccessibilityAction(view, i, bundle);
                }
                NavigationBarView.this.mRecentsOptional.ifPresent(new SystemActions$$ExternalSyntheticLambda1());
                return true;
            }
        };
        this.mRotationButtonListener = new AnonymousClass2();
        this.mPipListener = new Consumer() { // from class: com.android.systemui.navigationbar.views.NavigationBarView$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                final NavigationBarView navigationBarView = NavigationBarView.this;
                final Rect rect = (Rect) obj;
                int i = NavigationBarView.$r8$clinit;
                navigationBarView.post(new Runnable() { // from class: com.android.systemui.navigationbar.views.NavigationBarView$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        NavigationBarView navigationBarView2 = NavigationBarView.this;
                        navigationBarView2.mEdgeBackGestureHandler.mPipExcludedBounds.set(rect);
                    }
                });
            }
        };
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, Utils.getThemeAttr(R.attr.darkIconTheme, context));
        ContextThemeWrapper contextThemeWrapper2 = new ContextThemeWrapper(context, Utils.getThemeAttr(R.attr.lightIconTheme, context));
        this.mLightContext = contextThemeWrapper2;
        int colorAttrDefaultColor = Utils.getColorAttrDefaultColor(R.attr.singleToneColor, 0, contextThemeWrapper2);
        this.mLightIconColor = colorAttrDefaultColor;
        int colorAttrDefaultColor2 = Utils.getColorAttrDefaultColor(R.attr.singleToneColor, 0, contextThemeWrapper);
        this.mDarkIconColor = colorAttrDefaultColor2;
        this.mIsVertical = false;
        ContextualButtonGroup contextualButtonGroup = new ContextualButtonGroup();
        this.mContextualButtonGroup = contextualButtonGroup;
        ContextualButton contextualButton = new ContextualButton(R.id.ime_switcher, android.R.drawable.ic_go, contextThemeWrapper2);
        ContextualButton contextualButton2 = new ContextualButton(R.id.accessibility_button, R.drawable.ic_sysbar_accessibility_button, contextThemeWrapper2);
        contextualButton.setVisibility(4);
        List list = contextualButtonGroup.mButtonData;
        ContextualButtonGroup.ButtonData buttonData = new ContextualButtonGroup.ButtonData();
        buttonData.button = contextualButton;
        buttonData.markedVisible = false;
        list.add(buttonData);
        contextualButton2.setVisibility(4);
        List list2 = contextualButtonGroup.mButtonData;
        ContextualButtonGroup.ButtonData buttonData2 = new ContextualButtonGroup.ButtonData();
        buttonData2.button = contextualButton2;
        buttonData2.markedVisible = false;
        list2.add(buttonData2);
        this.mFloatingRotationButton = new FloatingRotationButton(((FrameLayout) this).mContext);
        this.mRotationButtonController = new RotationButtonController(contextThemeWrapper2, colorAttrDefaultColor, colorAttrDefaultColor2, new NavigationBarView$$ExternalSyntheticLambda2(this));
        Configuration configuration = new Configuration();
        this.mConfiguration = configuration;
        this.mTmpLastConfiguration = new Configuration();
        configuration.updateFrom(context.getResources().getConfiguration());
        this.mScreenPinningNotify = new ScreenPinningNotify(((FrameLayout) this).mContext);
        sparseArray.put(R.id.back, new ButtonDispatcher(R.id.back));
        sparseArray.put(R.id.home, new ButtonDispatcher(R.id.home));
        sparseArray.put(R.id.home_handle, new ButtonDispatcher(R.id.home_handle));
        sparseArray.put(R.id.recent_apps, new ButtonDispatcher(R.id.recent_apps));
        sparseArray.put(R.id.ime_switcher, contextualButton);
        sparseArray.put(R.id.accessibility_button, contextualButton2);
        sparseArray.put(R.id.menu_container, contextualButtonGroup);
        this.mDeadZone = new DeadZone(this);
    }

    public static void dumpButton(PrintWriter printWriter, String str, ButtonDispatcher buttonDispatcher) {
        printWriter.print("      " + str + ": ");
        if (buttonDispatcher == null) {
            printWriter.print("null");
        } else {
            printWriter.print(visibilityToString(buttonDispatcher.getVisibility()) + " alpha=" + buttonDispatcher.getAlpha());
        }
        printWriter.println();
    }

    public static String visibilityToString(int i) {
        return i != 4 ? i != 8 ? "VISIBLE" : "GONE" : "INVISIBLE";
    }

    public final void abortCurrentGesture() {
        ButtonDispatcher homeButton = getHomeButton();
        int size = homeButton.mViews.size();
        for (int i = 0; i < size; i++) {
            if (homeButton.mViews.get(i) instanceof ButtonInterface) {
                ((ButtonInterface) homeButton.mViews.get(i)).abortCurrentGesture();
            }
        }
    }

    public final ButtonDispatcher getBackButton() {
        return (ButtonDispatcher) this.mButtonDispatchers.get(R.id.back);
    }

    public final KeyButtonDrawable getDrawable(int i) {
        return KeyButtonDrawable.create(this.mLightContext, this.mLightIconColor, this.mDarkIconColor, i, true);
    }

    public final ButtonDispatcher getHomeButton() {
        return (ButtonDispatcher) this.mButtonDispatchers.get(R.id.home);
    }

    public final ButtonDispatcher getRecentsButton() {
        return (ButtonDispatcher) this.mButtonDispatchers.get(R.id.recent_apps);
    }

    public final boolean isImeRenderingNavButtons() {
        return this.mImeDrawsImeNavBar && this.mImeCanRenderGesturalNavButtons && (this.mNavigationIconHints & 2) != 0;
    }

    public final boolean isOverviewEnabled() {
        return (this.mDisabledFlags & 16777216) == 0;
    }

    public boolean isRecentsButtonDisabled() {
        if (isOverviewEnabled()) {
            int displayId = getContext().getDisplayId();
            this.mDisplayTracker.getClass();
            if (displayId == 0) {
                return false;
            }
        }
        return true;
    }

    public final void notifyActiveTouchRegions() {
        NavigationBar$$ExternalSyntheticLambda17 navigationBar$$ExternalSyntheticLambda17 = this.mUpdateActiveTouchRegionsCallback;
        if (navigationBar$$ExternalSyntheticLambda17 != null) {
            NavigationBar navigationBar = navigationBar$$ExternalSyntheticLambda17.f$0;
            Region buttonLocations = navigationBar.getButtonLocations(true, true);
            OverviewProxyService overviewProxyService = navigationBar.mOverviewProxyService;
            overviewProxyService.mActiveNavBarRegion = buttonLocations;
            overviewProxyService.dispatchNavButtonBounds();
        }
    }

    @Override // android.view.View
    public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        int systemWindowInsetLeft = windowInsets.getSystemWindowInsetLeft();
        int systemWindowInsetRight = windowInsets.getSystemWindowInsetRight();
        setPadding(systemWindowInsetLeft, windowInsets.getSystemWindowInsetTop(), systemWindowInsetRight, windowInsets.getSystemWindowInsetBottom());
        EdgeBackGestureHandler edgeBackGestureHandler = this.mEdgeBackGestureHandler;
        edgeBackGestureHandler.mLeftInset = systemWindowInsetLeft;
        edgeBackGestureHandler.mRightInset = systemWindowInsetRight;
        NavigationEdgeBackPlugin navigationEdgeBackPlugin = edgeBackGestureHandler.mEdgeBackPlugin;
        if (navigationEdgeBackPlugin != null) {
            navigationEdgeBackPlugin.setInsets(systemWindowInsetLeft, systemWindowInsetRight);
        }
        boolean z = !QuickStepContract.isGesturalMode(this.mNavBarMode) || windowInsets.getSystemWindowInsetBottom() == 0;
        setClipChildren(z);
        setClipToPadding(z);
        return super.onApplyWindowInsets(windowInsets);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        requestApplyInsets();
        reorient();
        RotationButtonController rotationButtonController = this.mRotationButtonController;
        if (rotationButtonController != null && !rotationButtonController.mListenersRegistered && !rotationButtonController.mContext.getPackageManager().hasSystemFeature("android.hardware.type.pc")) {
            rotationButtonController.mListenersRegistered = true;
            rotationButtonController.mBgExecutor.execute(new RotationButtonController$$ExternalSyntheticLambda0(3, rotationButtonController));
            TaskStackChangeListeners.INSTANCE.registerTaskStackListener(rotationButtonController.mTaskStackListener);
        }
        updateNavButtonIcons();
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mTmpLastConfiguration.updateFrom(this.mConfiguration);
        int updateFrom = this.mConfiguration.updateFrom(configuration);
        FloatingRotationButton floatingRotationButton = this.mFloatingRotationButton;
        floatingRotationButton.getClass();
        if ((updateFrom & 4096) != 0 || (updateFrom & 1024) != 0) {
            floatingRotationButton.updateDimensionResources();
            if (floatingRotationButton.mIsShowing) {
                RotationButtonController rotationButtonController = floatingRotationButton.mRotationButtonController;
                floatingRotationButton.updateIcon(rotationButtonController.mLightIconColor, rotationButtonController.mDarkIconColor);
                floatingRotationButton.mWindowManager.updateViewLayout(floatingRotationButton.mKeyButtonContainer, floatingRotationButton.adjustViewPositionAndCreateLayoutParams());
                AnimatedVectorDrawable animatedVectorDrawable = floatingRotationButton.mAnimatedDrawable;
                if (animatedVectorDrawable != null) {
                    animatedVectorDrawable.reset();
                    floatingRotationButton.mAnimatedDrawable.start();
                }
            }
        }
        if ((updateFrom & 4) != 0) {
            floatingRotationButton.mKeyButtonView.setContentDescription(floatingRotationButton.mContext.getString(floatingRotationButton.mContentDescriptionResource));
        }
        Configuration configuration2 = this.mConfiguration;
        if (configuration2 != null) {
            boolean z = (configuration2.uiMode & 15) == 3;
            if (z != this.mInCarMode) {
                this.mInCarMode = z;
            }
        }
        updateIcons(this.mTmpLastConfiguration);
        updateRecentsIcon();
        updateCurrentRotation();
        Configuration configuration3 = this.mTmpLastConfiguration;
        if (configuration3.densityDpi == this.mConfiguration.densityDpi && configuration3.getLayoutDirection() == this.mConfiguration.getLayoutDirection()) {
            return;
        }
        updateNavButtonIcons();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        for (int i = 0; i < this.mButtonDispatchers.size(); i++) {
            ((ButtonDispatcher) this.mButtonDispatchers.valueAt(i)).getClass();
        }
        if (this.mRotationButtonController != null) {
            this.mFloatingRotationButton.hide();
            RotationButtonController rotationButtonController = this.mRotationButtonController;
            if (rotationButtonController.mListenersRegistered) {
                rotationButtonController.mListenersRegistered = false;
                rotationButtonController.mBgExecutor.execute(new RotationButtonController$$ExternalSyntheticLambda0(2, rotationButtonController));
                TaskStackChangeListeners.INSTANCE.unregisterTaskStackListener(rotationButtonController.mTaskStackListener);
            }
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        DeadZone deadZone = this.mDeadZone;
        if (deadZone.mUseDeadZone && deadZone.mShouldFlash && deadZone.mFlashFrac > 0.0f) {
            int size = (int) deadZone.getSize(SystemClock.uptimeMillis());
            if (!deadZone.mVertical) {
                canvas.clipRect(0, 0, canvas.getWidth(), size);
            } else if (deadZone.mDisplayRotation == 3) {
                canvas.clipRect(canvas.getWidth() - size, 0, canvas.getWidth(), canvas.getHeight());
            } else {
                canvas.clipRect(0, 0, size, canvas.getHeight());
            }
            canvas.drawARGB((int) (deadZone.mFlashFrac * 255.0f), 221, 238, 170);
        }
        super.onDraw(canvas);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        NavigationBarInflaterView navigationBarInflaterView = (NavigationBarInflaterView) findViewById(R.id.navigation_inflater);
        this.mNavigationInflaterView = navigationBarInflaterView;
        SparseArray sparseArray = this.mButtonDispatchers;
        navigationBarInflaterView.mButtonDispatchers = sparseArray;
        navigationBarInflaterView.clearDispatcherViews();
        for (int i = 0; i < sparseArray.size(); i++) {
            ButtonDispatcher buttonDispatcher = (ButtonDispatcher) sparseArray.valueAt(i);
            NavigationBarInflaterView.addAll(buttonDispatcher, (ViewGroup) navigationBarInflaterView.mHorizontal.findViewById(R.id.ends_group));
            NavigationBarInflaterView.addAll(buttonDispatcher, (ViewGroup) navigationBarInflaterView.mHorizontal.findViewById(R.id.center_group));
            NavigationBarInflaterView.addAll(buttonDispatcher, (ViewGroup) navigationBarInflaterView.mVertical.findViewById(R.id.ends_group));
            NavigationBarInflaterView.addAll(buttonDispatcher, (ViewGroup) navigationBarInflaterView.mVertical.findViewById(R.id.center_group));
        }
        this.mHorizontal = findViewById(R.id.horizontal);
        this.mVertical = findViewById(R.id.vertical);
        updateCurrentView();
        updateIcons(Configuration.EMPTY);
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.mTouchHandler.onInterceptTouchEvent(motionEvent) || super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        notifyActiveTouchRegions();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        boolean z = size > 0 && size2 > size && !QuickStepContract.isGesturalMode(this.mNavBarMode);
        if (z != this.mIsVertical) {
            this.mIsVertical = z;
            reorient();
            NavigationBar$$ExternalSyntheticLambda17 navigationBar$$ExternalSyntheticLambda17 = this.mOnVerticalChangedListener;
            if (navigationBar$$ExternalSyntheticLambda17 != null) {
                NavigationBar navigationBar = navigationBar$$ExternalSyntheticLambda17.f$0;
                if (((Optional) navigationBar.mCentralSurfacesOptionalLazy.get()).isPresent()) {
                    navigationBar.mShadeViewController.setQsScrimEnabled(true ^ z);
                }
            }
        }
        if (QuickStepContract.isGesturalMode(this.mNavBarMode)) {
            this.mBarTransitions.mBarBackground.mFrame = new Rect(0, getResources().getDimensionPixelSize(android.R.dimen.navigation_bar_width) - (this.mIsVertical ? getResources().getDimensionPixelSize(android.R.dimen.notification_action_disabled_content_alpha) : getResources().getDimensionPixelSize(android.R.dimen.notification_action_disabled_alpha)), size, size2);
        } else {
            this.mBarTransitions.mBarBackground.mFrame = null;
        }
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        this.mTouchHandler.shouldDeadZoneConsumeTouchEvents(motionEvent);
        return super.onTouchEvent(motionEvent);
    }

    public final void orientBackButton(KeyButtonDrawable keyButtonDrawable) {
        float f;
        boolean z = (this.mNavigationIconHints & 1) != 0;
        boolean z2 = this.mConfiguration.getLayoutDirection() == 1;
        float f2 = 0.0f;
        if (z) {
            f = z2 ? 90 : -90;
        } else {
            f = 0.0f;
        }
        if (keyButtonDrawable.mState.mRotateDegrees == f) {
            return;
        }
        if (QuickStepContract.isGesturalMode(this.mNavBarMode)) {
            keyButtonDrawable.setRotation(f);
            return;
        }
        if (!this.mShowSwipeUpUi && !this.mIsVertical && z) {
            f2 = -getResources().getDimension(R.dimen.navbar_back_button_ime_offset);
        }
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(keyButtonDrawable, PropertyValuesHolder.ofFloat(KeyButtonDrawable.KEY_DRAWABLE_ROTATE, f), PropertyValuesHolder.ofFloat(KeyButtonDrawable.KEY_DRAWABLE_TRANSLATE_Y, f2));
        ofPropertyValuesHolder.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
        ofPropertyValuesHolder.setDuration(200L);
        ofPropertyValuesHolder.start();
    }

    public final void reorient() {
        updateCurrentView();
        ((NavigationBarFrame) getRootView()).mDeadZone = this.mDeadZone;
        NavigationBarTransitions navigationBarTransitions = this.mBarTransitions;
        navigationBarTransitions.applyModeBackground(navigationBarTransitions.mMode, false);
        navigationBarTransitions.applyLightsOut(false, true);
        if (!isLayoutDirectionResolved()) {
            resolveLayoutDirection();
        }
        updateNavButtonIcons();
        ButtonDispatcher homeButton = getHomeButton();
        boolean z = this.mIsVertical;
        homeButton.mVertical = z;
        int size = homeButton.mViews.size();
        for (int i = 0; i < size; i++) {
            KeyEvent.Callback callback = (View) homeButton.mViews.get(i);
            if (callback instanceof ButtonInterface) {
                ((ButtonInterface) callback).setVertical(z);
            }
        }
    }

    public final void setDisabledFlags(int i, SysUiState sysUiState) {
        if (this.mDisabledFlags == i) {
            return;
        }
        boolean isOverviewEnabled = isOverviewEnabled();
        this.mDisabledFlags = i;
        if (!isOverviewEnabled && isOverviewEnabled()) {
            updateIcons(Configuration.EMPTY);
        }
        updateNavButtonIcons();
        updateSlippery();
        updateDisabledSystemUiStateFlags(sysUiState);
    }

    @Override // android.view.View
    public final void setLayoutDirection(int i) {
        updateIcons(Configuration.EMPTY);
        super.setLayoutDirection(i);
    }

    public final void setSlippery(boolean z) {
        WindowManager.LayoutParams layoutParams;
        ViewGroup viewGroup = (ViewGroup) getParent();
        if (viewGroup == null || (layoutParams = (WindowManager.LayoutParams) viewGroup.getLayoutParams()) == null) {
            return;
        }
        int i = layoutParams.flags;
        if (z == ((i & 536870912) != 0)) {
            return;
        }
        if (z) {
            layoutParams.flags = i | 536870912;
        } else {
            layoutParams.flags = (-536870913) & i;
        }
        ((WindowManager) getContext().getSystemService(WindowManager.class)).updateViewLayout(viewGroup, layoutParams);
    }

    public final void updateCurrentRotation() {
        int displayRotation = this.mConfiguration.windowConfiguration.getDisplayRotation();
        if (this.mCurrentRotation == displayRotation) {
            return;
        }
        this.mCurrentRotation = displayRotation;
        NavigationBarInflaterView navigationBarInflaterView = this.mNavigationInflaterView;
        boolean z = displayRotation == 1;
        if (z != navigationBarInflaterView.mAlternativeOrder) {
            navigationBarInflaterView.mAlternativeOrder = z;
            navigationBarInflaterView.updateAlternativeOrder();
        }
        this.mDeadZone.onConfigurationChanged(this.mCurrentRotation);
    }

    public final void updateCurrentView() {
        this.mHorizontal.setVisibility(8);
        this.mVertical.setVisibility(8);
        View view = this.mIsVertical ? this.mVertical : this.mHorizontal;
        this.mCurrentView = view;
        view.setVisibility(0);
        NavigationBarInflaterView navigationBarInflaterView = this.mNavigationInflaterView;
        boolean z = this.mIsVertical;
        if (z != navigationBarInflaterView.mIsVertical) {
            navigationBarInflaterView.mIsVertical = z;
        }
        navigationBarInflaterView.updateButtonDispatchersCurrentView();
        updateLayoutTransitionsEnabled();
        updateCurrentRotation();
    }

    public final void updateDisabledSystemUiStateFlags(SysUiState sysUiState) {
        int displayId = ((FrameLayout) this).mContext.getDisplayId();
        sysUiState.setFlag(128L, (this.mDisabledFlags & 16777216) != 0);
        sysUiState.setFlag(256L, (this.mDisabledFlags & 2097152) != 0);
        sysUiState.setFlag(1024L, (this.mDisabledFlags & 33554432) != 0);
        sysUiState.commitUpdate(displayId);
    }

    public final void updateIcons(Configuration configuration) {
        int i = configuration.orientation;
        Configuration configuration2 = this.mConfiguration;
        boolean z = i != configuration2.orientation;
        boolean z2 = configuration.densityDpi != configuration2.densityDpi;
        boolean z3 = configuration.getLayoutDirection() != this.mConfiguration.getLayoutDirection();
        if (z || z2) {
            this.mDockedIcon = getDrawable(R.drawable.ic_sysbar_docked);
            KeyButtonDrawable drawable = this.mShowSwipeUpUi ? getDrawable(R.drawable.ic_sysbar_home_quick_step) : getDrawable(R.drawable.ic_sysbar_home);
            drawable.setRotation(this.mIsVertical ? 90.0f : 0.0f);
            this.mHomeDefaultIcon = drawable;
        }
        if (z2 || z3) {
            this.mRecentIcon = getDrawable(R.drawable.ic_sysbar_recent);
            ContextualButtonGroup contextualButtonGroup = this.mContextualButtonGroup;
            int i2 = this.mLightIconColor;
            int i3 = this.mDarkIconColor;
            Iterator it = contextualButtonGroup.mButtonData.iterator();
            while (it.hasNext()) {
                ContextualButton contextualButton = ((ContextualButtonGroup.ButtonData) it.next()).button;
                int i4 = contextualButton.mIconResId;
                if (i4 != 0) {
                    KeyButtonDrawable keyButtonDrawable = contextualButton.mImageDrawable;
                    KeyButtonDrawable create = KeyButtonDrawable.create(contextualButton.mLightContext, i2, i3, i4, false);
                    if (keyButtonDrawable != null) {
                        create.setDarkIntensity(keyButtonDrawable.mState.mDarkIntensity);
                    }
                    contextualButton.setImageDrawable(create);
                }
            }
        }
        if (z || z2 || z3) {
            KeyButtonDrawable drawable2 = getDrawable(R.drawable.ic_sysbar_back);
            orientBackButton(drawable2);
            this.mBackIcon = drawable2;
        }
    }

    public final void updateLayoutTransitionsEnabled() {
        boolean z = !this.mWakeAndUnlocking && this.mLayoutTransitionsEnabled;
        LayoutTransition layoutTransition = ((ViewGroup) this.mCurrentView.findViewById(R.id.nav_buttons)).getLayoutTransition();
        if (layoutTransition != null) {
            if (z) {
                layoutTransition.enableTransitionType(2);
                layoutTransition.enableTransitionType(3);
                layoutTransition.enableTransitionType(0);
                layoutTransition.enableTransitionType(1);
                return;
            }
            layoutTransition.disableTransitionType(2);
            layoutTransition.disableTransitionType(3);
            layoutTransition.disableTransitionType(0);
            layoutTransition.disableTransitionType(1);
        }
    }

    public final void updateNavButtonIcons() {
        LayoutTransition layoutTransition;
        boolean z = (this.mNavigationIconHints & 1) != 0;
        KeyButtonDrawable keyButtonDrawable = this.mBackIcon;
        orientBackButton(keyButtonDrawable);
        KeyButtonDrawable keyButtonDrawable2 = this.mHomeDefaultIcon;
        keyButtonDrawable2.setRotation(this.mIsVertical ? 90.0f : 0.0f);
        getHomeButton().setImageDrawable(keyButtonDrawable2);
        getBackButton().setImageDrawable(keyButtonDrawable);
        updateRecentsIcon();
        this.mContextualButtonGroup.setButtonVisibility(R.id.ime_switcher, !((this.mNavigationIconHints & 4) == 0 || isImeRenderingNavButtons()));
        NavigationBarTransitions navigationBarTransitions = this.mBarTransitions;
        navigationBarTransitions.applyDarkIntensity(navigationBarTransitions.mLightTransitionsController.mDarkIntensity);
        boolean z2 = QuickStepContract.isGesturalMode(this.mNavBarMode) || (this.mDisabledFlags & 2097152) != 0;
        boolean isRecentsButtonDisabled = isRecentsButtonDisabled();
        boolean z3 = isRecentsButtonDisabled && (2097152 & this.mDisabledFlags) != 0;
        boolean z4 = (!z && (this.mEdgeBackGestureHandler.isHandlingGestures() || (this.mDisabledFlags & 4194304) != 0)) || isImeRenderingNavButtons();
        if (this.mOverviewProxyEnabled) {
            int i = this.mNavBarMode;
            isRecentsButtonDisabled |= true ^ (i == 0);
            if (this.mScreenPinningActive && !QuickStepContract.isGesturalMode(i)) {
                z4 = false;
                z2 = false;
            }
        } else if (this.mScreenPinningActive) {
            z4 = false;
            isRecentsButtonDisabled = false;
        }
        ViewGroup viewGroup = (ViewGroup) this.mCurrentView.findViewById(R.id.nav_buttons);
        if (viewGroup != null && (layoutTransition = viewGroup.getLayoutTransition()) != null && !layoutTransition.getTransitionListeners().contains(this.mTransitionListener)) {
            layoutTransition.addTransitionListener(this.mTransitionListener);
        }
        getBackButton().setVisibility(z4 ? 4 : 0);
        getHomeButton().setVisibility(z2 ? 4 : 0);
        getRecentsButton().setVisibility(isRecentsButtonDisabled ? 4 : 0);
        ((ButtonDispatcher) this.mButtonDispatchers.get(R.id.home_handle)).setVisibility(z3 ? 4 : 0);
        notifyActiveTouchRegions();
    }

    public final void updateRecentsIcon() {
        this.mDockedIcon.setRotation(0.0f);
        getRecentsButton().setImageDrawable(this.mRecentIcon);
        NavigationBarTransitions navigationBarTransitions = this.mBarTransitions;
        navigationBarTransitions.applyDarkIntensity(navigationBarTransitions.mLightTransitionsController.mDarkIntensity);
    }

    public final void updateRotationButton() {
        final RotationButtonController rotationButtonController = this.mRotationButtonController;
        FloatingRotationButton floatingRotationButton = this.mFloatingRotationButton;
        AnonymousClass2 anonymousClass2 = this.mRotationButtonListener;
        rotationButtonController.mRotationButton = floatingRotationButton;
        floatingRotationButton.mRotationButtonController = rotationButtonController;
        floatingRotationButton.updateIcon(rotationButtonController.mLightIconColor, rotationButtonController.mDarkIconColor);
        FloatingRotationButton floatingRotationButton2 = rotationButtonController.mRotationButton;
        floatingRotationButton2.mKeyButtonView.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.shared.rotation.RotationButtonController$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RotationButtonController rotationButtonController2 = RotationButtonController.this;
                rotationButtonController2.mUiEventLogger.log(RotationButtonController.RotationButtonEvent.ROTATION_SUGGESTION_ACCEPTED);
                ContentResolver contentResolver = rotationButtonController2.mContext.getContentResolver();
                int i = Settings.Secure.getInt(contentResolver, "num_rotation_suggestions_accepted", 0);
                if (i < 3) {
                    Settings.Secure.putInt(contentResolver, "num_rotation_suggestions_accepted", i + 1);
                }
                Boolean isRotationLocked = RotationPolicyUtil.isRotationLocked(rotationButtonController2.mContext);
                int i2 = rotationButtonController2.mLastRotationSuggestion;
                if (isRotationLocked != null) {
                    RotationPolicy.setRotationLockAtAngle(rotationButtonController2.mContext, isRotationLocked.booleanValue(), i2, "RotationButtonController#onRotateSuggestionClick");
                }
                Log.i("RotationButtonController", "onRotateSuggestionClick() mLastRotationSuggestion=" + rotationButtonController2.mLastRotationSuggestion);
                view.performHapticFeedback(1);
            }
        });
        FloatingRotationButton floatingRotationButton3 = rotationButtonController.mRotationButton;
        floatingRotationButton3.mKeyButtonView.setOnHoverListener(new View.OnHoverListener() { // from class: com.android.systemui.shared.rotation.RotationButtonController$$ExternalSyntheticLambda4
            @Override // android.view.View.OnHoverListener
            public final boolean onHover(View view, MotionEvent motionEvent) {
                RotationButtonController rotationButtonController2 = RotationButtonController.this;
                rotationButtonController2.getClass();
                int actionMasked = motionEvent.getActionMasked();
                rotationButtonController2.mHoveringRotationSuggestion = actionMasked == 9 || actionMasked == 7;
                rotationButtonController2.rescheduleRotationTimeout(true);
                return false;
            }
        });
        rotationButtonController.mRotationButton.mUpdatesCallback = anonymousClass2;
    }

    public final void updateSlippery() {
        PanelExpansionInteractor panelExpansionInteractor;
        setSlippery((this.mShowSwipeUpUi && isOverviewEnabled() && ((panelExpansionInteractor = this.mPanelExpansionInteractor) == null || !panelExpansionInteractor.isFullyExpanded() || this.mPanelExpansionInteractor.isCollapsing())) ? false : true);
    }

    public final void updateStates() {
        NavigationBarInflaterView navigationBarInflaterView = this.mNavigationInflaterView;
        if (navigationBarInflaterView != null) {
            String defaultLayout = navigationBarInflaterView.getDefaultLayout();
            if (!Objects.equals(navigationBarInflaterView.mCurrentLayout, defaultLayout)) {
                navigationBarInflaterView.clearDispatcherViews();
                ViewGroup viewGroup = (ViewGroup) navigationBarInflaterView.mHorizontal.findViewById(R.id.nav_buttons);
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    ((ViewGroup) viewGroup.getChildAt(i)).removeAllViews();
                }
                ViewGroup viewGroup2 = (ViewGroup) navigationBarInflaterView.mVertical.findViewById(R.id.nav_buttons);
                for (int i2 = 0; i2 < viewGroup2.getChildCount(); i2++) {
                    ((ViewGroup) viewGroup2.getChildAt(i2)).removeAllViews();
                }
                navigationBarInflaterView.inflateLayout(defaultLayout);
            }
        }
        updateSlippery();
        updateIcons(Configuration.EMPTY);
        updateNavButtonIcons();
        this.mBgExecutor.execute(new Runnable() { // from class: com.android.systemui.navigationbar.views.NavigationBarView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    WindowManagerGlobal.getWindowManagerService().setNavBarVirtualKeyHapticFeedbackEnabled(!NavigationBarView.this.mShowSwipeUpUi);
                } catch (RemoteException e) {
                    Log.w("NavBarView", "Failed to enable or disable navigation bar button haptics: ", e);
                }
            }
        });
        ButtonDispatcher homeButton = getHomeButton();
        AnonymousClass1 anonymousClass1 = this.mShowSwipeUpUi ? this.mQuickStepAccessibilityDelegate : null;
        homeButton.mAccessibilityDelegate = anonymousClass1;
        int size = homeButton.mViews.size();
        for (int i3 = 0; i3 < size; i3++) {
            ((View) homeButton.mViews.get(i3)).setAccessibilityDelegate(anonymousClass1);
        }
    }
}
