package com.android.systemui.biometrics.ui.binder;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.airbnb.lottie.LottieAnimationView;
import com.android.systemui.CoreStartable;
import com.android.systemui.biometrics.domain.interactor.DisplayStateInteractor;
import com.android.systemui.biometrics.domain.interactor.SideFpsSensorInteractor;
import com.android.systemui.biometrics.ui.viewmodel.SideFpsOverlayViewModel;
import com.android.systemui.keyguard.domain.interactor.DeviceEntrySideFpsOverlayInteractor;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.wm.shell.R;
import dagger.Lazy;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SideFpsOverlayViewBinder implements CoreStartable {
    public final Context applicationContext;
    public final CoroutineScope applicationScope;
    public final Lazy biometricStatusInteractor;
    public final Lazy deviceEntrySideFpsOverlayInteractor;
    public final Lazy displayStateInteractor;
    public final Lazy layoutInflater;
    public View overlayView;
    public final Lazy sfpsSensorInteractor;
    public final Lazy sideFpsProgressBarViewModel;
    public final Lazy windowManager;

    public SideFpsOverlayViewBinder(CoroutineScope coroutineScope, Context context, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, Lazy lazy7) {
        this.applicationScope = coroutineScope;
        this.applicationContext = context;
        this.biometricStatusInteractor = lazy;
        this.displayStateInteractor = lazy2;
        this.deviceEntrySideFpsOverlayInteractor = lazy3;
        this.layoutInflater = lazy4;
        this.sideFpsProgressBarViewModel = lazy5;
        this.sfpsSensorInteractor = lazy6;
        this.windowManager = lazy7;
    }

    public static final void access$hide(SideFpsOverlayViewBinder sideFpsOverlayViewBinder) {
        View view = sideFpsOverlayViewBinder.overlayView;
        if (view != null) {
            LottieAnimationView lottieAnimationView = (LottieAnimationView) view.requireViewById(R.id.sidefps_animation);
            lottieAnimationView.pauseAnimation();
            lottieAnimationView.removeAllLottieOnCompositionLoadedListener();
            Log.d("SideFpsOverlayViewBinder", "hide(): removing overlayView " + sideFpsOverlayViewBinder.overlayView + ", setting to null");
            ((WindowManager) sideFpsOverlayViewBinder.windowManager.get()).removeView(sideFpsOverlayViewBinder.overlayView);
            sideFpsOverlayViewBinder.overlayView = null;
        }
    }

    public static final void access$show(SideFpsOverlayViewBinder sideFpsOverlayViewBinder) {
        View view = sideFpsOverlayViewBinder.overlayView;
        if (view != null && view.isAttachedToWindow()) {
            Log.d("SideFpsOverlayViewBinder", "show(): overlayView " + sideFpsOverlayViewBinder.overlayView + " isAttachedToWindow already, ignoring show request");
            return;
        }
        sideFpsOverlayViewBinder.overlayView = ((LayoutInflater) sideFpsOverlayViewBinder.layoutInflater.get()).inflate(R.layout.sidefps_view, (ViewGroup) null, false);
        SideFpsOverlayViewModel sideFpsOverlayViewModel = new SideFpsOverlayViewModel(sideFpsOverlayViewBinder.applicationContext, (DeviceEntrySideFpsOverlayInteractor) sideFpsOverlayViewBinder.deviceEntrySideFpsOverlayInteractor.get(), (DisplayStateInteractor) sideFpsOverlayViewBinder.displayStateInteractor.get(), (SideFpsSensorInteractor) sideFpsOverlayViewBinder.sfpsSensorInteractor.get());
        View view2 = sideFpsOverlayViewBinder.overlayView;
        Intrinsics.checkNotNull(view2);
        Lazy lazy = sideFpsOverlayViewBinder.windowManager;
        RepeatWhenAttachedKt.repeatWhenAttached(view2, EmptyCoroutineContext.INSTANCE, new SideFpsOverlayViewBinder$Companion$bind$1(view2, sideFpsOverlayViewModel, (WindowManager) lazy.get(), null));
        View view3 = sideFpsOverlayViewBinder.overlayView;
        Intrinsics.checkNotNull(view3);
        view3.setVisibility(4);
        Log.d("SideFpsOverlayViewBinder", "show(): adding overlayView " + sideFpsOverlayViewBinder.overlayView);
        ((WindowManager) lazy.get()).addView(sideFpsOverlayViewBinder.overlayView, SideFpsOverlayViewModel.getDefaultOverlayViewParams());
        View view4 = sideFpsOverlayViewBinder.overlayView;
        Intrinsics.checkNotNull(view4);
        view4.announceForAccessibility(sideFpsOverlayViewBinder.applicationContext.getResources().getString(R.string.accessibility_side_fingerprint_indicator_label));
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        BuildersKt.launch$default(this.applicationScope, null, null, new SideFpsOverlayViewBinder$start$1(this, null), 3);
    }
}
