package vendor.google.wireless_charger;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DockInfo implements Parcelable {
    public static final Parcelable.Creator CREATOR = new AnonymousClass1();
    public boolean isGetInfoSupported;
    public String issuer;
    public String manufacturer;
    public int maxFwSize;
    public String model;
    public byte orientation;
    public String qi;
    public String serial;
    public byte type;
    public FirmwareVersion version;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: vendor.google.wireless_charger.DockInfo$1, reason: invalid class name */
    public final class AnonymousClass1 implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            DockInfo dockInfo = new DockInfo();
            dockInfo.maxFwSize = 0;
            dockInfo.isGetInfoSupported = false;
            dockInfo.orientation = (byte) 0;
            dockInfo.type = (byte) 15;
            dockInfo.qi = "";
            dockInfo.issuer = "";
            int dataPosition = parcel.dataPosition();
            int readInt = parcel.readInt();
            try {
                if (readInt < 4) {
                    throw new BadParcelableException("Parcelable too small");
                }
                if (parcel.dataPosition() - dataPosition < readInt) {
                    dockInfo.manufacturer = parcel.readString();
                    if (parcel.dataPosition() - dataPosition < readInt) {
                        dockInfo.model = parcel.readString();
                        if (parcel.dataPosition() - dataPosition < readInt) {
                            dockInfo.serial = parcel.readString();
                            if (parcel.dataPosition() - dataPosition < readInt) {
                                dockInfo.maxFwSize = parcel.readInt();
                                if (parcel.dataPosition() - dataPosition < readInt) {
                                    dockInfo.isGetInfoSupported = parcel.readBoolean();
                                    if (parcel.dataPosition() - dataPosition < readInt) {
                                        dockInfo.version = (FirmwareVersion) parcel.readTypedObject(FirmwareVersion.CREATOR);
                                        if (parcel.dataPosition() - dataPosition < readInt) {
                                            dockInfo.orientation = parcel.readByte();
                                            if (parcel.dataPosition() - dataPosition < readInt) {
                                                dockInfo.type = parcel.readByte();
                                                if (parcel.dataPosition() - dataPosition < readInt) {
                                                    dockInfo.qi = parcel.readString();
                                                    if (parcel.dataPosition() - dataPosition < readInt) {
                                                        dockInfo.issuer = parcel.readString();
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
                return dockInfo;
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
            return new DockInfo[i];
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
        parcel.writeString(this.manufacturer);
        parcel.writeString(this.model);
        parcel.writeString(this.serial);
        parcel.writeInt(this.maxFwSize);
        parcel.writeBoolean(this.isGetInfoSupported);
        parcel.writeTypedObject(this.version, i);
        parcel.writeByte(this.orientation);
        parcel.writeByte(this.type);
        parcel.writeString(this.qi);
        parcel.writeString(this.issuer);
        int dataPosition2 = parcel.dataPosition();
        parcel.setDataPosition(dataPosition);
        parcel.writeInt(dataPosition2 - dataPosition);
        parcel.setDataPosition(dataPosition2);
    }
}
