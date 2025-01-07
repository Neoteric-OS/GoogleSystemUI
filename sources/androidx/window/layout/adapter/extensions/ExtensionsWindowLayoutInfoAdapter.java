package androidx.window.layout.adapter.extensions;

import android.graphics.Rect;
import androidx.window.core.Bounds;
import androidx.window.extensions.layout.FoldingFeature;
import androidx.window.layout.FoldingFeature$State;
import androidx.window.layout.HardwareFoldingFeature;
import androidx.window.layout.WindowLayoutInfo;
import androidx.window.layout.WindowMetrics;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ExtensionsWindowLayoutInfoAdapter {
    public static WindowLayoutInfo translate$window_release(WindowMetrics windowMetrics, androidx.window.extensions.layout.WindowLayoutInfo windowLayoutInfo) {
        HardwareFoldingFeature.Type type;
        FoldingFeature$State foldingFeature$State;
        List<FoldingFeature> displayFeatures = windowLayoutInfo.getDisplayFeatures();
        ArrayList arrayList = new ArrayList();
        for (FoldingFeature foldingFeature : displayFeatures) {
            HardwareFoldingFeature hardwareFoldingFeature = null;
            if (foldingFeature instanceof FoldingFeature) {
                FoldingFeature foldingFeature2 = foldingFeature;
                int type2 = foldingFeature2.getType();
                if (type2 == 1) {
                    type = HardwareFoldingFeature.Type.FOLD;
                } else if (type2 == 2) {
                    type = HardwareFoldingFeature.Type.HINGE;
                }
                int state = foldingFeature2.getState();
                if (state == 1) {
                    foldingFeature$State = FoldingFeature$State.FLAT;
                } else if (state == 2) {
                    foldingFeature$State = FoldingFeature$State.HALF_OPENED;
                }
                Bounds bounds = new Bounds(foldingFeature2.getBounds());
                Rect rect = windowMetrics._bounds.toRect();
                if ((bounds.getHeight() != 0 || bounds.getWidth() != 0) && ((bounds.getWidth() == rect.width() || bounds.getHeight() == rect.height()) && ((bounds.getWidth() >= rect.width() || bounds.getHeight() >= rect.height()) && (bounds.getWidth() != rect.width() || bounds.getHeight() != rect.height())))) {
                    hardwareFoldingFeature = new HardwareFoldingFeature(new Bounds(foldingFeature2.getBounds()), type, foldingFeature$State);
                }
            }
            if (hardwareFoldingFeature != null) {
                arrayList.add(hardwareFoldingFeature);
            }
        }
        return new WindowLayoutInfo(arrayList);
    }
}
