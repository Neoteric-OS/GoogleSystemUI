package androidx.constraintlayout.core.motion.utils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ArcCurveFit extends CurveFit {
    public final Arc[] mArcs;
    public final double[] mTime;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Arc {
        public static final double[] sOurPercent = new double[91];
        public double mArcDistance;
        public double mArcVelocity;
        public double mEllipseA;
        public double mEllipseB;
        public double mEllipseCenterX;
        public double mEllipseCenterY;
        public boolean mLinear;
        public double[] mLut;
        public double mOneOverDeltaTime;
        public double mTime1;
        public double mTime2;
        public double mTmpCosAngle;
        public double mTmpSinAngle;
        public boolean mVertical;
        public double mX1;
        public double mX2;
        public double mY1;
        public double mY2;

        public final double getDX() {
            double d = this.mEllipseA * this.mTmpCosAngle;
            double hypot = this.mArcVelocity / Math.hypot(d, (-this.mEllipseB) * this.mTmpSinAngle);
            if (this.mVertical) {
                d = -d;
            }
            return d * hypot;
        }

        public final double getDY() {
            double d = this.mEllipseA * this.mTmpCosAngle;
            double d2 = (-this.mEllipseB) * this.mTmpSinAngle;
            double hypot = this.mArcVelocity / Math.hypot(d, d2);
            return this.mVertical ? (-d2) * hypot : d2 * hypot;
        }

        public final double getLinearX(double d) {
            double d2 = (d - this.mTime1) * this.mOneOverDeltaTime;
            double d3 = this.mX2;
            double d4 = this.mX1;
            return ((d3 - d4) * d2) + d4;
        }

        public final double getLinearY(double d) {
            double d2 = (d - this.mTime1) * this.mOneOverDeltaTime;
            double d3 = this.mY2;
            double d4 = this.mY1;
            return ((d3 - d4) * d2) + d4;
        }

        public final double getX() {
            return (this.mEllipseA * this.mTmpSinAngle) + this.mEllipseCenterX;
        }

        public final double getY() {
            return (this.mEllipseB * this.mTmpCosAngle) + this.mEllipseCenterY;
        }

        public final void setPoint(double d) {
            double d2 = (this.mVertical ? this.mTime2 - d : d - this.mTime1) * this.mOneOverDeltaTime;
            double d3 = 0.0d;
            if (d2 > 0.0d) {
                d3 = 1.0d;
                if (d2 < 1.0d) {
                    double[] dArr = this.mLut;
                    double length = d2 * (dArr.length - 1);
                    int i = (int) length;
                    double d4 = dArr[i];
                    d3 = ((dArr[i + 1] - d4) * (length - i)) + d4;
                }
            }
            double d5 = d3 * 1.5707963267948966d;
            this.mTmpSinAngle = Math.sin(d5);
            this.mTmpCosAngle = Math.cos(d5);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:93:0x0030, code lost:
    
        if (r5 == r3) goto L19;
     */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ArcCurveFit(int[] r33, double[] r34, double[][] r35) {
        /*
            Method dump skipped, instructions count: 421
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.motion.utils.ArcCurveFit.<init>(int[], double[], double[][]):void");
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public final void getPos(double d, double[] dArr) {
        Arc[] arcArr = this.mArcs;
        Arc arc = arcArr[0];
        double d2 = arc.mTime1;
        if (d < d2) {
            double d3 = d - d2;
            if (arc.mLinear) {
                double linearX = arc.getLinearX(d2);
                Arc arc2 = arcArr[0];
                dArr[0] = (arc2.mEllipseCenterX * d3) + linearX;
                dArr[1] = (d3 * arcArr[0].mEllipseCenterY) + arc2.getLinearY(d2);
                return;
            }
            arc.setPoint(d2);
            dArr[0] = (arcArr[0].getDX() * d3) + arcArr[0].getX();
            dArr[1] = (arcArr[0].getDY() * d3) + arcArr[0].getY();
            return;
        }
        if (d <= arcArr[arcArr.length - 1].mTime2) {
            for (int i = 0; i < arcArr.length; i++) {
                Arc arc3 = arcArr[i];
                if (d <= arc3.mTime2) {
                    if (arc3.mLinear) {
                        dArr[0] = arc3.getLinearX(d);
                        dArr[1] = arcArr[i].getLinearY(d);
                        return;
                    } else {
                        arc3.setPoint(d);
                        dArr[0] = arcArr[i].getX();
                        dArr[1] = arcArr[i].getY();
                        return;
                    }
                }
            }
            return;
        }
        double d4 = arcArr[arcArr.length - 1].mTime2;
        double d5 = d - d4;
        int length = arcArr.length - 1;
        Arc arc4 = arcArr[length];
        if (arc4.mLinear) {
            double linearX2 = arc4.getLinearX(d4);
            Arc arc5 = arcArr[length];
            dArr[0] = (arc5.mEllipseCenterX * d5) + linearX2;
            dArr[1] = (d5 * arcArr[length].mEllipseCenterY) + arc5.getLinearY(d4);
            return;
        }
        arc4.setPoint(d);
        dArr[0] = (arcArr[length].getDX() * d5) + arcArr[length].getX();
        dArr[1] = (arcArr[length].getDY() * d5) + arcArr[length].getY();
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public final void getSlope(double d, double[] dArr) {
        Arc[] arcArr = this.mArcs;
        double d2 = arcArr[0].mTime1;
        if (d < d2) {
            d = d2;
        } else if (d > arcArr[arcArr.length - 1].mTime2) {
            d = arcArr[arcArr.length - 1].mTime2;
        }
        for (int i = 0; i < arcArr.length; i++) {
            Arc arc = arcArr[i];
            if (d <= arc.mTime2) {
                if (arc.mLinear) {
                    dArr[0] = arc.mEllipseCenterX;
                    dArr[1] = arc.mEllipseCenterY;
                    return;
                } else {
                    arc.setPoint(d);
                    dArr[0] = arcArr[i].getDX();
                    dArr[1] = arcArr[i].getDY();
                    return;
                }
            }
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public final double[] getTimePoints() {
        return this.mTime;
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public final void getPos(double d, float[] fArr) {
        Arc[] arcArr = this.mArcs;
        Arc arc = arcArr[0];
        double d2 = arc.mTime1;
        if (d < d2) {
            double d3 = d - d2;
            if (arc.mLinear) {
                double linearX = arc.getLinearX(d2);
                Arc arc2 = arcArr[0];
                fArr[0] = (float) ((arc2.mEllipseCenterX * d3) + linearX);
                fArr[1] = (float) ((d3 * arcArr[0].mEllipseCenterY) + arc2.getLinearY(d2));
                return;
            }
            arc.setPoint(d2);
            fArr[0] = (float) ((arcArr[0].getDX() * d3) + arcArr[0].getX());
            fArr[1] = (float) ((arcArr[0].getDY() * d3) + arcArr[0].getY());
            return;
        }
        if (d > arcArr[arcArr.length - 1].mTime2) {
            double d4 = arcArr[arcArr.length - 1].mTime2;
            double d5 = d - d4;
            int length = arcArr.length - 1;
            Arc arc3 = arcArr[length];
            if (arc3.mLinear) {
                double linearX2 = arc3.getLinearX(d4);
                Arc arc4 = arcArr[length];
                fArr[0] = (float) ((arc4.mEllipseCenterX * d5) + linearX2);
                fArr[1] = (float) ((d5 * arcArr[length].mEllipseCenterY) + arc4.getLinearY(d4));
                return;
            }
            arc3.setPoint(d);
            fArr[0] = (float) arcArr[length].getX();
            fArr[1] = (float) arcArr[length].getY();
            return;
        }
        for (int i = 0; i < arcArr.length; i++) {
            Arc arc5 = arcArr[i];
            if (d <= arc5.mTime2) {
                if (arc5.mLinear) {
                    fArr[0] = (float) arc5.getLinearX(d);
                    fArr[1] = (float) arcArr[i].getLinearY(d);
                    return;
                } else {
                    arc5.setPoint(d);
                    fArr[0] = (float) arcArr[i].getX();
                    fArr[1] = (float) arcArr[i].getY();
                    return;
                }
            }
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public final double getPos(double d) {
        Arc[] arcArr = this.mArcs;
        Arc arc = arcArr[0];
        double d2 = arc.mTime1;
        if (d < d2) {
            double d3 = d - d2;
            if (arc.mLinear) {
                return (d3 * arcArr[0].mEllipseCenterX) + arc.getLinearX(d2);
            }
            arc.setPoint(d2);
            return (arcArr[0].getDX() * d3) + arcArr[0].getX();
        }
        if (d > arcArr[arcArr.length - 1].mTime2) {
            double d4 = arcArr[arcArr.length - 1].mTime2;
            double d5 = d - d4;
            int length = arcArr.length - 1;
            return (d5 * arcArr[length].mEllipseCenterX) + arcArr[length].getLinearX(d4);
        }
        for (int i = 0; i < arcArr.length; i++) {
            Arc arc2 = arcArr[i];
            if (d <= arc2.mTime2) {
                if (arc2.mLinear) {
                    return arc2.getLinearX(d);
                }
                arc2.setPoint(d);
                return arcArr[i].getX();
            }
        }
        return Double.NaN;
    }
}
