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
public abstract class ColumnKt {
    public static final ColumnMeasurePolicy DefaultColumnMeasurePolicy = new ColumnMeasurePolicy(Arrangement.Top, Alignment.Companion.Start);

    public static final ColumnMeasurePolicy columnMeasurePolicy(Arrangement.Vertical vertical, BiasAlignment.Horizontal horizontal, Composer composer, int i) {
        ColumnMeasurePolicy columnMeasurePolicy;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(-1789954967);
        if (Intrinsics.areEqual(vertical, Arrangement.Top) && horizontal.equals(Alignment.Companion.Start)) {
            columnMeasurePolicy = DefaultColumnMeasurePolicy;
        } else {
            boolean z = true;
            boolean z2 = (((i & 14) ^ 6) > 4 && composerImpl.changed(vertical)) || (i & 6) == 4;
            if ((((i & 112) ^ 48) <= 32 || !composerImpl.changed(horizontal)) && (i & 48) != 32) {
                z = false;
            }
            boolean z3 = z2 | z;
            Object rememberedValue = composerImpl.rememberedValue();
            if (z3 || rememberedValue == Composer.Companion.Empty) {
                rememberedValue = new ColumnMeasurePolicy(vertical, horizontal);
                composerImpl.updateRememberedValue(rememberedValue);
            }
            columnMeasurePolicy = (ColumnMeasurePolicy) rememberedValue;
        }
        composerImpl.end(false);
        return columnMeasurePolicy;
    }
}
