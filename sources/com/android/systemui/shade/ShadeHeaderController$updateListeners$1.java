package com.android.systemui.shade;

import androidx.constraintlayout.widget.ConstraintSet;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShadeHeaderController$updateListeners$1 {
    public final /* synthetic */ ShadeHeaderController this$0;

    public /* synthetic */ ShadeHeaderController$updateListeners$1(ShadeHeaderController shadeHeaderController) {
        this.this$0 = shadeHeaderController;
    }

    public void onChipVisibilityRefreshed(boolean z) {
        ShadeHeaderController shadeHeaderController = this.this$0;
        shadeHeaderController.combinedShadeHeadersConstraintManager.getClass();
        final float f = z ? 0.0f : 1.0f;
        ShadeHeaderController.updateConstraints(shadeHeaderController.header, R.id.qqs_header_constraint, new Function1() { // from class: com.android.systemui.shade.CombinedShadeHeadersConstraintManagerImpl$privacyChipVisibilityConstraints$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ((ConstraintSet) obj).setAlpha(R.id.shade_header_system_icons, f);
                return Unit.INSTANCE;
            }
        });
    }
}
