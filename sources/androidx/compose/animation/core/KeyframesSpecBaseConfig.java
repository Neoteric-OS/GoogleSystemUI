package androidx.compose.animation.core;

import androidx.collection.IntObjectMapKt;
import androidx.collection.MutableIntObjectMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class KeyframesSpecBaseConfig {
    public int durationMillis = 300;
    public final MutableIntObjectMap keyframes;

    public KeyframesSpecBaseConfig() {
        MutableIntObjectMap mutableIntObjectMap = IntObjectMapKt.EmptyIntObjectMap;
        this.keyframes = new MutableIntObjectMap();
    }
}
