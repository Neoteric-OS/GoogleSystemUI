package androidx.compose.ui.input.pointer;

import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import androidx.compose.ui.geometry.Offset;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PointerInputEventData {
    public final boolean activeHover;
    public final boolean down;
    public final List historical;
    public final long id;
    public final long originalEventPosition;
    public final long position;
    public final long positionOnScreen;
    public final float pressure;
    public final long scrollDelta;
    public final int type;
    public final long uptime;

    public PointerInputEventData(long j, long j2, long j3, long j4, boolean z, float f, int i, boolean z2, List list, long j5, long j6) {
        this.id = j;
        this.uptime = j2;
        this.positionOnScreen = j3;
        this.position = j4;
        this.down = z;
        this.pressure = f;
        this.type = i;
        this.activeHover = z2;
        this.historical = list;
        this.scrollDelta = j5;
        this.originalEventPosition = j6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PointerInputEventData)) {
            return false;
        }
        PointerInputEventData pointerInputEventData = (PointerInputEventData) obj;
        return PointerId.m463equalsimpl0(this.id, pointerInputEventData.id) && this.uptime == pointerInputEventData.uptime && Offset.m310equalsimpl0(this.positionOnScreen, pointerInputEventData.positionOnScreen) && Offset.m310equalsimpl0(this.position, pointerInputEventData.position) && this.down == pointerInputEventData.down && Float.compare(this.pressure, pointerInputEventData.pressure) == 0 && PointerType.m468equalsimpl0(this.type, pointerInputEventData.type) && this.activeHover == pointerInputEventData.activeHover && this.historical.equals(pointerInputEventData.historical) && Offset.m310equalsimpl0(this.scrollDelta, pointerInputEventData.scrollDelta) && Offset.m310equalsimpl0(this.originalEventPosition, pointerInputEventData.originalEventPosition);
    }

    public final int hashCode() {
        return Long.hashCode(this.originalEventPosition) + Scale$$ExternalSyntheticOutline0.m(PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.type, FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Long.hashCode(this.id) * 31, 31, this.uptime), 31, this.positionOnScreen), 31, this.position), 31, this.down), this.pressure, 31), 31), 31, this.activeHover), 31, this.historical), 31, this.scrollDelta);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("PointerInputEventData(id=");
        sb.append((Object) PointerId.m464toStringimpl(this.id));
        sb.append(", uptime=");
        sb.append(this.uptime);
        sb.append(", positionOnScreen=");
        sb.append((Object) Offset.m317toStringimpl(this.positionOnScreen));
        sb.append(", position=");
        sb.append((Object) Offset.m317toStringimpl(this.position));
        sb.append(", down=");
        sb.append(this.down);
        sb.append(", pressure=");
        sb.append(this.pressure);
        sb.append(", type=");
        int i = this.type;
        sb.append((Object) (i != 1 ? i != 2 ? i != 3 ? i != 4 ? "Unknown" : "Eraser" : "Stylus" : "Mouse" : "Touch"));
        sb.append(", activeHover=");
        sb.append(this.activeHover);
        sb.append(", historical=");
        sb.append(this.historical);
        sb.append(", scrollDelta=");
        sb.append((Object) Offset.m317toStringimpl(this.scrollDelta));
        sb.append(", originalEventPosition=");
        sb.append((Object) Offset.m317toStringimpl(this.originalEventPosition));
        sb.append(')');
        return sb.toString();
    }
}
