package com.android.systemui.qs.external;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.provider.DeviceConfig;
import android.util.Log;
import com.android.systemui.qs.QSHostAdapter;
import com.android.systemui.qs.pipeline.data.repository.CustomTileAddedRepository;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.util.concurrency.ExecutorImpl;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class TileServiceManager {
    static final String PREFS_FILE = "CustomTileModes";
    public boolean mBindAllowed;
    public boolean mBindRequested;
    public boolean mBound;
    public final CustomTileAddedRepository mCustomTileAddedRepository;
    public final Handler mHandler;
    public boolean mJustBound;
    final Runnable mJustBoundOver;
    public long mLastUpdate;
    public int mPriority;
    public final TileServices mServices;
    public boolean mShowingDialog;
    public final TileLifecycleManager mStateManager;
    public final AnonymousClass1 mUnbind;
    public final AnonymousClass3 mUninstallReceiver;
    public final UserTracker mUserTracker;
    public boolean mPendingBind = true;
    public boolean mStarted = false;
    public final AtomicBoolean mListeningFromRequest = new AtomicBoolean(false);

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.systemui.qs.external.TileServiceManager$1] */
    /* JADX WARN: Type inference failed for: r3v0, types: [android.content.BroadcastReceiver, com.android.systemui.qs.external.TileServiceManager$3] */
    public TileServiceManager(TileServices tileServices, Handler handler, UserTracker userTracker, CustomTileAddedRepository customTileAddedRepository, TileLifecycleManager tileLifecycleManager) {
        final int i = 0;
        this.mUnbind = new Runnable(this) { // from class: com.android.systemui.qs.external.TileServiceManager.1
            public final /* synthetic */ TileServiceManager this$0;

            {
                this.this$0 = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                switch (i) {
                    case 0:
                        TileServiceManager tileServiceManager = this.this$0;
                        boolean z = tileServiceManager.mBound;
                        if (z && !tileServiceManager.mBindRequested) {
                            if (!z) {
                                Log.e("TileServiceManager", "Service not bound");
                                break;
                            } else {
                                tileServiceManager.mBound = false;
                                tileServiceManager.mJustBound = false;
                                TileLifecycleManager tileLifecycleManager2 = tileServiceManager.mStateManager;
                                ((ExecutorImpl) tileLifecycleManager2.mExecutor).execute(new TileLifecycleManager$$ExternalSyntheticLambda11(tileLifecycleManager2, false));
                                break;
                            }
                        }
                        break;
                    default:
                        TileServiceManager tileServiceManager2 = this.this$0;
                        tileServiceManager2.mJustBound = false;
                        tileServiceManager2.mServices.recalculateBindAllowance();
                        break;
                }
            }
        };
        final int i2 = 1;
        this.mJustBoundOver = new Runnable(this) { // from class: com.android.systemui.qs.external.TileServiceManager.1
            public final /* synthetic */ TileServiceManager this$0;

            {
                this.this$0 = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                switch (i2) {
                    case 0:
                        TileServiceManager tileServiceManager = this.this$0;
                        boolean z = tileServiceManager.mBound;
                        if (z && !tileServiceManager.mBindRequested) {
                            if (!z) {
                                Log.e("TileServiceManager", "Service not bound");
                                break;
                            } else {
                                tileServiceManager.mBound = false;
                                tileServiceManager.mJustBound = false;
                                TileLifecycleManager tileLifecycleManager2 = tileServiceManager.mStateManager;
                                ((ExecutorImpl) tileLifecycleManager2.mExecutor).execute(new TileLifecycleManager$$ExternalSyntheticLambda11(tileLifecycleManager2, false));
                                break;
                            }
                        }
                        break;
                    default:
                        TileServiceManager tileServiceManager2 = this.this$0;
                        tileServiceManager2.mJustBound = false;
                        tileServiceManager2.mServices.recalculateBindAllowance();
                        break;
                }
            }
        };
        ?? r3 = new BroadcastReceiver() { // from class: com.android.systemui.qs.external.TileServiceManager.3
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                if ("android.intent.action.PACKAGE_REMOVED".equals(intent.getAction())) {
                    String encodedSchemeSpecificPart = intent.getData().getEncodedSchemeSpecificPart();
                    ComponentName component = TileServiceManager.this.mStateManager.mIntent.getComponent();
                    if (Objects.equals(encodedSchemeSpecificPart, component.getPackageName())) {
                        if (intent.getBooleanExtra("android.intent.extra.REPLACING", false)) {
                            Intent intent2 = new Intent("android.service.quicksettings.action.QS_TILE");
                            intent2.setPackage(encodedSchemeSpecificPart);
                            for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentServicesAsUser(intent2, 0, ((UserTrackerImpl) TileServiceManager.this.mUserTracker).getUserId())) {
                                if (Objects.equals(resolveInfo.serviceInfo.packageName, component.getPackageName()) && Objects.equals(resolveInfo.serviceInfo.name, component.getClassName())) {
                                    return;
                                }
                            }
                        }
                        ((QSHostAdapter) TileServiceManager.this.mServices.mHost).removeTile(CustomTile.toSpec(component));
                    }
                }
            }
        };
        this.mUninstallReceiver = r3;
        this.mServices = tileServices;
        this.mHandler = handler;
        this.mStateManager = tileLifecycleManager;
        this.mUserTracker = userTracker;
        this.mCustomTileAddedRepository = customTileAddedRepository;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        tileServices.mContext.registerReceiverAsUser(r3, ((UserTrackerImpl) userTracker).getUserHandle(), intentFilter, null, handler, 2);
    }

    public final void bindService() {
        if (this.mBound) {
            Log.e("TileServiceManager", "Service already bound");
            return;
        }
        TileLifecycleManager tileLifecycleManager = this.mStateManager;
        if (!tileLifecycleManager.mBound.get()) {
            this.mPendingBind = true;
        }
        this.mBound = true;
        this.mJustBound = true;
        this.mHandler.postDelayed(this.mJustBoundOver, 5000L);
        ((ExecutorImpl) tileLifecycleManager.mExecutor).execute(new TileLifecycleManager$$ExternalSyntheticLambda11(tileLifecycleManager, true));
    }

    public final void handleDestroy() {
        setBindAllowed(false);
        this.mServices.mContext.unregisterReceiver(this.mUninstallReceiver);
        TileLifecycleManager tileLifecycleManager = this.mStateManager;
        if (tileLifecycleManager.mDebug) {
            Log.d("TileLifecycleManager", "handleDestroy");
        }
        if (tileLifecycleManager.mPackageReceiverRegistered.get() || tileLifecycleManager.mUserReceiverRegistered.get()) {
            tileLifecycleManager.stopPackageListening();
        }
        tileLifecycleManager.mChangeListener = null;
        TileLifecycleManager$$ExternalSyntheticLambda7 tileLifecycleManager$$ExternalSyntheticLambda7 = tileLifecycleManager.mDeviceConfigChangedListener;
        if (tileLifecycleManager$$ExternalSyntheticLambda7 != null) {
            DeviceConfig.removeOnPropertiesChangedListener(tileLifecycleManager$$ExternalSyntheticLambda7);
        }
    }

    public final boolean isToggleableTile() {
        Bundle bundle;
        TileLifecycleManager tileLifecycleManager = this.mStateManager;
        tileLifecycleManager.getClass();
        try {
            ServiceInfo serviceInfo = tileLifecycleManager.mPackageManagerAdapter.mIPackageManager.getServiceInfo(tileLifecycleManager.mIntent.getComponent(), 794752, tileLifecycleManager.mUser.getIdentifier());
            if (serviceInfo == null || (bundle = serviceInfo.metaData) == null) {
                return false;
            }
            return bundle.getBoolean("android.service.quicksettings.TOGGLEABLE_TILE", false);
        } catch (RemoteException unused) {
            return false;
        }
    }

    public final void setBindAllowed(boolean z) {
        boolean z2;
        if (this.mBindAllowed == z) {
            return;
        }
        this.mBindAllowed = z;
        if (z || !(z2 = this.mBound)) {
            if (z && this.mBindRequested && !this.mBound) {
                bindService();
                return;
            }
            return;
        }
        if (!z2) {
            Log.e("TileServiceManager", "Service not bound");
            return;
        }
        this.mBound = false;
        this.mJustBound = false;
        TileLifecycleManager tileLifecycleManager = this.mStateManager;
        ((ExecutorImpl) tileLifecycleManager.mExecutor).execute(new TileLifecycleManager$$ExternalSyntheticLambda11(tileLifecycleManager, false));
    }

    public final void setBindRequested(boolean z) {
        if (this.mBindRequested == z) {
            return;
        }
        this.mBindRequested = z;
        boolean z2 = this.mBindAllowed;
        AnonymousClass1 anonymousClass1 = this.mUnbind;
        Handler handler = this.mHandler;
        if (z2 && z && !this.mBound) {
            handler.removeCallbacks(anonymousClass1);
            bindService();
        } else {
            this.mServices.recalculateBindAllowance();
        }
        if (!this.mBound || this.mBindRequested) {
            return;
        }
        handler.postDelayed(anonymousClass1, 30000L);
    }
}
