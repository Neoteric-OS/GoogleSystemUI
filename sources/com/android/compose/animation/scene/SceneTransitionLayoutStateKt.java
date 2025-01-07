package com.android.compose.animation.scene;

import kotlin.collections.EmptyList;
import kotlin.collections.EmptySet;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SceneTransitionLayoutStateKt {
    public static MutableSceneTransitionLayoutStateImpl MutableSceneTransitionLayoutState$default(SceneKey sceneKey, SceneTransitions sceneTransitions, Function1 function1, int i) {
        EmptySet emptySet = EmptySet.INSTANCE;
        if ((i & 8) != 0) {
            function1 = new Function1() { // from class: com.android.compose.animation.scene.SceneTransitionLayoutStateKt$MutableSceneTransitionLayoutState$1
                @Override // kotlin.jvm.functions.Function1
                public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return Boolean.TRUE;
                }
            };
        }
        return new MutableSceneTransitionLayoutStateImpl(sceneKey, sceneTransitions, emptySet, function1, new Function1() { // from class: com.android.compose.animation.scene.SceneTransitionLayoutStateKt$MutableSceneTransitionLayoutState$2
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return Boolean.TRUE;
            }
        }, new Function1() { // from class: com.android.compose.animation.scene.SceneTransitionLayoutStateKt$MutableSceneTransitionLayoutState$3
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return Boolean.TRUE;
            }
        }, new Function2() { // from class: com.android.compose.animation.scene.SceneTransitionLayoutStateKt$MutableSceneTransitionLayoutState$4
            @Override // kotlin.jvm.functions.Function2
            public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return Boolean.TRUE;
            }
        }, EmptyList.INSTANCE, (i & 256) != 0);
    }
}
