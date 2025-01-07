package androidx.compose.ui.text.platform.extensions;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.LocaleSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.ShaderBrush;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.AnnotatedStringKt;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.android.style.BaselineShiftSpan;
import androidx.compose.ui.text.android.style.FontFeatureSpan;
import androidx.compose.ui.text.android.style.LetterSpacingSpanEm;
import androidx.compose.ui.text.android.style.LetterSpacingSpanPx;
import androidx.compose.ui.text.android.style.ShadowSpan;
import androidx.compose.ui.text.android.style.SkewXSpan;
import androidx.compose.ui.text.android.style.TextDecorationSpan;
import androidx.compose.ui.text.android.style.TypefaceSpan;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.Locale;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.platform.style.DrawStyleSpan;
import androidx.compose.ui.text.platform.style.ShaderBrushSpan;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextForegroundStyle;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.math.MathKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SpannableExtensions_androidKt {
    /* renamed from: resolveLineHeightInPx-o2QH7mI, reason: not valid java name */
    public static final float m628resolveLineHeightInPxo2QH7mI(long j, float f, Density density) {
        float m688getValueimpl;
        long m687getTypeUIouoOA = TextUnit.m687getTypeUIouoOA(j);
        if (TextUnitType.m691equalsimpl0(m687getTypeUIouoOA, 4294967296L)) {
            if (density.getFontScale() <= 1.05d) {
                return density.mo50toPxR2X_6o(j);
            }
            m688getValueimpl = TextUnit.m688getValueimpl(j) / TextUnit.m688getValueimpl(density.mo54toSpkPz2Gy4(f));
        } else {
            if (!TextUnitType.m691equalsimpl0(m687getTypeUIouoOA, 8589934592L)) {
                return Float.NaN;
            }
            m688getValueimpl = TextUnit.m688getValueimpl(j);
        }
        return m688getValueimpl * f;
    }

    /* renamed from: setColor-RPmYEkk, reason: not valid java name */
    public static final void m629setColorRPmYEkk(Spannable spannable, long j, int i, int i2) {
        if (j != 16) {
            setSpan(spannable, new ForegroundColorSpan(ColorKt.m373toArgb8_81llA(j)), i, i2);
        }
    }

    /* renamed from: setFontSize-KmRG4DE, reason: not valid java name */
    public static final void m630setFontSizeKmRG4DE(Spannable spannable, long j, Density density, int i, int i2) {
        long m687getTypeUIouoOA = TextUnit.m687getTypeUIouoOA(j);
        if (TextUnitType.m691equalsimpl0(m687getTypeUIouoOA, 4294967296L)) {
            setSpan(spannable, new AbsoluteSizeSpan(MathKt.roundToInt(density.mo50toPxR2X_6o(j)), false), i, i2);
        } else if (TextUnitType.m691equalsimpl0(m687getTypeUIouoOA, 8589934592L)) {
            setSpan(spannable, new RelativeSizeSpan(TextUnit.m688getValueimpl(j)), i, i2);
        }
    }

    public static final void setLocaleList(Spannable spannable, LocaleList localeList, int i, int i2) {
        if (localeList != null) {
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(localeList, 10));
            Iterator it = localeList.localeList.iterator();
            while (it.hasNext()) {
                arrayList.add(((Locale) it.next()).platformLocale);
            }
            java.util.Locale[] localeArr = (java.util.Locale[]) arrayList.toArray(new java.util.Locale[0]);
            setSpan(spannable, new LocaleSpan(new android.os.LocaleList((java.util.Locale[]) Arrays.copyOf(localeArr, localeArr.length))), i, i2);
        }
    }

    public static final void setSpan(Spannable spannable, Object obj, int i, int i2) {
        spannable.setSpan(obj, i, i2, 33);
    }

    public static final void setSpanStyles(final Spannable spannable, TextStyle textStyle, List list, Density density, final Function4 function4) {
        int i;
        int i2;
        int i3;
        int i4;
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        int i5 = 0;
        for (int i6 = 0; i6 < size; i6++) {
            Object obj = list.get(i6);
            Object obj2 = ((AnnotatedString.Range) obj).item;
            SpanStyle spanStyle = (SpanStyle) obj2;
            if (spanStyle.fontFamily != null || spanStyle.fontStyle != null || spanStyle.fontWeight != null || ((SpanStyle) obj2).fontSynthesis != null) {
                arrayList.add(obj);
            }
        }
        SpanStyle spanStyle2 = textStyle.spanStyle;
        FontFamily fontFamily = spanStyle2.fontFamily;
        SpanStyle spanStyle3 = ((fontFamily != null || spanStyle2.fontStyle != null || spanStyle2.fontWeight != null) || spanStyle2.fontSynthesis != null) ? new SpanStyle(0L, 0L, spanStyle2.fontWeight, spanStyle2.fontStyle, spanStyle2.fontSynthesis, fontFamily, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, 65475) : null;
        Function3 function3 = new Function3() { // from class: androidx.compose.ui.text.platform.extensions.SpannableExtensions_androidKt$setFontAttributes$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj3, Object obj4, Object obj5) {
                SpanStyle spanStyle4 = (SpanStyle) obj3;
                int intValue = ((Number) obj4).intValue();
                int intValue2 = ((Number) obj5).intValue();
                Spannable spannable2 = spannable;
                Function4 function42 = function4;
                FontFamily fontFamily2 = spanStyle4.fontFamily;
                FontWeight fontWeight = spanStyle4.fontWeight;
                if (fontWeight == null) {
                    fontWeight = FontWeight.Normal;
                }
                FontStyle fontStyle = spanStyle4.fontStyle;
                FontStyle fontStyle2 = new FontStyle(fontStyle != null ? fontStyle.value : 0);
                FontSynthesis fontSynthesis = spanStyle4.fontSynthesis;
                spannable2.setSpan(new TypefaceSpan((Typeface) function42.invoke(fontFamily2, fontWeight, fontStyle2, new FontSynthesis(fontSynthesis != null ? fontSynthesis.value : 1))), intValue, intValue2, 33);
                return Unit.INSTANCE;
            }
        };
        if (arrayList.size() > 1) {
            int size2 = arrayList.size();
            int i7 = size2 * 2;
            int[] iArr = new int[i7];
            int size3 = arrayList.size();
            for (int i8 = 0; i8 < size3; i8++) {
                AnnotatedString.Range range = (AnnotatedString.Range) arrayList.get(i8);
                iArr[i8] = range.start;
                iArr[i8 + size2] = range.end;
            }
            if (i7 > 1) {
                Arrays.sort(iArr);
            }
            if (i7 == 0) {
                throw new NoSuchElementException("Array is empty.");
            }
            int i9 = iArr[0];
            int i10 = 0;
            while (i10 < i7) {
                int i11 = iArr[i10];
                if (i11 != i9) {
                    int size4 = arrayList.size();
                    SpanStyle spanStyle4 = spanStyle3;
                    for (int i12 = i5; i12 < size4; i12++) {
                        AnnotatedString.Range range2 = (AnnotatedString.Range) arrayList.get(i12);
                        int i13 = range2.start;
                        int i14 = range2.end;
                        if (i13 != i14 && AnnotatedStringKt.intersect(i9, i11, i13, i14)) {
                            SpanStyle spanStyle5 = (SpanStyle) range2.item;
                            if (spanStyle4 != null) {
                                spanStyle5 = spanStyle4.merge(spanStyle5);
                            }
                            spanStyle4 = spanStyle5;
                        }
                    }
                    if (spanStyle4 != null) {
                        function3.invoke(spanStyle4, Integer.valueOf(i9), Integer.valueOf(i11));
                    }
                    i9 = i11;
                }
                i10++;
                i5 = 0;
            }
        } else if (!arrayList.isEmpty()) {
            SpanStyle spanStyle6 = (SpanStyle) ((AnnotatedString.Range) arrayList.get(0)).item;
            if (spanStyle3 != null) {
                spanStyle6 = spanStyle3.merge(spanStyle6);
            }
            function3.invoke(spanStyle6, Integer.valueOf(((AnnotatedString.Range) arrayList.get(0)).start), Integer.valueOf(((AnnotatedString.Range) arrayList.get(0)).end));
        }
        int size5 = list.size();
        boolean z = false;
        for (int i15 = 0; i15 < size5; i15++) {
            AnnotatedString.Range range3 = (AnnotatedString.Range) list.get(i15);
            int i16 = range3.start;
            if (i16 >= 0 && i16 < spannable.length() && (i2 = range3.end) > i16 && i2 <= spannable.length()) {
                SpanStyle spanStyle7 = (SpanStyle) range3.item;
                BaselineShift baselineShift = spanStyle7.baselineShift;
                int i17 = range3.start;
                int i18 = range3.end;
                if (baselineShift != null) {
                    spannable.setSpan(new BaselineShiftSpan(baselineShift.multiplier), i17, i18, 33);
                }
                TextForegroundStyle textForegroundStyle = spanStyle7.textForegroundStyle;
                m629setColorRPmYEkk(spannable, textForegroundStyle.mo631getColor0d7_KjU(), i17, i18);
                Brush brush = textForegroundStyle.getBrush();
                float alpha = textForegroundStyle.getAlpha();
                if (brush != null) {
                    if (brush instanceof SolidColor) {
                        m629setColorRPmYEkk(spannable, ((SolidColor) brush).value, i17, i18);
                    } else {
                        spannable.setSpan(new ShaderBrushSpan((ShaderBrush) brush, alpha), i17, i18, 33);
                    }
                }
                TextDecoration textDecoration = spanStyle7.textDecoration;
                if (textDecoration != null) {
                    spannable.setSpan(new TextDecorationSpan(textDecoration.contains(TextDecoration.Underline), textDecoration.contains(TextDecoration.LineThrough)), i17, i18, 33);
                }
                m630setFontSizeKmRG4DE(spannable, spanStyle7.fontSize, density, i17, i18);
                String str = spanStyle7.fontFeatureSettings;
                if (str != null) {
                    spannable.setSpan(new FontFeatureSpan(str), i17, i18, 33);
                }
                TextGeometricTransform textGeometricTransform = spanStyle7.textGeometricTransform;
                if (textGeometricTransform != null) {
                    spannable.setSpan(new ScaleXSpan(textGeometricTransform.scaleX), i17, i18, 33);
                    spannable.setSpan(new SkewXSpan(textGeometricTransform.skewX), i17, i18, 33);
                }
                setLocaleList(spannable, spanStyle7.localeList, i17, i18);
                long j = spanStyle7.background;
                if (j != 16) {
                    setSpan(spannable, new BackgroundColorSpan(ColorKt.m373toArgb8_81llA(j)), i17, i18);
                }
                Shadow shadow = spanStyle7.shadow;
                if (shadow != null) {
                    int m373toArgb8_81llA = ColorKt.m373toArgb8_81llA(shadow.color);
                    long j2 = shadow.offset;
                    float intBitsToFloat = Float.intBitsToFloat((int) (j2 >> 32));
                    float intBitsToFloat2 = Float.intBitsToFloat((int) (j2 & 4294967295L));
                    float f = shadow.blurRadius;
                    if (f == 0.0f) {
                        f = Float.MIN_VALUE;
                    }
                    ShadowSpan shadowSpan = new ShadowSpan(m373toArgb8_81llA, intBitsToFloat, intBitsToFloat2, f);
                    i4 = i18;
                    i3 = 33;
                    spannable.setSpan(shadowSpan, i17, i4, 33);
                } else {
                    i3 = 33;
                    i4 = i18;
                }
                DrawStyle drawStyle = spanStyle7.drawStyle;
                if (drawStyle != null) {
                    spannable.setSpan(new DrawStyleSpan(drawStyle), i17, i4, i3);
                }
                if (TextUnitType.m691equalsimpl0(TextUnit.m687getTypeUIouoOA(spanStyle7.letterSpacing), 4294967296L) || TextUnitType.m691equalsimpl0(TextUnit.m687getTypeUIouoOA(spanStyle7.letterSpacing), 8589934592L)) {
                    z = true;
                }
            }
        }
        if (z) {
            int size6 = list.size();
            for (int i19 = 0; i19 < size6; i19++) {
                AnnotatedString.Range range4 = (AnnotatedString.Range) list.get(i19);
                int i20 = range4.start;
                SpanStyle spanStyle8 = (SpanStyle) range4.item;
                if (i20 >= 0 && i20 < spannable.length() && (i = range4.end) > i20 && i <= spannable.length()) {
                    long j3 = spanStyle8.letterSpacing;
                    long m687getTypeUIouoOA = TextUnit.m687getTypeUIouoOA(j3);
                    Object letterSpacingSpanPx = TextUnitType.m691equalsimpl0(m687getTypeUIouoOA, 4294967296L) ? new LetterSpacingSpanPx(density.mo50toPxR2X_6o(j3)) : TextUnitType.m691equalsimpl0(m687getTypeUIouoOA, 8589934592L) ? new LetterSpacingSpanEm(TextUnit.m688getValueimpl(j3)) : null;
                    if (letterSpacingSpanPx != null) {
                        spannable.setSpan(letterSpacingSpanPx, i20, i, 33);
                    }
                }
            }
        }
    }
}
