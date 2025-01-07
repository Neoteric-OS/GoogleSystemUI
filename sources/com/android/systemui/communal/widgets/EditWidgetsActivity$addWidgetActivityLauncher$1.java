package com.android.systemui.communal.widgets;

import android.content.ComponentName;
import android.content.Intent;
import android.os.UserHandle;
import android.util.Log;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.compose.foundation.text.input.internal.RecordingInputConnection$$ExternalSyntheticOutline0;
import com.android.systemui.communal.shared.log.CommunalUiEvent;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class EditWidgetsActivity$addWidgetActivityLauncher$1 implements ActivityResultCallback {
    public final /* synthetic */ EditWidgetsActivity this$0;

    public EditWidgetsActivity$addWidgetActivityLauncher$1(EditWidgetsActivity editWidgetsActivity) {
        this.this$0 = editWidgetsActivity;
    }

    @Override // androidx.activity.result.ActivityResultCallback
    public final void onActivityResult(Object obj) {
        ActivityResult activityResult = (ActivityResult) obj;
        int i = activityResult.resultCode;
        if (i != -1) {
            RecordingInputConnection$$ExternalSyntheticOutline0.m("Failed to receive result from widget picker, code=", "EditWidgetsActivity", i);
            return;
        }
        EditWidgetsActivity editWidgetsActivity = this.this$0;
        editWidgetsActivity.uiEventLogger.log(CommunalUiEvent.COMMUNAL_HUB_WIDGET_PICKER_SHOWN);
        Intent intent = activityResult.data;
        if (intent == null) {
            Log.w("EditWidgetsActivity", "No data in result.");
            return;
        }
        if (intent.getBooleanExtra("is_pending_widget_drag", false)) {
            return;
        }
        ComponentName componentName = (ComponentName) intent.getParcelableExtra("android.intent.extra.COMPONENT_NAME", ComponentName.class);
        UserHandle userHandle = (UserHandle) intent.getParcelableExtra("android.intent.extra.USER", UserHandle.class);
        if (componentName == null || userHandle == null) {
            Log.w("EditWidgetsActivity", "No AppWidgetProviderInfo found in result.");
        } else {
            editWidgetsActivity.communalViewModel.onAddWidget(componentName, userHandle, null, (WidgetConfigurationController) editWidgetsActivity.widgetConfigurator$delegate.getValue());
        }
    }
}
