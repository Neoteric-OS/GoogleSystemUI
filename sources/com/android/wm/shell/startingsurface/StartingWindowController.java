package com.android.wm.shell.startingsurface;

import android.R;
import android.app.ActivityThread;
import android.app.TaskInfo;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Slog;
import android.util.SparseIntArray;
import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import com.android.internal.util.function.TriConsumer;
import com.android.launcher3.icons.IconProvider;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.ExternalInterfaceBinder;
import com.android.wm.shell.common.RemoteCallable;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.SingleInstanceRemoteListener;
import com.android.wm.shell.shared.TransactionPool;
import com.android.wm.shell.startingsurface.SplashscreenContentDrawer;
import com.android.wm.shell.startingsurface.StartingWindowController;
import com.android.wm.shell.startingsurface.phone.PhoneStartingWindowTypeAlgorithm;
import com.android.wm.shell.sysui.ShellController;
import com.android.wm.shell.sysui.ShellInit;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StartingWindowController implements RemoteCallable {
    public final Context mContext;
    public final ShellController mShellController;
    public final ShellTaskOrganizer mShellTaskOrganizer;
    public final ShellExecutor mSplashScreenExecutor;
    public final StartingSurfaceDrawer mStartingSurfaceDrawer;
    public final PhoneStartingWindowTypeAlgorithm mStartingWindowTypeAlgorithm;
    public TriConsumer mTaskLaunchingCallback;
    public final StartingSurfaceImpl mImpl = new StartingSurfaceImpl();
    public final SparseIntArray mTaskBackgroundColors = new SparseIntArray();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StartingSurfaceImpl {
        public StartingSurfaceImpl() {
        }

        public final int getBackgroundColor(TaskInfo taskInfo) {
            synchronized (StartingWindowController.this.mTaskBackgroundColors) {
                try {
                    int indexOfKey = StartingWindowController.this.mTaskBackgroundColors.indexOfKey(taskInfo.taskId);
                    if (indexOfKey >= 0) {
                        return StartingWindowController.this.mTaskBackgroundColors.valueAt(indexOfKey);
                    }
                    SplashscreenWindowCreator splashscreenWindowCreator = StartingWindowController.this.mStartingSurfaceDrawer.mSplashscreenWindowCreator;
                    splashscreenWindowCreator.getClass();
                    ActivityInfo activityInfo = taskInfo.topActivityInfo;
                    int i = 0;
                    if (activityInfo != null) {
                        String str = activityInfo.packageName;
                        int i2 = taskInfo.userId;
                        try {
                            Context createPackageContextAsUser = splashscreenWindowCreator.mContext.createPackageContextAsUser(str, 4, UserHandle.of(i2));
                            try {
                                String splashScreenTheme = ActivityThread.getPackageManager().getSplashScreenTheme(str, i2);
                                int identifier = splashScreenTheme != null ? createPackageContextAsUser.getResources().getIdentifier(splashScreenTheme, null, null) : 0;
                                if (identifier == 0) {
                                    identifier = activityInfo.getThemeResource() != 0 ? activityInfo.getThemeResource() : R.style.Theme.DeviceDefault.DayNight;
                                }
                                if (identifier != createPackageContextAsUser.getThemeResId()) {
                                    createPackageContextAsUser.setTheme(identifier);
                                }
                                SplashscreenContentDrawer.SplashScreenWindowAttrs splashScreenWindowAttrs = new SplashscreenContentDrawer.SplashScreenWindowAttrs();
                                SplashscreenContentDrawer.getWindowAttrs(createPackageContextAsUser, splashScreenWindowAttrs);
                                i = SplashscreenContentDrawer.peekWindowBGColor(createPackageContextAsUser, splashScreenWindowAttrs);
                            } catch (RemoteException | RuntimeException e) {
                                Slog.w("ShellStartingWindow", "failed get starting window background color at taskId: " + taskInfo.taskId, e);
                            }
                        } catch (PackageManager.NameNotFoundException e2) {
                            StringBuilder m = ActivityResultRegistry$$ExternalSyntheticOutline0.m("Failed creating package context with package name ", str, " for user ");
                            m.append(taskInfo.userId);
                            Slog.w("ShellStartingWindow", m.toString(), e2);
                        }
                    }
                    return i != 0 ? i : SplashscreenContentDrawer.getSystemBGColor();
                } finally {
                }
            }
        }
    }

    public StartingWindowController(Context context, ShellInit shellInit, ShellController shellController, ShellTaskOrganizer shellTaskOrganizer, ShellExecutor shellExecutor, PhoneStartingWindowTypeAlgorithm phoneStartingWindowTypeAlgorithm, IconProvider iconProvider, TransactionPool transactionPool) {
        this.mContext = context;
        this.mShellController = shellController;
        this.mShellTaskOrganizer = shellTaskOrganizer;
        this.mStartingSurfaceDrawer = new StartingSurfaceDrawer(context, shellExecutor, iconProvider, transactionPool);
        this.mStartingWindowTypeAlgorithm = phoneStartingWindowTypeAlgorithm;
        this.mSplashScreenExecutor = shellExecutor;
        shellInit.addInitCallback(new StartingWindowController$$ExternalSyntheticLambda4(this, 1), this);
    }

    @Override // com.android.wm.shell.common.RemoteCallable
    public final Context getContext() {
        return this.mContext;
    }

    @Override // com.android.wm.shell.common.RemoteCallable
    public final ShellExecutor getRemoteCallExecutor() {
        return this.mSplashScreenExecutor;
    }

    public boolean hasStartingWindowListener() {
        return this.mTaskLaunchingCallback != null;
    }

    public void setStartingWindowListener(TriConsumer triConsumer) {
        this.mTaskLaunchingCallback = triConsumer;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class IStartingWindowImpl extends Binder implements ExternalInterfaceBinder, IInterface {
        public StartingWindowController mController;
        public final SingleInstanceRemoteListener mListener;
        public final StartingWindowController$IStartingWindowImpl$$ExternalSyntheticLambda1 mStartingWindowListener;

        /* JADX WARN: Type inference failed for: r0v1, types: [com.android.wm.shell.startingsurface.StartingWindowController$IStartingWindowImpl$$ExternalSyntheticLambda1] */
        public IStartingWindowImpl(StartingWindowController startingWindowController) {
            attachInterface(this, "com.android.wm.shell.startingsurface.IStartingWindow");
            this.mStartingWindowListener = new TriConsumer() { // from class: com.android.wm.shell.startingsurface.StartingWindowController$IStartingWindowImpl$$ExternalSyntheticLambda1
                public final void accept(Object obj, Object obj2, Object obj3) {
                    final Integer num = (Integer) obj;
                    final Integer num2 = (Integer) obj2;
                    final Integer num3 = (Integer) obj3;
                    StartingWindowController.IStartingWindowImpl.this.mListener.call(new SingleInstanceRemoteListener.RemoteCall() { // from class: com.android.wm.shell.startingsurface.StartingWindowController$IStartingWindowImpl$$ExternalSyntheticLambda4
                        @Override // com.android.wm.shell.common.SingleInstanceRemoteListener.RemoteCall
                        public final void accept(Object obj4) {
                            IStartingWindowListener$Stub$Proxy iStartingWindowListener$Stub$Proxy = (IStartingWindowListener$Stub$Proxy) obj4;
                            int intValue = num.intValue();
                            int intValue2 = num2.intValue();
                            int intValue3 = num3.intValue();
                            Parcel obtain = Parcel.obtain(iStartingWindowListener$Stub$Proxy.mRemote);
                            try {
                                obtain.writeInterfaceToken("com.android.wm.shell.startingsurface.IStartingWindowListener");
                                obtain.writeInt(intValue);
                                obtain.writeInt(intValue2);
                                obtain.writeInt(intValue3);
                                iStartingWindowListener$Stub$Proxy.mRemote.transact(1, obtain, null, 1);
                            } finally {
                                obtain.recycle();
                            }
                        }
                    });
                }
            };
            this.mController = startingWindowController;
            this.mListener = new SingleInstanceRemoteListener(startingWindowController, new Consumer() { // from class: com.android.wm.shell.startingsurface.StartingWindowController$IStartingWindowImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((StartingWindowController) obj).setStartingWindowListener(StartingWindowController.IStartingWindowImpl.this.mStartingWindowListener);
                }
            }, new StartingWindowController$IStartingWindowImpl$$ExternalSyntheticLambda3());
        }

        @Override // com.android.wm.shell.common.ExternalInterfaceBinder
        public final void invalidate() {
            this.mController = null;
            this.mListener.unregister();
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            final IStartingWindowListener$Stub$Proxy iStartingWindowListener$Stub$Proxy;
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("com.android.wm.shell.startingsurface.IStartingWindow");
            }
            if (i == 1598968902) {
                parcel2.writeString("com.android.wm.shell.startingsurface.IStartingWindow");
                return true;
            }
            if (i != 44) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            IBinder readStrongBinder = parcel.readStrongBinder();
            if (readStrongBinder == null) {
                iStartingWindowListener$Stub$Proxy = null;
            } else {
                IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.android.wm.shell.startingsurface.IStartingWindowListener");
                if (queryLocalInterface == null || !(queryLocalInterface instanceof IStartingWindowListener$Stub$Proxy)) {
                    IStartingWindowListener$Stub$Proxy iStartingWindowListener$Stub$Proxy2 = new IStartingWindowListener$Stub$Proxy();
                    iStartingWindowListener$Stub$Proxy2.mRemote = readStrongBinder;
                    iStartingWindowListener$Stub$Proxy = iStartingWindowListener$Stub$Proxy2;
                } else {
                    iStartingWindowListener$Stub$Proxy = (IStartingWindowListener$Stub$Proxy) queryLocalInterface;
                }
            }
            parcel.enforceNoDataAvail();
            ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "setStartingWindowListener", new Consumer() { // from class: com.android.wm.shell.startingsurface.StartingWindowController$IStartingWindowImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    StartingWindowController.IStartingWindowImpl iStartingWindowImpl = StartingWindowController.IStartingWindowImpl.this;
                    IStartingWindowListener$Stub$Proxy iStartingWindowListener$Stub$Proxy3 = iStartingWindowListener$Stub$Proxy;
                    if (iStartingWindowListener$Stub$Proxy3 != null) {
                        iStartingWindowImpl.mListener.register(iStartingWindowListener$Stub$Proxy3);
                    } else {
                        iStartingWindowImpl.mListener.unregister();
                    }
                }
            }, false);
            return true;
        }

        @Override // com.android.wm.shell.common.ExternalInterfaceBinder, android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }
    }
}
