package com.android.systemui.qs.panels.shared.model;

import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceBuilderIterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class TileRowKt$splitInRowsSequence$1 extends RestrictedSuspendLambda implements Function2 {
    final /* synthetic */ int $columns;
    final /* synthetic */ List $tiles;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TileRowKt$splitInRowsSequence$1(int i, List list, Continuation continuation) {
        super(continuation);
        this.$columns = i;
        this.$tiles = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        TileRowKt$splitInRowsSequence$1 tileRowKt$splitInRowsSequence$1 = new TileRowKt$splitInRowsSequence$1(this.$columns, this.$tiles, continuation);
        tileRowKt$splitInRowsSequence$1.L$0 = obj;
        return tileRowKt$splitInRowsSequence$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((TileRowKt$splitInRowsSequence$1) create((SequenceBuilderIterator) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        SequenceBuilderIterator sequenceBuilderIterator;
        TileRow tileRow;
        Iterator it;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            sequenceBuilderIterator = (SequenceBuilderIterator) this.L$0;
            tileRow = new TileRow(this.$columns);
            it = this.$tiles.iterator();
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            SizedTile sizedTile = (SizedTile) this.L$3;
            it = (Iterator) this.L$2;
            tileRow = (TileRow) this.L$1;
            sequenceBuilderIterator = (SequenceBuilderIterator) this.L$0;
            ResultKt.throwOnFailure(obj);
            tileRow._tiles.clear();
            int i2 = tileRow.columns;
            tileRow.availableColumns = i2;
            if (i2 - sizedTile.getWidth() >= 0) {
                tileRow._tiles.add(sizedTile);
                tileRow.availableColumns -= sizedTile.getWidth();
            }
        }
        while (it.hasNext()) {
            SizedTile sizedTile2 = (SizedTile) it.next();
            if (sizedTile2.getWidth() > this.$columns) {
                throw new IllegalStateException("Check failed.");
            }
            if (tileRow.availableColumns - sizedTile2.getWidth() < 0) {
                List list = CollectionsKt.toList(tileRow._tiles);
                this.L$0 = sequenceBuilderIterator;
                this.L$1 = tileRow;
                this.L$2 = it;
                this.L$3 = sizedTile2;
                this.label = 1;
                sequenceBuilderIterator.yield(list, this);
                CoroutineSingletons coroutineSingletons2 = CoroutineSingletons.COROUTINE_SUSPENDED;
                return coroutineSingletons;
            }
            tileRow._tiles.add(sizedTile2);
            tileRow.availableColumns -= sizedTile2.getWidth();
        }
        if (!CollectionsKt.toList(tileRow._tiles).isEmpty()) {
            List list2 = CollectionsKt.toList(tileRow._tiles);
            this.L$0 = null;
            this.L$1 = null;
            this.L$2 = null;
            this.L$3 = null;
            this.label = 2;
            sequenceBuilderIterator.yield(list2, this);
            CoroutineSingletons coroutineSingletons3 = CoroutineSingletons.COROUTINE_SUSPENDED;
            return coroutineSingletons;
        }
        return Unit.INSTANCE;
    }
}
