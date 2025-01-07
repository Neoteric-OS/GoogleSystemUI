package com.android.systemui.media.controls.domain.pipeline;

import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.os.SystemClock;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import com.android.internal.logging.InstanceId;
import com.android.systemui.media.controls.domain.pipeline.MediaDataLoader;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.util.MediaControllerFactory;
import com.android.systemui.media.controls.util.MediaUiEvent;
import com.android.systemui.media.controls.util.MediaUiEventLogger;
import com.android.systemui.util.time.SystemClockImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class LegacyMediaDataManagerImpl$loadMediaDataWithLoader$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ boolean $isConvertingToActive;
    final /* synthetic */ boolean $isNewlyActiveEntry;
    final /* synthetic */ String $key;
    final /* synthetic */ String $oldKey;
    final /* synthetic */ StatusBarNotification $sbn;
    long J$0;
    int label;
    final /* synthetic */ LegacyMediaDataManagerImpl this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.media.controls.domain.pipeline.LegacyMediaDataManagerImpl$loadMediaDataWithLoader$2$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ String $key;
        final /* synthetic */ MediaData $mediaData;
        final /* synthetic */ String $oldKey;
        int label;
        final /* synthetic */ LegacyMediaDataManagerImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(LegacyMediaDataManagerImpl legacyMediaDataManagerImpl, String str, String str2, MediaData mediaData, Continuation continuation) {
            super(2, continuation);
            this.this$0 = legacyMediaDataManagerImpl;
            this.$key = str;
            this.$oldKey = str2;
            this.$mediaData = mediaData;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.this$0, this.$key, this.$oldKey, this.$mediaData, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = (AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2);
            Unit unit = Unit.INSTANCE;
            anonymousClass1.invokeSuspend(unit);
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.this$0.onMediaDataLoaded(this.$key, this.$oldKey, this.$mediaData);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LegacyMediaDataManagerImpl$loadMediaDataWithLoader$2(StatusBarNotification statusBarNotification, LegacyMediaDataManagerImpl legacyMediaDataManagerImpl, String str, String str2, Continuation continuation, boolean z, boolean z2) {
        super(2, continuation);
        this.this$0 = legacyMediaDataManagerImpl;
        this.$key = str;
        this.$sbn = statusBarNotification;
        this.$isConvertingToActive = z;
        this.$isNewlyActiveEntry = z2;
        this.$oldKey = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        LegacyMediaDataManagerImpl legacyMediaDataManagerImpl = this.this$0;
        String str = this.$key;
        return new LegacyMediaDataManagerImpl$loadMediaDataWithLoader$2(this.$sbn, legacyMediaDataManagerImpl, str, this.$oldKey, continuation, this.$isConvertingToActive, this.$isNewlyActiveEntry);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((LegacyMediaDataManagerImpl$loadMediaDataWithLoader$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        long elapsedRealtime;
        Object loadMediaData;
        InstanceId newInstanceId;
        InstanceId instanceId;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ((SystemClockImpl) this.this$0.systemClock).getClass();
            elapsedRealtime = SystemClock.elapsedRealtime();
            MediaDataLoader mediaDataLoader = (MediaDataLoader) this.this$0.mediaDataLoader.get();
            String str = this.$key;
            StatusBarNotification statusBarNotification = this.$sbn;
            boolean z = this.$isConvertingToActive;
            this.J$0 = elapsedRealtime;
            this.label = 1;
            loadMediaData = mediaDataLoader.loadMediaData(str, statusBarNotification, z, this);
            if (loadMediaData == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            elapsedRealtime = this.J$0;
            ResultKt.throwOnFailure(obj);
            loadMediaData = obj;
        }
        long j = elapsedRealtime;
        MediaDataLoader.MediaDataLoaderResult mediaDataLoaderResult = (MediaDataLoader.MediaDataLoaderResult) loadMediaData;
        if (mediaDataLoaderResult == null) {
            Log.d("MediaDataManager", "No result from loadMediaData");
            return unit;
        }
        MediaData mediaData = (MediaData) this.this$0.mediaEntries.get(this.$key);
        if (mediaData == null || (newInstanceId = mediaData.instanceId) == null) {
            newInstanceId = this.this$0.logger.instanceIdSequence.newInstanceId();
        }
        InstanceId instanceId2 = newInstanceId;
        long j2 = mediaData != null ? mediaData.createdTimestampMillis : 0L;
        Runnable runnable = mediaData != null ? mediaData.resumeAction : null;
        boolean z2 = mediaData != null && mediaData.hasCheckedForResume;
        boolean z3 = mediaData != null ? mediaData.active : true;
        MediaControllerFactory mediaControllerFactory = this.this$0.mediaControllerFactory;
        MediaSession.Token token = mediaDataLoaderResult.token;
        Intrinsics.checkNotNull(token);
        MediaController create = mediaControllerFactory.create(token);
        MediaData mediaData2 = new MediaData(this.$sbn.getNormalizedUserId(), true, mediaDataLoaderResult.appName, mediaDataLoaderResult.appIcon, mediaDataLoaderResult.artist, mediaDataLoaderResult.song, mediaDataLoaderResult.artworkIcon, mediaDataLoaderResult.actionIcons, mediaDataLoaderResult.actionsToShowInCompact, mediaDataLoaderResult.semanticActions, this.$sbn.getPackageName(), mediaDataLoaderResult.token, mediaDataLoaderResult.clickIntent, mediaDataLoaderResult.device, z3, runnable, mediaDataLoaderResult.playbackLocation, this.$key, z2, mediaDataLoaderResult.isPlaying, !this.$sbn.isOngoing(), j, j2, instanceId2, mediaDataLoaderResult.appUid, mediaDataLoaderResult.isExplicit, (Double) null, 0, 939655168);
        if (MediaProcessingHelperKt.isSameMediaData(this.this$0.context, create, mediaData2, mediaData)) {
            this.this$0.mediaLogger.logDuplicateMediaNotification(this.$key);
            return unit;
        }
        boolean z4 = this.$isNewlyActiveEntry;
        int i2 = mediaDataLoaderResult.playbackLocation;
        int i3 = mediaDataLoaderResult.appUid;
        if (z4) {
            LegacyMediaDataManagerImpl legacyMediaDataManagerImpl = this.this$0;
            String packageName = this.$sbn.getPackageName();
            int size = legacyMediaDataManagerImpl.mediaEntries.size();
            MediaUiEventLogger mediaUiEventLogger = legacyMediaDataManagerImpl.logger;
            if (size == 1) {
                instanceId = instanceId2;
                mediaUiEventLogger.logger.logWithInstanceId(MediaUiEvent.MEDIA_CAROUSEL_SINGLE_PLAYER, i3, packageName, instanceId);
            } else {
                instanceId = instanceId2;
                if (legacyMediaDataManagerImpl.mediaEntries.size() == 2) {
                    mediaUiEventLogger.logger.logWithInstanceId(MediaUiEvent.MEDIA_CAROUSEL_MULTIPLE_PLAYERS, i3, packageName, instanceId);
                }
            }
            this.this$0.logger.logActiveMediaAdded(i3, this.$sbn.getPackageName(), instanceId, i2);
        } else if (mediaData == null || i2 != mediaData.playbackLocation) {
            this.this$0.logger.logPlaybackLocationChange(i3, this.$sbn.getPackageName(), instanceId2, i2);
        }
        LegacyMediaDataManagerImpl legacyMediaDataManagerImpl2 = this.this$0;
        CoroutineDispatcher coroutineDispatcher = legacyMediaDataManagerImpl2.mainDispatcher;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(legacyMediaDataManagerImpl2, this.$key, this.$oldKey, mediaData2, null);
        this.label = 2;
        return BuildersKt.withContext(coroutineDispatcher, anonymousClass1, this) == coroutineSingletons ? coroutineSingletons : unit;
    }
}
