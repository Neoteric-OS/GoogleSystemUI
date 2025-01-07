package com.android.systemui.keyboard.stickykeys.ui;

import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import androidx.activity.ComponentDialog;
import com.android.systemui.keyboard.stickykeys.ui.view.StickyKeysIndicatorKt;
import com.android.systemui.keyboard.stickykeys.ui.viewmodel.StickyKeysIndicatorViewModel;
import com.android.wm.shell.R;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class StickyKeysIndicatorCoordinator$startListening$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ StickyKeysIndicatorCoordinator this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StickyKeysIndicatorCoordinator$startListening$1(StickyKeysIndicatorCoordinator stickyKeysIndicatorCoordinator, Continuation continuation) {
        super(2, continuation);
        this.this$0 = stickyKeysIndicatorCoordinator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new StickyKeysIndicatorCoordinator$startListening$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((StickyKeysIndicatorCoordinator$startListening$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
        ResultKt.throwOnFailure(obj);
        final StickyKeysIndicatorCoordinator stickyKeysIndicatorCoordinator = this.this$0;
        StickyKeysIndicatorViewModel stickyKeysIndicatorViewModel = stickyKeysIndicatorCoordinator.viewModel;
        FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.keyboard.stickykeys.ui.StickyKeysIndicatorCoordinator$startListening$1.1
            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj2, Continuation continuation) {
                Map map = (Map) obj2;
                StickyKeysIndicatorCoordinator stickyKeysIndicatorCoordinator2 = StickyKeysIndicatorCoordinator.this;
                stickyKeysIndicatorCoordinator2.stickyKeysLogger.logNewUiState(map);
                if (map.isEmpty()) {
                    ComponentDialog componentDialog = stickyKeysIndicatorCoordinator2.dialog;
                    if (componentDialog != null) {
                        componentDialog.dismiss();
                    }
                    stickyKeysIndicatorCoordinator2.dialog = null;
                } else if (stickyKeysIndicatorCoordinator2.dialog == null) {
                    StickyKeyDialogFactory stickyKeyDialogFactory = stickyKeysIndicatorCoordinator2.stickyKeyDialogFactory;
                    stickyKeyDialogFactory.getClass();
                    ComponentDialog componentDialog2 = new ComponentDialog(R.style.Theme_SystemUI_Dialog_StickyKeys, stickyKeyDialogFactory.context);
                    Window window = componentDialog2.getWindow();
                    if (window != null) {
                        window.requestFeature(1);
                        window.setType(2017);
                        window.addFlags(24);
                        window.clearFlags(2);
                        window.setGravity(8388661);
                        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                        layoutParams.copyFrom(window.getAttributes());
                        layoutParams.receiveInsetsIgnoringZOrder = true;
                        layoutParams.setFitInsetsTypes(WindowInsets.Type.systemBars());
                        layoutParams.setTitle("StickyKeysIndicator");
                        window.setAttributes(layoutParams);
                    }
                    componentDialog2.setContentView(StickyKeysIndicatorKt.createStickyKeyIndicatorView(componentDialog2.getContext(), stickyKeysIndicatorCoordinator2.viewModel));
                    stickyKeysIndicatorCoordinator2.dialog = componentDialog2;
                    componentDialog2.show();
                }
                return Unit.INSTANCE;
            }
        };
        this.label = 1;
        ((StateFlowImpl) stickyKeysIndicatorViewModel.indicatorContent.$$delegate_0).collect(flowCollector, this);
        return coroutineSingletons;
    }
}
