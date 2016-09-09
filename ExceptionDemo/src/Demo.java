
public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		while(true){
			System.out.println("-------->");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			throw new RuntimeException("hello");
		}
	}

}
