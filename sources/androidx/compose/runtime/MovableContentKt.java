package androidx.compose.runtime;

import androidx.compose.runtime.internal.ComposableLambdaImpl;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class MovableContentKt {
    public static final ComposableLambdaImpl movableContentOf(ComposableLambdaImpl composableLambdaImpl) {
        final MovableContent movableContent = new MovableContent(composableLambdaImpl);
        return new ComposableLambdaImpl(-434707029, true, new Function3() { // from class: androidx.compose.runtime.MovableContentKt$movableContentOf$2
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                Composer composer = (Composer) obj2;
                int intValue = ((Number) obj3).intValue();
                if ((intValue & 6) == 0) {
                    intValue |= (intValue & 8) == 0 ? ((ComposerImpl) composer).changed(obj) : ((ComposerImpl) composer).changedInstance(obj) ? 4 : 2;
                }
                if ((intValue & 19) == 18) {
                    ComposerImpl composerImpl = (ComposerImpl) composer;
                    if (composerImpl.getSkipping()) {
                        composerImpl.skipToGroupEnd();
                        return Unit.INSTANCE;
                    }
                }
                OpaqueKey opaqueKey = ComposerKt.invocation;
                ComposerImpl composerImpl2 = (ComposerImpl) composer;
                composerImpl2.invokeMovableContentLambda(MovableContent.this, composerImpl2.currentCompositionLocalScope(), obj, false);
                return Unit.INSTANCE;
            }
        });
    }
}
