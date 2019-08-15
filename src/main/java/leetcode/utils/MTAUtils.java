package leetcode.utils;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URI;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import leetcode.model.Config;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
public class MTAUtils {
    private static String URL = "http://pingtcss.qq.com/pingd";
    private static String SID = "500676642";
    private static String SI = getI("s");
    private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    private static final PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();

    public static CloseableHttpClient getHttpClient()
    {
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(1000).setConnectTimeout(1000).setSocketTimeout(1000).build();

        return HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connManager)
                .build();
    }

    public static String getI(String prefix)
    {
        int[] b = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        for (int e = 10; 1 < e; e--)
        {
            int c = (int)Math.floor(10.0D * Math.random());
            int f = b[c];
            b[c] = b[(e - 1)];
            b[(e - 1)] = f;
        }
        int c = 0;
        for (int e = 0; 5 > e; e++) {
            c = 10 * c + b[e];
        }
        return prefix + c + System.currentTimeMillis();
    }

    public static void click(String actionsId, Config config)
    {
        cachedThreadPool.execute(new ClickTask(getHttpClient(), config, actionsId));
    }

    private static class ClickTask
            implements Runnable
    {
        private CloseableHttpClient client;
        private Config config;
        private String actionsId;

        public ClickTask(CloseableHttpClient client, Config config, String actionsId)
        {
            this.client = client;
            this.config = config;
            this.actionsId = actionsId;
        }

        public void run()
        {
            try
            {
                Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
                Calendar calendar = Calendar.getInstance();

                URI uri = new URIBuilder(MTAUtils.URL).setParameter("dm", "127.0.0.1").setParameter("pvi", this.config.getId()).setParameter("si", MTAUtils.SI).setParameter("url", "/" + this.actionsId).setParameter("arg", "").setParameter("ty", "0").setParameter("rdm", "").setParameter("rurl", "").setParameter("rarg", "").setParameter("adt", "").setParameter("r2", MTAUtils.SID).setParameter("scr", (int)screensize.getWidth() + "x" + (int)screensize.getHeight()).setParameter("scl", Toolkit.getDefaultToolkit().getScreenResolution() + "-bit").setParameter("lg", Locale.getDefault().toString().replace("_", "-").toLowerCase()).setParameter("tz", -(calendar.get(15) + calendar.get(16)) / 60000 / 60 + "").setParameter("ext", "version=2.0.14").setParameter("random", System.currentTimeMillis() + "").build();

                HttpGet get = new HttpGet(uri);

                HttpResponse response = this.client.execute(get);
                EntityUtils.consume(response.getEntity());
            }
            catch (Exception localException) {}
        }
    }
}
