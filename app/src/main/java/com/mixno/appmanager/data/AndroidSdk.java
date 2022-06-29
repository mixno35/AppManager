package com.mixno.appmanager.data;

public class AndroidSdk {
	
	public static String getFullAndroidName(int sdk) {
		String name = "0";
		switch (sdk) {
			case 1:
				name = "Android 1.0";
				break;
			case 2:
				name = "Android 1.1";
				break;
			case 3:
				name = "Android 1.5 Cupcake";
				break;
			case 4:
				name = "Android 1.6 Donut";
				break;
			case 5:
				name = "Android 2.0 Eclair";
				break;
			case 6:
				name = "Android 2.0 Eclair";
				break;
			case 7:
				name = "Android 2.1 Eclair";
				break;
			case 8:
				name = "Android 2.2 Froyo";
				break;
			case 9:
				name = "Android 2.3 Gingerbread";
				break;
			case 10:
				name = "Android 2.3.7 Gengerbread";
				break;
			case 11:
				name = "Android 3.0 Honeycomp";
				break;
			case 12:
				name = "Android 3.1 Honeycomp";
				break;
			case 13:
				name = "Android 3.2 Honeycomp";
				break;
			case 14:
				name = "Android 4.0 Ice Cream Sandwich";
				break;
			case 15:
				name = "Android 4.0.4 Ice Cream Sandwich";
				break;
			case 16:
				name = "Android 4.1 Jelly Bean";
				break;
			case 17:
				name = "Android 4.2 Jelly Bean";
				break;
			case 18:
				name = "Android 4.3 Jelly Bean";
				break;
			case 19:
				name = "Android 4.4 KitKat";
				break;
			case 20:
				name = "Android 4.4.4 KitKat";
				break;
			case 21:
				name = "Android 5.0 Lollipop";
				break;
			case 22:
				name = "Android 5.1 Lollipop";
				break;
			case 23:
				name = "Android 6.0 Marshmallow";
				break;
			case 24:
				name = "Android 7.0 Nougat";
				break;
			case 25:
				name = "Android 7.1 Nougat";
				break;
			case 26:
				name = "Android 8.0 Oreo";
				break;
			case 27:
				name = "Android 8.1 Oreo";
				break;
			case 28:
				name = "Android 9.0 Pie";
				break;
			case 29:
				name = "Android 10 Q";
				break;
			case 30:
				name = "Android 11 R";
				break;
			case 31:
				name = "Android 12 Snow Cone";
				break;
			case 32:
				name = "Android 12L Snow Cone";
				break;
			case 33:
				name = "Android 13 T";
				break;
		}
		return name;
	}
	
	public static String getAndroidName(int sdk) {
		String name = "0";
		switch (sdk) {
			case 1:
				name = "Android 1.0";
				break;
			case 2:
				name = "Android 1.1";
				break;
			case 3:
				name = "Android 1.5";
				break;
			case 4:
				name = "Android 1.6";
				break;
			case 5:
				name = "Android 2.0";
				break;
			case 6:
				name = "Android 2.0";
				break;
			case 7:
				name = "Android 2.1";
				break;
			case 8:
				name = "Android 2.2";
				break;
			case 9:
				name = "Android 2.3";
				break;
			case 10:
				name = "Android 2.3.7";
				break;
			case 11:
				name = "Android 3.0";
				break;
			case 12:
				name = "Android 3.1";
				break;
			case 13:
				name = "Android 3.2";
				break;
			case 14:
				name = "Android 4.0";
				break;
			case 15:
				name = "Android 4.0.4";
				break;
			case 16:
				name = "Android 4.1";
				break;
			case 17:
				name = "Android 4.2";
				break;
			case 18:
				name = "Android 4.3";
				break;
			case 19:
				name = "Android 4.4";
				break;
			case 20:
				name = "Android 4.4.4";
				break;
			case 21:
				name = "Android 5.0";
				break;
			case 22:
				name = "Android 5.1";
				break;
			case 23:
				name = "Android 6.0";
				break;
			case 24:
				name = "Android 7.0";
				break;
			case 25:
				name = "Android 7.1";
				break;
			case 26:
				name = "Android 8.0";
				break;
			case 27:
				name = "Android 8.1";
				break;
			case 28:
				name = "Android 9.0";
				break;
			case 29:
				name = "Android 10";
				break;
			case 30:
				name = "Android 11";
				break;
			case 31:
				name = "Android 12";
				break;
			case 32:
				name = "Android 12L";
				break;
			case 33:
				name = "Android 13";
				break;
		}
		return name;
	}
	
