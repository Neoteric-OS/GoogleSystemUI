package androidx.compose.animation.graphics.vector;

import androidx.compose.animation.core.RepeatMode;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Timestamp {
    public final int durationMillis;
    public final PropertyValuesHolder1D holder;
    public final int repeatCount;
    public final RepeatMode repeatMode;
    public final int timeMillis;

    public Timestamp(int i, int i2, int i3, RepeatMode repeatMode, PropertyValuesHolder1D propertyValuesHolder1D) {
        this.timeMillis = i;
        this.durationMillis = i2;
        this.repeatCount = i3;
        this.repeatMode = repeatMode;
        this.holder = propertyValuesHolder1D;
    }
}
