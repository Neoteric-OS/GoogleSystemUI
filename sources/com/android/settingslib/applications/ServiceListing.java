package com.android.settingslib.applications;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.util.Slog;
import com.android.systemui.controls.ControlsServiceInfo;
import com.android.systemui.controls.management.ControlsListingControllerImpl;
import com.android.systemui.controls.management.ControlsListingControllerImpl$changeUser$1;
import com.android.systemui.controls.management.ControlsListingControllerImpl$serviceListingCallback$1;
import com.android.systemui.settings.UserTrackerImpl;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ServiceListing {
    public final ContentResolver mContentResolver;
    public final Context mContext;
    public boolean mListening;
    public final HashSet mEnabledServices = new HashSet();
    public final List mServices = new ArrayList();
    public final List mCallbacks = new ArrayList();
    public final AnonymousClass1 mSettingsObserver = new ContentObserver(new Handler()) { // from class: com.android.settingslib.applications.ServiceListing.1
        @Override // android.database.ContentObserver
        public final void onChange(boolean z, Uri uri) {
            ServiceListing.this.reload();
        }
    };
    public final AnonymousClass2 mPackageReceiver = new BroadcastReceiver() { // from class: com.android.settingslib.applications.ServiceListing.2
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            ServiceListing.this.reload();
        }
    };
    public final String mTag = "controls_providers";
    public final String mSetting = "controls_providers";
    public final String mIntentAction = "android.service.controls.ControlsProviderService";
    public final String mPermission = "android.permission.BIND_CONTROLS";
    public final String mNoun = "Controls Provider";
    public final boolean mAddDeviceLockedFlags = true;

    /* JADX WARN: Type inference failed for: r0v3, types: [com.android.settingslib.applications.ServiceListing$1] */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.android.settingslib.applications.ServiceListing$2] */
    public ServiceListing(Context context) {
        this.mContentResolver = context.getContentResolver();
        this.mContext = context;
    }

    public final void reload() {
        ControlsListingControllerImpl controlsListingControllerImpl;
        this.mEnabledServices.clear();
        String string = Settings.Secure.getString(this.mContentResolver, this.mSetting);
        if (string != null && !"".equals(string)) {
            for (String str : string.split(":")) {
                ComponentName unflattenFromString = ComponentName.unflattenFromString(str);
                if (unflattenFromString != null) {
                    this.mEnabledServices.add(unflattenFromString);
                }
            }
        }
        this.mServices.clear();
        int currentUser = ActivityManager.getCurrentUser();
        int i = this.mAddDeviceLockedFlags ? 786564 : 132;
        PackageManager packageManager = this.mContext.getPackageManager();
        Iterator it = packageManager.queryIntentServicesAsUser(new Intent(this.mIntentAction), i, currentUser).iterator();
        while (it.hasNext()) {
            ServiceInfo serviceInfo = ((ResolveInfo) it.next()).serviceInfo;
            if (!this.mEnabledServices.contains(serviceInfo.getComponentName())) {
                String str2 = serviceInfo.permission;
                String str3 = this.mPermission;
                if (str3.equals(str2)) {
                    this.mServices.add(serviceInfo);
                } else {
                    Slog.w(this.mTag, "Skipping " + this.mNoun + " service " + serviceInfo.packageName + "/" + serviceInfo.name + ": it does not require the permission " + str3);
                }
            }
        }
        Iterator it2 = this.mEnabledServices.iterator();
        while (it2.hasNext()) {
            Iterator it3 = packageManager.queryIntentServicesAsUser(new Intent().setComponent((ComponentName) it2.next()), i, currentUser).iterator();
            while (it3.hasNext()) {
                this.mServices.add(((ResolveInfo) it3.next()).serviceInfo);
            }
        }
        for (ControlsListingControllerImpl$serviceListingCallback$1 controlsListingControllerImpl$serviceListingCallback$1 : this.mCallbacks) {
            List list = this.mServices;
            controlsListingControllerImpl$serviceListingCallback$1.getClass();
            ArrayList arrayList = (ArrayList) list;
            Log.d("ControlsListingControllerImpl", "ServiceConfig reloaded, count: " + arrayList.size());
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
            Iterator it4 = arrayList.iterator();
            while (true) {
                boolean hasNext = it4.hasNext();
                controlsListingControllerImpl = controlsListingControllerImpl$serviceListingCallback$1.this$0;
                if (hasNext) {
                    ServiceInfo serviceInfo2 = (ServiceInfo) it4.next();
                    Context userContext = ((UserTrackerImpl) controlsListingControllerImpl.userTracker).getUserContext();
                    Intrinsics.checkNotNull(serviceInfo2);
                    arrayList2.add(new ControlsServiceInfo(userContext, serviceInfo2));
                }
            }
            controlsListingControllerImpl.backgroundExecutor.execute(new ControlsListingControllerImpl$changeUser$1(controlsListingControllerImpl, arrayList2, 1));
        }
    }

    public final void setListening(boolean z) {
        if (this.mListening == z) {
            return;
        }
        this.mListening = z;
        AnonymousClass1 anonymousClass1 = this.mSettingsObserver;
        AnonymousClass2 anonymousClass2 = this.mPackageReceiver;
        if (!z) {
            this.mContext.unregisterReceiver(anonymousClass2);
            this.mContentResolver.unregisterContentObserver(anonymousClass1);
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addDataScheme("package");
        this.mContext.registerReceiver(anonymousClass2, intentFilter);
        this.mContentResolver.registerContentObserver(Settings.Secure.getUriFor(this.mSetting), false, anonymousClass1);
    }
}
