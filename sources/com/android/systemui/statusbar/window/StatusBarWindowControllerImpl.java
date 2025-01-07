package com.android.systemui.statusbar.window;

import android.content.Context;
import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Binder;
import android.os.RemoteException;
import android.view.DisplayCutout;
import android.view.IWindowManager;
import android.view.InsetsFrameProvider;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowManager;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.internal.policy.SystemBarUtils;
import com.android.systemui.fragments.FragmentService;
import com.android.systemui.statusbar.phone.StatusBarContentInsetsProvider;
import com.android.systemui.unfold.UnfoldTransitionProgressProvider;
import com.android.systemui.unfold.util.JankMonitorTransitionProgressListener;
import com.android.wm.shell.R;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarWindowControllerImpl {
    public int mBarHeight;
    public final StatusBarContentInsetsProvider mContentInsetsProvider;
    public final Context mContext;
    public final FragmentService mFragmentService;
    public final IWindowManager mIWindowManager;
    public boolean mIsAttached;
    public final ViewGroup mLaunchAnimationContainer;
    public WindowManager.LayoutParams mLp;
    public final WindowManager.LayoutParams mLpChanged;
    public final StatusBarWindowView mStatusBarWindowView;
    public final ViewCaptureAwareWindowManager mWindowManager;
    public final State mCurrentState = new State();
    public final Binder mInsetsSourceOwner = new Binder();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class State {
        public boolean mForceStatusBarVisible;
        public boolean mIsLaunchAnimationRunning;
        public boolean mOngoingProcessRequiresStatusBarVisible;
    }

    public StatusBarWindowControllerImpl(Context context, StatusBarWindowViewInflaterImpl statusBarWindowViewInflaterImpl, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager, IWindowManager iWindowManager, StatusBarContentInsetsProvider statusBarContentInsetsProvider, FragmentService fragmentService, Optional optional) {
        this.mBarHeight = -1;
        this.mContext = context;
        this.mWindowManager = viewCaptureAwareWindowManager;
        this.mIWindowManager = iWindowManager;
        this.mContentInsetsProvider = statusBarContentInsetsProvider;
        statusBarWindowViewInflaterImpl.getClass();
        StatusBarWindowView statusBarWindowView = (StatusBarWindowView) LayoutInflater.from(context).inflate(R.layout.super_status_bar, (ViewGroup) null);
        if (statusBarWindowView == null) {
            throw new IllegalStateException("R.layout.super_status_bar could not be properly inflated");
        }
        this.mStatusBarWindowView = statusBarWindowView;
        this.mFragmentService = fragmentService;
        this.mLaunchAnimationContainer = (ViewGroup) statusBarWindowView.findViewById(R.id.status_bar_launch_animation_container);
        this.mLpChanged = new WindowManager.LayoutParams();
        if (this.mBarHeight < 0) {
            this.mBarHeight = SystemBarUtils.getStatusBarHeight(context);
        }
        optional.ifPresent(new Consumer() { // from class: com.android.systemui.statusbar.window.StatusBarWindowControllerImpl$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                StatusBarWindowControllerImpl statusBarWindowControllerImpl = StatusBarWindowControllerImpl.this;
                statusBarWindowControllerImpl.getClass();
                ((UnfoldTransitionProgressProvider) obj).addCallback(new JankMonitorTransitionProgressListener(new StatusBarWindowControllerImpl$$ExternalSyntheticLambda1(statusBarWindowControllerImpl)));
            }
        });
    }

    public final void apply(State state) {
        if (this.mIsAttached) {
            if (state.mForceStatusBarVisible || state.mIsLaunchAnimationRunning || state.mOngoingProcessRequiresStatusBarVisible) {
                this.mLpChanged.forciblyShownTypes |= WindowInsets.Type.statusBars();
            } else {
                this.mLpChanged.forciblyShownTypes &= ~WindowInsets.Type.statusBars();
            }
            this.mLpChanged.height = state.mIsLaunchAnimationRunning ? -1 : this.mBarHeight;
            for (int i = 0; i <= 3; i++) {
                int statusBarHeightForRotation = SystemBarUtils.getStatusBarHeightForRotation(this.mContext, i);
                WindowManager.LayoutParams layoutParams = this.mLpChanged.paramsForRotation[i];
                layoutParams.height = state.mIsLaunchAnimationRunning ? -1 : statusBarHeightForRotation;
                InsetsFrameProvider[] insetsFrameProviderArr = layoutParams.providedInsets;
                if (insetsFrameProviderArr != null) {
                    for (InsetsFrameProvider insetsFrameProvider : insetsFrameProviderArr) {
                        insetsFrameProvider.setInsetsSize(Insets.of(0, statusBarHeightForRotation, 0, 0));
                    }
                }
            }
            WindowManager.LayoutParams layoutParams2 = this.mLp;
            if (layoutParams2 == null || layoutParams2.copyFrom(this.mLpChanged) == 0) {
                return;
            }
            this.mWindowManager.updateViewLayout(this.mStatusBarWindowView, this.mLp);
        }
    }

    public final void calculateStatusBarLocationsForAllRotations() {
        DisplayCutout cutout = this.mContext.getDisplay().getCutout();
        StatusBarContentInsetsProvider statusBarContentInsetsProvider = this.mContentInsetsProvider;
        try {
            this.mIWindowManager.updateStaticPrivacyIndicatorBounds(this.mContext.getDisplayId(), new Rect[]{statusBarContentInsetsProvider.getBoundingRectForPrivacyChipForRotation(0, cutout), statusBarContentInsetsProvider.getBoundingRectForPrivacyChipForRotation(1, cutout), statusBarContentInsetsProvider.getBoundingRectForPrivacyChipForRotation(2, cutout), statusBarContentInsetsProvider.getBoundingRectForPrivacyChipForRotation(3, cutout)});
        } catch (RemoteException unused) {
        }
    }

    public final WindowManager.LayoutParams getBarLayoutParamsForRotation(int i) {
        int statusBarHeightForRotation = SystemBarUtils.getStatusBarHeightForRotation(this.mContext, i);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, statusBarHeightForRotation, 2000, -2139095032, -3);
        layoutParams.privateFlags |= 16777216;
        layoutParams.token = new Binder();
        layoutParams.gravity = 48;
        layoutParams.setFitInsetsTypes(0);
        layoutParams.setTitle("StatusBar");
        layoutParams.packageName = this.mContext.getPackageName();
        layoutParams.layoutInDisplayCutoutMode = 3;
        InsetsFrameProvider insetsFrameProvider = new InsetsFrameProvider(this.mInsetsSourceOwner, 0, WindowInsets.Type.mandatorySystemGestures());
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(android.R.dimen.edit_text_inset_horizontal_material);
        if (dimensionPixelSize > 0) {
            insetsFrameProvider.setMinimalInsetsSizeInDisplayCutoutSafe(Insets.of(0, dimensionPixelSize, 0, 0));
        }
        layoutParams.providedInsets = new InsetsFrameProvider[]{new InsetsFrameProvider(this.mInsetsSourceOwner, 0, WindowInsets.Type.statusBars()).setInsetsSize(Insets.of(0, statusBarHeightForRotation, 0, 0)), new InsetsFrameProvider(this.mInsetsSourceOwner, 0, WindowInsets.Type.tappableElement()).setInsetsSize(Insets.of(0, statusBarHeightForRotation, 0, 0)), insetsFrameProvider};
        return layoutParams;
    }
}
