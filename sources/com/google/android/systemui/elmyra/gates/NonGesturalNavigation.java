package com.google.android.systemui.elmyra.gates;

import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.shared.system.QuickStepContract;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NonGesturalNavigation extends Gate {
    public boolean mCurrentModeIsGestural;
    public final NavigationModeController mModeController;
    public final AnonymousClass1 mModeListener;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.android.systemui.elmyra.gates.NonGesturalNavigation$1] */
    public NonGesturalNavigation(Executor executor, NavigationModeController navigationModeController) {
        super(executor);
        this.mModeListener = new NavigationModeController.ModeChangedListener() { // from class: com.google.android.systemui.elmyra.gates.NonGesturalNavigation.1
            @Override // com.android.systemui.navigationbar.NavigationModeController.ModeChangedListener
            public final void onNavigationModeChanged(int i) {
                boolean isGesturalMode = QuickStepContract.isGesturalMode(i);
                NonGesturalNavigation nonGesturalNavigation = NonGesturalNavigation.this;
                nonGesturalNavigation.mCurrentModeIsGestural = isGesturalMode;
                nonGesturalNavigation.notifyListener();
            }
        };
        this.mModeController = navigationModeController;
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final boolean isBlocked() {
        return !this.mCurrentModeIsGestural;
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final void onActivate() {
        this.mCurrentModeIsGestural = QuickStepContract.isGesturalMode(this.mModeController.addListener(this.mModeListener));
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final void onDeactivate() {
        this.mModeController.removeListener(this.mModeListener);
    }
}
