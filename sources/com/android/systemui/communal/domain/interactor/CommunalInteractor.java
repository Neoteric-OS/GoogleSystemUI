package com.android.systemui.communal.domain.interactor;

import android.content.IntentFilter;
import android.os.UserManager;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.communal.data.repository.CommunalMediaRepositoryImpl;
import com.android.systemui.communal.data.repository.CommunalSmartspaceRepositoryImpl;
import com.android.systemui.communal.data.repository.CommunalWidgetRepositoryImpl;
import com.android.systemui.communal.domain.model.CommunalContentModel;
import com.android.systemui.communal.shared.model.CommunalContentSize;
import com.android.systemui.communal.widgets.CommunalAppWidgetHost;
import com.android.systemui.communal.widgets.EditWidgetsActivityStarterImpl;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.shared.model.Edge;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.Logger;
import com.android.systemui.log.table.DiffableKt;
import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.scene.domain.interactor.SceneInteractor;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.phone.ManagedProfileController;
import com.android.systemui.util.kotlin.BooleanFlowOperators$not$$inlined$map$1;
import com.android.systemui.util.kotlin.FlowKt$emitOnStart$1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalInteractor {
    public static final /* synthetic */ int $r8$clinit = 0;
    public static final long DISCLAIMER_RESET_MILLIS;
    public final StateFlowImpl _editActivityShowing;
    public final StateFlowImpl _editModeOpen;
    public int _firstVisibleItemIndex;
    public int _firstVisibleItemOffset;
    public final StateFlowImpl _isDisclaimerDismissed;
    public final StateFlowImpl _selectedKey;
    public final SharedFlowImpl _userActivity;
    public final ActivityStarter activityStarter;
    public final CommunalAppWidgetHost appWidgetHost;
    public final CoroutineDispatcher bgDispatcher;
    public final CoroutineScope bgScope;
    public final CommunalPrefsInteractor communalPrefsInteractor;
    public final CommunalSceneInteractor communalSceneInteractor;
    public final ChannelFlowTransformLatest communalWidgets;
    public final CommunalInteractor$special$$inlined$map$4 ctaTileContent;
    public final ReadonlyStateFlow desiredScene;
    public final ReadonlyStateFlow dreamFromOccluded;
    public final ReadonlyStateFlow editActivityShowing;
    public final ReadonlyStateFlow editModeOpen;
    public final EditWidgetsActivityStarterImpl editWidgetsActivityStarter;
    public final ReadonlySharedFlow isCommunalAvailable;
    public final ReadonlyStateFlow isCommunalEnabled;
    public final ReadonlySharedFlow isCommunalShowing;
    public final ReadonlyStateFlow isCommunalVisible;
    public final ReadonlyStateFlow isDisclaimerDismissed;
    public final ReadonlyStateFlow isIdleOnCommunal;
    public final Logger logger;
    public final ManagedProfileController managedProfileController;
    public final CommunalMediaRepositoryImpl mediaRepository;
    public final ReadonlyStateFlow selectedKey;
    public final ReadonlyStateFlow showCommunalFromOccluded;
    public final CommunalSmartspaceRepositoryImpl smartspaceRepository;
    public final List tutorialContent;
    public final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 updateOnWorkProfileBroadcastReceived;
    public final ReadonlySharedFlow userActivity;
    public final UserManager userManager;
    public final UserTracker userTracker;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 widgetContent;
    public final CommunalWidgetRepositoryImpl widgetRepository;

    static {
        int i = Duration.$r8$clinit;
        DISCLAIMER_RESET_MILLIS = DurationKt.toDuration(30, DurationUnit.MINUTES);
    }

    public CommunalInteractor(CoroutineScope coroutineScope, CoroutineScope coroutineScope2, CoroutineDispatcher coroutineDispatcher, BroadcastDispatcher broadcastDispatcher, CommunalWidgetRepositoryImpl communalWidgetRepositoryImpl, CommunalPrefsInteractor communalPrefsInteractor, CommunalMediaRepositoryImpl communalMediaRepositoryImpl, CommunalSmartspaceRepositoryImpl communalSmartspaceRepositoryImpl, KeyguardInteractor keyguardInteractor, KeyguardTransitionInteractor keyguardTransitionInteractor, CommunalSettingsInteractor communalSettingsInteractor, CommunalAppWidgetHost communalAppWidgetHost, EditWidgetsActivityStarterImpl editWidgetsActivityStarterImpl, UserTracker userTracker, ActivityStarter activityStarter, UserManager userManager, CommunalSceneInteractor communalSceneInteractor, SceneInteractor sceneInteractor, LogBuffer logBuffer, TableLogBuffer tableLogBuffer, ManagedProfileController managedProfileController) {
        int i = 0;
        this.bgScope = coroutineScope2;
        this.bgDispatcher = coroutineDispatcher;
        this.widgetRepository = communalWidgetRepositoryImpl;
        this.communalPrefsInteractor = communalPrefsInteractor;
        this.mediaRepository = communalMediaRepositoryImpl;
        this.smartspaceRepository = communalSmartspaceRepositoryImpl;
        this.appWidgetHost = communalAppWidgetHost;
        this.editWidgetsActivityStarter = editWidgetsActivityStarterImpl;
        this.userTracker = userTracker;
        this.activityStarter = activityStarter;
        this.userManager = userManager;
        this.communalSceneInteractor = communalSceneInteractor;
        this.managedProfileController = managedProfileController;
        this.logger = new Logger(logBuffer, "CommunalInteractor");
        Boolean bool = Boolean.FALSE;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(bool);
        this._editModeOpen = MutableStateFlow;
        this.editModeOpen = new ReadonlyStateFlow(MutableStateFlow);
        StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(bool);
        this._editActivityShowing = MutableStateFlow2;
        this.editActivityShowing = new ReadonlyStateFlow(MutableStateFlow2);
        StateFlowImpl MutableStateFlow3 = StateFlowKt.MutableStateFlow(null);
        this._selectedKey = MutableStateFlow3;
        this.selectedKey = new ReadonlyStateFlow(MutableStateFlow3);
        ReadonlyStateFlow readonlyStateFlow = communalSettingsInteractor.isCommunalEnabled;
        this.isCommunalEnabled = readonlyStateFlow;
        ReadonlySharedFlow shareIn = FlowKt.shareIn(DiffableKt.logDiffsForTable((Flow) new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.distinctUntilChanged(FlowKt.distinctUntilChanged(new BooleanFlowOperators$not$$inlined$map$1(1, (Flow[]) CollectionsKt.toList(ArraysKt.asIterable(new Flow[]{readonlyStateFlow, new BooleanFlowOperators$not$$inlined$map$1(i, keyguardInteractor.isEncryptedOrLockdown), keyguardInteractor.isKeyguardShowing})).toArray(new Flow[0])))), new CommunalInteractor$isCommunalAvailable$1(this, null), i), tableLogBuffer, "", "isCommunalAvailable", false), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), 1);
        this.isCommunalAvailable = shareIn;
        StateFlowImpl MutableStateFlow4 = StateFlowKt.MutableStateFlow(bool);
        this._isDisclaimerDismissed = MutableStateFlow4;
        this.isDisclaimerDismissed = new ReadonlyStateFlow(MutableStateFlow4);
        int i2 = 2;
        this.showCommunalFromOccluded = FlowKt.stateIn(FlowKt.flowOn(new CommunalInteractor$special$$inlined$map$2(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(new CommunalInteractor$special$$inlined$map$4(keyguardTransitionInteractor.startedKeyguardTransitionStep, i2), shareIn, CommunalInteractor$showCommunalFromOccluded$3.INSTANCE), i2), coroutineDispatcher), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        Edge.Companion companion = Edge.Companion;
        this.dreamFromOccluded = FlowKt.stateIn(new CommunalInteractor$special$$inlined$map$2(keyguardTransitionInteractor.transition(Edge.Companion.create$default(null, KeyguardState.OCCLUDED, 1)), 0), coroutineScope, SharingStarted.Companion.Eagerly, bool);
        this.desiredScene = communalSceneInteractor.currentScene;
        SharedFlowImpl MutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 1, BufferOverflow.DROP_OLDEST, 1);
        this._userActivity = MutableSharedFlow$default;
        this.userActivity = new ReadonlySharedFlow(MutableSharedFlow$default);
        this.isCommunalShowing = FlowKt.shareIn(DiffableKt.logDiffsForTable((Flow) new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.distinctUntilChanged(FlowKt.transformLatest(new SafeFlow(new CommunalInteractor$isCommunalShowing$1(2, null)), new CommunalInteractor$special$$inlined$flatMapLatest$1(null, sceneInteractor, this))), new CommunalInteractor$isCommunalShowing$3(this, null), 0), tableLogBuffer, "", "isCommunalShowing", false), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), 1);
        this.isIdleOnCommunal = communalSceneInteractor.isIdleOnCommunal;
        this.isCommunalVisible = communalSceneInteractor.isCommunalVisible;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.MANAGED_PROFILE_AVAILABLE");
        intentFilter.addAction("android.intent.action.MANAGED_PROFILE_UNAVAILABLE");
        FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new FlowKt$emitOnStart$1(2, null), BroadcastDispatcher.broadcastFlow$default(broadcastDispatcher, intentFilter, null, 14));
        this.communalWidgets = FlowKt.transformLatest(shareIn, new CommunalInteractor$special$$inlined$flatMapLatest$2(this, null));
        final Flow flow = communalWidgetRepositoryImpl.communalWidgets;
        this.widgetContent = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(new Flow() { // from class: com.android.systemui.communal.domain.interactor.CommunalInteractor$special$$inlined$map$3

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.communal.domain.interactor.CommunalInteractor$special$$inlined$map$3$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ CommunalInteractor this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.communal.domain.interactor.CommunalInteractor$special$$inlined$map$3$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, CommunalInteractor communalInteractor) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = communalInteractor;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r8, kotlin.coroutines.Continuation r9) {
                    /*
                        r7 = this;
                        boolean r0 = r9 instanceof com.android.systemui.communal.domain.interactor.CommunalInteractor$special$$inlined$map$3.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r9
                        com.android.systemui.communal.domain.interactor.CommunalInteractor$special$$inlined$map$3$2$1 r0 = (com.android.systemui.communal.domain.interactor.CommunalInteractor$special$$inlined$map$3.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.communal.domain.interactor.CommunalInteractor$special$$inlined$map$3$2$1 r0 = new com.android.systemui.communal.domain.interactor.CommunalInteractor$special$$inlined$map$3$2$1
                        r0.<init>(r9)
                    L18:
                        java.lang.Object r9 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L30
                        if (r2 != r3) goto L28
                        kotlin.ResultKt.throwOnFailure(r9)
                        goto Lba
                    L28:
                        java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                        java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                        r7.<init>(r8)
                        throw r7
                    L30:
                        kotlin.ResultKt.throwOnFailure(r9)
                        java.util.List r8 = (java.util.List) r8
                        com.android.systemui.communal.domain.interactor.CommunalInteractor r9 = r7.this$0
                        com.android.systemui.settings.UserTracker r9 = r9.userTracker
                        com.android.systemui.settings.UserTrackerImpl r9 = (com.android.systemui.settings.UserTrackerImpl) r9
                        java.util.List r9 = r9.getUserProfiles()
                        java.util.ArrayList r2 = new java.util.ArrayList
                        r4 = 10
                        int r4 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r9, r4)
                        r2.<init>(r4)
                        java.util.Iterator r9 = r9.iterator()
                    L4e:
                        boolean r4 = r9.hasNext()
                        if (r4 == 0) goto L64
                        java.lang.Object r4 = r9.next()
                        android.content.pm.UserInfo r4 = (android.content.pm.UserInfo) r4
                        int r4 = r4.id
                        java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
                        r2.add(r4)
                        goto L4e
                    L64:
                        java.util.Set r9 = kotlin.collections.CollectionsKt.toSet(r2)
                        java.util.ArrayList r2 = new java.util.ArrayList
                        r2.<init>()
                        java.util.Iterator r8 = r8.iterator()
                    L71:
                        boolean r4 = r8.hasNext()
                        if (r4 == 0) goto Laf
                        java.lang.Object r4 = r8.next()
                        r5 = r4
                        com.android.systemui.communal.shared.model.CommunalWidgetContentModel r5 = (com.android.systemui.communal.shared.model.CommunalWidgetContentModel) r5
                        boolean r6 = r5 instanceof com.android.systemui.communal.shared.model.CommunalWidgetContentModel.Available
                        if (r6 == 0) goto L9e
                        r6 = r9
                        java.lang.Iterable r6 = (java.lang.Iterable) r6
                        com.android.systemui.communal.shared.model.CommunalWidgetContentModel$Available r5 = (com.android.systemui.communal.shared.model.CommunalWidgetContentModel.Available) r5
                        android.appwidget.AppWidgetProviderInfo r5 = r5.providerInfo
                        android.os.UserHandle r5 = r5.getProfile()
                        if (r5 == 0) goto L98
                        int r5 = r5.getIdentifier()
                        java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                        goto L99
                    L98:
                        r5 = 0
                    L99:
                        boolean r5 = kotlin.collections.CollectionsKt.contains(r6, r5)
                        goto La3
                    L9e:
                        boolean r5 = r5 instanceof com.android.systemui.communal.shared.model.CommunalWidgetContentModel.Pending
                        if (r5 == 0) goto La9
                        r5 = r3
                    La3:
                        if (r5 == 0) goto L71
                        r2.add(r4)
                        goto L71
                    La9:
                        kotlin.NoWhenBranchMatchedException r7 = new kotlin.NoWhenBranchMatchedException
                        r7.<init>()
                        throw r7
                    Laf:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r7 = r7.$this_unsafeFlow
                        java.lang.Object r7 = r7.emit(r2, r0)
                        if (r7 != r1) goto Lba
                        return r1
                    Lba:
                        kotlin.Unit r7 = kotlin.Unit.INSTANCE
                        return r7
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.communal.domain.interactor.CommunalInteractor$special$$inlined$map$3.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }, communalSettingsInteractor.workProfileUserDisallowedByDevicePolicy, new CommunalInteractor$widgetContent$2(this, null)), flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1, new CommunalInteractor$widgetContent$3(this, null));
        this.ctaTileContent = new CommunalInteractor$special$$inlined$map$4(communalPrefsInteractor.isCtaDismissed, 0);
        CommunalContentModel.Tutorial tutorial = new CommunalContentModel.Tutorial(0, CommunalContentSize.FULL);
        CommunalContentSize communalContentSize = CommunalContentSize.THIRD;
        CommunalContentModel.Tutorial tutorial2 = new CommunalContentModel.Tutorial(1, communalContentSize);
        CommunalContentModel.Tutorial tutorial3 = new CommunalContentModel.Tutorial(2, communalContentSize);
        CommunalContentModel.Tutorial tutorial4 = new CommunalContentModel.Tutorial(3, communalContentSize);
        CommunalContentSize communalContentSize2 = CommunalContentSize.HALF;
        this.tutorialContent = CollectionsKt__CollectionsKt.listOf(tutorial, tutorial2, tutorial3, tutorial4, new CommunalContentModel.Tutorial(4, communalContentSize2), new CommunalContentModel.Tutorial(5, communalContentSize2), new CommunalContentModel.Tutorial(6, communalContentSize2), new CommunalContentModel.Tutorial(7, communalContentSize2));
    }

    public static final void resizeItems$resizeColumn(List list) {
        Object obj;
        if (list.isEmpty()) {
            return;
        }
        CommunalContentSize.Companion companion = CommunalContentSize.Companion;
        int span = CommunalContentSize.FULL.getSpan() / ((ArrayList) list).size();
        companion.getClass();
        Iterator it = ((AbstractList) CommunalContentSize.$ENTRIES).iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (((CommunalContentSize) obj).getSpan() == span) {
                    break;
                }
            }
        }
        CommunalContentSize communalContentSize = (CommunalContentSize) obj;
        if (communalContentSize == null) {
            throw new Exception("Invalid span for communal content size");
        }
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            ((CommunalContentModel.Ongoing) it2.next()).setSize(communalContentSize);
        }
    }

    public final Flow ongoingContent(boolean z) {
        return FlowKt.flowOn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(this.smartspaceRepository.timers, this.mediaRepository.mediaModel, new CommunalInteractor$ongoingContent$1(z, this, null)), this.bgDispatcher);
    }

    public final void setDisclaimerDismissed() {
        CoroutineTracingKt.launch$default(this.bgScope, null, new CommunalInteractor$setDisclaimerDismissed$1(this, null), 6);
    }
}
