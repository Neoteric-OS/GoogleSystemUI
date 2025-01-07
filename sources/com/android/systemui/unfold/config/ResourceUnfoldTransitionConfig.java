package com.android.systemui.unfold.config;

import android.content.res.Resources;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ResourceUnfoldTransitionConfig {
    public final Lazy isEnabled$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig$isEnabled$2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Boolean.valueOf(Resources.getSystem().getBoolean(Resources.getSystem().getIdentifier("config_unfoldTransitionEnabled", "bool", "android")));
        }
    });
    public final Lazy isHingeAngleEnabled$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig$isHingeAngleEnabled$2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Boolean.valueOf(Resources.getSystem().getBoolean(Resources.getSystem().getIdentifier("config_unfoldTransitionHingeAngle", "bool", "android")));
        }
    });
    public final Lazy isHapticsEnabled$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig$isHapticsEnabled$2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Boolean.valueOf(Resources.getSystem().getBoolean(Resources.getSystem().getIdentifier("config_unfoldTransitionHapticsEnabled", "bool", "android")));
        }
    });
    public final Lazy halfFoldedTimeoutMillis$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig$halfFoldedTimeoutMillis$2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Integer.valueOf(Resources.getSystem().getInteger(Resources.getSystem().getIdentifier("config_unfoldTransitionHalfFoldedTimeout", "integer", "android")));
        }
    });
}
