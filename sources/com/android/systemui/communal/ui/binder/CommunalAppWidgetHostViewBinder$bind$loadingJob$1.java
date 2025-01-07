package com.android.systemui.communal.ui.binder;

import android.content.Context;
import android.util.SizeF;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import com.android.systemui.communal.domain.model.CommunalContentModel;
import com.android.systemui.communal.util.WidgetViewFactory;
import com.android.systemui.communal.widgets.CommunalAppWidgetHostView;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.SafeContinuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CommunalAppWidgetHostViewBinder$bind$loadingJob$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ FrameLayout $container;
    final /* synthetic */ Context $context;
    final /* synthetic */ WidgetViewFactory $factory;
    final /* synthetic */ CommunalContentModel.WidgetContent.Widget $model;
    final /* synthetic */ SizeF $size;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommunalAppWidgetHostViewBinder$bind$loadingJob$1(WidgetViewFactory widgetViewFactory, Context context, CommunalContentModel.WidgetContent.Widget widget, SizeF sizeF, FrameLayout frameLayout, Continuation continuation) {
        super(2, continuation);
        this.$factory = widgetViewFactory;
        this.$context = context;
        this.$model = widget;
        this.$size = sizeF;
        this.$container = frameLayout;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new CommunalAppWidgetHostViewBinder$bind$loadingJob$1(this.$factory, this.$context, this.$model, this.$size, this.$container, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((CommunalAppWidgetHostViewBinder$bind$loadingJob$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        final CommunalAppWidgetHostView communalAppWidgetHostView;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            WidgetViewFactory widgetViewFactory = this.$factory;
            Context context = this.$context;
            CommunalContentModel.WidgetContent.Widget widget = this.$model;
            SizeF sizeF = this.$size;
            this.label = 1;
            obj = widgetViewFactory.createWidget(context, widget, sizeF, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                communalAppWidgetHostView = (CommunalAppWidgetHostView) this.L$0;
                ResultKt.throwOnFailure(obj);
                final FrameLayout frameLayout = this.$container;
                frameLayout.post(new Runnable() { // from class: com.android.systemui.communal.ui.binder.CommunalAppWidgetHostViewBinder$bind$loadingJob$1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        FrameLayout frameLayout2 = frameLayout;
                        CommunalAppWidgetHostView communalAppWidgetHostView2 = communalAppWidgetHostView;
                        if (Intrinsics.areEqual(communalAppWidgetHostView2.getParent(), frameLayout2)) {
                            return;
                        }
                        ViewParent parent = communalAppWidgetHostView2.getParent();
                        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
                        if (viewGroup != null) {
                            viewGroup.removeView(communalAppWidgetHostView2);
                        }
                        frameLayout2.addView(communalAppWidgetHostView2);
                    }
                });
                return unit;
            }
            ResultKt.throwOnFailure(obj);
        }
        CommunalAppWidgetHostView communalAppWidgetHostView2 = (CommunalAppWidgetHostView) obj;
        FrameLayout frameLayout2 = this.$container;
        this.L$0 = communalAppWidgetHostView2;
        this.label = 2;
        final SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt__IntrinsicsJvmKt.intercepted(this));
        if (!frameLayout2.isLaidOut() || frameLayout2.isLayoutRequested()) {
            frameLayout2.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.android.systemui.communal.ui.binder.CommunalAppWidgetHostViewBinder$waitForLayout$lambda$1$$inlined$doOnLayout$1
                @Override // android.view.View.OnLayoutChangeListener
                public final void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                    view.removeOnLayoutChangeListener(this);
                    SafeContinuation.this.resumeWith(Unit.INSTANCE);
                }
            });
        } else {
            safeContinuation.resumeWith(unit);
        }
        Object orThrow = safeContinuation.getOrThrow();
        if (orThrow != coroutineSingletons) {
            orThrow = unit;
        }
        if (orThrow == coroutineSingletons) {
            return coroutineSingletons;
        }
        communalAppWidgetHostView = communalAppWidgetHostView2;
        final FrameLayout frameLayout3 = this.$container;
        frameLayout3.post(new Runnable() { // from class: com.android.systemui.communal.ui.binder.CommunalAppWidgetHostViewBinder$bind$loadingJob$1.1
            @Override // java.lang.Runnable
            public final void run() {
                FrameLayout frameLayout22 = frameLayout3;
                CommunalAppWidgetHostView communalAppWidgetHostView22 = communalAppWidgetHostView;
                if (Intrinsics.areEqual(communalAppWidgetHostView22.getParent(), frameLayout22)) {
                    return;
                }
                ViewParent parent = communalAppWidgetHostView22.getParent();
                ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
                if (viewGroup != null) {
                    viewGroup.removeView(communalAppWidgetHostView22);
                }
                frameLayout22.addView(communalAppWidgetHostView22);
            }
        });
        return unit;
    }
}
