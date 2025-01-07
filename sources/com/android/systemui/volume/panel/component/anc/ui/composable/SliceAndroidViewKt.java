package com.android.systemui.volume.panel.component.anc.ui.composable;

import android.content.Context;
import android.view.ContextThemeWrapper;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.viewinterop.AndroidView_androidKt;
import androidx.slice.Slice;
import androidx.slice.widget.ListContent;
import androidx.slice.widget.RowContent;
import androidx.slice.widget.SliceViewPolicy;
import androidx.slice.widget.TemplateView;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SliceAndroidViewKt {
    public static final void SliceAndroidView(final Slice slice, Modifier modifier, final Function1 function1, final boolean z, Composer composer, final int i, final int i2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-417492503);
        if ((i2 & 2) != 0) {
            modifier = Modifier.Companion.$$INSTANCE;
        }
        if ((i2 & 4) != 0) {
            function1 = null;
        }
        if ((i2 & 8) != 0) {
            z = true;
        }
        OpaqueKey opaqueKey = ComposerKt.invocation;
        Modifier modifier2 = modifier;
        AndroidView_androidKt.AndroidView(new Function1() { // from class: com.android.systemui.volume.panel.component.anc.ui.composable.SliceAndroidViewKt$SliceAndroidView$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                RowContent rowContent;
                ComposeSliceView composeSliceView = new ComposeSliceView(new ContextThemeWrapper((Context) obj, R.style.Widget_SliceView_VolumePanel));
                composeSliceView.enableAccessibility = true;
                composeSliceView.mViewPolicy.getClass();
                SliceViewPolicy sliceViewPolicy = composeSliceView.mViewPolicy;
                boolean z2 = sliceViewPolicy.mScrollable;
                if (z2 && z2) {
                    sliceViewPolicy.mScrollable = false;
                    TemplateView templateView = sliceViewPolicy.mListener;
                    if (templateView != null) {
                        templateView.mRecyclerView.setNestedScrollingEnabled(false);
                        ListContent listContent = templateView.mListContent;
                        if (listContent != null) {
                            templateView.updateDisplayedItems(listContent.getHeight(templateView.mSliceStyle, templateView.mViewPolicy));
                        }
                    }
                }
                composeSliceView.setImportantForAccessibility(2);
                composeSliceView.mShowTitleItems = true;
                ListContent listContent2 = composeSliceView.mListContent;
                if (listContent2 != null && (rowContent = listContent2.mHeaderContent) != null) {
                    rowContent.mShowTitleItems = true;
                }
                return composeSliceView;
            }
        }, modifier2, null, new Function1() { // from class: com.android.systemui.volume.panel.component.anc.ui.composable.SliceAndroidViewKt$SliceAndroidView$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ComposeSliceView composeSliceView = (ComposeSliceView) obj;
                OnWidthChangedLayoutListener onWidthChangedLayoutListener = composeSliceView.layoutListener;
                if (onWidthChangedLayoutListener != null) {
                    composeSliceView.removeOnLayoutChangeListener(onWidthChangedLayoutListener);
                }
                composeSliceView.layoutListener = null;
                composeSliceView.setSlice(null);
                composeSliceView.enableAccessibility = true;
                return Unit.INSTANCE;
            }
        }, new Function1() { // from class: com.android.systemui.volume.panel.component.anc.ui.composable.SliceAndroidViewKt$SliceAndroidView$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ComposeSliceView composeSliceView = (ComposeSliceView) obj;
                composeSliceView.setSlice(Slice.this);
                Function1 function12 = function1;
                OnWidthChangedLayoutListener onWidthChangedLayoutListener = function12 != null ? new OnWidthChangedLayoutListener(function12) : null;
                OnWidthChangedLayoutListener onWidthChangedLayoutListener2 = composeSliceView.layoutListener;
                if (onWidthChangedLayoutListener2 != null) {
                    composeSliceView.removeOnLayoutChangeListener(onWidthChangedLayoutListener2);
                }
                composeSliceView.layoutListener = onWidthChangedLayoutListener;
                if (onWidthChangedLayoutListener != null) {
                    composeSliceView.addOnLayoutChangeListener(onWidthChangedLayoutListener);
                }
                composeSliceView.enableAccessibility = z;
                return Unit.INSTANCE;
            }
        }, composerImpl, (i & 112) | 3078, 4);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier3 = modifier;
            final Function1 function12 = function1;
            final boolean z2 = z;
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.volume.panel.component.anc.ui.composable.SliceAndroidViewKt$SliceAndroidView$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    SliceAndroidViewKt.SliceAndroidView(Slice.this, modifier3, function12, z2, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
