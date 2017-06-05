package cn.ac.iie.Entity;

public class URLInfoEntity {
	private String url_id;
	private String url;	//
	private long m_chat_room;//
	private long m_publish_time;//
	private long u_ch_id;//
	private String u_send_ip;//
	private int u_loc_county;//
	private int u_loc_city;//
	private int u_loc_province;//
	private String domain;	//
	
	private int m_dom_for;//
	private int m_country_code;//
	private String u_name;
	
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public URLInfoEntity(String url_id,String url,long m_chat_room,long u_ch_id,long m_publish_time,
			String u_send_ip,int u_loc_county,int u_loc_province, String domain,String u_name, int u_loc_city){
		this.url_id = url_id;
		this.url = url;
		this.m_chat_room = m_chat_room;
		this.u_ch_id = u_ch_id;
		this.m_publish_time = m_publish_time;
		this.u_send_ip = u_send_ip;
		this.u_loc_county = u_loc_county;
		this.u_loc_city = u_loc_city;
		this.u_loc_province=u_loc_province;
		this.domain = domain;
		this.u_name = u_name;
	}
	public URLInfoEntity(){
		
	}
	
	public String getUrl_id() {
		return url_id;
	}
	public void setUrl_id(String url_id) {
		this.url_id = url_id;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the m_chat_room
	 */
	public long getM_chat_room() {
		return m_chat_room;
	}
	/**
	 * @param m_chat_room the m_chat_room to set
	 */
	public void setM_chat_room(long m_chat_room) {
		this.m_chat_room = m_chat_room;
	}
	/**
	 * @return the u_ch_id
	 */
	public long getU_ch_id() {
		return u_ch_id;
	}
	/**
	 * @param u_ch_id the u_ch_id to set
	 */
	public void setU_ch_id(long u_ch_id) {
		this.u_ch_id = u_ch_id;
	}
	/**
	 * @return the m_publish_time
	 */
	public long getM_publish_time() {
		return m_publish_time;
	}
	/**
	 * @param m_publish_time the m_publish_time to set
	 */
	public void setM_publish_time(long m_publish_time) {
		this.m_publish_time = m_publish_time;
	}
	/**
	 * @return the u_send_ip
	 */
	public String getU_send_ip() {
		return u_send_ip;
	}
	/**
	 * @param u_send_ip the u_send_ip to set
	 */
	public void setU_send_ip(String u_send_ip) {
		this.u_send_ip = u_send_ip;
	}
	/**
	 * @return the u_loc_county
	 */
	public int getU_loc_county() {
		return u_loc_county;
	}
	/**
	 * @param u_loc_county the u_loc_county to set
	 */
	public void setU_loc_county(int u_loc_county) {
		this.u_loc_county = u_loc_county;
	}
	/**
	 * @return the u_loc_province
	 */
	public int getU_loc_province() {
		return u_loc_province;
	}
	/**
	 * @param u_loc_province the u_loc_province to set
	 */
	public void setU_loc_province(int u_loc_province) {
		this.u_loc_province = u_loc_province;
	}
	
	
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public int getM_dom_for(){
		return m_dom_for;
	}
	public void setM_dom_for(int m_dom_for){
		this.m_dom_for = m_dom_for;
	}
	
	public  int getM_country_code(){
		return m_country_code;
	}
	public void setM_country_code(int m_country_code){
		this.m_country_code = m_country_code;
	}
	public int getU_loc_city() {
		return u_loc_city;
	}
	public void setU_loc_city(int u_loc_city) {
		this.u_loc_city = u_loc_city;
	}
	
}
