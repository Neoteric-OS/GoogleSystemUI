package com.android.systemui.volume.panel.component.captioning.ui.viewmodel;

import com.android.systemui.accessibility.domain.interactor.CaptioningInteractor;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class CaptioningViewModel$setIsSystemAudioCaptioningEnabled$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ boolean $enabled;
    int label;
    final /* synthetic */ CaptioningViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CaptioningViewModel$setIsSystemAudioCaptioningEnabled$1(CaptioningViewModel captioningViewModel, boolean z, Continuation continuation) {
        super(2, continuation);
        this.this$0 = captioningViewModel;
        this.$enabled = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new CaptioningViewModel$setIsSystemAudioCaptioningEnabled$1(this.this$0, this.$enabled, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((CaptioningViewModel$setIsSystemAudioCaptioningEnabled$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CaptioningInteractor captioningInteractor = this.this$0.captioningInteractor;
            boolean z = this.$enabled;
            this.label = 1;
            Object isSystemAudioCaptioningEnabled = captioningInteractor.repository.setIsSystemAudioCaptioningEnabled(z, this);
            if (isSystemAudioCaptioningEnabled != coroutineSingletons) {
                isSystemAudioCaptioningEnabled = unit;
            }
            if (isSystemAudioCaptioningEnabled == coroutineSingletons) {
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
