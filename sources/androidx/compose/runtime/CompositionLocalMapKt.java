package androidx.compose.runtime;

import androidx.compose.runtime.internal.PersistentCompositionLocalHashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class CompositionLocalMapKt {
    public static final Object read(PersistentCompositionLocalMap persistentCompositionLocalMap, CompositionLocal compositionLocal) {
        Object obj = persistentCompositionLocalMap.get(compositionLocal);
        if (obj == null) {
            obj = compositionLocal.getDefaultValueHolder$runtime_release();
        }
        return ((ValueHolder) obj).readValue(persistentCompositionLocalMap);
    }

    public static final PersistentCompositionLocalHashMap updateCompositionMap(ProvidedValue[] providedValueArr, PersistentCompositionLocalMap persistentCompositionLocalMap, PersistentCompositionLocalMap persistentCompositionLocalMap2) {
        PersistentCompositionLocalHashMap persistentCompositionLocalHashMap = PersistentCompositionLocalHashMap.Empty;
        PersistentCompositionLocalHashMap.Builder builder = new PersistentCompositionLocalHashMap.Builder(persistentCompositionLocalHashMap);
        builder.map = persistentCompositionLocalHashMap;
        for (ProvidedValue providedValue : providedValueArr) {
            ProvidableCompositionLocal providableCompositionLocal = providedValue.compositionLocal;
            if (providedValue.canOverride || !persistentCompositionLocalMap.containsKey(providableCompositionLocal)) {
                builder.put(providableCompositionLocal, providableCompositionLocal.updatedStateOf$runtime_release(providedValue, (ValueHolder) persistentCompositionLocalMap2.get(providableCompositionLocal)));
            }
        }
        return builder.build();
    }
}
