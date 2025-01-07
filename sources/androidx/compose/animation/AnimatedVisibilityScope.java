package androidx.compose.animation;

import androidx.compose.animation.core.Transition;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface AnimatedVisibilityScope {
    default Modifier animateEnterExit(Modifier modifier, final EnterTransition enterTransition, final ExitTransition exitTransition) {
        Function1 function1 = InspectableValueKt.NoInspectorInfo;
        return ComposedModifierKt.composed(modifier, new Function3() { // from class: androidx.compose.animation.AnimatedVisibilityScope$animateEnterExit$2
            final /* synthetic */ String $label = "animateEnterExit";

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                ((Number) obj3).intValue();
                ComposerImpl composerImpl = (ComposerImpl) ((Composer) obj2);
                composerImpl.startReplaceGroup(1840112047);
                OpaqueKey opaqueKey = ComposerKt.invocation;
                Modifier then = ((Modifier) obj).then(EnterExitTransitionKt.createModifier(AnimatedVisibilityScope.this.getTransition(), enterTransition, exitTransition, this.$label, composerImpl, 0));
                composerImpl.end(false);
                return then;
            }
        });
    }

    Transition getTransition();
}
