package androidx.compose.ui.text;

import android.util.Log;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverKt$Saver$1;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.LinkAnnotation;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.Locale;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.intl.PlatformLocaleKt;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.Hyphens;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.unit.TextUnitType;
import java.util.ArrayList;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SaversKt {
    public static final SaverKt$Saver$1 AnnotatedStringSaver = null;
    public static final SaverKt$Saver$1 AnnotationRangeListSaver;
    public static final SaverKt$Saver$1 AnnotationRangeSaver;
    public static final SaverKt$Saver$1 BaselineShiftSaver;
    public static final SaverKt$Saver$1 ClickableSaver;
    public static final SaversKt$NonNullValueClassSaver$1 ColorSaver;
    public static final SaverKt$Saver$1 FontWeightSaver;
    public static final SaverKt$Saver$1 LineHeightStyleSaver;
    public static final SaverKt$Saver$1 LinkSaver;
    public static final SaverKt$Saver$1 LocaleListSaver;
    public static final SaverKt$Saver$1 LocaleSaver;
    public static final SaversKt$NonNullValueClassSaver$1 OffsetSaver;
    public static final SaverKt$Saver$1 ParagraphStyleSaver;
    public static final SaverKt$Saver$1 ShadowSaver;
    public static final SaverKt$Saver$1 SpanStyleSaver;
    public static final SaverKt$Saver$1 TextDecorationSaver;
    public static final SaverKt$Saver$1 TextGeometricTransformSaver;
    public static final SaverKt$Saver$1 TextIndentSaver;
    public static final SaverKt$Saver$1 TextLinkStylesSaver;
    public static final SaverKt$Saver$1 TextRangeSaver = null;
    public static final SaversKt$NonNullValueClassSaver$1 TextUnitSaver;
    public static final SaverKt$Saver$1 UrlAnnotationSaver;
    public static final SaverKt$Saver$1 VerbatimTtsAnnotationSaver;

    static {
        SaversKt$AnnotatedStringSaver$1 saversKt$AnnotatedStringSaver$1 = new Function2() { // from class: androidx.compose.ui.text.SaversKt$AnnotatedStringSaver$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                AnnotatedString annotatedString = (AnnotatedString) obj2;
                String str = annotatedString.text;
                SaverKt$Saver$1 saverKt$Saver$1 = SaversKt.AnnotatedStringSaver;
                return CollectionsKt__CollectionsKt.arrayListOf(str, SaversKt.save(annotatedString.annotations, SaversKt.AnnotationRangeListSaver, (SaverScope) obj));
            }
        };
        SaversKt$AnnotatedStringSaver$2 saversKt$AnnotatedStringSaver$2 = new Function1() { // from class: androidx.compose.ui.text.SaversKt$AnnotatedStringSaver$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                List list = (List) obj;
                Object obj2 = list.get(1);
                SaverKt$Saver$1 saverKt$Saver$1 = SaversKt.AnnotationRangeListSaver;
                List list2 = ((!Intrinsics.areEqual(obj2, Boolean.FALSE) || (saverKt$Saver$1 instanceof NonNullValueClassSaver)) && obj2 != null) ? (List) saverKt$Saver$1.$restore.invoke(obj2) : null;
                Object obj3 = list.get(0);
                String str = obj3 != null ? (String) obj3 : null;
                Intrinsics.checkNotNull(str);
                return new AnnotatedString(list2, str);
            }
        };
        SaverKt$Saver$1 saverKt$Saver$1 = SaverKt.AutoSaver;
        new SaverKt$Saver$1(saversKt$AnnotatedStringSaver$1, saversKt$AnnotatedStringSaver$2);
        AnnotationRangeListSaver = new SaverKt$Saver$1(new Function2() { // from class: androidx.compose.ui.text.SaversKt$AnnotationRangeListSaver$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                SaverScope saverScope = (SaverScope) obj;
                List list = (List) obj2;
                ArrayList arrayList = new ArrayList(list.size());
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    arrayList.add(SaversKt.save((AnnotatedString.Range) list.get(i), SaversKt.AnnotationRangeSaver, saverScope));
                }
                return arrayList;
            }
        }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$AnnotationRangeListSaver$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                List list = (List) obj;
                ArrayList arrayList = new ArrayList(list.size());
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    Object obj2 = list.get(i);
                    SaverKt$Saver$1 saverKt$Saver$12 = SaversKt.AnnotationRangeSaver;
                    AnnotatedString.Range range = null;
                    if ((!Intrinsics.areEqual(obj2, Boolean.FALSE) || (saverKt$Saver$12 instanceof NonNullValueClassSaver)) && obj2 != null) {
                        range = (AnnotatedString.Range) saverKt$Saver$12.$restore.invoke(obj2);
                    }
                    Intrinsics.checkNotNull(range);
                    arrayList.add(range);
                }
                return arrayList;
            }
        });
        AnnotationRangeSaver = new SaverKt$Saver$1(new Function2() { // from class: androidx.compose.ui.text.SaversKt$AnnotationRangeSaver$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                AnnotationType annotationType;
                Object save;
                SaverScope saverScope = (SaverScope) obj;
                AnnotatedString.Range range = (AnnotatedString.Range) obj2;
                Object obj3 = range.item;
                if (obj3 instanceof ParagraphStyle) {
                    annotationType = AnnotationType.Paragraph;
                } else if (obj3 instanceof SpanStyle) {
                    annotationType = AnnotationType.Span;
                } else if (obj3 instanceof VerbatimTtsAnnotation) {
                    annotationType = AnnotationType.VerbatimTts;
                } else if (obj3 instanceof UrlAnnotation) {
                    annotationType = AnnotationType.Url;
                } else if (obj3 instanceof LinkAnnotation.Url) {
                    annotationType = AnnotationType.Link;
                } else if (obj3 instanceof LinkAnnotation.Clickable) {
                    annotationType = AnnotationType.Clickable;
                } else {
                    if (!(obj3 instanceof StringAnnotation)) {
                        throw new UnsupportedOperationException();
                    }
                    annotationType = AnnotationType.String;
                }
                int ordinal = annotationType.ordinal();
                Object obj4 = range.item;
                switch (ordinal) {
                    case 0:
                        save = SaversKt.save((ParagraphStyle) obj4, SaversKt.ParagraphStyleSaver, saverScope);
                        break;
                    case 1:
                        save = SaversKt.save((SpanStyle) obj4, SaversKt.SpanStyleSaver, saverScope);
                        break;
                    case 2:
                        save = SaversKt.save((VerbatimTtsAnnotation) obj4, SaversKt.VerbatimTtsAnnotationSaver, saverScope);
                        break;
                    case 3:
                        save = SaversKt.save((UrlAnnotation) obj4, SaversKt.UrlAnnotationSaver, saverScope);
                        break;
                    case 4:
                        save = SaversKt.save((LinkAnnotation.Url) obj4, SaversKt.LinkSaver, saverScope);
                        break;
                    case 5:
                        save = SaversKt.save((LinkAnnotation.Clickable) obj4, SaversKt.ClickableSaver, saverScope);
                        break;
                    case 6:
                        SaverKt$Saver$1 saverKt$Saver$12 = SaversKt.AnnotatedStringSaver;
                        save = ((StringAnnotation) obj4).value;
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                return CollectionsKt__CollectionsKt.arrayListOf(annotationType, save, Integer.valueOf(range.start), Integer.valueOf(range.end), range.tag);
            }
        }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$AnnotationRangeSaver$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                List list = (List) obj;
                Object obj2 = list.get(0);
                ParagraphStyle paragraphStyle = null;
                r0 = null;
                LinkAnnotation.Clickable clickable = null;
                r0 = null;
                LinkAnnotation.Url url = null;
                r0 = null;
                UrlAnnotation urlAnnotation = null;
                r0 = null;
                VerbatimTtsAnnotation verbatimTtsAnnotation = null;
                r0 = null;
                SpanStyle spanStyle = null;
                paragraphStyle = null;
                AnnotationType annotationType = obj2 != null ? (AnnotationType) obj2 : null;
                Intrinsics.checkNotNull(annotationType);
                Object obj3 = list.get(2);
                Integer num = obj3 != null ? (Integer) obj3 : null;
                Intrinsics.checkNotNull(num);
                int intValue = num.intValue();
                Object obj4 = list.get(3);
                Integer num2 = obj4 != null ? (Integer) obj4 : null;
                Intrinsics.checkNotNull(num2);
                int intValue2 = num2.intValue();
                Object obj5 = list.get(4);
                String str = obj5 != null ? (String) obj5 : null;
                Intrinsics.checkNotNull(str);
                switch (annotationType.ordinal()) {
                    case 0:
                        Object obj6 = list.get(1);
                        SaverKt$Saver$1 saverKt$Saver$12 = SaversKt.ParagraphStyleSaver;
                        if ((!Intrinsics.areEqual(obj6, Boolean.FALSE) || (saverKt$Saver$12 instanceof NonNullValueClassSaver)) && obj6 != null) {
                            paragraphStyle = (ParagraphStyle) saverKt$Saver$12.$restore.invoke(obj6);
                        }
                        Intrinsics.checkNotNull(paragraphStyle);
                        return new AnnotatedString.Range(paragraphStyle, intValue, intValue2, str);
                    case 1:
                        Object obj7 = list.get(1);
                        SaverKt$Saver$1 saverKt$Saver$13 = SaversKt.SpanStyleSaver;
                        if ((!Intrinsics.areEqual(obj7, Boolean.FALSE) || (saverKt$Saver$13 instanceof NonNullValueClassSaver)) && obj7 != null) {
                            spanStyle = (SpanStyle) saverKt$Saver$13.$restore.invoke(obj7);
                        }
                        Intrinsics.checkNotNull(spanStyle);
                        return new AnnotatedString.Range(spanStyle, intValue, intValue2, str);
                    case 2:
                        Object obj8 = list.get(1);
                        SaverKt$Saver$1 saverKt$Saver$14 = SaversKt.VerbatimTtsAnnotationSaver;
                        if ((!Intrinsics.areEqual(obj8, Boolean.FALSE) || (saverKt$Saver$14 instanceof NonNullValueClassSaver)) && obj8 != null) {
                            verbatimTtsAnnotation = (VerbatimTtsAnnotation) saverKt$Saver$14.$restore.invoke(obj8);
                        }
                        Intrinsics.checkNotNull(verbatimTtsAnnotation);
                        return new AnnotatedString.Range(verbatimTtsAnnotation, intValue, intValue2, str);
                    case 3:
                        Object obj9 = list.get(1);
                        SaverKt$Saver$1 saverKt$Saver$15 = SaversKt.UrlAnnotationSaver;
                        if ((!Intrinsics.areEqual(obj9, Boolean.FALSE) || (saverKt$Saver$15 instanceof NonNullValueClassSaver)) && obj9 != null) {
                            urlAnnotation = (UrlAnnotation) saverKt$Saver$15.$restore.invoke(obj9);
                        }
                        Intrinsics.checkNotNull(urlAnnotation);
                        return new AnnotatedString.Range(urlAnnotation, intValue, intValue2, str);
                    case 4:
                        Object obj10 = list.get(1);
                        SaverKt$Saver$1 saverKt$Saver$16 = SaversKt.LinkSaver;
                        if ((!Intrinsics.areEqual(obj10, Boolean.FALSE) || (saverKt$Saver$16 instanceof NonNullValueClassSaver)) && obj10 != null) {
                            url = (LinkAnnotation.Url) saverKt$Saver$16.$restore.invoke(obj10);
                        }
                        Intrinsics.checkNotNull(url);
                        return new AnnotatedString.Range(url, intValue, intValue2, str);
                    case 5:
                        Object obj11 = list.get(1);
                        SaverKt$Saver$1 saverKt$Saver$17 = SaversKt.ClickableSaver;
                        if ((!Intrinsics.areEqual(obj11, Boolean.FALSE) || (saverKt$Saver$17 instanceof NonNullValueClassSaver)) && obj11 != null) {
                            clickable = (LinkAnnotation.Clickable) saverKt$Saver$17.$restore.invoke(obj11);
                        }
                        Intrinsics.checkNotNull(clickable);
                        return new AnnotatedString.Range(clickable, intValue, intValue2, str);
                    case 6:
                        Object obj12 = list.get(1);
                        String str2 = obj12 != null ? (String) obj12 : null;
                        Intrinsics.checkNotNull(str2);
                        return new AnnotatedString.Range(new StringAnnotation(str2), intValue, intValue2, str);
                    default:
                        throw new NoWhenBranchMatchedException();
                }
            }
        });
        VerbatimTtsAnnotationSaver = new SaverKt$Saver$1(new Function2() { // from class: androidx.compose.ui.text.SaversKt$VerbatimTtsAnnotationSaver$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                String str = ((VerbatimTtsAnnotation) obj2).verbatim;
                SaverKt$Saver$1 saverKt$Saver$12 = SaversKt.AnnotatedStringSaver;
                return str;
            }
        }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$VerbatimTtsAnnotationSaver$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                String str = obj != null ? (String) obj : null;
                Intrinsics.checkNotNull(str);
                return new VerbatimTtsAnnotation(str);
            }
        });
        UrlAnnotationSaver = new SaverKt$Saver$1(new Function2() { // from class: androidx.compose.ui.text.SaversKt$UrlAnnotationSaver$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                String str = ((UrlAnnotation) obj2).url;
                SaverKt$Saver$1 saverKt$Saver$12 = SaversKt.AnnotatedStringSaver;
                return str;
            }
        }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$UrlAnnotationSaver$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                String str = obj != null ? (String) obj : null;
                Intrinsics.checkNotNull(str);
                return new UrlAnnotation(str);
            }
        });
        LinkSaver = new SaverKt$Saver$1(new Function2() { // from class: androidx.compose.ui.text.SaversKt$LinkSaver$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                LinkAnnotation.Url url = (LinkAnnotation.Url) obj2;
                String str = url.url;
                SaverKt$Saver$1 saverKt$Saver$12 = SaversKt.TextLinkStylesSaver;
                return CollectionsKt__CollectionsKt.arrayListOf(str, SaversKt.save(url.styles, saverKt$Saver$12, (SaverScope) obj));
            }
        }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$LinkSaver$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                List list = (List) obj;
                Object obj2 = list.get(0);
                TextLinkStyles textLinkStyles = null;
                String str = obj2 != null ? (String) obj2 : null;
                Intrinsics.checkNotNull(str);
                Object obj3 = list.get(1);
                SaverKt$Saver$1 saverKt$Saver$12 = SaversKt.TextLinkStylesSaver;
                if ((!Intrinsics.areEqual(obj3, Boolean.FALSE) || (saverKt$Saver$12 instanceof NonNullValueClassSaver)) && obj3 != null) {
                    textLinkStyles = (TextLinkStyles) saverKt$Saver$12.$restore.invoke(obj3);
                }
                return new LinkAnnotation.Url(str, textLinkStyles);
            }
        });
        ClickableSaver = new SaverKt$Saver$1(new Function2() { // from class: androidx.compose.ui.text.SaversKt$ClickableSaver$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                LinkAnnotation.Clickable clickable = (LinkAnnotation.Clickable) obj2;
                String str = clickable.tag;
                SaverKt$Saver$1 saverKt$Saver$12 = SaversKt.TextLinkStylesSaver;
                return CollectionsKt__CollectionsKt.arrayListOf(str, SaversKt.save(clickable.styles, saverKt$Saver$12, (SaverScope) obj));
            }
        }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$ClickableSaver$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                List list = (List) obj;
                Object obj2 = list.get(0);
                TextLinkStyles textLinkStyles = null;
                String str = obj2 != null ? (String) obj2 : null;
                Intrinsics.checkNotNull(str);
                Object obj3 = list.get(1);
                SaverKt$Saver$1 saverKt$Saver$12 = SaversKt.TextLinkStylesSaver;
                if ((!Intrinsics.areEqual(obj3, Boolean.FALSE) || (saverKt$Saver$12 instanceof NonNullValueClassSaver)) && obj3 != null) {
                    textLinkStyles = (TextLinkStyles) saverKt$Saver$12.$restore.invoke(obj3);
                }
                return new LinkAnnotation.Clickable(str, textLinkStyles);
            }
        });
        ParagraphStyleSaver = new SaverKt$Saver$1(new Function2() { // from class: androidx.compose.ui.text.SaversKt$ParagraphStyleSaver$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                SaverScope saverScope = (SaverScope) obj;
                ParagraphStyle paragraphStyle = (ParagraphStyle) obj2;
                TextAlign textAlign = new TextAlign(paragraphStyle.textAlign);
                SaverKt$Saver$1 saverKt$Saver$12 = SaversKt.AnnotatedStringSaver;
                TextDirection textDirection = new TextDirection(paragraphStyle.textDirection);
                Object save = SaversKt.save(new TextUnit(paragraphStyle.lineHeight), SaversKt.TextUnitSaver, saverScope);
                TextIndent textIndent = TextIndent.None;
                Object save2 = SaversKt.save(paragraphStyle.textIndent, SaversKt.TextIndentSaver, saverScope);
                Object save3 = SaversKt.save(paragraphStyle.platformStyle, Savers_androidKt.PlatformParagraphStyleSaver, saverScope);
                LineHeightStyle lineHeightStyle = LineHeightStyle.Default;
                return CollectionsKt__CollectionsKt.arrayListOf(textAlign, textDirection, save, save2, save3, SaversKt.save(paragraphStyle.lineHeightStyle, SaversKt.LineHeightStyleSaver, saverScope), SaversKt.save(new LineBreak(paragraphStyle.lineBreak), Savers_androidKt.LineBreakSaver, saverScope), new Hyphens(paragraphStyle.hyphens), SaversKt.save(paragraphStyle.textMotion, Savers_androidKt.TextMotionSaver, saverScope));
            }
        }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$ParagraphStyleSaver$2
            /* JADX WARN: Type inference failed for: r4v2, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                List list = (List) obj;
                Object obj2 = list.get(0);
                TextAlign textAlign = obj2 != null ? (TextAlign) obj2 : null;
                Intrinsics.checkNotNull(textAlign);
                Object obj3 = list.get(1);
                TextDirection textDirection = obj3 != null ? (TextDirection) obj3 : null;
                Intrinsics.checkNotNull(textDirection);
                Object obj4 = list.get(2);
                TextUnitType[] textUnitTypeArr = TextUnit.TextUnitTypes;
                SaversKt$NonNullValueClassSaver$1 saversKt$NonNullValueClassSaver$1 = SaversKt.TextUnitSaver;
                Boolean bool = Boolean.FALSE;
                TextUnit textUnit = ((Intrinsics.areEqual(obj4, bool) && saversKt$NonNullValueClassSaver$1 == null) || obj4 == null) ? null : (TextUnit) saversKt$NonNullValueClassSaver$1.$restore.invoke(obj4);
                Intrinsics.checkNotNull(textUnit);
                Object obj5 = list.get(3);
                TextIndent textIndent = TextIndent.None;
                SaverKt$Saver$1 saverKt$Saver$12 = SaversKt.TextIndentSaver;
                TextIndent textIndent2 = ((!Intrinsics.areEqual(obj5, bool) || (saverKt$Saver$12 instanceof NonNullValueClassSaver)) && obj5 != null) ? (TextIndent) saverKt$Saver$12.$restore.invoke(obj5) : null;
                Object obj6 = list.get(4);
                SaverKt$Saver$1 saverKt$Saver$13 = Savers_androidKt.PlatformParagraphStyleSaver;
                PlatformParagraphStyle platformParagraphStyle = ((!Intrinsics.areEqual(obj6, bool) || (saverKt$Saver$13 instanceof NonNullValueClassSaver)) && obj6 != null) ? (PlatformParagraphStyle) saverKt$Saver$13.$restore.invoke(obj6) : null;
                Object obj7 = list.get(5);
                LineHeightStyle lineHeightStyle = LineHeightStyle.Default;
                SaverKt$Saver$1 saverKt$Saver$14 = SaversKt.LineHeightStyleSaver;
                LineHeightStyle lineHeightStyle2 = ((!Intrinsics.areEqual(obj7, bool) || (saverKt$Saver$14 instanceof NonNullValueClassSaver)) && obj7 != null) ? (LineHeightStyle) saverKt$Saver$14.$restore.invoke(obj7) : null;
                Object obj8 = list.get(6);
                SaverKt$Saver$1 saverKt$Saver$15 = Savers_androidKt.LineBreakSaver;
                LineBreak lineBreak = ((!Intrinsics.areEqual(obj8, bool) || (saverKt$Saver$15 instanceof NonNullValueClassSaver)) && obj8 != null) ? (LineBreak) saverKt$Saver$15.$restore.invoke(obj8) : null;
                Intrinsics.checkNotNull(lineBreak);
                Object obj9 = list.get(7);
                Hyphens hyphens = obj9 != null ? (Hyphens) obj9 : null;
                Intrinsics.checkNotNull(hyphens);
                Object obj10 = list.get(8);
                SaverKt$Saver$1 saverKt$Saver$16 = Savers_androidKt.TextMotionSaver;
                return new ParagraphStyle(textAlign.value, textDirection.value, textUnit.packedValue, textIndent2, platformParagraphStyle, lineHeightStyle2, lineBreak.mask, hyphens.value, ((!Intrinsics.areEqual(obj10, bool) || (saverKt$Saver$16 instanceof NonNullValueClassSaver)) && obj10 != null) ? (TextMotion) saverKt$Saver$16.$restore.invoke(obj10) : null);
            }
        });
        SpanStyleSaver = new SaverKt$Saver$1(new Function2() { // from class: androidx.compose.ui.text.SaversKt$SpanStyleSaver$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                SaverScope saverScope = (SaverScope) obj;
                SpanStyle spanStyle = (SpanStyle) obj2;
                Color color = new Color(spanStyle.textForegroundStyle.mo631getColor0d7_KjU());
                SaversKt$NonNullValueClassSaver$1 saversKt$NonNullValueClassSaver$1 = SaversKt.ColorSaver;
                Object save = SaversKt.save(color, saversKt$NonNullValueClassSaver$1, saverScope);
                TextUnit textUnit = new TextUnit(spanStyle.fontSize);
                SaversKt$NonNullValueClassSaver$1 saversKt$NonNullValueClassSaver$12 = SaversKt.TextUnitSaver;
                Object save2 = SaversKt.save(textUnit, saversKt$NonNullValueClassSaver$12, saverScope);
                FontWeight fontWeight = FontWeight.W400;
                Object save3 = SaversKt.save(spanStyle.fontWeight, SaversKt.FontWeightSaver, saverScope);
                Object save4 = SaversKt.save(new TextUnit(spanStyle.letterSpacing), saversKt$NonNullValueClassSaver$12, saverScope);
                Object save5 = SaversKt.save(spanStyle.baselineShift, SaversKt.BaselineShiftSaver, saverScope);
                Object save6 = SaversKt.save(spanStyle.textGeometricTransform, SaversKt.TextGeometricTransformSaver, saverScope);
                LocaleList localeList = LocaleList.Empty;
                Object save7 = SaversKt.save(spanStyle.localeList, SaversKt.LocaleListSaver, saverScope);
                Object save8 = SaversKt.save(new Color(spanStyle.background), saversKt$NonNullValueClassSaver$1, saverScope);
                Object save9 = SaversKt.save(spanStyle.textDecoration, SaversKt.TextDecorationSaver, saverScope);
                Shadow shadow = Shadow.None;
                Object save10 = SaversKt.save(spanStyle.shadow, SaversKt.ShadowSaver, saverScope);
                return CollectionsKt__CollectionsKt.arrayListOf(save, save2, save3, spanStyle.fontStyle, spanStyle.fontSynthesis, -1, spanStyle.fontFeatureSettings, save4, save5, save6, save7, save8, save9, save10);
            }
        }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$SpanStyleSaver$2
            /* JADX WARN: Type inference failed for: r2v2, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
            /* JADX WARN: Type inference failed for: r4v1, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
            /* JADX WARN: Type inference failed for: r6v2, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
            /* JADX WARN: Type inference failed for: r7v1, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                List list = (List) obj;
                Object obj2 = list.get(0);
                int i = Color.$r8$clinit;
                SaversKt$NonNullValueClassSaver$1 saversKt$NonNullValueClassSaver$1 = SaversKt.ColorSaver;
                Boolean bool = Boolean.FALSE;
                Color color = ((Intrinsics.areEqual(obj2, bool) && saversKt$NonNullValueClassSaver$1 == null) || obj2 == null) ? null : (Color) saversKt$NonNullValueClassSaver$1.$restore.invoke(obj2);
                Intrinsics.checkNotNull(color);
                Object obj3 = list.get(1);
                TextUnitType[] textUnitTypeArr = TextUnit.TextUnitTypes;
                SaversKt$NonNullValueClassSaver$1 saversKt$NonNullValueClassSaver$12 = SaversKt.TextUnitSaver;
                TextUnit textUnit = ((Intrinsics.areEqual(obj3, bool) && saversKt$NonNullValueClassSaver$12 == null) || obj3 == null) ? null : (TextUnit) saversKt$NonNullValueClassSaver$12.$restore.invoke(obj3);
                Intrinsics.checkNotNull(textUnit);
                Object obj4 = list.get(2);
                FontWeight fontWeight = FontWeight.W400;
                SaverKt$Saver$1 saverKt$Saver$12 = SaversKt.FontWeightSaver;
                FontWeight fontWeight2 = ((!Intrinsics.areEqual(obj4, bool) || (saverKt$Saver$12 instanceof NonNullValueClassSaver)) && obj4 != null) ? (FontWeight) saverKt$Saver$12.$restore.invoke(obj4) : null;
                Object obj5 = list.get(3);
                FontStyle fontStyle = obj5 != null ? (FontStyle) obj5 : null;
                Object obj6 = list.get(4);
                FontSynthesis fontSynthesis = obj6 != null ? (FontSynthesis) obj6 : null;
                Object obj7 = list.get(6);
                String str = obj7 != null ? (String) obj7 : null;
                Object obj8 = list.get(7);
                TextUnit textUnit2 = ((Intrinsics.areEqual(obj8, bool) && saversKt$NonNullValueClassSaver$12 == null) || obj8 == null) ? null : (TextUnit) saversKt$NonNullValueClassSaver$12.$restore.invoke(obj8);
                Intrinsics.checkNotNull(textUnit2);
                Object obj9 = list.get(8);
                SaverKt$Saver$1 saverKt$Saver$13 = SaversKt.BaselineShiftSaver;
                BaselineShift baselineShift = ((!Intrinsics.areEqual(obj9, bool) || (saverKt$Saver$13 instanceof NonNullValueClassSaver)) && obj9 != null) ? (BaselineShift) saverKt$Saver$13.$restore.invoke(obj9) : null;
                Object obj10 = list.get(9);
                SaverKt$Saver$1 saverKt$Saver$14 = SaversKt.TextGeometricTransformSaver;
                TextGeometricTransform textGeometricTransform = ((!Intrinsics.areEqual(obj10, bool) || (saverKt$Saver$14 instanceof NonNullValueClassSaver)) && obj10 != null) ? (TextGeometricTransform) saverKt$Saver$14.$restore.invoke(obj10) : null;
                Object obj11 = list.get(10);
                LocaleList localeList = LocaleList.Empty;
                SaverKt$Saver$1 saverKt$Saver$15 = SaversKt.LocaleListSaver;
                LocaleList localeList2 = ((!Intrinsics.areEqual(obj11, bool) || (saverKt$Saver$15 instanceof NonNullValueClassSaver)) && obj11 != null) ? (LocaleList) saverKt$Saver$15.$restore.invoke(obj11) : null;
                Object obj12 = list.get(11);
                Color color2 = ((Intrinsics.areEqual(obj12, bool) && saversKt$NonNullValueClassSaver$1 == null) || obj12 == null) ? null : (Color) saversKt$NonNullValueClassSaver$1.$restore.invoke(obj12);
                Intrinsics.checkNotNull(color2);
                Object obj13 = list.get(12);
                SaverKt$Saver$1 saverKt$Saver$16 = SaversKt.TextDecorationSaver;
                TextDecoration textDecoration = ((!Intrinsics.areEqual(obj13, bool) || (saverKt$Saver$16 instanceof NonNullValueClassSaver)) && obj13 != null) ? (TextDecoration) saverKt$Saver$16.$restore.invoke(obj13) : null;
                Object obj14 = list.get(13);
                Shadow shadow = Shadow.None;
                SaverKt$Saver$1 saverKt$Saver$17 = SaversKt.ShadowSaver;
                Shadow shadow2 = ((!Intrinsics.areEqual(obj14, bool) || (saverKt$Saver$17 instanceof NonNullValueClassSaver)) && obj14 != null) ? (Shadow) saverKt$Saver$17.$restore.invoke(obj14) : null;
                return new SpanStyle(color.value, textUnit.packedValue, fontWeight2, fontStyle, fontSynthesis, (FontFamily) null, str, textUnit2.packedValue, baselineShift, textGeometricTransform, localeList2, color2.value, textDecoration, shadow2, 49184);
            }
        });
        TextLinkStylesSaver = new SaverKt$Saver$1(new Function2() { // from class: androidx.compose.ui.text.SaversKt$TextLinkStylesSaver$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                SaverScope saverScope = (SaverScope) obj;
                TextLinkStyles textLinkStyles = (TextLinkStyles) obj2;
                SpanStyle spanStyle = textLinkStyles.style;
                SaverKt$Saver$1 saverKt$Saver$12 = SaversKt.SpanStyleSaver;
                return CollectionsKt__CollectionsKt.arrayListOf(SaversKt.save(spanStyle, saverKt$Saver$12, saverScope), SaversKt.save(textLinkStyles.focusedStyle, saverKt$Saver$12, saverScope), SaversKt.save(textLinkStyles.hoveredStyle, saverKt$Saver$12, saverScope), SaversKt.save(textLinkStyles.pressedStyle, saverKt$Saver$12, saverScope));
            }
        }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$TextLinkStylesSaver$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                List list = (List) obj;
                Object obj2 = list.get(0);
                SaverKt$Saver$1 saverKt$Saver$12 = SaversKt.SpanStyleSaver;
                Boolean bool = Boolean.FALSE;
                SpanStyle spanStyle = null;
                SpanStyle spanStyle2 = ((!Intrinsics.areEqual(obj2, bool) || (saverKt$Saver$12 instanceof NonNullValueClassSaver)) && obj2 != null) ? (SpanStyle) saverKt$Saver$12.$restore.invoke(obj2) : null;
                Object obj3 = list.get(1);
                SpanStyle spanStyle3 = ((!Intrinsics.areEqual(obj3, bool) || (saverKt$Saver$12 instanceof NonNullValueClassSaver)) && obj3 != null) ? (SpanStyle) saverKt$Saver$12.$restore.invoke(obj3) : null;
                Object obj4 = list.get(2);
                SpanStyle spanStyle4 = ((!Intrinsics.areEqual(obj4, bool) || (saverKt$Saver$12 instanceof NonNullValueClassSaver)) && obj4 != null) ? (SpanStyle) saverKt$Saver$12.$restore.invoke(obj4) : null;
                Object obj5 = list.get(3);
                if ((!Intrinsics.areEqual(obj5, bool) || (saverKt$Saver$12 instanceof NonNullValueClassSaver)) && obj5 != null) {
                    spanStyle = (SpanStyle) saverKt$Saver$12.$restore.invoke(obj5);
                }
                return new TextLinkStyles(spanStyle2, spanStyle3, spanStyle4, spanStyle);
            }
        });
        TextDecorationSaver = new SaverKt$Saver$1(new Function2() { // from class: androidx.compose.ui.text.SaversKt$TextDecorationSaver$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(((TextDecoration) obj2).mask);
            }
        }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$TextDecorationSaver$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return new TextDecoration(((Integer) obj).intValue());
            }
        });
        TextGeometricTransformSaver = new SaverKt$Saver$1(new Function2() { // from class: androidx.compose.ui.text.SaversKt$TextGeometricTransformSaver$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                TextGeometricTransform textGeometricTransform = (TextGeometricTransform) obj2;
                return CollectionsKt__CollectionsKt.arrayListOf(Float.valueOf(textGeometricTransform.scaleX), Float.valueOf(textGeometricTransform.skewX));
            }
        }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$TextGeometricTransformSaver$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                List list = (List) obj;
                return new TextGeometricTransform(((Number) list.get(0)).floatValue(), ((Number) list.get(1)).floatValue());
            }
        });
        TextIndentSaver = new SaverKt$Saver$1(new Function2() { // from class: androidx.compose.ui.text.SaversKt$TextIndentSaver$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                SaverScope saverScope = (SaverScope) obj;
                TextIndent textIndent = (TextIndent) obj2;
                TextUnit textUnit = new TextUnit(textIndent.firstLine);
                SaversKt$NonNullValueClassSaver$1 saversKt$NonNullValueClassSaver$1 = SaversKt.TextUnitSaver;
                return CollectionsKt__CollectionsKt.arrayListOf(SaversKt.save(textUnit, saversKt$NonNullValueClassSaver$1, saverScope), SaversKt.save(new TextUnit(textIndent.restLine), saversKt$NonNullValueClassSaver$1, saverScope));
            }
        }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$TextIndentSaver$2
            /* JADX WARN: Type inference failed for: r1v2, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
            /* JADX WARN: Type inference failed for: r3v1, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                List list = (List) obj;
                Object obj2 = list.get(0);
                TextUnitType[] textUnitTypeArr = TextUnit.TextUnitTypes;
                SaversKt$NonNullValueClassSaver$1 saversKt$NonNullValueClassSaver$1 = SaversKt.TextUnitSaver;
                Boolean bool = Boolean.FALSE;
                TextUnit textUnit = null;
                TextUnit textUnit2 = ((Intrinsics.areEqual(obj2, bool) && saversKt$NonNullValueClassSaver$1 == null) || obj2 == null) ? null : (TextUnit) saversKt$NonNullValueClassSaver$1.$restore.invoke(obj2);
                Intrinsics.checkNotNull(textUnit2);
                Object obj3 = list.get(1);
                if ((!Intrinsics.areEqual(obj3, bool) || saversKt$NonNullValueClassSaver$1 != null) && obj3 != null) {
                    textUnit = (TextUnit) saversKt$NonNullValueClassSaver$1.$restore.invoke(obj3);
                }
                Intrinsics.checkNotNull(textUnit);
                return new TextIndent(textUnit2.packedValue, textUnit.packedValue);
            }
        });
        FontWeightSaver = new SaverKt$Saver$1(new Function2() { // from class: androidx.compose.ui.text.SaversKt$FontWeightSaver$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(((FontWeight) obj2).weight);
            }
        }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$FontWeightSaver$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return new FontWeight(((Integer) obj).intValue());
            }
        });
        BaselineShiftSaver = new SaverKt$Saver$1(new Function2() { // from class: androidx.compose.ui.text.SaversKt$BaselineShiftSaver$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Float.valueOf(((BaselineShift) obj2).multiplier);
            }
        }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$BaselineShiftSaver$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return new BaselineShift(((Float) obj).floatValue());
            }
        });
        new SaverKt$Saver$1(new Function2() { // from class: androidx.compose.ui.text.SaversKt$TextRangeSaver$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                long j = ((TextRange) obj2).packedValue;
                int i = TextRange.$r8$clinit;
                Integer valueOf = Integer.valueOf((int) (j >> 32));
                SaverKt$Saver$1 saverKt$Saver$12 = SaversKt.AnnotatedStringSaver;
                return CollectionsKt__CollectionsKt.arrayListOf(valueOf, Integer.valueOf((int) (j & 4294967295L)));
            }
        }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$TextRangeSaver$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                List list = (List) obj;
                Object obj2 = list.get(0);
                Integer num = obj2 != null ? (Integer) obj2 : null;
                Intrinsics.checkNotNull(num);
                int intValue = num.intValue();
                Object obj3 = list.get(1);
                Integer num2 = obj3 != null ? (Integer) obj3 : null;
                Intrinsics.checkNotNull(num2);
                return new TextRange(TextRangeKt.TextRange(intValue, num2.intValue()));
            }
        });
        ShadowSaver = new SaverKt$Saver$1(new Function2() { // from class: androidx.compose.ui.text.SaversKt$ShadowSaver$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                SaverScope saverScope = (SaverScope) obj;
                Shadow shadow = (Shadow) obj2;
                return CollectionsKt__CollectionsKt.arrayListOf(SaversKt.save(new Color(shadow.color), SaversKt.ColorSaver, saverScope), SaversKt.save(new Offset(shadow.offset), SaversKt.OffsetSaver, saverScope), Float.valueOf(shadow.blurRadius));
            }
        }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$ShadowSaver$2
            /* JADX WARN: Type inference failed for: r1v2, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
            /* JADX WARN: Type inference failed for: r2v2, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                List list = (List) obj;
                Object obj2 = list.get(0);
                int i = Color.$r8$clinit;
                SaversKt$NonNullValueClassSaver$1 saversKt$NonNullValueClassSaver$1 = SaversKt.ColorSaver;
                Boolean bool = Boolean.FALSE;
                Color color = ((Intrinsics.areEqual(obj2, bool) && saversKt$NonNullValueClassSaver$1 == null) || obj2 == null) ? null : (Color) saversKt$NonNullValueClassSaver$1.$restore.invoke(obj2);
                Intrinsics.checkNotNull(color);
                Object obj3 = list.get(1);
                SaversKt$NonNullValueClassSaver$1 saversKt$NonNullValueClassSaver$12 = SaversKt.OffsetSaver;
                Offset offset = ((Intrinsics.areEqual(obj3, bool) && saversKt$NonNullValueClassSaver$12 == null) || obj3 == null) ? null : (Offset) saversKt$NonNullValueClassSaver$12.$restore.invoke(obj3);
                Intrinsics.checkNotNull(offset);
                Object obj4 = list.get(2);
                Float f = obj4 != null ? (Float) obj4 : null;
                Intrinsics.checkNotNull(f);
                return new Shadow(f.floatValue(), color.value, offset.packedValue);
            }
        });
        ColorSaver = new SaversKt$NonNullValueClassSaver$1(new Function2() { // from class: androidx.compose.ui.text.SaversKt$ColorSaver$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                long j = ((Color) obj2).value;
                return j == 16 ? Boolean.FALSE : Integer.valueOf(ColorKt.m373toArgb8_81llA(j));
            }
        }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$ColorSaver$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Intrinsics.areEqual(obj, Boolean.FALSE) ? new Color(Color.Unspecified) : new Color(ColorKt.Color(((Integer) obj).intValue()));
            }
        });
        TextUnitSaver = new SaversKt$NonNullValueClassSaver$1(new Function2() { // from class: androidx.compose.ui.text.SaversKt$TextUnitSaver$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                long j = ((TextUnit) obj2).packedValue;
                if (TextUnit.m686equalsimpl0(j, TextUnit.Unspecified)) {
                    return Boolean.FALSE;
                }
                Float valueOf = Float.valueOf(TextUnit.m688getValueimpl(j));
                SaverKt$Saver$1 saverKt$Saver$12 = SaversKt.AnnotatedStringSaver;
                return CollectionsKt__CollectionsKt.arrayListOf(valueOf, new TextUnitType(TextUnit.m687getTypeUIouoOA(j)));
            }
        }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$TextUnitSaver$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                if (Intrinsics.areEqual(obj, Boolean.FALSE)) {
                    return new TextUnit(TextUnit.Unspecified);
                }
                List list = (List) obj;
                Object obj2 = list.get(0);
                Float f = obj2 != null ? (Float) obj2 : null;
                Intrinsics.checkNotNull(f);
                float floatValue = f.floatValue();
                Object obj3 = list.get(1);
                TextUnitType textUnitType = obj3 != null ? (TextUnitType) obj3 : null;
                Intrinsics.checkNotNull(textUnitType);
                return new TextUnit(TextUnitKt.pack(floatValue, textUnitType.type));
            }
        });
        OffsetSaver = new SaversKt$NonNullValueClassSaver$1(new Function2() { // from class: androidx.compose.ui.text.SaversKt$OffsetSaver$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                long j = ((Offset) obj2).packedValue;
                if (Offset.m310equalsimpl0(j, 9205357640488583168L)) {
                    return Boolean.FALSE;
                }
                Float valueOf = Float.valueOf(Float.intBitsToFloat((int) (j >> 32)));
                SaverKt$Saver$1 saverKt$Saver$12 = SaversKt.AnnotatedStringSaver;
                return CollectionsKt__CollectionsKt.arrayListOf(valueOf, Float.valueOf(Float.intBitsToFloat((int) (j & 4294967295L))));
            }
        }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$OffsetSaver$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                if (Intrinsics.areEqual(obj, Boolean.FALSE)) {
                    return new Offset(9205357640488583168L);
                }
                List list = (List) obj;
                Object obj2 = list.get(0);
                Float f = obj2 != null ? (Float) obj2 : null;
                Intrinsics.checkNotNull(f);
                float floatValue = f.floatValue();
                Object obj3 = list.get(1);
                Intrinsics.checkNotNull(obj3 != null ? (Float) obj3 : null);
                return new Offset((Float.floatToRawIntBits(r0.floatValue()) & 4294967295L) | (Float.floatToRawIntBits(floatValue) << 32));
            }
        });
        LocaleListSaver = new SaverKt$Saver$1(new Function2() { // from class: androidx.compose.ui.text.SaversKt$LocaleListSaver$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                SaverScope saverScope = (SaverScope) obj;
                List list = ((LocaleList) obj2).localeList;
                ArrayList arrayList = new ArrayList(list.size());
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    arrayList.add(SaversKt.save((Locale) list.get(i), SaversKt.LocaleSaver, saverScope));
                }
                return arrayList;
            }
        }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$LocaleListSaver$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                List list = (List) obj;
                ArrayList arrayList = new ArrayList(list.size());
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    Object obj2 = list.get(i);
                    SaverKt$Saver$1 saverKt$Saver$12 = SaversKt.LocaleSaver;
                    Locale locale = null;
                    if ((!Intrinsics.areEqual(obj2, Boolean.FALSE) || (saverKt$Saver$12 instanceof NonNullValueClassSaver)) && obj2 != null) {
                        locale = (Locale) saverKt$Saver$12.$restore.invoke(obj2);
                    }
                    Intrinsics.checkNotNull(locale);
                    arrayList.add(locale);
                }
                return new LocaleList(arrayList);
            }
        });
        LocaleSaver = new SaverKt$Saver$1(new Function2() { // from class: androidx.compose.ui.text.SaversKt$LocaleSaver$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((Locale) obj2).platformLocale.toLanguageTag();
            }
        }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$LocaleSaver$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                String str = (String) obj;
                PlatformLocaleKt.platformLocaleDelegate.getClass();
                java.util.Locale forLanguageTag = java.util.Locale.forLanguageTag(str);
                if (Intrinsics.areEqual(forLanguageTag.toLanguageTag(), "und")) {
                    Log.e("Locale", "The language tag " + str + " is not well-formed. Locale is resolved to Undetermined. Note that underscore '_' is not a valid subtag delimiter and must be replaced with '-'.");
                }
                return new Locale(forLanguageTag);
            }
        });
        LineHeightStyleSaver = new SaverKt$Saver$1(new Function2() { // from class: androidx.compose.ui.text.SaversKt$LineHeightStyleSaver$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                LineHeightStyle lineHeightStyle = (LineHeightStyle) obj2;
                LineHeightStyle.Alignment alignment = new LineHeightStyle.Alignment(lineHeightStyle.alignment);
                SaverKt$Saver$1 saverKt$Saver$12 = SaversKt.AnnotatedStringSaver;
                return CollectionsKt__CollectionsKt.arrayListOf(alignment, new LineHeightStyle.Trim(lineHeightStyle.trim), new LineHeightStyle.Mode());
            }
        }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$LineHeightStyleSaver$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                List list = (List) obj;
                Object obj2 = list.get(0);
                LineHeightStyle.Alignment alignment = obj2 != null ? (LineHeightStyle.Alignment) obj2 : null;
                Intrinsics.checkNotNull(alignment);
                Object obj3 = list.get(1);
                LineHeightStyle.Trim trim = obj3 != null ? (LineHeightStyle.Trim) obj3 : null;
                Intrinsics.checkNotNull(trim);
                Object obj4 = list.get(2);
                Intrinsics.checkNotNull(obj4 != null ? (LineHeightStyle.Mode) obj4 : null);
                return new LineHeightStyle(trim.value, alignment.topRatio);
            }
        });
    }

    public static final Object save(Object obj, Saver saver, SaverScope saverScope) {
        Object save;
        return (obj == null || (save = saver.save(saverScope, obj)) == null) ? Boolean.FALSE : save;
    }
}
