package com.android.wm.shell.shared.bubbles;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ObservableProperty;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BubblePopupDrawable extends Drawable {
    public static final /* synthetic */ KProperty[] $$delegatedProperties;
    public final BubblePopupDrawable$special$$inlined$observable$1 arrowDirection$delegate;
    public final BubblePopupDrawable$special$$inlined$observable$1 arrowPosition$delegate;
    public final Config config;
    public final Paint paint;
    public final Path path;
    public boolean shouldUpdatePath;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ArrowDirection {
        public static final /* synthetic */ ArrowDirection[] $VALUES;
        public static final ArrowDirection DOWN;
        public static final ArrowDirection UP;

        static {
            ArrowDirection arrowDirection = new ArrowDirection("UP", 0);
            UP = arrowDirection;
            ArrowDirection arrowDirection2 = new ArrowDirection("DOWN", 1);
            DOWN = arrowDirection2;
            ArrowDirection[] arrowDirectionArr = {arrowDirection, arrowDirection2};
            $VALUES = arrowDirectionArr;
            EnumEntriesKt.enumEntries(arrowDirectionArr);
        }

        public static ArrowDirection valueOf(String str) {
            return (ArrowDirection) Enum.valueOf(ArrowDirection.class, str);
        }

        public static ArrowDirection[] values() {
            return (ArrowDirection[]) $VALUES.clone();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class ArrowPosition {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Center extends ArrowPosition {
            public static final Center INSTANCE = new Center();
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class End extends ArrowPosition {
            public static final End INSTANCE = new End();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Config {
        public final float arrowHeight;
        public final float arrowRadius;
        public final float arrowWidth;
        public final int color;
        public final int contentPadding;
        public final float cornerRadius;

        public Config(float f, float f2, float f3, float f4, int i, int i2) {
            this.color = i;
            this.cornerRadius = f;
            this.contentPadding = i2;
            this.arrowWidth = f2;
            this.arrowHeight = f3;
            this.arrowRadius = f4;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Config)) {
                return false;
            }
            Config config = (Config) obj;
            return this.color == config.color && Float.compare(this.cornerRadius, config.cornerRadius) == 0 && this.contentPadding == config.contentPadding && Float.compare(this.arrowWidth, config.arrowWidth) == 0 && Float.compare(this.arrowHeight, config.arrowHeight) == 0 && Float.compare(this.arrowRadius, config.arrowRadius) == 0;
        }

        public final int hashCode() {
            return Float.hashCode(this.arrowRadius) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.contentPadding, FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Integer.hashCode(this.color) * 31, this.cornerRadius, 31), 31), this.arrowWidth, 31), this.arrowHeight, 31);
        }

        public final String toString() {
            return "Config(color=" + this.color + ", cornerRadius=" + this.cornerRadius + ", contentPadding=" + this.contentPadding + ", arrowWidth=" + this.arrowWidth + ", arrowHeight=" + this.arrowHeight + ", arrowRadius=" + this.arrowRadius + ")";
        }
    }

    static {
        MutablePropertyReference1Impl mutablePropertyReference1Impl = new MutablePropertyReference1Impl(BubblePopupDrawable.class, "arrowDirection", "getArrowDirection()Lcom/android/wm/shell/shared/bubbles/BubblePopupDrawable$ArrowDirection;", 0);
        Reflection.factory.getClass();
        $$delegatedProperties = new KProperty[]{mutablePropertyReference1Impl, new MutablePropertyReference1Impl(BubblePopupDrawable.class, "arrowPosition", "getArrowPosition()Lcom/android/wm/shell/shared/bubbles/BubblePopupDrawable$ArrowPosition;", 0)};
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.wm.shell.shared.bubbles.BubblePopupDrawable$special$$inlined$observable$1] */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.wm.shell.shared.bubbles.BubblePopupDrawable$special$$inlined$observable$1] */
    public BubblePopupDrawable(Config config) {
        this.config = config;
        ArrowDirection arrowDirection = ArrowDirection.UP;
        final int i = 0;
        this.arrowDirection$delegate = new ObservableProperty(this, i) { // from class: com.android.wm.shell.shared.bubbles.BubblePopupDrawable$special$$inlined$observable$1
            public final /* synthetic */ int $r8$classId;
            public final /* synthetic */ BubblePopupDrawable this$0;

            /* JADX WARN: Illegal instructions before constructor call */
            {
                /*
                    r0 = this;
                    r0.$r8$classId = r2
                    switch(r2) {
                        case 1: goto Ld;
                        default: goto L5;
                    }
                L5:
                    com.android.wm.shell.shared.bubbles.BubblePopupDrawable$ArrowDirection r2 = com.android.wm.shell.shared.bubbles.BubblePopupDrawable.ArrowDirection.UP
                    r0.this$0 = r1
                    r0.<init>(r2)
                    return
                Ld:
                    com.android.wm.shell.shared.bubbles.BubblePopupDrawable$ArrowPosition$Center r2 = com.android.wm.shell.shared.bubbles.BubblePopupDrawable.ArrowPosition.Center.INSTANCE
                    r0.this$0 = r1
                    r0.<init>(r2)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.shared.bubbles.BubblePopupDrawable$special$$inlined$observable$1.<init>(com.android.wm.shell.shared.bubbles.BubblePopupDrawable, int):void");
            }

            @Override // kotlin.properties.ObservableProperty
            public final void afterChange(Object obj, Object obj2) {
                switch (this.$r8$classId) {
                    case 0:
                        this.this$0.shouldUpdatePath = true;
                        break;
                    default:
                        this.this$0.shouldUpdatePath = true;
                        break;
                }
            }
        };
        final int i2 = 1;
        this.arrowPosition$delegate = new ObservableProperty
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0015: IPUT 
              (wrap:??:0x0012: CONSTRUCTOR 
              (r2v0 'this' com.android.wm.shell.shared.bubbles.BubblePopupDrawable A[DONT_INLINE, IMMUTABLE_TYPE, THIS])
              (r1v1 'i2' int A[DONT_INLINE])
             A[MD:(com.android.wm.shell.shared.bubbles.BubblePopupDrawable, int):void (m), WRAPPED] (LINE:19) call: com.android.wm.shell.shared.bubbles.BubblePopupDrawable$special$$inlined$observable$1.<init>(com.android.wm.shell.shared.bubbles.BubblePopupDrawable, int):void type: CONSTRUCTOR)
              (r2v0 'this' com.android.wm.shell.shared.bubbles.BubblePopupDrawable A[IMMUTABLE_TYPE, THIS])
             (LINE:22) com.android.wm.shell.shared.bubbles.BubblePopupDrawable.arrowPosition$delegate com.android.wm.shell.shared.bubbles.BubblePopupDrawable$special$$inlined$observable$1 in method: com.android.wm.shell.shared.bubbles.BubblePopupDrawable.<init>(com.android.wm.shell.shared.bubbles.BubblePopupDrawable$Config):void, file: classes2.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:406)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
            	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:285)
            	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
            	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
            	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
            	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
            	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:636)
            	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:297)
            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:286)
            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:270)
            	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:161)
            	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:103)
            	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:45)
            	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:34)
            	at jadx.core.codegen.CodeGen.generate(CodeGen.java:22)
            	at jadx.core.ProcessClass.process(ProcessClass.java:79)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:402)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:390)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:340)
            Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.SSAVar.setCodeVar(jadx.core.dex.instructions.args.CodeVar)" because the return value of "jadx.core.dex.instructions.args.RegisterArg.getSVar()" is null
            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:836)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:487)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
            	... 35 more
            */
        /*
            this = this;
            r2.<init>()
            r2.config = r3
            com.android.wm.shell.shared.bubbles.BubblePopupDrawable$ArrowDirection r0 = com.android.wm.shell.shared.bubbles.BubblePopupDrawable.ArrowDirection.UP
            com.android.wm.shell.shared.bubbles.BubblePopupDrawable$special$$inlined$observable$1 r0 = new com.android.wm.shell.shared.bubbles.BubblePopupDrawable$special$$inlined$observable$1
            r1 = 0
            r0.<init>(r2, r1)
            r2.arrowDirection$delegate = r0
            com.android.wm.shell.shared.bubbles.BubblePopupDrawable$special$$inlined$observable$1 r0 = new com.android.wm.shell.shared.bubbles.BubblePopupDrawable$special$$inlined$observable$1
            r1 = 1
            r0.<init>(r2, r1)
            r2.arrowPosition$delegate = r0
            android.graphics.Path r0 = new android.graphics.Path
            r0.<init>()
            r2.path = r0
            android.graphics.Paint r0 = new android.graphics.Paint
            r0.<init>()
            r2.paint = r0
            r2.shouldUpdatePath = r1
            int r2 = r3.color
            r0.setColor(r2)
            android.graphics.Paint$Style r2 = android.graphics.Paint.Style.FILL
            r0.setStyle(r2)
            r0.setAntiAlias(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.shared.bubbles.BubblePopupDrawable.<init>(com.android.wm.shell.shared.bubbles.BubblePopupDrawable$Config):void");
    }

    public final void addRoundedArrowPositioned(Path path, ArrowPosition arrowPosition) {
        float width;
        Matrix matrix = new Matrix();
        if (arrowPosition instanceof ArrowPosition.Center) {
            width = getBounds().width() / 2.0f;
        } else {
            if (!(arrowPosition instanceof ArrowPosition.End)) {
                throw new NoWhenBranchMatchedException();
            }
            width = getBounds().width();
        }
        Config config = this.config;
        float f = 2;
        float f2 = width - (config.arrowWidth / f);
        float width2 = getBounds().width();
        Config config2 = this.config;
        matrix.setTranslate(-RangesKt.coerceIn(f2, config.cornerRadius, (width2 - config2.cornerRadius) - config2.arrowWidth), 0.0f);
        path.transform(matrix);
        Config config3 = this.config;
        float f3 = config3.arrowWidth / (config3.arrowHeight * 2.0f);
        double atan = (float) Math.atan(f3);
        float degrees = (float) Math.toDegrees(atan);
        float sin = this.config.arrowRadius / ((float) Math.sin(atan));
        float f4 = this.config.arrowRadius / f3;
        float cos = ((float) Math.cos(atan)) * f4;
        float sin2 = f4 * ((float) Math.sin(atan));
        Config config4 = this.config;
        float f5 = config4.arrowWidth / 2.0f;
        path.moveTo(0.0f, config4.arrowHeight);
        path.lineTo(f5 - sin2, cos);
        float f6 = this.config.arrowRadius;
        float f7 = f5 - f6;
        float f8 = sin - f6;
        float f9 = f5 + f6;
        float f10 = sin + f6;
        float f11 = 180;
        path.arcTo(f7, f8, f9, f10, f11 + degrees, f11 - (f * degrees), false);
        Config config5 = this.config;
        path.lineTo(config5.arrowWidth, config5.arrowHeight);
        path.close();
        matrix.invert(matrix);
        path.transform(matrix);
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        updatePathIfNeeded();
        canvas.drawPath(this.path, this.paint);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return this.paint.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public final void getOutline(Outline outline) {
        updatePathIfNeeded();
        outline.setPath(this.path);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean getPadding(Rect rect) {
        int i = this.config.contentPadding;
        rect.set(i, i, i, i);
        BubblePopupDrawable$special$$inlined$observable$1 bubblePopupDrawable$special$$inlined$observable$1 = this.arrowDirection$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        int ordinal = ((ArrowDirection) bubblePopupDrawable$special$$inlined$observable$1.value).ordinal();
        if (ordinal == 0) {
            rect.top += (int) this.config.arrowHeight;
        } else if (ordinal == 1) {
            rect.bottom += (int) this.config.arrowHeight;
        }
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        this.shouldUpdatePath = true;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        this.paint.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.paint.setColorFilter(colorFilter);
    }

    public final void updatePathIfNeeded() {
        if (this.shouldUpdatePath) {
            if (!getBounds().isEmpty()) {
                this.path.reset();
                RectF rectF = new RectF(getBounds());
                BubblePopupDrawable$special$$inlined$observable$1 bubblePopupDrawable$special$$inlined$observable$1 = this.arrowDirection$delegate;
                KProperty[] kPropertyArr = $$delegatedProperties;
                KProperty kProperty = kPropertyArr[0];
                int ordinal = ((ArrowDirection) bubblePopupDrawable$special$$inlined$observable$1.value).ordinal();
                if (ordinal == 0) {
                    Path path = this.path;
                    BubblePopupDrawable$special$$inlined$observable$1 bubblePopupDrawable$special$$inlined$observable$12 = this.arrowPosition$delegate;
                    KProperty kProperty2 = kPropertyArr[1];
                    addRoundedArrowPositioned(path, (ArrowPosition) bubblePopupDrawable$special$$inlined$observable$12.value);
                    rectF.top += this.config.arrowHeight;
                } else if (ordinal == 1) {
                    Matrix matrix = new Matrix();
                    matrix.setScale(1.0f, -1.0f, getBounds().width() / 2.0f, getBounds().height() / 2.0f);
                    this.path.transform(matrix);
                    Path path2 = this.path;
                    BubblePopupDrawable$special$$inlined$observable$1 bubblePopupDrawable$special$$inlined$observable$13 = this.arrowPosition$delegate;
                    KProperty kProperty3 = kPropertyArr[1];
                    addRoundedArrowPositioned(path2, (ArrowPosition) bubblePopupDrawable$special$$inlined$observable$13.value);
                    matrix.invert(matrix);
                    this.path.transform(matrix);
                    rectF.bottom -= this.config.arrowHeight;
                }
                Path path3 = this.path;
                float f = this.config.cornerRadius;
                path3.addRoundRect(rectF, f, f, Path.Direction.CW);
            }
            this.shouldUpdatePath = false;
        }
    }
}
