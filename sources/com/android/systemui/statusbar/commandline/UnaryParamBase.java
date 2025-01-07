package com.android.systemui.statusbar.commandline;

import android.util.IndentingPrintWriter;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class UnaryParamBase implements Param {
    public boolean handled;
    public final MultipleArgParam wrapped;

    public UnaryParamBase(MultipleArgParam multipleArgParam) {
        this.wrapped = multipleArgParam;
    }

    @Override // com.android.systemui.statusbar.commandline.Describable
    public final void describe(final IndentingPrintWriter indentingPrintWriter) {
        if (getShortName() != null) {
            indentingPrintWriter.print(getShortName() + ", ");
        }
        indentingPrintWriter.print(getLongName());
        MultipleArgParam multipleArgParam = this.wrapped;
        ValueParserKt$parseInt$1 valueParserKt$parseInt$1 = Type.Int;
        ValueParser valueParser = multipleArgParam.valueParser;
        indentingPrintWriter.println(" ".concat(Intrinsics.areEqual(valueParser, valueParserKt$parseInt$1) ? "<int>" : Intrinsics.areEqual(valueParser, Type.Float) ? "<float>" : Intrinsics.areEqual(valueParser, Type.String) ? "<string>" : Intrinsics.areEqual(valueParser, Type.Boolean) ? "<boolean>" : "<arg>"));
        if (getDescription() != null) {
            ParseableCommandKt.indented(indentingPrintWriter, new Function0() { // from class: com.android.systemui.statusbar.commandline.UnaryParamBase$describe$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    indentingPrintWriter.println(this.getDescription());
                    return Unit.INSTANCE;
                }
            });
        }
    }
}
