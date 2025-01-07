package androidx.compose.material3;

import androidx.compose.material3.tokens.TypographyTokens;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TypographyKt {
    public static final StaticProvidableCompositionLocal LocalTypography = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.material3.TypographyKt$LocalTypography$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return new Typography(TypographyTokens.DisplayLarge, TypographyTokens.DisplayMedium, TypographyTokens.DisplaySmall, TypographyTokens.HeadlineLarge, TypographyTokens.HeadlineMedium, TypographyTokens.HeadlineSmall, TypographyTokens.TitleLarge, TypographyTokens.TitleMedium, TypographyTokens.TitleSmall, TypographyTokens.BodyLarge, TypographyTokens.BodyMedium, TypographyTokens.BodySmall, TypographyTokens.LabelLarge, TypographyTokens.LabelMedium, TypographyTokens.LabelSmall);
        }
    });
}
