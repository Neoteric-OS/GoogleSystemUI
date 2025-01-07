package androidx.compose.material3;

import androidx.compose.runtime.DynamicProvidableCompositionLocal;
import androidx.compose.ui.graphics.Color;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ContentColorKt {
    public static final DynamicProvidableCompositionLocal LocalContentColor = new DynamicProvidableCompositionLocal(new Function0() { // from class: androidx.compose.material3.ContentColorKt$LocalContentColor$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return new Color(Color.Black);
        }
    });
}
