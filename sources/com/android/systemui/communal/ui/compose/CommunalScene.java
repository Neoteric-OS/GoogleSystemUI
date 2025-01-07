package com.android.systemui.communal.ui.compose;

import com.android.compose.animation.scene.SceneKey;
import com.android.compose.animation.scene.Swipe;
import com.android.compose.animation.scene.SwipeDirection;
import com.android.systemui.communal.ui.viewmodel.CommunalViewModel;
import com.android.systemui.communal.util.CommunalColors;
import com.android.systemui.lifecycle.ExclusiveActivatable;
import com.android.systemui.scene.shared.model.Scenes;
import com.android.systemui.scene.ui.composable.Scene;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalScene extends ExclusiveActivatable implements Scene {
    public final CommunalColors communalColors;
    public final CommunalContent communalContent;
    public final SceneKey key;
    public final ReadonlyStateFlow userActions;
    public final CommunalViewModel viewModel;

    public CommunalScene(CommunalViewModel communalViewModel, CommunalColors communalColors, CommunalContent communalContent) {
        SceneKey sceneKey = Scenes.Communal;
        new ReadonlyStateFlow(StateFlowKt.MutableStateFlow(MapsKt__MapsJVMKt.mapOf(new Swipe(SwipeDirection.End, null, 6).to(Scenes.Lockscreen))));
    }
}
