package com.android.systemui.user.domain.interactor;

import android.R;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.UserInfo;
import android.os.Process;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import androidx.compose.runtime.collection.MutableVectorKt$$ExternalSyntheticOutline0;
import com.android.internal.logging.UiEventLogger;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.SystemUISecondaryUserService;
import com.android.systemui.animation.Expandable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.FeatureFlagsClassicRelease;
import com.android.systemui.flags.Flags;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.process.ProcessWrapper;
import com.android.systemui.qs.user.UserSwitchDialogController;
import com.android.systemui.telephony.domain.interactor.TelephonyInteractor;
import com.android.systemui.user.CreateUserActivity;
import com.android.systemui.user.data.repository.UserRepositoryImpl;
import com.android.systemui.user.domain.model.ShowDialogRequestModel;
import com.android.systemui.user.shared.model.UserActionModel;
import com.android.systemui.user.ui.dialog.DialogShowerImpl;
import com.android.systemui.user.utils.MultiUserActionsEvent;
import com.android.systemui.util.kotlin.WithPrev;
import com.android.systemui.utils.UserRestrictionChecker;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedEagerly;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.sync.MutexImpl;
import kotlinx.coroutines.sync.MutexKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UserSwitcherInteractor {
    public final StateFlowImpl _dialogDismissRequests;
    public final StateFlowImpl _dialogShowRequests;
    public final ActivityManager activityManager;
    public final ActivityStarter activityStarter;
    public final Context applicationContext;
    public final CoroutineScope applicationScope;
    public final CoroutineDispatcher backgroundDispatcher;
    public final MutexImpl callbackMutex = MutexKt.Mutex$default();
    public final Set callbacks = new LinkedHashSet();
    public final ReadonlyStateFlow dialogDismissRequests;
    public final ReadonlyStateFlow dialogShowRequests;
    public final FeatureFlags featureFlags;
    public final GuestUserInteractor guestUserInteractor;
    public final HeadlessSystemUserModeImpl headlessSystemUserMode;
    public final boolean isGuestUserAutoCreated;
    public final boolean isGuestUserResetting;
    public final boolean isStatusBarUserChipEnabled;
    public final KeyguardInteractor keyguardInteractor;
    public final KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback;
    public final CoroutineDispatcher mainDispatcher;
    public final UserManager manager;
    public final ProcessWrapper processWrapper;
    public final RefreshUsersScheduler refreshUsersScheduler;
    public final UserRepositoryImpl repository;
    public final ReadonlyStateFlow selectedUserRecord;
    public final UiEventLogger uiEventLogger;
    public final UserSwitcherInteractor$special$$inlined$map$1 userInfos;
    public final ReadonlyStateFlow userRecords;
    public final UserRestrictionChecker userRestrictionChecker;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.user.domain.interactor.UserSwitcherInteractor$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return UserSwitcherInteractor.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = (AnonymousClass1) create(Integer.valueOf(((Number) obj).intValue()), (Continuation) obj2);
            Unit unit = Unit.INSTANCE;
            anonymousClass1.invokeSuspend(unit);
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            UserSwitcherInteractor.this.refreshUsersScheduler.refreshIfNotPaused();
            return Unit.INSTANCE;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.user.domain.interactor.UserSwitcherInteractor$4, reason: invalid class name */
    final class AnonymousClass4 extends SuspendLambda implements Function3 {
        /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(3, (Continuation) obj3);
            anonymousClass4.L$0 = (Intent) obj;
            anonymousClass4.L$1 = (WithPrev) obj2;
            return anonymousClass4.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return new Pair((Intent) this.L$0, ((WithPrev) this.L$1).previousValue);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.user.domain.interactor.UserSwitcherInteractor$5, reason: invalid class name */
    final class AnonymousClass5 extends SuspendLambda implements Function2 {
        /* synthetic */ Object L$0;
        int label;

        public AnonymousClass5(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass5 anonymousClass5 = UserSwitcherInteractor.this.new AnonymousClass5(continuation);
            anonymousClass5.L$0 = obj;
            return anonymousClass5;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass5) create((Pair) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Pair pair = (Pair) this.L$0;
                Intent intent = (Intent) pair.component1();
                UserInfo userInfo = (UserInfo) pair.component2();
                UserSwitcherInteractor userSwitcherInteractor = UserSwitcherInteractor.this;
                this.label = 1;
                if (UserSwitcherInteractor.access$onBroadcastReceived(userSwitcherInteractor, intent, userInfo, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.user.domain.interactor.UserSwitcherInteractor$6, reason: invalid class name */
    final class AnonymousClass6 extends SuspendLambda implements Function2 {
        final /* synthetic */ KeyguardUpdateMonitor $keyguardUpdateMonitor;
        int label;
        final /* synthetic */ UserSwitcherInteractor this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.user.domain.interactor.UserSwitcherInteractor$6$1, reason: invalid class name */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            final /* synthetic */ KeyguardUpdateMonitor $keyguardUpdateMonitor;
            int label;
            final /* synthetic */ UserSwitcherInteractor this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(KeyguardUpdateMonitor keyguardUpdateMonitor, UserSwitcherInteractor userSwitcherInteractor, Continuation continuation) {
                super(2, continuation);
                this.$keyguardUpdateMonitor = keyguardUpdateMonitor;
                this.this$0 = userSwitcherInteractor;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass1(this.$keyguardUpdateMonitor, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                AnonymousClass1 anonymousClass1 = (AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2);
                Unit unit = Unit.INSTANCE;
                anonymousClass1.invokeSuspend(unit);
                return unit;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.$keyguardUpdateMonitor.registerCallback(this.this$0.keyguardUpdateMonitorCallback);
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass6(KeyguardUpdateMonitor keyguardUpdateMonitor, UserSwitcherInteractor userSwitcherInteractor, Continuation continuation) {
            super(2, continuation);
            this.this$0 = userSwitcherInteractor;
            this.$keyguardUpdateMonitor = keyguardUpdateMonitor;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass6(this.$keyguardUpdateMonitor, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass6) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                UserSwitcherInteractor userSwitcherInteractor = this.this$0;
                CoroutineDispatcher coroutineDispatcher = userSwitcherInteractor.mainDispatcher;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$keyguardUpdateMonitor, userSwitcherInteractor, null);
                this.label = 1;
                if (BuildersKt.withContext(coroutineDispatcher, anonymousClass1, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface UserCallback {
        default boolean isEvictable() {
            return false;
        }

        void onUserStateChanged();
    }

    /* JADX WARN: Type inference failed for: r6v0, types: [com.android.systemui.user.domain.interactor.UserSwitcherInteractor$special$$inlined$map$1, kotlinx.coroutines.flow.Flow] */
    public UserSwitcherInteractor(Context context, UserRepositoryImpl userRepositoryImpl, ActivityStarter activityStarter, KeyguardInteractor keyguardInteractor, FeatureFlags featureFlags, UserManager userManager, HeadlessSystemUserModeImpl headlessSystemUserModeImpl, CoroutineScope coroutineScope, TelephonyInteractor telephonyInteractor, BroadcastDispatcher broadcastDispatcher, KeyguardUpdateMonitor keyguardUpdateMonitor, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, ActivityManager activityManager, RefreshUsersScheduler refreshUsersScheduler, GuestUserInteractor guestUserInteractor, UiEventLogger uiEventLogger, UserRestrictionChecker userRestrictionChecker, ProcessWrapper processWrapper) {
        this.applicationContext = context;
        this.repository = userRepositoryImpl;
        this.activityStarter = activityStarter;
        this.keyguardInteractor = keyguardInteractor;
        this.featureFlags = featureFlags;
        this.manager = userManager;
        this.headlessSystemUserMode = headlessSystemUserModeImpl;
        this.applicationScope = coroutineScope;
        this.backgroundDispatcher = coroutineDispatcher;
        this.mainDispatcher = coroutineDispatcher2;
        this.activityManager = activityManager;
        this.refreshUsersScheduler = refreshUsersScheduler;
        this.guestUserInteractor = guestUserInteractor;
        this.uiEventLogger = uiEventLogger;
        this.userRestrictionChecker = userRestrictionChecker;
        final FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 = userRepositoryImpl.userInfos;
        ?? r6 = new Flow() { // from class: com.android.systemui.user.domain.interactor.UserSwitcherInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.user.domain.interactor.UserSwitcherInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.user.domain.interactor.UserSwitcherInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r6, kotlin.coroutines.Continuation r7) {
                    /*
                        r5 = this;
                        boolean r0 = r7 instanceof com.android.systemui.user.domain.interactor.UserSwitcherInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.user.domain.interactor.UserSwitcherInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.user.domain.interactor.UserSwitcherInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.user.domain.interactor.UserSwitcherInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.user.domain.interactor.UserSwitcherInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r7)
                    L18:
                        java.lang.Object r7 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r7)
                        goto L5f
                    L27:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r7)
                        java.util.List r6 = (java.util.List) r6
                        java.util.ArrayList r7 = new java.util.ArrayList
                        r7.<init>()
                        java.util.Iterator r6 = r6.iterator()
                    L3d:
                        boolean r2 = r6.hasNext()
                        if (r2 == 0) goto L54
                        java.lang.Object r2 = r6.next()
                        r4 = r2
                        android.content.pm.UserInfo r4 = (android.content.pm.UserInfo) r4
                        boolean r4 = r4.isFull()
                        if (r4 == 0) goto L3d
                        r7.add(r2)
                        goto L3d
                    L54:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
                        java.lang.Object r5 = r5.emit(r7, r0)
                        if (r5 != r1) goto L5f
                        return r1
                    L5f:
                        kotlin.Unit r5 = kotlin.Unit.INSTANCE
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.user.domain.interactor.UserSwitcherInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1.this.collect(new AnonymousClass2(flowCollector), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        };
        this.userInfos = r6;
        FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 flowKt__TransformKt$onEach$$inlined$unsafeTransform$1 = new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.combine(r6, userRepositoryImpl.selectedUserInfo, getActions(), userRepositoryImpl.userSwitcherSettings, new UserSwitcherInteractor$userRecords$1(this, null)), new UserSwitcherInteractor$userRecords$2(this, null), 0);
        StartedEagerly startedEagerly = SharingStarted.Companion.Eagerly;
        this.userRecords = FlowKt.stateIn(flowKt__TransformKt$onEach$$inlined$unsafeTransform$1, coroutineScope, startedEagerly, new ArrayList());
        this.selectedUserRecord = FlowKt.stateIn(new UserSwitcherInteractor$special$$inlined$map$2(userRepositoryImpl.selectedUserInfo, this, 0), coroutineScope, startedEagerly, null);
        this.isGuestUserAutoCreated = guestUserInteractor.isGuestUserAutoCreated;
        this.isGuestUserResetting = guestUserInteractor.isGuestUserResetting;
        this.isStatusBarUserChipEnabled = userRepositoryImpl.isStatusBarUserChipEnabled;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(null);
        this._dialogShowRequests = MutableStateFlow;
        this.dialogShowRequests = new ReadonlyStateFlow(MutableStateFlow);
        StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(null);
        this._dialogDismissRequests = MutableStateFlow2;
        this.dialogDismissRequests = new ReadonlyStateFlow(MutableStateFlow2);
        this.keyguardUpdateMonitorCallback = new KeyguardUpdateMonitorCallback() { // from class: com.android.systemui.user.domain.interactor.UserSwitcherInteractor$keyguardUpdateMonitorCallback$1
            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onKeyguardGoingAway() {
                UserSwitcherInteractor.this.dismissDialog();
            }
        };
        refreshUsersScheduler.refreshIfNotPaused();
        FlowKt.launchIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.distinctUntilChanged(telephonyInteractor.callState), new AnonymousClass1(null), 0), coroutineScope);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_ADDED");
        intentFilter.addAction("android.intent.action.USER_REMOVED");
        intentFilter.addAction("android.intent.action.USER_INFO_CHANGED");
        intentFilter.addAction("android.intent.action.USER_SWITCHED");
        intentFilter.addAction("android.intent.action.USER_STOPPED");
        intentFilter.addAction("android.intent.action.USER_UNLOCKED");
        intentFilter.addAction("android.intent.action.LOCALE_CHANGED");
        FlowKt.launchIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(BroadcastDispatcher.broadcastFlow$default(broadcastDispatcher, intentFilter, UserHandle.SYSTEM, new Function2() { // from class: com.android.systemui.user.domain.interactor.UserSwitcherInteractor.3
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return (Intent) obj;
            }
        }, 12), com.android.systemui.util.kotlin.FlowKt.pairwise(userRepositoryImpl.selectedUserInfo, null), new AnonymousClass4(3, null)), new AnonymousClass5(null), 0), coroutineScope);
        restartSecondaryService(userRepositoryImpl.getSelectedUserInfo().id);
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass6(keyguardUpdateMonitor, this, null), 3);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:11:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$onBroadcastReceived(com.android.systemui.user.domain.interactor.UserSwitcherInteractor r6, android.content.Intent r7, android.content.pm.UserInfo r8, kotlin.coroutines.jvm.internal.ContinuationImpl r9) {
        /*
            r6.getClass()
            boolean r0 = r9 instanceof com.android.systemui.user.domain.interactor.UserSwitcherInteractor$onBroadcastReceived$1
            if (r0 == 0) goto L16
            r0 = r9
            com.android.systemui.user.domain.interactor.UserSwitcherInteractor$onBroadcastReceived$1 r0 = (com.android.systemui.user.domain.interactor.UserSwitcherInteractor$onBroadcastReceived$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            com.android.systemui.user.domain.interactor.UserSwitcherInteractor$onBroadcastReceived$1 r0 = new com.android.systemui.user.domain.interactor.UserSwitcherInteractor$onBroadcastReceived$1
            r0.<init>(r6, r9)
        L1b:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L38
            if (r2 != r4) goto L30
            java.lang.Object r6 = r0.L$0
            com.android.systemui.user.domain.interactor.UserSwitcherInteractor r6 = (com.android.systemui.user.domain.interactor.UserSwitcherInteractor) r6
            kotlin.ResultKt.throwOnFailure(r9)
            goto La0
        L30:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L38:
            kotlin.ResultKt.throwOnFailure(r9)
            java.lang.String r9 = r7.getAction()
            if (r9 == 0) goto La0
            int r2 = r9.hashCode()
            java.lang.String r5 = "android.intent.extra.user_handle"
            switch(r2) {
                case -201513518: goto L9d;
                case -19011148: goto L96;
                case 833559602: goto L82;
                case 959232034: goto L4b;
                default: goto L4a;
            }
        L4a:
            goto La0
        L4b:
            java.lang.String r2 = "android.intent.action.USER_SWITCHED"
            boolean r9 = r9.equals(r2)
            if (r9 != 0) goto L54
            goto La0
        L54:
            r6.dismissDialog()
            r9 = -1
            int r7 = r7.getIntExtra(r5, r9)
            if (r8 == 0) goto L63
            int r8 = r8.id
            if (r8 != r7) goto L63
            goto L71
        L63:
            com.android.systemui.user.domain.interactor.UserSwitcherInteractor$notifyCallbacks$1 r8 = new com.android.systemui.user.domain.interactor.UserSwitcherInteractor$notifyCallbacks$1
            r8.<init>(r6, r3)
            r9 = 3
            kotlinx.coroutines.CoroutineScope r2 = r6.applicationScope
            kotlinx.coroutines.BuildersKt.launch$default(r2, r3, r3, r8, r9)
            r6.restartSecondaryService(r7)
        L71:
            com.android.systemui.user.domain.interactor.GuestUserInteractor r7 = r6.guestUserInteractor
            boolean r8 = r7.isGuestUserAutoCreated
            if (r8 == 0) goto La0
            r0.L$0 = r6
            r0.label = r4
            java.lang.Object r7 = r7.guaranteePresent(r0)
            if (r7 != r1) goto La0
            goto Lb6
        L82:
            java.lang.String r8 = "android.intent.action.USER_UNLOCKED"
            boolean r8 = r9.equals(r8)
            if (r8 != 0) goto L8b
            goto La0
        L8b:
            r8 = -10000(0xffffffffffffd8f0, float:NaN)
            int r7 = r7.getIntExtra(r5, r8)
            if (r7 != 0) goto L94
            goto La0
        L94:
            r4 = 0
            goto La0
        L96:
            java.lang.String r7 = "android.intent.action.LOCALE_CHANGED"
        L98:
            boolean r7 = r9.equals(r7)
            goto La0
        L9d:
            java.lang.String r7 = "android.intent.action.USER_INFO_CHANGED"
            goto L98
        La0:
            if (r4 == 0) goto Lb4
            com.android.systemui.user.domain.interactor.RefreshUsersScheduler r6 = r6.refreshUsersScheduler
            r6.getClass()
            com.android.systemui.user.domain.interactor.RefreshUsersScheduler$unpauseAndRefresh$1 r7 = new com.android.systemui.user.domain.interactor.RefreshUsersScheduler$unpauseAndRefresh$1
            r7.<init>(r6, r3)
            kotlinx.coroutines.CoroutineDispatcher r8 = r6.mainDispatcher
            r9 = 2
            kotlinx.coroutines.CoroutineScope r6 = r6.applicationScope
            kotlinx.coroutines.BuildersKt.launch$default(r6, r8, r3, r7, r9)
        Lb4:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
        Lb6:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.user.domain.interactor.UserSwitcherInteractor.access$onBroadcastReceived(com.android.systemui.user.domain.interactor.UserSwitcherInteractor, android.content.Intent, android.content.pm.UserInfo, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00a3 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$toRecord(com.android.systemui.user.domain.interactor.UserSwitcherInteractor r22, android.content.pm.UserInfo r23, int r24, kotlin.coroutines.jvm.internal.ContinuationImpl r25) {
        /*
            r0 = r22
            r1 = r23
            r2 = r24
            r3 = r25
            r22.getClass()
            boolean r4 = r3 instanceof com.android.systemui.user.domain.interactor.UserSwitcherInteractor$toRecord$1
            if (r4 == 0) goto L1e
            r4 = r3
            com.android.systemui.user.domain.interactor.UserSwitcherInteractor$toRecord$1 r4 = (com.android.systemui.user.domain.interactor.UserSwitcherInteractor$toRecord$1) r4
            int r5 = r4.label
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            r7 = r5 & r6
            if (r7 == 0) goto L1e
            int r5 = r5 - r6
            r4.label = r5
            goto L23
        L1e:
            com.android.systemui.user.domain.interactor.UserSwitcherInteractor$toRecord$1 r4 = new com.android.systemui.user.domain.interactor.UserSwitcherInteractor$toRecord$1
            r4.<init>(r0, r3)
        L23:
            java.lang.Object r3 = r4.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r5 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r6 = r4.label
            r7 = 0
            r8 = 1
            if (r6 == 0) goto L4a
            if (r6 != r8) goto L42
            int r0 = r4.I$0
            java.lang.Object r1 = r4.L$2
            android.os.UserManager r1 = (android.os.UserManager) r1
            java.lang.Object r2 = r4.L$1
            android.content.Context r2 = (android.content.Context) r2
            java.lang.Object r4 = r4.L$0
            android.content.pm.UserInfo r4 = (android.content.pm.UserInfo) r4
            kotlin.ResultKt.throwOnFailure(r3)
            r11 = r4
            goto L6e
        L42:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L4a:
            kotlin.ResultKt.throwOnFailure(r3)
            android.content.Context r3 = r0.applicationContext
            android.os.UserManager r6 = r0.manager
            int r9 = r1.id
            if (r9 != r2) goto L57
            r9 = r8
            goto L58
        L57:
            r9 = r7
        L58:
            r4.L$0 = r1
            r4.L$1 = r3
            r4.L$2 = r6
            r4.I$0 = r9
            r4.label = r8
            java.lang.Object r0 = r0.canSwitchUsers(r2, r4, r7)
            if (r0 != r5) goto L69
            goto Lbc
        L69:
            r11 = r1
            r2 = r3
            r1 = r6
            r3 = r0
            r0 = r9
        L6e:
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            if (r0 == 0) goto L78
            r14 = r8
            goto L79
        L78:
            r14 = r7
        L79:
            boolean r13 = r11.isGuest()
            com.android.systemui.user.data.source.UserRecord r5 = new com.android.systemui.user.data.source.UserRecord
            boolean r0 = r11.isGuest()
            r4 = 0
            if (r0 == 0) goto L88
        L86:
            r12 = r4
            goto La1
        L88:
            int r0 = r11.id
            android.graphics.Bitmap r0 = r1.getUserIcon(r0)
            if (r0 != 0) goto L91
            goto L86
        L91:
            android.content.res.Resources r1 = r2.getResources()
            r2 = 2131166939(0x7f0706db, float:1.7948138E38)
            int r1 = r1.getDimensionPixelSize(r2)
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createScaledBitmap(r0, r1, r1, r8)
            r12 = r0
        La1:
            if (r3 != 0) goto Lab
            if (r14 == 0) goto La8
            if (r13 != 0) goto La8
            goto Lab
        La8:
            r17 = r7
            goto Lad
        Lab:
            r17 = r8
        Lad:
            r19 = 0
            r20 = 0
            r15 = 0
            r16 = 0
            r18 = 0
            r21 = 944(0x3b0, float:1.323E-42)
            r10 = r5
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
        Lbc:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.user.domain.interactor.UserSwitcherInteractor.access$toRecord(com.android.systemui.user.domain.interactor.UserSwitcherInteractor, android.content.pm.UserInfo, int, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d6, code lost:
    
        if (r12 != r1) goto L41;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$toUserModels(com.android.systemui.user.domain.interactor.UserSwitcherInteractor r8, java.util.List r9, int r10, boolean r11, kotlin.coroutines.jvm.internal.ContinuationImpl r12) {
        /*
            Method dump skipped, instructions count: 226
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.user.domain.interactor.UserSwitcherInteractor.access$toUserModels(com.android.systemui.user.domain.interactor.UserSwitcherInteractor, java.util.List, int, boolean, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final void addCallback(UserCallback userCallback) {
        BuildersKt.launch$default(this.applicationScope, null, null, new UserSwitcherInteractor$addCallback$1(this, userCallback, null), 3);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x007d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object canSwitchUsers(int r10, kotlin.coroutines.jvm.internal.ContinuationImpl r11, boolean r12) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof com.android.systemui.user.domain.interactor.UserSwitcherInteractor$canSwitchUsers$1
            if (r0 == 0) goto L13
            r0 = r11
            com.android.systemui.user.domain.interactor.UserSwitcherInteractor$canSwitchUsers$1 r0 = (com.android.systemui.user.domain.interactor.UserSwitcherInteractor$canSwitchUsers$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.user.domain.interactor.UserSwitcherInteractor$canSwitchUsers$1 r0 = new com.android.systemui.user.domain.interactor.UserSwitcherInteractor$canSwitchUsers$1
            r0.<init>(r9, r11)
        L18:
            java.lang.Object r11 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 0
            r4 = 3
            r5 = 2
            r6 = 0
            r7 = 1
            if (r2 == 0) goto L4e
            if (r2 == r7) goto L42
            if (r2 == r5) goto L38
            if (r2 != r4) goto L30
            kotlin.ResultKt.throwOnFailure(r11)
            goto La7
        L30:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L38:
            int r9 = r0.I$0
            java.lang.Object r10 = r0.L$0
            com.android.systemui.user.domain.interactor.UserSwitcherInteractor r10 = (com.android.systemui.user.domain.interactor.UserSwitcherInteractor) r10
            kotlin.ResultKt.throwOnFailure(r11)
            goto L81
        L42:
            boolean r12 = r0.Z$0
            int r10 = r0.I$0
            java.lang.Object r9 = r0.L$0
            com.android.systemui.user.domain.interactor.UserSwitcherInteractor r9 = (com.android.systemui.user.domain.interactor.UserSwitcherInteractor) r9
            kotlin.ResultKt.throwOnFailure(r11)
            goto L67
        L4e:
            kotlin.ResultKt.throwOnFailure(r11)
            com.android.systemui.user.domain.interactor.UserSwitcherInteractor$canSwitchUsers$isHeadlessSystemUserMode$1 r11 = new com.android.systemui.user.domain.interactor.UserSwitcherInteractor$canSwitchUsers$isHeadlessSystemUserMode$1
            r11.<init>(r9, r6)
            r0.L$0 = r9
            r0.I$0 = r10
            r0.Z$0 = r12
            r0.label = r7
            kotlinx.coroutines.CoroutineDispatcher r2 = r9.backgroundDispatcher
            java.lang.Object r11 = kotlinx.coroutines.BuildersKt.withContext(r2, r11, r0)
            if (r11 != r1) goto L67
            return r1
        L67:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r12 == 0) goto L8f
            if (r11 == 0) goto L8f
            r0.L$0 = r9
            r0.I$0 = r10
            r0.label = r5
            java.lang.Object r11 = r9.isAnyUserUnlocked(r0)
            if (r11 != r1) goto L7e
            return r1
        L7e:
            r8 = r10
            r10 = r9
            r9 = r8
        L81:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L8d
            r8 = r10
            r10 = r9
            r9 = r8
            goto L8f
        L8d:
            r11 = r3
            goto L93
        L8f:
            r11 = r7
            r8 = r10
            r10 = r9
            r9 = r8
        L93:
            if (r11 == 0) goto Lb0
            kotlinx.coroutines.CoroutineDispatcher r11 = r10.backgroundDispatcher
            com.android.systemui.user.domain.interactor.UserSwitcherInteractor$canSwitchUsers$2 r12 = new com.android.systemui.user.domain.interactor.UserSwitcherInteractor$canSwitchUsers$2
            r12.<init>(r10, r9, r6)
            r0.L$0 = r6
            r0.label = r4
            java.lang.Object r11 = kotlinx.coroutines.BuildersKt.withContext(r11, r12, r0)
            if (r11 != r1) goto La7
            return r1
        La7:
            java.lang.Number r11 = (java.lang.Number) r11
            int r9 = r11.intValue()
            if (r9 != 0) goto Lb0
            r3 = r7
        Lb0:
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r3)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.user.domain.interactor.UserSwitcherInteractor.canSwitchUsers(int, kotlin.coroutines.jvm.internal.ContinuationImpl, boolean):java.lang.Object");
    }

    public final void dismissDialog() {
        Unit unit = Unit.INSTANCE;
        StateFlowImpl stateFlowImpl = this._dialogDismissRequests;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, unit);
    }

    public final void executeAction(UserActionModel userActionModel, final DialogShowerImpl dialogShowerImpl) {
        int ordinal = userActionModel.ordinal();
        if (ordinal == 0) {
            this.uiEventLogger.log(MultiUserActionsEvent.CREATE_GUEST_FROM_USER_SWITCHER);
            UserSwitcherInteractor$executeAction$1 userSwitcherInteractor$executeAction$1 = new UserSwitcherInteractor$executeAction$1(1, this, UserSwitcherInteractor.class, "showDialog", "showDialog(Lcom/android/systemui/user/domain/model/ShowDialogRequestModel;)V", 0);
            UserSwitcherInteractor$executeAction$2 userSwitcherInteractor$executeAction$2 = new UserSwitcherInteractor$executeAction$2(0, this, UserSwitcherInteractor.class, "dismissDialog", "dismissDialog()V", 0);
            Function1 function1 = new Function1() { // from class: com.android.systemui.user.domain.interactor.UserSwitcherInteractor$executeAction$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    UserSwitcherInteractor.this.selectUser(((Number) obj).intValue(), dialogShowerImpl);
                    return Unit.INSTANCE;
                }
            };
            GuestUserInteractor guestUserInteractor = this.guestUserInteractor;
            guestUserInteractor.getClass();
            BuildersKt.launch$default(guestUserInteractor.applicationScope, null, null, new GuestUserInteractor$createAndSwitchTo$1(guestUserInteractor, userSwitcherInteractor$executeAction$1, userSwitcherInteractor$executeAction$2, function1, null), 3);
            return;
        }
        if (ordinal != 1) {
            ActivityStarter activityStarter = this.activityStarter;
            if (ordinal != 2) {
                if (ordinal != 3) {
                    return;
                }
                activityStarter.startActivity(new Intent("android.settings.USER_SETTINGS"), true);
                return;
            } else {
                this.uiEventLogger.log(MultiUserActionsEvent.CREATE_RESTRICTED_USER_FROM_USER_SWITCHER);
                dismissDialog();
                activityStarter.startActivity(new Intent().setAction("android.os.action.CREATE_SUPERVISED_USER").setPackage(this.applicationContext.getString(R.string.config_usbPermissionActivity)).addFlags(268435456), true);
                return;
            }
        }
        this.uiEventLogger.log(MultiUserActionsEvent.CREATE_USER_FROM_USER_SWITCHER);
        UserInfo selectedUserInfo = this.repository.getSelectedUserInfo();
        dismissDialog();
        Context context = this.applicationContext;
        boolean isKeyguardShowing = this.keyguardInteractor.isKeyguardShowing();
        int i = CreateUserActivity.$r8$clinit;
        Intent intent = new Intent(context, (Class<?>) CreateUserActivity.class);
        intent.addFlags(335544320);
        intent.putExtra("extra_is_keyguard_showing", isKeyguardShowing);
        this.activityStarter.startActivity(intent, true, null, true, selectedUserInfo.getUserHandle());
    }

    public final void exitGuestUser(int i, int i2, boolean z) {
        UserSwitcherInteractor$exitGuestUser$1 userSwitcherInteractor$exitGuestUser$1 = new UserSwitcherInteractor$exitGuestUser$1(1, this, UserSwitcherInteractor.class, "showDialog", "showDialog(Lcom/android/systemui/user/domain/model/ShowDialogRequestModel;)V", 0);
        UserSwitcherInteractor$exitGuestUser$2 userSwitcherInteractor$exitGuestUser$2 = new UserSwitcherInteractor$exitGuestUser$2(0, this, UserSwitcherInteractor.class, "dismissDialog", "dismissDialog()V", 0);
        UserSwitcherInteractor$exitGuestUser$3 userSwitcherInteractor$exitGuestUser$3 = new UserSwitcherInteractor$exitGuestUser$3(1, this, UserSwitcherInteractor.class, "switchUser", "switchUser(I)V", 0);
        GuestUserInteractor guestUserInteractor = this.guestUserInteractor;
        UserInfo selectedUserInfo = guestUserInteractor.repository.getSelectedUserInfo();
        int i3 = selectedUserInfo.id;
        if (i3 != i) {
            Log.w("GuestUserInteractor", MutableVectorKt$$ExternalSyntheticOutline0.m(i, i3, "User requesting to start a new session (", ") is not current user (", ")"));
            return;
        }
        if (selectedUserInfo.isGuest()) {
            BuildersKt.launch$default(guestUserInteractor.applicationScope, null, null, new GuestUserInteractor$exit$1(guestUserInteractor, i2, selectedUserInfo, z, userSwitcherInteractor$exitGuestUser$1, userSwitcherInteractor$exitGuestUser$2, userSwitcherInteractor$exitGuestUser$3, null), 3);
        } else {
            Log.w("GuestUserInteractor", "User requesting to start a new session (" + i + ") is not a guest");
        }
    }

    public final Flow getActions() {
        UserRepositoryImpl userRepositoryImpl = this.repository;
        return FlowKt.flowOn(FlowKt.combine(userRepositoryImpl.selectedUserInfo, this.userInfos, userRepositoryImpl.userSwitcherSettings, this.keyguardInteractor.isKeyguardShowing, new UserSwitcherInteractor$actions$1(this, null)), this.backgroundDispatcher);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getUserImage(int r5, kotlin.coroutines.jvm.internal.ContinuationImpl r6, boolean r7) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.android.systemui.user.domain.interactor.UserSwitcherInteractor$getUserImage$1
            if (r0 == 0) goto L13
            r0 = r6
            com.android.systemui.user.domain.interactor.UserSwitcherInteractor$getUserImage$1 r0 = (com.android.systemui.user.domain.interactor.UserSwitcherInteractor$getUserImage$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.user.domain.interactor.UserSwitcherInteractor$getUserImage$1 r0 = new com.android.systemui.user.domain.interactor.UserSwitcherInteractor$getUserImage$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            int r5 = r0.I$0
            java.lang.Object r4 = r0.L$0
            com.android.systemui.user.domain.interactor.UserSwitcherInteractor r4 = (com.android.systemui.user.domain.interactor.UserSwitcherInteractor) r4
            kotlin.ResultKt.throwOnFailure(r6)
            goto L63
        L2d:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L35:
            kotlin.ResultKt.throwOnFailure(r6)
            if (r7 == 0) goto L4e
            android.content.Context r4 = r4.applicationContext
            r5 = 2131232269(0x7f08060d, float:1.8080642E38)
            android.graphics.drawable.Drawable r4 = r4.getDrawable(r5)
            if (r4 == 0) goto L46
            return r4
        L46:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "Required value was null."
            r4.<init>(r5)
            throw r4
        L4e:
            com.android.systemui.user.domain.interactor.UserSwitcherInteractor$getUserImage$userIcon$1 r6 = new com.android.systemui.user.domain.interactor.UserSwitcherInteractor$getUserImage$userIcon$1
            r7 = 0
            r6.<init>(r4, r5, r7)
            r0.L$0 = r4
            r0.I$0 = r5
            r0.label = r3
            kotlinx.coroutines.CoroutineDispatcher r7 = r4.backgroundDispatcher
            java.lang.Object r6 = kotlinx.coroutines.BuildersKt.withContext(r7, r6, r0)
            if (r6 != r1) goto L63
            return r1
        L63:
            android.graphics.Bitmap r6 = (android.graphics.Bitmap) r6
            if (r6 == 0) goto L6d
            android.graphics.drawable.BitmapDrawable r4 = new android.graphics.drawable.BitmapDrawable
            r4.<init>(r6)
            return r4
        L6d:
            android.content.Context r4 = r4.applicationContext
            android.content.res.Resources r4 = r4.getResources()
            r6 = 0
            android.graphics.drawable.Drawable r4 = com.android.internal.util.UserIcons.getDefaultUserIcon(r4, r5, r6)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.user.domain.interactor.UserSwitcherInteractor.getUserImage(int, kotlin.coroutines.jvm.internal.ContinuationImpl, boolean):java.lang.Object");
    }

    public final FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 getUsers() {
        UserRepositoryImpl userRepositoryImpl = this.repository;
        return FlowKt.combine(this.userInfos, userRepositoryImpl.selectedUserInfo, userRepositoryImpl.userSwitcherSettings, new UserSwitcherInteractor$users$1(this, null));
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0074 -> B:10:0x0077). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object isAnyUserUnlocked(kotlin.coroutines.jvm.internal.ContinuationImpl r10) {
        /*
            r9 = this;
            boolean r0 = r10 instanceof com.android.systemui.user.domain.interactor.UserSwitcherInteractor$isAnyUserUnlocked$1
            if (r0 == 0) goto L13
            r0 = r10
            com.android.systemui.user.domain.interactor.UserSwitcherInteractor$isAnyUserUnlocked$1 r0 = (com.android.systemui.user.domain.interactor.UserSwitcherInteractor$isAnyUserUnlocked$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.user.domain.interactor.UserSwitcherInteractor$isAnyUserUnlocked$1 r0 = new com.android.systemui.user.domain.interactor.UserSwitcherInteractor$isAnyUserUnlocked$1
            r0.<init>(r9, r10)
        L18:
            java.lang.Object r10 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L38
            if (r2 != r4) goto L30
            java.lang.Object r9 = r0.L$1
            java.util.Iterator r9 = (java.util.Iterator) r9
            java.lang.Object r2 = r0.L$0
            com.android.systemui.user.domain.interactor.UserSwitcherInteractor r2 = (com.android.systemui.user.domain.interactor.UserSwitcherInteractor) r2
            kotlin.ResultKt.throwOnFailure(r10)
            goto L77
        L30:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L38:
            kotlin.ResultKt.throwOnFailure(r10)
            android.os.UserManager r10 = r9.manager
            java.util.List r10 = r10.getUsers(r4, r4, r4)
            boolean r2 = r10.isEmpty()
            if (r2 == 0) goto L48
            goto L87
        L48:
            java.util.Iterator r10 = r10.iterator()
            r8 = r10
            r10 = r9
            r9 = r8
        L4f:
            boolean r2 = r9.hasNext()
            if (r2 == 0) goto L87
            java.lang.Object r2 = r9.next()
            android.content.pm.UserInfo r2 = (android.content.pm.UserInfo) r2
            int r5 = r2.id
            if (r5 == 0) goto L83
            kotlinx.coroutines.CoroutineDispatcher r5 = r10.backgroundDispatcher
            com.android.systemui.user.domain.interactor.UserSwitcherInteractor$isAnyUserUnlocked$2$1 r6 = new com.android.systemui.user.domain.interactor.UserSwitcherInteractor$isAnyUserUnlocked$2$1
            r7 = 0
            r6.<init>(r10, r2, r7)
            r0.L$0 = r10
            r0.L$1 = r9
            r0.label = r4
            java.lang.Object r2 = kotlinx.coroutines.BuildersKt.withContext(r5, r6, r0)
            if (r2 != r1) goto L74
            return r1
        L74:
            r8 = r2
            r2 = r10
            r10 = r8
        L77:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L82
            r10 = r2
            r2 = r4
            goto L84
        L82:
            r10 = r2
        L83:
            r2 = r3
        L84:
            if (r2 == 0) goto L4f
            r3 = r4
        L87:
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r3)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.user.domain.interactor.UserSwitcherInteractor.isAnyUserUnlocked(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final void removeCallback(UserCallback userCallback) {
        BuildersKt.launch$default(this.applicationScope, null, null, new UserSwitcherInteractor$removeCallback$1(this, userCallback, null), 3);
    }

    public final void removeGuestUser(int i) {
        BuildersKt.launch$default(this.applicationScope, null, null, new UserSwitcherInteractor$removeGuestUser$1(this, i, -10000, null), 3);
    }

    public final void restartSecondaryService(int i) {
        List aliveUsers = this.manager.getAliveUsers();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(aliveUsers, 10));
        Iterator it = aliveUsers.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(((UserInfo) it.next()).id));
        }
        if (arrayList.contains(Integer.valueOf(i))) {
            Intent intent = new Intent(this.applicationContext, (Class<?>) SystemUISecondaryUserService.class);
            UserRepositoryImpl userRepositoryImpl = this.repository;
            int i2 = userRepositoryImpl.secondaryUserId;
            if (i2 != -10000) {
                this.applicationContext.stopServiceAsUser(intent, UserHandle.of(i2));
                userRepositoryImpl.secondaryUserId = -10000;
            }
            if (i == Process.myUserHandle().getIdentifier() || ProcessWrapper.isSystemUser()) {
                return;
            }
            this.applicationContext.startServiceAsUser(intent, UserHandle.of(i));
            userRepositoryImpl.secondaryUserId = i;
        }
    }

    public final void selectUser(int i, UserSwitchDialogController.DialogShower dialogShower) {
        UserRepositoryImpl userRepositoryImpl = this.repository;
        UserInfo selectedUserInfo = userRepositoryImpl.getSelectedUserInfo();
        int i2 = selectedUserInfo.id;
        KeyguardInteractor keyguardInteractor = this.keyguardInteractor;
        if (i == i2 && selectedUserInfo.isGuest()) {
            showDialog(new ShowDialogRequestModel.ShowExitGuestDialog(selectedUserInfo.id, userRepositoryImpl.lastSelectedNonGuestUserId, selectedUserInfo.isEphemeral(), keyguardInteractor.isKeyguardShowing(), new UserSwitcherInteractor$selectUser$1(3, this, UserSwitcherInteractor.class, "exitGuestUser", "exitGuestUser(IIZ)V", 0), dialogShower));
        } else {
            if (selectedUserInfo.isGuest()) {
                showDialog(new ShowDialogRequestModel.ShowExitGuestDialog(selectedUserInfo.id, i, selectedUserInfo.isEphemeral(), keyguardInteractor.isKeyguardShowing(), new UserSwitcherInteractor$selectUser$2(3, this, UserSwitcherInteractor.class, "exitGuestUser", "exitGuestUser(IIZ)V", 0), dialogShower));
                return;
            }
            if (dialogShower != null) {
                ((DialogShowerImpl) dialogShower).dismiss();
            }
            switchUser(i);
        }
    }

    public final void showDialog(ShowDialogRequestModel showDialogRequestModel) {
        this._dialogShowRequests.setValue(showDialogRequestModel);
    }

    public final void showUserSwitcher(Expandable expandable) {
        if (((FeatureFlagsClassicRelease) this.featureFlags).isEnabled(Flags.FULL_SCREEN_USER_SWITCHER)) {
            showDialog(new ShowDialogRequestModel.ShowUserSwitcherFullscreenDialog(expandable));
        } else {
            showDialog(new ShowDialogRequestModel.ShowUserSwitcherDialog(expandable));
        }
    }

    public final void switchUser(final int i) {
        RefreshUsersScheduler refreshUsersScheduler = this.refreshUsersScheduler;
        refreshUsersScheduler.getClass();
        BuildersKt.launch$default(refreshUsersScheduler.applicationScope, refreshUsersScheduler.mainDispatcher, null, new RefreshUsersScheduler$pause$1(refreshUsersScheduler, null), 2);
        BuildersKt.launch$default(this.applicationScope, null, null, new UserSwitcherInteractor$switchUser$1(this, new Runnable() { // from class: com.android.systemui.user.domain.interactor.UserSwitcherInteractor$switchUser$runnable$1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    UserSwitcherInteractor.this.activityManager.switchUser(i);
                } catch (RemoteException e) {
                    Log.e("UserSwitcherInteractor", "Couldn't switch user.", e);
                }
            }
        }, null), 3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002b  */
    /* JADX WARN: Type inference failed for: r17v0, types: [com.android.systemui.user.domain.interactor.UserSwitcherInteractor] */
    /* JADX WARN: Type inference failed for: r4v4, types: [com.android.systemui.common.shared.model.Text] */
    /* JADX WARN: Type inference failed for: r4v6, types: [com.android.systemui.common.shared.model.Text] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object toUserModel(android.content.pm.UserInfo r18, int r19, boolean r20, kotlin.coroutines.jvm.internal.ContinuationImpl r21) {
        /*
            Method dump skipped, instructions count: 206
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.user.domain.interactor.UserSwitcherInteractor.toUserModel(android.content.pm.UserInfo, int, boolean, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$toRecord(com.android.systemui.user.domain.interactor.UserSwitcherInteractor r19, com.android.systemui.user.shared.model.UserActionModel r20, int r21, boolean r22, kotlin.coroutines.jvm.internal.ContinuationImpl r23) {
        /*
            Method dump skipped, instructions count: 192
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.user.domain.interactor.UserSwitcherInteractor.access$toRecord(com.android.systemui.user.domain.interactor.UserSwitcherInteractor, com.android.systemui.user.shared.model.UserActionModel, int, boolean, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
