package androidx.compose.foundation.pager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LazyLayoutPagerKt {
    /* JADX WARN: Code restructure failed: missing block: B:148:0x03f5, code lost:
    
        if (r5.changed(r56) == false) goto L293;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x041a, code lost:
    
        if (r5.changed(r3) == false) goto L303;
     */
    /* JADX WARN: Removed duplicated region for block: B:101:0x02ee A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0337  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x034e  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0367  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0380  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0398  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x03b2  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x03ca  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x03eb  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0414  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0433  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0481  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x048e  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x04aa A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:173:0x04bc  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x04c7  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x04d3 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:183:0x04f0  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0500 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0539  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0542  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0564  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x057b  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0590 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:207:0x05be  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x05c9  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x05cb  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x05c1  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x0553  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x053c  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x04f3  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x04ca  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x04bf  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x0484  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0447  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0426  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x041d  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x0405  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x03f8  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x03e2  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x03d8 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:268:0x02e7  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x060d  */
    /* JADX WARN: Removed duplicated region for block: B:70:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0248 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x029a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x02d2  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x02e4  */
    /* renamed from: Pager-uYRUAWA, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m140PageruYRUAWA(final androidx.compose.ui.Modifier r42, final androidx.compose.foundation.pager.PagerState r43, final androidx.compose.foundation.layout.PaddingValues r44, final boolean r45, final androidx.compose.foundation.gestures.Orientation r46, final androidx.compose.foundation.gestures.TargetedFlingBehavior r47, final boolean r48, int r49, float r50, final androidx.compose.foundation.pager.PageSize r51, final androidx.compose.ui.input.nestedscroll.NestedScrollConnection r52, final kotlin.jvm.functions.Function1 r53, final androidx.compose.ui.Alignment.Horizontal r54, final androidx.compose.ui.Alignment.Vertical r55, final androidx.compose.foundation.gestures.snapping.SnapPosition r56, final kotlin.jvm.functions.Function4 r57, androidx.compose.runtime.Composer r58, final int r59, final int r60, final int r61) {
        /*
            Method dump skipped, instructions count: 1602
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.pager.LazyLayoutPagerKt.m140PageruYRUAWA(androidx.compose.ui.Modifier, androidx.compose.foundation.pager.PagerState, androidx.compose.foundation.layout.PaddingValues, boolean, androidx.compose.foundation.gestures.Orientation, androidx.compose.foundation.gestures.TargetedFlingBehavior, boolean, int, float, androidx.compose.foundation.pager.PageSize, androidx.compose.ui.input.nestedscroll.NestedScrollConnection, kotlin.jvm.functions.Function1, androidx.compose.ui.Alignment$Horizontal, androidx.compose.ui.Alignment$Vertical, androidx.compose.foundation.gestures.snapping.SnapPosition, kotlin.jvm.functions.Function4, androidx.compose.runtime.Composer, int, int, int):void");
    }
}
