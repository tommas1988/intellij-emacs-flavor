package pers.tommas.emacsflavor.actions;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.IdeActions;
import com.intellij.openapi.actionSystem.impl.ActionConfigurationCustomizer;
import com.intellij.openapi.keymap.KeymapUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.wm.StatusBar;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class EmacsFlavorActionManager {
    public static boolean actionReplaced = false;

    private static final Map<String, AnAction> originActionMap = new HashMap<>();
    private static final Map<String, AnAction> emacsFlavorActionMap = new HashMap<>();

    static {
        emacsFlavorActionMap.put(IdeActions.ACTION_FIND, new IncrementalFindAction());
        emacsFlavorActionMap.put("EditorIndentSelection", new LangIndentSelectionAction());
    }

    public static class Customizer implements ActionConfigurationCustomizer {
        @Override
        public void customize(@NotNull ActionManager actionManager) {
            if (KeymapUtil.isEmacsKeymap()) {
                replaceActions(actionManager);
            }
        }
    }

    public static void replaceActions(ActionManager actionManager) {
        for (String actionId : emacsFlavorActionMap.keySet()) {
            AnAction originAction = actionManager.getAction(actionId);
            originActionMap.put(actionId, originAction);
            actionManager.replaceAction(actionId, emacsFlavorActionMap.get(actionId));
        }

        actionReplaced = true;
        showMessage("EmacsFlavor enabled");
    }

    public static void restoreActions(ActionManager actionManager) {
        for (String actionId : originActionMap.keySet()) {
            AnAction action = originActionMap.get(actionId);
            if (action != null) {
                actionManager.replaceAction(actionId, action);
            }
        }

        actionReplaced = false;
        showMessage("EmacsFlavor disabled");
    }

    private static void showMessage(String message) {
        ProjectManager projectManager = ProjectManager.getInstance();
        if (projectManager == null)
            return;

        for (Project project : projectManager.getOpenProjects()) {
            if (!project.isDisposed()) {
                StatusBar.Info.set(message, project);
            }
        }
    }
}