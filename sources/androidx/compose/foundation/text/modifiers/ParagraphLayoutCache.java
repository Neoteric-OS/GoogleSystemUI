package androidx.compose.foundation.text.modifiers;

import androidx.compose.foundation.text.TextDelegateKt;
import androidx.compose.ui.text.AndroidParagraph;
import androidx.compose.ui.text.ParagraphIntrinsics;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.TextStyleKt;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.platform.AndroidParagraphIntrinsics;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitType;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ParagraphLayoutCache {
    public Density density;
    public boolean didOverflow;
    public FontFamily.Resolver fontFamilyResolver;
    public LayoutDirection intrinsicsLayoutDirection;
    public long layoutSize;
    public MinLinesConstrainer mMinLinesConstrainer;
    public int maxLines;
    public int minLines;
    public int overflow;
    public AndroidParagraph paragraph;
    public ParagraphIntrinsics paragraphIntrinsics;
    public boolean softWrap;
    public TextStyle style;
    public String text;
    public long lastDensity = InlineDensity.Unspecified;
    public long prevConstraints = Constraints.Companion.m661fixedJhjzzOo(0, 0);
    public int cachedIntrinsicHeightInputWidth = -1;
    public int cachedIntrinsicHeight = -1;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class FontSizeSearchScopeImpl implements Density {
        public final LayoutDirection layoutDirection;
        public long originalFontSize;

        public FontSizeSearchScopeImpl() {
            Constraints.Companion.m661fixedJhjzzOo(0, 0);
            this.layoutDirection = LayoutDirection.Ltr;
            this.originalFontSize = TextUnit.Unspecified;
        }

        @Override // androidx.compose.ui.unit.Density
        public final float getDensity() {
            Density density = ParagraphLayoutCache.this.density;
            Intrinsics.checkNotNull(density);
            return density.getDensity();
        }

        @Override // androidx.compose.ui.unit.FontScaling
        public final float getFontScale() {
            Density density = ParagraphLayoutCache.this.density;
            Intrinsics.checkNotNull(density);
            return density.getFontScale();
        }

        @Override // androidx.compose.ui.unit.Density
        /* renamed from: toPx--R2X_6o */
        public final float mo50toPxR2X_6o(long j) {
            if (!TextUnit.m689isEmimpl(j)) {
                return getDensity() * mo46toDpGaN1DYA(j);
            }
            if (TextUnit.m689isEmimpl(this.originalFontSize)) {
                throw new IllegalStateException("AutoSize -> toPx(): Cannot convert Em to Px when style.fontSize is Em\nDeclare the composable's style.fontSize with Sp units instead.");
            }
            long j2 = this.originalFontSize;
            TextUnitType[] textUnitTypeArr = TextUnit.TextUnitTypes;
            if (TextUnit.m686equalsimpl0(j2, TextUnit.Unspecified)) {
                TextStyle textStyle = TextStyle.Default;
                this.originalFontSize = TextStyleKt.resolveDefaults(TextStyle.Default, this.layoutDirection).spanStyle.fontSize;
            }
            return TextUnit.m688getValueimpl(j) * mo50toPxR2X_6o(this.originalFontSize);
        }
    }

    public ParagraphLayoutCache(String str, TextStyle textStyle, FontFamily.Resolver resolver, int i, boolean z, int i2, int i3) {
        this.text = str;
        this.style = textStyle;
        this.fontFamilyResolver = resolver;
        this.overflow = i;
        this.softWrap = z;
        this.maxLines = i2;
        this.minLines = i3;
        long j = 0;
        this.layoutSize = (j & 4294967295L) | (j << 32);
        new FontSizeSearchScopeImpl();
    }

    public final int intrinsicHeight(int i, LayoutDirection layoutDirection) {
        int i2;
        int i3 = this.cachedIntrinsicHeightInputWidth;
        int i4 = this.cachedIntrinsicHeight;
        if (i == i3 && i3 != -1) {
            return i4;
        }
        long Constraints = ConstraintsKt.Constraints(0, i, 0, Integer.MAX_VALUE);
        ParagraphIntrinsics layoutDirection2 = setLayoutDirection(layoutDirection);
        long m177finalConstraintstfFHcEY = LayoutUtilsKt.m177finalConstraintstfFHcEY(Constraints, this.softWrap, this.overflow, layoutDirection2.getMaxIntrinsicWidth());
        boolean z = this.softWrap;
        int i5 = this.overflow;
        int i6 = this.maxLines;
        if (z || !(TextOverflow.m647equalsimpl0(i5, 2) || TextOverflow.m647equalsimpl0(i5, 4) || TextOverflow.m647equalsimpl0(i5, 5))) {
            if (i6 < 1) {
                i6 = 1;
            }
            i2 = i6;
        } else {
            i2 = 1;
        }
        int ceilToIntPx = TextDelegateKt.ceilToIntPx(new AndroidParagraph((AndroidParagraphIntrinsics) layoutDirection2, i2, this.overflow, m177finalConstraintstfFHcEY).getHeight());
        this.cachedIntrinsicHeightInputWidth = i;
        this.cachedIntrinsicHeight = ceilToIntPx;
        return ceilToIntPx;
    }

    public final void markDirty() {
        this.paragraph = null;
        this.paragraphIntrinsics = null;
        this.intrinsicsLayoutDirection = null;
        this.cachedIntrinsicHeightInputWidth = -1;
        this.cachedIntrinsicHeight = -1;
        this.prevConstraints = Constraints.Companion.m661fixedJhjzzOo(0, 0);
        long j = 0;
        this.layoutSize = (j & 4294967295L) | (j << 32);
        this.didOverflow = false;
    }

    public final void setDensity$foundation_release(Density density) {
        long j;
        Density density2 = this.density;
        if (density != null) {
            int i = InlineDensity.$r8$clinit;
            j = InlineDensity.m176constructorimpl(density.getDensity(), density.getFontScale());
        } else {
            j = InlineDensity.Unspecified;
        }
        if (density2 == null) {
            this.density = density;
            this.lastDensity = j;
        } else if (density == null || this.lastDensity != j) {
            this.density = density;
            this.lastDensity = j;
            markDirty();
        }
    }

    public final ParagraphIntrinsics setLayoutDirection(LayoutDirection layoutDirection) {
        ParagraphIntrinsics paragraphIntrinsics = this.paragraphIntrinsics;
        if (paragraphIntrinsics == null || layoutDirection != this.intrinsicsLayoutDirection || paragraphIntrinsics.getHasStaleResolvedFonts()) {
            this.intrinsicsLayoutDirection = layoutDirection;
            String str = this.text;
            TextStyle resolveDefaults = TextStyleKt.resolveDefaults(this.style, layoutDirection);
            Density density = this.density;
            Intrinsics.checkNotNull(density);
            FontFamily.Resolver resolver = this.fontFamilyResolver;
            EmptyList emptyList = EmptyList.INSTANCE;
            paragraphIntrinsics = new AndroidParagraphIntrinsics(str, resolveDefaults, emptyList, emptyList, resolver, density);
        }
        this.paragraphIntrinsics = paragraphIntrinsics;
        return paragraphIntrinsics;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ParagraphLayoutCache(paragraph=");
        sb.append(this.paragraph != null ? "<paragraph>" : "null");
        sb.append(", lastDensity=");
        long j = this.lastDensity;
        int i = InlineDensity.$r8$clinit;
        sb.append((Object) ("InlineDensity(density=" + Float.intBitsToFloat((int) (j >> 32)) + ", fontScale=" + Float.intBitsToFloat((int) (j & 4294967295L)) + ')'));
        sb.append(')');
        return sb.toString();
    }
}
