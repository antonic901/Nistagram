package nistagram.postservice.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nistagram.postservice.dto.LocationDTO;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Location {
	
	@Column
	private String city;

	@Column
	private String city_district;

	@Column
	private String country;

	@Column
	private String country_code;

	@Column
	private String county;
	
	@Column
	private String house_number;

	@Column
	private String neighbourhood;

	@Column
	private int postcode;

	@Column
	private String road;

	@Column
	private String state;

	@Column
	private String village;

	@Column
	private String display_name;

	public Location(LocationDTO locationDTO) {
		this.city = locationDTO.getAddress().getCity();
		this.city_district = locationDTO.getAddress().getCity_district();
		this.country = locationDTO.getAddress().getCountry();
		this.country_code = locationDTO.getAddress().getCountry_code();
		this.county = locationDTO.getAddress().getCounty();
		this.house_number = locationDTO.getAddress().getHouse_number();
		this.neighbourhood = locationDTO.getAddress().getNeighbourhood();
		this.postcode = locationDTO.getAddress().getPostcode();
		this.road = locationDTO.getAddress().getRoad();
		this.state = locationDTO.getAddress().getState();
		this.village = locationDTO.getAddress().getVillage();
		this.display_name = locationDTO.getDisplay_name();
	}
	
	
	
}
