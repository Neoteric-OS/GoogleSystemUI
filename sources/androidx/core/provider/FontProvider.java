package androidx.core.provider;

import android.content.ContentProviderClient;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.util.Log;
import androidx.collection.LruCache;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.tracing.Trace;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class FontProvider {
    public static final LruCache sProviderCache = new LruCache(2);
    public static final FontProvider$$ExternalSyntheticLambda0 sByteArrayComparator = new FontProvider$$ExternalSyntheticLambda0();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ProviderCacheKey {
        public String mAuthority;
        public List mCertificates;
        public String mPackageName;

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ProviderCacheKey)) {
                return false;
            }
            ProviderCacheKey providerCacheKey = (ProviderCacheKey) obj;
            return Objects.equals(this.mAuthority, providerCacheKey.mAuthority) && Objects.equals(this.mPackageName, providerCacheKey.mPackageName) && Objects.equals(this.mCertificates, providerCacheKey.mCertificates);
        }

        public final int hashCode() {
            return Objects.hash(this.mAuthority, this.mPackageName, this.mCertificates);
        }
    }

    public static FontsContractCompat$FontFamilyResult getFontFamilyResult(Context context, List list) {
        Trace.beginSection("FontProvider.getFontFamilyResult");
        try {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                FontRequest fontRequest = (FontRequest) list.get(i);
                ProviderInfo provider = getProvider(context.getPackageManager(), fontRequest, context.getResources());
                if (provider == null) {
                    FontsContractCompat$FontFamilyResult fontsContractCompat$FontFamilyResult = new FontsContractCompat$FontFamilyResult();
                    android.os.Trace.endSection();
                    return fontsContractCompat$FontFamilyResult;
                }
                arrayList.add(query(context, fontRequest, provider.authority));
            }
            FontsContractCompat$FontFamilyResult fontsContractCompat$FontFamilyResult2 = new FontsContractCompat$FontFamilyResult(arrayList);
            android.os.Trace.endSection();
            return fontsContractCompat$FontFamilyResult2;
        } catch (Throwable th) {
            android.os.Trace.endSection();
            throw th;
        }
    }

    public static ProviderInfo getProvider(PackageManager packageManager, FontRequest fontRequest, Resources resources) {
        FontProvider$$ExternalSyntheticLambda0 fontProvider$$ExternalSyntheticLambda0 = sByteArrayComparator;
        Trace.beginSection("FontProvider.getProvider");
        try {
            List list = fontRequest.mCertificates;
            String str = fontRequest.mProviderAuthority;
            String str2 = fontRequest.mProviderPackage;
            if (list == null) {
                list = FontResourcesParserCompat.readCerts(0, resources);
            }
            ProviderCacheKey providerCacheKey = new ProviderCacheKey();
            providerCacheKey.mAuthority = str;
            providerCacheKey.mPackageName = str2;
            providerCacheKey.mCertificates = list;
            LruCache lruCache = sProviderCache;
            ProviderInfo providerInfo = (ProviderInfo) lruCache.get(providerCacheKey);
            if (providerInfo != null) {
                return providerInfo;
            }
            ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(str, 0);
            if (resolveContentProvider == null) {
                throw new PackageManager.NameNotFoundException("No package found for authority: " + str);
            }
            if (!resolveContentProvider.packageName.equals(str2)) {
                throw new PackageManager.NameNotFoundException("Found content provider " + str + ", but package was not " + str2);
            }
            Signature[] signatureArr = packageManager.getPackageInfo(resolveContentProvider.packageName, 64).signatures;
            ArrayList arrayList = new ArrayList();
            for (Signature signature : signatureArr) {
                arrayList.add(signature.toByteArray());
            }
            Collections.sort(arrayList, fontProvider$$ExternalSyntheticLambda0);
            for (int i = 0; i < list.size(); i++) {
                ArrayList arrayList2 = new ArrayList((Collection) list.get(i));
                Collections.sort(arrayList2, fontProvider$$ExternalSyntheticLambda0);
                if (arrayList.size() == arrayList2.size()) {
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        if (!Arrays.equals((byte[]) arrayList.get(i2), (byte[]) arrayList2.get(i2))) {
                            break;
                        }
                    }
                    lruCache.put(providerCacheKey, resolveContentProvider);
                    return resolveContentProvider;
                }
            }
            android.os.Trace.endSection();
            return null;
        } finally {
            android.os.Trace.endSection();
        }
    }

    public static FontsContractCompat$FontInfo[] query(Context context, FontRequest fontRequest, String str) {
        Trace.beginSection("FontProvider.query");
        try {
            ArrayList arrayList = new ArrayList();
            Uri build = new Uri.Builder().scheme("content").authority(str).build();
            Uri build2 = new Uri.Builder().scheme("content").authority(str).appendPath("file").build();
            ContentProviderClient acquireUnstableContentProviderClient = context.getContentResolver().acquireUnstableContentProviderClient(build);
            Cursor cursor = null;
            try {
                String[] strArr = {"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"};
                Trace.beginSection("ContentQueryWrapper.query");
                try {
                    String[] strArr2 = {fontRequest.mQuery};
                    if (acquireUnstableContentProviderClient != null) {
                        try {
                            cursor = acquireUnstableContentProviderClient.query(build, strArr, "query = ?", strArr2, null, null);
                        } catch (RemoteException e) {
                            Log.w("FontsProvider", "Unable to query the content provider", e);
                        }
                    }
                    android.os.Trace.endSection();
                    if (cursor != null && cursor.getCount() > 0) {
                        int columnIndex = cursor.getColumnIndex("result_code");
                        ArrayList arrayList2 = new ArrayList();
                        int columnIndex2 = cursor.getColumnIndex("_id");
                        int columnIndex3 = cursor.getColumnIndex("file_id");
                        int columnIndex4 = cursor.getColumnIndex("font_ttc_index");
                        int columnIndex5 = cursor.getColumnIndex("font_weight");
                        int columnIndex6 = cursor.getColumnIndex("font_italic");
                        while (cursor.moveToNext()) {
                            int i = columnIndex != -1 ? cursor.getInt(columnIndex) : 0;
                            arrayList2.add(new FontsContractCompat$FontInfo(columnIndex3 == -1 ? ContentUris.withAppendedId(build, cursor.getLong(columnIndex2)) : ContentUris.withAppendedId(build2, cursor.getLong(columnIndex3)), columnIndex4 != -1 ? cursor.getInt(columnIndex4) : 0, columnIndex5 != -1 ? cursor.getInt(columnIndex5) : 400, columnIndex6 != -1 && cursor.getInt(columnIndex6) == 1, i));
                        }
                        arrayList = arrayList2;
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (acquireUnstableContentProviderClient != null) {
                        acquireUnstableContentProviderClient.close();
                    }
                    return (FontsContractCompat$FontInfo[]) arrayList.toArray(new FontsContractCompat$FontInfo[0]);
                } finally {
                    android.os.Trace.endSection();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                if (acquireUnstableContentProviderClient != null) {
                    acquireUnstableContentProviderClient.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }
}
