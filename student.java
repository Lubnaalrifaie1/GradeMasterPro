import java.io.File;  // Import the File class
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;  

public class student{
    private static File coursesFile = new File("CourseFile.txt");
    private static File namesFile = new File("NameFile.txt");
    
    
    public static ArrayList<String[]> names = new ArrayList<String[]>();
    public static ArrayList<String[]> grades = new ArrayList<String[]>();
    public static ArrayList<student> studentsOBJ = new ArrayList<student>();
    public static ArrayList<student> studentGradesOBJ = new ArrayList<student>();

    public String ID, CODE, GRADE1, GRADE2, GRADE3, GRADE_EXAM, NAME, FINAL_GRADE;

    student(String id, String code, String grade1, String grade2, String grade3, String grade_exam){
        this.ID = id;
        this.CODE = code;
        this.GRADE1 = grade1;
        this.GRADE2 = grade2;
        this.GRADE3 = grade3;
        this.GRADE_EXAM = grade_exam;
    }

    student(String id, String name){
        this.ID = id;
        this.NAME = name;
    }

    public String getName(){
        return NAME;
    }

    public String getCode(){
        return CODE;
    }

    public String getID(){
        return ID;
    }

    public String getG1(){
        return GRADE1;
    }

    public String getG2(){
        return GRADE2;
    }
    
    public String getG3(){
        return GRADE3;
    }
    
    public String getEG(){
        return GRADE_EXAM;
    }

    public String getGrade(){
        return FINAL_GRADE;
    }

    public void setName(String name){
        this.NAME = name;
    }

    public void setFinal(String grade){
        this.FINAL_GRADE = grade;
    }


    public static ArrayList<String[]> readNames(){//reads the contents of NameFile.txt
        try{
            Scanner scanner = new Scanner(namesFile);
            int counter = 1;//keeps track of which line the scanner is on
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();//the current line of data
                String data[] = line.trim().split("\\s*,\\s*");//removes any leading and trailing whitespace 

                if(data.length == 2){

                    if(data[0].length() == 9){//check studentID length is 9 digits
                        try{
                            int temp = Integer.parseInt(data[0]);//check to make sure only numbers in student ID
                        } catch(Exception e){
                            String msg = "ERROR: on line " + counter + " of NameFile.txt\nEnsure studentID only contains numbers.";//generate an error message with line number
                            utilities.writeFile(msg, true);//submit error message with True flag 
                            System.exit(1);//stop running code
                        }

                    }

                    else if(data[0].length() !=9){//check studentID length is 9 digits
                        String msg = "ERROR: on line " + counter + " of NameFile.txt\nEnsure studentID is of correct length.";//generate an error message with line number
                        utilities.writeFile(msg, true);//submit error message with True flag 
                        System.exit(1);//stop running code
                    }

                    if(data[1].length() > 20){
                        String msg = "ERROR: on line " + counter + " of NameFile.txt\nEnsure names do not exceed 20 bytes.";//generate an error message with line number
                        utilities.writeFile(msg, true);//submit error message with True flag 
                        System.exit(1);//stop running code
                    }

                    if(data[0].equals(data[1])){//multiple studentID check
                        String msg = "ERROR: on line " + counter + " of NameFile.txt\nEnsure studentID is not duplicated.";//generate an error message with line number
                        utilities.writeFile(msg, true);//submit error message with True flag 
                        System.exit(1);//stop running code
                    }

                    else{
                        String[] result = new String[2];//creates a string array with the ID and Name 
                        result[0] = data[0];//storing ID
                        result[1] = data[1];//storing Name
                        student student = new student(result[0], result[1]);

                        studentsOBJ.add(student);
                        names.add(result);//appending the array to a list
                    }
                }
                else{
                    String msg = "ERROR: on line " + counter + " of NameFile.txt\nEnsure no missing/extra data in line or a comma is not lost.";//generate an error message with line number
                    utilities.writeFile(msg, true);//submit error message with True flag 
                    System.exit(1);//stop running code   
                }
                counter++;//increment line counter
            }
            scanner.close();

        } catch(Exception e){
            String msg = "ERROR: Could not read NameFile.txt\nEnsure that the file is named correctly and follows a '.txt' extension";
            utilities.writeFile(msg, true);//submit error message with True flag 
            System.exit(1);
        }

