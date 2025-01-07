package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.net.TetheringManager;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.UserManager;
import android.util.Log;
import com.android.app.viewcapture.data.ViewNode;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.policy.HotspotController;
import com.android.wm.shell.R;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HotspotControllerImpl implements HotspotController, WifiManager.SoftApCallback {
    public static final boolean DEBUG = Log.isLoggable("HotspotController", 3);
    public final Context mContext;
    public int mHotspotState;
    public final boolean mIsTetheringSupportedConfig;
    public final Handler mMainHandler;
    public volatile int mNumConnectedDevices;
    public final AnonymousClass1 mTetheringCallback;
    public final TetheringManager mTetheringManager;
    public final UserTracker mUserTracker;
    public boolean mWaitingForTerminalState;
    public final WifiManager mWifiManager;
    public final ArrayList mCallbacks = new ArrayList();
    public volatile boolean mIsTetheringSupported = true;
    public volatile boolean mHasTetherableWifiRegexs = true;

    /* renamed from: -$$Nest$mfireHotspotAvailabilityChanged, reason: not valid java name */
    public static void m888$$Nest$mfireHotspotAvailabilityChanged(HotspotControllerImpl hotspotControllerImpl) {
        ArrayList arrayList;
        synchronized (hotspotControllerImpl.mCallbacks) {
            arrayList = new ArrayList(hotspotControllerImpl.mCallbacks);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((HotspotController.Callback) it.next()).onHotspotAvailabilityChanged(hotspotControllerImpl.isHotspotSupported());
        }
    }

    public HotspotControllerImpl(Context context, UserTracker userTracker, Handler handler, Handler handler2, DumpManager dumpManager) {
        TetheringManager.TetheringEventCallback tetheringEventCallback = new TetheringManager.TetheringEventCallback() { // from class: com.android.systemui.statusbar.policy.HotspotControllerImpl.1
            public final void onTetherableInterfaceRegexpsChanged(TetheringManager.TetheringInterfaceRegexps tetheringInterfaceRegexps) {
                boolean z = tetheringInterfaceRegexps.getTetherableWifiRegexs().size() != 0;
                if (HotspotControllerImpl.this.mHasTetherableWifiRegexs != z) {
                    HotspotControllerImpl.this.mHasTetherableWifiRegexs = z;
                    HotspotControllerImpl.m888$$Nest$mfireHotspotAvailabilityChanged(HotspotControllerImpl.this);
                }
            }

            public final void onTetheringSupported(boolean z) {
                if (HotspotControllerImpl.this.mIsTetheringSupported != z) {
                    HotspotControllerImpl.this.mIsTetheringSupported = z;
                    HotspotControllerImpl.m888$$Nest$mfireHotspotAvailabilityChanged(HotspotControllerImpl.this);
                }
            }
        };
        this.mContext = context;
        this.mUserTracker = userTracker;
        TetheringManager tetheringManager = (TetheringManager) context.getSystemService(TetheringManager.class);
        this.mTetheringManager = tetheringManager;
        this.mWifiManager = (WifiManager) context.getSystemService("wifi");
        this.mMainHandler = handler;
        boolean z = context.getResources().getBoolean(R.bool.config_show_wifi_tethering);
        this.mIsTetheringSupportedConfig = z;
        if (z) {
            tetheringManager.registerTetheringEventCallback(new HandlerExecutor(handler2), tetheringEventCallback);
        }
        dumpManager.getClass();
        DumpManager.registerDumpable$default(dumpManager, "HotspotControllerImpl", this);
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Object obj) {
        final HotspotController.Callback callback = (HotspotController.Callback) obj;
        synchronized (this.mCallbacks) {
            if (callback != null) {
                try {
                    if (!this.mCallbacks.contains(callback)) {
                        if (DEBUG) {
                            Log.d("HotspotController", "addCallback " + callback);
                        }
                        this.mCallbacks.add(callback);
                        if (this.mWifiManager != null) {
                            if (this.mCallbacks.size() == 1) {
                                this.mWifiManager.registerSoftApCallback(new HandlerExecutor(this.mMainHandler), this);
                            } else {
                                this.mMainHandler.post(new Runnable() { // from class: com.android.systemui.statusbar.policy.HotspotControllerImpl$$ExternalSyntheticLambda0
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        HotspotControllerImpl hotspotControllerImpl = HotspotControllerImpl.this;
                                        callback.onHotspotChanged(hotspotControllerImpl.mNumConnectedDevices, hotspotControllerImpl.isHotspotEnabled());
                                    }
                                });
                            }
                        }
                    }
                } finally {
                }
            }
        }
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        String str;
        printWriter.println("HotspotController state:");
        printWriter.print("  available=");
        printWriter.println(isHotspotSupported());
        printWriter.print("  mHotspotState=");
        switch (this.mHotspotState) {
            case 10:
                str = "DISABLING";
                break;
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                str = "DISABLED";
                break;
            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                str = "ENABLING";
                break;
            case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                str = "ENABLED";
                break;
            case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                str = "FAILED";
                break;
            default:
                str = null;
                break;
        }
        printWriter.println(str);
        printWriter.print("  mNumConnectedDevices=");
        printWriter.println(this.mNumConnectedDevices);
        printWriter.print("  mWaitingForTerminalState=");
        printWriter.println(this.mWaitingForTerminalState);
    }

    public final void fireHotspotChangedCallback() {
        ArrayList arrayList;
        synchronized (this.mCallbacks) {
            arrayList = new ArrayList(this.mCallbacks);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((HotspotController.Callback) it.next()).onHotspotChanged(this.mNumConnectedDevices, isHotspotEnabled());
        }
    }

    public final boolean isHotspotEnabled() {
        return this.mHotspotState == 13;
    }

    public final boolean isHotspotSupported() {
        return this.mIsTetheringSupportedConfig && this.mIsTetheringSupported && this.mHasTetherableWifiRegexs && UserManager.get(this.mContext).isUserAdmin(((UserTrackerImpl) this.mUserTracker).getUserId());
    }

    public final void maybeResetSoftApState() {
        if (this.mWaitingForTerminalState) {
            int i = this.mHotspotState;
            if (i != 11 && i != 13) {
                if (i != 14) {
                    return;
                } else {
                    this.mTetheringManager.stopTethering(0);
                }
            }
            this.mWaitingForTerminalState = false;
        }
    }

    public final void onConnectedClientsChanged(List list) {
        this.mNumConnectedDevices = list.size();
        fireHotspotChangedCallback();
    }

    public final void onStateChanged(int i, int i2) {
        this.mHotspotState = i;
        maybeResetSoftApState();
        if (!isHotspotEnabled()) {
            this.mNumConnectedDevices = 0;
        }
        fireHotspotChangedCallback();
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        WifiManager wifiManager;
        HotspotController.Callback callback = (HotspotController.Callback) obj;
        if (callback == null) {
            return;
        }
        if (DEBUG) {
            Log.d("HotspotController", "removeCallback " + callback);
        }
        synchronized (this.mCallbacks) {
            try {
                this.mCallbacks.remove(callback);
                if (this.mCallbacks.isEmpty() && (wifiManager = this.mWifiManager) != null) {
                    wifiManager.unregisterSoftApCallback(this);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
