package com.android.systemui.communal.ui.compose;

import androidx.compose.foundation.lazy.grid.LazyGridState;
import androidx.compose.runtime.MutableState;
import com.android.systemui.communal.domain.model.CommunalContentModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CommunalHubKt$ScrollOnUpdatedLiveContentEffect$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ List $communalContent;
    final /* synthetic */ MutableState $communalContentPending$delegate;
    final /* synthetic */ LazyGridState $gridState;
    final /* synthetic */ List $liveContentKeys;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommunalHubKt$ScrollOnUpdatedLiveContentEffect$1(List list, List list2, LazyGridState lazyGridState, MutableState mutableState, Continuation continuation) {
        super(2, continuation);
        this.$communalContent = list;
        this.$liveContentKeys = list2;
        this.$gridState = lazyGridState;
        this.$communalContentPending$delegate = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new CommunalHubKt$ScrollOnUpdatedLiveContentEffect$1(this.$communalContent, this.$liveContentKeys, this.$gridState, this.$communalContentPending$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((CommunalHubKt$ScrollOnUpdatedLiveContentEffect$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (((Boolean) this.$communalContentPending$delegate.getValue()).booleanValue() && this.$communalContent.isEmpty()) {
                return unit;
            }
            List list = CollectionsKt.toList(this.$liveContentKeys);
            List list2 = this.$communalContent;
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : list2) {
                CommunalContentModel communalContentModel = (CommunalContentModel) obj2;
                communalContentModel.getClass();
                if ((communalContentModel instanceof CommunalContentModel.Smartspace) || (communalContentModel instanceof CommunalContentModel.Umo)) {
                    arrayList.add(obj2);
                }
            }
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add(((CommunalContentModel) it.next()).getKey());
            }
            this.$liveContentKeys.clear();
            this.$liveContentKeys.addAll(arrayList2);
            if (((Boolean) this.$communalContentPending$delegate.getValue()).booleanValue()) {
                this.$communalContentPending$delegate.setValue(Boolean.FALSE);
                return unit;
            }
            Iterator it2 = arrayList2.iterator();
            int i2 = 0;
            while (true) {
                if (!it2.hasNext()) {
                    i2 = -1;
                    break;
                }
                if (!list.contains((String) it2.next())) {
                    break;
                }
                i2++;
            }
            if (i2 >= 0 && i2 < this.$gridState.scrollPosition.getIndex()) {
                LazyGridState lazyGridState = this.$gridState;
                this.label = 1;
                if (lazyGridState.scrollToItem(i2, 0, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return unit;
    }
}
