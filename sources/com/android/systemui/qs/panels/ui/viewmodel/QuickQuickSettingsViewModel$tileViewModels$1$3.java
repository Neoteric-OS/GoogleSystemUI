package com.android.systemui.qs.panels.ui.viewmodel;

import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.panels.shared.model.SizedTileImpl;
import com.android.systemui.qs.panels.shared.model.TileRowKt;
import com.android.systemui.qs.pipeline.domain.model.TileModel;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import java.util.ArrayList;
import java.util.List;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequencesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class QuickQuickSettingsViewModel$tileViewModels$1$3 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $columns;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ QuickQuickSettingsViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QuickQuickSettingsViewModel$tileViewModels$1$3(QuickQuickSettingsViewModel quickQuickSettingsViewModel, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = quickQuickSettingsViewModel;
        this.$columns = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        QuickQuickSettingsViewModel$tileViewModels$1$3 quickQuickSettingsViewModel$tileViewModels$1$3 = new QuickQuickSettingsViewModel$tileViewModels$1$3(this.this$0, this.$columns, continuation);
        quickQuickSettingsViewModel$tileViewModels$1$3.L$0 = obj;
        return quickQuickSettingsViewModel$tileViewModels$1$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((QuickQuickSettingsViewModel$tileViewModels$1$3) create((Pair) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Pair pair = (Pair) this.L$0;
        List<TileModel> list = (List) pair.component1();
        int intValue = ((Number) pair.component2()).intValue();
        QuickQuickSettingsViewModel quickQuickSettingsViewModel = this.this$0;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        for (TileModel tileModel : list) {
            QSTile qSTile = tileModel.tile;
            TileSpec tileSpec = tileModel.spec;
            arrayList.add(new SizedTileImpl(quickQuickSettingsViewModel.iconTilesViewModel.isIconTile(tileSpec) ? 1 : 2, new TileViewModel(qSTile, tileSpec)));
        }
        return CollectionsKt__IterablesKt.flatten(SequencesKt.toList(SequencesKt.take(TileRowKt.splitInRowsSequence(this.$columns, arrayList), intValue)));
    }
}
