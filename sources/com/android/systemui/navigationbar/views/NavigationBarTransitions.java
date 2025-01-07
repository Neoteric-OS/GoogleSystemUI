package com.android.systemui.navigationbar.views;

import android.util.SparseArray;
import android.view.View;
import com.android.systemui.navigationbar.views.buttons.ButtonDispatcher;
import com.android.systemui.navigationbar.views.buttons.ButtonInterface;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.shared.navigationbar.KeyButtonRipple;
import com.android.systemui.shared.statusbar.phone.BarTransitions;
import com.android.systemui.statusbar.phone.LightBarTransitionsController;
import com.android.systemui.util.Utils;
import com.android.wm.shell.R;
import com.android.wm.shell.shared.handles.RegionSamplingHelper;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$15;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NavigationBarTransitions extends BarTransitions implements LightBarTransitionsController.DarkIntensityApplier {
    public final boolean mAllowAutoDimWallpaperNotVisible;
    public boolean mAutoDim;
    public final List mDarkIntensityListeners;
    public final DisplayTracker mDisplayTracker;
    public final LightBarTransitionsController mLightTransitionsController;
    public boolean mLightsOut;
    public final List mListeners;
    public int mNavBarMode;
    public View mNavButtons;
    public final NavigationBarView mView;
    public boolean mWallpaperVisible;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface DarkIntensityListener {
        void onDarkIntensity(float f);
    }

    public NavigationBarTransitions(NavigationBarView navigationBarView, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$15 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$15, DisplayTracker displayTracker) {
        super(navigationBarView, R.drawable.nav_background);
        this.mListeners = new ArrayList();
        this.mNavBarMode = 0;
        this.mView = navigationBarView;
        this.mLightTransitionsController = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$15.create(this);
        this.mDisplayTracker = displayTracker;
        this.mAllowAutoDimWallpaperNotVisible = navigationBarView.getContext().getResources().getBoolean(R.bool.config_navigation_bar_enable_auto_dim_no_visible_wallpaper);
        this.mDarkIntensityListeners = new ArrayList();
        navigationBarView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.android.systemui.navigationbar.views.NavigationBarTransitions$$ExternalSyntheticLambda0
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                NavigationBarTransitions navigationBarTransitions = NavigationBarTransitions.this;
                View view2 = navigationBarTransitions.mView.mCurrentView;
                if (view2 != null) {
                    navigationBarTransitions.mNavButtons = view2.findViewById(R.id.nav_buttons);
                    navigationBarTransitions.applyLightsOut(false, true);
                }
            }
        });
        View view = navigationBarView.mCurrentView;
        if (view != null) {
            this.mNavButtons = view.findViewById(R.id.nav_buttons);
        }
    }

    @Override // com.android.systemui.statusbar.phone.LightBarTransitionsController.DarkIntensityApplier
    public final void applyDarkIntensity(float f) {
        NavigationBarView navigationBarView = this.mView;
        SparseArray sparseArray = navigationBarView.mButtonDispatchers;
        int size = sparseArray.size() - 1;
        while (true) {
            if (size < 0) {
                break;
            }
            ButtonDispatcher buttonDispatcher = (ButtonDispatcher) sparseArray.valueAt(size);
            buttonDispatcher.mDarkIntensity = Float.valueOf(f);
            int size2 = buttonDispatcher.mViews.size();
            for (int i = 0; i < size2; i++) {
                if (buttonDispatcher.mViews.get(i) instanceof ButtonInterface) {
                    ((ButtonInterface) buttonDispatcher.mViews.get(i)).setDarkIntensity(f);
                }
            }
            size--;
        }
        KeyButtonRipple keyButtonRipple = navigationBarView.mRotationButtonController.mRotationButton.mKeyButtonView.mRipple;
        keyButtonRipple.getClass();
        keyButtonRipple.mDark = f >= 0.5f;
        Iterator it = this.mDarkIntensityListeners.iterator();
        while (it.hasNext()) {
            ((DarkIntensityListener) it.next()).onDarkIntensity(f);
        }
        if (this.mAutoDim) {
            applyLightsOut(false, true);
        }
    }

    public final void applyLightsOut(boolean z, boolean z2) {
        boolean isLightsOut = isLightsOut(this.mMode);
        if (z2 || isLightsOut != this.mLightsOut) {
            this.mLightsOut = isLightsOut;
            View view = this.mNavButtons;
            if (view == null) {
                return;
            }
            view.animate().cancel();
            float f = isLightsOut ? (this.mLightTransitionsController.mDarkIntensity / 10.0f) + 0.6f : 1.0f;
            if (z) {
                this.mNavButtons.animate().alpha(f).setDuration(isLightsOut ? 1500 : 250).start();
            } else {
                this.mNavButtons.setAlpha(f);
            }
        }
    }

    @Override // com.android.systemui.statusbar.phone.LightBarTransitionsController.DarkIntensityApplier
    public final int getTintAnimationDuration() {
        if (Utils.isGesturalModeOnDefaultDisplay(this.mView.getContext(), this.mDisplayTracker, this.mNavBarMode)) {
            return Math.max(1700, 400);
        }
        return 120;
    }

    @Override // com.android.systemui.shared.statusbar.phone.BarTransitions
    public final boolean isLightsOut(int i) {
        return super.isLightsOut(i) || (this.mAllowAutoDimWallpaperNotVisible && this.mAutoDim && !this.mWallpaperVisible && i != 5);
    }

    public final void onTransition(int i, int i2, boolean z) {
        applyModeBackground(i2, z);
        applyLightsOut(z, false);
        Iterator it = this.mListeners.iterator();
        while (it.hasNext()) {
            NavigationBar navigationBar = ((NavigationBar$$ExternalSyntheticLambda2) it.next()).f$0;
            RegionSamplingHelper regionSamplingHelper = navigationBar.mRegionSamplingHelper;
            if (i2 == 4) {
                regionSamplingHelper.stop();
                navigationBar.mNavigationBarTransitions.mLightTransitionsController.setIconsDark(false, true);
            } else {
                regionSamplingHelper.start(navigationBar.mSamplingBounds);
            }
        }
    }

    public final void setAutoDim(boolean z) {
        if ((z && Utils.isGesturalModeOnDefaultDisplay(this.mView.getContext(), this.mDisplayTracker, this.mNavBarMode)) || this.mAutoDim == z) {
            return;
        }
        this.mAutoDim = z;
        applyLightsOut(true, false);
    }
}
