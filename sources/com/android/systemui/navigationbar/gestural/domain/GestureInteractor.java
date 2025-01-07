package com.android.systemui.navigationbar.gestural.domain;

import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.app.tracing.coroutines.CoroutineTraceName;
import com.android.app.tracing.coroutines.flow.FlowExtKt$withTraceName$1;
import com.android.systemui.navigationbar.gestural.data.respository.GestureRepositoryImpl;
import com.android.systemui.navigationbar.gestural.domain.TaskMatcher;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.shared.system.TaskStackChangeListeners;
import com.android.systemui.util.kotlin.FlowKt$emitOnStart$1;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlin.collections.EmptySet;
import kotlin.coroutines.CoroutineContext;
import kotlin.enums.EnumEntriesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GestureInteractor {
    public final StateFlowImpl _localGestureBlockedMatchers;
    public final ActivityManagerWrapper activityManagerWrapper;
    public final CoroutineContext backgroundCoroutineContext;
    public final GestureRepositoryImpl gestureRepository;
    public final CoroutineScope scope;
    public final TaskStackChangeListeners taskStackChangeListeners;
    public final FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 topActivityBlocked;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Scope {
        public static final /* synthetic */ Scope[] $VALUES;
        public static final Scope Global;
        public static final Scope Local;

        static {
            Scope scope = new Scope("Local", 0);
            Local = scope;
            Scope scope2 = new Scope("Global", 1);
            Global = scope2;
            Scope[] scopeArr = {scope, scope2};
            $VALUES = scopeArr;
            EnumEntriesKt.enumEntries(scopeArr);
        }

        public static Scope valueOf(String str) {
            return (Scope) Enum.valueOf(Scope.class, str);
        }

        public static Scope[] values() {
            return (Scope[]) $VALUES.clone();
        }
    }

    public GestureInteractor(GestureRepositoryImpl gestureRepositoryImpl, CoroutineDispatcher coroutineDispatcher, CoroutineContext coroutineContext, CoroutineScope coroutineScope, ActivityManagerWrapper activityManagerWrapper, TaskStackChangeListeners taskStackChangeListeners) {
        this.gestureRepository = gestureRepositoryImpl;
        this.backgroundCoroutineContext = coroutineContext;
        this.scope = coroutineScope;
        this.activityManagerWrapper = activityManagerWrapper;
        this.taskStackChangeListeners = taskStackChangeListeners;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(EmptySet.INSTANCE);
        this._localGestureBlockedMatchers = MutableStateFlow;
        Flow conflatedCallbackFlow = FlowConflatedKt.conflatedCallbackFlow(new GestureInteractor$_topActivity$1(this, null));
        if (coroutineDispatcher.get(CoroutineTraceName.Key) != null) {
            throw new ClassCastException();
        }
        if (coroutineDispatcher.get(CoroutineName.Key) != null) {
            throw new ClassCastException();
        }
        CoroutineDispatcher coroutineDispatcher2 = (CoroutineDispatcher) coroutineDispatcher.get(CoroutineDispatcher.Key);
        String simpleName = coroutineDispatcher2 != null ? coroutineDispatcher2.getClass().getSimpleName() : null;
        this.topActivityBlocked = FlowKt.combine(FlowKt.distinctUntilChanged(FlowKt.mapLatest(new GestureInteractor$_topActivity$2(this, null), new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new FlowKt$emitOnStart$1(2, null), new FlowExtKt$withTraceName$1(FlowKt.flowOn(conflatedCallbackFlow, coroutineDispatcher), DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("flowOn(", simpleName == null ? coroutineDispatcher.getClass().getSimpleName() : simpleName, ")"))))), gestureRepositoryImpl._gestureBlockedMatchers, new ReadonlyStateFlow(MutableStateFlow), new GestureInteractor$topActivityBlocked$1(4, null));
    }

    public final void addGestureBlockedMatcher(TaskMatcher taskMatcher, Scope scope) {
        BuildersKt.launch$default(this.scope, null, null, new GestureInteractor$addGestureBlockedMatcher$1(scope, this, taskMatcher, null), 3);
    }

    public final void removeGestureBlockedMatcher(TaskMatcher.TopActivityType topActivityType) {
        BuildersKt.launch$default(this.scope, null, null, new GestureInteractor$removeGestureBlockedMatcher$1(Scope.Global, this, topActivityType, null), 3);
    }
}
