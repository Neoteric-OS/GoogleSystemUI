package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class RowKt {
    public static final RowMeasurePolicy DefaultRowMeasurePolicy = new RowMeasurePolicy(Arrangement.Start, Alignment.Companion.Top);

    public static final RowMeasurePolicy rowMeasurePolicy(Arrangement.Horizontal horizontal, BiasAlignment.Vertical vertical, Composer composer, int i) {
        RowMeasurePolicy rowMeasurePolicy;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(-1828505005);
        if (Intrinsics.areEqual(horizontal, Arrangement.Start) && Intrinsics.areEqual(vertical, Alignment.Companion.Top)) {
            rowMeasurePolicy = DefaultRowMeasurePolicy;
        } else {
            boolean z = true;
            boolean z2 = (((i & 14) ^ 6) > 4 && composerImpl.changed(horizontal)) || (i & 6) == 4;
            if ((((i & 112) ^ 48) <= 32 || !composerImpl.changed(vertical)) && (i & 48) != 32) {
                z = false;
            }
            boolean z3 = z2 | z;
            Object rememberedValue = composerImpl.rememberedValue();
            if (z3 || rememberedValue == Composer.Companion.Empty) {
                rememberedValue = new RowMeasurePolicy(horizontal, vertical);
                composerImpl.updateRememberedValue(rememberedValue);
            }
            rowMeasurePolicy = (RowMeasurePolicy) rememberedValue;
        }
        composerImpl.end(false);
        return rowMeasurePolicy;
    }
}
