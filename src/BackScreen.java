import java.io.*;
import java.util.*;

public class BackScreen {
    private static int fileNumber;
    private List<Passenger> passengers;
    private   List <Passenger> passengerFilter;
    public BackScreen() {
        this.fileNumber = 0;
        this.passengers = new ArrayList<>();
        createPassengerList();
    }

    public String filter(String pClass,String gender,String embarked,String passengerName,String ticketNumber,String cabin,String passengerNumMin,String passengerNumMax,String sibSp,
                         String ticketCostMin,String ticketCostMax,String parCh){
        this.passengerFilter = new ArrayList<>();
        windowFilter(pClass,gender,passengerName,embarked,ticketNumber,cabin,passengerNumMin,passengerNumMax,sibSp,ticketCostMin,ticketCostMax,parCh);
        System.out.println(this.passengerFilter);
        createFilterSCV();
        String result = "Total Row: " + this.passengerFilter.size()+ " Survived: " +
                howManySurvived(this.passengerFilter) +" dead "+ ( howMuchNotSurvived(this.passengerFilter));
        return result;
    }
   private void windowFilter(String pClass,String gender,String passengerName,String embarked,String ticketNumber,
                             String cabin,String passengerNumMin,String passengerNumMax,String sibSp,String ticketCostMin,
                             String ticketCostMax,String parCh){
        boolean isValid =  true;
       for (Passenger passenger: this.passengers) {
           if (!pClass.equals("All")){
               if (!passenger.identicalPClass(returnClassNumber(pClass))) {
                   isValid = false;
               }} if (!gender.equals("All")){
               if (!passenger.identicalGender(gender) ){
                  isValid=false;
               }}if (!passengerName.equals("")){
               if (!passenger.isContainedInName(passengerName)) {
                   isValid=false;
               }}  if (!embarked.equals("")){
               if (!passenger.identicalEmbarked(returnEmbarked(embarked))) {
                   isValid=false;
               }} if (!ticketNumber.equals("")){
               if (!passenger.isContainedInTicket(ticketNumber)) {
                   isValid = false;
               }}if (!cabin.equals("")){
               if (!passenger.isContainedInCabin(cabin)) {
                   isValid= false;
               }}
           if (!passengerNumMin.equals("")){
               if (!passenger.isBiggerId(Integer.parseInt(passengerNumMin))){ ///////min-max
                   isValid= false;
               }} if (!passengerNumMax.equals("")){
               if (passenger.isBiggerId(Integer.parseInt(passengerNumMax))){
                   isValid = false;
               }} if (!sibSp.equals("")){
               if (!passenger.isContainedInSibSp(sibSp)){
                   isValid=false;
               }}if (!ticketCostMin.equals("")){
               if (!passenger.isBiggerFare(Float.valueOf(ticketCostMin))){
                   isValid=false;
               }}if (!ticketCostMax.equals("")){
               if (passenger.isBiggerFare(Float.valueOf(ticketCostMax))){
                   isValid=false;
               }} if (!parCh.equals("")){
               if (!passenger.isContainedInParCh(Integer.parseInt(parCh))){
                  isValid=false;
               }}
           if (isValid){
               this.passengerFilter.add(passenger);
           }else {
               isValid=true;
           }
       }
   }


