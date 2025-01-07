package com.android.systemui.common.usagestats.data.repository;

import android.app.usage.UsageEvents;
import android.app.usage.UsageEventsQuery;
import com.android.systemui.common.usagestats.data.model.UsageStatsQuery;
import com.android.systemui.common.usagestats.shared.model.ActivityEventModel;
import java.util.Arrays;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.builders.ListBuilder;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class UsageStatsRepositoryImpl$queryActivityEvents$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ UsageStatsQuery $query;
    int label;
    final /* synthetic */ UsageStatsRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UsageStatsRepositoryImpl$queryActivityEvents$2(UsageStatsQuery usageStatsQuery, UsageStatsRepositoryImpl usageStatsRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.$query = usageStatsQuery;
        this.this$0 = usageStatsRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new UsageStatsRepositoryImpl$queryActivityEvents$2(this.$query, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((UsageStatsRepositoryImpl$queryActivityEvents$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        UsageStatsQuery usageStatsQuery = this.$query;
        UsageEventsQuery.Builder builder = new UsageEventsQuery.Builder(usageStatsQuery.startTime, usageStatsQuery.endTime);
        UsageStatsQuery usageStatsQuery2 = this.$query;
        builder.setUserId(usageStatsQuery2.user.getIdentifier());
        builder.setEventTypes(new int[]{1, 2, 23, 24});
        if (!usageStatsQuery2.packageNames.isEmpty()) {
            String[] strArr = (String[]) usageStatsQuery2.packageNames.toArray(new String[0]);
            builder.setPackageNames((String[]) Arrays.copyOf(strArr, strArr.length));
        }
        UsageEvents queryEvents = this.this$0.usageStatsManager.queryEvents(builder.build());
        ListBuilder listBuilder = new ListBuilder();
        if (queryEvents != null) {
            UsageEvents.Event event = new UsageEvents.Event();
            while (queryEvents.getNextEvent(event)) {
                int eventType = event.getEventType();
                listBuilder.add(new ActivityEventModel(event.getInstanceId(), event.getPackageName(), eventType != 1 ? eventType != 2 ? eventType != 23 ? eventType != 24 ? ActivityEventModel.Lifecycle.UNKNOWN : ActivityEventModel.Lifecycle.DESTROYED : ActivityEventModel.Lifecycle.STOPPED : ActivityEventModel.Lifecycle.PAUSED : ActivityEventModel.Lifecycle.RESUMED, event.getTimeStamp()));
            }
        }
        return listBuilder.build();
    }
}
