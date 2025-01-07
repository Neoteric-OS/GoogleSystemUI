package androidx.compose.ui.text.font;

import android.content.Context;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class FontFamilyResolver_androidKt {
    public static final FontFamilyResolverImpl createFontFamilyResolver(Context context) {
        return new FontFamilyResolverImpl(new AndroidFontLoader(context), new AndroidFontResolveInterceptor(context.getResources().getConfiguration().fontWeightAdjustment));
    }
}
