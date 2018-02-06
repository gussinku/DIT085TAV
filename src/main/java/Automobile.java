import org.junit.experimental.theories.Theories;

/*

* The first phase of this project concerns test-driven development of a module
* that has  the basic functionalities regarding an
* autonomous lane change system.
*
*/
public class Automobile implements CarInterFace {
    // first one is x and the second one is y
    int x;
    int y;
    
    public Automobile(int x,int y) {
    	this.x = x;
    	this.y = y;
    }
    
    public Automobile() {
    	x = 3;
    	y = 0;
    }
   

    public static void main(String[] args) {
        int[] radarValues = {30, 340, 220};
        //System.out.println(leftLaneDetect(radarValues, 30, 1));
    }

    public String  leftLaneDetect(Radar radars[], Lidar lidar, int query) {
        int faultyReadings = 0;

        //checking for sensor faulty readings, needed for cases no: 3,4,5,11,15
        for (int i = 0; i < radars.length; i++) {
            if (radars[i].getReading() > 50 || radars[i].getReading() < 0) {
                faultyReadings++;
            }
        }
        if (lidar.getReading() > 50 || lidar.getReading() < 0) {
            faultyReadings++;
        }

        //If more than 2 faulty readings, return error.
        if (faultyReadings > 1) {
            return "Error: faulty readings";
        }

        //Check if there is a car 5 meters to the car's left
        //Satisfies test cases no: 6-9
        for (int i = 0; i < radars.length; i++) {
            if (radars[i].getReading() >= 0 && radars[i].getReading() < 6) {
                return "Car detected";
            }
        }
        if (lidar.getReading() >= 0 && lidar.getReading() < 6) {
            return "Car detected";
        }

        //If the method was only called once, it will call itself again
        if (query == 1) {
            leftLaneDetect(radars, lidar,2);
        }
        
        //Otherwise no car has been detected, needed for cases no: 1,2,14
        return "No car detected";
    }

    public String changeLane(Automobile auto, Radar radarValues[], Lidar lider  ){
  
    	String str1 ="No car detected" ;
    	String str2 = "Car detected";
    	String str3 = "Error: faulty readings";
        String detect = leftLaneDetect(radarValues, lider, 1);
  
        /*checking corrrect bound of the y value 
          and 28,29
         */
    	if (detect.equals(str1) ) {
    		if (y < 0 || y > 95) {
    			return "y value incorrrect";
    		}
			moveForward(auto);
			/*check for car lane is with possible change lane condition
			  satisfy test case 27,32,36 change lane and x value.
			  and test case 27,32,36
			*/
			
			if ( auto.x >= 2 && auto.x <= 3) {
				 auto.x--;
				 return "Lane changed";
			}
			else {
				return "Can't change from this lane";
			}
    	}
    	/*
    	 * satisfy test case 25,26,31,35. Car detected
    	 * 
    	 * */
    		else if (detect.equals(str2)) {
    		moveForward(auto);
    		return "Lane change failed car detected";
    	}
    	/*
    	 * satisfy test case 30,33,34 for faulty readings 
    	 **/
    		else if (detect.equals(str3)) {
    		moveForward(auto);
    		return "Lane change failed,Error:faulty readings";
    	}
		return null;
    }

    //Satisfies test cases 1-3
    public int[] whereIs(){
    	int[] ReturnArray = new int[2];
    	ReturnArray[0] = x;
    	ReturnArray[1] = y;
        return ReturnArray;
    }
        public int moveForward(Automobile auto){
    	int val;
    	int carPosY = auto.whereIs()[1];
    	System.out.println("testing "+ y);
		// the if condition satisfies Test 4,Test 6, the boundrary values
        if(carPosY < 96 && carPosY >= 0 ){
			// this fulfills Test 5,Test 9, the car moves 5 meter
            y += 5;
            val = carPosY;
        }else if(carPosY >= 96 && carPosY <= 100) {
        	val = carPosY;
		// this condition satisfies Test 7,Test 8, when an faulty y position entered.	
        }else {
        	val = -1;
        }
        return val;
    }


}
