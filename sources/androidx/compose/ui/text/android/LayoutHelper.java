package androidx.compose.ui.text.android;

import android.text.Layout;
import androidx.compose.animation.ChangeSize$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LayoutHelper {
    public final boolean[] bidiProcessedParagraphs;
    public final Layout layout;
    public final List paragraphBidi;
    public final List paragraphEnds;
    public char[] tmpBuffer;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BidiRun {
        public final int end;
        public final boolean isRtl;
        public final int start;

        public BidiRun(int i, int i2, boolean z) {
            this.start = i;
            this.end = i2;
            this.isRtl = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BidiRun)) {
                return false;
            }
            BidiRun bidiRun = (BidiRun) obj;
            return this.start == bidiRun.start && this.end == bidiRun.end && this.isRtl == bidiRun.isRtl;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.isRtl) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.end, Integer.hashCode(this.start) * 31, 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("BidiRun(start=");
            sb.append(this.start);
            sb.append(", end=");
            sb.append(this.end);
            sb.append(", isRtl=");
            return ChangeSize$$ExternalSyntheticOutline0.m(sb, this.isRtl, ')');
        }
    }

    public LayoutHelper(Layout layout) {
        this.layout = layout;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        do {
            int indexOf$default = StringsKt.indexOf$default(this.layout.getText(), '\n', i, 4);
            i = indexOf$default < 0 ? this.layout.getText().length() : indexOf$default + 1;
            arrayList.add(Integer.valueOf(i));
        } while (i < this.layout.getText().length());
        this.paragraphEnds = arrayList;
        int size = arrayList.size();
        ArrayList arrayList2 = new ArrayList(size);
        for (int i2 = 0; i2 < size; i2++) {
            arrayList2.add(null);
        }
        this.paragraphBidi = arrayList2;
        this.bidiProcessedParagraphs = new boolean[((ArrayList) this.paragraphEnds).size()];
        ((ArrayList) this.paragraphEnds).size();
    }

    public final float getDownstreamHorizontal(int i, boolean z) {
        int lineEnd = this.layout.getLineEnd(this.layout.getLineForOffset(i));
        if (i > lineEnd) {
            i = lineEnd;
        }
        return z ? this.layout.getPrimaryHorizontal(i) : this.layout.getSecondaryHorizontal(i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:143:0x0100, code lost:
    
        if (r9.getRunCount() == 1) goto L47;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final float getHorizontalPosition(int r24, boolean r25, boolean r26) {
        /*
            Method dump skipped, instructions count: 576
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.android.LayoutHelper.getHorizontalPosition(int, boolean, boolean):float");
    }

    public final int getParagraphStart(int i) {
        if (i == 0) {
            return 0;
        }
        return ((Number) ((ArrayList) this.paragraphEnds).get(i - 1)).intValue();
    }

    public final int lineEndToVisibleEnd(int i, int i2) {
        while (i > i2) {
            char charAt = this.layout.getText().charAt(i - 1);
            if (charAt != ' ' && charAt != '\n' && charAt != 5760 && ((Intrinsics.compare(charAt, 8192) < 0 || Intrinsics.compare(charAt, 8202) > 0 || charAt == 8199) && charAt != 8287 && charAt != 12288)) {
                break;
            }
            i--;
        }
        return i;
    }
}
