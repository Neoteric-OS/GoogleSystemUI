package androidx.compose.foundation;

import android.view.KeyEvent;
import androidx.collection.LongObjectMapKt;
import androidx.collection.MutableLongObjectMap;
import androidx.compose.foundation.interaction.HoverInteraction$Enter;
import androidx.compose.foundation.interaction.HoverInteraction$Exit;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction$Cancel;
import androidx.compose.foundation.interaction.PressInteraction$Press;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierNode;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerEventType;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.PointerInputModifierNode;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AbstractClickableNode extends DelegatingNode implements PointerInputModifierNode, KeyInputModifierNode, SemanticsModifierNode, TraversableNode {
    public static final TraverseKey TraverseKey = new TraverseKey();
    public long centerOffset;
    public final MutableLongObjectMap currentKeyPressInteractions;
    public boolean enabled;
    public final FocusableNode focusableNode;
    public HoverInteraction$Enter hoverInteraction;
    public DelegatableNode indicationNode;
    public IndicationNodeFactory indicationNodeFactory;
    public MutableInteractionSource interactionSource;
    public boolean lazilyCreateIndication;
    public Function0 onClick;
    public String onClickLabel;
    public SuspendingPointerInputModifierNode pointerInputNode;
    public PressInteraction$Press pressInteraction;
    public Role role;
    public final TraverseKey traverseKey;
    public MutableInteractionSource userProvidedInteractionSource;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TraverseKey {
    }

    public AbstractClickableNode(MutableInteractionSource mutableInteractionSource, IndicationNodeFactory indicationNodeFactory, boolean z, String str, Role role, Function0 function0) {
        this.interactionSource = mutableInteractionSource;
        this.indicationNodeFactory = indicationNodeFactory;
        this.onClickLabel = str;
        this.role = role;
        this.enabled = z;
        this.onClick = function0;
        boolean z2 = false;
        this.focusableNode = new FocusableNode(mutableInteractionSource, 0, new AbstractClickableNode$focusableNode$1(1, this, AbstractClickableNode.class, "onFocusChange", "onFocusChange(Z)V", 0));
        int i = LongObjectMapKt.$r8$clinit;
        this.currentKeyPressInteractions = new MutableLongObjectMap(6);
        this.centerOffset = 0L;
        MutableInteractionSource mutableInteractionSource2 = this.interactionSource;
        this.userProvidedInteractionSource = mutableInteractionSource2;
        if (mutableInteractionSource2 == null && this.indicationNodeFactory != null) {
            z2 = true;
        }
        this.lazilyCreateIndication = z2;
        this.traverseKey = TraverseKey;
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public final void applySemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        Role role = this.role;
        if (role != null) {
            SemanticsPropertiesKt.m577setRolekuIjeqM(semanticsPropertyReceiver, role.value);
        }
        SemanticsPropertiesKt.onClick(semanticsPropertyReceiver, this.onClickLabel, new Function0() { // from class: androidx.compose.foundation.AbstractClickableNode$applySemantics$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                AbstractClickableNode.this.onClick.invoke();
                return Boolean.TRUE;
            }
        });
        if (this.enabled) {
            this.focusableNode.applySemantics(semanticsPropertyReceiver);
        } else {
            SemanticsPropertiesKt.disabled(semanticsPropertyReceiver);
        }
        applyAdditionalSemantics(semanticsPropertyReceiver);
    }

    public abstract Object clickPointerInput(PointerInputScope pointerInputScope, Continuation continuation);

    public final void disposeInteractions() {
        MutableInteractionSource mutableInteractionSource = this.interactionSource;
        MutableLongObjectMap mutableLongObjectMap = this.currentKeyPressInteractions;
        if (mutableInteractionSource != null) {
            PressInteraction$Press pressInteraction$Press = this.pressInteraction;
            if (pressInteraction$Press != null) {
                mutableInteractionSource.tryEmit(new PressInteraction$Cancel(pressInteraction$Press));
            }
            HoverInteraction$Enter hoverInteraction$Enter = this.hoverInteraction;
            if (hoverInteraction$Enter != null) {
                mutableInteractionSource.tryEmit(new HoverInteraction$Exit(hoverInteraction$Enter));
            }
            Object[] objArr = mutableLongObjectMap.values;
            long[] jArr = mutableLongObjectMap.metadata;
            int length = jArr.length - 2;
            if (length >= 0) {
                int i = 0;
                while (true) {
                    long j = jArr[i];
                    if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i2 = 8 - ((~(i - length)) >>> 31);
                        for (int i3 = 0; i3 < i2; i3++) {
                            if ((255 & j) < 128) {
                                mutableInteractionSource.tryEmit(new PressInteraction$Cancel((PressInteraction$Press) objArr[(i << 3) + i3]));
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
        }
        this.pressInteraction = null;
        this.hoverInteraction = null;
        mutableLongObjectMap.clear();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final boolean getShouldAutoInvalidate() {
        return false;
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public final boolean getShouldMergeDescendantSemantics() {
        return true;
    }

    @Override // androidx.compose.ui.node.TraversableNode
    public final Object getTraverseKey() {
        return this.traverseKey;
    }

    public final void initializeIndicationAndInteractionSourceIfNeeded() {
        IndicationNodeFactory indicationNodeFactory;
        if (this.indicationNode == null && (indicationNodeFactory = this.indicationNodeFactory) != null) {
            if (this.interactionSource == null) {
                this.interactionSource = InteractionSourceKt.MutableInteractionSource();
            }
            this.focusableNode.update(this.interactionSource);
            MutableInteractionSource mutableInteractionSource = this.interactionSource;
            Intrinsics.checkNotNull(mutableInteractionSource);
            DelegatableNode create = indicationNodeFactory.create(mutableInteractionSource);
            delegate(create);
            this.indicationNode = create;
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onAttach() {
        if (!this.lazilyCreateIndication) {
            initializeIndicationAndInteractionSourceIfNeeded();
        }
        if (this.enabled) {
            delegate(this.focusableNode);
        }
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final void onCancelPointerInput() {
        HoverInteraction$Enter hoverInteraction$Enter;
        MutableInteractionSource mutableInteractionSource = this.interactionSource;
        if (mutableInteractionSource != null && (hoverInteraction$Enter = this.hoverInteraction) != null) {
            mutableInteractionSource.tryEmit(new HoverInteraction$Exit(hoverInteraction$Enter));
        }
        this.hoverInteraction = null;
        SuspendingPointerInputModifierNode suspendingPointerInputModifierNode = this.pointerInputNode;
        if (suspendingPointerInputModifierNode != null) {
            ((SuspendingPointerInputModifierNodeImpl) suspendingPointerInputModifierNode).onCancelPointerInput();
        }
    }

    /* renamed from: onClickKeyDownEvent-ZmokQxo, reason: not valid java name */
    public abstract boolean mo12onClickKeyDownEventZmokQxo(KeyEvent keyEvent);

    /* renamed from: onClickKeyUpEvent-ZmokQxo, reason: not valid java name */
    public abstract void mo13onClickKeyUpEventZmokQxo(KeyEvent keyEvent);

    @Override // androidx.compose.ui.Modifier.Node
    public final void onDetach() {
        disposeInteractions();
        if (this.userProvidedInteractionSource == null) {
            this.interactionSource = null;
        }
        DelegatableNode delegatableNode = this.indicationNode;
        if (delegatableNode != null) {
            undelegate(delegatableNode);
        }
        this.indicationNode = null;
    }

    @Override // androidx.compose.ui.input.key.KeyInputModifierNode
    /* renamed from: onKeyEvent-ZmokQxo, reason: not valid java name */
    public final boolean mo14onKeyEventZmokQxo(KeyEvent keyEvent) {
        boolean z;
        initializeIndicationAndInteractionSourceIfNeeded();
        long m448getKeyZmokQxo = KeyEvent_androidKt.m448getKeyZmokQxo(keyEvent);
        boolean z2 = this.enabled;
        MutableLongObjectMap mutableLongObjectMap = this.currentKeyPressInteractions;
        if (z2) {
            int i = Clickable_androidKt.$r8$clinit;
            if (KeyEventType.m447equalsimpl0(KeyEvent_androidKt.m449getTypeZmokQxo(keyEvent), 2) && Clickable_androidKt.m36isEnterZmokQxo(keyEvent)) {
                if (mutableLongObjectMap.containsKey(m448getKeyZmokQxo)) {
                    z = false;
                } else {
                    PressInteraction$Press pressInteraction$Press = new PressInteraction$Press(this.centerOffset);
                    mutableLongObjectMap.set(m448getKeyZmokQxo, pressInteraction$Press);
                    if (this.interactionSource != null) {
                        BuildersKt.launch$default(getCoroutineScope(), null, null, new AbstractClickableNode$onKeyEvent$1(this, pressInteraction$Press, null), 3);
                    }
                    z = true;
                }
                if (mo12onClickKeyDownEventZmokQxo(keyEvent) || z) {
                    return true;
                }
                return false;
            }
        }
        if (this.enabled) {
            int i2 = Clickable_androidKt.$r8$clinit;
            if (KeyEventType.m447equalsimpl0(KeyEvent_androidKt.m449getTypeZmokQxo(keyEvent), 1) && Clickable_androidKt.m36isEnterZmokQxo(keyEvent)) {
                PressInteraction$Press pressInteraction$Press2 = (PressInteraction$Press) mutableLongObjectMap.remove(m448getKeyZmokQxo);
                if (pressInteraction$Press2 != null) {
                    if (this.interactionSource != null) {
                        BuildersKt.launch$default(getCoroutineScope(), null, null, new AbstractClickableNode$onKeyEvent$2(this, pressInteraction$Press2, null), 3);
                    }
                    mo13onClickKeyUpEventZmokQxo(keyEvent);
                }
                if (pressInteraction$Press2 != null) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    /* renamed from: onPointerEvent-H0pRuoY, reason: not valid java name */
    public final void mo15onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pointerEventPass, long j) {
        long j2 = ((j >> 33) << 32) | (((j << 32) >> 33) & 4294967295L);
        this.centerOffset = (Float.floatToRawIntBits((int) (j2 >> 32)) << 32) | (Float.floatToRawIntBits((int) (j2 & 4294967295L)) & 4294967295L);
        initializeIndicationAndInteractionSourceIfNeeded();
        if (this.enabled && pointerEventPass == PointerEventPass.Main) {
            int i = pointerEvent.type;
            if (PointerEventType.m461equalsimpl0(i, 4)) {
                BuildersKt.launch$default(getCoroutineScope(), null, null, new AbstractClickableNode$onPointerEvent$1(this, null), 3);
            } else if (PointerEventType.m461equalsimpl0(i, 5)) {
                BuildersKt.launch$default(getCoroutineScope(), null, null, new AbstractClickableNode$onPointerEvent$2(this, null), 3);
            }
        }
        if (this.pointerInputNode == null) {
            SuspendingPointerInputModifierNodeImpl SuspendingPointerInputModifierNode = SuspendingPointerInputFilterKt.SuspendingPointerInputModifierNode(new PointerInputEventHandler() { // from class: androidx.compose.foundation.AbstractClickableNode$onPointerEvent$3
                @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                public final Object invoke(PointerInputScope pointerInputScope, Continuation continuation) {
                    Object clickPointerInput = AbstractClickableNode.this.clickPointerInput(pointerInputScope, continuation);
                    return clickPointerInput == CoroutineSingletons.COROUTINE_SUSPENDED ? clickPointerInput : Unit.INSTANCE;
                }
            });
            delegate(SuspendingPointerInputModifierNode);
            this.pointerInputNode = SuspendingPointerInputModifierNode;
        }
        SuspendingPointerInputModifierNode suspendingPointerInputModifierNode = this.pointerInputNode;
        if (suspendingPointerInputModifierNode != null) {
            ((SuspendingPointerInputModifierNodeImpl) suspendingPointerInputModifierNode).mo15onPointerEventH0pRuoY(pointerEvent, pointerEventPass, j);
        }
    }

    @Override // androidx.compose.ui.input.key.KeyInputModifierNode
    /* renamed from: onPreKeyEvent-ZmokQxo, reason: not valid java name */
    public final boolean mo16onPreKeyEventZmokQxo(KeyEvent keyEvent) {
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x006e, code lost:
    
        if (r3.indicationNode == null) goto L38;
     */
    /* renamed from: updateCommon-QzZPfjk, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m17updateCommonQzZPfjk(androidx.compose.foundation.interaction.MutableInteractionSource r4, androidx.compose.foundation.IndicationNodeFactory r5, boolean r6, java.lang.String r7, androidx.compose.ui.semantics.Role r8, kotlin.jvm.functions.Function0 r9) {
        /*
            r3 = this;
            androidx.compose.foundation.interaction.MutableInteractionSource r0 = r3.userProvidedInteractionSource
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r4)
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L13
            r3.disposeInteractions()
            r3.userProvidedInteractionSource = r4
            r3.interactionSource = r4
            r4 = r2
            goto L14
        L13:
            r4 = r1
        L14:
            androidx.compose.foundation.IndicationNodeFactory r0 = r3.indicationNodeFactory
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r5)
            if (r0 != 0) goto L1f
            r3.indicationNodeFactory = r5
            r4 = r2
        L1f:
            boolean r5 = r3.enabled
            androidx.compose.foundation.FocusableNode r0 = r3.focusableNode
            if (r5 == r6) goto L36
            if (r6 == 0) goto L2b
            r3.delegate(r0)
            goto L31
        L2b:
            r3.undelegate(r0)
            r3.disposeInteractions()
        L31:
            androidx.compose.ui.node.SemanticsModifierNodeKt.invalidateSemantics(r3)
            r3.enabled = r6
        L36:
            java.lang.String r5 = r3.onClickLabel
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r7)
            if (r5 != 0) goto L43
            r3.onClickLabel = r7
            androidx.compose.ui.node.SemanticsModifierNodeKt.invalidateSemantics(r3)
        L43:
            androidx.compose.ui.semantics.Role r5 = r3.role
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r8)
            if (r5 != 0) goto L50
            r3.role = r8
            androidx.compose.ui.node.SemanticsModifierNodeKt.invalidateSemantics(r3)
        L50:
            r3.onClick = r9
            boolean r5 = r3.lazilyCreateIndication
            androidx.compose.foundation.interaction.MutableInteractionSource r6 = r3.userProvidedInteractionSource
            if (r6 != 0) goto L5e
            androidx.compose.foundation.IndicationNodeFactory r7 = r3.indicationNodeFactory
            if (r7 == 0) goto L5e
            r7 = r2
            goto L5f
        L5e:
            r7 = r1
        L5f:
            if (r5 == r7) goto L71
            if (r6 != 0) goto L68
            androidx.compose.foundation.IndicationNodeFactory r5 = r3.indicationNodeFactory
            if (r5 == 0) goto L68
            r1 = r2
        L68:
            r3.lazilyCreateIndication = r1
            if (r1 != 0) goto L71
            androidx.compose.ui.node.DelegatableNode r5 = r3.indicationNode
            if (r5 != 0) goto L71
            goto L72
        L71:
            r2 = r4
        L72:
            if (r2 == 0) goto L87
            androidx.compose.ui.node.DelegatableNode r4 = r3.indicationNode
            if (r4 != 0) goto L7c
            boolean r5 = r3.lazilyCreateIndication
            if (r5 != 0) goto L87
        L7c:
            if (r4 == 0) goto L81
            r3.undelegate(r4)
        L81:
            r4 = 0
            r3.indicationNode = r4
            r3.initializeIndicationAndInteractionSourceIfNeeded()
        L87:
            androidx.compose.foundation.interaction.MutableInteractionSource r3 = r3.interactionSource
            r0.update(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.AbstractClickableNode.m17updateCommonQzZPfjk(androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.foundation.IndicationNodeFactory, boolean, java.lang.String, androidx.compose.ui.semantics.Role, kotlin.jvm.functions.Function0):void");
    }

    public void onCancelKeyInput() {
    }

    public void applyAdditionalSemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
    }
}
