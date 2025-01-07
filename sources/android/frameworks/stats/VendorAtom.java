package android.frameworks.stats;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class VendorAtom implements Parcelable {
    public static final Parcelable.Creator CREATOR = new AnonymousClass1();
    public Annotation[] atomAnnotations;
    public int atomId = 0;
    public String reverseDomainName;
    public VendorAtomValue[] values;
    public AnnotationSet[] valuesAnnotations;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: android.frameworks.stats.VendorAtom$1, reason: invalid class name */
    public final class AnonymousClass1 implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            VendorAtom vendorAtom = new VendorAtom();
            int dataPosition = parcel.dataPosition();
            int readInt = parcel.readInt();
            try {
                if (readInt < 4) {
                    throw new BadParcelableException("Parcelable too small");
                }
                if (parcel.dataPosition() - dataPosition < readInt) {
                    vendorAtom.reverseDomainName = parcel.readString();
                    if (parcel.dataPosition() - dataPosition < readInt) {
                        vendorAtom.atomId = parcel.readInt();
                        if (parcel.dataPosition() - dataPosition < readInt) {
                            vendorAtom.values = (VendorAtomValue[]) parcel.createTypedArray(VendorAtomValue.CREATOR);
                            if (parcel.dataPosition() - dataPosition < readInt) {
                                vendorAtom.valuesAnnotations = (AnnotationSet[]) parcel.createTypedArray(AnnotationSet.CREATOR);
                                if (parcel.dataPosition() - dataPosition < readInt) {
                                    vendorAtom.atomAnnotations = (Annotation[]) parcel.createTypedArray(Annotation.CREATOR);
                                    if (dataPosition > Integer.MAX_VALUE - readInt) {
                                        throw new BadParcelableException("Overflow in the size of parcelable");
                                    }
                                } else if (dataPosition > Integer.MAX_VALUE - readInt) {
                                    throw new BadParcelableException("Overflow in the size of parcelable");
                                }
                            } else if (dataPosition > Integer.MAX_VALUE - readInt) {
                                throw new BadParcelableException("Overflow in the size of parcelable");
                            }
                        } else if (dataPosition > Integer.MAX_VALUE - readInt) {
                            throw new BadParcelableException("Overflow in the size of parcelable");
                        }
                    } else if (dataPosition > Integer.MAX_VALUE - readInt) {
                        throw new BadParcelableException("Overflow in the size of parcelable");
                    }
                } else if (dataPosition > Integer.MAX_VALUE - readInt) {
                    throw new BadParcelableException("Overflow in the size of parcelable");
                }
                parcel.setDataPosition(dataPosition + readInt);
                return vendorAtom;
            } catch (Throwable th) {
                if (dataPosition > Integer.MAX_VALUE - readInt) {
                    throw new BadParcelableException("Overflow in the size of parcelable");
                }
                parcel.setDataPosition(dataPosition + readInt);
                throw th;
            }
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new VendorAtom[i];
        }
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return describeContents(this.atomAnnotations) | describeContents(this.values) | describeContents(this.valuesAnnotations);
    }

    public final int getStability() {
        return 1;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int dataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeString(this.reverseDomainName);
        parcel.writeInt(this.atomId);
        parcel.writeTypedArray(this.values, i);
        parcel.writeTypedArray(this.valuesAnnotations, i);
        parcel.writeTypedArray(this.atomAnnotations, i);
        int dataPosition2 = parcel.dataPosition();
        parcel.setDataPosition(dataPosition);
        parcel.writeInt(dataPosition2 - dataPosition);
        parcel.setDataPosition(dataPosition2);
    }

    public static int describeContents(Object obj) {
        if (obj == null) {
            return 0;
        }
        if (obj instanceof Object[]) {
            int i = 0;
            for (Object obj2 : (Object[]) obj) {
                i |= describeContents(obj2);
            }
            return i;
        }
        if (obj instanceof Parcelable) {
            return ((Parcelable) obj).describeContents();
        }
        return 0;
    }
}
