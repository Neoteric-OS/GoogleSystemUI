package androidx.slice.compat;

import android.app.PendingIntent;
import android.app.slice.Slice;
import android.app.slice.SliceManager;
import android.app.slice.SliceProvider;
import android.app.slice.SliceSpec;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import androidx.collection.ArraySet;
import androidx.slice.SliceConvert;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SliceProviderWrapperContainer$SliceProviderWrapper extends SliceProvider {
    public String[] mAutoGrantPermissions;
    public SliceManager mSliceManager;
    public androidx.slice.SliceProvider mSliceProvider;

    @Override // android.app.slice.SliceProvider, android.content.ContentProvider
    public final void attachInfo(Context context, ProviderInfo providerInfo) {
        this.mSliceProvider.attachInfo(context, providerInfo);
        super.attachInfo(context, providerInfo);
        this.mSliceManager = (SliceManager) context.getSystemService(SliceManager.class);
    }

    @Override // android.app.slice.SliceProvider, android.content.ContentProvider
    public final Bundle call(String str, String str2, Bundle bundle) {
        Intent intent;
        Uri uri;
        if (this.mAutoGrantPermissions != null) {
            if ("bind_slice".equals(str)) {
                if (bundle != null) {
                    uri = (Uri) bundle.getParcelable("slice_uri");
                    if (uri != null && this.mSliceManager.checkSlicePermission(uri, Binder.getCallingPid(), Binder.getCallingUid()) != 0) {
                        checkPermissions(uri);
                    }
                }
            } else if ("map_slice".equals(str) && (intent = (Intent) bundle.getParcelable("slice_intent")) != null) {
                onMapIntentToUri(intent);
                throw null;
            }
            uri = null;
            if (uri != null) {
                checkPermissions(uri);
            }
        }
        if (!"androidx.remotecallback.method.PROVIDER_CALLBACK".equals(str)) {
            return super.call(str, str2, bundle);
        }
        this.mSliceProvider.getClass();
        return null;
    }

    public final void checkPermissions(Uri uri) {
        if (uri != null) {
            for (String str : this.mAutoGrantPermissions) {
                if (getContext().checkCallingPermission(str) == 0) {
                    this.mSliceManager.grantSlicePermission(str, uri);
                    getContext().getContentResolver().notifyChange(uri, null);
                    return;
                }
            }
        }
    }

    @Override // android.app.slice.SliceProvider
    public final Slice onBindSlice(Uri uri, Set set) {
        ArraySet arraySet = new ArraySet(0);
        if (set != null) {
            Iterator it = set.iterator();
            while (it.hasNext()) {
                SliceSpec sliceSpec = (SliceSpec) it.next();
                arraySet.add(sliceSpec == null ? null : new androidx.slice.SliceSpec(sliceSpec.getType(), sliceSpec.getRevision()));
            }
        }
        androidx.slice.SliceProvider.sSpecs = arraySet;
        try {
            return SliceConvert.unwrap(this.mSliceProvider.onBindSlice());
        } catch (Exception e) {
            Log.wtf("SliceProviderWrapper", "Slice with URI " + uri.toString() + " is invalid.", e);
            return null;
        } finally {
            androidx.slice.SliceProvider.sSpecs = null;
        }
    }

    @Override // android.content.ContentProvider
    public final boolean onCreate() {
        return true;
    }

    @Override // android.app.slice.SliceProvider
    public final PendingIntent onCreatePermissionRequest(Uri uri) {
        if (this.mAutoGrantPermissions != null) {
            checkPermissions(uri);
        }
        androidx.slice.SliceProvider sliceProvider = this.mSliceProvider;
        getCallingPackage();
        sliceProvider.getClass();
        return super.onCreatePermissionRequest(uri);
    }

    @Override // android.app.slice.SliceProvider
    public final Collection onGetSliceDescendants(Uri uri) {
        this.mSliceProvider.getClass();
        return Collections.emptyList();
    }

    @Override // android.app.slice.SliceProvider
    public final Uri onMapIntentToUri(Intent intent) {
        this.mSliceProvider.getClass();
        throw new UnsupportedOperationException("This provider has not implemented intent to uri mapping");
    }

    @Override // android.app.slice.SliceProvider
    public final void onSlicePinned(Uri uri) {
        this.mSliceProvider.getClass();
        List pinnedSlices = this.mSliceProvider.getPinnedSlices();
        if (pinnedSlices.contains(uri)) {
            return;
        }
        pinnedSlices.add(uri);
    }

    @Override // android.app.slice.SliceProvider
    public final void onSliceUnpinned(Uri uri) {
        this.mSliceProvider.getClass();
        List pinnedSlices = this.mSliceProvider.getPinnedSlices();
        if (pinnedSlices.contains(uri)) {
            pinnedSlices.remove(uri);
        }
    }
}
