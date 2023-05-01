import java.io.*;
import java.util.*;

public class BackScreen {
    private static int fileNumber;
    private List<Passenger> passengers;

    public BackScreen() {
        this.fileNumber = 0;
        this.passengers = new ArrayList<>();
        createPassengerList();
    }

    public String filter(String pClass,String gender,String embarked,String passengerName,String ticketNumber,String cabin,String passengerNumMin,String passengerNumMax,String sibSp,
                         String ticketCostMin,String ticketCostMax,String parCh){
        List <Passenger> passengerFilter = new ArrayList<>();
        passengerNameFilter(passengerFilter,passengerName);
        ticketNumberFilter(passengerFilter,ticketNumber);
        cabinFilter(passengerFilter,cabin);
        pClassFilter(passengerFilter,pClass);
        genderFilter(passengerFilter,gender);
        embarkedFilter(passengerFilter,embarked);
        passengerNumMinFilter(passengerFilter,passengerNumMin);
        passengerNumMaxFilter(passengerFilter,passengerNumMax);
        sibSpFilter(passengerFilter,sibSp);
        ticketCostMinFilter(passengerFilter,ticketCostMin);
        ticketCostMaxFilter(passengerFilter,ticketCostMax);
        parChFilter(passengerFilter,parCh);
        orderByName(passengerFilter);
        createFilterSCV(passengerFilter);
        System.out.println(passengerFilter);
        String result = "Total Row: " + passengerFilter.size()+ " Survived: " +
                howManySurvived(passengerFilter) +" dead "+ ( howMuchNotSurvived(passengerFilter));
        return result;
    }


    private  void pClassFilter(List <Passenger> passengerFilter, String pClass){
        for (Passenger passenger: this.passengers) {
            if (!pClass.equals("All")){
                if (!passenger.identicalPClass(returnClassNumber(pClass))) {
                    passengerFilter.remove(passenger);
                }}}
    }
    private  void genderFilter( List <Passenger> passengerFilter,String gender){
        for (Passenger passenger: this.passengers) {
            if (!gender.equals("All")){
                if (!passenger.identicalGender(gender) ){
                    passengerFilter.remove(passenger);
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


    private  void passengerNameFilter( List <Passenger> passengerFilter,String passengerName){
        for (Passenger passenger: this.passengers) {
            if (!passengerName.equals("")){
                if (passenger.isContainedInName(passengerName)) {
                    passengerFilter.add(passenger);
                }}}
    }

    private  void embarkedFilter( List <Passenger> passengerFilter,String embarked){
        for (Passenger passenger: this.passengers) {
            if (!embarked.equals("")){
                if (!passenger.identicalEmbarked(returnEmbarked(embarked))) {
                    passengerFilter.remove(passenger);
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

    private  void ticketNumberFilter( List <Passenger> passengerFilter,String ticketNumber){
        for (Passenger passenger: this.passengers) {
            if (!ticketNumber.equals("")){
                if (!passenger.isContainedInTicket(ticketNumber)) {
                    passengerFilter.remove(passenger);
                }}}
    }

    private  void cabinFilter( List <Passenger> passengerFilter,String cabin){
        for (Passenger passenger: this.passengers) {
            if (!cabin.equals("")){
                if (!passenger.isContainedInCabin(cabin)) {
                    passengerFilter.remove(passenger);
                }
            }

        }}

    private  void passengerNumMinFilter( List <Passenger> passengerFilter,String passengerNumMin){
        for (Passenger passenger: this.passengers) {
            if (!passengerNumMin.equals("")){
                if (!passenger.isBiggerId(Integer.parseInt(passengerNumMin))){
                    passengerFilter.remove(passenger);
                }
            }
        }}
    private  void passengerNumMaxFilter( List <Passenger> passengerFilter,String passengerNumMax){
        for (Passenger passenger: this.passengers) {
            if (!passengerNumMax.equals("")){
                if (passenger.isBiggerId(Integer.parseInt(passengerNumMax))){
                    passengerFilter.remove(passenger);
                }
            }
        }}

    private  void sibSpFilter( List <Passenger> passengerFilter,String sibSp){
        for (Passenger passenger: this.passengers) {
            if (!sibSp.equals("")){
                if (!passenger.isContainedInSibSp(sibSp)){
                    passengerFilter.remove(passenger);
                }
            }
        }
    }

    private  void ticketCostMinFilter(List <Passenger> passengerFilter,String ticketCostMin){
        for (Passenger passenger: this.passengers) {
            if (!ticketCostMin.equals("")){
                if (!passenger.isBiggerFare(Float.valueOf(ticketCostMin))){
                    passengerFilter.remove(passenger);
                }
            }
        }
    }
    private  void ticketCostMaxFilter(List <Passenger> passengerFilter,String ticketCostMax){
        for (Passenger passenger: this.passengers) {
            if (!ticketCostMax.equals("")){
                if (passenger.isBiggerFare(Float.valueOf(ticketCostMax))){
                    passengerFilter.remove(passenger);
                }
            }
        }
    }

    private  void parChFilter( List <Passenger> passengerFilter,String parCh){
        for (Passenger passenger: this.passengers) {
            if (!parCh.equals("")){
                if (!passenger.isContainedInParCh(Integer.parseInt(parCh))){
                    passengerFilter.remove(passenger);
                }
            }
        }
    }
    public void statistics(){
        String lines = "";
        lines += "Classes: \n" + classStatist();
        lines+="\n" + genderStatistics();
        lines+="\n" + embarkedStatist();
        lines+="\n" +fareStatistics();
        lines+="\n" +ageStatistics();
        lines+="\n" + relativesStatistics();
        createStatisticFile(lines);
    }

    private void createStatisticFile(String lines){
        File file = new File("statistic.txt");
        try {
            boolean success =  file.createNewFile();
            if (success){
                System.out.println("good");
            }else {
                System.out.println("exist");
            }
        }catch (IOException e){
            System.out.println("cant");
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
            classStatistLinens +="class " + i + ": ";
            classStatistLinens+=Float.toString(result)+"%";
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
            embarkedStatistLinens +=" embarked" + embarkeds.charAt(i) + ": ";
            embarkedStatistLinens+=Float.toString(result)+"%";
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
            genderStatistLinens+=Float.toString(result)+"%";
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
            classStatistLinens+=Float.toString(result)+"%";
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
            classStatistLinens+=Float.toString(result)+"%";
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
            genderStatistLinens += " no relative: ";
            genderStatistLinens += Float.toString(result) + "%"+"\n";
            all = relativesFilter.size();
            survived = howManySurvived(relativesFilter);
            result = (survived / all) * Constants.ONE_HUNDRED_PERCENT;
            genderStatistLinens += " at list one relativee: ";
            genderStatistLinens += Float.toString(result) + "%";

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

    private void createFilterSCV(List<Passenger> passengerFilter){
        this.fileNumber++;
        String fileName = Integer.toString(this.fileNumber);
        File file = new File(fileName+".csv");
        try {
            PrintWriter printWriter = new PrintWriter(file);

            for (Passenger passenger:passengerFilter) {
                printWriter.println(passenger.toString());
            }
            printWriter.close();

        }catch (IOException e){
            e.printStackTrace();
        }


    }


    private  void createPassengerList() {
        String line = "";
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
        Integer survived = Integer.parseInt(dataOfPassenger[1]); ;
        Integer pClass = Integer.parseInt(dataOfPassenger[2]);;
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
