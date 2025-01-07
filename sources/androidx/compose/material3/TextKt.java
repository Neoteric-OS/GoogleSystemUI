package androidx.compose.material3;

import androidx.compose.material3.tokens.TypographyTokensKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.DynamicProvidableCompositionLocal;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.text.TextStyle;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TextKt {
    public static final DynamicProvidableCompositionLocal LocalTextStyle = new DynamicProvidableCompositionLocal(new Function0() { // from class: androidx.compose.material3.TextKt$LocalTextStyle$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return TypographyTokensKt.DefaultTextStyle;
        }
    });

    public static final void ProvideTextStyle(final TextStyle textStyle, final Function2 function2, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-460300127);
        if ((i & 6) == 0) {
            i2 = (composerImpl.changed(textStyle) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerImpl.changedInstance(function2) ? 32 : 16;
        }
        if ((i2 & 19) == 18 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            DynamicProvidableCompositionLocal dynamicProvidableCompositionLocal = LocalTextStyle;
            CompositionLocalKt.CompositionLocalProvider(dynamicProvidableCompositionLocal.defaultProvidedValue$runtime_release(((TextStyle) composerImpl.consume(dynamicProvidableCompositionLocal)).merge(textStyle)), function2, composerImpl, (i2 & 112) | 8);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.material3.TextKt$ProvideTextStyle$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    TextKt.ProvideTextStyle(TextStyle.this, function2, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x02c9  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x02d0  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x02dc  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x02ef  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x02f8  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x02fd  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x030e  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x02df  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x02d8  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x02d2  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x02cc  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x03c5  */
    /* JADX WARN: Removed duplicated region for block: B:78:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x034a  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x034d  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0325  */
    /* renamed from: Text--4IGK_g, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m241Text4IGK_g(final java.lang.String r45, androidx.compose.ui.Modifier r46, long r47, long r49, androidx.compose.ui.text.font.FontStyle r51, androidx.compose.ui.text.font.FontWeight r52, androidx.compose.ui.text.font.FontFamily r53, long r54, androidx.compose.ui.text.style.TextDecoration r56, androidx.compose.ui.text.style.TextAlign r57, long r58, int r60, boolean r61, int r62, int r63, kotlin.jvm.functions.Function1 r64, androidx.compose.ui.text.TextStyle r65, androidx.compose.runtime.Composer r66, final int r67, final int r68, final int r69) {
        /*
            Method dump skipped, instructions count: 994
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextKt.m241Text4IGK_g(java.lang.String, androidx.compose.ui.Modifier, long, long, androidx.compose.ui.text.font.FontStyle, androidx.compose.ui.text.font.FontWeight, androidx.compose.ui.text.font.FontFamily, long, androidx.compose.ui.text.style.TextDecoration, androidx.compose.ui.text.style.TextAlign, long, int, boolean, int, int, kotlin.jvm.functions.Function1, androidx.compose.ui.text.TextStyle, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0380  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0358  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x02c0  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x02dd  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x02e4  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x02fd  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0304  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x030a  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0310  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0319  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0331  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0322  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x02f4  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x02ee  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x02e7  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x02e0  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x02d9  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x02ca  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x040a  */
    /* JADX WARN: Removed duplicated region for block: B:83:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0286  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0354  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x037d  */
    /* renamed from: Text-IbK3jfQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m242TextIbK3jfQ(final androidx.compose.ui.text.AnnotatedString r47, androidx.compose.ui.Modifier r48, long r49, long r51, androidx.compose.ui.text.font.FontStyle r53, androidx.compose.ui.text.font.FontWeight r54, androidx.compose.ui.text.font.FontFamily r55, long r56, androidx.compose.ui.text.style.TextDecoration r58, androidx.compose.ui.text.style.TextAlign r59, long r60, int r62, boolean r63, int r64, int r65, java.util.Map r66, kotlin.jvm.functions.Function1 r67, androidx.compose.ui.text.TextStyle r68, androidx.compose.runtime.Composer r69, final int r70, final int r71, final int r72) {
        /*
            Method dump skipped, instructions count: 1077
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextKt.m242TextIbK3jfQ(androidx.compose.ui.text.AnnotatedString, androidx.compose.ui.Modifier, long, long, androidx.compose.ui.text.font.FontStyle, androidx.compose.ui.text.font.FontWeight, androidx.compose.ui.text.font.FontFamily, long, androidx.compose.ui.text.style.TextDecoration, androidx.compose.ui.text.style.TextAlign, long, int, boolean, int, int, java.util.Map, kotlin.jvm.functions.Function1, androidx.compose.ui.text.TextStyle, androidx.compose.runtime.Composer, int, int, int):void");
    }
}
