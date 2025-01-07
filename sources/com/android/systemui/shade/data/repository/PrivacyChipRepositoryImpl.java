package com.android.systemui.shade.data.repository;

import android.content.IntentFilter;
import android.os.UserHandle;
import android.safetycenter.SafetyCenterManager;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.privacy.PrivacyConfig;
import com.android.systemui.privacy.PrivacyItemController;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedEagerly;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PrivacyChipRepositoryImpl {
    public final ReadonlyStateFlow isLocationIndicationEnabled;
    public final ReadonlyStateFlow isMicCameraIndicationEnabled;
    public final ReadonlyStateFlow isSafetyCenterEnabled;
    public final PrivacyConfig privacyConfig;
    public final PrivacyItemController privacyItemController;
    public final ReadonlyStateFlow privacyItems;
    public final SafetyCenterManager safetyCenterManager;

    public PrivacyChipRepositoryImpl(CoroutineScope coroutineScope, PrivacyConfig privacyConfig, PrivacyItemController privacyItemController, CoroutineDispatcher coroutineDispatcher, BroadcastDispatcher broadcastDispatcher, SafetyCenterManager safetyCenterManager) {
        this.privacyConfig = privacyConfig;
        this.privacyItemController = privacyItemController;
        this.safetyCenterManager = safetyCenterManager;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.safetycenter.action.SAFETY_CENTER_ENABLED_CHANGED");
        FlowKt.stateIn(FlowKt.flowOn(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new PrivacyChipRepositoryImpl$isSafetyCenterEnabled$3(this, null), BroadcastDispatcher.broadcastFlow$default(broadcastDispatcher, intentFilter, UserHandle.SYSTEM, new Function2() { // from class: com.android.systemui.shade.data.repository.PrivacyChipRepositoryImpl$isSafetyCenterEnabled$2
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(PrivacyChipRepositoryImpl.this.safetyCenterManager.isSafetyCenterEnabled());
            }
        }, 12)), coroutineDispatcher), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.FALSE);
        Flow conflatedCallbackFlow = FlowConflatedKt.conflatedCallbackFlow(new PrivacyChipRepositoryImpl$privacyItems$1(this, null));
        StartedEagerly startedEagerly = SharingStarted.Companion.Eagerly;
        this.privacyItems = FlowKt.stateIn(conflatedCallbackFlow, coroutineScope, startedEagerly, EmptyList.INSTANCE);
        Flow conflatedCallbackFlow2 = FlowConflatedKt.conflatedCallbackFlow(new PrivacyChipRepositoryImpl$isMicCameraIndicationEnabled$1(this, null));
        PrivacyConfig privacyConfig2 = privacyItemController.privacyConfig;
        this.isMicCameraIndicationEnabled = FlowKt.stateIn(conflatedCallbackFlow2, coroutineScope, startedEagerly, Boolean.valueOf(privacyConfig2.micCameraAvailable));
        this.isLocationIndicationEnabled = FlowKt.stateIn(FlowConflatedKt.conflatedCallbackFlow(new PrivacyChipRepositoryImpl$isLocationIndicationEnabled$1(this, null)), coroutineScope, startedEagerly, Boolean.valueOf(privacyConfig2.locationAvailable));
    }
}
