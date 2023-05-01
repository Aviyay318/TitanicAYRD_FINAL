public class Passenger {
    private Integer id;
    private Integer survived;
    private Integer pClass;
    private String name;
    private String gender;
    private Float age;
    private Integer sibSp;
    private Integer parCh;
    private String ticket;
    private Double fare;
    private String cabin;
    private Character embarked;


    public Passenger(Integer id, Integer survived, Integer pClass, String name, String gender, Float age, Integer sibSp, Integer parCh, String ticket, Double fare, String cabin, Character embarked) {

        this.id = id;
        this.survived = survived;
        this.pClass = pClass;
        this.name = name;
        if (genderValidation(gender)){
            this.gender = gender;
        }
        this.age = age;
        this.sibSp = sibSp;
        this.parCh = parCh;
        this.ticket = ticket;
        this.fare = fare;
        this.cabin = cabin;
        this.embarked = embarked;
    }

    private boolean genderValidation (String gender){
        boolean result = false;
        if (gender!=null){
            gender=gender.toLowerCase();
            if (gender.equals(Constants.FEMALE)|| gender.equals(Constants.MALE)){
                result=true;
            }
        }
        return result;
    }
    public String getFormattedName () {
        String fullName="";
        try {
            fullName+=this.name.substring(this.name.indexOf(".")+1,this.name.length()-1) ;
            fullName+=this.name.substring(0, this.name.indexOf(","));
        }catch (Exception e){
            fullName+=this.name.substring(1, this.name.indexOf(" "));
        }
        return removeChars(fullName);
    }
    private String removeChars(String fullName){
        String cleanName="";
        for (int i=0;i<fullName.length();i++){
            if (fullName.charAt(i)>='a'&&fullName.charAt(i)<='z'||fullName.charAt(i)>='A'&&fullName.charAt(i)<='Z'||fullName.charAt(i)== ' '){
                cleanName+=fullName.charAt(i);
            }
        }
        return cleanName;
    }
    public boolean isBiggerId(Integer minId) {
        boolean result = false;
        if (this.id>=minId){
            result=true;
        }
        return result;
    }
    public boolean survived () {
        boolean result =false;
        if (this.survived!=null){
            if (this.survived==1){
                result=true;
            }
        }
        return result;
    }

   public int sumRelatives(){
        return this.parCh+this.sibSp;
   }
    public boolean isContainedInName (String partOfName) {
        boolean result =false;
        if (this.name!=null){
            if (getFormattedName().contains(partOfName)){
                result = true;
            }
        }
        return result;
    }
    public boolean identicalGender (String gender) {
        boolean result =false;
        if (this.gender.equals(gender)){
            result = true;
        }
        return result;
    }

    public boolean identicalAge (Float age) {
        boolean result =false;
        if (this.age!=null){
            if (this.age.equals(age)){
                result = true;
            }
        }
        return result;
    }

    public boolean isContainedInSibSp (String sibSp) {
        boolean result =false;
        if (this.sibSp!=null){
            if (Integer.toString(this.sibSp).contains(sibSp)){
                result = true;
            }
        }
        return result;
    }

    public boolean isContainedInParCh (Integer parCh) {
        boolean result =false;
        if (this.parCh!=null){
            if (Integer.toString(this.parCh).contains(Integer.toString(parCh))){
                result = true;
            }
        }
        return result;
    }

    public boolean isBiggerFare (Float fare) {
        boolean result =false;
        if (this.fare!=null){
            if (this.fare>=fare){
                result = true;
            }
        }
        return result;
    }

    public boolean isContainedInTicket (String partOfTicket) {
        boolean result =false;
        if (this.ticket.contains(partOfTicket)){
            result = true;
        }
        return result;
    }
    public boolean isContainedInCabin (String cabin) {
        boolean result =false;
        if (this.cabin.contains(cabin)){
            result = true;
        }
        return result;
    }

    public int ticketPriceCheck (){  // 10 ,11-30 30+
        int ticketFare = Constants.BETWEEN_ELEVEN_THIRTY;
        if (this.fare<=10){
            ticketFare = Constants.SMALLER_THAN_TEN;
        }else if(this.fare>30){
            ticketFare = Constants.HIGHER_THAN_THIRTY;
        }
        return ticketFare;
    }
    public int ageCheck (){
        int age= Constants.BETWEEN_ZERO_TEN;
        if (this.age!=null){
            if (this.age>=11 && this.age<=20){
                age=Constants.BETWEEN_ELEVEN_TWENTY;
            } else if (this.age>=21 && this.age<=30) {
                age=Constants.BETWEEN_TWENTY_ONE_THIRTY;
            } else if (this.age>=31 && this.age<=40){
                age=Constants.BETWEEN_THIRTY_ONE_FORTY;
            } else if (this.age>=41 && this.age<=50) {
                age=Constants.BETWEEN_FORTY_ONE_FIFTY;
            } else if (this.age>=51) {
                age=Constants.FIFTY_ONE_PLUS;
            }
        }
        return age;

    }

    public boolean identicalPClass (int pClass) {
        boolean result =false;
        if (this.pClass==pClass){
            result = true;
        }
        return result;
    }

    public boolean identicalEmbarked(char embarked) {
        boolean result =false;
        if (this.embarked!=null){
            if (this.embarked==embarked){
                result = true;
            }
        }

        return result;
    }




    public String toString() {
        String output="";
        output+= "Passenger ID: " + this.id +", Status: " + this.survived+ ", Class: " + this.pClass+ ", Name: " + getFormattedName();
        output+= ", Gender: " + this.gender + ", Age: " + this.age + ", Siblings: " + this.sibSp + ", Parents: " + this.parCh + ", Ticket Number: " + this.ticket
                + ", Fare: " + this.fare + ", Cabin: " + this.cabin + " ,Embarked at: " + this.embarked + "\n";
        return output;
    }

}