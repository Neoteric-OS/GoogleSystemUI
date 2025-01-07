package androidx.compose.foundation.contextmenu;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.geometry.Offset;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ContextMenuState {
    public final MutableState status$delegate = SnapshotStateKt.mutableStateOf$default(Status.Closed.INSTANCE);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Status {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Closed extends Status {
            public static final Closed INSTANCE = new Closed();

            public final String toString() {
                return "Closed";
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Open extends Status {
            public final long offset;

            public Open(long j) {
                this.offset = j;
                if ((j & 9223372034707292159L) != 9205357640488583168L) {
                    return;
                }
                InlineClassHelperKt.throwIllegalStateException("ContextMenuState.Status should never be open with an unspecified offset. Use ContextMenuState.Status.Closed instead.");
            }

            public final boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Open)) {
                    return false;
                }
                return Offset.m310equalsimpl0(this.offset, ((Open) obj).offset);
            }

            public final int hashCode() {
                return Long.hashCode(this.offset);
            }

            public final String toString() {
                return "Open(offset=" + ((Object) Offset.m317toStringimpl(this.offset)) + ')';
            }
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ContextMenuState) {
            return Intrinsics.areEqual((Status) ((SnapshotMutableStateImpl) ((ContextMenuState) obj).status$delegate).getValue(), (Status) ((SnapshotMutableStateImpl) this.status$delegate).getValue());
        }
        return false;
    }

    public final int hashCode() {
        return ((Status) ((SnapshotMutableStateImpl) this.status$delegate).getValue()).hashCode();
    }

    public final String toString() {
        return "ContextMenuState(status=" + ((Status) ((SnapshotMutableStateImpl) this.status$delegate).getValue()) + ')';
    }
}
