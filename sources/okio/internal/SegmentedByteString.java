package okio.internal;

import okio.C0279SegmentedByteString;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* renamed from: okio.internal.-SegmentedByteString, reason: invalid class name */
/* loaded from: classes2.dex */
public abstract class SegmentedByteString {
    public static final int segment(C0279SegmentedByteString c0279SegmentedByteString, int i) {
        int i2;
        int[] iArr = c0279SegmentedByteString.directory;
        int i3 = i + 1;
        int length = c0279SegmentedByteString.segments.length - 1;
        int i4 = 0;
        while (true) {
            if (i4 <= length) {
                i2 = (i4 + length) >>> 1;
                int i5 = iArr[i2];
                if (i5 >= i3) {
                    if (i5 <= i3) {
                        break;
                    }
                    length = i2 - 1;
                } else {
                    i4 = i2 + 1;
                }
            } else {
                i2 = (-i4) - 1;
                break;
            }
        }
        return i2 >= 0 ? i2 : ~i2;
    }
}
