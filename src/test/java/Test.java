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
import java.util.Map;
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

        /*HttpClient httpClient = null;
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
        }*/


        /*String word="找帮";
        int page=0;
        String url= null;
        try {
            url = "http://appc.baidu.com/as?uid=YO2fi_il2ul3u287gO-oilicB8gO8v8fgiHh8laLHiqUuSiTga2E8_aa28_4av8V3dlqC&tn=native&abi=armeabi-v7a&from=1009306c&ver=16794028&is_support_webp=true&cct=qivtkjihetggRHi86iS3kliheug_MHf3odfqA&network=WF&cen=cuid_cut_cua_uid&platform_version_id=19&province=qivtkjihetggRHi86iS3kliheug_MHf3odfqA&st=10a001&apn=&native_api=1&psize=3&usertype=2&cll=ga24N_uN2tjpRvaEgPslfgadv8LqA&operator=460018&country=CN&pkname=com.baidu.appsearch&gms=false&pu=cua%40_avLC_aE-i4qywoUfpw1zyPLXio5uL8bxLqqC%2Cosname%40baiduappsearch%2Cctv%401%2Ccfrom%401000561u%2Ccuid%40YO2fi_il2ul3u287gO-oilicB8gO8v8fgiHh8laLHi67uviJlav8i_aL28_Aa285ga2VtqqHB%2Ccut%40lpw2qfy3AIYQuB8lz9v6iya8XizJuL8DjNL1j5J4mzqcC%2Ccsrc%40app_box_txt&word="+ URLEncoder.encode(word, "UTF-8")+"&language=zh&disp=4.4.049.P0.8297-T01&&crid=1510718613999&native_api=1&pn="+page+"&f=search&bannert=26%4027%4028%4029%4030%4032%4043&rqt=rty&ptl=hp ";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
/*

        int i=23944466;
        String url="http://m.baidu.com/appsrv?country=CN&ver=16785288&cll=gPslfgadv9gzivij_h228_ODvfwqC&pc_channel=&pu=cuid%40_8BYig89H8lGaSt3laB1a0aeBaYa82iOjuvAi0ai286uuviJ0OvRi_OSBf_luvi9ga2Vttw6B%2Ccut%400t3_N0tN2iyqPXiDzuD58gIVQMlykSO6A%2Cctv%401%2Ccua%40_a-qi4uq-igBNE6lI5me6NIy2I_UhvCrSdNqA%2Cosname%40baiduappsearch%2Ccfrom%401000561u&platform_version_id=26&abi=armeabi-v7a&usertype=0&language=zh&gms=true&cen=cuid_cut_cua_uid&operator=460077&network=WF&pkname=com.baidu.appsearch&is_support_webp=true&uid=_8BYig89H8lGaSt3laB1a0aeBaYa82iOjuvAi0ai28qRuHf3gu2VfjOQ2iggaviJ3dFqC&firstdoc=&psize=3&cct=qivtkjihetggRHi86iS3kliheug_MHf3odfqA&native_api=1&action=detail&from=1000561u&apn=&&native_api=1&docid="+i+"&f=homepage%40oneclickinstallold%401%402%40source%2BNATURAL%40boardid%2B20149%40pos%2B2%40searchid%2B223986765735699215%40terminal_type%2Bclient%40spec%2B0";

*/

//String url = "http://gdown.baidu.com/data/wisegame/8f65ab62a9ef7392/zhaobangshoujifangdao_56.apk";
        /*String word="定位";
        try {
            word = URLEncoder.encode(word, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "http://m5.qq.com/pcsoftmgr/searchapp.htm?keyword="+word+"&searchScene=1&pageSize=20&contextData=MjA=";
*/
        String url = "http://m5.qq.com/app/getappdetail.htm?pkgName=com.jlzb.android&sceneId=0";
        String result = "";

        HttpClient httpClient = null;
        try {
            httpClient = new DefaultHttpClient();
            URI uri = new URI(url);
            HttpGet httpget = new HttpGet(uri);

            HttpResponse response = httpClient.execute(httpget);

            result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(httpClient != null)
                httpClient.getConnectionManager().shutdown();
        }



        /*BufferedReader bufferedReader = null;
        InputStream urlStream = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();

//            connection.setRequestProperty("sign", "XGBWAEOl5peoqBhbz04RIw==");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36 QBCore/3.43.815.400 QQBrowser/9.0.2524.400");
            connection.setRequestProperty("Host", "m5.qq.com");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Accept-Encoding", "gzip");



            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            //Map<String, List<String>> map = connection.getHeaderFields();
            // 定义 BufferedReader输入流来读取URL的响应
//            in = new BufferedReader(new InputStreamReader(
//                    connection.getInputStream()));

                urlStream = connection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(urlStream,"utf-8"));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(bufferedReader != null)
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                if(urlStream != null)
                    try {
                        urlStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }*/

        System.out.println(result);
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
