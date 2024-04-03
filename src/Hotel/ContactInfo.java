package Hotel;


public class ContactInfo {
    private int prefix;
    private int phone;
    private String web;

    public ContactInfo(int prefix, int phone, String web) {
        this.prefix = prefix;
        this.phone = phone;
        this.web = web;
    }

    public int getPrefix() {
        return prefix;
    }

    public int getPhone() {
        return phone;
    }

    public String getWeb() {
        return web;
    }
}