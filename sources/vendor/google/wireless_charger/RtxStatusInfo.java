package vendor.google.wireless_charger;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RtxStatusInfo implements Parcelable {
    public static final Parcelable.Creator CREATOR = new AnonymousClass1();
    public int acctype;
    public boolean chgConnected;
    public int iout;
    public int level;
    public byte mode;
    public byte reason;
    public int vout;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: vendor.google.wireless_charger.RtxStatusInfo$1, reason: invalid class name */
    public final class AnonymousClass1 implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            RtxStatusInfo rtxStatusInfo = new RtxStatusInfo();
            rtxStatusInfo.mode = (byte) 0;
            rtxStatusInfo.acctype = 0;
            rtxStatusInfo.chgConnected = false;
            rtxStatusInfo.vout = 0;
            rtxStatusInfo.iout = 0;
            rtxStatusInfo.level = 0;
            rtxStatusInfo.reason = (byte) 15;
            int dataPosition = parcel.dataPosition();
            int readInt = parcel.readInt();
            try {
                if (readInt < 4) {
                    throw new BadParcelableException("Parcelable too small");
                }
                if (parcel.dataPosition() - dataPosition < readInt) {
                    rtxStatusInfo.mode = parcel.readByte();
                    if (parcel.dataPosition() - dataPosition < readInt) {
                        rtxStatusInfo.acctype = parcel.readInt();
                        if (parcel.dataPosition() - dataPosition < readInt) {
                            rtxStatusInfo.chgConnected = parcel.readBoolean();
                            if (parcel.dataPosition() - dataPosition < readInt) {
                                rtxStatusInfo.vout = parcel.readInt();
                                if (parcel.dataPosition() - dataPosition < readInt) {
                                    rtxStatusInfo.iout = parcel.readInt();
                                    if (parcel.dataPosition() - dataPosition < readInt) {
                                        rtxStatusInfo.level = parcel.readInt();
                                        if (parcel.dataPosition() - dataPosition < readInt) {
                                            rtxStatusInfo.reason = parcel.readByte();
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
                parcel.setDataPosition(dataPosition + readInt);
                return rtxStatusInfo;
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
            return new RtxStatusInfo[i];
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
        parcel.writeByte(this.mode);
        parcel.writeInt(this.acctype);
        parcel.writeBoolean(this.chgConnected);
        parcel.writeInt(this.vout);
        parcel.writeInt(this.iout);
        parcel.writeInt(this.level);
        parcel.writeByte(this.reason);
        int dataPosition2 = parcel.dataPosition();
        parcel.setDataPosition(dataPosition);
        parcel.writeInt(dataPosition2 - dataPosition);
        parcel.setDataPosition(dataPosition2);
    }
}
