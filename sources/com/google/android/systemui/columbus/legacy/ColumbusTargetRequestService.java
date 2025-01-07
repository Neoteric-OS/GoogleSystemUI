package com.google.android.systemui.columbus.legacy;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.SystemClock;
import android.os.Trace;
import android.provider.Settings;
import android.util.Log;
import androidx.compose.foundation.text.input.internal.RecordingInputConnection$$ExternalSyntheticOutline0;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.ListImplementation$$ExternalSyntheticOutline0;
import androidx.fragment.app.FragmentManagerViewModel$$ExternalSyntheticOutline0;
import com.android.app.tracing.TraceUtilsKt;
import com.android.internal.logging.UiEventLogger;
import com.android.keyguard.ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.wm.shell.R;
import com.google.android.systemui.columbus.ColumbusContext;
import com.google.android.systemui.columbus.ColumbusEvent;
import com.google.android.systemui.columbus.legacy.ColumbusTargetRequestService;
import java.security.MessageDigest;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.Result;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.json.JSONObject;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ColumbusTargetRequestService extends Service {
    public static final long PACKAGE_DENY_COOLDOWN_MS = TimeUnit.DAYS.toMillis(5);
    public final Set allowCertList;
    public final Set allowPackageList;
    public final ColumbusContext columbusContext;
    public final ColumbusSettings columbusSettings;
    public final ColumbusStructuredDataManager columbusStructuredDataManager;
    public LauncherApps launcherApps;
    public final Handler mainHandler;
    public final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
    public final Messenger messenger;
    public final Context sysUIContext;
    public final UiEventLogger uiEventLogger;
    public final UserTracker userTracker;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class IncomingMessageHandler extends Handler {
        public IncomingMessageHandler(Looper looper) {
            super(looper);
        }

        public static void replyToMessenger(Messenger messenger, int i, int i2) {
            Object failure;
            if (messenger == null) {
                return;
            }
            Message what = Message.obtain().setWhat(i2);
            what.arg1 = i;
            try {
                messenger.send(what);
                failure = Unit.INSTANCE;
            } catch (Throwable th) {
                failure = new Result.Failure(th);
            }
            Throwable m1771exceptionOrNullimpl = Result.m1771exceptionOrNullimpl(failure);
            if (m1771exceptionOrNullimpl != null) {
                Log.e("Columbus/TargetRequest", ListImplementation$$ExternalSyntheticOutline0.m("Could not send response ", i2, i, " for request "), m1771exceptionOrNullimpl);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final LauncherActivityInfo getAppInfoForPackage(String str) {
            List<LauncherActivityInfo> activityList;
            boolean isEnabled;
            String concat = "getAppInfoForPackage pkg=".concat(str);
            ColumbusTargetRequestService columbusTargetRequestService = ColumbusTargetRequestService.this;
            boolean isEnabled2 = Trace.isEnabled();
            if (isEnabled2) {
                TraceUtilsKt.beginSlice(concat);
            }
            try {
                LauncherApps launcherApps = columbusTargetRequestService.launcherApps;
                LauncherActivityInfo launcherActivityInfo = null;
                if (launcherApps != null && (activityList = launcherApps.getActivityList(str, ((UserTrackerImpl) columbusTargetRequestService.userTracker).getUserHandle())) != null) {
                    Iterator<T> it = activityList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Object next = it.next();
                        LauncherActivityInfo launcherActivityInfo2 = (LauncherActivityInfo) next;
                        boolean z = false;
                        try {
                            String str2 = "getMainActivityLaunchIntent component=" + launcherActivityInfo2.getComponentName();
                            isEnabled = Trace.isEnabled();
                            if (isEnabled) {
                                TraceUtilsKt.beginSlice(str2);
                            }
                        } catch (RuntimeException unused) {
                        }
                        try {
                            LauncherApps launcherApps2 = columbusTargetRequestService.launcherApps;
                            boolean z2 = (launcherApps2 != null ? launcherApps2.getMainActivityLaunchIntent(launcherActivityInfo2.getComponentName(), null, ((UserTrackerImpl) columbusTargetRequestService.userTracker).getUserHandle()) : null) != null;
                            if (isEnabled) {
                                TraceUtilsKt.endSlice();
                            }
                            z = z2;
                            if (z) {
                                launcherActivityInfo = next;
                                break;
                            }
                        } catch (Throwable th) {
                            if (isEnabled) {
                                TraceUtilsKt.endSlice();
                            }
                            throw th;
                        }
                    }
                    launcherActivityInfo = launcherActivityInfo;
                }
                return launcherActivityInfo;
            } finally {
                if (isEnabled2) {
                    TraceUtilsKt.endSlice();
                }
            }
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            final ColumbusTargetRequestService columbusTargetRequestService = ColumbusTargetRequestService.this;
            String[] packagesForUid = columbusTargetRequestService.getPackageManager().getPackagesForUid(message.sendingUid);
            String str = packagesForUid != null ? packagesForUid[0] : null;
            int i = message.what;
            if (i != 1) {
                if (i != 2) {
                    RecordingInputConnection$$ExternalSyntheticOutline0.m("Invalid request type: ", "Columbus/TargetRequest", i);
                    return;
                }
                if (str == null || packageIsNotAllowed(str) || columbusTargetRequestService.columbusStructuredDataManager.getPackageShownCount(str) >= 3 || getAppInfoForPackage(str) == null) {
                    replyToMessenger(message.replyTo, message.what, 2);
                    return;
                }
                if (packageIsTarget(str)) {
                    replyToMessenger(message.replyTo, message.what, 0);
                    return;
                } else if (packageNeedsToCoolDown(str)) {
                    replyToMessenger(message.replyTo, message.what, 3);
                    return;
                } else {
                    replyToMessenger(message.replyTo, message.what, 1);
                    return;
                }
            }
            if (str == null || packageIsNotAllowed(str)) {
                replyToMessenger(message.replyTo, message.what, 1);
                Log.d("Columbus/TargetRequest", "Unsupported caller: " + str);
                return;
            }
            if (packageIsTarget(str)) {
                replyToMessenger(message.replyTo, message.what, 0);
                Log.d("Columbus/TargetRequest", "Caller already target: ".concat(str));
                return;
            }
            if (packageNeedsToCoolDown(str)) {
                replyToMessenger(message.replyTo, message.what, 2);
                Log.d("Columbus/TargetRequest", "Caller throttled: ".concat(str));
                return;
            }
            if (columbusTargetRequestService.columbusStructuredDataManager.getPackageShownCount(str) >= 3) {
                replyToMessenger(message.replyTo, message.what, 3);
                Log.d("Columbus/TargetRequest", "Caller already shown max times: ".concat(str));
                return;
            }
            final LauncherActivityInfo appInfoForPackage = getAppInfoForPackage(str);
            if (appInfoForPackage == null) {
                replyToMessenger(message.replyTo, message.what, 4);
                Log.d("Columbus/TargetRequest", "Caller not launchable: ".concat(str));
            } else {
                final Messenger messenger = message.replyTo;
                final int i2 = message.what;
                columbusTargetRequestService.mainHandler.post(new Runnable() { // from class: com.google.android.systemui.columbus.legacy.ColumbusTargetRequestService$IncomingMessageHandler$displayDialog$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        int packageShownCount = ColumbusTargetRequestService.this.columbusStructuredDataManager.getPackageShownCount(appInfoForPackage.getComponentName().getPackageName());
                        ColumbusTargetRequestService.this.uiEventLogger.log(ColumbusEvent.COLUMBUS_RETARGET_DIALOG_SHOWN, 0, appInfoForPackage.getComponentName().getPackageName());
                        ColumbusTargetRequestDialog columbusTargetRequestDialog = new ColumbusTargetRequestDialog(ColumbusTargetRequestService.this.sysUIContext);
                        columbusTargetRequestDialog.show();
                        LauncherActivityInfo launcherActivityInfo = appInfoForPackage;
                        ColumbusTargetRequestService columbusTargetRequestService2 = ColumbusTargetRequestService.this;
                        ColumbusTargetRequestService.IncomingMessageHandler incomingMessageHandler = this;
                        Messenger messenger2 = messenger;
                        int i3 = i2;
                        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener(i3, packageShownCount, launcherActivityInfo, messenger2, incomingMessageHandler, columbusTargetRequestService2) { // from class: com.google.android.systemui.columbus.legacy.ColumbusTargetRequestService$IncomingMessageHandler$displayDialog$1.1
                            public final /* synthetic */ LauncherActivityInfo $appInfo;
                            public final /* synthetic */ int $previousCount;
                            public final /* synthetic */ Messenger $replyTo;
                            public final /* synthetic */ int $requestCode;
                            public final /* synthetic */ ColumbusTargetRequestService this$0;
                            public final /* synthetic */ ColumbusTargetRequestService.IncomingMessageHandler this$1;

                            {
                                this.this$0 = columbusTargetRequestService2;
                            }

                            @Override // android.content.DialogInterface.OnClickListener
                            public final void onClick(DialogInterface dialogInterface, int i4) {
                                long currentTimeMillis;
                                if (i4 != -2) {
                                    if (i4 != -1) {
                                        ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0.m("Invalid dialog option: ", "Columbus/TargetRequest", i4);
                                        return;
                                    }
                                    Settings.Secure.putIntForUser(this.this$0.getContentResolver(), "columbus_enabled", 1, ((UserTrackerImpl) this.this$0.userTracker).getUserId());
                                    Settings.Secure.putStringForUser(this.this$0.getContentResolver(), "columbus_action", "launch", ((UserTrackerImpl) this.this$0.userTracker).getUserId());
                                    Settings.Secure.putStringForUser(this.this$0.getContentResolver(), "columbus_launch_app", this.$appInfo.getComponentName().flattenToString(), ((UserTrackerImpl) this.this$0.userTracker).getUserId());
                                    Settings.Secure.putStringForUser(this.this$0.getContentResolver(), "columbus_launch_app_shortcut", this.$appInfo.getComponentName().flattenToString(), ((UserTrackerImpl) this.this$0.userTracker).getUserId());
                                    ColumbusTargetRequestService.IncomingMessageHandler.replyToMessenger(this.$replyTo, this.$requestCode, 0);
                                    FragmentManagerViewModel$$ExternalSyntheticOutline0.m("Target changed to ", this.$appInfo.getComponentName().flattenToString(), "Columbus/TargetRequest");
                                    this.this$0.uiEventLogger.log(this.$previousCount == 0 ? ColumbusEvent.COLUMBUS_RETARGET_APPROVED : ColumbusEvent.COLUMBUS_RETARGET_FOLLOW_ON_APPROVED, 0, this.$appInfo.getComponentName().flattenToString());
                                    return;
                                }
                                ColumbusStructuredDataManager columbusStructuredDataManager = this.this$0.columbusStructuredDataManager;
                                String packageName = this.$appInfo.getComponentName().getPackageName();
                                synchronized (columbusStructuredDataManager.lock) {
                                    try {
                                        currentTimeMillis = SystemClock.currentNetworkTimeMillis();
                                    } catch (DateTimeException unused) {
                                        currentTimeMillis = System.currentTimeMillis();
                                    }
                                    int length = columbusStructuredDataManager.packageStats.length();
                                    int i5 = 0;
                                    while (true) {
                                        if (i5 >= length) {
                                            columbusStructuredDataManager.packageStats.put(ColumbusStructuredDataManager.makeJSONObject$default(columbusStructuredDataManager, packageName, currentTimeMillis, 2));
                                            columbusStructuredDataManager.storePackageStats();
                                            break;
                                        }
                                        JSONObject jSONObject = columbusStructuredDataManager.packageStats.getJSONObject(i5);
                                        if (packageName.equals(jSONObject.getString("packageName"))) {
                                            jSONObject.put("lastDeny", currentTimeMillis);
                                            columbusStructuredDataManager.packageStats.put(i5, jSONObject);
                                            columbusStructuredDataManager.storePackageStats();
                                            break;
                                        }
                                        i5++;
                                    }
                                }
                                ColumbusTargetRequestService.IncomingMessageHandler.replyToMessenger(this.$replyTo, this.$requestCode, 5);
                                FragmentManagerViewModel$$ExternalSyntheticOutline0.m("Target change denied by user: ", this.$appInfo.getComponentName().flattenToString(), "Columbus/TargetRequest");
                                this.this$0.uiEventLogger.log(this.$previousCount == 0 ? ColumbusEvent.COLUMBUS_RETARGET_NOT_APPROVED : ColumbusEvent.COLUMBUS_RETARGET_FOLLOW_ON_NOT_APPROVED, 0, this.$appInfo.getComponentName().flattenToString());
                            }
                        };
                        DialogInterface.OnCancelListener onCancelListener = new DialogInterface.OnCancelListener(i3, packageShownCount, launcherActivityInfo, messenger2, incomingMessageHandler, columbusTargetRequestService2) { // from class: com.google.android.systemui.columbus.legacy.ColumbusTargetRequestService$IncomingMessageHandler$displayDialog$1.2
                            public final /* synthetic */ LauncherActivityInfo $appInfo;
                            public final /* synthetic */ int $previousCount;
                            public final /* synthetic */ Messenger $replyTo;
                            public final /* synthetic */ int $requestCode;
                            public final /* synthetic */ ColumbusTargetRequestService.IncomingMessageHandler this$0;
                            public final /* synthetic */ ColumbusTargetRequestService this$1;

                            {
                                this.this$1 = columbusTargetRequestService2;
                            }

                            @Override // android.content.DialogInterface.OnCancelListener
                            public final void onCancel(DialogInterface dialogInterface) {
                                ColumbusTargetRequestService.IncomingMessageHandler.replyToMessenger(this.$replyTo, this.$requestCode, 6);
                                FragmentManagerViewModel$$ExternalSyntheticOutline0.m("Target change dismissed by user: ", this.$appInfo.getComponentName().flattenToString(), "Columbus/TargetRequest");
                                this.this$1.uiEventLogger.log(this.$previousCount == 0 ? ColumbusEvent.COLUMBUS_RETARGET_NOT_APPROVED : ColumbusEvent.COLUMBUS_RETARGET_FOLLOW_ON_NOT_APPROVED, 0, this.$appInfo.getComponentName().flattenToString());
                            }
                        };
                        columbusTargetRequestDialog.setTitle(columbusTargetRequestDialog.getContext().getString(R.string.columbus_target_request_dialog_title, launcherActivityInfo.getLabel()));
                        columbusTargetRequestDialog.setMessage(columbusTargetRequestDialog.getContext().getString(R.string.columbus_target_request_dialog_summary, launcherActivityInfo.getLabel()));
                        columbusTargetRequestDialog.setPositiveButton(R.string.columbus_target_request_dialog_allow, onClickListener);
                        columbusTargetRequestDialog.setNegativeButton(R.string.columbus_target_request_dialog_deny, onClickListener);
                        columbusTargetRequestDialog.setOnCancelListener(onCancelListener);
                        columbusTargetRequestDialog.setCanceledOnTouchOutside(true);
                        ColumbusTargetRequestService.IncomingMessageHandler incomingMessageHandler2 = this;
                        String packageName = appInfoForPackage.getComponentName().getPackageName();
                        ColumbusStructuredDataManager columbusStructuredDataManager = ColumbusTargetRequestService.this.columbusStructuredDataManager;
                        synchronized (columbusStructuredDataManager.lock) {
                            int length = columbusStructuredDataManager.packageStats.length();
                            for (int i4 = 0; i4 < length; i4++) {
                                JSONObject jSONObject = columbusStructuredDataManager.packageStats.getJSONObject(i4);
                                if (packageName.equals(jSONObject.getString("packageName"))) {
                                    jSONObject.put("shownCount", jSONObject.getInt("shownCount") + 1);
                                    columbusStructuredDataManager.packageStats.put(i4, jSONObject);
                                    columbusStructuredDataManager.storePackageStats();
                                    return;
                                }
                            }
                            columbusStructuredDataManager.packageStats.put(ColumbusStructuredDataManager.makeJSONObject$default(columbusStructuredDataManager, packageName, 0L, 4));
                            columbusStructuredDataManager.storePackageStats();
                        }
                    }
                });
            }
        }

        public final boolean packageIsNotAllowed(String str) {
            ColumbusTargetRequestService columbusTargetRequestService = ColumbusTargetRequestService.this;
            if (!columbusTargetRequestService.allowPackageList.contains(str)) {
                return true;
            }
            SigningInfo signingInfo = columbusTargetRequestService.sysUIContext.getPackageManager().getPackageInfo(str, 134217728).signingInfo;
            if (signingInfo == null) {
                throw new IllegalStateException("Required value was null.");
            }
            Signature[] apkContentsSigners = signingInfo.hasMultipleSigners() ? signingInfo.getApkContentsSigners() : signingInfo.getSigningCertificateHistory();
            Intrinsics.checkNotNull(apkContentsSigners);
            ArrayList arrayList = new ArrayList(apkContentsSigners.length);
            boolean z = false;
            for (Signature signature : apkContentsSigners) {
                arrayList.add(new String(columbusTargetRequestService.messageDigest.digest(signature.toByteArray()), Charsets.UTF_16));
            }
            if (!arrayList.isEmpty()) {
                Iterator it = arrayList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    if (columbusTargetRequestService.allowCertList.contains((String) it.next())) {
                        z = true;
                        break;
                    }
                }
            }
            return !z;
        }

        public final boolean packageIsTarget(String str) {
            ColumbusTargetRequestService columbusTargetRequestService = ColumbusTargetRequestService.this;
            boolean isColumbusEnabled = columbusTargetRequestService.columbusSettings.isColumbusEnabled();
            boolean equals = "launch".equals(columbusTargetRequestService.columbusSettings.selectedAction());
            ComponentName unflattenFromString = ComponentName.unflattenFromString(columbusTargetRequestService.columbusSettings.selectedApp());
            return isColumbusEnabled && equals && str.equals(unflattenFromString != null ? unflattenFromString.getPackageName() : null);
        }

        public final boolean packageNeedsToCoolDown(String str) {
            long currentTimeMillis;
            long lastDenyTimestamp;
            ColumbusStructuredDataManager columbusStructuredDataManager = ColumbusTargetRequestService.this.columbusStructuredDataManager;
            synchronized (columbusStructuredDataManager.lock) {
                try {
                    currentTimeMillis = SystemClock.currentNetworkTimeMillis();
                } catch (DateTimeException unused) {
                    currentTimeMillis = System.currentTimeMillis();
                }
                lastDenyTimestamp = currentTimeMillis - columbusStructuredDataManager.getLastDenyTimestamp(str);
            }
            return lastDenyTimestamp < ColumbusTargetRequestService.PACKAGE_DENY_COOLDOWN_MS;
        }
    }

    public ColumbusTargetRequestService(Context context, UserTracker userTracker, ColumbusSettings columbusSettings, ColumbusStructuredDataManager columbusStructuredDataManager, UiEventLogger uiEventLogger, Handler handler, Looper looper) {
        this.sysUIContext = context;
        this.userTracker = userTracker;
        this.columbusSettings = columbusSettings;
        this.columbusStructuredDataManager = columbusStructuredDataManager;
        this.uiEventLogger = uiEventLogger;
        this.mainHandler = handler;
        this.columbusContext = new ColumbusContext(context);
        this.messenger = new Messenger(new IncomingMessageHandler(looper));
        String[] stringArray = context.getResources().getStringArray(R.array.columbus_sumatra_package_allow_list);
        this.allowPackageList = SetsKt.setOf(Arrays.copyOf(stringArray, stringArray.length));
        String[] stringArray2 = context.getResources().getStringArray(R.array.columbus_sumatra_cert_allow_list);
        this.allowCertList = SetsKt.setOf(Arrays.copyOf(stringArray2, stringArray2.length));
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        if (this.columbusContext.packageManager.hasSystemFeature("com.google.android.feature.QUICK_TAP")) {
            return this.messenger.getBinder();
        }
        return null;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        Object systemService = getSystemService("launcherapps");
        this.launcherApps = systemService instanceof LauncherApps ? (LauncherApps) systemService : null;
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }
}
