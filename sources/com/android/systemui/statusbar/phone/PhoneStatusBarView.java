package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.android.internal.policy.SystemBarUtils;
import com.android.systemui.Dependency;
import com.android.systemui.statusbar.phone.PhoneStatusBarViewController;
import com.android.systemui.statusbar.window.StatusBarWindowControllerImpl;
import com.android.systemui.util.leak.RotationUtils;
import com.android.wm.shell.R;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class PhoneStatusBarView extends FrameLayout {
    public int mCutoutSideNudge;
    public View mCutoutSpace;
    public int mDensity;
    public DisplayCutout mDisplayCutout;
    public Rect mDisplaySize;
    public float mFontScale;
    public PhoneStatusBarViewController.AnonymousClass1 mHasCornerCutoutFetcher;
    public PhoneStatusBarViewController.AnonymousClass1 mInsetsFetcher;
    public int mRotationOrientation;
    public final StatusBarWindowControllerImpl mStatusBarWindowController;
    public PhoneStatusBarViewController.PhoneStatusBarViewTouchHandler mTouchEventHandler;

    public PhoneStatusBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRotationOrientation = -1;
        this.mCutoutSideNudge = 0;
        this.mStatusBarWindowController = (StatusBarWindowControllerImpl) Dependency.sDependency.getDependencyInner(StatusBarWindowControllerImpl.class);
    }

    @Override // android.view.View
    public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        if (updateDisplayParameters()) {
            updateStatusBarHeight();
            updateCutoutLocation();
            updateSafeInsets();
            requestLayout();
        }
        return super.onApplyWindowInsets(windowInsets);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (updateDisplayParameters()) {
            updateStatusBarHeight();
            updateCutoutLocation();
            updateSafeInsets();
            updateWindowHeight();
        }
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mCutoutSideNudge = getResources().getDimensionPixelSize(R.dimen.display_cutout_margin_consumption);
        updateStatusBarHeight();
        if (updateDisplayParameters()) {
            updateStatusBarHeight();
            updateCutoutLocation();
            updateSafeInsets();
            requestLayout();
        }
        updateWindowHeight();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mDisplayCutout = null;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mCutoutSpace = findViewById(R.id.cutout_space_view);
        this.mCutoutSideNudge = getResources().getDimensionPixelSize(R.dimen.display_cutout_margin_consumption);
        updateStatusBarHeight();
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.mTouchEventHandler.onInterceptTouchEvent(motionEvent);
        return super.onInterceptTouchEvent(motionEvent);
    }

    public final boolean onRequestSendAccessibilityEventInternal(View view, AccessibilityEvent accessibilityEvent) {
        if (!super.onRequestSendAccessibilityEventInternal(view, accessibilityEvent)) {
            return false;
        }
        AccessibilityEvent obtain = AccessibilityEvent.obtain();
        onInitializeAccessibilityEvent(obtain);
        dispatchPopulateAccessibilityEvent(obtain);
        accessibilityEvent.appendRecord(obtain);
        return true;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        PhoneStatusBarViewController.PhoneStatusBarViewTouchHandler phoneStatusBarViewTouchHandler = this.mTouchEventHandler;
        if (phoneStatusBarViewTouchHandler != null) {
            return phoneStatusBarViewTouchHandler.onTouchEvent(motionEvent);
        }
        Log.w("PhoneStatusBarView", String.format("onTouch: No touch handler provided; eating gesture at (%d,%d)", Integer.valueOf((int) motionEvent.getX()), Integer.valueOf((int) motionEvent.getY())));
        return true;
    }

    public final void updateCutoutLocation() {
        boolean z;
        if (this.mCutoutSpace == null) {
            return;
        }
        PhoneStatusBarViewController.AnonymousClass1 anonymousClass1 = this.mHasCornerCutoutFetcher;
        if (anonymousClass1 != null) {
            z = PhoneStatusBarViewController.this.statusBarContentInsetsProvider.currentRotationHasCornerCutout();
        } else {
            Log.e("PhoneStatusBarView", "mHasCornerCutoutFetcher unexpectedly null");
            z = true;
        }
        DisplayCutout displayCutout = this.mDisplayCutout;
        if (displayCutout == null || displayCutout.isEmpty() || z) {
            this.mCutoutSpace.setVisibility(8);
            return;
        }
        this.mCutoutSpace.setVisibility(0);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mCutoutSpace.getLayoutParams();
        Rect boundingRectTop = this.mDisplayCutout.getBoundingRectTop();
        int i = boundingRectTop.left;
        int i2 = this.mCutoutSideNudge;
        boundingRectTop.left = i + i2;
        boundingRectTop.right -= i2;
        layoutParams.width = boundingRectTop.width();
        layoutParams.height = boundingRectTop.height();
    }

    public final boolean updateDisplayParameters() {
        boolean z;
        int exactRotation = RotationUtils.getExactRotation(((FrameLayout) this).mContext);
        if (exactRotation != this.mRotationOrientation) {
            this.mRotationOrientation = exactRotation;
            z = true;
        } else {
            z = false;
        }
        if (!Objects.equals(getRootWindowInsets().getDisplayCutout(), this.mDisplayCutout)) {
            this.mDisplayCutout = getRootWindowInsets().getDisplayCutout();
            z = true;
        }
        Configuration configuration = ((FrameLayout) this).mContext.getResources().getConfiguration();
        Rect maxBounds = configuration.windowConfiguration.getMaxBounds();
        if (!Objects.equals(maxBounds, this.mDisplaySize)) {
            this.mDisplaySize = maxBounds;
            z = true;
        }
        int i = configuration.densityDpi;
        if (i != this.mDensity) {
            this.mDensity = i;
            z = true;
        }
        float f = configuration.fontScale;
        if (f == this.mFontScale) {
            return z;
        }
        this.mFontScale = f;
        return true;
    }

    public final void updateSafeInsets() {
        PhoneStatusBarViewController.AnonymousClass1 anonymousClass1 = this.mInsetsFetcher;
        if (anonymousClass1 == null) {
            Log.e("PhoneStatusBarView", "mInsetsFetcher unexpectedly null");
        } else {
            Insets statusBarContentInsetsForCurrentRotation = PhoneStatusBarViewController.this.statusBarContentInsetsProvider.getStatusBarContentInsetsForCurrentRotation();
            setPadding(statusBarContentInsetsForCurrentRotation.left, statusBarContentInsetsForCurrentRotation.top, statusBarContentInsetsForCurrentRotation.right, getPaddingBottom());
        }
    }

    public final void updateStatusBarHeight() {
        DisplayCutout displayCutout = this.mDisplayCutout;
        int i = displayCutout == null ? 0 : displayCutout.getWaterfallInsets().top;
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = SystemBarUtils.getStatusBarHeight(((FrameLayout) this).mContext) - i;
        View findViewById = findViewById(R.id.system_icons);
        ViewGroup.LayoutParams layoutParams2 = findViewById.getLayoutParams();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.status_bar_system_icons_height);
        if (layoutParams2.height != dimensionPixelSize) {
            layoutParams2.height = dimensionPixelSize;
            findViewById.setLayoutParams(layoutParams2);
        }
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.status_bar_padding_start);
        findViewById(R.id.status_bar_contents).setPaddingRelative(dimensionPixelSize2, getResources().getDimensionPixelSize(R.dimen.status_bar_padding_top), getResources().getDimensionPixelSize(R.dimen.status_bar_padding_end), 0);
        findViewById(R.id.notification_lights_out).setPaddingRelative(0, dimensionPixelSize2, 0, 0);
        findViewById(R.id.system_icons).setPaddingRelative(getResources().getDimensionPixelSize(R.dimen.status_bar_icons_padding_start), getResources().getDimensionPixelSize(R.dimen.status_bar_icons_padding_top), getResources().getDimensionPixelSize(R.dimen.status_bar_icons_padding_end), getResources().getDimensionPixelSize(R.dimen.status_bar_icons_padding_bottom));
        setLayoutParams(layoutParams);
    }

    public final void updateWindowHeight() {
        StatusBarWindowControllerImpl statusBarWindowControllerImpl = this.mStatusBarWindowController;
        statusBarWindowControllerImpl.getClass();
        Trace.beginSection("StatusBarWindowController#refreshStatusBarHeight");
        try {
            int statusBarHeight = SystemBarUtils.getStatusBarHeight(statusBarWindowControllerImpl.mContext);
            if (statusBarWindowControllerImpl.mBarHeight != statusBarHeight) {
                statusBarWindowControllerImpl.mBarHeight = statusBarHeight;
                statusBarWindowControllerImpl.apply(statusBarWindowControllerImpl.mCurrentState);
            }
        } finally {
            Trace.endSection();
        }
    }
}
