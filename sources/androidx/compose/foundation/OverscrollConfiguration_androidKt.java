package androidx.compose.foundation;

import androidx.compose.runtime.DynamicProvidableCompositionLocal;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class OverscrollConfiguration_androidKt {
    public static final DynamicProvidableCompositionLocal LocalOverscrollConfiguration = new DynamicProvidableCompositionLocal(new Function0() { // from class: androidx.compose.foundation.OverscrollConfiguration_androidKt$LocalOverscrollConfiguration$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return new OverscrollConfiguration();
        }
    });
}
