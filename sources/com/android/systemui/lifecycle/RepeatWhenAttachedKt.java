package com.android.systemui.lifecycle;

import android.view.View;
import androidx.lifecycle.LifecycleKt;
import com.android.app.tracing.coroutines.TraceContextElementKt;
import com.android.app.tracing.coroutines.TraceDataThreadLocal;
import com.android.systemui.util.Assert;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.android.HandlerContext;
import kotlinx.coroutines.internal.MainDispatcherLoader;
import kotlinx.coroutines.scheduling.DefaultScheduler;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class RepeatWhenAttachedKt {
    public static final CoroutineContext MAIN_DISPATCHER_SINGLETON;

    static {
        DefaultScheduler defaultScheduler = Dispatchers.Default;
        HandlerContext handlerContext = MainDispatcherLoader.dispatcher;
        TraceDataThreadLocal traceDataThreadLocal = TraceContextElementKt.traceThreadLocal;
        EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
        handlerContext.getClass();
        MAIN_DISPATCHER_SINGLETON = CoroutineContext.DefaultImpls.plus(handlerContext, emptyCoroutineContext);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [android.view.View$OnAttachStateChangeListener, com.android.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttached$onAttachListener$1] */
    public static final RepeatWhenAttachedKt$repeatWhenAttached$1 repeatWhenAttached(final View view, CoroutineContext coroutineContext, final Function3 function3) {
        Assert.isMainThread();
        final CoroutineContext plus = MAIN_DISPATCHER_SINGLETON.plus(coroutineContext);
        final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        ?? r1 = new View.OnAttachStateChangeListener(view, plus, function3) { // from class: com.android.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttached$onAttachListener$1
            public final /* synthetic */ SuspendLambda $block;
            public final /* synthetic */ CoroutineContext $lifecycleCoroutineContext;
            public final /* synthetic */ View $view;

            /* JADX WARN: Multi-variable type inference failed */
            {
                this.$block = (SuspendLambda) function3;
            }

            /* JADX WARN: Type inference failed for: r2v0, types: [kotlin.coroutines.jvm.internal.SuspendLambda, kotlin.jvm.functions.Function3] */
            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewAttachedToWindow(View view2) {
                Assert.isMainThread();
                ViewLifecycleOwner viewLifecycleOwner = (ViewLifecycleOwner) Ref$ObjectRef.this.element;
                if (viewLifecycleOwner != null) {
                    viewLifecycleOwner.onDestroy();
                }
                Ref$ObjectRef ref$ObjectRef2 = Ref$ObjectRef.this;
                View view3 = this.$view;
                CoroutineContext coroutineContext2 = this.$lifecycleCoroutineContext;
                ?? r2 = this.$block;
                ViewLifecycleOwner viewLifecycleOwner2 = new ViewLifecycleOwner(view3);
                viewLifecycleOwner2.onCreate();
                BuildersKt.launch$default(LifecycleKt.getCoroutineScope(viewLifecycleOwner2.getLifecycle()), coroutineContext2, null, new RepeatWhenAttachedKt$createLifecycleOwnerAndRun$1$1("repeatWhenAttached", r2, viewLifecycleOwner2, view3, null), 2);
                ref$ObjectRef2.element = viewLifecycleOwner2;
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewDetachedFromWindow(View view2) {
                ViewLifecycleOwner viewLifecycleOwner = (ViewLifecycleOwner) Ref$ObjectRef.this.element;
                if (viewLifecycleOwner != null) {
                    viewLifecycleOwner.onDestroy();
                }
                Ref$ObjectRef.this.element = null;
            }
        };
        view.addOnAttachStateChangeListener(r1);
        if (view.isAttachedToWindow()) {
            ViewLifecycleOwner viewLifecycleOwner = new ViewLifecycleOwner(view);
            viewLifecycleOwner.onCreate();
            BuildersKt.launch$default(LifecycleKt.getCoroutineScope(viewLifecycleOwner.getLifecycle()), plus, null, new RepeatWhenAttachedKt$createLifecycleOwnerAndRun$1$1("repeatWhenAttached", function3, viewLifecycleOwner, view, null), 2);
            ref$ObjectRef.element = viewLifecycleOwner;
        }
        return new RepeatWhenAttachedKt$repeatWhenAttached$1(ref$ObjectRef, view, r1);
    }
}
