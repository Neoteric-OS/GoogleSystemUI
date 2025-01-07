package com.android.systemui.statusbar.phone;

import com.android.systemui.shade.ShadeViewController;
import com.android.systemui.statusbar.LightRevealScrim;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface ScreenOffAnimation {
    void initialize(CentralSurfaces centralSurfaces, ShadeViewController shadeViewController, LightRevealScrim lightRevealScrim);

    boolean isAnimationPlaying();

    default boolean isKeyguardHideDelayed() {
        return false;
    }

    default boolean isKeyguardShowDelayed() {
        return false;
    }

    default boolean overrideNotificationsDozeAmount() {
        return false;
    }

    default boolean shouldAnimateClockChange() {
        return true;
    }

    default boolean shouldAnimateDozingChange() {
        return true;
    }

    boolean shouldDelayDisplayDozeTransition();

    default boolean shouldDelayKeyguardShow() {
        return false;
    }

    default boolean shouldHideScrimOnWakeUp() {
        return false;
    }

    boolean shouldPlayAnimation();

    boolean shouldShowAodIconsWhenShade();

    boolean startAnimation();

    default void onAlwaysOnChanged(boolean z) {
    }

    default void onScrimOpaqueChanged(boolean z) {
    }
}
