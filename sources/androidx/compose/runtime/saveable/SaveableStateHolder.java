package androidx.compose.runtime.saveable;

import androidx.compose.runtime.Composer;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface SaveableStateHolder {
    void SaveableStateProvider(Object obj, Function2 function2, Composer composer, int i);

    void removeState(Object obj);
}
