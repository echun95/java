package homework;

import java.text.NumberFormat;

public class hw20200323 {

	public enum Planet {
		수성(2439), 금성(6052), 지구(6371), 화성(3390), 목성(69911), 토성(58232), 천왕성(25362), 해왕성(24622);

		private double data;

		Planet(double data) {
			this.data = data;
		}

		public double getData() {
			return data;
		}

		public static double PlanetFunc(double data) {
			double result = 4 * 3.14 * data * data;
			return result;
		}

		public static void main(String[] args) {

			Planet[] enumArr = Planet.values();

			System.out.println("===================");
			System.out.println("행성별 반지름");
			System.out.println("===================");
			for (int i = 0; i < enumArr.length; i++) {
				System.out.println(enumArr[i].name() + " : " + enumArr[i].getData() + " km");
			}
			System.out.println("===================");

			System.out.println("행성별 면적");
			System.out.println("===================");
			for (int i = 0; i < enumArr.length; i++) {
				double data = enumArr[i].getData();
				double result = PlanetFunc(data);
				System.out.println(enumArr[i].name() + " : " + NumberFormat.getInstance().format(result) + " km^2");
			}

		}
	}
}