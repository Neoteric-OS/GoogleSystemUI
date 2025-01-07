package okio;

import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import androidx.compose.runtime.collection.MutableVectorKt$$ExternalSyntheticOutline0;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.ListImplementation$$ExternalSyntheticOutline0;
import java.util.Arrays;
import kotlin.collections.ArraysKt__ArraysJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* renamed from: okio.SegmentedByteString, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0279SegmentedByteString extends ByteString {
    public final transient int[] directory;
    public final transient byte[][] segments;

    public C0279SegmentedByteString(byte[][] bArr, int[] iArr) {
        super(ByteString.EMPTY.getData$external__okio__android_common__okio_lib());
        this.segments = bArr;
        this.directory = iArr;
    }

    private final Object writeReplace() {
        return new ByteString(toByteArray());
    }

    @Override // okio.ByteString
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            if (byteString.getSize$external__okio__android_common__okio_lib() == getSize$external__okio__android_common__okio_lib() && rangeEquals(0, byteString, getSize$external__okio__android_common__okio_lib())) {
                return true;
            }
        }
        return false;
    }

    @Override // okio.ByteString
    public final int getSize$external__okio__android_common__okio_lib() {
        return this.directory[this.segments.length - 1];
    }

    @Override // okio.ByteString
    public final int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int length = this.segments.length;
        int i2 = 0;
        int i3 = 1;
        int i4 = 0;
        while (i2 < length) {
            int[] iArr = this.directory;
            int i5 = iArr[length + i2];
            int i6 = iArr[i2];
            byte[] bArr = this.segments[i2];
            int i7 = (i6 - i4) + i5;
            while (i5 < i7) {
                i3 = (i3 * 31) + bArr[i5];
                i5++;
            }
            i2++;
            i4 = i6;
        }
        this.hashCode = i3;
        return i3;
    }

    @Override // okio.ByteString
    public final String hex() {
        return new ByteString(toByteArray()).hex();
    }

    @Override // okio.ByteString
    public final int indexOf(int i, byte[] bArr) {
        return new ByteString(toByteArray()).indexOf(i, bArr);
    }

    @Override // okio.ByteString
    public final byte[] internalArray$external__okio__android_common__okio_lib() {
        return toByteArray();
    }

    @Override // okio.ByteString
    public final byte internalGet$external__okio__android_common__okio_lib(int i) {
        SegmentedByteString.checkOffsetAndCount(this.directory[this.segments.length - 1], i, 1L);
        int segment = okio.internal.SegmentedByteString.segment(this, i);
        int i2 = segment == 0 ? 0 : this.directory[segment - 1];
        int[] iArr = this.directory;
        byte[][] bArr = this.segments;
        return bArr[segment][(i - i2) + iArr[bArr.length + segment]];
    }

    @Override // okio.ByteString
    public final int lastIndexOf(byte[] bArr) {
        return new ByteString(toByteArray()).lastIndexOf(bArr);
    }

    @Override // okio.ByteString
    public final boolean rangeEquals(int i, ByteString byteString, int i2) {
        if (i < 0 || i > getSize$external__okio__android_common__okio_lib() - i2) {
            return false;
        }
        int i3 = i2 + i;
        int segment = okio.internal.SegmentedByteString.segment(this, i);
        int i4 = 0;
        while (i < i3) {
            int i5 = segment == 0 ? 0 : this.directory[segment - 1];
            int[] iArr = this.directory;
            int i6 = iArr[segment] - i5;
            int i7 = iArr[this.segments.length + segment];
            int min = Math.min(i3, i6 + i5) - i;
            if (!byteString.rangeEquals(i4, this.segments[segment], (i - i5) + i7, min)) {
                return false;
            }
            i4 += min;
            i += min;
            segment++;
        }
        return true;
    }

    @Override // okio.ByteString
    public final ByteString substring(int i, int i2) {
        if (i2 == -1234567890) {
            i2 = getSize$external__okio__android_common__okio_lib();
        }
        if (i < 0) {
            throw new IllegalArgumentException(GenericDocument$$ExternalSyntheticOutline0.m("beginIndex=", " < 0", i).toString());
        }
        if (i2 > getSize$external__okio__android_common__okio_lib()) {
            throw new IllegalArgumentException(MutableVectorKt$$ExternalSyntheticOutline0.m(i2, getSize$external__okio__android_common__okio_lib(), "endIndex=", " > length(", ")").toString());
        }
        int i3 = i2 - i;
        if (i3 < 0) {
            throw new IllegalArgumentException(ListImplementation$$ExternalSyntheticOutline0.m("endIndex=", i2, i, " < beginIndex=").toString());
        }
        if (i == 0 && i2 == getSize$external__okio__android_common__okio_lib()) {
            return this;
        }
        if (i == i2) {
            return ByteString.EMPTY;
        }
        int segment = okio.internal.SegmentedByteString.segment(this, i);
        int segment2 = okio.internal.SegmentedByteString.segment(this, i2 - 1);
        byte[][] bArr = this.segments;
        int i4 = segment2 + 1;
        ArraysKt__ArraysJVMKt.copyOfRangeToIndexCheck(i4, bArr.length);
        byte[][] bArr2 = (byte[][]) Arrays.copyOfRange(bArr, segment, i4);
        int[] iArr = new int[bArr2.length * 2];
        if (segment <= segment2) {
            int i5 = segment;
            int i6 = 0;
            while (true) {
                iArr[i6] = Math.min(this.directory[i5] - i, i3);
                int i7 = i6 + 1;
                iArr[i6 + bArr2.length] = this.directory[this.segments.length + i5];
                if (i5 == segment2) {
                    break;
                }
                i5++;
                i6 = i7;
            }
        }
        int i8 = segment != 0 ? this.directory[segment - 1] : 0;
        int length = bArr2.length;
        iArr[length] = (i - i8) + iArr[length];
        return new C0279SegmentedByteString(bArr2, iArr);
    }

    public final byte[] toByteArray() {
        byte[] bArr = new byte[getSize$external__okio__android_common__okio_lib()];
        int length = this.segments.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < length) {
            int[] iArr = this.directory;
            int i4 = iArr[length + i];
            int i5 = iArr[i];
            int i6 = i5 - i2;
            System.arraycopy(this.segments[i], i4, bArr, i3, (i4 + i6) - i4);
            i3 += i6;
            i++;
            i2 = i5;
        }
        return bArr;
    }

    @Override // okio.ByteString
    public final String toString() {
        return new ByteString(toByteArray()).toString();
    }

    @Override // okio.ByteString
    public final void write$external__okio__android_common__okio_lib(Buffer buffer, int i) {
        int segment = okio.internal.SegmentedByteString.segment(this, 0);
        int i2 = 0;
        while (i2 < i) {
            int i3 = segment == 0 ? 0 : this.directory[segment - 1];
            int[] iArr = this.directory;
            int i4 = iArr[segment] - i3;
            int i5 = iArr[this.segments.length + segment];
            int min = Math.min(i, i4 + i3) - i2;
            int i6 = (i2 - i3) + i5;
            Segment segment2 = new Segment(this.segments[segment], i6, i6 + min, true);
            Segment segment3 = buffer.head;
            if (segment3 == null) {
                segment2.prev = segment2;
                segment2.next = segment2;
                buffer.head = segment2;
            } else {
                Segment segment4 = segment3.prev;
                Intrinsics.checkNotNull(segment4);
                segment4.push(segment2);
            }
            i2 += min;
            segment++;
        }
        buffer.size += i;
    }

    @Override // okio.ByteString
    public final boolean rangeEquals(int i, byte[] bArr, int i2, int i3) {
        if (i < 0 || i > getSize$external__okio__android_common__okio_lib() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int i4 = i3 + i;
        int segment = okio.internal.SegmentedByteString.segment(this, i);
        while (i < i4) {
            int i5 = segment == 0 ? 0 : this.directory[segment - 1];
            int[] iArr = this.directory;
            int i6 = iArr[segment] - i5;
            int i7 = iArr[this.segments.length + segment];
            int min = Math.min(i4, i6 + i5) - i;
            if (!SegmentedByteString.arrayRangeEquals(this.segments[segment], (i - i5) + i7, bArr, i2, min)) {
                return false;
            }
            i2 += min;
            i += min;
            segment++;
        }
        return true;
    }
}
