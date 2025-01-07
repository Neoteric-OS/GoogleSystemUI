package com.android.compose.animation.scene;

import androidx.compose.ui.unit.Density;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class UserActionDistanceScopeImpl implements Density {
    public final /* synthetic */ ElementStateScopeImpl $$delegate_0;
    public final SceneTransitionLayoutImpl layoutImpl;

    public UserActionDistanceScopeImpl(SceneTransitionLayoutImpl sceneTransitionLayoutImpl) {
        this.layoutImpl = sceneTransitionLayoutImpl;
        ElementStateScopeImpl elementStateScopeImpl = sceneTransitionLayoutImpl.elementStateScope;
    }

    @Override // androidx.compose.ui.unit.Density
    public final float getDensity() {
        return this.layoutImpl.density.getDensity();
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public final float getFontScale() {
        return this.layoutImpl.density.getFontScale();
    }
}
