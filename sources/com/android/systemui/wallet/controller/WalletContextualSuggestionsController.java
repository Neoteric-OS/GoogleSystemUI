package com.android.systemui.wallet.controller;

import android.content.IntentFilter;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.FeatureFlagsClassicRelease;
import com.android.systemui.flags.Flags;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.collections.EmptyList;
import kotlin.collections.EmptySet;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WalletContextualSuggestionsController {
    public final StateFlowImpl _suggestionCardIds;
    public final ReadonlyStateFlow allWalletCards;
    public final CoroutineScope applicationCoroutineScope;
    public final Set cardsReceivedCallbacks = new LinkedHashSet();
    public final ReadonlyStateFlow contextualSuggestionsCardIds;
    public final QuickAccessWalletController walletController;

    public WalletContextualSuggestionsController(CoroutineScope coroutineScope, QuickAccessWalletController quickAccessWalletController, BroadcastDispatcher broadcastDispatcher, FeatureFlags featureFlags) {
        this.applicationCoroutineScope = coroutineScope;
        this.walletController = quickAccessWalletController;
        ReadonlyStateFlow stateIn = ((FeatureFlagsClassicRelease) featureFlags).isEnabled(Flags.ENABLE_WALLET_CONTEXTUAL_LOYALTY_CARDS) ? FlowKt.stateIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.transformLatest(BroadcastDispatcher.broadcastFlow$default(broadcastDispatcher, new IntentFilter("android.intent.action.SCREEN_ON"), null, 14), new WalletContextualSuggestionsController$special$$inlined$flatMapLatest$1(this, null)), new WalletContextualSuggestionsController$allWalletCards$2(this, null), 0), coroutineScope, SharingStarted.Companion.Eagerly, EmptyList.INSTANCE) : new ReadonlyStateFlow(StateFlowKt.MutableStateFlow(EmptyList.INSTANCE));
        this.allWalletCards = stateIn;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(EmptySet.INSTANCE);
        this._suggestionCardIds = MutableStateFlow;
        FlowKt.stateIn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(stateIn, new ReadonlyStateFlow(MutableStateFlow), new WalletContextualSuggestionsController$contextualSuggestionCards$1(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), EmptyList.INSTANCE);
    }
}
