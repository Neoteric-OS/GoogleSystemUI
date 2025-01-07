package androidx.compose.animation.graphics.vector.compat;

import androidx.compose.animation.graphics.vector.Keyframe;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class XmlAnimatorParser_androidKt$getPropertyValuesHolder1D$$inlined$sortBy$1 implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return ComparisonsKt___ComparisonsJvmKt.compareValues(Float.valueOf(((Keyframe) obj).fraction), Float.valueOf(((Keyframe) obj2).fraction));
    }
}
