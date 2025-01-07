package android.frameworks.stats;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class VendorAtomValue implements Parcelable {
    public static final Parcelable.Creator CREATOR = new AnonymousClass1();
    public int _tag;
    public Object _value;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: android.frameworks.stats.VendorAtomValue$1, reason: invalid class name */
    public final class AnonymousClass1 implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            VendorAtomValue vendorAtomValue = new VendorAtomValue();
            int readInt = parcel.readInt();
            switch (readInt) {
                case 0:
                    Integer valueOf = Integer.valueOf(parcel.readInt());
                    vendorAtomValue._tag = readInt;
                    vendorAtomValue._value = valueOf;
                    return vendorAtomValue;
                case 1:
                    Long valueOf2 = Long.valueOf(parcel.readLong());
                    vendorAtomValue._tag = readInt;
                    vendorAtomValue._value = valueOf2;
                    return vendorAtomValue;
                case 2:
                    Float valueOf3 = Float.valueOf(parcel.readFloat());
                    vendorAtomValue._tag = readInt;
                    vendorAtomValue._value = valueOf3;
                    return vendorAtomValue;
                case 3:
                    String readString = parcel.readString();
                    vendorAtomValue._tag = readInt;
                    vendorAtomValue._value = readString;
                    return vendorAtomValue;
                case 4:
                    Boolean valueOf4 = Boolean.valueOf(parcel.readBoolean());
                    vendorAtomValue._tag = readInt;
                    vendorAtomValue._value = valueOf4;
                    return vendorAtomValue;
                case 5:
                    int[] createIntArray = parcel.createIntArray();
                    vendorAtomValue._tag = readInt;
                    vendorAtomValue._value = createIntArray;
                    return vendorAtomValue;
                case 6:
                    long[] createLongArray = parcel.createLongArray();
                    vendorAtomValue._tag = readInt;
                    vendorAtomValue._value = createLongArray;
                    return vendorAtomValue;
                case 7:
                    float[] createFloatArray = parcel.createFloatArray();
                    vendorAtomValue._tag = readInt;
                    vendorAtomValue._value = createFloatArray;
                    return vendorAtomValue;
                case 8:
                    String[] createStringArray = parcel.createStringArray();
                    vendorAtomValue._tag = readInt;
                    vendorAtomValue._value = createStringArray;
                    return vendorAtomValue;
                case 9:
                    boolean[] createBooleanArray = parcel.createBooleanArray();
                    vendorAtomValue._tag = readInt;
                    vendorAtomValue._value = createBooleanArray;
                    return vendorAtomValue;
                case 10:
                    byte[] createByteArray = parcel.createByteArray();
                    vendorAtomValue._tag = readInt;
                    vendorAtomValue._value = createByteArray;
                    return vendorAtomValue;
                default:
                    throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(readInt, "union: unknown tag: "));
            }
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new VendorAtomValue[i];
        }
    }

    public VendorAtomValue(int i, Object obj) {
        this._tag = i;
        this._value = obj;
    }

    public static String _tagString(int i) {
        switch (i) {
            case 0:
                return "intValue";
            case 1:
                return "longValue";
            case 2:
                return "floatValue";
            case 3:
                return "stringValue";
            case 4:
                return "boolValue";
            case 5:
                return "repeatedIntValue";
            case 6:
                return "repeatedLongValue";
            case 7:
                return "repeatedFloatValue";
            case 8:
                return "repeatedStringValue";
            case 9:
                return "repeatedBoolValue";
            case 10:
                return "byteArrayValue";
            default:
                throw new IllegalStateException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "unknown field: "));
        }
    }

    public static VendorAtomValue intValue(int i) {
        return new VendorAtomValue(0, Integer.valueOf(i));
    }

    public final void _assertTag(int i) {
        if (this._tag == i) {
            return;
        }
        throw new IllegalStateException("bad access: " + _tagString(i) + ", " + _tagString(this._tag) + " is available.");
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final int getStability() {
        return 1;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this._tag);
        switch (this._tag) {
            case 0:
                _assertTag(0);
                parcel.writeInt(((Integer) this._value).intValue());
                break;
            case 1:
                _assertTag(1);
                parcel.writeLong(((Long) this._value).longValue());
                break;
            case 2:
                _assertTag(2);
                parcel.writeFloat(((Float) this._value).floatValue());
                break;
            case 3:
                _assertTag(3);
                parcel.writeString((String) this._value);
                break;
            case 4:
                _assertTag(4);
                parcel.writeBoolean(((Boolean) this._value).booleanValue());
                break;
            case 5:
                _assertTag(5);
                parcel.writeIntArray((int[]) this._value);
                break;
            case 6:
                _assertTag(6);
                parcel.writeLongArray((long[]) this._value);
                break;
            case 7:
                _assertTag(7);
                parcel.writeFloatArray((float[]) this._value);
                break;
            case 8:
                _assertTag(8);
                parcel.writeStringArray((String[]) this._value);
                break;
            case 9:
                _assertTag(9);
                parcel.writeBooleanArray((boolean[]) this._value);
                break;
            case 10:
                _assertTag(10);
                parcel.writeByteArray((byte[]) this._value);
                break;
        }
    }
}
