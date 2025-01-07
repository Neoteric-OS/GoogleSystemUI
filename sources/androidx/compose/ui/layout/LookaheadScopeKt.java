package androidx.compose.ui.layout;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.node.InnerNodeCoordinator;
import androidx.compose.ui.node.LayoutNode;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LookaheadScopeKt {
    public static final Function2 defaultPlacementApproachInProgress = new Function2() { // from class: androidx.compose.ui.layout.LookaheadScopeKt$defaultPlacementApproachInProgress$1
        @Override // kotlin.jvm.functions.Function2
        public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return Boolean.FALSE;
        }
    };

    public static final void LookaheadScope(final Function3 function3, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1078066484);
        if ((i & 6) == 0) {
            i2 = (composerImpl.changedInstance(function3) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 3) == 2 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            Object rememberedValue = composerImpl.rememberedValue();
            Object obj = rememberedValue;
            if (rememberedValue == Composer.Companion.Empty) {
                LookaheadScopeImpl lookaheadScopeImpl = new LookaheadScopeImpl();
                lookaheadScopeImpl.scopeCoordinates = null;
                composerImpl.updateRememberedValue(lookaheadScopeImpl);
                obj = lookaheadScopeImpl;
            }
            Object obj2 = (LookaheadScopeImpl) obj;
            LookaheadScopeKt$LookaheadScope$1 lookaheadScopeKt$LookaheadScope$1 = new Function0() { // from class: androidx.compose.ui.layout.LookaheadScopeKt$LookaheadScope$1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return new LayoutNode(2);
                }
            };
            composerImpl.startReusableNode();
            if (composerImpl.inserting) {
                composerImpl.createNode(lookaheadScopeKt$LookaheadScope$1);
            } else {
                composerImpl.useNode();
            }
            Updater.m258initimpl(composerImpl, new Function1() { // from class: androidx.compose.ui.layout.LookaheadScopeKt$LookaheadScope$2$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj3) {
                    ((LayoutNode) obj3).isVirtualLookaheadRoot = true;
                    return Unit.INSTANCE;
                }
            });
            Updater.m259setimpl(composerImpl, obj2, new Function2() { // from class: androidx.compose.ui.layout.LookaheadScopeKt$LookaheadScope$2$2

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: androidx.compose.ui.layout.LookaheadScopeKt$LookaheadScope$2$2$1, reason: invalid class name */
                final class AnonymousClass1 extends Lambda implements Function0 {
                    final /* synthetic */ LayoutNode $this_set;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public AnonymousClass1(LayoutNode layoutNode) {
                        super(0);
                        this.$this_set = layoutNode;
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        LayoutNode parent$ui_release = this.$this_set.getParent$ui_release();
                        Intrinsics.checkNotNull(parent$ui_release);
                        InnerNodeCoordinator innerNodeCoordinator = parent$ui_release.nodes.innerCoordinator;
                        innerNodeCoordinator.getClass();
                        return innerNodeCoordinator;
                    }
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    ((LookaheadScopeImpl) obj4).scopeCoordinates = new AnonymousClass1((LayoutNode) obj3);
                    return Unit.INSTANCE;
                }
            });
            function3.invoke(obj2, composerImpl, Integer.valueOf((i2 << 3) & 112));
            composerImpl.end(true);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.ui.layout.LookaheadScopeKt$LookaheadScope$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    ((Number) obj4).intValue();
                    LookaheadScopeKt.LookaheadScope(Function3.this, (Composer) obj3, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
