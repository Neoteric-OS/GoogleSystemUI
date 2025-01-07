package androidx.compose.foundation.text;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotMutableFloatStateImpl;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.saveable.ListSaverKt;
import androidx.compose.runtime.saveable.SaverKt$Saver$1;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.text.TextRange;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TextFieldScrollerPosition {
    public static final SaverKt$Saver$1 Saver = ListSaverKt.listSaver(new Function2() { // from class: androidx.compose.foundation.text.TextFieldScrollerPosition$Companion$Saver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            TextFieldScrollerPosition textFieldScrollerPosition = (TextFieldScrollerPosition) obj2;
            return CollectionsKt__CollectionsKt.listOf(Float.valueOf(textFieldScrollerPosition.getOffset()), Boolean.valueOf(((Orientation) ((SnapshotMutableStateImpl) textFieldScrollerPosition.orientation$delegate).getValue()) == Orientation.Vertical));
        }
    }, new Function1() { // from class: androidx.compose.foundation.text.TextFieldScrollerPosition$Companion$Saver$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            List list = (List) obj;
            return new TextFieldScrollerPosition(((Boolean) list.get(1)).booleanValue() ? Orientation.Vertical : Orientation.Horizontal, ((Float) list.get(0)).floatValue());
        }
    });
    public final MutableFloatState offset$delegate;
    public final MutableState orientation$delegate;
    public final MutableFloatState maximum$delegate = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
    public Rect previousCursorRect = Rect.Zero;
    public long previousSelection = TextRange.Zero;

    public TextFieldScrollerPosition(Orientation orientation, float f) {
        this.offset$delegate = PrimitiveSnapshotStateKt.mutableFloatStateOf(f);
        this.orientation$delegate = SnapshotStateKt.mutableStateOf(orientation, SnapshotStateKt.structuralEqualityPolicy());
    }

    public final float getOffset() {
        return ((SnapshotMutableFloatStateImpl) this.offset$delegate).getFloatValue();
    }

    public final void update(Orientation orientation, Rect rect, int i, int i2) {
        float f = i2 - i;
        ((SnapshotMutableFloatStateImpl) this.maximum$delegate).setFloatValue(f);
        Rect rect2 = this.previousCursorRect;
        float f2 = rect2.left;
        float f3 = rect.left;
        MutableFloatState mutableFloatState = this.offset$delegate;
        float f4 = rect.top;
        if (f3 != f2 || f4 != rect2.top) {
            boolean z = orientation == Orientation.Vertical;
            if (z) {
                f3 = f4;
            }
            float f5 = z ? rect.bottom : rect.right;
            float offset = getOffset();
            float f6 = i;
            float f7 = offset + f6;
            ((SnapshotMutableFloatStateImpl) mutableFloatState).setFloatValue(getOffset() + ((f5 <= f7 && (f3 >= offset || f5 - f3 <= f6)) ? (f3 >= offset || f5 - f3 > f6) ? 0.0f : f3 - offset : f5 - f7));
            this.previousCursorRect = rect;
        }
        ((SnapshotMutableFloatStateImpl) mutableFloatState).setFloatValue(RangesKt.coerceIn(getOffset(), 0.0f, f));
    }
}
