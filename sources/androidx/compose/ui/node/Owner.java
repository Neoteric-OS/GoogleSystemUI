package androidx.compose.ui.node;

import android.view.View;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.platform.DrawChildContainer;
import androidx.compose.ui.platform.GraphicsLayerOwnerLayer;
import androidx.compose.ui.platform.RenderNodeLayer;
import androidx.compose.ui.platform.ViewLayer;
import androidx.compose.ui.platform.ViewLayerContainer;
import androidx.compose.ui.platform.WeakCache;
import java.lang.ref.Reference;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface Owner {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface OnLayoutCompletedListener {
    }

    static OwnedLayer createLayer$default(Owner owner, Function2 function2, Function0 function0, GraphicsLayer graphicsLayer, boolean z, int i) {
        Reference poll;
        MutableVector mutableVector;
        Object obj = null;
        GraphicsLayer graphicsLayer2 = (i & 4) != 0 ? null : graphicsLayer;
        if ((i & 8) != 0) {
            z = false;
        }
        AndroidComposeView androidComposeView = (AndroidComposeView) owner;
        if (graphicsLayer2 != null) {
            return new GraphicsLayerOwnerLayer(graphicsLayer2, null, androidComposeView, function2, function0);
        }
        if (!z) {
            WeakCache weakCache = androidComposeView.layerCache;
            do {
                poll = weakCache.referenceQueue.poll();
                mutableVector = weakCache.values;
                if (poll != null) {
                    mutableVector.remove(poll);
                }
            } while (poll != null);
            while (true) {
                int i2 = mutableVector.size;
                if (i2 == 0) {
                    break;
                }
                Object obj2 = ((Reference) mutableVector.removeAt(i2 - 1)).get();
                if (obj2 != null) {
                    obj = obj2;
                    break;
                }
            }
            OwnedLayer ownedLayer = (OwnedLayer) obj;
            if (ownedLayer != null) {
                ownedLayer.reuseLayer(function2, function0);
                return ownedLayer;
            }
            if (androidComposeView.isHardwareAccelerated()) {
                return new GraphicsLayerOwnerLayer(androidComposeView.graphicsContext.createGraphicsLayer(), androidComposeView.graphicsContext, androidComposeView, function2, function0);
            }
        }
        if (androidComposeView.isHardwareAccelerated() && androidComposeView.isRenderNodeCompatible) {
            try {
                return new RenderNodeLayer(androidComposeView, function2, function0);
            } catch (Throwable unused) {
                androidComposeView.isRenderNodeCompatible = false;
            }
        }
        if (androidComposeView.viewLayersContainer == null) {
            if (!ViewLayer.hasRetrievedMethod) {
                ViewLayer.Companion.updateDisplayList(new View(androidComposeView.getContext()));
            }
            DrawChildContainer drawChildContainer = ViewLayer.shouldUseDispatchDraw ? new DrawChildContainer(androidComposeView.getContext()) : new ViewLayerContainer(androidComposeView.getContext());
            androidComposeView.viewLayersContainer = drawChildContainer;
            androidComposeView.addView(drawChildContainer, -1);
        }
        DrawChildContainer drawChildContainer2 = androidComposeView.viewLayersContainer;
        Intrinsics.checkNotNull(drawChildContainer2);
        return new ViewLayer(androidComposeView, drawChildContainer2, function2, function0);
    }
}
