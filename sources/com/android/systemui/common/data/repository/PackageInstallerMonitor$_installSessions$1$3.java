package com.android.systemui.common.data.repository;

import android.content.pm.PackageInstaller;
import android.text.TextUtils;
import com.android.systemui.common.data.repository.PackageInstallerMonitor;
import com.android.systemui.common.shared.model.PackageInstallSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PackageInstallerMonitor$_installSessions$1$3 extends SuspendLambda implements Function2 {
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ PackageInstallerMonitor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PackageInstallerMonitor$_installSessions$1$3(PackageInstallerMonitor packageInstallerMonitor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = packageInstallerMonitor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        PackageInstallerMonitor$_installSessions$1$3 packageInstallerMonitor$_installSessions$1$3 = new PackageInstallerMonitor$_installSessions$1$3(this.this$0, continuation);
        packageInstallerMonitor$_installSessions$1$3.Z$0 = ((Boolean) obj).booleanValue();
        return packageInstallerMonitor$_installSessions$1$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        PackageInstallerMonitor$_installSessions$1$3 packageInstallerMonitor$_installSessions$1$3 = (PackageInstallerMonitor$_installSessions$1$3) create(bool, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        packageInstallerMonitor$_installSessions$1$3.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        if (this.Z$0) {
            PackageInstallerMonitor packageInstallerMonitor = this.this$0;
            synchronized (packageInstallerMonitor.sessions) {
                try {
                    Map map = packageInstallerMonitor.sessions;
                    List<PackageInstaller.SessionInfo> allSessions = packageInstallerMonitor.packageInstaller.getAllSessions();
                    ArrayList<PackageInstaller.SessionInfo> arrayList = new ArrayList();
                    for (Object obj2 : allSessions) {
                        if (!TextUtils.isEmpty(((PackageInstaller.SessionInfo) obj2).appPackageName)) {
                            arrayList.add(obj2);
                        }
                    }
                    ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
                    for (PackageInstaller.SessionInfo sessionInfo : arrayList) {
                        PackageInstallerMonitor.Companion companion = PackageInstallerMonitor.Companion;
                        Intrinsics.checkNotNull(sessionInfo);
                        arrayList2.add(new PackageInstallSession(sessionInfo.sessionId, sessionInfo.appPackageName, sessionInfo.getAppIcon(), sessionInfo.getUser()));
                    }
                    int mapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList2, 10));
                    if (mapCapacity < 16) {
                        mapCapacity = 16;
                    }
                    LinkedHashMap linkedHashMap = new LinkedHashMap(mapCapacity);
                    for (Object obj3 : arrayList2) {
                        linkedHashMap.put(new Integer(((PackageInstallSession) obj3).sessionId), obj3);
                    }
                    map.putAll(linkedHashMap);
                    packageInstallerMonitor.updateInstallerSessionsFlow();
                } catch (Throwable th) {
                    throw th;
                }
            }
            PackageInstallerMonitor packageInstallerMonitor2 = this.this$0;
            packageInstallerMonitor2.packageInstaller.registerSessionCallback(packageInstallerMonitor2, packageInstallerMonitor2.bgHandler);
        } else {
            PackageInstallerMonitor packageInstallerMonitor3 = this.this$0;
            synchronized (packageInstallerMonitor3.sessions) {
                packageInstallerMonitor3.sessions.clear();
                packageInstallerMonitor3.updateInstallerSessionsFlow();
            }
            PackageInstallerMonitor packageInstallerMonitor4 = this.this$0;
            packageInstallerMonitor4.packageInstaller.unregisterSessionCallback(packageInstallerMonitor4);
        }
        return Unit.INSTANCE;
    }
}
