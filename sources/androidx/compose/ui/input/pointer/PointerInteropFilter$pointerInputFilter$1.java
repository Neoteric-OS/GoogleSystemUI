package androidx.compose.ui.input.pointer;

import android.os.SystemClock;
import android.view.MotionEvent;
import androidx.compose.ui.input.pointer.PointerInteropFilter;
import androidx.compose.ui.layout.LayoutCoordinates;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PointerInteropFilter$pointerInputFilter$1 extends PointerInputFilter {
    public PointerInteropFilter.DispatchToViewState state = PointerInteropFilter.DispatchToViewState.Unknown;
    public final /* synthetic */ PointerInteropFilter this$0;

    public PointerInteropFilter$pointerInputFilter$1(PointerInteropFilter pointerInteropFilter) {
        this.this$0 = pointerInteropFilter;
    }

    public final void dispatchToView(PointerEvent pointerEvent) {
        List list = pointerEvent.changes;
        int size = list.size();
        int i = 0;
        while (true) {
            PointerInteropFilter.DispatchToViewState dispatchToViewState = PointerInteropFilter.DispatchToViewState.Dispatching;
            final PointerInteropFilter pointerInteropFilter = this.this$0;
            if (i >= size) {
                LayoutCoordinates layoutCoordinates = this.layoutCoordinates;
                if (layoutCoordinates == null) {
                    throw new IllegalStateException("layoutCoordinates not set");
                }
                PointerInteropUtils_androidKt.m467toMotionEventScopeubNVwUQ(pointerEvent, layoutCoordinates.mo484localToRootMKHz9U(0L), new Function1() { // from class: androidx.compose.ui.input.pointer.PointerInteropFilter$pointerInputFilter$1$dispatchToView$3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        MotionEvent motionEvent = (MotionEvent) obj;
                        if (motionEvent.getActionMasked() == 0) {
                            PointerInteropFilter$pointerInputFilter$1 pointerInteropFilter$pointerInputFilter$1 = PointerInteropFilter$pointerInputFilter$1.this;
                            Function1 function1 = pointerInteropFilter.onTouchEvent;
                            pointerInteropFilter$pointerInputFilter$1.state = ((Boolean) (function1 != null ? function1 : null).invoke(motionEvent)).booleanValue() ? PointerInteropFilter.DispatchToViewState.Dispatching : PointerInteropFilter.DispatchToViewState.NotDispatching;
                        } else {
                            Function1 function12 = pointerInteropFilter.onTouchEvent;
                            (function12 != null ? function12 : null).invoke(motionEvent);
                        }
                        return Unit.INSTANCE;
                    }
                }, false);
                if (this.state == dispatchToViewState) {
                    int size2 = list.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        ((PointerInputChange) list.get(i2)).consume();
                    }
                    InternalPointerEvent internalPointerEvent = pointerEvent.internalPointerEvent;
                    if (internalPointerEvent == null) {
                        return;
                    }
                    internalPointerEvent.suppressMovementConsumption = !pointerInteropFilter.disallowIntercept;
                    return;
                }
                return;
            }
            if (((PointerInputChange) list.get(i)).isConsumed()) {
                if (this.state == dispatchToViewState) {
                    LayoutCoordinates layoutCoordinates2 = this.layoutCoordinates;
                    if (layoutCoordinates2 == null) {
                        throw new IllegalStateException("layoutCoordinates not set");
                    }
                    PointerInteropUtils_androidKt.m467toMotionEventScopeubNVwUQ(pointerEvent, layoutCoordinates2.mo484localToRootMKHz9U(0L), new Function1() { // from class: androidx.compose.ui.input.pointer.PointerInteropFilter$pointerInputFilter$1$dispatchToView$2
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            MotionEvent motionEvent = (MotionEvent) obj;
                            Function1 function1 = PointerInteropFilter.this.onTouchEvent;
                            if (function1 == null) {
                                function1 = null;
                            }
                            function1.invoke(motionEvent);
                            return Unit.INSTANCE;
                        }
                    }, true);
                }
                this.state = PointerInteropFilter.DispatchToViewState.NotDispatching;
                return;
            }
            i++;
        }
    }

    public final void onCancel() {
        if (this.state == PointerInteropFilter.DispatchToViewState.Dispatching) {
            long uptimeMillis = SystemClock.uptimeMillis();
            final PointerInteropFilter pointerInteropFilter = this.this$0;
            Function1 function1 = new Function1() { // from class: androidx.compose.ui.input.pointer.PointerInteropFilter$pointerInputFilter$1$onCancel$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    MotionEvent motionEvent = (MotionEvent) obj;
                    Function1 function12 = PointerInteropFilter.this.onTouchEvent;
                    if (function12 == null) {
                        function12 = null;
                    }
                    function12.invoke(motionEvent);
                    return Unit.INSTANCE;
                }
            };
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            obtain.setSource(0);
            function1.invoke(obtain);
            obtain.recycle();
            this.state = PointerInteropFilter.DispatchToViewState.Unknown;
            pointerInteropFilter.disallowIntercept = false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /* renamed from: onPointerEvent-H0pRuoY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m466onPointerEventH0pRuoY(androidx.compose.ui.input.pointer.PointerEvent r8, androidx.compose.ui.input.pointer.PointerEventPass r9) {
        /*
            r7 = this;
            java.util.List r0 = r8.changes
            androidx.compose.ui.input.pointer.PointerInteropFilter r1 = r7.this$0
            boolean r2 = r1.disallowIntercept
            r3 = 0
            if (r2 != 0) goto L28
            int r2 = r0.size()
            r4 = r3
        Le:
            if (r4 >= r2) goto L26
            java.lang.Object r5 = r0.get(r4)
            androidx.compose.ui.input.pointer.PointerInputChange r5 = (androidx.compose.ui.input.pointer.PointerInputChange) r5
            boolean r6 = androidx.compose.ui.input.pointer.PointerEventKt.changedToDownIgnoreConsumed(r5)
            if (r6 != 0) goto L28
            boolean r5 = androidx.compose.ui.input.pointer.PointerEventKt.changedToUpIgnoreConsumed(r5)
            if (r5 == 0) goto L23
            goto L28
        L23:
            int r4 = r4 + 1
            goto Le
        L26:
            r2 = r3
            goto L29
        L28:
            r2 = 1
        L29:
            androidx.compose.ui.input.pointer.PointerInteropFilter$DispatchToViewState r4 = r7.state
            androidx.compose.ui.input.pointer.PointerInteropFilter$DispatchToViewState r5 = androidx.compose.ui.input.pointer.PointerInteropFilter.DispatchToViewState.NotDispatching
            androidx.compose.ui.input.pointer.PointerEventPass r6 = androidx.compose.ui.input.pointer.PointerEventPass.Final
            if (r4 == r5) goto L41
            androidx.compose.ui.input.pointer.PointerEventPass r4 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
            if (r9 != r4) goto L3a
            if (r2 == 0) goto L3a
            r7.dispatchToView(r8)
        L3a:
            if (r9 != r6) goto L41
            if (r2 != 0) goto L41
            r7.dispatchToView(r8)
        L41:
            if (r9 != r6) goto L60
            int r8 = r0.size()
            r9 = r3
        L48:
            if (r9 >= r8) goto L5a
            java.lang.Object r2 = r0.get(r9)
            androidx.compose.ui.input.pointer.PointerInputChange r2 = (androidx.compose.ui.input.pointer.PointerInputChange) r2
            boolean r2 = androidx.compose.ui.input.pointer.PointerEventKt.changedToUpIgnoreConsumed(r2)
            if (r2 != 0) goto L57
            goto L60
        L57:
            int r9 = r9 + 1
            goto L48
        L5a:
            androidx.compose.ui.input.pointer.PointerInteropFilter$DispatchToViewState r8 = androidx.compose.ui.input.pointer.PointerInteropFilter.DispatchToViewState.Unknown
            r7.state = r8
            r1.disallowIntercept = r3
        L60:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.pointer.PointerInteropFilter$pointerInputFilter$1.m466onPointerEventH0pRuoY(androidx.compose.ui.input.pointer.PointerEvent, androidx.compose.ui.input.pointer.PointerEventPass):void");
    }
}
