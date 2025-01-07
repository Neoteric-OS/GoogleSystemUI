package androidx.compose.foundation.text.selection;

import androidx.compose.runtime.DynamicProvidableCompositionLocal;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TextSelectionColorsKt {
    public static final TextSelectionColors DefaultTextSelectionColors;
    public static final DynamicProvidableCompositionLocal LocalTextSelectionColors = new DynamicProvidableCompositionLocal(new Function0() { // from class: androidx.compose.foundation.text.selection.TextSelectionColorsKt$LocalTextSelectionColors$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return TextSelectionColorsKt.DefaultTextSelectionColors;
        }
    });

    static {
        long Color;
        long Color2 = ColorKt.Color(4282550004L);
        Color = ColorKt.Color(Color.m368getRedimpl(Color2), Color.m367getGreenimpl(Color2), Color.m365getBlueimpl(Color2), 0.4f, Color.m366getColorSpaceimpl(Color2));
        DefaultTextSelectionColors = new TextSelectionColors(Color2, Color);
    }
}
