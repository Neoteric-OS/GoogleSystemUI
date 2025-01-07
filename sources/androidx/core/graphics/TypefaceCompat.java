package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.util.Log;
import androidx.appcompat.widget.AppCompatTextHelper;
import androidx.collection.LruCache;
import androidx.core.provider.FontsContractCompat$FontInfo;
import androidx.tracing.Trace;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TypefaceCompat {
    public static final LruCache sTypefaceCache;
    public static final TypefaceCompatApi29Impl sTypefaceCompatImpl;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ResourcesCallbackAdapter {
        public AppCompatTextHelper.AnonymousClass1 mFontCallback;
    }

    static {
        Trace.beginSection("TypefaceCompat static init");
        TypefaceCompatApi29Impl typefaceCompatApi29Impl = new TypefaceCompatApi29Impl();
        new ConcurrentHashMap();
        sTypefaceCompatImpl = typefaceCompatApi29Impl;
        sTypefaceCache = new LruCache(16);
        android.os.Trace.endSection();
    }

    public static Typeface createFromFontInfo(Context context, FontsContractCompat$FontInfo[] fontsContractCompat$FontInfoArr, int i) {
        Trace.beginSection("TypefaceCompat.createFromFontInfo");
        try {
            sTypefaceCompatImpl.getClass();
            Typeface typeface = null;
            try {
                FontFamily fontFamily = TypefaceCompatApi29Impl.getFontFamily(fontsContractCompat$FontInfoArr, context.getContentResolver());
                if (fontFamily != null) {
                    typeface = new Typeface.CustomFallbackBuilder(fontFamily).setStyle(TypefaceCompatApi29Impl.findBaseFont(fontFamily, i).getStyle()).build();
                }
            } catch (Exception e) {
                Log.w("TypefaceCompatApi29Impl", "Font load failed", e);
            }
            return typeface;
        } finally {
            android.os.Trace.endSection();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x002a, code lost:
    
        if (r2.equals(r4) == false) goto L15;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:111:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0218  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Typeface createFromResourcesFamilyXml(android.content.Context r14, androidx.core.content.res.FontResourcesParserCompat.FamilyResourceEntry r15, android.content.res.Resources r16, int r17, java.lang.String r18, int r19, int r20, androidx.appcompat.widget.AppCompatTextHelper.AnonymousClass1 r21, boolean r22) {
        /*
            Method dump skipped, instructions count: 546
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.TypefaceCompat.createFromResourcesFamilyXml(android.content.Context, androidx.core.content.res.FontResourcesParserCompat$FamilyResourceEntry, android.content.res.Resources, int, java.lang.String, int, int, androidx.appcompat.widget.AppCompatTextHelper$1, boolean):android.graphics.Typeface");
    }

    public static Typeface createFromResourcesFontFile(Resources resources, int i, String str, int i2, int i3) {
        Typeface typeface;
        sTypefaceCompatImpl.getClass();
        try {
            Font build = new Font.Builder(resources, i).build();
            typeface = new Typeface.CustomFallbackBuilder(new FontFamily.Builder(build).build()).setStyle(build.getStyle()).build();
        } catch (Exception e) {
            Log.w("TypefaceCompatApi29Impl", "Font load failed", e);
            typeface = null;
        }
        if (typeface != null) {
            sTypefaceCache.put(createResourceUid(resources, i, str, i2, i3), typeface);
        }
        return typeface;
    }

    public static String createResourceUid(Resources resources, int i, String str, int i2, int i3) {
        return resources.getResourcePackageName(i) + '-' + str + '-' + i2 + '-' + i + '-' + i3;
    }
}
