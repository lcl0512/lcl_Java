abstract class Animal{
	abstract void eat();
}
class Bird extends Animal{
	void eat(){
		System.out.println("小鸟是杂食动物！");
	}
}
class Tiger extends Animal{
	void eat(){
		System.out.println("老虎是肉食性动物");
	}
}
public class Test21{
	public static void main(String args[]){
		Tiger ti = new Tiger();
		ti.eat();
		Bird bi = new Bird();
		bi.eat();
	}
}
