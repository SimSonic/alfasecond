/*
 * Api Documentation
 * Api Documentation
 *
 * The version of the OpenAPI document: 1.0
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.lampa.alfabattle.second.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.lampa.alfabattle.second.dtokafka.Payment;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * PaymentCategoryInfo
 */
@JsonPropertyOrder({
        PaymentCategoryInfo.JSON_PROPERTY_MAX,
        PaymentCategoryInfo.JSON_PROPERTY_MIN,
        PaymentCategoryInfo.JSON_PROPERTY_SUM
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2020-06-27T15:06:47.007876100+03:00[Europe/Moscow]")
public class PaymentCategoryInfo {
    public static final String JSON_PROPERTY_MAX = "max";
    private BigDecimal max;

    public static final String JSON_PROPERTY_MIN = "min";
    private BigDecimal min;

    public static final String JSON_PROPERTY_SUM = "sum";
    private BigDecimal sum;

    @JsonIgnore
    private int useCount = 1;

    @JsonIgnore
    private Integer categoryId;


    public PaymentCategoryInfo max(BigDecimal max) {

        this.max = max;
        return this;
    }

    /**
     * Get max
     *
     * @return max
     **/
    @ApiModelProperty(value = "")
    @JsonProperty(JSON_PROPERTY_MAX)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

    public BigDecimal getMax() {
        return max;
    }


    public void setMax(BigDecimal max) {
        this.max = max;
    }


    public PaymentCategoryInfo min(BigDecimal min) {

        this.min = min;
        return this;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Get min
     *
     * @return min
     **/
    @ApiModelProperty(value = "")
    @JsonProperty(JSON_PROPERTY_MIN)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

    public BigDecimal getMin() {
        return min;
    }


    public void setMin(BigDecimal min) {
        this.min = min;
    }


    public int getUseCount() {
        return useCount;
    }

    public void setUseCount(int useCount) {
        this.useCount = useCount;
    }

    public PaymentCategoryInfo sum(BigDecimal sum) {

        this.sum = sum;
        return this;
    }

    /**
     * Get sum
     *
     * @return sum
     **/
    @ApiModelProperty(value = "")
    @JsonProperty(JSON_PROPERTY_SUM)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

    public BigDecimal getSum() {
        return sum;
    }


    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PaymentCategoryInfo paymentCategoryInfo = (PaymentCategoryInfo) o;
        return Objects.equals(this.categoryId, paymentCategoryInfo.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PaymentCategoryInfo {\n");
        sb.append("    max: ").append(toIndentedString(max)).append("\n");
        sb.append("    min: ").append(toIndentedString(min)).append("\n");
        sb.append("    sum: ").append(toIndentedString(sum)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    public void update(Payment payment) {
        this.max = payment.getAmount().max(this.max);
        this.min = payment.getAmount().min(this.min);
        this.sum = this.sum.add(payment.getAmount());
        this.useCount++;
    }
}

