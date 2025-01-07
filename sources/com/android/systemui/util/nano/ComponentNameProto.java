package com.android.systemui.util.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ComponentNameProto extends MessageNano {
    public String className;
    public String packageName;

    public ComponentNameProto() {
        clear();
    }

    public ComponentNameProto clear() {
        this.packageName = "";
        this.className = "";
        this.cachedSize = -1;
        return this;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public int computeSerializedSize() {
        int computeStringSize = !this.packageName.equals("") ? CodedOutputByteBufferNano.computeStringSize(1, this.packageName) : 0;
        return !this.className.equals("") ? computeStringSize + CodedOutputByteBufferNano.computeStringSize(2, this.className) : computeStringSize;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
        if (!this.packageName.equals("")) {
            codedOutputByteBufferNano.writeString(1, this.packageName);
        }
        if (this.className.equals("")) {
            return;
        }
        codedOutputByteBufferNano.writeString(2, this.className);
    }

    @Override // com.google.protobuf.nano.MessageNano
    public ComponentNameProto mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                return this;
            }
            if (readTag == 10) {
                this.packageName = codedInputByteBufferNano.readString();
            } else if (readTag == 18) {
                this.className = codedInputByteBufferNano.readString();
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                return this;
            }
        }
    }
}
