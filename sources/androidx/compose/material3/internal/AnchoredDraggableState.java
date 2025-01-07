package androidx.compose.material3.internal;

import androidx.compose.material3.SheetValue;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotMutableFloatStateImpl;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AnchoredDraggableState {
    public final AnchoredDraggableState$anchoredDragScope$1 anchoredDragScope;
    public final MutableState anchors$delegate;
    public final Function0 animationSpec;
    public final Function1 confirmValueChange;
    public final MutableState currentValue$delegate;
    public final MutableState dragTarget$delegate;
    public final MutableFloatState lastVelocity$delegate;
    public final Function1 positionalThreshold;
    public final Function0 velocityThreshold;
    public final InternalMutatorMutex dragMutex = new InternalMutatorMutex();
    public final AnchoredDraggableState$draggableState$1 draggableState = new AnchoredDraggableState$draggableState$1(this);
    public final State targetValue$delegate = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.material3.internal.AnchoredDraggableState$targetValue$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            Object value = ((SnapshotMutableStateImpl) AnchoredDraggableState.this.dragTarget$delegate).getValue();
            if (value != null) {
                return value;
            }
            AnchoredDraggableState anchoredDraggableState = AnchoredDraggableState.this;
            float offset = anchoredDraggableState.getOffset();
            boolean isNaN = Float.isNaN(offset);
            MutableState mutableState = anchoredDraggableState.currentValue$delegate;
            return !isNaN ? anchoredDraggableState.computeTarget(offset, 0.0f, ((SnapshotMutableStateImpl) mutableState).getValue()) : ((SnapshotMutableStateImpl) mutableState).getValue();
        }
    });
    public final State closestValue$delegate = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.material3.internal.AnchoredDraggableState$closestValue$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            Object closestAnchor;
            Object value = ((SnapshotMutableStateImpl) AnchoredDraggableState.this.dragTarget$delegate).getValue();
            if (value != null) {
                return value;
            }
            AnchoredDraggableState anchoredDraggableState = AnchoredDraggableState.this;
            float offset = anchoredDraggableState.getOffset();
            boolean isNaN = Float.isNaN(offset);
            MutableState mutableState = anchoredDraggableState.currentValue$delegate;
            if (isNaN) {
                return ((SnapshotMutableStateImpl) mutableState).getValue();
            }
            Object value2 = ((SnapshotMutableStateImpl) mutableState).getValue();
            MapDraggableAnchors mapDraggableAnchors = (MapDraggableAnchors) anchoredDraggableState.getAnchors();
            float positionOf = mapDraggableAnchors.positionOf(value2);
            if (positionOf != offset && !Float.isNaN(positionOf) && (positionOf >= offset ? (closestAnchor = mapDraggableAnchors.closestAnchor(offset, false)) != null : (closestAnchor = mapDraggableAnchors.closestAnchor(offset, true)) != null)) {
                value2 = closestAnchor;
            }
            return value2;
        }
    });
    public final MutableFloatState offset$delegate = PrimitiveSnapshotStateKt.mutableFloatStateOf(Float.NaN);

    public AnchoredDraggableState(SheetValue sheetValue, Function1 function1, Function0 function0, Function0 function02, Function1 function12) {
        this.positionalThreshold = function1;
        this.velocityThreshold = function0;
        this.animationSpec = function02;
        this.confirmValueChange = function12;
        this.currentValue$delegate = SnapshotStateKt.mutableStateOf$default(sheetValue);
        SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0() { // from class: androidx.compose.material3.internal.AnchoredDraggableState$progress$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                float positionOf = ((MapDraggableAnchors) AnchoredDraggableState.this.getAnchors()).positionOf(((SnapshotMutableStateImpl) AnchoredDraggableState.this.currentValue$delegate).getValue());
                float positionOf2 = ((MapDraggableAnchors) AnchoredDraggableState.this.getAnchors()).positionOf(AnchoredDraggableState.this.closestValue$delegate.getValue()) - positionOf;
                float abs = Math.abs(positionOf2);
                float f = 1.0f;
                if (!Float.isNaN(abs) && abs > 1.0E-6f) {
                    float requireOffset = (AnchoredDraggableState.this.requireOffset() - positionOf) / positionOf2;
                    if (requireOffset < 1.0E-6f) {
                        f = 0.0f;
                    } else if (requireOffset <= 0.999999f) {
                        f = requireOffset;
                    }
                }
                return Float.valueOf(f);
            }
        });
        this.lastVelocity$delegate = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.dragTarget$delegate = SnapshotStateKt.mutableStateOf$default(null);
        this.anchors$delegate = SnapshotStateKt.mutableStateOf$default(new MapDraggableAnchors(MapsKt.emptyMap()));
        this.anchoredDragScope = new AnchoredDraggableState$anchoredDragScope$1(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object anchoredDrag(androidx.compose.foundation.MutatePriority r7, kotlin.jvm.functions.Function3 r8, kotlin.coroutines.jvm.internal.ContinuationImpl r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof androidx.compose.material3.internal.AnchoredDraggableState$anchoredDrag$1
            if (r0 == 0) goto L13
            r0 = r9
            androidx.compose.material3.internal.AnchoredDraggableState$anchoredDrag$1 r0 = (androidx.compose.material3.internal.AnchoredDraggableState$anchoredDrag$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.material3.internal.AnchoredDraggableState$anchoredDrag$1 r0 = new androidx.compose.material3.internal.AnchoredDraggableState$anchoredDrag$1
            r0.<init>(r6, r9)
        L18:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1056964608(0x3f000000, float:0.5)
            r4 = 1
            if (r2 == 0) goto L37
            if (r2 != r4) goto L2f
            java.lang.Object r6 = r0.L$0
            androidx.compose.material3.internal.AnchoredDraggableState r6 = (androidx.compose.material3.internal.AnchoredDraggableState) r6
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L2d
            goto L55
        L2d:
            r7 = move-exception
            goto L90
        L2f:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L37:
            kotlin.ResultKt.throwOnFailure(r9)
            androidx.compose.material3.internal.InternalMutatorMutex r9 = r6.dragMutex     // Catch: java.lang.Throwable -> L2d
            androidx.compose.material3.internal.AnchoredDraggableState$anchoredDrag$2 r2 = new androidx.compose.material3.internal.AnchoredDraggableState$anchoredDrag$2     // Catch: java.lang.Throwable -> L2d
            r5 = 0
            r2.<init>(r6, r5, r8)     // Catch: java.lang.Throwable -> L2d
            r0.L$0 = r6     // Catch: java.lang.Throwable -> L2d
            r0.label = r4     // Catch: java.lang.Throwable -> L2d
            r9.getClass()     // Catch: java.lang.Throwable -> L2d
            androidx.compose.material3.internal.InternalMutatorMutex$mutate$2 r8 = new androidx.compose.material3.internal.InternalMutatorMutex$mutate$2     // Catch: java.lang.Throwable -> L2d
            r8.<init>(r7, r9, r2, r5)     // Catch: java.lang.Throwable -> L2d
            java.lang.Object r7 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r0, r8)     // Catch: java.lang.Throwable -> L2d
            if (r7 != r1) goto L55
            return r1
        L55:
            androidx.compose.material3.internal.DraggableAnchors r7 = r6.getAnchors()
            float r8 = r6.getOffset()
            androidx.compose.material3.internal.MapDraggableAnchors r7 = (androidx.compose.material3.internal.MapDraggableAnchors) r7
            java.lang.Object r7 = r7.closestAnchor(r8)
            if (r7 == 0) goto L8d
            float r8 = r6.getOffset()
            androidx.compose.material3.internal.DraggableAnchors r9 = r6.getAnchors()
            androidx.compose.material3.internal.MapDraggableAnchors r9 = (androidx.compose.material3.internal.MapDraggableAnchors) r9
            float r9 = r9.positionOf(r7)
            float r8 = r8 - r9
            float r8 = java.lang.Math.abs(r8)
            int r8 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r8 > 0) goto L8d
            kotlin.jvm.functions.Function1 r8 = r6.confirmValueChange
            java.lang.Object r8 = r8.invoke(r7)
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L8d
            r6.setCurrentValue(r7)
        L8d:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L90:
            androidx.compose.material3.internal.DraggableAnchors r8 = r6.getAnchors()
            float r9 = r6.getOffset()
            androidx.compose.material3.internal.MapDraggableAnchors r8 = (androidx.compose.material3.internal.MapDraggableAnchors) r8
            java.lang.Object r8 = r8.closestAnchor(r9)
            if (r8 == 0) goto Lc8
            float r9 = r6.getOffset()
            androidx.compose.material3.internal.DraggableAnchors r0 = r6.getAnchors()
            androidx.compose.material3.internal.MapDraggableAnchors r0 = (androidx.compose.material3.internal.MapDraggableAnchors) r0
            float r0 = r0.positionOf(r8)
            float r9 = r9 - r0
            float r9 = java.lang.Math.abs(r9)
            int r9 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r9 > 0) goto Lc8
            kotlin.jvm.functions.Function1 r9 = r6.confirmValueChange
            java.lang.Object r9 = r9.invoke(r8)
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto Lc8
            r6.setCurrentValue(r8)
        Lc8:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.internal.AnchoredDraggableState.anchoredDrag(androidx.compose.foundation.MutatePriority, kotlin.jvm.functions.Function3, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final Object computeTarget(float f, float f2, Object obj) {
        Object closestAnchor;
        MapDraggableAnchors mapDraggableAnchors = (MapDraggableAnchors) getAnchors();
        float positionOf = mapDraggableAnchors.positionOf(obj);
        float floatValue = ((Number) this.velocityThreshold.invoke()).floatValue();
        if (positionOf == f || Float.isNaN(positionOf)) {
            return obj;
        }
        Function1 function1 = this.positionalThreshold;
        if (positionOf < f) {
            if (f2 >= floatValue) {
                Object closestAnchor2 = mapDraggableAnchors.closestAnchor(f, true);
                Intrinsics.checkNotNull(closestAnchor2);
                return closestAnchor2;
            }
            closestAnchor = mapDraggableAnchors.closestAnchor(f, true);
            Intrinsics.checkNotNull(closestAnchor);
            if (f < Math.abs(Math.abs(((Number) function1.invoke(Float.valueOf(Math.abs(mapDraggableAnchors.positionOf(closestAnchor) - positionOf)))).floatValue()) + positionOf)) {
                return obj;
            }
        } else {
            if (f2 <= (-floatValue)) {
                Object closestAnchor3 = mapDraggableAnchors.closestAnchor(f, false);
                Intrinsics.checkNotNull(closestAnchor3);
                return closestAnchor3;
            }
            closestAnchor = mapDraggableAnchors.closestAnchor(f, false);
            Intrinsics.checkNotNull(closestAnchor);
            float abs = Math.abs(positionOf - Math.abs(((Number) function1.invoke(Float.valueOf(Math.abs(positionOf - mapDraggableAnchors.positionOf(closestAnchor))))).floatValue()));
            if (f < 0.0f) {
                if (Math.abs(f) < abs) {
                    return obj;
                }
            } else if (f > abs) {
                return obj;
            }
        }
        return closestAnchor;
    }

    public final DraggableAnchors getAnchors() {
        return (DraggableAnchors) ((SnapshotMutableStateImpl) this.anchors$delegate).getValue();
    }

    public final float getOffset() {
        return ((SnapshotMutableFloatStateImpl) this.offset$delegate).getFloatValue();
    }

    public final float newOffsetForDelta$material3_release(float f) {
        float offset = (Float.isNaN(getOffset()) ? 0.0f : getOffset()) + f;
        float minAnchor = ((MapDraggableAnchors) getAnchors()).minAnchor();
        Float maxOrNull = CollectionsKt.maxOrNull(((MapDraggableAnchors) getAnchors()).anchors.values());
        return RangesKt.coerceIn(offset, minAnchor, maxOrNull != null ? maxOrNull.floatValue() : Float.NaN);
    }

    public final float requireOffset() {
        if (Float.isNaN(getOffset())) {
            throw new IllegalStateException("The offset was read before being initialized. Did you access the offset in a phase before layout, like effects or composition?");
        }
        return getOffset();
    }

    public final void setCurrentValue(Object obj) {
        ((SnapshotMutableStateImpl) this.currentValue$delegate).setValue(obj);
    }

    public final void setDragTarget(Object obj) {
        ((SnapshotMutableStateImpl) this.dragTarget$delegate).setValue(obj);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object anchoredDrag(java.lang.Object r7, androidx.compose.foundation.MutatePriority r8, kotlin.jvm.functions.Function4 r9, kotlin.coroutines.jvm.internal.ContinuationImpl r10) {
        /*
            r6 = this;
            boolean r0 = r10 instanceof androidx.compose.material3.internal.AnchoredDraggableState$anchoredDrag$3
            if (r0 == 0) goto L13
            r0 = r10
            androidx.compose.material3.internal.AnchoredDraggableState$anchoredDrag$3 r0 = (androidx.compose.material3.internal.AnchoredDraggableState$anchoredDrag$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.material3.internal.AnchoredDraggableState$anchoredDrag$3 r0 = new androidx.compose.material3.internal.AnchoredDraggableState$anchoredDrag$3
            r0.<init>(r6, r10)
        L18:
            java.lang.Object r10 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 0
            r4 = 1056964608(0x3f000000, float:0.5)
            r5 = 1
            if (r2 == 0) goto L38
            if (r2 != r5) goto L30
            java.lang.Object r6 = r0.L$0
            androidx.compose.material3.internal.AnchoredDraggableState r6 = (androidx.compose.material3.internal.AnchoredDraggableState) r6
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L2e
            goto L63
        L2e:
            r7 = move-exception
            goto L9f
        L30:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L38:
            kotlin.ResultKt.throwOnFailure(r10)
            androidx.compose.material3.internal.DraggableAnchors r10 = r6.getAnchors()
            androidx.compose.material3.internal.MapDraggableAnchors r10 = (androidx.compose.material3.internal.MapDraggableAnchors) r10
            java.util.Map r10 = r10.anchors
            boolean r10 = r10.containsKey(r7)
            if (r10 == 0) goto Ldb
            androidx.compose.material3.internal.InternalMutatorMutex r10 = r6.dragMutex     // Catch: java.lang.Throwable -> L2e
            androidx.compose.material3.internal.AnchoredDraggableState$anchoredDrag$4 r2 = new androidx.compose.material3.internal.AnchoredDraggableState$anchoredDrag$4     // Catch: java.lang.Throwable -> L2e
            r2.<init>(r6, r7, r9, r3)     // Catch: java.lang.Throwable -> L2e
            r0.L$0 = r6     // Catch: java.lang.Throwable -> L2e
            r0.label = r5     // Catch: java.lang.Throwable -> L2e
            r10.getClass()     // Catch: java.lang.Throwable -> L2e
            androidx.compose.material3.internal.InternalMutatorMutex$mutate$2 r7 = new androidx.compose.material3.internal.InternalMutatorMutex$mutate$2     // Catch: java.lang.Throwable -> L2e
            r7.<init>(r8, r10, r2, r3)     // Catch: java.lang.Throwable -> L2e
            java.lang.Object r7 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r0, r7)     // Catch: java.lang.Throwable -> L2e
            if (r7 != r1) goto L63
            return r1
        L63:
            r6.setDragTarget(r3)
            androidx.compose.material3.internal.DraggableAnchors r7 = r6.getAnchors()
            float r8 = r6.getOffset()
            androidx.compose.material3.internal.MapDraggableAnchors r7 = (androidx.compose.material3.internal.MapDraggableAnchors) r7
            java.lang.Object r7 = r7.closestAnchor(r8)
            if (r7 == 0) goto Lde
            float r8 = r6.getOffset()
            androidx.compose.material3.internal.DraggableAnchors r9 = r6.getAnchors()
            androidx.compose.material3.internal.MapDraggableAnchors r9 = (androidx.compose.material3.internal.MapDraggableAnchors) r9
            float r9 = r9.positionOf(r7)
            float r8 = r8 - r9
            float r8 = java.lang.Math.abs(r8)
            int r8 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r8 > 0) goto Lde
            kotlin.jvm.functions.Function1 r8 = r6.confirmValueChange
            java.lang.Object r8 = r8.invoke(r7)
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto Lde
            r6.setCurrentValue(r7)
            goto Lde
        L9f:
            r6.setDragTarget(r3)
            androidx.compose.material3.internal.DraggableAnchors r8 = r6.getAnchors()
            float r9 = r6.getOffset()
            androidx.compose.material3.internal.MapDraggableAnchors r8 = (androidx.compose.material3.internal.MapDraggableAnchors) r8
            java.lang.Object r8 = r8.closestAnchor(r9)
            if (r8 == 0) goto Lda
            float r9 = r6.getOffset()
            androidx.compose.material3.internal.DraggableAnchors r10 = r6.getAnchors()
            androidx.compose.material3.internal.MapDraggableAnchors r10 = (androidx.compose.material3.internal.MapDraggableAnchors) r10
            float r10 = r10.positionOf(r8)
            float r9 = r9 - r10
            float r9 = java.lang.Math.abs(r9)
            int r9 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r9 > 0) goto Lda
            kotlin.jvm.functions.Function1 r9 = r6.confirmValueChange
            java.lang.Object r9 = r9.invoke(r8)
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto Lda
            r6.setCurrentValue(r8)
        Lda:
            throw r7
        Ldb:
            r6.setCurrentValue(r7)
        Lde:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.internal.AnchoredDraggableState.anchoredDrag(java.lang.Object, androidx.compose.foundation.MutatePriority, kotlin.jvm.functions.Function4, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
