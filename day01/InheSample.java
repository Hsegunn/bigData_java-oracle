package day01;

class Animal{
	int num = 10;
	String name;	// 멤버함수
	Animal(){
		System.out.println("Animal 생성자");
	}
	void setName(String name) {		// 멤버함수
		this.name = name;
	}
}
class Dog extends Animal{	// Animal 상속
	int num = 20;
	Dog(){
		System.out.println("Dog 생성자");
	}
	void sleep() {
		System.out.println(this.name+"잠");
	}
}
class HouseDog extends Dog{
	int num = 30;
	HouseDog(){
		
	}
	HouseDog(String name){			// 클래스 이름과 동일한 메서드 (생성자) , 출력이 없음
		this.setName(name);
		System.out.println("HouseDog 생성자");
	}
	void sleep() {		// 재정의(상속관계)
		System.out.println(this.name+"Sleeeeeep");
	}
	void sleep(int h) {	// 다중 정의
		System.out.println(this.name+"Sleeeeeep"+ h + "잠");
	}
	void numPrint() {
		System.out.printf("Anum: %d, Dnum: %d, Hnum: %d\n", super.num,super.num,this.num);
	}
}

public class InheSample {
	public static void main(String[] args) {
//		Dog dog = new Dog();	// dog 객체생성
//		dog.setName("고양이");
//		System.out.println(dog.name);
//		dog.sleep();
//		HouseDog hd = new HouseDog("바둑이");
//		hd.setName("ㅇㅇㅇ");
//		hd.sleep();
//		hd.sleep(3);	// 다중정의 메서드 호출 시 입력주의
		
//		System.out.println(hd.name);
//		HouseDog hd2 = new HouseDog();
//		hd2.numPrint();
		
		Animal a = new Animal();
		Dog d = new Dog();
		HouseDog hd = new HouseDog();
		// 다형성 : 부모는 자식을 가르킬 수 있다
		Animal a2 = new Dog();		// Upcasting
		Animal a3 = new HouseDog();
		Dog d2 = new HouseDog();

//		HouseDog hd2 = new Dog();	// 자식은 부모를 가르킬수 없음 , Downcasting

		
	}
}