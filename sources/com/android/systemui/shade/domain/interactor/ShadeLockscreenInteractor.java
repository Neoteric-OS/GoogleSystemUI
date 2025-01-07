package com.android.systemui.shade.domain.interactor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface ShadeLockscreenInteractor {
    void blockExpansionForCurrentTouch();

    void dozeTimeTick();

    void expandToNotifications();

    boolean isExpanded();

    void resetViewGroupFade();

    void resetViews(boolean z);

    void setKeyguardStatusBarAlpha(float f);

    void setKeyguardTransitionProgress(int i, float f);

    void setOverStretchAmount(float f);

    void setPulsing(boolean z);

    void showAodUi();

    void startBouncerPreHideAnimation();

    void transitionToExpandedShade(long j);
}
