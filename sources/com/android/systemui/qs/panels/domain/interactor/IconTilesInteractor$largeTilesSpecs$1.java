package com.android.systemui.qs.panels.domain.interactor;

import com.android.systemui.qs.pipeline.domain.model.TileModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class IconTilesInteractor$largeTilesSpecs$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ IconTilesInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IconTilesInteractor$largeTilesSpecs$1(IconTilesInteractor iconTilesInteractor, Continuation continuation) {
        super(3, continuation);
        this.this$0 = iconTilesInteractor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        IconTilesInteractor$largeTilesSpecs$1 iconTilesInteractor$largeTilesSpecs$1 = new IconTilesInteractor$largeTilesSpecs$1(this.this$0, (Continuation) obj3);
        iconTilesInteractor$largeTilesSpecs$1.L$0 = (Set) obj;
        iconTilesInteractor$largeTilesSpecs$1.L$1 = (List) obj2;
        return iconTilesInteractor$largeTilesSpecs$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Set set = (Set) this.L$0;
        List list = (List) this.L$1;
        if (list.isEmpty()) {
            return set;
        }
        Set set2 = set;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((TileModel) it.next()).spec);
        }
        Set intersect = CollectionsKt.intersect(set2, CollectionsKt.toSet(arrayList));
        if (!intersect.equals(set)) {
            this.this$0.preferencesInteractor.setLargeTilesSpecs(intersect);
        }
        return intersect;
    }
}
