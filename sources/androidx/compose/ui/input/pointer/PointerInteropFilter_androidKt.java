package androidx.compose.ui.input.pointer;

import android.view.MotionEvent;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.viewinterop.AndroidViewHolder;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PointerInteropFilter_androidKt {
    public static final Modifier motionEventSpy(Modifier modifier, final Function1 function1) {
        return SuspendingPointerInputFilterKt.pointerInput(modifier, function1, new PointerInputEventHandler() { // from class: androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt$motionEventSpy$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt$motionEventSpy$1$1, reason: invalid class name */
            final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2 {
                final /* synthetic */ Function1 $watcher;
                private /* synthetic */ Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass1(Continuation continuation, Function1 function1) {
                    super(continuation);
                    this.$watcher = function1;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(continuation, this.$watcher);
                    anonymousClass1.L$0 = obj;
                    return anonymousClass1;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((AnonymousClass1) create((AwaitPointerEventScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                    return CoroutineSingletons.COROUTINE_SUSPENDED;
                }

                /* JADX WARN: Removed duplicated region for block: B:11:0x002e A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:13:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:7:0x003a  */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x002f -> B:5:0x0032). Please report as a decompilation issue!!! */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r6) {
                    /*
                        r5 = this;
                        kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r1 = r5.label
                        r2 = 1
                        if (r1 == 0) goto L19
                        if (r1 != r2) goto L11
                        java.lang.Object r1 = r5.L$0
                        androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L32
                    L11:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L19:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.lang.Object r6 = r5.L$0
                        androidx.compose.ui.input.pointer.AwaitPointerEventScope r6 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r6
                    L20:
                        androidx.compose.ui.input.pointer.PointerEventPass r1 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                        r5.L$0 = r6
                        r5.label = r2
                        androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine r6 = (androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl.PointerEventHandlerCoroutine) r6
                        java.lang.Object r1 = r6.awaitPointerEvent(r1, r5)
                        if (r1 != r0) goto L2f
                        return r0
                    L2f:
                        r4 = r1
                        r1 = r6
                        r6 = r4
                    L32:
                        androidx.compose.ui.input.pointer.PointerEvent r6 = (androidx.compose.ui.input.pointer.PointerEvent) r6
                        android.view.MotionEvent r6 = r6.getMotionEvent$ui_release()
                        if (r6 == 0) goto L3f
                        kotlin.jvm.functions.Function1 r3 = r5.$watcher
                        r3.invoke(r6)
                    L3f:
                        r6 = r1
                        goto L20
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt$motionEventSpy$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
            public final Object invoke(PointerInputScope pointerInputScope, Continuation continuation) {
                pointerInputScope.setInterceptOutOfBoundsChildEvents();
                Object awaitPointerEventScope = pointerInputScope.awaitPointerEventScope(continuation, new AnonymousClass1(null, Function1.this));
                return awaitPointerEventScope == CoroutineSingletons.COROUTINE_SUSPENDED ? awaitPointerEventScope : Unit.INSTANCE;
            }
        });
    }

    public static final Modifier pointerInteropFilter(Modifier modifier, final AndroidViewHolder androidViewHolder) {
        PointerInteropFilter pointerInteropFilter = new PointerInteropFilter();
        pointerInteropFilter.onTouchEvent = new Function1() { // from class: androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt$pointerInteropFilter$3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                boolean dispatchTouchEvent;
                MotionEvent motionEvent = (MotionEvent) obj;
                switch (motionEvent.getActionMasked()) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        dispatchTouchEvent = AndroidViewHolder.this.dispatchTouchEvent(motionEvent);
                        break;
                    default:
                        dispatchTouchEvent = AndroidViewHolder.this.dispatchGenericMotionEvent(motionEvent);
                        break;
                }
                return Boolean.valueOf(dispatchTouchEvent);
            }
        };
        RequestDisallowInterceptTouchEvent requestDisallowInterceptTouchEvent = new RequestDisallowInterceptTouchEvent();
        RequestDisallowInterceptTouchEvent requestDisallowInterceptTouchEvent2 = pointerInteropFilter.requestDisallowInterceptTouchEvent;
        if (requestDisallowInterceptTouchEvent2 != null) {
            requestDisallowInterceptTouchEvent2.pointerInteropFilter = null;
        }
        pointerInteropFilter.requestDisallowInterceptTouchEvent = requestDisallowInterceptTouchEvent;
        requestDisallowInterceptTouchEvent.pointerInteropFilter = pointerInteropFilter;
        androidViewHolder.onRequestDisallowInterceptTouchEvent = requestDisallowInterceptTouchEvent;
        return modifier.then(pointerInteropFilter);
    }

    public static Modifier pointerInteropFilter$default(Modifier modifier, final Function1 function1) {
        Function1 function12 = InspectableValueKt.NoInspectorInfo;
        return ComposedModifierKt.composed(modifier, new Function3() { // from class: androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt$pointerInteropFilter$2
            final /* synthetic */ RequestDisallowInterceptTouchEvent $requestDisallowInterceptTouchEvent = null;

            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                ((Number) obj3).intValue();
                ComposerImpl composerImpl = (ComposerImpl) ((Composer) obj2);
                composerImpl.startReplaceGroup(374375707);
                OpaqueKey opaqueKey = ComposerKt.invocation;
                Object rememberedValue = composerImpl.rememberedValue();
                if (rememberedValue == Composer.Companion.Empty) {
                    rememberedValue = new PointerInteropFilter();
                    composerImpl.updateRememberedValue(rememberedValue);
                }
                PointerInteropFilter pointerInteropFilter = (PointerInteropFilter) rememberedValue;
                pointerInteropFilter.onTouchEvent = Function1.this;
                RequestDisallowInterceptTouchEvent requestDisallowInterceptTouchEvent = this.$requestDisallowInterceptTouchEvent;
                RequestDisallowInterceptTouchEvent requestDisallowInterceptTouchEvent2 = pointerInteropFilter.requestDisallowInterceptTouchEvent;
                if (requestDisallowInterceptTouchEvent2 != null) {
                    requestDisallowInterceptTouchEvent2.pointerInteropFilter = null;
                }
                pointerInteropFilter.requestDisallowInterceptTouchEvent = requestDisallowInterceptTouchEvent;
                if (requestDisallowInterceptTouchEvent != null) {
                    requestDisallowInterceptTouchEvent.pointerInteropFilter = pointerInteropFilter;
                }
                composerImpl.end(false);
                return pointerInteropFilter;
            }
        });
    }
}