	public static String getOnlyAndroidVersion(int sdk) {
		String name = "0";
		switch (sdk) {
			case 1:
				name = "1.0";
				break;
			case 2:
				name = "1.1";
				break;
			case 3:
				name = "1.5";
				break;
			case 4:
				name = "1.6";
				break;
			case 5:
				name = "2.0";
				break;
			case 6:
				name = "2.0";
				break;
			case 7:
				name = "2.1";
				break;
			case 8:
				name = "2.2";
				break;
			case 9:
				name = "2.3";
				break;
			case 10:
				name = "2.3.7";
				break;
			case 11:
				name = "3.0";
				break;
			case 12:
				name = "3.1";
				break;
			case 13:
				name = "3.2";
				break;
			case 14:
				name = "4.0";
				break;
			case 15:
				name = "4.0.4";
				break;
			case 16:
				name = "4.1";
				break;
			case 17:
				name = "4.2";
				break;
			case 18:
				name = "4.3";
				break;
			case 19:
				name = "4.4";
				break;
			case 20:
				name = "4.4.4";
				break;
			case 21:
				name = "5.0";
				break;
			case 22:
				name = "5.1";
				break;
			case 23:
				name = "6.0";
				break;
			case 24:
				name = "7.0";
				break;
			case 25:
				name = "7.1";
				break;
			case 26:
				name = "8.0";
				break;
			case 27:
				name = "8.1";
				break;
			case 28:
				name = "9.0";
				break;
			case 29:
				name = "10";
				break;
			case 30:
				name = "11";
				break;
			case 31:
				name = "12";
				break;
			case 32:
				name = "12L";
				break;
			case 33:
				name = "13";
				break;
		}
		return name;
	}
	
	public static String getOnlyAndroidName(int sdk) {
		String name = "0";
		switch (sdk) {
			case 1:
				name = "Android 1.0";
				break;
			case 2:
				name = "Android 1.1";
				break;
			case 3:
				name = "Cupcake";
				break;
			case 4:
				name = "Donut";
				break;
			case 5:
				name = "Eclair";
				break;
			case 6:
				name = "Eclair";
				break;
			case 7:
				name = "Eclair";
				break;
			case 8:
				name = "Froyo";
				break;
			case 9:
				name = "Gingerbread";
				break;
			case 10:
				name = "Gengerbread";
				break;
			case 11:
				name = "Honeycomp";
				break;
			case 12:
				name = "Honeycomp";
				break;
			case 13:
				name = "Honeycomp";
				break;
			case 14:
				name = "Ice Cream Sandwich";
				break;
			case 15:
				name = "Ice Cream Sandwich";
				break;
			case 16:
				name = "Jelly Bean";
				break;
			case 17:
				name = "Jelly Bean";
				break;
			case 18:
				name = "Jelly Bean";
				break;
			case 19:
				name = "KitKat";
				break;
			case 20:
				name = "KitKat";
				break;
			case 21:
				name = "Lollipop";
				break;
			case 22:
				name = "Lollipop";
				break;
			case 23:
				name = "Marshmallow";
				break;
			case 24:
				name = "Nougat";
				break;
			case 25:
				name = "Nougat";
				break;
			case 26:
				name = "Oreo";
				break;
			case 27:
				name = "Oreo";
				break;
			case 28:
				name = "Pie";
				break;
			case 29:
				name = "Android Q";
				break;
			case 30:
				name = "Android R";
				break;
			case 31:
				name = "Snow Cone";
				break;
			case 32:
				name = "L Snow Cone";
				break;
			case 33:
				name = "Android T";
				break;
		}
		return name;
	}
}
