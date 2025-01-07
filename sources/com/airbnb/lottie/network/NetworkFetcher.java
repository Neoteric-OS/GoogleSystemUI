package com.airbnb.lottie.network;

import android.content.Context;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieResult;
import com.airbnb.lottie.utils.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipInputStream;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NetworkFetcher {
    public final DefaultLottieNetworkFetcher fetcher;
    public final NetworkCache networkCache;

    public NetworkFetcher(NetworkCache networkCache, DefaultLottieNetworkFetcher defaultLottieNetworkFetcher) {
        this.networkCache = networkCache;
    }

    public final LottieResult fromInputStream(Context context, String str, InputStream inputStream, String str2, String str3) {
        LottieResult fromZipStreamSync;
        FileExtension fileExtension;
        if (str2 == null) {
            str2 = "application/json";
        }
        boolean contains = str2.contains("application/zip");
        NetworkCache networkCache = this.networkCache;
        if (contains || str2.contains("application/x-zip") || str2.contains("application/x-zip-compressed") || str.split("\\?")[0].endsWith(".lottie")) {
            Logger.debug();
            FileExtension fileExtension2 = FileExtension.ZIP;
            fromZipStreamSync = str3 != null ? LottieCompositionFactory.fromZipStreamSync(context, new ZipInputStream(new FileInputStream(networkCache.writeTempCacheFile(str, inputStream, fileExtension2))), str) : LottieCompositionFactory.fromZipStreamSync(context, new ZipInputStream(inputStream), null);
            fileExtension = fileExtension2;
        } else if (str2.contains("application/gzip") || str2.contains("application/x-gzip") || str.split("\\?")[0].endsWith(".tgs")) {
            Logger.debug();
            fileExtension = FileExtension.GZIP;
            fromZipStreamSync = str3 != null ? LottieCompositionFactory.fromJsonInputStreamSync(new GZIPInputStream(new FileInputStream(networkCache.writeTempCacheFile(str, inputStream, fileExtension))), str) : LottieCompositionFactory.fromJsonInputStreamSync(new GZIPInputStream(inputStream), null);
        } else {
            Logger.debug();
            fileExtension = FileExtension.JSON;
            fromZipStreamSync = str3 != null ? LottieCompositionFactory.fromJsonInputStreamSync(new FileInputStream(networkCache.writeTempCacheFile(str, inputStream, fileExtension).getAbsolutePath()), str) : LottieCompositionFactory.fromJsonInputStreamSync(inputStream, null);
        }
        if (str3 != null && fromZipStreamSync.value != null) {
            File file = new File(networkCache.parentDir(), NetworkCache.filenameForUrl(str, fileExtension, true));
            File file2 = new File(file.getAbsolutePath().replace(".temp", ""));
            boolean renameTo = file.renameTo(file2);
            file2.toString();
            Logger.debug();
            if (!renameTo) {
                Logger.warning("Unable to rename cache file " + file.getAbsolutePath() + " to " + file2.getAbsolutePath() + ".");
            }
        }
        return fromZipStreamSync;
    }
}
