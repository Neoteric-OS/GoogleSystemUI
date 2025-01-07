package com.android.systemui.dreams.ui.viewmodel;

import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.common.ui.domain.interactor.ConfigurationInteractor;
import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.keyguard.domain.interactor.FromDreamingTransitionInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.shared.model.Edge;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.keyguard.ui.KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1;
import com.android.systemui.keyguard.ui.viewmodel.DreamingToGlanceableHubTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.DreamingToLockscreenTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.GlanceableHubToDreamingTransitionViewModel;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.util.kotlin.FlowDumperImpl;
import com.android.wm.shell.R;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DreamViewModel extends FlowDumperImpl {
    public final CommunalInteractor communalInteractor;
    public final SafeFlow dreamAlpha;
    public final Flow dreamOverlayAlpha;
    public final Flow dreamOverlayTranslationX;
    public final ChannelFlowTransformLatest dreamOverlayTranslationY;
    public final FromDreamingTransitionInteractor fromDreamingTransitionInteractor;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    public final DreamingToLockscreenTransitionViewModel toLockscreenTransitionViewModel;
    public final DreamViewModel$special$$inlined$filter$1 transitionEnded;
    public final UserTracker userTracker;

    public DreamViewModel(ConfigurationInteractor configurationInteractor, KeyguardTransitionInteractor keyguardTransitionInteractor, GlanceableHubToDreamingTransitionViewModel glanceableHubToDreamingTransitionViewModel, DreamingToGlanceableHubTransitionViewModel dreamingToGlanceableHubTransitionViewModel, DreamingToLockscreenTransitionViewModel dreamingToLockscreenTransitionViewModel, FromDreamingTransitionInteractor fromDreamingTransitionInteractor, CommunalInteractor communalInteractor, KeyguardUpdateMonitor keyguardUpdateMonitor, UserTracker userTracker, DumpManager dumpManager) {
        super(dumpManager);
        this.toLockscreenTransitionViewModel = dreamingToLockscreenTransitionViewModel;
        this.fromDreamingTransitionInteractor = fromDreamingTransitionInteractor;
        this.communalInteractor = communalInteractor;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.userTracker = userTracker;
        this.dreamOverlayTranslationX = FlowKt.distinctUntilChanged(FlowKt.merge(dreamingToGlanceableHubTransitionViewModel.dreamOverlayTranslationX, glanceableHubToDreamingTransitionViewModel.dreamOverlayTranslationX));
        this.dreamOverlayTranslationY = FlowKt.transformLatest(configurationInteractor.dimensionPixelSize(R.dimen.dream_overlay_exit_y_offset), new DreamViewModel$special$$inlined$flatMapLatest$1(null, this));
        KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1 keyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1 = dreamingToLockscreenTransitionViewModel.dreamOverlayAlpha;
        this.dreamAlpha = (SafeFlow) dumpWhileCollecting(FlowKt.distinctUntilChanged(FlowKt.merge(keyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1, dreamingToGlanceableHubTransitionViewModel.dreamAlpha)), "dreamAlpha");
        this.dreamOverlayAlpha = FlowKt.distinctUntilChanged(FlowKt.merge(keyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1, dreamingToGlanceableHubTransitionViewModel.dreamOverlayAlpha, glanceableHubToDreamingTransitionViewModel.dreamOverlayAlpha));
        Edge.Companion companion = Edge.Companion;
        this.transitionEnded = new DreamViewModel$special$$inlined$filter$1(keyguardTransitionInteractor.transition(Edge.Companion.create$default(KeyguardState.DREAMING, null, 2)));
    }
}
