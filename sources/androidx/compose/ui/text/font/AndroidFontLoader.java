package androidx.compose.ui.text.font;

import android.content.Context;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AndroidFontLoader {
    public final Context context;

    public AndroidFontLoader(Context context) {
        this.context = context.getApplicationContext();
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0075, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r0, androidx.compose.ui.text.font.PlatformTypefacesApi28.m614createAndroidTypefaceApi28RetOiIg(null, r4, 0)) == false) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadBlocking(androidx.compose.ui.text.font.Font r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof androidx.compose.ui.text.font.AndroidFont
            r1 = 0
            if (r0 == 0) goto Lb7
            androidx.compose.ui.text.font.AndroidFont r8 = (androidx.compose.ui.text.font.AndroidFont) r8
            r8.getClass()
            android.content.Context r7 = r7.context
            boolean r0 = r8 instanceof androidx.compose.ui.text.font.DeviceFontFamilyNameFont
            if (r0 == 0) goto L13
            androidx.compose.ui.text.font.DeviceFontFamilyNameFont r8 = (androidx.compose.ui.text.font.DeviceFontFamilyNameFont) r8
            goto L14
        L13:
            r8 = r1
        L14:
            if (r8 == 0) goto Lb7
            java.lang.String r0 = r8.familyName
            java.lang.String r2 = "sans-serif"
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r2)
            androidx.compose.ui.text.font.FontWeight r4 = r8.weight
            r5 = 0
            if (r3 == 0) goto L28
            android.graphics.Typeface r0 = androidx.compose.ui.text.font.PlatformTypefacesApi28.m614createAndroidTypefaceApi28RetOiIg(r2, r4, r5)
            goto L79
        L28:
            java.lang.String r2 = "serif"
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r2)
            if (r3 == 0) goto L35
            android.graphics.Typeface r0 = androidx.compose.ui.text.font.PlatformTypefacesApi28.m614createAndroidTypefaceApi28RetOiIg(r2, r4, r5)
            goto L79
        L35:
            java.lang.String r2 = "monospace"
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r2)
            if (r3 == 0) goto L42
            android.graphics.Typeface r0 = androidx.compose.ui.text.font.PlatformTypefacesApi28.m614createAndroidTypefaceApi28RetOiIg(r2, r4, r5)
            goto L79
        L42:
            java.lang.String r2 = "cursive"
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r2)
            if (r3 == 0) goto L4f
            android.graphics.Typeface r0 = androidx.compose.ui.text.font.PlatformTypefacesApi28.m614createAndroidTypefaceApi28RetOiIg(r2, r4, r5)
            goto L79
        L4f:
            int r2 = r0.length()
            if (r2 != 0) goto L56
            goto L78
        L56:
            android.graphics.Typeface r0 = androidx.compose.ui.text.font.PlatformTypefacesApi28.m614createAndroidTypefaceApi28RetOiIg(r0, r4, r5)
            r2 = 1
            boolean r2 = androidx.compose.ui.text.font.FontStyle.m609equalsimpl0(r5, r2)
            android.graphics.Typeface r3 = android.graphics.Typeface.DEFAULT
            int r6 = r4.weight
            android.graphics.Typeface r2 = android.graphics.Typeface.create(r3, r6, r2)
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r2)
            if (r2 != 0) goto L78
            android.graphics.Typeface r2 = androidx.compose.ui.text.font.PlatformTypefacesApi28.m614createAndroidTypefaceApi28RetOiIg(r1, r4, r5)
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r2)
            if (r2 != 0) goto L78
            goto L79
        L78:
            r0 = r1
        L79:
            androidx.compose.ui.text.font.FontVariation$Settings r8 = r8.variationSettings
            java.lang.ThreadLocal r2 = androidx.compose.ui.text.font.TypefaceCompatApi26.threadLocalPaint
            if (r0 != 0) goto L80
            goto Lb7
        L80:
            java.util.List r2 = r8.settings
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L8a
            r1 = r0
            goto Lb7
        L8a:
            java.lang.ThreadLocal r2 = androidx.compose.ui.text.font.TypefaceCompatApi26.threadLocalPaint
            java.lang.Object r3 = r2.get()
            android.graphics.Paint r3 = (android.graphics.Paint) r3
            if (r3 != 0) goto L9c
            android.graphics.Paint r3 = new android.graphics.Paint
            r3.<init>()
            r2.set(r3)
        L9c:
            r3.setTypeface(r0)
            androidx.compose.ui.unit.Density r7 = androidx.compose.ui.unit.AndroidDensity_androidKt.Density(r7)
            java.util.List r8 = r8.settings
            androidx.compose.ui.text.font.TypefaceCompatApi26$toAndroidString$1 r0 = new androidx.compose.ui.text.font.TypefaceCompatApi26$toAndroidString$1
            r0.<init>()
            r7 = 31
            java.lang.String r7 = androidx.compose.ui.util.ListUtilsKt.fastJoinToString$default(r8, r1, r0, r7)
            r3.setFontVariationSettings(r7)
            android.graphics.Typeface r1 = r3.getTypeface()
        Lb7:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.font.AndroidFontLoader.loadBlocking(androidx.compose.ui.text.font.Font):java.lang.Object");
    }
}
