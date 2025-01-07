package com.android.systemui.unfold.progress;

import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.android.systemui.unfold.UnfoldTransitionProgressProvider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UnfoldTransitionProgressForwarder extends IUnfoldAnimation$Stub implements UnfoldTransitionProgressProvider.TransitionProgressListener {
    public IUnfoldTransitionListener$Stub$Proxy remoteListener;

    @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
    public final void onTransitionFinished() {
        try {
            Log.d("UnfoldTransitionProgressForwarder", "onTransitionFinished");
            IUnfoldTransitionListener$Stub$Proxy iUnfoldTransitionListener$Stub$Proxy = this.remoteListener;
            if (iUnfoldTransitionListener$Stub$Proxy != null) {
                Parcel obtain = Parcel.obtain(iUnfoldTransitionListener$Stub$Proxy.mRemote);
                try {
                    obtain.writeInterfaceToken("com.android.systemui.unfold.progress.IUnfoldTransitionListener");
                    iUnfoldTransitionListener$Stub$Proxy.mRemote.transact(4, obtain, null, 1);
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain.recycle();
                    throw th;
                }
            }
        } catch (RemoteException e) {
            Log.e("UnfoldTransitionProgressForwarder", "Failed call onTransitionFinished", e);
        }
    }

    @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
    public final void onTransitionProgress(float f) {
        try {
            IUnfoldTransitionListener$Stub$Proxy iUnfoldTransitionListener$Stub$Proxy = this.remoteListener;
            if (iUnfoldTransitionListener$Stub$Proxy != null) {
                Parcel obtain = Parcel.obtain(iUnfoldTransitionListener$Stub$Proxy.mRemote);
                try {
                    obtain.writeInterfaceToken("com.android.systemui.unfold.progress.IUnfoldTransitionListener");
                    obtain.writeFloat(f);
                    iUnfoldTransitionListener$Stub$Proxy.mRemote.transact(3, obtain, null, 1);
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain.recycle();
                    throw th;
                }
            }
        } catch (RemoteException e) {
            Log.e("UnfoldTransitionProgressForwarder", "Failed call onTransitionProgress", e);
        }
    }

    @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
    public final void onTransitionStarted() {
        try {
            Log.d("UnfoldTransitionProgressForwarder", "onTransitionStarted");
            IUnfoldTransitionListener$Stub$Proxy iUnfoldTransitionListener$Stub$Proxy = this.remoteListener;
            if (iUnfoldTransitionListener$Stub$Proxy != null) {
                Parcel obtain = Parcel.obtain(iUnfoldTransitionListener$Stub$Proxy.mRemote);
                try {
                    obtain.writeInterfaceToken("com.android.systemui.unfold.progress.IUnfoldTransitionListener");
                    iUnfoldTransitionListener$Stub$Proxy.mRemote.transact(2, obtain, null, 1);
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain.recycle();
                    throw th;
                }
            }
        } catch (RemoteException e) {
            Log.e("UnfoldTransitionProgressForwarder", "Failed call onTransitionStarted", e);
        }
    }
}
