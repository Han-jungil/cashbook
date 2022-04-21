package vo;

public class TagCategory {
	private String tag;
	private int cashbookNo;
	private int cash;
	private String kind;
	private String memo;
	private String cashDate;
	@Override
	public String toString() {
		 return "cashbook [cashbookNo=" + cashbookNo + ", cashDate=" + cashDate + ", kind=" + kind + ", memo=" + memo
	                + ", tag=" + tag + ", cash=" + cash + "]";
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getCashbookNo() {
		return cashbookNo;
	}
	public void setCashbookNo(int cashbookNo) {
		this.cashbookNo = cashbookNo;
	}
	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getCashDate() {
		return cashDate;
	}
	public void setCashDate(String cashDate) {
		this.cashDate = cashDate;
	}
	
}
