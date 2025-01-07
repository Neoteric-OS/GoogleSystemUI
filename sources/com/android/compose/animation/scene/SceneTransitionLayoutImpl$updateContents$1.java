package com.android.compose.animation.scene;

import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.compose.runtime.SnapshotMutableFloatStateImpl;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.ui.unit.LayoutDirection;
import com.android.compose.animation.scene.UserAction;
import com.android.compose.animation.scene.UserActionResult;
import com.android.compose.animation.scene.content.Scene;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlin.jvm.internal.Ref$FloatRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SceneTransitionLayoutImpl$updateContents$1 {
    public final /* synthetic */ LayoutDirection $layoutDirection;
    public final /* synthetic */ Ref$BooleanRef $overlaysDefined;
    public final /* synthetic */ Set $overlaysToRemove;
    public final /* synthetic */ Set $scenesToRemove;
    public final /* synthetic */ Ref$FloatRef $zIndex;
    public final /* synthetic */ SceneTransitionLayoutImpl this$0;

    public SceneTransitionLayoutImpl$updateContents$1(Ref$BooleanRef ref$BooleanRef, Set set, SceneTransitionLayoutImpl sceneTransitionLayoutImpl, LayoutDirection layoutDirection, Ref$FloatRef ref$FloatRef, Set set2) {
        this.$overlaysDefined = ref$BooleanRef;
        this.$scenesToRemove = set;
        this.this$0 = sceneTransitionLayoutImpl;
        this.$layoutDirection = layoutDirection;
        this.$zIndex = ref$FloatRef;
    }

    public final void scene(SceneKey sceneKey, Map map, ComposableLambdaImpl composableLambdaImpl) {
        if (this.$overlaysDefined.element) {
            throw new IllegalArgumentException("all scenes must be defined before overlays");
        }
        this.$scenesToRemove.remove(sceneKey);
        LayoutDirection layoutDirection = this.$layoutDirection;
        SceneTransitionLayoutImpl sceneTransitionLayoutImpl = this.this$0;
        sceneTransitionLayoutImpl.getClass();
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            linkedHashMap.put(((UserAction) entry.getKey()).resolve$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(layoutDirection), entry.getValue());
        }
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            UserAction.Resolved resolved = (UserAction.Resolved) entry2.getKey();
            UserActionResult userActionResult = (UserActionResult) entry2.getValue();
            if (userActionResult instanceof UserActionResult.ChangeScene) {
                if (Intrinsics.areEqual(sceneKey, ((UserActionResult.ChangeScene) userActionResult).toScene)) {
                    throw new IllegalStateException(("Transition to the same scene is not supported. " + SceneTransitionLayoutImpl.checkUserActions$lambda$12$details(sceneKey, resolved, userActionResult)).toString());
                }
            } else {
                if (userActionResult instanceof UserActionResult.ReplaceByOverlay) {
                    throw new IllegalStateException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("ReplaceByOverlay() can only be used for overlays, not scenes. ", SceneTransitionLayoutImpl.checkUserActions$lambda$12$details(sceneKey, resolved, userActionResult)).toString());
                }
                if (!(userActionResult instanceof UserActionResult.ShowOverlay)) {
                    boolean z = userActionResult instanceof UserActionResult.HideOverlay;
                }
            }
        }
        Scene scene = (Scene) sceneTransitionLayoutImpl.scenes.get(sceneKey);
        Ref$FloatRef ref$FloatRef = this.$zIndex;
        if (scene != null) {
            ((SnapshotMutableStateImpl) scene.content$delegate).setValue(composableLambdaImpl);
            ((SnapshotMutableStateImpl) scene.userActions$delegate).setValue(linkedHashMap);
            ((SnapshotMutableFloatStateImpl) scene.zIndex$delegate).setFloatValue(ref$FloatRef.element);
        } else {
            sceneTransitionLayoutImpl.scenes.put(sceneKey, new Scene(sceneKey, sceneTransitionLayoutImpl, composableLambdaImpl, linkedHashMap, ref$FloatRef.element));
        }
        ref$FloatRef.element += 1.0f;
    }
}
