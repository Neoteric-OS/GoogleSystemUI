package com.android.systemui.qs.external;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Icon;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.UserHandle;
import android.service.quicksettings.IQSService;
import android.service.quicksettings.Tile;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArrayMap;
import com.android.internal.statusbar.StatusBarIcon;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QSHostAdapter;
import com.android.systemui.qs.pipeline.data.repository.CustomTileAddedRepository;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractor;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractorImpl;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.phone.StatusBarIconHolder;
import com.android.systemui.statusbar.phone.ui.StatusBarIconController;
import com.android.systemui.statusbar.phone.ui.StatusBarIconControllerImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$52;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.function.BiConsumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TileServices extends IQSService.Stub {
    public static final TileServices$$ExternalSyntheticLambda1 SERVICE_SORT = new TileServices$$ExternalSyntheticLambda1();
    public final Context mContext;
    public final CustomTileAddedRepository mCustomTileAddedRepository;
    public final DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider mHandlerProvider;
    public final QSHost mHost;
    public final KeyguardStateController mKeyguardStateController;
    public final Handler mMainHandler;
    public final PanelInteractor mPanelInteractor;
    public final AnonymousClass2 mRequestListeningCallback;
    public final StatusBarIconController mStatusBarIconController;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$52 mTileLifecycleManagerFactory;
    public final UserTracker mUserTracker;
    public final ArrayMap mServices = new ArrayMap();
    public final SparseArrayMap mTiles = new SparseArrayMap();
    public final ArrayMap mTokenMap = new ArrayMap();
    public final int mMaxBound = 3;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.qs.external.TileServices$2, reason: invalid class name */
    public final class AnonymousClass2 implements CommandQueue.Callbacks {
        public AnonymousClass2() {
        }

        @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
        public final void requestTileServiceListeningState(ComponentName componentName) {
            TileServices.this.mMainHandler.post(new TileServices$$ExternalSyntheticLambda2(this, componentName));
        }
    }

    public TileServices(QSHost qSHost, DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider switchingProvider, BroadcastDispatcher broadcastDispatcher, UserTracker userTracker, KeyguardStateController keyguardStateController, CommandQueue commandQueue, StatusBarIconController statusBarIconController, PanelInteractor panelInteractor, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$52 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$52, CustomTileAddedRepository customTileAddedRepository) {
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        this.mHost = qSHost;
        this.mKeyguardStateController = keyguardStateController;
        this.mContext = ((QSHostAdapter) qSHost).context;
        this.mHandlerProvider = switchingProvider;
        this.mMainHandler = (Handler) switchingProvider.get();
        this.mUserTracker = userTracker;
        this.mStatusBarIconController = statusBarIconController;
        commandQueue.addCallback((CommandQueue.Callbacks) anonymousClass2);
        this.mPanelInteractor = panelInteractor;
        this.mTileLifecycleManagerFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$52;
        this.mCustomTileAddedRepository = customTileAddedRepository;
    }

    public final Tile getTile(IBinder iBinder) {
        CustomTileInterface tileForToken = getTileForToken(iBinder);
        if (tileForToken != null) {
            verifyCaller(tileForToken);
            return tileForToken.getQsTile();
        }
        StringBuilder sb = new StringBuilder("Tile for token ");
        sb.append(iBinder);
        sb.append("not found. Tiles in map: ");
        final StringBuilder sb2 = new StringBuilder("[");
        synchronized (this.mServices) {
            this.mTokenMap.forEach(new BiConsumer() { // from class: com.android.systemui.qs.external.TileServices$$ExternalSyntheticLambda0
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    StringBuilder sb3 = sb2;
                    CustomTileInterface customTileInterface = (CustomTileInterface) obj2;
                    sb3.append(((IBinder) obj).toString());
                    sb3.append(":");
                    sb3.append(customTileInterface.getComponent().flattenToShortString());
                    sb3.append(":");
                    sb3.append(customTileInterface.getUser());
                    sb3.append(",");
                }
            });
        }
        sb2.append("]");
        sb.append(sb2.toString());
        Log.e("TileServices", sb.toString());
        return null;
    }

    public final CustomTileInterface getTileForToken(IBinder iBinder) {
        CustomTileInterface customTileInterface;
        synchronized (this.mServices) {
            customTileInterface = (CustomTileInterface) this.mTokenMap.get(iBinder);
        }
        return customTileInterface;
    }

    public final boolean isLocked() {
        return ((KeyguardStateControllerImpl) this.mKeyguardStateController).mShowing;
    }

    public final boolean isSecure() {
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.mKeyguardStateController;
        return keyguardStateControllerImpl.mSecure && keyguardStateControllerImpl.mShowing;
    }

    public final void onDialogHidden(IBinder iBinder) {
        CustomTileInterface tileForToken = getTileForToken(iBinder);
        if (tileForToken != null) {
            verifyCaller(tileForToken);
            TileServiceManager tileServiceManager = (TileServiceManager) this.mServices.get(tileForToken);
            Objects.requireNonNull(tileServiceManager);
            tileServiceManager.mShowingDialog = false;
            tileForToken.onDialogHidden();
        }
    }

    public final void onShowDialog(IBinder iBinder) {
        CustomTileInterface tileForToken = getTileForToken(iBinder);
        if (tileForToken != null) {
            verifyCaller(tileForToken);
            tileForToken.onDialogShown();
            ((PanelInteractorImpl) this.mPanelInteractor).shadeController.postAnimateForceCollapseShade();
            TileServiceManager tileServiceManager = (TileServiceManager) this.mServices.get(tileForToken);
            Objects.requireNonNull(tileServiceManager);
            tileServiceManager.mShowingDialog = true;
        }
    }

    public final void onStartActivity(IBinder iBinder) {
        CustomTileInterface tileForToken = getTileForToken(iBinder);
        if (tileForToken != null) {
            verifyCaller(tileForToken);
            ((PanelInteractorImpl) this.mPanelInteractor).shadeController.postAnimateForceCollapseShade();
        }
    }

    public final void onStartSuccessful(IBinder iBinder) {
        CustomTileInterface tileForToken = getTileForToken(iBinder);
        if (tileForToken != null) {
            verifyCaller(tileForToken);
            synchronized (this.mServices) {
                TileServiceManager tileServiceManager = (TileServiceManager) this.mServices.get(tileForToken);
                if (tileServiceManager != null && tileServiceManager.mStarted) {
                    tileServiceManager.mPendingBind = false;
                    tileForToken.refreshState();
                    return;
                }
                Log.e("TileServices", "TileServiceManager not started for " + tileForToken.getComponent(), new IllegalStateException());
            }
        }
    }

    public final void recalculateBindAllowance() {
        ArrayList arrayList;
        synchronized (this.mServices) {
            arrayList = new ArrayList(this.mServices.values());
        }
        int size = arrayList.size();
        if (size > this.mMaxBound) {
            long currentTimeMillis = System.currentTimeMillis();
            for (int i = 0; i < size; i++) {
                TileServiceManager tileServiceManager = (TileServiceManager) arrayList.get(i);
                if (tileServiceManager.mStateManager.hasPendingClick()) {
                    tileServiceManager.mPriority = Integer.MAX_VALUE;
                } else if (tileServiceManager.mShowingDialog) {
                    tileServiceManager.mPriority = 2147483646;
                } else if (tileServiceManager.mJustBound) {
                    tileServiceManager.mPriority = 2147483645;
                } else if (tileServiceManager.mBindRequested) {
                    long j = currentTimeMillis - tileServiceManager.mLastUpdate;
                    if (j > 2147483644) {
                        tileServiceManager.mPriority = 2147483644;
                    } else {
                        tileServiceManager.mPriority = (int) j;
                    }
                } else {
                    tileServiceManager.mPriority = Integer.MIN_VALUE;
                }
            }
            Collections.sort(arrayList, SERVICE_SORT);
        }
        int i2 = 0;
        while (i2 < this.mMaxBound && i2 < size) {
            ((TileServiceManager) arrayList.get(i2)).setBindAllowed(true);
            i2++;
        }
        while (i2 < size) {
            ((TileServiceManager) arrayList.get(i2)).setBindAllowed(false);
            i2++;
        }
    }

    public final void startActivity(IBinder iBinder, PendingIntent pendingIntent) {
        startActivity(getTileForToken(iBinder), pendingIntent);
    }

    public final void startUnlockAndRun(IBinder iBinder) {
        CustomTileInterface tileForToken = getTileForToken(iBinder);
        if (tileForToken != null) {
            verifyCaller(tileForToken);
            tileForToken.startUnlockAndRun();
        }
    }

    public final void updateQsTile(Tile tile, IBinder iBinder) {
        CustomTileInterface tileForToken = getTileForToken(iBinder);
        if (tileForToken != null) {
            int verifyCaller = verifyCaller(tileForToken);
            synchronized (this.mServices) {
                TileServiceManager tileServiceManager = (TileServiceManager) this.mServices.get(tileForToken);
                if (tileServiceManager != null && tileServiceManager.mStarted) {
                    tileServiceManager.mPendingBind = false;
                    tileServiceManager.mLastUpdate = System.currentTimeMillis();
                    if (tileServiceManager.mBound && tileServiceManager.mStateManager.isActiveTile() && tileServiceManager.mListeningFromRequest.compareAndSet(true, false)) {
                        tileServiceManager.mStateManager.onStopListening();
                        tileServiceManager.setBindRequested(false);
                    }
                    tileServiceManager.mServices.recalculateBindAllowance();
                    tileForToken.updateTileState(tile, verifyCaller);
                    tileForToken.refreshState();
                    return;
                }
                Log.e("TileServices", "TileServiceManager not started for " + tileForToken.getComponent(), new IllegalStateException());
            }
        }
    }

    public final void updateStatusIcon(IBinder iBinder, Icon icon, String str) {
        CustomTileInterface tileForToken = getTileForToken(iBinder);
        if (tileForToken != null) {
            verifyCaller(tileForToken);
            try {
                ComponentName component = tileForToken.getComponent();
                String packageName = component.getPackageName();
                UserHandle callingUserHandle = IQSService.Stub.getCallingUserHandle();
                if (this.mContext.getPackageManager().getPackageInfoAsUser(packageName, 0, callingUserHandle.getIdentifier()).applicationInfo.isSystemApp()) {
                    final StatusBarIcon statusBarIcon = icon != null ? new StatusBarIcon(callingUserHandle, packageName, icon, 0, 0, str, StatusBarIcon.Type.SystemIcon) : null;
                    final String className = component.getClassName();
                    this.mMainHandler.post(new Runnable() { // from class: com.android.systemui.qs.external.TileServices.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            StatusBarIconController statusBarIconController = TileServices.this.mStatusBarIconController;
                            String str2 = className;
                            StatusBarIcon statusBarIcon2 = statusBarIcon;
                            StatusBarIconControllerImpl statusBarIconControllerImpl = (StatusBarIconControllerImpl) statusBarIconController;
                            statusBarIconControllerImpl.getClass();
                            if (statusBarIcon2 == null) {
                                if (!str2.endsWith("__external")) {
                                    str2 = str2.concat("__external");
                                }
                                statusBarIconControllerImpl.removeAllIconsForSlot(str2, false);
                            } else {
                                if (!str2.endsWith("__external")) {
                                    str2 = str2.concat("__external");
                                }
                                StatusBarIconHolder statusBarIconHolder = new StatusBarIconHolder();
                                statusBarIconHolder.icon = statusBarIcon2;
                                statusBarIconControllerImpl.setIcon(str2, statusBarIconHolder);
                            }
                        }
                    });
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
    }

    public final int verifyCaller(CustomTileInterface customTileInterface) {
        try {
            int packageUidAsUser = this.mContext.getPackageManager().getPackageUidAsUser(customTileInterface.getComponent().getPackageName(), Binder.getCallingUserHandle().getIdentifier());
            if (Binder.getCallingUid() == packageUidAsUser) {
                return packageUidAsUser;
            }
            throw new SecurityException("Component outside caller's uid");
        } catch (PackageManager.NameNotFoundException e) {
            throw new SecurityException(e);
        }
    }

    public void startActivity(CustomTileInterface customTileInterface, PendingIntent pendingIntent) {
        if (customTileInterface != null) {
            verifyCaller(customTileInterface);
            customTileInterface.startActivityAndCollapse(pendingIntent);
        }
    }
}
