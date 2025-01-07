package com.android.systemui.statusbar.notification.domain.interactor;

import android.os.Build;
import android.util.Log;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import com.android.systemui.statusbar.notification.HeadsUpManagerPhone;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.EmptySet;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HeadsUpNotificationInteractor {
    public final FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 canShowHeadsUp;
    public final HeadsUpManagerPhone headsUpRepository;
    public final Lazy isHeadsUpOrAnimatingAway$delegate;
    public final StateFlowImpl topHeadsUpRow;
    public final Flow topHeadsUpRowIfPinned;

    public HeadsUpNotificationInteractor(HeadsUpManagerPhone headsUpManagerPhone, DeviceEntryFaceAuthInteractor deviceEntryFaceAuthInteractor, KeyguardTransitionInteractor keyguardTransitionInteractor, NotificationsKeyguardInteractor notificationsKeyguardInteractor, ShadeInteractor shadeInteractor) {
        FlowKt.distinctUntilChanged(FlowKt.transformLatest(headsUpManagerPhone.mTopHeadsUpRow, new HeadsUpNotificationInteractor$special$$inlined$flatMapLatest$1(3, null)));
        LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.notification.domain.interactor.HeadsUpNotificationInteractor$pinnedHeadsUpRows$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                if (Log.isLoggable("RefactorFlagAssert", 7)) {
                    Log.wtf("RefactorFlagAssert", "New code path expects SceneContainerFlag to be enabled.", Build.isDebuggable() ? new IllegalStateException("New code path expects SceneContainerFlag to be enabled.") : null);
                } else if (Log.isLoggable("RefactorFlag", 5)) {
                    Log.w("RefactorFlag", "New code path expects SceneContainerFlag to be enabled.");
                }
                return new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(EmptySet.INSTANCE);
            }
        });
        LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.notification.domain.interactor.HeadsUpNotificationInteractor$hasPinnedRows$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                if (Log.isLoggable("RefactorFlagAssert", 7)) {
                    Log.wtf("RefactorFlagAssert", "New code path expects SceneContainerFlag to be enabled.", Build.isDebuggable() ? new IllegalStateException("New code path expects SceneContainerFlag to be enabled.") : null);
                } else if (Log.isLoggable("RefactorFlag", 5)) {
                    Log.w("RefactorFlag", "New code path expects SceneContainerFlag to be enabled.");
                }
                return new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(Boolean.FALSE);
            }
        });
        LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.notification.domain.interactor.HeadsUpNotificationInteractor$isHeadsUpOrAnimatingAway$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                if (Log.isLoggable("RefactorFlagAssert", 7)) {
                    Log.wtf("RefactorFlagAssert", "New code path expects SceneContainerFlag to be enabled.", Build.isDebuggable() ? new IllegalStateException("New code path expects SceneContainerFlag to be enabled.") : null);
                } else if (Log.isLoggable("RefactorFlag", 5)) {
                    Log.w("RefactorFlag", "New code path expects SceneContainerFlag to be enabled.");
                }
                return new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(Boolean.FALSE);
            }
        });
        deviceEntryFaceAuthInteractor.isBypassEnabled();
        ReadonlyStateFlow readonlyStateFlow = keyguardTransitionInteractor.currentKeyguardState;
        new HeadsUpNotificationInteractor$canShowHeadsUp$1(5, null);
        Flow flow = ((ShadeInteractorImpl) shadeInteractor).isShadeFullyCollapsed;
        StateFlowImpl stateFlowImpl = notificationsKeyguardInteractor.areNotificationsFullyHidden;
        LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.notification.domain.interactor.HeadsUpNotificationInteractor$showHeadsUpStatusBar$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                if (Log.isLoggable("RefactorFlagAssert", 7)) {
                    Log.wtf("RefactorFlagAssert", "New code path expects SceneContainerFlag to be enabled.", Build.isDebuggable() ? new IllegalStateException("New code path expects SceneContainerFlag to be enabled.") : null);
                } else if (Log.isLoggable("RefactorFlag", 5)) {
                    Log.w("RefactorFlag", "New code path expects SceneContainerFlag to be enabled.");
                }
                return new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(Boolean.FALSE);
            }
        });
    }
}
