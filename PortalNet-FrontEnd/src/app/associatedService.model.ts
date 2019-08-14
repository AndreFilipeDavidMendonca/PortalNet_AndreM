export class AssociatedService {
    public id?: number;
    public clientId: number;
    public employeeId: number;
    public serviceName: string;
    public installationAddress: string;
    public postalCode: string;
    public locality: string;
    public alterationDate: string;
    public contractEndDate: string;
    public workerNumber: number;
    public monthlyPay: number;

    constructor(id: number, clientId: number, serviceName: string, 
        installationAddress: string, postalCode: string, locality: string, 
        alterationDate: string, contractEndDate: string, workerNumber: number, monthlyPay: number) {
            this.id = id;
            this.clientId = clientId;
            this.serviceName = serviceName;
            this.installationAddress = installationAddress;
            this.postalCode = postalCode;
            this.locality = locality;
            this.alterationDate = alterationDate;
            this.contractEndDate = contractEndDate;
            this.workerNumber = workerNumber;
            this.monthlyPay = monthlyPay;

        }
}