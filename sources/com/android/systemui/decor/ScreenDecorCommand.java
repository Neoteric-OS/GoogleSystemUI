package com.android.systemui.decor;

import android.graphics.Color;
import com.android.systemui.ScreenDecorations$$ExternalSyntheticLambda0;
import com.android.systemui.statusbar.commandline.ArgParseError;
import com.android.systemui.statusbar.commandline.OptionalSubCommand;
import com.android.systemui.statusbar.commandline.ParseableCommand;
import com.android.systemui.statusbar.commandline.SingleArgParamOptional;
import com.android.systemui.statusbar.commandline.Type;
import com.android.systemui.statusbar.commandline.ValueParser;
import com.android.systemui.statusbar.commandline.ValueParserKt$parseInt$1;
import java.io.PrintWriter;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ScreenDecorCommand extends ParseableCommand {
    public static final /* synthetic */ KProperty[] $$delegatedProperties;
    public final ScreenDecorations$$ExternalSyntheticLambda0 callback;
    public final SingleArgParamOptional color$delegate;
    public final SingleArgParamOptional debug$delegate;
    public final OptionalSubCommand roundedBottom$delegate;
    public final OptionalSubCommand roundedTop$delegate;

    static {
        PropertyReference1Impl propertyReference1Impl = new PropertyReference1Impl(ScreenDecorCommand.class, "debug", "getDebug()Ljava/lang/Boolean;", 0);
        Reflection.factory.getClass();
        $$delegatedProperties = new KProperty[]{propertyReference1Impl, new PropertyReference1Impl(ScreenDecorCommand.class, "color", "getColor()Ljava/lang/Integer;", 0), new PropertyReference1Impl(ScreenDecorCommand.class, "roundedTop", "getRoundedTop()Lcom/android/systemui/decor/RoundedCornerSubCommand;", 0), new PropertyReference1Impl(ScreenDecorCommand.class, "roundedBottom", "getRoundedBottom()Lcom/android/systemui/decor/RoundedCornerSubCommand;", 0)};
    }

    public ScreenDecorCommand(ScreenDecorations$$ExternalSyntheticLambda0 screenDecorations$$ExternalSyntheticLambda0) {
        super("screen-decor");
        this.callback = screenDecorations$$ExternalSyntheticLambda0;
        this.debug$delegate = param("debug", null, "Enter or exits debug mode. Effectively makes the corners visible and allows for overriding the path data for the anti-aliasing corner paths and display cutout.", Type.Boolean);
        final ValueParserKt$parseInt$1 valueParserKt$parseInt$1 = Type.String;
        this.color$delegate = param("color", "c", "Set a specific color for the debug assets. See Color#parseString() for accepted inputs.", new ValueParser() { // from class: com.android.systemui.decor.ScreenDecorCommand$special$$inlined$map$1
            @Override // com.android.systemui.statusbar.commandline.ValueParser
            /* renamed from: parseValue-IoAF18A */
            public final Object mo801parseValueIoAF18A(String str) {
                Integer num;
                ValueParserKt$parseInt$1.this.getClass();
                ResultKt.throwOnFailure(str);
                try {
                    num = Integer.valueOf(Color.parseColor(str));
                } catch (Exception unused) {
                    num = null;
                }
                return num != null ? num : new Result.Failure(new ArgParseError("Failed to transform value ".concat(str)));
            }
        });
        this.roundedTop$delegate = subCommand(new RoundedCornerSubCommand("rounded-top"));
        this.roundedBottom$delegate = subCommand(new RoundedCornerSubCommand("rounded-bottom"));
    }

    @Override // com.android.systemui.statusbar.commandline.ParseableCommand
    public final void execute(PrintWriter printWriter) {
        this.callback.onExecute(this);
    }

    public final Integer getColor() {
        return (Integer) this.color$delegate.getValue(this, $$delegatedProperties[1]);
    }

    public final String toString() {
        KProperty[] kPropertyArr = $$delegatedProperties;
        Boolean bool = (Boolean) this.debug$delegate.getValue(this, kPropertyArr[0]);
        Integer color = getColor();
        KProperty kProperty = kPropertyArr[2];
        OptionalSubCommand optionalSubCommand = this.roundedTop$delegate;
        RoundedCornerSubCommand roundedCornerSubCommand = (RoundedCornerSubCommand) (optionalSubCommand.isPresent ? optionalSubCommand.cmd : null);
        KProperty kProperty2 = kPropertyArr[3];
        OptionalSubCommand optionalSubCommand2 = this.roundedBottom$delegate;
        return "ScreenDecorCommand(debug=" + bool + ", color=" + color + ", roundedTop=" + roundedCornerSubCommand + ", roundedBottom=" + ((RoundedCornerSubCommand) (optionalSubCommand2.isPresent ? optionalSubCommand2.cmd : null)) + ")";
    }
}
