package com.android.systemui.controls.ui;

import android.content.Context;
import android.content.Intent;
import com.android.systemui.controls.management.ControlsProviderSelectorActivity;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class ControlsUiControllerImpl$show$2 extends FunctionReferenceImpl implements Function1 {
    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        List list = (List) obj;
        ControlsUiControllerImpl controlsUiControllerImpl = (ControlsUiControllerImpl) this.receiver;
        controlsUiControllerImpl.getClass();
        if (!list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (((SelectionItem) it.next()).isPanel) {
                    controlsUiControllerImpl.showControlsView(list);
                    break;
                }
            }
        }
        Context context = controlsUiControllerImpl.activityContext;
        if (context == null) {
            context = null;
        }
        Intent intent = new Intent(context, (Class<?>) ControlsProviderSelectorActivity.class);
        intent.putExtra("back_should_exit", true);
        controlsUiControllerImpl.startActivity(intent, true);
        Runnable runnable = controlsUiControllerImpl.onDismiss;
        (runnable != null ? runnable : null).run();
        return Unit.INSTANCE;
    }
}
