package vendor.google.wireless_charger;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WpcAuthChallengeResponse implements Parcelable {
    public static final Parcelable.Creator CREATOR = new AnonymousClass1();
    public byte certificateChainHashLsb;
    public byte maxProtocolVersion;
    public byte[] signatureR;
    public byte[] signatureS;
    public byte slotPopulatedMask;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: vendor.google.wireless_charger.WpcAuthChallengeResponse$1, reason: invalid class name */
    public final class AnonymousClass1 implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            WpcAuthChallengeResponse wpcAuthChallengeResponse = new WpcAuthChallengeResponse();
            wpcAuthChallengeResponse.maxProtocolVersion = (byte) 0;
            wpcAuthChallengeResponse.slotPopulatedMask = (byte) 0;
            wpcAuthChallengeResponse.certificateChainHashLsb = (byte) 0;
            int dataPosition = parcel.dataPosition();
            int readInt = parcel.readInt();
            try {
                if (readInt < 4) {
                    throw new BadParcelableException("Parcelable too small");
                }
                if (parcel.dataPosition() - dataPosition < readInt) {
                    wpcAuthChallengeResponse.maxProtocolVersion = parcel.readByte();
                    if (parcel.dataPosition() - dataPosition < readInt) {
                        wpcAuthChallengeResponse.slotPopulatedMask = parcel.readByte();
                        if (parcel.dataPosition() - dataPosition < readInt) {
                            wpcAuthChallengeResponse.certificateChainHashLsb = parcel.readByte();
                            if (parcel.dataPosition() - dataPosition < readInt) {
                                wpcAuthChallengeResponse.signatureR = parcel.createByteArray();
                                if (parcel.dataPosition() - dataPosition < readInt) {
                                    wpcAuthChallengeResponse.signatureS = parcel.createByteArray();
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
                return wpcAuthChallengeResponse;
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
            return new WpcAuthChallengeResponse[i];
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
        parcel.writeByte(this.maxProtocolVersion);
        parcel.writeByte(this.slotPopulatedMask);
        parcel.writeByte(this.certificateChainHashLsb);
        parcel.writeByteArray(this.signatureR);
        parcel.writeByteArray(this.signatureS);
        int dataPosition2 = parcel.dataPosition();
        parcel.setDataPosition(dataPosition);
        parcel.writeInt(dataPosition2 - dataPosition);
        parcel.setDataPosition(dataPosition2);
    }
}
