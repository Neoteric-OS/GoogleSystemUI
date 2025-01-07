package androidx.compose.foundation.text;

import androidx.compose.foundation.text.modifiers.TextAnnotatedStringElement;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntRectKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Pair;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BasicTextKt {
    /* JADX WARN: Removed duplicated region for block: B:10:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0369  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01a9  */
    /* renamed from: BasicText-RWo7tUw, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m152BasicTextRWo7tUw(final androidx.compose.ui.text.AnnotatedString r42, androidx.compose.ui.Modifier r43, androidx.compose.ui.text.TextStyle r44, kotlin.jvm.functions.Function1 r45, int r46, boolean r47, int r48, int r49, java.util.Map r50, androidx.compose.ui.graphics.ColorProducer r51, androidx.compose.runtime.Composer r52, final int r53, final int r54) {
        /*
            Method dump skipped, instructions count: 879
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.BasicTextKt.m152BasicTextRWo7tUw(androidx.compose.ui.text.AnnotatedString, androidx.compose.ui.Modifier, androidx.compose.ui.text.TextStyle, kotlin.jvm.functions.Function1, int, boolean, int, int, java.util.Map, androidx.compose.ui.graphics.ColorProducer, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x00dd  */
    /* renamed from: BasicText-VhcvRP8, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m153BasicTextVhcvRP8(final java.lang.String r32, androidx.compose.ui.Modifier r33, androidx.compose.ui.text.TextStyle r34, kotlin.jvm.functions.Function1 r35, int r36, boolean r37, int r38, int r39, androidx.compose.ui.graphics.ColorProducer r40, androidx.compose.runtime.Composer r41, final int r42, final int r43) {
        /*
            Method dump skipped, instructions count: 645
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.BasicTextKt.m153BasicTextVhcvRP8(java.lang.String, androidx.compose.ui.Modifier, androidx.compose.ui.text.TextStyle, kotlin.jvm.functions.Function1, int, boolean, int, int, androidx.compose.ui.graphics.ColorProducer, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:120:0x031f  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x033d  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x038c  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0396 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:140:0x03bc  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0437  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x044e  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x046e  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x047c  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0480  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x043b  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x03e2  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x038e  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0357  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0330  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0305  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0498  */
    /* JADX WARN: Removed duplicated region for block: B:65:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0217  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0269  */
    /* JADX WARN: Type inference failed for: r10v1, types: [androidx.compose.runtime.Composer, androidx.compose.runtime.ComposerImpl] */
    /* JADX WARN: Type inference failed for: r31v0 */
    /* JADX WARN: Type inference failed for: r31v1, types: [kotlin.jvm.functions.Function1] */
    /* JADX WARN: Type inference failed for: r31v2 */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5, types: [androidx.compose.foundation.text.TextLinkScope, java.lang.Object] */
    /* renamed from: LayoutWithLinksAndInlineContent-vOo2fZY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m154LayoutWithLinksAndInlineContentvOo2fZY(final androidx.compose.ui.Modifier r36, final androidx.compose.ui.text.AnnotatedString r37, final kotlin.jvm.functions.Function1 r38, final boolean r39, java.util.Map r40, final androidx.compose.ui.text.TextStyle r41, final int r42, final boolean r43, final int r44, final int r45, final androidx.compose.ui.text.font.FontFamily.Resolver r46, final androidx.compose.ui.graphics.ColorProducer r47, final kotlin.jvm.functions.Function1 r48, androidx.compose.runtime.Composer r49, final int r50, final int r51, final int r52) {
        /*
            Method dump skipped, instructions count: 1223
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.BasicTextKt.m154LayoutWithLinksAndInlineContentvOo2fZY(androidx.compose.ui.Modifier, androidx.compose.ui.text.AnnotatedString, kotlin.jvm.functions.Function1, boolean, java.util.Map, androidx.compose.ui.text.TextStyle, int, boolean, int, int, androidx.compose.ui.text.font.FontFamily$Resolver, androidx.compose.ui.graphics.ColorProducer, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int, int):void");
    }

    public static final List access$measureWithTextRangeMeasureConstraints(List list, Function0 function0) {
        TextRangeLayoutMeasureResult textRangeLayoutMeasureResult;
        if (!((Boolean) function0.invoke()).booleanValue()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Measurable measurable = (Measurable) list.get(i);
            TextLinkScope$$ExternalSyntheticLambda0 textLinkScope$$ExternalSyntheticLambda0 = ((TextRangeLayoutModifier) measurable.getParentData()).measurePolicy;
            TextLayoutResult textLayoutResult = (TextLayoutResult) ((SnapshotMutableStateImpl) textLinkScope$$ExternalSyntheticLambda0.f$0.textLayoutResult$delegate).getValue();
            if (textLayoutResult == null) {
                textRangeLayoutMeasureResult = new TextRangeLayoutMeasureResult(0, 0, new Function0() { // from class: androidx.compose.foundation.text.TextLinkScope$textRange$1$layoutResult$1
                    @Override // kotlin.jvm.functions.Function0
                    public final /* synthetic */ Object invoke() {
                        return new IntOffset(0L);
                    }
                });
            } else {
                final IntRect roundToIntRect = IntRectKt.roundToIntRect(textLayoutResult.getPathForRange(textLinkScope$$ExternalSyntheticLambda0.f$1, textLinkScope$$ExternalSyntheticLambda0.f$2).getBounds());
                textRangeLayoutMeasureResult = new TextRangeLayoutMeasureResult(roundToIntRect.getWidth(), roundToIntRect.getHeight(), new Function0() { // from class: androidx.compose.foundation.text.TextLinkScope$textRange$1$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return new IntOffset(IntRect.this.m680getTopLeftnOccac());
                    }
                });
            }
            int i2 = textRangeLayoutMeasureResult.width;
            int i3 = textRangeLayoutMeasureResult.height;
            arrayList.add(new Pair(measurable.mo479measureBRTryo0(Constraints.Companion.m660fitPrioritizingWidthZbe2FdA(i2, i2, i3, i3)), textRangeLayoutMeasureResult.place));
        }
        return arrayList;
    }

    /* renamed from: textModifier-cFh6CEA, reason: not valid java name */
    public static final Modifier m155textModifiercFh6CEA(Modifier modifier, AnnotatedString annotatedString, TextStyle textStyle, Function1 function1, int i, boolean z, int i2, int i3, FontFamily.Resolver resolver, List list, Function1 function12, ColorProducer colorProducer, Function1 function13) {
        return modifier.then(Modifier.Companion.$$INSTANCE).then(new TextAnnotatedStringElement(annotatedString, textStyle, resolver, function1, i, z, i2, i3, list, function12, colorProducer, function13));
    }
}
