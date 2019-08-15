package leetcode;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class LeetSettingConfigurable implements SearchableConfigurable {
    private LeetCodeSetForm form;

    @NotNull
    @Override
    public String getId() {
        return "com.longshihan.leetid";
    }


    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "插件测试";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        this.form = new LeetCodeSetForm();
        this.form.createUI();
        return this.form.getContentPane();
    }

    public boolean isModified()
    {
        return this.form.isModified();
    }

    public void apply()
            throws ConfigurationException
    {
        this.form.apply();
    }

    public void reset()
    {
        this.form.reset();
    }

    public void disposeUIResources()
    {
        this.form = null;
    }
}
