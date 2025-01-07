package com.android.systemui.deviceentry.data.repository;

import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.plugins.log.TableLogBufferBase;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.IntProgressionIterator;
import kotlin.ranges.IntRange;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.internal.CombineKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DeviceEntryFaceAuthRepositoryKt {
    /* JADX WARN: Type inference failed for: r1v5, types: [com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryKt$andAllFlows$$inlined$combine$1] */
    public static final DeviceEntryFaceAuthRepositoryKt$andAllFlows$$inlined$combine$1 access$andAllFlows(final List list, final String str, final TableLogBuffer tableLogBuffer) {
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add((Flow) ((Pair) it.next()).getFirst());
        }
        final Flow[] flowArr = (Flow[]) CollectionsKt.toList(arrayList).toArray(new Flow[0]);
        return new Flow() { // from class: com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryKt$andAllFlows$$inlined$combine$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryKt$andAllFlows$$inlined$combine$1$3, reason: invalid class name */
            public final class AnonymousClass3 extends SuspendLambda implements Function3 {
                final /* synthetic */ String $combinedLoggingInfo$inlined;
                final /* synthetic */ TableLogBuffer $tableLogBuffer$inlined;
                final /* synthetic */ List $this_andAllFlows$inlined;
                private /* synthetic */ Object L$0;
                /* synthetic */ Object L$1;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass3(Continuation continuation, TableLogBuffer tableLogBuffer, String str, List list) {
                    super(3, continuation);
                    this.$tableLogBuffer$inlined = tableLogBuffer;
                    this.$combinedLoggingInfo$inlined = str;
                    this.$this_andAllFlows$inlined = list;
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    AnonymousClass3 anonymousClass3 = new AnonymousClass3((Continuation) obj3, this.$tableLogBuffer$inlined, this.$combinedLoggingInfo$inlined, this.$this_andAllFlows$inlined);
                    anonymousClass3.L$0 = (FlowCollector) obj;
                    anonymousClass3.L$1 = (Object[]) obj2;
                    return anonymousClass3.invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        FlowCollector flowCollector = (FlowCollector) this.L$0;
                        Boolean[] boolArr = (Boolean[]) ((Object[]) this.L$1);
                        if (boolArr.length == 0) {
                            throw new UnsupportedOperationException("Empty array can't be reduced.");
                        }
                        Boolean bool = boolArr[0];
                        IntProgressionIterator it = new IntRange(1, boolArr.length - 1, 1).iterator();
                        while (it.hasNext) {
                            int nextInt = it.nextInt();
                            boolean booleanValue = boolArr[nextInt].booleanValue();
                            boolean booleanValue2 = bool.booleanValue();
                            TableLogBuffer tableLogBuffer = this.$tableLogBuffer$inlined;
                            String str = (String) ((Pair) this.$this_andAllFlows$inlined.get(nextInt)).getSecond();
                            tableLogBuffer.getClass();
                            TableLogBufferBase.DefaultImpls.logChange(tableLogBuffer, "", str, booleanValue);
                            bool = Boolean.valueOf(booleanValue2 && booleanValue);
                        }
                        boolean booleanValue3 = bool.booleanValue();
                        TableLogBuffer tableLogBuffer2 = this.$tableLogBuffer$inlined;
                        String str2 = this.$combinedLoggingInfo$inlined;
                        tableLogBuffer2.getClass();
                        TableLogBufferBase.DefaultImpls.logChange(tableLogBuffer2, "", str2, booleanValue3);
                        this.label = 1;
                        if (flowCollector.emit(bool, this) == coroutineSingletons) {
                            return coroutineSingletons;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                final Flow[] flowArr2 = flowArr;
                Object combineInternal = CombineKt.combineInternal(continuation, new Function0() { // from class: com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryKt$andAllFlows$$inlined$combine$1.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return new Boolean[flowArr2.length];
                    }
                }, new AnonymousClass3(null, tableLogBuffer, str, list), flowCollector, flowArr2);
                return combineInternal == CoroutineSingletons.COROUTINE_SUSPENDED ? combineInternal : Unit.INSTANCE;
            }
        };
    }
}
