package com.android.wm.shell.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WmShellTransitionTraceProto extends MessageNano {
    public HandlerMapping[] handlerMappings;
    public long magicNumber;
    public long realToElapsedTimeOffsetNanos;
    public Transition[] transitions;

    public WmShellTransitionTraceProto() {
        clear();
    }

    public WmShellTransitionTraceProto clear() {
        this.magicNumber = 0L;
        this.transitions = Transition.emptyArray();
        this.handlerMappings = HandlerMapping.emptyArray();
        this.realToElapsedTimeOffsetNanos = 0L;
        this.cachedSize = -1;
        return this;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public int computeSerializedSize() {
        int computeTagSize = CodedOutputByteBufferNano.computeTagSize(1) + 8;
        Transition[] transitionArr = this.transitions;
        int i = 0;
        if (transitionArr != null && transitionArr.length > 0) {
            int i2 = 0;
            while (true) {
                Transition[] transitionArr2 = this.transitions;
                if (i2 >= transitionArr2.length) {
                    break;
                }
                Transition transition = transitionArr2[i2];
                if (transition != null) {
                    computeTagSize = CodedOutputByteBufferNano.computeMessageSize(2, transition) + computeTagSize;
                }
                i2++;
            }
        }
        HandlerMapping[] handlerMappingArr = this.handlerMappings;
        if (handlerMappingArr != null && handlerMappingArr.length > 0) {
            while (true) {
                HandlerMapping[] handlerMappingArr2 = this.handlerMappings;
                if (i >= handlerMappingArr2.length) {
                    break;
                }
                HandlerMapping handlerMapping = handlerMappingArr2[i];
                if (handlerMapping != null) {
                    computeTagSize = CodedOutputByteBufferNano.computeMessageSize(3, handlerMapping) + computeTagSize;
                }
                i++;
            }
        }
        return this.realToElapsedTimeOffsetNanos != 0 ? computeTagSize + CodedOutputByteBufferNano.computeTagSize(4) + 8 : computeTagSize;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
        codedOutputByteBufferNano.writeFixed64(this.magicNumber, 1);
        Transition[] transitionArr = this.transitions;
        int i = 0;
        if (transitionArr != null && transitionArr.length > 0) {
            int i2 = 0;
            while (true) {
                Transition[] transitionArr2 = this.transitions;
                if (i2 >= transitionArr2.length) {
                    break;
                }
                Transition transition = transitionArr2[i2];
                if (transition != null) {
                    codedOutputByteBufferNano.writeMessage(2, transition);
                }
                i2++;
            }
        }
        HandlerMapping[] handlerMappingArr = this.handlerMappings;
        if (handlerMappingArr != null && handlerMappingArr.length > 0) {
            while (true) {
                HandlerMapping[] handlerMappingArr2 = this.handlerMappings;
                if (i >= handlerMappingArr2.length) {
                    break;
                }
                HandlerMapping handlerMapping = handlerMappingArr2[i];
                if (handlerMapping != null) {
                    codedOutputByteBufferNano.writeMessage(3, handlerMapping);
                }
                i++;
            }
        }
        long j = this.realToElapsedTimeOffsetNanos;
        if (j != 0) {
            codedOutputByteBufferNano.writeFixed64(j, 4);
        }
    }

    @Override // com.google.protobuf.nano.MessageNano
    public WmShellTransitionTraceProto mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                return this;
            }
            if (readTag == 9) {
                this.magicNumber = codedInputByteBufferNano.readRawLittleEndian64();
            } else if (readTag == 18) {
                int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 18);
                Transition[] transitionArr = this.transitions;
                int length = transitionArr == null ? 0 : transitionArr.length;
                int i = repeatedFieldArrayLength + length;
                Transition[] transitionArr2 = new Transition[i];
                if (length != 0) {
                    System.arraycopy(transitionArr, 0, transitionArr2, 0, length);
                }
                while (length < i - 1) {
                    Transition transition = new Transition();
                    transitionArr2[length] = transition;
                    codedInputByteBufferNano.readMessage(transition);
                    codedInputByteBufferNano.readTag();
                    length++;
                }
                Transition transition2 = new Transition();
                transitionArr2[length] = transition2;
                codedInputByteBufferNano.readMessage(transition2);
                this.transitions = transitionArr2;
            } else if (readTag == 26) {
                int repeatedFieldArrayLength2 = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 26);
                HandlerMapping[] handlerMappingArr = this.handlerMappings;
                int length2 = handlerMappingArr == null ? 0 : handlerMappingArr.length;
                int i2 = repeatedFieldArrayLength2 + length2;
                HandlerMapping[] handlerMappingArr2 = new HandlerMapping[i2];
                if (length2 != 0) {
                    System.arraycopy(handlerMappingArr, 0, handlerMappingArr2, 0, length2);
                }
                while (length2 < i2 - 1) {
                    HandlerMapping handlerMapping = new HandlerMapping();
                    handlerMappingArr2[length2] = handlerMapping;
                    codedInputByteBufferNano.readMessage(handlerMapping);
                    codedInputByteBufferNano.readTag();
                    length2++;
                }
                HandlerMapping handlerMapping2 = new HandlerMapping();
                handlerMappingArr2[length2] = handlerMapping2;
                codedInputByteBufferNano.readMessage(handlerMapping2);
                this.handlerMappings = handlerMappingArr2;
            } else if (readTag == 33) {
                this.realToElapsedTimeOffsetNanos = codedInputByteBufferNano.readRawLittleEndian64();
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                return this;
            }
        }
    }
}
