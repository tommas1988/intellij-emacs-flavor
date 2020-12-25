package pers.tommas.emacsflavor.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class LangIndentSelectionAction extends com.intellij.openapi.editor.actions.LangIndentSelectionAction {
    @Override
    public void update(@NotNull AnActionEvent e) {
        super.update(e);

        InputEvent inputEvent = e.getInputEvent();
        // disabled when trigger from keyboard
        if (inputEvent != null && inputEvent instanceof KeyEvent) {
            e.getPresentation().setEnabled(false);
        }
    }
}
