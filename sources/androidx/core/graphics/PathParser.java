package androidx.core.graphics;

import android.graphics.Path;
import android.util.Log;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PathParser {
    public static float[] copyOfRange(float[] fArr, int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        int length = fArr.length;
        if (length < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int min = Math.min(i, length);
        float[] fArr2 = new float[i];
        System.arraycopy(fArr, 0, fArr2, 0, min);
        return fArr2;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0096 A[Catch: NumberFormatException -> 0x00aa, LOOP:3: B:25:0x0068->B:35:0x0096, LOOP_END, TryCatch #0 {NumberFormatException -> 0x00aa, blocks: (B:22:0x0054, B:25:0x0068, B:27:0x006e, B:31:0x007a, B:35:0x0096, B:39:0x009c, B:44:0x00b1, B:56:0x00b4), top: B:21:0x0054 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0095 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x009c A[Catch: NumberFormatException -> 0x00aa, TryCatch #0 {NumberFormatException -> 0x00aa, blocks: (B:22:0x0054, B:25:0x0068, B:27:0x006e, B:31:0x007a, B:35:0x0096, B:39:0x009c, B:44:0x00b1, B:56:0x00b4), top: B:21:0x0054 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00b1 A[Catch: NumberFormatException -> 0x00aa, TryCatch #0 {NumberFormatException -> 0x00aa, blocks: (B:22:0x0054, B:25:0x0068, B:27:0x006e, B:31:0x007a, B:35:0x0096, B:39:0x009c, B:44:0x00b1, B:56:0x00b4), top: B:21:0x0054 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d7 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static androidx.core.graphics.PathParser.PathDataNode[] createNodesFromPathData(java.lang.String r17) {
        /*
            Method dump skipped, instructions count: 268
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.PathParser.createNodesFromPathData(java.lang.String):androidx.core.graphics.PathParser$PathDataNode[]");
    }

    public static Path createPathFromPathData(String str) {
        Path path = new Path();
        try {
            nodesToPath(createNodesFromPathData(str), path);
            return path;
        } catch (RuntimeException e) {
            throw new RuntimeException("Error in parsing ".concat(str), e);
        }
    }

    public static PathDataNode[] deepCopyNodes(PathDataNode[] pathDataNodeArr) {
        PathDataNode[] pathDataNodeArr2 = new PathDataNode[pathDataNodeArr.length];
        for (int i = 0; i < pathDataNodeArr.length; i++) {
            pathDataNodeArr2[i] = new PathDataNode(pathDataNodeArr[i]);
        }
        return pathDataNodeArr2;
    }

    public static void nodesToPath(PathDataNode[] pathDataNodeArr, Path path) {
        int i;
        int i2;
        char c;
        int i3;
        int i4;
        PathDataNode pathDataNode;
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        float f11;
        float f12;
        PathDataNode[] pathDataNodeArr2 = pathDataNodeArr;
        int i5 = 6;
        float[] fArr = new float[6];
        int length = pathDataNodeArr2.length;
        int i6 = 0;
        char c2 = 'm';
        while (i6 < length) {
            PathDataNode pathDataNode2 = pathDataNodeArr2[i6];
            char c3 = pathDataNode2.mType;
            float f13 = fArr[0];
            float f14 = fArr[1];
            float f15 = fArr[2];
            float f16 = fArr[3];
            float f17 = fArr[4];
            float f18 = fArr[5];
            switch (c3) {
                case 'A':
                case 'a':
                    i = 7;
                    break;
                case 'C':
                case 'c':
                    i = i5;
                    break;
                case 'H':
                case 'V':
                case 'h':
                case 'v':
                    i = 1;
                    break;
                case 'Q':
                case 'S':
                case 'q':
                case 's':
                    i = 4;
                    break;
                case 'Z':
                case 'z':
                    path.close();
                    path.moveTo(f17, f18);
                    f13 = f17;
                    f15 = f13;
                    f14 = f18;
                    f16 = f14;
                default:
                    i = 2;
                    break;
            }
            float f19 = f17;
            float f20 = f18;
            float f21 = f13;
            float f22 = f14;
            int i7 = 0;
            while (true) {
                float[] fArr2 = pathDataNode2.mParams;
                if (i7 < fArr2.length) {
                    if (c3 != 'A') {
                        if (c3 != 'C') {
                            if (c3 == 'H') {
                                i2 = i7;
                                c = c3;
                                i3 = i6;
                                i4 = length;
                                pathDataNode = pathDataNode2;
                                path.lineTo(fArr2[i2], f22);
                                f21 = fArr2[i2];
                            } else if (c3 == 'Q') {
                                i2 = i7;
                                c = c3;
                                i3 = i6;
                                i4 = length;
                                pathDataNode = pathDataNode2;
                                float f23 = fArr2[i2];
                                int i8 = i2 + 1;
                                float f24 = fArr2[i8];
                                int i9 = i2 + 2;
                                int i10 = i2 + 3;
                                path.quadTo(f23, f24, fArr2[i9], fArr2[i10]);
                                f = fArr2[i2];
                                f2 = fArr2[i8];
                                f21 = fArr2[i9];
                                f22 = fArr2[i10];
                            } else if (c3 == 'V') {
                                i2 = i7;
                                c = c3;
                                i3 = i6;
                                i4 = length;
                                pathDataNode = pathDataNode2;
                                path.lineTo(f21, fArr2[i2]);
                                f22 = fArr2[i2];
                            } else if (c3 != 'a') {
                                if (c3 != 'c') {
                                    if (c3 != 'h') {
                                        if (c3 == 'q') {
                                            i2 = i7;
                                            float f25 = f22;
                                            float f26 = f21;
                                            int i11 = i2 + 1;
                                            int i12 = i2 + 2;
                                            int i13 = i2 + 3;
                                            path.rQuadTo(fArr2[i2], fArr2[i11], fArr2[i12], fArr2[i13]);
                                            float f27 = f26 + fArr2[i2];
                                            float f28 = fArr2[i11] + f25;
                                            float f29 = f26 + fArr2[i12];
                                            f22 = f25 + fArr2[i13];
                                            f16 = f28;
                                            f15 = f27;
                                            c = c3;
                                            i3 = i6;
                                            i4 = length;
                                            f21 = f29;
                                        } else if (c3 == 'v') {
                                            i2 = i7;
                                            path.rLineTo(0.0f, fArr2[i2]);
                                            f22 += fArr2[i2];
                                        } else if (c3 == 'L') {
                                            i2 = i7;
                                            int i14 = i2 + 1;
                                            path.lineTo(fArr2[i2], fArr2[i14]);
                                            f21 = fArr2[i2];
                                            f22 = fArr2[i14];
                                        } else if (c3 == 'M') {
                                            i2 = i7;
                                            f21 = fArr2[i2];
                                            f22 = fArr2[i2 + 1];
                                            if (i2 > 0) {
                                                path.lineTo(f21, f22);
                                            } else {
                                                path.moveTo(f21, f22);
                                                f20 = f22;
                                                f19 = f21;
                                            }
                                        } else if (c3 == 'S') {
                                            i2 = i7;
                                            float f30 = f22;
                                            float f31 = f21;
                                            if (c2 == 'c' || c2 == 's' || c2 == 'C' || c2 == 'S') {
                                                f7 = (f30 * 2.0f) - f16;
                                                f8 = (f31 * 2.0f) - f15;
                                            } else {
                                                f8 = f31;
                                                f7 = f30;
                                            }
                                            int i15 = i2 + 1;
                                            int i16 = i2 + 2;
                                            int i17 = i2 + 3;
                                            path.cubicTo(f8, f7, fArr2[i2], fArr2[i15], fArr2[i16], fArr2[i17]);
                                            float f32 = fArr2[i2];
                                            float f33 = fArr2[i15];
                                            f21 = fArr2[i16];
                                            f22 = fArr2[i17];
                                            f16 = f33;
                                            f15 = f32;
                                        } else if (c3 == 'T') {
                                            i2 = i7;
                                            float f34 = f22;
                                            float f35 = f21;
                                            if (c2 == 'q' || c2 == 't' || c2 == 'Q' || c2 == 'T') {
                                                f3 = (f35 * 2.0f) - f15;
                                                f4 = (f34 * 2.0f) - f16;
                                            } else {
                                                f3 = f35;
                                                f4 = f34;
                                            }
                                            int i18 = i2 + 1;
                                            path.quadTo(f3, f4, fArr2[i2], fArr2[i18]);
                                            f5 = fArr2[i2];
                                            f6 = fArr2[i18];
                                        } else if (c3 == 'l') {
                                            i2 = i7;
                                            int i19 = i2 + 1;
                                            path.rLineTo(fArr2[i2], fArr2[i19]);
                                            f21 += fArr2[i2];
                                            f22 += fArr2[i19];
                                        } else if (c3 == 'm') {
                                            i2 = i7;
                                            float f36 = fArr2[i2];
                                            f21 += f36;
                                            float f37 = fArr2[i2 + 1];
                                            f22 += f37;
                                            if (i2 > 0) {
                                                path.rLineTo(f36, f37);
                                            } else {
                                                path.rMoveTo(f36, f37);
                                                f20 = f22;
                                                f19 = f21;
                                            }
                                        } else if (c3 == 's') {
                                            if (c2 == 'c' || c2 == 's' || c2 == 'C' || c2 == 'S') {
                                                float f38 = f21 - f15;
                                                f9 = f22 - f16;
                                                f10 = f38;
                                            } else {
                                                f9 = 0.0f;
                                                f10 = 0.0f;
                                            }
                                            int i20 = i7 + 1;
                                            int i21 = i7 + 2;
                                            int i22 = i7 + 3;
                                            i2 = i7;
                                            float f39 = f22;
                                            float f40 = f21;
                                            path.rCubicTo(f10, f9, fArr2[i7], fArr2[i20], fArr2[i21], fArr2[i22]);
                                            f3 = f40 + fArr2[i2];
                                            f4 = f39 + fArr2[i20];
                                            f5 = f40 + fArr2[i21];
                                            f6 = fArr2[i22] + f39;
                                        } else if (c3 != 't') {
                                            i2 = i7;
                                        } else {
                                            if (c2 == 'q' || c2 == 't' || c2 == 'Q' || c2 == 'T') {
                                                f11 = f21 - f15;
                                                f12 = f22 - f16;
                                            } else {
                                                f12 = 0.0f;
                                                f11 = 0.0f;
                                            }
                                            int i23 = i7 + 1;
                                            path.rQuadTo(f11, f12, fArr2[i7], fArr2[i23]);
                                            float f41 = f11 + f21;
                                            float f42 = f12 + f22;
                                            f21 += fArr2[i7];
                                            f22 += fArr2[i23];
                                            f16 = f42;
                                            i2 = i7;
                                            c = c3;
                                            i3 = i6;
                                            i4 = length;
                                            f15 = f41;
                                        }
                                        pathDataNode = pathDataNode2;
                                    } else {
                                        i2 = i7;
                                        path.rLineTo(fArr2[i2], 0.0f);
                                        f21 += fArr2[i2];
                                    }
                                    c = c3;
                                    i3 = i6;
                                    i4 = length;
                                    pathDataNode = pathDataNode2;
                                } else {
                                    i2 = i7;
                                    float f43 = f22;
                                    float f44 = f21;
                                    int i24 = i2 + 2;
                                    int i25 = i2 + 3;
                                    int i26 = i2 + 4;
                                    int i27 = i2 + 5;
                                    path.rCubicTo(fArr2[i2], fArr2[i2 + 1], fArr2[i24], fArr2[i25], fArr2[i26], fArr2[i27]);
                                    f3 = f44 + fArr2[i24];
                                    f4 = f43 + fArr2[i25];
                                    f5 = f44 + fArr2[i26];
                                    f6 = fArr2[i27] + f43;
                                }
                                f16 = f4;
                                f15 = f3;
                                c = c3;
                                i3 = i6;
                                i4 = length;
                                f21 = f5;
                                f22 = f6;
                                pathDataNode = pathDataNode2;
                            } else {
                                i2 = i7;
                                float f45 = f22;
                                float f46 = f21;
                                int i28 = i2 + 5;
                                int i29 = i2 + 6;
                                c = c3;
                                i4 = length;
                                pathDataNode = pathDataNode2;
                                i3 = i6;
                                PathDataNode.drawArc(path, f46, f45, fArr2[i28] + f46, fArr2[i29] + f45, fArr2[i2], fArr2[i2 + 1], fArr2[i2 + 2], fArr2[i2 + 3] != 0.0f, fArr2[i2 + 4] != 0.0f);
                                f21 = f46 + fArr2[i28];
                                f22 = f45 + fArr2[i29];
                            }
                            i7 = i2 + i;
                            pathDataNode2 = pathDataNode;
                            length = i4;
                            c2 = c;
                            c3 = c2;
                            i6 = i3;
                        } else {
                            i2 = i7;
                            c = c3;
                            i3 = i6;
                            i4 = length;
                            pathDataNode = pathDataNode2;
                            int i30 = i2 + 2;
                            int i31 = i2 + 3;
                            int i32 = i2 + 4;
                            int i33 = i2 + 5;
                            path.cubicTo(fArr2[i2], fArr2[i2 + 1], fArr2[i30], fArr2[i31], fArr2[i32], fArr2[i33]);
                            f21 = fArr2[i32];
                            f22 = fArr2[i33];
                            f = fArr2[i30];
                            f2 = fArr2[i31];
                        }
                        f15 = f;
                        f16 = f2;
                        i7 = i2 + i;
                        pathDataNode2 = pathDataNode;
                        length = i4;
                        c2 = c;
                        c3 = c2;
                        i6 = i3;
                    } else {
                        i2 = i7;
                        c = c3;
                        i3 = i6;
                        i4 = length;
                        pathDataNode = pathDataNode2;
                        int i34 = i2 + 5;
                        int i35 = i2 + 6;
                        PathDataNode.drawArc(path, f21, f22, fArr2[i34], fArr2[i35], fArr2[i2], fArr2[i2 + 1], fArr2[i2 + 2], fArr2[i2 + 3] != 0.0f, fArr2[i2 + 4] != 0.0f);
                        f21 = fArr2[i34];
                        f22 = fArr2[i35];
                    }
                    f16 = f22;
                    f15 = f21;
                    i7 = i2 + i;
                    pathDataNode2 = pathDataNode;
                    length = i4;
                    c2 = c;
                    c3 = c2;
                    i6 = i3;
                }
            }
            fArr[0] = f21;
            fArr[1] = f22;
            fArr[2] = f15;
            fArr[3] = f16;
            fArr[4] = f19;
            fArr[5] = f20;
            c2 = pathDataNode2.mType;
            i6++;
            pathDataNodeArr2 = pathDataNodeArr;
            length = length;
            i5 = 6;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PathDataNode {
        public final float[] mParams;
        public char mType;

        public PathDataNode(char c, float[] fArr) {
            this.mType = c;
            this.mParams = fArr;
        }

        public static void drawArc(Path path, float f, float f2, float f3, float f4, float f5, float f6, float f7, boolean z, boolean z2) {
            double d;
            double d2;
            double radians = Math.toRadians(f7);
            double cos = Math.cos(radians);
            double sin = Math.sin(radians);
            double d3 = f;
            double d4 = f2;
            double d5 = (d4 * sin) + (d3 * cos);
            double d6 = d3;
            double d7 = f5;
            double d8 = d5 / d7;
            double d9 = f6;
            double d10 = ((d4 * cos) + ((-f) * sin)) / d9;
            double d11 = d4;
            double d12 = f4;
            double d13 = ((d12 * sin) + (f3 * cos)) / d7;
            double d14 = ((d12 * cos) + ((-f3) * sin)) / d9;
            double d15 = d8 - d13;
            double d16 = d10 - d14;
            double d17 = (d8 + d13) / 2.0d;
            double d18 = (d10 + d14) / 2.0d;
            double d19 = (d16 * d16) + (d15 * d15);
            if (d19 == 0.0d) {
                Log.w("PathParser", " Points are coincident");
                return;
            }
            double d20 = (1.0d / d19) - 0.25d;
            if (d20 < 0.0d) {
                Log.w("PathParser", "Points are too far apart " + d19);
                float sqrt = (float) (Math.sqrt(d19) / 1.99999d);
                drawArc(path, f, f2, f3, f4, f5 * sqrt, f6 * sqrt, f7, z, z2);
                return;
            }
            double sqrt2 = Math.sqrt(d20);
            double d21 = d15 * sqrt2;
            double d22 = sqrt2 * d16;
            if (z == z2) {
                d = d17 - d22;
                d2 = d18 + d21;
            } else {
                d = d17 + d22;
                d2 = d18 - d21;
            }
            double atan2 = Math.atan2(d10 - d2, d8 - d);
            double atan22 = Math.atan2(d14 - d2, d13 - d) - atan2;
            if (z2 != (atan22 >= 0.0d)) {
                atan22 = atan22 > 0.0d ? atan22 - 6.283185307179586d : atan22 + 6.283185307179586d;
            }
            double d23 = d * d7;
            double d24 = d2 * d9;
            double d25 = (d23 * cos) - (d24 * sin);
            double d26 = (d24 * cos) + (d23 * sin);
            int ceil = (int) Math.ceil(Math.abs((atan22 * 4.0d) / 3.141592653589793d));
            double cos2 = Math.cos(radians);
            double sin2 = Math.sin(radians);
            double cos3 = Math.cos(atan2);
            double sin3 = Math.sin(atan2);
            double d27 = -d7;
            double d28 = d27 * cos2;
            double d29 = d9 * sin2;
            double d30 = (d28 * sin3) - (d29 * cos3);
            double d31 = d27 * sin2;
            double d32 = d9 * cos2;
            double d33 = (cos3 * d32) + (sin3 * d31);
            double d34 = d32;
            double d35 = atan22 / ceil;
            int i = 0;
            while (i < ceil) {
                double d36 = atan2 + d35;
                double sin4 = Math.sin(d36);
                double cos4 = Math.cos(d36);
                double d37 = d35;
                double d38 = (((d7 * cos2) * cos4) + d25) - (d29 * sin4);
                double d39 = d34;
                double d40 = d25;
                double d41 = (d39 * sin4) + (d7 * sin2 * cos4) + d26;
                double d42 = (d28 * sin4) - (d29 * cos4);
                double d43 = (cos4 * d39) + (sin4 * d31);
                double d44 = d36 - atan2;
                double tan = Math.tan(d44 / 2.0d);
                double sqrt3 = ((Math.sqrt(((tan * 3.0d) * tan) + 4.0d) - 1.0d) * Math.sin(d44)) / 3.0d;
                path.rLineTo(0.0f, 0.0f);
                path.cubicTo((float) ((d30 * sqrt3) + d6), (float) ((d33 * sqrt3) + d11), (float) (d38 - (sqrt3 * d42)), (float) (d41 - (sqrt3 * d43)), (float) d38, (float) d41);
                i++;
                atan2 = d36;
                d31 = d31;
                cos2 = cos2;
                ceil = ceil;
                d33 = d43;
                d7 = d7;
                d30 = d42;
                d6 = d38;
                d11 = d41;
                d25 = d40;
                d35 = d37;
                d34 = d39;
            }
        }

        public PathDataNode(PathDataNode pathDataNode) {
            this.mType = pathDataNode.mType;
            float[] fArr = pathDataNode.mParams;
            this.mParams = PathParser.copyOfRange(fArr, fArr.length);
        }
    }
}
