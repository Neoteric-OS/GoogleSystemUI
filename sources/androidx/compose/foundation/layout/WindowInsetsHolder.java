package androidx.compose.foundation.layout;

import android.view.View;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.core.graphics.Insets;
import androidx.core.view.DisplayCutoutCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;
import com.android.wm.shell.R;
import java.util.WeakHashMap;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WindowInsetsHolder {
    public static final Companion Companion = null;
    public static final WeakHashMap viewMap = new WeakHashMap();
    public int accessCount;
    public final AndroidWindowInsets captionBar = Companion.access$systemInsets(4, "captionBar");
    public final ValueInsets captionBarIgnoringVisibility;
    public final boolean consumes;
    public final AndroidWindowInsets displayCutout;
    public final AndroidWindowInsets ime;
    public final ValueInsets imeAnimationSource;
    public final ValueInsets imeAnimationTarget;
    public final InsetsListener insetsListener;
    public final AndroidWindowInsets mandatorySystemGestures;
    public final AndroidWindowInsets navigationBars;
    public final ValueInsets navigationBarsIgnoringVisibility;
    public final WindowInsets safeDrawing;
    public final AndroidWindowInsets statusBars;
    public final ValueInsets statusBarsIgnoringVisibility;
    public final AndroidWindowInsets systemBars;
    public final ValueInsets systemBarsIgnoringVisibility;
    public final AndroidWindowInsets systemGestures;
    public final AndroidWindowInsets tappableElement;
    public final ValueInsets tappableElementIgnoringVisibility;
    public final ValueInsets waterfall;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static final AndroidWindowInsets access$systemInsets(int i, String str) {
            Companion companion = WindowInsetsHolder.Companion;
            return new AndroidWindowInsets(i, str);
        }

        public static final ValueInsets access$valueInsetsIgnoringVisibility(int i, String str) {
            Companion companion = WindowInsetsHolder.Companion;
            return new ValueInsets(WindowInsets_androidKt.toInsetsValues(Insets.NONE), str);
        }

        public static WindowInsetsHolder current(Composer composer) {
            final WindowInsetsHolder windowInsetsHolder;
            OpaqueKey opaqueKey = ComposerKt.invocation;
            ComposerImpl composerImpl = (ComposerImpl) composer;
            final View view = (View) composerImpl.consume(AndroidCompositionLocals_androidKt.LocalView);
            WeakHashMap weakHashMap = WindowInsetsHolder.viewMap;
            synchronized (weakHashMap) {
                try {
                    Object obj = weakHashMap.get(view);
                    if (obj == null) {
                        obj = new WindowInsetsHolder(view);
                        weakHashMap.put(view, obj);
                    }
                    windowInsetsHolder = (WindowInsetsHolder) obj;
                } catch (Throwable th) {
                    throw th;
                }
            }
            boolean changedInstance = composerImpl.changedInstance(windowInsetsHolder) | composerImpl.changedInstance(view);
            Object rememberedValue = composerImpl.rememberedValue();
            if (changedInstance || rememberedValue == Composer.Companion.Empty) {
                rememberedValue = new Function1() { // from class: androidx.compose.foundation.layout.WindowInsetsHolder$Companion$current$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        WindowInsetsHolder windowInsetsHolder2 = WindowInsetsHolder.this;
                        View view2 = view;
                        if (windowInsetsHolder2.accessCount == 0) {
                            WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                            InsetsListener insetsListener = windowInsetsHolder2.insetsListener;
                            ViewCompat.Api21Impl.setOnApplyWindowInsetsListener(view2, insetsListener);
                            if (view2.isAttachedToWindow()) {
                                view2.requestApplyInsets();
                            }
                            view2.addOnAttachStateChangeListener(insetsListener);
                            view2.setWindowInsetsAnimationCallback(insetsListener != null ? new WindowInsetsAnimationCompat.Impl30.ProxyCallback(insetsListener) : null);
                        }
                        windowInsetsHolder2.accessCount++;
                        final WindowInsetsHolder windowInsetsHolder3 = WindowInsetsHolder.this;
                        final View view3 = view;
                        return new DisposableEffectResult() { // from class: androidx.compose.foundation.layout.WindowInsetsHolder$Companion$current$1$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public final void dispose() {
                                View view4 = view3;
                                WindowInsetsHolder windowInsetsHolder4 = WindowInsetsHolder.this;
                                int i = windowInsetsHolder4.accessCount - 1;
                                windowInsetsHolder4.accessCount = i;
                                if (i == 0) {
                                    WeakHashMap weakHashMap3 = ViewCompat.sViewPropertyAnimatorMap;
                                    ViewCompat.Api21Impl.setOnApplyWindowInsetsListener(view4, null);
                                    view4.setWindowInsetsAnimationCallback(null);
                                    view4.removeOnAttachStateChangeListener(windowInsetsHolder4.insetsListener);
                                }
                            }
                        };
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue);
            }
            EffectsKt.DisposableEffect(windowInsetsHolder, (Function1) rememberedValue, composerImpl);
            return windowInsetsHolder;
        }
    }

    public WindowInsetsHolder(View view) {
        AndroidWindowInsets access$systemInsets = Companion.access$systemInsets(128, "displayCutout");
        this.displayCutout = access$systemInsets;
        AndroidWindowInsets access$systemInsets2 = Companion.access$systemInsets(8, "ime");
        this.ime = access$systemInsets2;
        this.mandatorySystemGestures = Companion.access$systemInsets(32, "mandatorySystemGestures");
        this.navigationBars = Companion.access$systemInsets(2, "navigationBars");
        this.statusBars = Companion.access$systemInsets(1, "statusBars");
        AndroidWindowInsets access$systemInsets3 = Companion.access$systemInsets(7, "systemBars");
        this.systemBars = access$systemInsets3;
        this.systemGestures = Companion.access$systemInsets(16, "systemGestures");
        this.tappableElement = Companion.access$systemInsets(64, "tappableElement");
        this.waterfall = new ValueInsets(WindowInsets_androidKt.toInsetsValues(Insets.NONE), "waterfall");
        this.safeDrawing = new UnionInsets(new UnionInsets(access$systemInsets3, access$systemInsets2), access$systemInsets);
        this.captionBarIgnoringVisibility = Companion.access$valueInsetsIgnoringVisibility(4, "captionBarIgnoringVisibility");
        this.navigationBarsIgnoringVisibility = Companion.access$valueInsetsIgnoringVisibility(2, "navigationBarsIgnoringVisibility");
        this.statusBarsIgnoringVisibility = Companion.access$valueInsetsIgnoringVisibility(1, "statusBarsIgnoringVisibility");
        this.systemBarsIgnoringVisibility = Companion.access$valueInsetsIgnoringVisibility(7, "systemBarsIgnoringVisibility");
        this.tappableElementIgnoringVisibility = Companion.access$valueInsetsIgnoringVisibility(64, "tappableElementIgnoringVisibility");
        this.imeAnimationTarget = Companion.access$valueInsetsIgnoringVisibility(8, "imeAnimationTarget");
        this.imeAnimationSource = Companion.access$valueInsetsIgnoringVisibility(8, "imeAnimationSource");
        Object parent = view.getParent();
        View view2 = parent instanceof View ? (View) parent : null;
        Object tag = view2 != null ? view2.getTag(R.id.consume_window_insets_tag) : null;
        Boolean bool = tag instanceof Boolean ? (Boolean) tag : null;
        this.consumes = bool != null ? bool.booleanValue() : true;
        this.insetsListener = new InsetsListener(this);
    }

    public static void update$default(WindowInsetsHolder windowInsetsHolder, WindowInsetsCompat windowInsetsCompat) {
        windowInsetsHolder.captionBar.update$foundation_layout_release(windowInsetsCompat, 0);
        windowInsetsHolder.ime.update$foundation_layout_release(windowInsetsCompat, 0);
        windowInsetsHolder.displayCutout.update$foundation_layout_release(windowInsetsCompat, 0);
        windowInsetsHolder.navigationBars.update$foundation_layout_release(windowInsetsCompat, 0);
        windowInsetsHolder.statusBars.update$foundation_layout_release(windowInsetsCompat, 0);
        windowInsetsHolder.systemBars.update$foundation_layout_release(windowInsetsCompat, 0);
        windowInsetsHolder.systemGestures.update$foundation_layout_release(windowInsetsCompat, 0);
        windowInsetsHolder.tappableElement.update$foundation_layout_release(windowInsetsCompat, 0);
        windowInsetsHolder.mandatorySystemGestures.update$foundation_layout_release(windowInsetsCompat, 0);
        windowInsetsHolder.captionBarIgnoringVisibility.setValue$foundation_layout_release(WindowInsets_androidKt.toInsetsValues(windowInsetsCompat.mImpl.getInsetsIgnoringVisibility(4)));
        WindowInsetsCompat.Impl impl = windowInsetsCompat.mImpl;
        windowInsetsHolder.navigationBarsIgnoringVisibility.setValue$foundation_layout_release(WindowInsets_androidKt.toInsetsValues(impl.getInsetsIgnoringVisibility(2)));
        windowInsetsHolder.statusBarsIgnoringVisibility.setValue$foundation_layout_release(WindowInsets_androidKt.toInsetsValues(impl.getInsetsIgnoringVisibility(1)));
        windowInsetsHolder.systemBarsIgnoringVisibility.setValue$foundation_layout_release(WindowInsets_androidKt.toInsetsValues(impl.getInsetsIgnoringVisibility(7)));
        windowInsetsHolder.tappableElementIgnoringVisibility.setValue$foundation_layout_release(WindowInsets_androidKt.toInsetsValues(impl.getInsetsIgnoringVisibility(64)));
        DisplayCutoutCompat displayCutout = impl.getDisplayCutout();
        if (displayCutout != null) {
            windowInsetsHolder.waterfall.setValue$foundation_layout_release(WindowInsets_androidKt.toInsetsValues(Insets.toCompatInsets(displayCutout.mDisplayCutout.getWaterfallInsets())));
        }
        Snapshot.Companion.sendApplyNotifications();
    }
}
