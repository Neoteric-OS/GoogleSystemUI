package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingValuesImpl;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TextFieldDefaults {
    public static final TextFieldDefaults INSTANCE = new TextFieldDefaults();
    public static final float UnfocusedIndicatorThickness = 1;
    public static final float FocusedIndicatorThickness = 2;

    public static TextFieldColors colors(int i, Composer composer) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        return getDefaultTextFieldColors(MaterialTheme.getColorScheme(composer), composer);
    }

    /* renamed from: contentPaddingWithoutLabel-a9UjIt4$default, reason: not valid java name */
    public static PaddingValuesImpl m237contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults textFieldDefaults) {
        float f = TextFieldImplKt.TextFieldPadding;
        textFieldDefaults.getClass();
        return new PaddingValuesImpl(f, f, f, f);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x00de  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static androidx.compose.material3.TextFieldColors getDefaultTextFieldColors(androidx.compose.material3.ColorScheme r96, androidx.compose.runtime.Composer r97) {
        /*
            Method dump skipped, instructions count: 486
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextFieldDefaults.getDefaultTextFieldColors(androidx.compose.material3.ColorScheme, androidx.compose.runtime.Composer):androidx.compose.material3.TextFieldColors");
    }

    /* renamed from: supportingTextPadding-a9UjIt4$material3_release$default, reason: not valid java name */
    public static PaddingValuesImpl m238supportingTextPaddinga9UjIt4$material3_release$default() {
        float f = TextFieldImplKt.TextFieldPadding;
        return new PaddingValuesImpl(f, TextFieldImplKt.SupportingTopPadding, f, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:60:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x016e  */
    /* renamed from: Container-4EFweAY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m239Container4EFweAY(final boolean r23, final boolean r24, final androidx.compose.foundation.interaction.InteractionSource r25, androidx.compose.ui.Modifier r26, androidx.compose.material3.TextFieldColors r27, androidx.compose.ui.graphics.Shape r28, float r29, float r30, androidx.compose.runtime.Composer r31, final int r32, final int r33) {
        /*
            Method dump skipped, instructions count: 633
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextFieldDefaults.m239Container4EFweAY(boolean, boolean, androidx.compose.foundation.interaction.InteractionSource, androidx.compose.ui.Modifier, androidx.compose.material3.TextFieldColors, androidx.compose.ui.graphics.Shape, float, float, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x039f  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x03aa  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x03b4  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x03e2  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x03e8  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x03ac  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x03a1  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x02d0  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x02dd  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x02e4  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0310  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0323  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0337  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0365  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0390  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x035b  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0331  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0307  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x02fc  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x02f5  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x02ee  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x02e7  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x02e0  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x02d9  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x02d2  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0217  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x048a  */
    /* JADX WARN: Removed duplicated region for block: B:93:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x028e  */
    /* JADX WARN: Type inference failed for: r1v5, types: [androidx.compose.material3.TextFieldDefaults$DecorationBox$1, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r2v2, types: [androidx.compose.material3.TextFieldDefaults$DecorationBox$2$1, kotlin.jvm.internal.Lambda] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void DecorationBox(final java.lang.String r45, final kotlin.jvm.functions.Function2 r46, final boolean r47, final boolean r48, final androidx.compose.ui.text.input.VisualTransformation r49, final androidx.compose.foundation.interaction.InteractionSource r50, boolean r51, kotlin.jvm.functions.Function2 r52, kotlin.jvm.functions.Function2 r53, kotlin.jvm.functions.Function2 r54, kotlin.jvm.functions.Function2 r55, kotlin.jvm.functions.Function2 r56, kotlin.jvm.functions.Function2 r57, kotlin.jvm.functions.Function2 r58, androidx.compose.ui.graphics.Shape r59, androidx.compose.material3.TextFieldColors r60, androidx.compose.foundation.layout.PaddingValues r61, kotlin.jvm.functions.Function2 r62, androidx.compose.runtime.Composer r63, final int r64, final int r65, final int r66) {
        /*
            Method dump skipped, instructions count: 1209
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextFieldDefaults.DecorationBox(java.lang.String, kotlin.jvm.functions.Function2, boolean, boolean, androidx.compose.ui.text.input.VisualTransformation, androidx.compose.foundation.interaction.InteractionSource, boolean, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.ui.graphics.Shape, androidx.compose.material3.TextFieldColors, androidx.compose.foundation.layout.PaddingValues, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int, int):void");
    }
}
