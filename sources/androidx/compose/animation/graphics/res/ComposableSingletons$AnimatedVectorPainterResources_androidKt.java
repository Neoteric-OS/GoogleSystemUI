package androidx.compose.animation.graphics.res;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.ui.graphics.vector.VectorGroup;
import androidx.compose.ui.graphics.vector.VectorPainterKt;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ComposableSingletons$AnimatedVectorPainterResources_androidKt {

    /* renamed from: lambda-1, reason: not valid java name */
    public static final ComposableLambdaImpl f0lambda1 = new ComposableLambdaImpl(-869223072, false, new Function4() { // from class: androidx.compose.animation.graphics.res.ComposableSingletons$AnimatedVectorPainterResources_androidKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function4
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            int intValue = ((Number) obj4).intValue();
            OpaqueKey opaqueKey = ComposerKt.invocation;
            VectorPainterKt.RenderVectorGroup((VectorGroup) obj, (Map) obj2, (Composer) obj3, intValue & 126, 0);
            return Unit.INSTANCE;
        }
    });
}
