package com.android.systemui.controls.management;

import android.content.ComponentName;
import android.content.Context;
import android.util.IndentingPrintWriter;
import android.util.Log;
import com.android.settingslib.applications.ServiceListing;
import com.android.systemui.Dumpable;
import com.android.systemui.controls.ControlsServiceInfo;
import com.android.systemui.controls.management.ControlsListingController;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.util.ActivityTaskManagerProxy;
import com.android.systemui.util.DumpUtilsKt;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsListingControllerImpl implements ControlsListingController, Dumpable {
    public final ActivityTaskManagerProxy activityTaskManagerProxy;
    public List availableServices;
    public final Executor backgroundExecutor;
    public final CopyOnWriteArraySet callbacks;
    public final Context context;
    public int currentUserId;
    public ServiceListing serviceListing;
    public final Function1 serviceListingBuilder;
    public final ControlsListingControllerImpl$serviceListingCallback$1 serviceListingCallback;
    public final AtomicInteger userChangeInProgress;
    public final UserTracker userTracker;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.controls.management.ControlsListingControllerImpl$1, reason: invalid class name */
    final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1 {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1, ControlsListingControllerImplKt.class, "createServiceListing", "createServiceListing(Landroid/content/Context;)Lcom/android/settingslib/applications/ServiceListing;", 1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return new ServiceListing((Context) obj);
        }
    }

    public ControlsListingControllerImpl(Context context, Executor executor, Function1 function1, UserTracker userTracker, ActivityTaskManagerProxy activityTaskManagerProxy, DumpManager dumpManager, FeatureFlags featureFlags) {
        this.context = context;
        this.backgroundExecutor = executor;
        this.serviceListingBuilder = function1;
        this.userTracker = userTracker;
        this.activityTaskManagerProxy = activityTaskManagerProxy;
        this.serviceListing = (ServiceListing) function1.invoke(context);
        this.callbacks = new CopyOnWriteArraySet();
        this.availableServices = EmptyList.INSTANCE;
        this.userChangeInProgress = new AtomicInteger(0);
        this.currentUserId = ((UserTrackerImpl) userTracker).getUserId();
        ControlsListingControllerImpl$serviceListingCallback$1 controlsListingControllerImpl$serviceListingCallback$1 = new ControlsListingControllerImpl$serviceListingCallback$1(this);
        this.serviceListingCallback = controlsListingControllerImpl$serviceListingCallback$1;
        Log.d("ControlsListingControllerImpl", "Initializing");
        DumpManager.registerDumpable$default(dumpManager, "ControlsListingControllerImpl", this);
        this.serviceListing.mCallbacks.add(controlsListingControllerImpl$serviceListingCallback$1);
        this.serviceListing.setListening(true);
        this.serviceListing.reload();
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("ControlsListingController:");
        IndentingPrintWriter asIndenting = DumpUtilsKt.asIndenting(printWriter);
        asIndenting.increaseIndent();
        asIndenting.println("Callbacks: " + this.callbacks);
        asIndenting.println("Services: " + getCurrentServices());
        asIndenting.decreaseIndent();
    }

    public final CharSequence getAppLabel(ComponentName componentName) {
        Object obj;
        Iterator it = this.availableServices.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((ControlsServiceInfo) obj).componentName, componentName)) {
                break;
            }
        }
        ControlsServiceInfo controlsServiceInfo = (ControlsServiceInfo) obj;
        if (controlsServiceInfo != null) {
            return controlsServiceInfo.loadLabel();
        }
        return null;
    }

    public final List getCurrentServices() {
        List<ControlsServiceInfo> list = this.availableServices;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        for (ControlsServiceInfo controlsServiceInfo : list) {
            ControlsServiceInfo controlsServiceInfo2 = new ControlsServiceInfo(controlsServiceInfo.context, controlsServiceInfo.serviceInfo);
            controlsServiceInfo2.panelActivity = controlsServiceInfo.panelActivity;
            arrayList.add(controlsServiceInfo2);
        }
        return arrayList;
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        this.backgroundExecutor.execute(new ControlsListingControllerImpl$addCallback$1(this, (ControlsListingController.ControlsListingCallback) obj, 1));
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0079, code lost:
    
        if ((r7 != 0 ? r7 == 1 : r5.enabled) != false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x007e, code lost:
    
        if (r6 != false) goto L33;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateServices(java.util.List r10) {
        /*
            r9 = this;
            android.content.Context r0 = r9.context
            com.android.systemui.util.ActivityTaskManagerProxy r1 = r9.activityTaskManagerProxy
            r1.getClass()
            boolean r0 = android.app.ActivityTaskManager.supportsMultiWindow(r0)
            if (r0 == 0) goto L86
            java.util.Iterator r0 = r10.iterator()
        L11:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L86
            java.lang.Object r1 = r0.next()
            com.android.systemui.controls.ControlsServiceInfo r1 = (com.android.systemui.controls.ControlsServiceInfo) r1
            boolean r2 = r1.resolved
            if (r2 == 0) goto L22
            goto L11
        L22:
            r2 = 1
            r1.resolved = r2
            android.content.ComponentName r3 = r1._panelActivity
            r4 = 0
            if (r3 == 0) goto L83
            android.content.pm.PackageManager r5 = r1.mPm
            android.content.Intent r6 = new android.content.Intent
            r6.<init>()
            android.content.Intent r6 = r6.setComponent(r3)
            r7 = 786432(0xc0000, double:3.88549E-318)
            android.content.pm.PackageManager$ResolveInfoFlags r7 = android.content.pm.PackageManager.ResolveInfoFlags.of(r7)
            int r8 = r1.userId
            android.os.UserHandle r8 = android.os.UserHandle.of(r8)
            java.util.List r5 = r5.queryIntentActivitiesAsUser(r6, r7, r8)
            boolean r6 = r5.isEmpty()
            if (r6 != 0) goto L81
            r6 = 0
            java.lang.Object r5 = r5.get(r6)
            android.content.pm.ResolveInfo r5 = (android.content.pm.ResolveInfo) r5
            android.content.pm.ActivityInfo r5 = r5.activityInfo
            if (r5 == 0) goto L7e
            java.lang.String r7 = r5.permission
            java.lang.String r8 = "android.permission.BIND_CONTROLS"
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r8)
            if (r7 == 0) goto L7c
            boolean r7 = r5.exported
            if (r7 == 0) goto L7c
            android.content.pm.PackageManager r7 = r1.mPm
            android.content.ComponentName r8 = r5.getComponentName()
            int r7 = r7.getComponentEnabledSetting(r8)
            if (r7 == 0) goto L77
            if (r7 == r2) goto L75
            r5 = r6
            goto L79
        L75:
            r5 = r2
            goto L79
        L77:
            boolean r5 = r5.enabled
        L79:
            if (r5 == 0) goto L7c
            goto L7d
        L7c:
            r2 = r6
        L7d:
            r6 = r2
        L7e:
            if (r6 == 0) goto L81
            goto L82
        L81:
            r3 = r4
        L82:
            r4 = r3
        L83:
            r1.panelActivity = r4
            goto L11
        L86:
            java.util.List r0 = r9.availableServices
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto Laa
            r9.availableServices = r10
            java.util.concurrent.CopyOnWriteArraySet r10 = r9.callbacks
            java.util.Iterator r10 = r10.iterator()
        L96:
            boolean r0 = r10.hasNext()
            if (r0 == 0) goto Laa
            java.lang.Object r0 = r10.next()
            com.android.systemui.controls.management.ControlsListingController$ControlsListingCallback r0 = (com.android.systemui.controls.management.ControlsListingController.ControlsListingCallback) r0
            java.util.List r1 = r9.getCurrentServices()
            r0.onServicesUpdated(r1)
            goto L96
        Laa:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.controls.management.ControlsListingControllerImpl.updateServices(java.util.List):void");
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(ControlsListingController.ControlsListingCallback controlsListingCallback) {
        this.backgroundExecutor.execute(new ControlsListingControllerImpl$addCallback$1(this, controlsListingCallback, 0));
    }

    public ControlsListingControllerImpl(Context context, Executor executor, UserTracker userTracker, ActivityTaskManagerProxy activityTaskManagerProxy, DumpManager dumpManager, FeatureFlags featureFlags) {
        this(context, executor, AnonymousClass1.INSTANCE, userTracker, activityTaskManagerProxy, dumpManager, featureFlags);
    }
}
