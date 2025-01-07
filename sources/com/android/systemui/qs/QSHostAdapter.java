package com.android.systemui.qs;

import android.app.IUriGrantsManager;
import android.content.ComponentName;
import android.content.Context;
import com.android.internal.logging.InstanceIdSequence;
import com.android.internal.logging.UiEventLoggerImpl;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.external.CustomTile;
import com.android.systemui.qs.external.TileRequestDialogEventLogger;
import com.android.systemui.qs.external.TileServiceRequestController;
import com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractor;
import com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl;
import com.android.systemui.qs.pipeline.domain.model.TileModel;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSHostAdapter implements QSHost {
    public final Map callbacksMap = new LinkedHashMap();
    public final Context context;
    public final CurrentTilesInteractor interactor;
    public final CoroutineScope scope;
    public final TileServiceRequestController.Builder tileServiceRequestControllerBuilder;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.qs.QSHostAdapter$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return QSHostAdapter.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = (AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2);
            Unit unit = Unit.INSTANCE;
            anonymousClass1.invokeSuspend(unit);
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            QSHostAdapter qSHostAdapter = QSHostAdapter.this;
            TileServiceRequestController.Builder builder = qSHostAdapter.tileServiceRequestControllerBuilder;
            builder.getClass();
            TileRequestDialogEventLogger tileRequestDialogEventLogger = new TileRequestDialogEventLogger(new UiEventLoggerImpl(), new InstanceIdSequence(1048576));
            IUriGrantsManager iUriGrantsManager = builder.iUriGrantsManager;
            new TileServiceRequestController(qSHostAdapter, builder.commandQueue, builder.commandRegistry, tileRequestDialogEventLogger, iUriGrantsManager).init();
            return Unit.INSTANCE;
        }
    }

    public QSHostAdapter(CurrentTilesInteractor currentTilesInteractor, Context context, TileServiceRequestController.Builder builder, CoroutineScope coroutineScope, DumpManager dumpManager) {
        this.interactor = currentTilesInteractor;
        this.context = context;
        this.tileServiceRequestControllerBuilder = builder;
        this.scope = coroutineScope;
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass1(null), 3);
        dumpManager.registerCriticalDumpable("QSTileHost", currentTilesInteractor);
    }

    public final void addCallback(QSHost.Callback callback) {
        StandaloneCoroutine launch$default = BuildersKt.launch$default(this.scope, null, null, new QSHostAdapter$addCallback$job$1(this, callback, null), 3);
        synchronized (this.callbacksMap) {
        }
    }

    public final void addTile(ComponentName componentName, boolean z) {
        ((CurrentTilesInteractorImpl) this.interactor).addTile(z ? -1 : 0, new TileSpec.CustomTileSpec(componentName, CustomTile.toSpec(componentName)));
    }

    public final void changeTilesByUser(List list) {
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(TileSpec.Companion.create((String) it.next()));
        }
        ((CurrentTilesInteractorImpl) this.interactor).setTiles(arrayList);
    }

    public final List getSpecs() {
        List currentTilesSpecs = this.interactor.getCurrentTilesSpecs();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(currentTilesSpecs, 10));
        Iterator it = currentTilesSpecs.iterator();
        while (it.hasNext()) {
            arrayList.add(((TileSpec) it.next()).getSpec());
        }
        return arrayList;
    }

    public final Collection getTiles() {
        CurrentTilesInteractor currentTilesInteractor = this.interactor;
        currentTilesInteractor.getClass();
        Iterable iterable = (Iterable) ((CurrentTilesInteractorImpl) currentTilesInteractor).currentTiles.getValue();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(((TileModel) it.next()).tile);
        }
        return arrayList;
    }

    public final Context getUserContext() {
        return (Context) ((StateFlowImpl) ((CurrentTilesInteractorImpl) this.interactor).userContext.$$delegate_0).getValue();
    }

    public final int getUserId() {
        return ((Number) ((CurrentTilesInteractorImpl) this.interactor).userId.getValue()).intValue();
    }

    public final void removeCallback(QSHost.Callback callback) {
        synchronized (this.callbacksMap) {
            Job job = (Job) this.callbacksMap.remove(callback);
            if (job != null) {
                job.cancel(null);
            }
        }
    }

    public final void removeTile(String str) {
        ((CurrentTilesInteractorImpl) this.interactor).removeTiles(Collections.singletonList(TileSpec.Companion.create(str)));
    }
}
