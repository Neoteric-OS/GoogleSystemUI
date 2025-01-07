package androidx.compose.foundation.gestures;

import android.content.Context;
import androidx.compose.foundation.gestures.BringIntoViewSpec;
import androidx.compose.runtime.CompositionLocalAccessorScope;
import androidx.compose.runtime.CompositionLocalMapKt;
import androidx.compose.runtime.ComputedProvidableCompositionLocal;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BringIntoViewSpec_androidKt {
    public static final ComputedProvidableCompositionLocal LocalBringIntoViewSpec = new ComputedProvidableCompositionLocal(new Function1() { // from class: androidx.compose.foundation.gestures.BringIntoViewSpec_androidKt$LocalBringIntoViewSpec$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            StaticProvidableCompositionLocal staticProvidableCompositionLocal = AndroidCompositionLocals_androidKt.LocalContext;
            PersistentCompositionLocalMap persistentCompositionLocalMap = (PersistentCompositionLocalMap) ((CompositionLocalAccessorScope) obj);
            persistentCompositionLocalMap.getClass();
            if (((Context) CompositionLocalMapKt.read(persistentCompositionLocalMap, staticProvidableCompositionLocal)).getPackageManager().hasSystemFeature("android.software.leanback")) {
                return BringIntoViewSpec_androidKt.PivotBringIntoViewSpec;
            }
            BringIntoViewSpec.Companion.getClass();
            return BringIntoViewSpec.Companion.DefaultBringIntoViewSpec;
        }
    });
    public static final BringIntoViewSpec_androidKt$PivotBringIntoViewSpec$1 PivotBringIntoViewSpec = new BringIntoViewSpec_androidKt$PivotBringIntoViewSpec$1();
}
