package com.google.android.systemui.elmyra.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ElmyraFilters$Filter extends MessageNano {
    public static volatile ElmyraFilters$Filter[] _emptyArray;
    public int parametersCase_ = 0;
    public MessageNano parameters_ = null;

    public ElmyraFilters$Filter() {
        this.cachedSize = -1;
    }

    public static ElmyraFilters$Filter[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                try {
                    if (_emptyArray == null) {
                        _emptyArray = new ElmyraFilters$Filter[0];
                    }
                } finally {
                }
            }
        }
        return _emptyArray;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final int computeSerializedSize() {
        int computeMessageSize = this.parametersCase_ == 1 ? CodedOutputByteBufferNano.computeMessageSize(1, this.parameters_) : 0;
        if (this.parametersCase_ == 2) {
            computeMessageSize += CodedOutputByteBufferNano.computeMessageSize(2, this.parameters_);
        }
        if (this.parametersCase_ == 3) {
            computeMessageSize += CodedOutputByteBufferNano.computeMessageSize(3, this.parameters_);
        }
        return this.parametersCase_ == 4 ? computeMessageSize + CodedOutputByteBufferNano.computeMessageSize(4, this.parameters_) : computeMessageSize;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                break;
            }
            if (readTag == 10) {
                if (this.parametersCase_ != 1) {
                    this.parameters_ = new MessageNano() { // from class: com.google.android.systemui.elmyra.proto.nano.ElmyraFilters$FIRFilter
                        public float[] coefficients = WireFormatNano.EMPTY_FLOAT_ARRAY;

                        {
                            this.cachedSize = -1;
                        }

                        @Override // com.google.protobuf.nano.MessageNano
                        public final int computeSerializedSize() {
                            float[] fArr = this.coefficients;
                            if (fArr == null || fArr.length <= 0) {
                                return 0;
                            }
                            return (fArr.length * 4) + fArr.length;
                        }

                        @Override // com.google.protobuf.nano.MessageNano
                        public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano2) {
                            while (true) {
                                int readTag2 = codedInputByteBufferNano2.readTag();
                                if (readTag2 == 0) {
                                    break;
                                }
                                if (readTag2 == 10) {
                                    int readRawVarint32 = codedInputByteBufferNano2.readRawVarint32();
                                    int pushLimit = codedInputByteBufferNano2.pushLimit(readRawVarint32);
                                    float[] fArr = this.coefficients;
                                    int length = fArr.length;
                                    int i = (readRawVarint32 / 4) + length;
                                    float[] fArr2 = new float[i];
                                    if (length != 0) {
                                        System.arraycopy(fArr, 0, fArr2, 0, length);
                                    }
                                    while (length < i) {
                                        fArr2[length] = codedInputByteBufferNano2.readFloat();
                                        length++;
                                    }
                                    this.coefficients = fArr2;
                                    codedInputByteBufferNano2.currentLimit = pushLimit;
                                    int i2 = codedInputByteBufferNano2.bufferSize + codedInputByteBufferNano2.bufferSizeAfterLimit;
                                    codedInputByteBufferNano2.bufferSize = i2;
                                    if (i2 > pushLimit) {
                                        int i3 = i2 - pushLimit;
                                        codedInputByteBufferNano2.bufferSizeAfterLimit = i3;
                                        codedInputByteBufferNano2.bufferSize = i2 - i3;
                                    } else {
                                        codedInputByteBufferNano2.bufferSizeAfterLimit = 0;
                                    }
                                } else if (readTag2 == 13) {
                                    int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano2, 13);
                                    float[] fArr3 = this.coefficients;
                                    int length2 = fArr3.length;
                                    int i4 = repeatedFieldArrayLength + length2;
                                    float[] fArr4 = new float[i4];
                                    if (length2 != 0) {
                                        System.arraycopy(fArr3, 0, fArr4, 0, length2);
                                    }
                                    while (length2 < i4 - 1) {
                                        fArr4[length2] = codedInputByteBufferNano2.readFloat();
                                        codedInputByteBufferNano2.readTag();
                                        length2++;
                                    }
                                    fArr4[length2] = codedInputByteBufferNano2.readFloat();
                                    this.coefficients = fArr4;
                                } else if (!codedInputByteBufferNano2.skipField(readTag2)) {
                                    break;
                                }
                            }
                            return this;
                        }

                        @Override // com.google.protobuf.nano.MessageNano
                        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
                            float[] fArr = this.coefficients;
                            if (fArr == null || fArr.length <= 0) {
                                return;
                            }
                            int i = 0;
                            while (true) {
                                float[] fArr2 = this.coefficients;
                                if (i >= fArr2.length) {
                                    return;
                                }
                                codedOutputByteBufferNano.writeFloat(1, fArr2[i]);
                                i++;
                            }
                        }
                    };
                }
                codedInputByteBufferNano.readMessage(this.parameters_);
                this.parametersCase_ = 1;
            } else if (readTag == 18) {
                if (this.parametersCase_ != 2) {
                    final int i = 1;
                    this.parameters_ = new MessageNano(i) { // from class: com.google.android.systemui.elmyra.proto.nano.ElmyraFilters$LowpassFilter
                        public final /* synthetic */ int $r8$classId;
                        public float cutoff;
                        public float rate;

                        {
                            this.$r8$classId = i;
                            switch (i) {
                                case 1:
                                    this.cutoff = 0.0f;
                                    this.rate = 0.0f;
                                    this.cachedSize = -1;
                                    break;
                                default:
                                    this.cutoff = 0.0f;
                                    this.rate = 0.0f;
                                    this.cachedSize = -1;
                                    break;
                            }
                        }

                        @Override // com.google.protobuf.nano.MessageNano
                        public final int computeSerializedSize() {
                            switch (this.$r8$classId) {
                                case 0:
                                    int computeFloatSize = Float.floatToIntBits(this.cutoff) != Float.floatToIntBits(0.0f) ? CodedOutputByteBufferNano.computeFloatSize(1) : 0;
                                    return Float.floatToIntBits(this.rate) != Float.floatToIntBits(0.0f) ? computeFloatSize + CodedOutputByteBufferNano.computeFloatSize(2) : computeFloatSize;
                                default:
                                    int computeFloatSize2 = Float.floatToIntBits(this.cutoff) != Float.floatToIntBits(0.0f) ? CodedOutputByteBufferNano.computeFloatSize(1) : 0;
                                    return Float.floatToIntBits(this.rate) != Float.floatToIntBits(0.0f) ? computeFloatSize2 + CodedOutputByteBufferNano.computeFloatSize(2) : computeFloatSize2;
                            }
                        }

                        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
                        @Override // com.google.protobuf.nano.MessageNano
                        public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano2) {
                            switch (this.$r8$classId) {
                                case 0:
                                    while (true) {
                                        int readTag2 = codedInputByteBufferNano2.readTag();
                                        if (readTag2 == 0) {
                                            break;
                                        } else if (readTag2 == 13) {
                                            this.cutoff = codedInputByteBufferNano2.readFloat();
                                        } else if (readTag2 == 21) {
                                            this.rate = codedInputByteBufferNano2.readFloat();
                                        } else if (!codedInputByteBufferNano2.skipField(readTag2)) {
                                            break;
                                        }
                                    }
                                default:
                                    while (true) {
                                        int readTag3 = codedInputByteBufferNano2.readTag();
                                        if (readTag3 == 0) {
                                            break;
                                        } else if (readTag3 == 13) {
                                            this.cutoff = codedInputByteBufferNano2.readFloat();
                                        } else if (readTag3 == 21) {
                                            this.rate = codedInputByteBufferNano2.readFloat();
                                        } else if (!codedInputByteBufferNano2.skipField(readTag3)) {
                                            break;
                                        }
                                    }
                            }
                            return this;
                        }

                        @Override // com.google.protobuf.nano.MessageNano
                        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
                            switch (this.$r8$classId) {
                                case 0:
                                    if (Float.floatToIntBits(this.cutoff) != Float.floatToIntBits(0.0f)) {
                                        codedOutputByteBufferNano.writeFloat(1, this.cutoff);
                                    }
                                    if (Float.floatToIntBits(this.rate) != Float.floatToIntBits(0.0f)) {
                                        codedOutputByteBufferNano.writeFloat(2, this.rate);
                                        break;
                                    }
                                    break;
                                default:
                                    if (Float.floatToIntBits(this.cutoff) != Float.floatToIntBits(0.0f)) {
                                        codedOutputByteBufferNano.writeFloat(1, this.cutoff);
                                    }
                                    if (Float.floatToIntBits(this.rate) != Float.floatToIntBits(0.0f)) {
                                        codedOutputByteBufferNano.writeFloat(2, this.rate);
                                        break;
                                    }
                                    break;
                            }
                        }
                    };
                }
                codedInputByteBufferNano.readMessage(this.parameters_);
                this.parametersCase_ = 2;
            } else if (readTag == 26) {
                if (this.parametersCase_ != 3) {
                    final int i2 = 0;
                    this.parameters_ = new MessageNano(i2) { // from class: com.google.android.systemui.elmyra.proto.nano.ElmyraFilters$LowpassFilter
                        public final /* synthetic */ int $r8$classId;
                        public float cutoff;
                        public float rate;

                        {
                            this.$r8$classId = i2;
                            switch (i2) {
                                case 1:
                                    this.cutoff = 0.0f;
                                    this.rate = 0.0f;
                                    this.cachedSize = -1;
                                    break;
                                default:
                                    this.cutoff = 0.0f;
                                    this.rate = 0.0f;
                                    this.cachedSize = -1;
                                    break;
                            }
                        }

                        @Override // com.google.protobuf.nano.MessageNano
                        public final int computeSerializedSize() {
                            switch (this.$r8$classId) {
                                case 0:
                                    int computeFloatSize = Float.floatToIntBits(this.cutoff) != Float.floatToIntBits(0.0f) ? CodedOutputByteBufferNano.computeFloatSize(1) : 0;
                                    return Float.floatToIntBits(this.rate) != Float.floatToIntBits(0.0f) ? computeFloatSize + CodedOutputByteBufferNano.computeFloatSize(2) : computeFloatSize;
                                default:
                                    int computeFloatSize2 = Float.floatToIntBits(this.cutoff) != Float.floatToIntBits(0.0f) ? CodedOutputByteBufferNano.computeFloatSize(1) : 0;
                                    return Float.floatToIntBits(this.rate) != Float.floatToIntBits(0.0f) ? computeFloatSize2 + CodedOutputByteBufferNano.computeFloatSize(2) : computeFloatSize2;
                            }
                        }

                        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
                        @Override // com.google.protobuf.nano.MessageNano
                        public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano2) {
                            switch (this.$r8$classId) {
                                case 0:
                                    while (true) {
                                        int readTag2 = codedInputByteBufferNano2.readTag();
                                        if (readTag2 == 0) {
                                            break;
                                        } else if (readTag2 == 13) {
                                            this.cutoff = codedInputByteBufferNano2.readFloat();
                                        } else if (readTag2 == 21) {
                                            this.rate = codedInputByteBufferNano2.readFloat();
                                        } else if (!codedInputByteBufferNano2.skipField(readTag2)) {
                                            break;
                                        }
                                    }
                                default:
                                    while (true) {
                                        int readTag3 = codedInputByteBufferNano2.readTag();
                                        if (readTag3 == 0) {
                                            break;
                                        } else if (readTag3 == 13) {
                                            this.cutoff = codedInputByteBufferNano2.readFloat();
                                        } else if (readTag3 == 21) {
                                            this.rate = codedInputByteBufferNano2.readFloat();
                                        } else if (!codedInputByteBufferNano2.skipField(readTag3)) {
                                            break;
                                        }
                                    }
                            }
                            return this;
                        }

                        @Override // com.google.protobuf.nano.MessageNano
                        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
                            switch (this.$r8$classId) {
                                case 0:
                                    if (Float.floatToIntBits(this.cutoff) != Float.floatToIntBits(0.0f)) {
                                        codedOutputByteBufferNano.writeFloat(1, this.cutoff);
                                    }
                                    if (Float.floatToIntBits(this.rate) != Float.floatToIntBits(0.0f)) {
                                        codedOutputByteBufferNano.writeFloat(2, this.rate);
                                        break;
                                    }
                                    break;
                                default:
                                    if (Float.floatToIntBits(this.cutoff) != Float.floatToIntBits(0.0f)) {
                                        codedOutputByteBufferNano.writeFloat(1, this.cutoff);
                                    }
                                    if (Float.floatToIntBits(this.rate) != Float.floatToIntBits(0.0f)) {
                                        codedOutputByteBufferNano.writeFloat(2, this.rate);
                                        break;
                                    }
                                    break;
                            }
                        }
                    };
                }
                codedInputByteBufferNano.readMessage(this.parameters_);
                this.parametersCase_ = 3;
            } else if (readTag == 34) {
                if (this.parametersCase_ != 4) {
                    this.parameters_ = new MessageNano() { // from class: com.google.android.systemui.elmyra.proto.nano.ElmyraFilters$MedianFilter
                        public int windowSize = 0;

                        {
                            this.cachedSize = -1;
                        }

                        @Override // com.google.protobuf.nano.MessageNano
                        public final int computeSerializedSize() {
                            int i3 = this.windowSize;
                            if (i3 == 0) {
                                return 0;
                            }
                            return CodedOutputByteBufferNano.computeRawVarint32Size(i3) + CodedOutputByteBufferNano.computeTagSize(1);
                        }

                        @Override // com.google.protobuf.nano.MessageNano
                        public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano2) {
                            while (true) {
                                int readTag2 = codedInputByteBufferNano2.readTag();
                                if (readTag2 == 0) {
                                    break;
                                }
                                if (readTag2 == 8) {
                                    this.windowSize = codedInputByteBufferNano2.readRawVarint32();
                                } else if (!codedInputByteBufferNano2.skipField(readTag2)) {
                                    break;
                                }
                            }
                            return this;
                        }

                        @Override // com.google.protobuf.nano.MessageNano
                        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
                            int i3 = this.windowSize;
                            if (i3 != 0) {
                                codedOutputByteBufferNano.writeTag(1, 0);
                                codedOutputByteBufferNano.writeRawVarint32(i3);
                            }
                        }
                    };
                }
                codedInputByteBufferNano.readMessage(this.parameters_);
                this.parametersCase_ = 4;
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                break;
            }
        }
        return this;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        if (this.parametersCase_ == 1) {
            codedOutputByteBufferNano.writeMessage(1, this.parameters_);
        }
        if (this.parametersCase_ == 2) {
            codedOutputByteBufferNano.writeMessage(2, this.parameters_);
        }
        if (this.parametersCase_ == 3) {
            codedOutputByteBufferNano.writeMessage(3, this.parameters_);
        }
        if (this.parametersCase_ == 4) {
            codedOutputByteBufferNano.writeMessage(4, this.parameters_);
        }
    }
}
