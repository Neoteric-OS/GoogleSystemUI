package com.android.systemui.shade;

import com.android.systemui.shade.domain.interactor.PanelExpansionInteractor;
import com.android.systemui.shade.domain.interactor.ShadeBackActionInteractor;
import com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda1;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda25;
import com.android.systemui.statusbar.policy.HeadsUpManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface ShadeSurface extends ShadeViewController, ShadeBackActionInteractor, ShadeLockscreenInteractor, PanelExpansionInteractor {
    void cancelAnimation();

    void cancelPendingCollapse();

    void fadeOut(CentralSurfacesImpl$$ExternalSyntheticLambda1 centralSurfacesImpl$$ExternalSyntheticLambda1);

    void initDependencies(CentralSurfacesImpl centralSurfacesImpl, CentralSurfacesImpl$$ExternalSyntheticLambda25 centralSurfacesImpl$$ExternalSyntheticLambda25, HeadsUpManager headsUpManager);

    void onThemeChanged();

    void resetAlpha();

    void resetTranslation();

    void setBouncerShowing(boolean z);

    void setDozing(boolean z, boolean z2);

    void setImportantForAccessibility(int i);

    void setTouchAndAnimationDisabled(boolean z);

    void setWillPlayDelayedDozeAmountAnimation();

    void updateExpansionAndVisibility();

    void updateResources();
}
