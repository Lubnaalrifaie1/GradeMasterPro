import java.io.FileWriter;

public class utilities extends student{
    
    private static String outputFile = "output.txt";

    utilities(String id, String code, String grade1, String grade2, String grade3, String grade_exam) {
        super(id, code, grade1, grade2, grade3, grade_exam);
        //TODO Auto-generated constructor stub
    }

    public static void writeFile(String content, Boolean flag){
        try{
            FileWriter myWriter = new FileWriter(outputFile);
            if(flag){          
                myWriter.write(content);
                myWriter.close();
            }
            else{
                studentGradesOBJ.sort((o1, o2) -> o1.getID().compareTo(o2.getID()));
                myWriter.write("STUDENT-ID  NAME" + " ".repeat(19) + "COURSE  GRADE\n");
                for(student student : studentGradesOBJ){
                    int len = student.getID().length() + student.getName().length() + 3;
                    len = 35-len;
                    myWriter.write(student.getID() + "   " + student.getName() + " ".repeat(len) + student.getCode() + "   " +student.getGrade() + "\n");
                }
                myWriter.close();
            }
        } catch(Exception e){}
    }

    public static void verifyGrades(){
        int counter = 0;
        try{
            for(student student : studentGradesOBJ){
                double[] temp = new double[4];
                temp[0] = Double.parseDouble(student.getG1());
                temp[1] = Double.parseDouble(student.getG2());
                temp[2] = Double.parseDouble(student.getG3());
                temp[3] = Double.parseDouble(student.getEG());

                for(Double d : temp){
                    if(d > 100 || d < 0){
                        String msg = "ERROR: with grades of student: " + studentGradesOBJ.get(counter).getID() + " on line: " + (counter+1) + " of CourseFile.txt\nEnsure all grades do not exceed the 0-100 range";
                        writeFile(msg, true);
                        System.exit(1);
                    }
                }

                calculateFinal(student, temp);
                counter++;
            }
        }catch(Exception e){
            String msg = "ERROR: with grades of student: " + studentGradesOBJ.get(counter).getID() + " on line: " + (counter+1) + " of CourseFile.txt\nEnsure all grades are numerical";
            writeFile(msg, true);
            System.exit(1);
        }
    }

    private static void calculateFinal(student student, double[] grade){
        double finalGrade = (grade[0]*0.2) + //grade 1 times 20% weightage
                            (grade[1]*0.2) + //grade 2 times 20% weightage
                            (grade[2]*0.2) + //grade 3 times 20% weightage
                            (grade[3]*0.4);  //exam grade times 40% weightage summed with test grades
        int scale = (int) Math.pow(10, 1);//scale is needed to accurately round to 1 decimal place

        finalGrade = (double) Math.round(finalGrade * scale)/scale;//return a rounded value

        student.setFinal(Double.toString(finalGrade));
    } 

    public static void matchNames(){
        for(student student : studentGradesOBJ){
            for(String[] arr : names){
                if(student.getID().equals(arr[0])){
                    student.setName(arr[1]);
                }
            }
            if(student.getName() == null){
                String msg = "ERROR: Student ID " + student.getID() + " in CourseFile.txt does not match with any ID's in NameFile.txt";//generate an error message with line number
                utilities.writeFile(msg, true);//submit error message with True flag 
                System.exit(1);//stop running code
            }
        }
    }
}
