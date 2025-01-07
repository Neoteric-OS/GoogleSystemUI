package com.android.systemui.statusbar;

import android.content.Context;
import android.util.IndentingPrintWriter;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.SplitShadeStateControllerImpl;
import com.android.wm.shell.R;
import kotlin.jvm.functions.Function0;
import kotlin.text.StringsKt__IndentKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LockscreenShadeQsTransitionController extends AbstractLockscreenShadeTransitionController {
    public boolean isTransitioningToFullShade;
    public float qsDragDownAmount;
    public final Function0 qsProvider;
    public float qsSquishStartFraction;
    public int qsSquishTransitionDistance;
    public float qsSquishTransitionFraction;
    public int qsTransitionDistance;
    public float qsTransitionFraction;
    public int qsTransitionStartDelay;

    public LockscreenShadeQsTransitionController(Context context, ConfigurationController configurationController, DumpManager dumpManager, Function0 function0, SplitShadeStateControllerImpl splitShadeStateControllerImpl) {
        super(context, configurationController, dumpManager, splitShadeStateControllerImpl);
        this.qsProvider = function0;
    }

    @Override // com.android.systemui.statusbar.AbstractLockscreenShadeTransitionController
    public final void dump(IndentingPrintWriter indentingPrintWriter) {
        int i = this.qsTransitionDistance;
        int i2 = this.qsTransitionStartDelay;
        int i3 = this.qsSquishTransitionDistance;
        float f = this.qsSquishStartFraction;
        float f2 = this.dragDownAmount;
        float f3 = this.qsDragDownAmount;
        float f4 = this.qsTransitionFraction;
        float f5 = this.qsSquishTransitionFraction;
        boolean z = this.isTransitioningToFullShade;
        StringBuilder m = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(i, i2, "\n            Resources:\n              qsTransitionDistance: ", "\n              qsTransitionStartDelay: ", "\n              qsSquishTransitionDistance: ");
        m.append(i3);
        m.append("\n              qsSquishStartFraction: ");
        m.append(f);
        m.append("\n            State:\n              dragDownAmount: ");
        m.append(f2);
        m.append("\n              qsDragDownAmount: ");
        m.append(f3);
        m.append("\n              qsDragFraction: ");
        m.append(f4);
        m.append("\n              qsSquishFraction: ");
        m.append(f5);
        m.append("\n              isTransitioningToFullShade: ");
        m.append(z);
        m.append("\n        ");
        indentingPrintWriter.println(StringsKt__IndentKt.trimIndent(m.toString()));
    }

    @Override // com.android.systemui.statusbar.AbstractLockscreenShadeTransitionController
    public final void updateResources() {
        this.qsTransitionDistance = this.context.getResources().getDimensionPixelSize(R.dimen.lockscreen_shade_qs_transition_distance);
        this.qsTransitionStartDelay = this.context.getResources().getDimensionPixelSize(R.dimen.lockscreen_shade_qs_transition_delay);
        this.qsSquishTransitionDistance = this.context.getResources().getDimensionPixelSize(R.dimen.lockscreen_shade_qs_squish_transition_distance);
        float f = this.context.getResources().getFloat(R.dimen.lockscreen_shade_qs_squish_start_fraction);
        this.qsSquishStartFraction = f;
        this.qsSquishTransitionFraction = Math.max(this.qsSquishTransitionFraction, f);
    }
}
