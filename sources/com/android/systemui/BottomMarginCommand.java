package com.android.systemui;

import com.android.systemui.statusbar.commandline.ParseableCommand;
import com.android.systemui.statusbar.commandline.SingleArgParamOptional;
import com.android.systemui.statusbar.commandline.Type;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BottomMarginCommand extends ParseableCommand {
    public static final /* synthetic */ KProperty[] $$delegatedProperties;
    public static final Companion Companion;
    public static final Set ROTATION_DEGREES_OPTIONS;
    public static final Map ROTATION_DEGREES_TO_VALUE_MAPPING;
    public final SingleArgParamOptional marginBottomDp$delegate;
    public final SingleArgParamOptional rotationDegrees$delegate;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
    }

    static {
        PropertyReference1Impl propertyReference1Impl = new PropertyReference1Impl(BottomMarginCommand.class, "rotationDegrees", "getRotationDegrees()Ljava/lang/Integer;", 0);
        Reflection.factory.getClass();
        $$delegatedProperties = new KProperty[]{propertyReference1Impl, new PropertyReference1Impl(BottomMarginCommand.class, "marginBottomDp", "getMarginBottomDp()Ljava/lang/Float;", 0)};
        Companion = new Companion();
        Map mapOf = MapsKt.mapOf(new Pair(0, 0), new Pair(90, 1), new Pair(180, 2), new Pair(270, 3));
        ROTATION_DEGREES_TO_VALUE_MAPPING = mapOf;
        ROTATION_DEGREES_OPTIONS = mapOf.keySet();
    }

    public BottomMarginCommand() {
        super("bottom-margin");
        this.rotationDegrees$delegate = param("rotation", "r", "For which rotation the margin should be set. One of 0, 90, 180, 270", Type.Int);
        this.marginBottomDp$delegate = param("margin", "m", "Margin amount, in dp. Can be a fractional value, such as 10.5", Type.Float);
    }

    @Override // com.android.systemui.statusbar.commandline.ParseableCommand
    public final void execute(PrintWriter printWriter) {
    }
}
