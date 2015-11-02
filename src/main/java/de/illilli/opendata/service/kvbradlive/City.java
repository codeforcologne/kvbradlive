package de.illilli.opendata.service.kvbradlive;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

/**
 * <pre>
 * &lt;city 
 *   uid="14" 
 *   lat="50.9429" 
 *   lng="6.95649" 
 *   zoom="12" 
 *   maps_icon="norisbike"
 *   alias="koeln" 
 *   break="0" 
 *   name="Köln"&gt;
 * </pre>
 * 
 */
public class City {

	List<Place> place;
	int uid;
	double lat;
	double lng;
	int zoom;
	String maps_icon;
	String alias;
	int _break;
	String name;

	@JacksonXmlElementWrapper(useWrapping = false)
	public List<Place> getPlace() {
		return place;
	}

	public void setPlace(List<Place> place) {
		this.place = place;
	}

	@XmlAttribute
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	@XmlAttribute
	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	@XmlAttribute
	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	@XmlAttribute
	public int getZoom() {
		return zoom;
	}

	public void setZoom(int zoom) {
		this.zoom = zoom;
	}

	@XmlAttribute
	public String getMaps_icon() {
		return maps_icon;
	}

	public void setMaps_icon(String maps_icon) {
		this.maps_icon = maps_icon;
	}

	@XmlAttribute
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@XmlAttribute
	public int getBreak() {
		return _break;
	}

	public void setBreak(int bread) {
		this._break = bread;
	}

	@XmlAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "City [place=" + place + ", uid=" + uid + ", lat=" + lat
				+ ", lng=" + lng + ", zoom=" + zoom + ", maps_icon="
				+ maps_icon + ", alias=" + alias + ", _break=" + _break
				+ ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _break;
		result = prime * result + ((alias == null) ? 0 : alias.hashCode());
		long temp;
		temp = Double.doubleToLongBits(lat);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(lng);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((maps_icon == null) ? 0 : maps_icon.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((place == null) ? 0 : place.hashCode());
		result = prime * result + uid;
		result = prime * result + zoom;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (_break != other._break)
			return false;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		if (Double.doubleToLongBits(lat) != Double.doubleToLongBits(other.lat))
			return false;
		if (Double.doubleToLongBits(lng) != Double.doubleToLongBits(other.lng))
			return false;
		if (maps_icon == null) {
			if (other.maps_icon != null)
				return false;
		} else if (!maps_icon.equals(other.maps_icon))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (place == null) {
			if (other.place != null)
				return false;
		} else if (!place.equals(other.place))
			return false;
		if (uid != other.uid)
			return false;
		if (zoom != other.zoom)
			return false;
		return true;
	}

}
