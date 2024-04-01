package day03;

public class PhoneMain {
	public static void main(String[] args) {
		Phone p = new Galaxy();		//인터페이스
		System.out.println(p.getNamefacture());
		System.out.println(p.getOs());
//		p.version
		
		p = new Phone() {
			@Override
			public String getNamefacture() {
				return "알뜰폰";
			}
			@Override
			public String getOs() {
				return "Android";
			}
		};
		
		p = new IPhone();
		System.out.println(p.getNamefacture());
		System.out.println(p.getOs());
	
	}

}