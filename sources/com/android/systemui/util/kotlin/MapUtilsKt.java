package com.android.systemui.util.kotlin;

import androidx.collection.ArrayMap;
import java.util.Map;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class MapUtilsKt {
    public static final void mapValuesNotNullTo(Map map, ArrayMap arrayMap, Function1 function1) {
        for (Map.Entry entry : map.entrySet()) {
            Object invoke = function1.invoke(entry);
            if (invoke != null) {
                arrayMap.put(entry.getKey(), invoke);
            }
        }
    }
}
