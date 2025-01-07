package androidx.compose.ui.text.font;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import androidx.activity.BackEventCompat$$ExternalSyntheticOutline0;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FontWeight implements Comparable {
    public static final FontWeight Bold;
    public static final FontWeight Medium;
    public static final FontWeight Normal;
    public static final FontWeight W400;
    public static final FontWeight W500;
    public static final FontWeight W600;
    public final int weight;

    static {
        FontWeight fontWeight = new FontWeight(100);
        FontWeight fontWeight2 = new FontWeight(200);
        FontWeight fontWeight3 = new FontWeight(300);
        FontWeight fontWeight4 = new FontWeight(400);
        W400 = fontWeight4;
        FontWeight fontWeight5 = new FontWeight(500);
        W500 = fontWeight5;
        FontWeight fontWeight6 = new FontWeight(600);
        W600 = fontWeight6;
        FontWeight fontWeight7 = new FontWeight(700);
        FontWeight fontWeight8 = new FontWeight(800);
        FontWeight fontWeight9 = new FontWeight(900);
        Normal = fontWeight4;
        Medium = fontWeight5;
        Bold = fontWeight7;
        CollectionsKt__CollectionsKt.listOf(fontWeight, fontWeight2, fontWeight3, fontWeight4, fontWeight5, fontWeight6, fontWeight7, fontWeight8, fontWeight9);
    }

    public FontWeight(int i) {
        this.weight = i;
        if (1 > i || i >= 1001) {
            throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Font weight can be in range [1, 1000]. Current value: ").toString());
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof FontWeight) && this.weight == ((FontWeight) obj).weight;
    }

    public final int hashCode() {
        return this.weight;
    }

    public final String toString() {
        return BackEventCompat$$ExternalSyntheticOutline0.m(new StringBuilder("FontWeight(weight="), this.weight, ')');
    }

    @Override // java.lang.Comparable
    public final int compareTo(FontWeight fontWeight) {
        return Intrinsics.compare(this.weight, fontWeight.weight);
    }
}
