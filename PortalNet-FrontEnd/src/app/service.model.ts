export class Service {
    public serviceID: number;
    public name: string;
    public internet: number;
    public tv: string;
    public mobilePhone: number;
    public phone: number;
    public price: number;
    public loyalty: string;
    public creationDate: string;
    public status: boolean;
    public imgUrl: string;
    
    constructor (serviceID: number, name: string, internet: number, tv: string, mobilePhone: number, phone: number, price: number, loyalty: string, creationDate: string, status: boolean, imgUrl: string)  {
        this.imgUrl = imgUrl;
        this.serviceID = serviceID;
        this.name = name;
        this.internet = internet;
        this.tv = tv;
        this.mobilePhone = mobilePhone;
        this.phone = phone;
        this.price = price;
        this.loyalty = loyalty;
        this.creationDate = creationDate;
        this.status = status;
    }
}
