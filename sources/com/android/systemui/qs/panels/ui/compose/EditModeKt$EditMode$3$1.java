package com.android.systemui.qs.panels.ui.compose;

import com.android.systemui.qs.panels.ui.viewmodel.EditModeViewModel;
import com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractor;
import com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import java.util.ArrayList;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final /* synthetic */ class EditModeKt$EditMode$3$1 extends FunctionReferenceImpl implements Function2 {
    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        TileSpec tileSpec = (TileSpec) obj;
        int intValue = ((Number) obj2).intValue();
        CurrentTilesInteractor currentTilesInteractor = ((EditModeViewModel) this.receiver).currentTilesInteractor;
        ArrayList arrayList = new ArrayList(currentTilesInteractor.getCurrentTilesSpecs());
        int indexOf = arrayList.indexOf(tileSpec);
        if (indexOf != -1) {
            if (indexOf != intValue) {
                arrayList.remove(indexOf);
            }
            return Unit.INSTANCE;
        }
        if (intValue < 0 || intValue >= arrayList.size()) {
            arrayList.add(tileSpec);
        } else {
            arrayList.add(intValue, tileSpec);
        }
        ((CurrentTilesInteractorImpl) currentTilesInteractor).setTiles(arrayList);
        return Unit.INSTANCE;
    }
}
