package com.android.systemui.communal.ui.compose;

import androidx.compose.runtime.DisposableEffectResult;
import com.android.systemui.communal.ui.viewmodel.CommunalViewModel;
import com.android.systemui.scene.shared.model.SceneDataSourceDelegator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalContainerKt$CommunalContainer$1$invoke$$inlined$onDispose$1 implements DisposableEffectResult {
    public final /* synthetic */ Object $dataSourceDelegator$inlined;
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ CommunalContainerKt$CommunalContainer$1$invoke$$inlined$onDispose$1(int i, Object obj) {
        this.$r8$classId = i;
        this.$dataSourceDelegator$inlined = obj;
    }

    @Override // androidx.compose.runtime.DisposableEffectResult
    public final void dispose() {
        switch (this.$r8$classId) {
            case 0:
                ((SceneDataSourceDelegator) this.$dataSourceDelegator$inlined).setDelegate(null);
                break;
            default:
                ((CommunalViewModel) this.$dataSourceDelegator$inlined).communalSceneInteractor.repository._transitionState.setValue(null);
                break;
        }
    }
}
