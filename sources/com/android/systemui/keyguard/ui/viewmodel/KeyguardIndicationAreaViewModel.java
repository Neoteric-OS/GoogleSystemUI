package com.android.systemui.keyguard.ui.viewmodel;

import com.android.systemui.common.ui.domain.interactor.ConfigurationInteractor;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.doze.util.BurnInHelperWrapper;
import com.android.systemui.keyguard.domain.interactor.BurnInInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.util.kotlin.BooleanFlowOperators$not$$inlined$map$1;
import com.android.wm.shell.R;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardIndicationAreaViewModel {
    public final ChannelFlowTransformLatest alpha;
    public final Flow burnIn;
    public final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 configurationChange;
    public final Flow indicationAreaTranslationX;
    public final Flow isIndicationAreaPadded;
    public final CoroutineDispatcher mainDispatcher;
    public final Flow visible;

    public KeyguardIndicationAreaViewModel(KeyguardInteractor keyguardInteractor, KeyguardBottomAreaViewModel keyguardBottomAreaViewModel, BurnInHelperWrapper burnInHelperWrapper, BurnInInteractor burnInInteractor, KeyguardQuickAffordancesCombinedViewModel keyguardQuickAffordancesCombinedViewModel, ConfigurationInteractor configurationInteractor, KeyguardTransitionInteractor keyguardTransitionInteractor, CommunalSceneInteractor communalSceneInteractor, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2) {
        int i = 2;
        this.mainDispatcher = coroutineDispatcher2;
        this.configurationChange = configurationInteractor.onAnyConfigurationChange;
        ChannelFlowTransformLatest channelFlowTransformLatest = keyguardBottomAreaViewModel.alpha;
        this.visible = FlowKt.distinctUntilChanged(new BooleanFlowOperators$not$$inlined$map$1(i, (Flow[]) CollectionsKt.toList(ArraysKt.asIterable(new Flow[]{new KeyguardIndicationAreaViewModel$special$$inlined$map$2(keyguardInteractor.statusBarState, i), communalSceneInteractor.isCommunalVisible})).toArray(new Flow[0])));
        this.isIndicationAreaPadded = FlowKt.distinctUntilChanged(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(keyguardQuickAffordancesCombinedViewModel.startButton, keyguardQuickAffordancesCombinedViewModel.endButton, new KeyguardIndicationAreaViewModel$isIndicationAreaPadded$1(3, null)));
        Flow flowOn = FlowKt.flowOn(FlowKt.distinctUntilChanged(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(burnInInteractor.burnIn(R.dimen.default_burn_in_prevention_offset), keyguardTransitionInteractor.getTransitionValueFlow(KeyguardState.AOD), new KeyguardIndicationAreaViewModel$burnIn$1(3, null))), coroutineDispatcher);
        this.burnIn = flowOn;
        this.indicationAreaTranslationX = FlowKt.flowOn(new KeyguardIndicationAreaViewModel$special$$inlined$map$2(flowOn, 0), coroutineDispatcher2);
    }
}
