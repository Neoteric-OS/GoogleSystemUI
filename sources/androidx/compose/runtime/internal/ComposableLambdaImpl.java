package androidx.compose.runtime.internal;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.RecomposeScope;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ComposableLambdaImpl implements ComposableLambda {
    public Object _block;
    public final int key;
    public RecomposeScopeImpl scope;
    public List scopes;
    public final boolean tracked;

    public ComposableLambdaImpl(int i, boolean z, Object obj) {
        this.key = i;
        this.tracked = z;
        this._block = obj;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke(((Number) obj2).intValue(), (Composer) obj);
    }

    public final void trackRead(Composer composer) {
        ComposerImpl composerImpl;
        RecomposeScopeImpl currentRecomposeScope$runtime_release;
        if (!this.tracked || (currentRecomposeScope$runtime_release = (composerImpl = (ComposerImpl) composer).getCurrentRecomposeScope$runtime_release()) == null) {
            return;
        }
        composerImpl.getClass();
        currentRecomposeScope$runtime_release.flags |= 1;
        if (ComposableLambdaKt.replacableWith(this.scope, currentRecomposeScope$runtime_release)) {
            this.scope = currentRecomposeScope$runtime_release;
            return;
        }
        List list = this.scopes;
        if (list == null) {
            ArrayList arrayList = new ArrayList();
            this.scopes = arrayList;
            arrayList.add(currentRecomposeScope$runtime_release);
            return;
        }
        ArrayList arrayList2 = (ArrayList) list;
        int size = arrayList2.size();
        for (int i = 0; i < size; i++) {
            if (ComposableLambdaKt.replacableWith((RecomposeScope) arrayList2.get(i), currentRecomposeScope$runtime_release)) {
                list.set(i, currentRecomposeScope$runtime_release);
                return;
            }
        }
        list.add(currentRecomposeScope$runtime_release);
    }

    @Override // kotlin.jvm.functions.Function3
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return invoke(obj, (Composer) obj2, ((Number) obj3).intValue());
    }

    @Override // kotlin.jvm.functions.Function4
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        return invoke(obj, obj2, (Composer) obj3, ((Number) obj4).intValue());
    }

    @Override // kotlin.jvm.functions.Function5
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return invoke(obj, obj2, obj3, (Composer) obj4, ((Number) obj5).intValue());
    }

    @Override // kotlin.jvm.functions.Function6
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return invoke(obj, obj2, obj3, obj4, (Composer) obj5, ((Number) obj6).intValue());
    }

    public final Object invoke(int i, Composer composer) {
        int bitsForSlot;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(this.key);
        trackRead(composerImpl);
        if (composerImpl.changed(this)) {
            bitsForSlot = ComposableLambdaKt.bitsForSlot(2, 0);
        } else {
            bitsForSlot = ComposableLambdaKt.bitsForSlot(1, 0);
        }
        int i2 = i | bitsForSlot;
        Object obj = this._block;
        TypeIntrinsics.beforeCheckcastToFunctionOfArity(2, obj);
        Object invoke = ((Function2) obj).invoke(composerImpl, Integer.valueOf(i2));
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new ComposableLambdaImpl$invoke$1(2, this, ComposableLambdaImpl.class, "invoke", "invoke(Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;", 8);
        }
        return invoke;
    }

    public final Object invoke(final Object obj, Composer composer, final int i) {
        int bitsForSlot;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(this.key);
        trackRead(composerImpl);
        if (composerImpl.changed(this)) {
            bitsForSlot = ComposableLambdaKt.bitsForSlot(2, 1);
        } else {
            bitsForSlot = ComposableLambdaKt.bitsForSlot(1, 1);
        }
        Object obj2 = this._block;
        TypeIntrinsics.beforeCheckcastToFunctionOfArity(3, obj2);
        Object invoke = ((Function3) obj2).invoke(obj, composerImpl, Integer.valueOf(bitsForSlot | i));
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.runtime.internal.ComposableLambdaImpl$invoke$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    ((Number) obj4).intValue();
                    ComposableLambdaImpl.this.invoke(obj, (Composer) obj3, RecomposeScopeImplKt.updateChangedFlags(i) | 1);
                    return Unit.INSTANCE;
                }
            };
        }
        return invoke;
    }

    public final Object invoke(final Object obj, final Object obj2, Composer composer, final int i) {
        int bitsForSlot;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(this.key);
        trackRead(composerImpl);
        if (composerImpl.changed(this)) {
            bitsForSlot = ComposableLambdaKt.bitsForSlot(2, 2);
        } else {
            bitsForSlot = ComposableLambdaKt.bitsForSlot(1, 2);
        }
        Object obj3 = this._block;
        TypeIntrinsics.beforeCheckcastToFunctionOfArity(4, obj3);
        Object invoke = ((Function4) obj3).invoke(obj, obj2, composerImpl, Integer.valueOf(bitsForSlot | i));
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.runtime.internal.ComposableLambdaImpl$invoke$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj4, Object obj5) {
                    ((Number) obj5).intValue();
                    ComposableLambdaImpl.this.invoke(obj, obj2, (Composer) obj4, RecomposeScopeImplKt.updateChangedFlags(i) | 1);
                    return Unit.INSTANCE;
                }
            };
        }
        return invoke;
    }

    public final Object invoke(final Object obj, final Object obj2, final Object obj3, Composer composer, final int i) {
        int bitsForSlot;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(this.key);
        trackRead(composerImpl);
        if (composerImpl.changed(this)) {
            bitsForSlot = ComposableLambdaKt.bitsForSlot(2, 3);
        } else {
            bitsForSlot = ComposableLambdaKt.bitsForSlot(1, 3);
        }
        Object obj4 = this._block;
        TypeIntrinsics.beforeCheckcastToFunctionOfArity(5, obj4);
        Object invoke = ((Function5) obj4).invoke(obj, obj2, obj3, composerImpl, Integer.valueOf(bitsForSlot | i));
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.runtime.internal.ComposableLambdaImpl$invoke$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj5, Object obj6) {
                    ((Number) obj6).intValue();
                    ComposableLambdaImpl.this.invoke(obj, obj2, obj3, (Composer) obj5, RecomposeScopeImplKt.updateChangedFlags(i) | 1);
                    return Unit.INSTANCE;
                }
            };
        }
        return invoke;
    }

    public final Object invoke(final Object obj, final Object obj2, final Object obj3, final Object obj4, Composer composer, final int i) {
        int bitsForSlot;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(this.key);
        trackRead(composerImpl);
        if (composerImpl.changed(this)) {
            bitsForSlot = ComposableLambdaKt.bitsForSlot(2, 4);
        } else {
            bitsForSlot = ComposableLambdaKt.bitsForSlot(1, 4);
        }
        Object obj5 = this._block;
        TypeIntrinsics.beforeCheckcastToFunctionOfArity(6, obj5);
        Object invoke = ((Function6) obj5).invoke(obj, obj2, obj3, obj4, composerImpl, Integer.valueOf(bitsForSlot | i));
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.runtime.internal.ComposableLambdaImpl$invoke$5
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj6, Object obj7) {
                    ((Number) obj7).intValue();
                    ComposableLambdaImpl.this.invoke(obj, obj2, obj3, obj4, (Composer) obj6, RecomposeScopeImplKt.updateChangedFlags(i) | 1);
                    return Unit.INSTANCE;
                }
            };
        }
        return invoke;
    }
}
