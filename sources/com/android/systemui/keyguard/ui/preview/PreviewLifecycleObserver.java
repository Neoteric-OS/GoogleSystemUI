package com.android.systemui.keyguard.ui.preview;

import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import com.android.systemui.keyguard.shared.quickaffordance.KeyguardQuickAffordancePosition;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardQuickAffordancesCombinedViewModel;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PreviewLifecycleObserver implements Handler.Callback, IBinder.DeathRecipient {
    public boolean isDestroyedOrDestroying;
    public final CoroutineDispatcher mainDispatcher;
    public Function1 onDestroy;
    public KeyguardPreviewRenderer renderer;
    public final CoroutineScope scope;

    public PreviewLifecycleObserver(CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, KeyguardPreviewRenderer keyguardPreviewRenderer, Function1 function1) {
        this.scope = coroutineScope;
        this.mainDispatcher = coroutineDispatcher;
        this.renderer = keyguardPreviewRenderer;
        this.onDestroy = function1;
    }

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
        Function1 function1 = this.onDestroy;
        if (function1 != null) {
            function1.invoke(this);
        }
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        Function1 function1;
        if (this.isDestroyedOrDestroying) {
            return true;
        }
        final KeyguardPreviewRenderer keyguardPreviewRenderer = this.renderer;
        if (keyguardPreviewRenderer == null || (function1 = this.onDestroy) == null) {
            Log.wtf("KeyguardRemotePreviewManager", "Renderer/onDestroy should not be null.");
            return true;
        }
        int i = message.what;
        KeyguardQuickAffordancesCombinedViewModel keyguardQuickAffordancesCombinedViewModel = keyguardPreviewRenderer.quickAffordancesCombinedViewModel;
        if (i == 214) {
            String string = message.getData().getString("initially_selected_slot_id");
            keyguardQuickAffordancesCombinedViewModel.enablePreviewMode(string != null ? string : "bottom_start", true);
        } else if (i == 707) {
            Map emptyMap = MapsKt.emptyMap();
            StateFlowImpl stateFlowImpl = keyguardQuickAffordancesCombinedViewModel.previewAffordances;
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, emptyMap);
            keyguardQuickAffordancesCombinedViewModel.enablePreviewMode(null, false);
        } else if (i == 1111) {
            final boolean z = message.getData().getBoolean("hide_smart_space");
            keyguardPreviewRenderer.mainHandler.post(new Runnable() { // from class: com.android.systemui.keyguard.ui.preview.KeyguardPreviewRenderer$hideSmartspace$1
                @Override // java.lang.Runnable
                public final void run() {
                    View view = KeyguardPreviewRenderer.this.smartSpaceView;
                    if (view == null) {
                        return;
                    }
                    view.setVisibility(z ? 4 : 0);
                }
            });
        } else if (i == 1337) {
            String string2 = message.getData().getString("slot_id");
            if (string2 != null) {
                KeyguardPreviewRenderer keyguardPreviewRenderer2 = this.renderer;
                if (keyguardPreviewRenderer2 == null) {
                    throw new IllegalStateException("Required value was null.");
                }
                StateFlowImpl stateFlowImpl2 = keyguardPreviewRenderer2.quickAffordancesCombinedViewModel.selectedPreviewSlotId;
                stateFlowImpl2.getClass();
                stateFlowImpl2.updateState(null, string2);
            }
        } else if (i != 1988) {
            ((KeyguardRemotePreviewManager$preview$1) function1).invoke(this);
        } else {
            String string3 = message.getData().getString("slot_id");
            String string4 = message.getData().getString("quick_affordance_id");
            if (string3 != null && string4 != null) {
                KeyguardPreviewRenderer keyguardPreviewRenderer3 = this.renderer;
                if (keyguardPreviewRenderer3 == null) {
                    throw new IllegalStateException("Required value was null.");
                }
                KeyguardQuickAffordancesCombinedViewModel keyguardQuickAffordancesCombinedViewModel2 = keyguardPreviewRenderer3.quickAffordancesCombinedViewModel;
                KeyguardQuickAffordancePosition.Companion.getClass();
                KeyguardQuickAffordancePosition keyguardQuickAffordancePosition = string3.equals("bottom_start") ? KeyguardQuickAffordancePosition.BOTTOM_START : string3.equals("bottom_end") ? KeyguardQuickAffordancePosition.BOTTOM_END : null;
                if (keyguardQuickAffordancePosition != null) {
                    StateFlowImpl stateFlowImpl3 = keyguardQuickAffordancesCombinedViewModel2.previewAffordances;
                    LinkedHashMap linkedHashMap = new LinkedHashMap((Map) stateFlowImpl3.getValue());
                    linkedHashMap.put(keyguardQuickAffordancePosition, string4);
                    stateFlowImpl3.updateState(null, new HashMap(linkedHashMap));
                }
            }
        }
        return true;
    }

    public static /* synthetic */ void getOnDestroy$annotations() {
    }

    public static /* synthetic */ void getRenderer$annotations() {
    }
}
