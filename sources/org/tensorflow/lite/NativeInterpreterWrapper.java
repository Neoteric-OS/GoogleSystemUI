package org.tensorflow.lite;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.tensorflow.lite.nnapi.NnApiDelegateImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
abstract class NativeInterpreterWrapper implements AutoCloseable {
    public long errorHandle;
    public final TensorImpl[] inputTensors;
    public long interpreterHandle;
    public boolean isMemoryAllocated;
    public ByteBuffer modelByteBuffer;
    public long modelHandle;
    public final TensorImpl[] outputTensors;
    public final List delegates = new ArrayList();
    public final List ownedDelegates = new ArrayList();

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0088, code lost:
    
        r2 = new java.lang.Class[0];
        r2 = (org.tensorflow.lite.nnapi.NnApiDelegateImpl) r5.getConstructor(null).newInstance(null);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public NativeInterpreterWrapper(java.nio.ByteBuffer r18) {
        /*
            Method dump skipped, instructions count: 318
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.tensorflow.lite.NativeInterpreterWrapper.<init>(java.nio.ByteBuffer):void");
    }

    private static native long allocateTensors(long j, long j2);

    private static native long createErrorReporter(int i);

    private static native long createInterpreter(long j, long j2, int i, boolean z, List list);

    private static native long createModelWithBuffer(ByteBuffer byteBuffer, long j);

    private static native void delete(long j, long j2, long j3);

    private static native long deleteCancellationFlag(long j);

    private static native int getInputCount(long j);

    private static native int getInputTensorIndex(long j, int i);

    private static native int getOutputCount(long j);

    private static native int getOutputTensorIndex(long j, int i);

    private static native String[] getSignatureKeys(long j);

    private static native boolean hasUnresolvedFlexOp(long j);

    private static native boolean resizeInput(long j, long j2, int i, int[] iArr, boolean z);

    private static native void run(long j, long j2);

    @Override // java.lang.AutoCloseable
    public final void close() {
        int i = 0;
        while (true) {
            TensorImpl[] tensorImplArr = this.inputTensors;
            if (i >= tensorImplArr.length) {
                break;
            }
            TensorImpl tensorImpl = tensorImplArr[i];
            if (tensorImpl != null) {
                tensorImpl.close();
                this.inputTensors[i] = null;
            }
            i++;
        }
        int i2 = 0;
        while (true) {
            TensorImpl[] tensorImplArr2 = this.outputTensors;
            if (i2 >= tensorImplArr2.length) {
                break;
            }
            TensorImpl tensorImpl2 = tensorImplArr2[i2];
            if (tensorImpl2 != null) {
                tensorImpl2.close();
                this.outputTensors[i2] = null;
            }
            i2++;
        }
        delete(this.errorHandle, this.modelHandle, this.interpreterHandle);
        deleteCancellationFlag(0L);
        this.errorHandle = 0L;
        this.modelHandle = 0L;
        this.interpreterHandle = 0L;
        this.modelByteBuffer = null;
        this.isMemoryAllocated = false;
        this.delegates.clear();
        Iterator it = this.ownedDelegates.iterator();
        while (it.hasNext()) {
            ((NnApiDelegateImpl) it.next()).close();
        }
        this.ownedDelegates.clear();
    }

    public final TensorImpl getInputTensor(int i) {
        if (i >= 0) {
            TensorImpl[] tensorImplArr = this.inputTensors;
            if (i < tensorImplArr.length) {
                TensorImpl tensorImpl = tensorImplArr[i];
                if (tensorImpl != null) {
                    return tensorImpl;
                }
                long j = this.interpreterHandle;
                TensorImpl fromIndex = TensorImpl.fromIndex(j, getInputTensorIndex(j, i));
                tensorImplArr[i] = fromIndex;
                return fromIndex;
            }
        }
        throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Invalid input Tensor index: "));
    }

    public final String[] getSignatureKeys() {
        return getSignatureKeys(this.interpreterHandle);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0044 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run(java.lang.Object[] r10, java.util.Map r11) {
        /*
            Method dump skipped, instructions count: 247
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.tensorflow.lite.NativeInterpreterWrapper.run(java.lang.Object[], java.util.Map):void");
    }
}
