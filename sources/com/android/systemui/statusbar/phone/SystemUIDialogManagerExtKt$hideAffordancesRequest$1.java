package com.android.systemui.statusbar.phone;

import com.android.systemui.biometrics.data.repository.FacePropertyRepositoryImpl$cameraInfo$1$callback$1$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.phone.SystemUIDialogManager;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class SystemUIDialogManagerExtKt$hideAffordancesRequest$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ SystemUIDialogManager $this_hideAffordancesRequest;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SystemUIDialogManagerExtKt$hideAffordancesRequest$1(SystemUIDialogManager systemUIDialogManager, Continuation continuation) {
        super(2, continuation);
        this.$this_hideAffordancesRequest = systemUIDialogManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        SystemUIDialogManagerExtKt$hideAffordancesRequest$1 systemUIDialogManagerExtKt$hideAffordancesRequest$1 = new SystemUIDialogManagerExtKt$hideAffordancesRequest$1(this.$this_hideAffordancesRequest, continuation);
        systemUIDialogManagerExtKt$hideAffordancesRequest$1.L$0 = obj;
        return systemUIDialogManagerExtKt$hideAffordancesRequest$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((SystemUIDialogManagerExtKt$hideAffordancesRequest$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.statusbar.phone.SystemUIDialogManagerExtKt$hideAffordancesRequest$1$callback$1, java.lang.Object] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final ?? r1 = new SystemUIDialogManager.Listener() { // from class: com.android.systemui.statusbar.phone.SystemUIDialogManagerExtKt$hideAffordancesRequest$1$callback$1
                @Override // com.android.systemui.statusbar.phone.SystemUIDialogManager.Listener
                public final void shouldHideAffordances(boolean z) {
                    Object mo1790trySendJP2dKIU = ((ProducerCoroutine) ProducerScope.this)._channel.mo1790trySendJP2dKIU(Boolean.valueOf(z));
                    if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                        FacePropertyRepositoryImpl$cameraInfo$1$callback$1$$ExternalSyntheticOutline0.m("Failed to send ", "updated state", " - downstream canceled or failed.", "dialogHideAffordancesRequest", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                    }
                }
            };
            this.$this_hideAffordancesRequest.mListeners.add(r1);
            Object mo1790trySendJP2dKIU = ((ProducerCoroutine) producerScope)._channel.mo1790trySendJP2dKIU(Boolean.valueOf(this.$this_hideAffordancesRequest.shouldHideAffordance()));
            if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                FacePropertyRepositoryImpl$cameraInfo$1$callback$1$$ExternalSyntheticOutline0.m("Failed to send ", "updated state", " - downstream canceled or failed.", "dialogHideAffordancesRequestInitial", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
            }
            final SystemUIDialogManager systemUIDialogManager = this.$this_hideAffordancesRequest;
            Function0 function0 = new Function0() { // from class: com.android.systemui.statusbar.phone.SystemUIDialogManagerExtKt$hideAffordancesRequest$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    SystemUIDialogManager systemUIDialogManager2 = SystemUIDialogManager.this;
                    systemUIDialogManager2.mListeners.remove(r1);
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, function0, this) == coroutineSingletons) {
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
