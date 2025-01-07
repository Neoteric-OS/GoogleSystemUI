package com.android.systemui.recents;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Parcel;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import com.android.systemui.CoreStartable;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.shared.recents.IOverviewProxy;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import java.io.PrintWriter;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class Recents implements CoreStartable, ConfigurationController.ConfigurationListener, CommandQueue.Callbacks {
    public final CommandQueue mCommandQueue;
    public final Context mContext;
    public final OverviewProxyRecentsImpl mImpl;

    public Recents(Context context, OverviewProxyRecentsImpl overviewProxyRecentsImpl, CommandQueue commandQueue) {
        this.mContext = context;
        this.mImpl = overviewProxyRecentsImpl;
        this.mCommandQueue = commandQueue;
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void appTransitionFinished(int i) {
        if (this.mContext.getDisplayId() == i) {
            this.mImpl.getClass();
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void cancelPreloadRecentApps() {
        if (isUserSetup$1()) {
            this.mImpl.getClass();
        }
    }

    @Override // com.android.systemui.CoreStartable, com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        this.mImpl.getClass();
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void hideRecentApps(boolean z, boolean z2) {
        IOverviewProxy iOverviewProxy;
        if (isUserSetup$1() && (iOverviewProxy = this.mImpl.mOverviewProxyService.mOverviewProxy) != null) {
            try {
                IOverviewProxy.Stub.Proxy proxy = (IOverviewProxy.Stub.Proxy) iOverviewProxy;
                Parcel obtain = Parcel.obtain(proxy.mRemote);
                try {
                    obtain.writeInterfaceToken("com.android.systemui.shared.recents.IOverviewProxy");
                    obtain.writeBoolean(z);
                    obtain.writeBoolean(z2);
                    proxy.mRemote.transact(9, obtain, null, 1);
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain.recycle();
                    throw th;
                }
            } catch (RemoteException e) {
                Log.e("OverviewProxyRecentsImpl", "Failed to send overview hide event to launcher.", e);
            }
        }
    }

    public final boolean isUserSetup$1() {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        return (Settings.Global.getInt(contentResolver, "device_provisioned", 0) == 0 || Settings.Secure.getInt(contentResolver, "user_setup_complete", 0) == 0) ? false : true;
    }

    @Override // com.android.systemui.CoreStartable
    public final void onBootCompleted() {
        this.mImpl.getClass();
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void preloadRecentApps() {
        if (isUserSetup$1()) {
            this.mImpl.getClass();
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void showRecentApps(boolean z) {
        IOverviewProxy iOverviewProxy;
        if (isUserSetup$1() && (iOverviewProxy = this.mImpl.mOverviewProxyService.mOverviewProxy) != null) {
            try {
                IOverviewProxy.Stub.Proxy proxy = (IOverviewProxy.Stub.Proxy) iOverviewProxy;
                Parcel obtain = Parcel.obtain(proxy.mRemote);
                try {
                    obtain.writeInterfaceToken("com.android.systemui.shared.recents.IOverviewProxy");
                    obtain.writeBoolean(z);
                    proxy.mRemote.transact(8, obtain, null, 1);
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain.recycle();
                    throw th;
                }
            } catch (RemoteException e) {
                Log.e("OverviewProxyRecentsImpl", "Failed to send overview show event to launcher.", e);
            }
        }
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        this.mCommandQueue.addCallback((CommandQueue.Callbacks) this);
        this.mImpl.mHandler = new Handler();
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.android.systemui.recents.OverviewProxyRecentsImpl$$ExternalSyntheticLambda0] */
    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void toggleRecentApps() {
        if (isUserSetup$1()) {
            final OverviewProxyRecentsImpl overviewProxyRecentsImpl = this.mImpl;
            if (overviewProxyRecentsImpl.mOverviewProxyService.mOverviewProxy != null) {
                final ?? r0 = new Runnable() { // from class: com.android.systemui.recents.OverviewProxyRecentsImpl$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        OverviewProxyService overviewProxyService = OverviewProxyRecentsImpl.this.mOverviewProxyService;
                        try {
                            IOverviewProxy iOverviewProxy = overviewProxyService.mOverviewProxy;
                            if (iOverviewProxy != null) {
                                IOverviewProxy.Stub.Proxy proxy = (IOverviewProxy.Stub.Proxy) iOverviewProxy;
                                Parcel obtain = Parcel.obtain(proxy.mRemote);
                                try {
                                    obtain.writeInterfaceToken("com.android.systemui.shared.recents.IOverviewProxy");
                                    proxy.mRemote.transact(7, obtain, null, 1);
                                    obtain.recycle();
                                    for (int size = ((ArrayList) overviewProxyService.mConnectionCallbacks).size() - 1; size >= 0; size--) {
                                        ((OverviewProxyService.OverviewProxyListener) ((ArrayList) overviewProxyService.mConnectionCallbacks).get(size)).onToggleRecentApps();
                                    }
                                } catch (Throwable th) {
                                    obtain.recycle();
                                    throw th;
                                }
                            }
                        } catch (RemoteException e) {
                            Log.e("OverviewProxyRecentsImpl", "Cannot send toggle recents through proxy service.", e);
                        }
                    }
                };
                if (!((KeyguardStateControllerImpl) overviewProxyRecentsImpl.mKeyguardStateController).mShowing) {
                    r0.run();
                } else {
                    overviewProxyRecentsImpl.mActivityStarter.executeRunnableDismissingKeyguard(new Runnable() { // from class: com.android.systemui.recents.OverviewProxyRecentsImpl$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            OverviewProxyRecentsImpl overviewProxyRecentsImpl2 = OverviewProxyRecentsImpl.this;
                            overviewProxyRecentsImpl2.mHandler.post(r0);
                        }
                    }, null, true, false, true);
                }
            }
        }
    }

    @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
    public final void onConfigChanged(Configuration configuration) {
    }
}
