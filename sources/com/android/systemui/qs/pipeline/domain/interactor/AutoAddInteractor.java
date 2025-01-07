package com.android.systemui.qs.pipeline.domain.interactor;

import android.util.IndentingPrintWriter;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.qs.pipeline.data.repository.AutoAddSettingRepository;
import com.android.systemui.qs.pipeline.domain.model.AutoAddable;
import com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger;
import com.android.systemui.util.DumpUtilsKt;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AutoAddInteractor implements Dumpable {
    public final Set autoAddables;
    public CurrentTilesInteractor currentTilesInteractor;
    public final DumpManager dumpManager;
    public final AtomicBoolean initialized = new AtomicBoolean(false);
    public final QSPipelineLogger qsPipelineLogger;
    public final AutoAddSettingRepository repository;
    public final CoroutineScope scope;

    public AutoAddInteractor(Set set, AutoAddSettingRepository autoAddSettingRepository, DumpManager dumpManager, QSPipelineLogger qSPipelineLogger, CoroutineScope coroutineScope) {
        this.autoAddables = set;
        this.repository = autoAddSettingRepository;
        this.dumpManager = dumpManager;
        this.qsPipelineLogger = qSPipelineLogger;
        this.scope = coroutineScope;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$collectAutoAddSignalsForUser(com.android.systemui.qs.pipeline.domain.interactor.AutoAddInteractor r10, kotlinx.coroutines.CoroutineScope r11, int r12, kotlin.coroutines.jvm.internal.ContinuationImpl r13) {
        /*
            Method dump skipped, instructions count: 240
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.pipeline.domain.interactor.AutoAddInteractor.access$collectAutoAddSignalsForUser(com.android.systemui.qs.pipeline.domain.interactor.AutoAddInteractor, kotlinx.coroutines.CoroutineScope, int, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        IndentingPrintWriter asIndenting = DumpUtilsKt.asIndenting(printWriter);
        asIndenting.println("AutoAddables:");
        asIndenting.increaseIndent();
        Iterator it = this.autoAddables.iterator();
        while (it.hasNext()) {
            asIndenting.println(((AutoAddable) it.next()).getDescription());
        }
        asIndenting.decreaseIndent();
    }

    public final void init(CurrentTilesInteractor currentTilesInteractor) {
        if (this.initialized.compareAndSet(false, true)) {
            this.currentTilesInteractor = currentTilesInteractor;
            this.dumpManager.registerNormalDumpable("AutoAddInteractor", this);
            BuildersKt.launch$default(this.scope, null, null, new AutoAddInteractor$init$1(currentTilesInteractor, this, null), 3);
        }
    }
}
