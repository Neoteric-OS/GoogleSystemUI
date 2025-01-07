package com.android.compose.animation.scene;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PredictiveBackHandlerKt {
    public static final void PredictiveBackHandler(final SceneTransitionLayoutImpl sceneTransitionLayoutImpl, final UserActionResult userActionResult, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1608346907);
        if ((i & 14) == 0) {
            i2 = (composerImpl.changed(sceneTransitionLayoutImpl) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerImpl.changed(userActionResult) ? 32 : 16;
        }
        if ((i2 & 91) == 18 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            boolean z = userActionResult != null;
            composerImpl.startReplaceGroup(314624835);
            boolean z2 = ((i2 & 112) == 32) | ((i2 & 14) == 4);
            Object rememberedValue = composerImpl.rememberedValue();
            if (z2 || rememberedValue == Composer.Companion.Empty) {
                rememberedValue = new PredictiveBackHandlerKt$PredictiveBackHandler$1$1(userActionResult, sceneTransitionLayoutImpl, null);
                composerImpl.updateRememberedValue(rememberedValue);
            }
            composerImpl.end(false);
            androidx.activity.compose.PredictiveBackHandlerKt.PredictiveBackHandler(z, (Function2) rememberedValue, composerImpl, 64, 0);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.compose.animation.scene.PredictiveBackHandlerKt$PredictiveBackHandler$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    PredictiveBackHandlerKt.PredictiveBackHandler(SceneTransitionLayoutImpl.this, userActionResult, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
