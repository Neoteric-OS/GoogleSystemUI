package com.google.android.apps.pixel.pearl.suggestion;

import android.app.assist.AssistContent;
import android.content.ComponentName;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface IPearlActionService extends IInterface {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Stub extends Binder implements IPearlActionService {
        public static final /* synthetic */ int $r8$clinit = 0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Proxy implements IPearlActionService {
            public IBinder mRemote;

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.mRemote;
            }

            public final void saveScreenshotAndProvideActions(Bitmap bitmap, ComponentName componentName, AssistContent assistContent, int i, int i2, PearlActionClientImpl$saveScreenshotAndProvideActions$2$1 pearlActionClientImpl$saveScreenshotAndProvideActions$2$1, PearlActionClientImpl$saveScreenshotAndProvideActions$2$1 pearlActionClientImpl$saveScreenshotAndProvideActions$2$12) {
                Parcel obtain = Parcel.obtain(this.mRemote);
                try {
                    obtain.writeInterfaceToken("com.google.android.apps.pixel.pearl.suggestion.IPearlActionService");
                    obtain.writeTypedObject(bitmap, 0);
                    obtain.writeTypedObject(componentName, 0);
                    obtain.writeTypedObject(assistContent, 0);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeStrongInterface(pearlActionClientImpl$saveScreenshotAndProvideActions$2$1);
                    obtain.writeStrongInterface(pearlActionClientImpl$saveScreenshotAndProvideActions$2$12);
                    this.mRemote.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }
    }
}
