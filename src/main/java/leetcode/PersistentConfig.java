package leetcode;

import com.intellij.ide.passwordSafe.PasswordSafe;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.util.xmlb.XmlSerializerUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import leetcode.model.Config;
import leetcode.utils.MessageUtils;
import leetcode.utils.PropertiesUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name = "PersistentConfig", storages = {@com.intellij.openapi.components.Storage(value = "leetcode-config.xml",
        roamingType = com.intellij.openapi.components.RoamingType.DISABLED)})
public class PersistentConfig
        implements PersistentStateComponent<PersistentConfig> {
    public static String PATH;
    private static String INITNAME;
    private Map<String, Config> initConfig;

    static {
        PersistentConfig.PATH = "leetcode-plugin";
        PersistentConfig.INITNAME = "initConfig";
    }

    public PersistentConfig() {
        this.initConfig = new HashMap<>();
    }

    @Nullable
    public static PersistentConfig getInstance() {
        return (PersistentConfig) ServiceManager.getService(PersistentConfig.class);
    }

    @Nullable
    public PersistentConfig getState() {
        return this;
    }

    public void loadState(@NotNull PersistentConfig persistentConfig) {
        if (persistentConfig == null) {
            return;
        }
        XmlSerializerUtil.copyBean(persistentConfig, this);
    }

    public Config getInitConfig() {
        return (Config) this.initConfig.get(PersistentConfig.INITNAME);
    }

    public Config getConfig() {
        Config config = (Config) this.initConfig.get(PersistentConfig.INITNAME);
        if (config == null) {
            MessageUtils.showWarnMsg("warning", PropertiesUtils.getInfo("config.first", new String[0]));
            throw new UnsupportedOperationException("not configured:File -> settings->tools->leetcode plugin");
        }
        return config;
    }

    public void setInitConfig(Config config) {
        this.initConfig.put(PersistentConfig.INITNAME, config);
    }

    public String getTempFilePath() {
        return getConfig().getFilePath() + File.separator + PersistentConfig.PATH + File.separator + ((Config) this.initConfig.get(PersistentConfig.INITNAME)).getAlias() + File.separator;
    }

    public void savePassword(String password) {
        PasswordSafe.getInstance().storePassword(null, getClass(), "leetcode-editor", password != null ? password : "");
    }

    public String getPassword(String password) {
        if (getConfig().getVersion() != null) {
            return PasswordSafe.getInstance().getPassword(null, getClass(), "leetcode-editor");
        }
        return password;
    }
}
