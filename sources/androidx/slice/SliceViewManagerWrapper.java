package androidx.slice;

import android.app.slice.SliceManager;
import android.content.ContentProviderClient;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import androidx.collection.ArrayMap;
import androidx.collection.ArraySet;
import androidx.collection.ArraySet.ElementIterator;
import androidx.slice.SliceViewManager;
import androidx.slice.widget.SliceLiveData;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SliceViewManagerWrapper extends SliceViewManager {
    public final ArrayMap mCachedAuthorities;
    public final ArrayMap mCachedSuspendFlags;
    public final Context mContext;
    public final android.util.ArrayMap mListenerLookup;
    public final SliceManager mManager;
    public final ArraySet mSpecs;

    public SliceViewManagerWrapper(Context context) {
        SliceManager sliceManager = (SliceManager) context.getSystemService(SliceManager.class);
        this.mListenerLookup = new android.util.ArrayMap();
        this.mContext = context;
        this.mCachedSuspendFlags = new ArrayMap(0);
        this.mCachedAuthorities = new ArrayMap(0);
        this.mManager = sliceManager;
        ArraySet arraySet = SliceLiveData.SUPPORTED_SPECS;
        ArraySet arraySet2 = new ArraySet(0);
        if (arraySet != null) {
            ArraySet.ElementIterator elementIterator = arraySet.new ElementIterator();
            while (elementIterator.hasNext()) {
                SliceSpec sliceSpec = (SliceSpec) elementIterator.next();
                arraySet2.add(sliceSpec == null ? null : new android.app.slice.SliceSpec(sliceSpec.mType, sliceSpec.mRevision));
            }
        }
        this.mSpecs = arraySet2;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0052 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0054  */
    @Override // androidx.slice.SliceViewManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.slice.Slice bindSlice(android.net.Uri r6) {
        /*
            r5 = this;
            java.lang.String r0 = r6.getAuthority()
            androidx.collection.ArrayMap r1 = r5.mCachedAuthorities
            java.lang.Object r2 = r1.get(r0)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 != 0) goto L21
            android.content.Context r2 = r5.mContext
            android.content.pm.PackageManager r2 = r2.getPackageManager()
            r3 = 0
            android.content.pm.ProviderInfo r2 = r2.resolveContentProvider(r0, r3)
            if (r2 != 0) goto L1c
            goto L50
        L1c:
            java.lang.String r2 = r2.packageName
            r1.put(r0, r2)
        L21:
            androidx.collection.ArrayMap r0 = r5.mCachedSuspendFlags
            java.lang.Object r1 = r0.get(r2)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            if (r1 != 0) goto L4b
            r1 = 0
            android.content.Context r3 = r5.mContext     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L49
            android.content.pm.PackageManager r3 = r3.getPackageManager()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L49
            android.content.pm.ApplicationInfo r3 = r3.getApplicationInfo(r2, r1)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L49
            int r3 = r3.flags     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L49
            r4 = 1073741824(0x40000000, float:2.0)
            r3 = r3 & r4
            if (r3 == 0) goto L3f
            r3 = 1
            goto L40
        L3f:
            r3 = r1
        L40:
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L49
            r0.put(r2, r3)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L49
            r1 = r3
            goto L4b
        L49:
            r3 = r1
            goto L50
        L4b:
            boolean r0 = r1.booleanValue()
            r3 = r0
        L50:
            if (r3 == 0) goto L54
            r5 = 0
            return r5
        L54:
            android.app.slice.SliceManager r0 = r5.mManager
            androidx.collection.ArraySet r1 = r5.mSpecs
            android.app.slice.Slice r6 = r0.bindSlice(r6, r1)
            android.content.Context r5 = r5.mContext
            androidx.slice.Slice r5 = androidx.slice.SliceConvert.wrap(r6, r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.slice.SliceViewManagerWrapper.bindSlice(android.net.Uri):androidx.slice.Slice");
    }

    @Override // androidx.slice.SliceViewManager
    public final void pinSlice(Uri uri) {
        try {
            this.mManager.pinSlice(uri, this.mSpecs);
        } catch (RuntimeException e) {
            ContentProviderClient acquireContentProviderClient = this.mContext.getContentResolver().acquireContentProviderClient(uri);
            if (acquireContentProviderClient != null) {
                acquireContentProviderClient.release();
                throw e;
            }
            throw new IllegalArgumentException("No provider found for " + uri);
        }
    }

    @Override // androidx.slice.SliceViewManager
    public final void registerSliceCallback(Uri uri, SliceViewManager.SliceCallback sliceCallback) {
        SliceViewManagerBase$SliceListenerImpl sliceViewManagerBase$SliceListenerImpl = new SliceViewManagerBase$SliceListenerImpl(this, uri, new SliceViewManagerBase$1(new Handler(Looper.getMainLooper())), sliceCallback);
        Pair pair = new Pair(uri, sliceCallback);
        synchronized (this.mListenerLookup) {
            SliceViewManagerBase$SliceListenerImpl sliceViewManagerBase$SliceListenerImpl2 = (SliceViewManagerBase$SliceListenerImpl) this.mListenerLookup.put(pair, sliceViewManagerBase$SliceListenerImpl);
            if (sliceViewManagerBase$SliceListenerImpl2 != null) {
                SliceViewManagerWrapper sliceViewManagerWrapper = sliceViewManagerBase$SliceListenerImpl2.this$0;
                sliceViewManagerWrapper.mContext.getContentResolver().unregisterContentObserver(sliceViewManagerBase$SliceListenerImpl2.mObserver);
                if (sliceViewManagerBase$SliceListenerImpl2.mPinned) {
                    sliceViewManagerWrapper.unpinSlice(sliceViewManagerBase$SliceListenerImpl2.mUri);
                    sliceViewManagerBase$SliceListenerImpl2.mPinned = false;
                }
            }
        }
        ContentProviderClient acquireContentProviderClient = this.mContext.getContentResolver().acquireContentProviderClient(sliceViewManagerBase$SliceListenerImpl.mUri);
        if (acquireContentProviderClient != null) {
            acquireContentProviderClient.release();
            this.mContext.getContentResolver().registerContentObserver(sliceViewManagerBase$SliceListenerImpl.mUri, true, sliceViewManagerBase$SliceListenerImpl.mObserver);
            if (sliceViewManagerBase$SliceListenerImpl.mPinned) {
                return;
            }
            try {
                pinSlice(sliceViewManagerBase$SliceListenerImpl.mUri);
                sliceViewManagerBase$SliceListenerImpl.mPinned = true;
            } catch (SecurityException unused) {
            }
        }
    }

    @Override // androidx.slice.SliceViewManager
    public final void unpinSlice(Uri uri) {
        try {
            this.mManager.unpinSlice(uri);
        } catch (IllegalStateException unused) {
        }
    }

    @Override // androidx.slice.SliceViewManager
    public final void unregisterSliceCallback(Uri uri, SliceViewManager.SliceCallback sliceCallback) {
        synchronized (this.mListenerLookup) {
            SliceViewManagerBase$SliceListenerImpl sliceViewManagerBase$SliceListenerImpl = (SliceViewManagerBase$SliceListenerImpl) this.mListenerLookup.remove(new Pair(uri, sliceCallback));
            if (sliceViewManagerBase$SliceListenerImpl != null) {
                SliceViewManagerWrapper sliceViewManagerWrapper = sliceViewManagerBase$SliceListenerImpl.this$0;
                sliceViewManagerWrapper.mContext.getContentResolver().unregisterContentObserver(sliceViewManagerBase$SliceListenerImpl.mObserver);
                if (sliceViewManagerBase$SliceListenerImpl.mPinned) {
                    sliceViewManagerWrapper.unpinSlice(sliceViewManagerBase$SliceListenerImpl.mUri);
                    sliceViewManagerBase$SliceListenerImpl.mPinned = false;
                }
            }
        }
    }
}
