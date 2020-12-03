package pers.tommas.emacsflavor.listeners;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.IdeActions;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.keymap.Keymap;
import com.intellij.openapi.keymap.KeymapManagerListener;
import com.intellij.openapi.keymap.KeymapUtil;
import org.jetbrains.annotations.Nullable;
import pers.tommas.emacsflavor.actions.EmacsFlavorActionManager;
import pers.tommas.emacsflavor.actions.EmacsFlavorIncrementalFindAction;

import java.util.HashMap;
import java.util.Map;

public class EmacsKeymapActiveListener implements KeymapManagerListener {
    @Override
    public void activeKeymapChanged(@Nullable Keymap keymap) {
        if (!KeymapUtil.isEmacsKeymap(keymap)) {
            if (EmacsFlavorActionManager.actionReplaced) {
                EmacsFlavorActionManager.restoreActions(ActionManager.getInstance());
            }
            return;
        }

        if (ApplicationManager.getApplication().isActive()) {
            EmacsFlavorActionManager.replaceActions(ActionManager.getInstance());
        }
    }
}