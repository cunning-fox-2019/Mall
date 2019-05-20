package com.seven.lib_model.model.user.mine;

import java.io.Serializable;

/**
 * Created by xxxxxxH on 2019/4/24.
 */

public class AddressEntity implements Serializable {

    /**
     * id : 1
     * contact_name : tyler1
     * contact_phone : 15680723021
     * province_id : 24
     * city_id : 271
     * district_id : 2723
     * address : he ping road
     * is_default : 0
     * province_name : 四川省
     * city_name : 成都市
     * district_name : 双流县
     */

    private int contact_id;
    private String contact_name;
    private String contact_phone;
    private String province_id;
    private String city_id;
    private String district_id;
    private String address;
    private String is_default;
    private String province_name;
    private String city_name;
    private String district_name;

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIs_default() {
        return is_default;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    @Override
    public String toString() {

        return "id>" + contact_id +
                "-----address>" + address +
                "-----contact_name>" + contact_name +
                "-----contact_phone>" + contact_phone +
                "-----选择还是查看";
    }
}
