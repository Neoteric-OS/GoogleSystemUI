package androidx.compose.ui.input.pointer;

import androidx.compose.ui.geometry.Offset;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class HistoricalChange {
    public final long originalEventPosition;
    public final long position;
    public final long uptimeMillis;

    public HistoricalChange(long j, long j2, long j3) {
        this.uptimeMillis = j;
        this.position = j2;
        this.originalEventPosition = j3;
    }

    public final String toString() {
        return "HistoricalChange(uptimeMillis=" + this.uptimeMillis + ", position=" + ((Object) Offset.m317toStringimpl(this.position)) + ')';
    }
}
