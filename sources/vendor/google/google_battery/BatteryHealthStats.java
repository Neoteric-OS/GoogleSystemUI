package vendor.google.google_battery;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BatteryHealthStats implements Parcelable {
    public static final Parcelable.Creator CREATOR = new AnonymousClass1();
    public int healthAlgo;
    public int healthCapacityIndex;
    public int healthImpedanceIndex;
    public int healthIndex;
    public int healthStatus;
    public int healthSwellIndex;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: vendor.google.google_battery.BatteryHealthStats$1, reason: invalid class name */
    public final class AnonymousClass1 implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            BatteryHealthStats batteryHealthStats = new BatteryHealthStats();
            batteryHealthStats.healthAlgo = 0;
            batteryHealthStats.healthStatus = 0;
            batteryHealthStats.healthIndex = 0;
            batteryHealthStats.healthCapacityIndex = 0;
            batteryHealthStats.healthImpedanceIndex = 0;
            batteryHealthStats.healthSwellIndex = 0;
            int dataPosition = parcel.dataPosition();
            int readInt = parcel.readInt();
            try {
                if (readInt < 4) {
                    throw new BadParcelableException("Parcelable too small");
                }
                if (parcel.dataPosition() - dataPosition < readInt) {
                    batteryHealthStats.healthAlgo = parcel.readInt();
                    if (parcel.dataPosition() - dataPosition < readInt) {
                        batteryHealthStats.healthStatus = parcel.readInt();
                        if (parcel.dataPosition() - dataPosition < readInt) {
                            batteryHealthStats.healthIndex = parcel.readInt();
                            if (parcel.dataPosition() - dataPosition < readInt) {
                                batteryHealthStats.healthCapacityIndex = parcel.readInt();
                                if (parcel.dataPosition() - dataPosition < readInt) {
                                    batteryHealthStats.healthImpedanceIndex = parcel.readInt();
                                    if (parcel.dataPosition() - dataPosition < readInt) {
                                        batteryHealthStats.healthSwellIndex = parcel.readInt();
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
                return batteryHealthStats;
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
            return new BatteryHealthStats[i];
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
        parcel.writeInt(this.healthAlgo);
        parcel.writeInt(this.healthStatus);
        parcel.writeInt(this.healthIndex);
        parcel.writeInt(this.healthCapacityIndex);
        parcel.writeInt(this.healthImpedanceIndex);
        parcel.writeInt(this.healthSwellIndex);
        int dataPosition2 = parcel.dataPosition();
        parcel.setDataPosition(dataPosition);
        parcel.writeInt(dataPosition2 - dataPosition);
        parcel.setDataPosition(dataPosition2);
    }
}
