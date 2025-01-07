package com.android.systemui.media.controls.domain.pipeline;

import android.service.notification.StatusBarNotification;
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
final class LegacyMediaDataManagerImpl$loadMediaData$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ boolean $isConvertingToActive;
    final /* synthetic */ boolean $isNewlyActiveEntry;
    final /* synthetic */ String $key;
    final /* synthetic */ String $oldKey;
    final /* synthetic */ StatusBarNotification $sbn;
    int label;
    final /* synthetic */ LegacyMediaDataManagerImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LegacyMediaDataManagerImpl$loadMediaData$1(StatusBarNotification statusBarNotification, LegacyMediaDataManagerImpl legacyMediaDataManagerImpl, String str, String str2, Continuation continuation, boolean z, boolean z2) {
        super(2, continuation);
        this.this$0 = legacyMediaDataManagerImpl;
        this.$key = str;
        this.$sbn = statusBarNotification;
        this.$oldKey = str2;
        this.$isNewlyActiveEntry = z;
        this.$isConvertingToActive = z2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new LegacyMediaDataManagerImpl$loadMediaData$1(this.$sbn, this.this$0, this.$key, this.$oldKey, continuation, this.$isNewlyActiveEntry, this.$isConvertingToActive);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((LegacyMediaDataManagerImpl$loadMediaData$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LegacyMediaDataManagerImpl legacyMediaDataManagerImpl = this.this$0;
            String str = this.$key;
            StatusBarNotification statusBarNotification = this.$sbn;
            String str2 = this.$oldKey;
            boolean z = this.$isNewlyActiveEntry;
            boolean z2 = this.$isConvertingToActive;
            this.label = 1;
            int i2 = LegacyMediaDataManagerImpl.MAX_NOTIFICATION_ACTIONS;
            legacyMediaDataManagerImpl.getClass();
            Object withContext = BuildersKt.withContext(legacyMediaDataManagerImpl.backgroundDispatcher, new LegacyMediaDataManagerImpl$loadMediaDataWithLoader$2(statusBarNotification, legacyMediaDataManagerImpl, str, str2, null, z2, z), this);
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
