package com.android.systemui.statusbar.phone;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.PaintDrawable;
import android.view.MotionEvent;
import android.view.View;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleKt;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.wm.shell.R;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusOverlayHoverListener implements View.OnHoverListener {
    public float cornerRadius;
    public int darkColor;
    public HoverTheme lastTheme = HoverTheme.LIGHT;
    public int lightColor;
    public final Resources resources;
    public final Flow themeFlow;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.StatusOverlayHoverListener$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function3 {
        final /* synthetic */ ConfigurationController $configurationController;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ StatusOverlayHoverListener this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.phone.StatusOverlayHoverListener$1$1, reason: invalid class name and collision with other inner class name */
        final class C02131 extends SuspendLambda implements Function2 {
            final /* synthetic */ LifecycleOwner $$this$repeatWhenAttached;
            final /* synthetic */ ConfigurationController $configurationController;
            Object L$0;
            int label;
            final /* synthetic */ StatusOverlayHoverListener this$0;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.phone.StatusOverlayHoverListener$1$1$1, reason: invalid class name and collision with other inner class name */
            final class C02141 extends SuspendLambda implements Function2 {
                final /* synthetic */ ConfigurationController $configurationController;
                final /* synthetic */ StatusOverlayHoverListener$1$1$configurationListener$1 $configurationListener;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C02141(ConfigurationController configurationController, StatusOverlayHoverListener$1$1$configurationListener$1 statusOverlayHoverListener$1$1$configurationListener$1, Continuation continuation) {
                    super(2, continuation);
                    this.$configurationController = configurationController;
                    this.$configurationListener = statusOverlayHoverListener$1$1$configurationListener$1;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new C02141(this.$configurationController, this.$configurationListener, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    C02141 c02141 = (C02141) create((CoroutineScope) obj, (Continuation) obj2);
                    Unit unit = Unit.INSTANCE;
                    c02141.invokeSuspend(unit);
                    return unit;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    ((ConfigurationControllerImpl) this.$configurationController).addCallback(this.$configurationListener);
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02131(LifecycleOwner lifecycleOwner, ConfigurationController configurationController, StatusOverlayHoverListener statusOverlayHoverListener, Continuation continuation) {
                super(2, continuation);
                this.$$this$repeatWhenAttached = lifecycleOwner;
                this.$configurationController = configurationController;
                this.this$0 = statusOverlayHoverListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C02131(this.$$this$repeatWhenAttached, this.$configurationController, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((C02131) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Type inference failed for: r8v1, types: [com.android.systemui.statusbar.phone.StatusOverlayHoverListener$1$1$configurationListener$1, java.lang.Object] */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                StatusOverlayHoverListener$1$1$configurationListener$1 statusOverlayHoverListener$1$1$configurationListener$1;
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    final StatusOverlayHoverListener statusOverlayHoverListener = this.this$0;
                    ?? r8 = new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.statusbar.phone.StatusOverlayHoverListener$1$1$configurationListener$1
                        @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
                        public final void onConfigChanged(Configuration configuration) {
                            StatusOverlayHoverListener statusOverlayHoverListener2 = StatusOverlayHoverListener.this;
                            statusOverlayHoverListener2.lightColor = statusOverlayHoverListener2.resources.getColor(R.color.status_bar_icons_hover_color_light);
                            statusOverlayHoverListener2.darkColor = statusOverlayHoverListener2.resources.getColor(R.color.status_bar_icons_hover_color_dark);
                            statusOverlayHoverListener2.cornerRadius = statusOverlayHoverListener2.resources.getDimension(R.dimen.status_icons_hover_state_background_radius);
                        }
                    };
                    LifecycleOwner lifecycleOwner = this.$$this$repeatWhenAttached;
                    Lifecycle.State state = Lifecycle.State.CREATED;
                    C02141 c02141 = new C02141(this.$configurationController, r8, null);
                    this.L$0 = r8;
                    this.label = 1;
                    if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, c02141, this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                    statusOverlayHoverListener$1$1$configurationListener$1 = r8;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    statusOverlayHoverListener$1$1$configurationListener$1 = (StatusOverlayHoverListener$1$1$configurationListener$1) this.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                ((ConfigurationControllerImpl) this.$configurationController).removeCallback(statusOverlayHoverListener$1$1$configurationListener$1);
                return Unit.INSTANCE;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.phone.StatusOverlayHoverListener$1$2, reason: invalid class name */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ StatusOverlayHoverListener this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(StatusOverlayHoverListener statusOverlayHoverListener, Continuation continuation) {
                super(2, continuation);
                this.this$0 = statusOverlayHoverListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    final StatusOverlayHoverListener statusOverlayHoverListener = this.this$0;
                    Flow flow = statusOverlayHoverListener.themeFlow;
                    FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.statusbar.phone.StatusOverlayHoverListener.1.2.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(Object obj2, Continuation continuation) {
                            StatusOverlayHoverListener.this.lastTheme = (HoverTheme) obj2;
                            return Unit.INSTANCE;
                        }
                    };
                    this.label = 1;
                    if (flow.collect(flowCollector, this) == coroutineSingletons) {
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

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ConfigurationController configurationController, StatusOverlayHoverListener statusOverlayHoverListener, Continuation continuation) {
            super(3, continuation);
            this.$configurationController = configurationController;
            this.this$0 = statusOverlayHoverListener;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$configurationController, this.this$0, (Continuation) obj3);
            anonymousClass1.L$0 = (LifecycleOwner) obj;
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
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            BuildersKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), null, null, new C02131(lifecycleOwner, this.$configurationController, this.this$0, null), 3);
            BuildersKt.launch$default(LifecycleKt.getCoroutineScope(lifecycleOwner.getLifecycle()), null, null, new AnonymousClass2(this.this$0, null), 3);
            return Unit.INSTANCE;
        }
    }

    public StatusOverlayHoverListener(View view, ConfigurationController configurationController, Resources resources, Flow flow) {
        this.resources = resources;
        this.themeFlow = flow;
        RepeatWhenAttachedKt.repeatWhenAttached(view, EmptyCoroutineContext.INSTANCE, new AnonymousClass1(configurationController, this, null));
        this.lightColor = resources.getColor(R.color.status_bar_icons_hover_color_light);
        this.darkColor = resources.getColor(R.color.status_bar_icons_hover_color_dark);
        this.cornerRadius = resources.getDimension(R.dimen.status_icons_hover_state_background_radius);
    }

    @Override // android.view.View.OnHoverListener
    public final boolean onHover(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 9) {
            if (motionEvent.getAction() != 10) {
                return true;
            }
            view.getOverlay().clear();
            return true;
        }
        PaintDrawable paintDrawable = new PaintDrawable(this.lastTheme == HoverTheme.LIGHT ? this.lightColor : this.darkColor);
        paintDrawable.setCornerRadius(this.cornerRadius);
        paintDrawable.setBounds(0, 0, view.getWidth(), view.getHeight());
        view.getOverlay().add(paintDrawable);
        return true;
    }
}
