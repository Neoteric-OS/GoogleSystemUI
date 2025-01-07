package com.android.systemui.qs.panels.ui.viewmodel;

import android.content.Context;
import androidx.compose.ui.text.AnnotatedString;
import com.android.systemui.common.shared.model.Text;
import com.android.systemui.common.ui.compose.TextExtKt;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class EditModeViewModel$tiles$1$2 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ EditModeViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EditModeViewModel$tiles$1$2(EditModeViewModel editModeViewModel, Continuation continuation) {
        super(3, continuation);
        this.this$0 = editModeViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        EditModeViewModel$tiles$1$2 editModeViewModel$tiles$1$2 = new EditModeViewModel$tiles$1$2(this.this$0, (Continuation) obj3);
        editModeViewModel$tiles$1$2.L$0 = (List) obj;
        return editModeViewModel$tiles$1$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        List list = (List) this.L$0;
        EditModeViewModel editModeViewModel = this.this$0;
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            UnloadedEditTileViewModel unloadedEditTileViewModel = (UnloadedEditTileViewModel) list.get(i);
            Context context = editModeViewModel.applicationContext;
            AnnotatedString annotatedString = TextExtKt.toAnnotatedString(unloadedEditTileViewModel.label, context);
            TileSpec tileSpec = unloadedEditTileViewModel.tileSpec;
            if (annotatedString == null) {
                annotatedString = new AnnotatedString(tileSpec.getSpec());
            }
            AnnotatedString annotatedString2 = annotatedString;
            Text.Loaded loaded = unloadedEditTileViewModel.appName;
            arrayList.add(new EditTileViewModel(tileSpec, unloadedEditTileViewModel.icon, annotatedString2, loaded != null ? TextExtKt.toAnnotatedString(loaded, context) : null, unloadedEditTileViewModel.isCurrent, unloadedEditTileViewModel.availableEditActions, unloadedEditTileViewModel.category));
        }
        return arrayList;
    }
}
