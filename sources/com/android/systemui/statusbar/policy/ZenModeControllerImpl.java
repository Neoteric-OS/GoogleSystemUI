package com.android.systemui.statusbar.policy;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.Trace;
import android.os.UserHandle;
import android.provider.Settings;
import android.service.notification.ZenModeConfig;
import android.text.format.DateFormat;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.keyguard.LegacyLockIconViewController$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.policy.ZenModeController;
import com.android.systemui.statusbar.policy.ZenModeControllerImpl;
import com.android.systemui.util.settings.GlobalSettings;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ZenModeControllerImpl implements ZenModeController, Dumpable {
    public final AlarmManager mAlarmManager;
    public final BroadcastDispatcher mBroadcastDispatcher;
    public final ArrayList mCallbacks = new ArrayList();
    public final Object mCallbacksLock = new Object();
    public ZenModeConfig mConfig;
    public NotificationManager.Policy mConsolidatedNotificationPolicy;
    public final Context mContext;
    public final GlobalSettings mGlobalSettings;
    public final NotificationManager mNoMan;
    public final AnonymousClass4 mReceiver;
    public boolean mRegistered;
    public final SetupObserver mSetupObserver;
    public final UserTracker.Callback mUserChangedCallback;
    public int mUserId;
    public final UserTracker mUserTracker;
    public volatile int mZenMode;
    public long mZenUpdateTime;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SetupObserver extends ContentObserver {
        public boolean mRegistered;
        public final ContentResolver mResolver;

        public SetupObserver(Handler handler) {
            super(handler);
            this.mResolver = ZenModeControllerImpl.this.mContext.getContentResolver();
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z, Uri uri) {
            if (Settings.Global.getUriFor("device_provisioned").equals(uri) || Settings.Secure.getUriFor("user_setup_complete").equals(uri)) {
                ZenModeControllerImpl zenModeControllerImpl = ZenModeControllerImpl.this;
                zenModeControllerImpl.fireSafeChange(new ZenModeControllerImpl$$ExternalSyntheticLambda4(zenModeControllerImpl.isZenAvailable()));
            }
        }

        public final void register() {
            if (this.mRegistered) {
                this.mResolver.unregisterContentObserver(this);
            }
            this.mResolver.registerContentObserver(Settings.Global.getUriFor("device_provisioned"), false, this);
            this.mResolver.registerContentObserver(Settings.Secure.getUriFor("user_setup_complete"), false, this, ZenModeControllerImpl.this.mUserId);
            this.mRegistered = true;
            ZenModeControllerImpl zenModeControllerImpl = ZenModeControllerImpl.this;
            zenModeControllerImpl.fireSafeChange(new ZenModeControllerImpl$$ExternalSyntheticLambda4(zenModeControllerImpl.isZenAvailable()));
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.android.systemui.statusbar.policy.ZenModeControllerImpl$4] */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.statusbar.policy.ZenModeControllerImpl$2] */
    /* JADX WARN: Type inference failed for: r7v1, types: [com.android.systemui.statusbar.policy.ZenModeControllerImpl$2] */
    public ZenModeControllerImpl(Context context, Handler handler, Handler handler2, BroadcastDispatcher broadcastDispatcher, DumpManager dumpManager, final GlobalSettings globalSettings, UserTracker userTracker) {
        UserTracker.Callback callback = new UserTracker.Callback() { // from class: com.android.systemui.statusbar.policy.ZenModeControllerImpl.1
            @Override // com.android.systemui.settings.UserTracker.Callback
            public final void onUserChanged(int i, Context context2) {
                ZenModeControllerImpl zenModeControllerImpl = ZenModeControllerImpl.this;
                zenModeControllerImpl.mUserId = i;
                boolean z = zenModeControllerImpl.mRegistered;
                AnonymousClass4 anonymousClass4 = zenModeControllerImpl.mReceiver;
                BroadcastDispatcher broadcastDispatcher2 = zenModeControllerImpl.mBroadcastDispatcher;
                if (z) {
                    broadcastDispatcher2.unregisterReceiver(anonymousClass4);
                }
                IntentFilter intentFilter = new IntentFilter("android.app.action.NEXT_ALARM_CLOCK_CHANGED");
                intentFilter.addAction("android.os.action.ACTION_EFFECTS_SUPPRESSOR_CHANGED");
                broadcastDispatcher2.registerReceiver(anonymousClass4, intentFilter, null, UserHandle.of(zenModeControllerImpl.mUserId));
                zenModeControllerImpl.mRegistered = true;
                zenModeControllerImpl.mSetupObserver.register();
            }
        };
        this.mUserChangedCallback = callback;
        this.mReceiver = new BroadcastReceiver() { // from class: com.android.systemui.statusbar.policy.ZenModeControllerImpl.4
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                if ("android.app.action.NEXT_ALARM_CLOCK_CHANGED".equals(intent.getAction())) {
                    ZenModeControllerImpl zenModeControllerImpl = ZenModeControllerImpl.this;
                    zenModeControllerImpl.getClass();
                    zenModeControllerImpl.fireSafeChange(new ZenModeControllerImpl$$ExternalSyntheticLambda0(1));
                }
                if ("android.os.action.ACTION_EFFECTS_SUPPRESSOR_CHANGED".equals(intent.getAction())) {
                    ZenModeControllerImpl zenModeControllerImpl2 = ZenModeControllerImpl.this;
                    zenModeControllerImpl2.getClass();
                    zenModeControllerImpl2.fireSafeChange(new ZenModeControllerImpl$$ExternalSyntheticLambda0(2));
                }
            }
        };
        this.mContext = context;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mGlobalSettings = globalSettings;
        final int i = 0;
        final ?? r7 = new ContentObserver(this, handler) { // from class: com.android.systemui.statusbar.policy.ZenModeControllerImpl.2
            public final /* synthetic */ ZenModeControllerImpl this$0;

            {
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                switch (i) {
                    case 0:
                        final int i2 = this.this$0.mGlobalSettings.getInt(0, "zen_mode");
                        ExifInterface$$ExternalSyntheticOutline0.m("Zen mode setting changed to ", "ZenModeController", i2);
                        this.this$0.updateZenMode(i2);
                        ZenModeControllerImpl zenModeControllerImpl = this.this$0;
                        zenModeControllerImpl.getClass();
                        zenModeControllerImpl.fireSafeChange(new Consumer() { // from class: com.android.systemui.statusbar.policy.ZenModeControllerImpl$$ExternalSyntheticLambda6
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                ((ZenModeController.Callback) obj).onZenChanged(i2);
                            }
                        });
                        return;
                    default:
                        try {
                            Trace.beginSection("updateZenModeConfig");
                            this.this$0.updateZenModeConfig();
                            return;
                        } finally {
                            Trace.endSection();
                        }
                }
            }
        };
        final int i2 = 1;
        final ?? r1 = new ContentObserver(this, handler) { // from class: com.android.systemui.statusbar.policy.ZenModeControllerImpl.2
            public final /* synthetic */ ZenModeControllerImpl this$0;

            {
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                switch (i2) {
                    case 0:
                        final int i22 = this.this$0.mGlobalSettings.getInt(0, "zen_mode");
                        ExifInterface$$ExternalSyntheticOutline0.m("Zen mode setting changed to ", "ZenModeController", i22);
                        this.this$0.updateZenMode(i22);
                        ZenModeControllerImpl zenModeControllerImpl = this.this$0;
                        zenModeControllerImpl.getClass();
                        zenModeControllerImpl.fireSafeChange(new Consumer() { // from class: com.android.systemui.statusbar.policy.ZenModeControllerImpl$$ExternalSyntheticLambda6
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                ((ZenModeController.Callback) obj).onZenChanged(i22);
                            }
                        });
                        return;
                    default:
                        try {
                            Trace.beginSection("updateZenModeConfig");
                            this.this$0.updateZenModeConfig();
                            return;
                        } finally {
                            Trace.endSection();
                        }
                }
            }
        };
        this.mNoMan = (NotificationManager) context.getSystemService("notification");
        handler2.post(new Runnable() { // from class: com.android.systemui.statusbar.policy.ZenModeControllerImpl$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                GlobalSettings globalSettings2 = GlobalSettings.this;
                ZenModeControllerImpl.AnonymousClass2 anonymousClass2 = r7;
                ZenModeControllerImpl.AnonymousClass2 anonymousClass22 = r1;
                globalSettings2.registerContentObserverSync("zen_mode", anonymousClass2);
                globalSettings2.registerContentObserverSync("zen_mode_config_etag", anonymousClass22);
            }
        });
        updateZenMode(globalSettings.getInt(0, "zen_mode"));
        updateZenModeConfig();
        updateConsolidatedNotificationPolicy();
        this.mAlarmManager = (AlarmManager) context.getSystemService("alarm");
        SetupObserver setupObserver = new SetupObserver(handler);
        this.mSetupObserver = setupObserver;
        setupObserver.register();
        UserTrackerImpl userTrackerImpl = (UserTrackerImpl) userTracker;
        userTrackerImpl.addCallback(callback, new HandlerExecutor(handler));
        callback.onUserChanged(userTrackerImpl.getUserId(), context);
        dumpManager.getClass();
        DumpManager.registerDumpable$default(dumpManager, "ZenModeControllerImpl", this);
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Object obj) {
        ZenModeController.Callback callback = (ZenModeController.Callback) obj;
        synchronized (this.mCallbacksLock) {
            Log.d("ZenModeController", "Added callback " + callback.getClass());
            this.mCallbacks.add(callback);
        }
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        StringBuilder m = LegacyLockIconViewController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, "ZenModeControllerImpl:", "  mZenMode="), this.mZenMode, printWriter, "  mConfig=");
        m.append(this.mConfig);
        printWriter.println(m.toString());
        printWriter.println("  mConsolidatedNotificationPolicy=" + this.mConsolidatedNotificationPolicy);
        printWriter.println("  mZenUpdateTime=" + ((Object) DateFormat.format("MM-dd HH:mm:ss", this.mZenUpdateTime)));
    }

    public void fireConfigChanged(ZenModeConfig zenModeConfig) {
        fireSafeChange(new ZenModeControllerImpl$$ExternalSyntheticLambda1(zenModeConfig));
    }

    public final void fireSafeChange(Consumer consumer) {
        ArrayList arrayList;
        synchronized (this.mCallbacksLock) {
            arrayList = new ArrayList(this.mCallbacks);
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            consumer.accept((ZenModeController.Callback) arrayList.get(i));
        }
    }

    public final boolean isZenAvailable() {
        SetupObserver setupObserver = this.mSetupObserver;
        return (Settings.Global.getInt(setupObserver.mResolver, "device_provisioned", 0) == 0 || Settings.Secure.getIntForUser(setupObserver.mResolver, "user_setup_complete", 0, ZenModeControllerImpl.this.mUserId) == 0) ? false : true;
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        ZenModeController.Callback callback = (ZenModeController.Callback) obj;
        synchronized (this.mCallbacksLock) {
            Log.d("ZenModeController", "Removed callback " + callback.getClass());
            this.mCallbacks.remove(callback);
        }
    }

    public final void setZen(int i, Uri uri, String str) {
        this.mNoMan.setZenMode(i, uri, str, true);
    }

    public void updateConsolidatedNotificationPolicy() {
        NotificationManager.Policy consolidatedNotificationPolicy = this.mNoMan.getConsolidatedNotificationPolicy();
        if (Objects.equals(consolidatedNotificationPolicy, this.mConsolidatedNotificationPolicy)) {
            return;
        }
        this.mConsolidatedNotificationPolicy = consolidatedNotificationPolicy;
        fireSafeChange(new ZenModeControllerImpl$$ExternalSyntheticLambda1(consolidatedNotificationPolicy));
    }

    public void updateZenMode(int i) {
        this.mZenMode = i;
        this.mZenUpdateTime = System.currentTimeMillis();
    }

    public void updateZenModeConfig() {
        ZenModeConfig zenModeConfig = this.mNoMan.getZenModeConfig();
        if (Objects.equals(zenModeConfig, this.mConfig)) {
            return;
        }
        ZenModeConfig zenModeConfig2 = this.mConfig;
        ZenModeConfig.ZenRule zenRule = zenModeConfig2 != null ? zenModeConfig2.manualRule : null;
        this.mConfig = zenModeConfig;
        this.mZenUpdateTime = System.currentTimeMillis();
        fireConfigChanged(zenModeConfig);
        if (!Objects.equals(zenRule, zenModeConfig != null ? zenModeConfig.manualRule : null)) {
            fireSafeChange(new ZenModeControllerImpl$$ExternalSyntheticLambda0(0));
        }
        NotificationManager.Policy consolidatedNotificationPolicy = this.mNoMan.getConsolidatedNotificationPolicy();
        if (Objects.equals(consolidatedNotificationPolicy, this.mConsolidatedNotificationPolicy)) {
            return;
        }
        this.mConsolidatedNotificationPolicy = consolidatedNotificationPolicy;
        fireSafeChange(new ZenModeControllerImpl$$ExternalSyntheticLambda1(consolidatedNotificationPolicy));
    }
}
