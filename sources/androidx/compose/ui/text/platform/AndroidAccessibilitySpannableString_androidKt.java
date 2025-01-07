package androidx.compose.ui.text.platform;

import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TtsSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.AnnotatedStringKt;
import androidx.compose.ui.text.LinkAnnotation;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TtsAnnotation;
import androidx.compose.ui.text.UrlAnnotation;
import androidx.compose.ui.text.VerbatimTtsAnnotation;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.platform.extensions.SpannableExtensions_androidKt;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextForegroundStyle;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.unit.Density;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.EmptyList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AndroidAccessibilitySpannableString_androidKt {
    public static final SpannableString toAccessibilitySpannableString(AnnotatedString annotatedString, Density density, URLSpanCache uRLSpanCache) {
        List list;
        List list2;
        int i;
        SpannableString spannableString = new SpannableString(annotatedString.text);
        List list3 = annotatedString.spanStylesOrNull;
        if (list3 != null) {
            int size = list3.size();
            for (int i2 = 0; i2 < size; i2++) {
                AnnotatedString.Range range = (AnnotatedString.Range) list3.get(i2);
                SpanStyle spanStyle = (SpanStyle) range.item;
                long mo631getColor0d7_KjU = spanStyle.textForegroundStyle.mo631getColor0d7_KjU();
                TextForegroundStyle textForegroundStyle = spanStyle.textForegroundStyle;
                if (!Color.m363equalsimpl0(mo631getColor0d7_KjU, textForegroundStyle.mo631getColor0d7_KjU())) {
                    textForegroundStyle = TextForegroundStyle.Companion.m645from8_81llA(mo631getColor0d7_KjU);
                }
                long mo631getColor0d7_KjU2 = textForegroundStyle.mo631getColor0d7_KjU();
                int i3 = range.start;
                int i4 = range.end;
                SpannableExtensions_androidKt.m629setColorRPmYEkk(spannableString, mo631getColor0d7_KjU2, i3, i4);
                SpannableExtensions_androidKt.m630setFontSizeKmRG4DE(spannableString, spanStyle.fontSize, density, i3, i4);
                FontWeight fontWeight = spanStyle.fontWeight;
                FontStyle fontStyle = spanStyle.fontStyle;
                if (fontWeight == null && fontStyle == null) {
                    i = i4;
                } else {
                    if (fontWeight == null) {
                        fontWeight = FontWeight.Normal;
                    }
                    int i5 = fontStyle != null ? fontStyle.value : 0;
                    int i6 = 1;
                    boolean z = fontWeight.compareTo(FontWeight.W600) >= 0;
                    boolean m609equalsimpl0 = FontStyle.m609equalsimpl0(i5, 1);
                    if (m609equalsimpl0 && z) {
                        i6 = 3;
                    } else if (!z) {
                        i6 = m609equalsimpl0 ? 2 : 0;
                    }
                    i = i4;
                    spannableString.setSpan(new StyleSpan(i6), i3, i, 33);
                }
                TextDecoration textDecoration = spanStyle.textDecoration;
                if (textDecoration != null) {
                    if (textDecoration.contains(TextDecoration.Underline)) {
                        spannableString.setSpan(new UnderlineSpan(), i3, i, 33);
                    }
                    if (textDecoration.contains(TextDecoration.LineThrough)) {
                        spannableString.setSpan(new StrikethroughSpan(), i3, i, 33);
                    }
                }
                TextGeometricTransform textGeometricTransform = spanStyle.textGeometricTransform;
                if (textGeometricTransform != null) {
                    spannableString.setSpan(new ScaleXSpan(textGeometricTransform.scaleX), i3, i, 33);
                }
                SpannableExtensions_androidKt.setLocaleList(spannableString, spanStyle.localeList, i3, i);
                long j = spanStyle.background;
                if (j != 16) {
                    SpannableExtensions_androidKt.setSpan(spannableString, new BackgroundColorSpan(ColorKt.m373toArgb8_81llA(j)), i3, i);
                }
            }
        }
        int length = annotatedString.text.length();
        List list4 = annotatedString.annotations;
        if (list4 != null) {
            list = new ArrayList(list4.size());
            int size2 = list4.size();
            for (int i7 = 0; i7 < size2; i7++) {
                Object obj = list4.get(i7);
                AnnotatedString.Range range2 = (AnnotatedString.Range) obj;
                if ((range2.item instanceof TtsAnnotation) && AnnotatedStringKt.intersect(0, length, range2.start, range2.end)) {
                    list.add(obj);
                }
            }
        } else {
            list = EmptyList.INSTANCE;
        }
        int size3 = list.size();
        for (int i8 = 0; i8 < size3; i8++) {
            AnnotatedString.Range range3 = (AnnotatedString.Range) list.get(i8);
            TtsAnnotation ttsAnnotation = (TtsAnnotation) range3.item;
            if (!(ttsAnnotation instanceof VerbatimTtsAnnotation)) {
                throw new NoWhenBranchMatchedException();
            }
            spannableString.setSpan(new TtsSpan.VerbatimBuilder(((VerbatimTtsAnnotation) ttsAnnotation).verbatim).build(), range3.start, range3.end, 33);
        }
        int length2 = annotatedString.text.length();
        List list5 = annotatedString.annotations;
        if (list5 != null) {
            list2 = new ArrayList(list5.size());
            int size4 = list5.size();
            for (int i9 = 0; i9 < size4; i9++) {
                Object obj2 = list5.get(i9);
                AnnotatedString.Range range4 = (AnnotatedString.Range) obj2;
                if ((range4.item instanceof UrlAnnotation) && AnnotatedStringKt.intersect(0, length2, range4.start, range4.end)) {
                    list2.add(obj2);
                }
            }
        } else {
            list2 = EmptyList.INSTANCE;
        }
        int size5 = list2.size();
        for (int i10 = 0; i10 < size5; i10++) {
            AnnotatedString.Range range5 = (AnnotatedString.Range) list2.get(i10);
            UrlAnnotation urlAnnotation = (UrlAnnotation) range5.item;
            WeakHashMap weakHashMap = uRLSpanCache.spansByAnnotation;
            Object obj3 = weakHashMap.get(urlAnnotation);
            if (obj3 == null) {
                obj3 = new URLSpan(urlAnnotation.url);
                weakHashMap.put(urlAnnotation, obj3);
            }
            spannableString.setSpan((URLSpan) obj3, range5.start, range5.end, 33);
        }
        List linkAnnotations = annotatedString.getLinkAnnotations(annotatedString.text.length());
        int size6 = linkAnnotations.size();
        for (int i11 = 0; i11 < size6; i11++) {
            AnnotatedString.Range range6 = (AnnotatedString.Range) linkAnnotations.get(i11);
            Object obj4 = range6.item;
            LinkAnnotation linkAnnotation = (LinkAnnotation) obj4;
            boolean z2 = linkAnnotation instanceof LinkAnnotation.Url;
            int i12 = range6.end;
            int i13 = range6.start;
            if (z2) {
                linkAnnotation.getClass();
                LinkAnnotation.Url url = (LinkAnnotation.Url) obj4;
                AnnotatedString.Range range7 = new AnnotatedString.Range(i13, i12, url);
                WeakHashMap weakHashMap2 = uRLSpanCache.urlSpansByAnnotation;
                Object obj5 = weakHashMap2.get(range7);
                if (obj5 == null) {
                    obj5 = new URLSpan(url.url);
                    weakHashMap2.put(range7, obj5);
                }
                spannableString.setSpan((URLSpan) obj5, i13, i12, 33);
            } else {
                WeakHashMap weakHashMap3 = uRLSpanCache.linkSpansWithListenerByAnnotation;
                Object obj6 = weakHashMap3.get(range6);
                if (obj6 == null) {
                    obj6 = new ComposeClickableSpan((LinkAnnotation) obj4);
                    weakHashMap3.put(range6, obj6);
                }
                spannableString.setSpan((ClickableSpan) obj6, i13, i12, 33);
            }
        }
        return spannableString;
    }
}
