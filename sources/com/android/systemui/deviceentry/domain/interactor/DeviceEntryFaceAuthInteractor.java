package com.android.systemui.deviceentry.domain.interactor;

import com.android.systemui.CoreStartable;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface DeviceEntryFaceAuthInteractor extends CoreStartable {
    boolean canFaceAuthRun();

    Flow getAuthenticationStatus();

    StateFlow isAuthenticated();

    Flow isBypassEnabled();

    boolean isFaceAuthEnabledAndEnrolled();

    boolean isFaceAuthStrong();

    StateFlow isLockedOut();

    boolean isRunning();

    void onAccessibilityAction();

    void onDeviceLifted();

    void onNotificationPanelClicked();

    void onPrimaryBouncerUserInput();

    void onShadeExpansionStarted();

    void onSwipeUpOnBouncer();

    void onUdfpsSensorTouched();

    void onWalletLaunched();
}
