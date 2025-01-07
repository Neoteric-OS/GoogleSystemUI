package com.android.wm.shell.common.pip;

import android.app.PictureInPictureParams;
import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.SurfaceControl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface IPip extends IInterface {
    void abortSwipePipToHome(int i, ComponentName componentName);

    void setLauncherAppIconSize(int i);

    void setLauncherKeepClearAreaHeight(int i, boolean z);

    void setPipAnimationListener(IPipAnimationListener$Stub$Proxy iPipAnimationListener$Stub$Proxy);

    void setPipAnimationTypeToAlpha();

    void setShelfHeight(int i, boolean z);

    Rect startSwipePipToHome(ComponentName componentName, ActivityInfo activityInfo, PictureInPictureParams pictureInPictureParams, int i, Rect rect);

    void stopSwipePipToHome(int i, ComponentName componentName, Rect rect, SurfaceControl surfaceControl, Rect rect2, Rect rect3);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Stub extends Binder implements IPip {
        public Stub() {
            attachInterface(this, "com.android.wm.shell.common.pip.IPip");
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            IPipAnimationListener$Stub$Proxy iPipAnimationListener$Stub$Proxy;
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("com.android.wm.shell.common.pip.IPip");
            }
            if (i == 1598968902) {
                parcel2.writeString("com.android.wm.shell.common.pip.IPip");
                return true;
            }
            switch (i) {
                case 2:
                    ComponentName componentName = (ComponentName) parcel.readTypedObject(ComponentName.CREATOR);
                    ActivityInfo activityInfo = (ActivityInfo) parcel.readTypedObject(ActivityInfo.CREATOR);
                    PictureInPictureParams pictureInPictureParams = (PictureInPictureParams) parcel.readTypedObject(PictureInPictureParams.CREATOR);
                    int readInt = parcel.readInt();
                    Rect rect = (Rect) parcel.readTypedObject(Rect.CREATOR);
                    parcel.enforceNoDataAvail();
                    Rect startSwipePipToHome = startSwipePipToHome(componentName, activityInfo, pictureInPictureParams, readInt, rect);
                    parcel2.writeNoException();
                    parcel2.writeTypedObject(startSwipePipToHome, 1);
                    return true;
                case 3:
                    int readInt2 = parcel.readInt();
                    ComponentName componentName2 = (ComponentName) parcel.readTypedObject(ComponentName.CREATOR);
                    Parcelable.Creator creator = Rect.CREATOR;
                    Rect rect2 = (Rect) parcel.readTypedObject(creator);
                    SurfaceControl surfaceControl = (SurfaceControl) parcel.readTypedObject(SurfaceControl.CREATOR);
                    Rect rect3 = (Rect) parcel.readTypedObject(creator);
                    Rect rect4 = (Rect) parcel.readTypedObject(creator);
                    parcel.enforceNoDataAvail();
                    stopSwipePipToHome(readInt2, componentName2, rect2, surfaceControl, rect3, rect4);
                    return true;
                case 4:
                    int readInt3 = parcel.readInt();
                    ComponentName componentName3 = (ComponentName) parcel.readTypedObject(ComponentName.CREATOR);
                    parcel.enforceNoDataAvail();
                    abortSwipePipToHome(readInt3, componentName3);
                    return true;
                case 5:
                    IBinder readStrongBinder = parcel.readStrongBinder();
                    if (readStrongBinder == null) {
                        iPipAnimationListener$Stub$Proxy = null;
                    } else {
                        IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.android.wm.shell.common.pip.IPipAnimationListener");
                        if (queryLocalInterface == null || !(queryLocalInterface instanceof IPipAnimationListener$Stub$Proxy)) {
                            IPipAnimationListener$Stub$Proxy iPipAnimationListener$Stub$Proxy2 = new IPipAnimationListener$Stub$Proxy();
                            iPipAnimationListener$Stub$Proxy2.mRemote = readStrongBinder;
                            iPipAnimationListener$Stub$Proxy = iPipAnimationListener$Stub$Proxy2;
                        } else {
                            iPipAnimationListener$Stub$Proxy = (IPipAnimationListener$Stub$Proxy) queryLocalInterface;
                        }
                    }
                    parcel.enforceNoDataAvail();
                    setPipAnimationListener(iPipAnimationListener$Stub$Proxy);
                    return true;
                case 6:
                    boolean readBoolean = parcel.readBoolean();
                    int readInt4 = parcel.readInt();
                    parcel.enforceNoDataAvail();
                    setShelfHeight(readInt4, readBoolean);
                    return true;
                case 7:
                    setPipAnimationTypeToAlpha();
                    return true;
                case 8:
                    boolean readBoolean2 = parcel.readBoolean();
                    int readInt5 = parcel.readInt();
                    parcel.enforceNoDataAvail();
                    setLauncherKeepClearAreaHeight(readInt5, readBoolean2);
                    return true;
                case 9:
                    int readInt6 = parcel.readInt();
                    parcel.enforceNoDataAvail();
                    setLauncherAppIconSize(readInt6);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }
    }
}
