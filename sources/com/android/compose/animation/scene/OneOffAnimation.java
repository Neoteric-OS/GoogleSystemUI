package com.android.compose.animation.scene;

import androidx.compose.animation.core.Animatable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class OneOffAnimation {
    public Animatable animatable;
    public Function1 onRun;

    public final float getProgress() {
        Animatable animatable = this.animatable;
        if (animatable == null) {
            animatable = null;
        }
        return ((Number) animatable.internalState.getValue()).floatValue();
    }

    public final Object run(Continuation continuation) {
        Function1 function1 = this.onRun;
        if (function1 == null) {
            function1 = null;
        }
        Object invoke = ((AnimateContentKt$animateContent$1) function1).invoke(continuation);
        return invoke == CoroutineSingletons.COROUTINE_SUSPENDED ? invoke : Unit.INSTANCE;
    }
}
