
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class IseAlimRobotu {
	private static double[][] arr,arr1,arr2;
	private static double sum = 0;
	private double array[];
    private double length;
    private static double[] distances;
    private static double [] values;
	private static double[] t1;
	private static double[] t2;
	private static double[] t3;
	private static double[] t4;

	public static void main(String[] args) {
		IseAlimRobotu is=  new IseAlimRobotu();
		 arr1= new double[1000][4]; 
		 arr2= new double[1000][5];
		 try {
				arr = getData();//taking data from text field
			} catch (IOException e) {
				System.out.println("Please be sure that the path of text file is correct...");
			}
		 t1= new double[arr.length];
		 t2= new double[arr.length];
		 t3= new double[arr.length];
		 t4=new double[arr.length];
		 for (int i = 0; i < arr.length; i++) {//for social
				t1[i]=arr[i][0];
			}
			for (int i = 0; i < arr.length; i++) {//for algorithm
				t2[i]=arr[i][1];
			}
			for (int i = 0; i < arr.length; i++) {//for gpa
				t3[i]=arr[i][2];
			}
			for (int i = 0; i < arr.length; i++) {//for age
				t4[i]=arr[i][3];
			}
		
		for (int i = 0; i <arr.length; i++) {
			arr1[i][0]=arr[i][0];
			arr1[i][1]=arr[i][1];
			arr1[i][2]=arr[i][2];
			arr1[i][3]=arr[i][3];
		}
		is.norm(arr1);       
		for (int i = 0; i < arr1.length; i++) {
			arr2[i][0]=arr1[i][0];
			arr2[i][1]=arr1[i][1];
			arr2[i][2]=arr1[i][2];
			arr2[i][3]=arr1[i][3];	
			arr2[i][4]=arr[i][4];	
		}  
		//Our candidates...
		double [][] yeni_adaylar= {{5,4,1,2},
				{4,3.454,2.5412,4.2564},
				{1,1,4,2},
				{2.222,1.452,3.5,4.21},
				{2.42,1.56,4,5},
				{4.24,2.5,3.6,4.0}};
	is.IseAlim(yeni_adaylar,arr2);
	}
	
	//sort() sorts an array with using quick sort algorithm...
	public void sort(double[] inputArr) {
        if (inputArr == null || inputArr.length == 0) {
            return;
        }
        this.array = inputArr;
        length = inputArr.length;
        quickSort(0, length - 1);
    }
 
    private void quickSort(double lowerIndex, double higherIndex) {
         
    	double i = lowerIndex;
    	double j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number55
        double pivot = array[(int) (lowerIndex+(higherIndex-lowerIndex)/2)];
        // Divide into two arrays
        while (i <= j) {
            /*
             * In each iteration, we will identify a number from left side which 
             * is greater then the pivot value, and also we will identify a number 
             * from right side which is less then the pivot value. Once the search 
             * is done, then we exchange both numbers.
             */
            while (array[(int) i] < pivot) {
                i++;
            }
            while (array[(int) j] > pivot) {
                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (lowerIndex < j)
            quickSort(lowerIndex, j);
        if (i < higherIndex)
            quickSort(i, higherIndex);
    }
 
    //exchangeNumbers() exchange the numbers with given index.
    //Also it exchanges the values arrays indexes which is parralel to their indexes///
    private void exchangeNumbers(double i, double j) {
    	double temp = array[(int) i];
    	double temp2 = values[(int) i];
        array[(int) i] = array[(int) j];
        array[(int) j] = temp;
        values[(int) i] = values[(int) j];
        values[(int) j] = temp2;
       
    }
    //printArray() prints the array on to the screen
	public static void printArray(double[] a) {
		System.out.print("{");
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]+" ");
		}System.out.print("}");
	
	}
	//EuclideanDistance(): Calculates the euclidian distance between a candidate and our data.
	//And put the distances into a double array with the information about is the person is hired or not... 
    public double[][] EuclideanDistance(double[] x , double [][] y) {
    	double array [][] = new double [y.length][2];
    	int count= 0 ;
    	while (count <y.length) {	
    	
            for (int i = 0 ; i <y.length ; i ++) {		
            
            sum += power(x[0]-y[i][0],2);
            sum += power(x[1]-y[i][1],2);
            array[count][0]= square(sum);
            array[count][1]= y[count][4];
            sum = 0 ;
            count ++;	
            }
    	}
    	return array;
    }
    
    //IseAlim() takes a two dimensional double array which includes candidates and our data.
    //Finds every candidates euclidian distance to every person in our data.
    //Sorts them for find the 5 nearest neighbour and look if they got the job or not
    //If more than their half is got the job the candidate gets the job...
    public void IseAlim(double[][] x,double[][] y) {
    	IseAlimRobotu sorter= new IseAlimRobotu();
    	norm(x);
    	for (int i = 0; i < x.length; i++) { 
    		
			double[][] fin = EuclideanDistance(x[i], y);
			
			distances = new double[fin.length];
			for (int j = 0; j < fin.length; j++) {
				distances[j]=fin[j][0];
			}
			values = new double[fin.length];
			for (int j = 0; j < fin.length; j++) {
				values[j]=fin[j][1];
			}
			sorter.sort(distances);
			
			int sum =0;
			for (int j = 0; j < 5; j++) {
				sum+=values[j];  
				
			}
                        
			
			if (sum>2) {
				System.out.print("Aday-"+(i+1)+" ");
			System.out.print(">>> Kabul edildi...");	System.out.println();
                            
			}
			else {
				System.out.print("Aday-"+(i+1)+" ");
				System.out.print(">>> RED edildi...");	System.out.println();
				
			}
			
    	}
    }
    	//square() is a helper method to find squareroot of a double number...
		public static double square(double number){
	       if(number==0)return 0;
			double t;
	      
	        double squareroot = number / 2;
	      
	        do {
	            t = squareroot;
	            squareroot = (t + (number / t)) / 2;
	        } while ((t - squareroot) != 0);
	      
	        return squareroot;
	    } 
		//power() is a helper method to find the nth power of a double number... 
			public static double power(double x, double y) {
		    double result = x;

		    for (int i = 1; i < y; i++) {
		      result = result * x;
		    }

		    return result;
		  }
	//getData(): Takes data from txt file and put them into a two dimensional double array.	
	public static double[][] getData() throws IOException { 
		String fileName = "C:\\Users\\berke\\OneDrive\\Desktop\\data.txt";		
		String[] arr= new String[1000];
		double[][] arr1= new double[1000][5];
		Path path = Paths.get(fileName);
		Scanner scanner = new Scanner(path);
		//reads line by line
		
		for(int i = 0;i<1000;i++){
		    //process each line	
			
		    String line = scanner.nextLine();
		    arr[i] = line;
		}
		
		scanner.close();
		String[] ss= new String[5];//string array to: read our lines into strings
		
		for (int i = 0; i < 1000; i++) {
			//spliting strings into integers and put them into two dimensional double array
			ss=arr[i].split(",");
			arr1[i][0]=Double.parseDouble(ss[0]);
			arr1[i][1]=Double.parseDouble(ss[1]);
			arr1[i][2]=Double.parseDouble(ss[2]);
			arr1[i][3]=Double.parseDouble(ss[3]);
			arr1[i][4]=Double.parseDouble(ss[4]);
		
		}
		return arr1;	
	}
	//norm() Normalizes a two dimensional double array according to our data...
	private double[][] norm(double[][] array) {
	
		double max;
		double min;
		
		max=getMax(t1);
		min=getMin(t1);
		
		for (int i = 0; i < array.length; i++) {
			array[i][0]=(array[i][0]-min)/(max-min);
		}
		
		//////////
		
	
		max=getMax(t2);
		min=getMin(t2);

		for (int i = 0; i < array.length; i++) {
		array[i][1]=(array[i][1]-min)/(max-min);
		}
		
		
		////////
		
		max=getMax(t3);
		min=getMin(t3);
		for (int i = 0; i < array.length; i++) {
			array[i][2]=(array[i][2]-min)/(max-min);
		}
		
		//////////
		
		
	max=getMax(t4);
	min=getMin(t4);

	for (int i = 0; i < array.length; i++) {
		array[i][3]=(array[i][3]-min)/(max-min);
	}
	return array;	
	}
	
	//getMax() is a helper method to find the maximum value in a double array...
	public static double getMax(double[] array) {
		double res=array[0];
		for (int i = 0; i < array.length; i++) {
			if (res<array[i])res = array[i];
			
		}
		return res;
	}
	//getMin() is a helper method to find the minimum value in a double array...
	public static double getMin(double[] array) {
		double res=array[0];
		for (int i = 0; i < array.length; i++) {
			if (res>array[i])res = array[i];
			
		}
		return res;
	}
	
	
	
}//end of class




