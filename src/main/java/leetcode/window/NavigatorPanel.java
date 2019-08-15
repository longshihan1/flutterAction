package leetcode.window;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.DataProvider;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.treeStructure.SimpleTree;
import com.intellij.util.EditSourceOnDoubleClickHandler;
import leetcode.model.Question;
import leetcode.utils.DataKeys;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

public class NavigatorPanel extends SimpleToolWindowPanel implements DataProvider {
    private JPanel queryPanel;
    private JBScrollPane contentScrollPanel;
    private SimpleTree tree;

    public NavigatorPanel(ToolWindow toolWindow, Project project) {
        super(true, true);
        ActionManager actionManager = ActionManager.getInstance();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(new Question("root"));
        this.tree = new SimpleTree(new DefaultTreeModel(root)) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        this.tree.getEmptyText().clear();
        this.tree.setRowHeight(21);
        this.tree.setOpaque(false);
        this.tree.getSelectionModel().setSelectionMode(1);
        this.tree.setRootVisible(false);

        ActionToolbar actionToolbar = actionManager.createActionToolbar("leetcode Toolbar",
                (DefaultActionGroup) actionManager.getAction("leetcode.NavigatorActionsToolbar"), true);


        actionToolbar.setTargetComponent(this.tree);
        setToolbar(actionToolbar.getComponent());

        SimpleToolWindowPanel treePanel = new SimpleToolWindowPanel(true, true);

        this.queryPanel = new JPanel();
        this.queryPanel.setLayout(new BoxLayout(this.queryPanel, 1));
        JTextField queryField = new JBTextField();
        queryField.setToolTipText("Enter Search");
        this.queryPanel.add(queryField);
        treePanel.setToolbar(this.queryPanel);
        setContent(treePanel);
    }

    public Object getData(String dataId) {
        if (DataKeys.LEETCODE_PROJECTS_TREE.is(dataId)) {
            return this.tree;
        }
        if (DataKeys.LEETCODE_PROJECTS_TERRFIND.is(dataId)) {
            return this.queryPanel;
        }
        if (DataKeys.LEETCODE_PROJECTS_SCROLL.is(dataId)) {
            return this.contentScrollPanel;
        }
        return super.getData(dataId);
    }
}
