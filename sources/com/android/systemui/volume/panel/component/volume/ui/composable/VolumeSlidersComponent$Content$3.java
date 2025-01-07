package com.android.systemui.volume.panel.component.volume.ui.composable;

import com.android.systemui.volume.panel.component.volume.ui.viewmodel.AudioVolumeComponentViewModel;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final /* synthetic */ class VolumeSlidersComponent$Content$3 extends FunctionReferenceImpl implements Function1 {
    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ((AudioVolumeComponentViewModel) this.receiver).onExpandedChanged(((Boolean) obj).booleanValue());
        return Unit.INSTANCE;
    }
}
