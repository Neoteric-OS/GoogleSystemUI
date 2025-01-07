package com.android.compose.animation.scene;

import java.util.Set;
import kotlin.collections.EmptySet;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__IndentKt;
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface ObservableTransitionState {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Transition implements ObservableTransitionState {
        public final ContentKey fromContent;
        public final SafeFlow isInPreviewStage;
        public final boolean isInitiatedByUserInput;
        public final SafeFlow isUserInputOngoing;
        public final SafeFlow progress;
        public final ContentKey toContent;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class ChangeScene extends Transition {
            public final SafeFlow currentScene;
            public final SceneKey fromScene;
            public final SceneKey toScene;

            public ChangeScene(SceneKey sceneKey, SceneKey sceneKey2, SafeFlow safeFlow, SafeFlow safeFlow2, boolean z, SafeFlow safeFlow3, SafeFlow safeFlow4, SafeFlow safeFlow5) {
                super(sceneKey, sceneKey2, safeFlow2, z, safeFlow3, safeFlow5);
                this.fromScene = sceneKey;
                this.toScene = sceneKey2;
                this.currentScene = safeFlow;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public abstract class OverlayTransition extends Transition {
            public final SceneKey currentScene;

            public OverlayTransition(ContentKey contentKey, ContentKey contentKey2, SceneKey sceneKey, SafeFlow safeFlow, boolean z, SafeFlow safeFlow2, SafeFlow safeFlow3) {
                super(contentKey, contentKey2, safeFlow, z, safeFlow2, safeFlow3);
                this.currentScene = sceneKey;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class ReplaceOverlay extends OverlayTransition {
            public final OverlayKey fromOverlay;
            public final OverlayKey toOverlay;

            public ReplaceOverlay(OverlayKey overlayKey, OverlayKey overlayKey2, SceneKey sceneKey, SafeFlow safeFlow, SafeFlow safeFlow2, boolean z, SafeFlow safeFlow3, SafeFlow safeFlow4, SafeFlow safeFlow5) {
                super(overlayKey, overlayKey2, sceneKey, safeFlow2, z, safeFlow3, safeFlow5);
                this.fromOverlay = overlayKey;
                this.toOverlay = overlayKey2;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class ShowOrHideOverlay extends OverlayTransition {
        }

        public Transition(ContentKey contentKey, ContentKey contentKey2, SafeFlow safeFlow, boolean z, SafeFlow safeFlow2, SafeFlow safeFlow3) {
            this.fromContent = contentKey;
            this.toContent = contentKey2;
            this.progress = safeFlow;
            this.isInitiatedByUserInput = z;
            this.isUserInputOngoing = safeFlow2;
        }

        public final String toString() {
            return StringsKt__IndentKt.trimMargin$default("Transition\n                |(from=" + this.fromContent + ",\n                | to=" + this.toContent + ",\n                | isInitiatedByUserInput=" + this.isInitiatedByUserInput + ",\n                | isUserInputOngoing=" + this.isUserInputOngoing + "\n                |)");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Idle implements ObservableTransitionState {
        public final Set currentOverlays;
        public final SceneKey currentScene;

        public Idle(SceneKey sceneKey) {
            this(sceneKey, EmptySet.INSTANCE);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Idle)) {
                return false;
            }
            Idle idle = (Idle) obj;
            return Intrinsics.areEqual(this.currentScene, idle.currentScene) && Intrinsics.areEqual(this.currentOverlays, idle.currentOverlays);
        }

        public final int hashCode() {
            return this.currentOverlays.hashCode() + (this.currentScene.identity.hashCode() * 31);
        }

        public final String toString() {
            return "Idle(currentScene=" + this.currentScene + ", currentOverlays=" + this.currentOverlays + ")";
        }

        public Idle(SceneKey sceneKey, Set set) {
            this.currentScene = sceneKey;
            this.currentOverlays = set;
        }
    }
}
