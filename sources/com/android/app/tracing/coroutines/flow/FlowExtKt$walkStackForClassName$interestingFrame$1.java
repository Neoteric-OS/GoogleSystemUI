package com.android.app.tracing.coroutines.flow;

import java.lang.StackWalker;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FlowExtKt$walkStackForClassName$interestingFrame$1 implements Function {
    public static final FlowExtKt$walkStackForClassName$interestingFrame$1 INSTANCE = new FlowExtKt$walkStackForClassName$interestingFrame$1();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.app.tracing.coroutines.flow.FlowExtKt$walkStackForClassName$interestingFrame$1$1, reason: invalid class name */
    public final /* synthetic */ class AnonymousClass1 implements Predicate {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        @Override // java.util.function.Predicate
        public final boolean test(Object obj) {
            return !Intrinsics.areEqual(((StackWalker.StackFrame) obj).getFileName(), FlowExt.currentFileName);
        }
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((Stream) obj).filter(AnonymousClass1.INSTANCE).limit(5L).findFirst();
    }
}
