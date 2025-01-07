package com.android.systemui.statusbar.policy;

import com.android.systemui.Dumpable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface KeyguardStateController extends CallbackController, Dumpable {
    default boolean isUnlocked() {
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this;
        return !keyguardStateControllerImpl.mShowing || keyguardStateControllerImpl.mCanDismissLockScreen;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Callback {
        default void onFaceEnrolledChanged() {
        }

        default void onKeyguardDismissAmountChanged() {
        }

        default void onKeyguardFadingAwayChanged() {
        }

        default void onKeyguardGoingAwayChanged() {
        }

        default void onKeyguardShowingChanged() {
        }

        default void onLaunchTransitionFadingAwayChanged() {
        }

        default void onPrimaryBouncerShowingChanged() {
        }

        default void onUnlockedChanged() {
        }
    }
}
