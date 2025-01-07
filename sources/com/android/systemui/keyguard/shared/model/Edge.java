package com.android.systemui.keyguard.shared.model;

import android.util.Log;
import com.android.app.viewcapture.data.ViewNode;
import com.android.compose.animation.scene.SceneKey;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class Edge {
    public static final Companion Companion = null;
    public static final StateToState INVALID = null;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static StateToState create$default(KeyguardState keyguardState, KeyguardState keyguardState2, int i) {
            Companion companion = Edge.Companion;
            if ((i & 1) != 0) {
                keyguardState = null;
            }
            if ((i & 2) != 0) {
                keyguardState2 = null;
            }
            return new StateToState(keyguardState, keyguardState2);
        }

        public static StateToScene create$default(SceneKey sceneKey) {
            Companion companion = Edge.Companion;
            return new StateToScene(sceneKey, null);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SceneToState extends Edge {
        public final SceneKey from;
        public final KeyguardState to;

        public SceneToState(SceneKey sceneKey, KeyguardState keyguardState) {
            this.from = sceneKey;
            this.to = keyguardState;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SceneToState)) {
                return false;
            }
            SceneToState sceneToState = (SceneToState) obj;
            return Intrinsics.areEqual(this.from, sceneToState.from) && this.to == sceneToState.to;
        }

        public final int hashCode() {
            int hashCode = this.from.identity.hashCode() * 31;
            KeyguardState keyguardState = this.to;
            return hashCode + (keyguardState == null ? 0 : keyguardState.hashCode());
        }

        public final String toString() {
            return "SceneToState(from=" + this.from + ", to=" + this.to + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StateToScene extends Edge {
        public final KeyguardState from;
        public final SceneKey to;

        public StateToScene(SceneKey sceneKey, KeyguardState keyguardState) {
            this.from = keyguardState;
            this.to = sceneKey;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof StateToScene)) {
                return false;
            }
            StateToScene stateToScene = (StateToScene) obj;
            return this.from == stateToScene.from && Intrinsics.areEqual(this.to, stateToScene.to);
        }

        public final int hashCode() {
            KeyguardState keyguardState = this.from;
            return this.to.identity.hashCode() + ((keyguardState == null ? 0 : keyguardState.hashCode()) * 31);
        }

        public final String toString() {
            return "StateToScene(from=" + this.from + ", to=" + this.to + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StateToState extends Edge {
        public final KeyguardState from;
        public final KeyguardState to;

        public StateToState(KeyguardState keyguardState, KeyguardState keyguardState2) {
            this.from = keyguardState;
            this.to = keyguardState2;
            if (keyguardState == null && keyguardState2 == null) {
                throw new IllegalStateException("to and from can't both be null");
            }
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof StateToState)) {
                return false;
            }
            StateToState stateToState = (StateToState) obj;
            return this.from == stateToState.from && this.to == stateToState.to;
        }

        public final int hashCode() {
            KeyguardState keyguardState = this.from;
            int hashCode = (keyguardState == null ? 0 : keyguardState.hashCode()) * 31;
            KeyguardState keyguardState2 = this.to;
            return hashCode + (keyguardState2 != null ? keyguardState2.hashCode() : 0);
        }

        public final String toString() {
            return "StateToState(from=" + this.from + ", to=" + this.to + ")";
        }
    }

    static {
        KeyguardState keyguardState = KeyguardState.UNDEFINED;
        new StateToState(keyguardState, keyguardState);
    }

    public static void verifyValidKeyguardStates(KeyguardState keyguardState, KeyguardState keyguardState2) {
        if (keyguardState != null) {
            switch (keyguardState.ordinal()) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
        if (keyguardState2 != null) {
            switch (keyguardState2.ordinal()) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
        KeyguardState keyguardState3 = KeyguardState.UNDEFINED;
        if (keyguardState == keyguardState3 || keyguardState2 == keyguardState3) {
            Log.e("Edge", "UNDEFINED should not be used when scene container is disabled");
        }
    }
}
