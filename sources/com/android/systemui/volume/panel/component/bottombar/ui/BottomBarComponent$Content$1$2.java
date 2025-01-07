package com.android.systemui.volume.panel.component.bottombar.ui;

import com.android.systemui.volume.panel.component.bottombar.ui.viewmodel.BottomBarViewModel;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final /* synthetic */ class BottomBarComponent$Content$1$2 extends FunctionReferenceImpl implements Function0 {
    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        ((BottomBarViewModel) this.receiver).volumePanelViewModel.volumePanelGlobalStateInteractor.setVisible(false);
        return Unit.INSTANCE;
    }
}
