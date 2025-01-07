package com.android.compose.animation.scene;

import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.ApproachLayoutModifierNode;
import androidx.compose.ui.layout.ApproachMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutAwareModifierNode;
import androidx.compose.ui.unit.IntSize;
import com.android.compose.animation.scene.content.state.TransitionState;
import com.android.compose.ui.util.MathHelpersKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LayoutNode extends Modifier.Node implements ApproachLayoutModifierNode, LayoutAwareModifierNode {
    public SceneTransitionLayoutImpl layoutImpl;

    @Override // androidx.compose.ui.layout.ApproachLayoutModifierNode
    /* renamed from: approachMeasure-3p2s80s */
    public final MeasureResult mo472approachMeasure3p2s80s(ApproachMeasureScope approachMeasureScope, Measurable measurable, long j) {
        int i;
        int i2;
        MeasureResult layout$1;
        final Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(j);
        TransitionState.Transition currentTransition = this.layoutImpl.state.getCurrentTransition();
        TransitionState.Transition.ChangeScene changeScene = currentTransition instanceof TransitionState.Transition.ChangeScene ? (TransitionState.Transition.ChangeScene) currentTransition : null;
        if (changeScene == null) {
            i = mo479measureBRTryo0.width;
            i2 = mo479measureBRTryo0.height;
        } else {
            long j2 = ((IntSize) ((SnapshotMutableStateImpl) this.layoutImpl.scene$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(changeScene.fromScene).targetSize$delegate).getValue()).packedValue;
            SceneTransitionLayoutImpl sceneTransitionLayoutImpl = this.layoutImpl;
            SceneKey sceneKey = changeScene.toScene;
            long j3 = ((IntSize) ((SnapshotMutableStateImpl) sceneTransitionLayoutImpl.scene$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(sceneKey).targetSize$delegate).getValue()).packedValue;
            if (IntSize.m683equalsimpl0(j2, j3)) {
                i = (int) (j2 >> 32);
                i2 = (int) (j2 & 4294967295L);
            } else {
                OverscrollSpecImpl currentOverscrollSpec$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout = changeScene.getCurrentOverscrollSpec$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout();
                long m746lerpe0twbBA = MathHelpersKt.m746lerpe0twbBA(currentOverscrollSpec$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout == null ? changeScene.getProgress() : Intrinsics.areEqual(currentOverscrollSpec$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout.content, sceneKey) ? 1.0f : 0.0f, j2, j3);
                i = (int) (m746lerpe0twbBA >> 32);
                if (i < 0) {
                    i = 0;
                }
                i2 = (int) (m746lerpe0twbBA & 4294967295L);
                if (i2 < 0) {
                    i2 = 0;
                }
            }
        }
        layout$1 = approachMeasureScope.layout$1(i, i2, MapsKt.emptyMap(), new Function1() { // from class: com.android.compose.animation.scene.LayoutNode$approachMeasure$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ((Placeable.PlacementScope) obj).place(Placeable.this, 0, 0, 0.0f);
                return Unit.INSTANCE;
            }
        });
        return layout$1;
    }

    @Override // androidx.compose.ui.layout.ApproachLayoutModifierNode
    /* renamed from: isMeasurementApproachInProgress-ozmzZPI */
    public final boolean mo473isMeasurementApproachInProgressozmzZPI(long j) {
        return SceneTransitionLayoutState.isTransitioning$default(this.layoutImpl.state, null, 3);
    }

    @Override // androidx.compose.ui.node.LayoutAwareModifierNode
    /* renamed from: onRemeasured-ozmzZPI */
    public final void mo43onRemeasuredozmzZPI(long j) {
        this.layoutImpl.lastSize = j;
    }
}
