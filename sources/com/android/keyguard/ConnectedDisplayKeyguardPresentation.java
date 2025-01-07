package com.android.keyguard;

import android.app.Presentation;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.FrameLayout;
import com.android.keyguard.ClockEventController;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttached$1;
import com.android.systemui.plugins.clocks.ClockController;
import com.android.systemui.plugins.clocks.ClockFaceController;
import com.android.systemui.shared.clocks.ClockRegistry;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.android.systemui.statusbar.policy.ZenModeControllerImpl;
import com.android.systemui.util.Assert;
import com.android.systemui.util.ThreadAssert;
import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ConnectedDisplayKeyguardPresentation extends Presentation {
    public View clock;
    public final ConnectedDisplayKeyguardPresentation$clockChangedListener$1 clockChangedListener;
    public final ClockEventController clockEventController;
    public final ClockRegistry clockRegistry;
    public ClockFaceController faceController;
    public final ConnectedDisplayKeyguardPresentation$layoutChangeListener$1 layoutChangeListener;
    public FrameLayout rootView;

    /* JADX WARN: Type inference failed for: r2v1, types: [com.android.keyguard.ConnectedDisplayKeyguardPresentation$clockChangedListener$1] */
    /* JADX WARN: Type inference failed for: r2v2, types: [com.android.keyguard.ConnectedDisplayKeyguardPresentation$layoutChangeListener$1] */
    public ConnectedDisplayKeyguardPresentation(Display display, Context context, DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory, ClockRegistry clockRegistry, ClockEventController clockEventController) {
        super(context, display, R.style.Theme_SystemUI_KeyguardPresentation, 2009);
        this.clockRegistry = clockRegistry;
        this.clockEventController = clockEventController;
        this.clockChangedListener = new ClockRegistry.ClockChangeListener() { // from class: com.android.keyguard.ConnectedDisplayKeyguardPresentation$clockChangedListener$1
            @Override // com.android.systemui.shared.clocks.ClockRegistry.ClockChangeListener
            public final void onCurrentClockChanged() {
                ConnectedDisplayKeyguardPresentation connectedDisplayKeyguardPresentation = ConnectedDisplayKeyguardPresentation.this;
                connectedDisplayKeyguardPresentation.setClock(connectedDisplayKeyguardPresentation.clockRegistry.createCurrentClock());
            }
        };
        this.layoutChangeListener = new View.OnLayoutChangeListener() { // from class: com.android.keyguard.ConnectedDisplayKeyguardPresentation$layoutChangeListener$1
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                ConnectedDisplayKeyguardPresentation connectedDisplayKeyguardPresentation = ConnectedDisplayKeyguardPresentation.this;
                View view2 = connectedDisplayKeyguardPresentation.clock;
                if (view2 != null) {
                    ClockFaceController clockFaceController = connectedDisplayKeyguardPresentation.faceController;
                    if (clockFaceController == null) {
                        clockFaceController = null;
                    }
                    clockFaceController.getEvents().onTargetRegionChanged(new Rect(view2.getLeft(), view2.getTop(), view2.getWidth(), view2.getHeight()));
                }
            }
        };
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final void onAttachedToWindow() {
        ClockRegistry clockRegistry = this.clockRegistry;
        ConnectedDisplayKeyguardPresentation$clockChangedListener$1 connectedDisplayKeyguardPresentation$clockChangedListener$1 = this.clockChangedListener;
        ThreadAssert threadAssert = clockRegistry.f43assert;
        Assert.isMainThread();
        clockRegistry.clockChangeListeners.add(connectedDisplayKeyguardPresentation$clockChangedListener$1);
        ClockEventController clockEventController = this.clockEventController;
        View view = this.clock;
        Intrinsics.checkNotNull(view);
        clockEventController.registerListeners(view);
        ClockFaceController clockFaceController = this.faceController;
        if (clockFaceController == null) {
            clockFaceController = null;
        }
        clockFaceController.getAnimations().enter();
    }

    @Override // android.app.Dialog
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FrameLayout frameLayout = new FrameLayout(getContext(), null);
        this.rootView = frameLayout;
        frameLayout.setClipChildren(false);
        FrameLayout frameLayout2 = this.rootView;
        setContentView(frameLayout2 != null ? frameLayout2 : null);
        Window window = getWindow();
        if (window == null) {
            throw new IllegalStateException("no window available.");
        }
        window.getDecorView().setSystemUiVisibility(1792);
        window.getAttributes().setFitInsetsTypes(0);
        window.setNavigationBarContrastEnforced(false);
        window.setNavigationBarColor(0);
        setClock(this.clockRegistry.createCurrentClock());
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final void onDetachedFromWindow() {
        ViewTreeObserver viewTreeObserver;
        ClockEventController clockEventController = this.clockEventController;
        if (clockEventController.isRegistered) {
            clockEventController.isRegistered = false;
            RepeatWhenAttachedKt$repeatWhenAttached$1 repeatWhenAttachedKt$repeatWhenAttached$1 = clockEventController.disposableHandle;
            if (repeatWhenAttachedKt$repeatWhenAttached$1 != null) {
                repeatWhenAttachedKt$repeatWhenAttached$1.dispose();
            }
            clockEventController.broadcastDispatcher.unregisterReceiver(clockEventController.localeBroadcastReceiver);
            ((ConfigurationControllerImpl) clockEventController.configurationController).removeCallback(clockEventController.configListener);
            ((BatteryControllerImpl) clockEventController.batteryController).removeCallback(clockEventController.batteryCallback);
            clockEventController.keyguardUpdateMonitor.removeCallback(clockEventController.keyguardUpdateMonitorCallback);
            ((ZenModeControllerImpl) clockEventController.zenModeController).removeCallback(clockEventController.zenModeCallback);
            ClockEventController.TimeListener timeListener = clockEventController.smallTimeListener;
            if (timeListener != null) {
                timeListener.stop();
            }
            ClockEventController.TimeListener timeListener2 = clockEventController.largeTimeListener;
            if (timeListener2 != null) {
                timeListener2.stop();
            }
            ClockController clockController = clockEventController.clock;
            if (clockController != null) {
                clockController.getSmallClock().getView().removeOnAttachStateChangeListener(clockEventController.smallClockOnAttachStateChangeListener);
                clockController.getLargeClock().getView().removeOnAttachStateChangeListener(clockEventController.largeClockOnAttachStateChangeListener);
            }
            ViewGroup viewGroup = clockEventController.smallClockFrame;
            if (viewGroup != null && (viewTreeObserver = viewGroup.getViewTreeObserver()) != null) {
                viewTreeObserver.removeOnGlobalLayoutListener(clockEventController.onGlobalLayoutListener);
            }
        }
        this.clockRegistry.unregisterClockChangeListener(this.clockChangedListener);
        super.onDetachedFromWindow();
    }

    @Override // android.app.Presentation
    public final void onDisplayChanged() {
        Window window = getWindow();
        if (window == null) {
            throw new IllegalStateException("no window available.");
        }
        window.getDecorView().requestLayout();
    }

    public final void setClock(ClockController clockController) {
        View view = this.clock;
        if (view != null) {
            view.removeOnLayoutChangeListener(this.layoutChangeListener);
        }
        FrameLayout frameLayout = this.rootView;
        if (frameLayout == null) {
            frameLayout = null;
        }
        frameLayout.removeAllViews();
        ClockFaceController largeClock = clockController.getLargeClock();
        this.faceController = largeClock;
        if (largeClock == null) {
            largeClock = null;
        }
        View view2 = largeClock.getView();
        view2.addOnLayoutChangeListener(this.layoutChangeListener);
        this.clock = view2;
        FrameLayout frameLayout2 = this.rootView;
        if (frameLayout2 == null) {
            frameLayout2 = null;
        }
        frameLayout2.addView(view2, new FrameLayout.LayoutParams(getContext().getResources().getDimensionPixelSize(R.dimen.keyguard_presentation_width), -2, 17));
        this.clockEventController.setClock(clockController);
        ClockEventController clockEventController = this.clockEventController;
        clockEventController.largeClockOnSecondaryDisplay = true;
        clockEventController.updateFontSizes();
        ClockFaceController clockFaceController = this.faceController;
        (clockFaceController != null ? clockFaceController : null).getEvents().onSecondaryDisplayChanged(true);
    }
}
