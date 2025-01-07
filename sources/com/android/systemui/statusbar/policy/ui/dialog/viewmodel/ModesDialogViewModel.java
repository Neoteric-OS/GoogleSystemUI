package com.android.systemui.statusbar.policy.ui.dialog.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.service.notification.ZenModeConfig;
import android.util.Log;
import com.android.settingslib.notification.modes.ZenMode;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.DialogTransitionAnimator$createActivityTransitionController$1;
import com.android.systemui.qs.QSModesEvent;
import com.android.systemui.qs.tiles.dialog.QSZenModeDialogMetricsLogger;
import com.android.systemui.statusbar.phone.ComponentSystemUIDialog;
import com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor;
import com.android.systemui.statusbar.policy.ui.dialog.ModesDialogDelegate;
import com.android.systemui.statusbar.policy.ui.dialog.ModesDialogEventLogger;
import com.android.systemui.util.Assert;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ModesDialogViewModel {
    public final Context context;
    public final ModesDialogDelegate dialogDelegate;
    public final ModesDialogEventLogger dialogEventLogger;
    public final Flow tiles;
    public final FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1 visibleModes;
    public final QSZenModeDialogMetricsLogger zenDialogMetricsLogger;

    public ModesDialogViewModel(Context context, final ZenModeInteractor zenModeInteractor, CoroutineDispatcher coroutineDispatcher, ModesDialogDelegate modesDialogDelegate, ModesDialogEventLogger modesDialogEventLogger) {
        this.context = context;
        this.dialogDelegate = modesDialogDelegate;
        this.dialogEventLogger = modesDialogEventLogger;
        this.zenDialogMetricsLogger = new QSZenModeDialogMetricsLogger(context);
        final FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1 flowKt__TransformKt$runningFold$$inlined$unsafeFlow$1 = new FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1(EmptyList.INSTANCE, new ModesDialogViewModel$visibleModes$1(3, null), zenModeInteractor.modes);
        this.tiles = FlowKt.flowOn(new Flow() { // from class: com.android.systemui.statusbar.policy.ui.dialog.viewmodel.ModesDialogViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.policy.ui.dialog.viewmodel.ModesDialogViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ ZenModeInteractor $zenModeInteractor$inlined;
                public final /* synthetic */ ModesDialogViewModel this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.policy.ui.dialog.viewmodel.ModesDialogViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    Object L$4;
                    Object L$5;
                    Object L$6;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, ZenModeInteractor zenModeInteractor, ModesDialogViewModel modesDialogViewModel) {
                    this.$this_unsafeFlow = flowCollector;
                    this.$zenModeInteractor$inlined = zenModeInteractor;
                    this.this$0 = modesDialogViewModel;
                }

                /* JADX WARN: Removed duplicated region for block: B:19:0x00b2  */
                /* JADX WARN: Removed duplicated region for block: B:22:0x00c4  */
                /* JADX WARN: Removed duplicated region for block: B:28:0x00f8  */
                /* JADX WARN: Removed duplicated region for block: B:31:0x0109  */
                /* JADX WARN: Removed duplicated region for block: B:35:0x007a  */
                /* JADX WARN: Removed duplicated region for block: B:39:0x013a  */
                /* JADX WARN: Removed duplicated region for block: B:42:0x00fb  */
                /* JADX WARN: Removed duplicated region for block: B:44:0x00e3  */
                /* JADX WARN: Removed duplicated region for block: B:46:0x00b4  */
                /* JADX WARN: Removed duplicated region for block: B:47:0x005a  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x009c -> B:17:0x009f). Please report as a decompilation issue!!! */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r24, kotlin.coroutines.Continuation r25) {
                    /*
                        Method dump skipped, instructions count: 344
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.policy.ui.dialog.viewmodel.ModesDialogViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1.this.collect(new AnonymousClass2(flowCollector, zenModeInteractor, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }, coroutineDispatcher);
    }

    public static final void access$openSettings(ModesDialogViewModel modesDialogViewModel, ZenMode zenMode) {
        ComponentSystemUIDialog componentSystemUIDialog;
        modesDialogViewModel.dialogEventLogger.uiEventLogger.log(zenMode.isManualDnd() ? QSModesEvent.QS_MODES_DND_SETTINGS : QSModesEvent.QS_MODES_MODE_SETTINGS, 0, zenMode.mRule.getPackageName());
        Intent putExtra = new Intent("android.settings.AUTOMATIC_ZEN_RULE_SETTINGS").putExtra("android.provider.extra.AUTOMATIC_ZEN_RULE_ID", zenMode.mId);
        ModesDialogDelegate modesDialogDelegate = modesDialogViewModel.dialogDelegate;
        modesDialogDelegate.getClass();
        Assert.isMainThread();
        if (modesDialogDelegate.currentDialog == null) {
            Log.w("ModesDialogDelegate", "Cannot launch from dialog, the dialog is not present. Will launch activity without animating.");
        }
        ComponentSystemUIDialog componentSystemUIDialog2 = modesDialogDelegate.currentDialog;
        DialogTransitionAnimator$createActivityTransitionController$1 createActivityTransitionController$default = componentSystemUIDialog2 != null ? DialogTransitionAnimator.createActivityTransitionController$default(modesDialogDelegate.dialogTransitionAnimator, componentSystemUIDialog2) : null;
        if (createActivityTransitionController$default == null && (componentSystemUIDialog = modesDialogDelegate.currentDialog) != null) {
            componentSystemUIDialog.dismiss();
        }
        modesDialogDelegate.activityStarter.startActivity(putExtra, true, (ActivityTransitionAnimator.Controller) createActivityTransitionController$default);
    }

    public final String getModeDescription(ZenMode zenMode) {
        if (!zenMode.mRule.isEnabled()) {
            return this.context.getResources().getString(R.string.zen_mode_set_up);
        }
        if (!zenMode.mRule.isManualInvocationAllowed() && !zenMode.isActive()) {
            return this.context.getResources().getString(R.string.zen_mode_no_manual_invocation);
        }
        Context context = this.context;
        if (zenMode.isManualDnd() && zenMode.isActive()) {
            long tryParseCountdownConditionId = ZenModeConfig.tryParseCountdownConditionId(zenMode.mRule.getConditionId());
            if (tryParseCountdownConditionId > 0) {
                return context.getString(17042058, ZenModeConfig.getFormattedTime(context, tryParseCountdownConditionId, ZenModeConfig.isToday(tryParseCountdownConditionId), context.getUserId()));
            }
        }
        return zenMode.mRule.getTriggerDescription();
    }
}
