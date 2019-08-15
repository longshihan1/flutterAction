package leetcode.utils;
import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class PropertiesUtils { private static final String baseName = "i18n/info";
    private static final ResourceBundle rb1 = ResourceBundle.getBundle("i18n/info");

    public static String getInfo(String key, String... params)
    {
        return new MessageFormat(rb1.getString(key)).format(params);
    }

    public static void main(String[] args)
    {
        System.out.println(Locale.getDefault());
        System.out.println(ResourceBundle.getBundle("i18n/info").getString("login.failed"));
        System.out.println(new MessageFormat(ResourceBundle.getBundle("i18n/info").getString("login.failed")).format(null));
        System.out.println(getInfo("login.failed", new String[0]));
    }

}
