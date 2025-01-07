package com.android.systemui.volume;

import android.R;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.AudioSystem;
import android.media.IAudioService;
import android.media.IVolumeController;
import android.media.MediaMetadata;
import android.media.MediaRouter2Manager;
import android.media.RoutingSessionInfo;
import android.media.VolumePolicy;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.VibrationEffect;
import android.provider.Settings;
import android.service.notification.Condition;
import android.service.notification.ZenModeConfig;
import android.util.ArrayMap;
import android.util.Log;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.CaptioningManager;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import androidx.collection.MutableObjectList$$ExternalSyntheticOutline0;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.text.input.internal.RecordingInputConnection$$ExternalSyntheticOutline0;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import androidx.fragment.app.FragmentManagerViewModel$$ExternalSyntheticOutline0;
import androidx.lifecycle.Observer;
import com.android.app.viewcapture.data.ViewNode;
import com.android.keyguard.ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0;
import com.android.settingslib.volume.MediaSessions;
import com.android.systemui.Dumpable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.systemui.util.RingerModeLiveData;
import com.android.systemui.util.RingerModeTrackerImpl;
import com.android.systemui.util.concurrency.ThreadFactoryImpl;
import com.android.systemui.util.kotlin.JavaAdapter;
import com.android.systemui.volume.VolumeDialogControllerImpl;
import com.android.systemui.volume.domain.interactor.AudioSharingInteractor;
import dalvik.annotation.optimization.NeverCompile;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VolumeDialogControllerImpl implements VolumeDialogController, Dumpable {
    public static final boolean DEBUG;
    static final int DYNAMIC_STREAM_BROADCAST = 99;
    public static final AudioAttributes SONIFICIATION_VIBRATION_ATTRIBUTES;
    public static final ArrayMap STREAMS;
    public static final String TAG;
    public final ActivityManager mActivityManager;
    public final AudioManager mAudio;
    public final IAudioService mAudioService;
    public final C mCallbacks;
    public final AtomicReference mCaptioningManager;
    public final Context mContext;
    public boolean mDeviceInteractive;
    public final boolean mHasVibrator;
    public final boolean mInAudioSharing;
    public final KeyguardManager mKeyguardManager;
    public long mLastToggledRingerOn;
    public final MediaSessions mMediaSessions;
    public final MediaSessionsCallbacks mMediaSessionsCallbacksW;
    public final NotificationManager mNoMan;
    public final PackageManager mPackageManager;
    public final RingerModeObservers mRingerModeObservers;
    public final MediaRouter2Manager mRouter2Manager;
    public boolean mShowA11yStream;
    public boolean mShowSafetyWarning;
    public boolean mShowVolumeDialog;
    public final VolumeDialogController.State mState;
    public VolumeDialogComponent mUserActivityListener;
    public final UserTracker.Callback mUserChangedCallback;
    public final UserTracker mUserTracker;
    public final VibratorHelper mVibrator;
    public final VC mVolumeController;
    public final VolumeControllerAdapter mVolumeControllerAdapter;
    public VolumePolicy mVolumePolicy;
    public final AnonymousClass1 mWakefullnessLifecycleObserver;
    public final WakefulnessLifecycle mWakefulnessLifecycle;
    public final W mWorker;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class C implements VolumeDialogController.Callbacks {
        public final Map mCallbackMap = new ConcurrentHashMap();

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.volume.VolumeDialogControllerImpl$C$2, reason: invalid class name */
        public final class AnonymousClass2 implements Runnable {
            public final /* synthetic */ int $r8$classId;
            public final /* synthetic */ Map.Entry val$entry;
            public final /* synthetic */ int val$reason;

            public /* synthetic */ AnonymousClass2(Map.Entry entry, int i, int i2) {
                this.$r8$classId = i2;
                this.val$entry = entry;
                this.val$reason = i;
            }

            @Override // java.lang.Runnable
            public final void run() {
                switch (this.$r8$classId) {
                    case 0:
                        ((VolumeDialogController.Callbacks) this.val$entry.getKey()).onDismissRequested(this.val$reason);
                        break;
                    case 1:
                        ((VolumeDialogController.Callbacks) this.val$entry.getKey()).onLayoutDirectionChanged(this.val$reason);
                        break;
                    default:
                        ((VolumeDialogController.Callbacks) this.val$entry.getKey()).onShowSafetyWarning(this.val$reason);
                        break;
                }
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.volume.VolumeDialogControllerImpl$C$5, reason: invalid class name */
        public final class AnonymousClass5 implements Runnable {
            public final /* synthetic */ int $r8$classId;
            public final /* synthetic */ Map.Entry val$entry;

            public /* synthetic */ AnonymousClass5(Map.Entry entry, int i) {
                this.$r8$classId = i;
                this.val$entry = entry;
            }

            @Override // java.lang.Runnable
            public final void run() {
                switch (this.$r8$classId) {
                    case 0:
                        ((VolumeDialogController.Callbacks) this.val$entry.getKey()).onConfigurationChanged();
                        break;
                    case 1:
                        ((VolumeDialogController.Callbacks) this.val$entry.getKey()).onVolumeChangedFromKey();
                        break;
                    case 2:
                        ((VolumeDialogController.Callbacks) this.val$entry.getKey()).onShowVibrateHint();
                        break;
                    case 3:
                        ((VolumeDialogController.Callbacks) this.val$entry.getKey()).onShowSilentHint();
                        break;
                    default:
                        ((VolumeDialogController.Callbacks) this.val$entry.getKey()).onScreenOff();
                        break;
                }
            }
        }

        @Override // com.android.systemui.plugins.VolumeDialogController.Callbacks
        public final void onAccessibilityModeChanged(Boolean bool) {
            final boolean z = bool != null && bool.booleanValue();
            for (final Map.Entry entry : ((ConcurrentHashMap) this.mCallbackMap).entrySet()) {
                ((Handler) entry.getValue()).post(new Runnable() { // from class: com.android.systemui.volume.VolumeDialogControllerImpl.C.12
                    @Override // java.lang.Runnable
                    public final void run() {
                        ((VolumeDialogController.Callbacks) entry.getKey()).onAccessibilityModeChanged(Boolean.valueOf(z));
                    }
                });
            }
        }

        @Override // com.android.systemui.plugins.VolumeDialogController.Callbacks
        public final void onCaptionComponentStateChanged(Boolean bool, Boolean bool2) {
            boolean z = bool != null && bool.booleanValue();
            for (Map.Entry entry : ((ConcurrentHashMap) this.mCallbackMap).entrySet()) {
                ((Handler) entry.getValue()).post(new VolumeDialogControllerImpl$C$$ExternalSyntheticLambda0(entry, z, bool2, 1));
            }
        }

        @Override // com.android.systemui.plugins.VolumeDialogController.Callbacks
        public final void onCaptionEnabledStateChanged(Boolean bool, Boolean bool2) {
            boolean z = bool != null && bool.booleanValue();
            for (Map.Entry entry : ((ConcurrentHashMap) this.mCallbackMap).entrySet()) {
                ((Handler) entry.getValue()).post(new VolumeDialogControllerImpl$C$$ExternalSyntheticLambda0(entry, z, bool2, 0));
            }
        }

        @Override // com.android.systemui.plugins.VolumeDialogController.Callbacks
        public final void onConfigurationChanged() {
            for (Map.Entry entry : ((ConcurrentHashMap) this.mCallbackMap).entrySet()) {
                ((Handler) entry.getValue()).post(new AnonymousClass5(entry, 0));
            }
        }

        @Override // com.android.systemui.plugins.VolumeDialogController.Callbacks
        public final void onDismissRequested(int i) {
            for (Map.Entry entry : ((ConcurrentHashMap) this.mCallbackMap).entrySet()) {
                ((Handler) entry.getValue()).post(new AnonymousClass2(entry, i, 0));
            }
        }

        @Override // com.android.systemui.plugins.VolumeDialogController.Callbacks
        public final void onLayoutDirectionChanged(int i) {
            for (Map.Entry entry : ((ConcurrentHashMap) this.mCallbackMap).entrySet()) {
                ((Handler) entry.getValue()).post(new AnonymousClass2(entry, i, 1));
            }
        }

        @Override // com.android.systemui.plugins.VolumeDialogController.Callbacks
        public final void onScreenOff() {
            for (Map.Entry entry : ((ConcurrentHashMap) this.mCallbackMap).entrySet()) {
                ((Handler) entry.getValue()).post(new AnonymousClass5(entry, 4));
            }
        }

        @Override // com.android.systemui.plugins.VolumeDialogController.Callbacks
        public final void onShowCsdWarning(final int i, final int i2) {
            for (final Map.Entry entry : ((ConcurrentHashMap) this.mCallbackMap).entrySet()) {
                ((Handler) entry.getValue()).post(new Runnable() { // from class: com.android.systemui.volume.VolumeDialogControllerImpl.C.10
                    @Override // java.lang.Runnable
                    public final void run() {
                        ((VolumeDialogController.Callbacks) entry.getKey()).onShowCsdWarning(i, i2);
                    }
                });
            }
        }

        @Override // com.android.systemui.plugins.VolumeDialogController.Callbacks
        public final void onShowRequested(final int i, final boolean z, final int i2) {
            for (final Map.Entry entry : ((ConcurrentHashMap) this.mCallbackMap).entrySet()) {
                ((Handler) entry.getValue()).post(new Runnable() { // from class: com.android.systemui.volume.VolumeDialogControllerImpl.C.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        ((VolumeDialogController.Callbacks) entry.getKey()).onShowRequested(i, z, i2);
                    }
                });
            }
        }

        @Override // com.android.systemui.plugins.VolumeDialogController.Callbacks
        public final void onShowSafetyWarning(int i) {
            for (Map.Entry entry : ((ConcurrentHashMap) this.mCallbackMap).entrySet()) {
                ((Handler) entry.getValue()).post(new AnonymousClass2(entry, i, 2));
            }
        }

        @Override // com.android.systemui.plugins.VolumeDialogController.Callbacks
        public final void onShowSilentHint() {
            for (Map.Entry entry : ((ConcurrentHashMap) this.mCallbackMap).entrySet()) {
                ((Handler) entry.getValue()).post(new AnonymousClass5(entry, 3));
            }
        }

        @Override // com.android.systemui.plugins.VolumeDialogController.Callbacks
        public final void onShowVibrateHint() {
            for (Map.Entry entry : ((ConcurrentHashMap) this.mCallbackMap).entrySet()) {
                ((Handler) entry.getValue()).post(new AnonymousClass5(entry, 2));
            }
        }

        @Override // com.android.systemui.plugins.VolumeDialogController.Callbacks
        public final void onStateChanged(VolumeDialogController.State state) {
            System.currentTimeMillis();
            final VolumeDialogController.State copy = state.copy();
            for (final Map.Entry entry : ((ConcurrentHashMap) this.mCallbackMap).entrySet()) {
                ((Handler) entry.getValue()).post(new Runnable() { // from class: com.android.systemui.volume.VolumeDialogControllerImpl.C.3
                    @Override // java.lang.Runnable
                    public final void run() {
                        ((VolumeDialogController.Callbacks) entry.getKey()).onStateChanged(copy);
                    }
                });
            }
            String str = Events.TAG;
        }

        @Override // com.android.systemui.plugins.VolumeDialogController.Callbacks
        public final void onVolumeChangedFromKey() {
            for (Map.Entry entry : ((ConcurrentHashMap) this.mCallbackMap).entrySet()) {
                ((Handler) entry.getValue()).post(new AnonymousClass5(entry, 1));
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MediaSessionsCallbacks {
        public final boolean mVolumeAdjustmentForRemoteGroupSessions;
        public final HashMap mRemoteStreams = new HashMap();
        public int mNextStream = 100;

        public MediaSessionsCallbacks(Context context) {
            this.mVolumeAdjustmentForRemoteGroupSessions = context.getResources().getBoolean(R.bool.config_windowEnableCircularEmulatorDisplayOverlay);
        }

        public final void addStream(MediaSession.Token token, String str) {
            synchronized (this.mRemoteStreams) {
                try {
                    if (!this.mRemoteStreams.containsKey(token)) {
                        this.mRemoteStreams.put(token, Integer.valueOf(this.mNextStream));
                        Log.d(VolumeDialogControllerImpl.TAG, str + ": added stream " + this.mNextStream + " from token + " + token.toString());
                        this.mNextStream = this.mNextStream + 1;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public final void onRemoteRemoved(MediaSession.Token token) {
            if (showForSession(token)) {
                synchronized (this.mRemoteStreams) {
                    try {
                        if (!this.mRemoteStreams.containsKey(token)) {
                            Log.d(VolumeDialogControllerImpl.TAG, "onRemoteRemoved: stream doesn't exist, aborting remote removed for token:" + token.toString());
                            return;
                        }
                        int intValue = ((Integer) this.mRemoteStreams.get(token)).intValue();
                        VolumeDialogControllerImpl.this.mState.states.remove(intValue);
                        VolumeDialogControllerImpl volumeDialogControllerImpl = VolumeDialogControllerImpl.this;
                        if (volumeDialogControllerImpl.mState.activeStream == intValue) {
                            volumeDialogControllerImpl.updateActiveStreamW(-1);
                        }
                        VolumeDialogControllerImpl volumeDialogControllerImpl2 = VolumeDialogControllerImpl.this;
                        volumeDialogControllerImpl2.mCallbacks.onStateChanged(volumeDialogControllerImpl2.mState);
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        }

        public final boolean showForSession(MediaSession.Token token) {
            if (this.mVolumeAdjustmentForRemoteGroupSessions) {
                if (VolumeDialogControllerImpl.DEBUG) {
                    Log.d(VolumeDialogControllerImpl.TAG, "Volume adjustment for remote group sessions allowed, showForSession: true");
                }
                return true;
            }
            VolumeDialogControllerImpl volumeDialogControllerImpl = VolumeDialogControllerImpl.this;
            String packageName = new MediaController(volumeDialogControllerImpl.mContext, token).getPackageName();
            List<RoutingSessionInfo> routingSessions = volumeDialogControllerImpl.mRouter2Manager.getRoutingSessions(packageName);
            if (VolumeDialogControllerImpl.DEBUG) {
                Log.d(VolumeDialogControllerImpl.TAG, "Found " + routingSessions.size() + " routing sessions for package name " + packageName);
            }
            for (RoutingSessionInfo routingSessionInfo : routingSessions) {
                if (VolumeDialogControllerImpl.DEBUG) {
                    Log.d(VolumeDialogControllerImpl.TAG, "Found routingSessionInfo: " + routingSessionInfo);
                }
                if (!routingSessionInfo.isSystemSession() && routingSessionInfo.getVolumeHandling() != 0) {
                    return true;
                }
            }
            FragmentManagerViewModel$$ExternalSyntheticOutline0.m("No routing session for ", packageName, VolumeDialogControllerImpl.TAG);
            return false;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Receiver extends BroadcastReceiver {
        public Receiver() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            boolean z = false;
            if (action.equals("android.media.VOLUME_CHANGED_ACTION")) {
                int intExtra = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1);
                int intExtra2 = intent.getIntExtra("android.media.EXTRA_PREV_VOLUME_STREAM_VALUE", -1);
                if (D.BUG) {
                    Log.d(VolumeDialogControllerImpl.TAG, "onReceive VOLUME_CHANGED_ACTION stream=" + intExtra + " oldLevel=" + intExtra2);
                }
                if (intExtra != -1) {
                    z = VolumeDialogControllerImpl.this.onVolumeChangedW(intExtra, 0);
                }
            } else if (action.equals("android.media.STREAM_DEVICES_CHANGED_ACTION")) {
                int intExtra3 = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1);
                int intExtra4 = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_DEVICES", -1);
                int intExtra5 = intent.getIntExtra("android.media.EXTRA_PREV_VOLUME_STREAM_DEVICES", -1);
                if (D.BUG) {
                    LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(intExtra3, intExtra4, "onReceive STREAM_DEVICES_CHANGED_ACTION stream=", " devices=", " oldDevices="), intExtra5, VolumeDialogControllerImpl.TAG);
                }
                if (intExtra3 != -1) {
                    z = VolumeDialogControllerImpl.this.checkRoutedToBluetoothW(intExtra3) | VolumeDialogControllerImpl.this.onVolumeChangedW(intExtra3, 0);
                }
            } else if (action.equals("android.media.STREAM_MUTE_CHANGED_ACTION")) {
                int intExtra6 = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1);
                boolean booleanExtra = intent.getBooleanExtra("android.media.EXTRA_STREAM_VOLUME_MUTED", false);
                if (D.BUG) {
                    Log.d(VolumeDialogControllerImpl.TAG, "onReceive STREAM_MUTE_CHANGED_ACTION stream=" + intExtra6 + " muted=" + booleanExtra);
                }
                if (intExtra6 != -1) {
                    z = VolumeDialogControllerImpl.this.updateStreamMuteW(intExtra6, booleanExtra);
                }
            } else if (action.equals("android.os.action.ACTION_EFFECTS_SUPPRESSOR_CHANGED")) {
                if (D.BUG) {
                    Log.d(VolumeDialogControllerImpl.TAG, "onReceive ACTION_EFFECTS_SUPPRESSOR_CHANGED");
                }
                VolumeDialogControllerImpl volumeDialogControllerImpl = VolumeDialogControllerImpl.this;
                z = volumeDialogControllerImpl.updateEffectsSuppressorW(volumeDialogControllerImpl.mNoMan.getEffectsSuppressor());
            } else if (action.equals("android.intent.action.CONFIGURATION_CHANGED")) {
                if (D.BUG) {
                    Log.d(VolumeDialogControllerImpl.TAG, "onReceive ACTION_CONFIGURATION_CHANGED");
                }
                VolumeDialogControllerImpl.this.mCallbacks.onConfigurationChanged();
            } else if (action.equals("android.intent.action.SCREEN_OFF")) {
                if (D.BUG) {
                    Log.d(VolumeDialogControllerImpl.TAG, "onReceive ACTION_SCREEN_OFF");
                }
                VolumeDialogControllerImpl.this.mCallbacks.onScreenOff();
            } else if (action.equals("android.intent.action.CLOSE_SYSTEM_DIALOGS")) {
                if (D.BUG) {
                    Log.d(VolumeDialogControllerImpl.TAG, "onReceive ACTION_CLOSE_SYSTEM_DIALOGS");
                }
                VolumeDialogControllerImpl.this.mCallbacks.onDismissRequested(2);
            }
            if (z) {
                VolumeDialogControllerImpl volumeDialogControllerImpl2 = VolumeDialogControllerImpl.this;
                volumeDialogControllerImpl2.mCallbacks.onStateChanged(volumeDialogControllerImpl2.mState);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RingerModeObservers {
        public final RingerModeLiveData mRingerMode;
        public final RingerModeLiveData mRingerModeInternal;
        public final AnonymousClass1 mRingerModeObserver = new AnonymousClass1(this, 0);
        public final AnonymousClass1 mRingerModeInternalObserver = new AnonymousClass1(this, 1);

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.volume.VolumeDialogControllerImpl$RingerModeObservers$1, reason: invalid class name */
        public final class AnonymousClass1 implements Observer {
            public final /* synthetic */ int $r8$classId;
            public final /* synthetic */ RingerModeObservers this$1;

            public /* synthetic */ AnonymousClass1(RingerModeObservers ringerModeObservers, int i) {
                this.$r8$classId = i;
                this.this$1 = ringerModeObservers;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                switch (this.$r8$classId) {
                    case 0:
                        final Integer num = (Integer) obj;
                        final int i = 0;
                        VolumeDialogControllerImpl.this.mWorker.post(new Runnable() { // from class: com.android.systemui.volume.VolumeDialogControllerImpl$RingerModeObservers$1$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                switch (i) {
                                    case 0:
                                        VolumeDialogControllerImpl.RingerModeObservers.AnonymousClass1 anonymousClass1 = (VolumeDialogControllerImpl.RingerModeObservers.AnonymousClass1) this;
                                        Integer num2 = num;
                                        anonymousClass1.getClass();
                                        int intValue = num2.intValue();
                                        VolumeDialogControllerImpl.RingerModeObservers ringerModeObservers = anonymousClass1.this$1;
                                        boolean z = ringerModeObservers.mRingerMode.initialSticky;
                                        VolumeDialogControllerImpl volumeDialogControllerImpl = VolumeDialogControllerImpl.this;
                                        if (z) {
                                            volumeDialogControllerImpl.mState.ringerModeExternal = intValue;
                                        }
                                        if (D.BUG) {
                                            Log.d(VolumeDialogControllerImpl.TAG, "onChange ringer_mode rm=" + Util.ringerModeToString(intValue));
                                        }
                                        String str = VolumeDialogControllerImpl.TAG;
                                        VolumeDialogController.State state = volumeDialogControllerImpl.mState;
                                        if (intValue != state.ringerModeExternal) {
                                            state.ringerModeExternal = intValue;
                                            Events.writeEvent(12, num2);
                                            volumeDialogControllerImpl.mCallbacks.onStateChanged(volumeDialogControllerImpl.mState);
                                            break;
                                        }
                                        break;
                                    default:
                                        VolumeDialogControllerImpl.RingerModeObservers.AnonymousClass1 anonymousClass12 = (VolumeDialogControllerImpl.RingerModeObservers.AnonymousClass1) this;
                                        Integer num3 = num;
                                        anonymousClass12.getClass();
                                        int intValue2 = num3.intValue();
                                        VolumeDialogControllerImpl.RingerModeObservers ringerModeObservers2 = anonymousClass12.this$1;
                                        boolean z2 = ringerModeObservers2.mRingerModeInternal.initialSticky;
                                        VolumeDialogControllerImpl volumeDialogControllerImpl2 = VolumeDialogControllerImpl.this;
                                        if (z2) {
                                            volumeDialogControllerImpl2.mState.ringerModeInternal = intValue2;
                                        }
                                        if (D.BUG) {
                                            Log.d(VolumeDialogControllerImpl.TAG, "onChange internal_ringer_mode rm=" + Util.ringerModeToString(intValue2));
                                        }
                                        String str2 = VolumeDialogControllerImpl.TAG;
                                        if (volumeDialogControllerImpl2.updateRingerModeInternalW(intValue2)) {
                                            volumeDialogControllerImpl2.mCallbacks.onStateChanged(volumeDialogControllerImpl2.mState);
                                            break;
                                        }
                                        break;
                                }
                            }
                        });
                        break;
                    default:
                        final Integer num2 = (Integer) obj;
                        final int i2 = 1;
                        VolumeDialogControllerImpl.this.mWorker.post(new Runnable() { // from class: com.android.systemui.volume.VolumeDialogControllerImpl$RingerModeObservers$1$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                switch (i2) {
                                    case 0:
                                        VolumeDialogControllerImpl.RingerModeObservers.AnonymousClass1 anonymousClass1 = (VolumeDialogControllerImpl.RingerModeObservers.AnonymousClass1) this;
                                        Integer num22 = num2;
                                        anonymousClass1.getClass();
                                        int intValue = num22.intValue();
                                        VolumeDialogControllerImpl.RingerModeObservers ringerModeObservers = anonymousClass1.this$1;
                                        boolean z = ringerModeObservers.mRingerMode.initialSticky;
                                        VolumeDialogControllerImpl volumeDialogControllerImpl = VolumeDialogControllerImpl.this;
                                        if (z) {
                                            volumeDialogControllerImpl.mState.ringerModeExternal = intValue;
                                        }
                                        if (D.BUG) {
                                            Log.d(VolumeDialogControllerImpl.TAG, "onChange ringer_mode rm=" + Util.ringerModeToString(intValue));
                                        }
                                        String str = VolumeDialogControllerImpl.TAG;
                                        VolumeDialogController.State state = volumeDialogControllerImpl.mState;
                                        if (intValue != state.ringerModeExternal) {
                                            state.ringerModeExternal = intValue;
                                            Events.writeEvent(12, num22);
                                            volumeDialogControllerImpl.mCallbacks.onStateChanged(volumeDialogControllerImpl.mState);
                                            break;
                                        }
                                        break;
                                    default:
                                        VolumeDialogControllerImpl.RingerModeObservers.AnonymousClass1 anonymousClass12 = (VolumeDialogControllerImpl.RingerModeObservers.AnonymousClass1) this;
                                        Integer num3 = num2;
                                        anonymousClass12.getClass();
                                        int intValue2 = num3.intValue();
                                        VolumeDialogControllerImpl.RingerModeObservers ringerModeObservers2 = anonymousClass12.this$1;
                                        boolean z2 = ringerModeObservers2.mRingerModeInternal.initialSticky;
                                        VolumeDialogControllerImpl volumeDialogControllerImpl2 = VolumeDialogControllerImpl.this;
                                        if (z2) {
                                            volumeDialogControllerImpl2.mState.ringerModeInternal = intValue2;
                                        }
                                        if (D.BUG) {
                                            Log.d(VolumeDialogControllerImpl.TAG, "onChange internal_ringer_mode rm=" + Util.ringerModeToString(intValue2));
                                        }
                                        String str2 = VolumeDialogControllerImpl.TAG;
                                        if (volumeDialogControllerImpl2.updateRingerModeInternalW(intValue2)) {
                                            volumeDialogControllerImpl2.mCallbacks.onStateChanged(volumeDialogControllerImpl2.mState);
                                            break;
                                        }
                                        break;
                                }
                            }
                        });
                        break;
                }
            }
        }

        public RingerModeObservers(RingerModeLiveData ringerModeLiveData, RingerModeLiveData ringerModeLiveData2) {
            this.mRingerMode = ringerModeLiveData;
            this.mRingerModeInternal = ringerModeLiveData2;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SettingObserver extends ContentObserver {
        public final Uri ZEN_MODE_CONFIG_URI;
        public final Uri ZEN_MODE_URI;

        public SettingObserver(W w) {
            super(w);
            this.ZEN_MODE_URI = Settings.Global.getUriFor("zen_mode");
            this.ZEN_MODE_CONFIG_URI = Settings.Global.getUriFor("zen_mode_config_etag");
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z, Uri uri) {
            boolean updateZenModeW = this.ZEN_MODE_URI.equals(uri) ? VolumeDialogControllerImpl.this.updateZenModeW() : false;
            if (this.ZEN_MODE_CONFIG_URI.equals(uri)) {
                updateZenModeW |= VolumeDialogControllerImpl.this.updateZenConfig();
            }
            if (updateZenModeW) {
                VolumeDialogControllerImpl volumeDialogControllerImpl = VolumeDialogControllerImpl.this;
                volumeDialogControllerImpl.mCallbacks.onStateChanged(volumeDialogControllerImpl.mState);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class VC extends IVolumeController.Stub {
        public final String TAG = ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder(), VolumeDialogControllerImpl.TAG, ".VC");

        public VC() {
        }

        public final void dismiss() {
            if (D.BUG) {
                Log.d(this.TAG, "dismiss requested");
            }
            VolumeDialogControllerImpl.this.mWorker.obtainMessage(2, 2, 0).sendToTarget();
            VolumeDialogControllerImpl.this.mWorker.sendEmptyMessage(2);
        }

        public final void displayCsdWarning(int i, int i2) {
            if (D.BUG) {
                ExifInterface$$ExternalSyntheticOutline0.m("displayCsdWarning durMs=", this.TAG, i2);
            }
            VolumeDialogControllerImpl.this.mWorker.obtainMessage(17, i, i2).sendToTarget();
        }

        public final void displaySafeVolumeWarning(int i) {
            if (D.BUG) {
                Log.d(this.TAG, "displaySafeVolumeWarning " + com.android.settingslib.volume.Util.audioManagerFlagsToString(i));
            }
            VolumeDialogControllerImpl.this.mWorker.obtainMessage(14, i, 0).sendToTarget();
        }

        public final void masterMuteChanged(int i) {
            if (D.BUG) {
                Log.d(this.TAG, "masterMuteChanged");
            }
        }

        public final void setA11yMode(int i) {
            if (D.BUG) {
                ExifInterface$$ExternalSyntheticOutline0.m("setA11yMode to ", this.TAG, i);
            }
            if (i == 0) {
                VolumeDialogControllerImpl.this.mShowA11yStream = false;
            } else if (i != 1) {
                ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0.m("Invalid accessibility mode ", this.TAG, i);
            } else {
                VolumeDialogControllerImpl.this.mShowA11yStream = true;
            }
            VolumeDialogControllerImpl volumeDialogControllerImpl = VolumeDialogControllerImpl.this;
            volumeDialogControllerImpl.mWorker.obtainMessage(15, Boolean.valueOf(volumeDialogControllerImpl.mShowA11yStream)).sendToTarget();
        }

        public final void setLayoutDirection(int i) {
            if (D.BUG) {
                Log.d(this.TAG, "setLayoutDirection");
            }
            VolumeDialogControllerImpl.this.mWorker.obtainMessage(8, i, 0).sendToTarget();
        }

        public final void volumeChanged(int i, int i2) {
            if (D.BUG) {
                Log.d(this.TAG, "volumeChanged " + AudioSystem.streamToString(i) + " " + com.android.settingslib.volume.Util.audioManagerFlagsToString(i2));
            }
            VolumeDialogControllerImpl.this.mWorker.obtainMessage(1, i, i2).sendToTarget();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class W extends Handler {
        public W(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            MediaSession.Token token = null;
            switch (message.what) {
                case 1:
                    VolumeDialogControllerImpl.this.onVolumeChangedW(message.arg1, message.arg2);
                    return;
                case 2:
                    VolumeDialogControllerImpl.this.mCallbacks.onDismissRequested(message.arg1);
                    return;
                case 3:
                    VolumeDialogControllerImpl volumeDialogControllerImpl = VolumeDialogControllerImpl.this;
                    volumeDialogControllerImpl.getClass();
                    for (Integer num : VolumeDialogControllerImpl.STREAMS.keySet()) {
                        int intValue = num.intValue();
                        volumeDialogControllerImpl.updateStreamLevelW(intValue, volumeDialogControllerImpl.mAudio.getLastAudibleStreamVolume(intValue));
                        volumeDialogControllerImpl.streamStateW(intValue).levelMin = volumeDialogControllerImpl.mAudio.getStreamMinVolumeInt(intValue);
                        volumeDialogControllerImpl.streamStateW(intValue).levelMax = Math.max(1, volumeDialogControllerImpl.mAudio.getStreamMaxVolume(intValue));
                        volumeDialogControllerImpl.updateStreamMuteW(intValue, volumeDialogControllerImpl.mAudio.isStreamMute(intValue));
                        VolumeDialogController.StreamState streamStateW = volumeDialogControllerImpl.streamStateW(intValue);
                        streamStateW.muteSupported = volumeDialogControllerImpl.mAudio.isStreamMutableByUi(intValue);
                        streamStateW.name = ((Integer) VolumeDialogControllerImpl.STREAMS.get(num)).intValue();
                        volumeDialogControllerImpl.checkRoutedToBluetoothW(intValue);
                    }
                    Integer value = volumeDialogControllerImpl.mRingerModeObservers.mRingerMode.getValue();
                    int intValue2 = value.intValue();
                    VolumeDialogController.State state = volumeDialogControllerImpl.mState;
                    if (intValue2 != state.ringerModeExternal) {
                        state.ringerModeExternal = intValue2;
                        Events.writeEvent(12, value);
                    }
                    volumeDialogControllerImpl.updateZenModeW();
                    volumeDialogControllerImpl.updateZenConfig();
                    volumeDialogControllerImpl.updateEffectsSuppressorW(volumeDialogControllerImpl.mNoMan.getEffectsSuppressor());
                    volumeDialogControllerImpl.mCallbacks.onStateChanged(volumeDialogControllerImpl.mState);
                    return;
                case 4:
                    VolumeDialogControllerImpl volumeDialogControllerImpl2 = VolumeDialogControllerImpl.this;
                    int i = message.arg1;
                    if (message.arg2 != 0) {
                        volumeDialogControllerImpl2.mAudio.setRingerMode(i);
                        return;
                    } else {
                        volumeDialogControllerImpl2.mAudio.setRingerModeInternal(i);
                        return;
                    }
                case 5:
                    VolumeDialogControllerImpl volumeDialogControllerImpl3 = VolumeDialogControllerImpl.this;
                    int i2 = message.arg1;
                    volumeDialogControllerImpl3.getClass();
                    boolean z = D.BUG;
                    String str = VolumeDialogControllerImpl.TAG;
                    if (z) {
                        ExifInterface$$ExternalSyntheticOutline0.m("onSetZenModeW ", str, i2);
                    }
                    volumeDialogControllerImpl3.mNoMan.setZenMode(i2, null, str);
                    return;
                case 6:
                    VolumeDialogControllerImpl volumeDialogControllerImpl4 = VolumeDialogControllerImpl.this;
                    Condition condition = (Condition) message.obj;
                    volumeDialogControllerImpl4.mNoMan.setZenMode(volumeDialogControllerImpl4.mState.zenMode, condition != null ? condition.id : null, VolumeDialogControllerImpl.TAG);
                    return;
                case 7:
                    VolumeDialogControllerImpl.this.mAudio.adjustStreamVolume(message.arg1, message.arg2 != 0 ? -100 : 100, 0);
                    return;
                case 8:
                    VolumeDialogControllerImpl.this.mCallbacks.onLayoutDirectionChanged(message.arg1);
                    return;
                case 9:
                    VolumeDialogControllerImpl.this.mCallbacks.onConfigurationChanged();
                    return;
                case 10:
                    VolumeDialogControllerImpl volumeDialogControllerImpl5 = VolumeDialogControllerImpl.this;
                    int i3 = message.arg1;
                    int i4 = message.arg2;
                    volumeDialogControllerImpl5.getClass();
                    if (D.BUG) {
                        Log.d(VolumeDialogControllerImpl.TAG, "onSetStreamVolume " + i3 + " level=" + i4);
                    }
                    if (i3 < 100) {
                        volumeDialogControllerImpl5.mAudio.setStreamVolume(i3, i4, 0);
                        return;
                    }
                    MediaSessionsCallbacks mediaSessionsCallbacks = volumeDialogControllerImpl5.mMediaSessionsCallbacksW;
                    synchronized (mediaSessionsCallbacks.mRemoteStreams) {
                        try {
                            Iterator it = mediaSessionsCallbacks.mRemoteStreams.entrySet().iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    Map.Entry entry = (Map.Entry) it.next();
                                    if (((Integer) entry.getValue()).equals(Integer.valueOf(i3))) {
                                        token = (MediaSession.Token) entry.getKey();
                                    }
                                }
                            }
                        } finally {
                        }
                    }
                    if (token == null) {
                        RecordingInputConnection$$ExternalSyntheticOutline0.m("setStreamVolume: No token found for stream: ", VolumeDialogControllerImpl.TAG, i3);
                        return;
                    }
                    if (mediaSessionsCallbacks.showForSession(token)) {
                        MediaSessions.MediaControllerRecord mediaControllerRecord = (MediaSessions.MediaControllerRecord) VolumeDialogControllerImpl.this.mMediaSessions.mRecords.get(token);
                        String str2 = MediaSessions.TAG;
                        if (mediaControllerRecord == null) {
                            Log.w(str2, "setVolume: No record found for token " + token);
                            return;
                        }
                        if (com.android.settingslib.volume.D.BUG) {
                            ExifInterface$$ExternalSyntheticOutline0.m("Setting level to ", str2, i4);
                        }
                        mediaControllerRecord.controller.setVolumeTo(i4, 0);
                        return;
                    }
                    return;
                case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                    VolumeDialogControllerImpl volumeDialogControllerImpl6 = VolumeDialogControllerImpl.this;
                    if (volumeDialogControllerImpl6.updateActiveStreamW(message.arg1)) {
                        volumeDialogControllerImpl6.mCallbacks.onStateChanged(volumeDialogControllerImpl6.mState);
                        return;
                    }
                    return;
                case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                    VolumeDialogControllerImpl volumeDialogControllerImpl7 = VolumeDialogControllerImpl.this;
                    boolean z2 = message.arg1 != 0;
                    VolumeControllerAdapter volumeControllerAdapter = volumeDialogControllerImpl7.mVolumeControllerAdapter;
                    volumeControllerAdapter.getClass();
                    BuildersKt.launch$default(volumeControllerAdapter.coroutineScope, null, null, new VolumeControllerAdapter$notifyVolumeControllerVisible$1(volumeControllerAdapter, z2, null), 3);
                    if (z2 || !volumeDialogControllerImpl7.updateActiveStreamW(-1)) {
                        return;
                    }
                    volumeDialogControllerImpl7.mCallbacks.onStateChanged(volumeDialogControllerImpl7.mState);
                    return;
                case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                    VolumeDialogControllerImpl volumeDialogControllerImpl8 = VolumeDialogControllerImpl.this;
                    synchronized (volumeDialogControllerImpl8) {
                        VolumeDialogComponent volumeDialogComponent = volumeDialogControllerImpl8.mUserActivityListener;
                        if (volumeDialogComponent != null) {
                            volumeDialogComponent.mKeyguardViewMediator.userActivity();
                        }
                    }
                    return;
                case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                    VolumeDialogControllerImpl volumeDialogControllerImpl9 = VolumeDialogControllerImpl.this;
                    int i5 = message.arg1;
                    if (volumeDialogControllerImpl9.mShowSafetyWarning) {
                        volumeDialogControllerImpl9.mCallbacks.onShowSafetyWarning(i5);
                        return;
                    }
                    return;
                case 15:
                    VolumeDialogControllerImpl.this.mCallbacks.onAccessibilityModeChanged((Boolean) message.obj);
                    return;
                case 16:
                    VolumeDialogControllerImpl volumeDialogControllerImpl10 = VolumeDialogControllerImpl.this;
                    Boolean bool = (Boolean) message.obj;
                    bool.getClass();
                    CaptioningManager captioningManager = (CaptioningManager) volumeDialogControllerImpl10.mCaptioningManager.get();
                    if (captioningManager != null) {
                        volumeDialogControllerImpl10.mCallbacks.onCaptionComponentStateChanged(Boolean.valueOf(captioningManager.isSystemAudioCaptioningUiEnabled()), bool);
                        return;
                    } else {
                        Log.e(VolumeDialogControllerImpl.TAG, "onGetCaptionsComponentStateW(), null captioningManager");
                        return;
                    }
                case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                    VolumeDialogControllerImpl.this.mCallbacks.onShowCsdWarning(message.arg1, message.arg2);
                    return;
                case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                    VolumeDialogControllerImpl volumeDialogControllerImpl11 = VolumeDialogControllerImpl.this;
                    Boolean bool2 = (Boolean) message.obj;
                    bool2.getClass();
                    CaptioningManager captioningManager2 = (CaptioningManager) volumeDialogControllerImpl11.mCaptioningManager.get();
                    if (captioningManager2 != null) {
                        volumeDialogControllerImpl11.mCallbacks.onCaptionEnabledStateChanged(Boolean.valueOf(captioningManager2.isSystemAudioCaptioningEnabled()), bool2);
                        return;
                    } else {
                        Log.e(VolumeDialogControllerImpl.TAG, "onGetCaptionsEnabledStateW(), null captioningManager");
                        return;
                    }
                case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                    VolumeDialogControllerImpl volumeDialogControllerImpl12 = VolumeDialogControllerImpl.this;
                    boolean booleanValue = ((Boolean) message.obj).booleanValue();
                    CaptioningManager captioningManager3 = (CaptioningManager) volumeDialogControllerImpl12.mCaptioningManager.get();
                    if (captioningManager3 == null) {
                        Log.e(VolumeDialogControllerImpl.TAG, "onGetCaptionsEnabledStateW(), null captioningManager");
                        return;
                    } else {
                        captioningManager3.setSystemAudioCaptioningEnabled(booleanValue);
                        volumeDialogControllerImpl12.mCallbacks.onCaptionEnabledStateChanged(Boolean.valueOf(captioningManager3.isSystemAudioCaptioningEnabled()), Boolean.FALSE);
                        return;
                    }
                default:
                    return;
            }
        }
    }

    static {
        String logTag = Util.logTag(VolumeDialogControllerImpl.class);
        TAG = logTag;
        DEBUG = Log.isLoggable(logTag, 3);
        SONIFICIATION_VIBRATION_ATTRIBUTES = new AudioAttributes.Builder().setContentType(4).setUsage(13).build();
        ArrayMap arrayMap = new ArrayMap();
        STREAMS = arrayMap;
        arrayMap.put(4, Integer.valueOf(com.android.wm.shell.R.string.stream_alarm));
        arrayMap.put(8, Integer.valueOf(com.android.wm.shell.R.string.stream_dtmf));
        arrayMap.put(3, Integer.valueOf(com.android.wm.shell.R.string.stream_music));
        arrayMap.put(10, Integer.valueOf(com.android.wm.shell.R.string.stream_accessibility));
        arrayMap.put(5, Integer.valueOf(com.android.wm.shell.R.string.stream_notification));
        arrayMap.put(2, Integer.valueOf(com.android.wm.shell.R.string.stream_ring));
        arrayMap.put(1, Integer.valueOf(com.android.wm.shell.R.string.stream_system));
        arrayMap.put(7, Integer.valueOf(com.android.wm.shell.R.string.stream_system_enforced));
        arrayMap.put(9, Integer.valueOf(com.android.wm.shell.R.string.stream_tts));
        arrayMap.put(0, Integer.valueOf(com.android.wm.shell.R.string.stream_voice_call));
    }

    public VolumeDialogControllerImpl(Context context, BroadcastDispatcher broadcastDispatcher, RingerModeTrackerImpl ringerModeTrackerImpl, ThreadFactoryImpl threadFactoryImpl, AudioManager audioManager, NotificationManager notificationManager, VibratorHelper vibratorHelper, IAudioService iAudioService, VolumeControllerAdapter volumeControllerAdapter, AccessibilityManager accessibilityManager, PackageManager packageManager, WakefulnessLifecycle wakefulnessLifecycle, KeyguardManager keyguardManager, ActivityManager activityManager, UserTracker userTracker, DumpManager dumpManager, AudioSharingInteractor audioSharingInteractor, JavaAdapter javaAdapter) {
        BroadcastReceiver receiver = new Receiver();
        AtomicReference atomicReference = new AtomicReference();
        this.mCaptioningManager = atomicReference;
        this.mCallbacks = new C();
        VolumeDialogController.State state = new VolumeDialogController.State();
        this.mState = state;
        this.mDeviceInteractive = true;
        this.mInAudioSharing = false;
        VC vc = new VC();
        this.mVolumeController = vc;
        WakefulnessLifecycle.Observer observer = new WakefulnessLifecycle.Observer() { // from class: com.android.systemui.volume.VolumeDialogControllerImpl.1
            @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
            public final void onFinishedGoingToSleep$1() {
                VolumeDialogControllerImpl.this.mDeviceInteractive = false;
            }

            @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
            public final void onStartedWakingUp() {
                VolumeDialogControllerImpl.this.mDeviceInteractive = true;
            }
        };
        UserTracker.Callback callback = new UserTracker.Callback() { // from class: com.android.systemui.volume.VolumeDialogControllerImpl.2
            @Override // com.android.systemui.settings.UserTracker.Callback
            public final void onUserChanged(int i, Context context2) {
                VolumeDialogControllerImpl.this.mCaptioningManager.set((CaptioningManager) context2.getSystemService(CaptioningManager.class));
            }
        };
        this.mUserChangedCallback = callback;
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mPackageManager = packageManager;
        this.mWakefulnessLifecycle = wakefulnessLifecycle;
        Events.writeEvent(5, new Object[0]);
        HandlerThread handlerThread = new HandlerThread("VolumeDialogControllerImpl");
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        W w = new W(looper);
        this.mWorker = w;
        this.mRouter2Manager = MediaRouter2Manager.getInstance(applicationContext);
        MediaSessionsCallbacks mediaSessionsCallbacks = new MediaSessionsCallbacks(applicationContext);
        this.mMediaSessionsCallbacksW = mediaSessionsCallbacks;
        this.mMediaSessions = new MediaSessions(applicationContext, looper, mediaSessionsCallbacks);
        this.mAudio = audioManager;
        this.mNoMan = notificationManager;
        SettingObserver settingObserver = new SettingObserver(w);
        RingerModeLiveData ringerModeLiveData = ringerModeTrackerImpl.ringerMode;
        RingerModeLiveData ringerModeLiveData2 = ringerModeTrackerImpl.ringerModeInternal;
        RingerModeObservers ringerModeObservers = new RingerModeObservers(ringerModeLiveData, ringerModeLiveData2);
        this.mRingerModeObservers = ringerModeObservers;
        int intValue = ringerModeLiveData.getValue().intValue();
        if (intValue != -1) {
            state.ringerModeExternal = intValue;
        }
        ringerModeLiveData.observeForever(ringerModeObservers.mRingerModeObserver);
        int intValue2 = ringerModeLiveData2.getValue().intValue();
        if (intValue2 != -1) {
            state.ringerModeInternal = intValue2;
        }
        ringerModeLiveData2.observeForever(ringerModeObservers.mRingerModeInternalObserver);
        applicationContext.getContentResolver().registerContentObserver(settingObserver.ZEN_MODE_URI, false, settingObserver);
        applicationContext.getContentResolver().registerContentObserver(settingObserver.ZEN_MODE_CONFIG_URI, false, settingObserver);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.media.VOLUME_CHANGED_ACTION");
        intentFilter.addAction("android.media.STREAM_DEVICES_CHANGED_ACTION");
        intentFilter.addAction("android.media.STREAM_MUTE_CHANGED_ACTION");
        intentFilter.addAction("android.os.action.ACTION_EFFECTS_SUPPRESSOR_CHANGED");
        intentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        broadcastDispatcher.registerReceiverWithHandler(receiver, intentFilter, w);
        this.mVibrator = vibratorHelper;
        this.mHasVibrator = vibratorHelper.hasVibrator();
        this.mAudioService = iAudioService;
        this.mVolumeControllerAdapter = volumeControllerAdapter;
        this.mKeyguardManager = keyguardManager;
        this.mActivityManager = activityManager;
        this.mUserTracker = userTracker;
        UserTrackerImpl userTrackerImpl = (UserTrackerImpl) userTracker;
        userTrackerImpl.addCallback(callback, new HandlerExecutor(w));
        atomicReference.set((CaptioningManager) userTrackerImpl.getUserContext().getSystemService(CaptioningManager.class));
        dumpManager.getClass();
        DumpManager.registerDumpable$default(dumpManager, "VolumeDialogControllerImpl", this);
        vc.setA11yMode(accessibilityManager.isAccessibilityVolumeStreamActive() ? 1 : 0);
        wakefulnessLifecycle.mObservers.add(observer);
    }

    @Override // com.android.systemui.plugins.VolumeDialogController
    public final void addCallback(VolumeDialogController.Callbacks callbacks, Handler handler) {
        C c = this.mCallbacks;
        c.getClass();
        if (callbacks == null || handler == null) {
            throw new IllegalArgumentException();
        }
        c.mCallbackMap.put(callbacks, handler);
        callbacks.onAccessibilityModeChanged(Boolean.valueOf(this.mShowA11yStream));
    }

    public final boolean checkRoutedToBluetoothW(int i) {
        if (i != 3) {
            if (i == 0) {
                return updateStreamRoutedToBluetoothW(i, (this.mAudio.getDevicesForStream(0) & 536871008) != 0);
            }
            return false;
        }
        if (!this.mInAudioSharing && (this.mAudio.getDevicesForStream(3) & 536871808) == 0) {
            r0 = false;
        }
        return updateStreamRoutedToBluetoothW(i, r0);
    }

    @Override // com.android.systemui.Dumpable
    @NeverCompile
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("VolumeDialogControllerImpl state:");
        printWriter.print("  mVolumePolicy: ");
        printWriter.println(this.mVolumePolicy);
        printWriter.print("  mState: ");
        printWriter.println(this.mState.toString(4));
        printWriter.print("  mHasVibrator: ");
        printWriter.println(this.mHasVibrator);
        synchronized (this.mMediaSessionsCallbacksW.mRemoteStreams) {
            printWriter.print("  mRemoteStreams: ");
            printWriter.println(this.mMediaSessionsCallbacksW.mRemoteStreams.values());
        }
        printWriter.print("  mShowA11yStream: ");
        printWriter.println(this.mShowA11yStream);
        printWriter.println();
        MediaSessions mediaSessions = this.mMediaSessions;
        mediaSessions.getClass();
        printWriter.println("MediaSessions state:");
        printWriter.print("  mInit: ");
        printWriter.println(mediaSessions.mInit);
        printWriter.print("  mRecords.size: ");
        printWriter.println(mediaSessions.mRecords.size());
        Iterator it = mediaSessions.mRecords.values().iterator();
        int i = 0;
        while (it.hasNext()) {
            i++;
            MediaController mediaController = ((MediaSessions.MediaControllerRecord) it.next()).controller;
            StringBuilder m = MutableObjectList$$ExternalSyntheticOutline0.m("  Controller ", ": ", i);
            m.append(mediaController.getPackageName());
            printWriter.println(m.toString());
            Bundle extras = mediaController.getExtras();
            long flags = mediaController.getFlags();
            MediaMetadata metadata = mediaController.getMetadata();
            MediaController.PlaybackInfo playbackInfo = mediaController.getPlaybackInfo();
            PlaybackState playbackState = mediaController.getPlaybackState();
            List<MediaSession.QueueItem> queue = mediaController.getQueue();
            CharSequence queueTitle = mediaController.getQueueTitle();
            int ratingType = mediaController.getRatingType();
            PendingIntent sessionActivity = mediaController.getSessionActivity();
            printWriter.println("    PlaybackState: " + com.android.settingslib.volume.Util.playbackStateToString(playbackState));
            printWriter.println("    PlaybackInfo: " + com.android.settingslib.volume.Util.playbackInfoToString(playbackInfo));
            if (metadata != null) {
                printWriter.println("  MediaMetadata.desc=" + metadata.getDescription());
            }
            printWriter.println("    RatingType: " + ratingType);
            printWriter.println("    Flags: " + flags);
            if (extras != null) {
                printWriter.println("    Extras:");
                for (String str : extras.keySet()) {
                    StringBuilder m2 = ActivityResultRegistry$$ExternalSyntheticOutline0.m("      ", str, "=");
                    m2.append(extras.get(str));
                    printWriter.println(m2.toString());
                }
            }
            if (queueTitle != null) {
                printWriter.println("    QueueTitle: " + ((Object) queueTitle));
            }
            if (queue != null && !queue.isEmpty()) {
                printWriter.println("    Queue:");
                Iterator<MediaSession.QueueItem> it2 = queue.iterator();
                while (it2.hasNext()) {
                    printWriter.println("      " + it2.next());
                }
            }
            if (playbackInfo != null) {
                printWriter.println("    sessionActivity: " + sessionActivity);
            }
        }
    }

    @Override // com.android.systemui.plugins.VolumeDialogController
    public final AudioManager getAudioManager() {
        return this.mAudio;
    }

    @Override // com.android.systemui.plugins.VolumeDialogController
    public final void getCaptionsComponentState(boolean z) {
        this.mWorker.obtainMessage(16, Boolean.valueOf(z)).sendToTarget();
    }

    @Override // com.android.systemui.plugins.VolumeDialogController
    public final void getCaptionsEnabledState(boolean z) {
        this.mWorker.obtainMessage(18, Boolean.valueOf(z)).sendToTarget();
    }

    @Override // com.android.systemui.plugins.VolumeDialogController
    public final void getState() {
        this.mWorker.sendEmptyMessage(3);
    }

    @Override // com.android.systemui.plugins.VolumeDialogController
    public final boolean hasVibrator() {
        return this.mHasVibrator;
    }

    @Override // com.android.systemui.plugins.VolumeDialogController
    public final void notifyVisible(boolean z) {
        this.mWorker.obtainMessage(12, z ? 1 : 0, 0).sendToTarget();
    }

    public final boolean onVolumeChangedW(int i, int i2) {
        boolean shouldShowUI = shouldShowUI(i2);
        boolean z = (i2 & 4096) != 0;
        boolean z2 = (i2 & 2048) != 0;
        boolean z3 = (i2 & 128) != 0;
        boolean updateActiveStreamW = shouldShowUI ? updateActiveStreamW(i) : false;
        int lastAudibleStreamVolume = this.mAudio.getLastAudibleStreamVolume(i);
        boolean updateStreamLevelW = updateActiveStreamW | updateStreamLevelW(i, lastAudibleStreamVolume) | checkRoutedToBluetoothW(shouldShowUI ? 3 : i);
        C c = this.mCallbacks;
        if (updateStreamLevelW) {
            c.onStateChanged(this.mState);
        }
        if (shouldShowUI) {
            this.mCallbacks.onShowRequested(1, this.mKeyguardManager.isKeyguardLocked(), this.mActivityManager.getLockTaskModeState());
        }
        if (z2) {
            c.onShowVibrateHint();
        }
        if (z3) {
            c.onShowSilentHint();
        }
        if (updateStreamLevelW && z) {
            Events.writeEvent(4, Integer.valueOf(i), Integer.valueOf(lastAudibleStreamVolume));
            c.onVolumeChangedFromKey();
        }
        return updateStreamLevelW;
    }

    @Override // com.android.systemui.plugins.VolumeDialogController
    public final void removeCallback(VolumeDialogController.Callbacks callbacks) {
        this.mCallbacks.mCallbackMap.remove(callbacks);
    }

    @Override // com.android.systemui.plugins.VolumeDialogController
    public final void scheduleTouchFeedback() {
        this.mLastToggledRingerOn = System.currentTimeMillis();
    }

    @Override // com.android.systemui.plugins.VolumeDialogController
    public final void setActiveStream(int i) {
        this.mWorker.obtainMessage(11, i, 0).sendToTarget();
    }

    @Override // com.android.systemui.plugins.VolumeDialogController
    public final void setCaptionsEnabledState(boolean z) {
        this.mWorker.obtainMessage(19, Boolean.valueOf(z)).sendToTarget();
    }

    @Override // com.android.systemui.plugins.VolumeDialogController
    public final void setRingerMode(int i, boolean z) {
        this.mWorker.obtainMessage(4, i, z ? 1 : 0).sendToTarget();
    }

    @Override // com.android.systemui.plugins.VolumeDialogController
    public final void setStreamVolume(int i, int i2) {
        this.mWorker.obtainMessage(10, i, i2).sendToTarget();
    }

    public final void setVolumePolicy(VolumePolicy volumePolicy) {
        this.mVolumePolicy = volumePolicy;
        if (volumePolicy == null) {
            return;
        }
        try {
            this.mAudio.setVolumePolicy(volumePolicy);
        } catch (NoSuchMethodError unused) {
            Log.w(TAG, "No volume policy api");
        }
    }

    public final boolean shouldShowUI(int i) {
        int i2 = this.mWakefulnessLifecycle.mWakefulness;
        return (i2 == 0 || i2 == 3 || !this.mDeviceInteractive || (i & 1) == 0 || !this.mShowVolumeDialog) ? false : true;
    }

    public final VolumeDialogController.StreamState streamStateW(int i) {
        VolumeDialogController.State state = this.mState;
        VolumeDialogController.StreamState streamState = (VolumeDialogController.StreamState) state.states.get(i);
        if (streamState != null) {
            return streamState;
        }
        VolumeDialogController.StreamState streamState2 = new VolumeDialogController.StreamState();
        state.states.put(i, streamState2);
        return streamState2;
    }

    public final boolean updateActiveStreamW(int i) {
        VolumeDialogController.State state = this.mState;
        if (i == state.activeStream) {
            return false;
        }
        state.activeStream = i;
        Events.writeEvent(2, Integer.valueOf(i));
        boolean z = D.BUG;
        String str = TAG;
        if (z) {
            ExifInterface$$ExternalSyntheticOutline0.m("updateActiveStreamW ", str, i);
        }
        if (i >= 100) {
            i = -1;
        }
        if (z) {
            ExifInterface$$ExternalSyntheticOutline0.m("forceVolumeControlStream ", str, i);
        }
        this.mAudio.forceVolumeControlStream(i);
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x002e, code lost:
    
        if (r3.length() > 0) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean updateEffectsSuppressorW(android.content.ComponentName r4) {
        /*
            r3 = this;
            com.android.systemui.plugins.VolumeDialogController$State r0 = r3.mState
            android.content.ComponentName r1 = r0.effectsSuppressor
            boolean r1 = java.util.Objects.equals(r1, r4)
            r2 = 0
            if (r1 == 0) goto Lc
            return r2
        Lc:
            r0.effectsSuppressor = r4
            android.content.pm.PackageManager r3 = r3.mPackageManager
            if (r4 != 0) goto L14
            r3 = 0
            goto L32
        L14:
            java.lang.String r4 = r4.getPackageName()
            android.content.pm.ApplicationInfo r1 = r3.getApplicationInfo(r4, r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L31
            java.lang.CharSequence r3 = r1.loadLabel(r3)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L31
            java.lang.String r1 = ""
            java.lang.String r3 = java.util.Objects.toString(r3, r1)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L31
            java.lang.String r3 = r3.trim()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L31
            int r1 = r3.length()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L31
            if (r1 <= 0) goto L31
            goto L32
        L31:
            r3 = r4
        L32:
            r0.effectsSuppressorName = r3
            android.content.ComponentName r3 = r0.effectsSuppressor
            java.lang.String r4 = r0.effectsSuppressorName
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r4}
            r4 = 14
            com.android.systemui.volume.Events.writeEvent(r4, r3)
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.volume.VolumeDialogControllerImpl.updateEffectsSuppressorW(android.content.ComponentName):boolean");
    }

    public final boolean updateRingerModeInternalW(int i) {
        VolumeDialogController.State state = this.mState;
        if (i == state.ringerModeInternal) {
            return false;
        }
        state.ringerModeInternal = i;
        Events.writeEvent(11, Integer.valueOf(i));
        if (state.ringerModeInternal != 2 || System.currentTimeMillis() - this.mLastToggledRingerOn >= 1000) {
            return true;
        }
        try {
            this.mAudioService.playSoundEffect(5, ((UserTrackerImpl) this.mUserTracker).getUserId());
            return true;
        } catch (RemoteException unused) {
            return true;
        }
    }

    public final boolean updateStreamLevelW(int i, int i2) {
        VolumeDialogController.StreamState streamStateW = streamStateW(i);
        if (streamStateW.level == i2) {
            return false;
        }
        streamStateW.level = i2;
        if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4) {
            Events.writeEvent(10, Integer.valueOf(i), Integer.valueOf(i2));
        }
        return true;
    }

    public final boolean updateStreamMuteW(int i, boolean z) {
        VolumeDialogController.StreamState streamStateW = streamStateW(i);
        if (streamStateW.muted == z) {
            return false;
        }
        streamStateW.muted = z;
        if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4) {
            Events.writeEvent(15, Integer.valueOf(i), Boolean.valueOf(z));
        }
        if (z && (i == 2 || i == 5)) {
            updateRingerModeInternalW(this.mRingerModeObservers.mRingerModeInternal.getValue().intValue());
        }
        return true;
    }

    public final boolean updateStreamRoutedToBluetoothW(int i, boolean z) {
        VolumeDialogController.StreamState streamStateW = streamStateW(i);
        if (streamStateW.routedToBluetooth == z) {
            return false;
        }
        streamStateW.routedToBluetooth = z;
        if (!D.BUG) {
            return true;
        }
        Log.d(TAG, "updateStreamRoutedToBluetoothW stream=" + i + " routedToBluetooth=" + z);
        return true;
    }

    public final boolean updateZenConfig() {
        NotificationManager.Policy consolidatedNotificationPolicy = this.mNoMan.getConsolidatedNotificationPolicy();
        int i = consolidatedNotificationPolicy.priorityCategories;
        boolean z = (i & 32) == 0;
        boolean z2 = (i & 64) == 0;
        boolean z3 = (i & 128) == 0;
        boolean areAllPriorityOnlyRingerSoundsMuted = ZenModeConfig.areAllPriorityOnlyRingerSoundsMuted(consolidatedNotificationPolicy);
        VolumeDialogController.State state = this.mState;
        if (state.disallowAlarms == z && state.disallowMedia == z2 && state.disallowRinger == areAllPriorityOnlyRingerSoundsMuted && state.disallowSystem == z3) {
            return false;
        }
        state.disallowAlarms = z;
        state.disallowMedia = z2;
        state.disallowSystem = z3;
        state.disallowRinger = areAllPriorityOnlyRingerSoundsMuted;
        StringBuilder m = BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m("disallowAlarms=", " disallowMedia=", " disallowSystem=", z, z2);
        m.append(z3);
        m.append(" disallowRinger=");
        m.append(areAllPriorityOnlyRingerSoundsMuted);
        Events.writeEvent(17, m.toString());
        return true;
    }

    public final boolean updateZenModeW() {
        int i = Settings.Global.getInt(this.mContext.getContentResolver(), "zen_mode", 0);
        VolumeDialogController.State state = this.mState;
        if (state.zenMode == i) {
            return false;
        }
        state.zenMode = i;
        Events.writeEvent(13, Integer.valueOf(i));
        return true;
    }

    @Override // com.android.systemui.plugins.VolumeDialogController
    public final void userActivity() {
        W w = this.mWorker;
        w.removeMessages(13);
        w.sendEmptyMessage(13);
    }

    @Override // com.android.systemui.plugins.VolumeDialogController
    public final void vibrate(VibrationEffect vibrationEffect) {
        this.mVibrator.vibrate(vibrationEffect, SONIFICIATION_VIBRATION_ATTRIBUTES);
    }
}
