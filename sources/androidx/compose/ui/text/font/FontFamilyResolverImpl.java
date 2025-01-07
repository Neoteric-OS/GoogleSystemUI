package androidx.compose.ui.text.font;

import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.platform.SynchronizedObject;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FontFamilyResolverImpl implements FontFamily.Resolver {
    public final Function1 createDefaultTypeface;
    public final FontListFontFamilyTypefaceAdapter fontListFontFamilyTypefaceAdapter;
    public final PlatformFontFamilyTypefaceAdapter platformFamilyTypefaceAdapter;
    public final AndroidFontLoader platformFontLoader;
    public final AndroidFontResolveInterceptor platformResolveInterceptor;
    public final TypefaceRequestCache typefaceRequestCache;

    public FontFamilyResolverImpl(AndroidFontLoader androidFontLoader, AndroidFontResolveInterceptor androidFontResolveInterceptor) {
        TypefaceRequestCache typefaceRequestCache = FontFamilyResolverKt.GlobalTypefaceRequestCache;
        FontListFontFamilyTypefaceAdapter fontListFontFamilyTypefaceAdapter = new FontListFontFamilyTypefaceAdapter(FontFamilyResolverKt.GlobalAsyncTypefaceCache);
        PlatformFontFamilyTypefaceAdapter platformFontFamilyTypefaceAdapter = new PlatformFontFamilyTypefaceAdapter();
        this.platformFontLoader = androidFontLoader;
        this.platformResolveInterceptor = androidFontResolveInterceptor;
        this.typefaceRequestCache = typefaceRequestCache;
        this.fontListFontFamilyTypefaceAdapter = fontListFontFamilyTypefaceAdapter;
        this.platformFamilyTypefaceAdapter = platformFontFamilyTypefaceAdapter;
        this.createDefaultTypeface = new FontFamilyResolverImpl$createDefaultTypeface$1(this);
    }

    public final TypefaceResult resolve(final TypefaceRequest typefaceRequest) {
        TypefaceResult typefaceResult;
        final TypefaceRequestCache typefaceRequestCache = this.typefaceRequestCache;
        Function1 function1 = new Function1() { // from class: androidx.compose.ui.text.font.FontFamilyResolverImpl$resolve$result$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Removed duplicated region for block: B:13:0x0396  */
            /* JADX WARN: Removed duplicated region for block: B:16:0x0398  */
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invoke(java.lang.Object r18) {
                /*
                    Method dump skipped, instructions count: 929
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.font.FontFamilyResolverImpl$resolve$result$1.invoke(java.lang.Object):java.lang.Object");
            }
        };
        synchronized (typefaceRequestCache.lock) {
            typefaceResult = (TypefaceResult) typefaceRequestCache.resultCache.get(typefaceRequest);
            if (typefaceResult != null) {
                if (!typefaceResult.getCacheable()) {
                }
            }
            try {
                typefaceResult = (TypefaceResult) function1.invoke(new Function1() { // from class: androidx.compose.ui.text.font.TypefaceRequestCache$runCached$currentTypefaceResult$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        TypefaceResult typefaceResult2 = (TypefaceResult) obj;
                        TypefaceRequestCache typefaceRequestCache2 = TypefaceRequestCache.this;
                        SynchronizedObject synchronizedObject = typefaceRequestCache2.lock;
                        TypefaceRequest typefaceRequest2 = typefaceRequest;
                        synchronized (synchronizedObject) {
                            try {
                                if (typefaceResult2.getCacheable()) {
                                    typefaceRequestCache2.resultCache.put(typefaceRequest2, typefaceResult2);
                                } else {
                                    typefaceRequestCache2.resultCache.remove(typefaceRequest2);
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                        return Unit.INSTANCE;
                    }
                });
                synchronized (typefaceRequestCache.lock) {
                    if (typefaceRequestCache.resultCache.get(typefaceRequest) == null && typefaceResult.getCacheable()) {
                        typefaceRequestCache.resultCache.put(typefaceRequest, typefaceResult);
                    }
                }
            } catch (Exception e) {
                throw new IllegalStateException("Could not load font", e);
            }
        }
        return typefaceResult;
    }

    /* renamed from: resolve-DPcqOEQ, reason: not valid java name */
    public final TypefaceResult m608resolveDPcqOEQ(FontFamily fontFamily, FontWeight fontWeight, int i, int i2) {
        AndroidFontResolveInterceptor androidFontResolveInterceptor = this.platformResolveInterceptor;
        androidFontResolveInterceptor.getClass();
        int i3 = androidFontResolveInterceptor.fontWeightAdjustment;
        FontWeight fontWeight2 = (i3 == 0 || i3 == Integer.MAX_VALUE) ? fontWeight : new FontWeight(RangesKt.coerceIn(fontWeight.weight + i3, 1, 1000));
        this.platformFontLoader.getClass();
        return resolve(new TypefaceRequest(fontFamily, fontWeight2, i, i2, null));
    }
}
