package androidx.datastore.preferences.protobuf;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CodedOutputStreamWriter {
    public final CodedOutputStream$OutputStreamEncoder output;

    public CodedOutputStreamWriter(CodedOutputStream$OutputStreamEncoder codedOutputStream$OutputStreamEncoder) {
        Internal.checkNotNull(codedOutputStream$OutputStreamEncoder, "output");
        this.output = codedOutputStream$OutputStreamEncoder;
        codedOutputStream$OutputStreamEncoder.wrapper = this;
    }

    public final void writeBytes(int i, ByteString byteString) {
        this.output.writeBytes(i, byteString);
    }

    public final void writeGroup(int i, Object obj, Schema schema) {
        CodedOutputStream$OutputStreamEncoder codedOutputStream$OutputStreamEncoder = this.output;
        codedOutputStream$OutputStreamEncoder.writeTag(i, 3);
        schema.writeTo((MessageLite) obj, codedOutputStream$OutputStreamEncoder.wrapper);
        codedOutputStream$OutputStreamEncoder.writeTag(i, 4);
    }
}
