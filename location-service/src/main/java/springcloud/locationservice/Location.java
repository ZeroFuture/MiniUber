package springcloud.locationservice;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Location {
  private long id;
  private double latitude;
  private double longitude;
  private LocalDateTime timeStamp;

  public Location() {}

  public Location(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
    this.timeStamp = LocalDateTime.now();
  }

  @JsonProperty
  public long getId() {
    return id;
  }

  @JsonProperty
  public double getLatitude() {
    return latitude;
  }

  @JsonProperty
  public double getLongitude() {
    return longitude;
  }

  @JsonProperty
  public LocalDateTime getTimeStamp() {
    return timeStamp;
  }

  @JsonProperty
  public String getGeoHash() {
    return GeoHashUtil.encode(latitude, longitude);
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public void setTimeStamp(LocalDateTime timeStamp) {
    this.timeStamp = timeStamp;
  }
}
