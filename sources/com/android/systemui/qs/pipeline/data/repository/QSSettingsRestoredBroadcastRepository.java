package com.android.systemui.qs.pipeline.data.repository;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.UserHandle;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.qs.pipeline.data.model.RestoreData;
import com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.util.kotlin.FlowKt$emitOnStart$1;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.EmptySet;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__ErrorsKt$catch$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__MergeKt;
import kotlinx.coroutines.flow.FlowKt__MergeKt$flattenConcat$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.sync.MutexImpl;
import kotlinx.coroutines.sync.MutexKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSSettingsRestoredBroadcastRepository implements QSSettingsRestoredRepository {
    public static final Companion Companion = null;
    public static final IntentFilter INTENT_FILTER = new IntentFilter("android.os.action.SETTING_RESTORED");
    public static final List requiredExtras = CollectionsKt__CollectionsKt.listOf("setting_name", "previous_value", "new_value");
    public final DeviceProvisionedController deviceProvisionedController;
    public final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 onUserSetupChangedForSomeUser;
    public final FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 restoreData;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static final void access$validateIntent(Intent intent) {
            Companion companion = QSSettingsRestoredBroadcastRepository.Companion;
            for (String str : QSSettingsRestoredBroadcastRepository.requiredExtras) {
                if (!intent.hasExtra(str)) {
                    throw new IllegalStateException(intent + " doesn't have " + str);
                }
            }
        }
    }

    public QSSettingsRestoredBroadcastRepository(BroadcastDispatcher broadcastDispatcher, DeviceProvisionedController deviceProvisionedController, QSPipelineLogger qSPipelineLogger, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher) {
        int i = 0;
        this.deviceProvisionedController = deviceProvisionedController;
        final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new FlowKt$emitOnStart$1(2, null), FlowConflatedKt.conflatedCallbackFlow(new QSSettingsRestoredBroadcastRepository$onUserSetupChangedForSomeUser$1(this, null)));
        final MutexImpl Mutex$default = MutexKt.Mutex$default();
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        final QSSettingsRestoredBroadcastRepository$restoreData$lambda$8$$inlined$filter$1 qSSettingsRestoredBroadcastRepository$restoreData$lambda$8$$inlined$filter$1 = new QSSettingsRestoredBroadcastRepository$restoreData$lambda$8$$inlined$filter$1(BroadcastDispatcher.broadcastFlow$default(broadcastDispatcher, INTENT_FILTER, UserHandle.ALL, new Function2() { // from class: com.android.systemui.qs.pipeline.data.repository.QSSettingsRestoredBroadcastRepository$restoreData$1$restoresFromTwoBroadcasts$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return new Pair((Intent) obj, Integer.valueOf(((BroadcastReceiver) obj2).getSendingUserId()));
            }
        }, 12));
        final int i2 = 1;
        FlowKt__ErrorsKt$catch$$inlined$unsafeFlow$1 flowKt__ErrorsKt$catch$$inlined$unsafeFlow$1 = new FlowKt__ErrorsKt$catch$$inlined$unsafeFlow$1(new Flow() { // from class: com.android.systemui.qs.pipeline.data.repository.QSSettingsRestoredBroadcastRepository$restoreData$lambda$8$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.qs.pipeline.data.repository.QSSettingsRestoredBroadcastRepository$restoreData$lambda$8$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ Map $firstIntent$inlined;
                public final /* synthetic */ MutexImpl $mutex$inlined;
                public final /* synthetic */ QSSettingsRestoredBroadcastRepository $this_run$inlined;
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.qs.pipeline.data.repository.QSSettingsRestoredBroadcastRepository$restoreData$lambda$8$$inlined$map$1$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    Object L$1;
                    Object L$2;
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

                public AnonymousClass2(FlowCollector flowCollector, MutexImpl mutexImpl, Map map, QSSettingsRestoredBroadcastRepository qSSettingsRestoredBroadcastRepository) {
                    this.$this_unsafeFlow = flowCollector;
                    this.$mutex$inlined = mutexImpl;
                    this.$firstIntent$inlined = map;
                    this.$this_run$inlined = qSSettingsRestoredBroadcastRepository;
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Removed duplicated region for block: B:23:0x0075 A[Catch: all -> 0x009b, TRY_ENTER, TryCatch #0 {all -> 0x009b, blocks: (B:19:0x005e, B:20:0x006d, B:23:0x0075, B:26:0x008f, B:31:0x009d, B:32:0x00a5, B:34:0x00ab, B:36:0x00bb, B:37:0x00cc, B:39:0x00d2, B:41:0x00f0), top: B:18:0x005e }] */
                /* JADX WARN: Removed duplicated region for block: B:30:0x009d A[EDGE_INSN: B:30:0x009d->B:31:0x009d BREAK  A[LOOP:0: B:20:0x006d->B:28:0x006d], SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:34:0x00ab A[Catch: all -> 0x009b, LOOP:1: B:32:0x00a5->B:34:0x00ab, LOOP_END, TryCatch #0 {all -> 0x009b, blocks: (B:19:0x005e, B:20:0x006d, B:23:0x0075, B:26:0x008f, B:31:0x009d, B:32:0x00a5, B:34:0x00ab, B:36:0x00bb, B:37:0x00cc, B:39:0x00d2, B:41:0x00f0), top: B:18:0x005e }] */
                /* JADX WARN: Removed duplicated region for block: B:39:0x00d2 A[Catch: all -> 0x009b, LOOP:2: B:37:0x00cc->B:39:0x00d2, LOOP_END, TryCatch #0 {all -> 0x009b, blocks: (B:19:0x005e, B:20:0x006d, B:23:0x0075, B:26:0x008f, B:31:0x009d, B:32:0x00a5, B:34:0x00ab, B:36:0x00bb, B:37:0x00cc, B:39:0x00d2, B:41:0x00f0), top: B:18:0x005e }] */
                /* JADX WARN: Removed duplicated region for block: B:44:0x0106 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:50:0x0045  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
                /* JADX WARN: Type inference failed for: r9v7, types: [kotlinx.coroutines.sync.Mutex] */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r10, kotlin.coroutines.Continuation r11) {
                    /*
                        Method dump skipped, instructions count: 270
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.pipeline.data.repository.QSSettingsRestoredBroadcastRepository$restoreData$lambda$8$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        Object collect = ((FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1) qSSettingsRestoredBroadcastRepository$restoreData$lambda$8$$inlined$filter$1).collect(new AnonymousClass2(flowCollector, Mutex$default, linkedHashMap, this), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = ((QSSettingsRestoredBroadcastRepository$restoreData$lambda$8$$inlined$filter$1) qSSettingsRestoredBroadcastRepository$restoreData$lambda$8$$inlined$filter$1).collect(new QSSettingsRestoredBroadcastRepository$restoreData$lambda$8$$inlined$mapNotNull$1$2(flowCollector, Mutex$default, linkedHashMap, this), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, new QSSettingsRestoredBroadcastRepository$restoreData$1$restoresFromTwoBroadcasts$4(3, null));
        final int i3 = 0;
        Flow flow = new Flow() { // from class: com.android.systemui.qs.pipeline.data.repository.QSSettingsRestoredBroadcastRepository$restoreData$lambda$8$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.qs.pipeline.data.repository.QSSettingsRestoredBroadcastRepository$restoreData$lambda$8$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ Map $firstIntent$inlined;
                public final /* synthetic */ MutexImpl $mutex$inlined;
                public final /* synthetic */ QSSettingsRestoredBroadcastRepository $this_run$inlined;
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.qs.pipeline.data.repository.QSSettingsRestoredBroadcastRepository$restoreData$lambda$8$$inlined$map$1$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    Object L$1;
                    Object L$2;
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

                public AnonymousClass2(FlowCollector flowCollector, MutexImpl mutexImpl, Map map, QSSettingsRestoredBroadcastRepository qSSettingsRestoredBroadcastRepository) {
                    this.$this_unsafeFlow = flowCollector;
                    this.$mutex$inlined = mutexImpl;
                    this.$firstIntent$inlined = map;
                    this.$this_run$inlined = qSSettingsRestoredBroadcastRepository;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        Method dump skipped, instructions count: 270
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.pipeline.data.repository.QSSettingsRestoredBroadcastRepository$restoreData$lambda$8$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i3) {
                    case 0:
                        Object collect = ((FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1) flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1).collect(new AnonymousClass2(flowCollector, Mutex$default, linkedHashMap, this), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = ((QSSettingsRestoredBroadcastRepository$restoreData$lambda$8$$inlined$filter$1) flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1).collect(new QSSettingsRestoredBroadcastRepository$restoreData$lambda$8$$inlined$mapNotNull$1$2(flowCollector, Mutex$default, linkedHashMap, this), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        int i4 = FlowKt__MergeKt.$r8$clinit;
        this.restoreData = new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.shareIn(FlowKt.buffer$default(FlowKt.flowOn(FlowKt.merge(flowKt__ErrorsKt$catch$$inlined$unsafeFlow$1, new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(new FlowKt__ErrorsKt$catch$$inlined$unsafeFlow$1(new FlowKt__MergeKt$flattenConcat$$inlined$unsafeFlow$1(flow), new QSSettingsRestoredBroadcastRepository$restoreData$1$restoresFromUserSetup$2(3, null)), new QSSettingsRestoredBroadcastRepository$restoreData$1$restoresFromUserSetup$3(qSPipelineLogger, null), i)), coroutineDispatcher), 10), coroutineScope, SharingStarted.Companion.Eagerly, 0), new QSSettingsRestoredBroadcastRepository$restoreData$2(2, qSPipelineLogger, QSPipelineLogger.class, "logSettingsRestored", "logSettingsRestored(Lcom/android/systemui/qs/pipeline/data/model/RestoreData;)V", 4), i);
    }

    public static final RestoreData access$processIntents(QSSettingsRestoredBroadcastRepository qSSettingsRestoredBroadcastRepository, int i, Intent intent, Intent intent2) {
        Pair pair;
        qSSettingsRestoredBroadcastRepository.getClass();
        Companion.access$validateIntent(intent);
        Companion.access$validateIntent(intent2);
        String stringExtra = intent.getStringExtra("setting_name");
        String stringExtra2 = intent2.getStringExtra("setting_name");
        if (Intrinsics.areEqual(stringExtra, "sysui_qs_tiles") && Intrinsics.areEqual(stringExtra2, "qs_auto_tiles")) {
            pair = new Pair(intent, intent2);
        } else {
            if (!Intrinsics.areEqual(stringExtra, "qs_auto_tiles") || !Intrinsics.areEqual(stringExtra2, "sysui_qs_tiles")) {
                throw new IllegalStateException("Wrong intents (" + intent + ", " + intent2 + ")");
            }
            pair = new Pair(intent2, intent);
        }
        Intent intent3 = (Intent) pair.component1();
        Intent intent4 = (Intent) pair.component2();
        String stringExtra3 = intent3.getStringExtra("new_value");
        if (stringExtra3 == null) {
            stringExtra3 = "";
        }
        List tilesList = TilesSettingConverter.toTilesList(stringExtra3);
        String stringExtra4 = intent4.getStringExtra("new_value");
        return new RestoreData(i, tilesList, TilesSettingConverter.toTilesSet(stringExtra4 != null ? stringExtra4 : ""));
    }

    public static final RestoreData access$processSingleIntent(QSSettingsRestoredBroadcastRepository qSSettingsRestoredBroadcastRepository, int i, Intent intent) {
        qSSettingsRestoredBroadcastRepository.getClass();
        Companion.access$validateIntent(intent);
        if (Intrinsics.areEqual(intent.getStringExtra("setting_name"), "sysui_qs_tiles")) {
            String stringExtra = intent.getStringExtra("new_value");
            if (stringExtra == null) {
                stringExtra = "";
            }
            return new RestoreData(i, TilesSettingConverter.toTilesList(stringExtra), EmptySet.INSTANCE);
        }
        throw new IllegalStateException("Single intent restored for user " + i + " is not tiles: " + intent);
    }
}
