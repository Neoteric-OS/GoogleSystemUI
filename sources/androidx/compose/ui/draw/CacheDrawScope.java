package androidx.compose.ui.draw;

import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.unit.Density;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CacheDrawScope implements Density {
    public BuildDrawCacheParams cacheParams = EmptyBuildDrawCacheParams.INSTANCE;
    public DrawResult drawResult;

    @Override // androidx.compose.ui.unit.Density
    public final float getDensity() {
        return this.cacheParams.getDensity().getDensity();
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public final float getFontScale() {
        return this.cacheParams.getDensity().getFontScale();
    }

    public final DrawResult onDrawBehind(final Function1 function1) {
        return onDrawWithContent(new Function1() { // from class: androidx.compose.ui.draw.CacheDrawScope$onDrawBehind$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ContentDrawScope contentDrawScope = (ContentDrawScope) obj;
                Function1.this.invoke(contentDrawScope);
                ((LayoutNodeDrawScope) contentDrawScope).drawContent();
                return Unit.INSTANCE;
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final DrawResult onDrawWithContent(Function1 function1) {
        DrawResult drawResult = new DrawResult();
        drawResult.block = (Lambda) function1;
        this.drawResult = drawResult;
        return drawResult;
    }
}
