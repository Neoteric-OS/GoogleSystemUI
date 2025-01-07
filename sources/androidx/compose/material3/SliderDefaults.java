package androidx.compose.material3;

import androidx.compose.material3.tokens.ColorSchemeKeyTokens;
import androidx.compose.material3.tokens.SliderTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.drawscope.DrawScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SliderDefaults {
    public static final SliderDefaults INSTANCE = new SliderDefaults();
    public static final float TickSize;
    public static final float TrackStopIndicatorSize;
    public static final AndroidPath trackPath;

    static {
        float f = SliderTokens.StopIndicatorSize;
        TrackStopIndicatorSize = f;
        TickSize = f;
        trackPath = AndroidPath_androidKt.Path();
    }

    public static SliderColors colors(Composer composer) {
        long Color;
        long Color2;
        long Color3;
        long Color4;
        long Color5;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ColorScheme colorScheme = MaterialTheme.getColorScheme(composer);
        SliderColors sliderColors = colorScheme.defaultSliderColorsCached;
        if (sliderColors != null) {
            return sliderColors;
        }
        float f = SliderTokens.ActiveHandleLeadingSpace;
        ColorSchemeKeyTokens colorSchemeKeyTokens = ColorSchemeKeyTokens.Primary;
        long fromToken = ColorSchemeKt.fromToken(colorScheme, colorSchemeKeyTokens);
        long fromToken2 = ColorSchemeKt.fromToken(colorScheme, colorSchemeKeyTokens);
        ColorSchemeKeyTokens colorSchemeKeyTokens2 = ColorSchemeKeyTokens.SecondaryContainer;
        long fromToken3 = ColorSchemeKt.fromToken(colorScheme, colorSchemeKeyTokens2);
        long fromToken4 = ColorSchemeKt.fromToken(colorScheme, colorSchemeKeyTokens2);
        long fromToken5 = ColorSchemeKt.fromToken(colorScheme, colorSchemeKeyTokens);
        ColorSchemeKeyTokens colorSchemeKeyTokens3 = ColorSchemeKeyTokens.OnSurface;
        Color = ColorKt.Color(Color.m368getRedimpl(r12), Color.m367getGreenimpl(r12), Color.m365getBlueimpl(r12), 0.38f, Color.m366getColorSpaceimpl(ColorSchemeKt.fromToken(colorScheme, colorSchemeKeyTokens3)));
        long m371compositeOverOWjLjI = ColorKt.m371compositeOverOWjLjI(Color, colorScheme.surface);
        Color2 = ColorKt.Color(Color.m368getRedimpl(r12), Color.m367getGreenimpl(r12), Color.m365getBlueimpl(r12), 0.38f, Color.m366getColorSpaceimpl(ColorSchemeKt.fromToken(colorScheme, colorSchemeKeyTokens3)));
        Color3 = ColorKt.Color(Color.m368getRedimpl(r12), Color.m367getGreenimpl(r12), Color.m365getBlueimpl(r12), 0.12f, Color.m366getColorSpaceimpl(ColorSchemeKt.fromToken(colorScheme, colorSchemeKeyTokens3)));
        Color4 = ColorKt.Color(Color.m368getRedimpl(r12), Color.m367getGreenimpl(r12), Color.m365getBlueimpl(r12), 0.12f, Color.m366getColorSpaceimpl(ColorSchemeKt.fromToken(colorScheme, colorSchemeKeyTokens3)));
        Color5 = ColorKt.Color(Color.m368getRedimpl(r12), Color.m367getGreenimpl(r12), Color.m365getBlueimpl(r12), 0.38f, Color.m366getColorSpaceimpl(ColorSchemeKt.fromToken(colorScheme, colorSchemeKeyTokens3)));
        SliderColors sliderColors2 = new SliderColors(fromToken, fromToken2, fromToken3, fromToken4, fromToken5, m371compositeOverOWjLjI, Color2, Color3, Color4, Color5);
        colorScheme.defaultSliderColorsCached = sliderColors2;
        return sliderColors2;
    }

    /* renamed from: drawTrackPath-Cx2C_VA, reason: not valid java name */
    public static void m229drawTrackPathCx2C_VA(DrawScope drawScope, long j, long j2, long j3, float f, float f2) {
        long floatToRawIntBits = (Float.floatToRawIntBits(f) << 32) | (Float.floatToRawIntBits(f) & 4294967295L);
        long floatToRawIntBits2 = (Float.floatToRawIntBits(f2) << 32) | (Float.floatToRawIntBits(f2) & 4294967295L);
        Rect m324Recttz77jQw = RectKt.m324Recttz77jQw(OffsetKt.Offset(Offset.m312getXimpl(j), 0.0f), SizeKt.Size(Size.m329getWidthimpl(j2), Size.m327getHeightimpl(j2)));
        RoundRect roundRect = new RoundRect(m324Recttz77jQw.left, m324Recttz77jQw.top, m324Recttz77jQw.right, m324Recttz77jQw.bottom, floatToRawIntBits, floatToRawIntBits2, floatToRawIntBits2, floatToRawIntBits);
        AndroidPath androidPath = trackPath;
        Path.addRoundRect$default(androidPath, roundRect);
        drawScope.mo414drawPathLG529CI(androidPath, j3);
        androidPath.internalPath.rewind();
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0128 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0069  */
    /* renamed from: Thumb-9LiSoMs, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m230Thumb9LiSoMs(final androidx.compose.foundation.interaction.MutableInteractionSource r19, androidx.compose.ui.Modifier r20, androidx.compose.material3.SliderColors r21, boolean r22, long r23, androidx.compose.runtime.Composer r25, final int r26, final int r27) {
        /*
            Method dump skipped, instructions count: 419
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderDefaults.m230Thumb9LiSoMs(androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.Modifier, androidx.compose.material3.SliderColors, boolean, long, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x026e  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x028f  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0287  */
    /* renamed from: Track-4EFweAY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m231Track4EFweAY(final androidx.compose.material3.SliderState r39, androidx.compose.ui.Modifier r40, boolean r41, androidx.compose.material3.SliderColors r42, kotlin.jvm.functions.Function2 r43, kotlin.jvm.functions.Function3 r44, float r45, float r46, androidx.compose.runtime.Composer r47, final int r48, final int r49) {
        /*
            Method dump skipped, instructions count: 756
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderDefaults.m231Track4EFweAY(androidx.compose.material3.SliderState, androidx.compose.ui.Modifier, boolean, androidx.compose.material3.SliderColors, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, float, float, androidx.compose.runtime.Composer, int, int):void");
    }
}
