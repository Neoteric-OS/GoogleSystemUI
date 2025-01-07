package com.android.compose.animation.scene;

import androidx.compose.runtime.SnapshotMutableStateImpl;
import com.android.compose.animation.scene.Swipe;
import com.android.compose.animation.scene.content.Content;
import java.util.Map;
import kotlin.Pair;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Swipes {
    public final Swipe.Resolved downOrRight;
    public final Swipe.Resolved downOrRightNoSource;
    public UserActionResult downOrRightResult;
    public final Swipe.Resolved upOrLeft;
    public final Swipe.Resolved upOrLeftNoSource;
    public UserActionResult upOrLeftResult;

    public Swipes(Swipe.Resolved resolved, Swipe.Resolved resolved2, Swipe.Resolved resolved3, Swipe.Resolved resolved4) {
        this.upOrLeft = resolved;
        this.downOrRight = resolved2;
        this.upOrLeftNoSource = resolved3;
        this.downOrRightNoSource = resolved4;
    }

    public final Pair computeSwipesResults(Content content) {
        Map map = (Map) ((SnapshotMutableStateImpl) content.userActions$delegate).getValue();
        Swipe.Resolved resolved = this.upOrLeft;
        UserActionResult userActionResult = resolved == null ? null : (UserActionResult) map.get(resolved);
        if (userActionResult == null) {
            userActionResult = (UserActionResult) map.get(this.upOrLeftNoSource);
        }
        Swipe.Resolved resolved2 = this.downOrRight;
        UserActionResult userActionResult2 = resolved2 != null ? (UserActionResult) map.get(resolved2) : null;
        if (userActionResult2 == null) {
            userActionResult2 = (UserActionResult) map.get(this.downOrRightNoSource);
        }
        return new Pair(userActionResult, userActionResult2);
    }

    public final void updateSwipesResults(Content content) {
        Pair computeSwipesResults = computeSwipesResults(content);
        UserActionResult userActionResult = (UserActionResult) computeSwipesResults.component1();
        UserActionResult userActionResult2 = (UserActionResult) computeSwipesResults.component2();
        this.upOrLeftResult = userActionResult;
        this.downOrRightResult = userActionResult2;
    }
}
