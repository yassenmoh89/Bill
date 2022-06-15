package core.model.setting;


import java.util.ArrayList;



public class Account {
	private int ledCodID=0;
	private String LedName="";
	private int trailCodID=0;
	private String trailName="";
	private String accName=""; 
	private int accCode=0; 
	private double debit=0.0; 
	private double credit=0.0; 
	private double tdebit=0.0;
	private double tcredit=0.0; // temp credit
	private String docNo=""; 
	private String voucherID=""; 
	private String nari="";
	private String flavour="";
	private Account accBean;
	private int acountID=0;
	private int serial=0;
	private int transID=0;
	private String transDate="";
	private boolean flag=false ;
	private String usrName="";
	
	public Account(String name, int code)
	{
		this.accountName=name;
		this.accCode=code;
	}
	
	public Account()
	{
	}
	
	
	public double getTdebit() {
		return tdebit;
	}
	public void setTdebit(double tdebit) {
		this.tdebit = tdebit;
	}
	public double getTcredit() {
		return tcredit;
	}
	public void setTcredit(double tcredit) {
		this.tcredit = tcredit;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getUsrName() {
		return usrName;
	}
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
	public int getSerial() {
		return serial;
	}
	public void setSerial(int serial) {
		this.serial = serial;
	}
	public int getTransID() {
		return transID;
	}
	public void setTransID(int transID) {
		this.transID = transID;
	}
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	private double hdebit=0.0; 
	private double hcredit=0.0;
	
	
	
	
	public double getHdebit() {
		return hdebit;
	}
	public void setHdebit(double hdebit) {
		this.hdebit = hdebit;
	}
	public double getHcredit() {
		return hcredit;
	}
	public void setHcredit(double hcredit) {
		this.hcredit = hcredit;
	}
	public int getAcountID() {
		return acountID;
	}
	public void setAcountID(int acountID) {
		this.acountID = acountID;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	private String accountName="";
	
		public Account getAccBean() {
		return accBean;
	}
	public void setAccBean(Account accBean) {
		this.accBean = accBean;
	}
		private ArrayList<Account> accList ; 
	    
	
	public ArrayList<Account> getAccList() {
		
		return accList;
	}
	public void setAccList(ArrayList<Account> accList) {
		this.accList = accList;
	}
	public String getFlavour() {
		return flavour;
	}
	public void setFlavour(String flavour) {
		this.flavour = flavour;
	}
	public String getNari() {
		return nari;
	}
	public void setNari(String nari) {
		this.nari = nari;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public int getAccCode() {
		return accCode;
	}
	public void setAccCode(int accCode) {
		this.accCode = accCode;
	}
	public double getDebit() {
		return debit;
	}
	public void setDebit(double debit) {
		this.debit = debit;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public String getVoucherID() {
		return voucherID;
	}
	public void setVoucherID(String voucherID) {
		this.voucherID = voucherID;
	}
	public int getTrailCodID() {
		return trailCodID;
	}
	public void setTrailCodID(int trailCodID) {
		this.trailCodID = trailCodID;
	}
	public String getTrailName() {
		return trailName;
	}
	public void setTrailName(String trailName) {
		this.trailName = trailName;
	}
	public int getLedCodID() {
		return ledCodID;
	}
	public void setLedCodID(int ledCodID) {
		this.ledCodID = ledCodID;
	}
	public String getLedName() {
		return LedName;
	}
	public void setLedName(String ledName) {
		LedName = ledName;
	}
	
	@Override
    public boolean equals(Object obj) {
            if(obj == null)
                    return false;
            if(!(obj instanceof Account))
                    return false;
            
            return ((Account)obj).getAccCode() == this.accCode;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        return hash * 31 + accName.hashCode();
    }

    @Override
    public String toString() {
            return accName;
    }
}
