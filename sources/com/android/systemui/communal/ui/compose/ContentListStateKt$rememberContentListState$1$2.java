package com.android.systemui.communal.ui.compose;

import android.content.ComponentName;
import com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class ContentListStateKt$rememberContentListState$1$2 extends FunctionReferenceImpl implements Function3 {
    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        int intValue = ((Number) obj3).intValue();
        ((BaseCommunalViewModel) this.receiver).onDeleteWidget(((Number) obj).intValue(), (ComponentName) obj2, intValue);
        return Unit.INSTANCE;
    }
}
