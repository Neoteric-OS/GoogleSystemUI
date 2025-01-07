package com.google.android.apps.pixel.pearl.suggestion;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.util.Log;
import java.util.ArrayList;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PearlActionClientImpl$saveScreenshotAndProvideActions$2$1 extends Binder implements IInterface {
    public final /* synthetic */ Function1 $onSaved;
    public final /* synthetic */ int $r8$classId;

    public PearlActionClientImpl$saveScreenshotAndProvideActions$2$1(int i, Function1 function1) {
        this.$r8$classId = i;
        switch (i) {
            case 1:
                this.$onSaved = function1;
                attachInterface(this, "com.google.android.apps.pixel.pearl.suggestion.IActionsStreamingCallback");
                break;
            default:
                this.$onSaved = function1;
                attachInterface(this, "com.google.android.apps.pixel.pearl.suggestion.IStatusUpdatedCallback");
                break;
        }
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        int i = this.$r8$classId;
        return this;
    }

    public final int getMaxTransactionId() {
        switch (this.$r8$classId) {
        }
        return 1;
    }

    public final String getTransactionName(int i) {
        switch (this.$r8$classId) {
            case 0:
                if (i != 2) {
                    return null;
                }
                return "onUpdated";
            default:
                if (i != 2) {
                    return null;
                }
                return "onNewActions";
        }
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (this.$r8$classId) {
            case 0:
                if (i >= 1 && i <= 16777215) {
                    parcel.enforceInterface("com.google.android.apps.pixel.pearl.suggestion.IStatusUpdatedCallback");
                }
                if (i != 1598968902) {
                    if (i == 2) {
                        long readLong = parcel.readLong();
                        parcel.enforceNoDataAvail();
                        Log.d("PearlActionClient", "Result received: " + readLong);
                        this.$onSaved.invoke(Long.valueOf(readLong));
                        break;
                    } else {
                        break;
                    }
                } else {
                    parcel2.writeString("com.google.android.apps.pixel.pearl.suggestion.IStatusUpdatedCallback");
                    break;
                }
                break;
            default:
                if (i >= 1 && i <= 16777215) {
                    parcel.enforceInterface("com.google.android.apps.pixel.pearl.suggestion.IActionsStreamingCallback");
                }
                if (i != 1598968902) {
                    if (i == 2) {
                        ArrayList createTypedArrayList = parcel.createTypedArrayList(PearlAction.CREATOR);
                        parcel.enforceNoDataAvail();
                        Log.d("PearlActionClient", "Actions received: " + createTypedArrayList);
                        this.$onSaved.invoke(createTypedArrayList);
                        break;
                    } else {
                        break;
                    }
                } else {
                    parcel2.writeString("com.google.android.apps.pixel.pearl.suggestion.IActionsStreamingCallback");
                    break;
                }
                break;
        }
        return true;
    }
}
