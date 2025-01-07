package androidx.compose.ui.graphics.vector;

import androidx.compose.foundation.BorderModifierNode$drawRoundRectBorder$1$$ExternalSyntheticOutline0;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope$drawContext$1;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class VectorComponent$drawVectorBlock$1 extends Lambda implements Function1 {
    final /* synthetic */ VectorComponent this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VectorComponent$drawVectorBlock$1(VectorComponent vectorComponent) {
        super(1);
        this.this$0 = vectorComponent;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        DrawScope drawScope = (DrawScope) obj;
        VectorComponent vectorComponent = this.this$0;
        GroupComponent groupComponent = vectorComponent.root;
        float f = vectorComponent.rootScaleX;
        float f2 = vectorComponent.rootScaleY;
        CanvasDrawScope$drawContext$1 drawContext = drawScope.getDrawContext();
        long m418getSizeNHjbRc = drawContext.m418getSizeNHjbRc();
        drawContext.getCanvas().save();
        try {
            drawContext.transform.m421scale0AR0LA0(f, f2, 0L);
            groupComponent.draw(drawScope);
            BorderModifierNode$drawRoundRectBorder$1$$ExternalSyntheticOutline0.m(drawContext, m418getSizeNHjbRc);
            return Unit.INSTANCE;
        } catch (Throwable th) {
            BorderModifierNode$drawRoundRectBorder$1$$ExternalSyntheticOutline0.m(drawContext, m418getSizeNHjbRc);
            throw th;
        }
    }
}
