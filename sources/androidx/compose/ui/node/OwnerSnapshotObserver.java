package androidx.compose.ui.node;

import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class OwnerSnapshotObserver {
    public final SnapshotStateObserver observer;
    public final Function1 onCommitAffectingLookaheadMeasure = new Function1() { // from class: androidx.compose.ui.node.OwnerSnapshotObserver$onCommitAffectingLookaheadMeasure$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            LayoutNode layoutNode = (LayoutNode) obj;
            if (layoutNode.isAttached()) {
                LayoutNode.requestLookaheadRemeasure$ui_release$default(layoutNode, false, 7);
            }
            return Unit.INSTANCE;
        }
    };
    public final Function1 onCommitAffectingMeasure = new Function1() { // from class: androidx.compose.ui.node.OwnerSnapshotObserver$onCommitAffectingMeasure$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            LayoutNode layoutNode = (LayoutNode) obj;
            if (layoutNode.isAttached()) {
                LayoutNode.requestRemeasure$ui_release$default(layoutNode, false, 7);
            }
            return Unit.INSTANCE;
        }
    };
    public final Function1 onCommitAffectingSemantics = new Function1() { // from class: androidx.compose.ui.node.OwnerSnapshotObserver$onCommitAffectingSemantics$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            LayoutNode layoutNode = (LayoutNode) obj;
            if (layoutNode.isAttached()) {
                layoutNode.invalidateSemantics$ui_release();
            }
            return Unit.INSTANCE;
        }
    };
    public final Function1 onCommitAffectingLayout = new Function1() { // from class: androidx.compose.ui.node.OwnerSnapshotObserver$onCommitAffectingLayout$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            LayoutNode layoutNode = (LayoutNode) obj;
            if (layoutNode.isAttached()) {
                layoutNode.requestRelayout$ui_release(false);
            }
            return Unit.INSTANCE;
        }
    };
    public final Function1 onCommitAffectingLayoutModifier = new Function1() { // from class: androidx.compose.ui.node.OwnerSnapshotObserver$onCommitAffectingLayoutModifier$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            LayoutNode layoutNode = (LayoutNode) obj;
            if (layoutNode.isAttached()) {
                layoutNode.requestRelayout$ui_release(false);
            }
            return Unit.INSTANCE;
        }
    };
    public final Function1 onCommitAffectingLayoutModifierInLookahead = new Function1() { // from class: androidx.compose.ui.node.OwnerSnapshotObserver$onCommitAffectingLayoutModifierInLookahead$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            LayoutNode layoutNode = (LayoutNode) obj;
            if (layoutNode.isAttached()) {
                layoutNode.requestLookaheadRelayout$ui_release(false);
            }
            return Unit.INSTANCE;
        }
    };
    public final Function1 onCommitAffectingLookahead = new Function1() { // from class: androidx.compose.ui.node.OwnerSnapshotObserver$onCommitAffectingLookahead$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            LayoutNode layoutNode = (LayoutNode) obj;
            if (layoutNode.isAttached()) {
                layoutNode.requestLookaheadRelayout$ui_release(false);
            }
            return Unit.INSTANCE;
        }
    };

    public OwnerSnapshotObserver(Function1 function1) {
        this.observer = new SnapshotStateObserver(function1);
    }

    public final void clearInvalidObservations$ui_release() {
        this.observer.clearIf(new Function1() { // from class: androidx.compose.ui.node.OwnerSnapshotObserver$clearInvalidObservations$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(!((OwnerScope) obj).isValidOwnerScope());
            }
        });
    }

    public final void observeReads$ui_release(OwnerScope ownerScope, Function1 function1, Function0 function0) {
        this.observer.observeReads(ownerScope, function1, function0);
    }
}
