package com.android.systemui.shade.domain.interactor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface ShadeBackActionInteractor {
    void animateCollapseQs(boolean z);

    boolean canBeCollapsed();

    boolean closeUserSwitcherIfOpen();

    void onBackPressed();
}
