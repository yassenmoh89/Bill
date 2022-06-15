package core.bill.common.bean;

import core.bill.common.message.MessageView;

public class GenericRole extends MessageView{
	
	private boolean hasSave ; 
	private boolean hasCreate ; 
	private boolean hasDelete ; 
	private boolean hasUpdate ; 
	private boolean hasView ; 
	private boolean hasPay ;
	private boolean hasPrint ; 
	private boolean hasSearch ;
	
	
	public boolean isHasSave() {
		return hasSave;
	}
	public boolean isHasCreate() {
		return hasCreate;
	}
	public boolean isHasDelete() {
		return hasDelete;
	}
	public boolean isHasUpdate() {
		return hasUpdate;
	}
	public boolean isHasView() {
		return hasView;
	}
	public boolean isHasPay() {
		return hasPay;
	}
	public boolean isHasPrint() {
		return hasPrint;
	}
	public boolean isHasSearch() {
		return hasSearch;
	}
	public void setHasSave(boolean hasSave) {
		this.hasSave = hasSave;
	}
	public void setHasCreate(boolean hasCreate) {
		this.hasCreate = hasCreate;
	}
	public void setHasDelete(boolean hasDelete) {
		this.hasDelete = hasDelete;
	}
	public void setHasUpdate(boolean hasUpdate) {
		this.hasUpdate = hasUpdate;
	}
	public void setHasView(boolean hasView) {
		this.hasView = hasView;
	}
	public void setHasPay(boolean hasPay) {
		this.hasPay = hasPay;
	}
	public void setHasPrint(boolean hasPrint) {
		this.hasPrint = hasPrint;
	}
	public void setHasSearch(boolean hasSearch) {
		this.hasSearch = hasSearch;
	} 
	
	

}
