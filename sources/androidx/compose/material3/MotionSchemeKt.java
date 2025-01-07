package androidx.compose.material3;

import androidx.compose.runtime.StaticProvidableCompositionLocal;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class MotionSchemeKt {
    public static final StaticProvidableCompositionLocal LocalMotionScheme = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.material3.MotionSchemeKt$LocalMotionScheme$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            StaticProvidableCompositionLocal staticProvidableCompositionLocal = MotionSchemeKt.LocalMotionScheme;
            return new MotionSchemeKt$standardMotionScheme$1();
        }
    });
}
