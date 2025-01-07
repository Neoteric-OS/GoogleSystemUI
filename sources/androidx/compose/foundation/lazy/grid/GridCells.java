package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface GridCells {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Fixed implements GridCells {
        public final int count;

        public Fixed(int i) {
            this.count = i;
            if (i > 0) {
                return;
            }
            InlineClassHelperKt.throwIllegalArgumentException("Provided count should be larger than zero");
        }

        public final List calculateCrossAxisCellSizes(int i, int i2) {
            int i3 = this.count;
            int i4 = i - ((i3 - 1) * i2);
            int i5 = i4 / i3;
            int i6 = i4 % i3;
            ArrayList arrayList = new ArrayList(i3);
            int i7 = 0;
            while (i7 < i3) {
                arrayList.add(Integer.valueOf((i7 < i6 ? 1 : 0) + i5));
                i7++;
            }
            return arrayList;
        }

        public final boolean equals(Object obj) {
            if (obj instanceof Fixed) {
                if (this.count == ((Fixed) obj).count) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return -this.count;
        }
    }
}
