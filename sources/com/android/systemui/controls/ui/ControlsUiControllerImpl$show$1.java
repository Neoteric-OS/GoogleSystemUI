package com.android.systemui.controls.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class ControlsUiControllerImpl$show$1 extends FunctionReferenceImpl implements Function1 {
    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ControlsUiControllerImpl controlsUiControllerImpl = (ControlsUiControllerImpl) this.receiver;
        LayoutInflater from = LayoutInflater.from(controlsUiControllerImpl.context);
        ViewGroup viewGroup = controlsUiControllerImpl.parent;
        if (viewGroup == null) {
            viewGroup = null;
        }
        from.inflate(R.layout.controls_no_favorites, viewGroup, true);
        ViewGroup viewGroup2 = controlsUiControllerImpl.parent;
        ((TextView) (viewGroup2 != null ? viewGroup2 : null).requireViewById(R.id.controls_subtitle)).setText(controlsUiControllerImpl.context.getResources().getString(R.string.controls_seeding_in_progress));
        return Unit.INSTANCE;
    }
}
