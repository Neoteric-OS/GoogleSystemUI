package com.android.systemui.user.domain.interactor;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.IntentFilter;
import android.os.UserHandle;
import android.os.UserManager;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.GuestResetOrExitSessionReceiver;
import com.android.systemui.GuestResumeSessionReceiver;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;
import com.android.systemui.user.data.repository.UserRepositoryImpl;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GuestUserInteractor {
    public final Context applicationContext;
    public final CoroutineScope applicationScope;
    public final CoroutineDispatcher backgroundDispatcher;
    public final DevicePolicyManager devicePolicyManager;
    public final DeviceProvisionedController deviceProvisionedController;
    public final boolean isGuestUserAutoCreated;
    public final boolean isGuestUserResetting;
    public final CoroutineDispatcher mainDispatcher;
    public final UserManager manager;
    public final RefreshUsersScheduler refreshUsersScheduler;
    public final UserRepositoryImpl repository;
    public final UiEventLogger uiEventLogger;

    public GuestUserInteractor(Context context, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, UserManager userManager, UserRepositoryImpl userRepositoryImpl, DeviceProvisionedController deviceProvisionedController, DevicePolicyManager devicePolicyManager, RefreshUsersScheduler refreshUsersScheduler, UiEventLogger uiEventLogger, GuestResumeSessionReceiver guestResumeSessionReceiver, GuestResetOrExitSessionReceiver guestResetOrExitSessionReceiver) {
        this.applicationContext = context;
        this.applicationScope = coroutineScope;
        this.mainDispatcher = coroutineDispatcher;
        this.backgroundDispatcher = coroutineDispatcher2;
        this.manager = userManager;
        this.repository = userRepositoryImpl;
        this.deviceProvisionedController = deviceProvisionedController;
        this.devicePolicyManager = devicePolicyManager;
        this.refreshUsersScheduler = refreshUsersScheduler;
        this.uiEventLogger = uiEventLogger;
        this.isGuestUserAutoCreated = userRepositoryImpl.isGuestUserAutoCreated;
        this.isGuestUserResetting = userRepositoryImpl.isGuestUserResetting;
        if (context.getUserId() == 0) {
            ((UserTrackerImpl) guestResumeSessionReceiver.mUserTracker).addCallback(guestResumeSessionReceiver.mUserChangedCallback, guestResumeSessionReceiver.mMainExecutor);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.GUEST_RESET");
            intentFilter.addAction("android.intent.action.GUEST_EXIT");
            guestResetOrExitSessionReceiver.mBroadcastDispatcher.registerReceiver(guestResetOrExitSessionReceiver, intentFilter, null, UserHandle.SYSTEM);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0079, code lost:
    
        if (r7 == r1) goto L29;
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object guaranteePresent(kotlin.coroutines.jvm.internal.ContinuationImpl r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.android.systemui.user.domain.interactor.GuestUserInteractor$guaranteePresent$1
            if (r0 == 0) goto L13
            r0 = r8
            com.android.systemui.user.domain.interactor.GuestUserInteractor$guaranteePresent$1 r0 = (com.android.systemui.user.domain.interactor.GuestUserInteractor$guaranteePresent$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.user.domain.interactor.GuestUserInteractor$guaranteePresent$1 r0 = new com.android.systemui.user.domain.interactor.GuestUserInteractor$guaranteePresent$1
            r0.<init>(r7, r8)
        L18:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            r4 = 2
            r5 = 0
            r6 = 1
            if (r2 == 0) goto L3d
            if (r2 == r6) goto L35
            if (r2 != r4) goto L2d
            kotlin.ResultKt.throwOnFailure(r8)
            goto L7e
        L2d:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L35:
            java.lang.Object r7 = r0.L$0
            com.android.systemui.user.domain.interactor.GuestUserInteractor r7 = (com.android.systemui.user.domain.interactor.GuestUserInteractor) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L59
        L3d:
            kotlin.ResultKt.throwOnFailure(r8)
            boolean r8 = r7.isDeviceAllowedToAddGuest()
            if (r8 != 0) goto L47
            return r3
        L47:
            com.android.systemui.user.domain.interactor.GuestUserInteractor$guaranteePresent$guestUser$1 r8 = new com.android.systemui.user.domain.interactor.GuestUserInteractor$guaranteePresent$guestUser$1
            r8.<init>(r7, r5)
            r0.L$0 = r7
            r0.label = r6
            kotlinx.coroutines.CoroutineDispatcher r2 = r7.backgroundDispatcher
            java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r2, r8, r0)
            if (r8 != r1) goto L59
            return r1
        L59:
            android.content.pm.UserInfo r8 = (android.content.pm.UserInfo) r8
            if (r8 != 0) goto L7e
            r0.L$0 = r5
            r0.label = r4
            com.android.systemui.user.data.repository.UserRepositoryImpl r8 = r7.repository
            java.util.concurrent.atomic.AtomicBoolean r8 = r8.isGuestUserCreationScheduled
            r2 = 0
            boolean r8 = r8.compareAndSet(r2, r6)
            if (r8 != 0) goto L6e
        L6c:
            r7 = r3
            goto L7b
        L6e:
            com.android.systemui.user.domain.interactor.GuestUserInteractor$scheduleCreation$2 r8 = new com.android.systemui.user.domain.interactor.GuestUserInteractor$scheduleCreation$2
            r8.<init>(r7, r5)
            kotlinx.coroutines.CoroutineDispatcher r7 = r7.backgroundDispatcher
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r7, r8, r0)
            if (r7 != r1) goto L6c
        L7b:
            if (r7 != r1) goto L7e
            return r1
        L7e:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.user.domain.interactor.GuestUserInteractor.guaranteePresent(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final boolean isDeviceAllowedToAddGuest() {
        return ((DeviceProvisionedControllerImpl) this.deviceProvisionedController).deviceProvisioned.get() && !this.devicePolicyManager.isDeviceManaged();
    }

    public final void onDeviceBootCompleted() {
        BuildersKt.launch$default(this.applicationScope, null, null, new GuestUserInteractor$onDeviceBootCompleted$1(this, null), 3);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x018a A[Catch: RemoteException -> 0x0196, TryCatch #0 {RemoteException -> 0x0196, blocks: (B:20:0x0184, B:22:0x018a, B:24:0x018e, B:25:0x0195), top: B:19:0x0184 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x018e A[Catch: RemoteException -> 0x0196, TryCatch #0 {RemoteException -> 0x0196, blocks: (B:20:0x0184, B:22:0x018a, B:24:0x018e, B:25:0x0195), top: B:19:0x0184 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0183 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object remove(int r19, int r20, kotlin.jvm.functions.Function1 r21, kotlin.jvm.functions.Function0 r22, kotlin.jvm.functions.Function1 r23, kotlin.coroutines.jvm.internal.ContinuationImpl r24) {
        /*
            Method dump skipped, instructions count: 474
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.user.domain.interactor.GuestUserInteractor.remove(int, int, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function1, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
