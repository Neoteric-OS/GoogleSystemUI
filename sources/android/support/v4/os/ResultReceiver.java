package android.support.v4.os;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class ResultReceiver implements Parcelable {
    public static final Parcelable.Creator CREATOR = new AnonymousClass1();
    public IResultReceiver mReceiver;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: android.support.v4.os.ResultReceiver$1, reason: invalid class name */
    public final class AnonymousClass1 implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            IResultReceiver iResultReceiver;
            ResultReceiver resultReceiver = new ResultReceiver();
            IBinder readStrongBinder = parcel.readStrongBinder();
            int i = MyResultReceiver.$r8$clinit;
            if (readStrongBinder == null) {
                iResultReceiver = null;
            } else {
                IInterface queryLocalInterface = readStrongBinder.queryLocalInterface(IResultReceiver.DESCRIPTOR);
                if (queryLocalInterface == null || !(queryLocalInterface instanceof IResultReceiver)) {
                    IResultReceiver$Stub$Proxy iResultReceiver$Stub$Proxy = new IResultReceiver$Stub$Proxy();
                    iResultReceiver$Stub$Proxy.mRemote = readStrongBinder;
                    iResultReceiver = iResultReceiver$Stub$Proxy;
                } else {
                    iResultReceiver = (IResultReceiver) queryLocalInterface;
                }
            }
            resultReceiver.mReceiver = iResultReceiver;
            return resultReceiver;
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new ResultReceiver[i];
        }
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        synchronized (this) {
            try {
                if (this.mReceiver == null) {
                    this.mReceiver = new MyResultReceiver();
                }
                parcel.writeStrongBinder(this.mReceiver.asBinder());
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MyResultReceiver extends Binder implements IResultReceiver {
        public static final /* synthetic */ int $r8$clinit = 0;

        public MyResultReceiver() {
            attachInterface(this, IResultReceiver.DESCRIPTOR);
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            String str = IResultReceiver.DESCRIPTOR;
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(str);
            }
            if (i == 1598968902) {
                parcel2.writeString(str);
                return true;
            }
            if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            ResultReceiver.this.onReceiveResult(parcel.readInt(), (Bundle) (parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null));
            return true;
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }
    }

    public void onReceiveResult(int i, Bundle bundle) {
    }
}
