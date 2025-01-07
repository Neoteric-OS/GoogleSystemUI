package com.android.systemui.navigationbar;

import android.R;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.om.IOverlayManager;
import android.content.pm.PackageManager;
import android.content.res.ApkAssets;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.Trace;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import androidx.collection.MutableObjectList$$ExternalSyntheticOutline0;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.deviceentry.data.repository.FaceWakeUpTriggersConfigImpl$$ExternalSyntheticOutline0;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NavigationModeController implements Dumpable {
    public final Context mContext;
    public Context mCurrentUserContext;
    public final ArrayList mListeners = new ArrayList();
    public final IOverlayManager mOverlayManager;
    public final AnonymousClass2 mReceiver;
    public final Executor mUiBgExecutor;
    public final UserTracker mUserTracker;
    public final UserTracker.Callback mUserTrackerCallback;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface ModeChangedListener {
        void onNavigationModeChanged(int i);
    }

    public NavigationModeController(Context context, ConfigurationController configurationController, UserTracker userTracker, Executor executor, Executor executor2, DumpManager dumpManager) {
        UserTracker.Callback callback = new UserTracker.Callback() { // from class: com.android.systemui.navigationbar.NavigationModeController.1
            @Override // com.android.systemui.settings.UserTracker.Callback
            public final void onUserChanged(int i, Context context2) {
                ExifInterface$$ExternalSyntheticOutline0.m("onUserChanged: ", "NavigationModeController", i);
                NavigationModeController.this.updateCurrentInteractionMode(true);
            }
        };
        this.mUserTrackerCallback = callback;
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.android.systemui.navigationbar.NavigationModeController.2
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                Log.d("NavigationModeController", "ACTION_OVERLAY_CHANGED");
                NavigationModeController.this.updateCurrentInteractionMode(true);
            }
        };
        this.mContext = context;
        this.mCurrentUserContext = context;
        this.mUserTracker = userTracker;
        ((UserTrackerImpl) userTracker).addCallback(callback, executor);
        this.mOverlayManager = IOverlayManager.Stub.asInterface(ServiceManager.getService("overlay"));
        this.mUiBgExecutor = executor2;
        dumpManager.getClass();
        DumpManager.registerDumpable$default(dumpManager, "NavigationModeController", this);
        IntentFilter intentFilter = new IntentFilter("android.intent.action.OVERLAY_CHANGED");
        intentFilter.addDataScheme("package");
        intentFilter.addDataSchemeSpecificPart("android", 0);
        context.registerReceiverAsUser(broadcastReceiver, UserHandle.ALL, intentFilter, null, null);
        ((ConfigurationControllerImpl) configurationController).addCallback(new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.navigationbar.NavigationModeController.3
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onThemeChanged() {
                Log.d("NavigationModeController", "onOverlayChanged");
                NavigationModeController.this.updateCurrentInteractionMode(true);
            }
        });
        updateCurrentInteractionMode(false);
    }

    public static int getCurrentInteractionMode(Context context) {
        int integer = context.getResources().getInteger(R.integer.config_navBarInteractionMode);
        StringBuilder m = MutableObjectList$$ExternalSyntheticOutline0.m("getCurrentInteractionMode: mode=", " contextUser=", integer);
        m.append(context.getUserId());
        Log.d("NavigationModeController", m.toString());
        return integer;
    }

    public final int addListener(ModeChangedListener modeChangedListener) {
        this.mListeners.add(modeChangedListener);
        return getCurrentInteractionMode(this.mCurrentUserContext);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        String str;
        StringBuilder m = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, "NavigationModeController:", "  mode=");
        m.append(getCurrentInteractionMode(this.mCurrentUserContext));
        printWriter.println(m.toString());
        try {
            str = String.join(", ", this.mOverlayManager.getDefaultOverlayPackages());
        } catch (RemoteException unused) {
            str = "failed_to_fetch";
        }
        FaceWakeUpTriggersConfigImpl$$ExternalSyntheticOutline0.m(printWriter, "  defaultOverlays=", str);
        dumpAssetPaths(this.mCurrentUserContext);
    }

    public final void dumpAssetPaths(Context context) {
        Log.d("NavigationModeController", "  contextUser=" + this.mCurrentUserContext.getUserId());
        Log.d("NavigationModeController", "  assetPaths=");
        for (ApkAssets apkAssets : context.getResources().getAssets().getApkAssets()) {
            Log.d("NavigationModeController", "    " + apkAssets.getDebugName());
        }
    }

    public final Context getCurrentUserContext() {
        int userId = ((UserTrackerImpl) this.mUserTracker).getUserId();
        Log.d("NavigationModeController", "getCurrentUserContext: contextUser=" + this.mContext.getUserId() + " currentUser=" + userId);
        if (this.mContext.getUserId() == userId) {
            return this.mContext;
        }
        try {
            Context context = this.mContext;
            return context.createPackageContextAsUser(context.getPackageName(), 0, UserHandle.of(userId));
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("NavigationModeController", "Failed to create package context", e);
            return null;
        }
    }

    public final void removeListener(ModeChangedListener modeChangedListener) {
        this.mListeners.remove(modeChangedListener);
    }

    public final void updateCurrentInteractionMode(boolean z) {
        Trace.beginSection("NMC#updateCurrentInteractionMode");
        Context currentUserContext = getCurrentUserContext();
        this.mCurrentUserContext = currentUserContext;
        final int currentInteractionMode = getCurrentInteractionMode(currentUserContext);
        this.mUiBgExecutor.execute(new Runnable() { // from class: com.android.systemui.navigationbar.NavigationModeController$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                Settings.Secure.putString(NavigationModeController.this.mCurrentUserContext.getContentResolver(), "navigation_mode", String.valueOf(currentInteractionMode));
            }
        });
        Log.d("NavigationModeController", "updateCurrentInteractionMode: mode=" + currentInteractionMode);
        dumpAssetPaths(this.mCurrentUserContext);
        if (z) {
            for (int i = 0; i < this.mListeners.size(); i++) {
                ((ModeChangedListener) this.mListeners.get(i)).onNavigationModeChanged(currentInteractionMode);
            }
        }
        Trace.endSection();
    }
}
