package com.android.systemui.keyguard.ui.composable;

import com.android.systemui.keyguard.domain.interactor.KeyguardClockInteractor;
import com.android.systemui.keyguard.ui.composable.blueprint.ComposableLockscreenSceneBlueprint;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$33;
import java.util.LinkedHashMap;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LockscreenContent {
    public final Lazy blueprintByBlueprintId$delegate;
    public final Set blueprints;
    public final KeyguardClockInteractor clockInteractor;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$33 viewModelFactory;

    public LockscreenContent(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$33 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$33, Set set, KeyguardClockInteractor keyguardClockInteractor) {
        this.blueprints = set;
        LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.keyguard.ui.composable.LockscreenContent$blueprintByBlueprintId$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Set set2 = LockscreenContent.this.blueprints;
                int mapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(set2, 10));
                if (mapCapacity < 16) {
                    mapCapacity = 16;
                }
                LinkedHashMap linkedHashMap = new LinkedHashMap(mapCapacity);
                for (Object obj : set2) {
                    linkedHashMap.put(((ComposableLockscreenSceneBlueprint) obj).getId(), obj);
                }
                return linkedHashMap;
            }
        });
    }
}
