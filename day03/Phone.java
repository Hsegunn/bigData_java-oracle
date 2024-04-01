package day03;

public interface Phone {
	public static final int version = 10;	// 상수필드
	String getNamefacture();	// 추상메서드
	String getOs();				// 추상메서드 cf. 추상클래스
}
class IPhone implements Phone{

	@Override
	public String getNamefacture() {
		return "Apple";
	}

	@Override
	public String getOs() {
		return "MacOS";
	}
	void filledInfo() {
		System.out.println(version);
	}
}
class Galaxy implements Phone{

	@Override
	public String getNamefacture() {
		return "SamSung";
	}

	@Override
	public String getOs() {
		return "Android";
	}
	void filledInfo() {
		System.out.println(version);
	}
}

class Main{
	public static void main(String[] args) {
		Phone p = new Galaxy();
		System.out.println(p.getNamefacture());
		System.out.println(p.getOs());
		
		p = new IPhone();
		System.out.println(p.getNamefacture());
		System.out.println(p.getOs());
	}
}