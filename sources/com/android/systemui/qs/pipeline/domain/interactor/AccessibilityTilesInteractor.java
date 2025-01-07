package com.android.systemui.qs.pipeline.domain.interactor;

import android.content.Context;
import com.android.systemui.accessibility.data.repository.AccessibilityQsShortcutsRepository;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AccessibilityTilesInteractor {
    public final AccessibilityQsShortcutsRepository a11yQsShortcutsRepository;
    public final CoroutineDispatcher backgroundDispatcher;
    public final AtomicBoolean initialized = new AtomicBoolean(false);
    public final CoroutineScope scope;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Data {
        public final List currentTileSpecs;
        public final Context userContext;

        public Data(Context context, List list) {
            this.currentTileSpecs = list;
            this.userContext = context;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Data)) {
                return false;
            }
            Data data = (Data) obj;
            return Intrinsics.areEqual(this.currentTileSpecs, data.currentTileSpecs) && Intrinsics.areEqual(this.userContext, data.userContext);
        }

        public final int hashCode() {
            return this.userContext.hashCode() + (this.currentTileSpecs.hashCode() * 31);
        }

        public final String toString() {
            return "Data(currentTileSpecs=" + this.currentTileSpecs + ", userContext=" + this.userContext + ")";
        }
    }

    public AccessibilityTilesInteractor(AccessibilityQsShortcutsRepository accessibilityQsShortcutsRepository, CoroutineDispatcher coroutineDispatcher, CoroutineScope coroutineScope) {
        this.a11yQsShortcutsRepository = accessibilityQsShortcutsRepository;
        this.backgroundDispatcher = coroutineDispatcher;
        this.scope = coroutineScope;
    }

    public final void init(CurrentTilesInteractor currentTilesInteractor) {
        if (this.initialized.compareAndSet(false, true)) {
            BuildersKt.launch$default(this.scope, this.backgroundDispatcher, null, new AccessibilityTilesInteractor$startObservingTiles$1(currentTilesInteractor, this, null), 2);
        }
    }
}
