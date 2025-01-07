package androidx.window.embedding;

import android.os.Bundle;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.window.embedding.EmbeddingBounds;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ActivityEmbeddingOptionsImpl {
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    public static EmbeddingBounds.Dimension getDimension(Bundle bundle, String str) {
        Bundle bundle2 = bundle.getBundle(str);
        Intrinsics.checkNotNull(bundle2);
        String string = bundle2.getString("androidx.window.embedding.EmbeddingBounds.dimension_type");
        if (string != null) {
            switch (string.hashCode()) {
                case -1939100487:
                    if (string.equals("expanded")) {
                        return EmbeddingBounds.Dimension.DIMENSION_EXPANDED;
                    }
                    break;
                case 99283243:
                    if (string.equals("hinge")) {
                        return EmbeddingBounds.Dimension.DIMENSION_HINGE;
                    }
                    break;
                case 106680966:
                    if (string.equals("pixel")) {
                        EmbeddingBounds.Dimension.Ratio ratio = EmbeddingBounds.Dimension.DIMENSION_EXPANDED;
                        return new EmbeddingBounds.Dimension.Pixel(bundle2.getInt("androidx.window.embedding.EmbeddingBounds.dimension_value"));
                    }
                    break;
                case 108285963:
                    if (string.equals("ratio")) {
                        EmbeddingBounds.Dimension.Ratio ratio2 = EmbeddingBounds.Dimension.DIMENSION_EXPANDED;
                        return new EmbeddingBounds.Dimension.Ratio(bundle2.getFloat("androidx.window.embedding.EmbeddingBounds.dimension_value"));
                    }
                    break;
            }
        }
        throw new IllegalArgumentException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Illegal type ", string));
    }
}
