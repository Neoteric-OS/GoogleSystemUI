package com.android.systemui.media.controls.domain.pipeline;

import android.app.PendingIntent;
import android.media.MediaDescription;
import android.media.session.MediaSession;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class LegacyMediaDataManagerImpl$addResumptionControls$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Runnable $action;
    final /* synthetic */ PendingIntent $appIntent;
    final /* synthetic */ String $appName;
    final /* synthetic */ MediaDescription $desc;
    final /* synthetic */ String $packageName;
    final /* synthetic */ MediaSession.Token $token;
    final /* synthetic */ int $userId;
    int label;
    final /* synthetic */ LegacyMediaDataManagerImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LegacyMediaDataManagerImpl$addResumptionControls$1(int i, PendingIntent pendingIntent, MediaDescription mediaDescription, MediaSession.Token token, LegacyMediaDataManagerImpl legacyMediaDataManagerImpl, Runnable runnable, String str, String str2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = legacyMediaDataManagerImpl;
        this.$userId = i;
        this.$desc = mediaDescription;
        this.$action = runnable;
        this.$token = token;
        this.$appName = str;
        this.$appIntent = pendingIntent;
        this.$packageName = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        LegacyMediaDataManagerImpl legacyMediaDataManagerImpl = this.this$0;
        int i = this.$userId;
        MediaDescription mediaDescription = this.$desc;
        Runnable runnable = this.$action;
        return new LegacyMediaDataManagerImpl$addResumptionControls$1(i, this.$appIntent, mediaDescription, this.$token, legacyMediaDataManagerImpl, runnable, this.$appName, this.$packageName, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((LegacyMediaDataManagerImpl$addResumptionControls$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LegacyMediaDataManagerImpl legacyMediaDataManagerImpl = this.this$0;
            int i2 = this.$userId;
            MediaDescription mediaDescription = this.$desc;
            Runnable runnable = this.$action;
            MediaSession.Token token = this.$token;
            String str = this.$appName;
            PendingIntent pendingIntent = this.$appIntent;
            String str2 = this.$packageName;
            this.label = 1;
            int i3 = LegacyMediaDataManagerImpl.MAX_NOTIFICATION_ACTIONS;
            legacyMediaDataManagerImpl.getClass();
            Object withContext = BuildersKt.withContext(legacyMediaDataManagerImpl.backgroundDispatcher, new LegacyMediaDataManagerImpl$loadMediaDataForResumption$2(i2, pendingIntent, mediaDescription, token, legacyMediaDataManagerImpl, runnable, str2, str, null), this);
            if (withContext != coroutineSingletons) {
                withContext = unit;
            }
            if (withContext == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return unit;
    }
}
