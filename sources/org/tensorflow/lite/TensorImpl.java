package org.tensorflow.lite;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class TensorImpl {
    public final DataType dtype;
    public long nativeHandle;
    public int[] shapeCopy;

    public TensorImpl(long j) {
        DataType dataType;
        this.nativeHandle = j;
        int dtype = dtype(j);
        switch (dtype) {
            case 1:
                dataType = DataType.FLOAT32;
                break;
            case 2:
                dataType = DataType.INT32;
                break;
            case 3:
                dataType = DataType.UINT8;
                break;
            case 4:
                dataType = DataType.INT64;
                break;
            case 5:
                dataType = DataType.STRING;
                break;
            case 6:
                dataType = DataType.BOOL;
                break;
            case 7:
                dataType = DataType.INT16;
                break;
            case 8:
            default:
                throw new IllegalArgumentException(GenericDocument$$ExternalSyntheticOutline0.m("DataType error: DataType ", " is not recognized in Java.", dtype));
            case 9:
                dataType = DataType.INT8;
                break;
        }
        this.dtype = dataType;
        this.shapeCopy = shape(j);
        shapeSignature(j);
        quantizationScale(j);
        quantizationZeroPoint(j);
    }

    private static native ByteBuffer buffer(long j);

    public static int computeNumDimensions(Object obj) {
        if (obj == null || !obj.getClass().isArray()) {
            return 0;
        }
        if (Array.getLength(obj) != 0) {
            return computeNumDimensions(Array.get(obj, 0)) + 1;
        }
        throw new IllegalArgumentException("Array lengths cannot be 0.");
    }

    private static native long create(long j, int i, int i2);

    private static native void delete(long j);

    private static native int dtype(long j);

    public static void fillShape(Object obj, int i, int[] iArr) {
        if (i == iArr.length) {
            return;
        }
        int length = Array.getLength(obj);
        int i2 = iArr[i];
        if (i2 == 0) {
            iArr[i] = length;
        } else if (i2 != length) {
            throw new IllegalArgumentException(String.format("Mismatched lengths (%d and %d) in dimension %d", Integer.valueOf(iArr[i]), Integer.valueOf(length), Integer.valueOf(i)));
        }
        int i3 = i + 1;
        if (i3 == iArr.length) {
            return;
        }
        for (int i4 = 0; i4 < length; i4++) {
            fillShape(Array.get(obj, i4), i3, iArr);
        }
    }

    public static TensorImpl fromIndex(long j, int i) {
        return new TensorImpl(create(j, i, 0));
    }

    private static native boolean hasDelegateBufferHandle(long j);

    private static native String name(long j);

    private static native int numBytes(long j);

    private static native float quantizationScale(long j);

    private static native int quantizationZeroPoint(long j);

    private static native void readMultiDimensionalArray(long j, Object obj);

    private static native int[] shape(long j);

    private static native int[] shapeSignature(long j);

    private static native void writeDirectBuffer(long j, Buffer buffer);

    private static native void writeMultiDimensionalArray(long j, Object obj);

    private static native void writeScalar(long j, Object obj);

    public final ByteBuffer buffer() {
        return buffer(this.nativeHandle).order(ByteOrder.nativeOrder());
    }

    public final void close() {
        delete(this.nativeHandle);
        this.nativeHandle = 0L;
    }

    public final int[] computeShapeOf(Object obj) {
        int computeNumDimensions = computeNumDimensions(obj);
        if (this.dtype == DataType.STRING) {
            Class<?> cls = obj.getClass();
            if (cls.isArray()) {
                while (cls.isArray()) {
                    cls = cls.getComponentType();
                }
                if (Byte.TYPE.equals(cls)) {
                    computeNumDimensions--;
                }
            }
        }
        int[] iArr = new int[computeNumDimensions];
        fillShape(obj, 0, iArr);
        return iArr;
    }

    public final void copyTo(Object obj) {
        if (obj == null) {
            if (!hasDelegateBufferHandle(this.nativeHandle)) {
                throw new IllegalArgumentException("Null outputs are allowed only if the Tensor is bound to a buffer handle.");
            }
            return;
        }
        throwIfTypeIsIncompatible(obj);
        boolean z = obj instanceof Buffer;
        if (z) {
            int numBytes = numBytes(this.nativeHandle);
            boolean z2 = obj instanceof ByteBuffer;
            int capacity = ((Buffer) obj).capacity();
            if (!z2) {
                capacity *= this.dtype.byteSize();
            }
            if (numBytes > capacity) {
                throw new IllegalArgumentException(String.format("Cannot copy from a TensorFlowLite tensor (%s) with %d bytes to a Java Buffer with %d bytes.", name(this.nativeHandle), Integer.valueOf(numBytes), Integer.valueOf(capacity)));
            }
        } else {
            int[] computeShapeOf = computeShapeOf(obj);
            if (!Arrays.equals(computeShapeOf, this.shapeCopy)) {
                throw new IllegalArgumentException(ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("Cannot copy from a TensorFlowLite tensor (", name(this.nativeHandle), ") with shape ", Arrays.toString(this.shapeCopy), " to a Java object with shape "), Arrays.toString(computeShapeOf), "."));
            }
        }
        if (!z) {
            readMultiDimensionalArray(this.nativeHandle, obj);
            return;
        }
        Buffer buffer = (Buffer) obj;
        if (buffer instanceof ByteBuffer) {
            ((ByteBuffer) buffer).put(buffer());
            return;
        }
        if (buffer instanceof FloatBuffer) {
            ((FloatBuffer) buffer).put(buffer().asFloatBuffer());
            return;
        }
        if (buffer instanceof LongBuffer) {
            ((LongBuffer) buffer).put(buffer().asLongBuffer());
            return;
        }
        if (buffer instanceof IntBuffer) {
            ((IntBuffer) buffer).put(buffer().asIntBuffer());
        } else if (buffer instanceof ShortBuffer) {
            ((ShortBuffer) buffer).put(buffer().asShortBuffer());
        } else {
            throw new IllegalArgumentException("Unexpected output buffer type: " + buffer);
        }
    }

    public final void refreshShape() {
        this.shapeCopy = shape(this.nativeHandle);
    }

    public final void setTo(Object obj) {
        if (obj == null) {
            if (!hasDelegateBufferHandle(this.nativeHandle)) {
                throw new IllegalArgumentException("Null inputs are allowed only if the Tensor is bound to a buffer handle.");
            }
            return;
        }
        throwIfTypeIsIncompatible(obj);
        boolean z = obj instanceof Buffer;
        DataType dataType = this.dtype;
        if (z) {
            int numBytes = numBytes(this.nativeHandle);
            boolean z2 = obj instanceof ByteBuffer;
            int capacity = ((Buffer) obj).capacity();
            if (!z2) {
                capacity *= dataType.byteSize();
            }
            if (numBytes != capacity) {
                throw new IllegalArgumentException(String.format("Cannot copy to a TensorFlowLite tensor (%s) with %d bytes from a Java Buffer with %d bytes.", name(this.nativeHandle), Integer.valueOf(numBytes), Integer.valueOf(capacity)));
            }
        } else {
            int[] computeShapeOf = computeShapeOf(obj);
            if (!Arrays.equals(computeShapeOf, this.shapeCopy)) {
                throw new IllegalArgumentException(ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("Cannot copy to a TensorFlowLite tensor (", name(this.nativeHandle), ") with shape ", Arrays.toString(this.shapeCopy), " from a Java object with shape "), Arrays.toString(computeShapeOf), "."));
            }
        }
        if (!z) {
            if (dataType == DataType.STRING && this.shapeCopy.length == 0) {
                writeScalar(this.nativeHandle, obj);
                return;
            } else if (obj.getClass().isArray()) {
                writeMultiDimensionalArray(this.nativeHandle, obj);
                return;
            } else {
                writeScalar(this.nativeHandle, obj);
                return;
            }
        }
        Buffer buffer = (Buffer) obj;
        if (buffer instanceof ByteBuffer) {
            ByteBuffer byteBuffer = (ByteBuffer) buffer;
            if (byteBuffer.isDirect() && byteBuffer.order() == ByteOrder.nativeOrder()) {
                writeDirectBuffer(this.nativeHandle, buffer);
                return;
            } else {
                buffer().put(byteBuffer);
                return;
            }
        }
        if (buffer instanceof LongBuffer) {
            LongBuffer longBuffer = (LongBuffer) buffer;
            if (longBuffer.isDirect() && longBuffer.order() == ByteOrder.nativeOrder()) {
                writeDirectBuffer(this.nativeHandle, buffer);
                return;
            } else {
                buffer().asLongBuffer().put(longBuffer);
                return;
            }
        }
        if (buffer instanceof FloatBuffer) {
            FloatBuffer floatBuffer = (FloatBuffer) buffer;
            if (floatBuffer.isDirect() && floatBuffer.order() == ByteOrder.nativeOrder()) {
                writeDirectBuffer(this.nativeHandle, buffer);
                return;
            } else {
                buffer().asFloatBuffer().put(floatBuffer);
                return;
            }
        }
        if (buffer instanceof IntBuffer) {
            IntBuffer intBuffer = (IntBuffer) buffer;
            if (intBuffer.isDirect() && intBuffer.order() == ByteOrder.nativeOrder()) {
                writeDirectBuffer(this.nativeHandle, buffer);
                return;
            } else {
                buffer().asIntBuffer().put(intBuffer);
                return;
            }
        }
        if (!(buffer instanceof ShortBuffer)) {
            throw new IllegalArgumentException("Unexpected input buffer type: " + buffer);
        }
        ShortBuffer shortBuffer = (ShortBuffer) buffer;
        if (shortBuffer.isDirect() && shortBuffer.order() == ByteOrder.nativeOrder()) {
            writeDirectBuffer(this.nativeHandle, buffer);
        } else {
            buffer().asShortBuffer().put(shortBuffer);
        }
    }

    public final void throwIfTypeIsIncompatible(Object obj) {
        DataType dataType;
        if (obj instanceof ByteBuffer) {
            return;
        }
        Class<?> cls = obj.getClass();
        boolean isArray = cls.isArray();
        DataType dataType2 = this.dtype;
        if (!isArray) {
            if (Float.class.equals(cls) || (obj instanceof FloatBuffer)) {
                dataType = DataType.FLOAT32;
            } else if (Integer.class.equals(cls) || (obj instanceof IntBuffer)) {
                dataType = DataType.INT32;
            } else if (Short.class.equals(cls) || (obj instanceof ShortBuffer)) {
                dataType = DataType.INT16;
            } else if (Byte.class.equals(cls)) {
                dataType = DataType.UINT8;
            } else if (Long.class.equals(cls) || (obj instanceof LongBuffer)) {
                dataType = DataType.INT64;
            } else {
                if (!Boolean.class.equals(cls)) {
                    if (String.class.equals(cls)) {
                        dataType = DataType.STRING;
                    }
                    throw new IllegalArgumentException("DataType error: cannot resolve DataType of ".concat(obj.getClass().getName()));
                }
                dataType = DataType.BOOL;
            }
            if (dataType != dataType2) {
                return;
            } else {
                return;
            }
        }
        while (cls.isArray()) {
            cls = cls.getComponentType();
        }
        if (Float.TYPE.equals(cls)) {
            dataType = DataType.FLOAT32;
        } else if (Integer.TYPE.equals(cls)) {
            dataType = DataType.INT32;
        } else if (Short.TYPE.equals(cls)) {
            dataType = DataType.INT16;
        } else if (Byte.TYPE.equals(cls)) {
            dataType = DataType.STRING;
            if (dataType2 != dataType) {
                dataType = DataType.UINT8;
            }
        } else if (Long.TYPE.equals(cls)) {
            dataType = DataType.INT64;
        } else {
            if (!Boolean.TYPE.equals(cls)) {
                if (String.class.equals(cls)) {
                    dataType = DataType.STRING;
                }
                throw new IllegalArgumentException("DataType error: cannot resolve DataType of ".concat(obj.getClass().getName()));
            }
            dataType = DataType.BOOL;
        }
        if (dataType != dataType2 || DataTypeUtils.toStringName(dataType).equals(DataTypeUtils.toStringName(dataType2))) {
            return;
        }
        throw new IllegalArgumentException("Cannot convert between a TensorFlowLite tensor with type " + dataType2 + " and a Java object of type " + obj.getClass().getName() + " (which is compatible with the TensorFlowLite type " + dataType + ").");
    }
}
