package com.android.systemui.user.data.repository;

import android.R;
import android.content.Context;
import android.content.pm.UserInfo;
import android.os.UserManager;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.user.data.model.SelectedUserModel;
import com.android.systemui.user.data.model.SelectionStatus;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.settings.SettingsProxyExt;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedEagerly;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UserRepositoryImpl {
    public final StateFlowImpl _userInfos;
    public final ReadonlyStateFlow _userSwitcherSettings;
    public final Context appContext;
    public final CoroutineScope applicationScope;
    public final CoroutineDispatcher backgroundDispatcher;
    public final GlobalSettings globalSettings;
    public final boolean isGuestUserAutoCreated;
    public final AtomicBoolean isGuestUserCreationScheduled;
    public boolean isGuestUserResetting;
    public final boolean isStatusBarUserChipEnabled;
    public int lastSelectedNonGuestUserId;
    public final CoroutineDispatcher mainDispatcher;
    public int mainUserId;
    public final UserManager manager;
    public int secondaryUserId;
    public final ReadonlyStateFlow selectedUser;
    public final UserRepositoryImpl$special$$inlined$map$2 selectedUserInfo;
    public final UserTracker tracker;
    public final FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 userInfos;
    public final ReadonlyStateFlow userSwitcherSettings;

    public UserRepositoryImpl(Context context, UserManager userManager, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, GlobalSettings globalSettings, UserTracker userTracker) {
        this.appContext = context;
        this.manager = userManager;
        this.applicationScope = coroutineScope;
        this.mainDispatcher = coroutineDispatcher;
        this.backgroundDispatcher = coroutineDispatcher2;
        this.globalSettings = globalSettings;
        this.tracker = userTracker;
        final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new UserRepositoryImpl$_userSwitcherSettings$1(2, null), SettingsProxyExt.observerFlow(globalSettings, "lockscreenSimpleUserSwitcher", "add_users_when_locked", "user_switcher_enabled"));
        Flow flow = new Flow() { // from class: com.android.systemui.user.data.repository.UserRepositoryImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.user.data.repository.UserRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ UserRepositoryImpl this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.user.data.repository.UserRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, UserRepositoryImpl userRepositoryImpl) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = userRepositoryImpl;
                }

                /* JADX WARN: Removed duplicated region for block: B:19:0x0064 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:20:0x003b  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
                    /*
                        r6 = this;
                        boolean r0 = r8 instanceof com.android.systemui.user.data.repository.UserRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r8
                        com.android.systemui.user.data.repository.UserRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.user.data.repository.UserRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.user.data.repository.UserRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.user.data.repository.UserRepositoryImpl$special$$inlined$map$1$2$1
                        r0.<init>(r8)
                    L18:
                        java.lang.Object r8 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 0
                        r4 = 2
                        r5 = 1
                        if (r2 == 0) goto L3b
                        if (r2 == r5) goto L33
                        if (r2 != r4) goto L2b
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L65
                    L2b:
                        java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                        java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                        r6.<init>(r7)
                        throw r6
                    L33:
                        java.lang.Object r6 = r0.L$0
                        kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L5a
                    L3b:
                        kotlin.ResultKt.throwOnFailure(r8)
                        kotlin.Unit r7 = (kotlin.Unit) r7
                        kotlinx.coroutines.flow.FlowCollector r7 = r6.$this_unsafeFlow
                        r0.L$0 = r7
                        r0.label = r5
                        com.android.systemui.user.data.repository.UserRepositoryImpl r6 = r6.this$0
                        r6.getClass()
                        com.android.systemui.user.data.repository.UserRepositoryImpl$getSettings$2 r8 = new com.android.systemui.user.data.repository.UserRepositoryImpl$getSettings$2
                        r8.<init>(r6, r3)
                        kotlinx.coroutines.CoroutineDispatcher r6 = r6.backgroundDispatcher
                        java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r6, r8, r0)
                        if (r8 != r1) goto L59
                        return r1
                    L59:
                        r6 = r7
                    L5a:
                        r0.L$0 = r3
                        r0.label = r4
                        java.lang.Object r6 = r6.emit(r8, r0)
                        if (r6 != r1) goto L65
                        return r1
                    L65:
                        kotlin.Unit r6 = kotlin.Unit.INSTANCE
                        return r6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.user.data.repository.UserRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        };
        StartedEagerly startedEagerly = SharingStarted.Companion.Eagerly;
        ReadonlyStateFlow stateIn = FlowKt.stateIn(flow, coroutineScope, startedEagerly, BuildersKt.runBlocking(EmptyCoroutineContext.INSTANCE, new UserRepositoryImpl$_userSwitcherSettings$3(this, null)));
        this._userSwitcherSettings = stateIn;
        this.userSwitcherSettings = stateIn;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(null);
        this._userInfos = MutableStateFlow;
        this.userInfos = new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(MutableStateFlow);
        this.mainUserId = -10000;
        this.lastSelectedNonGuestUserId = -10000;
        this.isGuestUserAutoCreated = context.getResources().getBoolean(R.bool.config_honor_data_retry_timer_for_emergency_network);
        this.isGuestUserResetting = false;
        this.isGuestUserCreationScheduled = new AtomicBoolean();
        this.isStatusBarUserChipEnabled = context.getResources().getBoolean(com.android.wm.shell.R.bool.flag_user_switcher_chip);
        this.secondaryUserId = -10000;
        Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        ref$ObjectRef.element = SelectionStatus.SELECTION_COMPLETE;
        ReadonlyStateFlow stateIn2 = FlowKt.stateIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowConflatedKt.conflatedCallbackFlow(new UserRepositoryImpl$selectedUser$1$1(this, ref$ObjectRef, null)), new UserRepositoryImpl$selectedUser$1$2(this, null), 0), coroutineScope, startedEagerly, new SelectedUserModel(((UserTrackerImpl) userTracker).getUserInfo(), (SelectionStatus) ref$ObjectRef.element));
        this.selectedUser = stateIn2;
        this.selectedUserInfo = new UserRepositoryImpl$special$$inlined$map$2(stateIn2);
    }

    public final UserInfo getSelectedUserInfo() {
        return ((SelectedUserModel) this.selectedUser.getValue()).userInfo;
    }

    public final void refreshUsers() {
        BuildersKt.launch$default(this.applicationScope, null, null, new UserRepositoryImpl$refreshUsers$1(this, null), 3);
    }
}
