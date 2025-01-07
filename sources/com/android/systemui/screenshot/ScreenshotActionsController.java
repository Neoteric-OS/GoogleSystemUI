package com.android.systemui.screenshot;

import android.app.assist.AssistContent;
import com.android.systemui.screenshot.ui.viewmodel.ActionButtonAppearance;
import com.android.systemui.screenshot.ui.viewmodel.ActionButtonViewModel;
import com.android.systemui.screenshot.ui.viewmodel.ScreenshotViewModel;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$69;
import com.google.android.systemui.screenshot.ScreenshotActionsProviderGoogle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenshotActionsController {
    public final ActionExecutor actionExecutor;
    public final Map actionProviders = new LinkedHashMap();
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$69 actionsProviderFactory;
    public UUID currentScreenshotId;
    public final ScreenshotViewModel viewModel;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ActionsCallback {
        public final UUID screenshotId;

        public ActionsCallback(UUID uuid) {
            this.screenshotId = uuid;
        }

        public final int provideActionButton(ActionButtonAppearance actionButtonAppearance, Function0 function0) {
            UUID uuid = this.screenshotId;
            ScreenshotActionsController screenshotActionsController = ScreenshotActionsController.this;
            if (!uuid.equals(screenshotActionsController.currentScreenshotId)) {
                return 0;
            }
            StateFlowImpl stateFlowImpl = screenshotActionsController.viewModel._actions;
            ArrayList arrayList = new ArrayList((Collection) stateFlowImpl.getValue());
            int i = ActionButtonViewModel.nextId;
            ActionButtonViewModel.nextId = i + 1;
            arrayList.add(new ActionButtonViewModel(actionButtonAppearance, i, true, function0));
            stateFlowImpl.updateState(null, arrayList);
            return i;
        }
    }

    public ScreenshotActionsController(ScreenshotViewModel screenshotViewModel, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$69 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$69, ActionExecutor actionExecutor) {
        this.viewModel = screenshotViewModel;
        this.actionsProviderFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$69;
        this.actionExecutor = actionExecutor;
    }

    public final void onAssistContent(UUID uuid, AssistContent assistContent) {
        ScreenshotActionsProviderGoogle screenshotActionsProviderGoogle = (ScreenshotActionsProviderGoogle) this.actionProviders.get(uuid);
        if (screenshotActionsProviderGoogle != null) {
            screenshotActionsProviderGoogle.onAssistContent(assistContent);
        }
    }
}
