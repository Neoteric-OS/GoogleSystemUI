package androidx.compose.animation;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.VisibilityThresholdsKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.ui.unit.IntSize;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ContentTransform {
    public final ExitTransition initialContentExit;
    public SizeTransform sizeTransform;
    public final EnterTransition targetContentEnter;
    public final MutableFloatState targetContentZIndex$delegate;

    public ContentTransform(EnterTransition enterTransition, ExitTransition exitTransition) {
        SizeTransformImpl sizeTransformImpl = new SizeTransformImpl(new Function2() { // from class: androidx.compose.animation.AnimatedContentKt$SizeTransform$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                long j = ((IntSize) obj).packedValue;
                long j2 = ((IntSize) obj2).packedValue;
                return AnimationSpecKt.spring$default(0.0f, 400.0f, new IntSize(VisibilityThresholdsKt.getVisibilityThreshold$1()), 1);
            }
        });
        this.targetContentEnter = enterTransition;
        this.initialContentExit = exitTransition;
        this.targetContentZIndex$delegate = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.sizeTransform = sizeTransformImpl;
    }
}
