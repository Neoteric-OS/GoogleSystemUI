package androidx.compose.runtime;

import androidx.compose.runtime.internal.ComposableLambdaImpl;
import java.util.Set;
import kotlin.coroutines.CoroutineContext;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class CompositionContext {
    public abstract void composeInitial$runtime_release(CompositionImpl compositionImpl, ComposableLambdaImpl composableLambdaImpl);

    public abstract void deletedMovableContent$runtime_release(MovableContentStateReference movableContentStateReference);

    public abstract boolean getCollectingCallByInformation$runtime_release();

    public abstract boolean getCollectingParameterInformation$runtime_release();

    public abstract boolean getCollectingSourceInformation$runtime_release();

    public PersistentCompositionLocalMap getCompositionLocalScope$runtime_release() {
        return CompositionContextKt.EmptyPersistentCompositionLocalMap;
    }

    public abstract int getCompoundHashKey$runtime_release();

    public abstract CoroutineContext getEffectCoroutineContext();

    public abstract void insertMovableContent$runtime_release(MovableContentStateReference movableContentStateReference);

    public abstract void invalidate$runtime_release(CompositionImpl compositionImpl);

    public abstract void movableContentStateReleased$runtime_release(MovableContentStateReference movableContentStateReference, MovableContentState movableContentState);

    public abstract MovableContentState movableContentStateResolve$runtime_release(MovableContentStateReference movableContentStateReference);

    public abstract void recordInspectionTable$runtime_release(Set set);

    public abstract void reportRemovedComposition$runtime_release(CompositionImpl compositionImpl);

    public abstract void unregisterComposition$runtime_release(CompositionImpl compositionImpl);

    public void doneComposing$runtime_release() {
    }

    public void startComposing$runtime_release() {
    }

    public void registerComposer$runtime_release(ComposerImpl composerImpl) {
    }

    public void unregisterComposer$runtime_release(Composer composer) {
    }
}
