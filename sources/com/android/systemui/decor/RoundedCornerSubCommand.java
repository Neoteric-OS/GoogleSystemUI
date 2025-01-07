package com.android.systemui.decor;

import android.graphics.Path;
import android.util.PathParser;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.commandline.ArgParseError;
import com.android.systemui.statusbar.commandline.ParseableCommand;
import com.android.systemui.statusbar.commandline.SingleArgParam;
import com.android.systemui.statusbar.commandline.SingleArgParamOptional;
import com.android.systemui.statusbar.commandline.Type;
import com.android.systemui.statusbar.commandline.ValueParser;
import com.android.systemui.statusbar.commandline.ValueParserKt$parseInt$1;
import java.io.PrintWriter;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RoundedCornerSubCommand extends ParseableCommand {
    public static final /* synthetic */ KProperty[] $$delegatedProperties;
    public final SingleArgParam height$delegate;
    public final SingleArgParam pathData$delegate;
    public final SingleArgParamOptional viewportHeight$delegate;
    public final SingleArgParamOptional viewportWidth$delegate;
    public final SingleArgParam width$delegate;

    static {
        PropertyReference1Impl propertyReference1Impl = new PropertyReference1Impl(RoundedCornerSubCommand.class, "height", "getHeight()I", 0);
        Reflection.factory.getClass();
        $$delegatedProperties = new KProperty[]{propertyReference1Impl, new PropertyReference1Impl(RoundedCornerSubCommand.class, "width", "getWidth()I", 0), new PropertyReference1Impl(RoundedCornerSubCommand.class, "pathData", "getPathData()Landroid/graphics/Path;", 0), new PropertyReference1Impl(RoundedCornerSubCommand.class, "viewportHeight", "getViewportHeight()Ljava/lang/Float;", 0), new PropertyReference1Impl(RoundedCornerSubCommand.class, "viewportWidth", "getViewportWidth()Ljava/lang/Float;", 0)};
    }

    public RoundedCornerSubCommand(String str) {
        super(str);
        ValueParserKt$parseInt$1 valueParserKt$parseInt$1 = Type.Int;
        this.height$delegate = required(param("height", null, "The height of a corner, in pixels.", valueParserKt$parseInt$1));
        this.width$delegate = required(param("width", null, "The width of the corner, in pixels. Likely should be equal to the height.", valueParserKt$parseInt$1));
        final ValueParserKt$parseInt$1 valueParserKt$parseInt$12 = Type.String;
        this.pathData$delegate = required(param("path-data", "d", "PathParser-compatible path string to be rendered as the corner drawable. This path should be a closed arc oriented as the top-left corner of the device", new ValueParser() { // from class: com.android.systemui.decor.RoundedCornerSubCommand$special$$inlined$map$1
            @Override // com.android.systemui.statusbar.commandline.ValueParser
            /* renamed from: parseValue-IoAF18A, reason: not valid java name */
            public final Object mo801parseValueIoAF18A(String str2) {
                Path path;
                ValueParserKt$parseInt$1.this.getClass();
                ResultKt.throwOnFailure(str2);
                try {
                    path = PathParser.createPathFromPathData(str2);
                } catch (Exception unused) {
                    path = null;
                }
                return path != null ? path : new Result.Failure(new ArgParseError("Failed to transform value ".concat(str2)));
            }
        }));
        ValueParserKt$parseInt$1 valueParserKt$parseInt$13 = Type.Float;
        this.viewportHeight$delegate = param("viewport-height", null, "The height of the viewport for the given path string. If null, the corner height will be used.", valueParserKt$parseInt$13);
        this.viewportWidth$delegate = param("viewport-width", null, "The width of the viewport for the given path string. If null, the corner width will be used.", valueParserKt$parseInt$13);
    }

    public final DebugRoundedCornerModel toRoundedCornerDebugModel() {
        float f;
        float f2;
        KProperty[] kPropertyArr = $$delegatedProperties;
        Path path = (Path) this.pathData$delegate.getValue(this, kPropertyArr[2]);
        KProperty kProperty = kPropertyArr[1];
        int intValue = ((Number) this.width$delegate.getValue(this, kProperty)).intValue();
        KProperty kProperty2 = kPropertyArr[0];
        int intValue2 = ((Number) this.height$delegate.getValue(this, kProperty2)).intValue();
        Float f3 = (Float) this.viewportWidth$delegate.getValue(this, kPropertyArr[4]);
        if (f3 != null) {
            f = ((Number) r4.getValue(this, kPropertyArr[1])).intValue() / f3.floatValue();
        } else {
            f = 1.0f;
        }
        Float f4 = (Float) this.viewportHeight$delegate.getValue(this, kPropertyArr[3]);
        if (f4 != null) {
            f2 = ((Number) r8.getValue(this, kPropertyArr[0])).intValue() / f4.floatValue();
        } else {
            f2 = 1.0f;
        }
        return new DebugRoundedCornerModel(path, intValue, intValue2, f, f2);
    }

    public final String toString() {
        KProperty[] kPropertyArr = $$delegatedProperties;
        int intValue = ((Number) this.height$delegate.getValue(this, kPropertyArr[0])).intValue();
        int intValue2 = ((Number) this.width$delegate.getValue(this, kPropertyArr[1])).intValue();
        Path path = (Path) this.pathData$delegate.getValue(this, kPropertyArr[2]);
        Float f = (Float) this.viewportHeight$delegate.getValue(this, kPropertyArr[3]);
        Float f2 = (Float) this.viewportWidth$delegate.getValue(this, kPropertyArr[4]);
        StringBuilder m = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(intValue, intValue2, "RoundedCornerSubCommand(height=", ", width=", ", pathData='");
        m.append(path);
        m.append("', viewportHeight=");
        m.append(f);
        m.append(", viewportWidth=");
        m.append(f2);
        m.append(")");
        return m.toString();
    }

    @Override // com.android.systemui.statusbar.commandline.ParseableCommand
    public final void execute(PrintWriter printWriter) {
    }
}
