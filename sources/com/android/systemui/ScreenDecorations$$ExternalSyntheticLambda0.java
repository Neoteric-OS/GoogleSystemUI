package com.android.systemui;

import com.android.systemui.decor.DebugRoundedCornerModel;
import com.android.systemui.decor.RoundedCornerSubCommand;
import com.android.systemui.decor.ScreenDecorCommand;
import com.android.systemui.statusbar.commandline.OptionalSubCommand;
import com.android.systemui.statusbar.commandline.SingleArgParamOptional;
import kotlin.reflect.KProperty;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class ScreenDecorations$$ExternalSyntheticLambda0 {
    public final /* synthetic */ ScreenDecorations f$0;

    public /* synthetic */ ScreenDecorations$$ExternalSyntheticLambda0(ScreenDecorations screenDecorations) {
        this.f$0 = screenDecorations;
    }

    public final void onExecute(ScreenDecorCommand screenDecorCommand) {
        DebugRoundedCornerModel debugRoundedCornerModel;
        ScreenDecorations screenDecorations = this.f$0;
        screenDecorations.getClass();
        screenDecorCommand.getClass();
        KProperty[] kPropertyArr = ScreenDecorCommand.$$delegatedProperties;
        KProperty kProperty = kPropertyArr[0];
        SingleArgParamOptional singleArgParamOptional = screenDecorCommand.debug$delegate;
        if (((Boolean) singleArgParamOptional.getValue(screenDecorCommand, kProperty)) != null && !((Boolean) singleArgParamOptional.getValue(screenDecorCommand, kPropertyArr[0])).booleanValue()) {
            screenDecorations.setDebug(false);
            return;
        }
        screenDecorations.setDebug(true);
        if (screenDecorCommand.getColor() != null) {
            screenDecorations.mDebugColor = screenDecorCommand.getColor().intValue();
            screenDecorations.mExecutor.execute(new ScreenDecorations$$ExternalSyntheticLambda7(screenDecorations, screenDecorCommand, 1));
        }
        KProperty kProperty2 = kPropertyArr[2];
        OptionalSubCommand optionalSubCommand = screenDecorCommand.roundedTop$delegate;
        boolean z = optionalSubCommand.isPresent;
        DebugRoundedCornerModel debugRoundedCornerModel2 = null;
        if (((RoundedCornerSubCommand) (z ? optionalSubCommand.cmd : null)) != null) {
            debugRoundedCornerModel = ((RoundedCornerSubCommand) (z ? optionalSubCommand.cmd : null)).toRoundedCornerDebugModel();
        } else {
            debugRoundedCornerModel = null;
        }
        KProperty kProperty3 = kPropertyArr[3];
        OptionalSubCommand optionalSubCommand2 = screenDecorCommand.roundedBottom$delegate;
        boolean z2 = optionalSubCommand2.isPresent;
        if (((RoundedCornerSubCommand) (z2 ? optionalSubCommand2.cmd : null)) != null) {
            debugRoundedCornerModel2 = ((RoundedCornerSubCommand) (z2 ? optionalSubCommand2.cmd : null)).toRoundedCornerDebugModel();
        }
        if (debugRoundedCornerModel == null && debugRoundedCornerModel2 == null) {
            return;
        }
        screenDecorations.mDebugRoundedCornerDelegate.applyNewDebugCorners(debugRoundedCornerModel, debugRoundedCornerModel2);
        screenDecorations.mExecutor.execute(new ScreenDecorations$$ExternalSyntheticLambda2(screenDecorations, 1));
    }
}
