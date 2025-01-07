package com.android.systemui.keyboard.shortcut.data.repository;

import android.content.Context;
import android.hardware.input.InputManager;
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.KeyboardShortcutGroup;
import com.android.systemui.keyboard.shortcut.data.source.AppCategoriesShortcutsSource;
import com.android.systemui.keyboard.shortcut.data.source.CurrentAppShortcutsSource;
import com.android.systemui.keyboard.shortcut.data.source.InputShortcutsSource;
import com.android.systemui.keyboard.shortcut.data.source.KeyboardShortcutGroupsSource;
import com.android.systemui.keyboard.shortcut.data.source.MultitaskingShortcutsSource;
import com.android.systemui.keyboard.shortcut.data.source.SystemShortcutsSource;
import com.android.systemui.keyboard.shortcut.shared.model.ShortcutCategoryType;
import com.android.systemui.keyboard.shortcut.shared.model.ShortcutKey;
import java.util.List;
import java.util.Locale;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ShortcutHelperCategoriesRepository {
    public static final List SUPPORTED_MODIFIERS = CollectionsKt__CollectionsKt.listOf(65536, 4096, 2, 1, 4, 8);
    public final ShortcutHelperCategoriesRepository$special$$inlined$map$1 activeInputDevice;
    public final AppCategoriesShortcutsSource appCategoriesShortcutsSource;
    public final CoroutineDispatcher backgroundDispatcher;
    public final ReadonlyStateFlow categories;
    public final Context context;
    public final CurrentAppShortcutsSource currentAppShortcutsSource;
    public final InputManager inputManager;
    public final InputShortcutsSource inputShortcutsSource;
    public final MultitaskingShortcutsSource multitaskingShortcutsSource;
    public final List sources;
    public final SystemShortcutsSource systemShortcutsSource;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class InternalGroupsSource {
        public final boolean isTrusted;
        public final KeyboardShortcutGroupsSource source;
        public final Lambda typeProvider;

        /* JADX WARN: Multi-variable type inference failed */
        public InternalGroupsSource(KeyboardShortcutGroupsSource keyboardShortcutGroupsSource, boolean z, Function1 function1) {
            this.source = keyboardShortcutGroupsSource;
            this.isTrusted = z;
            this.typeProvider = (Lambda) function1;
        }
    }

    public ShortcutHelperCategoriesRepository(Context context, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, SystemShortcutsSource systemShortcutsSource, MultitaskingShortcutsSource multitaskingShortcutsSource, AppCategoriesShortcutsSource appCategoriesShortcutsSource, InputShortcutsSource inputShortcutsSource, CurrentAppShortcutsSource currentAppShortcutsSource, InputManager inputManager, ShortcutHelperStateRepository shortcutHelperStateRepository) {
        this.context = context;
        this.backgroundDispatcher = coroutineDispatcher;
        this.inputManager = inputManager;
        this.sources = CollectionsKt__CollectionsKt.listOf(new InternalGroupsSource(systemShortcutsSource, true, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository$sources$1
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return ShortcutCategoryType.System.INSTANCE;
            }
        }), new InternalGroupsSource(multitaskingShortcutsSource, true, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository$sources$2
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return ShortcutCategoryType.MultiTasking.INSTANCE;
            }
        }), new InternalGroupsSource(appCategoriesShortcutsSource, true, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository$sources$3
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return ShortcutCategoryType.AppCategories.INSTANCE;
            }
        }), new InternalGroupsSource(inputShortcutsSource, false, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository$sources$4
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return ShortcutCategoryType.InputMethodEditor.INSTANCE;
            }
        }), new InternalGroupsSource(currentAppShortcutsSource, false, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository$sources$5
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                List list = (List) obj;
                ShortcutHelperCategoriesRepository.this.getClass();
                if (list.isEmpty()) {
                    return null;
                }
                return new ShortcutCategoryType.CurrentApp(((KeyboardShortcutGroup) list.get(0)).getPackageName().toString());
            }
        }));
        final StateFlowImpl stateFlowImpl = shortcutHelperStateRepository.state;
        final int i = 0;
        final Flow flow = new Flow() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ ShortcutHelperCategoriesRepository this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, ShortcutHelperCategoriesRepository shortcutHelperCategoriesRepository) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = shortcutHelperCategoriesRepository;
                }

                /* JADX WARN: Removed duplicated region for block: B:20:0x0068 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:21:0x003b  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r8, kotlin.coroutines.Continuation r9) {
                    /*
                        r7 = this;
                        boolean r0 = r9 instanceof com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r9
                        com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository$special$$inlined$map$1$2$1 r0 = (com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository$special$$inlined$map$1$2$1 r0 = new com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository$special$$inlined$map$1$2$1
                        r0.<init>(r9)
                    L18:
                        java.lang.Object r9 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 2
                        r4 = 1
                        r5 = 0
                        if (r2 == 0) goto L3b
                        if (r2 == r4) goto L33
                        if (r2 != r3) goto L2b
                        kotlin.ResultKt.throwOnFailure(r9)
                        goto L69
                    L2b:
                        java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                        java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                        r7.<init>(r8)
                        throw r7
                    L33:
                        java.lang.Object r7 = r0.L$0
                        kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
                        kotlin.ResultKt.throwOnFailure(r9)
                        goto L5b
                    L3b:
                        kotlin.ResultKt.throwOnFailure(r9)
                        com.android.systemui.keyboard.shortcut.shared.model.ShortcutHelperState r8 = (com.android.systemui.keyboard.shortcut.shared.model.ShortcutHelperState) r8
                        boolean r9 = r8 instanceof com.android.systemui.keyboard.shortcut.shared.model.ShortcutHelperState.Active
                        kotlinx.coroutines.flow.FlowCollector r2 = r7.$this_unsafeFlow
                        if (r9 == 0) goto L5d
                        com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository r7 = r7.this$0
                        kotlinx.coroutines.CoroutineDispatcher r9 = r7.backgroundDispatcher
                        com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository$activeInputDevice$1$1 r6 = new com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository$activeInputDevice$1$1
                        r6.<init>(r7, r8, r5)
                        r0.L$0 = r2
                        r0.label = r4
                        java.lang.Object r9 = kotlinx.coroutines.BuildersKt.withContext(r9, r6, r0)
                        if (r9 != r1) goto L5a
                        return r1
                    L5a:
                        r7 = r2
                    L5b:
                        r2 = r7
                        goto L5e
                    L5d:
                        r9 = r5
                    L5e:
                        r0.L$0 = r5
                        r0.label = r3
                        java.lang.Object r7 = r2.emit(r9, r0)
                        if (r7 != r1) goto L69
                        return r1
                    L69:
                        kotlin.Unit r7 = kotlin.Unit.INSTANCE
                        return r7
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        ((StateFlowImpl) stateFlowImpl).collect(new AnonymousClass2(flowCollector, this), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                    default:
                        Object collect = ((ShortcutHelperCategoriesRepository$special$$inlined$map$1) stateFlowImpl).collect(new ShortcutHelperCategoriesRepository$special$$inlined$map$2$2(flowCollector, this), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                }
            }
        };
        final int i2 = 1;
        this.categories = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ ShortcutHelperCategoriesRepository this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, ShortcutHelperCategoriesRepository shortcutHelperCategoriesRepository) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = shortcutHelperCategoriesRepository;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r9 instanceof com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r9
                        com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository$special$$inlined$map$1$2$1 r0 = (com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository$special$$inlined$map$1$2$1 r0 = new com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository$special$$inlined$map$1$2$1
                        r0.<init>(r9)
                    L18:
                        java.lang.Object r9 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 2
                        r4 = 1
                        r5 = 0
                        if (r2 == 0) goto L3b
                        if (r2 == r4) goto L33
                        if (r2 != r3) goto L2b
                        kotlin.ResultKt.throwOnFailure(r9)
                        goto L69
                    L2b:
                        java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                        java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                        r7.<init>(r8)
                        throw r7
                    L33:
                        java.lang.Object r7 = r0.L$0
                        kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
                        kotlin.ResultKt.throwOnFailure(r9)
                        goto L5b
                    L3b:
                        kotlin.ResultKt.throwOnFailure(r9)
                        com.android.systemui.keyboard.shortcut.shared.model.ShortcutHelperState r8 = (com.android.systemui.keyboard.shortcut.shared.model.ShortcutHelperState) r8
                        boolean r9 = r8 instanceof com.android.systemui.keyboard.shortcut.shared.model.ShortcutHelperState.Active
                        kotlinx.coroutines.flow.FlowCollector r2 = r7.$this_unsafeFlow
                        if (r9 == 0) goto L5d
                        com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository r7 = r7.this$0
                        kotlinx.coroutines.CoroutineDispatcher r9 = r7.backgroundDispatcher
                        com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository$activeInputDevice$1$1 r6 = new com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository$activeInputDevice$1$1
                        r6.<init>(r7, r8, r5)
                        r0.L$0 = r2
                        r0.label = r4
                        java.lang.Object r9 = kotlinx.coroutines.BuildersKt.withContext(r9, r6, r0)
                        if (r9 != r1) goto L5a
                        return r1
                    L5a:
                        r7 = r2
                    L5b:
                        r2 = r7
                        goto L5e
                    L5d:
                        r9 = r5
                    L5e:
                        r0.L$0 = r5
                        r0.label = r3
                        java.lang.Object r7 = r2.emit(r9, r0)
                        if (r7 != r1) goto L69
                        return r1
                    L69:
                        kotlin.Unit r7 = kotlin.Unit.INSTANCE
                        return r7
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperCategoriesRepository$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        ((StateFlowImpl) flow).collect(new AnonymousClass2(flowCollector, this), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                    default:
                        Object collect = ((ShortcutHelperCategoriesRepository$special$$inlined$map$1) flow).collect(new ShortcutHelperCategoriesRepository$special$$inlined$map$2$2(flowCollector, this), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                }
            }
        }, coroutineScope, SharingStarted.Companion.Lazily, EmptyList.INSTANCE);
    }

    public final ShortcutKey toShortcutKey(KeyCharacterMap keyCharacterMap, int i, char c) {
        Integer num = (Integer) ShortcutHelperKeys.keyIcons.get(Integer.valueOf(i));
        if (num != null) {
            return new ShortcutKey.Icon(num.intValue());
        }
        if (Intrinsics.compare(c, 0) > 0) {
            return new ShortcutKey.Text(String.valueOf(c).toUpperCase(Locale.ROOT));
        }
        Function1 function1 = (Function1) ShortcutHelperKeys.specialKeyLabels.get(Integer.valueOf(i));
        if (function1 != null) {
            return new ShortcutKey.Text((String) function1.invoke(this.context));
        }
        char displayLabel = keyCharacterMap.getDisplayLabel(i);
        if (displayLabel != 0) {
            return new ShortcutKey.Text(String.valueOf(displayLabel));
        }
        Log.wtf("SHCategoriesRepo", "Couldn't find label or icon for key: " + i);
        return null;
    }
}
