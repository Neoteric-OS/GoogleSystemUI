package com.android.systemui.media.controls.domain.pipeline;

import android.R;
import android.app.PendingIntent;
import android.app.smartspace.SmartspaceManager;
import android.app.smartspace.SmartspaceSession;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.media.session.MediaSession;
import android.net.Uri;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import com.android.internal.logging.InstanceId;
import com.android.keyguard.ActiveUnlockConfig$$ExternalSyntheticOutline0;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.CoreStartable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.android.systemui.media.controls.data.repository.MediaDataRepository;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.shared.MediaLogger;
import com.android.systemui.media.controls.shared.model.MediaAction;
import com.android.systemui.media.controls.shared.model.MediaButton;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.shared.model.MediaDeviceData;
import com.android.systemui.media.controls.shared.model.SmartspaceMediaData;
import com.android.systemui.media.controls.shared.model.SmartspaceMediaDataProvider;
import com.android.systemui.media.controls.util.MediaControllerFactory;
import com.android.systemui.media.controls.util.MediaFlags;
import com.android.systemui.media.controls.util.MediaUiEvent;
import com.android.systemui.media.controls.util.MediaUiEventLogger;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.android.systemui.util.Assert;
import com.android.systemui.util.Utils;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.concurrency.ThreadFactoryImpl;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import dagger.Lazy;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaDataProcessor implements CoreStartable, BcSmartspaceDataPlugin.SmartspaceTargetListener {
    public final ActivityStarter activityStarter;
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
    public final Lazy mediaDataLoader;
    public final MediaDataRepository mediaDataRepository;
    public final MediaFlags mediaFlags;
    public final MediaLogger mediaLogger;
    public final SecureSettings secureSettings;
    private SmartspaceSession smartspaceSession;
    public final SystemClock systemClock;
    public final int themeText;
    public final boolean useMediaResumption;
    public final boolean useQsMediaPlayer;

    public MediaDataProcessor(Context context, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, ThreadFactoryImpl threadFactoryImpl, Executor executor, DelayableExecutor delayableExecutor, CoroutineDispatcher coroutineDispatcher2, MediaControllerFactory mediaControllerFactory, DumpManager dumpManager, BroadcastDispatcher broadcastDispatcher, ActivityStarter activityStarter, SmartspaceMediaDataProvider smartspaceMediaDataProvider, SystemClock systemClock, SecureSettings secureSettings, MediaFlags mediaFlags, MediaUiEventLogger mediaUiEventLogger, SmartspaceManager smartspaceManager, KeyguardUpdateMonitor keyguardUpdateMonitor, MediaDataRepository mediaDataRepository, Lazy lazy, MediaLogger mediaLogger) {
        ExecutorImpl buildExecutorOnNewThread = threadFactoryImpl.buildExecutorOnNewThread("MediaDataProcessor");
        boolean useMediaResumption = Utils.useMediaResumption(context);
        boolean useQsMediaPlayer = Utils.useQsMediaPlayer(context);
        this.context = context;
        this.applicationScope = coroutineScope;
        this.backgroundDispatcher = coroutineDispatcher;
        this.backgroundExecutor = buildExecutorOnNewThread;
        this.foregroundExecutor = delayableExecutor;
        this.mainDispatcher = coroutineDispatcher2;
        this.mediaControllerFactory = mediaControllerFactory;
        this.activityStarter = activityStarter;
        this.useMediaResumption = useMediaResumption;
        this.useQsMediaPlayer = useQsMediaPlayer;
        this.systemClock = systemClock;
        this.mediaFlags = mediaFlags;
        this.logger = mediaUiEventLogger;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mediaDataRepository = mediaDataRepository;
        this.mediaDataLoader = lazy;
        this.mediaLogger = mediaLogger;
        this.themeText = com.android.settingslib.Utils.getColorAttr(R.attr.textColorPrimary, context).getDefaultColor();
        this.internalListeners = new LinkedHashSet();
        context.getResources().getDimensionPixelSize(R.dimen.config_minScrollbarTouchTarget);
        context.getResources().getDimensionPixelSize(com.android.wm.shell.R.dimen.qs_media_session_height_expanded);
        new BroadcastReceiver() { // from class: com.android.systemui.media.controls.domain.pipeline.MediaDataProcessor$appChangeReceiver$1
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
                        MediaDataProcessor.access$removeAllForPackage(MediaDataProcessor.this, encodedSchemeSpecificPart);
                        return;
                    }
                    if (action.equals("android.intent.action.PACKAGES_SUSPENDED") && (stringArrayExtra = intent.getStringArrayExtra("android.intent.extra.changed_package_list")) != null) {
                        MediaDataProcessor mediaDataProcessor = MediaDataProcessor.this;
                        for (String str : stringArrayExtra) {
                            Intrinsics.checkNotNull(str);
                            MediaDataProcessor.access$removeAllForPackage(mediaDataProcessor, str);
                        }
                    }
                }
            }
        };
    }

    public static final void access$removeAllForPackage(MediaDataProcessor mediaDataProcessor, String str) {
        mediaDataProcessor.getClass();
        Assert.isMainThread();
        Map map = (Map) ((StateFlowImpl) mediaDataProcessor.mediaDataRepository.mediaEntries.$$delegate_0).getValue();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : map.entrySet()) {
            if (Intrinsics.areEqual(((MediaData) entry.getValue()).packageName, str)) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        Iterator it = linkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            removeEntry$default(mediaDataProcessor, (String) ((Map.Entry) it.next()).getKey(), false, 6);
        }
    }

    public static void notifyMediaDataRemoved$default(MediaDataProcessor mediaDataProcessor, String str) {
        Iterator it = mediaDataProcessor.internalListeners.iterator();
        while (it.hasNext()) {
            ((MediaDataManager.Listener) it.next()).onMediaDataRemoved(str, false);
        }
    }

    public static void removeEntry$default(MediaDataProcessor mediaDataProcessor, String str, boolean z, int i) {
        if ((i & 4) != 0) {
            z = false;
        }
        MediaData removeMediaEntry = mediaDataProcessor.mediaDataRepository.removeMediaEntry(str);
        if (removeMediaEntry != null) {
            InstanceId instanceId = removeMediaEntry.instanceId;
            mediaDataProcessor.logger.logMediaRemoved(removeMediaEntry.appUid, removeMediaEntry.packageName, instanceId);
        }
        Iterator it = mediaDataProcessor.internalListeners.iterator();
        while (it.hasNext()) {
            ((MediaDataManager.Listener) it.next()).onMediaDataRemoved(str, z);
        }
    }

    public final boolean dismissMediaData(InstanceId instanceId, long j, boolean z) {
        Map map = (Map) ((StateFlowImpl) this.mediaDataRepository.mediaEntries.$$delegate_0).getValue();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : map.entrySet()) {
            if (Intrinsics.areEqual(((MediaData) entry.getValue()).instanceId, instanceId)) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        if (linkedHashMap.isEmpty()) {
            return false;
        }
        return dismissMediaData((String) CollectionsKt.first(linkedHashMap.keySet()), j, z);
    }

    public final void dismissSmartspaceRecommendation(long j, String str) {
        MediaDataRepository mediaDataRepository = this.mediaDataRepository;
        ReadonlyStateFlow readonlyStateFlow = mediaDataRepository.smartspaceMediaData;
        SmartspaceMediaData smartspaceMediaData = (SmartspaceMediaData) ((StateFlowImpl) readonlyStateFlow.$$delegate_0).getValue();
        if (Intrinsics.areEqual(smartspaceMediaData.targetId, str) && smartspaceMediaData.isValid()) {
            Log.d("MediaDataRepository", "Dismissing Smartspace media target");
            if (smartspaceMediaData.isActive) {
                MutableStateFlow mutableStateFlow = readonlyStateFlow.$$delegate_0;
                SmartspaceMediaData smartspaceMediaData2 = new SmartspaceMediaData(((SmartspaceMediaData) ((StateFlowImpl) mutableStateFlow).getValue()).targetId, false, null, null, null, null, 0L, ((SmartspaceMediaData) ((StateFlowImpl) mutableStateFlow).getValue()).instanceId, 0L, 894);
                StateFlowImpl stateFlowImpl = mediaDataRepository._smartspaceMediaData;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, smartspaceMediaData2);
            }
            this.foregroundExecutor.executeDelayed(new MediaDataProcessor$dismissMediaData$1(this, str, 1), j);
        }
    }

    @Override // com.android.systemui.CoreStartable, com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("internalListeners: " + this.internalListeners);
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("useMediaResumption: ", this.useMediaResumption, printWriter);
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("allowMediaRecommendations: ", false, printWriter);
    }

    public final void notifyMediaDataLoaded(String str, String str2, MediaData mediaData) {
        Iterator it = this.internalListeners.iterator();
        while (it.hasNext()) {
            MediaDataManager.Listener.onMediaDataLoaded$default((MediaDataManager.Listener) it.next(), str, str2, mediaData, false, 0, false, 56);
        }
    }

    public final void onNotificationAdded(StatusBarNotification statusBarNotification, String str) {
        if (!this.useQsMediaPlayer || !statusBarNotification.getNotification().isMediaNotification()) {
            onNotificationRemoved(str);
            return;
        }
        Assert.isMainThread();
        String packageName = statusBarNotification.getPackageName();
        MediaDataRepository mediaDataRepository = this.mediaDataRepository;
        Map map = (Map) ((StateFlowImpl) mediaDataRepository.mediaEntries.$$delegate_0).getValue();
        String str2 = map.containsKey(str) ? str : map.containsKey(packageName) ? packageName : null;
        boolean z = true;
        if (str2 == null) {
            InstanceId newInstanceId = this.logger.instanceIdSequence.newInstanceId();
            MediaData mediaData = new MediaData(0, false, (String) null, (Icon) null, (CharSequence) null, (CharSequence) null, (Icon) null, (List) null, (List) null, (MediaButton) null, (String) null, (MediaSession.Token) null, (PendingIntent) null, (MediaDeviceData) null, false, (Runnable) null, 0, (String) null, false, (Boolean) null, false, 0L, 0L, (InstanceId) null, 0, false, (Double) null, 0, 1073741823);
            String packageName2 = statusBarNotification.getPackageName();
            ((SystemClockImpl) this.systemClock).getClass();
            long currentTimeMillis = System.currentTimeMillis();
            Intrinsics.checkNotNull(packageName2);
            mediaDataRepository.addMediaEntry(MediaData.copy$default(mediaData, null, null, null, packageName2, null, null, false, null, false, null, 0L, currentTimeMillis, newInstanceId, 0, 1048574975), str);
        } else if (str2.equals(str)) {
            z = false;
        } else {
            MediaData removeMediaEntry = mediaDataRepository.removeMediaEntry(str2);
            Intrinsics.checkNotNull(removeMediaEntry);
            mediaDataRepository.addMediaEntry(removeMediaEntry, str);
        }
        BuildersKt.launch$default(this.applicationScope, null, null, new MediaDataProcessor$loadMediaData$1(statusBarNotification, this, str, str2, null, z), 3);
    }

    public final void onNotificationRemoved(String str) {
        boolean z;
        long j;
        MediaFlags mediaFlags = this.mediaFlags;
        MediaUiEventLogger mediaUiEventLogger = this.logger;
        Assert.isMainThread();
        MediaDataRepository mediaDataRepository = this.mediaDataRepository;
        MediaData removeMediaEntry = mediaDataRepository.removeMediaEntry(str);
        if (removeMediaEntry == null) {
            return;
        }
        boolean isUserInLockdown = this.keyguardUpdateMonitor.isUserInLockdown(removeMediaEntry.userId);
        String str2 = removeMediaEntry.packageName;
        int i = removeMediaEntry.appUid;
        if (isUserInLockdown) {
            mediaUiEventLogger.logMediaRemoved(i, str2, removeMediaEntry.instanceId);
            return;
        }
        if (removeMediaEntry.playbackLocation == 0) {
            z = true;
        } else {
            mediaFlags.getClass();
            UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
            mediaFlags.featureFlags.getClass();
            z = false;
        }
        if (!(this.useMediaResumption && removeMediaEntry.resumeAction != null && z)) {
            mediaFlags.getClass();
            UnreleasedFlag unreleasedFlag2 = Flags.NULL_FLAG;
            mediaFlags.featureFlags.getClass();
            notifyMediaDataRemoved$default(this, str);
            mediaUiEventLogger.logMediaRemoved(i, str2, removeMediaEntry.instanceId);
            return;
        }
        Log.d("MediaDataProcessor", "Converting " + str + " to resume");
        CharSequence charSequence = removeMediaEntry.song;
        String str3 = removeMediaEntry.packageName;
        if (charSequence == null || StringsKt__StringsJVMKt.isBlank(charSequence)) {
            Log.e("MediaDataProcessor", "Description incomplete");
            notifyMediaDataRemoved$default(this, str);
            mediaUiEventLogger.logMediaRemoved(removeMediaEntry.appUid, str3, removeMediaEntry.instanceId);
            return;
        }
        Runnable runnable = removeMediaEntry.resumeAction;
        MediaAction mediaAction = runnable != null ? new MediaAction(Icon.createWithResource(this.context, com.android.wm.shell.R.drawable.ic_media_play).setTint(this.themeText).loadDrawable(this.context), runnable, this.context.getString(com.android.wm.shell.R.string.controls_media_resume), this.context.getDrawable(com.android.wm.shell.R.drawable.ic_media_play_container), null) : null;
        if (mediaAction != null) {
            Collections.singletonList(mediaAction);
        } else {
            EmptyList emptyList = EmptyList.INSTANCE;
        }
        Intent launchIntentForPackage = this.context.getPackageManager().getLaunchIntentForPackage(str3);
        PendingIntent activity = launchIntentForPackage != null ? PendingIntent.getActivity(this.context, 0, launchIntentForPackage, 67108864) : null;
        if (removeMediaEntry.active) {
            ((SystemClockImpl) this.systemClock).getClass();
            j = android.os.SystemClock.elapsedRealtime();
        } else {
            j = removeMediaEntry.lastActive;
        }
        MediaData copy$default = MediaData.copy$default(removeMediaEntry, EmptyList.INSTANCE, Collections.singletonList(0), new MediaButton(mediaAction), null, activity, null, false, null, false, Boolean.FALSE, j, 0L, null, 0, 1066247295);
        boolean z2 = mediaDataRepository.addMediaEntry(copy$default, str3) == null;
        Log.d("MediaDataProcessor", "migrating? " + z2 + " from " + str + " -> " + str3);
        if (z2) {
            notifyMediaDataLoaded(str3, str, copy$default);
        } else {
            notifyMediaDataRemoved$default(this, str);
            notifyMediaDataLoaded(str3, str3, copy$default);
        }
        mediaUiEventLogger.logger.logWithInstanceId(MediaUiEvent.ACTIVE_TO_RESUME, copy$default.appUid, str3, copy$default.instanceId);
        Map map = (Map) ((StateFlowImpl) mediaDataRepository.mediaEntries.$$delegate_0).getValue();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : map.entrySet()) {
            if (((MediaData) entry.getValue()).resumption) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        int size = linkedHashMap.size();
        if (size > 5) {
            for (Pair pair : CollectionsKt.sortedWith(MapsKt.toList(linkedHashMap), new MediaDataProcessor$convertToResumePlayer$$inlined$sortedBy$1()).subList(0, size - 5)) {
                String str4 = (String) pair.component1();
                MediaData mediaData = (MediaData) pair.component2();
                Log.d("MediaDataProcessor", "Removing excess control " + str4);
                mediaDataRepository.removeMediaEntry(str4);
                notifyMediaDataRemoved$default(this, str4);
                mediaUiEventLogger.logMediaRemoved(mediaData.appUid, mediaData.packageName, mediaData.instanceId);
            }
        }
    }

    @Override // com.android.systemui.plugins.BcSmartspaceDataPlugin.SmartspaceTargetListener
    public final void onSmartspaceTargetsUpdated(List list) {
        Log.d("MediaDataProcessor", "Smartspace recommendation is disabled in Settings.");
    }

    public final boolean dismissMediaData(final String str, long j, final boolean z) {
        boolean z2 = ((Map) ((StateFlowImpl) this.mediaDataRepository.mediaEntries.$$delegate_0).getValue()).get(str) != null;
        this.backgroundExecutor.execute(new MediaDataProcessor$dismissMediaData$1(this, str, 0));
        this.foregroundExecutor.executeDelayed(new Runnable() { // from class: com.android.systemui.media.controls.domain.pipeline.MediaDataProcessor$dismissMediaData$2
            @Override // java.lang.Runnable
            public final void run() {
                MediaDataProcessor.removeEntry$default(MediaDataProcessor.this, str, z, 2);
            }
        }, j);
        return z2;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
    }
}
