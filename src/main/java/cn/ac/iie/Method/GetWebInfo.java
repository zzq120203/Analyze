package cn.ac.iie.Method;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.spreada.utils.chinese.ZHConverter;

import cn.ac.iie.Entity.WEBContentEntity;

public class GetWebInfo {
	static Logger log = Logger.getLogger(GetWebInfo.class.getName());
	private static AtomicLong url_filter_nr = new AtomicLong(0);
	
	
	public long getUrlFilterNr() {
        return url_filter_nr.get();
    }
//	public static WEBContentEntity GetText(String url){
//		WEBContentEntity title_content = new WEBContentEntity();
//		int count =0;
//		if(url.length() >5 && url.substring(0,4).equals("http") == false)
//			url= "http://" + url;
//		System.out.println("url:" + url);
//		int url_status_code ;
//		Connection connection = null;
//		Document document = null;
//		try {
//			 connection = Jsoup.connect(url).timeout(3000);
//			 connection.header("Content-Type", "application/json");
//			 url_status_code = connection.execute().statusCode();
//			 
//			if(url_status_code == 200)
//			{
//				document = connection.get();
//				for( count=0; count<2 ; count++){
//					if( (Pattern.compile("wxn.qq.com").matcher(url).find() ) == true)
//						title_content = GetWebInfo.GetWeiXinNews(document);
//					else if( ( Pattern.compile("weixin.qq.com").matcher(url).find() ) == true)
//						title_content = GetWebInfo.GetWeiXinGZHH(document);
//					else if( ( Pattern.compile(".qq.com").matcher(url).find() ) == true)
//						title_content = GetWebInfo.GetTecentNews(document);
//					else if( ( Pattern.compile(".ifeng.com").matcher(url).find() ) == true)
//						title_content = GetWebInfo.GetIFeng(document);
//					else if( ( Pattern.compile("tieba.baidu.com").matcher(url).find() ) == true)
//						title_content = GetWebInfo.GetBaiduTB(document);
//					else if( ( Pattern.compile(".people.com.cn").matcher(url).find() ) == true)
//						title_content = GetWebInfo.GetPeopleNews(document);
//					else if( ( Pattern.compile(".zhihu.com").matcher(url).find() ) == true)
//						title_content = GetWebInfo.GetZhiHu(document);
//					else {
//						title_content = GetWebInfo.GetTotal(document);
//					}
//					if(title_content.getTitle().startsWith("SocketTimeoutException") || title_content.getTitle().startsWith("UnknownHostException"))
//						continue;
//					return title_content;
//				}
//			}else{
//				url_filter_nr.incrementAndGet();
//				log.info("----------------totally url_filter_nr: " + url_filter_nr.get());
//				log.info("filter url :" + url + "[statuscode:" + url_status_code+"]");
//			}
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return title_content;
//	}
	// 对获取网页文本时捕获的异常进行处理
	public static String GetErrorInfoFromException(Exception e){
		try{
			StringWriter str = new StringWriter();
			PrintWriter pri = new PrintWriter(str);
			e.printStackTrace(pri);
			if(str.toString().contains("java.net.UnknownHostException"))
				return "UnknownHostException";
			if(str.toString().contains("java.net.SocketTimeoutException"))
				return "SocketTimeoutException";
			return "UnknownException";
		}catch(Exception ex){
			log.error("Exception info process error.");
			return "GetErrorInfoFromExcetpion";
		}
	}
	// 知乎 
	public static WEBContentEntity GetZhiHu(Document doc){
		// 初始化繁体转简体(简体不影响)
		ZHConverter to_simpl_chinese = ZHConverter.getInstance(ZHConverter.SIMPLIFIED);
		WEBContentEntity entity = new WEBContentEntity();
		try {
			Elements ele = doc.select("title");
			entity.setTitle(to_simpl_chinese.convert(ele.text()));
			ele = doc.select("div.rich_media_content");
			entity.setContent(to_simpl_chinese.convert(ele.text()));
			 
			if( entity.getTitle().length()<3 )	//if(entity.getContent().length() < 20 )
				return GetWebInfo.GetTotal(doc);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			entity.setTitle(GetWebInfo.GetErrorInfoFromException(e));
			entity.setContent(null);
			return entity;
		}
	}
	// 人民网
	public static WEBContentEntity GetPeopleNews(Document doc){
		// 初始化繁体转简体(简体不影响)
		ZHConverter to_simpl_chinese = ZHConverter.getInstance(ZHConverter.SIMPLIFIED);
		WEBContentEntity entity = new WEBContentEntity();
		try {
			Elements ele = doc.select("title");
			entity.setTitle(to_simpl_chinese.convert(ele.text()));
			
			ele = doc.select("div.box_con"); 	
			entity.setContent(to_simpl_chinese.convert(ele.text()));
			if( entity.getTitle().length()<3 )	//if(entity.getContent().length() < 20 )
				return GetWebInfo.GetTotal(doc);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			entity.setTitle(GetWebInfo.GetErrorInfoFromException(e));
			entity.setContent(null);
			return entity;
		}
	}
	// 百度贴吧 
	public static WEBContentEntity GetBaiduTB(Document doc){
		// 初始化繁体转简体(简体不影响)
		ZHConverter to_simpl_chinese = ZHConverter.getInstance(ZHConverter.SIMPLIFIED);
		WEBContentEntity entity = new WEBContentEntity();
		try { 
			
			Elements ele = doc.select("title");
			entity.setTitle(to_simpl_chinese.convert(ele.text()));
			ele = doc.select("div.p_postlist");
			entity.setContent(to_simpl_chinese.convert(ele.text()));
			
			if( entity.getTitle().length()<3 )	//if(entity.getContent().length() < 20 )
				return GetWebInfo.GetTotal(doc);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			entity.setTitle(GetWebInfo.GetErrorInfoFromException(e));
			entity.setContent(null);
			return entity;
		}
	}
		
