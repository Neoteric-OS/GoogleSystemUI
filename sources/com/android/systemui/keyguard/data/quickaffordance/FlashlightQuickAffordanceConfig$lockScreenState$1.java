package com.android.systemui.keyguard.data.quickaffordance;

import com.android.systemui.biometrics.data.repository.FacePropertyRepositoryImpl$cameraInfo$1$callback$1$$ExternalSyntheticOutline0;
import com.android.systemui.common.shared.model.ContentDescription;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig;
import com.android.systemui.keyguard.shared.quickaffordance.ActivationState;
import com.android.systemui.statusbar.policy.FlashlightController$FlashlightListener;
import com.android.wm.shell.R;
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
/* loaded from: classes.dex */
final class FlashlightQuickAffordanceConfig$lockScreenState$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ FlashlightQuickAffordanceConfig this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlashlightQuickAffordanceConfig$lockScreenState$1(FlashlightQuickAffordanceConfig flashlightQuickAffordanceConfig, Continuation continuation) {
        super(2, continuation);
        this.this$0 = flashlightQuickAffordanceConfig;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        FlashlightQuickAffordanceConfig$lockScreenState$1 flashlightQuickAffordanceConfig$lockScreenState$1 = new FlashlightQuickAffordanceConfig$lockScreenState$1(this.this$0, continuation);
        flashlightQuickAffordanceConfig$lockScreenState$1.L$0 = obj;
        return flashlightQuickAffordanceConfig$lockScreenState$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FlashlightQuickAffordanceConfig$lockScreenState$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.keyguard.data.quickaffordance.FlashlightQuickAffordanceConfig$lockScreenState$1$flashlightCallback$1, java.lang.Object] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final FlashlightQuickAffordanceConfig flashlightQuickAffordanceConfig = this.this$0;
            final ?? r1 = new FlashlightController$FlashlightListener() { // from class: com.android.systemui.keyguard.data.quickaffordance.FlashlightQuickAffordanceConfig$lockScreenState$1$flashlightCallback$1
                @Override // com.android.systemui.statusbar.policy.FlashlightController$FlashlightListener
                public final void onFlashlightAvailabilityChanged(boolean z) {
                    Object mo1790trySendJP2dKIU = ((ProducerCoroutine) ProducerScope.this)._channel.mo1790trySendJP2dKIU(!z ? KeyguardQuickAffordanceConfig.LockScreenState.Hidden.INSTANCE : flashlightQuickAffordanceConfig.flashlightController.isEnabled() ? new KeyguardQuickAffordanceConfig.LockScreenState.Visible(new Icon.Resource(R.drawable.qs_flashlight_icon_on, new ContentDescription.Resource(R.string.quick_settings_flashlight_label)), ActivationState.Active.INSTANCE) : new KeyguardQuickAffordanceConfig.LockScreenState.Visible(new Icon.Resource(R.drawable.qs_flashlight_icon_off, new ContentDescription.Resource(R.string.quick_settings_flashlight_label)), ActivationState.Inactive.INSTANCE));
                    if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                        FacePropertyRepositoryImpl$cameraInfo$1$callback$1$$ExternalSyntheticOutline0.m("Failed to send ", "updated state", " - downstream canceled or failed.", "FlashlightQuickAffordanceConfig", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                    }
                }

                @Override // com.android.systemui.statusbar.policy.FlashlightController$FlashlightListener
                public final void onFlashlightChanged(boolean z) {
                    Object mo1790trySendJP2dKIU = ((ProducerCoroutine) ProducerScope.this)._channel.mo1790trySendJP2dKIU(z ? new KeyguardQuickAffordanceConfig.LockScreenState.Visible(new Icon.Resource(R.drawable.qs_flashlight_icon_on, new ContentDescription.Resource(R.string.quick_settings_flashlight_label)), ActivationState.Active.INSTANCE) : new KeyguardQuickAffordanceConfig.LockScreenState.Visible(new Icon.Resource(R.drawable.qs_flashlight_icon_off, new ContentDescription.Resource(R.string.quick_settings_flashlight_label)), ActivationState.Inactive.INSTANCE));
                    if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                        FacePropertyRepositoryImpl$cameraInfo$1$callback$1$$ExternalSyntheticOutline0.m("Failed to send ", "updated state", " - downstream canceled or failed.", "FlashlightQuickAffordanceConfig", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                    }
                }

                @Override // com.android.systemui.statusbar.policy.FlashlightController$FlashlightListener
                public final void onFlashlightError() {
                    Object mo1790trySendJP2dKIU = ((ProducerCoroutine) ProducerScope.this)._channel.mo1790trySendJP2dKIU(new KeyguardQuickAffordanceConfig.LockScreenState.Visible(new Icon.Resource(R.drawable.qs_flashlight_icon_off, new ContentDescription.Resource(R.string.quick_settings_flashlight_label)), ActivationState.Inactive.INSTANCE));
                    if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                        FacePropertyRepositoryImpl$cameraInfo$1$callback$1$$ExternalSyntheticOutline0.m("Failed to send ", "updated state", " - downstream canceled or failed.", "FlashlightQuickAffordanceConfig", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                    }
                }
            };
            flashlightQuickAffordanceConfig.flashlightController.addCallback(r1);
            final FlashlightQuickAffordanceConfig flashlightQuickAffordanceConfig2 = this.this$0;
            Function0 function0 = new Function0() { // from class: com.android.systemui.keyguard.data.quickaffordance.FlashlightQuickAffordanceConfig$lockScreenState$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    FlashlightQuickAffordanceConfig.this.flashlightController.removeCallback(r1);
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
