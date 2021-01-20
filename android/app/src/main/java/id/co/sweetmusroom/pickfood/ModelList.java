package id.co.sweetmusroom.pickfood;

public class ModelList {
    String id, title, desc, img;
    double harga, qty;

    public ModelList(){};

    public ModelList(String id, String title, String desc, double harga, String img, double qty){
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.harga = harga;
        this.img = img;
        this.qty = qty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }
}
