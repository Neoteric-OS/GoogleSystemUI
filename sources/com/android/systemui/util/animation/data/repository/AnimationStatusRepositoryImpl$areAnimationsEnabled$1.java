package com.android.systemui.util.animation.data.repository;

import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt__StringNumberConversionsJVMKt;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class AnimationStatusRepositoryImpl$areAnimationsEnabled$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ AnimationStatusRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AnimationStatusRepositoryImpl$areAnimationsEnabled$1(AnimationStatusRepositoryImpl animationStatusRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = animationStatusRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        AnimationStatusRepositoryImpl$areAnimationsEnabled$1 animationStatusRepositoryImpl$areAnimationsEnabled$1 = new AnimationStatusRepositoryImpl$areAnimationsEnabled$1(this.this$0, continuation);
        animationStatusRepositoryImpl$areAnimationsEnabled$1.L$0 = obj;
        return animationStatusRepositoryImpl$areAnimationsEnabled$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((AnimationStatusRepositoryImpl$areAnimationsEnabled$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [android.database.ContentObserver, com.android.systemui.util.animation.data.repository.AnimationStatusRepositoryImpl$areAnimationsEnabled$1$observer$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Float floatOrNull;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope = (ProducerScope) this.L$0;
            String string = Settings.Global.getString(this.this$0.resolver, "animator_duration_scale");
            final ProducerCoroutine producerCoroutine = (ProducerCoroutine) producerScope;
            producerCoroutine.mo1790trySendJP2dKIU(Boolean.valueOf(!(((string == null || (floatOrNull = StringsKt__StringNumberConversionsJVMKt.toFloatOrNull(string)) == null) ? 1.0f : floatOrNull.floatValue()) == 0.0f)));
            final Handler handler = this.this$0.backgroundHandler;
            final AnimationStatusRepositoryImpl animationStatusRepositoryImpl = this.this$0;
            final ?? r5 = new ContentObserver(handler) { // from class: com.android.systemui.util.animation.data.repository.AnimationStatusRepositoryImpl$areAnimationsEnabled$1$observer$1
                @Override // android.database.ContentObserver
                public final void onChange(boolean z) {
                    Float floatOrNull2;
                    String string2 = Settings.Global.getString(AnimationStatusRepositoryImpl.this.resolver, "animator_duration_scale");
                    ((ProducerCoroutine) producerCoroutine).mo1790trySendJP2dKIU(Boolean.valueOf(!(((string2 == null || (floatOrNull2 = StringsKt__StringNumberConversionsJVMKt.toFloatOrNull(string2)) == null) ? 1.0f : floatOrNull2.floatValue()) == 0.0f)));
                }
            };
            this.this$0.resolver.registerContentObserver(Settings.Global.getUriFor("animator_duration_scale"), false, r5);
            final AnimationStatusRepositoryImpl animationStatusRepositoryImpl2 = this.this$0;
            Function0 function0 = new Function0() { // from class: com.android.systemui.util.animation.data.repository.AnimationStatusRepositoryImpl$areAnimationsEnabled$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    AnimationStatusRepositoryImpl.this.resolver.unregisterContentObserver(r5);
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (ProduceKt.awaitClose(producerCoroutine, function0, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
