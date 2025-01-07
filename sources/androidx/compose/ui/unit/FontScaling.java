package androidx.compose.ui.unit;

import androidx.compose.ui.unit.fontscaling.FontScaleConverter;
import androidx.compose.ui.unit.fontscaling.FontScaleConverterFactory;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface FontScaling {
    float getFontScale();

    /* renamed from: toDp-GaN1DYA */
    default float mo46toDpGaN1DYA(long j) {
        if (!TextUnitType.m691equalsimpl0(TextUnit.m687getTypeUIouoOA(j), 4294967296L)) {
            InlineClassHelperKt.throwIllegalStateException("Only Sp can convert to Px");
        }
        float[] fArr = FontScaleConverterFactory.CommonFontSizes;
        if (getFontScale() < 1.03f) {
            return getFontScale() * TextUnit.m688getValueimpl(j);
        }
        FontScaleConverter forScale = FontScaleConverterFactory.forScale(getFontScale());
        if (forScale != null) {
            return forScale.convertSpToDp(TextUnit.m688getValueimpl(j));
        }
        return getFontScale() * TextUnit.m688getValueimpl(j);
    }

    /* renamed from: toSp-0xMU5do */
    default long mo53toSp0xMU5do(float f) {
        float[] fArr = FontScaleConverterFactory.CommonFontSizes;
        if (!(getFontScale() >= 1.03f)) {
            return TextUnitKt.pack(f / getFontScale(), 4294967296L);
        }
        FontScaleConverter forScale = FontScaleConverterFactory.forScale(getFontScale());
        return TextUnitKt.pack(forScale != null ? forScale.convertDpToSp(f) : f / getFontScale(), 4294967296L);
    }
}
