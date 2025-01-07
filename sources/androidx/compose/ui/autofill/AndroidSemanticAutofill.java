package androidx.compose.ui.autofill;

import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import androidx.collection.IntObjectMapKt;
import androidx.collection.MutableIntObjectMap;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.platform.SemanticsNodeCopy;
import androidx.compose.ui.platform.SemanticsNodeWithAdjustedBounds;
import androidx.compose.ui.platform.SemanticsUtils_androidKt;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.text.AnnotatedString;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.BufferedChannel;
import kotlinx.coroutines.channels.ChannelKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AndroidSemanticAutofill {
    public final AndroidSemanticAutofill$$ExternalSyntheticLambda0 autofillChangeChecker;
    public final AutofillManagerWrapper autofillManager;
    public final BufferedChannel boundsUpdateChannel;
    public MutableIntObjectMap currentSemanticsNodes;
    public boolean currentSemanticsNodesInvalidated;
    public final MutableIntObjectMap previousSemanticsNodes;
    public final AndroidComposeView view;

    public AndroidSemanticAutofill(AndroidComposeView androidComposeView) {
        this.view = androidComposeView;
        this.autofillManager = new AutofillManagerWrapperImpl(androidComposeView);
        androidComposeView.setImportantForAutofill(1);
        new Handler(Looper.getMainLooper());
        MutableIntObjectMap mutableIntObjectMap = IntObjectMapKt.EmptyIntObjectMap;
        this.previousSemanticsNodes = new MutableIntObjectMap();
        SemanticsNode unmergedRootSemanticsNode = androidComposeView.semanticsOwner.getUnmergedRootSemanticsNode();
        MutableIntObjectMap mutableIntObjectMap2 = IntObjectMapKt.EmptyIntObjectMap;
        new SemanticsNodeCopy(unmergedRootSemanticsNode, mutableIntObjectMap2);
        ChannelKt.Channel$default(1, null, null, 6);
        this.currentSemanticsNodesInvalidated = true;
        this.currentSemanticsNodes = mutableIntObjectMap2;
        new Runnable() { // from class: androidx.compose.ui.autofill.AndroidSemanticAutofill$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AndroidSemanticAutofill androidSemanticAutofill;
                MutableIntObjectMap mutableIntObjectMap3;
                MutableIntObjectMap mutableIntObjectMap4;
                MutableIntObjectMap mutableIntObjectMap5;
                MutableIntObjectMap mutableIntObjectMap6;
                int[] iArr;
                long[] jArr;
                AndroidSemanticAutofill androidSemanticAutofill2;
                MutableIntObjectMap mutableIntObjectMap7;
                int[] iArr2;
                long[] jArr2;
                MutableIntObjectMap mutableIntObjectMap8;
                int i;
                String str;
                SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds;
                Rect rect;
                AndroidSemanticAutofill androidSemanticAutofill3 = AndroidSemanticAutofill.this;
                androidSemanticAutofill3.view.measureAndLayout(true);
                MutableIntObjectMap currentSemanticsNodes$ui_release = androidSemanticAutofill3.getCurrentSemanticsNodes$ui_release();
                int[] iArr3 = currentSemanticsNodes$ui_release.keys;
                long[] jArr3 = currentSemanticsNodes$ui_release.metadata;
                int length = jArr3.length - 2;
                MutableIntObjectMap mutableIntObjectMap9 = androidSemanticAutofill3.previousSemanticsNodes;
                int i2 = 8;
                long j = -9187201950435737472L;
                char c = 7;
                if (length >= 0) {
                    int i3 = 0;
                    while (true) {
                        long j2 = jArr3[i3];
                        if ((((~j2) << c) & j2 & j) != j) {
                            int i4 = 8 - ((~(i3 - length)) >>> 31);
                            int i5 = 0;
                            while (i5 < i4) {
                                if ((j2 & 255) < 128) {
                                    int i6 = iArr3[(i3 << 3) + i5];
                                    SemanticsNodeCopy semanticsNodeCopy = (SemanticsNodeCopy) mutableIntObjectMap9.get(i6);
                                    SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds2 = (SemanticsNodeWithAdjustedBounds) currentSemanticsNodes$ui_release.get(i6);
                                    SemanticsNode semanticsNode = semanticsNodeWithAdjustedBounds2 != null ? semanticsNodeWithAdjustedBounds2.semanticsNode : null;
                                    if (semanticsNode == null) {
                                        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("no value for specified key");
                                        throw null;
                                    }
                                    if (semanticsNodeCopy == null) {
                                        androidSemanticAutofill2 = androidSemanticAutofill3;
                                        mutableIntObjectMap7 = currentSemanticsNodes$ui_release;
                                        iArr2 = iArr3;
                                        jArr2 = jArr3;
                                        mutableIntObjectMap8 = mutableIntObjectMap9;
                                    } else {
                                        SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.EditableText;
                                        SemanticsConfiguration semanticsConfiguration = semanticsNodeCopy.unmergedConfig;
                                        mutableIntObjectMap7 = currentSemanticsNodes$ui_release;
                                        AnnotatedString annotatedString = (AnnotatedString) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, semanticsPropertyKey);
                                        if (annotatedString != null) {
                                            str = annotatedString.text;
                                            iArr2 = iArr3;
                                        } else {
                                            iArr2 = iArr3;
                                            str = null;
                                        }
                                        SemanticsConfiguration semanticsConfiguration2 = semanticsNode.unmergedConfig;
                                        AnnotatedString annotatedString2 = (AnnotatedString) SemanticsConfigurationKt.getOrNull(semanticsConfiguration2, semanticsPropertyKey);
                                        String str2 = annotatedString2 != null ? annotatedString2.text : null;
                                        if (!Intrinsics.areEqual(str, str2) && str2 != null) {
                                            SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds3 = (SemanticsNodeWithAdjustedBounds) androidSemanticAutofill3.getCurrentSemanticsNodes$ui_release().get(i6);
                                            SemanticsNode semanticsNode2 = semanticsNodeWithAdjustedBounds3 != null ? semanticsNodeWithAdjustedBounds3.semanticsNode : null;
                                            if (semanticsNode2 != null && SemanticsConfigurationKt.getOrNull(semanticsNode2.unmergedConfig, SemanticsProperties.ContentDataType) != null) {
                                                throw new ClassCastException();
                                            }
                                            throw new NotImplementedError("An operation is not implemented: b/138604541: Add Autofill support for ContentDataType.None");
                                        }
                                        SemanticsPropertyKey semanticsPropertyKey2 = SemanticsProperties.Focused;
                                        Boolean bool = (Boolean) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, semanticsPropertyKey2);
                                        Boolean bool2 = (Boolean) SemanticsConfigurationKt.getOrNull(semanticsConfiguration2, semanticsPropertyKey2);
                                        Boolean bool3 = Boolean.TRUE;
                                        boolean areEqual = Intrinsics.areEqual(bool, bool3);
                                        jArr2 = jArr3;
                                        AutofillManagerWrapper autofillManagerWrapper = androidSemanticAutofill3.autofillManager;
                                        if (areEqual || !Intrinsics.areEqual(bool2, bool3) || (semanticsNodeWithAdjustedBounds = (SemanticsNodeWithAdjustedBounds) androidSemanticAutofill3.getCurrentSemanticsNodes$ui_release().get(i6)) == null || (rect = semanticsNodeWithAdjustedBounds.adjustedBounds) == null) {
                                            androidSemanticAutofill2 = androidSemanticAutofill3;
                                            mutableIntObjectMap8 = mutableIntObjectMap9;
                                        } else {
                                            androidSemanticAutofill2 = androidSemanticAutofill3;
                                            AutofillManagerWrapperImpl autofillManagerWrapperImpl = (AutofillManagerWrapperImpl) autofillManagerWrapper;
                                            mutableIntObjectMap8 = mutableIntObjectMap9;
                                            autofillManagerWrapperImpl.autofillManager.notifyViewEntered(autofillManagerWrapperImpl.view, i6, rect);
                                        }
                                        if (Intrinsics.areEqual(bool, bool3) && !Intrinsics.areEqual(bool2, bool3)) {
                                            AutofillManagerWrapperImpl autofillManagerWrapperImpl2 = (AutofillManagerWrapperImpl) autofillManagerWrapper;
                                            autofillManagerWrapperImpl2.autofillManager.notifyViewExited(autofillManagerWrapperImpl2.view, i6);
                                        }
                                        NodeCoordinator findCoordinatorToGetBounds$ui_release = semanticsNode.findCoordinatorToGetBounds$ui_release();
                                        boolean isTransparent = findCoordinatorToGetBounds$ui_release != null ? findCoordinatorToGetBounds$ui_release.isTransparent() : false;
                                        if (semanticsNodeCopy.isTransparent != isTransparent) {
                                            AutofillManagerWrapperImpl autofillManagerWrapperImpl3 = (AutofillManagerWrapperImpl) autofillManagerWrapper;
                                            autofillManagerWrapperImpl3.autofillManager.notifyViewVisibilityChanged(autofillManagerWrapperImpl3.view, i6, !isTransparent);
                                        }
                                    }
                                    i = 8;
                                } else {
                                    androidSemanticAutofill2 = androidSemanticAutofill3;
                                    mutableIntObjectMap7 = currentSemanticsNodes$ui_release;
                                    iArr2 = iArr3;
                                    jArr2 = jArr3;
                                    mutableIntObjectMap8 = mutableIntObjectMap9;
                                    i = i2;
                                }
                                j2 >>= i;
                                i5++;
                                i2 = i;
                                iArr3 = iArr2;
                                currentSemanticsNodes$ui_release = mutableIntObjectMap7;
                                jArr3 = jArr2;
                                androidSemanticAutofill3 = androidSemanticAutofill2;
                                mutableIntObjectMap9 = mutableIntObjectMap8;
                            }
                            androidSemanticAutofill = androidSemanticAutofill3;
                            mutableIntObjectMap6 = currentSemanticsNodes$ui_release;
                            iArr = iArr3;
                            jArr = jArr3;
                            mutableIntObjectMap3 = mutableIntObjectMap9;
                            if (i4 != i2) {
                                break;
                            }
                        } else {
                            androidSemanticAutofill = androidSemanticAutofill3;
                            mutableIntObjectMap6 = currentSemanticsNodes$ui_release;
                            iArr = iArr3;
                            jArr = jArr3;
                            mutableIntObjectMap3 = mutableIntObjectMap9;
                        }
                        if (i3 == length) {
                            break;
                        }
                        i3++;
                        iArr3 = iArr;
                        currentSemanticsNodes$ui_release = mutableIntObjectMap6;
                        jArr3 = jArr;
                        androidSemanticAutofill3 = androidSemanticAutofill;
                        mutableIntObjectMap9 = mutableIntObjectMap3;
                        i2 = 8;
                        j = -9187201950435737472L;
                        c = 7;
                    }
                } else {
                    androidSemanticAutofill = androidSemanticAutofill3;
                    mutableIntObjectMap3 = mutableIntObjectMap9;
                }
                mutableIntObjectMap3.clear();
                MutableIntObjectMap currentSemanticsNodes$ui_release2 = androidSemanticAutofill.getCurrentSemanticsNodes$ui_release();
                int[] iArr4 = currentSemanticsNodes$ui_release2.keys;
                Object[] objArr = currentSemanticsNodes$ui_release2.values;
                long[] jArr4 = currentSemanticsNodes$ui_release2.metadata;
                int length2 = jArr4.length - 2;
                if (length2 >= 0) {
                    int i7 = 0;
                    while (true) {
                        long j3 = jArr4[i7];
                        if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                            int i8 = 8 - ((~(i7 - length2)) >>> 31);
                            int i9 = 0;
                            while (i9 < i8) {
                                if ((j3 & 255) < 128) {
                                    int i10 = (i7 << 3) + i9;
                                    mutableIntObjectMap5 = mutableIntObjectMap3;
                                    mutableIntObjectMap5.set(iArr4[i10], new SemanticsNodeCopy(((SemanticsNodeWithAdjustedBounds) objArr[i10]).semanticsNode, androidSemanticAutofill.getCurrentSemanticsNodes$ui_release()));
                                } else {
                                    mutableIntObjectMap5 = mutableIntObjectMap3;
                                }
                                j3 >>= 8;
                                i9++;
                                mutableIntObjectMap3 = mutableIntObjectMap5;
                            }
                            mutableIntObjectMap4 = mutableIntObjectMap3;
                            if (i8 != 8) {
                                break;
                            }
                        } else {
                            mutableIntObjectMap4 = mutableIntObjectMap3;
                        }
                        if (i7 == length2) {
                            break;
                        }
                        i7++;
                        mutableIntObjectMap3 = mutableIntObjectMap4;
                    }
                }
                AndroidSemanticAutofill androidSemanticAutofill4 = androidSemanticAutofill;
                new SemanticsNodeCopy(androidSemanticAutofill4.view.semanticsOwner.getUnmergedRootSemanticsNode(), androidSemanticAutofill4.getCurrentSemanticsNodes$ui_release());
            }
        };
    }

    public final MutableIntObjectMap getCurrentSemanticsNodes$ui_release() {
        if (this.currentSemanticsNodesInvalidated) {
            this.currentSemanticsNodesInvalidated = false;
            this.currentSemanticsNodes = SemanticsUtils_androidKt.getAllUncoveredSemanticsNodesToIntObjectMap(this.view.semanticsOwner);
        }
        return this.currentSemanticsNodes;
    }
}
