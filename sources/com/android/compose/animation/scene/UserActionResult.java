package com.android.compose.animation.scene;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class UserActionResult {
    public static final Companion Companion = null;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ChangeScene extends UserActionResult {
        public final boolean requiresFullDistanceSwipe;
        public final SceneKey toScene;
        public final TransitionKey transitionKey;

        public ChangeScene(SceneKey sceneKey, TransitionKey transitionKey, boolean z) {
            this.toScene = sceneKey;
            this.transitionKey = transitionKey;
            this.requiresFullDistanceSwipe = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ChangeScene)) {
                return false;
            }
            ChangeScene changeScene = (ChangeScene) obj;
            return Intrinsics.areEqual(this.toScene, changeScene.toScene) && Intrinsics.areEqual(this.transitionKey, changeScene.transitionKey) && this.requiresFullDistanceSwipe == changeScene.requiresFullDistanceSwipe;
        }

        @Override // com.android.compose.animation.scene.UserActionResult
        public final boolean getRequiresFullDistanceSwipe() {
            return this.requiresFullDistanceSwipe;
        }

        @Override // com.android.compose.animation.scene.UserActionResult
        public final TransitionKey getTransitionKey() {
            return this.transitionKey;
        }

        public final int hashCode() {
            int hashCode = this.toScene.identity.hashCode() * 31;
            TransitionKey transitionKey = this.transitionKey;
            return Boolean.hashCode(this.requiresFullDistanceSwipe) + ((hashCode + (transitionKey == null ? 0 : transitionKey.identity.hashCode())) * 31);
        }

        @Override // com.android.compose.animation.scene.UserActionResult
        public final ContentKey toContent$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(SceneKey sceneKey) {
            return this.toScene;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("ChangeScene(toScene=");
            sb.append(this.toScene);
            sb.append(", transitionKey=");
            sb.append(this.transitionKey);
            sb.append(", requiresFullDistanceSwipe=");
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.requiresFullDistanceSwipe, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ReplaceByOverlay extends UserActionResult {
        public final OverlayKey overlay;
        public final boolean requiresFullDistanceSwipe;
        public final TransitionKey transitionKey;

        public ReplaceByOverlay(OverlayKey overlayKey, TransitionKey transitionKey, boolean z) {
            this.overlay = overlayKey;
            this.transitionKey = transitionKey;
            this.requiresFullDistanceSwipe = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ReplaceByOverlay)) {
                return false;
            }
            ReplaceByOverlay replaceByOverlay = (ReplaceByOverlay) obj;
            return Intrinsics.areEqual(this.overlay, replaceByOverlay.overlay) && Intrinsics.areEqual(this.transitionKey, replaceByOverlay.transitionKey) && this.requiresFullDistanceSwipe == replaceByOverlay.requiresFullDistanceSwipe;
        }

        @Override // com.android.compose.animation.scene.UserActionResult
        public final boolean getRequiresFullDistanceSwipe() {
            return this.requiresFullDistanceSwipe;
        }

        @Override // com.android.compose.animation.scene.UserActionResult
        public final TransitionKey getTransitionKey() {
            return this.transitionKey;
        }

        public final int hashCode() {
            int hashCode = this.overlay.identity.hashCode() * 31;
            TransitionKey transitionKey = this.transitionKey;
            return Boolean.hashCode(this.requiresFullDistanceSwipe) + ((hashCode + (transitionKey == null ? 0 : transitionKey.identity.hashCode())) * 31);
        }

        @Override // com.android.compose.animation.scene.UserActionResult
        public final ContentKey toContent$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(SceneKey sceneKey) {
            return this.overlay;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("ReplaceByOverlay(overlay=");
            sb.append(this.overlay);
            sb.append(", transitionKey=");
            sb.append(this.transitionKey);
            sb.append(", requiresFullDistanceSwipe=");
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.requiresFullDistanceSwipe, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ShowOverlay extends UserActionResult {
        public final OverlayKey overlay;
        public final boolean requiresFullDistanceSwipe;
        public final TransitionKey transitionKey;

        public ShowOverlay(OverlayKey overlayKey, TransitionKey transitionKey, boolean z) {
            this.overlay = overlayKey;
            this.transitionKey = transitionKey;
            this.requiresFullDistanceSwipe = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ShowOverlay)) {
                return false;
            }
            ShowOverlay showOverlay = (ShowOverlay) obj;
            return Intrinsics.areEqual(this.overlay, showOverlay.overlay) && Intrinsics.areEqual(this.transitionKey, showOverlay.transitionKey) && this.requiresFullDistanceSwipe == showOverlay.requiresFullDistanceSwipe;
        }

        @Override // com.android.compose.animation.scene.UserActionResult
        public final boolean getRequiresFullDistanceSwipe() {
            return this.requiresFullDistanceSwipe;
        }

        @Override // com.android.compose.animation.scene.UserActionResult
        public final TransitionKey getTransitionKey() {
            return this.transitionKey;
        }

        public final int hashCode() {
            int hashCode = this.overlay.identity.hashCode() * 31;
            TransitionKey transitionKey = this.transitionKey;
            return Boolean.hashCode(this.requiresFullDistanceSwipe) + ((hashCode + (transitionKey == null ? 0 : transitionKey.identity.hashCode())) * 31);
        }

        @Override // com.android.compose.animation.scene.UserActionResult
        public final ContentKey toContent$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(SceneKey sceneKey) {
            return this.overlay;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("ShowOverlay(overlay=");
            sb.append(this.overlay);
            sb.append(", transitionKey=");
            sb.append(this.transitionKey);
            sb.append(", requiresFullDistanceSwipe=");
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.requiresFullDistanceSwipe, ")");
        }
    }

    public abstract boolean getRequiresFullDistanceSwipe();

    public abstract TransitionKey getTransitionKey();

    public abstract ContentKey toContent$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(SceneKey sceneKey);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class HideOverlay extends UserActionResult {
        public final OverlayKey overlay;
        public final boolean requiresFullDistanceSwipe;
        public final TransitionKey transitionKey;

        public HideOverlay(OverlayKey overlayKey, TransitionKey transitionKey, boolean z) {
            this.overlay = overlayKey;
            this.transitionKey = transitionKey;
            this.requiresFullDistanceSwipe = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof HideOverlay)) {
                return false;
            }
            HideOverlay hideOverlay = (HideOverlay) obj;
            return Intrinsics.areEqual(this.overlay, hideOverlay.overlay) && Intrinsics.areEqual(this.transitionKey, hideOverlay.transitionKey) && this.requiresFullDistanceSwipe == hideOverlay.requiresFullDistanceSwipe;
        }

        @Override // com.android.compose.animation.scene.UserActionResult
        public final boolean getRequiresFullDistanceSwipe() {
            return this.requiresFullDistanceSwipe;
        }

        @Override // com.android.compose.animation.scene.UserActionResult
        public final TransitionKey getTransitionKey() {
            return this.transitionKey;
        }

        public final int hashCode() {
            int hashCode = this.overlay.identity.hashCode() * 31;
            TransitionKey transitionKey = this.transitionKey;
            return Boolean.hashCode(this.requiresFullDistanceSwipe) + ((hashCode + (transitionKey == null ? 0 : transitionKey.identity.hashCode())) * 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("HideOverlay(overlay=");
            sb.append(this.overlay);
            sb.append(", transitionKey=");
            sb.append(this.transitionKey);
            sb.append(", requiresFullDistanceSwipe=");
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.requiresFullDistanceSwipe, ")");
        }

        @Override // com.android.compose.animation.scene.UserActionResult
        public final ContentKey toContent$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(SceneKey sceneKey) {
            return sceneKey;
        }
    }
}
