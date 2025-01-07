package androidx.datastore.preferences.protobuf;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ProtoSyntax {
    public static final /* synthetic */ ProtoSyntax[] $VALUES;
    public static final ProtoSyntax PROTO2;
    public static final ProtoSyntax PROTO3;

    static {
        ProtoSyntax protoSyntax = new ProtoSyntax("PROTO2", 0);
        PROTO2 = protoSyntax;
        ProtoSyntax protoSyntax2 = new ProtoSyntax("PROTO3", 1);
        PROTO3 = protoSyntax2;
        $VALUES = new ProtoSyntax[]{protoSyntax, protoSyntax2};
    }

    public static ProtoSyntax valueOf(String str) {
        return (ProtoSyntax) Enum.valueOf(ProtoSyntax.class, str);
    }

    public static ProtoSyntax[] values() {
        return (ProtoSyntax[]) $VALUES.clone();
    }
}
