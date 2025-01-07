package com.android.systemui.unfold;

import com.android.systemui.unfold.util.NaturalRotationUnfoldProgressProvider;
import com.android.systemui.unfold.util.ScopedUnfoldTransitionProgressProvider;
import java.util.function.Function;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UnfoldTransitionModule$providesFoldStateLogger$1 implements Function {
    public final /* synthetic */ int $r8$classId;
    public static final UnfoldTransitionModule$providesFoldStateLogger$1 INSTANCE$1 = new UnfoldTransitionModule$providesFoldStateLogger$1(1);
    public static final UnfoldTransitionModule$providesFoldStateLogger$1 INSTANCE = new UnfoldTransitionModule$providesFoldStateLogger$1(0);

    public /* synthetic */ UnfoldTransitionModule$providesFoldStateLogger$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                FoldStateLoggingProviderImpl foldStateLoggingProviderImpl = (FoldStateLoggingProviderImpl) obj;
                Intrinsics.checkNotNull(foldStateLoggingProviderImpl);
                return new FoldStateLogger(foldStateLoggingProviderImpl);
            default:
                return new ScopedUnfoldTransitionProgressProvider((NaturalRotationUnfoldProgressProvider) obj);
        }
    }
}
