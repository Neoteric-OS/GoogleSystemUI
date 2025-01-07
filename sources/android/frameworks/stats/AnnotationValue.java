package android.frameworks.stats;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AnnotationValue implements Parcelable {
    public static final Parcelable.Creator CREATOR = new AnonymousClass1();
    public int _tag;
    public Object _value;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: android.frameworks.stats.AnnotationValue$1, reason: invalid class name */
    public final class AnonymousClass1 implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            AnnotationValue annotationValue = new AnnotationValue();
            int readInt = parcel.readInt();
            if (readInt == 0) {
                Integer valueOf = Integer.valueOf(parcel.readInt());
                annotationValue._tag = readInt;
                annotationValue._value = valueOf;
            } else {
                if (readInt != 1) {
                    throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(readInt, "union: unknown tag: "));
                }
                Boolean valueOf2 = Boolean.valueOf(parcel.readBoolean());
                annotationValue._tag = readInt;
                annotationValue._value = valueOf2;
            }
            return annotationValue;
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new AnnotationValue[i];
        }
    }

    public static String _tagString(int i) {
        if (i == 0) {
            return "intValue";
        }
        if (i == 1) {
            return "boolValue";
        }
        throw new IllegalStateException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "unknown field: "));
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
        int i2 = this._tag;
        if (i2 == 0) {
            _assertTag(0);
            parcel.writeInt(((Integer) this._value).intValue());
        } else {
            if (i2 != 1) {
                return;
            }
            _assertTag(1);
            parcel.writeBoolean(((Boolean) this._value).booleanValue());
        }
    }
}
