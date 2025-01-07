package androidx.compose.ui.viewinterop;

import android.content.Context;
import android.view.View;
import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.saveable.SaveableStateRegistry;
import androidx.compose.runtime.saveable.SaveableStateRegistryKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.lifecycle.LifecycleOwner;
import androidx.savedstate.SavedStateRegistryOwner;
import com.android.wm.shell.R;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AndroidView_androidKt {
    public static final Function1 NoOpUpdate = new Function1() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$NoOpUpdate$1
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return Unit.INSTANCE;
        }
    };

    /* JADX WARN: Removed duplicated region for block: B:10:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void AndroidView(final kotlin.jvm.functions.Function1 r13, androidx.compose.ui.Modifier r14, kotlin.jvm.functions.Function1 r15, androidx.compose.runtime.Composer r16, final int r17, final int r18) {
        /*
            r4 = r17
            r0 = 2
            r1 = 4
            r2 = r16
            androidx.compose.runtime.ComposerImpl r2 = (androidx.compose.runtime.ComposerImpl) r2
            r3 = -1783766393(0xffffffff95ade287, float:-7.023154E-26)
            r2.startRestartGroup(r3)
            r3 = r18 & 1
            if (r3 == 0) goto L17
            r3 = r4 | 6
            r5 = r3
            r3 = r13
            goto L29
        L17:
            r3 = r4 & 6
            if (r3 != 0) goto L27
            r3 = r13
            boolean r5 = r2.changedInstance(r13)
            if (r5 == 0) goto L24
            r5 = r1
            goto L25
        L24:
            r5 = r0
        L25:
            r5 = r5 | r4
            goto L29
        L27:
            r3 = r13
            r5 = r4
        L29:
            r0 = r18 & 2
            if (r0 == 0) goto L31
            r5 = r5 | 48
        L2f:
            r6 = r14
            goto L42
        L31:
            r6 = r4 & 48
            if (r6 != 0) goto L2f
            r6 = r14
            boolean r7 = r2.changed(r14)
            if (r7 == 0) goto L3f
            r7 = 32
            goto L41
        L3f:
            r7 = 16
        L41:
            r5 = r5 | r7
        L42:
            r1 = r18 & 4
            if (r1 == 0) goto L4a
            r5 = r5 | 384(0x180, float:5.38E-43)
        L48:
            r7 = r15
            goto L5b
        L4a:
            r7 = r4 & 384(0x180, float:5.38E-43)
            if (r7 != 0) goto L48
            r7 = r15
            boolean r8 = r2.changedInstance(r15)
            if (r8 == 0) goto L58
            r8 = 256(0x100, float:3.59E-43)
            goto L5a
        L58:
            r8 = 128(0x80, float:1.8E-43)
        L5a:
            r5 = r5 | r8
        L5b:
            r8 = r5 & 147(0x93, float:2.06E-43)
            r9 = 146(0x92, float:2.05E-43)
            if (r8 != r9) goto L6c
            boolean r8 = r2.getSkipping()
            if (r8 != 0) goto L68
            goto L6c
        L68:
            r2.skipToGroupEnd()
            goto L95
        L6c:
            if (r0 == 0) goto L71
            androidx.compose.ui.Modifier$Companion r0 = androidx.compose.ui.Modifier.Companion.$$INSTANCE
            goto L72
        L71:
            r0 = r6
        L72:
            kotlin.jvm.functions.Function1 r8 = androidx.compose.ui.viewinterop.AndroidView_androidKt.NoOpUpdate
            if (r1 == 0) goto L78
            r1 = r8
            goto L79
        L78:
            r1 = r7
        L79:
            androidx.compose.runtime.OpaqueKey r6 = androidx.compose.runtime.ComposerKt.invocation
            r6 = r5 & 14
            r6 = r6 | 3072(0xc00, float:4.305E-42)
            r7 = r5 & 112(0x70, float:1.57E-43)
            r6 = r6 | r7
            r7 = 57344(0xe000, float:8.0356E-41)
            int r5 = r5 << 6
            r5 = r5 & r7
            r11 = r6 | r5
            r12 = 4
            r7 = 0
            r5 = r13
            r6 = r0
            r9 = r1
            r10 = r2
            AndroidView(r5, r6, r7, r8, r9, r10, r11, r12)
            r6 = r0
            r7 = r1
        L95:
            androidx.compose.runtime.RecomposeScopeImpl r8 = r2.endRestartGroup()
            if (r8 == 0) goto Laa
            androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$1 r9 = new androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$1
            r0 = r9
            r1 = r13
            r2 = r6
            r3 = r7
            r4 = r17
            r5 = r18
            r0.<init>()
            r8.block = r9
        Laa:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.viewinterop.AndroidView_androidKt.AndroidView(kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final ViewFactoryHolder access$requireViewFactoryHolder(LayoutNode layoutNode) {
        AndroidViewHolder androidViewHolder = layoutNode.interopViewFactoryHolder;
        if (androidViewHolder != null) {
            return (ViewFactoryHolder) androidViewHolder;
        }
        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Required value was null.");
        throw null;
    }

    public static final Function0 createAndroidViewNodeFactory(final Function1 function1, Composer composer, int i) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        final int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer);
        ComposerImpl composerImpl = (ComposerImpl) composer;
        final Context context = (Context) composerImpl.consume(AndroidCompositionLocals_androidKt.LocalContext);
        final CompositionContext rememberCompositionContext = ComposablesKt.rememberCompositionContext(composerImpl);
        final SaveableStateRegistry saveableStateRegistry = (SaveableStateRegistry) composerImpl.consume(SaveableStateRegistryKt.LocalSaveableStateRegistry);
        final View view = (View) composerImpl.consume(AndroidCompositionLocals_androidKt.LocalView);
        boolean changedInstance = ((((i & 14) ^ 6) > 4 && composerImpl.changed(function1)) || (i & 6) == 4) | composerImpl.changedInstance(context) | composerImpl.changedInstance(rememberCompositionContext) | composerImpl.changedInstance(saveableStateRegistry) | composerImpl.changed(currentCompositeKeyHash) | composerImpl.changedInstance(view);
        Object rememberedValue = composerImpl.rememberedValue();
        if (changedInstance || rememberedValue == Composer.Companion.Empty) {
            rememberedValue = new Function0() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$createAndroidViewNodeFactory$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return new ViewFactoryHolder(context, function1, rememberCompositionContext, saveableStateRegistry, currentCompositeKeyHash, (Owner) view).layoutNode;
                }
            };
            composerImpl.updateRememberedValue(rememberedValue);
        }
        return (Function0) rememberedValue;
    }

    /* renamed from: updateViewHolderParams-6NefGtU, reason: not valid java name */
    public static final void m700updateViewHolderParams6NefGtU(Composer composer, Modifier modifier, int i, Density density, LifecycleOwner lifecycleOwner, SavedStateRegistryOwner savedStateRegistryOwner, LayoutDirection layoutDirection, PersistentCompositionLocalMap persistentCompositionLocalMap) {
        ComposeUiNode.Companion.getClass();
        Updater.m259setimpl(composer, persistentCompositionLocalMap, ComposeUiNode.Companion.SetResolvedCompositionLocals);
        Updater.m259setimpl(composer, modifier, new Function2() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$updateViewHolderParams$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Modifier modifier2 = (Modifier) obj2;
                ViewFactoryHolder access$requireViewFactoryHolder = AndroidView_androidKt.access$requireViewFactoryHolder((LayoutNode) obj);
                if (modifier2 != access$requireViewFactoryHolder.modifier) {
                    access$requireViewFactoryHolder.modifier = modifier2;
                    Function1 function1 = access$requireViewFactoryHolder.onModifierChanged;
                    if (function1 != null) {
                        ((AndroidViewHolder$layoutNode$1$1) function1).invoke(modifier2);
                    }
                }
                return Unit.INSTANCE;
            }
        });
        Updater.m259setimpl(composer, density, new Function2() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$updateViewHolderParams$2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Density density2 = (Density) obj2;
                ViewFactoryHolder access$requireViewFactoryHolder = AndroidView_androidKt.access$requireViewFactoryHolder((LayoutNode) obj);
                if (density2 != access$requireViewFactoryHolder.density) {
                    access$requireViewFactoryHolder.density = density2;
                    Function1 function1 = access$requireViewFactoryHolder.onDensityChanged;
                    if (function1 != null) {
                        ((AndroidViewHolder$layoutNode$1$2) function1).invoke(density2);
                    }
                }
                return Unit.INSTANCE;
            }
        });
        Updater.m259setimpl(composer, lifecycleOwner, new Function2() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$updateViewHolderParams$3
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                LifecycleOwner lifecycleOwner2 = (LifecycleOwner) obj2;
                ViewFactoryHolder access$requireViewFactoryHolder = AndroidView_androidKt.access$requireViewFactoryHolder((LayoutNode) obj);
                if (lifecycleOwner2 != access$requireViewFactoryHolder.lifecycleOwner) {
                    access$requireViewFactoryHolder.lifecycleOwner = lifecycleOwner2;
                    access$requireViewFactoryHolder.setTag(R.id.view_tree_lifecycle_owner, lifecycleOwner2);
                }
                return Unit.INSTANCE;
            }
        });
        Updater.m259setimpl(composer, savedStateRegistryOwner, new Function2() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$updateViewHolderParams$4
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                SavedStateRegistryOwner savedStateRegistryOwner2 = (SavedStateRegistryOwner) obj2;
                ViewFactoryHolder access$requireViewFactoryHolder = AndroidView_androidKt.access$requireViewFactoryHolder((LayoutNode) obj);
                if (savedStateRegistryOwner2 != access$requireViewFactoryHolder.savedStateRegistryOwner) {
                    access$requireViewFactoryHolder.savedStateRegistryOwner = savedStateRegistryOwner2;
                    access$requireViewFactoryHolder.setTag(R.id.view_tree_saved_state_registry_owner, savedStateRegistryOwner2);
                }
                return Unit.INSTANCE;
            }
        });
        Updater.m259setimpl(composer, layoutDirection, new Function2() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$updateViewHolderParams$5
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                int i2;
                ViewFactoryHolder access$requireViewFactoryHolder = AndroidView_androidKt.access$requireViewFactoryHolder((LayoutNode) obj);
                int ordinal = ((LayoutDirection) obj2).ordinal();
                if (ordinal != 0) {
                    i2 = 1;
                    if (ordinal != 1) {
                        throw new NoWhenBranchMatchedException();
                    }
                } else {
                    i2 = 0;
                }
                access$requireViewFactoryHolder.setLayoutDirection(i2);
                return Unit.INSTANCE;
            }
        });
        Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i, composerImpl, i, function2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void AndroidView(final kotlin.jvm.functions.Function1 r17, androidx.compose.ui.Modifier r18, kotlin.jvm.functions.Function1 r19, kotlin.jvm.functions.Function1 r20, kotlin.jvm.functions.Function1 r21, androidx.compose.runtime.Composer r22, final int r23, final int r24) {
        /*
            Method dump skipped, instructions count: 426
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.viewinterop.AndroidView_androidKt.AndroidView(kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int):void");
    }
}
