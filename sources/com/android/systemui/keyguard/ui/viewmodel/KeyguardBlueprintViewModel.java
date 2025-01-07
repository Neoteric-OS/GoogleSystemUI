package com.android.systemui.keyguard.ui.viewmodel;

import android.os.Handler;
import android.transition.Transition;
import android.transition.TransitionManager;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.systemui.keyguard.domain.interactor.KeyguardBlueprintInteractor;
import com.android.systemui.keyguard.ui.view.layout.blueprints.transitions.IntraBlueprintTransition;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardBlueprintViewModel {
    public final StateFlowImpl _currentTransition;
    public final StateFlowImpl blueprint;
    public final ReadonlyStateFlow currentTransition;
    public final Handler handler;
    public final KeyguardBlueprintInteractor keyguardBlueprintInteractor;
    public final SharedFlowImpl refreshTransition;
    public final Set runningTransitions;
    public final KeyguardBlueprintViewModel$transitionListener$1 transitionListener;

    /* JADX WARN: Type inference failed for: r1v6, types: [com.android.systemui.keyguard.ui.viewmodel.KeyguardBlueprintViewModel$transitionListener$1] */
    public KeyguardBlueprintViewModel(Handler handler, KeyguardBlueprintInteractor keyguardBlueprintInteractor) {
        this.handler = handler;
        this.keyguardBlueprintInteractor = keyguardBlueprintInteractor;
        this.blueprint = keyguardBlueprintInteractor.blueprint;
        this.refreshTransition = keyguardBlueprintInteractor.refreshTransition;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(null);
        this._currentTransition = MutableStateFlow;
        this.currentTransition = new ReadonlyStateFlow(MutableStateFlow);
        this.runningTransitions = new LinkedHashSet();
        this.transitionListener = new Transition.TransitionListener() { // from class: com.android.systemui.keyguard.ui.viewmodel.KeyguardBlueprintViewModel$transitionListener$1
            @Override // android.transition.Transition.TransitionListener
            public final void onTransitionCancel(final Transition transition) {
                KeyguardBlueprintViewModel.this.updateTransitions(null, new Function1() { // from class: com.android.systemui.keyguard.ui.viewmodel.KeyguardBlueprintViewModel$transitionListener$1$onTransitionCancel$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        ((Set) obj).remove(transition);
                        return Unit.INSTANCE;
                    }
                });
            }

            @Override // android.transition.Transition.TransitionListener
            public final void onTransitionEnd(final Transition transition) {
                KeyguardBlueprintViewModel.this.updateTransitions(null, new Function1() { // from class: com.android.systemui.keyguard.ui.viewmodel.KeyguardBlueprintViewModel$transitionListener$1$onTransitionEnd$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        ((Set) obj).remove(transition);
                        return Unit.INSTANCE;
                    }
                });
            }

            @Override // android.transition.Transition.TransitionListener
            public final void onTransitionPause(final Transition transition) {
                KeyguardBlueprintViewModel.this.updateTransitions(null, new Function1() { // from class: com.android.systemui.keyguard.ui.viewmodel.KeyguardBlueprintViewModel$transitionListener$1$onTransitionPause$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        ((Set) obj).remove(transition);
                        return Unit.INSTANCE;
                    }
                });
            }

            @Override // android.transition.Transition.TransitionListener
            public final void onTransitionResume(final Transition transition) {
                KeyguardBlueprintViewModel.this.updateTransitions(null, new Function1() { // from class: com.android.systemui.keyguard.ui.viewmodel.KeyguardBlueprintViewModel$transitionListener$1$onTransitionResume$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        ((Set) obj).add(transition);
                        return Unit.INSTANCE;
                    }
                });
            }

            @Override // android.transition.Transition.TransitionListener
            public final void onTransitionStart(final Transition transition) {
                KeyguardBlueprintViewModel.this.updateTransitions(null, new Function1() { // from class: com.android.systemui.keyguard.ui.viewmodel.KeyguardBlueprintViewModel$transitionListener$1$onTransitionStart$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        ((Set) obj).add(transition);
                        return Unit.INSTANCE;
                    }
                });
            }
        };
    }

    public final void runTransition(final ConstraintLayout constraintLayout, final IntraBlueprintTransition intraBlueprintTransition, final IntraBlueprintTransition.Config config, final Function0 function0) {
        TransitionData transitionData = (TransitionData) ((StateFlowImpl) this.currentTransition.$$delegate_0).getValue();
        int priority = transitionData != null ? transitionData.config.type.getPriority() : -1;
        if (config.checkPriority && config.type.getPriority() < priority) {
            function0.invoke();
            return;
        }
        updateTransitions(new TransitionData(config), new Function1() { // from class: com.android.systemui.keyguard.ui.viewmodel.KeyguardBlueprintViewModel$runTransition$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ((Set) obj).add(intraBlueprintTransition);
                return Unit.INSTANCE;
            }
        });
        intraBlueprintTransition.addListener((Transition.TransitionListener) this.transitionListener);
        this.handler.post(new Runnable(constraintLayout, intraBlueprintTransition, function0, this) { // from class: com.android.systemui.keyguard.ui.viewmodel.KeyguardBlueprintViewModel$runTransition$2
            public final /* synthetic */ Lambda $apply;
            public final /* synthetic */ ConstraintLayout $constraintLayout;
            public final /* synthetic */ IntraBlueprintTransition $transition;
            public final /* synthetic */ KeyguardBlueprintViewModel this$0;

            /* JADX WARN: Multi-variable type inference failed */
            {
                this.$apply = (Lambda) function0;
                this.this$0 = this;
            }

            /* JADX WARN: Type inference failed for: r0v3, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
            @Override // java.lang.Runnable
            public final void run() {
                if (IntraBlueprintTransition.Config.this.terminatePrevious) {
                    TransitionManager.endTransitions(this.$constraintLayout);
                }
                TransitionManager.beginDelayedTransition(this.$constraintLayout, this.$transition);
                this.$apply.invoke();
                final KeyguardBlueprintViewModel keyguardBlueprintViewModel = this.this$0;
                Handler handler = keyguardBlueprintViewModel.handler;
                final IntraBlueprintTransition intraBlueprintTransition2 = this.$transition;
                handler.post(new Runnable() { // from class: com.android.systemui.keyguard.ui.viewmodel.KeyguardBlueprintViewModel$runTransition$2.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        KeyguardBlueprintViewModel keyguardBlueprintViewModel2 = KeyguardBlueprintViewModel.this;
                        final IntraBlueprintTransition intraBlueprintTransition3 = intraBlueprintTransition2;
                        keyguardBlueprintViewModel2.updateTransitions(null, new Function1() { // from class: com.android.systemui.keyguard.ui.viewmodel.KeyguardBlueprintViewModel.runTransition.2.1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                ((Set) obj).remove(intraBlueprintTransition3);
                                return Unit.INSTANCE;
                            }
                        });
                    }
                });
            }
        });
    }

    public final void updateTransitions(TransitionData transitionData, Function1 function1) {
        function1.invoke(this.runningTransitions);
        int size = this.runningTransitions.size();
        StateFlowImpl stateFlowImpl = this._currentTransition;
        if (size <= 0) {
            stateFlowImpl.setValue(null);
        } else if (transitionData != null) {
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, transitionData);
        }
    }
}
