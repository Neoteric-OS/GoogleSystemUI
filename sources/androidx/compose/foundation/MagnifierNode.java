package androidx.compose.foundation;

import android.view.View;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatableNode_androidKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.GlobalPositionAwareModifierNode;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.channels.BufferedChannel;
import kotlinx.coroutines.channels.ChannelKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MagnifierNode extends Modifier.Node implements GlobalPositionAwareModifierNode, DrawModifierNode, SemanticsModifierNode, ObserverModifierNode {
    public State anchorPositionInRootState;
    public Density density;
    public BufferedChannel drawSignalChannel;
    public PlatformMagnifier magnifier;
    public Function1 onSizeChanged;
    public PlatformMagnifierFactory platformMagnifierFactory;
    public IntSize previousSize;
    public Function1 sourceCenter;
    public View view;
    public final MutableState layoutCoordinates$delegate = SnapshotStateKt.mutableStateOf(null, SnapshotStateKt.neverEqualPolicy());
    public long sourceCenterInRoot = 9205357640488583168L;

    public MagnifierNode(Function1 function1, Function1 function12, PlatformMagnifierFactory platformMagnifierFactory) {
        this.sourceCenter = function1;
        this.onSizeChanged = function12;
        this.platformMagnifierFactory = platformMagnifierFactory;
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public final void applySemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        ((SemanticsConfiguration) semanticsPropertyReceiver).set(Magnifier_androidKt.MagnifierPositionInRoot, new Function0() { // from class: androidx.compose.foundation.MagnifierNode$applySemantics$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return new Offset(MagnifierNode.this.sourceCenterInRoot);
            }
        });
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public final void draw(LayoutNodeDrawScope layoutNodeDrawScope) {
        layoutNodeDrawScope.drawContent();
        BufferedChannel bufferedChannel = this.drawSignalChannel;
        if (bufferedChannel != null) {
            bufferedChannel.mo1790trySendJP2dKIU(Unit.INSTANCE);
        }
    }

    /* renamed from: getAnchorPositionInRoot-F1C5BW0, reason: not valid java name */
    public final long m38getAnchorPositionInRootF1C5BW0() {
        if (this.anchorPositionInRootState == null) {
            this.anchorPositionInRootState = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.foundation.MagnifierNode$anchorPositionInRoot$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    LayoutCoordinates layoutCoordinates = (LayoutCoordinates) ((SnapshotMutableStateImpl) MagnifierNode.this.layoutCoordinates$delegate).getValue();
                    return new Offset(layoutCoordinates != null ? layoutCoordinates.mo484localToRootMKHz9U(0L) : 9205357640488583168L);
                }
            });
        }
        State state = this.anchorPositionInRootState;
        if (state != null) {
            return ((Offset) state.getValue()).packedValue;
        }
        return 9205357640488583168L;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onAttach() {
        onObservedReadsChanged();
        this.drawSignalChannel = ChannelKt.Channel$default(0, null, null, 7);
        BuildersKt.launch$default(getCoroutineScope(), null, null, new MagnifierNode$onAttach$1(this, null), 3);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onDetach() {
        PlatformMagnifier platformMagnifier = this.magnifier;
        if (platformMagnifier != null) {
            ((PlatformMagnifierFactoryApi28Impl$PlatformMagnifierImpl) platformMagnifier).magnifier.dismiss();
        }
        this.magnifier = null;
    }

    @Override // androidx.compose.ui.node.GlobalPositionAwareModifierNode
    public final void onGloballyPositioned(NodeCoordinator nodeCoordinator) {
        ((SnapshotMutableStateImpl) this.layoutCoordinates$delegate).setValue(nodeCoordinator);
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public final void onObservedReadsChanged() {
        ObserverModifierNodeKt.observeReads(this, new Function0() { // from class: androidx.compose.foundation.MagnifierNode$onObservedReadsChanged$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                MagnifierNode.this.updateMagnifier();
                return Unit.INSTANCE;
            }
        });
    }

    public final void recreateMagnifier() {
        PlatformMagnifier platformMagnifier = this.magnifier;
        if (platformMagnifier != null) {
            ((PlatformMagnifierFactoryApi28Impl$PlatformMagnifierImpl) platformMagnifier).magnifier.dismiss();
        }
        View view = this.view;
        if (view == null) {
            view = DelegatableNode_androidKt.requireView(this);
        }
        this.view = view;
        Density density = this.density;
        if (density == null) {
            density = DelegatableNodeKt.requireLayoutNode(this).density;
        }
        this.density = density;
        this.magnifier = this.platformMagnifierFactory.mo40createnHHXs2Y(view, density);
        updateSizeIfNecessary();
    }

    public final void updateMagnifier() {
        Density density = this.density;
        if (density == null) {
            density = DelegatableNodeKt.requireLayoutNode(this).density;
            this.density = density;
        }
        long j = ((Offset) this.sourceCenter.invoke(density)).packedValue;
        if ((j & 9223372034707292159L) == 9205357640488583168L || (9223372034707292159L & m38getAnchorPositionInRootF1C5BW0()) == 9205357640488583168L) {
            this.sourceCenterInRoot = 9205357640488583168L;
            PlatformMagnifier platformMagnifier = this.magnifier;
            if (platformMagnifier != null) {
                ((PlatformMagnifierFactoryApi28Impl$PlatformMagnifierImpl) platformMagnifier).magnifier.dismiss();
                return;
            }
            return;
        }
        this.sourceCenterInRoot = Offset.m315plusMKHz9U(m38getAnchorPositionInRootF1C5BW0(), j);
        if (this.magnifier == null) {
            recreateMagnifier();
        }
        PlatformMagnifier platformMagnifier2 = this.magnifier;
        if (platformMagnifier2 != null) {
            platformMagnifier2.mo39updateWko1d7g(this.sourceCenterInRoot, 9205357640488583168L);
        }
        updateSizeIfNecessary();
    }

    public final void updateSizeIfNecessary() {
        Density density;
        PlatformMagnifier platformMagnifier = this.magnifier;
        if (platformMagnifier == null || (density = this.density) == null) {
            return;
        }
        PlatformMagnifierFactoryApi28Impl$PlatformMagnifierImpl platformMagnifierFactoryApi28Impl$PlatformMagnifierImpl = (PlatformMagnifierFactoryApi28Impl$PlatformMagnifierImpl) platformMagnifier;
        if (IntSize.m682equalsimpl(platformMagnifierFactoryApi28Impl$PlatformMagnifierImpl.m41getSizeYbymL2g(), this.previousSize)) {
            return;
        }
        this.onSizeChanged.invoke(new DpSize(density.mo49toDpSizekrfVVM(IntSizeKt.m685toSizeozmzZPI(platformMagnifierFactoryApi28Impl$PlatformMagnifierImpl.m41getSizeYbymL2g()))));
        this.previousSize = new IntSize(platformMagnifierFactoryApi28Impl$PlatformMagnifierImpl.m41getSizeYbymL2g());
    }
}
