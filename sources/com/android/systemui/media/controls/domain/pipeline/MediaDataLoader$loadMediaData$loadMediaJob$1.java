package com.android.systemui.media.controls.domain.pipeline;

import android.service.notification.StatusBarNotification;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class MediaDataLoader$loadMediaData$loadMediaJob$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ boolean $isConvertingToActive;
    final /* synthetic */ String $key;
    final /* synthetic */ StatusBarNotification $sbn;
    int label;
    final /* synthetic */ MediaDataLoader this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaDataLoader$loadMediaData$loadMediaJob$1(MediaDataLoader mediaDataLoader, String str, StatusBarNotification statusBarNotification, boolean z, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mediaDataLoader;
        this.$key = str;
        this.$sbn = statusBarNotification;
        this.$isConvertingToActive = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new MediaDataLoader$loadMediaData$loadMediaJob$1(this.this$0, this.$key, this.$sbn, this.$isConvertingToActive, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((MediaDataLoader$loadMediaData$loadMediaJob$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MediaDataLoader mediaDataLoader = this.this$0;
            String str = this.$key;
            StatusBarNotification statusBarNotification = this.$sbn;
            boolean z = this.$isConvertingToActive;
            this.label = 1;
            obj = MediaDataLoader.access$loadMediaDataInBackground(mediaDataLoader, str, statusBarNotification, z, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
