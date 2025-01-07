package vendor.google.wireless_charger;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FanInfo implements Parcelable {
    public static final Parcelable.Creator CREATOR = new AnonymousClass1();
    public byte count;
    public char currentRpm;
    public byte fanMode;
    public char maximumRpm;
    public char minimumRpm;
    public byte type;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: vendor.google.wireless_charger.FanInfo$1, reason: invalid class name */
    public final class AnonymousClass1 implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            FanInfo fanInfo = new FanInfo();
            fanInfo.fanMode = (byte) 0;
            fanInfo.currentRpm = (char) 0;
            fanInfo.minimumRpm = (char) 0;
            fanInfo.maximumRpm = (char) 0;
            fanInfo.type = (byte) 0;
            fanInfo.count = (byte) 0;
            int dataPosition = parcel.dataPosition();
            int readInt = parcel.readInt();
            try {
                if (readInt < 4) {
                    throw new BadParcelableException("Parcelable too small");
                }
                if (parcel.dataPosition() - dataPosition < readInt) {
                    fanInfo.fanMode = parcel.readByte();
                    if (parcel.dataPosition() - dataPosition < readInt) {
                        fanInfo.currentRpm = (char) parcel.readInt();
                        if (parcel.dataPosition() - dataPosition < readInt) {
                            fanInfo.minimumRpm = (char) parcel.readInt();
                            if (parcel.dataPosition() - dataPosition < readInt) {
                                fanInfo.maximumRpm = (char) parcel.readInt();
                                if (parcel.dataPosition() - dataPosition < readInt) {
                                    fanInfo.type = parcel.readByte();
                                    if (parcel.dataPosition() - dataPosition < readInt) {
                                        fanInfo.count = parcel.readByte();
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
                return fanInfo;
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
            return new FanInfo[i];
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
        parcel.writeByte(this.fanMode);
        parcel.writeInt(this.currentRpm);
        parcel.writeInt(this.minimumRpm);
        parcel.writeInt(this.maximumRpm);
        parcel.writeByte(this.type);
        parcel.writeByte(this.count);
        int dataPosition2 = parcel.dataPosition();
        parcel.setDataPosition(dataPosition);
        parcel.writeInt(dataPosition2 - dataPosition);
        parcel.setDataPosition(dataPosition2);
    }
}
