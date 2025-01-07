package com.android.wm.shell.dagger;

import com.android.wm.shell.back.BackAnimationController;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.desktopmode.DesktopModeTaskRepository;
import com.android.wm.shell.desktopmode.DesktopTasksController;
import com.android.wm.shell.onehanded.OneHandedController;
import com.android.wm.shell.recents.RecentTasksController;
import com.android.wm.shell.splitscreen.SplitScreenController;
import java.util.function.Function;
import java.util.function.IntPredicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class WMShellBaseModule$$ExternalSyntheticLambda1 implements Function {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ WMShellBaseModule$$ExternalSyntheticLambda1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                return ((RecentTasksController) obj).mImpl;
            case 1:
                return ((BubbleController) obj).asBubbles();
            case 2:
                return ((SplitScreenController) obj).mImpl;
            case 3:
                return ((BackAnimationController) obj).mBackAnimation;
            case 4:
                return ((OneHandedController) obj).mImpl;
            case 5:
                return ((DesktopTasksController) obj).desktopMode;
            default:
                final DesktopModeTaskRepository desktopModeTaskRepository = (DesktopModeTaskRepository) obj;
                return new IntPredicate() { // from class: com.android.wm.shell.dagger.WMShellBaseModule$$ExternalSyntheticLambda12
                    @Override // java.util.function.IntPredicate
                    public final boolean test(int i) {
                        return DesktopModeTaskRepository.this.getVisibleTaskCount(i) > 0;
                    }
                };
        }
    }
}
