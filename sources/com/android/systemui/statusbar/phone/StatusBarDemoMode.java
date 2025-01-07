package com.android.systemui.statusbar.phone;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import com.android.systemui.demomode.DemoMode;
import com.android.systemui.demomode.DemoModeCommandReceiver;
import com.android.systemui.demomode.DemoModeController;
import com.android.systemui.navigationbar.NavigationBarControllerImpl;
import com.android.systemui.statusbar.policy.Clock;
import com.android.systemui.util.ViewController;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarDemoMode extends ViewController implements DemoMode {
    public final Clock mClockView;
    public final DemoModeController mDemoModeController;
    public final int mDisplayId;
    public final NavigationBarControllerImpl mNavigationBarController;
    public final View mOperatorNameView;
    public final PhoneStatusBarTransitions mPhoneStatusBarTransitions;

    public StatusBarDemoMode(Clock clock, View view, DemoModeController demoModeController, PhoneStatusBarTransitions phoneStatusBarTransitions, NavigationBarControllerImpl navigationBarControllerImpl, int i) {
        super(clock);
        this.mClockView = clock;
        this.mOperatorNameView = view;
        this.mDemoModeController = demoModeController;
        this.mPhoneStatusBarTransitions = phoneStatusBarTransitions;
        this.mNavigationBarController = navigationBarControllerImpl;
        this.mDisplayId = i;
    }

    @Override // com.android.systemui.demomode.DemoMode
    public final List demoCommands() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("bars");
        arrayList.add("clock");
        arrayList.add("operator");
        return arrayList;
    }

    @Override // com.android.systemui.demomode.DemoModeCommandReceiver
    public final void dispatchDemoCommand(Bundle bundle, String str) {
        Clock clock;
        if (str.equals("clock") && (clock = this.mClockView) != null) {
            clock.dispatchDemoCommand(bundle, str);
        }
        if (str.equals("operator")) {
            KeyEvent.Callback callback = this.mOperatorNameView;
            if (callback instanceof DemoModeCommandReceiver) {
                ((DemoModeCommandReceiver) callback).dispatchDemoCommand(bundle, str);
            }
        }
        if (str.equals("bars")) {
            String string = bundle.getString("mode");
            int i = "opaque".equals(string) ? 4 : "translucent".equals(string) ? 2 : "semi-transparent".equals(string) ? 1 : "transparent".equals(string) ? 0 : "warning".equals(string) ? 5 : -1;
            if (i != -1) {
                PhoneStatusBarTransitions phoneStatusBarTransitions = this.mPhoneStatusBarTransitions;
                int i2 = phoneStatusBarTransitions.mMode;
                if (i2 != i) {
                    phoneStatusBarTransitions.mMode = i;
                    phoneStatusBarTransitions.onTransition(i2, i, true);
                }
                this.mNavigationBarController.transitionTo(this.mDisplayId, i);
            }
        }
    }

    @Override // com.android.systemui.demomode.DemoModeCommandReceiver
    public final void onDemoModeFinished() {
        Clock clock = this.mClockView;
        if (clock != null) {
            clock.onDemoModeFinished();
        }
        KeyEvent.Callback callback = this.mOperatorNameView;
        if (callback instanceof DemoModeCommandReceiver) {
            ((DemoModeCommandReceiver) callback).onDemoModeFinished();
        }
    }

    @Override // com.android.systemui.demomode.DemoModeCommandReceiver
    public final void onDemoModeStarted() {
        Clock clock = this.mClockView;
        if (clock != null) {
            clock.onDemoModeStarted();
        }
        KeyEvent.Callback callback = this.mOperatorNameView;
        if (callback instanceof DemoModeCommandReceiver) {
            ((DemoModeCommandReceiver) callback).onDemoModeStarted();
        }
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
        this.mDemoModeController.addCallback((DemoMode) this);
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
        this.mDemoModeController.removeCallback((DemoMode) this);
    }
}
