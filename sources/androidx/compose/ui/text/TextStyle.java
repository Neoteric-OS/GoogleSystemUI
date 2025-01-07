package androidx.compose.ui.text;

import androidx.compose.foundation.OverscrollConfiguration$$ExternalSyntheticOutline0;
import androidx.compose.material3.internal.DefaultPlatformTextStyle_androidKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.Hyphens;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.text.style.TextForegroundStyle;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.unit.TextUnit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TextStyle {
    public static final TextStyle Default = new TextStyle(0, 0, null, null, 0, 0, 0, 16777215);
    public final ParagraphStyle paragraphStyle;
    public final PlatformTextStyle platformStyle;
    public final SpanStyle spanStyle;

    public TextStyle(SpanStyle spanStyle, ParagraphStyle paragraphStyle, PlatformTextStyle platformTextStyle) {
        this.spanStyle = spanStyle;
        this.paragraphStyle = paragraphStyle;
        this.platformStyle = platformTextStyle;
    }

    /* renamed from: copy-p1EtxEg$default, reason: not valid java name */
    public static TextStyle m604copyp1EtxEg$default(TextStyle textStyle, long j, long j2, FontWeight fontWeight, FontFamily fontFamily, long j3, long j4, LineHeightStyle lineHeightStyle, int i) {
        LocaleList localeList;
        int i2;
        long j5;
        long j6;
        PlatformTextStyle platformTextStyle = DefaultPlatformTextStyle_androidKt.DefaultPlatformTextStyle;
        long mo631getColor0d7_KjU = (i & 1) != 0 ? textStyle.spanStyle.textForegroundStyle.mo631getColor0d7_KjU() : j;
        long j7 = (i & 2) != 0 ? textStyle.spanStyle.fontSize : j2;
        FontWeight fontWeight2 = (i & 4) != 0 ? textStyle.spanStyle.fontWeight : fontWeight;
        SpanStyle spanStyle = textStyle.spanStyle;
        FontStyle fontStyle = spanStyle.fontStyle;
        FontSynthesis fontSynthesis = spanStyle.fontSynthesis;
        FontFamily fontFamily2 = (i & 32) != 0 ? spanStyle.fontFamily : fontFamily;
        String str = spanStyle.fontFeatureSettings;
        long j8 = (i & 128) != 0 ? spanStyle.letterSpacing : j3;
        BaselineShift baselineShift = spanStyle.baselineShift;
        TextGeometricTransform textGeometricTransform = spanStyle.textGeometricTransform;
        LocaleList localeList2 = spanStyle.localeList;
        FontFamily fontFamily3 = fontFamily2;
        long j9 = spanStyle.background;
        TextDecoration textDecoration = spanStyle.textDecoration;
        Shadow shadow = spanStyle.shadow;
        DrawStyle drawStyle = spanStyle.drawStyle;
        if ((i & 32768) != 0) {
            localeList = localeList2;
            i2 = textStyle.paragraphStyle.textAlign;
        } else {
            localeList = localeList2;
            i2 = 3;
        }
        int i3 = i2;
        ParagraphStyle paragraphStyle = textStyle.paragraphStyle;
        int i4 = paragraphStyle.textDirection;
        if ((i & 131072) != 0) {
            j5 = j9;
            j6 = paragraphStyle.lineHeight;
        } else {
            j5 = j9;
            j6 = j4;
        }
        TextIndent textIndent = paragraphStyle.textIndent;
        PlatformTextStyle platformTextStyle2 = (524288 & i) != 0 ? textStyle.platformStyle : platformTextStyle;
        LineHeightStyle lineHeightStyle2 = (i & 1048576) != 0 ? paragraphStyle.lineHeightStyle : lineHeightStyle;
        return new TextStyle(new SpanStyle(Color.m363equalsimpl0(mo631getColor0d7_KjU, spanStyle.textForegroundStyle.mo631getColor0d7_KjU()) ? spanStyle.textForegroundStyle : TextForegroundStyle.Companion.m645from8_81llA(mo631getColor0d7_KjU), j7, fontWeight2, fontStyle, fontSynthesis, fontFamily3, str, j8, baselineShift, textGeometricTransform, localeList, j5, textDecoration, shadow, platformTextStyle2 != null ? platformTextStyle2.spanStyle : null, drawStyle), new ParagraphStyle(i3, i4, j6, textIndent, platformTextStyle2 != null ? platformTextStyle2.paragraphStyle : null, lineHeightStyle2, paragraphStyle.lineBreak, paragraphStyle.hyphens, paragraphStyle.textMotion), platformTextStyle2);
    }

    /* renamed from: merge-dA7vx0o$default, reason: not valid java name */
    public static TextStyle m605mergedA7vx0o$default(TextStyle textStyle, long j, long j2, FontWeight fontWeight, FontStyle fontStyle, FontFamily fontFamily, long j3, TextDecoration textDecoration, int i, long j4, int i2) {
        long j5 = (i2 & 2) != 0 ? TextUnit.Unspecified : j2;
        FontWeight fontWeight2 = (i2 & 4) != 0 ? null : fontWeight;
        FontStyle fontStyle2 = (i2 & 8) != 0 ? null : fontStyle;
        FontFamily fontFamily2 = (i2 & 32) != 0 ? null : fontFamily;
        long j6 = (i2 & 128) != 0 ? TextUnit.Unspecified : j3;
        long j7 = Color.Unspecified;
        TextDecoration textDecoration2 = (i2 & 4096) != 0 ? null : textDecoration;
        int i3 = (32768 & i2) != 0 ? Integer.MIN_VALUE : i;
        long j8 = (i2 & 131072) != 0 ? TextUnit.Unspecified : j4;
        SpanStyle m594fastMergedSHsh3o = SpanStyleKt.m594fastMergedSHsh3o(textStyle.spanStyle, j, null, Float.NaN, j5, fontWeight2, fontStyle2, null, fontFamily2, null, j6, null, null, null, j7, textDecoration2, null, null, null);
        ParagraphStyle m593fastMergej5T8yCg = ParagraphStyleKt.m593fastMergej5T8yCg(textStyle.paragraphStyle, i3, Integer.MIN_VALUE, j8, null, null, null, 0, Integer.MIN_VALUE, null);
        return (textStyle.spanStyle == m594fastMergedSHsh3o && textStyle.paragraphStyle == m593fastMergej5T8yCg) ? textStyle : new TextStyle(m594fastMergedSHsh3o, m593fastMergej5T8yCg);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextStyle)) {
            return false;
        }
        TextStyle textStyle = (TextStyle) obj;
        return Intrinsics.areEqual(this.spanStyle, textStyle.spanStyle) && Intrinsics.areEqual(this.paragraphStyle, textStyle.paragraphStyle) && Intrinsics.areEqual(this.platformStyle, textStyle.platformStyle);
    }

    /* renamed from: getColor-0d7_KjU, reason: not valid java name */
    public final long m606getColor0d7_KjU() {
        return this.spanStyle.textForegroundStyle.mo631getColor0d7_KjU();
    }

    public final boolean hasSameLayoutAffectingAttributes(TextStyle textStyle) {
        if (this != textStyle) {
            if (!Intrinsics.areEqual(this.paragraphStyle, textStyle.paragraphStyle) || !this.spanStyle.hasSameLayoutAffectingAttributes$ui_text_release(textStyle.spanStyle)) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int hashCode = (this.paragraphStyle.hashCode() + (this.spanStyle.hashCode() * 31)) * 31;
        PlatformTextStyle platformTextStyle = this.platformStyle;
        return hashCode + (platformTextStyle != null ? platformTextStyle.hashCode() : 0);
    }

    public final TextStyle merge(TextStyle textStyle) {
        return (textStyle == null || textStyle.equals(Default)) ? this : new TextStyle(this.spanStyle.merge(textStyle.spanStyle), this.paragraphStyle.merge(textStyle.paragraphStyle));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("TextStyle(color=");
        sb.append((Object) Color.m369toStringimpl(m606getColor0d7_KjU()));
        sb.append(", brush=");
        SpanStyle spanStyle = this.spanStyle;
        sb.append(spanStyle.textForegroundStyle.getBrush());
        sb.append(", alpha=");
        sb.append(spanStyle.textForegroundStyle.getAlpha());
        sb.append(", fontSize=");
        sb.append((Object) TextUnit.m690toStringimpl(spanStyle.fontSize));
        sb.append(", fontWeight=");
        sb.append(spanStyle.fontWeight);
        sb.append(", fontStyle=");
        sb.append(spanStyle.fontStyle);
        sb.append(", fontSynthesis=");
        sb.append(spanStyle.fontSynthesis);
        sb.append(", fontFamily=");
        sb.append(spanStyle.fontFamily);
        sb.append(", fontFeatureSettings=");
        sb.append(spanStyle.fontFeatureSettings);
        sb.append(", letterSpacing=");
        sb.append((Object) TextUnit.m690toStringimpl(spanStyle.letterSpacing));
        sb.append(", baselineShift=");
        sb.append(spanStyle.baselineShift);
        sb.append(", textGeometricTransform=");
        sb.append(spanStyle.textGeometricTransform);
        sb.append(", localeList=");
        sb.append(spanStyle.localeList);
        sb.append(", background=");
        OverscrollConfiguration$$ExternalSyntheticOutline0.m(spanStyle.background, ", textDecoration=", sb);
        sb.append(spanStyle.textDecoration);
        sb.append(", shadow=");
        sb.append(spanStyle.shadow);
        sb.append(", drawStyle=");
        sb.append(spanStyle.drawStyle);
        sb.append(", textAlign=");
        ParagraphStyle paragraphStyle = this.paragraphStyle;
        sb.append((Object) TextAlign.m641toStringimpl(paragraphStyle.textAlign));
        sb.append(", textDirection=");
        sb.append((Object) TextDirection.m643toStringimpl(paragraphStyle.textDirection));
        sb.append(", lineHeight=");
        sb.append((Object) TextUnit.m690toStringimpl(paragraphStyle.lineHeight));
        sb.append(", textIndent=");
        sb.append(paragraphStyle.textIndent);
        sb.append(", platformStyle=");
        sb.append(this.platformStyle);
        sb.append(", lineHeightStyle=");
        sb.append(paragraphStyle.lineHeightStyle);
        sb.append(", lineBreak=");
        sb.append((Object) LineBreak.m634toStringimpl(paragraphStyle.lineBreak));
        sb.append(", hyphens=");
        sb.append((Object) Hyphens.m633toStringimpl(paragraphStyle.hyphens));
        sb.append(", textMotion=");
        sb.append(paragraphStyle.textMotion);
        sb.append(')');
        return sb.toString();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public TextStyle(androidx.compose.ui.text.SpanStyle r4, androidx.compose.ui.text.ParagraphStyle r5) {
        /*
            r3 = this;
            androidx.compose.ui.text.PlatformSpanStyle r0 = r4.platformStyle
            androidx.compose.ui.text.PlatformParagraphStyle r1 = r5.platformStyle
            if (r0 != 0) goto La
            if (r1 != 0) goto La
            r0 = 0
            goto L10
        La:
            androidx.compose.ui.text.PlatformTextStyle r2 = new androidx.compose.ui.text.PlatformTextStyle
            r2.<init>(r0, r1)
            r0 = r2
        L10:
            r3.<init>(r4, r5, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.TextStyle.<init>(androidx.compose.ui.text.SpanStyle, androidx.compose.ui.text.ParagraphStyle):void");
    }

    public TextStyle(long j, long j2, FontWeight fontWeight, FontFamily fontFamily, long j3, int i, long j4, int i2) {
        this(new SpanStyle((i2 & 1) != 0 ? Color.Unspecified : j, (i2 & 2) != 0 ? TextUnit.Unspecified : j2, (i2 & 4) != 0 ? null : fontWeight, (FontStyle) null, (FontSynthesis) null, (i2 & 32) != 0 ? null : fontFamily, (String) null, (i2 & 128) != 0 ? TextUnit.Unspecified : j3, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, Color.Unspecified, (TextDecoration) null, (Shadow) null, (PlatformSpanStyle) null), new ParagraphStyle((32768 & i2) != 0 ? Integer.MIN_VALUE : i, Integer.MIN_VALUE, (i2 & 131072) != 0 ? TextUnit.Unspecified : j4, null, null, null, 0, Integer.MIN_VALUE, null), null);
    }
}
