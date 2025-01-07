package androidx.compose.ui.draw;

import androidx.compose.foundation.contextmenu.ContextMenuSpec;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ShadowGraphicsLayerElement$createBlock$1 extends Lambda implements Function1 {
    final /* synthetic */ ShadowGraphicsLayerElement this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShadowGraphicsLayerElement$createBlock$1(ShadowGraphicsLayerElement shadowGraphicsLayerElement) {
        super(1);
        this.this$0 = shadowGraphicsLayerElement;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        this.this$0.getClass();
        ReusableGraphicsLayerScope reusableGraphicsLayerScope = (ReusableGraphicsLayerScope) ((GraphicsLayerScope) obj);
        reusableGraphicsLayerScope.setShadowElevation(reusableGraphicsLayerScope.graphicsDensity.getDensity() * ContextMenuSpec.MenuContainerElevation);
        reusableGraphicsLayerScope.setShape(this.this$0.shape);
        reusableGraphicsLayerScope.setClip(this.this$0.clip);
        reusableGraphicsLayerScope.m388setAmbientShadowColor8_81llA(this.this$0.ambientColor);
        reusableGraphicsLayerScope.m390setSpotShadowColor8_81llA(this.this$0.spotColor);
        return Unit.INSTANCE;
    }
}
