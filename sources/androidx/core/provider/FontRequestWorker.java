package androidx.core.provider;

import android.content.Context;
import android.graphics.Typeface;
import androidx.collection.LruCache;
import androidx.collection.SimpleArrayMap;
import androidx.core.util.Consumer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class FontRequestWorker {
    public static final ExecutorService DEFAULT_EXECUTOR_SERVICE;
    public static final Object LOCK;
    public static final SimpleArrayMap PENDING_REPLIES;
    public static final LruCache sTypefaceCache = new LruCache(16);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.core.provider.FontRequestWorker$1, reason: invalid class name */
    public final class AnonymousClass1 implements Callable {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ Context val$context;
        public final /* synthetic */ String val$id;
        public final /* synthetic */ Object val$request;
        public final /* synthetic */ int val$style;

        public /* synthetic */ AnonymousClass1(String str, Context context, Object obj, int i, int i2) {
            this.$r8$classId = i2;
            this.val$id = str;
            this.val$context = context;
            this.val$request = obj;
            this.val$style = i;
        }

        @Override // java.util.concurrent.Callable
        public final Object call() {
            switch (this.$r8$classId) {
                case 0:
                    return FontRequestWorker.getFontSync(this.val$id, this.val$context, List.of((FontRequest) this.val$request), this.val$style);
                default:
                    try {
                        return FontRequestWorker.getFontSync(this.val$id, this.val$context, (List) this.val$request, this.val$style);
                    } catch (Throwable unused) {
                        return new TypefaceResult(-3);
                    }
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.core.provider.FontRequestWorker$2, reason: invalid class name */
    public final class AnonymousClass2 implements Consumer {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ Object val$callback;

        public /* synthetic */ AnonymousClass2(int i, Object obj) {
            this.$r8$classId = i;
            this.val$callback = obj;
        }

        @Override // androidx.core.util.Consumer
        public final void accept(Object obj) {
            switch (this.$r8$classId) {
                case 0:
                    TypefaceResult typefaceResult = (TypefaceResult) obj;
                    if (typefaceResult == null) {
                        typefaceResult = new TypefaceResult(-3);
                    }
                    ((CallbackWrapper) this.val$callback).onTypefaceResult(typefaceResult);
                    return;
                default:
                    TypefaceResult typefaceResult2 = (TypefaceResult) obj;
                    synchronized (FontRequestWorker.LOCK) {
                        try {
                            SimpleArrayMap simpleArrayMap = FontRequestWorker.PENDING_REPLIES;
                            ArrayList arrayList = (ArrayList) simpleArrayMap.get((String) this.val$callback);
                            if (arrayList == null) {
                                return;
                            }
                            simpleArrayMap.remove((String) this.val$callback);
                            for (int i = 0; i < arrayList.size(); i++) {
                                ((Consumer) arrayList.get(i)).accept(typefaceResult2);
                            }
                            return;
                        } finally {
                        }
                    }
            }
        }
    }

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 10000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque(), new RequestExecutor$DefaultThreadFactory());
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        DEFAULT_EXECUTOR_SERVICE = threadPoolExecutor;
        LOCK = new Object();
        PENDING_REPLIES = new SimpleArrayMap(0);
    }

    public static String createCacheId(int i, List list) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < list.size(); i2++) {
            sb.append(((FontRequest) list.get(i2)).mIdentifier);
            sb.append("-");
            sb.append(i);
            if (i2 < list.size() - 1) {
                sb.append(";");
            }
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0050 A[Catch: all -> 0x0019, TRY_LEAVE, TryCatch #1 {all -> 0x0019, all -> 0x0074, NameNotFoundException -> 0x009c, blocks: (B:3:0x0008, B:5:0x0010, B:10:0x001c, B:11:0x0020, B:16:0x0050, B:19:0x0059, B:21:0x0061, B:24:0x0070, B:26:0x0087, B:29:0x0093, B:34:0x0075, B:35:0x0078, B:36:0x0079, B:38:0x002c, B:40:0x0036, B:43:0x003a, B:45:0x003e, B:47:0x0049, B:56:0x009c, B:23:0x006a), top: B:2:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0059 A[Catch: all -> 0x0019, TRY_ENTER, TryCatch #1 {all -> 0x0019, all -> 0x0074, NameNotFoundException -> 0x009c, blocks: (B:3:0x0008, B:5:0x0010, B:10:0x001c, B:11:0x0020, B:16:0x0050, B:19:0x0059, B:21:0x0061, B:24:0x0070, B:26:0x0087, B:29:0x0093, B:34:0x0075, B:35:0x0078, B:36:0x0079, B:38:0x002c, B:40:0x0036, B:43:0x003a, B:45:0x003e, B:47:0x0049, B:56:0x009c, B:23:0x006a), top: B:2:0x0008 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static androidx.core.provider.FontRequestWorker.TypefaceResult getFontSync(java.lang.String r8, android.content.Context r9, java.util.List r10, int r11) {
        /*
            r0 = 1
            java.lang.String r1 = "getFontSync"
            androidx.tracing.Trace.beginSection(r1)
            androidx.collection.LruCache r1 = androidx.core.provider.FontRequestWorker.sTypefaceCache
            java.lang.Object r2 = r1.get(r8)     // Catch: java.lang.Throwable -> L19
            android.graphics.Typeface r2 = (android.graphics.Typeface) r2     // Catch: java.lang.Throwable -> L19
            if (r2 == 0) goto L1c
            androidx.core.provider.FontRequestWorker$TypefaceResult r8 = new androidx.core.provider.FontRequestWorker$TypefaceResult     // Catch: java.lang.Throwable -> L19
            r8.<init>(r2)     // Catch: java.lang.Throwable -> L19
            android.os.Trace.endSection()
            return r8
        L19:
            r8 = move-exception
            goto La6
        L1c:
            androidx.core.provider.FontsContractCompat$FontFamilyResult r10 = androidx.core.provider.FontProvider.getFontFamilyResult(r9, r10)     // Catch: java.lang.Throwable -> L19 android.content.pm.PackageManager.NameNotFoundException -> L9c
            int r2 = r10.mStatusCode     // Catch: java.lang.Throwable -> L19
            r3 = 0
            r4 = -3
            if (r2 == 0) goto L2c
            if (r2 == r0) goto L2a
        L28:
            r2 = r4
            goto L4e
        L2a:
            r2 = -2
            goto L4e
        L2c:
            java.util.List r2 = r10.mFonts     // Catch: java.lang.Throwable -> L19
            java.lang.Object r2 = r2.get(r3)     // Catch: java.lang.Throwable -> L19
            androidx.core.provider.FontsContractCompat$FontInfo[] r2 = (androidx.core.provider.FontsContractCompat$FontInfo[]) r2     // Catch: java.lang.Throwable -> L19
            if (r2 == 0) goto L4d
            int r5 = r2.length     // Catch: java.lang.Throwable -> L19
            if (r5 != 0) goto L3a
            goto L4d
        L3a:
            int r5 = r2.length     // Catch: java.lang.Throwable -> L19
            r6 = r3
        L3c:
            if (r6 >= r5) goto L4b
            r7 = r2[r6]     // Catch: java.lang.Throwable -> L19
            int r7 = r7.mResultCode     // Catch: java.lang.Throwable -> L19
            if (r7 == 0) goto L49
            if (r7 >= 0) goto L47
            goto L28
        L47:
            r2 = r7
            goto L4e
        L49:
            int r6 = r6 + r0
            goto L3c
        L4b:
            r2 = r3
            goto L4e
        L4d:
            r2 = r0
        L4e:
            if (r2 == 0) goto L59
            androidx.core.provider.FontRequestWorker$TypefaceResult r8 = new androidx.core.provider.FontRequestWorker$TypefaceResult     // Catch: java.lang.Throwable -> L19
            r8.<init>(r2)     // Catch: java.lang.Throwable -> L19
            android.os.Trace.endSection()
            return r8
        L59:
            java.util.List r2 = r10.mFonts     // Catch: java.lang.Throwable -> L19
            int r2 = r2.size()     // Catch: java.lang.Throwable -> L19
            if (r2 <= r0) goto L79
            java.util.List r10 = r10.mFonts     // Catch: java.lang.Throwable -> L19
            androidx.core.graphics.TypefaceCompatApi29Impl r0 = androidx.core.graphics.TypefaceCompat.sTypefaceCompatImpl     // Catch: java.lang.Throwable -> L19
            java.lang.String r0 = "TypefaceCompat.createFromFontInfoWithFallback"
            androidx.tracing.Trace.beginSection(r0)     // Catch: java.lang.Throwable -> L19
            androidx.core.graphics.TypefaceCompatApi29Impl r0 = androidx.core.graphics.TypefaceCompat.sTypefaceCompatImpl     // Catch: java.lang.Throwable -> L74
            android.graphics.Typeface r9 = r0.createFromFontInfoWithFallback(r9, r10, r11)     // Catch: java.lang.Throwable -> L74
            android.os.Trace.endSection()     // Catch: java.lang.Throwable -> L19
            goto L85
        L74:
            r8 = move-exception
            android.os.Trace.endSection()     // Catch: java.lang.Throwable -> L19
            throw r8     // Catch: java.lang.Throwable -> L19
        L79:
            java.util.List r10 = r10.mFonts     // Catch: java.lang.Throwable -> L19
            java.lang.Object r10 = r10.get(r3)     // Catch: java.lang.Throwable -> L19
            androidx.core.provider.FontsContractCompat$FontInfo[] r10 = (androidx.core.provider.FontsContractCompat$FontInfo[]) r10     // Catch: java.lang.Throwable -> L19
            android.graphics.Typeface r9 = androidx.core.graphics.TypefaceCompat.createFromFontInfo(r9, r10, r11)     // Catch: java.lang.Throwable -> L19
        L85:
            if (r9 == 0) goto L93
            r1.put(r8, r9)     // Catch: java.lang.Throwable -> L19
            androidx.core.provider.FontRequestWorker$TypefaceResult r8 = new androidx.core.provider.FontRequestWorker$TypefaceResult     // Catch: java.lang.Throwable -> L19
            r8.<init>(r9)     // Catch: java.lang.Throwable -> L19
            android.os.Trace.endSection()
            return r8
        L93:
            androidx.core.provider.FontRequestWorker$TypefaceResult r8 = new androidx.core.provider.FontRequestWorker$TypefaceResult     // Catch: java.lang.Throwable -> L19
            r8.<init>(r4)     // Catch: java.lang.Throwable -> L19
            android.os.Trace.endSection()
            return r8
        L9c:
            androidx.core.provider.FontRequestWorker$TypefaceResult r8 = new androidx.core.provider.FontRequestWorker$TypefaceResult     // Catch: java.lang.Throwable -> L19
            r9 = -1
            r8.<init>(r9)     // Catch: java.lang.Throwable -> L19
            android.os.Trace.endSection()
            return r8
        La6:
            android.os.Trace.endSection()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.provider.FontRequestWorker.getFontSync(java.lang.String, android.content.Context, java.util.List, int):androidx.core.provider.FontRequestWorker$TypefaceResult");
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TypefaceResult {
        public final int mResult;
        public final Typeface mTypeface;

        public TypefaceResult(int i) {
            this.mTypeface = null;
            this.mResult = i;
        }

        public TypefaceResult(Typeface typeface) {
            this.mTypeface = typeface;
            this.mResult = 0;
        }
    }
}
