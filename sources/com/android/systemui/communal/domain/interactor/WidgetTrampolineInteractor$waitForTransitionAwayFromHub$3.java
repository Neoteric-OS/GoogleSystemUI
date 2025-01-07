package com.android.systemui.communal.domain.interactor;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WidgetTrampolineInteractor$waitForTransitionAwayFromHub$3 implements FlowCollector {
    public static final WidgetTrampolineInteractor$waitForTransitionAwayFromHub$3 INSTANCE = new WidgetTrampolineInteractor$waitForTransitionAwayFromHub$3();

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
        ((Boolean) obj).getClass();
        return Unit.INSTANCE;
    }
}
