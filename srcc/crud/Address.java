public class Address {
    private int addressId;
    private String area;
    private String city;
    private String state;
    private String district;
    private String pincode;
    private String addressType;

    public Address(int addressId, String area, String city, String state,
                   String district, String pincode, String addressType) {
        this.addressId = addressId;
        this.area = area;
        this.city = city;
        this.state = state;
        this.district = district;
        this.pincode = pincode;
        this.addressType = addressType;
    }

    // Getter & Setter
    public int getAddressId() { return addressId; }
    public void setAddressId(int addressId) { this.addressId = addressId; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }

    public String getAddressType() { return addressType; }
    public void setAddressType(String addressType) { this.addressType = addressType; }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + addressId +
                ", area='" + area + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", district='" + district + '\'' +
                ", pincode='" + pincode + '\'' +
                ", type='" + addressType + '\'' +
                '}';
    }
}
