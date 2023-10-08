/**
 * @author Bledar B
 */
package org.phones.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PhoneApiInfoDto {

    private Integer id;

    @NotBlank(message = "Phone ID name is mandatory")
    @Size(min = 5, max = 200, message = "Phone ID name must be between 5 and 200 characters")
    private String phoneId;

    @Column(name = "brand")
    @NotBlank(message = "brand name is mandatory")
    @Size(min = 1, max = 200, message = "brand name must be between 1 and 200 characters")
    private String brand;

    @Column(name = "technology")
    @NotBlank(message = "technology name is mandatory")
    @Size(min = 1, max = 200, message = "technology name must be between 1 and 200 characters")
    private String technology;

    @Column(name = "gprs")
    @NotBlank(message = "gprs name is mandatory")
    @Size(min = 1, max = 200, message = "gprs name must be between 1 and 200 characters")
    private String gprs;

    @Column(name = "edge")
    @NotBlank(message = "edge name is mandatory")
    @Size(min = 1, max = 200, message = "edge name must be between 1 and 200 characters")
    private String edge;

    @Column(name = "announced")
    @NotBlank(message = "announced name is mandatory")
    @Size(min = 1, max = 200, message = "announced name must be between 1 and 200 characters")
    private String announced;

    @Column(name = "status")
    @NotBlank(message = "status name is mandatory")
    @Size(min = 1, max = 200, message = "status name must be between 1 and 200 characters")
    private String status;

    @Column(name = "dimensions")
    @NotBlank(message = "dimensions name is mandatory")
    @Size(min = 1, max = 200, message = "dimensions name must be between 1 and 200 characters")
    private String dimensions;

    @Column(name = "weight")
    @NotBlank(message = "weight name is mandatory")
    @Size(min = 1, max = 200, message = "weight name must be between 1 and 200 characters")
    private String weight;

    @Column(name = "sim")
    @NotBlank(message = "sim name is mandatory")
    @Size(min = 1, max = 200, message = "sim name must be between 1 and 200 characters")
    private String sim;

    @Column(name = "display_type")
    @NotBlank(message = "display type name is mandatory")
    @Size(min = 1, max = 200, message = "display type name must be between 1 and 200 characters")
    private String displayType;

    @Column(name = "display_size")
    @NotBlank(message = "display size name is mandatory")
    @Size(min = 1, max = 200, message = "display size name must be between 1 and 200 characters")
    private String displaySize;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(String phoneId) {
        this.phoneId = phoneId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getGprs() {
        return gprs;
    }

    public void setGprs(String gprs) {
        this.gprs = gprs;
    }

    public String getEdge() {
        return edge;
    }

    public void setEdge(String edge) {
        this.edge = edge;
    }

    public String getAnnounced() {
        return announced;
    }

    public void setAnnounced(String announced) {
        this.announced = announced;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSim() {
        return sim;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    public String getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(String displaySize) {
        this.displaySize = displaySize;
    }
}
