package com.android.systemui.communal.widgets;

import androidx.lifecycle.LifecycleKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class EditWidgetsActivity$onCreate$1$1$1$1 extends FunctionReferenceImpl implements Function0 {
    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        EditWidgetsActivity editWidgetsActivity = (EditWidgetsActivity) this.receiver;
        int i = EditWidgetsActivity.$r8$clinit;
        editWidgetsActivity.getClass();
        BuildersKt.launch$default(LifecycleKt.getCoroutineScope(editWidgetsActivity.getLifecycle()), null, null, new EditWidgetsActivity$onOpenWidgetPicker$1(editWidgetsActivity, null), 3);
        return Unit.INSTANCE;
    }
}
