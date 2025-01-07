package dagger.internal;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MapBuilder {
    public final Map contributions;

    public MapBuilder(int i) {
        this.contributions = new LinkedHashMap(i < 3 ? i + 1 : i < 1073741824 ? (int) ((i / 0.75f) + 1.0f) : Integer.MAX_VALUE);
    }

    public final Map build() {
        return this.contributions.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.contributions);
    }
}
