package com.google.android.systemui.screenshot;

import android.app.assist.AssistContent;
import android.content.ComponentName;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import androidx.compose.foundation.text.input.internal.RecordingInputConnection$$ExternalSyntheticOutline0;
import com.android.systemui.screenshot.ScreenshotActionsController;
import com.android.systemui.screenshot.ScreenshotData;
import com.android.systemui.screenshot.ui.viewmodel.ActionButtonAppearance;
import com.android.systemui.screenshot.ui.viewmodel.ActionButtonViewModel;
import com.google.android.apps.pixel.pearl.suggestion.PearlAction;
import com.google.android.apps.pixel.pearl.suggestion.PearlActionClientImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ScreenshotActionsProviderGoogle$onAssistContent$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ AssistContent $assistContent;
    int label;
    final /* synthetic */ ScreenshotActionsProviderGoogle this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScreenshotActionsProviderGoogle$onAssistContent$2(ScreenshotActionsProviderGoogle screenshotActionsProviderGoogle, AssistContent assistContent, Continuation continuation) {
        super(2, continuation);
        this.this$0 = screenshotActionsProviderGoogle;
        this.$assistContent = assistContent;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ScreenshotActionsProviderGoogle$onAssistContent$2(this.this$0, this.$assistContent, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ScreenshotActionsProviderGoogle$onAssistContent$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ScreenshotActionsProviderGoogle screenshotActionsProviderGoogle = this.this$0;
            PearlActionClientImpl pearlActionClientImpl = screenshotActionsProviderGoogle.actionClient;
            ScreenshotData screenshotData = screenshotActionsProviderGoogle.request;
            Bitmap bitmap = screenshotData.bitmap;
            if (bitmap == null) {
                throw new IllegalArgumentException("Required value was null.");
            }
            ComponentName componentName = screenshotData.topComponent;
            AssistContent assistContent = this.$assistContent;
            int i2 = screenshotData.source;
            int i3 = screenshotData.taskId;
            Function1 function1 = new Function1() { // from class: com.google.android.systemui.screenshot.ScreenshotActionsProviderGoogle$onAssistContent$2.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    int i4;
                    long longValue = ((Number) obj2).longValue();
                    if (longValue != -1) {
                        ScreenshotActionsProviderGoogle screenshotActionsProviderGoogle2 = ScreenshotActionsProviderGoogle.this;
                        screenshotActionsProviderGoogle2.containerId = longValue;
                        screenshotActionsProviderGoogle2.mayContainerAndScreenshotUriReady();
                    } else {
                        ScreenshotActionsProviderGoogle screenshotActionsProviderGoogle3 = ScreenshotActionsProviderGoogle.this;
                        ScreenshotActionsController.ActionsCallback actionsCallback = screenshotActionsProviderGoogle3.actionsCallback;
                        UUID uuid = actionsCallback.screenshotId;
                        ScreenshotActionsController screenshotActionsController = ScreenshotActionsController.this;
                        if (uuid.equals(screenshotActionsController.currentScreenshotId)) {
                            StateFlowImpl stateFlowImpl = screenshotActionsController.viewModel._actions;
                            ArrayList arrayList = new ArrayList((Collection) stateFlowImpl.getValue());
                            Iterator it = arrayList.iterator();
                            int i5 = 0;
                            while (true) {
                                boolean hasNext = it.hasNext();
                                i4 = screenshotActionsProviderGoogle3.reminderButtonId;
                                if (!hasNext) {
                                    i5 = -1;
                                    break;
                                }
                                if (((ActionButtonViewModel) it.next()).id == i4) {
                                    break;
                                }
                                i5++;
                            }
                            if (i5 >= 0) {
                                ActionButtonAppearance actionButtonAppearance = ((ActionButtonViewModel) arrayList.get(i5)).appearance;
                                ((ActionButtonViewModel) arrayList.get(i5)).getClass();
                                arrayList.set(i5, new ActionButtonViewModel(actionButtonAppearance, i4, false, ((ActionButtonViewModel) arrayList.get(i5)).onClicked));
                                stateFlowImpl.updateState(null, arrayList);
                            } else {
                                RecordingInputConnection$$ExternalSyntheticOutline0.m("Attempted to update unknown action id ", "ScreenshotViewModel", i4);
                            }
                        }
                    }
                    return Unit.INSTANCE;
                }
            };
            Function1 function12 = new Function1() { // from class: com.google.android.systemui.screenshot.ScreenshotActionsProviderGoogle$onAssistContent$2.2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    final ScreenshotActionsProviderGoogle screenshotActionsProviderGoogle2 = ScreenshotActionsProviderGoogle.this;
                    for (final PearlAction pearlAction : (List) obj2) {
                        screenshotActionsProviderGoogle2.actionsCallback.provideActionButton(new ActionButtonAppearance(new BitmapDrawable(screenshotActionsProviderGoogle2.context.getResources(), pearlAction.icon), pearlAction.label, pearlAction.description, pearlAction.tint, 16), new Function0() { // from class: com.google.android.systemui.screenshot.ScreenshotActionsProviderGoogle$onAssistContent$2$2$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                ScreenshotActionsProviderGoogle.this.actionExecutor.sendPendingIntent(pearlAction.action);
                                return Unit.INSTANCE;
                            }
                        });
                    }
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (pearlActionClientImpl.saveScreenshotAndProvideActions(bitmap, componentName, assistContent, i2, i3, function1, function12, this) == coroutineSingletons) {
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
