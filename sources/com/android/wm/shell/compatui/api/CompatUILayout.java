package com.android.wm.shell.compatui.api;

import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CompatUILayout {
    public final Function4 positionFactory;
    public final Function4 viewBinder;
    public final Function3 viewBuilder;
    public final Function0 viewReleaser;

    public CompatUILayout(Function3 function3, Function4 function4, Function4 function42) {
        AnonymousClass2 anonymousClass2 = new Function0() { // from class: com.android.wm.shell.compatui.api.CompatUILayout.2
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Unit.INSTANCE;
            }
        };
        this.viewBuilder = function3;
        this.viewBinder = function4;
        this.positionFactory = function42;
        this.viewReleaser = anonymousClass2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CompatUILayout)) {
            return false;
        }
        CompatUILayout compatUILayout = (CompatUILayout) obj;
        compatUILayout.getClass();
        return Intrinsics.areEqual(this.viewBuilder, compatUILayout.viewBuilder) && Intrinsics.areEqual(this.viewBinder, compatUILayout.viewBinder) && Intrinsics.areEqual(this.positionFactory, compatUILayout.positionFactory) && Intrinsics.areEqual(this.viewReleaser, compatUILayout.viewReleaser);
    }

    public final int hashCode() {
        return this.viewReleaser.hashCode() + ((this.positionFactory.hashCode() + ((this.viewBinder.hashCode() + ((this.viewBuilder.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(40, Integer.hashCode(10010) * 31, 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "CompatUILayout(zOrder=10010, layoutParamFlags=40, viewBuilder=" + this.viewBuilder + ", viewBinder=" + this.viewBinder + ", positionFactory=" + this.positionFactory + ", viewReleaser=" + this.viewReleaser + ")";
    }
}
