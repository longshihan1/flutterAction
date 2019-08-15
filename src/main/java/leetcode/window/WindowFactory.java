package leetcode.window;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.sun.istack.NotNull;

public class WindowFactory implements ToolWindowFactory {
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        if (project == null) {
            return;
        }
        if (toolWindow == null) {
            return;
        }
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(new NavigatorPanel(toolWindow, project), "", false);
        toolWindow.getContentManager().addContent(content);
    }
}
