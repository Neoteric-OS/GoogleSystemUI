package com.android.systemui.qs.external;

import android.app.ActivityManager;
import android.app.compat.CompatChanges;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IDeviceIdleController;
import android.os.RemoteException;
import android.os.UserHandle;
import android.provider.DeviceConfig;
import android.service.quicksettings.IQSService;
import android.service.quicksettings.IQSTileService;
import android.util.ArraySet;
import android.util.Log;
import androidx.fragment.app.FragmentManagerViewModel$$ExternalSyntheticOutline0;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.time.SystemClock;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TileLifecycleManager extends BroadcastReceiver implements IQSTileService, ServiceConnection, IBinder.DeathRecipient {
    public final AtomicBoolean isDeathRebindScheduled;
    public final ActivityManager mActivityManager;
    public int mBindTryCount;
    public final AtomicBoolean mBound;
    public final BroadcastDispatcher mBroadcastDispatcher;
    public CustomTile mChangeListener;
    public IBinder mClickBinder;
    public final Context mContext;
    public final boolean mDebug;
    public final TileLifecycleManager$$ExternalSyntheticLambda7 mDeviceConfigChangedListener;
    public final AtomicBoolean mDeviceConfigChangedListenerRegistered;
    public final IDeviceIdleController mDeviceIdleController;
    public final DelayableExecutor mExecutor;
    public final Handler mHandler;
    public final Intent mIntent;
    public final AtomicBoolean mIsBound;
    public boolean mListening;
    public volatile Optional mOptionalWrapper;
    public final PackageManagerAdapter mPackageManagerAdapter;
    public final AtomicBoolean mPackageReceiverRegistered;
    public final Set mQueuedMessages;
    public final SystemClock mSystemClock;
    public long mTempAllowFgsLaunchDuration;
    public final IBinder mToken;
    public final AtomicBoolean mUnbindImmediate;
    public final UserHandle mUser;
    public final AtomicBoolean mUserReceiverRegistered;

    /* JADX WARN: Type inference failed for: r5v7, types: [com.android.systemui.qs.external.TileLifecycleManager$$ExternalSyntheticLambda7] */
    public TileLifecycleManager(Handler handler, Context context, IQSService iQSService, PackageManagerAdapter packageManagerAdapter, BroadcastDispatcher broadcastDispatcher, Intent intent, UserHandle userHandle, ActivityManager activityManager, IDeviceIdleController iDeviceIdleController, DelayableExecutor delayableExecutor, SystemClock systemClock) {
        boolean isLoggable = Log.isLoggable("TileLifecycleManager", 3);
        this.mDebug = isLoggable;
        Binder binder = new Binder();
        this.mToken = binder;
        this.mQueuedMessages = new ArraySet();
        this.mOptionalWrapper = Optional.empty();
        this.isDeathRebindScheduled = new AtomicBoolean(false);
        this.mBound = new AtomicBoolean(false);
        this.mPackageReceiverRegistered = new AtomicBoolean(false);
        this.mUserReceiverRegistered = new AtomicBoolean(false);
        this.mUnbindImmediate = new AtomicBoolean(false);
        this.mIsBound = new AtomicBoolean(false);
        this.mTempAllowFgsLaunchDuration = 15000L;
        this.mDeviceConfigChangedListenerRegistered = new AtomicBoolean(false);
        this.mContext = context;
        this.mHandler = handler;
        this.mIntent = intent;
        intent.putExtra("service", iQSService.asBinder());
        intent.putExtra("token", binder);
        this.mUser = userHandle;
        this.mExecutor = delayableExecutor;
        this.mSystemClock = systemClock;
        this.mPackageManagerAdapter = packageManagerAdapter;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mActivityManager = activityManager;
        this.mDeviceIdleController = iDeviceIdleController;
        this.mDeviceConfigChangedListener = new DeviceConfig.OnPropertiesChangedListener() { // from class: com.android.systemui.qs.external.TileLifecycleManager$$ExternalSyntheticLambda7
            public final void onPropertiesChanged(DeviceConfig.Properties properties) {
                TileLifecycleManager tileLifecycleManager = TileLifecycleManager.this;
                tileLifecycleManager.getClass();
                if ("systemui".equals(properties.getNamespace())) {
                    tileLifecycleManager.mTempAllowFgsLaunchDuration = properties.getLong("property_tile_service_onclick_allow_list_duration", 15000L);
                }
            }
        };
        if (isLoggable) {
            Log.d("TileLifecycleManager", "Creating " + intent + " " + userHandle);
        }
    }

    public static boolean isNullOrFailedAction(Optional optional, Predicate predicate) {
        return !((Boolean) optional.map(new TileLifecycleManager$$ExternalSyntheticLambda14(predicate)).orElse(Boolean.FALSE)).booleanValue();
    }

    public final IBinder asBinder() {
        return (IBinder) this.mOptionalWrapper.map(new TileLifecycleManager$$ExternalSyntheticLambda10()).orElse(null);
    }

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
        if (this.mDebug) {
            Log.d("TileLifecycleManager", "binderDeath " + this.mIntent.getComponent());
        }
        handleDeath();
    }

    public final boolean checkComponentState() {
        String packageName = this.mIntent.getComponent().getPackageName();
        try {
            this.mPackageManagerAdapter.mPackageManager.getPackageInfoAsUser(packageName, 0, this.mUser.getIdentifier());
            this.mIntent.getComponent().getPackageName();
            try {
                ServiceInfo serviceInfo = this.mPackageManagerAdapter.mIPackageManager.getServiceInfo(this.mIntent.getComponent(), 0, this.mUser.getIdentifier());
                if (this.mDebug && serviceInfo == null) {
                    Log.d("TileLifecycleManager", "Can't find component " + this.mIntent.getComponent());
                }
                if (serviceInfo != null) {
                    return true;
                }
            } catch (RemoteException unused) {
            }
        } catch (PackageManager.NameNotFoundException e) {
            if (this.mDebug) {
                Log.d("TileLifecycleManager", "Package not available: " + packageName, e);
            } else {
                FragmentManagerViewModel$$ExternalSyntheticOutline0.m("Package not available: ", packageName, "TileLifecycleManager");
            }
        }
        startPackageListening();
        return false;
    }

    public final void freeWrapper() {
        if (this.mOptionalWrapper.isPresent()) {
            try {
                this.mOptionalWrapper.ifPresent(new Consumer() { // from class: com.android.systemui.qs.external.TileLifecycleManager$$ExternalSyntheticLambda12
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        TileLifecycleManager tileLifecycleManager = TileLifecycleManager.this;
                        tileLifecycleManager.getClass();
                        ((QSTileServiceWrapper) obj).mService.asBinder().unlinkToDeath(tileLifecycleManager, 0);
                    }
                });
            } catch (NoSuchElementException unused) {
                Log.w("TileLifecycleManager", "Trying to unlink not linked recipient for component" + this.mIntent.getComponent().flattenToShortString());
            }
            this.mOptionalWrapper = Optional.empty();
        }
    }

    public final void handleDeath() {
        if (this.mIsBound.get()) {
            ((ExecutorImpl) this.mExecutor).execute(new TileLifecycleManager$$ExternalSyntheticLambda0(this, 2));
        }
    }

    public final boolean hasPendingClick() {
        boolean contains;
        synchronized (this.mQueuedMessages) {
            contains = ((ArraySet) this.mQueuedMessages).contains(2);
        }
        return contains;
    }

    public final boolean isActiveTile() {
        Bundle bundle;
        try {
            ServiceInfo serviceInfo = this.mPackageManagerAdapter.mIPackageManager.getServiceInfo(this.mIntent.getComponent(), 794752, this.mUser.getIdentifier());
            if (serviceInfo == null || (bundle = serviceInfo.metaData) == null) {
                return false;
            }
            return bundle.getBoolean("android.service.quicksettings.ACTIVE_TILE", false);
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // android.content.ServiceConnection
    public final void onBindingDied(ComponentName componentName) {
        if (this.mDebug) {
            Log.d("TileLifecycleManager", "onBindingDied " + componentName);
        }
        handleDeath();
    }

    public final void onClick(final IBinder iBinder) {
        if (this.mDebug) {
            Log.d("TileLifecycleManager", "onClick " + iBinder + " " + this.mIntent.getComponent() + " " + this.mUser);
        }
        if (isNullOrFailedAction(this.mOptionalWrapper, new Predicate() { // from class: com.android.systemui.qs.external.TileLifecycleManager$$ExternalSyntheticLambda13
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                TileLifecycleManager tileLifecycleManager = TileLifecycleManager.this;
                IBinder iBinder2 = iBinder;
                QSTileServiceWrapper qSTileServiceWrapper = (QSTileServiceWrapper) obj;
                try {
                    tileLifecycleManager.mDeviceIdleController.addPowerSaveTempWhitelistApp(tileLifecycleManager.mIntent.getComponent().getPackageName(), tileLifecycleManager.mTempAllowFgsLaunchDuration, tileLifecycleManager.mUser.getIdentifier(), 329, "tile onclick");
                } catch (RemoteException e) {
                    Log.d("TileLifecycleManager", "Caught exception trying to add client package to temp allow list", e);
                }
                qSTileServiceWrapper.getClass();
                try {
                    qSTileServiceWrapper.mService.onClick(iBinder2);
                    return true;
                } catch (Exception e2) {
                    Log.d("IQSTileServiceWrapper", "Caught exception from TileService", e2);
                    return false;
                }
            }
        })) {
            this.mClickBinder = iBinder;
            queueMessage(2);
            handleDeath();
        }
    }

    @Override // android.content.ServiceConnection
    public final void onNullBinding(ComponentName componentName) {
        ((ExecutorImpl) this.mExecutor).execute(new TileLifecycleManager$$ExternalSyntheticLambda11(this, false));
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        CustomTile customTile;
        if (this.mDebug) {
            Log.d("TileLifecycleManager", "onReceive: " + intent);
        }
        if ("android.intent.action.USER_UNLOCKED".equals(intent.getAction()) || Objects.equals(intent.getData().getEncodedSchemeSpecificPart(), this.mIntent.getComponent().getPackageName())) {
            if ("android.intent.action.PACKAGE_CHANGED".equals(intent.getAction()) && (customTile = this.mChangeListener) != null) {
                this.mIntent.getComponent();
                customTile.mHandler.post(new CustomTile$$ExternalSyntheticLambda2(customTile, 1));
            }
            stopPackageListening();
            ((ExecutorImpl) this.mExecutor).execute(new TileLifecycleManager$$ExternalSyntheticLambda0(this, 0));
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        ArraySet arraySet;
        if (this.mDebug) {
            Log.d("TileLifecycleManager", "onServiceConnected " + componentName);
        }
        this.mBindTryCount = 0;
        QSTileServiceWrapper qSTileServiceWrapper = new QSTileServiceWrapper(IQSTileService.Stub.asInterface(iBinder));
        try {
            iBinder.linkToDeath(this, 0);
        } catch (RemoteException unused) {
        }
        this.mOptionalWrapper = Optional.of(qSTileServiceWrapper);
        synchronized (this.mQueuedMessages) {
            arraySet = new ArraySet(this.mQueuedMessages);
            ((ArraySet) this.mQueuedMessages).clear();
        }
        if (arraySet.contains(0)) {
            if (this.mDebug) {
                Log.d("TileLifecycleManager", "Handling pending onAdded " + this.mIntent.getComponent());
            }
            onTileAdded();
        }
        if (this.mListening) {
            if (this.mDebug) {
                Log.d("TileLifecycleManager", "Handling pending onStartListening " + this.mIntent.getComponent());
            }
            onStartListening();
        }
        if (arraySet.contains(2)) {
            if (this.mDebug) {
                Log.d("TileLifecycleManager", "Handling pending onClick " + this.mIntent.getComponent());
            }
            if (this.mListening) {
                onClick(this.mClickBinder);
            } else {
                Log.w("TileLifecycleManager", "Managed to get click on non-listening state... " + this.mIntent.getComponent());
            }
        }
        if (arraySet.contains(3)) {
            if (this.mDebug) {
                Log.d("TileLifecycleManager", "Handling pending onUnlockComplete " + this.mIntent.getComponent());
            }
            if (this.mListening) {
                onUnlockComplete();
            } else {
                Log.w("TileLifecycleManager", "Managed to get unlock on non-listening state... " + this.mIntent.getComponent());
            }
        }
        if (arraySet.contains(4)) {
            if (this.mDebug) {
                Log.d("TileLifecycleManager", "Handling pending onStopListening " + this.mIntent.getComponent());
            }
            if (this.mListening) {
                onStopListening();
            } else {
                Log.w("TileLifecycleManager", "Trying to stop listening when not listening " + this.mIntent.getComponent());
            }
        }
        if (arraySet.contains(1)) {
            if (this.mDebug) {
                Log.d("TileLifecycleManager", "Handling pending onRemoved " + this.mIntent.getComponent());
            }
            if (this.mListening) {
                Log.w("TileLifecycleManager", "Managed to get remove in listening state... " + this.mIntent.getComponent());
                onStopListening();
            }
            onTileRemoved();
        }
        ((ExecutorImpl) this.mExecutor).execute(new TileLifecycleManager$$ExternalSyntheticLambda0(this, 4));
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        if (this.mDebug) {
            Log.d("TileLifecycleManager", "onServiceDisconnected " + componentName);
        }
        freeWrapper();
    }

    public final void onStartListening() {
        if (this.mDebug) {
            Log.d("TileLifecycleManager", "onStartListening " + this.mIntent.getComponent());
        }
        this.mListening = true;
        if (((Boolean) this.mOptionalWrapper.map(new TileLifecycleManager$$ExternalSyntheticLambda14(new TileLifecycleManager$$ExternalSyntheticLambda2(1))).orElse(Boolean.TRUE)).booleanValue()) {
            return;
        }
        handleDeath();
    }

    public final void onStopListening() {
        if (hasPendingClick()) {
            Log.d("TileLifecycleManager", "Enqueue stop listening");
            queueMessage(4);
            return;
        }
        if (this.mDebug) {
            Log.d("TileLifecycleManager", "onStopListening " + this.mIntent.getComponent());
        }
        this.mListening = false;
        if (((Boolean) this.mOptionalWrapper.map(new TileLifecycleManager$$ExternalSyntheticLambda14(new TileLifecycleManager$$ExternalSyntheticLambda2(2))).orElse(Boolean.TRUE)).booleanValue()) {
            return;
        }
        handleDeath();
    }

    public final void onTileAdded() {
        if (this.mDebug) {
            Log.d("TileLifecycleManager", "onTileAdded " + this.mIntent.getComponent());
        }
        if (isNullOrFailedAction(this.mOptionalWrapper, new TileLifecycleManager$$ExternalSyntheticLambda2(0))) {
            queueMessage(0);
            handleDeath();
        }
    }

    public final void onTileRemoved() {
        if (this.mDebug) {
            Log.d("TileLifecycleManager", "onTileRemoved " + this.mIntent.getComponent());
        }
        if (isNullOrFailedAction(this.mOptionalWrapper, new TileLifecycleManager$$ExternalSyntheticLambda2(3))) {
            queueMessage(1);
            handleDeath();
        }
    }

    public final void onUnlockComplete() {
        if (this.mDebug) {
            Log.d("TileLifecycleManager", "onUnlockComplete " + this.mIntent.getComponent());
        }
        if (isNullOrFailedAction(this.mOptionalWrapper, new TileLifecycleManager$$ExternalSyntheticLambda2(4))) {
            queueMessage(3);
            handleDeath();
        }
    }

    public final void queueMessage(int i) {
        synchronized (this.mQueuedMessages) {
            ((ArraySet) this.mQueuedMessages).add(Integer.valueOf(i));
        }
    }

    public final void setBindService(boolean z) {
        if (this.mBound.get() && this.mUnbindImmediate.get()) {
            this.mUnbindImmediate.set(false);
            return;
        }
        this.mBound.set(z);
        if (!z) {
            unbindService();
            return;
        }
        if (this.mDeviceConfigChangedListenerRegistered.compareAndSet(false, true)) {
            DeviceConfig.addOnPropertiesChangedListener("systemui", this.mExecutor, this.mDeviceConfigChangedListener);
            this.mTempAllowFgsLaunchDuration = DeviceConfig.getLong("systemui", "property_tile_service_onclick_allow_list_duration", 15000L);
        }
        if (this.mBindTryCount == 5) {
            startPackageListening();
            return;
        }
        if (checkComponentState()) {
            if (this.mDebug) {
                Log.d("TileLifecycleManager", "Binding service " + this.mIntent + " " + this.mUser);
            }
            this.mBindTryCount++;
            try {
                this.mIsBound.compareAndSet(false, CompatChanges.isChangeEnabled(241766793L, this.mIntent.getComponent().getPackageName(), this.mUser) ? this.mContext.bindServiceAsUser(this.mIntent, this, 33554465, this.mUser) : this.mContext.bindServiceAsUser(this.mIntent, this, 34603041, this.mUser));
                if (this.mIsBound.get()) {
                    return;
                }
                Log.d("TileLifecycleManager", "Failed to bind to service");
                this.mContext.unbindService(this);
            } catch (SecurityException e) {
                Log.e("TileLifecycleManager", "Failed to bind to service", e);
                this.mIsBound.set(false);
            }
        }
    }

    public final void startPackageListening() {
        if (this.mDebug) {
            Log.d("TileLifecycleManager", "startPackageListening " + this.mIntent.getComponent());
        }
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
        intentFilter.addDataScheme("package");
        try {
            this.mPackageReceiverRegistered.set(true);
            this.mContext.registerReceiverAsUser(this, this.mUser, intentFilter, null, this.mHandler, 2);
        } catch (Exception e) {
            this.mPackageReceiverRegistered.set(false);
            Log.e("TileLifecycleManager", "Could not register package receiver " + this.mIntent.getComponent(), e);
        }
        IntentFilter intentFilter2 = new IntentFilter("android.intent.action.USER_UNLOCKED");
        try {
            this.mUserReceiverRegistered.set(true);
            BroadcastDispatcher broadcastDispatcher = this.mBroadcastDispatcher;
            Handler handler = this.mHandler;
            UserHandle userHandle = this.mUser;
            broadcastDispatcher.getClass();
            BroadcastDispatcher.registerReceiverWithHandler$default(broadcastDispatcher, this, intentFilter2, handler, userHandle, 48);
        } catch (Exception e2) {
            this.mUserReceiverRegistered.set(false);
            Log.e("TileLifecycleManager", "Could not register unlock receiver " + this.mIntent.getComponent(), e2);
        }
    }

    public final void stopPackageListening() {
        if (this.mDebug) {
            Log.d("TileLifecycleManager", "stopPackageListening " + this.mIntent.getComponent());
        }
        if (this.mUserReceiverRegistered.compareAndSet(true, false)) {
            this.mBroadcastDispatcher.unregisterReceiver(this);
        }
        if (this.mPackageReceiverRegistered.compareAndSet(true, false)) {
            this.mContext.unregisterReceiver(this);
        }
    }

    public final void unbindService() {
        if (this.mDebug) {
            Log.d("TileLifecycleManager", "Unbinding service " + this.mIntent + " " + this.mUser);
        }
        this.mBindTryCount = 0;
        freeWrapper();
        if (this.mIsBound.get()) {
            try {
                this.mContext.unbindService(this);
            } catch (Exception e) {
                Log.e("TileLifecycleManager", "Failed to unbind service " + this.mIntent.getComponent().flattenToShortString(), e);
            }
            this.mIsBound.set(false);
        }
    }
}
