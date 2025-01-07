package com.android.systemui.assist.ui;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.Log;
import android.util.Pair;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PerimeterPathGuide {
    public final int mBottomCornerRadiusPx;
    public final PathSpecCornerPathRenderer mCornerPathRenderer;
    public final int mDeviceHeightPx;
    public final int mDeviceWidthPx;
    public final int mEdgeInset;
    public final int mTopCornerRadiusPx;
    public final PathMeasure mScratchPathMeasure = new PathMeasure(new Path(), false);
    public int mRotation = 0;
    public final RegionAttributes[] mRegions = new RegionAttributes[8];

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Region {
        public static final /* synthetic */ Region[] $VALUES;
        public static final Region BOTTOM;

        static {
            Region region = new Region("BOTTOM", 0);
            BOTTOM = region;
            $VALUES = new Region[]{region, new Region("BOTTOM_RIGHT", 1), new Region("RIGHT", 2), new Region("TOP_RIGHT", 3), new Region("TOP", 4), new Region("TOP_LEFT", 5), new Region("LEFT", 6), new Region("BOTTOM_LEFT", 7)};
        }

        public static Region valueOf(String str) {
            return (Region) Enum.valueOf(Region.class, str);
        }

        public static Region[] values() {
            return (Region[]) $VALUES.clone();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RegionAttributes {
        public float absoluteLength;
        public float endCoordinate;
        public float normalizedLength;
        public Path path;
    }

    public PerimeterPathGuide(Context context, PathSpecCornerPathRenderer pathSpecCornerPathRenderer, int i, int i2, int i3) {
        int i4 = 0;
        this.mCornerPathRenderer = pathSpecCornerPathRenderer;
        this.mDeviceWidthPx = i2;
        this.mDeviceHeightPx = i3;
        this.mTopCornerRadiusPx = DisplayUtils.getCornerRadiusTop(context);
        this.mBottomCornerRadiusPx = DisplayUtils.getCornerRadiusBottom(context);
        this.mEdgeInset = i;
        while (true) {
            RegionAttributes[] regionAttributesArr = this.mRegions;
            if (i4 >= regionAttributesArr.length) {
                computeRegions();
                return;
            } else {
                regionAttributesArr[i4] = new RegionAttributes();
                i4++;
            }
        }
    }

    public final void computeRegions() {
        int i = this.mRotation;
        int i2 = i != 1 ? i != 2 ? i != 3 ? 0 : -270 : -180 : -90;
        Matrix matrix = new Matrix();
        int i3 = this.mDeviceWidthPx;
        int i4 = this.mDeviceHeightPx;
        matrix.postRotate(i2, i3 / 2, i4 / 2);
        int i5 = this.mRotation;
        if (i5 == 1 || i5 == 3) {
            matrix.postTranslate((i4 - i3) / 2, (i3 - i4) / 2);
        } else {
            i4 = i3;
            i3 = i4;
        }
        CornerPathRenderer$Corner rotatedCorner = getRotatedCorner(CornerPathRenderer$Corner.BOTTOM_LEFT);
        CornerPathRenderer$Corner rotatedCorner2 = getRotatedCorner(CornerPathRenderer$Corner.BOTTOM_RIGHT);
        CornerPathRenderer$Corner rotatedCorner3 = getRotatedCorner(CornerPathRenderer$Corner.TOP_LEFT);
        CornerPathRenderer$Corner rotatedCorner4 = getRotatedCorner(CornerPathRenderer$Corner.TOP_RIGHT);
        RegionAttributes[] regionAttributesArr = this.mRegions;
        RegionAttributes regionAttributes = regionAttributesArr[7];
        int i6 = this.mEdgeInset;
        PathSpecCornerPathRenderer pathSpecCornerPathRenderer = this.mCornerPathRenderer;
        regionAttributes.path = pathSpecCornerPathRenderer.getInsetPath(rotatedCorner, i6);
        regionAttributesArr[1].path = pathSpecCornerPathRenderer.getInsetPath(rotatedCorner2, i6);
        regionAttributesArr[3].path = pathSpecCornerPathRenderer.getInsetPath(rotatedCorner4, i6);
        regionAttributesArr[5].path = pathSpecCornerPathRenderer.getInsetPath(rotatedCorner3, i6);
        regionAttributesArr[7].path.transform(matrix);
        regionAttributesArr[1].path.transform(matrix);
        regionAttributesArr[3].path.transform(matrix);
        regionAttributesArr[5].path.transform(matrix);
        Path path = new Path();
        path.moveTo(getPhysicalCornerRadius(rotatedCorner), i3 - i6);
        path.lineTo(i4 - getPhysicalCornerRadius(rotatedCorner2), i3 - i6);
        regionAttributesArr[0].path = path;
        Path path2 = new Path();
        path2.moveTo(i4 - getPhysicalCornerRadius(rotatedCorner4), i6);
        path2.lineTo(getPhysicalCornerRadius(rotatedCorner3), i6);
        regionAttributesArr[4].path = path2;
        Path path3 = new Path();
        path3.moveTo(i4 - i6, i3 - getPhysicalCornerRadius(rotatedCorner2));
        path3.lineTo(i4 - i6, getPhysicalCornerRadius(rotatedCorner4));
        regionAttributesArr[2].path = path3;
        Path path4 = new Path();
        path4.moveTo(i6, getPhysicalCornerRadius(rotatedCorner3));
        path4.lineTo(i6, i3 - getPhysicalCornerRadius(rotatedCorner));
        regionAttributesArr[6].path = path4;
        PathMeasure pathMeasure = new PathMeasure();
        float f = 0.0f;
        float f2 = 0.0f;
        for (int i7 = 0; i7 < regionAttributesArr.length; i7++) {
            pathMeasure.setPath(regionAttributesArr[i7].path, false);
            regionAttributesArr[i7].absoluteLength = pathMeasure.getLength();
            f2 += regionAttributesArr[i7].absoluteLength;
        }
        for (RegionAttributes regionAttributes2 : regionAttributesArr) {
            float f3 = regionAttributes2.absoluteLength;
            regionAttributes2.normalizedLength = f3 / f2;
            f += f3;
            regionAttributes2.endCoordinate = f / f2;
        }
        regionAttributesArr[regionAttributesArr.length - 1].endCoordinate = 1.0f;
    }

    public final int getPhysicalCornerRadius(CornerPathRenderer$Corner cornerPathRenderer$Corner) {
        return (cornerPathRenderer$Corner == CornerPathRenderer$Corner.BOTTOM_LEFT || cornerPathRenderer$Corner == CornerPathRenderer$Corner.BOTTOM_RIGHT) ? this.mBottomCornerRadiusPx : this.mTopCornerRadiusPx;
    }

    public final CornerPathRenderer$Corner getRotatedCorner(CornerPathRenderer$Corner cornerPathRenderer$Corner) {
        int ordinal = cornerPathRenderer$Corner.ordinal();
        int i = this.mRotation;
        if (i == 1) {
            ordinal += 3;
        } else if (i == 2) {
            ordinal += 2;
        } else if (i == 3) {
            ordinal++;
        }
        return CornerPathRenderer$Corner.values()[ordinal % 4];
    }

    public final Pair placePoint(float f) {
        Region region;
        RegionAttributes[] regionAttributesArr;
        Region region2;
        if (0.0f > f || f > 1.0f) {
            f = ((f % 1.0f) + 1.0f) % 1.0f;
        }
        float f2 = (f < 0.0f || f > 1.0f) ? ((f % 1.0f) + 1.0f) % 1.0f : f;
        Region[] values = Region.values();
        int length = values.length;
        int i = 0;
        while (true) {
            region = Region.BOTTOM;
            regionAttributesArr = this.mRegions;
            if (i >= length) {
                Log.e("PerimeterPathGuide", "Fell out of getRegionForPoint");
                region2 = region;
                break;
            }
            region2 = values[i];
            if (f2 <= regionAttributesArr[region2.ordinal()].endCoordinate) {
                break;
            }
            i++;
        }
        return region2.equals(region) ? Pair.create(region2, Float.valueOf(f / regionAttributesArr[region2.ordinal()].normalizedLength)) : Pair.create(region2, Float.valueOf((f - regionAttributesArr[region2.ordinal() - 1].endCoordinate) / regionAttributesArr[region2.ordinal()].normalizedLength));
    }

    public final void strokeRegion(Path path, Region region, float f, float f2) {
        if (f == f2) {
            return;
        }
        this.mScratchPathMeasure.setPath(this.mRegions[region.ordinal()].path, false);
        PathMeasure pathMeasure = this.mScratchPathMeasure;
        pathMeasure.getSegment(pathMeasure.getLength() * f, this.mScratchPathMeasure.getLength() * f2, path, true);
    }

    public final void strokeSegmentInternal(Path path, float f, float f2) {
        Pair placePoint = placePoint(f);
        Pair placePoint2 = placePoint(f2);
        if (((Region) placePoint.first).equals(placePoint2.first)) {
            strokeRegion(path, (Region) placePoint.first, ((Float) placePoint.second).floatValue(), ((Float) placePoint2.second).floatValue());
            return;
        }
        strokeRegion(path, (Region) placePoint.first, ((Float) placePoint.second).floatValue(), 1.0f);
        boolean z = false;
        for (Region region : Region.values()) {
            if (region.equals(placePoint.first)) {
                z = true;
            } else if (!z) {
                continue;
            } else {
                if (region.equals(placePoint2.first)) {
                    strokeRegion(path, region, 0.0f, ((Float) placePoint2.second).floatValue());
                    return;
                }
                strokeRegion(path, region, 0.0f, 1.0f);
            }
        }
    }
}
