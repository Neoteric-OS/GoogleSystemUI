package com.android.systemui.statusbar;

import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.view.KeyboardShortcutGroup;
import android.widget.ImageView;
import com.android.wm.shell.R;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class KeyboardShortcutListSearch$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ KeyboardShortcutListSearch f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ KeyboardShortcutListSearch$$ExternalSyntheticLambda3(KeyboardShortcutListSearch keyboardShortcutListSearch, Object obj, int i) {
        this.$r8$classId = i;
        this.f$0 = keyboardShortcutListSearch;
        this.f$1 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                KeyboardShortcutListSearch keyboardShortcutListSearch = this.f$0;
                List list = (List) this.f$1;
                if (list != null && !list.isEmpty()) {
                    KeyboardShortcuts.sanitiseShortcuts(list);
                    keyboardShortcutListSearch.mInputGroup.addAll(KeyboardShortcutListSearch.reMapToKeyboardShortcutMultiMappingGroup(list));
                }
                keyboardShortcutListSearch.mImeShortcutsReceived = true;
                if (keyboardShortcutListSearch.mAppShortcutsReceived) {
                    keyboardShortcutListSearch.mergeAndShowKeyboardShortcutsGroups();
                    break;
                }
                break;
            case 1:
                KeyboardShortcutListSearch keyboardShortcutListSearch2 = this.f$0;
                List list2 = (List) this.f$1;
                if (list2 != null) {
                    if (list2.isEmpty()) {
                        keyboardShortcutListSearch2.mCurrentAppPackageName = null;
                        keyboardShortcutListSearch2.mKeySearchResultMap.put(3, Boolean.FALSE);
                    } else {
                        keyboardShortcutListSearch2.mCurrentAppPackageName = ((KeyboardShortcutGroup) list2.get(0)).getPackageName();
                        KeyboardShortcuts.sanitiseShortcuts(list2);
                        keyboardShortcutListSearch2.mSpecificAppGroup.addAll(KeyboardShortcutListSearch.reMapToKeyboardShortcutMultiMappingGroup(list2));
                        keyboardShortcutListSearch2.mKeySearchResultMap.put(3, Boolean.TRUE);
                    }
                }
                keyboardShortcutListSearch2.mAppShortcutsReceived = true;
                if (keyboardShortcutListSearch2.mImeShortcutsReceived) {
                    keyboardShortcutListSearch2.mergeAndShowKeyboardShortcutsGroups();
                    break;
                }
                break;
            default:
                KeyboardShortcutListSearch keyboardShortcutListSearch3 = this.f$0;
                ImageView imageView = (ImageView) this.f$1;
                keyboardShortcutListSearch3.getClass();
                Drawable drawable = imageView.getDrawable();
                float dimensionPixelSize = keyboardShortcutListSearch3.mContext.getResources().getDimensionPixelSize(R.dimen.ksh_icon_scaled_size);
                int width = imageView.getWidth();
                int height = imageView.getHeight();
                float intrinsicWidth = dimensionPixelSize / drawable.getIntrinsicWidth();
                int paddingLeft = imageView.getPaddingLeft();
                int paddingTop = imageView.getPaddingTop();
                Matrix matrix = new Matrix();
                matrix.postScale(intrinsicWidth, intrinsicWidth);
                matrix.postTranslate(((width - dimensionPixelSize) / 2.0f) - paddingLeft, ((height - dimensionPixelSize) / 2.0f) - paddingTop);
                imageView.setImageMatrix(matrix);
                break;
        }
    }
}
