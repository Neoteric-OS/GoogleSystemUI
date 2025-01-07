package com.android.systemui.plugins.statusbar;

import com.android.systemui.plugins.annotations.DependsOn;
import com.android.systemui.plugins.annotations.ProvidesInterface;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
@ProvidesInterface(version = 1)
@DependsOn(target = StateListener.class)
/* loaded from: classes.dex */
public interface StatusBarStateController {
    public static final int VERSION = 1;

    void addCallback(StateListener stateListener);

    float getDozeAmount();

    int getState();

    boolean isDozing();

    boolean isDreaming();

    boolean isExpanded();

    boolean isPulsing();

    void removeCallback(StateListener stateListener);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    @ProvidesInterface(version = 1)
    public interface StateListener {
        public static final int VERSION = 1;

        default void onStatePostChange() {
        }

        default void onDozingChanged(boolean z) {
        }

        default void onDreamingChanged(boolean z) {
        }

        default void onExpandedChanged(boolean z) {
        }

        default void onPulsingChanged(boolean z) {
        }

        default void onStateChanged(int i) {
        }

        default void onUpcomingStateChanged(int i) {
        }

        default void onDozeAmountChanged(float f, float f2) {
        }

        default void onStatePreChange(int i, int i2) {
        }
    }
}
