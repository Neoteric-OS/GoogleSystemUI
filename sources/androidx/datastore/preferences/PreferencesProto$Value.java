package androidx.datastore.preferences;

import androidx.datastore.preferences.protobuf.ByteString;
import androidx.datastore.preferences.protobuf.GeneratedMessageLite;
import androidx.datastore.preferences.protobuf.Parser;
import androidx.datastore.preferences.protobuf.RawMessageInfo;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PreferencesProto$Value extends GeneratedMessageLite {
    public static final int BOOLEAN_FIELD_NUMBER = 1;
    public static final int BYTES_FIELD_NUMBER = 8;
    private static final PreferencesProto$Value DEFAULT_INSTANCE;
    public static final int DOUBLE_FIELD_NUMBER = 7;
    public static final int FLOAT_FIELD_NUMBER = 2;
    public static final int INTEGER_FIELD_NUMBER = 3;
    public static final int LONG_FIELD_NUMBER = 4;
    private static volatile Parser PARSER = null;
    public static final int STRING_FIELD_NUMBER = 5;
    public static final int STRING_SET_FIELD_NUMBER = 6;
    private int bitField0_;
    private int valueCase_ = 0;
    private Object value_;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder extends GeneratedMessageLite.Builder {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public enum ValueCase {
        BOOLEAN(1),
        FLOAT(2),
        INTEGER(3),
        LONG(4),
        STRING(5),
        STRING_SET(6),
        DOUBLE(7),
        BYTES(8),
        VALUE_NOT_SET(0);

        private final int value;

        ValueCase(int i) {
            this.value = i;
        }
    }

    static {
        PreferencesProto$Value preferencesProto$Value = new PreferencesProto$Value();
        DEFAULT_INSTANCE = preferencesProto$Value;
        GeneratedMessageLite.registerDefaultInstance(PreferencesProto$Value.class, preferencesProto$Value);
    }

    public static void access$1100(PreferencesProto$Value preferencesProto$Value, long j) {
        preferencesProto$Value.valueCase_ = 4;
        preferencesProto$Value.value_ = Long.valueOf(j);
    }

    public static void access$1300(PreferencesProto$Value preferencesProto$Value, String str) {
        preferencesProto$Value.getClass();
        str.getClass();
        preferencesProto$Value.valueCase_ = 5;
        preferencesProto$Value.value_ = str;
    }

    public static void access$1600(PreferencesProto$Value preferencesProto$Value, PreferencesProto$StringSet preferencesProto$StringSet) {
        preferencesProto$Value.getClass();
        preferencesProto$Value.value_ = preferencesProto$StringSet;
        preferencesProto$Value.valueCase_ = 6;
    }

    public static void access$1900(PreferencesProto$Value preferencesProto$Value, double d) {
        preferencesProto$Value.valueCase_ = 7;
        preferencesProto$Value.value_ = Double.valueOf(d);
    }

    public static void access$2100(PreferencesProto$Value preferencesProto$Value, ByteString byteString) {
        preferencesProto$Value.getClass();
        preferencesProto$Value.valueCase_ = 8;
        preferencesProto$Value.value_ = byteString;
    }

    public static void access$500(PreferencesProto$Value preferencesProto$Value, boolean z) {
        preferencesProto$Value.valueCase_ = 1;
        preferencesProto$Value.value_ = Boolean.valueOf(z);
    }

    public static void access$700(PreferencesProto$Value preferencesProto$Value, float f) {
        preferencesProto$Value.valueCase_ = 2;
        preferencesProto$Value.value_ = Float.valueOf(f);
    }

    public static void access$900(PreferencesProto$Value preferencesProto$Value, int i) {
        preferencesProto$Value.valueCase_ = 3;
        preferencesProto$Value.value_ = Integer.valueOf(i);
    }

    public static PreferencesProto$Value getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) ((GeneratedMessageLite.Builder) DEFAULT_INSTANCE.dynamicMethod(GeneratedMessageLite.MethodToInvoke.NEW_BUILDER));
    }

    @Override // androidx.datastore.preferences.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke) {
        switch (methodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new RawMessageInfo(DEFAULT_INSTANCE, "\u0001\b\u0001\u0001\u0001\b\b\u0000\u0000\u0000\u0001်\u0000\u0002ဴ\u0000\u0003့\u0000\u0004ဵ\u0000\u0005ျ\u0000\u0006ြ\u0000\u0007ဳ\u0000\bွ\u0000", new Object[]{"value_", "valueCase_", "bitField0_", PreferencesProto$StringSet.class});
            case 3:
                return new PreferencesProto$Value();
            case 4:
                return new Builder(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (PreferencesProto$Value.class) {
                        try {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser();
                                PARSER = parser;
                            }
                        } finally {
                        }
                    }
                }
                return parser;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public final boolean getBoolean() {
        if (this.valueCase_ == 1) {
            return ((Boolean) this.value_).booleanValue();
        }
        return false;
    }

    public final ByteString getBytes() {
        return this.valueCase_ == 8 ? (ByteString) this.value_ : ByteString.EMPTY;
    }

    public final double getDouble() {
        if (this.valueCase_ == 7) {
            return ((Double) this.value_).doubleValue();
        }
        return 0.0d;
    }

    public final float getFloat() {
        if (this.valueCase_ == 2) {
            return ((Float) this.value_).floatValue();
        }
        return 0.0f;
    }

    public final int getInteger() {
        if (this.valueCase_ == 3) {
            return ((Integer) this.value_).intValue();
        }
        return 0;
    }

    public final long getLong() {
        if (this.valueCase_ == 4) {
            return ((Long) this.value_).longValue();
        }
        return 0L;
    }

    public final String getString() {
        return this.valueCase_ == 5 ? (String) this.value_ : "";
    }

    public final PreferencesProto$StringSet getStringSet() {
        return this.valueCase_ == 6 ? (PreferencesProto$StringSet) this.value_ : PreferencesProto$StringSet.getDefaultInstance();
    }

    public final ValueCase getValueCase() {
        switch (this.valueCase_) {
            case 0:
                return ValueCase.VALUE_NOT_SET;
            case 1:
                return ValueCase.BOOLEAN;
            case 2:
                return ValueCase.FLOAT;
            case 3:
                return ValueCase.INTEGER;
            case 4:
                return ValueCase.LONG;
            case 5:
                return ValueCase.STRING;
            case 6:
                return ValueCase.STRING_SET;
            case 7:
                return ValueCase.DOUBLE;
            case 8:
                return ValueCase.BYTES;
            default:
                return null;
        }
    }
}
