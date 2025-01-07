package androidx.compose.material3;

import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.foundation.text.selection.SimpleLayoutKt$$ExternalSyntheticOutline0;
import androidx.compose.material3.internal.MutableWindowInsets;
import androidx.compose.material3.tokens.MotionTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.ZIndexModifierKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SearchBar_androidKt {
    public static final TweenSpec AnimationEnterFloatSpec;
    public static final TweenSpec AnimationEnterSizeSpec = null;
    public static final TweenSpec AnimationExitFloatSpec;
    public static final TweenSpec AnimationExitSizeSpec = null;
    public static final TweenSpec AnimationPredictiveBackExitFloatSpec;
    public static final float SearchBarCornerRadius;
    public static final float SearchBarIconOffsetX;
    public static final float SearchBarMaxWidth;
    public static final float SearchBarMinWidth;
    public static final float SearchBarPredictiveBackMaxOffsetY;
    public static final float SearchBarPredictiveBackMinMargin;
    public static final float SearchBarVerticalPadding;

    static {
        int i = Color.$r8$clinit;
        SearchBarCornerRadius = SearchBarDefaults.InputFieldHeight / 2;
        SearchBarMinWidth = 360;
        SearchBarMaxWidth = 720;
        float f = 8;
        SearchBarVerticalPadding = f;
        SearchBarIconOffsetX = 4;
        SearchBarPredictiveBackMinMargin = f;
        SearchBarPredictiveBackMaxOffsetY = 24;
        CubicBezierEasing cubicBezierEasing = MotionTokens.EasingEmphasizedDecelerateCubicBezier;
        CubicBezierEasing cubicBezierEasing2 = new CubicBezierEasing(0.0f, 1.0f, 0.0f, 1.0f);
        TweenSpec tweenSpec = new TweenSpec(600, 100, cubicBezierEasing);
        AnimationEnterFloatSpec = tweenSpec;
        TweenSpec tweenSpec2 = new TweenSpec(350, 100, cubicBezierEasing2);
        AnimationExitFloatSpec = tweenSpec2;
        AnimationPredictiveBackExitFloatSpec = AnimationSpecKt.tween$default(350, 0, cubicBezierEasing2, 2);
        TweenSpec tweenSpec3 = new TweenSpec(600, 100, cubicBezierEasing);
        TweenSpec tweenSpec4 = new TweenSpec(350, 100, cubicBezierEasing2);
        EnterExitTransitionKt.fadeIn$default(tweenSpec, 2).plus(EnterExitTransitionKt.expandVertically$default(tweenSpec3, null, 14));
        EnterExitTransitionKt.fadeOut$default(tweenSpec2, 2).plus(EnterExitTransitionKt.shrinkVertically$default(tweenSpec4, null, 14));
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0299  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x02e0  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x02e7  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0307  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0319  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x03a9  */
    /* JADX WARN: Removed duplicated region for block: B:84:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0253  */
    /* JADX WARN: Type inference failed for: r12v4, types: [androidx.compose.material3.SearchBar_androidKt$SearchBar$4, kotlin.jvm.internal.Lambda] */
    /* renamed from: SearchBar-WuY5d9Q, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m224SearchBarWuY5d9Q(final java.lang.String r39, final kotlin.jvm.functions.Function1 r40, final kotlin.jvm.functions.Function1 r41, final boolean r42, final kotlin.jvm.functions.Function1 r43, androidx.compose.ui.Modifier r44, boolean r45, kotlin.jvm.functions.Function2 r46, kotlin.jvm.functions.Function2 r47, kotlin.jvm.functions.Function2 r48, androidx.compose.ui.graphics.Shape r49, androidx.compose.material3.SearchBarColors r50, float r51, float r52, androidx.compose.foundation.layout.WindowInsets r53, androidx.compose.foundation.interaction.MutableInteractionSource r54, final kotlin.jvm.functions.Function3 r55, androidx.compose.runtime.Composer r56, final int r57, final int r58, final int r59) {
        /*
            Method dump skipped, instructions count: 984
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SearchBar_androidKt.m224SearchBarWuY5d9Q(java.lang.String, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, boolean, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.ui.graphics.Shape, androidx.compose.material3.SearchBarColors, float, float, androidx.compose.foundation.layout.WindowInsets, androidx.compose.foundation.interaction.MutableInteractionSource, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x02b2  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x025b  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x029b  */
    /* renamed from: SearchBar-Y92LkZI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m225SearchBarY92LkZI(final kotlin.jvm.functions.Function2 r36, final boolean r37, final kotlin.jvm.functions.Function1 r38, androidx.compose.ui.Modifier r39, androidx.compose.ui.graphics.Shape r40, androidx.compose.material3.SearchBarColors r41, float r42, float r43, androidx.compose.foundation.layout.WindowInsets r44, final kotlin.jvm.functions.Function3 r45, androidx.compose.runtime.Composer r46, final int r47, final int r48) {
        /*
            Method dump skipped, instructions count: 838
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SearchBar_androidKt.m225SearchBarY92LkZI(kotlin.jvm.functions.Function2, boolean, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, androidx.compose.ui.graphics.Shape, androidx.compose.material3.SearchBarColors, float, float, androidx.compose.foundation.layout.WindowInsets, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0310  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01de  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x035c  */
    /* JADX WARN: Removed duplicated region for block: B:68:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x02a2 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x02dd  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0301  */
    /* JADX WARN: Type inference failed for: r1v12, types: [androidx.compose.material3.SearchBar_androidKt$SearchBarImpl$surface$1, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r1v30, types: [androidx.compose.material3.SearchBar_androidKt$SearchBarImpl$wrappedContent$1, kotlin.jvm.internal.Lambda] */
    /* renamed from: SearchBarImpl-j1jLAyQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m226SearchBarImplj1jLAyQ(final androidx.compose.animation.core.Animatable r29, final androidx.compose.runtime.MutableFloatState r30, final androidx.compose.runtime.MutableState r31, final androidx.compose.runtime.MutableState r32, androidx.compose.ui.Modifier r33, final kotlin.jvm.functions.Function2 r34, androidx.compose.ui.graphics.Shape r35, androidx.compose.material3.SearchBarColors r36, float r37, float r38, androidx.compose.foundation.layout.WindowInsets r39, final kotlin.jvm.functions.Function3 r40, androidx.compose.runtime.Composer r41, final int r42, final int r43, final int r44) {
        /*
            Method dump skipped, instructions count: 897
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SearchBar_androidKt.m226SearchBarImplj1jLAyQ(androidx.compose.animation.core.Animatable, androidx.compose.runtime.MutableFloatState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2, androidx.compose.ui.graphics.Shape, androidx.compose.material3.SearchBarColors, float, float, androidx.compose.foundation.layout.WindowInsets, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    public static final void SearchBarLayout(final Animatable animatable, final MutableFloatState mutableFloatState, final MutableState mutableState, final MutableState mutableState2, final Modifier modifier, final WindowInsets windowInsets, final Function2 function2, final Function2 function22, final Function2 function23, Composer composer, final int i) {
        int i2;
        int i3;
        boolean z;
        boolean z2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(70029564);
        if ((i & 6) == 0) {
            i2 = ((i & 8) == 0 ? composerImpl.changed(animatable) : composerImpl.changedInstance(animatable) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerImpl.changed(mutableFloatState) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerImpl.changed(mutableState) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerImpl.changed(mutableState2) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerImpl.changed(modifier) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerImpl.changed(windowInsets) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerImpl.changedInstance(function2) ? 1048576 : 524288;
        }
        if ((12582912 & i) == 0) {
            i2 |= composerImpl.changedInstance(function22) ? 8388608 : 4194304;
        }
        if ((100663296 & i) == 0) {
            i2 |= composerImpl.changedInstance(function23) ? 67108864 : 33554432;
        }
        int i4 = i2;
        if ((38347923 & i4) == 38347922 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            Object rememberedValue = composerImpl.rememberedValue();
            Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
            if (rememberedValue == composer$Companion$Empty$1) {
                rememberedValue = new MutableWindowInsets();
                composerImpl.updateRememberedValue(rememberedValue);
            }
            final MutableWindowInsets mutableWindowInsets = (MutableWindowInsets) rememberedValue;
            Modifier zIndex = ZIndexModifierKt.zIndex(modifier, 1.0f);
            boolean z3 = (i4 & 458752) == 131072;
            Object rememberedValue2 = composerImpl.rememberedValue();
            if (z3 || rememberedValue2 == composer$Companion$Empty$1) {
                rememberedValue2 = new Function1() { // from class: androidx.compose.material3.SearchBar_androidKt$SearchBarLayout$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        MutableWindowInsets mutableWindowInsets2 = MutableWindowInsets.this;
                        ((SnapshotMutableStateImpl) mutableWindowInsets2.insets$delegate).setValue(WindowInsetsKt.exclude(windowInsets, (WindowInsets) obj));
                        return Unit.INSTANCE;
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue2);
            }
            Modifier consumeWindowInsets = WindowInsetsPaddingKt.consumeWindowInsets(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(zIndex, (Function1) rememberedValue2), windowInsets);
            boolean z4 = ((i4 & 7168) == 2048) | ((i4 & 14) == 4 || ((i4 & 8) != 0 && composerImpl.changedInstance(animatable))) | ((i4 & 112) == 32) | ((i4 & 896) == 256);
            Object rememberedValue3 = composerImpl.rememberedValue();
            if (z4 || rememberedValue3 == composer$Companion$Empty$1) {
                i3 = i4;
                MeasurePolicy measurePolicy = new MeasurePolicy() { // from class: androidx.compose.material3.SearchBar_androidKt$SearchBarLayout$2$1
                    /* JADX WARN: Removed duplicated region for block: B:25:0x010c  */
                    /* JADX WARN: Removed duplicated region for block: B:36:0x012c  */
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* renamed from: measure-3p2s80s */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final androidx.compose.ui.layout.MeasureResult mo2measure3p2s80s(final androidx.compose.ui.layout.MeasureScope r21, java.util.List r22, final long r23) {
                        /*
                            Method dump skipped, instructions count: 364
                            To view this dump add '--comments-level debug' option
                        */
                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SearchBar_androidKt$SearchBarLayout$2$1.mo2measure3p2s80s(androidx.compose.ui.layout.MeasureScope, java.util.List, long):androidx.compose.ui.layout.MeasureResult");
                    }
                };
                composerImpl.updateRememberedValue(measurePolicy);
                rememberedValue3 = measurePolicy;
            } else {
                i3 = i4;
            }
            MeasurePolicy measurePolicy2 = (MeasurePolicy) rememberedValue3;
            int i5 = composerImpl.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, consumeWindowInsets);
            ComposeUiNode.Companion.getClass();
            Function0 function0 = ComposeUiNode.Companion.Constructor;
            composerImpl.startReusableNode();
            if (composerImpl.inserting) {
                composerImpl.createNode(function0);
            } else {
                composerImpl.useNode();
            }
            Function2 function24 = ComposeUiNode.Companion.SetMeasurePolicy;
            Updater.m259setimpl(composerImpl, measurePolicy2, function24);
            Function2 function25 = ComposeUiNode.Companion.SetResolvedCompositionLocals;
            Updater.m259setimpl(composerImpl, currentCompositionLocalScope, function25);
            Function2 function26 = ComposeUiNode.Companion.SetCompositeKeyHash;
            if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i5))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i5, composerImpl, i5, function26);
            }
            Function2 function27 = ComposeUiNode.Companion.SetModifier;
            Updater.m259setimpl(composerImpl, materializeModifier, function27);
            Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
            Modifier layoutId = LayoutIdKt.layoutId(companion, "Surface");
            BiasAlignment biasAlignment = Alignment.Companion.TopStart;
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(biasAlignment, true);
            int i6 = composerImpl.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope2 = composerImpl.currentCompositionLocalScope();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composerImpl, layoutId);
            composerImpl.startReusableNode();
            if (composerImpl.inserting) {
                composerImpl.createNode(function0);
            } else {
                composerImpl.useNode();
            }
            Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy, function24);
            Updater.m259setimpl(composerImpl, currentCompositionLocalScope2, function25);
            if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i6))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i6, composerImpl, i6, function26);
            }
            Updater.m259setimpl(composerImpl, materializeModifier2, function27);
            SimpleLayoutKt$$ExternalSyntheticOutline0.m((i3 >> 21) & 14, function22, composerImpl, true);
            Modifier layoutId2 = LayoutIdKt.layoutId(companion, "InputField");
            MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(biasAlignment, true);
            int i7 = composerImpl.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope3 = composerImpl.currentCompositionLocalScope();
            Modifier materializeModifier3 = ComposedModifierKt.materializeModifier(composerImpl, layoutId2);
            composerImpl.startReusableNode();
            if (composerImpl.inserting) {
                composerImpl.createNode(function0);
            } else {
                composerImpl.useNode();
            }
            Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy2, function24);
            Updater.m259setimpl(composerImpl, currentCompositionLocalScope3, function25);
            if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i7))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i7, composerImpl, i7, function26);
            }
            Updater.m259setimpl(composerImpl, materializeModifier3, function27);
            function2.invoke(composerImpl, Integer.valueOf((i3 >> 18) & 14));
            composerImpl.end(true);
            composerImpl.startReplaceGroup(-1106685601);
            if (function23 == null) {
                z = true;
                z2 = false;
            } else {
                Modifier layoutId3 = LayoutIdKt.layoutId(companion, "Content");
                MeasurePolicy maybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(biasAlignment, true);
                int i8 = composerImpl.compoundKeyHash;
                PersistentCompositionLocalMap currentCompositionLocalScope4 = composerImpl.currentCompositionLocalScope();
                Modifier materializeModifier4 = ComposedModifierKt.materializeModifier(composerImpl, layoutId3);
                composerImpl.startReusableNode();
                if (composerImpl.inserting) {
                    composerImpl.createNode(function0);
                } else {
                    composerImpl.useNode();
                }
                Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy3, function24);
                Updater.m259setimpl(composerImpl, currentCompositionLocalScope4, function25);
                if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i8))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(i8, composerImpl, i8, function26);
                }
                Updater.m259setimpl(composerImpl, materializeModifier4, function27);
                z = true;
                z2 = false;
                SimpleLayoutKt$$ExternalSyntheticOutline0.m(0, function23, composerImpl, true);
            }
            composerImpl.end(z2);
            composerImpl.end(z);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.material3.SearchBar_androidKt$SearchBarLayout$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    SearchBar_androidKt.SearchBarLayout(Animatable.this, mutableFloatState, mutableState, mutableState2, modifier, windowInsets, function2, function22, function23, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
