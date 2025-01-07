package androidx.compose.foundation;

import android.view.KeyEvent;
import androidx.collection.LongObjectMapKt;
import androidx.collection.MutableLongObjectMap;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.PlatformHapticFeedback;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.PointerInputModifierNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.StandaloneCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CombinedClickableNodeImpl extends AbstractClickableNode implements PointerInputModifierNode, CompositionLocalConsumerModifierNode {
    public final MutableLongObjectMap doubleKeyClickStates;
    public boolean hapticFeedbackEnabled;
    public final MutableLongObjectMap longKeyPressJobs;
    public Function0 onDoubleClick;
    public Function0 onLongClick;
    public String onLongClickLabel;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DoubleKeyClickState {
        public boolean doubleTapMinTimeMillisElapsed;
        public final StandaloneCoroutine job;

        public DoubleKeyClickState(StandaloneCoroutine standaloneCoroutine) {
            this.job = standaloneCoroutine;
        }
    }

    public CombinedClickableNodeImpl(IndicationNodeFactory indicationNodeFactory, MutableInteractionSource mutableInteractionSource, Role role, String str, String str2, Function0 function0, Function0 function02, Function0 function03, boolean z, boolean z2) {
        super(mutableInteractionSource, indicationNodeFactory, z2, str2, role, function0);
        this.onLongClickLabel = str;
        this.onLongClick = function02;
        this.onDoubleClick = function03;
        this.hapticFeedbackEnabled = z;
        int i = LongObjectMapKt.$r8$clinit;
        this.longKeyPressJobs = new MutableLongObjectMap(6);
        this.doubleKeyClickStates = new MutableLongObjectMap(6);
    }

    @Override // androidx.compose.foundation.AbstractClickableNode
    public final void applyAdditionalSemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        if (this.onLongClick != null) {
            String str = this.onLongClickLabel;
            Function0 function0 = new Function0() { // from class: androidx.compose.foundation.CombinedClickableNodeImpl$applyAdditionalSemantics$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Function0 function02 = CombinedClickableNodeImpl.this.onLongClick;
                    if (function02 != null) {
                        function02.invoke();
                    }
                    return Boolean.TRUE;
                }
            };
            KProperty[] kPropertyArr = SemanticsPropertiesKt.$$delegatedProperties;
            ((SemanticsConfiguration) semanticsPropertyReceiver).set(SemanticsActions.OnLongClick, new AccessibilityAction(str, function0));
        }
    }

    @Override // androidx.compose.foundation.AbstractClickableNode
    public final Object clickPointerInput(PointerInputScope pointerInputScope, Continuation continuation) {
        boolean z = this.enabled;
        Object detectTapGestures = TapGestureDetectorKt.detectTapGestures(pointerInputScope, continuation, (!z || this.onDoubleClick == null) ? null : new Function1() { // from class: androidx.compose.foundation.CombinedClickableNodeImpl$clickPointerInput$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                long j = ((Offset) obj).packedValue;
                Function0 function0 = CombinedClickableNodeImpl.this.onDoubleClick;
                if (function0 != null) {
                    function0.invoke();
                }
                return Unit.INSTANCE;
            }
        }, (!z || this.onLongClick == null) ? null : new Function1() { // from class: androidx.compose.foundation.CombinedClickableNodeImpl$clickPointerInput$3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                long j = ((Offset) obj).packedValue;
                Function0 function0 = CombinedClickableNodeImpl.this.onLongClick;
                if (function0 != null) {
                    function0.invoke();
                }
                CombinedClickableNodeImpl combinedClickableNodeImpl = CombinedClickableNodeImpl.this;
                if (combinedClickableNodeImpl.hapticFeedbackEnabled) {
                    ((PlatformHapticFeedback) ((HapticFeedback) CompositionLocalConsumerModifierNodeKt.currentValueOf(combinedClickableNodeImpl, CompositionLocalsKt.LocalHapticFeedback))).m445performHapticFeedbackCdsT49E(0);
                }
                return Unit.INSTANCE;
            }
        }, new Function1() { // from class: androidx.compose.foundation.CombinedClickableNodeImpl$clickPointerInput$5
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                long j = ((Offset) obj).packedValue;
                CombinedClickableNodeImpl combinedClickableNodeImpl = CombinedClickableNodeImpl.this;
                if (combinedClickableNodeImpl.enabled) {
                    combinedClickableNodeImpl.onClick.invoke();
                }
                return Unit.INSTANCE;
            }
        }, new CombinedClickableNodeImpl$clickPointerInput$4(this, null));
        return detectTapGestures == CoroutineSingletons.COROUTINE_SUSPENDED ? detectTapGestures : Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.AbstractClickableNode
    public final void onCancelKeyInput() {
        resetKeyPressState();
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x002f  */
    @Override // androidx.compose.foundation.AbstractClickableNode
    /* renamed from: onClickKeyDownEvent-ZmokQxo */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean mo12onClickKeyDownEventZmokQxo(android.view.KeyEvent r8) {
        /*
            r7 = this;
            long r0 = androidx.compose.ui.input.key.KeyEvent_androidKt.m448getKeyZmokQxo(r8)
            kotlin.jvm.functions.Function0 r8 = r7.onLongClick
            r2 = 0
            if (r8 == 0) goto L24
            androidx.collection.MutableLongObjectMap r8 = r7.longKeyPressJobs
            java.lang.Object r3 = r8.get(r0)
            if (r3 != 0) goto L24
            kotlinx.coroutines.CoroutineScope r3 = r7.getCoroutineScope()
            androidx.compose.foundation.CombinedClickableNodeImpl$onClickKeyDownEvent$1 r4 = new androidx.compose.foundation.CombinedClickableNodeImpl$onClickKeyDownEvent$1
            r4.<init>(r7, r2)
            r5 = 3
            kotlinx.coroutines.StandaloneCoroutine r3 = kotlinx.coroutines.BuildersKt.launch$default(r3, r2, r2, r4, r5)
            r8.set(r0, r3)
            r8 = 1
            goto L25
        L24:
            r8 = 0
        L25:
            androidx.collection.MutableLongObjectMap r3 = r7.doubleKeyClickStates
            java.lang.Object r4 = r3.get(r0)
            androidx.compose.foundation.CombinedClickableNodeImpl$DoubleKeyClickState r4 = (androidx.compose.foundation.CombinedClickableNodeImpl.DoubleKeyClickState) r4
            if (r4 == 0) goto L4a
            kotlinx.coroutines.StandaloneCoroutine r5 = r4.job
            boolean r6 = r5.isActive()
            if (r6 == 0) goto L47
            r5.cancel(r2)
            boolean r2 = r4.doubleTapMinTimeMillisElapsed
            if (r2 != 0) goto L4a
            kotlin.jvm.functions.Function0 r7 = r7.onClick
            r7.invoke()
            r3.remove(r0)
            goto L4a
        L47:
            r3.remove(r0)
        L4a:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.CombinedClickableNodeImpl.mo12onClickKeyDownEventZmokQxo(android.view.KeyEvent):boolean");
    }

    @Override // androidx.compose.foundation.AbstractClickableNode
    /* renamed from: onClickKeyUpEvent-ZmokQxo */
    public final void mo13onClickKeyUpEventZmokQxo(KeyEvent keyEvent) {
        Function0 function0;
        long m448getKeyZmokQxo = KeyEvent_androidKt.m448getKeyZmokQxo(keyEvent);
        MutableLongObjectMap mutableLongObjectMap = this.longKeyPressJobs;
        boolean z = false;
        if (mutableLongObjectMap.get(m448getKeyZmokQxo) != null) {
            Job job = (Job) mutableLongObjectMap.get(m448getKeyZmokQxo);
            if (job != null) {
                if (job.isActive()) {
                    job.cancel(null);
                } else {
                    z = true;
                }
            }
            mutableLongObjectMap.remove(m448getKeyZmokQxo);
        }
        if (this.onDoubleClick == null) {
            if (z) {
                return;
            }
            this.onClick.invoke();
            return;
        }
        MutableLongObjectMap mutableLongObjectMap2 = this.doubleKeyClickStates;
        if (mutableLongObjectMap2.get(m448getKeyZmokQxo) == null) {
            if (z) {
                return;
            }
            mutableLongObjectMap2.set(m448getKeyZmokQxo, new DoubleKeyClickState(BuildersKt.launch$default(getCoroutineScope(), null, null, new CombinedClickableNodeImpl$onClickKeyUpEvent$2(this, m448getKeyZmokQxo, null), 3)));
        } else {
            if (!z && (function0 = this.onDoubleClick) != null) {
                function0.invoke();
            }
            mutableLongObjectMap2.remove(m448getKeyZmokQxo);
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onReset() {
        resetKeyPressState();
    }

    public final void resetKeyPressState() {
        MutableLongObjectMap mutableLongObjectMap = this.longKeyPressJobs;
        Object[] objArr = mutableLongObjectMap.values;
        long[] jArr = mutableLongObjectMap.metadata;
        int length = jArr.length - 2;
        char c = 7;
        if (length >= 0) {
            int i = 0;
            while (true) {
                long j = jArr[i];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i2 = 8 - ((~(i - length)) >>> 31);
                    for (int i3 = 0; i3 < i2; i3++) {
                        if ((j & 255) < 128) {
                            ((Job) objArr[(i << 3) + i3]).cancel(null);
                        }
                        j >>= 8;
                    }
                    if (i2 != 8) {
                        break;
                    }
                }
                if (i == length) {
                    break;
                } else {
                    i++;
                }
            }
        }
        mutableLongObjectMap.clear();
        MutableLongObjectMap mutableLongObjectMap2 = this.doubleKeyClickStates;
        Object[] objArr2 = mutableLongObjectMap2.values;
        long[] jArr2 = mutableLongObjectMap2.metadata;
        int length2 = jArr2.length - 2;
        if (length2 >= 0) {
            int i4 = 0;
            while (true) {
                long j2 = jArr2[i4];
                if ((((~j2) << c) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i5 = 8 - ((~(i4 - length2)) >>> 31);
                    for (int i6 = 0; i6 < i5; i6++) {
                        if ((j2 & 255) < 128) {
                            ((DoubleKeyClickState) objArr2[(i4 << 3) + i6]).job.cancel(null);
                        }
                        j2 >>= 8;
                    }
                    if (i5 != 8) {
                        break;
                    }
                }
                if (i4 == length2) {
                    break;
                }
                i4++;
                c = 7;
            }
        }
        mutableLongObjectMap2.clear();
    }
}
