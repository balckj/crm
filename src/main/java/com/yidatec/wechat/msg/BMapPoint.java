package com.yidatec.wechat.msg;

/**
 * GPS-百度坐标系对象
 * @author j
 *
 */
public class BMapPoint {

	private String gps_Lon;
	private String gps_Lat;
	
	private String bmap_Lon;
	private String bmap_Lat;
	
//	public BMapPoint() {
//		gps_Lon = "";
//		gps_Lat = "";
//		bmap_Lon = "";
//		bmap_Lat = "";
//	}
	
	public String getStringGps() {
		return gps_Lon + "," + gps_Lat;
	}
	
	public String getGps_Lon() {
		return gps_Lon;
	}
	public void setGps_Lon(String gps_Lon) {
		this.gps_Lon = gps_Lon;
	}
	public String getGps_Lat() {
		return gps_Lat;
	}
	public void setGps_Lat(String gps_Lat) {
		this.gps_Lat = gps_Lat;
	}
	public String getBmap_Lon() {
		return bmap_Lon;
	}
	public void setBmap_Lon(String bmap_Lon) {
		this.bmap_Lon = bmap_Lon;
	}
	public String getBmap_Lat() {
		return bmap_Lat;
	}
	public void setBmap_Lat(String bmap_Lat) {
		this.bmap_Lat = bmap_Lat;
	}
	
	public String toString(){
		return getStringGps();
	}
}
