package androidx.compose.foundation.text;

import androidx.compose.ui.unit.DpKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BasicTextFieldKt {
    public static final /* synthetic */ int $r8$clinit = 0;

    static {
        float f = 40;
        DpKt.m670DpSizeYgX7TsA(f, f);
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x038b  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x039d  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x03a1  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x03b7  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x03bb  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x03d7  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x03de  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x03ee  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x03f8 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:124:0x03e1  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x03da  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x03be  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x03a4  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x038d  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0363  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0324  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x026e  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0275  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x02a0  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x02b3  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x02ba  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x02c8  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x02fc  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x02cb  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0286  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0271  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0475  */
    /* JADX WARN: Removed duplicated region for block: B:75:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0310  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x034c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0360  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x036a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0387  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void BasicTextField(final java.lang.String r41, final kotlin.jvm.functions.Function1 r42, androidx.compose.ui.Modifier r43, boolean r44, boolean r45, androidx.compose.ui.text.TextStyle r46, androidx.compose.foundation.text.KeyboardOptions r47, androidx.compose.foundation.text.KeyboardActions r48, boolean r49, int r50, int r51, androidx.compose.ui.text.input.VisualTransformation r52, kotlin.jvm.functions.Function1 r53, androidx.compose.foundation.interaction.MutableInteractionSource r54, androidx.compose.ui.graphics.Brush r55, kotlin.jvm.functions.Function3 r56, androidx.compose.runtime.Composer r57, final int r58, final int r59, final int r60) {
        /*
            Method dump skipped, instructions count: 1167
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.BasicTextFieldKt.BasicTextField(java.lang.String, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.ui.text.TextStyle, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, boolean, int, int, androidx.compose.ui.text.input.VisualTransformation, kotlin.jvm.functions.Function1, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Brush, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }
}