    private  void pClassFilter(String pClass){
        for (Passenger passenger: this.passengers) {
            if (!pClass.equals("All")){
                if (!passenger.identicalPClass(returnClassNumber(pClass))) {
                    this.passengerFilter.remove(passenger);
                }}}
    }
    private  void removeGenderFilter(String gender){
        for (Passenger passenger: this.passengers) {
            if (!gender.equals("All")){
                if (!passenger.identicalGender(gender) ){
                    this.passengerFilter.remove(passenger);
                }}}
    }
    private int returnClassNumber(String pClass){
        int classNum=Constants.PCLASS_THREE;
        switch (pClass){
            case Constants.FIRST_CLASS  -> classNum=Constants.PCLASS_ONE;
            case Constants.SECOND_CLASS -> classNum=Constants.PCLASS_TWO;
        }
        return classNum;
    }
    private  void passengerNameFilter(String passengerName){
        for (Passenger passenger: this.passengers) {
            if (!passengerName.equals("")){
                if (passenger.isContainedInName(passengerName)) {
                    this.passengerFilter.add(passenger);
                }}else {
                this.passengerFilter.add(passenger);
            }
        }
    }
    private  void removeEmbarkedFilter(String embarked){
        for (Passenger passenger: this.passengers) {
            if (!embarked.equals("")){
                if (!passenger.identicalEmbarked(returnEmbarked(embarked))) {
                    this.passengerFilter.remove(passenger);
                }}}
    }
    private char returnEmbarked(String embarked){
        char embarkedNum=Constants.CHARBURGH_CHAR;
        switch (embarked){
            case Constants.SOUTH_HAMPTON_NAME  -> embarkedNum=Constants.SOUTH_HAMPTON_CHAR;
            case Constants.QUEENSTOWN_NAME -> embarkedNum=Constants.QUEENSTOWN_CHAR;
        }
        return embarkedNum;
    }
    private  void ticketNumberFilter(String ticketNumber){
            for (Passenger passenger: this.passengerFilter) {
                if (!ticketNumber.equals("")){
                    if (!passenger.isContainedInTicket(ticketNumber)) {
                        this.passengerFilter.remove(passenger);
                    }}}
    }
    private  void cabinFilter(String cabin){
        for (Passenger passenger: this.passengerFilter) {
            if (!cabin.equals("")){
                if (!passenger.isContainedInCabin(cabin)) {
                    this.passengerFilter.remove(passenger);
                }
            }

        }}
    private  void passengerNumMinFilter( String passengerNumMin){
        for (Passenger passenger: this.passengerFilter) {
            if (!passengerNumMin.equals("")){
                if (!passenger.isBiggerId(Integer.parseInt(passengerNumMin))){
                    this.passengerFilter.remove(passenger);
                }
            }
        }}
    private  void passengerNumMaxFilter( String passengerNumMax){
        for (Passenger passenger: this.passengerFilter) {
            if (!passengerNumMax.equals("")){
                if (passenger.isBiggerId(Integer.parseInt(passengerNumMax))){
                    this.passengerFilter.remove(passenger);
                }
            }
        }
    }
    private  void sibSpFilter(String sibSp){
        for (Passenger passenger: this.passengerFilter) {
            if (!sibSp.equals("")){
                if (!passenger.isContainedInSibSp(sibSp)){
                    this.passengerFilter.remove(passenger);
                }
            }
        }
    }
    private  void ticketCostMinFilter(String ticketCostMin){
        for (Passenger passenger: this.passengerFilter) {
            if (!ticketCostMin.equals("")){
                if (!passenger.isBiggerFare(Float.valueOf(ticketCostMin))){
                    this.passengerFilter.remove(passenger);
                }
            }
        }
    }
    private  void ticketCostMaxFilter(String ticketCostMax){
        for (Passenger passenger: this.passengerFilter) {
            if (!ticketCostMax.equals("")){
                if (passenger.isBiggerFare(Float.valueOf(ticketCostMax))){
                    this.passengerFilter.remove(passenger);
                }
            }
        }
    }
    private  void parChFilter(String parCh){
        for (Passenger passenger: this.passengerFilter) {
            if (!parCh.equals("")){
                if (!passenger.isContainedInParCh(Integer.parseInt(parCh))){
                    this.passengerFilter.remove(passenger);
                }
            }
        }
    }
    public void statistics(){
        String lines = "";
        lines += "Classes: \n" + classStatist();
        lines+= "\n" + "Genders: \n" + genderStatistics();
        lines+= "\n" + "Embarked: \n" + embarkedStatist();
        lines+= "\n" + "Fare: \n" + fareStatistics();
        lines+= "\n" + "Age: \n" + ageStatistics();
        lines+= "\n" + "Relatives: \n" + relativesStatistics();
        createStatisticFile(lines);
    }
    private void createStatisticFile(String lines){
        File file = new File("statistic.txt");
        try {
            boolean success =  file.createNewFile();
            if (success){
                System.out.println("File created successfully");
            }else {
                System.out.println("File already exists");
            }
        }catch (IOException e){
            System.out.println("Something happened, cannot create file.");
        }
        if (file.exists()){
            try {
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(lines);
                bufferedWriter.close();
                fileWriter.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    private String classStatist() {
        String classStatistLinens = "";
        float all;
        float survived;
        float result;
        for (int i=1;i<=3;i++){
            List<Passenger> classFilter = classFilter(i);
            all = classFilter.size();
            survived = howManySurvived(classFilter);
            result= (survived/all)*Constants.ONE_HUNDRED_PERCENT;
            classStatistLinens +=" "+"Class " + i + ": ";
            classStatistLinens+=Float.toString(result)+"%" + "\n";
        }
        return classStatistLinens;
    }
    private String embarkedStatist() {
        String embarkedStatistLinens = "";
        float all;
        float survived;
        float result;
        String embarkeds = "SQC";
        for (int i=0;i<embarkeds.length();i++){
            List<Passenger> classFilter = embarkedFilter(embarkeds.charAt(i));
            all = classFilter.size();
            survived = howManySurvived(classFilter);
            result= (survived/all)*100;
            embarkedStatistLinens +=" "+"Embarked" + embarkeds.charAt(i) + ": ";
            embarkedStatistLinens+=Float.toString(result)+"%" + "\n";
        }
        return embarkedStatistLinens;
    }
    private String genderStatistics() {
        String genderStatistLinens = "";
        float all;
        float survived;
        float result;
        for (int i=0;i<=1;i++){
            List<Passenger> genderFilter = genderFilter(Constants.GENDERS[i]);
            all = genderFilter.size();
            survived = howManySurvived(genderFilter);
            result= (survived/all)*Constants.ONE_HUNDRED_PERCENT;
            genderStatistLinens +=" "+Constants.GENDERS[i]+ ": ";
            genderStatistLinens+=Float.toString(result)+"%" + "\n";
        }
        return genderStatistLinens;
    }
    private String ageStatistics(){
        String classStatistLinens = "";
        float all;
        float survived;
        float result;
        for (int i=1;i<=6;i++){
            List<Passenger> ageFilter = ageFilter(i);
            all = ageFilter.size();
            survived = howManySurvived(ageFilter);
            result= (survived/all)*Constants.ONE_HUNDRED_PERCENT;
            classStatistLinens +=" "+Constants.AGES[i-1]+ ": ";
            classStatistLinens+=Float.toString(result)+"%" + "\n";
        }

        return classStatistLinens;
    }
    private String fareStatistics(){
        String classStatistLinens = "";
        float all;
        float survived;
        float result;
        for (int i=1;i<=3;i++){
            List<Passenger> fareFilter = fareFilter(i);
            all = fareFilter.size();
            survived = howManySurvived(fareFilter);
            result= (survived/all)*Constants.ONE_HUNDRED_PERCENT;
            classStatistLinens +=" "+Constants.PRICES[i-1]+ ": ";
            classStatistLinens+=Float.toString(result)+"%" + "\n";
        }

        return classStatistLinens;
    }
    private List<Passenger> ageFilter(int ageRange) {
        List<Passenger> ageFilter = new ArrayList<>();
        for (int i = 0; i < this.passengers.size(); i++) {
            if (this.passengers.get(i).ageCheck()==ageRange) {
                ageFilter.add(this.passengers.get(i));
            }
        }
        return ageFilter;
    }
    private String relativesStatistics() {
        String genderStatistLinens = "";
        float all;
        float survived;
        float result;
        List<Passenger> noRelativesFilter = new ArrayList<>();
        List<Passenger> relativesFilter = new ArrayList<>();

        for (int i = 0; i < this.passengers.size(); i++) {
            if (this.passengers.get(i).sumRelatives() == Constants.NO_RELATIVES) {
                noRelativesFilter.add(this.passengers.get(i));
            } else {
                relativesFilter.add(this.passengers.get(i));
            }}
            all = noRelativesFilter.size();
            survived = howManySurvived(noRelativesFilter);
            result = (survived / all) * Constants.ONE_HUNDRED_PERCENT;
            genderStatistLinens += " No relative: ";
            genderStatistLinens += Float.toString(result) + "%"+"\n";
            all = relativesFilter.size();
            survived = howManySurvived(relativesFilter);
            result = (survived / all) * Constants.ONE_HUNDRED_PERCENT;
            genderStatistLinens += " At list one relative: ";
            genderStatistLinens += Float.toString(result) + "%" + "\n";

            return genderStatistLinens;

    }
    private List<Passenger> fareFilter(int fareRange) {
        List<Passenger> fareFilter = new ArrayList<>();
        for (int i = 0; i < this.passengers.size(); i++) {
            if (this.passengers.get(i).ticketPriceCheck()==fareRange) {
                fareFilter.add(this.passengers.get(i));
            }
        }
        return fareFilter;
    }
    private void orderByName(List<Passenger> passengers1){
        for (int i=0;i<passengers1.size();i++) {
            for (int j=i+1;j<passengers1.size();j++)
                if(passengers.get(i).getFormattedName().compareTo(passengers1.get(j).getFormattedName())>0){
                    Passenger passenger = passengers1.get(i);
                    passengers1.set(i,passengers1.get(j));
                    passengers1.set(j,passenger);
                }
        }
    }
    private int howManySurvived(List<Passenger> passengers) {
        int survived = 0;
        for (int i = 0; i < passengers.size(); i++) {
            if (passengers.get(i).survived()){
                survived++;
            }
        }
        return survived;
    }
    private int howMuchNotSurvived(List<Passenger> passengers) {
        int notSurvived = 0;
        for (int i = 0; i < passengers.size(); i++) {
            if (!passengers.get(i).survived()){
                notSurvived++;
            }
        }
        return notSurvived;
    }
    private List<Passenger> classFilter(int pClass) {
        List<Passenger> classFilter = new ArrayList<>();
        for (int i = 0; i < this.passengers.size(); i++) {
            if (this.passengers.get(i).identicalPClass(pClass)) {
                classFilter.add(this.passengers.get(i));
            }
        }
        return classFilter;
    }

    private List<Passenger> genderFilter(String gender) {
        List<Passenger> genderFilter = new ArrayList<>();
        for (int i = 0; i < this.passengers.size(); i++) {
            if (this.passengers.get(i).identicalGender(gender)) {
                genderFilter.add(this.passengers.get(i));
            }
        }
        return genderFilter;

    }

    private List<Passenger> embarkedFilter(char embarked) {
        List<Passenger> embarkedFilter = new ArrayList<>();
        for (int i = 0; i < this.passengers.size(); i++) {
            if (this.passengers.get(i).identicalEmbarked(embarked)) {
                embarkedFilter.add(this.passengers.get(i));
            }
        }
        return embarkedFilter;
    }

    private void createFilterSCV(){
        this.fileNumber++;
        String fileName = Integer.toString(this.fileNumber);
        File file = new File(fileName+".csv");
        try {
            PrintWriter printWriter = new PrintWriter(file);

            for (Passenger passenger:this.passengerFilter) {
                printWriter.println(passenger.toString());
            }
            printWriter.close();

        }catch (IOException e){
            e.printStackTrace();
        }


    }


    private  void createPassengerList() {
        String line;
        String splitBy=",";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader((Constants.PATH_TO_DATA_FILE)));
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) !=null){
                String[] dataOfPassenger = line.split(splitBy);
                this.passengers.add(createNewPassenger(dataOfPassenger));
            }
        }catch (IOException e){
            System.out.println("can't read from the file");
        }
    }

    private  Passenger createNewPassenger(String[] dataOfPassenger) {
        Integer id = Integer.parseInt(dataOfPassenger[0]);
        Integer survived = Integer.parseInt(dataOfPassenger[1]);
        Integer pClass = Integer.parseInt(dataOfPassenger[2]);
        String name = dataOfPassenger[3] + dataOfPassenger[4];
        String gender =  dataOfPassenger[5] ;
        Float age = null;
        if (!dataOfPassenger[6].equals("")){
            age = Float.valueOf(dataOfPassenger[6]);
        }
        Integer sibSp = Integer.parseInt(dataOfPassenger[7]);
        Integer parCh = Integer.parseInt(dataOfPassenger[8]);
        String ticket = dataOfPassenger[9];
        Double fare = Double.valueOf(dataOfPassenger[10]);
        String cabin= dataOfPassenger[11];
        Character embarked = null;
        if (dataOfPassenger.length==13){
            embarked = dataOfPassenger[12].charAt(0);
        }
        return new Passenger(id,survived,pClass,name,gender,age,sibSp,parCh,ticket,fare,cabin,embarked);

    }

}
