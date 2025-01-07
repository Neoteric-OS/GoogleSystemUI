package com.android.systemui.unfold;

import com.android.systemui.unfold.util.CallbackController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface UnfoldTransitionProgressProvider extends CallbackController {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface TransitionProgressListener {
        default void onTransitionFinished() {
        }

        default void onTransitionFinishing() {
        }

        default void onTransitionProgress(float f) {
        }

        default void onTransitionStarted() {
        }
    }
}
