class Single{
	private static Single single = new Single();
	private Single(){}
	public static Single getSingle(){
		return single;
	}
}

public class Test01{
	public static void main(String[] args){
		Single s1 = Single.getSingle();
		Single s2 = Single.getSingle();
		if(s1.equals(s2))
			System.out.println("只有一个单例模式的实例对象");
		else
			System.out.println("违背事实");
	}
}

