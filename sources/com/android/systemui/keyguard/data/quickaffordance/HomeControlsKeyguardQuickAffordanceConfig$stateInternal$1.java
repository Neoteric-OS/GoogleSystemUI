package com.android.systemui.keyguard.data.quickaffordance;

import com.android.systemui.biometrics.data.repository.FacePropertyRepositoryImpl$cameraInfo$1$callback$1$$ExternalSyntheticOutline0;
import com.android.systemui.common.shared.model.ContentDescription;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.controls.ControlsServiceInfo;
import com.android.systemui.controls.controller.ControlsController;
import com.android.systemui.controls.controller.Favorites;
import com.android.systemui.controls.dagger.ControlsComponent;
import com.android.systemui.controls.management.ControlsListingController;
import com.android.systemui.controls.management.ControlsListingControllerImpl;
import com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig;
import java.util.Iterator;
import java.util.List;
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
final class HomeControlsKeyguardQuickAffordanceConfig$stateInternal$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ ControlsListingController $listingController;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ HomeControlsKeyguardQuickAffordanceConfig this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeControlsKeyguardQuickAffordanceConfig$stateInternal$1(ControlsListingController controlsListingController, HomeControlsKeyguardQuickAffordanceConfig homeControlsKeyguardQuickAffordanceConfig, Continuation continuation) {
        super(2, continuation);
        this.$listingController = controlsListingController;
        this.this$0 = homeControlsKeyguardQuickAffordanceConfig;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        HomeControlsKeyguardQuickAffordanceConfig$stateInternal$1 homeControlsKeyguardQuickAffordanceConfig$stateInternal$1 = new HomeControlsKeyguardQuickAffordanceConfig$stateInternal$1(this.$listingController, this.this$0, continuation);
        homeControlsKeyguardQuickAffordanceConfig$stateInternal$1.L$0 = obj;
        return homeControlsKeyguardQuickAffordanceConfig$stateInternal$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((HomeControlsKeyguardQuickAffordanceConfig$stateInternal$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.controls.management.ControlsListingController$ControlsListingCallback, com.android.systemui.keyguard.data.quickaffordance.HomeControlsKeyguardQuickAffordanceConfig$stateInternal$1$callback$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final HomeControlsKeyguardQuickAffordanceConfig homeControlsKeyguardQuickAffordanceConfig = this.this$0;
            final ?? r1 = new ControlsListingController.ControlsListingCallback() { // from class: com.android.systemui.keyguard.data.quickaffordance.HomeControlsKeyguardQuickAffordanceConfig$stateInternal$1$callback$1
                @Override // com.android.systemui.controls.management.ControlsListingController.ControlsListingCallback
                public final void onServicesUpdated(List list) {
                    HomeControlsKeyguardQuickAffordanceConfig homeControlsKeyguardQuickAffordanceConfig2 = HomeControlsKeyguardQuickAffordanceConfig.this;
                    List allStructures = ((ControlsController) homeControlsKeyguardQuickAffordanceConfig2.component.controlsController.orElse(null)) != null ? Favorites.getAllStructures() : null;
                    ControlsComponent controlsComponent = homeControlsKeyguardQuickAffordanceConfig2.component;
                    boolean z = controlsComponent.featureEnabled;
                    boolean z2 = false;
                    boolean z3 = allStructures != null && (allStructures.isEmpty() ^ true);
                    if (!list.isEmpty()) {
                        Iterator it = list.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            } else if (((ControlsServiceInfo) it.next()).panelActivity != null) {
                                z2 = true;
                                break;
                            }
                        }
                    }
                    Object mo1790trySendJP2dKIU = ((ProducerCoroutine) producerScope)._channel.mo1790trySendJP2dKIU((z && (z3 || z2) && !list.isEmpty() && controlsComponent.getVisibility() == ControlsComponent.Visibility.AVAILABLE) ? new KeyguardQuickAffordanceConfig.LockScreenState.Visible(new Icon.Resource(controlsComponent.controlsTileResourceConfiguration.getTileImageId(), new ContentDescription.Resource(controlsComponent.controlsTileResourceConfiguration.getTileTitleId()))) : KeyguardQuickAffordanceConfig.LockScreenState.Hidden.INSTANCE);
                    if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                        FacePropertyRepositoryImpl$cameraInfo$1$callback$1$$ExternalSyntheticOutline0.m("Failed to send ", "updated state", " - downstream canceled or failed.", "HomeControlsKeyguardQuickAffordanceConfig", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                    }
                }
            };
            ControlsListingControllerImpl controlsListingControllerImpl = (ControlsListingControllerImpl) this.$listingController;
            controlsListingControllerImpl.getClass();
            controlsListingControllerImpl.addCallback((ControlsListingController.ControlsListingCallback) r1);
            final ControlsListingController controlsListingController = this.$listingController;
            Function0 function0 = new Function0() { // from class: com.android.systemui.keyguard.data.quickaffordance.HomeControlsKeyguardQuickAffordanceConfig$stateInternal$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    ((ControlsListingControllerImpl) ControlsListingController.this).removeCallback(r1);
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
