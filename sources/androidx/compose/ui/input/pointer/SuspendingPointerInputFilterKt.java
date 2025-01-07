package androidx.compose.ui.input.pointer;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.material3.SliderState;
import androidx.compose.ui.Modifier;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SuspendingPointerInputFilterKt {
    public static final PointerEvent EmptyPointerEvent = new PointerEvent(EmptyList.INSTANCE, null);

    public static final SuspendingPointerInputModifierNodeImpl SuspendingPointerInputModifierNode(PointerInputEventHandler pointerInputEventHandler) {
        return new SuspendingPointerInputModifierNodeImpl(null, null, null, pointerInputEventHandler);
    }

    public static final Modifier pointerInput(Modifier modifier, Object obj, PointerInputEventHandler pointerInputEventHandler) {
        return modifier.then(new SuspendPointerInputElement(obj, null, null, pointerInputEventHandler, 6));
    }

    public static final Modifier pointerInput(SliderState sliderState, MutableInteractionSource mutableInteractionSource, Function2 function2) {
        return new SuspendPointerInputElement(sliderState, mutableInteractionSource, null, new SuspendingPointerInputFilterKt$sam$androidx_compose_ui_input_pointer_PointerInputEventHandler$0(function2), 4);
    }
}
