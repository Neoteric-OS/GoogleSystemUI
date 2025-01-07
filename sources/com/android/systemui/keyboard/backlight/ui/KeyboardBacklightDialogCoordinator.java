package com.android.systemui.keyboard.backlight.ui;

import android.content.Context;
import com.android.systemui.keyboard.backlight.ui.view.KeyboardBacklightDialog;
import com.android.systemui.keyboard.backlight.ui.viewmodel.BacklightDialogViewModel;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyboardBacklightDialogCoordinator {
    public final CoroutineScope applicationScope;
    public final Function2 createDialog;
    public KeyboardBacklightDialog dialog;
    public final BacklightDialogViewModel viewModel;

    public KeyboardBacklightDialogCoordinator(CoroutineScope coroutineScope, final Context context, BacklightDialogViewModel backlightDialogViewModel) {
        Function2 function2 = new Function2() { // from class: com.android.systemui.keyboard.backlight.ui.KeyboardBacklightDialogCoordinatorKt$defaultCreateDialog$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return new KeyboardBacklightDialog(((Number) obj).intValue(), ((Number) obj2).intValue(), context);
            }
        };
        this.applicationScope = coroutineScope;
        this.viewModel = backlightDialogViewModel;
        this.createDialog = function2;
    }

    public final void startListening() {
        BuildersKt.launch$default(this.applicationScope, null, null, new KeyboardBacklightDialogCoordinator$startListening$1(this, null), 3);
    }
}
