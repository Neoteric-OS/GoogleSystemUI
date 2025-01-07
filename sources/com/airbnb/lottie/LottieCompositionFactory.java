package com.airbnb.lottie;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.util.Base64;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.LottieCompositionCache;
import com.airbnb.lottie.parser.LottieCompositionMoshiParser;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.parser.moshi.JsonUtf8Reader;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import okio.Okio;
import okio.PeekSource;
import okio.RealBufferedSource;
import okio.RealBufferedSource$inputStream$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LottieCompositionFactory {
    public static final Map taskCache = new HashMap();
    public static final Set taskIdleListeners = new HashSet();
    public static final byte[] ZIP_MAGIC = {80, 75, 3, 4};
    public static final byte[] GZIP_MAGIC = {31, -117, 8};

    public static LottieTask cache(final String str, Callable callable, Runnable runnable) {
        LottieComposition lottieComposition = str == null ? null : LottieCompositionCache.INSTANCE.get(str);
        LottieTask lottieTask = lottieComposition != null ? new LottieTask(lottieComposition) : null;
        if (str != null) {
            Map map = taskCache;
            if (map.containsKey(str)) {
                lottieTask = (LottieTask) map.get(str);
            }
        }
        if (lottieTask != null) {
            if (runnable != null) {
                runnable.run();
            }
            return lottieTask;
        }
        LottieTask lottieTask2 = new LottieTask(callable, false);
        if (str != null) {
            final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            final int i = 0;
            lottieTask2.addListener(new LottieListener() { // from class: com.airbnb.lottie.LottieCompositionFactory$$ExternalSyntheticLambda7
                @Override // com.airbnb.lottie.LottieListener
                public final void onResult(Object obj) {
                    switch (i) {
                        case 0:
                            AtomicBoolean atomicBoolean2 = atomicBoolean;
                            Map map2 = LottieCompositionFactory.taskCache;
                            map2.remove(str);
                            atomicBoolean2.set(true);
                            if (map2.size() == 0) {
                                LottieCompositionFactory.notifyTaskCacheIdleListeners();
                                break;
                            }
                            break;
                        default:
                            AtomicBoolean atomicBoolean3 = atomicBoolean;
                            Map map3 = LottieCompositionFactory.taskCache;
                            map3.remove(str);
                            atomicBoolean3.set(true);
                            if (map3.size() == 0) {
                                LottieCompositionFactory.notifyTaskCacheIdleListeners();
                                break;
                            }
                            break;
                    }
                }
            });
            final int i2 = 1;
            lottieTask2.addFailureListener(new LottieListener() { // from class: com.airbnb.lottie.LottieCompositionFactory$$ExternalSyntheticLambda7
                @Override // com.airbnb.lottie.LottieListener
                public final void onResult(Object obj) {
                    switch (i2) {
                        case 0:
                            AtomicBoolean atomicBoolean2 = atomicBoolean;
                            Map map2 = LottieCompositionFactory.taskCache;
                            map2.remove(str);
                            atomicBoolean2.set(true);
                            if (map2.size() == 0) {
                                LottieCompositionFactory.notifyTaskCacheIdleListeners();
                                break;
                            }
                            break;
                        default:
                            AtomicBoolean atomicBoolean3 = atomicBoolean;
                            Map map3 = LottieCompositionFactory.taskCache;
                            map3.remove(str);
                            atomicBoolean3.set(true);
                            if (map3.size() == 0) {
                                LottieCompositionFactory.notifyTaskCacheIdleListeners();
                                break;
                            }
                            break;
                    }
                }
            });
            if (!atomicBoolean.get()) {
                Map map2 = taskCache;
                map2.put(str, lottieTask2);
                if (map2.size() == 1) {
                    notifyTaskCacheIdleListeners();
                }
            }
        }
        return lottieTask2;
    }

    public static LottieResult fromAssetSync(Context context, String str, String str2) {
        LottieComposition lottieComposition = str2 == null ? null : LottieCompositionCache.INSTANCE.get(str2);
        if (lottieComposition != null) {
            return new LottieResult(lottieComposition);
        }
        try {
            RealBufferedSource realBufferedSource = new RealBufferedSource(Okio.source(context.getAssets().open(str)));
            return matchesMagicBytes(realBufferedSource, ZIP_MAGIC).booleanValue() ? fromZipStreamSync(context, new ZipInputStream(new RealBufferedSource$inputStream$1(realBufferedSource)), str2) : matchesMagicBytes(realBufferedSource, GZIP_MAGIC).booleanValue() ? fromJsonInputStreamSync(new GZIPInputStream(new RealBufferedSource$inputStream$1(realBufferedSource)), str2) : fromJsonInputStreamSync(new RealBufferedSource$inputStream$1(realBufferedSource), str2);
        } catch (IOException e) {
            return new LottieResult(e);
        }
    }

    public static LottieResult fromJsonInputStreamSync(InputStream inputStream, String str) {
        RealBufferedSource realBufferedSource = new RealBufferedSource(Okio.source(inputStream));
        String[] strArr = JsonReader.REPLACEMENT_CHARS;
        return fromJsonReaderSyncInternal(new JsonUtf8Reader(realBufferedSource), str, true);
    }

    public static LottieResult fromJsonReaderSyncInternal(JsonUtf8Reader jsonUtf8Reader, String str, boolean z) {
        LottieComposition lottieComposition;
        try {
            if (str == null) {
                lottieComposition = null;
            } else {
                try {
                    lottieComposition = LottieCompositionCache.INSTANCE.get(str);
                } catch (Exception e) {
                    LottieResult lottieResult = new LottieResult(e);
                    if (z) {
                        Utils.closeQuietly(jsonUtf8Reader);
                    }
                    return lottieResult;
                }
            }
            if (lottieComposition != null) {
                LottieResult lottieResult2 = new LottieResult(lottieComposition);
                if (z) {
                    Utils.closeQuietly(jsonUtf8Reader);
                }
                return lottieResult2;
            }
            LottieComposition parse = LottieCompositionMoshiParser.parse(jsonUtf8Reader);
            if (str != null) {
                LottieCompositionCache.INSTANCE.cache.put(str, parse);
            }
            LottieResult lottieResult3 = new LottieResult(parse);
            if (z) {
                Utils.closeQuietly(jsonUtf8Reader);
            }
            return lottieResult3;
        } catch (Throwable th) {
            if (z) {
                Utils.closeQuietly(jsonUtf8Reader);
            }
            throw th;
        }
    }

    public static LottieTask fromRawRes(Context context, final String str, final int i) {
        final WeakReference weakReference = new WeakReference(context);
        final Context applicationContext = context.getApplicationContext();
        return cache(str, new Callable() { // from class: com.airbnb.lottie.LottieCompositionFactory$$ExternalSyntheticLambda6
            @Override // java.util.concurrent.Callable
            public final Object call() {
                WeakReference weakReference2 = weakReference;
                Context context2 = applicationContext;
                Context context3 = (Context) weakReference2.get();
                if (context3 != null) {
                    context2 = context3;
                }
                return LottieCompositionFactory.fromRawResSync(context2, str, i);
            }
        }, null);
    }

    public static LottieResult fromRawResSync(Context context, String str, int i) {
        LottieComposition lottieComposition = str == null ? null : LottieCompositionCache.INSTANCE.get(str);
        if (lottieComposition != null) {
            return new LottieResult(lottieComposition);
        }
        try {
            RealBufferedSource realBufferedSource = new RealBufferedSource(Okio.source(context.getResources().openRawResource(i)));
            if (matchesMagicBytes(realBufferedSource, ZIP_MAGIC).booleanValue()) {
                return fromZipStreamSync(context, new ZipInputStream(new RealBufferedSource$inputStream$1(realBufferedSource)), str);
            }
            if (!matchesMagicBytes(realBufferedSource, GZIP_MAGIC).booleanValue()) {
                return fromJsonInputStreamSync(new RealBufferedSource$inputStream$1(realBufferedSource), str);
            }
            try {
                return fromJsonInputStreamSync(new GZIPInputStream(new RealBufferedSource$inputStream$1(realBufferedSource)), str);
            } catch (IOException e) {
                return new LottieResult(e);
            }
        } catch (Resources.NotFoundException e2) {
            return new LottieResult(e2);
        }
    }

    public static LottieResult fromZipStreamSync(Context context, ZipInputStream zipInputStream, String str) {
        try {
            return fromZipStreamSyncInternal(context, zipInputStream, str);
        } finally {
            Utils.closeQuietly(zipInputStream);
        }
    }

    public static LottieResult fromZipStreamSyncInternal(Context context, ZipInputStream zipInputStream, String str) {
        LottieComposition lottieComposition;
        LottieImageAsset lottieImageAsset;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        if (str == null) {
            lottieComposition = null;
        } else {
            try {
                lottieComposition = LottieCompositionCache.INSTANCE.get(str);
            } catch (IOException e) {
                return new LottieResult(e);
            }
        }
        if (lottieComposition != null) {
            return new LottieResult(lottieComposition);
        }
        ZipEntry nextEntry = zipInputStream.getNextEntry();
        LottieComposition lottieComposition2 = null;
        while (nextEntry != null) {
            String name = nextEntry.getName();
            if (name.contains("__MACOSX")) {
                zipInputStream.closeEntry();
            } else if (nextEntry.getName().equalsIgnoreCase("manifest.json")) {
                zipInputStream.closeEntry();
            } else if (nextEntry.getName().contains(".json")) {
                RealBufferedSource realBufferedSource = new RealBufferedSource(Okio.source(zipInputStream));
                String[] strArr = JsonReader.REPLACEMENT_CHARS;
                lottieComposition2 = fromJsonReaderSyncInternal(new JsonUtf8Reader(realBufferedSource), null, false).value;
            } else {
                if (!name.contains(".png") && !name.contains(".webp") && !name.contains(".jpg") && !name.contains(".jpeg")) {
                    if (!name.contains(".ttf") && !name.contains(".otf")) {
                        zipInputStream.closeEntry();
                    }
                    String[] split = name.split("/");
                    String str2 = split[split.length - 1];
                    String str3 = str2.split("\\.")[0];
                    File file = new File(context.getCacheDir(), str2);
                    new FileOutputStream(file);
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        try {
                            byte[] bArr = new byte[4096];
                            while (true) {
                                int read = zipInputStream.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, read);
                            }
                            fileOutputStream.flush();
                            fileOutputStream.close();
                        } catch (Throwable th) {
                            try {
                                fileOutputStream.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        Logger.warning("Unable to save font " + str3 + " to the temporary file: " + str2 + ". ", th3);
                    }
                    Typeface createFromFile = Typeface.createFromFile(file);
                    if (!file.delete()) {
                        Logger.warning("Failed to delete temp font file " + file.getAbsolutePath() + ".");
                    }
                    hashMap2.put(str3, createFromFile);
                }
                String[] split2 = name.split("/");
                hashMap.put(split2[split2.length - 1], BitmapFactory.decodeStream(zipInputStream));
            }
            nextEntry = zipInputStream.getNextEntry();
        }
        if (lottieComposition2 == null) {
            return new LottieResult(new IllegalArgumentException("Unable to parse composition"));
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            String str4 = (String) entry.getKey();
            Iterator it = lottieComposition2.getImages().values().iterator();
            while (true) {
                if (!it.hasNext()) {
                    lottieImageAsset = null;
                    break;
                }
                lottieImageAsset = (LottieImageAsset) it.next();
                if (lottieImageAsset.fileName.equals(str4)) {
                    break;
                }
            }
            if (lottieImageAsset != null) {
                lottieImageAsset.bitmap = Utils.resizeBitmapIfNeeded((Bitmap) entry.getValue(), lottieImageAsset.width, lottieImageAsset.height);
            }
        }
        for (Map.Entry entry2 : hashMap2.entrySet()) {
            boolean z = false;
            for (Font font : lottieComposition2.fonts.values()) {
                if (font.family.equals(entry2.getKey())) {
                    font.typeface = (Typeface) entry2.getValue();
                    z = true;
                }
            }
            if (!z) {
                Logger.warning("Parsed font for " + ((String) entry2.getKey()) + " however it was not found in the animation.");
            }
        }
        if (hashMap.isEmpty()) {
            Iterator it2 = lottieComposition2.getImages().entrySet().iterator();
            while (it2.hasNext()) {
                LottieImageAsset lottieImageAsset2 = (LottieImageAsset) ((Map.Entry) it2.next()).getValue();
                if (lottieImageAsset2 == null) {
                    return null;
                }
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inScaled = true;
                options.inDensity = 160;
                String str5 = lottieImageAsset2.fileName;
                if (str5.startsWith("data:") && str5.indexOf("base64,") > 0) {
                    try {
                        byte[] decode = Base64.decode(str5.substring(str5.indexOf(44) + 1), 0);
                        lottieImageAsset2.bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length, options);
                    } catch (IllegalArgumentException e2) {
                        Logger.warning("data URL did not have correct base64 format.", e2);
                        return null;
                    }
                }
            }
        }
        if (str != null) {
            LottieCompositionCache.INSTANCE.cache.put(str, lottieComposition2);
        }
        return new LottieResult(lottieComposition2);
    }

    public static Boolean matchesMagicBytes(RealBufferedSource realBufferedSource, byte[] bArr) {
        try {
            RealBufferedSource realBufferedSource2 = new RealBufferedSource(new PeekSource(realBufferedSource));
            for (byte b : bArr) {
                if (realBufferedSource2.readByte() != b) {
                    return Boolean.FALSE;
                }
            }
            realBufferedSource2.close();
            return Boolean.TRUE;
        } catch (Exception unused) {
            Logger.INSTANCE.getClass();
            return Boolean.FALSE;
        } catch (NoSuchMethodError unused2) {
            return Boolean.FALSE;
        }
    }

    public static void notifyTaskCacheIdleListeners() {
        ArrayList arrayList = new ArrayList(taskIdleListeners);
        if (arrayList.size() <= 0) {
            return;
        }
        arrayList.get(0).getClass();
        throw new ClassCastException();
    }

    public static String rawResCacheKey(int i, Context context) {
        StringBuilder sb = new StringBuilder("rawRes");
        sb.append((context.getResources().getConfiguration().uiMode & 48) == 32 ? "_night_" : "_day_");
        sb.append(i);
        return sb.toString();
    }
}
