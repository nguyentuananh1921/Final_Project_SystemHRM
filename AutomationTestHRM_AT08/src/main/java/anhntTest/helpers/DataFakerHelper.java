package anhntTest.helpers;

import net.datafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class DataFakerHelper {

    private Faker faker;
    private String language;
    private static Random random;
    public DataFakerHelper(String language) {
        this.language = language;
        if (language == null || language.isEmpty()){
            this.faker = new Faker();
        }
        else {
            this.faker = new Faker(new Locale(language));
        }
    }

    public String getFullName() {
        return faker.name().fullName();
    }
    public String getFirstName(){
        return faker.name().firstName();
    }
    public String getLastName(){
        return faker.name().lastName();
    }

    public String getFullAddress() {
        return faker.address().fullAddress();
    }
    public String getStreetAddress(){
        return faker.address().streetAddress();
    }
    public String getCity(){
        return  faker.address().city();
    }
    public String getState(){
        return faker.address().state();
    }
    public String getZipCode(){
        return faker.address().zipCode();
    }
    public String getAvailableStatus(){
        return faker.options().option("Active","Banned");
    }
    public String getEmail() {
        return faker.internet().emailAddress();
    }

    public String getPhoneNumber() {
        return faker.phoneNumber().phoneNumber();
    }
    public String getPassword(){
        return  faker.internet().password();
    }
    public String getGender() {
        return faker.options().option("Male", "Female");
    }
    public String getUsername(){
        return faker.name().username();
    }
    public String getCountry(){
        return faker.address().country();
    }
    public String getAvailableCountries(){
        return faker.options().option("Albania","United States","Angola","Singapore","Australia","Afghanistan");
    }
    public String getOptions(String[] arrayOptions){
        random = new Random();
        if (arrayOptions == null || arrayOptions.length == 0) {
            return null; // Hoặc ném ngoại lệ tùy theo yêu cầu của bạn
        }
        int randomIndex = random.nextInt(arrayOptions.length);
        return arrayOptions[randomIndex];
    }
    public String getRandomNumber(int min, int max){
        int number = faker.number().numberBetween(min, max);
        return String.valueOf(number);
    }
    public String getDate(){
        Date date = faker.date().birthday(0, 10);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    public String getDescribe(int length){
        return faker.lorem().sentence(length);
    }
}