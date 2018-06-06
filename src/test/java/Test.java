import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jlzb.storedownload.http.VivoHttpRequest;
import com.jlzb.storedownload.utils.StringUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class Test {

    public static void main(String[] args) {
        /*HttpRequest httpDownloader = new HttpDownloader(device);
        httpDownloader.req(downloadurl);*/

        /*String url = "http://userdata.unking.cn/userdata_oss/upfiles/userimage/";

        File sdfile = new File("C:\\Users\\Administrator\\Desktop\\store\\sd.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(sdfile));
            String str = null;
            while((str = reader.readLine()) != null) {
                if(!str.contains("NULL")) {
                    String[] strs = str.split("\t");
                    System.out.println(strs[1]+ "     " + url+getDirectoryById(strs[1]) + "/"+strs[0]);
                }
            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/



       /* try {
            HttpClient httpClient = new DefaultHttpClient();
            URI uri = null;
            uri = new URI("https://www.ipip.net/ip.html");
            HttpGet httpget = new HttpGet(uri);
            HttpResponse response = httpClient.execute(httpget);
            String s = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
           // System.out.println(s);

            Document parse = Jsoup.parse(s);

            Elements select = parse.select("input[autocomplete=off]");
            System.out.println(select.get(0).attr("value"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
/*
        File rulefile = new File("C:\\Users\\Administrator\\Desktop\\store\\runrule.xml");
        SAXReader reader = new SAXReader();
        try {
            org.dom4j.Document document =  reader.read(rulefile);
            Element element = document.getRootElement();
            Element configs = element.element("configs");
            List<Element> config = configs.elements("config");
            System.out.println(configs.elements("config").size());
            for (int i = 0; i < config.size(); i++) {
                System.out.println(config.get(i).element("store").getText());
                System.out.println(config.get(i).element("runcount").getText());
                System.out.println(config.get(i).element("keyword").getText());
                System.out.println(config.get(i).element("packagename").getText());
            }

            Element ischangeip = element.element("ischangeip");
            System.out.println(ischangeip.element("adslswitch").getText());
            System.out.println(ischangeip.element("connectname").getText());
            System.out.println(ischangeip.element("adsluser").getText());
            System.out.println(ischangeip.element("adslpass").getText());
        } catch (DocumentException e) {
            e.printStackTrace();
        }*/

       // System.out.println(StringUtil.getUUID());
        //try {

        /*String url="http://info.appstore.vivo.com.cn/port/package/?screensize=1080_1920&app_version=1011&density=3.0&nt=WIFI&need_comment=0&elapsedtime=688297156&cp=280&an=8.0.0target=local&cs=0&module_id=8&pictype=webp&u=1234567890&av=26&page_index=1&imei=867779030125927&model=ALP-AL00&content_complete=1&id=57726";*/
            /*String word="手机定位";
            int page=1;
            String url="http://search.appstore.vivo.com.cn/port/packages/?nt=WIFI&model=Coolpad+8297-T01&cfrom=2&density=2.0&page_index="+page+"&screensize=720_1280&imei=867660022602745&build_number=4.4.049.P0.8297-T01&app_version=1063&av=19&apps_per_page=20&cs=0&sshow=110&id=0&u=90014a483847316505076c91bc695200&pictype=webp&elapsedtime=1440738&an=4.4.4&target=local&key="+word+"&s=2%7C1111905542";



            URL realUrl = new URL(url);
        // 打开和URL之间的连接
        URLConnection connection = realUrl.openConnection();

        connection.setRequestProperty("accept-encoding", "gzip");
        connection.setRequestProperty("Host", "pl.appstore.vivo.com.cn");
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2) Gecko/20100115 Firefox/3.6");

        // 建立实际的连接
            connection.connect();


        String result = "";
        InputStream urlStream = new GZIPInputStream(connection.getInputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(urlStream, "utf-8"));
        String line;

        while ((line = in.readLine()) != null) {
            result += line;
        }

        in.close();

            System.out.println(new String(result.getBytes(), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        /*String word = "定位";
        String url = "http://a.vmall.com/uowap/index?method=internal.getTabDetail&maxResults=25&reqPageNum=1&serviceType=13&uri=searchApp" + URLEncoder.encode("|") + URLEncoder.encode(word);
        HttpGet httpGet = new HttpGet(url);

        CloseableHttpClient client = HttpClients.createDefault();
        try {
            CloseableHttpResponse response = client.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpGet.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();

            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        /*try {

            String word = URLEncoder.encode("定位", "UTF-8");
            System.out.println(word);
            //String url = "https://app.market.xiaomi.com/apm/search?channel=market_100_1_android&clientId=0726c9c46e155f4fba91b3d9a977b1f3&co=CN&densityScaleFactor=3.0&imei=1a40e0d8960b4dd41ec1a8d6f517831b&keyword="+word+"&la=zh&marketVersion=147&model=vivo+X20A&os=eng.compil.20180515.183659&page=0&ref=input&resolution=1080*1920&sdk=25&session=2jmj7l5rSw0yVb_v";

            String url = "https://app.market.xiaomi.com/apm/download/9900?channel=market_100_1_android&clientId=0726c9c46e155f4fba91b3d9a977b1f3&co=CN&densityScaleFactor=3.0&imei=1a40e0d8960b4dd41ec1a8d6f517831b&la=zh&marketVersion=147&model=vivo+X20A&net=wifi&os=eng.compil.20180515.183659&ref=search&refPosition=17&resolution=1080*1920&sdk=25&session=2jmj7l5rSw0yVb_v";

            HttpGet httpGet = new HttpGet(url);
            HttpClient client = HttpClients.createDefault();
            HttpResponse response = client.execute(httpGet);

            HttpEntity entity = response.getEntity();
            String result  = EntityUtils.toString(entity, "utf-8");
            //EntityUtils.consume(entity);

            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }*/

        HttpClient httpClient = null;
        try {
            File f = new File("C:\\Users\\Administrator\\Desktop\\store\\123123123.apk");
            if (!f.exists())
                f.createNewFile();

            httpClient = new DefaultHttpClient();

            URI uri = new URI("http://f6.market.xiaomi.com/download/AppStore/0a820c4c4f2fe430038be73f9afaa8ca7a6f4f439");
            HttpGet httpGet = new HttpGet(uri);

            HttpResponse response = httpClient.execute(httpGet);

            response.getEntity().writeTo(new FileOutputStream(f));



            //每次下载完休息2秒
            //Thread.sleep(RuleManage.rule.getSleeptime());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(httpClient != null)
                httpClient.getConnectionManager().shutdown();
        }

    }

    /**
     * 通过ID来获取一定规则的目录名
     *
     * @param id
     * @return
     */
    public static String getDirectoryById(String id) {
        int id_length = id.length();
        if (id_length < 10) {
            for (int i = 0; i < 10 - id_length; i++) {
                id = "0" + id;

            }
        }

        String dir = id.substring(0, 2) + "/" + id.substring(2, 4) + "/" + id.substring(4, 7) + "/"
                + id.substring(8, 10) + "/" + id.substring(7, 8);

        return dir;
    }

}
