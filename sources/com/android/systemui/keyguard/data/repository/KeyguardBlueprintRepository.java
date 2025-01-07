package com.android.systemui.keyguard.data.repository;

import android.os.Handler;
import android.util.Log;
import com.android.systemui.keyguard.shared.model.KeyguardBlueprint;
import com.android.systemui.keyguard.ui.view.layout.blueprints.transitions.IntraBlueprintTransition;
import com.android.systemui.util.ThreadAssert;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeMap;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardBlueprintRepository {

    /* renamed from: assert, reason: not valid java name */
    public final ThreadAssert f36assert;
    public final StateFlowImpl blueprint;
    public final TreeMap blueprintIdMap;
    public final Handler handler;
    public final SharedFlowImpl refreshTransition;
    public IntraBlueprintTransition.Config targetTransitionConfig;

    public KeyguardBlueprintRepository(Set set, Handler handler, ThreadAssert threadAssert) {
        this.handler = handler;
        this.f36assert = threadAssert;
        TreeMap treeMap = new TreeMap();
        Set set2 = set;
        int mapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(set2, 10));
        LinkedHashMap linkedHashMap = new LinkedHashMap(mapCapacity < 16 ? 16 : mapCapacity);
        for (Object obj : set2) {
            linkedHashMap.put(((KeyguardBlueprint) obj).getId(), obj);
        }
        treeMap.putAll(linkedHashMap);
        this.blueprintIdMap = treeMap;
        Object obj2 = treeMap.get("default");
        Intrinsics.checkNotNull(obj2);
        this.blueprint = StateFlowKt.MutableStateFlow(obj2);
        this.refreshTransition = SharedFlowKt.MutableSharedFlow$default(0, 1, null, 5);
    }

    public final boolean applyBlueprint(String str) {
        KeyguardBlueprint keyguardBlueprint = (KeyguardBlueprint) this.blueprintIdMap.get(str);
        if (keyguardBlueprint == null) {
            Log.e("KeyguardBlueprintRepository", "Could not find blueprint with id: " + str + ". Perhaps it was not added to KeyguardBlueprintModule?");
            return false;
        }
        StateFlowImpl stateFlowImpl = this.blueprint;
        if (keyguardBlueprint.equals(stateFlowImpl.getValue())) {
            return true;
        }
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, keyguardBlueprint);
        return true;
    }

    public static /* synthetic */ void getTargetTransitionConfig$annotations() {
    }
}
