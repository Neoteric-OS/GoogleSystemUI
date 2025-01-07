package androidx.compose.ui.text;

import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverKt$Saver$1;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.TextMotion;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class Savers_androidKt {
    public static final SaverKt$Saver$1 LineBreakSaver;
    public static final SaverKt$Saver$1 PlatformParagraphStyleSaver;
    public static final SaverKt$Saver$1 TextMotionSaver;

    static {
        Savers_androidKt$PlatformParagraphStyleSaver$1 savers_androidKt$PlatformParagraphStyleSaver$1 = new Function2() { // from class: androidx.compose.ui.text.Savers_androidKt$PlatformParagraphStyleSaver$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Boolean valueOf = Boolean.valueOf(((PlatformParagraphStyle) obj2).includeFontPadding);
                SaverKt$Saver$1 saverKt$Saver$1 = SaversKt.AnnotatedStringSaver;
                return CollectionsKt__CollectionsKt.arrayListOf(valueOf, new EmojiSupportMatch());
            }
        };
        Savers_androidKt$PlatformParagraphStyleSaver$2 savers_androidKt$PlatformParagraphStyleSaver$2 = new Function1() { // from class: androidx.compose.ui.text.Savers_androidKt$PlatformParagraphStyleSaver$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                List list = (List) obj;
                Object obj2 = list.get(0);
                Boolean bool = obj2 != null ? (Boolean) obj2 : null;
                Intrinsics.checkNotNull(bool);
                boolean booleanValue = bool.booleanValue();
                Object obj3 = list.get(1);
                Intrinsics.checkNotNull(obj3 != null ? (EmojiSupportMatch) obj3 : null);
                return new PlatformParagraphStyle(booleanValue);
            }
        };
        SaverKt$Saver$1 saverKt$Saver$1 = SaverKt.AutoSaver;
        PlatformParagraphStyleSaver = new SaverKt$Saver$1(savers_androidKt$PlatformParagraphStyleSaver$1, savers_androidKt$PlatformParagraphStyleSaver$2);
        LineBreakSaver = new SaverKt$Saver$1(new Function2() { // from class: androidx.compose.ui.text.Savers_androidKt$LineBreakSaver$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(((LineBreak) obj2).mask);
            }
        }, new Function1() { // from class: androidx.compose.ui.text.Savers_androidKt$LineBreakSaver$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return new LineBreak(((Integer) obj).intValue());
            }
        });
        TextMotionSaver = new SaverKt$Saver$1(new Function2() { // from class: androidx.compose.ui.text.Savers_androidKt$TextMotionSaver$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                TextMotion textMotion = (TextMotion) obj2;
                TextMotion.Linearity linearity = new TextMotion.Linearity(textMotion.linearity);
                SaverKt$Saver$1 saverKt$Saver$12 = SaversKt.AnnotatedStringSaver;
                return CollectionsKt__CollectionsKt.arrayListOf(linearity, Boolean.valueOf(textMotion.subpixelTextPositioning));
            }
        }, new Function1() { // from class: androidx.compose.ui.text.Savers_androidKt$TextMotionSaver$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                List list = (List) obj;
                Object obj2 = list.get(0);
                TextMotion.Linearity linearity = obj2 != null ? (TextMotion.Linearity) obj2 : null;
                Intrinsics.checkNotNull(linearity);
                Object obj3 = list.get(1);
                Boolean bool = obj3 != null ? (Boolean) obj3 : null;
                Intrinsics.checkNotNull(bool);
                return new TextMotion(linearity.value, bool.booleanValue());
            }
        });
    }
}