        return names;
    }

    public static ArrayList<String[]> readCourses(){//reads the contents of CourseFile.txt
        try{
            Scanner scanner = new Scanner(coursesFile);
            int counter = 1;//keeps track of which line the scanner is on
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();//the current line of data
                String data[] = line.trim().split("\\s*,\\s*");//removes any leading and trailing whitespace 

                if(data.length == 6){//ensures that the there is exactly 6 pieces of data
                    Boolean flag = false;

                    for(int i = 0; i < data.length; i++){//check to make sure there is no data missing in the array slots
                        if(data[i].length() == 0){//there is data missing
                            flag = true;//trigger flag
                        }
                    }

                    if(data[0].equals(data[1])){//multiple studentID check
                        String msg = "ERROR: on line " + counter + " of CourseFile.txt\nEnsure studentID is not duplicated.";//generate an error message with line number
                        utilities.writeFile(msg, true);//submit error message with True flag 
                        System.exit(1);//stop running code
                    }

                    if(data[1].length() != 5 || (!Character.isLetter(data[1].charAt(0))) || !Character.isLetter(data[1].charAt(1)) || Character.isLetter(data[1].charAt(2)) || Character.isLetter(data[1].charAt(3)) || Character.isLetter(data[1].charAt(4))){
                        String msg = "ERROR: on line " + counter + " of CourseFile.txt\nEnsure courseID is of correct length and/or format.";//generate an error message with line number
                        utilities.writeFile(msg, true);//submit error message with True flag 
                        System.exit(1);//stop running code
                    }

                    if(data[0].length() == 9){//check studentID length is 9 digits
                        try{
                            int temp = Integer.parseInt(data[0]);//check to make sure only numbers in student ID
                        } catch(Exception e){
                            String msg = "ERROR: on line " + counter + " of CourseFile.txt\nEnsure studentID only contains numbers.";//generate an error message with line number
                            utilities.writeFile(msg, true);//submit error message with True flag 
                            System.exit(1);//stop running code
                        }

                    }

                    else if(data[0].length() != 9){
                        String msg = "ERROR: on line " + counter + " of CourseFile.txt\nEnsure studentID is of correct length.";//generate an error message with line number
                        utilities.writeFile(msg, true);//submit error message with True flag 
                        System.exit(1);//stop running code        
                    }

                    if(!flag){//if no data is missing
                        String[] result = new String[6];//creates a string array with the ID and Name 

                        for(int i = 0; i < data.length; i++){
                            result[i] = data[i];
                        }

                        student student = new student(result[0], result[1], result[2], result[3], result[4], result[5]);

                        studentGradesOBJ.add(student);
                        grades.add(result);//appending the array to a list
                    }

                    else{//there is an empty index in the array
                        String msg = "ERROR: on line " + counter + " of CourseFile.txt\nEnsure no extra/missing data in line or a comma is not lost.";//generate an error message with line number
                        utilities.writeFile(msg, true);//submit error message with True flag 
                        System.exit(1);//stop running code
                    }
                }

                else{//there is missing information for the student
                    String msg = "ERROR: on line " + counter + " of CourseFile.txt\nEnsure no extra/missing data in line or a comma is not lost.";//generate an error message with line number
                    utilities.writeFile(msg, true);//submit error message with True flag 
                    System.exit(1);//stop running code
                }
                counter++;//increment line counter
            }
            scanner.close();

        } catch(Exception e){
            String msg = "ERROR: Could not read CourseFile.txt\nEnsure that the file is named correctly and follows a '.txt' extension";
            utilities.writeFile(msg, true);//submit error message with True flag 
            System.exit(1);
        }

        return names;
    }

    public static void main(String[] args){
        readNames();
        readCourses();
        utilities.verifyGrades();
        utilities.matchNames();
        utilities.writeFile("", false);
    }
}
