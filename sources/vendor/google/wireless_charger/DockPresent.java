package vendor.google.wireless_charger;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DockPresent implements Parcelable {
    public static final Parcelable.Creator CREATOR = new AnonymousClass1();
    public boolean docked;
    public int id;
    public boolean isGetInfoSupported;
    public byte orientation;
    public int ptmc;
    public byte type;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: vendor.google.wireless_charger.DockPresent$1, reason: invalid class name */
    public final class AnonymousClass1 implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            DockPresent dockPresent = new DockPresent();
            dockPresent.docked = false;
            dockPresent.type = (byte) 15;
            dockPresent.orientation = (byte) 0;
            dockPresent.isGetInfoSupported = false;
            dockPresent.id = 0;
            dockPresent.ptmc = 0;
            int dataPosition = parcel.dataPosition();
            int readInt = parcel.readInt();
            try {
                if (readInt < 4) {
                    throw new BadParcelableException("Parcelable too small");
                }
                if (parcel.dataPosition() - dataPosition < readInt) {
                    dockPresent.docked = parcel.readBoolean();
                    if (parcel.dataPosition() - dataPosition < readInt) {
                        dockPresent.type = parcel.readByte();
                        if (parcel.dataPosition() - dataPosition < readInt) {
                            dockPresent.orientation = parcel.readByte();
                            if (parcel.dataPosition() - dataPosition < readInt) {
                                dockPresent.isGetInfoSupported = parcel.readBoolean();
                                if (parcel.dataPosition() - dataPosition < readInt) {
                                    dockPresent.id = parcel.readInt();
                                    if (parcel.dataPosition() - dataPosition < readInt) {
                                        dockPresent.ptmc = parcel.readInt();
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
                } else if (dataPosition > Integer.MAX_VALUE - readInt) {
                    throw new BadParcelableException("Overflow in the size of parcelable");
                }
                parcel.setDataPosition(dataPosition + readInt);
                return dockPresent;
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
            return new DockPresent[i];
        }
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
        int dataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeBoolean(this.docked);
        parcel.writeByte(this.type);
        parcel.writeByte(this.orientation);
        parcel.writeBoolean(this.isGetInfoSupported);
        parcel.writeInt(this.id);
        parcel.writeInt(this.ptmc);
        int dataPosition2 = parcel.dataPosition();
        parcel.setDataPosition(dataPosition);
        parcel.writeInt(dataPosition2 - dataPosition);
        parcel.setDataPosition(dataPosition2);
    }
}
