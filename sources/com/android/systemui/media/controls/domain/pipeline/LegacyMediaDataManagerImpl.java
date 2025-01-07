package com.android.systemui.media.controls.domain.pipeline;

import android.R;
import android.app.PendingIntent;
import android.app.StatusBarManager;
import android.app.smartspace.SmartspaceAction;
import android.app.smartspace.SmartspaceConfig;
import android.app.smartspace.SmartspaceManager;
import android.app.smartspace.SmartspaceSession;
import android.app.smartspace.SmartspaceTarget;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Icon;
import android.media.MediaDescription;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Bundle;
import android.os.Trace;
import android.os.UserHandle;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import androidx.fragment.app.FragmentManagerViewModel$$ExternalSyntheticOutline0;
import com.android.app.tracing.TraceUtilsKt;
import com.android.internal.logging.InstanceId;
import com.android.keyguard.ActiveUnlockConfig$$ExternalSyntheticOutline0;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.Dumpable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.android.systemui.media.controls.domain.pipeline.LegacyMediaDataManagerImpl;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.domain.resume.MediaResumeListener;
import com.android.systemui.media.controls.domain.resume.MediaResumeListener$getResumeAction$1;
import com.android.systemui.media.controls.shared.MediaLogger;
import com.android.systemui.media.controls.shared.model.MediaAction;
import com.android.systemui.media.controls.shared.model.MediaButton;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.shared.model.SmartspaceMediaData;
import com.android.systemui.media.controls.shared.model.SmartspaceMediaDataProvider;
import com.android.systemui.media.controls.ui.view.MediaViewHolder;
import com.android.systemui.media.controls.util.MediaControllerFactory;
import com.android.systemui.media.controls.util.MediaFlags;
import com.android.systemui.media.controls.util.MediaUiEvent;
import com.android.systemui.media.controls.util.MediaUiEventLogger;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.android.systemui.statusbar.NotificationMediaManager;
import com.android.systemui.tuner.TunerService;
import com.android.systemui.util.Assert;
import com.android.systemui.util.Utils;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.concurrency.ThreadFactoryImpl;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import dagger.Lazy;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LegacyMediaDataManagerImpl implements Dumpable, BcSmartspaceDataPlugin.SmartspaceTargetListener, MediaDataManager {
    public static final int MAX_NOTIFICATION_ACTIONS;
    public boolean allowMediaRecommendations;
    public final CoroutineScope applicationScope;
    public final CoroutineDispatcher backgroundDispatcher;
    public final ExecutorImpl backgroundExecutor;
    public final Context context;
    public final DelayableExecutor foregroundExecutor;
    public final Set internalListeners;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    public final MediaUiEventLogger logger;
    public final CoroutineDispatcher mainDispatcher;
    public final MediaControllerFactory mediaControllerFactory;
    public final LegacyMediaDataFilterImpl mediaDataFilter;
    public final Lazy mediaDataLoader;
    public final MediaDeviceManager mediaDeviceManager;
    public final Map mediaEntries;
    public final MediaFlags mediaFlags;
    public final MediaLogger mediaLogger;
    public SmartspaceMediaData smartspaceMediaData;
    public final SmartspaceMediaDataProvider smartspaceMediaDataProvider;
    private SmartspaceSession smartspaceSession;
    public final SystemClock systemClock;
    public final int themeText;
    public boolean useMediaResumption;
    public final boolean useQsMediaPlayer;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.media.controls.domain.pipeline.LegacyMediaDataManagerImpl$1, reason: invalid class name */
    final class AnonymousClass1 extends Lambda implements Function2 {
        public AnonymousClass1() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            boolean booleanValue = ((Boolean) obj2).booleanValue();
            LegacyMediaDataManagerImpl.this.setInactive((String) obj, booleanValue, false);
            return Unit.INSTANCE;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.media.controls.domain.pipeline.LegacyMediaDataManagerImpl$3, reason: invalid class name */
    final class AnonymousClass3 extends Lambda implements Function1 {
        public AnonymousClass3() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            String str = (String) obj;
            LegacyMediaDataManagerImpl legacyMediaDataManagerImpl = LegacyMediaDataManagerImpl.this;
            int i = LegacyMediaDataManagerImpl.MAX_NOTIFICATION_ACTIONS;
            legacyMediaDataManagerImpl.getClass();
            Log.d("MediaDataManager", "session destroyed for ".concat(str));
            MediaData mediaData = (MediaData) legacyMediaDataManagerImpl.mediaEntries.remove(str);
            if (mediaData != null) {
                MediaData copy$default = MediaData.copy$default(mediaData, null, null, null, null, null, null, false, null, false, null, 0L, 0L, null, 0, 1073739775);
                boolean z = copy$default.token != null;
                MediaButton mediaButton = copy$default.semanticActions;
                if (z && mediaButton != null) {
                    FragmentManagerViewModel$$ExternalSyntheticOutline0.m("Notification removed but using session actions ", str, "MediaDataManager");
                    legacyMediaDataManagerImpl.mediaEntries.put(str, copy$default);
                    legacyMediaDataManagerImpl.notifyMediaDataLoaded$1(str, str, copy$default);
                } else if (mediaButton == null) {
                    FragmentManagerViewModel$$ExternalSyntheticOutline0.m("Session destroyed but using notification actions ", str, "MediaDataManager");
                    legacyMediaDataManagerImpl.mediaEntries.put(str, copy$default);
                    legacyMediaDataManagerImpl.notifyMediaDataLoaded$1(str, str, copy$default);
                } else {
                    boolean z2 = copy$default.active;
                    MediaUiEventLogger mediaUiEventLogger = legacyMediaDataManagerImpl.logger;
                    String str2 = copy$default.packageName;
                    int i2 = copy$default.appUid;
                    if (!z2 || legacyMediaDataManagerImpl.isAbleToResume$1(copy$default)) {
                        MediaFlags mediaFlags = legacyMediaDataManagerImpl.mediaFlags;
                        mediaFlags.getClass();
                        UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
                        mediaFlags.featureFlags.getClass();
                        if (legacyMediaDataManagerImpl.isAbleToResume$1(copy$default)) {
                            Log.d("MediaDataManager", "Notification (false) and/or session (" + z + ") gone for inactive player " + str);
                            legacyMediaDataManagerImpl.convertToResumePlayer$1(copy$default, str);
                        } else {
                            Log.d("MediaDataManager", "Removing player " + str);
                            LegacyMediaDataManagerImpl.notifyMediaDataRemoved$default(legacyMediaDataManagerImpl, str);
                            mediaUiEventLogger.logMediaRemoved(i2, str2, copy$default.instanceId);
                        }
                    } else {
                        Log.d("MediaDataManager", "Removing still-active player " + str);
                        LegacyMediaDataManagerImpl.notifyMediaDataRemoved$default(legacyMediaDataManagerImpl, str);
                        mediaUiEventLogger.logMediaRemoved(i2, str2, copy$default.instanceId);
                    }
                }
            }
            return Unit.INSTANCE;
        }
    }

    static {
        Set set = MediaViewHolder.controlsIds;
        MAX_NOTIFICATION_ACTIONS = MediaViewHolder.genericButtonIds.size();
    }

    public LegacyMediaDataManagerImpl(Context context, ThreadFactoryImpl threadFactoryImpl, CoroutineDispatcher coroutineDispatcher, Executor executor, DelayableExecutor delayableExecutor, CoroutineDispatcher coroutineDispatcher2, CoroutineScope coroutineScope, MediaControllerFactory mediaControllerFactory, DumpManager dumpManager, BroadcastDispatcher broadcastDispatcher, MediaTimeoutListener mediaTimeoutListener, final MediaResumeListener mediaResumeListener, MediaSessionBasedFilter mediaSessionBasedFilter, MediaDeviceManager mediaDeviceManager, MediaDataCombineLatest mediaDataCombineLatest, LegacyMediaDataFilterImpl legacyMediaDataFilterImpl, SmartspaceMediaDataProvider smartspaceMediaDataProvider, SystemClock systemClock, TunerService tunerService, MediaFlags mediaFlags, MediaUiEventLogger mediaUiEventLogger, SmartspaceManager smartspaceManager, KeyguardUpdateMonitor keyguardUpdateMonitor, Lazy lazy, MediaLogger mediaLogger) {
        ExecutorImpl buildExecutorOnNewThread = threadFactoryImpl.buildExecutorOnNewThread("MediaDataManager");
        boolean useMediaResumption = Utils.useMediaResumption(context);
        boolean useQsMediaPlayer = Utils.useQsMediaPlayer(context);
        this.context = context;
        this.backgroundExecutor = buildExecutorOnNewThread;
        this.backgroundDispatcher = coroutineDispatcher;
        this.foregroundExecutor = delayableExecutor;
        this.mainDispatcher = coroutineDispatcher2;
        this.applicationScope = coroutineScope;
        this.mediaControllerFactory = mediaControllerFactory;
        this.mediaDeviceManager = mediaDeviceManager;
        this.mediaDataFilter = legacyMediaDataFilterImpl;
        this.smartspaceMediaDataProvider = smartspaceMediaDataProvider;
        this.useMediaResumption = useMediaResumption;
        this.useQsMediaPlayer = useQsMediaPlayer;
        this.systemClock = systemClock;
        this.mediaFlags = mediaFlags;
        this.logger = mediaUiEventLogger;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mediaDataLoader = lazy;
        this.mediaLogger = mediaLogger;
        this.themeText = com.android.settingslib.Utils.getColorAttr(R.attr.textColorPrimary, context).getDefaultColor();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        this.internalListeners = linkedHashSet;
        Map synchronizedMap = Collections.synchronizedMap(new LinkedHashMap());
        Intrinsics.checkNotNull(synchronizedMap);
        this.mediaEntries = synchronizedMap;
        this.smartspaceMediaData = LegacyMediaDataManagerImplKt.EMPTY_SMARTSPACE_MEDIA_DATA;
        this.allowMediaRecommendations = Utils.useQsMediaPlayer(context) && Settings.Secure.getInt(context.getContentResolver(), "qs_media_recommend", 1) > 0;
        context.getResources().getDimensionPixelSize(R.dimen.config_minScrollbarTouchTarget);
        context.getResources().getDimensionPixelSize(com.android.wm.shell.R.dimen.qs_media_session_height_expanded);
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.android.systemui.media.controls.domain.pipeline.LegacyMediaDataManagerImpl$appChangeReceiver$1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                String[] stringArrayExtra;
                String encodedSchemeSpecificPart;
                String action = intent.getAction();
                if (action != null) {
                    int hashCode = action.hashCode();
                    if (hashCode != -1001645458) {
                        if (hashCode != -757780528) {
                            if (hashCode != 525384130 || !action.equals("android.intent.action.PACKAGE_REMOVED")) {
                                return;
                            }
                        } else if (!action.equals("android.intent.action.PACKAGE_RESTARTED")) {
                            return;
                        }
                        Uri data = intent.getData();
                        if (data == null || (encodedSchemeSpecificPart = data.getEncodedSchemeSpecificPart()) == null) {
                            return;
                        }
                        LegacyMediaDataManagerImpl.access$removeAllForPackage(LegacyMediaDataManagerImpl.this, encodedSchemeSpecificPart);
                        return;
                    }
                    if (action.equals("android.intent.action.PACKAGES_SUSPENDED") && (stringArrayExtra = intent.getStringArrayExtra("android.intent.extra.changed_package_list")) != null) {
                        LegacyMediaDataManagerImpl legacyMediaDataManagerImpl = LegacyMediaDataManagerImpl.this;
                        for (String str : stringArrayExtra) {
                            Intrinsics.checkNotNull(str);
                            LegacyMediaDataManagerImpl.access$removeAllForPackage(legacyMediaDataManagerImpl, str);
                        }
                    }
                }
            }
        };
        DumpManager.registerDumpable$default(dumpManager, "MediaDataManager", this);
        linkedHashSet.add(mediaTimeoutListener);
        linkedHashSet.add(mediaResumeListener);
        linkedHashSet.add(mediaSessionBasedFilter);
        mediaSessionBasedFilter.listeners.add(mediaDeviceManager);
        mediaSessionBasedFilter.listeners.add(mediaDataCombineLatest);
        mediaDeviceManager.listeners.add(mediaDataCombineLatest);
        mediaDataCombineLatest.listeners.add(legacyMediaDataFilterImpl);
        mediaTimeoutListener.timeoutCallback = new AnonymousClass1();
        mediaTimeoutListener.stateCallback = new Function2() { // from class: com.android.systemui.media.controls.domain.pipeline.LegacyMediaDataManagerImpl.2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                String str;
                MediaData copy$default;
                String str2 = (String) obj;
                PlaybackState playbackState = (PlaybackState) obj2;
                LegacyMediaDataManagerImpl legacyMediaDataManagerImpl = LegacyMediaDataManagerImpl.this;
                MediaData mediaData = (MediaData) legacyMediaDataManagerImpl.mediaEntries.get(str2);
                if (mediaData != null) {
                    MediaSession.Token token = mediaData.token;
                    if (token == null) {
                        Log.d("MediaDataManager", "State updated, but token was null");
                    } else {
                        MediaController create = legacyMediaDataManagerImpl.mediaControllerFactory.create(token);
                        UserHandle userHandle = new UserHandle(mediaData.userId);
                        legacyMediaDataManagerImpl.mediaFlags.getClass();
                        String str3 = mediaData.packageName;
                        MediaButton createActionsFromState = !StatusBarManager.useMediaSessionActionsForApp(str3, userHandle) ? null : MediaActionsKt.createActionsFromState(legacyMediaDataManagerImpl.context, str3, create);
                        if (createActionsFromState != null) {
                            MediaButton mediaButton = createActionsFromState;
                            str = "MediaDataManager";
                            copy$default = MediaData.copy$default(mediaData, null, null, mediaButton, null, null, null, false, null, false, Boolean.valueOf(NotificationMediaManager.isPlayingState(playbackState.getState())), 0L, 0L, null, 0, 1072692735);
                        } else {
                            str = "MediaDataManager";
                            copy$default = MediaData.copy$default(mediaData, null, null, null, null, null, null, false, null, false, Boolean.valueOf(NotificationMediaManager.isPlayingState(playbackState.getState())), 0L, 0L, null, 0, 1072693247);
                        }
                        Log.d(str, "State updated outside of notification");
                        legacyMediaDataManagerImpl.onMediaDataLoaded(str2, str2, copy$default);
                    }
                }
                return Unit.INSTANCE;
            }
        };
        mediaTimeoutListener.sessionCallback = new AnonymousClass3();
        mediaResumeListener.mediaDataManager = this;
        mediaResumeListener.tunerService.addTunable(new TunerService.Tunable() { // from class: com.android.systemui.media.controls.domain.resume.MediaResumeListener$setManager$1
            @Override // com.android.systemui.tuner.TunerService.Tunable
            public final void onTuningChanged(String str, String str2) {
                MediaResumeListener mediaResumeListener2 = MediaResumeListener.this;
                boolean useMediaResumption2 = Utils.useMediaResumption(mediaResumeListener2.context);
                mediaResumeListener2.useMediaResumption = useMediaResumption2;
                LegacyMediaDataManagerImpl legacyMediaDataManagerImpl = mediaResumeListener2.mediaDataManager;
                if (legacyMediaDataManagerImpl == null) {
                    legacyMediaDataManagerImpl = null;
                }
                if (legacyMediaDataManagerImpl.useMediaResumption == useMediaResumption2) {
                    return;
                }
                legacyMediaDataManagerImpl.useMediaResumption = useMediaResumption2;
                if (useMediaResumption2) {
                    return;
                }
                Map map = legacyMediaDataManagerImpl.mediaEntries;
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (Map.Entry entry : map.entrySet()) {
                    if (!((MediaData) entry.getValue()).active) {
                        linkedHashMap.put(entry.getKey(), entry.getValue());
                    }
                }
                for (Map.Entry entry2 : linkedHashMap.entrySet()) {
                    legacyMediaDataManagerImpl.mediaEntries.remove(entry2.getKey());
                    LegacyMediaDataManagerImpl.notifyMediaDataRemoved$default(legacyMediaDataManagerImpl, (String) entry2.getKey());
                    legacyMediaDataManagerImpl.logger.logMediaRemoved(((MediaData) entry2.getValue()).appUid, ((MediaData) entry2.getValue()).packageName, ((MediaData) entry2.getValue()).instanceId);
                }
            }
        }, "qs_media_resumption");
        legacyMediaDataFilterImpl.mediaDataManager = this;
        BroadcastDispatcher.registerReceiver$default(broadcastDispatcher, broadcastReceiver, new IntentFilter("android.intent.action.PACKAGES_SUSPENDED"), null, UserHandle.ALL, 0, 48);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addAction("android.intent.action.PACKAGE_RESTARTED");
        intentFilter.addDataScheme("package");
        context.registerReceiver(broadcastReceiver, intentFilter);
        smartspaceMediaDataProvider.registerListener(this);
        SmartspaceSession createSmartspaceSession = smartspaceManager != null ? smartspaceManager.createSmartspaceSession(new SmartspaceConfig.Builder(context, BcSmartspaceDataPlugin.UI_SURFACE_MEDIA).build()) : null;
        this.smartspaceSession = createSmartspaceSession;
        if (createSmartspaceSession != null) {
            createSmartspaceSession.addOnTargetsAvailableListener(executor, new SmartspaceSession.OnTargetsAvailableListener() { // from class: com.android.systemui.media.controls.domain.pipeline.LegacyMediaDataManagerImpl$4$1
                public final void onTargetsAvailable(List list) {
                    LegacyMediaDataManagerImpl.this.smartspaceMediaDataProvider.onTargetsAvailable(list);
                }
            });
        }
        SmartspaceSession smartspaceSession = this.smartspaceSession;
        if (smartspaceSession != null) {
            smartspaceSession.requestSmartspaceUpdate();
        }
        tunerService.addTunable(new TunerService.Tunable() { // from class: com.android.systemui.media.controls.domain.pipeline.LegacyMediaDataManagerImpl.6
            @Override // com.android.systemui.tuner.TunerService.Tunable
            public final void onTuningChanged(String str, String str2) {
                LegacyMediaDataManagerImpl legacyMediaDataManagerImpl = LegacyMediaDataManagerImpl.this;
                Context context2 = legacyMediaDataManagerImpl.context;
                MediaData mediaData = LegacyMediaDataManagerImplKt.LOADING;
                boolean z = Utils.useQsMediaPlayer(context2) && Settings.Secure.getInt(context2.getContentResolver(), "qs_media_recommend", 1) > 0;
                legacyMediaDataManagerImpl.allowMediaRecommendations = z;
                if (z) {
                    return;
                }
                legacyMediaDataManagerImpl.dismissSmartspaceRecommendation(0L, legacyMediaDataManagerImpl.smartspaceMediaData.targetId);
            }
        }, "qs_media_recommend");
    }

    public static final void access$removeAllForPackage(LegacyMediaDataManagerImpl legacyMediaDataManagerImpl, String str) {
        legacyMediaDataManagerImpl.getClass();
        Assert.isMainThread();
        Map map = legacyMediaDataManagerImpl.mediaEntries;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : map.entrySet()) {
            if (Intrinsics.areEqual(((MediaData) entry.getValue()).packageName, str)) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        Iterator it = linkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            removeEntry$default(legacyMediaDataManagerImpl, (String) ((Map.Entry) it.next()).getKey(), false, 6);
        }
    }

    public static void notifyMediaDataRemoved$default(LegacyMediaDataManagerImpl legacyMediaDataManagerImpl, String str) {
        Iterator it = legacyMediaDataManagerImpl.internalListeners.iterator();
        while (it.hasNext()) {
            ((MediaDataManager.Listener) it.next()).onMediaDataRemoved(str, false);
        }
    }

    public static void removeEntry$default(LegacyMediaDataManagerImpl legacyMediaDataManagerImpl, String str, boolean z, int i) {
        if ((i & 4) != 0) {
            z = false;
        }
        MediaData mediaData = (MediaData) legacyMediaDataManagerImpl.mediaEntries.remove(str);
        if (mediaData != null) {
            InstanceId instanceId = mediaData.instanceId;
            legacyMediaDataManagerImpl.logger.logMediaRemoved(mediaData.appUid, mediaData.packageName, instanceId);
        }
        Iterator it = legacyMediaDataManagerImpl.internalListeners.iterator();
        while (it.hasNext()) {
            ((MediaDataManager.Listener) it.next()).onMediaDataRemoved(str, z);
        }
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager
    public final void addListener(MediaDataManager.Listener listener) {
        this.mediaDataFilter._listeners.add(listener);
    }

    public final void addResumptionControls(int i, MediaDescription mediaDescription, MediaResumeListener$getResumeAction$1 mediaResumeListener$getResumeAction$1, MediaSession.Token token, String str, PendingIntent pendingIntent, String str2) {
        int i2;
        if (!this.mediaEntries.containsKey(str2)) {
            MediaUiEventLogger mediaUiEventLogger = this.logger;
            InstanceId newInstanceId = mediaUiEventLogger.instanceIdSequence.newInstanceId();
            try {
                ApplicationInfo applicationInfo = this.context.getPackageManager().getApplicationInfo(str2, 0);
                Integer valueOf = applicationInfo != null ? Integer.valueOf(applicationInfo.uid) : null;
                Intrinsics.checkNotNull(valueOf);
                i2 = valueOf.intValue();
            } catch (PackageManager.NameNotFoundException e) {
                Log.w("MediaDataManager", "Could not get app UID for ".concat(str2), e);
                i2 = -1;
            }
            MediaData mediaData = LegacyMediaDataManagerImplKt.LOADING;
            ((SystemClockImpl) this.systemClock).getClass();
            this.mediaEntries.put(str2, MediaData.copy$default(mediaData, null, null, null, str2, null, null, false, mediaResumeListener$getResumeAction$1, true, null, 0L, System.currentTimeMillis(), newInstanceId, i2, 1014463487));
            if (this.mediaEntries.size() == 1) {
                mediaUiEventLogger.logger.logWithInstanceId(MediaUiEvent.MEDIA_CAROUSEL_SINGLE_PLAYER, i2, str2, newInstanceId);
            } else if (this.mediaEntries.size() == 2) {
                mediaUiEventLogger.logger.logWithInstanceId(MediaUiEvent.MEDIA_CAROUSEL_MULTIPLE_PLAYERS, i2, str2, newInstanceId);
            }
            mediaUiEventLogger.logger.logWithInstanceId(MediaUiEvent.RESUME_MEDIA_ADDED, i2, str2, newInstanceId);
        }
        BuildersKt.launch$default(this.applicationScope, null, null, new LegacyMediaDataManagerImpl$addResumptionControls$1(i, pendingIntent, mediaDescription, token, this, mediaResumeListener$getResumeAction$1, str, str2, null), 3);
    }

    public final void convertToResumePlayer$1(MediaData mediaData, String str) {
        long j;
        Log.d("MediaDataManager", "Converting " + str + " to resume");
        CharSequence charSequence = mediaData.song;
        MediaUiEventLogger mediaUiEventLogger = this.logger;
        String str2 = mediaData.packageName;
        if (charSequence == null || StringsKt__StringsJVMKt.isBlank(charSequence)) {
            Log.e("MediaDataManager", "Description incomplete");
            notifyMediaDataRemoved$default(this, str);
            mediaUiEventLogger.logMediaRemoved(mediaData.appUid, str2, mediaData.instanceId);
            return;
        }
        Runnable runnable = mediaData.resumeAction;
        MediaAction mediaAction = runnable != null ? new MediaAction(Icon.createWithResource(this.context, com.android.wm.shell.R.drawable.ic_media_play).setTint(this.themeText).loadDrawable(this.context), runnable, this.context.getString(com.android.wm.shell.R.string.controls_media_resume), this.context.getDrawable(com.android.wm.shell.R.drawable.ic_media_play_container), null) : null;
        if (mediaAction != null) {
            Collections.singletonList(mediaAction);
        } else {
            EmptyList emptyList = EmptyList.INSTANCE;
        }
        Intent launchIntentForPackage = this.context.getPackageManager().getLaunchIntentForPackage(str2);
        PendingIntent activity = launchIntentForPackage != null ? PendingIntent.getActivity(this.context, 0, launchIntentForPackage, 67108864) : null;
        if (mediaData.active) {
            ((SystemClockImpl) this.systemClock).getClass();
            j = android.os.SystemClock.elapsedRealtime();
        } else {
            j = mediaData.lastActive;
        }
        MediaData copy$default = MediaData.copy$default(mediaData, EmptyList.INSTANCE, Collections.singletonList(0), new MediaButton(mediaAction), null, activity, null, false, null, false, Boolean.FALSE, j, 0L, null, 0, 1066247295);
        boolean z = this.mediaEntries.put(str2, copy$default) == null;
        Log.d("MediaDataManager", "migrating? " + z + " from " + str + " -> " + str2);
        if (z) {
            notifyMediaDataLoaded$1(str2, str, copy$default);
        } else {
            notifyMediaDataRemoved$default(this, str);
            notifyMediaDataLoaded$1(str2, str2, copy$default);
        }
        mediaUiEventLogger.logger.logWithInstanceId(MediaUiEvent.ACTIVE_TO_RESUME, copy$default.appUid, str2, copy$default.instanceId);
        Map map = this.mediaEntries;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : map.entrySet()) {
            if (((MediaData) entry.getValue()).resumption) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        int size = linkedHashMap.size();
        if (size > 5) {
            for (Pair pair : CollectionsKt.sortedWith(MapsKt.toList(linkedHashMap), new LegacyMediaDataManagerImpl$convertToResumePlayer$$inlined$sortedBy$1()).subList(0, size - 5)) {
                String str3 = (String) pair.component1();
                MediaData mediaData2 = (MediaData) pair.component2();
                FragmentManagerViewModel$$ExternalSyntheticOutline0.m("Removing excess control ", str3, "MediaDataManager");
                this.mediaEntries.remove(str3);
                notifyMediaDataRemoved$default(this, str3);
                mediaUiEventLogger.logMediaRemoved(mediaData2.appUid, mediaData2.packageName, mediaData2.instanceId);
            }
        }
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager
    public final boolean dismissMediaData(final String str, long j, final boolean z) {
        boolean z2 = this.mediaEntries.get(str) != null;
        this.backgroundExecutor.execute(new Runnable() { // from class: com.android.systemui.media.controls.domain.pipeline.LegacyMediaDataManagerImpl$dismissMediaData$1
            @Override // java.lang.Runnable
            public final void run() {
                MediaSession.Token token;
                MediaData mediaData = (MediaData) LegacyMediaDataManagerImpl.this.mediaEntries.get(str);
                if (mediaData != null) {
                    LegacyMediaDataManagerImpl legacyMediaDataManagerImpl = LegacyMediaDataManagerImpl.this;
                    if (mediaData.playbackLocation != 0 || (token = mediaData.token) == null) {
                        return;
                    }
                    legacyMediaDataManagerImpl.mediaControllerFactory.create(token).getTransportControls().stop();
                }
            }
        });
        this.foregroundExecutor.executeDelayed(new Runnable() { // from class: com.android.systemui.media.controls.domain.pipeline.LegacyMediaDataManagerImpl$dismissMediaData$2
            @Override // java.lang.Runnable
            public final void run() {
                LegacyMediaDataManagerImpl.removeEntry$default(LegacyMediaDataManagerImpl.this, str, z, 2);
            }
        }, j);
        return z2;
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager
    public final void dismissSmartspaceRecommendation(long j, String str) {
        if (Intrinsics.areEqual(this.smartspaceMediaData.targetId, str) && this.smartspaceMediaData.isValid()) {
            Log.d("MediaDataManager", "Dismissing Smartspace media target");
            SmartspaceMediaData smartspaceMediaData = this.smartspaceMediaData;
            if (smartspaceMediaData.isActive) {
                this.smartspaceMediaData = SmartspaceMediaData.copy$default(LegacyMediaDataManagerImplKt.EMPTY_SMARTSPACE_MEDIA_DATA, smartspaceMediaData.targetId, null, 0L, smartspaceMediaData.instanceId, 0L, 894);
            }
            this.foregroundExecutor.executeDelayed(new Runnable() { // from class: com.android.systemui.media.controls.domain.pipeline.LegacyMediaDataManagerImpl$dismissSmartspaceRecommendation$1
                @Override // java.lang.Runnable
                public final void run() {
                    LegacyMediaDataManagerImpl legacyMediaDataManagerImpl = LegacyMediaDataManagerImpl.this;
                    legacyMediaDataManagerImpl.notifySmartspaceMediaDataRemoved$1(legacyMediaDataManagerImpl.smartspaceMediaData.targetId, true);
                }
            }, j);
        }
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("internalListeners: " + this.internalListeners);
        printWriter.println("externalListeners: " + CollectionsKt.toSet(this.mediaDataFilter._listeners));
        printWriter.println("mediaEntries: " + this.mediaEntries);
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("useMediaResumption: ", this.useMediaResumption, printWriter);
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("allowMediaRecommendations: ", this.allowMediaRecommendations, printWriter);
        this.mediaDeviceManager.dump(printWriter);
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager
    public final boolean hasActiveMedia() {
        return this.mediaDataFilter.hasActiveMedia();
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager
    public final boolean hasActiveMediaOrRecommendation() {
        LegacyMediaDataFilterImpl legacyMediaDataFilterImpl = this.mediaDataFilter;
        LinkedHashMap linkedHashMap = legacyMediaDataFilterImpl.userEntries;
        if (!linkedHashMap.isEmpty()) {
            Iterator it = linkedHashMap.entrySet().iterator();
            while (it.hasNext()) {
                if (((MediaData) ((Map.Entry) it.next()).getValue()).active) {
                    break;
                }
            }
        }
        SmartspaceMediaData smartspaceMediaData = legacyMediaDataFilterImpl.smartspaceMediaData;
        return smartspaceMediaData.isActive && (smartspaceMediaData.isValid() || legacyMediaDataFilterImpl.reactivatedKey != null);
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager
    public final boolean hasAnyMedia() {
        return !this.mediaDataFilter.userEntries.isEmpty();
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager
    public final boolean hasAnyMediaOrRecommendation() {
        LegacyMediaDataFilterImpl legacyMediaDataFilterImpl = this.mediaDataFilter;
        legacyMediaDataFilterImpl.mediaFlags.isPersistentSsCardEnabled();
        SmartspaceMediaData smartspaceMediaData = legacyMediaDataFilterImpl.smartspaceMediaData;
        return !legacyMediaDataFilterImpl.userEntries.isEmpty() || (smartspaceMediaData.isActive && smartspaceMediaData.isValid());
    }

    public final boolean isAbleToResume$1(MediaData mediaData) {
        boolean z;
        if (mediaData.playbackLocation == 0) {
            z = true;
        } else {
            MediaFlags mediaFlags = this.mediaFlags;
            mediaFlags.getClass();
            UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
            mediaFlags.featureFlags.getClass();
            z = false;
        }
        return this.useMediaResumption && mediaData.resumeAction != null && z;
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager
    public final boolean isRecommendationActive() {
        return this.smartspaceMediaData.isActive;
    }

    public final void notifyMediaDataLoaded$1(String str, String str2, MediaData mediaData) {
        Iterator it = this.internalListeners.iterator();
        while (it.hasNext()) {
            MediaDataManager.Listener.onMediaDataLoaded$default((MediaDataManager.Listener) it.next(), str, str2, mediaData, false, 0, false, 56);
        }
    }

    public final void notifySmartspaceMediaDataRemoved$1(String str, boolean z) {
        Iterator it = this.internalListeners.iterator();
        while (it.hasNext()) {
            ((MediaDataManager.Listener) it.next()).onSmartspaceMediaDataRemoved(str, z);
        }
    }

    public final void onMediaDataLoaded(String str, String str2, MediaData mediaData) {
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("MediaDataManager#onMediaDataLoaded");
        }
        try {
            Assert.isMainThread();
            if (this.mediaEntries.containsKey(str)) {
                this.mediaEntries.put(str, mediaData);
                notifyMediaDataLoaded$1(str, str2, mediaData);
            }
        } finally {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        }
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager
    public final void onNotificationAdded(StatusBarNotification statusBarNotification, String str) {
        boolean z;
        boolean z2;
        if (!this.useQsMediaPlayer || !statusBarNotification.getNotification().isMediaNotification()) {
            onNotificationRemoved(str);
            return;
        }
        Assert.isMainThread();
        String packageName = statusBarNotification.getPackageName();
        String str2 = this.mediaEntries.containsKey(str) ? str : this.mediaEntries.containsKey(packageName) ? packageName : null;
        if (str2 == null) {
            InstanceId newInstanceId = this.logger.instanceIdSequence.newInstanceId();
            MediaData mediaData = LegacyMediaDataManagerImplKt.LOADING;
            String packageName2 = statusBarNotification.getPackageName();
            ((SystemClockImpl) this.systemClock).getClass();
            long currentTimeMillis = System.currentTimeMillis();
            Intrinsics.checkNotNull(packageName2);
            this.mediaEntries.put(str, MediaData.copy$default(mediaData, null, null, null, packageName2, null, null, false, null, false, null, 0L, currentTimeMillis, newInstanceId, 0, 1048574975));
            z2 = false;
            z = true;
        } else {
            if (str2.equals(str)) {
                z = false;
            } else {
                Object remove = this.mediaEntries.remove(str2);
                Intrinsics.checkNotNull(remove);
                this.mediaEntries.put(str, (MediaData) remove);
                z = true;
            }
            z2 = z;
        }
        BuildersKt.launch$default(this.applicationScope, null, null, new LegacyMediaDataManagerImpl$loadMediaData$1(statusBarNotification, this, str, str2, null, z, z2), 3);
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager
    public final void onNotificationRemoved(String str) {
        Assert.isMainThread();
        MediaData mediaData = (MediaData) this.mediaEntries.remove(str);
        if (mediaData == null) {
            return;
        }
        boolean isUserInLockdown = this.keyguardUpdateMonitor.isUserInLockdown(mediaData.userId);
        MediaUiEventLogger mediaUiEventLogger = this.logger;
        String str2 = mediaData.packageName;
        int i = mediaData.appUid;
        if (isUserInLockdown) {
            mediaUiEventLogger.logMediaRemoved(i, str2, mediaData.instanceId);
            return;
        }
        if (isAbleToResume$1(mediaData)) {
            convertToResumePlayer$1(mediaData, str);
            return;
        }
        MediaFlags mediaFlags = this.mediaFlags;
        mediaFlags.getClass();
        UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
        mediaFlags.featureFlags.getClass();
        notifyMediaDataRemoved$default(this, str);
        mediaUiEventLogger.logMediaRemoved(i, str2, mediaData.instanceId);
    }

    @Override // com.android.systemui.plugins.BcSmartspaceDataPlugin.SmartspaceTargetListener
    public final void onSmartspaceTargetsUpdated(List list) {
        String string;
        String str;
        Bundle extras;
        if (!this.allowMediaRecommendations) {
            Log.d("MediaDataManager", "Smartspace recommendation is disabled in Settings.");
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (obj instanceof SmartspaceTarget) {
                arrayList.add(obj);
            }
        }
        int size = arrayList.size();
        MediaFlags mediaFlags = this.mediaFlags;
        if (size == 0) {
            if (this.smartspaceMediaData.isActive) {
                Log.d("MediaDataManager", "Set Smartspace media to be inactive for the data update");
                mediaFlags.isPersistentSsCardEnabled();
                SmartspaceMediaData smartspaceMediaData = LegacyMediaDataManagerImplKt.EMPTY_SMARTSPACE_MEDIA_DATA;
                SmartspaceMediaData smartspaceMediaData2 = this.smartspaceMediaData;
                SmartspaceMediaData copy$default = SmartspaceMediaData.copy$default(smartspaceMediaData, smartspaceMediaData2.targetId, null, 0L, smartspaceMediaData2.instanceId, 0L, 894);
                this.smartspaceMediaData = copy$default;
                notifySmartspaceMediaDataRemoved$1(copy$default.targetId, false);
                return;
            }
            return;
        }
        if (size != 1) {
            Log.wtf("MediaDataManager", "More than 1 Smartspace Media Update. Resetting the status...");
            notifySmartspaceMediaDataRemoved$1(this.smartspaceMediaData.targetId, false);
            this.smartspaceMediaData = LegacyMediaDataManagerImplKt.EMPTY_SMARTSPACE_MEDIA_DATA;
            return;
        }
        SmartspaceTarget smartspaceTarget = (SmartspaceTarget) arrayList.get(0);
        if (Intrinsics.areEqual(this.smartspaceMediaData.targetId, smartspaceTarget.getSmartspaceTargetId())) {
            return;
        }
        Log.d("MediaDataManager", "Forwarding Smartspace media update.");
        SmartspaceAction baseAction = smartspaceTarget.getBaseAction();
        Intent intent = (baseAction == null || (extras = baseAction.getExtras()) == null) ? null : (Intent) extras.getParcelable("dismiss_intent");
        mediaFlags.isPersistentSsCardEnabled();
        List iconGrid = smartspaceTarget.getIconGrid();
        if (iconGrid.isEmpty()) {
            Log.w("MediaDataManager", "Empty or null media recommendation list.");
        } else {
            Iterator it = iconGrid.iterator();
            while (it.hasNext()) {
                Bundle extras2 = ((SmartspaceAction) it.next()).getExtras();
                if (extras2 != null && (string = extras2.getString("package_name")) != null) {
                    str = string;
                    break;
                }
            }
            Log.w("MediaDataManager", "No valid package name is provided.");
        }
        str = null;
        MediaUiEventLogger mediaUiEventLogger = this.logger;
        SmartspaceMediaData smartspaceMediaData3 = str != null ? new SmartspaceMediaData(smartspaceTarget.getSmartspaceTargetId(), true, str, smartspaceTarget.getBaseAction(), smartspaceTarget.getIconGrid(), intent, smartspaceTarget.getCreationTimeMillis(), mediaUiEventLogger.instanceIdSequence.newInstanceId(), smartspaceTarget.getExpiryTimeMillis(), 512) : SmartspaceMediaData.copy$default(LegacyMediaDataManagerImplKt.EMPTY_SMARTSPACE_MEDIA_DATA, smartspaceTarget.getSmartspaceTargetId(), intent, smartspaceTarget.getCreationTimeMillis(), mediaUiEventLogger.instanceIdSequence.newInstanceId(), smartspaceTarget.getExpiryTimeMillis(), 540);
        this.smartspaceMediaData = smartspaceMediaData3;
        Iterator it2 = this.internalListeners.iterator();
        while (it2.hasNext()) {
            ((MediaDataManager.Listener) it2.next()).onSmartspaceMediaDataLoaded(smartspaceMediaData3.targetId, smartspaceMediaData3, false);
        }
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager
    public final void onSwipeToDismiss() {
        LegacyMediaDataFilterImpl legacyMediaDataFilterImpl = this.mediaDataFilter;
        legacyMediaDataFilterImpl.getClass();
        Log.d("MediaDataFilter", "Media carousel swiped away");
        Iterator it = CollectionsKt.toSet(legacyMediaDataFilterImpl.userEntries.keySet()).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            String str = (String) it.next();
            LegacyMediaDataManagerImpl legacyMediaDataManagerImpl = legacyMediaDataFilterImpl.mediaDataManager;
            if (legacyMediaDataManagerImpl != null) {
                r3 = legacyMediaDataManagerImpl;
            }
            Intrinsics.checkNotNull(str);
            r3.setInactive(str, true, true);
        }
        SmartspaceMediaData smartspaceMediaData = legacyMediaDataFilterImpl.smartspaceMediaData;
        if (smartspaceMediaData.isActive) {
            Intent intent = smartspaceMediaData.dismissIntent;
            if (intent == null) {
                Log.w("MediaDataFilter", "Cannot create dismiss action click action: extras missing dismiss_intent.");
            } else {
                ComponentName component = intent.getComponent();
                if (Intrinsics.areEqual(component != null ? component.getClassName() : null, "com.google.android.apps.gsa.staticplugins.opa.smartspace.ExportedSmartspaceTrampolineActivity")) {
                    legacyMediaDataFilterImpl.context.startActivity(intent);
                } else {
                    legacyMediaDataFilterImpl.broadcastSender.sendBroadcast(intent);
                }
            }
            legacyMediaDataFilterImpl.mediaFlags.isPersistentSsCardEnabled();
            SmartspaceMediaData smartspaceMediaData2 = LegacyMediaDataManagerImplKt.EMPTY_SMARTSPACE_MEDIA_DATA;
            SmartspaceMediaData smartspaceMediaData3 = legacyMediaDataFilterImpl.smartspaceMediaData;
            SmartspaceMediaData copy$default = SmartspaceMediaData.copy$default(smartspaceMediaData2, smartspaceMediaData3.targetId, null, 0L, smartspaceMediaData3.instanceId, 0L, 894);
            legacyMediaDataFilterImpl.smartspaceMediaData = copy$default;
            LegacyMediaDataManagerImpl legacyMediaDataManagerImpl2 = legacyMediaDataFilterImpl.mediaDataManager;
            (legacyMediaDataManagerImpl2 != null ? legacyMediaDataManagerImpl2 : null).dismissSmartspaceRecommendation(0L, copy$default.targetId);
        }
    }

    @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager
    public final void removeListener(MediaDataManager.Listener listener) {
        this.mediaDataFilter._listeners.remove(listener);
    }

    public final void setInactive(String str, boolean z, boolean z2) {
        MediaData mediaData = (MediaData) this.mediaEntries.get(str);
        if (mediaData != null) {
            if (z && !z2) {
                this.logger.logger.logWithInstanceId(MediaUiEvent.MEDIA_TIMEOUT, mediaData.appUid, mediaData.packageName, mediaData.instanceId);
            }
            boolean z3 = mediaData.active;
            boolean z4 = !z;
            if (z3 == z4 && !z2) {
                if (mediaData.resumption) {
                    Log.d("MediaDataManager", "timing out resume player ".concat(str));
                    dismissMediaData(str, 0L, false);
                    return;
                }
                return;
            }
            if (z3) {
                ((SystemClockImpl) this.systemClock).getClass();
                mediaData.lastActive = android.os.SystemClock.elapsedRealtime();
            }
            mediaData.active = z4;
            Log.d("MediaDataManager", "Updating " + str + " timedOut: " + z);
            onMediaDataLoaded(str, str, mediaData);
        }
        if (str.equals(this.smartspaceMediaData.targetId)) {
            Log.d("MediaDataManager", "smartspace card expired");
            dismissSmartspaceRecommendation(0L, str);
        }
    }

    public final void setResumeAction(String str, Runnable runnable) {
        MediaData mediaData = (MediaData) this.mediaEntries.get(str);
        if (mediaData != null) {
            mediaData.resumeAction = runnable;
            mediaData.hasCheckedForResume = true;
        }
    }
}
