package com.android.systemui.keyguard.ui.binder;

import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import com.android.systemui.common.ui.view.LongPressHandlingView;
import com.android.systemui.keyguard.domain.interactor.KeyguardTouchHandlingInteractor;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardTouchHandlingViewModel;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.shade.NotificationPanelViewController$$ExternalSyntheticLambda22;
import com.android.wm.shell.R;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class KeyguardLongPressViewBinder {
    public static final void bind(LongPressHandlingView longPressHandlingView, final KeyguardTouchHandlingViewModel keyguardTouchHandlingViewModel, final NotificationPanelViewController$$ExternalSyntheticLambda22 notificationPanelViewController$$ExternalSyntheticLambda22, final FalsingManager falsingManager) {
        longPressHandlingView.accessibilityHintLongPressAction = new AccessibilityNodeInfo.AccessibilityAction(32, longPressHandlingView.getResources().getString(R.string.lock_screen_settings));
        longPressHandlingView.listener = new LongPressHandlingView.Listener() { // from class: com.android.systemui.keyguard.ui.binder.KeyguardLongPressViewBinder$bind$1
            @Override // com.android.systemui.common.ui.view.LongPressHandlingView.Listener
            public final void onLongPressDetected(View view, boolean z) {
                if (z || !FalsingManager.this.isFalseLongTap(1)) {
                    KeyguardTouchHandlingInteractor keyguardTouchHandlingInteractor = keyguardTouchHandlingViewModel.interactor;
                    if (((Boolean) ((StateFlowImpl) keyguardTouchHandlingInteractor.isLongPressHandlingEnabled.$$delegate_0).getValue()).booleanValue()) {
                        if (z) {
                            Boolean bool = Boolean.TRUE;
                            StateFlowImpl stateFlowImpl = keyguardTouchHandlingInteractor._shouldOpenSettings;
                            stateFlowImpl.getClass();
                            stateFlowImpl.updateState(null, bool);
                            return;
                        }
                        Boolean bool2 = Boolean.TRUE;
                        StateFlowImpl stateFlowImpl2 = keyguardTouchHandlingInteractor._isMenuVisible;
                        stateFlowImpl2.getClass();
                        stateFlowImpl2.updateState(null, bool2);
                        keyguardTouchHandlingInteractor.scheduleAutomaticMenuHiding();
                        keyguardTouchHandlingInteractor.logger.log(KeyguardTouchHandlingInteractor.LogEvents.LOCK_SCREEN_LONG_PRESS_POPUP_SHOWN);
                    }
                }
            }

            @Override // com.android.systemui.common.ui.view.LongPressHandlingView.Listener
            public final void onSingleTapDetected() {
                if (FalsingManager.this.isFalseTap(1)) {
                    return;
                }
                notificationPanelViewController$$ExternalSyntheticLambda22.invoke();
            }
        };
        RepeatWhenAttachedKt.repeatWhenAttached(longPressHandlingView, EmptyCoroutineContext.INSTANCE, new KeyguardLongPressViewBinder$bind$2(keyguardTouchHandlingViewModel, longPressHandlingView, null));
    }
}
