package org.tensorflow.lite;

import java.nio.ByteBuffer;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class Interpreter implements AutoCloseable {
    public NativeInterpreterWrapperExperimental wrapper;

    public Interpreter(ByteBuffer byteBuffer) {
        NativeInterpreterWrapperExperimental nativeInterpreterWrapperExperimental = new NativeInterpreterWrapperExperimental(byteBuffer);
        this.wrapper = nativeInterpreterWrapperExperimental;
        nativeInterpreterWrapperExperimental.getSignatureKeys();
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        NativeInterpreterWrapperExperimental nativeInterpreterWrapperExperimental = this.wrapper;
        if (nativeInterpreterWrapperExperimental != null) {
            nativeInterpreterWrapperExperimental.close();
            this.wrapper = null;
        }
    }

    public final void finalize() {
        try {
            close();
        } finally {
            super.finalize();
        }
    }

    public final void runForMultipleInputsOutputs(Object[] objArr, Map map) {
        NativeInterpreterWrapperExperimental nativeInterpreterWrapperExperimental = this.wrapper;
        if (nativeInterpreterWrapperExperimental == null) {
            throw new IllegalStateException("Internal error: The Interpreter has already been closed.");
        }
        nativeInterpreterWrapperExperimental.run(objArr, map);
    }
}
