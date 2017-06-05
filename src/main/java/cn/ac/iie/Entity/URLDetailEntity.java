package cn.ac.iie.Entity;

import java.util.List;

public class URLDetailEntity {
	
	private String g_id;			// url ID
	
	private String url;				// 外链 原文件获取
	
	private long u_ch_id;			// 用户ID 原文件获取
	
	private long m_chat_room;		// 群id 原文件获取
	
	private long m_publish_time;	//发布消息时间  原文件获取
	
	private long m_insert_time;		// 插入数据库时间  空值
	
	private String u_send_ip;		// 发送ip 原文件获取
	
	private String m_content;
	
	

	private int u_loc_province;		// 省  原文件获取
	
	private int u_loc_city;		// 市、县  原文件获取
	private int u_loc_county;		// 市、县  原文件获取
	
	private String domain;			// url域名 原文件获取
	
	private String url_title;		// 外链标题  爬虫获取
	
	private String url_content;		// 外链内容  爬虫获取
	
	private List<Long> t_id;		// 命中主题列表  默认空值
	
	private List<Long> tp_id;		// 命中专题列表  默认赋空值ֵ
	
	private List<Long> rules;		// 命中规则列表  默认赋空值
	
	private int u_is_harm;		// 是否有害  默认0
	
	private int u_is_dispose;	// 是否处置  默认0
	
	private int u_is_target;	// 是否命中  默认0

	private int m_dom_for;		// 境内外
	
	private int m_country_code;	// 国家代码
	
	private String u_name; 
	
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	private int crawlercount;		// 爬取次数  爬虫内用，返回网闸内的json文件不含
	private String crawler_fail_reason;// 失败原因  爬虫内用，返回网闸内的json文件不含
	private long crawler_last_process_time;// 上次爬取时间  爬虫内用，返回网闸内的json文件不含
	public String getG_id() {
		return g_id;
	}
	public void setG_id(String g_id) {
		this.g_id = g_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getU_ch_id() {
		return u_ch_id;
	}
	public void setU_ch_id(long u_ch_id) {
		this.u_ch_id = u_ch_id;
	}
	public long getM_chat_room() {
		return m_chat_room;
	}
	public void setM_chat_room(long m_chat_room) {
		this.m_chat_room = m_chat_room;
	}
	public long getM_publish_time() {
		return m_publish_time;
	}
	public void setM_publish_time(long m_publish_time) {
		this.m_publish_time = m_publish_time;
	}
	public long getM_insert_time() {
		return m_insert_time;
	}
	public void setM_insert_time(long m_insert_time) {
		this.m_insert_time = m_insert_time;
	}
	public String getU_send_ip() {
		return u_send_ip;
	}
	public void setU_send_ip(String u_send_ip) {
		this.u_send_ip = u_send_ip;
	}
	public String getM_content() {
		return m_content;
	}
	public void setM_content(String m_content) {
		this.m_content = m_content;
	}
	public int getU_loc_province() {
		return u_loc_province;
	}
	public void setU_loc_province(int u_loc_province) {
		this.u_loc_province = u_loc_province;
	}
	public int getU_loc_county() {
		return u_loc_county;
	}
	public void setU_loc_county(int u_loc_county) {
		this.u_loc_county = u_loc_county;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getUrl_title() {
		return url_title;
	}
	public void setUrl_title(String url_title) {
		this.url_title = url_title;
	}
	public String getUrl_content() {
		return url_content;
	}
	public void setUrl_content(String url_content) {
		this.url_content = url_content;
	}
	public List<Long> getT_id() {
		return t_id;
	}
	public void setT_id(List<Long> t_id) {
		this.t_id = t_id;
	}
	public List<Long> getTp_id() {
		return tp_id;
	}
	public void setTp_id(List<Long> tp_id) {
		this.tp_id = tp_id;
	}
	public List<Long> getRules() {
		return rules;
	}
	public void setRules(List<Long> rules) {
		this.rules = rules;
	}
	public int getU_is_harm() {
		return u_is_harm;
	}
	public void setU_is_harm(int u_is_harm) {
		this.u_is_harm = u_is_harm;
	}
	public int getU_is_dispose() {
		return u_is_dispose;
	}
	public void setU_is_dispose(int u_is_dispose) {
		this.u_is_dispose = u_is_dispose;
	}
	public int getU_is_target() {
		return u_is_target;
	}
	public void setU_is_target(int u_is_target) {
		this.u_is_target = u_is_target;
	}
	public int getM_dom_for() {
		return m_dom_for;
	}
	public void setM_dom_for(int m_dom_for) {
		this.m_dom_for = m_dom_for;
	}
	public int getM_country_code() {
		return m_country_code;
	}
	public void setM_country_code(int m_country_code) {
		this.m_country_code = m_country_code;
	}
	public int getCrawlercount() {
		return crawlercount;
	}
	public void setCrawlercount(int crawlercount) {
		this.crawlercount = crawlercount;
	}
	public String getCrawler_fail_reason() {
		return crawler_fail_reason;
	}
	public void setCrawler_fail_reason(String crawler_fail_reason) {
		this.crawler_fail_reason = crawler_fail_reason;
	}
	public long getCrawler_last_process_time() {
		return crawler_last_process_time;
	}
	public void setCrawler_last_process_time(long crawler_last_process_time) {
		this.crawler_last_process_time = crawler_last_process_time;
	}
	
	public int getU_loc_city() {
		return u_loc_city;
	}
	public void setU_loc_city(int u_loc_city) {
		this.u_loc_city = u_loc_city;
	}
	@Override
	public String toString() {
		return "URLDetailEntity [g_id=" + g_id + ", url=" + url + ", u_ch_id=" + u_ch_id + ", m_chat_room="
				+ m_chat_room + ", m_publish_time=" + m_publish_time + ", m_insert_time=" + m_insert_time
				+ ", u_send_ip=" + u_send_ip + ", m_content=" + m_content + ", u_loc_province=" + u_loc_province
				+ ", u_loc_city=" + u_loc_city + ", u_loc_county=" + u_loc_county + ", domain=" + domain
				+ ", url_title=" + url_title + ", url_content=" + url_content + ", t_id=" + t_id + ", tp_id=" + tp_id
				+ ", rules=" + rules + ", u_is_harm=" + u_is_harm + ", u_is_dispose=" + u_is_dispose + ", u_is_target="
				+ u_is_target + ", m_dom_for=" + m_dom_for + ", m_country_code=" + m_country_code + ", u_name=" + u_name
				+ ", crawlercount=" + crawlercount + ", crawler_fail_reason=" + crawler_fail_reason
				+ ", crawler_last_process_time=" + crawler_last_process_time + "]";
	}
	
}
