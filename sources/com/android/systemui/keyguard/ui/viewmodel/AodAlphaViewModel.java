package com.android.systemui.keyguard.ui.viewmodel;

import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$4;
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AodAlphaViewModel {
    public final SafeFlow alpha;

    public AodAlphaViewModel(KeyguardTransitionInteractor keyguardTransitionInteractor, GoneToAodTransitionViewModel goneToAodTransitionViewModel, GoneToDozingTransitionViewModel goneToDozingTransitionViewModel, KeyguardInteractor keyguardInteractor) {
        FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new AodAlphaViewModel$alpha$5(2, null), goneToAodTransitionViewModel.enterFromTopAnimationAlpha);
        FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$12 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new AodAlphaViewModel$alpha$6(2, null), goneToDozingTransitionViewModel.lockscreenAlpha);
        FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$13 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new AodAlphaViewModel$alpha$7(2, null), keyguardInteractor.keyguardAlpha);
        this.alpha = new SafeFlow(new FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$4(new Flow[]{keyguardTransitionInteractor.transitions, flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1, flowKt__EmittersKt$onStart$$inlined$unsafeFlow$12, flowKt__EmittersKt$onStart$$inlined$unsafeFlow$13}, null, new AodAlphaViewModel$alpha$8(null)));
    }
}
