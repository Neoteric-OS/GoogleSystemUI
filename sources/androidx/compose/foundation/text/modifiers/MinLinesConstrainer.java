package androidx.compose.foundation.text.modifiers;

import androidx.compose.ui.text.ParagraphKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.TextStyleKt;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DensityKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MinLinesConstrainer {
    public static MinLinesConstrainer last;
    public final Density density;
    public final FontFamily.Resolver fontFamilyResolver;
    public final TextStyle inputTextStyle;
    public final LayoutDirection layoutDirection;
    public float lineHeightCache = Float.NaN;
    public float oneLineHeightCache = Float.NaN;
    public final TextStyle resolvedStyle;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        public static MinLinesConstrainer from(MinLinesConstrainer minLinesConstrainer, LayoutDirection layoutDirection, TextStyle textStyle, Density density, FontFamily.Resolver resolver) {
            if (minLinesConstrainer != null && layoutDirection == minLinesConstrainer.layoutDirection && Intrinsics.areEqual(textStyle, minLinesConstrainer.inputTextStyle) && density.getDensity() == minLinesConstrainer.density.getDensity() && resolver == minLinesConstrainer.fontFamilyResolver) {
                return minLinesConstrainer;
            }
            MinLinesConstrainer minLinesConstrainer2 = MinLinesConstrainer.last;
            if (minLinesConstrainer2 != null && layoutDirection == minLinesConstrainer2.layoutDirection && Intrinsics.areEqual(textStyle, minLinesConstrainer2.inputTextStyle) && density.getDensity() == minLinesConstrainer2.density.getDensity() && resolver == minLinesConstrainer2.fontFamilyResolver) {
                return minLinesConstrainer2;
            }
            MinLinesConstrainer minLinesConstrainer3 = new MinLinesConstrainer(layoutDirection, TextStyleKt.resolveDefaults(textStyle, layoutDirection), DensityKt.Density(density.getDensity(), density.getFontScale()), resolver);
            MinLinesConstrainer.last = minLinesConstrainer3;
            return minLinesConstrainer3;
        }
    }

    public MinLinesConstrainer(LayoutDirection layoutDirection, TextStyle textStyle, Density density, FontFamily.Resolver resolver) {
        this.layoutDirection = layoutDirection;
        this.inputTextStyle = textStyle;
        this.density = density;
        this.fontFamilyResolver = resolver;
        this.resolvedStyle = TextStyleKt.resolveDefaults(textStyle, layoutDirection);
    }

    /* renamed from: coerceMinLines-Oh53vG4$foundation_release, reason: not valid java name */
    public final long m178coerceMinLinesOh53vG4$foundation_release(long j, int i) {
        int m656getMinHeightimpl;
        float f = this.oneLineHeightCache;
        float f2 = this.lineHeightCache;
        if (Float.isNaN(f) || Float.isNaN(f2)) {
            String str = MinLinesConstrainerKt.EmptyTextReplacement;
            long Constraints$default = ConstraintsKt.Constraints$default(0, 0, 0, 0, 15);
            Density density = this.density;
            float height = ParagraphKt.m592ParagraphUl8oQg4$default(str, this.resolvedStyle, Constraints$default, density, this.fontFamilyResolver, 1).getHeight();
            float height2 = ParagraphKt.m592ParagraphUl8oQg4$default(MinLinesConstrainerKt.TwoLineTextReplacement, this.resolvedStyle, ConstraintsKt.Constraints$default(0, 0, 0, 0, 15), density, this.fontFamilyResolver, 2).getHeight() - height;
            this.oneLineHeightCache = height;
            this.lineHeightCache = height2;
            f2 = height2;
            f = height;
        }
        if (i != 1) {
            int round = Math.round((f2 * (i - 1)) + f);
            m656getMinHeightimpl = round >= 0 ? round : 0;
            int m654getMaxHeightimpl = Constraints.m654getMaxHeightimpl(j);
            if (m656getMinHeightimpl > m654getMaxHeightimpl) {
                m656getMinHeightimpl = m654getMaxHeightimpl;
            }
        } else {
            m656getMinHeightimpl = Constraints.m656getMinHeightimpl(j);
        }
        return ConstraintsKt.Constraints(Constraints.m657getMinWidthimpl(j), Constraints.m655getMaxWidthimpl(j), m656getMinHeightimpl, Constraints.m654getMaxHeightimpl(j));
    }
}
