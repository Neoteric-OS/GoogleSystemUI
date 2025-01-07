package com.android.compose.animation.scene.transformation;

import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.IntSize;
import com.android.compose.animation.scene.ContentKey;
import com.android.compose.animation.scene.Edge;
import com.android.compose.animation.scene.Element;
import com.android.compose.animation.scene.ElementKey;
import com.android.compose.animation.scene.ElementMatcher;
import com.android.compose.animation.scene.SceneTransitionLayoutImpl;
import com.android.compose.animation.scene.content.state.TransitionState;
import kotlin.NoWhenBranchMatchedException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class EdgeTranslate implements PropertyTransformation {
    public final Edge edge;
    public final ElementKey matcher;
    public final boolean startsOutsideLayoutBounds;

    public EdgeTranslate(ElementKey elementKey, Edge edge, boolean z) {
        this.matcher = elementKey;
        this.edge = edge;
        this.startsOutsideLayoutBounds = z;
    }

    @Override // com.android.compose.animation.scene.transformation.Transformation
    public final ElementMatcher getMatcher() {
        return this.matcher;
    }

    @Override // com.android.compose.animation.scene.transformation.PropertyTransformation
    public final Object transform(SceneTransitionLayoutImpl sceneTransitionLayoutImpl, ContentKey contentKey, Element element, Element.State state, TransitionState.Transition transition, Object obj) {
        long j;
        long j2;
        long floatToRawIntBits;
        int floatToRawIntBits2;
        long j3;
        long j4 = ((IntSize) ((SnapshotMutableStateImpl) sceneTransitionLayoutImpl.content$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(contentKey).targetSize$delegate).getValue()).packedValue;
        long m730getTargetSizeYbymL2g = state.m730getTargetSizeYbymL2g();
        boolean m683equalsimpl0 = IntSize.m683equalsimpl0(m730getTargetSizeYbymL2g, Element.SizeUnspecified);
        long j5 = ((Offset) obj).packedValue;
        if (!m683equalsimpl0) {
            int ordinal = this.edge.resolve(sceneTransitionLayoutImpl.layoutDirection).ordinal();
            boolean z = this.startsOutsideLayoutBounds;
            if (ordinal != 0) {
                if (ordinal != 1) {
                    if (ordinal != 2) {
                        if (ordinal != 3) {
                            throw new NoWhenBranchMatchedException();
                        }
                        if (z) {
                            float f = (int) (j4 & 4294967295L);
                            floatToRawIntBits = Float.floatToRawIntBits(Float.intBitsToFloat((int) (j5 >> 32)));
                            floatToRawIntBits2 = Float.floatToRawIntBits(f);
                        } else {
                            float f2 = ((int) (j4 & 4294967295L)) - ((int) (m730getTargetSizeYbymL2g & 4294967295L));
                            floatToRawIntBits = Float.floatToRawIntBits(Float.intBitsToFloat((int) (j5 >> 32)));
                            floatToRawIntBits2 = Float.floatToRawIntBits(f2);
                        }
                    } else if (z) {
                        floatToRawIntBits = Float.floatToRawIntBits(Float.intBitsToFloat((int) (j5 >> 32)));
                        floatToRawIntBits2 = Float.floatToRawIntBits(-((int) (m730getTargetSizeYbymL2g & 4294967295L)));
                    } else {
                        j = Float.floatToRawIntBits(Float.intBitsToFloat((int) (j5 >> 32))) << 32;
                        j2 = Float.floatToRawIntBits(0.0f) & 4294967295L;
                        j3 = j | j2;
                    }
                } else if (z) {
                    float intBitsToFloat = Float.intBitsToFloat((int) (j5 & 4294967295L));
                    floatToRawIntBits = Float.floatToRawIntBits((int) (j4 >> 32));
                    floatToRawIntBits2 = Float.floatToRawIntBits(intBitsToFloat);
                } else {
                    float intBitsToFloat2 = Float.intBitsToFloat((int) (j5 & 4294967295L));
                    floatToRawIntBits = Float.floatToRawIntBits(((int) (j4 >> 32)) - ((int) (m730getTargetSizeYbymL2g >> 32)));
                    floatToRawIntBits2 = Float.floatToRawIntBits(intBitsToFloat2);
                }
                j3 = (floatToRawIntBits2 & 4294967295L) | (floatToRawIntBits << 32);
            } else if (z) {
                float intBitsToFloat3 = Float.intBitsToFloat((int) (j5 & 4294967295L));
                floatToRawIntBits = Float.floatToRawIntBits(-((int) (m730getTargetSizeYbymL2g >> 32)));
                floatToRawIntBits2 = Float.floatToRawIntBits(intBitsToFloat3);
                j3 = (floatToRawIntBits2 & 4294967295L) | (floatToRawIntBits << 32);
            } else {
                float intBitsToFloat4 = Float.intBitsToFloat((int) (j5 & 4294967295L));
                long floatToRawIntBits3 = Float.floatToRawIntBits(0.0f);
                long floatToRawIntBits4 = Float.floatToRawIntBits(intBitsToFloat4);
                j = floatToRawIntBits3 << 32;
                j2 = floatToRawIntBits4 & 4294967295L;
                j3 = j | j2;
            }
            j5 = j3;
        }
        return new Offset(j5);
    }
}
