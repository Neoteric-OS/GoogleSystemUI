package androidx.compose.ui;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.ui.Modifier;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ComposedModifierKt {
    public static final Modifier composed(Modifier modifier, Function3 function3) {
        return modifier.then(new ComposedModifier(function3));
    }

    public static final Modifier materializeImpl(Composer composer, Modifier modifier) {
        if (modifier.all(new Function1() { // from class: androidx.compose.ui.ComposedModifierKt$materializeImpl$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(!(((Modifier.Element) obj) instanceof ComposedModifier));
            }
        })) {
            return modifier;
        }
        final ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(1219399079);
        Modifier modifier2 = (Modifier) modifier.foldIn(Modifier.Companion.$$INSTANCE, new Function2() { // from class: androidx.compose.ui.ComposedModifierKt$materializeImpl$result$1
            {
                super(2);
            }

            /* JADX WARN: Type inference failed for: r5v4, types: [java.lang.Object, kotlin.jvm.functions.Function3, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Modifier modifier3 = (Modifier) obj;
                Modifier modifier4 = (Modifier.Element) obj2;
                if (modifier4 instanceof ComposedModifier) {
                    ?? r5 = ((ComposedModifier) modifier4).factory;
                    TypeIntrinsics.beforeCheckcastToFunctionOfArity(3, r5);
                    modifier4 = ComposedModifierKt.materializeImpl(Composer.this, (Modifier) r5.invoke(Modifier.Companion.$$INSTANCE, Composer.this, 0));
                }
                return modifier3.then(modifier4);
            }
        });
        composerImpl.end(false);
        return modifier2;
    }

    public static final Modifier materializeModifier(Composer composer, Modifier modifier) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(439770924);
        Modifier materializeImpl = materializeImpl(composerImpl, modifier);
        composerImpl.end(false);
        return materializeImpl;
    }
}
