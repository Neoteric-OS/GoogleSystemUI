package com.android.systemui.mediaprojection.data.repository;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class MediaProjectionManagerRepository$stopProjecting$2 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ MediaProjectionManagerRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaProjectionManagerRepository$stopProjecting$2(MediaProjectionManagerRepository mediaProjectionManagerRepository, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mediaProjectionManagerRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new MediaProjectionManagerRepository$stopProjecting$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        MediaProjectionManagerRepository$stopProjecting$2 mediaProjectionManagerRepository$stopProjecting$2 = (MediaProjectionManagerRepository$stopProjecting$2) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        mediaProjectionManagerRepository$stopProjecting$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        LogBuffer logBuffer = this.this$0.logger;
        logBuffer.commit(logBuffer.obtain("MediaProjectionMngrRepo", LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.mediaprojection.data.repository.MediaProjectionManagerRepository$stopProjecting$2.2
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                return "Requesting MediaProjectionManager#stopActiveProjection";
            }
        }, null));
        this.this$0.mediaProjectionManager.stopActiveProjection();
        return Unit.INSTANCE;
    }
}
