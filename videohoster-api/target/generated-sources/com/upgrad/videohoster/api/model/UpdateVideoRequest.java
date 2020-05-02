package com.upgrad.videohoster.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UpdateVideoRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-05-03T02:38:29.613+05:30")

public class UpdateVideoRequest   {
  @JsonProperty("video")
  private String video = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("status")
  private String status = null;

  public UpdateVideoRequest video(String video) {
    this.video = video;
    return this;
  }

  /**
   * This is video url
   * @return video
  **/
  @ApiModelProperty(value = "This is video url")


  public String getVideo() {
    return video;
  }

  public void setVideo(String video) {
    this.video = video;
  }

  public UpdateVideoRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the video
   * @return name
  **/
  @ApiModelProperty(value = "Name of the video")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UpdateVideoRequest description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Description of the video
   * @return description
  **/
  @ApiModelProperty(value = "Description of the video")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public UpdateVideoRequest status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Status of the video
   * @return status
  **/
  @ApiModelProperty(value = "Status of the video")


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateVideoRequest updateVideoRequest = (UpdateVideoRequest) o;
    return Objects.equals(this.video, updateVideoRequest.video) &&
        Objects.equals(this.name, updateVideoRequest.name) &&
        Objects.equals(this.description, updateVideoRequest.description) &&
        Objects.equals(this.status, updateVideoRequest.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(video, name, description, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateVideoRequest {\n");
    
    sb.append("    video: ").append(toIndentedString(video)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

