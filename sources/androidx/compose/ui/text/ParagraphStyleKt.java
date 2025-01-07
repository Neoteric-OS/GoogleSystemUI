package androidx.compose.ui.text;

import androidx.compose.ui.text.style.Hyphens;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitType;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ParagraphStyleKt {
    public static final /* synthetic */ int $r8$clinit = 0;
    public static final long DefaultLineHeight;

    static {
        TextUnitType[] textUnitTypeArr = TextUnit.TextUnitTypes;
        DefaultLineHeight = TextUnit.Unspecified;
    }

    /* renamed from: fastMerge-j5T8yCg, reason: not valid java name */
    public static final ParagraphStyle m593fastMergej5T8yCg(ParagraphStyle paragraphStyle, int i, int i2, long j, TextIndent textIndent, PlatformParagraphStyle platformParagraphStyle, LineHeightStyle lineHeightStyle, int i3, int i4, TextMotion textMotion) {
        int i5 = i;
        int i6 = i2;
        long j2 = j;
        TextIndent textIndent2 = textIndent;
        PlatformParagraphStyle platformParagraphStyle2 = platformParagraphStyle;
        LineHeightStyle lineHeightStyle2 = lineHeightStyle;
        int i7 = i3;
        int i8 = i4;
        TextMotion textMotion2 = textMotion;
        if (TextAlign.m640equalsimpl0(i5, Integer.MIN_VALUE) || TextAlign.m640equalsimpl0(i5, paragraphStyle.textAlign)) {
            TextUnitType[] textUnitTypeArr = TextUnit.TextUnitTypes;
            if (((j2 & 1095216660480L) == 0 || TextUnit.m686equalsimpl0(j2, paragraphStyle.lineHeight)) && ((textIndent2 == null || textIndent2.equals(paragraphStyle.textIndent)) && ((TextDirection.m642equalsimpl0(i6, Integer.MIN_VALUE) || TextDirection.m642equalsimpl0(i6, paragraphStyle.textDirection)) && ((platformParagraphStyle2 == null || platformParagraphStyle2.equals(paragraphStyle.platformStyle)) && ((lineHeightStyle2 == null || lineHeightStyle2.equals(paragraphStyle.lineHeightStyle)) && ((i7 == 0 || i7 == paragraphStyle.lineBreak) && ((Hyphens.m632equalsimpl0(i8, Integer.MIN_VALUE) || Hyphens.m632equalsimpl0(i8, paragraphStyle.hyphens)) && (textMotion2 == null || textMotion2.equals(paragraphStyle.textMotion))))))))) {
                return paragraphStyle;
            }
        }
        TextUnitType[] textUnitTypeArr2 = TextUnit.TextUnitTypes;
        if ((j2 & 1095216660480L) == 0) {
            j2 = paragraphStyle.lineHeight;
        }
        if (textIndent2 == null) {
            textIndent2 = paragraphStyle.textIndent;
        }
        if (TextAlign.m640equalsimpl0(i5, Integer.MIN_VALUE)) {
            i5 = paragraphStyle.textAlign;
        }
        if (TextDirection.m642equalsimpl0(i6, Integer.MIN_VALUE)) {
            i6 = paragraphStyle.textDirection;
        }
        PlatformParagraphStyle platformParagraphStyle3 = paragraphStyle.platformStyle;
        if (platformParagraphStyle3 != null && platformParagraphStyle2 == null) {
            platformParagraphStyle2 = platformParagraphStyle3;
        }
        if (lineHeightStyle2 == null) {
            lineHeightStyle2 = paragraphStyle.lineHeightStyle;
        }
        if (i7 == 0) {
            i7 = paragraphStyle.lineBreak;
        }
        if (Hyphens.m632equalsimpl0(i8, Integer.MIN_VALUE)) {
            i8 = paragraphStyle.hyphens;
        }
        if (textMotion2 == null) {
            textMotion2 = paragraphStyle.textMotion;
        }
        return new ParagraphStyle(i5, i6, j2, textIndent2, platformParagraphStyle2, lineHeightStyle2, i7, i8, textMotion2);
    }
}
