package androidx.compose.ui.platform;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.DynamicProvidableCompositionLocal;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class CompositionLocalsKt {
    public static final StaticProvidableCompositionLocal LocalAccessibilityManager = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalAccessibilityManager$1
        @Override // kotlin.jvm.functions.Function0
        public final /* bridge */ /* synthetic */ Object invoke() {
            return null;
        }
    });
    public static final StaticProvidableCompositionLocal LocalAutofill = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalAutofill$1
        @Override // kotlin.jvm.functions.Function0
        public final /* bridge */ /* synthetic */ Object invoke() {
            return null;
        }
    });
    public static final StaticProvidableCompositionLocal LocalAutofillTree = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalAutofillTree$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            CompositionLocalsKt.access$noLocalProvidedFor("LocalAutofillTree");
            throw null;
        }
    });
    public static final StaticProvidableCompositionLocal LocalClipboardManager = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalClipboardManager$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            CompositionLocalsKt.access$noLocalProvidedFor("LocalClipboardManager");
            throw null;
        }
    });
    public static final StaticProvidableCompositionLocal LocalGraphicsContext = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalGraphicsContext$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            CompositionLocalsKt.access$noLocalProvidedFor("LocalGraphicsContext");
            throw null;
        }
    });
    public static final StaticProvidableCompositionLocal LocalDensity = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalDensity$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            CompositionLocalsKt.access$noLocalProvidedFor("LocalDensity");
            throw null;
        }
    });
    public static final StaticProvidableCompositionLocal LocalFocusManager = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalFocusManager$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            CompositionLocalsKt.access$noLocalProvidedFor("LocalFocusManager");
            throw null;
        }
    });
    public static final StaticProvidableCompositionLocal LocalFontLoader = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalFontLoader$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            CompositionLocalsKt.access$noLocalProvidedFor("LocalFontLoader");
            throw null;
        }
    });
    public static final StaticProvidableCompositionLocal LocalFontFamilyResolver = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalFontFamilyResolver$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            CompositionLocalsKt.access$noLocalProvidedFor("LocalFontFamilyResolver");
            throw null;
        }
    });
    public static final StaticProvidableCompositionLocal LocalHapticFeedback = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalHapticFeedback$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            CompositionLocalsKt.access$noLocalProvidedFor("LocalHapticFeedback");
            throw null;
        }
    });
    public static final StaticProvidableCompositionLocal LocalInputModeManager = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalInputModeManager$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            CompositionLocalsKt.access$noLocalProvidedFor("LocalInputManager");
            throw null;
        }
    });
    public static final StaticProvidableCompositionLocal LocalLayoutDirection = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalLayoutDirection$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            CompositionLocalsKt.access$noLocalProvidedFor("LocalLayoutDirection");
            throw null;
        }
    });
    public static final StaticProvidableCompositionLocal LocalTextInputService = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalTextInputService$1
        @Override // kotlin.jvm.functions.Function0
        public final /* bridge */ /* synthetic */ Object invoke() {
            return null;
        }
    });
    public static final StaticProvidableCompositionLocal LocalSoftwareKeyboardController = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalSoftwareKeyboardController$1
        @Override // kotlin.jvm.functions.Function0
        public final /* bridge */ /* synthetic */ Object invoke() {
            return null;
        }
    });
    public static final StaticProvidableCompositionLocal LocalTextToolbar = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalTextToolbar$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            CompositionLocalsKt.access$noLocalProvidedFor("LocalTextToolbar");
            throw null;
        }
    });
    public static final StaticProvidableCompositionLocal LocalUriHandler = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalUriHandler$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            CompositionLocalsKt.access$noLocalProvidedFor("LocalUriHandler");
            throw null;
        }
    });
    public static final StaticProvidableCompositionLocal LocalViewConfiguration = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalViewConfiguration$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            CompositionLocalsKt.access$noLocalProvidedFor("LocalViewConfiguration");
            throw null;
        }
    });
    public static final StaticProvidableCompositionLocal LocalWindowInfo = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalWindowInfo$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            CompositionLocalsKt.access$noLocalProvidedFor("LocalWindowInfo");
            throw null;
        }
    });
    public static final StaticProvidableCompositionLocal LocalPointerIconService = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalPointerIconService$1
        @Override // kotlin.jvm.functions.Function0
        public final /* bridge */ /* synthetic */ Object invoke() {
            return null;
        }
    });
    public static final DynamicProvidableCompositionLocal LocalProvidableScrollCaptureInProgress = new DynamicProvidableCompositionLocal(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalProvidableScrollCaptureInProgress$1
        @Override // kotlin.jvm.functions.Function0
        public final /* bridge */ /* synthetic */ Object invoke() {
            return Boolean.FALSE;
        }
    });
    public static final StaticProvidableCompositionLocal LocalCursorBlinkEnabled = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$LocalCursorBlinkEnabled$1
        @Override // kotlin.jvm.functions.Function0
        public final /* bridge */ /* synthetic */ Object invoke() {
            return Boolean.TRUE;
        }
    });

    public static final void ProvideCommonCompositionLocals(final Owner owner, final UriHandler uriHandler, final Function2 function2, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(874662829);
        if ((i & 6) == 0) {
            i2 = ((i & 8) == 0 ? composerImpl.changed(owner) : composerImpl.changedInstance(owner) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= (i & 64) == 0 ? composerImpl.changed(uriHandler) : composerImpl.changedInstance(uriHandler) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerImpl.changedInstance(function2) ? 256 : 128;
        }
        if ((i2 & 147) == 146 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            AndroidComposeView androidComposeView = (AndroidComposeView) owner;
            ProvidedValue defaultProvidedValue$runtime_release = LocalAccessibilityManager.defaultProvidedValue$runtime_release(androidComposeView.accessibilityManager);
            ProvidedValue defaultProvidedValue$runtime_release2 = LocalAutofill.defaultProvidedValue$runtime_release(androidComposeView._autofill);
            ProvidedValue defaultProvidedValue$runtime_release3 = LocalAutofillTree.defaultProvidedValue$runtime_release(androidComposeView.autofillTree);
            ProvidedValue defaultProvidedValue$runtime_release4 = LocalClipboardManager.defaultProvidedValue$runtime_release(androidComposeView.clipboardManager);
            ProvidedValue defaultProvidedValue$runtime_release5 = LocalDensity.defaultProvidedValue$runtime_release((Density) ((SnapshotMutableStateImpl) androidComposeView.density$delegate).getValue());
            ProvidedValue defaultProvidedValue$runtime_release6 = LocalFocusManager.defaultProvidedValue$runtime_release(androidComposeView.focusOwner);
            ProvidedValue defaultProvidedValue$runtime_release7 = LocalFontLoader.defaultProvidedValue$runtime_release(androidComposeView.fontLoader);
            defaultProvidedValue$runtime_release7.canOverride = false;
            ProvidedValue defaultProvidedValue$runtime_release8 = LocalFontFamilyResolver.defaultProvidedValue$runtime_release((FontFamily.Resolver) ((SnapshotMutableStateImpl) androidComposeView.fontFamilyResolver$delegate).getValue());
            defaultProvidedValue$runtime_release8.canOverride = false;
            CompositionLocalKt.CompositionLocalProvider(new ProvidedValue[]{defaultProvidedValue$runtime_release, defaultProvidedValue$runtime_release2, defaultProvidedValue$runtime_release3, defaultProvidedValue$runtime_release4, defaultProvidedValue$runtime_release5, defaultProvidedValue$runtime_release6, defaultProvidedValue$runtime_release7, defaultProvidedValue$runtime_release8, LocalHapticFeedback.defaultProvidedValue$runtime_release(androidComposeView.hapticFeedBack), LocalInputModeManager.defaultProvidedValue$runtime_release(androidComposeView._inputModeManager), LocalLayoutDirection.defaultProvidedValue$runtime_release((LayoutDirection) ((SnapshotMutableStateImpl) androidComposeView.layoutDirection$delegate).getValue()), LocalTextInputService.defaultProvidedValue$runtime_release(androidComposeView.textInputService), LocalSoftwareKeyboardController.defaultProvidedValue$runtime_release(androidComposeView.softwareKeyboardController), LocalTextToolbar.defaultProvidedValue$runtime_release(androidComposeView.textToolbar), LocalUriHandler.defaultProvidedValue$runtime_release(uriHandler), LocalViewConfiguration.defaultProvidedValue$runtime_release(androidComposeView.viewConfiguration), LocalWindowInfo.defaultProvidedValue$runtime_release(androidComposeView._windowInfo), LocalPointerIconService.defaultProvidedValue$runtime_release(androidComposeView.pointerIconService), LocalGraphicsContext.defaultProvidedValue$runtime_release(androidComposeView.graphicsContext)}, function2, composerImpl, ((i2 >> 3) & 112) | 8);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.ui.platform.CompositionLocalsKt$ProvideCommonCompositionLocals$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CompositionLocalsKt.ProvideCommonCompositionLocals(Owner.this, uriHandler, function2, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void access$noLocalProvidedFor(String str) {
        throw new IllegalStateException(("CompositionLocal " + str + " not present").toString());
    }
}
