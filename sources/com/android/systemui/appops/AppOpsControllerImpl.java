package com.android.systemui.appops;

import android.app.AppOpsManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.AudioRecordingConfiguration;
import android.os.Handler;
import android.os.Looper;
import android.permission.PermissionManager;
import android.util.ArraySet;
import android.util.SparseArray;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.appops.AppOpsController;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.statusbar.policy.IndividualSensorPrivacyController;
import com.android.systemui.statusbar.policy.IndividualSensorPrivacyControllerImpl;
import com.android.systemui.util.Assert;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AppOpsControllerImpl extends BroadcastReceiver implements AppOpsController, AppOpsManager.OnOpActiveChangedListener, AppOpsManager.OnOpNotedInternalListener, IndividualSensorPrivacyController.Callback, Dumpable {
    public static final int[] OPS;
    public static final int[] OPS_CAMERA;
    protected static final int[] OPS_MIC;
    public final AppOpsManager mAppOps;
    public final AudioManager mAudioManager;
    public H mBGHandler;
    public final Executor mBgExecutor;
    public boolean mCameraDisabled;
    public final SystemClock mClock;
    public final Context mContext;
    public final BroadcastDispatcher mDispatcher;
    public boolean mListening;
    public boolean mMicMuted;
    public final IndividualSensorPrivacyController mSensorPrivacyController;
    public final List mCallbacks = new ArrayList();
    public final SparseArray mCallbacksByCode = new SparseArray();
    public final List mActiveItems = new ArrayList();
    public final List mNotedItems = new ArrayList();
    public final SparseArray mRecordingsByUid = new SparseArray();
    public final AnonymousClass1 mAudioRecordingCallback = new AnonymousClass1();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.appops.AppOpsControllerImpl$1, reason: invalid class name */
    public final class AnonymousClass1 extends AudioManager.AudioRecordingCallback {
        public AnonymousClass1() {
        }

        @Override // android.media.AudioManager.AudioRecordingCallback
        public final void onRecordingConfigChanged(List list) {
            synchronized (AppOpsControllerImpl.this.mActiveItems) {
                try {
                    AppOpsControllerImpl.this.mRecordingsByUid.clear();
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        AudioRecordingConfiguration audioRecordingConfiguration = (AudioRecordingConfiguration) list.get(i);
                        ArrayList arrayList = (ArrayList) AppOpsControllerImpl.this.mRecordingsByUid.get(audioRecordingConfiguration.getClientUid());
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                            AppOpsControllerImpl.this.mRecordingsByUid.put(audioRecordingConfiguration.getClientUid(), arrayList);
                        }
                        arrayList.add(audioRecordingConfiguration);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            AppOpsControllerImpl.this.updateSensorDisabledStatus();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class H extends Handler {
        public H(Looper looper) {
            super(looper);
        }
    }

    static {
        int[] iArr = {27, 100, 120, 136, 121};
        OPS_MIC = iArr;
        int[] iArr2 = {26, 101};
        OPS_CAMERA = iArr2;
        int[][] iArr3 = {iArr, iArr2, new int[]{1, 0, 42}, new int[]{24}};
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            int[] iArr4 = iArr3[i2];
            if (iArr4 != null && iArr4.length != 0) {
                i += iArr4.length;
            }
        }
        int[] iArr5 = new int[i];
        int i3 = 0;
        for (int i4 = 0; i4 < 4; i4++) {
            int[] iArr6 = iArr3[i4];
            if (iArr6 != null && iArr6.length != 0) {
                System.arraycopy(iArr6, 0, iArr5, i3, iArr6.length);
                i3 += iArr6.length;
            }
        }
        OPS = iArr5;
    }

    public AppOpsControllerImpl(Context context, Looper looper, Executor executor, DumpManager dumpManager, AudioManager audioManager, IndividualSensorPrivacyController individualSensorPrivacyController, BroadcastDispatcher broadcastDispatcher, SystemClock systemClock) {
        this.mDispatcher = broadcastDispatcher;
        this.mAppOps = (AppOpsManager) context.getSystemService("appops");
        this.mBGHandler = new H(looper);
        this.mBgExecutor = executor;
        int length = OPS.length;
        for (int i = 0; i < length; i++) {
            this.mCallbacksByCode.put(OPS[i], new ArraySet());
        }
        this.mAudioManager = audioManager;
        this.mSensorPrivacyController = individualSensorPrivacyController;
        this.mMicMuted = audioManager.isMicrophoneMute() || ((IndividualSensorPrivacyControllerImpl) individualSensorPrivacyController).isSensorBlocked(1);
        this.mCameraDisabled = ((IndividualSensorPrivacyControllerImpl) individualSensorPrivacyController).isSensorBlocked(2);
        this.mContext = context;
        this.mClock = systemClock;
        dumpManager.getClass();
        DumpManager.registerDumpable$default(dumpManager, "AppOpsControllerImpl", this);
    }

    public static AppOpItem getAppOpItemLocked(List list, int i, int i2, String str) {
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            AppOpItem appOpItem = (AppOpItem) list.get(i3);
            if (appOpItem.mCode == i && appOpItem.mUid == i2 && appOpItem.mPackageName.equals(str)) {
                return appOpItem;
            }
        }
        return null;
    }

    public final void addCallback(int[] iArr, AppOpsController.Callback callback) {
        int length = iArr.length;
        boolean z = false;
        for (int i = 0; i < length; i++) {
            if (this.mCallbacksByCode.contains(iArr[i])) {
                ((Set) this.mCallbacksByCode.get(iArr[i])).add(callback);
                z = true;
            }
        }
        if (z) {
            this.mCallbacks.add(callback);
        }
        if (this.mCallbacks.isEmpty()) {
            return;
        }
        setListening(true);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        StringBuilder m = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, "AppOpsController state:", "  Listening: ");
        m.append(this.mListening);
        printWriter.println(m.toString());
        printWriter.println("  Active Items:");
        for (int i = 0; i < ((ArrayList) this.mActiveItems).size(); i++) {
            AppOpItem appOpItem = (AppOpItem) ((ArrayList) this.mActiveItems).get(i);
            printWriter.print("    ");
            printWriter.println(appOpItem.toString());
        }
        printWriter.println("  Noted Items:");
        for (int i2 = 0; i2 < ((ArrayList) this.mNotedItems).size(); i2++) {
            AppOpItem appOpItem2 = (AppOpItem) ((ArrayList) this.mNotedItems).get(i2);
            printWriter.print("    ");
            printWriter.println(appOpItem2.toString());
        }
    }

    public final List getActiveAppOps(boolean z) {
        int i;
        Assert.isNotMainThread();
        ArrayList arrayList = new ArrayList();
        synchronized (this.mActiveItems) {
            try {
                int size = ((ArrayList) this.mActiveItems).size();
                for (int i2 = 0; i2 < size; i2++) {
                    AppOpItem appOpItem = (AppOpItem) ((ArrayList) this.mActiveItems).get(i2);
                    if (PermissionManager.shouldShowPackageForIndicatorCached(this.mContext, appOpItem.mPackageName) && (z || !appOpItem.mIsDisabled)) {
                        arrayList.add(appOpItem);
                    }
                }
            } finally {
            }
        }
        synchronized (this.mNotedItems) {
            try {
                int size2 = ((ArrayList) this.mNotedItems).size();
                for (i = 0; i < size2; i++) {
                    AppOpItem appOpItem2 = (AppOpItem) ((ArrayList) this.mNotedItems).get(i);
                    if (PermissionManager.shouldShowPackageForIndicatorCached(this.mContext, appOpItem2.mPackageName)) {
                        arrayList.add(appOpItem2);
                    }
                }
            } finally {
            }
        }
        return arrayList;
    }

    public final boolean isAnyRecordingPausedLocked(int i) {
        if (this.mMicMuted) {
            return true;
        }
        List list = (List) this.mRecordingsByUid.get(i);
        if (list == null) {
            return false;
        }
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((AudioRecordingConfiguration) list.get(i2)).isClientSilenced()) {
                return true;
            }
        }
        return false;
    }

    public final void notifySuscribersWorker(int i, int i2, String str, boolean z) {
        if (this.mCallbacksByCode.contains(i) && PermissionManager.shouldShowPackageForIndicatorCached(this.mContext, str)) {
            Iterator it = ((Set) this.mCallbacksByCode.get(i)).iterator();
            while (it.hasNext()) {
                ((AppOpsController.Callback) it.next()).onActiveStateChanged(i, i2, str, z);
            }
        }
    }

    @Override // android.app.AppOpsManager.OnOpActiveChangedListener
    public final void onOpActiveChanged(String str, int i, String str2, boolean z) {
        onOpActiveChanged(str, i, str2, null, z, 0, -1);
    }

    public final void onOpNoted(int i, int i2, String str, String str2, int i3, int i4) {
        final AppOpItem appOpItemLocked;
        boolean z;
        boolean z2;
        if (i4 != 0) {
            return;
        }
        synchronized (this.mNotedItems) {
            try {
                appOpItemLocked = getAppOpItemLocked(this.mNotedItems, i, i2, str);
                if (appOpItemLocked == null) {
                    ((SystemClockImpl) this.mClock).getClass();
                    AppOpItem appOpItem = new AppOpItem(i, i2, android.os.SystemClock.elapsedRealtime(), str);
                    this.mNotedItems.add(appOpItem);
                    z = true;
                    appOpItemLocked = appOpItem;
                } else {
                    z = false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.mBGHandler.removeCallbacksAndMessages(appOpItemLocked);
        final H h = this.mBGHandler;
        h.removeCallbacksAndMessages(appOpItemLocked);
        h.postDelayed(new Runnable() { // from class: com.android.systemui.appops.AppOpsControllerImpl.H.1
            @Override // java.lang.Runnable
            public final void run() {
                boolean z3;
                AppOpsControllerImpl appOpsControllerImpl = AppOpsControllerImpl.this;
                AppOpItem appOpItem2 = appOpItemLocked;
                int i5 = appOpItem2.mCode;
                int i6 = appOpItem2.mUid;
                String str3 = appOpItem2.mPackageName;
                synchronized (appOpsControllerImpl.mNotedItems) {
                    try {
                        AppOpItem appOpItemLocked2 = AppOpsControllerImpl.getAppOpItemLocked(appOpsControllerImpl.mNotedItems, i5, i6, str3);
                        if (appOpItemLocked2 == null) {
                            return;
                        }
                        appOpsControllerImpl.mNotedItems.remove(appOpItemLocked2);
                        synchronized (appOpsControllerImpl.mActiveItems) {
                            z3 = AppOpsControllerImpl.getAppOpItemLocked(appOpsControllerImpl.mActiveItems, i5, i6, str3) != null;
                        }
                        if (z3) {
                            return;
                        }
                        appOpsControllerImpl.notifySuscribersWorker(i5, i6, str3, false);
                    } finally {
                    }
                }
            }
        }, appOpItemLocked, 5000L);
        if (z) {
            synchronized (this.mActiveItems) {
                z2 = getAppOpItemLocked(this.mActiveItems, i, i2, str) != null;
            }
            if (z2) {
                return;
            }
            this.mBGHandler.post(new AppOpsControllerImpl$$ExternalSyntheticLambda0(this, i, i2, str, true));
        }
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        boolean z = true;
        if (!this.mAudioManager.isMicrophoneMute() && !((IndividualSensorPrivacyControllerImpl) this.mSensorPrivacyController).isSensorBlocked(1)) {
            z = false;
        }
        this.mMicMuted = z;
        updateSensorDisabledStatus();
    }

    @Override // com.android.systemui.statusbar.policy.IndividualSensorPrivacyController.Callback
    public final void onSensorBlockedChanged(final int i, final boolean z) {
        this.mBGHandler.post(new Runnable() { // from class: com.android.systemui.appops.AppOpsControllerImpl$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                AppOpsControllerImpl appOpsControllerImpl = AppOpsControllerImpl.this;
                int i2 = i;
                boolean z2 = z;
                if (i2 == 2) {
                    appOpsControllerImpl.mCameraDisabled = z2;
                } else {
                    boolean z3 = true;
                    if (i2 == 1) {
                        if (!appOpsControllerImpl.mAudioManager.isMicrophoneMute() && !z2) {
                            z3 = false;
                        }
                        appOpsControllerImpl.mMicMuted = z3;
                    }
                }
                appOpsControllerImpl.updateSensorDisabledStatus();
            }
        });
    }

    public void setBGHandler(H h) {
        this.mBGHandler = h;
    }

    public void setListening(final boolean z) {
        this.mListening = z;
        this.mBgExecutor.execute(new Runnable() { // from class: com.android.systemui.appops.AppOpsControllerImpl$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                final AppOpsManager.OnOpNotedListener onOpNotedListener = AppOpsControllerImpl.this;
                if (!z) {
                    onOpNotedListener.mAppOps.stopWatchingActive(onOpNotedListener);
                    onOpNotedListener.mAppOps.stopWatchingNoted(onOpNotedListener);
                    onOpNotedListener.mAudioManager.unregisterAudioRecordingCallback(onOpNotedListener.mAudioRecordingCallback);
                    ((IndividualSensorPrivacyControllerImpl) onOpNotedListener.mSensorPrivacyController).removeCallback(onOpNotedListener);
                    onOpNotedListener.mBGHandler.removeCallbacksAndMessages(null);
                    onOpNotedListener.mDispatcher.unregisterReceiver(onOpNotedListener);
                    synchronized (onOpNotedListener.mActiveItems) {
                        onOpNotedListener.mActiveItems.clear();
                        onOpNotedListener.mRecordingsByUid.clear();
                    }
                    synchronized (onOpNotedListener.mNotedItems) {
                        onOpNotedListener.mNotedItems.clear();
                    }
                    return;
                }
                List<AppOpsManager.PackageOps> packagesForOps = onOpNotedListener.mAppOps.getPackagesForOps(AppOpsControllerImpl.OPS);
                if (packagesForOps != null) {
                    for (AppOpsManager.PackageOps packageOps : packagesForOps) {
                        for (AppOpsManager.OpEntry opEntry : packageOps.getOps()) {
                            for (Map.Entry entry : opEntry.getAttributedOpEntries().entrySet()) {
                                if (((AppOpsManager.AttributedOpEntry) entry.getValue()).isRunning()) {
                                    onOpNotedListener.onOpActiveChanged(opEntry.getOpStr(), packageOps.getUid(), packageOps.getPackageName(), (String) entry.getKey(), true, 0, -1);
                                }
                            }
                        }
                    }
                }
                AppOpsManager appOpsManager = onOpNotedListener.mAppOps;
                int[] iArr = AppOpsControllerImpl.OPS;
                appOpsManager.startWatchingActive(iArr, onOpNotedListener);
                onOpNotedListener.mAppOps.startWatchingNoted(iArr, onOpNotedListener);
                onOpNotedListener.mAudioManager.registerAudioRecordingCallback(onOpNotedListener.mAudioRecordingCallback, onOpNotedListener.mBGHandler);
                ((IndividualSensorPrivacyControllerImpl) onOpNotedListener.mSensorPrivacyController).addCallback(onOpNotedListener);
                boolean z2 = true;
                if (!onOpNotedListener.mAudioManager.isMicrophoneMute() && !((IndividualSensorPrivacyControllerImpl) onOpNotedListener.mSensorPrivacyController).isSensorBlocked(1)) {
                    z2 = false;
                }
                onOpNotedListener.mMicMuted = z2;
                onOpNotedListener.mCameraDisabled = ((IndividualSensorPrivacyControllerImpl) onOpNotedListener.mSensorPrivacyController).isSensorBlocked(2);
                onOpNotedListener.mBGHandler.post(new Runnable() { // from class: com.android.systemui.appops.AppOpsControllerImpl$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        AppOpsControllerImpl appOpsControllerImpl = AppOpsControllerImpl.this;
                        appOpsControllerImpl.mAudioRecordingCallback.onRecordingConfigChanged(appOpsControllerImpl.mAudioManager.getActiveRecordingConfigurations());
                    }
                });
                onOpNotedListener.mDispatcher.registerReceiverWithHandler(onOpNotedListener, new IntentFilter("android.media.action.MICROPHONE_MUTE_CHANGED"), onOpNotedListener.mBGHandler);
            }
        });
    }

    public final void updateSensorDisabledStatus() {
        boolean z;
        boolean z2;
        boolean z3;
        synchronized (this.mActiveItems) {
            try {
                int size = ((ArrayList) this.mActiveItems).size();
                for (int i = 0; i < size; i++) {
                    AppOpItem appOpItem = (AppOpItem) ((ArrayList) this.mActiveItems).get(i);
                    int i2 = appOpItem.mCode;
                    int i3 = 0;
                    while (true) {
                        int[] iArr = OPS_MIC;
                        z = true;
                        if (i3 >= iArr.length) {
                            z2 = false;
                            break;
                        } else {
                            if (i2 == iArr[i3]) {
                                z2 = true;
                                break;
                            }
                            i3++;
                        }
                    }
                    if (z2) {
                        z3 = isAnyRecordingPausedLocked(appOpItem.mUid);
                    } else {
                        int i4 = appOpItem.mCode;
                        int i5 = 0;
                        while (true) {
                            if (i5 >= 2) {
                                z = false;
                                break;
                            } else if (i4 == OPS_CAMERA[i5]) {
                                break;
                            } else {
                                i5++;
                            }
                        }
                        z3 = z ? this.mCameraDisabled : false;
                    }
                    if (appOpItem.mIsDisabled != z3) {
                        appOpItem.mIsDisabled = z3;
                        this.mBGHandler.post(new AppOpsControllerImpl$$ExternalSyntheticLambda0(this, appOpItem.mCode, appOpItem.mUid, appOpItem.mPackageName, !z3));
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void onOpActiveChanged(String str, int i, String str2, String str3, boolean z, int i2, int i3) {
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        int strOpToOp = AppOpsManager.strOpToOp(str);
        if (z && i3 != -1 && i2 != 0 && (i2 & 1) == 0 && (i2 & 8) == 0) {
            return;
        }
        synchronized (this.mActiveItems) {
            try {
                AppOpItem appOpItemLocked = getAppOpItemLocked(this.mActiveItems, strOpToOp, i, str2);
                if (appOpItemLocked == null && z) {
                    ((SystemClockImpl) this.mClock).getClass();
                    AppOpItem appOpItem = new AppOpItem(strOpToOp, i, android.os.SystemClock.elapsedRealtime(), str2);
                    int i4 = 0;
                    while (true) {
                        int[] iArr = OPS_MIC;
                        if (i4 >= iArr.length) {
                            z4 = false;
                            break;
                        } else {
                            if (strOpToOp == iArr[i4]) {
                                z4 = true;
                                break;
                            }
                            i4++;
                        }
                    }
                    if (z4) {
                        appOpItem.mIsDisabled = isAnyRecordingPausedLocked(i);
                    } else {
                        int i5 = 0;
                        while (true) {
                            if (i5 >= 2) {
                                z5 = false;
                                break;
                            } else {
                                if (strOpToOp == OPS_CAMERA[i5]) {
                                    z5 = true;
                                    break;
                                }
                                i5++;
                            }
                        }
                        if (z5) {
                            appOpItem.mIsDisabled = this.mCameraDisabled;
                        }
                    }
                    this.mActiveItems.add(appOpItem);
                    z2 = !appOpItem.mIsDisabled;
                } else if (appOpItemLocked == null || z) {
                    z2 = false;
                } else {
                    this.mActiveItems.remove(appOpItemLocked);
                    z2 = true;
                }
            } finally {
            }
        }
        if (z2) {
            synchronized (this.mNotedItems) {
                z3 = getAppOpItemLocked(this.mNotedItems, strOpToOp, i, str2) != null;
            }
            if (z3) {
                return;
            }
            this.mBGHandler.post(new AppOpsControllerImpl$$ExternalSyntheticLambda0(this, strOpToOp, i, str2, z));
        }
    }
}
