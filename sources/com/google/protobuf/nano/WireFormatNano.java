package com.google.protobuf.nano;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import androidx.collection.MutableObjectList$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WireFormatNano {
    public static final float[] EMPTY_FLOAT_ARRAY = new float[0];

    public static final int getRepeatedFieldArrayLength(CodedInputByteBufferNano codedInputByteBufferNano, int i) {
        int i2 = codedInputByteBufferNano.bufferPos;
        int i3 = codedInputByteBufferNano.bufferStart;
        int i4 = i2 - i3;
        codedInputByteBufferNano.skipField(i);
        int i5 = 1;
        while (codedInputByteBufferNano.readTag() == i) {
            codedInputByteBufferNano.skipField(i);
            i5++;
        }
        if (i4 > codedInputByteBufferNano.bufferPos - i3) {
            StringBuilder m = MutableObjectList$$ExternalSyntheticOutline0.m("Position ", " is beyond current ", i4);
            m.append(codedInputByteBufferNano.bufferPos - i3);
            throw new IllegalArgumentException(m.toString());
        }
        if (i4 < 0) {
            throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i4, "Bad position "));
        }
        codedInputByteBufferNano.bufferPos = i3 + i4;
        return i5;
    }
}