	// 凤凰 	(不支持直播)
	public static WEBContentEntity GetIFeng(Document doc){
		// 初始化繁体转简体(简体不影响)
		ZHConverter to_simpl_chinese = ZHConverter.getInstance(ZHConverter.SIMPLIFIED);
		WEBContentEntity entity = new WEBContentEntity();
		try {
			
			Elements ele = doc.select("title");
			entity.setTitle(to_simpl_chinese.convert(ele.text()));
			// 一种网页格式 正文位置
			ele = doc.select("div.js_selection_area"); 
			// 另一种网页格式 正文位置
			if(ele.text().isEmpty())
				ele = doc.select("div.yc_con_l"); 
			// 财经 小报告(非直播)
			if(ele.text().isEmpty())
				ele = doc.select("div.artical"); 
			// 娱乐 专题
			if(ele.text().isEmpty()){
				ele = doc.select("div.fl.infoLefCon");
			}
			// 凤凰资讯的另一种网页格式
			if(ele.text().isEmpty()){
				ele = doc.select("div.textBox");
			}
			// 评论
			if(ele.text().isEmpty()){
				ele = doc.select("div.Acon.wrapperllb");
			}
			// 智库 台湾
			if(ele.text().isEmpty()){
				Element ele2 = doc.getElementById("artical"); 
				if(null != ele2 )
					entity.setContent(to_simpl_chinese.convert(ele.text()));
			}

			entity.setContent(to_simpl_chinese.convert(ele.text()));
			if( entity.getTitle().length()<3 )	//if(entity.getContent().length() < 20 )
				return GetWebInfo.GetTotal(doc);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			entity.setTitle(GetWebInfo.GetErrorInfoFromException(e));
			entity.setContent(null);
			return entity;
		}
	}
	// 腾讯新闻
	public static WEBContentEntity GetTecentNews(Document doc){
		// 初始化繁体转简体(简体不影响)
		ZHConverter to_simpl_chinese = ZHConverter.getInstance(ZHConverter.SIMPLIFIED);
		WEBContentEntity entity = new WEBContentEntity();
		try {
			
			Elements ele = doc.select("title");
			entity.setTitle(to_simpl_chinese.convert(ele.text()));
			ele = doc.select("div.qq_article"); 	// 上层div.qq_main
			entity.setContent(to_simpl_chinese.convert(ele.text()));
			// 今日话题     view.news.qq.com/....
			if(ele.text().isEmpty()){
				if( !(doc.select("div.daoyu").text().isEmpty()) && 
					!(doc.select("div.focus").text().isEmpty()) &&
					!(doc.select("div.article").text().isEmpty()) &&
					!(doc.select("div.summary").text().isEmpty()) ){
					String Info =  doc.select("div.daoyu").text() + " " +  doc.select("div.focus").text() 
						  + " " +  doc.select("div.article").text() + " " + doc.select("div.summary").text();
					entity.setContent(to_simpl_chinese.convert(Info));
				}else{
					return GetWebInfo.GetTotal(doc);
				}
			}			
			if( entity.getTitle().length()<3 )	//if(entity.getContent().length() < 20 )
				return GetWebInfo.GetTotal(doc);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			entity.setTitle(GetWebInfo.GetErrorInfoFromException(e));
			entity.setContent(null);
			return entity;
		}
	}
	
