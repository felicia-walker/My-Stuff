package chapter4;

public class Four {

	public static void main(String[] args) {
		
		// Make some arbirary dates
		String[] dates = new String[10];
		for (int i = 1; i <= dates.length; i++) {
			StringBuilder tmpBuilder = new StringBuilder();
			int month = i;
			int day = i + 15;
			int year = (1000 * i) + i;
			tmpBuilder.append(month);
			tmpBuilder.append('/');
			tmpBuilder.append(day);
			tmpBuilder.append('/');
			tmpBuilder.append(year);
			dates[i-1] = tmpBuilder.toString();
		}
		
		for (String curDate : dates) {
			System.out.println(curDate);
			
			String[] splitDate = curDate.split("/", 0);
			int month = Integer.parseInt(splitDate[0]);
			int day = Integer.parseInt(splitDate[1]);
			int year = Integer.parseInt(splitDate[2]);
			
			StringBuilder dateBuilder = new StringBuilder();
			dateBuilder.append(day);
			
			switch (day) {
				case 1:
				case 21:
				case 31:
					dateBuilder.append("st");
					break;
			
				case 2:
				case 22:
					dateBuilder.append("nd");
					break;
					
				case 3:
				case 23:
					dateBuilder.append("rd");
					break;
					
				default:
					dateBuilder.append("th");
			}
			
			switch (month) {
				case 1:
					dateBuilder.append(" January ");
					break;
				
				case 2:
					dateBuilder.append(" February ");
					break;
				
				case 3:
					dateBuilder.append(" March ");
					break;
				
				case 4:
					dateBuilder.append(" April ");
					break;
				
				case 5:
					dateBuilder.append(" May ");
					break;
				
				case 6:
					dateBuilder.append(" June ");
					break;
				
				case 7:
					dateBuilder.append(" July ");
					break;
				
				case 8:
					dateBuilder.append(" August ");
					break;
				
				case 9:
					dateBuilder.append(" September ");
					break;
				
				case 10:
					dateBuilder.append(" October ");
					break;
				
				case 11:
					dateBuilder.append(" November ");
					break;
				
				case 12:
					dateBuilder.append(" December ");
					break;
					
				default:
					dateBuilder.append("INVALID");
			}
			
			dateBuilder.append(year);
			
			System.out.println(dateBuilder.toString());
		}
	}

}
