package com.android.systemui.qs.tiles.impl.uimodenight.domain.interactor;

import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.LocationController;
import com.android.systemui.statusbar.policy.LocationControllerImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class UiModeNightTileDataInteractor$tileData$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ UiModeNightTileDataInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UiModeNightTileDataInteractor$tileData$1(UiModeNightTileDataInteractor uiModeNightTileDataInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = uiModeNightTileDataInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        UiModeNightTileDataInteractor$tileData$1 uiModeNightTileDataInteractor$tileData$1 = new UiModeNightTileDataInteractor$tileData$1(this.this$0, continuation);
        uiModeNightTileDataInteractor$tileData$1.L$0 = obj;
        return uiModeNightTileDataInteractor$tileData$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((UiModeNightTileDataInteractor$tileData$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.qs.tiles.impl.uimodenight.domain.interactor.UiModeNightTileDataInteractor$tileData$1$configurationCallback$1, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v3, types: [com.android.systemui.qs.tiles.impl.uimodenight.domain.interactor.UiModeNightTileDataInteractor$tileData$1$batteryCallback$1, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v2, types: [com.android.systemui.qs.tiles.impl.uimodenight.domain.interactor.UiModeNightTileDataInteractor$tileData$1$locationCallback$1, java.lang.Object] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerCoroutine producerCoroutine = (ProducerCoroutine) ((ProducerScope) this.L$0);
            producerCoroutine.mo1790trySendJP2dKIU(UiModeNightTileDataInteractor.access$createModel(this.this$0));
            final UiModeNightTileDataInteractor uiModeNightTileDataInteractor = this.this$0;
            final ?? r1 = new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.qs.tiles.impl.uimodenight.domain.interactor.UiModeNightTileDataInteractor$tileData$1$configurationCallback$1
                @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
                public final void onUiModeChanged() {
                    ((ProducerCoroutine) ProducerScope.this).mo1790trySendJP2dKIU(UiModeNightTileDataInteractor.access$createModel(uiModeNightTileDataInteractor));
                }
            };
            ((ConfigurationControllerImpl) uiModeNightTileDataInteractor.configurationController).addCallback(r1);
            final UiModeNightTileDataInteractor uiModeNightTileDataInteractor2 = this.this$0;
            final ?? r3 = new BatteryController.BatteryStateChangeCallback() { // from class: com.android.systemui.qs.tiles.impl.uimodenight.domain.interactor.UiModeNightTileDataInteractor$tileData$1$batteryCallback$1
                @Override // com.android.systemui.statusbar.policy.BatteryController.BatteryStateChangeCallback
                public final void onPowerSaveChanged(boolean z) {
                    ((ProducerCoroutine) ProducerScope.this).mo1790trySendJP2dKIU(UiModeNightTileDataInteractor.access$createModel(uiModeNightTileDataInteractor2));
                }
            };
            uiModeNightTileDataInteractor2.batteryController.addCallback(r3);
            final UiModeNightTileDataInteractor uiModeNightTileDataInteractor3 = this.this$0;
            final ?? r4 = new LocationController.LocationChangeCallback() { // from class: com.android.systemui.qs.tiles.impl.uimodenight.domain.interactor.UiModeNightTileDataInteractor$tileData$1$locationCallback$1
                @Override // com.android.systemui.statusbar.policy.LocationController.LocationChangeCallback
                public final void onLocationSettingsChanged(boolean z) {
                    ((ProducerCoroutine) ProducerScope.this).mo1790trySendJP2dKIU(UiModeNightTileDataInteractor.access$createModel(uiModeNightTileDataInteractor3));
                }
            };
            ((LocationControllerImpl) uiModeNightTileDataInteractor3.locationController).addCallback(r4);
            final UiModeNightTileDataInteractor uiModeNightTileDataInteractor4 = this.this$0;
            Function0 function0 = new Function0() { // from class: com.android.systemui.qs.tiles.impl.uimodenight.domain.interactor.UiModeNightTileDataInteractor$tileData$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    ((ConfigurationControllerImpl) UiModeNightTileDataInteractor.this.configurationController).removeCallback(r1);
                    ((BatteryControllerImpl) UiModeNightTileDataInteractor.this.batteryController).removeCallback(r3);
                    ((LocationControllerImpl) UiModeNightTileDataInteractor.this.locationController).removeCallback(r4);
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