	public static WEBContentEntity GetWeiXinNews(Document doc){
		// 初始化繁体转简体(简体不影响)
		ZHConverter to_simpl_chinese = ZHConverter.getInstance(ZHConverter.SIMPLIFIED);
		WEBContentEntity entity = new WEBContentEntity();
		try {
			Elements ele = doc.select("title");
			entity.setTitle(to_simpl_chinese.convert(ele.text()));
			ele = doc.select("div.cont");
			entity.setContent(to_simpl_chinese.convert(ele.text()));
			
			if( entity.getTitle().length()<3 )	//if(entity.getContent().length() < 20 )
				return GetWebInfo.GetTotal(doc);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			entity.setTitle(GetWebInfo.GetErrorInfoFromException(e));
			entity.setContent(null);
			return entity;
		}
	}
	// 微信公众号
	public static WEBContentEntity GetWeiXinGZHH(Document doc){
		// 初始化繁体转简体(简体不影响)
		ZHConverter to_simpl_chinese = ZHConverter.getInstance(ZHConverter.SIMPLIFIED);
		WEBContentEntity entity = new WEBContentEntity();
		try {
//			Document doc = null;
//			Connection connection = Jsoup.connect(url);
//			connection.header("Content-Type", "application/json");
//			doc = Jsoup.connect(url).timeout(3000).get();
			
			Elements ele = doc.select("title");
			entity.setTitle(to_simpl_chinese.convert(ele.text()));
			ele = doc.select("div.rich_media_content");
			entity.setContent(to_simpl_chinese.convert(ele.text()));
			 
			if(entity.getTitle().length() < 3)	//if(entity.getContent().length() < 20 )
				return GetWebInfo.GetTotal(doc);
			return entity;
		} catch (Exception e) {
			log.error(e.getMessage());
			entity.setTitle(GetWebInfo.GetErrorInfoFromException(e));
			entity.setContent(null);
			return entity;
		}
	}
	// 通用爬取 获取title + 整个body
	public static WEBContentEntity GetTotal(Document doc) {
		// 始化繁体转简体(简体不影响)
		ZHConverter to_simpl_chinese = ZHConverter.getInstance(ZHConverter.SIMPLIFIED);
		WEBContentEntity entity = new WEBContentEntity();
		try {
			Elements ele = doc.select("title");
			entity.setTitle(to_simpl_chinese.convert(ele.text()));
			ele = doc.select("body");
			//System.out.println("body:" + ele.text());
			entity.setContent(to_simpl_chinese.convert(ele.text()));
			return entity;
		} catch (Exception e) {
			log.error(e.getMessage());
			//return GetWebInfo.GetErrorInfoFromException(e);
			entity.setTitle(GetWebInfo.GetErrorInfoFromException(e));
			entity.setContent(null);
			return entity;
		}
	}
}
