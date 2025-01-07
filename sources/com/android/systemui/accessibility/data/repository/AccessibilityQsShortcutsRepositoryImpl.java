package com.android.systemui.accessibility.data.repository;

import android.util.SparseArray;
import android.view.accessibility.AccessibilityManager;
import com.android.internal.accessibility.AccessibilityShortcutController;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$76;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AccessibilityQsShortcutsRepositoryImpl implements AccessibilityQsShortcutsRepository {
    public static final Map TILE_SPEC_TO_COMPONENT_MAPPING = MapsKt.mapOf(new Pair("color_correction", AccessibilityShortcutController.DALTONIZER_TILE_COMPONENT_NAME), new Pair("inversion", AccessibilityShortcutController.COLOR_INVERSION_TILE_COMPONENT_NAME), new Pair("onehanded", AccessibilityShortcutController.ONE_HANDED_TILE_COMPONENT_NAME), new Pair("reduce_brightness", AccessibilityShortcutController.REDUCE_BRIGHT_COLORS_TILE_SERVICE_COMPONENT_NAME), new Pair("font_scaling", AccessibilityShortcutController.FONT_SIZE_TILE_COMPONENT_NAME), new Pair("hearing_devices", AccessibilityShortcutController.ACCESSIBILITY_HEARING_AIDS_TILE_COMPONENT_NAME));
    public final CoroutineDispatcher backgroundDispatcher;
    public final AccessibilityManager manager;
    public final SparseArray userA11yQsShortcutsRepositories = new SparseArray();
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$76 userA11yQsShortcutsRepositoryFactory;

    public AccessibilityQsShortcutsRepositoryImpl(AccessibilityManager accessibilityManager, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$76 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$76, CoroutineDispatcher coroutineDispatcher) {
        this.manager = accessibilityManager;
        this.userA11yQsShortcutsRepositoryFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$76;
        this.backgroundDispatcher = coroutineDispatcher;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00cb A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /* JADX WARN: Type inference failed for: r9v5, types: [java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object notifyAccessibilityManagerTilesChanged(android.content.Context r10, java.util.List r11, kotlin.coroutines.jvm.internal.ContinuationImpl r12) {
        /*
            r9 = this;
            boolean r0 = r12 instanceof com.android.systemui.accessibility.data.repository.AccessibilityQsShortcutsRepositoryImpl$notifyAccessibilityManagerTilesChanged$1
            if (r0 == 0) goto L13
            r0 = r12
            com.android.systemui.accessibility.data.repository.AccessibilityQsShortcutsRepositoryImpl$notifyAccessibilityManagerTilesChanged$1 r0 = (com.android.systemui.accessibility.data.repository.AccessibilityQsShortcutsRepositoryImpl$notifyAccessibilityManagerTilesChanged$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.accessibility.data.repository.AccessibilityQsShortcutsRepositoryImpl$notifyAccessibilityManagerTilesChanged$1 r0 = new com.android.systemui.accessibility.data.repository.AccessibilityQsShortcutsRepositoryImpl$notifyAccessibilityManagerTilesChanged$1
            r0.<init>(r9, r12)
        L18:
            java.lang.Object r12 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L4d
            if (r2 == r4) goto L34
            if (r2 != r3) goto L2c
            kotlin.ResultKt.throwOnFailure(r12)
            goto Lcc
        L2c:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L34:
            java.lang.Object r9 = r0.L$3
            java.util.List r9 = (java.util.List) r9
            java.lang.Object r10 = r0.L$2
            r11 = r10
            java.util.List r11 = (java.util.List) r11
            java.lang.Object r10 = r0.L$1
            android.content.Context r10 = (android.content.Context) r10
            java.lang.Object r2 = r0.L$0
            com.android.systemui.accessibility.data.repository.AccessibilityQsShortcutsRepositoryImpl r2 = (com.android.systemui.accessibility.data.repository.AccessibilityQsShortcutsRepositoryImpl) r2
            kotlin.ResultKt.throwOnFailure(r12)
            r8 = r12
            r12 = r9
            r9 = r2
            r2 = r8
            goto L6b
        L4d:
            kotlin.ResultKt.throwOnFailure(r12)
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            r0.L$0 = r9
            r0.L$1 = r10
            r0.L$2 = r11
            r0.L$3 = r12
            r0.label = r4
            com.android.systemui.accessibility.data.repository.AccessibilityQsShortcutsRepositoryImpl$getAccessibilityTileServices$2 r2 = new com.android.systemui.accessibility.data.repository.AccessibilityQsShortcutsRepositoryImpl$getAccessibilityTileServices$2
            r2.<init>(r9, r10, r5)
            java.lang.Object r2 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r0, r2)
            if (r2 != r1) goto L6b
            return r1
        L6b:
            java.util.Set r2 = (java.util.Set) r2
            java.util.Iterator r11 = r11.iterator()
        L71:
            boolean r4 = r11.hasNext()
            if (r4 == 0) goto Lb4
            java.lang.Object r4 = r11.next()
            com.android.systemui.qs.pipeline.shared.TileSpec r4 = (com.android.systemui.qs.pipeline.shared.TileSpec) r4
            boolean r6 = r4 instanceof com.android.systemui.qs.pipeline.shared.TileSpec.CustomTileSpec
            if (r6 == 0) goto L91
            com.android.systemui.qs.pipeline.shared.TileSpec$CustomTileSpec r4 = (com.android.systemui.qs.pipeline.shared.TileSpec.CustomTileSpec) r4
            android.content.ComponentName r6 = r4.componentName
            boolean r6 = r2.contains(r6)
            if (r6 == 0) goto L71
            android.content.ComponentName r4 = r4.componentName
            r12.add(r4)
            goto L71
        L91:
            boolean r6 = r4 instanceof com.android.systemui.qs.pipeline.shared.TileSpec.PlatformTileSpec
            if (r6 == 0) goto Lae
            java.util.Map r6 = com.android.systemui.accessibility.data.repository.AccessibilityQsShortcutsRepositoryImpl.TILE_SPEC_TO_COMPONENT_MAPPING
            com.android.systemui.qs.pipeline.shared.TileSpec$PlatformTileSpec r4 = (com.android.systemui.qs.pipeline.shared.TileSpec.PlatformTileSpec) r4
            java.lang.String r7 = r4.spec
            boolean r7 = r6.containsKey(r7)
            if (r7 == 0) goto L71
            java.lang.String r4 = r4.spec
            java.lang.Object r4 = r6.get(r4)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            r12.add(r4)
            goto L71
        Lae:
            com.android.systemui.qs.pipeline.shared.TileSpec$Invalid r6 = com.android.systemui.qs.pipeline.shared.TileSpec.Invalid.INSTANCE
            kotlin.jvm.internal.Intrinsics.areEqual(r4, r6)
            goto L71
        Lb4:
            kotlinx.coroutines.CoroutineDispatcher r11 = r9.backgroundDispatcher
            com.android.systemui.accessibility.data.repository.AccessibilityQsShortcutsRepositoryImpl$notifyAccessibilityManagerTilesChanged$3 r2 = new com.android.systemui.accessibility.data.repository.AccessibilityQsShortcutsRepositoryImpl$notifyAccessibilityManagerTilesChanged$3
            r2.<init>(r9, r10, r12, r5)
            r0.L$0 = r5
            r0.L$1 = r5
            r0.L$2 = r5
            r0.L$3 = r5
            r0.label = r3
            java.lang.Object r9 = kotlinx.coroutines.BuildersKt.withContext(r11, r2, r0)
            if (r9 != r1) goto Lcc
            return r1
        Lcc:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.accessibility.data.repository.AccessibilityQsShortcutsRepositoryImpl.notifyAccessibilityManagerTilesChanged(android.content.Context, java.util.List, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
