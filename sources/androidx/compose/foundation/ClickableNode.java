package androidx.compose.foundation;

import android.view.KeyEvent;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputScope;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class ClickableNode extends AbstractClickableNode {
    @Override // androidx.compose.foundation.AbstractClickableNode
    public final Object clickPointerInput(PointerInputScope pointerInputScope, Continuation continuation) {
        Object detectTapAndPress = TapGestureDetectorKt.detectTapAndPress(pointerInputScope, new ClickableNode$clickPointerInput$2(this, null), new Function1() { // from class: androidx.compose.foundation.ClickableNode$clickPointerInput$3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                long j = ((Offset) obj).packedValue;
                ClickableNode clickableNode = ClickableNode.this;
                if (clickableNode.enabled) {
                    clickableNode.onClick.invoke();
                }
                return Unit.INSTANCE;
            }
        }, continuation);
        return detectTapAndPress == CoroutineSingletons.COROUTINE_SUSPENDED ? detectTapAndPress : Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.AbstractClickableNode
    /* renamed from: onClickKeyDownEvent-ZmokQxo */
    public final boolean mo12onClickKeyDownEventZmokQxo(KeyEvent keyEvent) {
        return false;
    }

    @Override // androidx.compose.foundation.AbstractClickableNode
    /* renamed from: onClickKeyUpEvent-ZmokQxo */
    public final void mo13onClickKeyUpEventZmokQxo(KeyEvent keyEvent) {
        this.onClick.invoke();
    }
}
