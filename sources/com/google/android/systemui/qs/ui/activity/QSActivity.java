package com.google.android.systemui.qs.ui.activity;

import android.os.Bundle;
import androidx.activity.ComponentActivity;
import com.android.systemui.qs.ui.viewmodel.QuickSettingsContainerViewModel;
import com.google.android.systemui.qs.ui.viewmodel.GridLayoutSelectorViewModel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSActivity extends ComponentActivity {
    public final GridLayoutSelectorViewModel gridLayoutSelectorViewModel;
    public final QuickSettingsContainerViewModel qsContainerViewModel;

    public QSActivity(QuickSettingsContainerViewModel quickSettingsContainerViewModel, GridLayoutSelectorViewModel gridLayoutSelectorViewModel) {
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        finish();
    }
}
