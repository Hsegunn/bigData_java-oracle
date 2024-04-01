package day03;

class Coffee{
	int price;
	
	Coffee(int price){
		this.price = price;
	}
}
class Americano extends Coffee{
	Americano(){
		super(5000);
	}
	@Override
	public String toString() {
		return "아메리카노";
	}
}
class Hazelnet extends Coffee{
	Hazelnet(){
		super(5500);
	}
	@Override
	public String toString() {
		return "헤이즐넛";
	}
}
class Caffelatte extends Coffee{
	Caffelatte(){
		super(7500);
	}
	@Override
	public String toString() {
		return "카페라떼";
	}
}


class Customer{
	int money = 300000;
	void buyCoffee(Coffee coffee) {
		if(money < coffee.price) {
			System.out.println("잔액이 부족합니다.");
			return;
		}
		money = money - coffee.price;
		System.out.printf("%s를 구매.\n", coffee);
	}
}
public class Polymorphism {

	public static void main(String[] args) {
		Customer c = new Customer();
		c.buyCoffee(new Americano());			// 5000
		c.buyCoffee(new Hazelnet());			// 5500
		c.buyCoffee(new Caffelatte());		// 7500
		System.out.printf("커피구매 후 잔돈은 %d 입니다.\n",c.money);
	}

}