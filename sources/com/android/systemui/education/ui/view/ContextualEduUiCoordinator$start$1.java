package com.android.systemui.education.ui.view;

import android.R;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.widget.Toast;
import androidx.core.app.NotificationCompat$Builder;
import com.android.systemui.education.ui.view.ContextualEduUiCoordinator;
import com.android.systemui.education.ui.viewmodel.ContextualEduContentViewModel;
import com.android.systemui.education.ui.viewmodel.ContextualEduNotificationViewModel;
import com.android.systemui.education.ui.viewmodel.ContextualEduToastViewModel;
import com.android.systemui.education.ui.viewmodel.ContextualEduViewModel$special$$inlined$map$1;
import com.android.systemui.inputdevice.tutorial.ui.view.KeyboardTouchpadTutorialActivity;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ContextualEduUiCoordinator$start$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ ContextualEduUiCoordinator this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContextualEduUiCoordinator$start$1(ContextualEduUiCoordinator contextualEduUiCoordinator, Continuation continuation) {
        super(2, continuation);
        this.this$0 = contextualEduUiCoordinator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ContextualEduUiCoordinator$start$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ContextualEduUiCoordinator$start$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ContextualEduUiCoordinator contextualEduUiCoordinator = this.this$0;
            ContextualEduViewModel$special$$inlined$map$1 contextualEduViewModel$special$$inlined$map$1 = contextualEduUiCoordinator.viewModel.eduContent;
            FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.education.ui.view.ContextualEduUiCoordinator$start$1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj2, Continuation continuation) {
                    ContextualEduContentViewModel contextualEduContentViewModel = (ContextualEduContentViewModel) obj2;
                    boolean z = contextualEduContentViewModel instanceof ContextualEduToastViewModel;
                    ContextualEduUiCoordinator contextualEduUiCoordinator2 = ContextualEduUiCoordinator.this;
                    if (z) {
                        contextualEduUiCoordinator2.getClass();
                        ((Toast) ((ContextualEduUiCoordinator.AnonymousClass1) contextualEduUiCoordinator2.createToast).invoke(((ContextualEduToastViewModel) contextualEduContentViewModel).message)).show();
                    } else if (contextualEduContentViewModel instanceof ContextualEduNotificationViewModel) {
                        ContextualEduNotificationViewModel contextualEduNotificationViewModel = (ContextualEduNotificationViewModel) contextualEduContentViewModel;
                        contextualEduUiCoordinator2.getClass();
                        Bundle bundle = new Bundle();
                        bundle.putString("android.substName", contextualEduUiCoordinator2.context.getString(R.string.android_upgrading_complete));
                        NotificationCompat$Builder notificationCompat$Builder = new NotificationCompat$Builder(contextualEduUiCoordinator2.context, "ContextualEduNotificationChannel");
                        notificationCompat$Builder.mNotification.icon = com.android.wm.shell.R.drawable.ic_settings;
                        notificationCompat$Builder.mContentTitle = NotificationCompat$Builder.limitCharSequenceLength(contextualEduNotificationViewModel.title);
                        notificationCompat$Builder.mContentText = NotificationCompat$Builder.limitCharSequenceLength(contextualEduNotificationViewModel.message);
                        Intent intent = new Intent(contextualEduUiCoordinator2.context, (Class<?>) KeyboardTouchpadTutorialActivity.class);
                        intent.addCategory("android.intent.category.DEFAULT");
                        intent.setFlags(268435456);
                        notificationCompat$Builder.mContentIntent = PendingIntent.getActivity(contextualEduUiCoordinator2.context, 0, intent, 67108864);
                        notificationCompat$Builder.setFlag(16, true);
                        notificationCompat$Builder.addExtras(bundle);
                        contextualEduUiCoordinator2.notificationManager.notifyAsUser("ContextualEduUiCoordinator", 1000, notificationCompat$Builder.build(), UserHandle.of(contextualEduNotificationViewModel.userId));
                    }
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (contextualEduViewModel$special$$inlined$map$1.collect(flowCollector, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
