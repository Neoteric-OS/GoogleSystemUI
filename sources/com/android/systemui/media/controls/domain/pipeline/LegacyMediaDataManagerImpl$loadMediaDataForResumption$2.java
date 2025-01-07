package com.android.systemui.media.controls.domain.pipeline;

import android.app.PendingIntent;
import android.graphics.drawable.Icon;
import android.media.MediaDescription;
import android.media.session.MediaSession;
import android.os.SystemClock;
import android.util.Log;
import com.android.internal.logging.InstanceId;
import com.android.systemui.media.controls.domain.pipeline.MediaDataLoader;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.util.time.SystemClockImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class LegacyMediaDataManagerImpl$loadMediaDataForResumption$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ PendingIntent $appIntent;
    final /* synthetic */ String $appName;
    final /* synthetic */ MediaDescription $desc;
    final /* synthetic */ String $packageName;
    final /* synthetic */ Runnable $resumeAction;
    final /* synthetic */ MediaSession.Token $token;
    final /* synthetic */ int $userId;
    long J$0;
    long J$1;
    Object L$0;
    int label;
    final /* synthetic */ LegacyMediaDataManagerImpl this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.media.controls.domain.pipeline.LegacyMediaDataManagerImpl$loadMediaDataForResumption$2$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ long $createdTimestampMillis;
        final /* synthetic */ InstanceId $instanceId;
        final /* synthetic */ long $lastActive;
        final /* synthetic */ String $packageName;
        final /* synthetic */ MediaDataLoader.MediaDataLoaderResult $result;
        final /* synthetic */ Runnable $resumeAction;
        final /* synthetic */ int $userId;
        int label;
        final /* synthetic */ LegacyMediaDataManagerImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(LegacyMediaDataManagerImpl legacyMediaDataManagerImpl, String str, int i, MediaDataLoader.MediaDataLoaderResult mediaDataLoaderResult, Runnable runnable, long j, long j2, InstanceId instanceId, Continuation continuation) {
            super(2, continuation);
            this.this$0 = legacyMediaDataManagerImpl;
            this.$packageName = str;
            this.$userId = i;
            this.$result = mediaDataLoaderResult;
            this.$resumeAction = runnable;
            this.$lastActive = j;
            this.$createdTimestampMillis = j2;
            this.$instanceId = instanceId;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.this$0, this.$packageName, this.$userId, this.$result, this.$resumeAction, this.$lastActive, this.$createdTimestampMillis, this.$instanceId, continuation);
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
            LegacyMediaDataManagerImpl legacyMediaDataManagerImpl = this.this$0;
            String str = this.$packageName;
            int i = this.$userId;
            MediaDataLoader.MediaDataLoaderResult mediaDataLoaderResult = this.$result;
            legacyMediaDataManagerImpl.onMediaDataLoaded(str, null, new MediaData(i, true, mediaDataLoaderResult.appName, (Icon) null, mediaDataLoaderResult.artist, mediaDataLoaderResult.song, mediaDataLoaderResult.artworkIcon, mediaDataLoaderResult.actionIcons, mediaDataLoaderResult.actionsToShowInCompact, mediaDataLoaderResult.semanticActions, str, mediaDataLoaderResult.token, mediaDataLoaderResult.clickIntent, mediaDataLoaderResult.device, false, this.$resumeAction, 0, str, true, (Boolean) null, false, this.$lastActive, this.$createdTimestampMillis, this.$instanceId, mediaDataLoaderResult.appUid, mediaDataLoaderResult.isExplicit, mediaDataLoaderResult.resumeProgress, 0, 808517632));
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LegacyMediaDataManagerImpl$loadMediaDataForResumption$2(int i, PendingIntent pendingIntent, MediaDescription mediaDescription, MediaSession.Token token, LegacyMediaDataManagerImpl legacyMediaDataManagerImpl, Runnable runnable, String str, String str2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = legacyMediaDataManagerImpl;
        this.$packageName = str;
        this.$userId = i;
        this.$desc = mediaDescription;
        this.$resumeAction = runnable;
        this.$token = token;
        this.$appName = str2;
        this.$appIntent = pendingIntent;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        LegacyMediaDataManagerImpl legacyMediaDataManagerImpl = this.this$0;
        String str = this.$packageName;
        int i = this.$userId;
        MediaDescription mediaDescription = this.$desc;
        Runnable runnable = this.$resumeAction;
        return new LegacyMediaDataManagerImpl$loadMediaDataForResumption$2(i, this.$appIntent, mediaDescription, this.$token, legacyMediaDataManagerImpl, runnable, str, this.$appName, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((LegacyMediaDataManagerImpl$loadMediaDataForResumption$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        MediaData mediaData;
        Object awaitInternal;
        long j;
        long j2;
        CharSequence title;
        InstanceId newInstanceId;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ((SystemClockImpl) this.this$0.systemClock).getClass();
            long elapsedRealtime = SystemClock.elapsedRealtime();
            mediaData = (MediaData) this.this$0.mediaEntries.get(this.$packageName);
            long j3 = mediaData != null ? mediaData.createdTimestampMillis : 0L;
            MediaDataLoader mediaDataLoader = (MediaDataLoader) this.this$0.mediaDataLoader.get();
            int i2 = this.$userId;
            MediaDescription mediaDescription = this.$desc;
            Runnable runnable = this.$resumeAction;
            MediaSession.Token token = this.$token;
            String str = this.$appName;
            PendingIntent pendingIntent = this.$appIntent;
            String str2 = this.$packageName;
            this.L$0 = mediaData;
            this.J$0 = elapsedRealtime;
            this.J$1 = j3;
            this.label = 1;
            mediaDataLoader.getClass();
            awaitInternal = BuildersKt.async$default(mediaDataLoader.backgroundScope, null, new MediaDataLoader$loadMediaDataForResumption$mediaData$1(mediaDataLoader, i2, mediaDescription, runnable, mediaData, token, str, pendingIntent, str2, null), 3).awaitInternal(this);
            if (awaitInternal == coroutineSingletons) {
                return coroutineSingletons;
            }
            j = elapsedRealtime;
            j2 = j3;
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            long j4 = this.J$1;
            long j5 = this.J$0;
            mediaData = (MediaData) this.L$0;
            ResultKt.throwOnFailure(obj);
            awaitInternal = obj;
            j2 = j4;
            j = j5;
        }
        MediaDataLoader.MediaDataLoaderResult mediaDataLoaderResult = (MediaDataLoader.MediaDataLoaderResult) awaitInternal;
        if (mediaDataLoaderResult == null || (title = this.$desc.getTitle()) == null || StringsKt__StringsJVMKt.isBlank(title)) {
            Log.d("MediaDataManager", "No MediaData result for resumption");
            this.this$0.mediaEntries.remove(this.$packageName);
            return unit;
        }
        if (mediaData == null || (newInstanceId = mediaData.instanceId) == null) {
            newInstanceId = this.this$0.logger.instanceIdSequence.newInstanceId();
        }
        InstanceId instanceId = newInstanceId;
        LegacyMediaDataManagerImpl legacyMediaDataManagerImpl = this.this$0;
        CoroutineDispatcher coroutineDispatcher = legacyMediaDataManagerImpl.mainDispatcher;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(legacyMediaDataManagerImpl, this.$packageName, this.$userId, mediaDataLoaderResult, this.$resumeAction, j, j2, instanceId, null);
        this.L$0 = null;
        this.label = 2;
        return BuildersKt.withContext(coroutineDispatcher, anonymousClass1, this) == coroutineSingletons ? coroutineSingletons : unit;
    }
}
