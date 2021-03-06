import { Authority } from './authority';

export class Client {
    
    public clientId:number;
    public name: string;
    public address: string;
    public city: string;
    public postalCode: string;
    public nif: number;
    public birthDate: string;
    public email: string;
    public mobilePhone?: number;
    public phone?: number;
    public gender: string;
    public entryDate: string;
    public endContract: string;
    public numberOfServices: number;
    public monthlyPay: number;
    public status: boolean;
    public fraudulent: boolean;
    public password: string;
    public role: string;
    accessToken?: string;
    authorities: Authority[];
   

    
    constructor(clientId: number, name: string, address: string, city: string, postalCode: string, nif: number, birthDate: string, email: string, mobilePhone: number, 
                gender: string, entryDate: string, phone: number, endContract: string, numberOfServices: number, monthlyPay: number, 
                status: boolean, fraudulent: boolean, password: string, role: string){

            

        this.clientId = clientId;    
        this.name = name;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.nif = nif;
        this.birthDate = birthDate;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.phone = phone;
        this.gender = gender;
        this.entryDate = entryDate;
        this.endContract = endContract;
        this.numberOfServices = numberOfServices;
        this.monthlyPay = monthlyPay;
        this.status = status;
        this.fraudulent = fraudulent;
        this.password = password;
        this.role = role;
       
    }
}

                   