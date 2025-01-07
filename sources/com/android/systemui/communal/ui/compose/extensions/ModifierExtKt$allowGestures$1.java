package com.android.systemui.communal.ui.compose.extensions;

import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ModifierExtKt$allowGestures$1 implements PointerInputEventHandler {
    public static final ModifierExtKt$allowGestures$1 INSTANCE = new ModifierExtKt$allowGestures$1();

    @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
    public final Object invoke(PointerInputScope pointerInputScope, Continuation continuation) {
        Object coroutineScope = CoroutineScopeKt.coroutineScope(continuation, new PointerInputScopeExtKt$consumeAllGestures$2(pointerInputScope, null));
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        Unit unit = Unit.INSTANCE;
        if (coroutineScope != coroutineSingletons) {
            coroutineScope = unit;
        }
        return coroutineScope == coroutineSingletons ? coroutineScope : unit;
    }
}
