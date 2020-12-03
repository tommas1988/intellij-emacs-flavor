package pers.tommas.emacsflavor.actions;

import com.intellij.find.SearchReplaceComponent;
import com.intellij.ide.lightEdit.LightEditCompatible;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import org.jetbrains.annotations.NotNull;

public class FindCloseAction extends DumbAwareAction implements LightEditCompatible {
    private SearchReplaceComponent component;

    public FindCloseAction(SearchReplaceComponent component) {
        this.component = component;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        component.close();
    }
}
