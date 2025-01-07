package com.android.systemui.scene.domain.interactor;

import com.android.compose.animation.scene.SceneKey;
import com.android.systemui.scene.data.model.EmptyStack;
import com.android.systemui.scene.data.model.SceneStack;
import com.android.systemui.scene.data.model.StackedNodes;
import com.android.systemui.scene.shared.logger.SceneLogger;
import com.android.systemui.scene.shared.model.SceneContainerConfig;
import java.util.Collections;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.EmptyList;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SceneBackInteractor {
    public final StateFlowImpl _backStack;
    public final SceneBackInteractor$special$$inlined$map$1 backScene;
    public final ReadonlyStateFlow backStack;
    public final SceneLogger logger;
    public final SceneContainerConfig sceneContainerConfig;

    public SceneBackInteractor(SceneLogger sceneLogger, SceneContainerConfig sceneContainerConfig) {
        List mutableList;
        SceneKey[] sceneKeyArr = new SceneKey[0];
        SceneStack sceneStack = EmptyStack.INSTANCE;
        if (sceneKeyArr.length == 0) {
            mutableList = EmptyList.INSTANCE;
        } else {
            mutableList = ArraysKt.toMutableList(sceneKeyArr);
            Collections.reverse(mutableList);
        }
        int size = mutableList.size();
        int i = 0;
        while (i < size) {
            SceneStack stackedNodes = new StackedNodes((SceneKey) mutableList.get(i), sceneStack);
            i++;
            sceneStack = stackedNodes;
        }
        ReadonlyStateFlow readonlyStateFlow = new ReadonlyStateFlow(StateFlowKt.MutableStateFlow(sceneStack));
        this.backStack = readonlyStateFlow;
        this.backScene = new SceneBackInteractor$special$$inlined$map$1(readonlyStateFlow);
    }
}
