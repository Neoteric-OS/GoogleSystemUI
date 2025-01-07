package androidx.compose.ui.text;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.text.style.TextForegroundStyle;
import androidx.compose.ui.unit.InlineClassHelperKt;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.unit.TextUnitType;
import androidx.compose.ui.util.MathHelpersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SpanStyleKt {
    public static final long DefaultFontSize = TextUnitKt.getSp(14);
    public static final long DefaultLetterSpacing = TextUnitKt.getSp(0);
    public static final long DefaultBackgroundColor = Color.Transparent;
    public static final TextForegroundStyle DefaultColorForegroundStyle = TextForegroundStyle.Companion.m645from8_81llA(Color.Black);

    /* JADX WARN: Removed duplicated region for block: B:14:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0119  */
    /* renamed from: fastMerge-dSHsh3o, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.ui.text.SpanStyle m594fastMergedSHsh3o(androidx.compose.ui.text.SpanStyle r24, long r25, androidx.compose.ui.graphics.Brush r27, float r28, long r29, androidx.compose.ui.text.font.FontWeight r31, androidx.compose.ui.text.font.FontStyle r32, androidx.compose.ui.text.font.FontSynthesis r33, androidx.compose.ui.text.font.FontFamily r34, java.lang.String r35, long r36, androidx.compose.ui.text.style.BaselineShift r38, androidx.compose.ui.text.style.TextGeometricTransform r39, androidx.compose.ui.text.intl.LocaleList r40, long r41, androidx.compose.ui.text.style.TextDecoration r43, androidx.compose.ui.graphics.Shadow r44, androidx.compose.ui.text.PlatformSpanStyle r45, androidx.compose.ui.graphics.drawscope.DrawStyle r46) {
        /*
            Method dump skipped, instructions count: 413
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.SpanStyleKt.m594fastMergedSHsh3o(androidx.compose.ui.text.SpanStyle, long, androidx.compose.ui.graphics.Brush, float, long, androidx.compose.ui.text.font.FontWeight, androidx.compose.ui.text.font.FontStyle, androidx.compose.ui.text.font.FontSynthesis, androidx.compose.ui.text.font.FontFamily, java.lang.String, long, androidx.compose.ui.text.style.BaselineShift, androidx.compose.ui.text.style.TextGeometricTransform, androidx.compose.ui.text.intl.LocaleList, long, androidx.compose.ui.text.style.TextDecoration, androidx.compose.ui.graphics.Shadow, androidx.compose.ui.text.PlatformSpanStyle, androidx.compose.ui.graphics.drawscope.DrawStyle):androidx.compose.ui.text.SpanStyle");
    }

    public static final Object lerpDiscrete(float f, Object obj, Object obj2) {
        return ((double) f) < 0.5d ? obj : obj2;
    }

    /* renamed from: lerpTextUnitInheritable-C3pnCVY, reason: not valid java name */
    public static final long m595lerpTextUnitInheritableC3pnCVY(float f, long j, long j2) {
        TextUnitType[] textUnitTypeArr = TextUnit.TextUnitTypes;
        long j3 = j & 1095216660480L;
        if (j3 != 0) {
            long j4 = 1095216660480L & j2;
            if (j4 != 0) {
                if (j3 == 0 || j4 == 0) {
                    InlineClassHelperKt.throwIllegalArgumentException("Cannot perform operation for Unspecified type.");
                }
                if (!TextUnitType.m691equalsimpl0(TextUnit.m687getTypeUIouoOA(j), TextUnit.m687getTypeUIouoOA(j2))) {
                    InlineClassHelperKt.throwIllegalArgumentException("Cannot perform operation for " + ((Object) TextUnitType.m692toStringimpl(TextUnit.m687getTypeUIouoOA(j))) + " and " + ((Object) TextUnitType.m692toStringimpl(TextUnit.m687getTypeUIouoOA(j2))));
                }
                return TextUnitKt.pack(MathHelpersKt.lerp(TextUnit.m688getValueimpl(j), TextUnit.m688getValueimpl(j2), f), j3);
            }
        }
        return ((TextUnit) lerpDiscrete(f, new TextUnit(j), new TextUnit(j2))).packedValue;
    }
}
