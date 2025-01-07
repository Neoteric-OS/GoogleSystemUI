package com.android.systemui.log.table;

import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.util.kotlin.FlowKt;
import com.android.systemui.util.time.SystemClockImpl;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DiffableKt {
    public static final SafeFlow logDiffsForTable(Flow flow, final TableLogBuffer tableLogBuffer, final String str, final Diffable diffable) {
        return FlowKt.pairwiseBy(flow, new DiffableKt$logDiffsForTable$1(1, new Function0() { // from class: com.android.systemui.log.table.DiffableKt$logDiffsForTable$getInitialValue$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                TableLogBuffer tableLogBuffer2 = TableLogBuffer.this;
                String str2 = str;
                final Diffable diffable2 = diffable;
                Function1 function1 = new Function1() { // from class: com.android.systemui.log.table.DiffableKt$logDiffsForTable$getInitialValue$1.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Diffable.this.logFull((TableLogBuffer.TableRowLoggerImpl) obj);
                        return Unit.INSTANCE;
                    }
                };
                synchronized (tableLogBuffer2) {
                    TableLogBuffer.TableRowLoggerImpl tableRowLoggerImpl = tableLogBuffer2.tempRow;
                    ((SystemClockImpl) tableLogBuffer2.systemClock).getClass();
                    tableRowLoggerImpl.timestamp = System.currentTimeMillis();
                    tableRowLoggerImpl.columnPrefix = str2;
                    tableRowLoggerImpl.isInitial = true;
                    function1.invoke(tableRowLoggerImpl);
                }
                return diffable;
            }
        }, Intrinsics.Kotlin.class, "suspendConversion0", "logDiffsForTable$suspendConversion0(Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0), new DiffableKt$logDiffsForTable$2(tableLogBuffer, str, null));
    }

    public static final SafeFlow logDiffsForTable(Flow flow, final TableLogBuffer tableLogBuffer, final String str, final String str2, final boolean z) {
        return FlowKt.pairwiseBy(flow, new DiffableKt$logDiffsForTable$3(1, new Function0() { // from class: com.android.systemui.log.table.DiffableKt$logDiffsForTable$initialValueFun$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                TableLogBuffer.this.logChange(str, str2, z, true);
                return Boolean.valueOf(z);
            }
        }, Intrinsics.Kotlin.class, "suspendConversion0", "logDiffsForTable$suspendConversion0$0(Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0), new DiffableKt$logDiffsForTable$4(tableLogBuffer, str, str2, null));
    }

    public static final SafeFlow logDiffsForTable(Flow flow, final TableLogBuffer tableLogBuffer, final String str, final String str2, final int i) {
        return FlowKt.pairwiseBy(flow, new DiffableKt$logDiffsForTable$5(1, new Function0() { // from class: com.android.systemui.log.table.DiffableKt$logDiffsForTable$initialValueFun$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                TableLogBuffer.this.logChange(str, str2, Integer.valueOf(i), true);
                return Integer.valueOf(i);
            }
        }, Intrinsics.Kotlin.class, "suspendConversion0", "logDiffsForTable$suspendConversion0$1(Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0), new DiffableKt$logDiffsForTable$6(tableLogBuffer, str, str2, null));
    }

    public static final SafeFlow logDiffsForTable(Flow flow, final TableLogBuffer tableLogBuffer, final String str, final Integer num) {
        return FlowKt.pairwiseBy(flow, new DiffableKt$logDiffsForTable$7(1, new Function0() { // from class: com.android.systemui.log.table.DiffableKt$logDiffsForTable$initialValueFun$3
            final /* synthetic */ String $columnPrefix = "Repo";

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                TableLogBuffer.this.logChange(this.$columnPrefix, str, num, true);
                return num;
            }
        }, Intrinsics.Kotlin.class, "suspendConversion0", "logDiffsForTable$suspendConversion0$2(Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0), new DiffableKt$logDiffsForTable$8(tableLogBuffer, "Repo", str, null));
    }

    public static final SafeFlow logDiffsForTable(Flow flow, final TableLogBuffer tableLogBuffer, final String str, final String str2, final String str3) {
        return FlowKt.pairwiseBy(flow, new DiffableKt$logDiffsForTable$9(1, new Function0() { // from class: com.android.systemui.log.table.DiffableKt$logDiffsForTable$initialValueFun$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                TableLogBuffer.this.logChange(str, str2, str3, true);
                return str3;
            }
        }, Intrinsics.Kotlin.class, "suspendConversion0", "logDiffsForTable$suspendConversion0$3(Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0), new DiffableKt$logDiffsForTable$10(tableLogBuffer, str, str2, null));
    }

    public static final SafeFlow logDiffsForTable(Flow flow, final TableLogBuffer tableLogBuffer, final String str, final String str2, final EmptyList emptyList) {
        return FlowKt.pairwiseBy(flow, new DiffableKt$logDiffsForTable$11(1, new Function0() { // from class: com.android.systemui.log.table.DiffableKt$logDiffsForTable$initialValueFun$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                TableLogBuffer.this.logChange(str, str2, emptyList.toString(), true);
                return emptyList;
            }
        }, Intrinsics.Kotlin.class, "suspendConversion0", "logDiffsForTable$suspendConversion0$4(Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0), new DiffableKt$logDiffsForTable$12(tableLogBuffer, str, str2, null));
    }
}
