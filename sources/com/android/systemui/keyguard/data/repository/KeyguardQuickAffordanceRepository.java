package com.android.systemui.keyguard.data.repository;

import android.content.Context;
import android.os.UserHandle;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.constraintlayout.motion.widget.MotionLayout$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig;
import com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceLegacySettingSyncer;
import com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceLocalUserSelectionManager;
import com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceRemoteUserSelectionManager;
import com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceSelectionManager;
import com.android.systemui.keyguard.shared.model.KeyguardSlotPickerRepresentation;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.android.wm.shell.R;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgressionIterator;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedEagerly;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardQuickAffordanceRepository {
    public final Context appContext;
    public final Map configsByAffordanceId;
    public final KeyguardQuickAffordanceLocalUserSelectionManager localUserSelectionManager;
    public final KeyguardQuickAffordanceRemoteUserSelectionManager remoteUserSelectionManager;
    public final CoroutineScope scope;
    public final ReadonlyStateFlow selectionManager;
    public final ReadonlyStateFlow selections;
    public final UserTracker userTracker;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Dumpster implements Dumpable {
        public Dumpster() {
        }

        @Override // com.android.systemui.Dumpable
        public final void dump(PrintWriter printWriter, String[] strArr) {
            KeyguardQuickAffordanceRepository keyguardQuickAffordanceRepository = KeyguardQuickAffordanceRepository.this;
            List<KeyguardSlotPickerRepresentation> slotPickerRepresentations = keyguardQuickAffordanceRepository.getSlotPickerRepresentations();
            Map currentSelections = keyguardQuickAffordanceRepository.getCurrentSelections();
            printWriter.println("Slots & selections:");
            for (KeyguardSlotPickerRepresentation keyguardSlotPickerRepresentation : slotPickerRepresentations) {
                String str = keyguardSlotPickerRepresentation.id;
                List list = (List) currentSelections.get(str);
                printWriter.println("    " + str + ((list == null || list.isEmpty()) ? " is empty" : ": ".concat(CollectionsKt.joinToString$default(list, ", ", null, null, null, 62))) + " (capacity = " + keyguardSlotPickerRepresentation.maxSelectedAffordances + ")");
            }
            printWriter.println("Available affordances on device:");
            for (KeyguardQuickAffordanceConfig keyguardQuickAffordanceConfig : keyguardQuickAffordanceRepository.configsByAffordanceId.values()) {
                printWriter.println(MotionLayout$$ExternalSyntheticOutline0.m("    ", keyguardQuickAffordanceConfig.getKey(), " (\"", keyguardQuickAffordanceConfig.pickerName(), "\")"));
            }
        }
    }

    public KeyguardQuickAffordanceRepository(Context context, CoroutineScope coroutineScope, KeyguardQuickAffordanceLocalUserSelectionManager keyguardQuickAffordanceLocalUserSelectionManager, KeyguardQuickAffordanceRemoteUserSelectionManager keyguardQuickAffordanceRemoteUserSelectionManager, UserTracker userTracker, KeyguardQuickAffordanceLegacySettingSyncer keyguardQuickAffordanceLegacySettingSyncer, Set set, DumpManager dumpManager, final UserHandle userHandle) {
        this.appContext = context;
        this.scope = coroutineScope;
        this.localUserSelectionManager = keyguardQuickAffordanceLocalUserSelectionManager;
        this.remoteUserSelectionManager = keyguardQuickAffordanceRemoteUserSelectionManager;
        this.userTracker = userTracker;
        Set set2 = set;
        int mapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(set2, 10));
        LinkedHashMap linkedHashMap = new LinkedHashMap(mapCapacity < 16 ? 16 : mapCapacity);
        for (Object obj : set2) {
            linkedHashMap.put(((KeyguardQuickAffordanceConfig) obj).getKey(), obj);
        }
        this.configsByAffordanceId = linkedHashMap;
        final Flow distinctUntilChanged = FlowKt.distinctUntilChanged(FlowConflatedKt.conflatedCallbackFlow(new KeyguardQuickAffordanceRepository$userId$1(this, null)));
        Flow flow = new Flow() { // from class: com.android.systemui.keyguard.data.repository.KeyguardQuickAffordanceRepository$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.data.repository.KeyguardQuickAffordanceRepository$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ UserHandle $userHandle$inlined;
                public final /* synthetic */ KeyguardQuickAffordanceRepository this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.data.repository.KeyguardQuickAffordanceRepository$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, UserHandle userHandle, KeyguardQuickAffordanceRepository keyguardQuickAffordanceRepository) {
                    this.$this_unsafeFlow = flowCollector;
                    this.$userHandle$inlined = userHandle;
                    this.this$0 = keyguardQuickAffordanceRepository;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof com.android.systemui.keyguard.data.repository.KeyguardQuickAffordanceRepository$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.keyguard.data.repository.KeyguardQuickAffordanceRepository$special$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.data.repository.KeyguardQuickAffordanceRepository$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.data.repository.KeyguardQuickAffordanceRepository$special$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.data.repository.KeyguardQuickAffordanceRepository$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L52
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.lang.Number r5 = (java.lang.Number) r5
                        int r5 = r5.intValue()
                        android.os.UserHandle r6 = r4.$userHandle$inlined
                        int r6 = r6.getIdentifier()
                        com.android.systemui.keyguard.data.repository.KeyguardQuickAffordanceRepository r2 = r4.this$0
                        if (r6 != r5) goto L45
                        com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceLocalUserSelectionManager r5 = r2.localUserSelectionManager
                        goto L47
                    L45:
                        com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceRemoteUserSelectionManager r5 = r2.remoteUserSelectionManager
                    L47:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L52
                        return r1
                    L52:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.data.repository.KeyguardQuickAffordanceRepository$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector, userHandle, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        };
        CoroutineScope coroutineScope2 = this.scope;
        StartedEagerly startedEagerly = SharingStarted.Companion.Eagerly;
        ReadonlyStateFlow stateIn = FlowKt.stateIn(flow, coroutineScope2, startedEagerly, this.localUserSelectionManager);
        this.selectionManager = stateIn;
        this.selections = FlowKt.stateIn(FlowKt.transformLatest(stateIn, new KeyguardQuickAffordanceRepository$special$$inlined$flatMapLatest$1(null, set)), this.scope, startedEagerly, MapsKt.emptyMap());
        KeyguardQuickAffordanceLegacySettingSyncer.startSyncing$default(keyguardQuickAffordanceLegacySettingSyncer);
        DumpManager.registerDumpable$default(dumpManager, "KeyguardQuickAffordances", new Dumpster());
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0080 -> B:10:0x0081). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getAffordancePickerRepresentations(kotlin.coroutines.jvm.internal.ContinuationImpl r13) {
        /*
            Method dump skipped, instructions count: 315
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.data.repository.KeyguardQuickAffordanceRepository.getAffordancePickerRepresentations(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final Map getCurrentSelections() {
        return ((KeyguardQuickAffordanceSelectionManager) ((StateFlowImpl) this.selectionManager.$$delegate_0).getValue()).getSelections();
    }

    public final List getSlotPickerRepresentations() {
        int length;
        String[] stringArray = this.appContext.getResources().getStringArray(R.array.config_keyguardQuickAffordanceSlots);
        if (this.appContext.getResources().getConfiguration().getLayoutDirection() == 1 && (length = (stringArray.length / 2) - 1) >= 0) {
            int length2 = stringArray.length - 1;
            IntProgressionIterator it = new IntRange(0, length, 1).iterator();
            while (it.hasNext) {
                int nextInt = it.nextInt();
                String str = stringArray[nextInt];
                stringArray[nextInt] = stringArray[length2];
                stringArray[length2] = str;
                length2--;
            }
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ArrayList arrayList = new ArrayList();
        for (String str2 : stringArray) {
            Intrinsics.checkNotNull(str2);
            List split$default = StringsKt.split$default(str2, new String[]{":"}, 0, 6);
            if (split$default.size() != 2) {
                throw new IllegalStateException("Check failed.");
            }
            Pair pair = new Pair((String) split$default.get(0), Integer.valueOf(Integer.parseInt((String) split$default.get(1))));
            String str3 = (String) pair.component1();
            int intValue = ((Number) pair.component2()).intValue();
            if (linkedHashSet.contains(str3)) {
                throw new IllegalStateException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Duplicate slot \"", str3, "\"!").toString());
            }
            linkedHashSet.add(str3);
            arrayList.add(new KeyguardSlotPickerRepresentation(str3, intValue));
        }
        return arrayList;
    }
}
