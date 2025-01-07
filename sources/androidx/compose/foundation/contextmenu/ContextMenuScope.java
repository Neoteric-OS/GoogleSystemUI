package androidx.compose.foundation.contextmenu;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.text.ContextMenu_androidKt$TextItem$1;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Modifier;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.text.StringsKt__StringsJVMKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ContextMenuScope {
    public final SnapshotStateList composables = new SnapshotStateList();

    public static void item$default(ContextMenuScope contextMenuScope, final ContextMenu_androidKt$TextItem$1 contextMenu_androidKt$TextItem$1, final Function0 function0) {
        contextMenuScope.getClass();
        contextMenuScope.composables.add(new ComposableLambdaImpl(262103052, true, new Function3() { // from class: androidx.compose.foundation.contextmenu.ContextMenuScope$item$1
            final /* synthetic */ boolean $enabled;
            final /* synthetic */ Function3 $leadingIcon;
            final /* synthetic */ Modifier $modifier;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
                Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
                this.$enabled = true;
                this.$modifier = companion;
                this.$leadingIcon = null;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                ContextMenuColors contextMenuColors = (ContextMenuColors) obj;
                Composer composer = (Composer) obj2;
                int intValue = ((Number) obj3).intValue();
                if ((intValue & 6) == 0) {
                    intValue |= ((ComposerImpl) composer).changed(contextMenuColors) ? 4 : 2;
                }
                if ((intValue & 19) == 18) {
                    ComposerImpl composerImpl = (ComposerImpl) composer;
                    if (composerImpl.getSkipping()) {
                        composerImpl.skipToGroupEnd();
                        return Unit.INSTANCE;
                    }
                }
                OpaqueKey opaqueKey = ComposerKt.invocation;
                String str = (String) contextMenu_androidKt$TextItem$1.invoke(composer, 0);
                if (StringsKt__StringsJVMKt.isBlank(str)) {
                    InlineClassHelperKt.throwIllegalStateException("Label must not be blank");
                }
                ContextMenuUi_androidKt.ContextMenuItem(str, this.$enabled, contextMenuColors, this.$modifier, this.$leadingIcon, function0, composer, (intValue << 6) & 896, 0);
                return Unit.INSTANCE;
            }
        }));
    }

    public final void Content$foundation_release(final ContextMenuColors contextMenuColors, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1320309496);
        int i2 = (i & 6) == 0 ? (composerImpl.changed(contextMenuColors) ? 4 : 2) | i : i;
        if ((i & 48) == 0) {
            i2 |= composerImpl.changed(this) ? 32 : 16;
        }
        if ((i2 & 19) == 18 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            SnapshotStateList snapshotStateList = this.composables;
            int size = snapshotStateList.size();
            for (int i3 = 0; i3 < size; i3++) {
                ((Function3) snapshotStateList.get(i3)).invoke(contextMenuColors, composerImpl, Integer.valueOf(i2 & 14));
            }
            OpaqueKey opaqueKey2 = ComposerKt.invocation;
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.foundation.contextmenu.ContextMenuScope$Content$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    ContextMenuScope.this.Content$foundation_release(contextMenuColors, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
