package com.android.systemui.statusbar.policy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface ZenModeController extends CallbackController {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Callback {
        void onZenChanged(int i);

        default void onConfigChanged$1() {
        }

        default void onConsolidatedPolicyChanged() {
        }

        default void onNextAlarmChanged() {
        }

        default void onZenAvailableChanged(boolean z) {
        }
    }
}
