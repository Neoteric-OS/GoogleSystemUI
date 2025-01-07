package com.android.systemui.flags;

import com.android.systemui.Dependency;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RefactorFlag {
    public static final Companion Companion = null;
    public final UnreleasedFlag flagName;
    public final Lazy isEnabled$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.flags.RefactorFlag$isEnabled$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            RefactorFlag.this.getClass();
            FeatureFlags featureFlags = (FeatureFlags) Dependency.sDependency.getDependencyInner(FeatureFlags.class);
            Function1 function1 = RefactorFlag.this.readFlagValue;
            Intrinsics.checkNotNull(featureFlags);
            return Boolean.FALSE;
        }
    });
    public final Function1 readFlagValue;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static RefactorFlag forView$default(final UnreleasedFlag unreleasedFlag) {
            return new RefactorFlag(unreleasedFlag, new Function1() { // from class: com.android.systemui.flags.RefactorFlag$Companion$forView$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return Boolean.FALSE;
                }
            });
        }
    }

    public RefactorFlag(UnreleasedFlag unreleasedFlag, Function1 function1) {
        this.readFlagValue = function1;
    }
}
