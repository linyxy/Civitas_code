package linyxy.civitas.model;

/*
 * Ŀǰ�ݲ�ʹ�õ�working Strategy��ʽ.
 */
public class WorkStrategy_Discard {
	
	String energyExpense;
	String productionInflu;
	String happyExpense;
	String skillInflu;
	String wuXinInflu;
	String menkan;
	String personInflu;
	
	public WorkStrategy_Discard(String energyExpense, String productionInflu,
			String happyExpense, String skillInflu, String wuXinInflu,
			String menkan, String personInflu) {
		super();
		this.energyExpense = energyExpense;
		this.productionInflu = productionInflu;
		this.happyExpense = happyExpense;
		this.skillInflu = skillInflu;
		this.wuXinInflu = wuXinInflu;
		this.menkan = menkan;
		this.personInflu = personInflu;
	}

	public WorkStrategy_Discard(String energyExpense, String productionInflu,
			String happyExpense, String skillInflu, String wuXinInflu,
			String personInflu) {
		super();
		this.energyExpense = energyExpense;
		this.productionInflu = productionInflu;
		this.happyExpense = happyExpense;
		this.skillInflu = skillInflu;
		this.wuXinInflu = wuXinInflu;
		this.personInflu = personInflu;
	}

	
}
