package androidx.compose.ui.graphics.vector;

import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.Composition;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.CompositionImpl;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.BlendModeColorFilter;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import java.util.ArrayList;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class VectorPainterKt {
    /* JADX WARN: Removed duplicated region for block: B:15:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x005d  */
    /* JADX WARN: Type inference failed for: r5v19, types: [androidx.compose.ui.graphics.vector.VectorPainterKt$RenderVectorGroup$1, kotlin.jvm.internal.Lambda] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void RenderVectorGroup(final androidx.compose.ui.graphics.vector.VectorGroup r26, java.util.Map r27, androidx.compose.runtime.Composer r28, final int r29, final int r30) {
        /*
            Method dump skipped, instructions count: 589
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.vector.VectorPainterKt.RenderVectorGroup(androidx.compose.ui.graphics.vector.VectorGroup, java.util.Map, androidx.compose.runtime.Composer, int, int):void");
    }

    /* renamed from: configureVectorPainter-T4PVSW8, reason: not valid java name */
    public static final void m442configureVectorPainterT4PVSW8(VectorPainter vectorPainter, long j, long j2, String str, ColorFilter colorFilter, boolean z) {
        ((SnapshotMutableStateImpl) vectorPainter.size$delegate).setValue(new Size(j));
        ((SnapshotMutableStateImpl) vectorPainter.autoMirror$delegate).setValue(Boolean.valueOf(z));
        VectorComponent vectorComponent = vectorPainter.vector;
        ((SnapshotMutableStateImpl) vectorComponent.intrinsicColorFilter$delegate).setValue(colorFilter);
        ((SnapshotMutableStateImpl) vectorComponent.viewportSize$delegate).setValue(new Size(j2));
        vectorComponent.name = str;
    }

    public static final void createGroupComponent(GroupComponent groupComponent, VectorGroup vectorGroup) {
        int size = ((ArrayList) vectorGroup.children).size();
        for (int i = 0; i < size; i++) {
            VectorNode vectorNode = (VectorNode) ((ArrayList) vectorGroup.children).get(i);
            if (vectorNode instanceof VectorPath) {
                PathComponent pathComponent = new PathComponent();
                VectorPath vectorPath = (VectorPath) vectorNode;
                pathComponent.pathData = vectorPath.pathData;
                pathComponent.isPathDirty = true;
                pathComponent.invalidate();
                pathComponent.renderPath.m354setFillTypeoQ8Xj4U(vectorPath.pathFillType);
                pathComponent.invalidate();
                pathComponent.invalidate();
                pathComponent.fill = vectorPath.fill;
                pathComponent.invalidate();
                pathComponent.fillAlpha = vectorPath.fillAlpha;
                pathComponent.invalidate();
                pathComponent.stroke = vectorPath.stroke;
                pathComponent.invalidate();
                pathComponent.strokeAlpha = vectorPath.strokeAlpha;
                pathComponent.invalidate();
                pathComponent.strokeLineWidth = vectorPath.strokeLineWidth;
                pathComponent.isStrokeDirty = true;
                pathComponent.invalidate();
                pathComponent.strokeLineCap = vectorPath.strokeLineCap;
                pathComponent.isStrokeDirty = true;
                pathComponent.invalidate();
                pathComponent.strokeLineJoin = vectorPath.strokeLineJoin;
                pathComponent.isStrokeDirty = true;
                pathComponent.invalidate();
                pathComponent.strokeLineMiter = vectorPath.strokeLineMiter;
                pathComponent.isStrokeDirty = true;
                pathComponent.invalidate();
                pathComponent.trimPathStart = vectorPath.trimPathStart;
                pathComponent.isTrimPathDirty = true;
                pathComponent.invalidate();
                pathComponent.trimPathEnd = vectorPath.trimPathEnd;
                pathComponent.isTrimPathDirty = true;
                pathComponent.invalidate();
                pathComponent.trimPathOffset = vectorPath.trimPathOffset;
                pathComponent.isTrimPathDirty = true;
                pathComponent.invalidate();
                groupComponent.insertAt(i, pathComponent);
            } else if (vectorNode instanceof VectorGroup) {
                GroupComponent groupComponent2 = new GroupComponent();
                VectorGroup vectorGroup2 = (VectorGroup) vectorNode;
                groupComponent2.name = vectorGroup2.name;
                groupComponent2.invalidate();
                groupComponent2.rotation = vectorGroup2.rotation;
                groupComponent2.isMatrixDirty = true;
                groupComponent2.invalidate();
                groupComponent2.scaleX = vectorGroup2.scaleX;
                groupComponent2.isMatrixDirty = true;
                groupComponent2.invalidate();
                groupComponent2.scaleY = vectorGroup2.scaleY;
                groupComponent2.isMatrixDirty = true;
                groupComponent2.invalidate();
                groupComponent2.translationX = vectorGroup2.translationX;
                groupComponent2.isMatrixDirty = true;
                groupComponent2.invalidate();
                groupComponent2.translationY = vectorGroup2.translationY;
                groupComponent2.isMatrixDirty = true;
                groupComponent2.invalidate();
                groupComponent2.pivotX = vectorGroup2.pivotX;
                groupComponent2.isMatrixDirty = true;
                groupComponent2.invalidate();
                groupComponent2.pivotY = vectorGroup2.pivotY;
                groupComponent2.isMatrixDirty = true;
                groupComponent2.invalidate();
                groupComponent2.clipPathData = vectorGroup2.clipPathData;
                groupComponent2.isClipPathDirty = true;
                groupComponent2.invalidate();
                createGroupComponent(groupComponent2, vectorGroup2);
                groupComponent.insertAt(i, groupComponent2);
            }
        }
    }

    /* renamed from: obtainViewportSize-Pq9zytI, reason: not valid java name */
    public static final long m443obtainViewportSizePq9zytI(float f, float f2, long j) {
        if (Float.isNaN(f)) {
            f = Float.intBitsToFloat((int) (j >> 32));
        }
        if (Float.isNaN(f2)) {
            f2 = Float.intBitsToFloat((int) (j & 4294967295L));
        }
        return (Float.floatToRawIntBits(f2) & 4294967295L) | (Float.floatToRawIntBits(f) << 32);
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0033, code lost:
    
        if (r2 == androidx.compose.runtime.Composer.Companion.Empty) goto L6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.ui.graphics.vector.VectorPainter rememberVectorPainter(androidx.compose.ui.graphics.vector.ImageVector r14, androidx.compose.runtime.Composer r15) {
        /*
            androidx.compose.runtime.OpaqueKey r0 = androidx.compose.runtime.ComposerKt.invocation
            androidx.compose.runtime.StaticProvidableCompositionLocal r0 = androidx.compose.ui.platform.CompositionLocalsKt.LocalDensity
            androidx.compose.runtime.ComposerImpl r15 = (androidx.compose.runtime.ComposerImpl) r15
            java.lang.Object r0 = r15.consume(r0)
            androidx.compose.ui.unit.Density r0 = (androidx.compose.ui.unit.Density) r0
            int r1 = r14.genId
            float r1 = (float) r1
            float r2 = r0.getDensity()
            int r1 = java.lang.Float.floatToRawIntBits(r1)
            long r3 = (long) r1
            int r1 = java.lang.Float.floatToRawIntBits(r2)
            long r1 = (long) r1
            r5 = 32
            long r3 = r3 << r5
            r6 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r1 = r1 & r6
            long r1 = r1 | r3
            boolean r1 = r15.changed(r1)
            java.lang.Object r2 = r15.rememberedValue()
            if (r1 != 0) goto L35
            androidx.compose.runtime.Composer$Companion$Empty$1 r1 = androidx.compose.runtime.Composer.Companion.Empty
            if (r2 != r1) goto L85
        L35:
            androidx.compose.ui.graphics.vector.GroupComponent r1 = new androidx.compose.ui.graphics.vector.GroupComponent
            r1.<init>()
            androidx.compose.ui.graphics.vector.VectorGroup r2 = r14.root
            createGroupComponent(r1, r2)
            float r2 = r14.defaultWidth
            float r2 = r0.mo51toPx0680j_4(r2)
            float r3 = r14.defaultHeight
            float r0 = r0.mo51toPx0680j_4(r3)
            int r2 = java.lang.Float.floatToRawIntBits(r2)
            long r2 = (long) r2
            int r0 = java.lang.Float.floatToRawIntBits(r0)
            long r8 = (long) r0
            long r2 = r2 << r5
            long r4 = r8 & r6
            long r7 = r2 | r4
            float r0 = r14.viewportWidth
            float r2 = r14.viewportHeight
            long r9 = m443obtainViewportSizePq9zytI(r0, r2, r7)
            androidx.compose.ui.graphics.vector.VectorPainter r2 = new androidx.compose.ui.graphics.vector.VectorPainter
            r2.<init>(r1)
            r0 = 16
            long r3 = r14.tintColor
            int r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r0 == 0) goto L78
            androidx.compose.ui.graphics.BlendModeColorFilter r0 = new androidx.compose.ui.graphics.BlendModeColorFilter
            int r1 = r14.tintBlendMode
            r0.<init>(r3, r1)
        L76:
            r12 = r0
            goto L7a
        L78:
            r0 = 0
            goto L76
        L7a:
            java.lang.String r11 = r14.name
            boolean r13 = r14.autoMirror
            r6 = r2
            m442configureVectorPainterT4PVSW8(r6, r7, r9, r11, r12, r13)
            r15.updateRememberedValue(r2)
        L85:
            androidx.compose.ui.graphics.vector.VectorPainter r2 = (androidx.compose.ui.graphics.vector.VectorPainter) r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.vector.VectorPainterKt.rememberVectorPainter(androidx.compose.ui.graphics.vector.ImageVector, androidx.compose.runtime.Composer):androidx.compose.ui.graphics.vector.VectorPainter");
    }

    /* renamed from: rememberVectorPainter-vIP8VLU, reason: not valid java name */
    public static final VectorPainter m444rememberVectorPaintervIP8VLU(float f, float f2, float f3, float f4, String str, long j, int i, final ComposableLambdaImpl composableLambdaImpl, Composer composer) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        Density density = (Density) composerImpl.consume(CompositionLocalsKt.LocalDensity);
        long floatToRawIntBits = (Float.floatToRawIntBits(density.mo51toPx0680j_4(f)) << 32) | (Float.floatToRawIntBits(density.mo51toPx0680j_4(f2)) & 4294967295L);
        final long m443obtainViewportSizePq9zytI = m443obtainViewportSizePq9zytI(f3, f4, floatToRawIntBits);
        boolean changed = composerImpl.changed(j) | composerImpl.changed(i);
        Object rememberedValue = composerImpl.rememberedValue();
        Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
        if (changed || rememberedValue == composer$Companion$Empty$1) {
            rememberedValue = j != 16 ? new BlendModeColorFilter(j, i) : null;
            composerImpl.updateRememberedValue(rememberedValue);
        }
        ColorFilter colorFilter = (ColorFilter) rememberedValue;
        composerImpl.startReplaceGroup(-1837507429);
        Object rememberedValue2 = composerImpl.rememberedValue();
        if (rememberedValue2 == composer$Companion$Empty$1) {
            rememberedValue2 = new VectorPainter(new GroupComponent());
            composerImpl.updateRememberedValue(rememberedValue2);
        }
        VectorPainter vectorPainter = (VectorPainter) rememberedValue2;
        m442configureVectorPainterT4PVSW8(vectorPainter, floatToRawIntBits, m443obtainViewportSizePq9zytI, str, colorFilter, true);
        CompositionContext rememberCompositionContext = ComposablesKt.rememberCompositionContext(composerImpl);
        boolean changed2 = composerImpl.changed(f3) | composerImpl.changed(f4);
        Object rememberedValue3 = composerImpl.rememberedValue();
        Object obj = rememberedValue3;
        if (changed2 || rememberedValue3 == composer$Companion$Empty$1) {
            Composition composition = vectorPainter.composition;
            Composition compositionImpl = (composition == null || composition.isDisposed()) ? new CompositionImpl(rememberCompositionContext, new VectorApplier(vectorPainter.vector.root)) : composition;
            compositionImpl.setContent(new ComposableLambdaImpl(2008312779, true, new Function2() { // from class: androidx.compose.ui.graphics.vector.VectorPainterKt$rememberVectorPainter$2$composition$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    Composer composer2 = (Composer) obj2;
                    if ((((Number) obj3).intValue() & 3) == 2) {
                        ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                        if (composerImpl2.getSkipping()) {
                            composerImpl2.skipToGroupEnd();
                            return Unit.INSTANCE;
                        }
                    }
                    OpaqueKey opaqueKey2 = ComposerKt.invocation;
                    composableLambdaImpl.invoke(Float.valueOf(Float.intBitsToFloat((int) (m443obtainViewportSizePq9zytI >> 32))), Float.valueOf(Float.intBitsToFloat((int) (m443obtainViewportSizePq9zytI & 4294967295L))), composer2, 0);
                    return Unit.INSTANCE;
                }
            }));
            composerImpl.updateRememberedValue(compositionImpl);
            obj = compositionImpl;
        }
        final Composition composition2 = (Composition) obj;
        vectorPainter.composition = composition2;
        boolean changedInstance = composerImpl.changedInstance(composition2);
        Object rememberedValue4 = composerImpl.rememberedValue();
        if (changedInstance || rememberedValue4 == composer$Companion$Empty$1) {
            rememberedValue4 = new Function1() { // from class: androidx.compose.ui.graphics.vector.VectorPainterKt$rememberVectorPainter$2$1$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    final Composition composition3 = Composition.this;
                    return new DisposableEffectResult() { // from class: androidx.compose.ui.graphics.vector.VectorPainterKt$rememberVectorPainter$2$1$1$invoke$$inlined$onDispose$1
                        @Override // androidx.compose.runtime.DisposableEffectResult
                        public final void dispose() {
                            Composition.this.dispose();
                        }
                    };
                }
            };
            composerImpl.updateRememberedValue(rememberedValue4);
        }
        EffectsKt.DisposableEffect(vectorPainter, (Function1) rememberedValue4, composerImpl);
        composerImpl.end(false);
        return vectorPainter;
    }
}
