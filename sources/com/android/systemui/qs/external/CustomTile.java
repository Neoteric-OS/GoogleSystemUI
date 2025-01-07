package com.android.systemui.qs.external;

import android.app.IUriGrantsManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.metrics.LogMaker;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.service.quicksettings.Tile;
import android.util.Log;
import android.view.IWindowManager;
import android.view.WindowManagerGlobal;
import android.widget.Button;
import android.widget.Switch;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.clocks.WeatherData;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QSHostAdapter;
import com.android.systemui.qs.QsEventLoggerImpl;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.pipeline.data.repository.CustomTileAddedSharedPrefsRepository;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.settings.UserFileManagerImpl;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.util.concurrency.ExecutorImpl;
import dagger.Lazy;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CustomTile extends QSTileImpl implements CustomTileInterface {
    public final ComponentName mComponent;
    public final CustomTileStatePersisterImpl mCustomTileStatePersister;
    public Icon mDefaultIcon;
    public final DisplayTracker mDisplayTracker;
    public Expandable mExpandableClicked;
    public final IUriGrantsManager mIUriGrantsManager;
    public final AtomicBoolean mInitialDefaultIconFetched;
    public boolean mIsShowingDialog;
    public boolean mIsTokenGranted;
    public final TileServiceKey mKey;
    public boolean mListening;
    public final TileLifecycleManager mService;
    public final TileServiceManager mServiceManager;
    public int mServiceUid;
    public final Tile mTile;
    public final TileServices mTileServices;
    public final IBinder mToken;
    public final int mUser;
    public final Context mUserContext;
    public final IWindowManager mWindowManager;

    public CustomTile(Lazy lazy, QsEventLoggerImpl qsEventLoggerImpl, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, String str, Context context, CustomTileStatePersisterImpl customTileStatePersisterImpl, TileServices tileServices, DisplayTracker displayTracker, IUriGrantsManager iUriGrantsManager) {
        super((QSHost) lazy.get(), qsEventLoggerImpl, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.mToken = new Binder();
        this.mInitialDefaultIconFetched = new AtomicBoolean(false);
        this.mServiceUid = -1;
        this.mTileServices = tileServices;
        this.mWindowManager = WindowManagerGlobal.getWindowManagerService();
        ComponentName unflattenFromString = ComponentName.unflattenFromString(str);
        this.mComponent = unflattenFromString;
        this.mTile = new Tile();
        this.mUserContext = context;
        int userId = context.getUserId();
        this.mUser = userId;
        this.mKey = new TileServiceKey(userId, unflattenFromString);
        tileServices.getClass();
        ComponentName component = getComponent();
        int user = getUser();
        Handler handler2 = (Handler) tileServices.mHandlerProvider.get();
        UserTracker userTracker = tileServices.mUserTracker;
        UserTrackerImpl userTrackerImpl = (UserTrackerImpl) userTracker;
        TileServiceManager tileServiceManager = new TileServiceManager(tileServices, handler2, userTrackerImpl, tileServices.mCustomTileAddedRepository, tileServices.mTileLifecycleManagerFactory.create(new Intent("android.service.quicksettings.action.QS_TILE").setComponent(component), userTrackerImpl.getUserHandle()));
        synchronized (tileServices.mServices) {
            tileServices.mServices.put(this, tileServiceManager);
            tileServices.mTiles.add(user, component, this);
            tileServices.mTokenMap.put(tileServiceManager.mStateManager.mToken, this);
        }
        tileServiceManager.mStarted = true;
        TileLifecycleManager tileLifecycleManager = tileServiceManager.mStateManager;
        ComponentName component2 = tileLifecycleManager.mIntent.getComponent();
        int identifier = tileLifecycleManager.mUser.getIdentifier();
        CustomTileAddedSharedPrefsRepository customTileAddedSharedPrefsRepository = (CustomTileAddedSharedPrefsRepository) tileServiceManager.mCustomTileAddedRepository;
        if (!((UserFileManagerImpl) customTileAddedSharedPrefsRepository.userFileManager).getSharedPreferences$1(identifier, "tiles_prefs").getBoolean(component2.flattenToString(), false)) {
            ((UserFileManagerImpl) customTileAddedSharedPrefsRepository.userFileManager).getSharedPreferences$1(identifier, "tiles_prefs").edit().putBoolean(component2.flattenToString(), true).apply();
            tileLifecycleManager.onTileAdded();
            ((ExecutorImpl) tileLifecycleManager.mExecutor).execute(new TileLifecycleManager$$ExternalSyntheticLambda0(tileLifecycleManager, 3));
        }
        this.mServiceManager = tileServiceManager;
        this.mService = tileServiceManager.mStateManager;
        this.mCustomTileStatePersister = customTileStatePersisterImpl;
        this.mDisplayTracker = displayTracker;
        this.mIUriGrantsManager = iUriGrantsManager;
    }

    public static String toSpec(ComponentName componentName) {
        return "custom(" + componentName.flattenToShortString() + ")";
    }

    public final void applyTileState(Tile tile, boolean z) {
        if (tile.getIcon() != null || z) {
            this.mTile.setIcon(tile.getIcon());
        }
        if (tile.getCustomLabel() != null || z) {
            this.mTile.setLabel(tile.getCustomLabel());
        }
        if (tile.getSubtitle() != null || z) {
            this.mTile.setSubtitle(tile.getSubtitle());
        }
        if (tile.getContentDescription() != null || z) {
            this.mTile.setContentDescription(tile.getContentDescription());
        }
        if (tile.getStateDescription() != null || z) {
            this.mTile.setStateDescription(tile.getStateDescription());
        }
        this.mTile.setActivityLaunchForClick(tile.getActivityLaunchForClick());
        this.mTile.setState(tile.getState());
    }

    @Override // com.android.systemui.qs.external.CustomTileInterface
    public final ComponentName getComponent() {
        return this.mComponent;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final Intent getLongClickIntent() {
        Intent intent;
        Intent intent2 = new Intent("android.service.quicksettings.action.QS_TILE_PREFERENCES");
        intent2.setPackage(this.mComponent.getPackageName());
        ResolveInfo resolveActivityAsUser = this.mContext.getPackageManager().resolveActivityAsUser(intent2, 0, this.mUser);
        if (resolveActivityAsUser != null) {
            Intent intent3 = new Intent("android.service.quicksettings.action.QS_TILE_PREFERENCES");
            ActivityInfo activityInfo = resolveActivityAsUser.activityInfo;
            intent = intent3.setClassName(activityInfo.packageName, activityInfo.name);
        } else {
            intent = null;
        }
        if (intent == null) {
            return new Intent("android.settings.APPLICATION_DETAILS_SETTINGS").setData(Uri.fromParts("package", this.mComponent.getPackageName(), null));
        }
        intent.putExtra("android.intent.extra.COMPONENT_NAME", this.mComponent);
        intent.putExtra(WeatherData.STATE_KEY, this.mTile.getState());
        return intent;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final int getMetricsCategory() {
        return 268;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final String getMetricsSpec() {
        return this.mComponent.getPackageName();
    }

    @Override // com.android.systemui.qs.external.CustomTileInterface
    public final Tile getQsTile() {
        updateDefaultTileAndIcon();
        return this.mTile;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final long getStaleTimeout() {
        return (((QSHostAdapter) this.mHost).getSpecs().indexOf(this.mTileSpec) * 60000) + 3600000;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final CharSequence getTileLabel() {
        return this.mState.label;
    }

    @Override // com.android.systemui.qs.external.CustomTileInterface
    public final int getUser() {
        return this.mUser;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleClick(Expandable expandable) {
        TileServiceManager tileServiceManager = this.mServiceManager;
        if (this.mTile.getState() == 0) {
            return;
        }
        this.mExpandableClicked = expandable;
        try {
            IWindowManager iWindowManager = this.mWindowManager;
            IBinder iBinder = this.mToken;
            this.mDisplayTracker.getClass();
            iWindowManager.addWindowToken(iBinder, 2035, 0, (Bundle) null);
            this.mIsTokenGranted = true;
        } catch (RemoteException unused) {
        }
        try {
            boolean isActiveTile = tileServiceManager.mStateManager.isActiveTile();
            TileLifecycleManager tileLifecycleManager = this.mService;
            if (isActiveTile) {
                tileServiceManager.setBindRequested(true);
                tileLifecycleManager.onStartListening();
            }
            if (this.mTile.getActivityLaunchForClick() != null) {
                startActivityAndCollapse(this.mTile.getActivityLaunchForClick());
            } else {
                tileLifecycleManager.onClick(this.mToken);
            }
        } catch (RemoteException unused2) {
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleDestroy() {
        super.handleDestroy();
        if (this.mIsTokenGranted) {
            try {
                IWindowManager iWindowManager = this.mWindowManager;
                IBinder iBinder = this.mToken;
                this.mDisplayTracker.getClass();
                iWindowManager.removeWindowToken(iBinder, 0);
            } catch (RemoteException unused) {
            }
        }
        TileServices tileServices = this.mTileServices;
        TileServiceManager tileServiceManager = this.mServiceManager;
        synchronized (tileServices.mServices) {
            tileServiceManager.setBindAllowed(false);
            tileServiceManager.handleDestroy();
            tileServices.mServices.remove(this);
            tileServices.mTokenMap.remove(tileServiceManager.mStateManager.mToken);
            tileServices.mTiles.delete(getUser(), getComponent());
            tileServices.mMainHandler.post(new TileServices$$ExternalSyntheticLambda2(tileServices, getComponent().getClassName()));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void handleInitialize() {
        /*
            r6 = this;
            r6.updateDefaultTileAndIcon()
            java.util.concurrent.atomic.AtomicBoolean r0 = r6.mInitialDefaultIconFetched
            r1 = 0
            r2 = 1
            boolean r0 = r0.compareAndSet(r1, r2)
            if (r0 == 0) goto L2e
            android.graphics.drawable.Icon r0 = r6.mDefaultIcon
            if (r0 != 0) goto L2e
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "No default icon for "
            r0.<init>(r2)
            java.lang.String r2 = r6.mTileSpec
            java.lang.String r3 = ", destroying tile"
            java.lang.String r0 = androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(r0, r2, r3)
            java.lang.String r2 = r6.TAG
            android.util.Log.w(r2, r0)
            java.lang.String r0 = r6.mTileSpec
            com.android.systemui.qs.QSHost r2 = r6.mHost
            com.android.systemui.qs.QSHostAdapter r2 = (com.android.systemui.qs.QSHostAdapter) r2
            r2.removeTile(r0)
        L2e:
            com.android.systemui.qs.external.TileServiceManager r0 = r6.mServiceManager
            boolean r2 = r0.isToggleableTile()
            if (r2 == 0) goto L4a
            com.android.systemui.plugins.qs.QSTile$State r2 = r6.newTileState()
            r6.mState = r2
            com.android.systemui.plugins.qs.QSTile$State r2 = r6.newTileState()
            r6.mTmpState = r2
            com.android.systemui.plugins.qs.QSTile$State r3 = r6.mState
            java.lang.String r4 = r6.mTileSpec
            r3.spec = r4
            r2.spec = r4
        L4a:
            com.android.systemui.qs.external.TileLifecycleManager r2 = r0.mStateManager
            r2.mChangeListener = r6
            boolean r2 = r2.isActiveTile()
            if (r2 == 0) goto L81
            com.android.systemui.qs.external.CustomTileStatePersisterImpl r2 = r6.mCustomTileStatePersister
            android.content.SharedPreferences r2 = r2.sharedPreferences
            com.android.systemui.qs.external.TileServiceKey r3 = r6.mKey
            java.lang.String r3 = r3.string
            r4 = 0
            java.lang.String r2 = r2.getString(r3, r4)
            if (r2 != 0) goto L65
        L63:
            r2 = r4
            goto L77
        L65:
            android.service.quicksettings.Tile r2 = com.android.systemui.qs.external.CustomTileStatePersisterKt.readTileFromString(r2)     // Catch: org.json.JSONException -> L6a
            goto L77
        L6a:
            r3 = move-exception
            java.lang.String r5 = "Bad saved state: "
            java.lang.String r2 = r5.concat(r2)
            java.lang.String r5 = "TileServicePersistence"
            android.util.Log.e(r5, r2, r3)
            goto L63
        L77:
            if (r2 == 0) goto L81
            r6.applyTileState(r2, r1)
            r0.mPendingBind = r1
            r6.refreshState(r4)
        L81:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.external.CustomTile.handleInitialize():void");
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleSetListening(boolean z) {
        super.handleSetListening(z);
        if (this.mListening == z) {
            return;
        }
        this.mListening = z;
        TileLifecycleManager tileLifecycleManager = this.mService;
        TileServiceManager tileServiceManager = this.mServiceManager;
        try {
            if (z) {
                updateDefaultTileAndIcon();
                refreshState(null);
                if (tileServiceManager.mStateManager.isActiveTile() && isTileReady()) {
                    return;
                }
                tileServiceManager.setBindRequested(true);
                tileLifecycleManager.onStartListening();
                return;
            }
            this.mExpandableClicked = null;
            tileLifecycleManager.onStopListening();
            if (this.mIsTokenGranted && !this.mIsShowingDialog) {
                try {
                    IWindowManager iWindowManager = this.mWindowManager;
                    IBinder iBinder = this.mToken;
                    this.mDisplayTracker.getClass();
                    iWindowManager.removeWindowToken(iBinder, 0);
                } catch (RemoteException unused) {
                }
                this.mIsTokenGranted = false;
            }
            this.mIsShowingDialog = false;
            tileServiceManager.setBindRequested(false);
        } catch (RemoteException unused2) {
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleUpdateState(QSTile.State state, Object obj) {
        final Drawable drawable;
        int state2 = this.mTile.getState();
        if (this.mServiceManager.mPendingBind) {
            state2 = 0;
        }
        state.state = state2;
        try {
            drawable = this.mTile.getIcon().loadDrawableCheckingUriGrant(this.mUserContext, this.mIUriGrantsManager, this.mServiceUid, this.mComponent.getPackageName());
        } catch (Exception unused) {
            Log.w(this.TAG, "Invalid icon, forcing into unavailable state");
            state.state = 0;
            drawable = null;
        }
        if (drawable == null) {
            Icon icon = this.mDefaultIcon;
            drawable = icon != null ? icon.loadDrawable(this.mUserContext) : null;
        }
        state.iconSupplier = new Supplier() { // from class: com.android.systemui.qs.external.CustomTile$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                Drawable.ConstantState constantState;
                Drawable drawable2 = drawable;
                if (drawable2 == null || (constantState = drawable2.getConstantState()) == null) {
                    return null;
                }
                return new QSTileImpl.DrawableIcon(constantState.newDrawable());
            }
        };
        state.label = this.mTile.getLabel();
        CharSequence subtitle = this.mTile.getSubtitle();
        if (subtitle == null || subtitle.length() <= 0) {
            state.secondaryLabel = null;
        } else {
            state.secondaryLabel = subtitle;
        }
        if (this.mTile.getContentDescription() != null) {
            state.contentDescription = this.mTile.getContentDescription();
        } else {
            state.contentDescription = state.label;
        }
        if (this.mTile.getStateDescription() != null) {
            state.stateDescription = this.mTile.getStateDescription();
        } else {
            state.stateDescription = null;
        }
        if (!(state instanceof QSTile.BooleanState)) {
            state.expandedAccessibilityClassName = Button.class.getName();
            return;
        }
        state.expandedAccessibilityClassName = Switch.class.getName();
        ((QSTile.BooleanState) state).value = state.state == 2;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final boolean isAvailable() {
        return (this.mInitialDefaultIconFetched.get() && this.mDefaultIcon == null) ? false : true;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final QSTile.State newTileState() {
        TileServiceManager tileServiceManager = this.mServiceManager;
        return (tileServiceManager == null || !tileServiceManager.isToggleableTile()) ? new QSTile.State() : new QSTile.BooleanState();
    }

    @Override // com.android.systemui.qs.external.CustomTileInterface
    public final void onDialogHidden() {
        this.mIsShowingDialog = false;
        try {
            IWindowManager iWindowManager = this.mWindowManager;
            IBinder iBinder = this.mToken;
            this.mDisplayTracker.getClass();
            iWindowManager.removeWindowToken(iBinder, 0);
        } catch (RemoteException unused) {
        }
    }

    @Override // com.android.systemui.qs.external.CustomTileInterface
    public final void onDialogShown() {
        this.mIsShowingDialog = true;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final LogMaker populate(LogMaker logMaker) {
        return super.populate(logMaker).setComponentName(this.mComponent);
    }

    @Override // com.android.systemui.qs.external.CustomTileInterface
    public final void startActivityAndCollapse(PendingIntent pendingIntent) {
        boolean isActivity = pendingIntent.isActivity();
        String str = this.TAG;
        if (!isActivity) {
            Log.i(str, "Intent not for activity.");
        } else {
            if (!this.mIsTokenGranted) {
                Log.i(str, "Launching activity before click");
                return;
            }
            Log.i(str, "The activity is starting");
            Expandable expandable = this.mExpandableClicked;
            this.mActivityStarter.startPendingIntentMaybeDismissingKeyguard(pendingIntent, null, expandable == null ? null : expandable.activityTransitionController(32));
        }
    }

    @Override // com.android.systemui.qs.external.CustomTileInterface
    public final void startUnlockAndRun() {
        this.mActivityStarter.postQSRunnableDismissingKeyguard(new CustomTile$$ExternalSyntheticLambda2(this, 0));
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0070 A[Catch: NameNotFoundException -> 0x008f, TryCatch #0 {NameNotFoundException -> 0x008f, blocks: (B:3:0x0001, B:6:0x001e, B:9:0x002d, B:11:0x0035, B:17:0x0045, B:19:0x004c, B:22:0x0053, B:25:0x005e, B:29:0x0070, B:30:0x007c, B:32:0x0080, B:33:0x0085, B:39:0x0029), top: B:2:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0080 A[Catch: NameNotFoundException -> 0x008f, TryCatch #0 {NameNotFoundException -> 0x008f, blocks: (B:3:0x0001, B:6:0x001e, B:9:0x002d, B:11:0x0035, B:17:0x0045, B:19:0x004c, B:22:0x0053, B:25:0x005e, B:29:0x0070, B:30:0x007c, B:32:0x0080, B:33:0x0085, B:39:0x0029), top: B:2:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateDefaultTileAndIcon() {
        /*
            r9 = this;
            r0 = 0
            android.content.Context r1 = r9.mUserContext     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
            android.content.pm.PackageManager r1 = r1.getPackageManager()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
            android.content.ComponentName r2 = r9.mComponent     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
            java.lang.String r2 = r2.getPackageName()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
            r3 = 0
            android.content.pm.ApplicationInfo r2 = r1.getApplicationInfo(r2, r3)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
            boolean r2 = r2.isSystemApp()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
            if (r2 == 0) goto L1c
            r2 = 786944(0xc0200, float:1.102743E-39)
            goto L1e
        L1c:
            r2 = 786432(0xc0000, float:1.102026E-39)
        L1e:
            android.content.ComponentName r4 = r9.mComponent     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
            android.content.pm.ServiceInfo r2 = r1.getServiceInfo(r4, r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
            int r4 = r2.icon     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
            if (r4 == 0) goto L29
            goto L2d
        L29:
            android.content.pm.ApplicationInfo r4 = r2.applicationInfo     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
            int r4 = r4.icon     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
        L2d:
            android.service.quicksettings.Tile r5 = r9.mTile     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
            android.graphics.drawable.Icon r5 = r5.getIcon()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
            if (r5 == 0) goto L6d
            android.service.quicksettings.Tile r5 = r9.mTile     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
            android.graphics.drawable.Icon r5 = r5.getIcon()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
            android.graphics.drawable.Icon r6 = r9.mDefaultIcon     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
            if (r5 != r6) goto L40
            goto L6d
        L40:
            if (r5 == 0) goto L6e
            if (r6 != 0) goto L45
            goto L6e
        L45:
            int r7 = r5.getType()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
            r8 = 2
            if (r7 != r8) goto L6e
            int r7 = r6.getType()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
            if (r7 == r8) goto L53
            goto L6e
        L53:
            int r7 = r5.getResId()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
            int r8 = r6.getResId()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
            if (r7 == r8) goto L5e
            goto L6e
        L5e:
            java.lang.String r5 = r5.getResPackage()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
            java.lang.String r6 = r6.getResPackage()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
            boolean r5 = java.util.Objects.equals(r5, r6)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
            if (r5 != 0) goto L6d
            goto L6e
        L6d:
            r3 = 1
        L6e:
            if (r4 == 0) goto L7b
            android.content.ComponentName r5 = r9.mComponent     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
            java.lang.String r5 = r5.getPackageName()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
            android.graphics.drawable.Icon r4 = android.graphics.drawable.Icon.createWithResource(r5, r4)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
            goto L7c
        L7b:
            r4 = r0
        L7c:
            r9.mDefaultIcon = r4     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
            if (r3 == 0) goto L85
            android.service.quicksettings.Tile r3 = r9.mTile     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
            r3.setIcon(r4)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
        L85:
            java.lang.CharSequence r1 = r2.loadLabel(r1)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
            android.service.quicksettings.Tile r2 = r9.mTile     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
            r2.setDefaultLabel(r1)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L8f
            goto L91
        L8f:
            r9.mDefaultIcon = r0
        L91:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.external.CustomTile.updateDefaultTileAndIcon():void");
    }

    @Override // com.android.systemui.qs.external.CustomTileInterface
    public final void updateTileState(final Tile tile, int i) {
        this.mServiceUid = i;
        this.mHandler.post(new Runnable() { // from class: com.android.systemui.qs.external.CustomTile$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                CustomTile customTile = CustomTile.this;
                Tile tile2 = tile;
                customTile.applyTileState(tile2, true);
                if (customTile.mServiceManager.mStateManager.isActiveTile()) {
                    CustomTileStatePersisterImpl customTileStatePersisterImpl = customTile.mCustomTileStatePersister;
                    customTileStatePersisterImpl.getClass();
                    customTileStatePersisterImpl.sharedPreferences.edit().putString(customTile.mKey.string, CustomTileStatePersisterKt.writeToString(tile2)).apply();
                }
            }
        });
    }
}
