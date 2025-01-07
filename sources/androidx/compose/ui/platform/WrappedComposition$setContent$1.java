package androidx.compose.ui.platform;

import android.view.View;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionImpl;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.SlotTable;
import androidx.compose.runtime.SlotWriter;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.tooling.InspectionTablesKt;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import com.android.wm.shell.R;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.jvm.internal.markers.KMutableSet;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class WrappedComposition$setContent$1 extends Lambda implements Function1 {
    final /* synthetic */ Function2 $content;
    final /* synthetic */ WrappedComposition this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WrappedComposition$setContent$1(WrappedComposition wrappedComposition, Function2 function2) {
        super(1);
        this.this$0 = wrappedComposition;
        this.$content = function2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        AndroidComposeView.ViewTreeOwners viewTreeOwners = (AndroidComposeView.ViewTreeOwners) obj;
        if (!this.this$0.disposed) {
            Lifecycle lifecycle = viewTreeOwners.lifecycleOwner.getLifecycle();
            WrappedComposition wrappedComposition = this.this$0;
            wrappedComposition.lastContent = this.$content;
            if (wrappedComposition.addedToLifecycle == null) {
                wrappedComposition.addedToLifecycle = lifecycle;
                lifecycle.addObserver(wrappedComposition);
            } else if (((LifecycleRegistry) lifecycle).state.isAtLeast(Lifecycle.State.CREATED)) {
                final WrappedComposition wrappedComposition2 = this.this$0;
                CompositionImpl compositionImpl = wrappedComposition2.original;
                final Function2 function2 = this.$content;
                compositionImpl.composeInitial(new ComposableLambdaImpl(-2000640158, true, new Function2() { // from class: androidx.compose.ui.platform.WrappedComposition$setContent$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    /* JADX WARN: Type inference failed for: r0v7, types: [androidx.compose.ui.platform.WrappedComposition$setContent$1$1$3, kotlin.jvm.internal.Lambda] */
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        Composer composer = (Composer) obj2;
                        if ((((Number) obj3).intValue() & 3) == 2) {
                            ComposerImpl composerImpl = (ComposerImpl) composer;
                            if (composerImpl.getSkipping()) {
                                composerImpl.skipToGroupEnd();
                                return Unit.INSTANCE;
                            }
                        }
                        OpaqueKey opaqueKey = ComposerKt.invocation;
                        Object tag = WrappedComposition.this.owner.getTag(R.id.inspection_slot_table_set);
                        Set set = (tag instanceof Set) && (!(tag instanceof KMappedMarker) || (tag instanceof KMutableSet)) ? (Set) tag : null;
                        if (set == null) {
                            Object parent = WrappedComposition.this.owner.getParent();
                            View view = parent instanceof View ? (View) parent : null;
                            Object tag2 = view != null ? view.getTag(R.id.inspection_slot_table_set) : null;
                            set = (!(tag2 instanceof Set) || ((tag2 instanceof KMappedMarker) && !(tag2 instanceof KMutableSet))) ? null : (Set) tag2;
                        }
                        if (set != null) {
                            ComposerImpl composerImpl2 = (ComposerImpl) composer;
                            set.add(composerImpl2.slotTable);
                            composerImpl2.forceRecomposeScopes = true;
                            composerImpl2.sourceMarkersEnabled = true;
                            composerImpl2.slotTable.collectSourceInformation();
                            composerImpl2.insertTable.collectSourceInformation();
                            SlotWriter slotWriter = composerImpl2.writer;
                            SlotTable slotTable = slotWriter.table;
                            slotWriter.sourceInformationMap = slotTable.sourceInformationMap;
                            slotWriter.calledByMap = slotTable.calledByMap;
                        }
                        WrappedComposition wrappedComposition3 = WrappedComposition.this;
                        AndroidComposeView androidComposeView = wrappedComposition3.owner;
                        ComposerImpl composerImpl3 = (ComposerImpl) composer;
                        boolean changedInstance = composerImpl3.changedInstance(wrappedComposition3);
                        WrappedComposition wrappedComposition4 = WrappedComposition.this;
                        Object rememberedValue = composerImpl3.rememberedValue();
                        Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
                        if (changedInstance || rememberedValue == composer$Companion$Empty$1) {
                            rememberedValue = new WrappedComposition$setContent$1$1$1$1(wrappedComposition4, null);
                            composerImpl3.updateRememberedValue(rememberedValue);
                        }
                        EffectsKt.LaunchedEffect(composerImpl3, androidComposeView, (Function2) rememberedValue);
                        WrappedComposition wrappedComposition5 = WrappedComposition.this;
                        AndroidComposeView androidComposeView2 = wrappedComposition5.owner;
                        boolean changedInstance2 = composerImpl3.changedInstance(wrappedComposition5);
                        WrappedComposition wrappedComposition6 = WrappedComposition.this;
                        Object rememberedValue2 = composerImpl3.rememberedValue();
                        if (changedInstance2 || rememberedValue2 == composer$Companion$Empty$1) {
                            rememberedValue2 = new WrappedComposition$setContent$1$1$2$1(wrappedComposition6, null);
                            composerImpl3.updateRememberedValue(rememberedValue2);
                        }
                        EffectsKt.LaunchedEffect(composerImpl3, androidComposeView2, (Function2) rememberedValue2);
                        ProvidedValue defaultProvidedValue$runtime_release = InspectionTablesKt.LocalInspectionTables.defaultProvidedValue$runtime_release(set);
                        final WrappedComposition wrappedComposition7 = WrappedComposition.this;
                        final Function2 function22 = function2;
                        CompositionLocalKt.CompositionLocalProvider(defaultProvidedValue$runtime_release, ComposableLambdaKt.rememberComposableLambda(-1193460702, new Function2() { // from class: androidx.compose.ui.platform.WrappedComposition.setContent.1.1.3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj4, Object obj5) {
                                Composer composer2 = (Composer) obj4;
                                if ((((Number) obj5).intValue() & 3) == 2) {
                                    ComposerImpl composerImpl4 = (ComposerImpl) composer2;
                                    if (composerImpl4.getSkipping()) {
                                        composerImpl4.skipToGroupEnd();
                                        return Unit.INSTANCE;
                                    }
                                }
                                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                                AndroidCompositionLocals_androidKt.ProvideAndroidCompositionLocals(WrappedComposition.this.owner, function22, composer2, 0);
                                return Unit.INSTANCE;
                            }
                        }, composerImpl3), composerImpl3, 56);
                        return Unit.INSTANCE;
                    }
                }));
            }
        }
        return Unit.INSTANCE;
    }
}
