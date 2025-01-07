package com.android.systemui.screenshot;

import android.media.MediaPlayer;
import android.util.Log;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DeferredCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ScreenshotSoundControllerImpl$playScreenshotSound$2 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ ScreenshotSoundControllerImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScreenshotSoundControllerImpl$playScreenshotSound$2(ScreenshotSoundControllerImpl screenshotSoundControllerImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = screenshotSoundControllerImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ScreenshotSoundControllerImpl$playScreenshotSound$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ScreenshotSoundControllerImpl$playScreenshotSound$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                DeferredCoroutine deferredCoroutine = this.this$0.player;
                this.label = 1;
                obj = deferredCoroutine.awaitInternal(this);
                if (obj == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return unit;
                }
                ResultKt.throwOnFailure(obj);
            }
            MediaPlayer mediaPlayer = (MediaPlayer) obj;
            if (mediaPlayer == null) {
                return null;
            }
            mediaPlayer.start();
            return unit;
        } catch (IllegalStateException e) {
            Log.w("ScreenshotSoundControllerImpl", "Screenshot sound failed to play", e);
            ScreenshotSoundControllerImpl screenshotSoundControllerImpl = this.this$0;
            this.label = 2;
            screenshotSoundControllerImpl.getClass();
            Object withContext = BuildersKt.withContext(screenshotSoundControllerImpl.bgDispatcher, new ScreenshotSoundControllerImpl$releaseScreenshotSound$2(screenshotSoundControllerImpl, null), this);
            if (withContext != CoroutineSingletons.COROUTINE_SUSPENDED) {
                withContext = unit;
            }
            return withContext == coroutineSingletons ? coroutineSingletons : unit;
        }
    }
}
