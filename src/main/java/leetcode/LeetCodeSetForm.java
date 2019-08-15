package leetcode;

import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBPasswordField;
import com.intellij.ui.components.JBTextField;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import leetcode.model.CodeTypeEnum;
import leetcode.model.Config;
import leetcode.utils.MTAUtils;
import org.apache.commons.lang.StringUtils;

public class LeetCodeSetForm extends JDialog {

    public JPanel mainPanel = new JBPanel();
    private JTextField userNameField = new JBTextField(10);
    private JPasswordField passwordField = new JBPasswordField();
    private TextFieldWithBrowseButton fileFolderBtn = new TextFieldWithBrowseButton();

    public LeetCodeSetForm() {
        setContentPane(this.mainPanel);
    }

    public void createUI() {
        this.mainPanel.setLayout(new GridLayout(10, 0));

        JPanel webMainPane = new JPanel(new FlowLayout(0));

        JPanel webPanel = new JPanel(new FlowLayout(0));
        webPanel.add(new JLabel("URL:"));
        webPanel.add(new JLabel("leetcode-cn.com"));

        JPanel codePanel = new JPanel(new FlowLayout(0));
        codePanel.add(new JLabel("Code Type:"));
        codePanel.add(new JLabel("Java"));

        webMainPane.add(webPanel);
        webMainPane.add(codePanel);

        JPanel loginMainPane = new JPanel(new FlowLayout(0));
        JPanel userNamePanel = new JPanel();
        userNamePanel.setLayout(new FlowLayout(0));
        userNamePanel.add(new JLabel("LoginName:"));
        userNamePanel.add(this.userNameField);

        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new FlowLayout(0));
        passwordPanel.add(new JLabel("Password:"));
        this.passwordField.setColumns(10);
        passwordPanel.add(this.passwordField);

        loginMainPane.add(userNamePanel);
        loginMainPane.add(passwordPanel);
        this.mainPanel.add(webMainPane);
        this.mainPanel.add(loginMainPane);
        Config config = PersistentConfig.getInstance().getInitConfig();
        if (config != null) {
            this.userNameField.setText(config.getLoginName());
            this.passwordField.setText(PersistentConfig.getInstance().getPassword(config.getPassword()));
            if (StringUtils.isNotBlank(config.getFilePath())) {
                this.fileFolderBtn.setText(config.getFilePath());
            }
        }
    }

    public boolean isModified() {
        boolean modified = true;
        return modified;
    }

    public void apply() {
        Config config = PersistentConfig.getInstance().getInitConfig();
        if (config == null) {
            config = new Config();
            config.setId(MTAUtils.getI(""));
        }
        config.setVersion(Integer.valueOf(1));
        config.setLoginName(this.userNameField.getText());
        config.setPassword("");
        config.setFilePath(this.fileFolderBtn.getText());
        config.setCodeType("Java");
        config.setUrl("leetcode-cn.com");
        File file = new File(config.getFilePath() + File.separator + PersistentConfig.PATH + File.separator);
        if (!file.exists()) {
            file.mkdirs();
        }
        PersistentConfig.getInstance().setInitConfig(config);
        PersistentConfig.getInstance().savePassword(this.passwordField.getText());
    }

    public void reset() {
    }

    public JPanel getContentPane() {
        return this.mainPanel;
    }
}
