package com.android.wm.shell.desktopmode;

import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Slog;
import com.android.internal.protolog.ProtoLog;
import com.android.wm.shell.common.SingleInstanceRemoteListener;
import com.android.wm.shell.desktopmode.DesktopTasksController;
import com.android.wm.shell.protolog.ShellProtoLogGroup;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopTasksController$dragToDesktopStateListener$1 {
    public final /* synthetic */ Object this$0;

    public void onTaskbarCornerRoundingUpdate(boolean z) {
        ProtoLog.v(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, "IDesktopModeImpl: onTaskbarCornerRoundingUpdate doesAnyTaskRequireTaskbarRounding=%s", new Object[]{Boolean.valueOf(z)});
        SingleInstanceRemoteListener singleInstanceRemoteListener = ((DesktopTasksController.IDesktopModeImpl) this.this$0).remoteListener;
        if (singleInstanceRemoteListener == null) {
            singleInstanceRemoteListener = null;
        }
        IInterface iInterface = singleInstanceRemoteListener.mListener;
        if (iInterface == null) {
            Slog.e("SingleInstanceRemoteListener", "Failed remote call on null listener");
            return;
        }
        try {
            IDesktopTaskListener$Stub$Proxy iDesktopTaskListener$Stub$Proxy = (IDesktopTaskListener$Stub$Proxy) iInterface;
            Parcel obtain = Parcel.obtain(iDesktopTaskListener$Stub$Proxy.mRemote);
            try {
                obtain.writeInterfaceToken("com.android.wm.shell.desktopmode.IDesktopTaskListener");
                obtain.writeBoolean(z);
                iDesktopTaskListener$Stub$Proxy.mRemote.transact(3, obtain, null, 1);
                obtain.recycle();
            } catch (Throwable th) {
                obtain.recycle();
                throw th;
            }
        } catch (RemoteException e) {
            Slog.e("SingleInstanceRemoteListener", "Failed remote call", e);
        }
    }
}
