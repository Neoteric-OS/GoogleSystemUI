package com.google.ux.material.libmonet.dynamiccolor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MaterialDynamicColors {
    public static DynamicColor highestSurface(DynamicScheme dynamicScheme) {
        return dynamicScheme.isDark ? new DynamicColor("surface_bright", new MaterialDynamicColors$$ExternalSyntheticLambda1(16), new MaterialDynamicColors$$ExternalSyntheticLambda1(17), true, null, null, null, null) : new DynamicColor("surface_dim", new MaterialDynamicColors$$ExternalSyntheticLambda0(20), new MaterialDynamicColors$$ExternalSyntheticLambda0(21), true, null, null, null, null);
    }

    public static boolean isMonochrome(DynamicScheme dynamicScheme) {
        return dynamicScheme.variant == Variant.MONOCHROME;
    }

    public static DynamicColor surfaceContainerLow() {
        return new DynamicColor("surface_container_low", new MaterialDynamicColors$$ExternalSyntheticLambda1(18), new MaterialDynamicColors$$ExternalSyntheticLambda1(19), true, null, null, null, null);
    }

    public final DynamicColor error() {
        return new DynamicColor("error", new MaterialDynamicColors$$ExternalSyntheticLambda0(16), new MaterialDynamicColors$$ExternalSyntheticLambda0(17), true, new MaterialDynamicColors$$ExternalSyntheticLambda4(this, 26), null, new ContrastCurve(3.0d, 4.5d, 7.0d, 7.0d), new MaterialDynamicColors$$ExternalSyntheticLambda4(this, 8));
    }

    public final DynamicColor errorContainer() {
        return new DynamicColor("error_container", new MaterialDynamicColors$$ExternalSyntheticLambda9(9), new MaterialDynamicColors$$ExternalSyntheticLambda9(10), true, new MaterialDynamicColors$$ExternalSyntheticLambda4(this, 26), null, new ContrastCurve(1.0d, 1.0d, 3.0d, 4.5d), new MaterialDynamicColors$$ExternalSyntheticLambda7(this, 10));
    }

    public final boolean isFidelity(DynamicScheme dynamicScheme) {
        Variant variant = dynamicScheme.variant;
        return variant == Variant.FIDELITY || variant == Variant.CONTENT;
    }

    public final DynamicColor outlineVariant() {
        return new DynamicColor("outline_variant", new MaterialDynamicColors$$ExternalSyntheticLambda5(6), new MaterialDynamicColors$$ExternalSyntheticLambda5(7), false, new MaterialDynamicColors$$ExternalSyntheticLambda4(this, 26), null, new ContrastCurve(1.0d, 1.0d, 3.0d, 4.5d), null);
    }

    public final DynamicColor primary() {
        return new DynamicColor("primary", new MaterialDynamicColors$$ExternalSyntheticLambda9(13), new MaterialDynamicColors$$ExternalSyntheticLambda9(14), true, new MaterialDynamicColors$$ExternalSyntheticLambda4(this, 26), null, new ContrastCurve(3.0d, 4.5d, 7.0d, 7.0d), new MaterialDynamicColors$$ExternalSyntheticLambda7(this, 12));
    }

    public final DynamicColor primaryContainer() {
        return new DynamicColor("primary_container", new MaterialDynamicColors$$ExternalSyntheticLambda0(28), new MaterialDynamicColors$$ExternalSyntheticLambda4(this, 13), true, new MaterialDynamicColors$$ExternalSyntheticLambda4(this, 26), null, new ContrastCurve(1.0d, 1.0d, 3.0d, 4.5d), new MaterialDynamicColors$$ExternalSyntheticLambda4(this, 14));
    }

    public final DynamicColor primaryFixed() {
        return new DynamicColor("primary_fixed", new MaterialDynamicColors$$ExternalSyntheticLambda5(10), new MaterialDynamicColors$$ExternalSyntheticLambda5(11), true, new MaterialDynamicColors$$ExternalSyntheticLambda4(this, 26), null, new ContrastCurve(1.0d, 1.0d, 3.0d, 4.5d), new MaterialDynamicColors$$ExternalSyntheticLambda7(this, 1));
    }

    public final DynamicColor primaryFixedDim() {
        return new DynamicColor("primary_fixed_dim", new MaterialDynamicColors$$ExternalSyntheticLambda0(9), new MaterialDynamicColors$$ExternalSyntheticLambda0(10), true, new MaterialDynamicColors$$ExternalSyntheticLambda4(this, 26), null, new ContrastCurve(1.0d, 1.0d, 3.0d, 4.5d), new MaterialDynamicColors$$ExternalSyntheticLambda4(this, 6));
    }

    public final DynamicColor secondary() {
        return new DynamicColor("secondary", new MaterialDynamicColors$$ExternalSyntheticLambda5(23), new MaterialDynamicColors$$ExternalSyntheticLambda5(24), true, new MaterialDynamicColors$$ExternalSyntheticLambda4(this, 26), null, new ContrastCurve(3.0d, 4.5d, 7.0d, 7.0d), new MaterialDynamicColors$$ExternalSyntheticLambda7(this, 4));
    }

    public final DynamicColor secondaryContainer() {
        return new DynamicColor("secondary_container", new MaterialDynamicColors$$ExternalSyntheticLambda5(1), new MaterialDynamicColors$$ExternalSyntheticLambda4(this, 25), true, new MaterialDynamicColors$$ExternalSyntheticLambda4(this, 26), null, new ContrastCurve(1.0d, 1.0d, 3.0d, 4.5d), new MaterialDynamicColors$$ExternalSyntheticLambda4(this, 27));
    }

    public final DynamicColor secondaryFixed() {
        return new DynamicColor("secondary_fixed", new MaterialDynamicColors$$ExternalSyntheticLambda0(3), new MaterialDynamicColors$$ExternalSyntheticLambda0(4), true, new MaterialDynamicColors$$ExternalSyntheticLambda4(this, 26), null, new ContrastCurve(1.0d, 1.0d, 3.0d, 4.5d), new MaterialDynamicColors$$ExternalSyntheticLambda4(this, 4));
    }

    public final DynamicColor secondaryFixedDim() {
        return new DynamicColor("secondary_fixed_dim", new MaterialDynamicColors$$ExternalSyntheticLambda1(2), new MaterialDynamicColors$$ExternalSyntheticLambda1(3), true, new MaterialDynamicColors$$ExternalSyntheticLambda4(this, 26), null, new ContrastCurve(1.0d, 1.0d, 3.0d, 4.5d), new MaterialDynamicColors$$ExternalSyntheticLambda4(this, 17));
    }

    public final DynamicColor tertiary() {
        return new DynamicColor("tertiary", new MaterialDynamicColors$$ExternalSyntheticLambda0(18), new MaterialDynamicColors$$ExternalSyntheticLambda0(19), true, new MaterialDynamicColors$$ExternalSyntheticLambda4(this, 26), null, new ContrastCurve(3.0d, 4.5d, 7.0d, 7.0d), new MaterialDynamicColors$$ExternalSyntheticLambda4(this, 9));
    }

    public final DynamicColor tertiaryContainer() {
        return new DynamicColor("tertiary_container", new MaterialDynamicColors$$ExternalSyntheticLambda0(22), new MaterialDynamicColors$$ExternalSyntheticLambda4(this, 10), true, new MaterialDynamicColors$$ExternalSyntheticLambda4(this, 26), null, new ContrastCurve(1.0d, 1.0d, 3.0d, 4.5d), new MaterialDynamicColors$$ExternalSyntheticLambda4(this, 11));
    }

    public final DynamicColor tertiaryFixed() {
        return new DynamicColor("tertiary_fixed", new MaterialDynamicColors$$ExternalSyntheticLambda9(11), new MaterialDynamicColors$$ExternalSyntheticLambda9(12), true, new MaterialDynamicColors$$ExternalSyntheticLambda4(this, 26), null, new ContrastCurve(1.0d, 1.0d, 3.0d, 4.5d), new MaterialDynamicColors$$ExternalSyntheticLambda7(this, 11));
    }

    public final DynamicColor tertiaryFixedDim() {
        return new DynamicColor("tertiary_fixed_dim", new MaterialDynamicColors$$ExternalSyntheticLambda5(12), new MaterialDynamicColors$$ExternalSyntheticLambda5(13), true, new MaterialDynamicColors$$ExternalSyntheticLambda4(this, 26), null, new ContrastCurve(1.0d, 1.0d, 3.0d, 4.5d), new MaterialDynamicColors$$ExternalSyntheticLambda7(this, 2));
    }
}
