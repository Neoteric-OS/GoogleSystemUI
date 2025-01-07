package com.android.systemui.statusbar.commandline;

import android.graphics.Color;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import kotlin.Result;
import kotlin.text.StringsKt__StringNumberConversionsJVMKt;
import kotlin.text.StringsKt__StringNumberConversionsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ValueParserKt$parseInt$1 implements ValueParser {
    public final /* synthetic */ int $r8$classId;
    public static final ValueParserKt$parseInt$1 INSTANCE$1 = new ValueParserKt$parseInt$1(1);
    public static final ValueParserKt$parseInt$1 INSTANCE$2 = new ValueParserKt$parseInt$1(2);
    public static final ValueParserKt$parseInt$1 INSTANCE$3 = new ValueParserKt$parseInt$1(3);
    public static final ValueParserKt$parseInt$1 INSTANCE = new ValueParserKt$parseInt$1(0);
    public static final ValueParserKt$parseInt$1 INSTANCE$4 = new ValueParserKt$parseInt$1(4);

    public /* synthetic */ ValueParserKt$parseInt$1(int i) {
        this.$r8$classId = i;
    }

    @Override // com.android.systemui.statusbar.commandline.ValueParser
    /* renamed from: parseValue-IoAF18A */
    public final Object mo801parseValueIoAF18A(String str) {
        switch (this.$r8$classId) {
            case 0:
                Integer intOrNull = StringsKt__StringNumberConversionsKt.toIntOrNull(str);
                return intOrNull != null ? Integer.valueOf(intOrNull.intValue()) : new Result.Failure(new ArgParseError(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Failed to parse ", str, " as an int")));
            case 1:
                Boolean bool = str.equals("true") ? Boolean.TRUE : str.equals("false") ? Boolean.FALSE : null;
                return bool != null ? bool : new Result.Failure(new ArgParseError(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Failed to parse ", str, " as a boolean")));
            case 2:
                try {
                    return Integer.valueOf(Color.parseColor(str));
                } catch (IllegalArgumentException e) {
                    return new Result.Failure(new ArgParseError("Failed to parse " + str + " as a color: " + e));
                }
            case 3:
                Float floatOrNull = StringsKt__StringNumberConversionsJVMKt.toFloatOrNull(str);
                return floatOrNull != null ? Float.valueOf(floatOrNull.floatValue()) : new Result.Failure(new ArgParseError(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Failed to parse ", str, " as a float")));
            default:
                return str;
        }
    }
}
