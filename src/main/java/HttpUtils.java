import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.intellij.util.containers.hash.HashMap;
import model.FANYIINFO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class HttpUtils {
    /** GET请求 */
    public static FANYIINFO doGet(String url) {

        FANYIINFO fanyiinfo=new FANYIINFO();
        InputStream in = null;
        ByteArrayOutputStream baos = null;
        try {
            URLConnection urlConnection = new URL(url).openConnection();
            HttpURLConnection httpUrlConnection = (HttpURLConnection) urlConnection;
            // 设置是否向httpUrlConnection输出，post请求，参数要放在http正文内，因此需要设为true,
            // 默认情况下是false;
            httpUrlConnection.setDoOutput(false);
            // 设置是否从httpUrlConnection读入，默认情况下是true;
            httpUrlConnection.setDoInput(true);
            // 忽略缓存
            httpUrlConnection.setUseCaches(false);
            // 设定请求的方法为"POST"，默认是GET
            httpUrlConnection.setRequestMethod("GET");
            httpUrlConnection.connect();

            // 获得响应状态
            int responseCode = httpUrlConnection.getResponseCode();

            if (HttpURLConnection.HTTP_OK == responseCode) {
                baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                in = httpUrlConnection.getInputStream();
                while ((len = in.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                    baos.flush();
                }

                fanyiinfo.setSuccess(true);
                fanyiinfo.setData(baos.toString("UTF-8"));
                fanyiinfo.setCode(200);
            } else {
                fanyiinfo.setSuccess(false);
                fanyiinfo.setMsg("请求异常");
                fanyiinfo.setCode(responseCode);
            }
        } catch (Exception e) {
            fanyiinfo.setSuccess(false);
            fanyiinfo.setMsg("请求异常");
            fanyiinfo.setCode(500);
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fanyiinfo;
    }

}
