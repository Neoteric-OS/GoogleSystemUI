package androidx.compose.ui.draw;

import androidx.collection.MutableObjectList;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.internal.InlineClassHelperKt;
import java.util.Arrays;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ScopedGraphicsContext implements GraphicsContext {
    public MutableObjectList allocatedGraphicsLayers;
    public GraphicsContext graphicsContext;

    @Override // androidx.compose.ui.graphics.GraphicsContext
    public final GraphicsLayer createGraphicsLayer() {
        GraphicsContext graphicsContext = this.graphicsContext;
        if (graphicsContext == null) {
            InlineClassHelperKt.throwIllegalStateException("GraphicsContext not provided");
        }
        GraphicsLayer createGraphicsLayer = graphicsContext.createGraphicsLayer();
        MutableObjectList mutableObjectList = this.allocatedGraphicsLayers;
        if (mutableObjectList == null) {
            MutableObjectList mutableObjectList2 = new MutableObjectList(1);
            mutableObjectList2.add(createGraphicsLayer);
            this.allocatedGraphicsLayers = mutableObjectList2;
        } else {
            mutableObjectList.add(createGraphicsLayer);
        }
        return createGraphicsLayer;
    }

    @Override // androidx.compose.ui.graphics.GraphicsContext
    public final void releaseGraphicsLayer(GraphicsLayer graphicsLayer) {
        GraphicsContext graphicsContext = this.graphicsContext;
        if (graphicsContext != null) {
            graphicsContext.releaseGraphicsLayer(graphicsLayer);
        }
    }

    public final void releaseGraphicsLayers() {
        MutableObjectList mutableObjectList = this.allocatedGraphicsLayers;
        if (mutableObjectList != null) {
            Object[] objArr = mutableObjectList.content;
            int i = mutableObjectList._size;
            for (int i2 = 0; i2 < i; i2++) {
                releaseGraphicsLayer((GraphicsLayer) objArr[i2]);
            }
            Arrays.fill(mutableObjectList.content, 0, mutableObjectList._size, (Object) null);
            mutableObjectList._size = 0;
        }
    }
}
