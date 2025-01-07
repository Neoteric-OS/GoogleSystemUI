package com.android.compose.animation.scene;

import androidx.compose.ui.geometry.Offset;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class SwipeToSceneNode$multiPointerDraggableNode$3 extends FunctionReferenceImpl implements Function3 {
    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        float floatValue = ((Number) obj2).floatValue();
        int intValue = ((Number) obj3).intValue();
        return ((DraggableHandlerImpl) this.receiver).m727onDragStartedMjzGXtM((Offset) obj, floatValue, intValue);
    }
}
