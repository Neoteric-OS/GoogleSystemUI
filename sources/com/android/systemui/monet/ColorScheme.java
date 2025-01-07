package com.android.systemui.monet;

import android.app.WallpaperColors;
import androidx.constraintlayout.core.PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0;
import com.google.ux.material.libmonet.dynamiccolor.DynamicScheme;
import com.google.ux.material.libmonet.hct.Cam16;
import com.google.ux.material.libmonet.hct.Hct;
import com.google.ux.material.libmonet.hct.ViewingConditions;
import com.google.ux.material.libmonet.utils.ColorUtils;
import com.google.ux.material.libmonet.utils.MathUtils;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ColorScheme {
    public final TonalPalette mAccent1;
    public final TonalPalette mAccent2;
    public final TonalPalette mAccent3;
    public final DynamicScheme mMaterialScheme;
    public final TonalPalette mNeutral1;
    public final TonalPalette mNeutral2;
    public final int mSeed;
    public final Style mStyle;

    /*  JADX ERROR: NullPointerException in pass: ConstructorVisitor
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.sameRegAndSVar(jadx.core.dex.instructions.args.InsnArg)" because "resultArg" is null
        	at jadx.core.dex.visitors.MoveInlineVisitor.processMove(MoveInlineVisitor.java:52)
        	at jadx.core.dex.visitors.MoveInlineVisitor.moveInline(MoveInlineVisitor.java:41)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:43)
        */
    public ColorScheme(
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r46v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:238)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:223)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:168)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:401)
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
        */
    /*  JADX ERROR: NullPointerException in pass: ConstructorVisitor
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.sameRegAndSVar(jadx.core.dex.instructions.args.InsnArg)" because "resultArg" is null
        	at jadx.core.dex.visitors.MoveInlineVisitor.processMove(MoveInlineVisitor.java:52)
        	at jadx.core.dex.visitors.MoveInlineVisitor.moveInline(MoveInlineVisitor.java:41)
        */

    public static List getSeedColors(WallpaperColors wallpaperColors, final boolean z) {
        final double sum = wallpaperColors.getAllColors().values().stream().mapToInt(new ColorScheme$$ExternalSyntheticLambda0()).sum();
        if (sum == 0.0d) {
            List list = (List) wallpaperColors.getMainColors().stream().map(new ColorScheme$$ExternalSyntheticLambda1(3)).distinct().filter(new Predicate() { // from class: com.android.systemui.monet.ColorScheme$$ExternalSyntheticLambda6
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean z2;
                    Integer num = (Integer) obj;
                    if (z) {
                        int intValue = num.intValue();
                        ViewingConditions viewingConditions = ViewingConditions.DEFAULT;
                        int i = intValue & 255;
                        double linearized = ColorUtils.linearized((16711680 & intValue) >> 16);
                        double linearized2 = ColorUtils.linearized((65280 & intValue) >> 8);
                        double linearized3 = ColorUtils.linearized(i);
                        double d = (0.18051042d * linearized3) + (0.35762064d * linearized2) + (0.41233895d * linearized);
                        double d2 = (0.0722d * linearized3) + (0.7152d * linearized2) + (0.2126d * linearized);
                        double d3 = (linearized3 * 0.95034478d) + (linearized2 * 0.11916382d) + (linearized * 0.01932141d);
                        double[][] dArr = Cam16.XYZ_TO_CAM16RGB;
                        double[] dArr2 = dArr[0];
                        double d4 = (dArr2[2] * d3) + (dArr2[1] * d2) + (dArr2[0] * d);
                        double[] dArr3 = dArr[1];
                        double d5 = (dArr3[2] * d3) + (dArr3[1] * d2) + (dArr3[0] * d);
                        double[] dArr4 = dArr[2];
                        double d6 = (d3 * dArr4[2]) + (d2 * dArr4[1]) + (d * dArr4[0]);
                        double[] dArr5 = viewingConditions.rgbD;
                        double d7 = dArr5[0] * d4;
                        double d8 = dArr5[1] * d5;
                        double d9 = dArr5[2] * d6;
                        double abs = Math.abs(d7);
                        double d10 = viewingConditions.fl;
                        double pow = Math.pow((abs * d10) / 100.0d, 0.42d);
                        double pow2 = Math.pow((Math.abs(d8) * d10) / 100.0d, 0.42d);
                        double pow3 = Math.pow((Math.abs(d9) * d10) / 100.0d, 0.42d);
                        double signum = ((Math.signum(d7) * 400.0d) * pow) / (pow + 27.13d);
                        double signum2 = ((Math.signum(d8) * 400.0d) * pow2) / (pow2 + 27.13d);
                        double signum3 = ((Math.signum(d9) * 400.0d) * pow3) / (pow3 + 27.13d);
                        double d11 = ((((-12.0d) * signum2) + (signum * 11.0d)) + signum3) / 11.0d;
                        double d12 = ((signum + signum2) - (signum3 * 2.0d)) / 9.0d;
                        double d13 = signum2 * 20.0d;
                        double d14 = ((21.0d * signum3) + ((signum * 20.0d) + d13)) / 20.0d;
                        double d15 = (((signum * 40.0d) + d13) + signum3) / 20.0d;
                        double degrees = Math.toDegrees(Math.atan2(d12, d11));
                        if (degrees < 0.0d) {
                            degrees += 360.0d;
                        } else if (degrees >= 360.0d) {
                            degrees -= 360.0d;
                        }
                        double radians = Math.toRadians(degrees);
                        double d16 = d15 * viewingConditions.nbb;
                        double d17 = viewingConditions.aw;
                        double d18 = viewingConditions.c;
                        double pow4 = (Math.pow(d16 / d17, viewingConditions.z * d18) * 100.0d) / 100.0d;
                        Math.sqrt(pow4);
                        double d19 = d17 + 4.0d;
                        if (degrees < 20.14d) {
                            degrees += 360.0d;
                        }
                        double pow5 = Math.pow((Math.hypot(d11, d12) * (((((Math.cos(Math.toRadians(degrees) + 2.0d) + 3.8d) * 0.25d) * 3846.153846153846d) * viewingConditions.nc) * viewingConditions.ncb)) / (d14 + 0.305d), 0.9d) * Math.pow(1.64d - Math.pow(0.29d, viewingConditions.n), 0.73d);
                        double sqrt = Math.sqrt(pow4) * pow5;
                        double d20 = viewingConditions.flRoot * sqrt;
                        Math.sqrt((pow5 * d18) / d19);
                        Math.log1p(d20 * 0.0228d);
                        Math.cos(radians);
                        Math.sin(radians);
                        z2 = true;
                        ColorUtils.labF(MathUtils.matrixMultiply(new double[]{ColorUtils.linearized((intValue >> 16) & 255), ColorUtils.linearized((intValue >> 8) & 255), ColorUtils.linearized(i)}, ColorUtils.SRGB_TO_XYZ)[1] / 100.0d);
                        if (sqrt < 5.0d) {
                            return false;
                        }
                    } else {
                        z2 = true;
                    }
                    return z2;
                }
            }).collect(Collectors.toList());
            return list.isEmpty() ? List.of(-14979341) : list;
        }
        Map map = (Map) wallpaperColors.getAllColors().entrySet().stream().collect(Collectors.toMap(new ColorScheme$$ExternalSyntheticLambda1(0), new Function() { // from class: com.android.systemui.monet.ColorScheme$$ExternalSyntheticLambda7
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Double.valueOf(((Integer) ((Map.Entry) obj).getValue()).doubleValue() / sum);
            }
        }));
        final Map map2 = (Map) wallpaperColors.getAllColors().entrySet().stream().collect(Collectors.toMap(new ColorScheme$$ExternalSyntheticLambda1(0), new ColorScheme$$ExternalSyntheticLambda1(4)));
        final ArrayList arrayList = new ArrayList(Collections.nCopies(360, Double.valueOf(0.0d)));
        for (Map.Entry entry : map.entrySet()) {
            double doubleValue = ((Double) entry.getValue()).doubleValue();
            Hct hct = (Hct) map2.get(entry.getKey());
            int round = ((int) Math.round(hct.hue)) % 360;
            if (!z || hct.chroma > 5.0d) {
                arrayList.set(round, Double.valueOf(((Double) arrayList.get(round)).doubleValue() + doubleValue));
            }
        }
        final Map map3 = (Map) wallpaperColors.getAllColors().entrySet().stream().collect(Collectors.toMap(new ColorScheme$$ExternalSyntheticLambda1(0), new Function() { // from class: com.android.systemui.monet.ColorScheme$$ExternalSyntheticLambda9
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Map map4 = map2;
                List list2 = arrayList;
                int round2 = (int) Math.round(((Hct) map4.get(((Map.Entry) obj).getKey())).hue);
                int i = round2 - 15;
                double d = 0.0d;
                while (i <= round2 + 15) {
                    d += ((Double) list2.get(i < 0 ? (i % 360) + 360 : i >= 360 ? i % 360 : i)).doubleValue();
                    i++;
                }
                return Double.valueOf(d);
            }
        }));
        List list2 = (List) (z ? (Map) map2.entrySet().stream().filter(new Predicate() { // from class: com.android.systemui.monet.ColorScheme$$ExternalSyntheticLambda10
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                Map.Entry entry2 = (Map.Entry) obj;
                return ((Hct) entry2.getValue()).chroma >= 5.0d && ((Double) map3.get(entry2.getKey())).doubleValue() > 0.01d;
            }
        }).collect(Collectors.toMap(new ColorScheme$$ExternalSyntheticLambda1(0), new ColorScheme$$ExternalSyntheticLambda1(2))) : map2).entrySet().stream().map(new Function() { // from class: com.android.systemui.monet.ColorScheme$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                double d;
                double d2;
                Map map4 = map3;
                Map.Entry entry2 = (Map.Entry) obj;
                Integer num = (Integer) entry2.getKey();
                Hct hct2 = (Hct) entry2.getValue();
                double doubleValue2 = ((Double) map4.get(entry2.getKey())).doubleValue() * 70.0d;
                double d3 = hct2.chroma;
                if (d3 < 48.0d) {
                    d = d3 - 48.0d;
                    d2 = 0.1d;
                } else {
                    d = d3 - 48.0d;
                    d2 = 0.3d;
                }
                return new AbstractMap.SimpleEntry(num, Double.valueOf((d * d2) + doubleValue2));
            }
        }).sorted(Map.Entry.comparingByValue().reversed()).collect(Collectors.toList());
        ArrayList arrayList2 = new ArrayList();
        for (final int i = 90; i >= 15; i--) {
            arrayList2.clear();
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                Integer num = (Integer) ((Map.Entry) it.next()).getKey();
                final int intValue = num.intValue();
                if (!arrayList2.stream().anyMatch(new Predicate() { // from class: com.android.systemui.monet.ColorScheme$$ExternalSyntheticLambda4
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        Map map4 = map2;
                        int i2 = intValue;
                        int i3 = i;
                        double abs = Math.abs(((Hct) map4.get(Integer.valueOf(i2))).hue - ((Hct) map4.get((Integer) obj)).hue);
                        if (abs > 180.0d) {
                            abs = 360.0d - abs;
                        }
                        return abs < ((double) i3);
                    }
                })) {
                    arrayList2.add(num);
                    if (arrayList2.size() >= 4) {
                        break;
                    }
                }
            }
            if (!arrayList2.isEmpty()) {
                break;
            }
        }
        if (arrayList2.isEmpty()) {
            arrayList2.add(-14979341);
        }
        return arrayList2;
    }

    public static String humanReadable(String str, List list) {
        StringBuilder m = PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0.m(str, "\n");
        m.append((String) list.stream().map(new ColorScheme$$ExternalSyntheticLambda1(1)).collect(Collectors.joining("\n")));
        return m.toString();
    }

    public static String stringForColor(int i) {
        ViewingConditions viewingConditions = ViewingConditions.DEFAULT;
        int i2 = i & 255;
        double linearized = ColorUtils.linearized((16711680 & i) >> 16);
        double linearized2 = ColorUtils.linearized((65280 & i) >> 8);
        double linearized3 = ColorUtils.linearized(i2);
        double d = (0.18051042d * linearized3) + (0.35762064d * linearized2) + (0.41233895d * linearized);
        double d2 = (0.0722d * linearized3) + (0.7152d * linearized2) + (0.2126d * linearized);
        double d3 = (linearized3 * 0.95034478d) + (linearized2 * 0.11916382d) + (linearized * 0.01932141d);
        double[][] dArr = Cam16.XYZ_TO_CAM16RGB;
        double[] dArr2 = dArr[0];
        double d4 = (dArr2[2] * d3) + (dArr2[1] * d2) + (dArr2[0] * d);
        double[] dArr3 = dArr[1];
        double d5 = (dArr3[2] * d3) + (dArr3[1] * d2) + (dArr3[0] * d);
        double[] dArr4 = dArr[2];
        double d6 = (d3 * dArr4[2]) + (d2 * dArr4[1]) + (d * dArr4[0]);
        double[] dArr5 = viewingConditions.rgbD;
        double d7 = dArr5[0] * d4;
        double d8 = dArr5[1] * d5;
        double d9 = dArr5[2] * d6;
        double abs = Math.abs(d7);
        double d10 = viewingConditions.fl;
        double pow = Math.pow((abs * d10) / 100.0d, 0.42d);
        double pow2 = Math.pow((Math.abs(d8) * d10) / 100.0d, 0.42d);
        double pow3 = Math.pow((Math.abs(d9) * d10) / 100.0d, 0.42d);
        double signum = ((Math.signum(d7) * 400.0d) * pow) / (pow + 27.13d);
        double signum2 = ((Math.signum(d8) * 400.0d) * pow2) / (pow2 + 27.13d);
        double signum3 = ((Math.signum(d9) * 400.0d) * pow3) / (pow3 + 27.13d);
        double d11 = ((((-12.0d) * signum2) + (signum * 11.0d)) + signum3) / 11.0d;
        double d12 = ((signum + signum2) - (signum3 * 2.0d)) / 9.0d;
        double d13 = signum2 * 20.0d;
        double d14 = ((21.0d * signum3) + ((signum * 20.0d) + d13)) / 20.0d;
        double d15 = (((signum * 40.0d) + d13) + signum3) / 20.0d;
        double degrees = Math.toDegrees(Math.atan2(d12, d11));
        if (degrees < 0.0d) {
            degrees += 360.0d;
        } else if (degrees >= 360.0d) {
            degrees -= 360.0d;
        }
        double radians = Math.toRadians(degrees);
        double d16 = d15 * viewingConditions.nbb;
        double d17 = viewingConditions.aw;
        double d18 = viewingConditions.c;
        double pow4 = (Math.pow(d16 / d17, viewingConditions.z * d18) * 100.0d) / 100.0d;
        Math.sqrt(pow4);
        double d19 = d17 + 4.0d;
        double d20 = degrees;
        double pow5 = Math.pow((Math.hypot(d11, d12) * (((((Math.cos(Math.toRadians(degrees < 20.14d ? 360.0d + degrees : degrees) + 2.0d) + 3.8d) * 0.25d) * 3846.153846153846d) * viewingConditions.nc) * viewingConditions.ncb)) / (d14 + 0.305d), 0.9d) * Math.pow(1.64d - Math.pow(0.29d, viewingConditions.n), 0.73d);
        double sqrt = Math.sqrt(pow4) * pow5;
        double d21 = viewingConditions.flRoot * sqrt;
        Math.sqrt((pow5 * d18) / d19);
        Math.log1p(d21 * 0.0228d);
        Math.cos(radians);
        Math.sin(radians);
        double labF = (ColorUtils.labF(MathUtils.matrixMultiply(new double[]{ColorUtils.linearized((i >> 16) & 255), ColorUtils.linearized((i >> 8) & 255), ColorUtils.linearized(i2)}, ColorUtils.SRGB_TO_XYZ)[1] / 100.0d) * 116.0d) - 16.0d;
        return "H".concat(String.format("%4s", Long.valueOf(Math.round(d20)))) + "C".concat(String.format("%4s", Long.valueOf(Math.round(sqrt)))) + "T".concat(String.format("%4s", Long.valueOf(Math.round(labF)))) + " = #" + Integer.toHexString(i & 16777215).toUpperCase();
    }

    public final String toString() {
        return "ColorScheme {\n  seed color: " + stringForColor(this.mSeed) + "\n  style: " + this.mStyle + "\n  palettes: \n  " + humanReadable("PRIMARY", this.mAccent1.allShades) + "\n  " + humanReadable("SECONDARY", this.mAccent2.allShades) + "\n  " + humanReadable("TERTIARY", this.mAccent3.allShades) + "\n  " + humanReadable("NEUTRAL", this.mNeutral1.allShades) + "\n  " + humanReadable("NEUTRAL VARIANT", this.mNeutral2.allShades) + "\n}";
    }

    public ColorScheme(WallpaperColors wallpaperColors, boolean z, Style style) {
        this(((Integer) getSeedColors(wallpaperColors, style != Style.CONTENT).get(0)).intValue(), z, style, 0.0d);
    }
}
