package kotlin.collections;

import java.util.Map;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class MapsKt__MapWithDefaultKt {
    public static Map withDefault(Map map, Function1 function1) {
        return map instanceof MapWithDefaultImpl ? withDefault(((MapWithDefaultImpl) map).map, function1) : new MapWithDefaultImpl(map, function1);
    }
}
